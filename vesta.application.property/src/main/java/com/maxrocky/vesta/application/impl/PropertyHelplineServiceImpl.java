package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.application.inf.HouseProjectService;
import com.maxrocky.vesta.application.inf.PropertyHelplineService;
import com.maxrocky.vesta.application.inf.UserSettingService;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportUtil;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by liudongxin on 2016/3/30.
 * 物业热线电话实现方法
 */
@Service
public class PropertyHelplineServiceImpl implements PropertyHelplineService {
    @Autowired
    private PropertyHelplineRepository propertyHelplineRepository;
    @Autowired
    private PropertyImageRepository propertyImageRepository;
    @Autowired
    private HouseProjectRepository houseProjectRepository;
    @Autowired
    private ImgService imgService;
    @Autowired
    private ClickUserRepository clickUserRepository;
    @Autowired
    AnnouncementRepository announcementRepository;
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    AnnouncementScopeRepository announcementScopeRepository;
    @Autowired
    SystemLogRepository systemLogRepository;
    @Autowired
    HouseInfoRepository houseInfoRepository;
    @Autowired
    UserSettingService userSettingService;
    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;


    /**
     * 通过便民信息Id检索发布项目范围
     */
    public List<Map<String,Object>> getProjectScopeByHelplineId(String propertyHelplineId){
        List<Map<String,Object>> projectList = new ArrayList<>();
        List<PropertyHelplineScopeEntity> propertyHelplineScopeEntities = propertyHelplineRepository.getScopeByHelpline(propertyHelplineId);
        if (propertyHelplineScopeEntities.size() > 0){
            if (propertyHelplineScopeEntities.size() == 1 && propertyHelplineScopeEntities.get(0).getCityId().equals("0")){
                //全部城市
                List<Object[]> list = announcementRepository.listAllProject();
                for (Object[] object : list){
                    Map<String,Object> project = new HashMap<>();
                    project.put("projectId",object[0].toString());
                    project.put("projectName",object[1].toString());
                    projectList.add(project);
                }
            }else{
                for (PropertyHelplineScopeEntity propertyHelplineScopeEntity : propertyHelplineScopeEntities){
                    Map<String,Object> project = new HashMap<>();
                    project.put("projectId",propertyHelplineScopeEntity.getProjectId());
                    project.put("projectName",propertyHelplineScopeEntity.getProjectName());
                    projectList.add(project);
                }
            }
        }
        return projectList;
    }


