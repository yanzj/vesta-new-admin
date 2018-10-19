package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.application.inf.HouseProjectService;
import com.maxrocky.vesta.application.inf.PropertyHelplineService;
import com.maxrocky.vesta.application.inf.PropertyHelplineWeiXinService;
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
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liudongxin on 2016/3/30.
 * 物业热线电话实现方法
 */
@Service
public class PropertyHelplineWeiXinServiceImpl implements PropertyHelplineWeiXinService {
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
    SystemLogRepository systemLogRepository;
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
            List<String> citys=propertyHelplineRepository.getAllCity();
            if(citys.size()>0){
                for(String str:citys){
                    HouseProjectDTO houseProject=new HouseProjectDTO();
                    houseProject.setCity(str);
                    houseProjects.add(houseProject);
                }
            }
            //获取所有小区
            List<Object[]> communities=propertyHelplineRepository.getAllCommunityName(city);
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
            List<PropertyHelplineEntity> propertyHelpline=propertyHelplineRepository.getServicePhoneWeiXin(projectId);
            if(propertyHelpline.size()>0){
                for(PropertyHelplineEntity line:propertyHelpline){
                    WorkOrderUserDTO workOrderUser = new WorkOrderUserDTO();
                    workOrderUser.setContent(line.getContent());
                    workOrderUser.setMobile(line.getPhone());
                    workOrderUser.setGroupName(line.getGroupName());
                    workOrderUsers.add(workOrderUser);
                    department.setMemberList(workOrderUsers);
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
    public ApiResult getServiceInfo(String projectId) throws GeneralException {
        if(StringUtil.isEmpty(projectId)){
            return ErrorResource.getError("tip_pe00000026");//小区为空
        }
        try{
            //服务电话信息
            List<WorkOrderUserDTO> workOrderUsers=new ArrayList<WorkOrderUserDTO>();
            //获取热线电话
            List<PropertyHelplineEntity> propertyHelpline=propertyHelplineRepository.getServiceInfo(projectId);
            if(propertyHelpline.size()>0){
                for(PropertyHelplineEntity line:propertyHelpline){
                    WorkOrderUserDTO workOrderUser=new WorkOrderUserDTO();
                    workOrderUser.setName(line.getGroupName());
                    workOrderUser.setContent(line.getContent());
                    workOrderUsers.add(workOrderUser);
                }
            }
            //增加用户日志记录
            UserVisitLogEntity userVisitLogEntity=new UserVisitLogEntity();
            userVisitLogEntity.setLogId(IdGen.uuid());
            userVisitLogEntity.setLogTime(new Date());//注册日期
         /*   userVisitLogEntity.setUserName(userInfo.getNickName());//用户昵称
            userVisitLogEntity.setUserType(userInfo.getUserType());//用户类型
            userVisitLogEntity.setUserMobile(userInfo.getMobile());//手机号*/
            userVisitLogEntity.setSourceFrom("微信");//来源
            userVisitLogEntity.setThisSection("服务");
            userVisitLogEntity.setThisFunction("便民信息");
            HouseProjectEntity project=houseProjectRepository.getProjectByCode(projectId);
            if(project!=null) {
                userVisitLogEntity.setLogContent(project.getName()+"：便民信息");
            }else{
                userVisitLogEntity.setLogContent("");
            }
            systemLogRepository.addUserVisitLog(userVisitLogEntity);
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
        propertyHelpline.setCity(hotLineDTO.getQueryScope());//城市
        propertyHelpline.setProjectName(hotLineDTO.getProject());//项目
        propertyHelpline.setGroupName(hotLineDTO.getTitle());//名称
        propertyHelpline.setPhone(hotLineDTO.getMemo());//电话
        propertyHelpline.setContent(hotLineDTO.getAnnouncementContent());//内容
        propertyHelpline.setCreateBy(hotLineDTO.getStartTime());//开始时间
        propertyHelpline.setModifyBy(hotLineDTO.getEndTime());//结束时间

        //设置用户权限范围(单位项目)
//        String roleScopes = "";
//        List<Map<String, Object>> roleScopeList = announcementDTO.getRoleScopeList();
//        for (Map<String,Object> roleScope : roleScopeList){
//            String scopeType = (String) roleScope.get("scopeType");
//            if (scopeType.equals("1")){
//                //城市级别(分公司)
//                List<Object[]> projectList = userPropertyStaffRepository.listProjectBy(roleScope.get("scopeId").toString());
//                for (Object[] project : projectList){
//                    if (!roleScopes.contains(project[0].toString())){
//                        roleScopes += "'"+project[0].toString()+"',";
//                    }
//                }
//            }else if (scopeType.equals("2")){
//                //项目级别
//                if (!roleScopes.contains(roleScope.get("scopeId").toString())){
//                    roleScopes += "'"+roleScope.get("scopeId")+"',";
//                }
//            }else if (scopeType.equals("0")){
//                //全部城市
//                roleScopes += "all,";
//            }
//        }

        //获取便民信息
        List<PropertyHelplineEntity> hotLines=propertyHelplineRepository.getHotLineList(propertyHelpline, webPage,"");
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
            propertyHelpline.setProjectId(hotLineDTO.getProject());
            HouseProjectEntity project=houseProjectRepository.get(hotLineDTO.getProject());
            if(project!=null) {
                propertyHelpline.setProjectName(project.getName());//项目
                propertyHelpline.setCity(project.getCity());
            }
            propertyHelpline.setState("1");
            if(!StringUtil.isEmpty(hotLineDTO.getMemo())) {
                propertyHelpline.setPhone(hotLineDTO.getMemo());
            }
            propertyHelpline.setContent(hotLineDTO.getAnnouncementContent());
            if(hotLineDTO.getImage()!=null) {
                String fileName = imgService.uploadAdminImage(hotLineDTO.getImage(), ImgType.ACTIVITY);
                //图片地址特殊处理
                String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                if (fileName.equals("")) {
                    fileName = "默认图";
                }
                PropertyImageEntity image=new PropertyImageEntity();
                image.setImageId(IdGen.uuid());
                image.setUploadDate(new Date());
                image.setImgFkId(hotLineDTO.getProject());
                image.setImagePath(fileName);
                image.setImageType("3");
                image.setUploadName(fileName);
                image.setState("0");
                propertyImageRepository.saveImage(image);
            }
            boolean line=propertyHelplineRepository.saveHotLine(propertyHelpline);
            if(line){
                resultMessage="0";//成功
            }else {
                resultMessage="1";//失败
            }
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
                lineDTO.setQueryScope(helpLine.getCity());//城市
                lineDTO.setProject(helpLine.getProjectName());//项目
                PropertyImageEntity propertyImage=propertyImageRepository.getImageUrlById(helpLine.getProjectId());
                if(propertyImage!=null) {
                    lineDTO.setImagePath(propertyImage.getImagePath());//图片
                }
                lineDTO.setTitle(helpLine.getGroupName());//名称
                lineDTO.setMemo(helpLine.getPhone());//电话
                lineDTO.setAnnouncementContent(helpLine.getContent());//内容
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
            PropertyHelplineEntity helpLine=propertyHelplineRepository.getServiceInfoById(hotLineDTO.getAnnouncementId());
            if(helpLine!=null){
                if(!StringUtil.isEmpty(hotLineDTO.getQueryScope())) {
                    helpLine.setCity(hotLineDTO.getQueryScope());
                }
                if(!StringUtil.isEmpty(hotLineDTO.getProject())) {
                    helpLine.setProjectName(hotLineDTO.getProject());
                }
                if(hotLineDTO.getImage().getSize()>0) {
                    String fileName = imgService.uploadAdminImage(hotLineDTO.getImage(), ImgType.REPAIRS);
                    //图片地址特殊处理
                    String urlTitle = "http:////211.94.93.223/images/";//ImageConfig.PIC_SERVER_INTE_URL
                    fileName = urlTitle + fileName.replace("/opt/image.server/images/images_source/", "");//ImageConfig.PIC_OSS_INTE_URL 应该是这个么？确认后请修改  Magic_ly
                    if (fileName.equals("")) {
                        fileName = "默认图";
                    }
                    PropertyImageEntity propertyImage = propertyImageRepository.getImageUrlById(helpLine.getId());
                    if (propertyImage != null) {
                        propertyImage.setImagePath(fileName);
                        propertyImage.setUploadName(fileName);
                        propertyImage.setModifyDate(new Date());
                        propertyImageRepository.updateImage(propertyImage);
                    }
                }
                if(!StringUtil.isEmpty(hotLineDTO.getTitle())) {
                    helpLine.setGroupName(hotLineDTO.getTitle());
                }
                if(!StringUtil.isEmpty(hotLineDTO.getMemo())){
                    helpLine.setPhone(hotLineDTO.getMemo());
                }
                if(!StringUtil.isEmpty(hotLineDTO.getAnnouncementContent())){
                    helpLine.setContent(hotLineDTO.getAnnouncementContent());
                }
                boolean line=propertyHelplineRepository.updateHotLine(helpLine);
                if(line){
                    resultMessage="0";//成功
                }else {
                    resultMessage="1";//失败
                }
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
        }
        return resultMessage;
    }

    @Override
    public List<Object[]> queryByHotLineId(String id) {
        return null;
    }
}
