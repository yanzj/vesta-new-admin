package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.application.inf.OperationLogService;
import com.maxrocky.vesta.application.DTO.adminDTO.MessageInsertDTO;
import com.maxrocky.vesta.application.inf.MessageInsertService;
import com.maxrocky.vesta.application.inf.PropertyAnnouncementService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by JillChen on 2016/1/23.
 */
@Service
public class PropertyAnnouncementServiceImpl implements PropertyAnnouncementService {
    /**
     * 业主app公告列表
     * @return
     */
    @Autowired
    private PropertyAnnouncementRepository propertyAnnouncementRepository;

    @Autowired
    private AnnouncementRepository announcementRepository;

    /**
     * 物业项目公司 持久层接口
     */
    @Autowired
    PropertyCompanyRepository propertyCompanyRepository;

    @Autowired
    MessageInsertService messageInsertService;
    /* 房产信息 */
    @Autowired
    HouseInfoRepository houseInfoRepository;

    @Autowired
    HouseUserBookRepository houseUserBookRepository;

    @Autowired
    UserSettingRepository userSettingRepository;

    /**
     *  后台核心日志 业务逻辑层接口
     */
    @Autowired
    private OperationLogService operationLogService;

    @Override
    public ApiResult propertyAnnouncementList(UserInfoEntity user,Page page) throws GeneralException {
        try {
            // 根据用户ID查询 用户默认项目ID
            UserSettingEntity userSettingEntity = userSettingRepository.get(user.getUserId());

            List<PropertyAnnouncementDTO> propertyAnnouncementlist = new ArrayList<PropertyAnnouncementDTO>();
            List<PropertyAnnouncementEntity> announcementList = propertyAnnouncementRepository.propertyAnnouncementList(userSettingEntity.getProjectId(),page);
            if (announcementList != null) {
                for (PropertyAnnouncementEntity propertyAnnouncement : announcementList) {
                    PropertyAnnouncementDTO propertyAnnouncementDTO = new PropertyAnnouncementDTO();
                    propertyAnnouncementDTO.setAnnouncementId(propertyAnnouncement.getAnnouncementId());          //公告ID
                    propertyAnnouncementDTO.setAnnouncementContent(propertyAnnouncement.getAnnouncementContent());//公告内容
                    propertyAnnouncementDTO.setCreateTime(DateUtils.format(propertyAnnouncement.getCreateTime()));//创建时间
                    propertyAnnouncementDTO.setTitle(propertyAnnouncement.getTitle());                            //公告标题
                    propertyAnnouncementDTO.setType(propertyAnnouncement.getType());          //公告类型
                    propertyAnnouncementlist.add(propertyAnnouncementDTO);
                }

            }

            return new SuccessApiResult(propertyAnnouncementlist);
        }catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("fun_00000056");
        }
    }


    /**
     * 获取最新的标题和id
     *
     * @return
     */
    @Override
    public ApiResult homeProperty(UserInfoEntity user) {
        try {
            // 根据用户ID查询 用户默认项目ID
            UserSettingEntity userSettingEntity = userSettingRepository.get(user.getUserId());

            PropertyTitleDTO propertyTitleDTO=new PropertyTitleDTO();
            List<PropertyAnnouncementEntity> announcementList = propertyAnnouncementRepository.propertyAnnouncementList(userSettingEntity.getProjectId());
            if (announcementList.size() > 0) {
                propertyTitleDTO.setId(announcementList.get(0).getAnnouncementId());
                propertyTitleDTO.setTitle(announcementList.get(0).getTitle());
            }
            return new SuccessApiResult(propertyTitleDTO);
        }catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("fun_00000056");
        }
    }
    /*******************************************************以下为后端*************************************************/

    /**
     * 初始化物业公告列表(后端)
     * @param propertyAnnouncementPage
     * @return
     */
    @Override
    public List<PropertyAnnouncementPageDTO> queryPropertyAnnouncement(PropertyAnnouncementPageDTO propertyAnnouncementPage,WebPage webPage) {
        PropertyAnnouncementEntity propertyAnnouncement = new PropertyAnnouncementEntity();// 物业公告表
        // 初始化 登陆人查询范围
        propertyAnnouncement.setMemo(propertyAnnouncementPage.getQueryScope());//初始化 登陆人查询范围

        propertyAnnouncement.setTitle(propertyAnnouncementPage.getTitle());//公告标题
        propertyAnnouncement.setAnnouncementSummary(propertyAnnouncementPage.getStartTime());//公告开始时间(用公告简介当临时变量 开始时间)
        propertyAnnouncement.setAnnouncementContent(propertyAnnouncementPage.getEndTime());//公告结束时间(用公告内容当临时变量 结束时间)
        propertyAnnouncement.setProjectId(propertyAnnouncementPage.getProject());//项目ID
        // 查询物业公告列表
        List<PropertyAnnouncementEntity> propertyAnnouncementEntityList = propertyAnnouncementRepository.queryPropertyAnnouncement(propertyAnnouncement, webPage);
        List<PropertyAnnouncementPageDTO> propertyAnnouncementList = new ArrayList<>();// 公告DTO
        for(PropertyAnnouncementEntity propertyAnnouncementMessage : propertyAnnouncementEntityList){
            PropertyAnnouncementPageDTO propertyAnnouncementDTO = new PropertyAnnouncementPageDTO();
            propertyAnnouncementDTO.setAnnouncementId(propertyAnnouncementMessage.getAnnouncementId());// 公告id
            propertyAnnouncementDTO.setTitle(propertyAnnouncementMessage.getTitle());//公告标题
            propertyAnnouncementDTO.setAnnouncementContent(propertyAnnouncementMessage.getAnnouncementContent());// 公告内容
            propertyAnnouncementDTO.setType(propertyAnnouncementMessage.getType());// 公告类型

            // 项目ID不为空则 根据项目ID查询项目名称
            if(null != propertyAnnouncementMessage.getProjectId() && !"".equals(propertyAnnouncementMessage.getProjectId())){
                // 根据项目ID 查询项目名称
                List<HouseProjectEntity> houseProjectEntities = propertyCompanyRepository.queryHouseProjectEntity(propertyAnnouncementMessage.getProjectId());
                if(houseProjectEntities.size() > 0){
                    propertyAnnouncementDTO.setProject(houseProjectEntities.get(0).getName());//项目名称
                }else {
                    propertyAnnouncementDTO.setType("");// 为空 项目名称
                }
            }else {
                propertyAnnouncementDTO.setType("");// 为空 项目名称
            }
            propertyAnnouncementDTO.setReleasePerson(propertyAnnouncementMessage.getReleasePerson());//创建人
            propertyAnnouncementDTO.setCreateTime(DateUtils.format(propertyAnnouncementMessage.getAnnouncementTime()));//发布时间
            propertyAnnouncementDTO.setStat(propertyAnnouncementMessage.getStat());//是否发布
            propertyAnnouncementList.add(propertyAnnouncementDTO);
        }

        return propertyAnnouncementList;
    }

    /**
     * 根据物业公告信息ID 删除信息
     * @param announcementId
     * @param userPropertystaffEntity
     * @return
     */
    @Override
    public String removePropertyServicesById(String announcementId, UserPropertyStaffEntity userPropertystaffEntity) {
        // 判断是否执行删除成功
        String resultMessage = "";
        // 根据公告信息ID查询 公告信息详情
        PropertyAnnouncementEntity propertyAnnouncement = propertyAnnouncementRepository.propertyAnnouncementDetail(announcementId);
        if(null != propertyAnnouncement){
            // 执行删除物业公告信息
            boolean delPropertyAnnouncement = propertyAnnouncementRepository.delPropertyAnnouncement(propertyAnnouncement);
            if (delPropertyAnnouncement){
                resultMessage = "0";//此信息删除成功!
            }else {
                resultMessage = "1";//此信息删除失败!
            }
            // 后台核心日志
            OperationLogDTO operation = new OperationLogDTO();
            operation.setUserName(userPropertystaffEntity.getStaffName());  // 用户名
            operation.setProjectId(userPropertystaffEntity.getProjectId());// 用户项目ID
            operation.setContent("删除公告信息!");      // 操作动作
            // 添加后台核心日志
            operationLogService.addOperationLog(operation);

        }else {
            resultMessage = "2";//此信息不存在!
            return resultMessage;
        }
        return resultMessage;
    }

    /**
     * 物业公告管理 添加或修改信息
     * @param userPropertystaffEntit
     * @param propertyAnnouncementPage
     * @return
     */
    @Override
    public boolean saveORupdatePropertyAnnouncement(UserPropertyStaffEntity userPropertystaffEntit, PropertyAnnouncementPageDTO propertyAnnouncementPage) {

        PropertyAnnouncementEntity propertyAnnouncement = new PropertyAnnouncementEntity();//物业公告是体表
        List<PropertyTypeEntity> propertyType = propertyCompanyRepository.queryPropertyTypeEntity(propertyAnnouncementPage.getType(), "");
        if(propertyType.size() > 0){
            propertyAnnouncement.setType(propertyType.get(0).getType());// 公告类别
        }else {
            propertyAnnouncement.setType("");// 公告类别
        }
        propertyAnnouncement.setTitle(propertyAnnouncementPage.getTitle());//公告标题
        propertyAnnouncement.setProjectId(userPropertystaffEntit.getProjectId());//项目id
        propertyAnnouncement.setStat(propertyAnnouncementPage.getStat()); //状态 是否推送 0 否 1 是
        propertyAnnouncement.setAnnouncementContent(propertyAnnouncementPage.getAnnouncementContent());// 公告内容
        propertyAnnouncement.setAnnouncementTime(new Date());// 公告时间
        propertyAnnouncement.setCreateTime(new Date());//创建时间
        propertyAnnouncement.setReleasePerson(userPropertystaffEntit.getStaffName());//发布人
        propertyAnnouncement.setOperator(userPropertystaffEntit.getStaffName());//操作人
        propertyAnnouncement.setOperatDate(new Date());//操作时间

        // 判断 物业公告ID 如果不为空则更新 否则添加信息
        if("".equals(propertyAnnouncementPage.getAnnouncementId())){
            PropertyAnnouncementEntity property = new PropertyAnnouncementEntity();
            property.setAnnouncementId(IdGen.uuid());//添加主键
            propertyAnnouncement.setAnnouncementId(property.getAnnouncementId());
            // 执行物业公告添加操作
            boolean message = propertyAnnouncementRepository.savePropertyAnnouncement(propertyAnnouncement);
            // 添加消息推送
            MessageInsertDTO messageInsertDTO = new MessageInsertDTO();
            messageInsertDTO.setMessageTitle(propertyAnnouncementPage.getTitle());  // 公告消息标题
            messageInsertDTO.setMessageUserType("1"); // 发送人类型，1 为业主
            messageInsertDTO.setMessageType("1");     // 类型 1为公告信息
            messageInsertDTO.setMessageTypeState("1");// 消息类型对应状态
            messageInsertDTO.setMessageUrl(propertyAnnouncement.getAnnouncementId());// 信息ID
            List<String> users = new ArrayList<>();
            if(propertyAnnouncement.getStat().equals("1")){
                // 根据公告 项目ID 查询 项目下所有 业主房屋
                List<UserSettingEntity> userSetting = userSettingRepository.userSettingList(userPropertystaffEntit.getProjectId());
                if(userSetting.size() > 0){
                    for(UserSettingEntity user : userSetting ){
                        users.add(user.getUserId());//用户ID
                    }
                }
            }
            messageInsertService.InsertMessage(messageInsertDTO, users);
            if(message){
                return true;
            }
            // 后台核心日志
            OperationLogDTO operation = new OperationLogDTO();
            operation.setUserName(userPropertystaffEntit.getStaffName());  // 用户名
            operation.setProjectId(userPropertystaffEntit.getProjectId());// 用户项目ID
            operation.setContent("修改公告信息!");      // 操作动作
            // 添加后台核心日志
            operationLogService.addOperationLog(operation);
        }else {
            //根据物业公告ID 查询信息详情
            PropertyAnnouncementEntity propertyAnnouncementEntity = propertyAnnouncementRepository.propertyAnnouncementDetail(propertyAnnouncementPage.getAnnouncementId());
            if(null != propertyAnnouncementEntity){
                propertyAnnouncement.setAnnouncementId(propertyAnnouncementEntity.getAnnouncementId());
                // 执行物业公告更新操作
                propertyAnnouncementRepository.changePropertyAnnouncement(propertyAnnouncement);

                // 后台核心日志
                OperationLogDTO operation = new OperationLogDTO();
                operation.setUserName(userPropertystaffEntit.getStaffName());  // 用户名
                operation.setProjectId(userPropertystaffEntit.getProjectId());// 用户项目ID
                operation.setContent("添加公告信息!");      // 操作动作
                // 添加后台核心日志
                operationLogService.addOperationLog(operation);

                // 添加消息推送
                MessageInsertDTO messageInsertDTO = new MessageInsertDTO();
                messageInsertDTO.setMessageTitle(propertyAnnouncementEntity.getTitle());  // 公告消息标题
                messageInsertDTO.setMessageUserType("1"); // 发送人类型，1 为业主
                messageInsertDTO.setMessageType("1");     // 类型 1为公告信息
                messageInsertDTO.setMessageTypeState("1");// 消息类型对应状态
                messageInsertDTO.setMessageUrl(propertyAnnouncementEntity.getAnnouncementId());// 信息ID
                List<String> users = new ArrayList<>();
                if(propertyAnnouncementPage.getStat().equals("1")){
                    // 根据公告 项目ID 查询 项目下所有 业主房屋
                    List<UserSettingEntity> userSetting = userSettingRepository.userSettingList(propertyAnnouncementEntity.getProjectId());
                    if(userSetting.size() > 0){
                        for(UserSettingEntity user : userSetting ){
                            users.add(user.getUserId());//用户ID
                        }
                    }
                }
                messageInsertService.InsertMessage(messageInsertDTO, users);

                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    /**
     * 根据物业公告ID 查询信息详情
     * @param announcementId
     * @return
     */
    @Override
    public PropertyAnnouncementPageDTO queryPropertyAnnouncementById(String announcementId) {
        PropertyAnnouncementPageDTO propertyAnnouncementPage = new PropertyAnnouncementPageDTO();
        PropertyAnnouncementEntity propertyAnnouncementEntity = propertyAnnouncementRepository.propertyAnnouncementDetail(announcementId);
        propertyAnnouncementPage.setAnnouncementId(propertyAnnouncementEntity.getAnnouncementId());// 公告id
        List<PropertyTypeEntity> propertyType = propertyCompanyRepository.queryPropertyTypeEntity("",propertyAnnouncementEntity.getType());
        if(propertyType.size() > 0){
            propertyAnnouncementPage.setType(propertyType.get(0).getTypeId());// 公告类别
        }else {
            propertyAnnouncementPage.setType("");// 公告类别
        }
        propertyAnnouncementPage.setTitle(propertyAnnouncementEntity.getTitle());//公告标题
        propertyAnnouncementPage.setProject(propertyAnnouncementEntity.getProjectId());//项目ID
        propertyAnnouncementPage.setStat(propertyAnnouncementEntity.getStat());//是否推送
        propertyAnnouncementPage.setAnnouncementContent(propertyAnnouncementEntity.getAnnouncementContent());// 公告内容
        return propertyAnnouncementPage;
    }


    /**
     * 公告类别公共方法
     * @param type
     * @return
     */
    public String announcementMethod(String type){
        String announcementType = "";
        switch (type){//公告类别
            case "1":
                announcementType = PropertyAnnouncementEntity.ANNOUNCEMENT__TYPE_PROPERTY;// 物业通知
                break;
            case "2":
                announcementType = PropertyAnnouncementEntity.ANNOUNCEMENT__TYPE_WORK;//工作通知
                break;
        }
        return announcementType;
    }


}