    /**
     * CreateBy:liudongxin
     * Description:通过城市获取所有的小区
     * param:城市
     * 业主默认选择：北京
     * ModifyBy:
     */
    @Override
    public ApiResult getAllProject(String city) throws GeneralException {
        if(StringUtil.isEmpty(city)){
            return ErrorResource.getError("tip_pe00000027");//城市为空
        }
        try{
            HouseCompanyDTO houseCompany=new HouseCompanyDTO();
            List<HouseProjectDTO> houseProjects=new ArrayList<HouseProjectDTO>();
            List<PropertyPriceSettingDTO> projects=new ArrayList<PropertyPriceSettingDTO>();
            //获取城市
            List<String> citys=propertyHelplineRepository.getAllCitys();
            if(citys.size()>0){
                for(String str:citys){
                    HouseProjectDTO houseProject=new HouseProjectDTO();
                    houseProject.setCity(str);
                    houseProjects.add(houseProject);
                }
            }
            //获取所有小区
            List<Object[]> communities=propertyHelplineRepository.getAllCommunityNames(city);
            if(communities.size()>0){
                for(Object[] obj:communities){
                    PropertyPriceSettingDTO project=new PropertyPriceSettingDTO();
                    project.setProjectName((String) obj[0]);
                    project.setProjectId((String)obj[1]);
                    projects.add(project);
                }
            }
            houseCompany.setCityList(houseProjects);
            houseCompany.setDefaultCity("北京");//获取默认城市
            houseCompany.setCommunityList(projects);
            return new SuccessApiResult(houseCompany);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * CreatedBy:liudongxin
     * 获取当前热线服务电话
     * Describe:通过小区id获取服务内容为空的热线电话
     * ModifyBy:
     */
    @Override
    public ApiResult getServicePhone(String projectId,String userId) throws GeneralException {
        if(StringUtil.isEmpty(projectId)){
            return ErrorResource.getError("tip_pe00000026");//小区为空
        }
        try{
            //封装到DTO
            List<WorkOrderDepartmentDTO> departments=new ArrayList<WorkOrderDepartmentDTO>();
            //服务电话信息
            List<WorkOrderUserDTO> workOrderUsers=new ArrayList<WorkOrderUserDTO>();
            WorkOrderDepartmentDTO department = new WorkOrderDepartmentDTO();
            //获取热线电话
            List<PropertyHelplineEntity> propertyHelpline=propertyHelplineRepository.getServicePhone(projectId);
            //String imgFk="";
            if(propertyHelpline.size()>0){
                for(PropertyHelplineEntity line:propertyHelpline){
                    WorkOrderUserDTO workOrderUser = new WorkOrderUserDTO();
                    workOrderUser.setContent(line.getContent());
                    workOrderUser.setMobile(line.getPhone());
                    workOrderUser.setGroupName(line.getGroupName());
                    if (line.getSortNum()!=null) {
                        workOrderUser.setSortNum(line.getSortNum());
                    }
                    workOrderUsers.add(workOrderUser);
                    department.setMemberList(workOrderUsers);
                    //imgFk=line.getId();
                }
                //获取项目图片
                PropertyImageEntity image=propertyImageRepository.getImageUrlById(projectId);
                if(image!=null && !"".equals(image.getImagePath())) {
                    department.setSrc(image.getImagePath());
                }
                departments.add(department);
            }
            //调用点击人统计
            String dateNow=DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
            ClickUsersEntity clickUserEntity=clickUserRepository.getTotalInfo(dateNow,"9",userId);
            if(clickUserEntity==null){
                ClickUsersEntity clickUser=new ClickUsersEntity();
                clickUser.setId(IdGen.uuid());
                clickUser.setCreateDate(new Date());
                clickUser.setClicks(1);
                clickUser.setUserId(userId);
                clickUser.setForeignId("9");
                clickUserRepository.create(clickUser);
            }else{
                clickUserEntity.setClicks(clickUserEntity.getClicks() + 1);
                clickUserRepository.update(clickUserEntity);
            }
            return new SuccessApiResult(departments);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * CreatedBy:liudongxin
     * 获取当前热线服务电话
     * Describe:通过小区id获取服务内容不为空的热线电话
     * ModifyBy:
     */
    @Override
    public ApiResult getServiceInfo(String projectId,UserInfoEntity userInfo) throws GeneralException {
        if(StringUtil.isEmpty(projectId)){
            return ErrorResource.getError("tip_pe00000026");//小区为空
        }
        try{
            //服务电话信息
            List<WorkOrderUserDTO> workOrderUsers=new ArrayList<WorkOrderUserDTO>();
            //获取热线电话
            //List<PropertyHelplineEntity> propertyHelpline=propertyHelplineRepository.getServiceInfo(projectId);
            List<PropertyHelplineEntity> propertyHelpline=propertyHelplineRepository.getServiceInfoss(projectId);
            if(propertyHelpline.size()>0){
                for(PropertyHelplineEntity line:propertyHelpline){
                    WorkOrderUserDTO workOrderUser=new WorkOrderUserDTO();
                    workOrderUser.setName(line.getGroupName());
                    workOrderUser.setContent(line.getContent());
                    if (line.getSortNum()!=null) {
                        workOrderUser.setSortNum(line.getSortNum());
                    }
                    workOrderUsers.add(workOrderUser);
                }
            }
            if(userInfo==null){
                //增加用户日志记录
                UserVisitLogEntity userVisitLogEntity=new UserVisitLogEntity();
                userVisitLogEntity.setLogId(IdGen.uuid());
                userVisitLogEntity.setLogTime(new Date());//注册日期
                userVisitLogEntity.setUserName("");//用户昵称
                userVisitLogEntity.setUserType("1");//用户类型为空,即为游客
                userVisitLogEntity.setUserMobile("");//手机号
                userVisitLogEntity.setSourceFrom("");//来源
                userVisitLogEntity.setThisSection("服务");
                userVisitLogEntity.setThisFunction("便民信息");
                /*UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(userInfo.getUserId());
                if (null != userSettingEntity) {
                    userVisitLogEntity.setProjectId(userSettingEntity.getProjectName());
                    userVisitLogEntity.setLogContent(userSettingEntity.getProjectName());
                } else{
                    userVisitLogEntity.setProjectId("");//项目
                    userVisitLogEntity.setLogContent("");//内容
                }*/

                systemLogRepository.addUserVisitLog(userVisitLogEntity);
            }else{
                //增加用户日志记录
                UserVisitLogEntity userVisitLogEntity=new UserVisitLogEntity();
                userVisitLogEntity.setLogId(IdGen.uuid());
                userVisitLogEntity.setLogTime(new Date());//注册日期
                userVisitLogEntity.setUserName(userInfo.getNickName());//用户昵称
                userVisitLogEntity.setUserType(userInfo.getUserType());//用户类型
                userVisitLogEntity.setUserMobile(userInfo.getMobile());//手机号
                userVisitLogEntity.setSourceFrom(userInfo.getSourceType());//来源
                userVisitLogEntity.setThisSection("服务");
                userVisitLogEntity.setThisFunction("便民信息");
                UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(userInfo.getUserId());
                if (null != userSettingEntity) {
                    userVisitLogEntity.setProjectId(userSettingEntity.getProjectName());
                    userVisitLogEntity.setLogContent(userSettingEntity.getProjectName());
                } else{
                    userVisitLogEntity.setProjectId("");//项目
                    userVisitLogEntity.setLogContent("");//内容
                }
                systemLogRepository.addUserVisitLog(userVisitLogEntity);
            }
            return new SuccessApiResult(workOrderUsers);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * CreatedBy:liudongxin
     * Describe:获取便民信息列表
     * ModifyBy:
     * param:user
     * param:hotLineDTO
     * param:webPage
     */
    @Override
    public List<PropertyAnnouncementPageDTO> getHotLineList(UserPropertyStaffEntity user, PropertyAnnouncementPageDTO hotLineDTO, WebPage webPage) {
        //检索条件
        PropertyHelplineEntity propertyHelpline=new PropertyHelplineEntity();
        //追加区域项目检索条件-Wyd20170407
        //如果检索项目不为Null,直接set propertyHelpline的projectId
        //如果检索项目为Null,城市不为Null,set propertyHelpline的projectId为该城市下所有项目Code,逗号间隔
        if (null != hotLineDTO.getProject() && !"0".equals(hotLineDTO.getProject()) && !"".equals(hotLineDTO.getProject())){
            propertyHelpline.setProjectId("'"+hotLineDTO.getProject() +"',");
        }else if (null != hotLineDTO.getQueryScope() && !"0".equals(hotLineDTO.getQueryScope()) && !"".equals(hotLineDTO.getQueryScope())){
            List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(hotLineDTO.getQueryScope());
            String projectIds = "";
            for (Object[] project : projectList) {
                projectIds += "'" + project[0].toString() + "',";
            }
            propertyHelpline.setProjectId(projectIds);
        }
        propertyHelpline.setGroupName(hotLineDTO.getTitle());//名称
        propertyHelpline.setPhone(hotLineDTO.getMemo());//电话
        propertyHelpline.setContent(hotLineDTO.getAnnouncementContent());//内容
        propertyHelpline.setCreateBy(hotLineDTO.getStartTime());//开始时间
        propertyHelpline.setModifyBy(hotLineDTO.getEndTime());//结束时间

        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = hotLineDTO.getRoleScopeList();
        for (Map<String,Object> roleScope : roleScopeList){
            String scopeType = (String) roleScope.get("scopeType");
            if (scopeType.equals("1")){
                //城市级别(分公司)
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(roleScope.get("scopeId").toString());
                for (Object[] project : projectList){
                    if (!roleScopes.contains(project[0].toString())){
                        roleScopes += "'"+project[0].toString()+"',";
                    }
                }
            }else if (scopeType.equals("2")){
                //项目级别
                if (!roleScopes.contains(roleScope.get("scopeId").toString())){
                    roleScopes += "'"+roleScope.get("scopeId")+"',";
                }
            }else if (scopeType.equals("0")){
                //全部城市
                roleScopes += "all,";
            }
        }

        //获取便民信息
        List<PropertyHelplineEntity> hotLines=propertyHelplineRepository.getHotLineList(propertyHelpline, webPage,roleScopes);
        //页面内容：封装到dto里
        List<PropertyAnnouncementPageDTO> hotLineDtos=new ArrayList<PropertyAnnouncementPageDTO>();
        if(hotLines.size()>0){
            for(PropertyHelplineEntity line:hotLines){
                PropertyAnnouncementPageDTO lineDTO=new PropertyAnnouncementPageDTO();
                lineDTO.setAnnouncementId(line.getId());
                lineDTO.setTitle(line.getGroupName());//名称
                lineDTO.setQueryScope(line.getCity());//城市
                lineDTO.setProject(line.getProjectName());//项目
                lineDTO.setMemo(line.getPhone());//电话
                lineDTO.setAnnouncementContent(line.getContent());//内容
                lineDTO.setCreateTime(DateUtils.format(line.getCreateDate(), "yyyy-MM-dd"));//发布时间
                lineDTO.setSortNumber(line.getSortNum());//排序序号
                hotLineDtos.add(lineDTO);
            }
        }
        return hotLineDtos;
    }

    /**
     * 保存
     * param:user
     * param:hotLineDTO
     * return
     */
    @Override
    public String saveHotLine(UserPropertyStaffEntity user, PropertyAnnouncementPageDTO hotLineDTO) {
        //判断是否执行成功
        String resultMessage = "";

        if(hotLineDTO!=null){
            PropertyHelplineEntity propertyHelpline=new PropertyHelplineEntity();
            propertyHelpline.setId(IdGen.uuid());
            propertyHelpline.setGroupName(hotLineDTO.getTitle());//名称
            propertyHelpline.setCreateBy(user.getStaffId());
            propertyHelpline.setCreateDate(new Date());
            //propertyHelpline.setProjectId(hotLineDTO.getProjectIds().substring(0, hotLineDTO.getProjectIds().length() - 1));
            propertyHelpline.setProjectId((hotLineDTO.getProject()));//项目编号
            //propertyHelpline.setCity(hotLineDTO.getCitys());
            propertyHelpline.setCity(propertyHelplineRepository.getCityNameById(hotLineDTO.getCity()));//城市名称
            //通过项目编号或许项目名称
            //propertyHelpline.setProjectName(hotLineDTO.getProjects());
            propertyHelpline.setProjectName(propertyHelplineRepository.getProjectNameById(hotLineDTO.getProject()));
            if (hotLineDTO.getSortNumber() != null) {
                propertyHelpline.setSortNum(hotLineDTO.getSortNumber());
            }
            //String roleScopes="";
            //WebPage webPage=new WebPage();
           // List<PropertyHelplineEntity> hotLines=propertyHelplineRepository.getHotLineList(propertyHelpline, webPage,roleScopes);



          /*  HouseProjectEntity project=houseProjectRepository.get(hotLineDTO.getProject());
            if(project!=null) {
                propertyHelpline.setProjectName(project.getName());//项目
                propertyHelpline.setCity(project.getCity());
            }*/
            propertyHelpline.setState("1");
            if(!StringUtil.isEmpty(hotLineDTO.getMemo())) {
                propertyHelpline.setPhone(hotLineDTO.getMemo());
            }
            propertyHelpline.setContent(hotLineDTO.getAnnouncementContent());
            //基础数据准备
            /*List<String> cityList = Arrays.asList(hotLineDTO.getCitys().split(","));
            List<String> projectList = Arrays.asList(hotLineDTO.getProjects().split(","));
            List<String> cityIds = Arrays.asList(hotLineDTO.getCityIds().split(","));
            List<String> projectIds = Arrays.asList(hotLineDTO.getProjectIds().split(","));*/
            PropertyHelplineScopeEntity PropertyHelplineScopeEntity;

            /*//全部城市
            if (cityList.size() == 1 && cityList.get(0).equals("全部城市")) {
                List<Object[]> projects = announcementRepository.listAllProject();
                propertyHelpline.setProjectName("全部项目");
                for (int i = 0; i < projects.size(); i++){
                    PropertyHelplineScopeEntity = new PropertyHelplineScopeEntity();
                    PropertyHelplineScopeEntity.setId(IdGen.uuid());
                    PropertyHelplineScopeEntity.setPropertyHelplineId(propertyHelpline.getId());
                    PropertyHelplineScopeEntity.setCity("全部城市");
                    PropertyHelplineScopeEntity.setCityId("0");
                    PropertyHelplineScopeEntity.setProjectId((String) projects.get(i)[0]);
                    PropertyHelplineScopeEntity.setProjectName((String) projects.get(i)[1]);
                    // PropertyHelplineScopeEntity.setScope("1");
                    PropertyHelplineScopeEntity.setIsAll(1);
                    PropertyHelplineScopeEntity.setStatus(1);
                    PropertyHelplineScopeEntity.setCreateDate(new Date());
                    PropertyHelplineScopeEntity.setOperateDate(new Date());
                    PropertyHelplineScopeEntity.setCreatePerson(hotLineDTO.getReleasePerson());
                    PropertyHelplineScopeEntity.setOperatePerson(hotLineDTO.getReleasePerson());

                 *//*   CommunityOverviewScopeEntity communityOverviewScopeEntity = new CommunityOverviewScopeEntity();
                    communityOverviewScopeEntity.setId(IdGen.uuid());
                    communityOverviewScopeEntity.setCommunityOverviewId(communityOverviewEntity.getId());
                    communityOverviewScopeEntity.setProjectCode((String) projects.get(i)[0]);
                    communityOverviewScopeEntity.setProjectName((String) projects.get(i)[1]);
                    communityOverviewScopeEntity.setStatus(0);
                    communityOverviewScopeEntity.setCreateOn(new Date());
                    communityOverviewScopeEntity.setCreateBy(communityOverviewDto.getReleasePerson());
                    communityOverviewScopeEntity.setModifyOn(new Date());
                    communityOverviewScopeEntity.setModifyBy(communityOverviewDto.getReleasePerson());
                    communityOverviewRepository.saveOrUpdate(communityOverviewScopeEntity);*//*

                    this.announcementScopeRepository.saveOrUpdate(PropertyHelplineScopeEntity);
                }
            }
            //全部项目
            if (projectList.size() == 1 && projectList.get(0).equals("全部项目")) {
                //遍历城市范围,检索每个城市下所有项目
                for (int i = 0; i < cityIds.size(); i++) {
                    String cityId = cityIds.get(i);
                    List<Object[]> projects = announcementRepository.listProject(cityId);

                    //为每个项目添加公告范围信息
                    for (int j = 0; j < projects.size(); j++) {
                        PropertyHelplineScopeEntity = new PropertyHelplineScopeEntity();
                        PropertyHelplineScopeEntity.setId(IdGen.uuid());
                        PropertyHelplineScopeEntity.setPropertyHelplineId(propertyHelpline.getId());
                        PropertyHelplineScopeEntity.setCity(cityList.get(i));
                        PropertyHelplineScopeEntity.setCityId(cityId);
                        PropertyHelplineScopeEntity.setProjectName((String) projects.get(j)[1]);
                        PropertyHelplineScopeEntity.setProjectId((String) projects.get(j)[0]);
                        //PropertyHelplineScopeEntity.setScope("2");
                        PropertyHelplineScopeEntity.setStatus(1);
                        PropertyHelplineScopeEntity.setCreateDate(new Date());
                        PropertyHelplineScopeEntity.setOperateDate(new Date());
                        PropertyHelplineScopeEntity.setCreatePerson(hotLineDTO.getReleasePerson());
                        PropertyHelplineScopeEntity.setOperatePerson(hotLineDTO.getReleasePerson());
                        this.announcementScopeRepository.saveOrUpdate(PropertyHelplineScopeEntity);
                    }
                }
            }*/

            //改为单个城市下的单个项目
            //单个项目
            /*if (!cityList.get(0).equals("全部城市") && !projectList.get(0).equals("全部项目")) {
                for (int i = 0; i < projectList.size(); i++) {
                    String projects = projectList.get(i);*/

                    PropertyHelplineScopeEntity = new PropertyHelplineScopeEntity();
                    PropertyHelplineScopeEntity.setId(IdGen.uuid());
                    PropertyHelplineScopeEntity.setPropertyHelplineId(propertyHelpline.getId());
                    //通过项目Code绑定城市
                    //List<Object[]> list = this.voteRepository.queryCityByProjectCode(projectIds.get(i));
                    //if (!list.isEmpty() && list.size() > 0){
                        PropertyHelplineScopeEntity.setCity(propertyHelpline.getCity());
                        PropertyHelplineScopeEntity.setCityId(hotLineDTO.getCity());
                    //}
                    PropertyHelplineScopeEntity.setProjectName(propertyHelpline.getProjectName());
                    PropertyHelplineScopeEntity.setProjectId(propertyHelpline.getProjectId());
                    // PropertyHelplineScopeEntity.setScope("3");
                    PropertyHelplineScopeEntity.setStatus(1);
                    PropertyHelplineScopeEntity.setCreateDate(new Date());
                    PropertyHelplineScopeEntity.setOperateDate(new Date());
                    PropertyHelplineScopeEntity.setCreatePerson(hotLineDTO.getReleasePerson());
                    PropertyHelplineScopeEntity.setOperatePerson(hotLineDTO.getReleasePerson());
                    this.announcementScopeRepository.saveOrUpdate(PropertyHelplineScopeEntity);
               /* }
            }*/

            boolean line=propertyHelplineRepository.saveHotLine(propertyHelpline);
            if(line){
                resultMessage="0";//成功
            }else {
                resultMessage="1";//失败
            }
            InfoReleaseEntity infoReleaseLog=new InfoReleaseEntity();
            infoReleaseLog.setLogId(IdGen.uuid());
            infoReleaseLog.setLogTime(new Date());//注册日期
            infoReleaseLog.setUserName(user.getStaffName());//用户昵称
            infoReleaseLog.setUserMobile(user.getMobile());//手机号
            infoReleaseLog.setThisSection("服务");//版块
            infoReleaseLog.setThisFunction("资讯管理-便民信息");//功能
            infoReleaseLog.setThisType("新增");//操作类型
            infoReleaseLog.setLogContent(propertyHelpline.getContent());//发布内容
           /* HouseProjectEntity houseProjectEntity=houseProjectRepository.getProjectByCode(projectCode);
            if(houseProjectEntity!=null){
                infoReleaseLog.setAsscommunity(houseProjectEntity.getName());//关联社区;
            }else{
                infoReleaseLog.setAsscommunity("");//关联社区;
            }*/
            Map<String, Object> map = queryProjectByHotLineId(propertyHelpline.getId());
            if(map!=null){
                infoReleaseLog.setAsscommunity(map.get("projectNames").toString());//关联社区;
            }else{
                infoReleaseLog.setAsscommunity("");
            }
            systemLogRepository.addInfoReleaseLog(infoReleaseLog);

        }
        return resultMessage;
    }

    /**
     * 详情
     * param:id
     * return
     */
    @Override
    public PropertyAnnouncementPageDTO getHotLine(String id) {
        PropertyAnnouncementPageDTO lineDTO=new PropertyAnnouncementPageDTO();
        if(!StringUtil.isEmpty(id)){
            PropertyHelplineEntity helpLine=propertyHelplineRepository.getServiceInfoById(id);
            if(helpLine!=null){
                lineDTO.setAnnouncementId(helpLine.getId());
                //通过城市名称拿到城市id
                lineDTO.setQueryScope(propertyHelplineRepository.getCityIdByName(helpLine.getCity()));//城市
                lineDTO.setProject(helpLine.getProjectId());//项目
                lineDTO.setProjects(helpLine.getProjectName());//项目名称

                //得到该项目对应的图片，imgFkId为项目编号
                PropertyImageEntity propertyImage=propertyImageRepository.getImageUrlById(helpLine.getProjectId());
                if(propertyImage!=null) {
                    lineDTO.setImagePath(propertyImage.getImagePath());//图片
                }
                lineDTO.setTitle(helpLine.getGroupName());//名称
                lineDTO.setMemo(helpLine.getPhone());//电话
                lineDTO.setAnnouncementContent(helpLine.getContent());//内容
                if (helpLine.getSortNum()!=null) {
                    lineDTO.setSortNumber(helpLine.getSortNum());//排序序号
                }
            }
        }
        return lineDTO;
    }

    /**
     * 便民信息修改
     * param:user
     * param:hotLineDTO
     * return
     */
    @Override
    public String updateHotLine(UserPropertyStaffEntity user, PropertyAnnouncementPageDTO hotLineDTO) {
        //判断是否执行更新成功
        String resultMessage = "";
        if(hotLineDTO!=null && !StringUtil.isEmpty(hotLineDTO.getAnnouncementId())){

            List<PropertyHelplineScopeEntity> PropertyHelplineScopes=propertyHelplineRepository.getServiceInfos(hotLineDTO.getAnnouncementId());
            // this.announcementScopeRepository.deleteAll(PropertyHelplineScopes);
            for(PropertyHelplineScopeEntity scope:PropertyHelplineScopes){
                //scope.setStatus(0);
                //announcementScopeRepository.deleteById(scope,scope.getId());
                //this.announcementScopeRepository.saveOrUpdate(scope);
                this.announcementScopeRepository.deleteById(scope.getClass(),scope.getId());
            }
            PropertyHelplineEntity helpLine=propertyHelplineRepository.getServiceInfoById(hotLineDTO.getAnnouncementId());
            if(helpLine!=null){
              /*  if(!StringUtil.isEmpty(hotLineDTO.getQueryScope())) {
                    helpLine.setCity(hotLineDTO.getQueryScope());
                }
                if(!StringUtil.isEmpty(hotLineDTO.getProject())) {
                    helpLine.setProjectName(hotLineDTO.getProject());
                }*/
                if (hotLineDTO.getSortNumber()!=null) {
                    helpLine.setSortNum(hotLineDTO.getSortNumber());
                }
                if(!StringUtil.isEmpty(hotLineDTO.getCity())) {
                    helpLine.setCity(propertyHelplineRepository.getCityNameById(hotLineDTO.getCity()));
                }
                if(!StringUtil.isEmpty(hotLineDTO.getProject())) {
                    helpLine.setProjectName(propertyHelplineRepository.getProjectNameById(hotLineDTO.getProject()));
                    helpLine.setProjectId(hotLineDTO.getProject());
                }

                //---------------------------------------------------------------------------对？
                /*if(!StringUtil.isEmpty(hotLineDTO.getProjectIds())){
                    //helpLine.setProjectId(hotLineDTO.getProjectIds().substring(0,hotLineDTO.getProjectIds().length()-1));
                    helpLine.setProjectId(hotLineDTO.getProject());
                }*/



                /*if(hotLineDTO.getImage().getSize()>0) {
                    //-------------------------------------------图片路径------------------------------------------------
                    String fileName = imgService.uploadAdminImage(hotLineDTO.getImage(), ImgType.ACTIVITY);
                    //String fileName = imgService.uploadAdminImage(activityAdminDto.getHomePageimgpath(),ImgType.ACTIVITY);
                    //图片地址特殊处理
//                    String urlTitle = "http:////211.94.93.223/images/";//ImageConfig.PIC_SERVER_INTE_URL
//
//                   // http://211.94.93.223/jin_images/houserental/b59b8eb7ac5c43b89f661df0a913ede0.jpg
//                    fileName = urlTitle + fileName.replace("/opt/image.server/images/images_source/", "");//ImageConfig.PIC_OSS_INTE_URL 应该是这个么？确认后请修改  Magic_ly
                    String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                    fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                    if (fileName.equals("")) {
                        fileName = "默认图";
                    }
                    PropertyImageEntity propertyImage = propertyImageRepository.getImageUrlById(helpLine.getId());
                    if (propertyImage != null) {
                        propertyImage.setImagePath(fileName);
                        propertyImage.setUploadName(fileName);
                        propertyImage.setModifyDate(new Date());
                        propertyImageRepository.updateImage(propertyImage);
                    }else{
                        PropertyImageEntity image=new PropertyImageEntity();
                        image.setImageId(IdGen.uuid());
                        image.setUploadDate(new Date());
                        //image.setImgFkId(hotLineDTO.getProject());
                        image.setImgFkId(helpLine.getId());
                        image.setImagePath(fileName);
                        image.setImageType("3");
                        image.setUploadName(fileName);
                        image.setState("0");
                        propertyImageRepository.saveImage(image);
                    }
                }*/
                if(!StringUtil.isEmpty(hotLineDTO.getTitle())) {
                    helpLine.setGroupName(hotLineDTO.getTitle());
                }
                if(!StringUtil.isEmpty(hotLineDTO.getMemo())){
                    helpLine.setPhone(hotLineDTO.getMemo());
                }
                if(!StringUtil.isEmpty(hotLineDTO.getAnnouncementContent())){
                    helpLine.setContent(hotLineDTO.getAnnouncementContent());
                }
                //if(!StringUtil.isEmpty(hotLineDTO.getMemo())){
                helpLine.setPhone(hotLineDTO.getMemo());
                //}

               /* phone
                        memo*/

                //基础数据准备
                /*List<String> cityList = Arrays.asList(hotLineDTO.getCitys().split(","));
                List<String> projectList = Arrays.asList(hotLineDTO.getProjects().split(","));
                List<String> cityIds = Arrays.asList(hotLineDTO.getCityIds().split(","));
                List<String> projectIds = Arrays.asList(hotLineDTO.getProjectIds().split(","));*/
                PropertyHelplineScopeEntity PropertyHelplineScopeEntity;

                /*//全部城市
                if (cityList.size() == 1 && cityList.get(0).equals("全部城市")) {
                    List<Object[]> projects = announcementRepository.listAllProject();
                    helpLine.setProjectName("全部项目");
                    for (int i = 0; i < projects.size(); i++){
                        PropertyHelplineScopeEntity = new PropertyHelplineScopeEntity();
                        PropertyHelplineScopeEntity.setId(IdGen.uuid());
                        PropertyHelplineScopeEntity.setPropertyHelplineId(hotLineDTO.getAnnouncementId());
                        PropertyHelplineScopeEntity.setCity("全部城市");
                        PropertyHelplineScopeEntity.setCityId("0");
                        PropertyHelplineScopeEntity.setProjectId((String) projects.get(i)[0]);
                        PropertyHelplineScopeEntity.setProjectName((String) projects.get(i)[1]);
                        // PropertyHelplineScopeEntity.setScope("1");
                        PropertyHelplineScopeEntity.setIsAll(1);
                        PropertyHelplineScopeEntity.setStatus(1);
                        PropertyHelplineScopeEntity.setCreateDate(new Date());
                        PropertyHelplineScopeEntity.setOperateDate(new Date());
                        PropertyHelplineScopeEntity.setCreatePerson(hotLineDTO.getReleasePerson());
                        PropertyHelplineScopeEntity.setOperatePerson(hotLineDTO.getReleasePerson());
                        this.announcementScopeRepository.saveOrUpdate(PropertyHelplineScopeEntity);
                    }

                }

                //全部项目
                if (projectList.size() == 1 && projectList.get(0).equals("全部项目")) {
                    //遍历城市范围,检索每个城市下所有项目
                    for (int i = 0; i < cityIds.size(); i++) {
                        String cityId = cityIds.get(i);
                        List<Object[]> projects = announcementRepository.listProject(cityId);

                        //为每个项目添加公告范围信息
                        for (int j = 0; j < projects.size(); j++) {
                            PropertyHelplineScopeEntity = new PropertyHelplineScopeEntity();
                            PropertyHelplineScopeEntity.setId(IdGen.uuid());
                            PropertyHelplineScopeEntity.setPropertyHelplineId(hotLineDTO.getAnnouncementId());
                            PropertyHelplineScopeEntity.setCity(cityList.get(i));
                            PropertyHelplineScopeEntity.setCityId(cityId);
                            PropertyHelplineScopeEntity.setProjectName((String) projects.get(j)[1]);
                            PropertyHelplineScopeEntity.setProjectId((String) projects.get(j)[0]);
                            //PropertyHelplineScopeEntity.setScope("2");
                            PropertyHelplineScopeEntity.setStatus(1);
                            PropertyHelplineScopeEntity.setCreateDate(new Date());
                            PropertyHelplineScopeEntity.setOperateDate(new Date());
                            PropertyHelplineScopeEntity.setCreatePerson(hotLineDTO.getReleasePerson());
                            PropertyHelplineScopeEntity.setOperatePerson(hotLineDTO.getReleasePerson());
                            this.announcementScopeRepository.saveOrUpdate(PropertyHelplineScopeEntity);
                        }
                    }
                }*/

                //单个城市下单个项目
                //单个项目
                /*if (!cityList.get(0).equals("全部城市") && !projectList.get(0).equals("全部项目")) {
                    for (int i = 0; i < projectList.size(); i++) {
                        String projects = projectList.get(i);
                        if (!StringUtils.isEmpty(projects)) {*/
                            PropertyHelplineScopeEntity = new PropertyHelplineScopeEntity();
                            PropertyHelplineScopeEntity.setId(IdGen.uuid());
                            PropertyHelplineScopeEntity.setPropertyHelplineId(hotLineDTO.getAnnouncementId());
                            //通过项目Code绑定城市
                            //List<Object[]> list = this.voteRepository.queryCityByProjectCode(projectIds.get(i));
                            //if (!list.isEmpty() && list.size() > 0){
                                PropertyHelplineScopeEntity.setCity(propertyHelplineRepository.getCityNameById(hotLineDTO.getCity()));
                                PropertyHelplineScopeEntity.setCityId(hotLineDTO.getCity());
                            //}
                            PropertyHelplineScopeEntity.setProjectName(helpLine.getProjectName());
                            PropertyHelplineScopeEntity.setProjectId(helpLine.getProjectId());
                            // PropertyHelplineScopeEntity.setScope("3");
                            PropertyHelplineScopeEntity.setStatus(1);
                            PropertyHelplineScopeEntity.setCreateDate(new Date());
                            PropertyHelplineScopeEntity.setOperateDate(new Date());
                            PropertyHelplineScopeEntity.setCreatePerson(user.getUserName());
                            PropertyHelplineScopeEntity.setOperatePerson(user.getUserName());
                            this.announcementScopeRepository.saveOrUpdate(PropertyHelplineScopeEntity);
                /*        }
                    }
                }*/

                boolean line=propertyHelplineRepository.updateHotLine(helpLine);
                if(line){
                    resultMessage="0";//成功
                }else {
                    resultMessage="1";//失败
                }

                InfoReleaseEntity infoReleaseLog=new InfoReleaseEntity();
                infoReleaseLog.setLogId(IdGen.uuid());
                infoReleaseLog.setLogTime(new Date());//注册日期
                infoReleaseLog.setUserName(user.getStaffName());//用户昵称
                infoReleaseLog.setUserMobile(user.getMobile());//手机号
                infoReleaseLog.setThisSection("服务");//版块
                infoReleaseLog.setThisFunction("资讯管理-便民信息");//功能
                infoReleaseLog.setThisType("修改");//操作类型
                infoReleaseLog.setLogContent(helpLine.getContent());//发布内容
                Map<String, Object> map = queryProjectByHotLineId(helpLine.getId());
                infoReleaseLog.setAsscommunity(map.get("projectNames").toString());//关联社区
                systemLogRepository.addInfoReleaseLog(infoReleaseLog);

            }
        }
        return resultMessage;
    }

    /**
     * 便民信息删除
     * param:user
     * param:id
     * return
     */
    @Override
    public String deleteHotLIne(UserPropertyStaffEntity user, String id) {
        //判断是否执行更新成功
        String resultMessage = "";
        if(!StringUtil.isEmpty(id)){
            PropertyHelplineEntity helpLine=propertyHelplineRepository.getServiceInfoById(id);
            //通过id去查询中间表,返回数据
            List<PropertyHelplineScopeEntity> propertyHelplineScopes=propertyHelplineRepository.getServiceInfos(id);
            for(PropertyHelplineScopeEntity scope:propertyHelplineScopes){
                scope.setStatus(2);
                boolean line=propertyHelplineRepository.updateHotLines(scope);
            }
            if(helpLine!=null){
                helpLine.setState("2");
                boolean line=propertyHelplineRepository.updateHotLine(helpLine);
                if(line){
                    resultMessage="0";//成功
                }else {
                    resultMessage="1";//失败
                }
            }else{
                resultMessage = "2";//此信息不存在!
            }
            InfoReleaseEntity infoReleaseLog=new InfoReleaseEntity();
            infoReleaseLog.setLogId(IdGen.uuid());
            infoReleaseLog.setLogTime(new Date());//注册日期
            infoReleaseLog.setUserName(user.getStaffName());//用户昵称
            infoReleaseLog.setUserMobile(user.getMobile());//手机号
            infoReleaseLog.setThisSection("服务");//版块
            infoReleaseLog.setThisFunction("资讯管理-便民信息");//功能
            infoReleaseLog.setThisType("删除");//操作类型
            infoReleaseLog.setLogContent(helpLine.getContent());//发布内容
            Map<String, Object> map = queryProjectByHotLineId(helpLine.getId());
            infoReleaseLog.setAsscommunity(map.get("projectNames").toString());//
            systemLogRepository.addInfoReleaseLog(infoReleaseLog);

        }
        return resultMessage;
    }

    @Override
    public List<Object[]> queryByHotLineId(String id) {
        return this.propertyHelplineRepository.queryByAnnouncementId(id);
    }

    @Override
    public Map<String, Object> queryProjectByHotLineId(String id) {
        Map<String,Object> resuleMap = new HashMap<>();
        String projectCodes = "";
        String projectNames = "";
        List<Map<String, Object>> mapList = propertyHelplineRepository.queryProjectByHotLineId(id);
        for (int i = 0; i < mapList.size(); i++){
            Map<String, Object> map = mapList.get(i);
            projectCodes = projectCodes + (String) map.get("projectCode") + ",";
            projectNames = projectNames + (String) map.get("projectName") + ",";
        }
        resuleMap.put("projectCodes",projectCodes);
        resuleMap.put("projectNames",projectNames);
        return resuleMap;
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 编辑展示图
    */
    @Override
    public void editDisplay(PropertyHotLineDisplayDTO propertyHotLineDisplayDTO) {
        //全部城市
        if ("0".equals(propertyHotLineDisplayDTO.getCityNum())) {
            List<HouseProjectEntity> list = propertyHelplineRepository.getAllProject();
            for (HouseProjectEntity houseProjectEntity : list) {
                this.saveImg(propertyHotLineDisplayDTO, houseProjectEntity.getPinyinCode());
            }
        }else {
            if ("0".equals(propertyHotLineDisplayDTO.getProjectNum())) {
                //某城市下全部项目
                List<HouseProjectEntity> list = propertyHelplineRepository.getAllProjectByCityNum(propertyHotLineDisplayDTO.getCityNum());
                for (HouseProjectEntity houseProjectEntity : list) {
                    this.saveImg(propertyHotLineDisplayDTO, houseProjectEntity.getPinyinCode());
                }
            }else {
                //某城市下某个项目
                this.saveImg(propertyHotLineDisplayDTO, propertyHotLineDisplayDTO.getProjectNum());
            }
        }
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取便民信息排序序号最大值，+1
    */
    @Override
    public Integer getMaxSortNumber(String projectId) {
        Integer sortNum = propertyHelplineRepository.getSortNumber(projectId);
        if (sortNum != null) {
            sortNum = sortNum + 1;
        }
        return sortNum;
    }

    public void saveImg(PropertyHotLineDisplayDTO propertyHotLineDisplayDTO, String imgFkId) {
        if(propertyHotLineDisplayDTO.getImage()!=null) {
            //--------------------------------------图片路径
            String fileName = imgService.uploadAdminImage(propertyHotLineDisplayDTO.getImage(), ImgType.ACTIVITY);
            //图片地址特殊处理
            String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
            fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
            if (fileName.equals("")) {
                fileName = "默认图";
            }
            PropertyImageEntity propertyImage = propertyImageRepository.getImageUrlById(imgFkId);
            if (propertyImage != null) {
                propertyImage.setImagePath(fileName);
                propertyImage.setUploadName(fileName);
                propertyImage.setModifyDate(new Date());
                propertyImageRepository.updateImage(propertyImage);
            }else{
                PropertyImageEntity image=new PropertyImageEntity();
                image.setImageId(IdGen.uuid());
                image.setUploadDate(new Date());
                image.setImgFkId(imgFkId);
                image.setImagePath(fileName);
                image.setImageType("4");//便民信息
                image.setUploadName(fileName);
                image.setState("0");
                propertyImageRepository.saveImage(image);
            }
        }
    }

    @Override
    public String exportExcel(HttpServletResponse httpServletResponse,PropertyAnnouncementPageDTO hotLineDTO, HttpServletRequest httpServletRequest) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);

        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        PropertyHelplineEntity propertyHelpline=new PropertyHelplineEntity();

        propertyHelpline.setCity(hotLineDTO.getQueryScope());//城市
//        propertyHelpline.setProjectName(hotLineDTO.getProject());//项目
        if (null != hotLineDTO.getProject() && !"0".equals(hotLineDTO.getProject()) && !"".equals(hotLineDTO.getProject())){
            propertyHelpline.setProjectId("'"+hotLineDTO.getProject()+"',");
        }else if (null != hotLineDTO.getQueryScope() && !"0".equals(hotLineDTO.getQueryScope()) && !"".equals(hotLineDTO.getQueryScope())){
            List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(hotLineDTO.getQueryScope());
            String projectIds = "";
            for (Object[] project : projectList) {
                projectIds += "'" + project[0].toString() + "',";
            }
            propertyHelpline.setProjectId(projectIds);
        }
        propertyHelpline.setSortNum(hotLineDTO.getSortNumber());//排序序号
        propertyHelpline.setGroupName(hotLineDTO.getTitle());//名称
        propertyHelpline.setPhone(hotLineDTO.getMemo());//电话
        propertyHelpline.setContent(hotLineDTO.getAnnouncementContent());//内容
        propertyHelpline.setCreateBy(hotLineDTO.getStartTime());//开始时间
        propertyHelpline.setModifyBy(hotLineDTO.getEndTime());//结束时间

        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = hotLineDTO.getRoleScopeList();
        for (Map<String,Object> roleScope : roleScopeList){
            String scopeType = (String) roleScope.get("scopeType");
            if (scopeType.equals("1")){
                //城市级别(分公司)
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(roleScope.get("scopeId").toString());
                for (Object[] project : projectList){
                    if (!roleScopes.contains(project[0].toString())){
                        roleScopes += "'"+project[0].toString()+"',";
                    }
                }
            }else if (scopeType.equals("2")){
                //项目级别
                if (!roleScopes.contains(roleScope.get("scopeId").toString())){
                    roleScopes += "'"+roleScope.get("scopeId")+"',";
                }
            }else if (scopeType.equals("0")){
                //全部城市
                roleScopes += "all,";
            }
        }

        List<PropertyHelplineEntity> hotLines=propertyHelplineRepository.getHotLineList(propertyHelpline, webPage,roleScopes);
        /*List<PropertyAnnouncementPageDTO> hotLineDtos=new ArrayList<PropertyAnnouncementPageDTO>();
        if(hotLines.size()>0){
            for(PropertyHelplineEntity line:hotLines){
                PropertyAnnouncementPageDTO lineDTO=new PropertyAnnouncementPageDTO();
                lineDTO.setAnnouncementId(line.getId());
                lineDTO.setTitle(line.getGroupName());//名称
                lineDTO.setQueryScope(line.getCity());//城市
                lineDTO.setProject(line.getProjectName());//项目
                lineDTO.setMemo(line.getPhone());//电话
                lineDTO.setAnnouncementContent(line.getContent());//内容
                lineDTO.setCreateTime(DateUtils.format(line.getCreateDate(), "yyyy-MM-dd"));//发布时间
                hotLineDtos.add(lineDTO);
            }
        }*/
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet("便民信息统计");
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
        String[] titles = {"序号", "名称", "城市", "项目", "排序序号", "电话", "内容"};
        XSSFRow headRow = sheet.createRow(0);

        if (hotLines.size() > 0) {

            hotLines.forEach(userDTO -> {

                XSSFCell cell=null;
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }

                if (hotLines.size() > 0) {
                    for (int i = 0; i < hotLines.size(); i++) {
                        XSSFRow bodyRow = sheet.createRow(i + 1);
                        PropertyHelplineEntity propertyHelplin = hotLines.get(i);
                        cell = bodyRow.createCell(0);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(i + 1);//序号

                        cell = bodyRow.createCell(1);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(propertyHelplin.getGroupName());//名称

                        cell = bodyRow.createCell(2);
                        cell.setCellStyle(bodyStyle);
                        cell.setCellValue(propertyHelplin.getCity());//城市

                        cell = bodyRow.createCell(3);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(propertyHelplin.getProjectName());//项目

                        cell = bodyRow.createCell(4);
                        cell.setCellStyle(bodyStyle);
                        if (propertyHelplin.getSortNum() == null) {
                            cell.setCellValue("");
                        }else {
                            cell.setCellValue(propertyHelplin.getSortNum());//排序序号
                        }

                        cell = bodyRow.createCell(5);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(propertyHelplin.getPhone());//电话

                        cell = bodyRow.createCell(6);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(propertyHelplin.getContent());//内容

                    }
                }
            });
        }
        try {
            //String fileName = new String(("活动报名列表").getBytes(), "ISO8859-1");
            String fileName = new String(("便民信息列表").getBytes(), "ISO8859-1");
            String agent = httpServletRequest.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                fileName = java.net.URLEncoder.encode("便民信息列表", "UTF8");
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
        return null;
    }
}

