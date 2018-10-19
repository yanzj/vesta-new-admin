package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.CommunityReservationListDto;
import com.maxrocky.vesta.application.admin.dto.DeliveryPlanCrmDto;
import com.maxrocky.vesta.application.admin.dto.UserAppointDto;
import com.maxrocky.vesta.application.inf.UserSettingService;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportUtil;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/3/31
 * Time: 10:10
 * Describe:
 */
@Service
public class CommunityDeliveryPlanCRMServiceImpl implements CommunityDeliveryPlanCRMService {

    static {
        // 对日期进行处理
        DateConverter dc = new DateConverter();
        dc.setPattern("yyyy/MM/dd");
        ConvertUtils.register(dc, Date.class);
    }

    @Autowired
    DeliveryPlanCRMRepository delieryPlanCRMRepository;
    @Autowired
    ImgService imgService;
    @Autowired
    private MenuTotalRepository menuTotalRepository;
    @Autowired
    private ClickUserRepository clickUserRepository;
    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    SystemLogRepository systemLogRepository;
    @Autowired
    CommunityCenterRespository communityCenterRespository;

    @Autowired
    HouseInfoRepository houseInfoRepository;
    @Autowired
    HouseProjectRepository houseProjectRepository;
    @Autowired
    UserSettingService userSettingService;
    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;
    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    UserCRMRepository userCRMRepository;


    /**
     * Describe:创建交付计划信息
     * CreateBy:liudongxin
     * CreateOn:2016-04-22 10:01:12
     */
    @Override
    public void create(DeliveryPlanCrmDto deliveryPlanCrmDto) {
    }

    /**
     * CreatedBy:liudongxin
     * Describe:修改交付计划信息
     * ModifyBy:
     */
    @Override
    public void update(DeliveryPlanCrmDto deliveryPlanCrmDto) throws InvocationTargetException, IllegalAccessException {
        //根据id先查询后更新
        DeliveryPlanCrmEntity deliveryPlanCrmEntity = this.getById(deliveryPlanCrmDto.getId());
        deliveryPlanCrmEntity.setNote(deliveryPlanCrmDto.getNote());
        deliveryPlanCrmEntity.setMaxUserAm(deliveryPlanCrmDto.getMaxUserAm());
        deliveryPlanCrmEntity.setMaxUserPm(deliveryPlanCrmDto.getMaxUserPm());
        deliveryPlanCrmEntity.setReservationType(deliveryPlanCrmDto.getReservationType());
        deliveryPlanCrmEntity.setPlanName_alias(deliveryPlanCrmDto.getPlanName_alias());//计划名称_别名
        deliveryPlanCrmEntity.setPlanDate_alias(deliveryPlanCrmDto.getPlanDate_alias());//计划起止时间_别名
        deliveryPlanCrmEntity.setDescription_alias(deliveryPlanCrmDto.getDescription_alias());//计划描述_别名
        deliveryPlanCrmEntity.setFocusAddress_alias(deliveryPlanCrmDto.getFocusAddress_alias());//集中交付地点_别名
        //更新批次状态
        deliveryPlanCrmEntity.setBatchStatus(deliveryPlanCrmDto.getBatchStatus());
        //图片上传
        if (!deliveryPlanCrmDto.getHomePageimgpath().isEmpty()){
            String fileName = imgService.uploadAdminImage(deliveryPlanCrmDto.getHomePageimgpath(), ImgType.ACTIVITY);
            //图片地址特殊处理
            // String urlTitle = "http://211.94.93.223/images/";
            String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
            //fileName = urlTitle + fileName.replace("/opt/image.server/images/images_source/", "");
            fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");

            if (fileName.equals("")) {
                fileName = "默认图";
            }
            if (!fileName.equals(ImageConfig.PIC_OSS_ADMIN_URL)) {
                deliveryPlanCrmEntity.setUrl(fileName);
            }
        }
        deliveryPlanCrmEntity.setType(CommunityImageInfoEntity.type.homePage_type);
        this.delieryPlanCRMRepository.update(deliveryPlanCrmEntity);
    }

    /**
     * 获取交付计划时间段配置列表
     */
    public List<DeliveryPlanReservationTimeRangeEntity> getReservationTimeRangeList(DeliveryPlanCrmDto deliveryPlanCrmDto){
        return delieryPlanCRMRepository.getReservationTimeRangeListByPlan(deliveryPlanCrmDto.getId(),DateUtils.parse(deliveryPlanCrmDto.getReservationDate(),"yyyy-MM-dd"));
    }

    /**
     * 保存交付预约时间段配置
     */
    public void saveReservationTimeRange(DeliveryPlanCrmDto deliveryPlanCrmDto){
        //删除该日期下所有时间段配置
        delieryPlanCRMRepository.deleteReservationTimeRangeByPlanAndDate(deliveryPlanCrmDto.getId(),DateUtils.parse(deliveryPlanCrmDto.getReservationDate(),"yyyy-MM-dd"));
        //设置预约时间段
        DeliveryPlanReservationTimeRangeEntity deliveryPlanReservationTimeRangeEntity = null;
        for (int i=0,length=deliveryPlanCrmDto.getStartDateList().size();i<length;i++){
            deliveryPlanReservationTimeRangeEntity = new DeliveryPlanReservationTimeRangeEntity();
            deliveryPlanReservationTimeRangeEntity.setId(IdGen.uuid());
            deliveryPlanReservationTimeRangeEntity.setDeliveryPlanId(deliveryPlanCrmDto.getId());//交付计划ID
            deliveryPlanReservationTimeRangeEntity.setReservationDate(DateUtils.parse(deliveryPlanCrmDto.getReservationDate(),"yyyy-MM-dd"));//预约日期
            deliveryPlanReservationTimeRangeEntity.setStartTime(deliveryPlanCrmDto.getStartDateList().get(i));//起始时间
            deliveryPlanReservationTimeRangeEntity.setEndTime(deliveryPlanCrmDto.getEndDateList().get(i));//截止时间
            deliveryPlanReservationTimeRangeEntity.setMaxUser(Integer.valueOf(deliveryPlanCrmDto.getMaxUserList().get(i)));//人数配额
            deliveryPlanReservationTimeRangeEntity.setReservationNum(0);//已预约人数
            deliveryPlanReservationTimeRangeEntity.setCreateOn(new Date());
            deliveryPlanReservationTimeRangeEntity.setCreateBy(deliveryPlanCrmDto.getModifyBy());
            delieryPlanCRMRepository.saveOrUpdate(deliveryPlanReservationTimeRangeEntity);
        }
    }

    /**
     * Describe:修改交付计划信息
     * ModifyBy:
     */
    @Override
    public void updateShortName(DeliveryPlanCrmDto deliveryPlanCrmDto) throws InvocationTargetException, IllegalAccessException {
        DeliveryPlanCrmEntity deliveryPlanCrmEntity = this.getById(deliveryPlanCrmDto.getId());
        deliveryPlanCrmEntity.setShortName(deliveryPlanCrmDto.getShortName());
        ActiveTemporaryTimeEntity ActiveTemporaryTime = this.getBytimeId(deliveryPlanCrmDto.getId());
        if (ActiveTemporaryTime == null) {
            return;
        }
        ActiveTemporaryTime.setName(deliveryPlanCrmDto.getShortName());
        ActiveTemporaryTime.setTimeStamp(new Date());
        this.delieryPlanCRMRepository.updatetime(ActiveTemporaryTime);
        this.delieryPlanCRMRepository.update(deliveryPlanCrmEntity);
    }


    /**
     * Describe:根据计划id获取信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public DeliveryPlanCrmEntity getById(String id) {
        return delieryPlanCRMRepository.getById(id);
    }

    @Override
    public ActiveTemporaryTimeEntity getBytimeId(String id) {
        return delieryPlanCRMRepository.getBytimeId(id);
    }

    /**
     * Describe:根据计划id获取信息:分页查询批次信息
     *
     * @param deliveryPlanCrmDto
     * @param webPage
     * @return DeliveryPlanCrmEntity
     * @throws Exception
     */
    @Override
    public List<DeliveryPlanCrmDto> queryDeliveryPlanCrm(DeliveryPlanCrmDto deliveryPlanCrmDto, WebPage webPage) throws Exception {
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = deliveryPlanCrmDto.getRoleScopeList();
        for (Map<String, Object> roleScope : roleScopeList) {
            String scopeType = (String) roleScope.get("scopeType");
            if (scopeType.equals("1")) {
                //城市级别(分公司)
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(roleScope.get("scopeId").toString());
                for (Object[] project : projectList) {
                    if (!roleScopes.contains(project[0].toString())) {
                        roleScopes += "'" + project[0].toString() + "',";
                    }
                }
            } else if (scopeType.equals("2")) {
                //项目级别
                if (!roleScopes.contains(roleScope.get("scopeId").toString())) {
                    roleScopes += "'" + roleScope.get("scopeId") + "',";
                }
            } else if (scopeType.equals("0")) {
                //全部城市
                roleScopes += "all,";
            }
        }

        List<DeliveryPlanCrmDto> deliveryPlanCrmDtos = new ArrayList<DeliveryPlanCrmDto>();
        //查询条件
        DeliveryPlanCrmEntity deliveryPlanCrmEntity = new DeliveryPlanCrmEntity();
        /**=========================================================**/
        // 对日期进行处理
        /*DateConverter dc = new DateConverter();
        dc.setPattern("yyyy/MM/dd");
        ConvertUtils.register(dc, Date.class);*/
        if (deliveryPlanCrmDto != null) {
            if (null != deliveryPlanCrmDto.getPlanStart() && "".equals(deliveryPlanCrmDto.getPlanStart())) {
                deliveryPlanCrmDto.setPlanStart(null);
            }
            if (null != deliveryPlanCrmDto.getPlanEnd() && "".equals(deliveryPlanCrmDto.getPlanEnd())) {
                deliveryPlanCrmDto.setPlanEnd(null);
            }
            deliveryPlanCrmEntity.setPlanStart(
                    deliveryPlanCrmDto.getPlanStart() != null && !deliveryPlanCrmDto.getPlanStart().isEmpty() ?
                            DateUtils.parse(deliveryPlanCrmDto.getPlanStart() + " 00:00:00") : null);
            deliveryPlanCrmEntity.setPlanEnd(
                    deliveryPlanCrmDto.getPlanEnd() != null && !deliveryPlanCrmDto.getPlanEnd().isEmpty() ?
                            DateUtils.parse(deliveryPlanCrmDto.getPlanEnd() + " 59:59:59") : null);
            deliveryPlanCrmEntity.setPlanName(deliveryPlanCrmDto.getPlanName());
            deliveryPlanCrmEntity.setBatchStatus(deliveryPlanCrmDto.getBatchStatus());
//            deliveryPlanCrmEntity.setProjectNum(deliveryPlanCrmDto.getProjectNum());
            //追加区域项目检索条件-Wyd20170406
            //如果检索项目不为Null,直接set deliveryPlanCrmEntity的projectNum
            //如果检索项目为Null,城市不为Null,set deliveryPlanCrmEntity的projectNum为该城市下所有项目Code,逗号间隔
            if (null != deliveryPlanCrmDto.getProjectNum() && !"0".equals(deliveryPlanCrmDto.getProjectNum()) && !"".equals(deliveryPlanCrmDto.getProjectNum())){
                deliveryPlanCrmEntity.setProjectNum("'" + deliveryPlanCrmDto.getProjectNum() + "',");
            }else if (null != deliveryPlanCrmDto.getScopeId() && !"0".equals(deliveryPlanCrmDto.getScopeId()) && !"".equals(deliveryPlanCrmDto.getScopeId())){
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(deliveryPlanCrmDto.getScopeId());
                String projectIds = "";
                for (Object[] project : projectList) {
                    projectIds += "'" + project[0].toString() + "',";
                }
                deliveryPlanCrmEntity.setProjectNum(projectIds);
            }
        }
        // 初始化查询条件数据
        /*BeanUtils.copyProperties(deliveryPlanCrmEntity, deliveryPlanCrmDto);*/
        /**=========================================================**/
    /*    //设置集中交付时间
        if (!StringUtil.isEmpty(deliveryPlanCrmDto.getPlanStart())) {
            deliveryPlanCrmEntity.setPlanStart(DateUtils.parse(deliveryPlanCrmDto.getPlanStart(), "yyyy/MM/dd"));
        }
        if (!StringUtil.isEmpty(deliveryPlanCrmDto.getPlanEnd())) {
            deliveryPlanCrmEntity.setPlanEnd(DateUtils.parse(deliveryPlanCrmDto.getPlanEnd(), "yyyy/MM/dd"));
        }*/


        // 转换参数为 DeliveryPlanCrmEntity
        List<DeliveryPlanCrmEntity> deliveryPlanCrmEntityList =
                delieryPlanCRMRepository.queryDeliveryPlanCrmEntity(deliveryPlanCrmEntity, webPage, roleScopes);

        //LIST封装为DTO进行返回
        for (DeliveryPlanCrmEntity planCrmEntity : deliveryPlanCrmEntityList) {
            DeliveryPlanCrmDto temp = new DeliveryPlanCrmDto();//批次管理DTO
            BeanUtils.copyProperties(temp, planCrmEntity);
            //拼接集中交付时间
            temp.setPlanStart(planCrmEntity.getPlanStart().toString().substring(0, 10));
            temp.setPlanEnd(planCrmEntity.getPlanEnd().toString().substring(0, 10));
            temp.setModifyTime(DateUtils.format(planCrmEntity.getModifyDate()));
            //项目编号转换为项目名称
            String proName = this.delieryPlanCRMRepository.getProNameByProNum(planCrmEntity.getProjectNum());
            temp.setProjectName(proName);
            deliveryPlanCrmDtos.add(temp);
        }

        return deliveryPlanCrmDtos;
    }

    @Override
    public List<DeliveryPlanCrmDto> queryDeliveryPlanCrm_1(DeliveryPlanCrmDto deliveryPlanCrmDto, WebPage webPage) throws Exception {
        List<DeliveryPlanCrmDto> deliveryPlanCrmDtos = new ArrayList<DeliveryPlanCrmDto>();
        //查询条件
        DeliveryPlanCrmEntity deliveryPlanCrmEntity = new DeliveryPlanCrmEntity();
        /**=========================================================**/
        // 对日期进行处理
        /*DateConverter dc = new DateConverter();
        dc.setPattern("yyyy/MM/dd");
        ConvertUtils.register(dc, Date.class);*/
        if (deliveryPlanCrmDto != null) {
            if (null != deliveryPlanCrmDto.getPlanStart() && "".equals(deliveryPlanCrmDto.getPlanStart())) {
                deliveryPlanCrmDto.setPlanStart(null);
            }
            if (null != deliveryPlanCrmDto.getPlanEnd() && "".equals(deliveryPlanCrmDto.getPlanEnd())) {
                deliveryPlanCrmDto.setPlanEnd(null);
            }
            deliveryPlanCrmEntity.setPlanStart(
                    deliveryPlanCrmDto.getPlanStart() != null && !deliveryPlanCrmDto.getPlanStart().isEmpty() ?
                            DateUtils.parse(deliveryPlanCrmDto.getPlanStart() + " 00:00:00") : null);
            deliveryPlanCrmEntity.setPlanEnd(
                    deliveryPlanCrmDto.getPlanEnd() != null && !deliveryPlanCrmDto.getPlanEnd().isEmpty() ?
                            DateUtils.parse(deliveryPlanCrmDto.getPlanEnd() + " 59:59:59") : null);
            deliveryPlanCrmEntity.setPlanName(deliveryPlanCrmDto.getPlanName());
            deliveryPlanCrmEntity.setBatchStatus(deliveryPlanCrmDto.getBatchStatus());
//            deliveryPlanCrmEntity.setProjectNum(deliveryPlanCrmDto.getProjectNum());
            //追加区域项目检索条件-Wyd20170406
            //如果检索项目不为Null,直接set deliveryPlanCrmEntity的projectNum
            //如果检索项目为Null,城市不为Null,set deliveryPlanCrmEntity的projectNum为该城市下所有项目Code,逗号间隔
            if (null != deliveryPlanCrmDto.getProjectNum() && !"0".equals(deliveryPlanCrmDto.getProjectNum()) && !"".equals(deliveryPlanCrmDto.getProjectNum())){
                deliveryPlanCrmEntity.setProjectNum("'" + deliveryPlanCrmDto.getProjectNum() + "',");
            }else if (null != deliveryPlanCrmDto.getScopeId() && !"0".equals(deliveryPlanCrmDto.getScopeId()) && !"".equals(deliveryPlanCrmDto.getScopeId())){
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(deliveryPlanCrmDto.getScopeId());
                String projectIds = "";
                for (Object[] project : projectList) {
                    projectIds += "'" + project[0].toString() + "',";
                }
                deliveryPlanCrmEntity.setProjectNum(projectIds);
            }
        }

        // 转换参数为 DeliveryPlanCrmEntity
        List<DeliveryPlanCrmEntity> deliveryPlanCrmEntityList =
                delieryPlanCRMRepository.queryDeliveryPlanCrmEntity(deliveryPlanCrmEntity, webPage, "");

        //LIST封装为DTO进行返回
        for (DeliveryPlanCrmEntity planCrmEntity : deliveryPlanCrmEntityList) {
            DeliveryPlanCrmDto temp = new DeliveryPlanCrmDto();//批次管理DTO
            BeanUtils.copyProperties(temp, planCrmEntity);
            //拼接集中交付时间
            temp.setPlanStart(planCrmEntity.getPlanStart().toString().substring(0, 10));
            temp.setPlanEnd(planCrmEntity.getPlanEnd().toString().substring(0, 10));
            temp.setModifyTime(DateUtils.format(planCrmEntity.getModifyDate()));
            //项目编号转换为项目名称
            String proName = this.delieryPlanCRMRepository.getProNameByProNum(planCrmEntity.getProjectNum());
            temp.setProjectName(proName);
            deliveryPlanCrmDtos.add(temp);
        }

        return deliveryPlanCrmDtos;
    }

    /**
     * 查询所有项目
     *
     * @return
     */
    @Override
    public LinkedHashMap<String, String> listProject() {
        LinkedHashMap<String, String> linkedHashMap = this.delieryPlanCRMRepository.listProject();
        return linkedHashMap;
    }

    /**
     * 查询所有批次
     *
     * @return
     */
    @Override
    public List<String> listBatch() {
        return this.delieryPlanCRMRepository.listBatch();
    }

    /**
     * 状态更新
     */
    @Override
    public void updateStatus(DeliveryPlanCrmEntity deliveryPlanCrmEntity) {
        this.delieryPlanCRMRepository.update(deliveryPlanCrmEntity);
    }

    @Override
    public void updateStatuss(UserPropertyStaffEntity user, DeliveryPlanCrmEntity deliveryPlanCrmEntity) {
        if (deliveryPlanCrmEntity.getBatchStatus() == 1) {
            //增加日志
            InfoReleaseEntity infoReleaseLog = new InfoReleaseEntity();
            infoReleaseLog.setLogId(IdGen.uuid());
            infoReleaseLog.setLogTime(new Date());//注册日期
            infoReleaseLog.setUserName(user.getStaffName());//用户昵称
            infoReleaseLog.setUserMobile(user.getMobile());//手机号
            infoReleaseLog.setThisSection("服务");//版块
            infoReleaseLog.setThisFunction("交房计划管理");//功能
            infoReleaseLog.setThisType("发布");//操作类型
            infoReleaseLog.setLogContent(deliveryPlanCrmEntity.getPlanName());//发布内容
            String projectCode = deliveryPlanCrmEntity.getProjectNum();
            HouseProjectEntity houseProjectEntity = houseProjectRepository.getProjectByCode(projectCode);
            if (houseProjectEntity != null) {
                infoReleaseLog.setAsscommunity(houseProjectEntity.getName());//关联社区;
            } else {
                infoReleaseLog.setAsscommunity("");//关联社区;
            }
            systemLogRepository.addInfoReleaseLog(infoReleaseLog);
        }
        if (deliveryPlanCrmEntity.getBatchStatus() == 0) {
            //增加日志
            InfoReleaseEntity infoReleaseLog = new InfoReleaseEntity();
            infoReleaseLog.setLogId(IdGen.uuid());
            infoReleaseLog.setLogTime(new Date());//注册日期
            infoReleaseLog.setUserName(user.getStaffName());//用户昵称
            infoReleaseLog.setUserMobile(user.getMobile());//手机号
            infoReleaseLog.setThisSection("服务");//版块
            infoReleaseLog.setThisFunction("交房计划管理");//功能
            infoReleaseLog.setThisType("取消发布");//操作类型
            infoReleaseLog.setLogContent(deliveryPlanCrmEntity.getPlanName());//发布内容
            String projectCode = deliveryPlanCrmEntity.getProjectNum();
            HouseProjectEntity houseProjectEntity = houseProjectRepository.getProjectByCode(projectCode);
            if (houseProjectEntity != null) {
                infoReleaseLog.setAsscommunity(houseProjectEntity.getName());//关联社区;
            } else {
                infoReleaseLog.setAsscommunity("");//关联社区;
            }
            systemLogRepository.addInfoReleaseLog(infoReleaseLog);
        }
        this.delieryPlanCRMRepository.update(deliveryPlanCrmEntity);
    }

    /**
     * 根据计划id查询房屋列表
     *
     * @return 以逗号分隔的字符串
     */
    @Override
    public String getHouseListByPlanId(String id) {
        List<String> houseListByPlanId = this.delieryPlanCRMRepository.getHouseListByPlanId(id);
        StringBuffer sb = new StringBuffer();
        for (String houseAddress : houseListByPlanId) {
            sb.append(houseAddress + ",");
        }
        if (StringUtil.isEmpty(sb.toString())) {
            return "";
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

    @Override
    public List<UserAppointDto> searchHouseListByPlanId(String id, WebPage webPage, DeliveryPlanCrmEntity deliveryPlanCrmEntity) {
        List<UserAppointDto> userAppointDtoList = new ArrayList<UserAppointDto>();
        if (id != null) {
            /*
            List<HouseInfoEntity> houseInfoEntityList = delieryPlanCRMRepository.getHouseInfoListByPlanId(id, webPage);
            if (houseInfoEntityList != null && houseInfoEntityList.size() > 0) {
                for (HouseInfoEntity houseInfoEntity : houseInfoEntityList) {
                    UserAppointDto userAppointDto = new UserAppointDto();
                    userAppointDto.setRoomAddress(houseInfoEntity.getAddress());
                    userAppointDto.setPlanName(deliveryPlanCrmEntity.getPlanName());
                    if (houseInfoEntity.getMemberId() != null && !houseInfoEntity.getMemberId().equals("")) {
                        UserCRMEntity userCRMEntity = userCRMRepository.getByMemberId(houseInfoEntity.getMemberId());

                        if (userCRMEntity != null) {
                            userAppointDto.setIdCard(userCRMEntity.getIdCard());
                            userAppointDto.setUserMobile(userCRMEntity.getMobile());
                            userAppointDto.setUserName(userCRMEntity.getRealName());

                            CommunityReservationListEntity communityReservationListEntity = delieryPlanCRMRepository.getReservationByUserId(userCRMEntity.getUserId());
                            if (communityReservationListEntity != null) {
                                if (communityReservationListEntity.getReservationDate() != null) {
                                    userAppointDto.setAppointUserTime(DateUtils.format(communityReservationListEntity.getReservationDate(), DateUtils.FORMAT_SHORT));
                                }
                                if (communityReservationListEntity.getAmOrPm() != null && !StringUtil.isEmpty(communityReservationListEntity.getAmOrPm())) {
                                    if (communityReservationListEntity.getAmOrPm().equals("0")) {
                                        userAppointDto.setAmOrPm("上午");
                                    } else if (communityReservationListEntity.getAmOrPm().equals("1")) {
                                        userAppointDto.setAmOrPm("下午");
                                    }
                                }
                            }

                        }
                    }
                    userAppointDtoList.add(userAppointDto);
                }
            }
            */
            List<Map<String, Object>> planHouseList = delieryPlanCRMRepository.getPlanHouseListByPlanId(id, webPage);
            if (null != planHouseList && planHouseList.size() > 0){
                Map<String,Object> planHouseMap = null;
                UserAppointDto userAppointDto = null;
                for (int i = 0,length = planHouseList.size(); i < length; i++){
                    planHouseMap = planHouseList.get(i);
                    userAppointDto = new UserAppointDto();
                    userAppointDto.setPlanName(deliveryPlanCrmEntity.getPlanName());
                    if (null != planHouseMap.get("address")){
                        userAppointDto.setRoomAddress(planHouseMap.get("address").toString());
                    }
                    if (null != planHouseMap.get("idCard")){
                        userAppointDto.setIdCard(planHouseMap.get("idCard").toString());
                    }
                    if (null != planHouseMap.get("mobile")){
                        userAppointDto.setUserMobile(planHouseMap.get("mobile").toString());
                    }
                    if (null != planHouseMap.get("realName")){
                        userAppointDto.setUserName(planHouseMap.get("realName").toString());
                    }
                    if (null != planHouseMap.get("userId")){
                        CommunityReservationListEntity communityReservationListEntity = delieryPlanCRMRepository.getReservationByUserId(planHouseMap.get("userId").toString());
                        if (communityReservationListEntity != null) {
                            if (communityReservationListEntity.getReservationDate() != null) {
                                userAppointDto.setAppointUserTime(DateUtils.format(communityReservationListEntity.getReservationDate(), DateUtils.FORMAT_SHORT));
                            }
                            if (communityReservationListEntity.getAmOrPm() != null && !StringUtil.isEmpty(communityReservationListEntity.getAmOrPm())) {
                                if (communityReservationListEntity.getAmOrPm().equals("0")) {
                                    userAppointDto.setAmOrPm("上午");
                                } else if (communityReservationListEntity.getAmOrPm().equals("1")) {
                                    userAppointDto.setAmOrPm("下午");
                                }
                            }
                        }
                    }
                    userAppointDtoList.add(userAppointDto);
                }
            }
        }
        return userAppointDtoList;
    }

    /**
     * 通过交房计划ID获取交房详情信息列表 WeiYangDong_2017-01-05
     */
    public List<Map<String,Object>> getPlanHouseListByPlanId(String id,WebPage webPage){
        return delieryPlanCRMRepository.getPlanHouseListByPlanId(id, webPage);
    }

    //=------------------------前台-----------------------------------------------

    /**
     * 查询批次数据返回给前台
     *
     * @return
     */
    @Override
    public ApiResult queryAllDeliveryPlanCrmToIntrface(String userId, String projectCode, String cityId, WebPage webPage) throws GeneralException {

        try {
            ApiResult apiResult = null;
            DeliveryPlanCrmEntity deliveryPlanCrmEntity = new DeliveryPlanCrmEntity();
            deliveryPlanCrmEntity.setBatchStatus(1);
            /* 城市/项目区分 */
            List<DeliveryPlanCrmEntity> deliveryPlanCrmEntitys = new ArrayList<>();

            if (null == cityId || "".equals(cityId)) {
                //添加区域项目查询
                deliveryPlanCrmEntity.setProjectNum(projectCode);
                deliveryPlanCrmEntitys = this.delieryPlanCRMRepository.findAll(deliveryPlanCrmEntity, webPage);
            } else {
                List<DeliveryPlanCrmEntity> all = this.delieryPlanCRMRepository.findAll(deliveryPlanCrmEntity, webPage);

                //检索该城市下的所有项目
                List<Object[]> list = announcementRepository.listProject(cityId);
                for (DeliveryPlanCrmEntity deliveryPlanCrm : all) {
                    //遍历该结果集，判断其项目是否属于该城市
                    String projectNum = deliveryPlanCrm.getProjectNum();
                    for (int i = 0; i < list.size(); i++) {
                        Object[] objects = list.get(i);
                        String proCode = (String) objects[0];
                        if (projectNum.equals(proCode)) {
                            deliveryPlanCrmEntitys.add(deliveryPlanCrm);
                            continue;
                        }
                    }
                }
            }
//            deliveryPlanCrmEntitys = this.delieryPlanCRMRepository.findAll(deliveryPlanCrmEntity);

            //TODO,下面的循环可以优化,项目名称查询移出循环,用户项目下房产查询移出循环   Wyd_20170303
            //遍历，将时间类型转换
            List<DeliveryPlanCrmDto> deliveryPlanCrmDtos = new ArrayList<DeliveryPlanCrmDto>();
            DeliveryPlanCrmDto temp = null;
            for (DeliveryPlanCrmEntity planCrmEntity : deliveryPlanCrmEntitys) {
                temp = new DeliveryPlanCrmDto();
                /* 追加返回isDelivery字段_Wyd_2016-09-26 */
                //是否可预约
                temp.setIsDelivery(0);    //默认不可预约
                String projectName = "";
                if (null != projectCode && !"".equals(projectCode)){
                    projectName = this.delieryPlanCRMRepository.getProNameByProNum(projectCode);
                }else{
                    projectName = this.delieryPlanCRMRepository.getProNameByProNum(planCrmEntity.getProjectNum());
                }
                //用户房产
                if (!userId.equals("1")){
                    List<String> userHouseList = delieryPlanCRMRepository.queryPersonalHouseByPlan(userId, projectName);
                    //交付房屋列表
                    String houseString = getHouseListByPlanId(planCrmEntity.getId());
                    String[] houseStrings = houseString.split(",");
                    List<String> houseList = Arrays.asList(houseStrings);
                    if (null != userHouseList) {
                        for (String userHouse : userHouseList) {
                            for (String house : houseList) {
                                if (userHouse.equals(house)) {
                                    temp.setIsDelivery(1);//可预约
                                    //可预约状态，判断用户是否已经预约
                                    CommunityReservationListEntity communityReservationListEntity = new CommunityReservationListEntity();
                                    communityReservationListEntity.setUserId(userId);
                                    communityReservationListEntity.setPlanName(planCrmEntity.getPlanName());
                                    Integer status = delieryPlanCRMRepository.queryReservationStatus(communityReservationListEntity);
                                    if(null != status && status == 1){
                                        //已预约
                                        temp.setIsDelivery(2);
                                    }
                                }
                            }
                        }
                    }
                }
                /*===================*/
                BeanUtils.copyProperties(temp, planCrmEntity);
                temp.setPlanStart(DateUtils.format(planCrmEntity.getPlanStart(), DateUtils.FORMAT_SHORT));
                temp.setPlanEnd(DateUtils.format(planCrmEntity.getPlanEnd(), DateUtils.FORMAT_SHORT));
                temp.setDealStart(DateUtils.format(planCrmEntity.getDealStart(), DateUtils.FORMAT_SHORT));
                temp.setDealEnd(DateUtils.format(planCrmEntity.getDealEnd(), DateUtils.FORMAT_SHORT));
                temp.setAppoint(DateUtils.format(planCrmEntity.getAppoint(), DateUtils.FORMAT_SHORT));
//                String projectName = this.delieryPlanCRMRepository.getProNameByProNum(planCrmEntity.getProjectNum());
                temp.setProjectName(projectName);
                deliveryPlanCrmDtos.add(temp);
            }
            apiResult = new SuccessApiResult(deliveryPlanCrmDtos);
            //调用点击人统计
            String dateNow = DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
            ClickUsersEntity clickUserEntity = clickUserRepository.getTotalInfo(dateNow, "5", userId);
            if (clickUserEntity == null) {
                ClickUsersEntity clickUser = new ClickUsersEntity();
                clickUser.setId(IdGen.uuid());
                clickUser.setCreateDate(new Date());
                clickUser.setClicks(1);
                clickUser.setUserId(userId);
                clickUser.setForeignId("5");
                clickUserRepository.create(clickUser);
            } else {
                clickUserEntity.setClicks(clickUserEntity.getClicks() + 1);
                clickUserRepository.update(clickUserEntity);
            }

            return apiResult;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "查询批次数据返回前台错误，InvocationTargetException");
        }
    }

    /**
     * 查询批次数据
     *
     * @param planId 计划id
     * @return
     */
    @Override
    public ApiResult

    queryBatchDetailById(String planId) throws GeneralException {
        try {
            ApiResult apiResult = null;

            //获取计划详情
            DeliveryPlanCrmEntity deliveryPlanCrmEntity = this.delieryPlanCRMRepository.getById(planId);

            //转换时间格式回显DTO，查询项目名称回显
            DeliveryPlanCrmDto deliveryPlanCrmDto = new DeliveryPlanCrmDto();
            BeanUtils.copyProperties(deliveryPlanCrmDto, deliveryPlanCrmEntity);

            String projectName = this.delieryPlanCRMRepository.getProNameByProNum(deliveryPlanCrmEntity.getProjectNum());
            deliveryPlanCrmDto.setProjectName(projectName);
            //格式化时间
            deliveryPlanCrmDto.setPlanStart(deliveryPlanCrmEntity.getPlanStart().toString().substring(0, 10));
            deliveryPlanCrmDto.setPlanEnd(deliveryPlanCrmEntity.getPlanEnd().toString().substring(0, 10));

            apiResult = new SuccessApiResult(deliveryPlanCrmDto);
            return apiResult;

        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "查询批次数据错误，InvocationTargetException");
        }
    }

    @Override
    public ApiResult queryBatchDetailById(String planId, String userId) throws GeneralException {
        try {
            ApiResult apiResult = null;

            //获取计划详情
            DeliveryPlanCrmEntity deliveryPlanCrmEntity = this.delieryPlanCRMRepository.getById(planId);

            //转换时间格式回显DTO，查询项目名称回显
            DeliveryPlanCrmDto deliveryPlanCrmDto = new DeliveryPlanCrmDto();
            BeanUtils.copyProperties(deliveryPlanCrmDto, deliveryPlanCrmEntity);

            String projectName = this.delieryPlanCRMRepository.getProNameByProNum(deliveryPlanCrmEntity.getProjectNum());
            deliveryPlanCrmDto.setProjectName(projectName);
            //格式化时间
            deliveryPlanCrmDto.setPlanStart(deliveryPlanCrmEntity.getPlanStart().toString().substring(0, 10));
            deliveryPlanCrmDto.setPlanEnd(deliveryPlanCrmEntity.getPlanEnd().toString().substring(0, 10));

            //是否可预约
            deliveryPlanCrmDto.setIsDelivery(0);    //默认不可预约
            if (!"".equals(userId)){
                //用户房产
                List<String> userHouseList = delieryPlanCRMRepository.queryPersonalHouseByPlan(userId, projectName);
                //交付房屋列表
                String houseString = getHouseListByPlanId(planId);
                String[] houseStrings = houseString.split(",");
                List<String> houseList = Arrays.asList(houseStrings);
                if (null != userHouseList && userHouseList.size() > 0) {
                    for (String userHouse : userHouseList) {
                        for (String house : houseList) {
                            if (userHouse.equals(house)) {
                                deliveryPlanCrmDto.setIsDelivery(1);//可预约
                            }
                        }
                    }
                }
            }

            apiResult = new SuccessApiResult(deliveryPlanCrmDto);

            UserVisitLogEntity userVisitLogEntity = new UserVisitLogEntity();
            UserInfoEntity userInfo = this.communityCenterRespository.get(UserInfoEntity.class, userId);
            if (userInfo != null) {
                userVisitLogEntity.setLogId(IdGen.uuid());
                userVisitLogEntity.setLogTime(new Date());//注册日期
                userVisitLogEntity.setUserName(userInfo.getNickName());//用户昵称
                userVisitLogEntity.setUserType(userInfo.getUserType());//用户类型
                userVisitLogEntity.setUserMobile(userInfo.getMobile());//手机号
                userVisitLogEntity.setSourceFrom(userInfo.getSourceType());//来源
                userVisitLogEntity.setThisSection("服务");
                userVisitLogEntity.setThisFunction("交付预约");
                userVisitLogEntity.setLogContent(deliveryPlanCrmEntity.getPlanName());
                UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(userInfo.getUserId());
                if (null != userSettingEntity) {
                    userVisitLogEntity.setProjectId(userSettingEntity.getProjectName());
                } else {
                    userVisitLogEntity.setProjectId("");//项目
                }
                systemLogRepository.addUserVisitLog(userVisitLogEntity);
            }

            return apiResult;

        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "查询批次数据错误，InvocationTargetException");
        }
    }

    /**
     * 查询用户拥有房屋列表
     *
     * @param userId      用户id
     * @param ProjectName 项目名称
     * @return
     */
    @Override
    public ApiResult queryPersonalHouseByPlan(String userId, String ProjectName) throws GeneralException {
        try {
            List<String> list = this.delieryPlanCRMRepository.queryPersonalHouseByPlan(userId, ProjectName);
            return new SuccessApiResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "计划详情错误，InvocationTargetException");
        }
    }

    /**
     * 查詢用戶預約時間
     *
     * @param userId   用户id
     * @param planName 批次名称
     * @return
     */
    @Override
    public String queryReservationDate(String userId, String planName) {

        return this.delieryPlanCRMRepository.queryReservationDate(userId, planName);
    }

    /**
     * 新增预约单
     *
     * @param userInfo                    用户信息
     * @param communityReservationListDto 前台传递：批次名称，用户id，项目名称
     */
    @Override
    public void saveReservation(UserInfoEntity userInfo, CommunityReservationListDto communityReservationListDto) throws GeneralException {

        //获取房屋列表，遍历进行添加或者修改
        List<String> houseList = null;
        try {
            houseList = this.delieryPlanCRMRepository.queryPersonalHouseByPlan(userInfo.getUserId(), communityReservationListDto.getProjectName());
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "请检查用户id与项目名称");
        }
        //一次查询完，下面进行添加
        List<Object[]> objects = this.delieryPlanCRMRepository.queryHouseAddress(userInfo.getUserId(), communityReservationListDto.getProjectName());
        if (houseList.size() > 0) {
            CommunityReservationListEntity communityReservation = new CommunityReservationListEntity();
            //批量添加/修改
            for (int i = 0; i < houseList.size(); i++) {
                //根据增加修改标示进行批量增加
                communityReservation.setId(IdGen.uuid());
                //设置批次名称
                communityReservation.setPlanName(communityReservationListDto.getPlanName());
                //设置预约时间
                communityReservation.setReservationDate(DateUtils.parse(communityReservationListDto.getReservationDate(), "yyyy/MM/dd"));
                //设置时间段,上午为0，下午为1
                communityReservation.setAmOrPm(communityReservationListDto.getAmOrPm());
                //设置用户信息
                communityReservation.setUserName(userInfo.getRealName());
                communityReservation.setMobile(userInfo.getMobile());
                communityReservation.setIdCard(userInfo.getIdCard());
                communityReservation.setUserId(userInfo.getUserId());
                //设置房产信息
                communityReservation.setProjectName(objects.get(i)[0].toString());
                //备案楼号:BUILDING_RECORD/预售楼号:BUILDING_SALE
                //如果备案楼号为空,则显示预售楼号
                Object buildingRecord = objects.get(i)[1];
                Object buildingSale = objects.get(i)[4];
                if (null != buildingRecord && !"".equals(buildingRecord)){
                    communityReservation.setBuildingName(buildingRecord.toString());
                }else if (null != buildingSale){
                    communityReservation.setBuildingName(buildingSale.toString());
                }else{
                    communityReservation.setBuildingName("");
                }
                //单元
                Object unit = objects.get(i)[2];
                if (null != unit){
                    communityReservation.setUnitName(unit.toString());
                }else{
                    communityReservation.setUnitName("");
                }
                communityReservation.setRoomName(objects.get(i)[3].toString());
                communityReservation.setReservationStatus("1");
                communityReservation.setCreateDate(new Date());
                communityReservation.setStatus("1");
                this.delieryPlanCRMRepository.saveReservation(communityReservation);
            }
        } else {
            throw new GeneralException("100", "没有用户在该房产下的房产信息，请确认用户信息");
        }
    }

    /**
     * 更新预约单
     *
     * @param userInfo                    用户信息
     * @param communityReservationListDto 前台传递：批次名称，用户id，项目名称
     */
    @Override
    public void updateReservation(UserInfoEntity userInfo, CommunityReservationListDto communityReservationListDto) {
        CommunityReservationListEntity communityReservation = new CommunityReservationListEntity();
        //设置预约时间
        communityReservation.setReservationDate(DateUtils.parse(communityReservationListDto.getReservationDate(), "yyyy/MM/dd"));
        //设置时间段,上午为0，下午为1
        communityReservation.setAmOrPm(communityReservationListDto.getAmOrPm());
        //设置用户id
        communityReservation.setUserId(userInfo.getUserId());
        //设置批次信息
        communityReservation.setPlanName(communityReservationListDto.getPlanName());
        //设置修改备注
        communityReservation.setOperatorDate(new Date());
        communityReservation.setOperator(userInfo.getUserId());
        //设置预约状态
        communityReservation.setReservationStatus("1");

        this.delieryPlanCRMRepository.updateReservation(communityReservation);
    }

    /**
     * 取消预约
     *
     * @param userInfo                    用户信息
     * @param communityReservationListDto 前台传递：批次名称，用户id，项目名称
     */
    @Override
    public void cancelReservation(UserInfoEntity userInfo, CommunityReservationListDto communityReservationListDto) {
        CommunityReservationListEntity communityReservation = new CommunityReservationListEntity();
        //设置用户id
        communityReservation.setUserId(userInfo.getUserId());
        //设置批次信息
        communityReservation.setPlanName(communityReservationListDto.getPlanName());

        //设置修改备注
        communityReservation.setOperatorDate(new Date());
        communityReservation.setOperator(userInfo.getUserId());
        //设置预约状态
        communityReservation.setReservationStatus("0");

        this.delieryPlanCRMRepository.cancelReservation(communityReservation);
    }

    /**
     * 查询预约状态
     *
     * @param userInfo                    用户信息
     * @param communityReservationListDto 前台传递：批次名称，用户id，项目名称
     */
    @Override
    public Integer queryReservationStatus(UserInfoEntity userInfo, CommunityReservationListDto communityReservationListDto) {
        CommunityReservationListEntity communityReservation = new CommunityReservationListEntity();
        //设置用户id
        communityReservation.setUserId(userInfo.getUserId());
        //设置批次信息
        communityReservation.setPlanName(communityReservationListDto.getPlanName());
//        //预约时间
//        communityReservation.setReservationDate(DateUtils.parse(communityReservationListDto.getReservationDate(), "yyyy/MM/dd"));
//        //预约状态
//        communityReservation.setReservationStatus("1");
        return this.delieryPlanCRMRepository.queryReservationStatus(communityReservation);
    }

    /**
     * 查询项目下所有批次信息
     *
     * @param projectNum
     * @return
     */

    @Override
    public List<String> queryBatchByProjectNum(String projectNum) {
        return this.delieryPlanCRMRepository.queryBatchByProjectNum(projectNum);
    }

    /**-------------------------------------管理系统端：业主预约管理接口----------------------------------------------*/
    /**
     * CreatedBy:liudongxin
     * Describe:获取业主预约信息列表
     * ModifyBy:
     */
    @Override
    public List<UserAppointDto> reservationList(UserAppointDto userAppointDto, WebPage webPage) {
        //检索条件
        CommunityReservationListEntity userAppoint = new CommunityReservationListEntity();
        if (userAppointDto != null) {
            //项目
//            userAppoint.setProjectName(userAppointDto.getProjectName());
            //追加区域项目检索条件-Wyd20170406
            //如果检索项目不为Null,直接set userAppoint的projectName
            //如果检索项目为Null,城市不为Null,set userAppoint的projectName为该城市下所有项目Code,逗号间隔
            if (null != userAppointDto.getProjectName() && !"全部项目".equals(userAppointDto.getProjectName()) && !"".equals(userAppointDto.getProjectName())){
                userAppoint.setProjectName("'" + userAppointDto.getProjectName() + "',");
            }else if (null != userAppointDto.getScopeId() && !"0".equals(userAppointDto.getScopeId()) && !"".equals(userAppointDto.getScopeId())){
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(userAppointDto.getScopeId());
                String projectIds = "";
                for (Object[] project : projectList) {
                    projectIds += "'" + project[1].toString() + "',";
                }
                userAppoint.setProjectName(projectIds);
            }
            userAppoint.setBuildingName(userAppointDto.getBuildName());//楼号
            userAppoint.setUnitName(userAppointDto.getUnitName());//单元
            userAppoint.setPlanName(userAppointDto.getPlanName());//交付批次
            userAppoint.setAmOrPm(userAppointDto.getAmOrPm());//时间段
            userAppoint.setMobile(userAppointDto.getUserMobile());//手机号
            userAppoint.setUserName(userAppointDto.getUserName());//业主姓名
            if (!StringUtil.isEmpty(userAppointDto.getStartTime())) {
                userAppoint.setOperatorDate(DateUtils.parse(userAppointDto.getStartTime(), "yyyy/MM/dd"));//开始时间(替用字段)
            }
            if (!StringUtil.isEmpty(userAppointDto.getEndTime())) {
                userAppoint.setCreateDate(DateUtils.parse(userAppointDto.getEndTime(), "yyyy/MM/dd"));//结束时间(替用字段)
            }
        }
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = userAppointDto.getRoleScopeList();
        for (Map<String, Object> roleScope : roleScopeList) {
            String scopeType = (String) roleScope.get("scopeType");
            if (scopeType.equals("1")) {
                //城市级别(分公司)
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(roleScope.get("scopeId").toString());
                for (Object[] project : projectList) {
                    if (!roleScopes.contains(project[1].toString())) {
                        roleScopes += "'" + project[1].toString() + "',";
                    }
                }
            } else if (scopeType.equals("2")) {
                //项目级别
                if (!roleScopes.contains(roleScope.get("scopeName").toString())) {
                    roleScopes += "'" + roleScope.get("scopeName") + "',";
                }
            } else if (scopeType.equals("0")) {
                //全部城市
                roleScopes += "all,";
            }
        }
        //获取业主预约信息
        List<CommunityReservationListEntity> userAppoints = delieryPlanCRMRepository.getReservationList(userAppoint, webPage, roleScopes);
        //页面内容：封装到dto里
        List<UserAppointDto> UserAppointDTOs = new ArrayList<UserAppointDto>();
        if (userAppoints.size() > 0) {
            for (CommunityReservationListEntity reservation : userAppoints) {
                UserAppointDto userAppointDTO = new UserAppointDto();
                userAppointDTO.setUserName(reservation.getUserName());//业主姓名
                userAppointDTO.setUserMobile(reservation.getMobile());//业主电话
                userAppointDTO.setProjectName(reservation.getProjectName());//项目
                if (!StringUtil.isEmpty(reservation.getBuildingName())) {
//                    userAppointDTO.setBuildName(reservation.getBuildingName() + "栋");//楼号
                    userAppointDTO.setBuildName(reservation.getBuildingName());//楼号
                }
                if (!StringUtil.isEmpty(reservation.getUnitName())) {
//                    userAppointDTO.setUnitName(reservation.getUnitName() + "单元");//单元
                    userAppointDTO.setUnitName(reservation.getUnitName());//单元
                }
                userAppointDTO.setRoomName(reservation.getRoomName());//房间
                userAppointDTO.setIdCard(reservation.getIdCard());//身份证号
                userAppointDTO.setAppointUserTime(DateUtils.format(reservation.getReservationDate(), "yyyy-MM-dd"));//预约时间
                if (!StringUtil.isEmpty(reservation.getAmOrPm())) {
                    if (reservation.getAmOrPm().equals("0")) {
                        userAppointDTO.setAmOrPm("上午");
                    } else if (reservation.getAmOrPm().equals("1")) {
                        userAppointDTO.setAmOrPm("下午");
                    }else{
                        userAppointDTO.setAmOrPm(reservation.getAmOrPm());
                    }
                }
                userAppointDTO.setPlanName(reservation.getPlanName());//交付批次
                UserAppointDTOs.add(userAppointDTO);
            }
        }
        return UserAppointDTOs;
    }

    /**
     * 查询用户信息
     *
     * @param id
     */
    @Override
    public List<Object[]> queryUser(String id) {
        return this.delieryPlanCRMRepository.queryUser(id);
    }

    /**
     * CreatedBy:liudongxin
     * Describe:通过单元获取交付批次
     * ModifyBy:
     */
    /*@Override
    public LinkedHashMap BatchList() {
        List<CommunityReservationListEntity> reservationEntities = delieryPlanCRMRepository.mapBatch("999");
        LinkedHashMap batch = new LinkedHashMap();
        batch.put("0","-----请选择交付批次-----");
        if (reservationEntities.size()>0){
            for (CommunityReservationListEntity reservationEntity:reservationEntities){
                batch.put(unitEntity.getId(),reservationEntity.getPlanName());
            }
        }
        return batch;
    }*/

    /**
     * 通过userId查询交互预约消息列表
     * @param userId 用户Id
     * @return List<Map<String,Object>>
     */
    public List<Map<String, Object>> getDeliveryPlanMsgByUser(String userId) {
        List<Map<String, Object>> deliveryPlans = new ArrayList<>();
        //通过userId获取交付预约消息列表
        List<Map<String, Object>> list = delieryPlanCRMRepository.getDeliveryPlanMsgByUser(userId);
        for (Map<String, Object> map : list) {
            String planName = (String) map.get("planName");
            //通过userId和planName检索用户预约详情
            CommunityReservationListEntity reservation = delieryPlanCRMRepository.getReservationByUserAndPlan(userId, planName);
            if (reservation == null) {
                //未预约,消息返回
                //处理日期格式
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date planStart = (Date) map.get("planStart");
                Date planEnd = (Date) map.get("planEnd");
                try {
                    map.put("planStart", dateFormat.format(planStart));
                    map.put("planEnd", dateFormat.format(planEnd));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                deliveryPlans.add(map);
            }
        }
        return deliveryPlans;
    }

    /**
     * 导出excel
     * param:user
     * param:httpServletResponse
     * return
     */
    @Override
    public String exportExcel(UserPropertyStaffEntity user, DeliveryPlanCrmDto deliveryPlanCrmDto,
                              UserAppointDto userAppointDto, HttpServletResponse httpServletResponse,
                              String type, HttpServletRequest httpServletRequest) throws Exception {
        OutputStream outputStream = httpServletResponse.getOutputStream();
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        if ("1".equals(type)) {
            List<DeliveryPlanCrmDto> deliveryDTO = queryDeliveryPlanCrm(deliveryPlanCrmDto, webPage);

            XSSFSheet sheet = workBook.createSheet("交付计划列表");

            ExportUtil exportUtil = new ExportUtil(workBook, sheet);
            XSSFCellStyle headStyle = exportUtil.getHeadStyle();
            XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();

            // 百分比
            XSSFDataFormat fmt = workBook.createDataFormat();
            XSSFDataFormat fmt1 = workBook.createDataFormat();

            // 四个边框加粗
            XSSFCellStyle style1 = workBook.createCellStyle();
            style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            style1.setBorderRight(XSSFCellStyle.BORDER_THIN);
            style1.setBorderTop(XSSFCellStyle.BORDER_THIN);
            XSSFFont font = workBook.createFont();
            // 设置字体加粗
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontName("宋体");
            font.setFontHeight((short) 200);
            style1.setFont(font);

            String[] titles = {"序号", "交付批次", "项目名称", "集中交付时间", "计划描述", "状态"};
            XSSFRow headRow = sheet.createRow(0);


            if (deliveryDTO.size() > 0) {

                deliveryDTO.forEach(userDTO -> {

                    XSSFCell cell = null;
                    for (int i = 0; i < titles.length; i++) {
                        cell = headRow.createCell(i);
                        headRow.createCell(i).setCellValue(titles.length);
                        sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                        cell.setCellStyle(headStyle);
                        cell.setCellValue(titles[i]);
                    }

                    if (deliveryDTO.size() > 0) {
                        for (int i = 0; i < deliveryDTO.size(); i++) {
                            XSSFRow bodyRow = sheet.createRow(i + 1);
                            DeliveryPlanCrmDto deliveryPlan = deliveryDTO.get(i);
                            cell = bodyRow.createCell(0);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(i + 1);//序号

                            cell = bodyRow.createCell(1);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(deliveryPlan.getPlanName());//交付批次

                            cell = bodyRow.createCell(2);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(deliveryPlan.getProjectName());//项目名称

                            cell = bodyRow.createCell(3);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(deliveryPlan.getPlanStart() + "至" + deliveryPlan.getPlanEnd());//集中交付时间

                            cell = bodyRow.createCell(4);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(deliveryPlan.getDescription());//计划描述

                            cell = bodyRow.createCell(5);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            String state = "";

                            if (deliveryPlan.getBatchStatus().toString().equals("0")) {
                                state = "未发布";
                            } else if (deliveryPlan.getBatchStatus().toString().equals("1")) {
                                state = "已发布";
                            }
                            cell.setCellValue(state);//状态
                        }
                    }
                });
            }
            try {
                //String fileName = new String(("交付计划管理").getBytes(), "ISO8859_1");
                String fileName = new String(("交付计划管理").getBytes(), "ISO8859-1");
                String agent = httpServletRequest.getHeader("USER-AGENT");
                if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                        && -1 != agent.indexOf("Trident")) {// ie

                    fileName = java.net.URLEncoder.encode("交付计划管理", "UTF8");
                }
                httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
                workBook.write(outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if ("2".equals(type)) {
            List<UserAppointDto> userAppointDTO = reservationList(userAppointDto, webPage);

            XSSFSheet sheet = workBook.createSheet("预约信息列表");
            // 百分比
            XSSFDataFormat fmt = workBook.createDataFormat();
            XSSFDataFormat fmt1 = workBook.createDataFormat();
            ExportUtil exportUtil = new ExportUtil(workBook, sheet);
            XSSFCellStyle headStyle = exportUtil.getHeadStyle();
            XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
            // 四个边框加粗
            XSSFCellStyle style1 = workBook.createCellStyle();
            style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            style1.setBorderRight(XSSFCellStyle.BORDER_THIN);
            style1.setBorderTop(XSSFCellStyle.BORDER_THIN);
            XSSFFont font = workBook.createFont();
            // 设置字体加粗
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontName("宋体");
            font.setFontHeight((short) 200);
            style1.setFont(font);
            String[] titles = {"序号", "姓名", "手机号", "项目", "楼号", "单元", "房间", "身份证", "预约时间", "时间段", "交付批次"};
            XSSFRow headRow = sheet.createRow(0);

            if (userAppointDTO.size() > 0) {

                userAppointDTO.forEach(userDTO -> {
                    XSSFCell cell = null;
                    for (int i = 0; i < titles.length; i++) {
                        cell = headRow.createCell(i);
                        headRow.createCell(i).setCellValue(titles.length);
                        sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                        cell.setCellStyle(headStyle);
                        cell.setCellValue(titles[i]);
                    }

                    if (userAppointDTO.size() > 0) {
                        for (int i = 0; i < userAppointDTO.size(); i++) {
                            XSSFRow bodyRow = sheet.createRow(i + 1);
                            UserAppointDto userDto = userAppointDTO.get(i);
                            cell = bodyRow.createCell(0);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(i + 1);//序号

                            cell = bodyRow.createCell(1);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getUserName());//姓名

                            cell = bodyRow.createCell(2);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getUserMobile());//手机号

                            cell = bodyRow.createCell(3);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getProjectName());//项目

                            cell = bodyRow.createCell(4);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getBuildName());//楼号

                            cell = bodyRow.createCell(5);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getUnitName());//单元

                            cell = bodyRow.createCell(6);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getRoomName());//房间

                            cell = bodyRow.createCell(7);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getIdCard());//身份证

                            cell = bodyRow.createCell(8);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getAppointUserTime());//预约时间

                            cell = bodyRow.createCell(9);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getAmOrPm());//时间段

                            cell = bodyRow.createCell(10);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getPlanName());//交付批次
                        }
                    }
                });
            }
            try {
                String fileName = new String(("业主预约管理").getBytes(), "ISO8859-1");
                httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
                workBook.write(outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if ("3".equals(type)) {
            DeliveryPlanCrmEntity deliveryPlanCrmEntity = getById(deliveryPlanCrmDto.getId());
            List<UserAppointDto> userAppointDtoList = searchHouseListByPlanId(deliveryPlanCrmDto.getId(), webPage, deliveryPlanCrmEntity);

            XSSFSheet sheet = workBook.createSheet("交房计划详情列表");
            // 百分比
            XSSFDataFormat fmt = workBook.createDataFormat();
            XSSFDataFormat fmt1 = workBook.createDataFormat();
            ExportUtil exportUtil = new ExportUtil(workBook, sheet);
            XSSFCellStyle headStyle = exportUtil.getHeadStyle();
            XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
            // 四个边框加粗
            XSSFCellStyle style1 = workBook.createCellStyle();
            style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            style1.setBorderRight(XSSFCellStyle.BORDER_THIN);
            style1.setBorderTop(XSSFCellStyle.BORDER_THIN);
            XSSFFont font = workBook.createFont();
            // 设置字体加粗
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontName("宋体");
            font.setFontHeight((short) 200);
            style1.setFont(font);
            String[] titles = {"序号", "计划名称", "房屋地址", "姓名", "手机号", "身份证号", "预约时间","时间段"};
            XSSFRow headRow = sheet.createRow(0);

            if (userAppointDtoList.size() > 0) {

                userAppointDtoList.forEach(userDTO -> {
                    XSSFCell cell = null;
                    for (int i = 0; i < titles.length; i++) {
                        cell = headRow.createCell(i);
                        headRow.createCell(i).setCellValue(titles.length);
                        sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                        cell.setCellStyle(headStyle);
                        cell.setCellValue(titles[i]);
                    }

                    if (userAppointDtoList.size() > 0) {
                        for (int i = 0; i < userAppointDtoList.size(); i++) {
                            XSSFRow bodyRow = sheet.createRow(i + 1);
                            UserAppointDto userDto = userAppointDtoList.get(i);

                            cell = bodyRow.createCell(0);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(i + 1);//序号

                            cell = bodyRow.createCell(1);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getPlanName());//计划名称

                            cell = bodyRow.createCell(2);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getRoomAddress());//房屋地址

                            cell = bodyRow.createCell(3);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getUserName());//姓名

                            cell = bodyRow.createCell(4);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getUserMobile());//手机号

                            cell = bodyRow.createCell(5);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getIdCard());//身份证

                            cell = bodyRow.createCell(6);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getAppointUserTime());//预约时间

                            cell = bodyRow.createCell(7);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getAmOrPm());//预约时间

                        }
                    }
                });
            }
            try {
                String fileName = new String(("交房计划详情管理").getBytes(), "ISO8859-1");
                httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
                workBook.write(outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
