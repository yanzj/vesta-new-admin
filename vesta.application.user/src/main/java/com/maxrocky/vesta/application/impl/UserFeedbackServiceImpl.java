package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.UserFeedbackDTO;
import com.maxrocky.vesta.application.DTO.admin.UserFeedbackSearchDTO;
import com.maxrocky.vesta.application.DTO.json.AppealDTO;
import com.maxrocky.vesta.application.DTO.json.UI0010.FeedbackParamJsonDTO;
import com.maxrocky.vesta.application.inf.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by kicky on 2016/2/24.
 */
@Service
public class UserFeedbackServiceImpl implements UserFeedbackService {
    @Autowired
    UserFeedbackRepository userFeedbackRepository;
    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    UserImageRepository userImageRepository;
    @Autowired
    UserSettingService userSettingService;
    @Autowired
    HouseInfoRepository houseInfoRepository;
    @Autowired
    SystemLogRepository systemLogRepository;
    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;
    @Autowired
    SMSAlertsService smsAlertsService;
    @Autowired
    SMSAuthService smsAuthService;
    @Autowired
    UserCRMRepository userCRMRepository;
    @Autowired
    InvoicesTotalRepository invoicesTotalRepository;
    @Autowired
    HouseInfoService houseInfoService;

    @Override
    public List<UserFeedbackDTO> FeedbackList(UserFeedbackSearchDTO userFeedbackSearchDTO, WebPage webPage) {
        List<UserFeedbackDTO> userFeedbackDTOList = new ArrayList<>();//DTO集合
        UserFeedbackEntity userFeedback = new UserFeedbackEntity();
        // 初始化 登陆人所负责范围
        userFeedback.setProjectId(userFeedbackSearchDTO.getQueryScope());

        if (null != userFeedbackSearchDTO.getContent() && !"".equals(userFeedbackSearchDTO.getContent())) {
            userFeedback.setContent(userFeedbackSearchDTO.getContent());
        }
        if (null != userFeedbackSearchDTO.getUserName() && !"".equals(userFeedbackSearchDTO.getUserName())) {
            UserInfoEntity UserInfo = userInfoRepository.getByName(userFeedbackSearchDTO.getUserName());
            userFeedback.setUserId(UserInfo.getUserId());
        }

        if (null != userFeedbackSearchDTO.getStartDate() && !"".equals(userFeedbackSearchDTO.getStartDate())) {
            userFeedback.setStartDate(DateUtils.parse(userFeedbackSearchDTO.getStartDate()));
        }
        if (null != userFeedbackSearchDTO.getEndDate() && !"".equals(userFeedbackSearchDTO.getEndDate())) {
            userFeedback.setEndDate(DateUtils.parse(userFeedbackSearchDTO.getEndDate()));
        }

        List<UserFeedbackEntity> userFeedbackList = userFeedbackRepository.FeedbackList(userFeedback, webPage);
        for (UserFeedbackEntity userFeedbackEntity : userFeedbackList) {
            UserFeedbackDTO userFeedbackDTO = new UserFeedbackDTO();
            userFeedbackDTO.setId(userFeedbackEntity.getId());
            userFeedbackDTO.setContent(userFeedbackEntity.getContent());
            userFeedbackDTO.setTime(DateUtils.format(userFeedbackEntity.getCreateOn(), DateUtils.FORMAT_LONG));
            UserInfoEntity info = userInfoRepository.get(userFeedbackEntity.getUserId());
            userFeedbackDTO.setName(info.getRealName());
            userFeedbackDTO.setUserName(info.getUserName());
            String userType = "";
            switch (info.getUserType()) {

                case UserInfoEntity.USER_TYPE_VISITOR:
                    userType = "游客";
                    break;
                case UserInfoEntity.USER_TYPE_NORMAL:
                    userType = "普通";
                    break;
                case UserInfoEntity.USER_TYPE_OWNER:
                    userType = "业主";
                    break;
                case UserInfoEntity.USER_TYPE_FAMILY:
                    userType = "家属";
                    break;
                case UserInfoEntity.USER_TYPE_TENANT:
                    userType = "租户";
                    break;
            }
            userFeedbackDTO.setType(userType);
            List<UserImageEntity> userImageList = userImageRepository.getUserImageByBusinessId(userFeedbackEntity.getId());
            Map<String, String> imgUrl = new LinkedHashMap<>();
            for (UserImageEntity img : userImageList) {
                imgUrl.put(img.getId(), img.getUrl());
            }
            userFeedbackDTO.setShowUrl(imgUrl);
            userFeedbackDTOList.add(userFeedbackDTO);

        }
        return userFeedbackDTOList;
    }

    @Override
    public UserFeedbackDTO getUserImageByBusinessId(String id) {
        UserFeedbackDTO userFeedbackDTO = new UserFeedbackDTO();
        if (null != id && !"".equals(id)) {
            List<UserImageEntity> userImageList = userImageRepository.getUserImageByBusinessId(id);
            Map<String, String> imgUrl = new LinkedHashMap<>();
            for (UserImageEntity img : userImageList) {
                imgUrl.put(img.getId(), img.getUrl());
            }
            userFeedbackDTO.setShowUrl(imgUrl);
            return userFeedbackDTO;
        } else {
            return null;
        }
    }

    /**
     * 意见反馈列表
     * param user
     * param feedbackDTO
     * param webPage
     * return
     */
    @Override
    public List<UserFeedbackSearchDTO> getFeedbackList(UserPropertyStaffEntity user, UserFeedbackSearchDTO feedbackDTO, WebPage webPage) {
        //检索条件
        UserFeedbackEntity feedback = new UserFeedbackEntity();
        if (feedbackDTO != null) {
//            feedback.setProjectName(feedbackDTO.getQueryScope());//项目
            //追加区域项目检索条件-Wyd20170401
            //如果检索项目不为Null,直接set feedback的projectId
            //如果检索项目为Null,城市不为Null,set feedback的projectId为该城市下所有项目Code,逗号间隔
            if (null != feedbackDTO.getProjectCode() && !"0".equals(feedbackDTO.getProjectCode()) && !"".equals(feedbackDTO.getProjectCode())){
                feedback.setProjectId("'"+feedbackDTO.getProjectCode()+"',");
            }else if (null != feedbackDTO.getScopeId() && !"0".equals(feedbackDTO.getScopeId()) && !"".equals(feedbackDTO.getScopeId())){
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(feedbackDTO.getScopeId());
                String projectIds = "";
                for (Object[] project : projectList) {
                    projectIds += "'" + project[0].toString() + "',";
                }
                feedback.setProjectId(projectIds);
            }
            feedback.setUserType(feedbackDTO.getUserType());//用户类型
            feedback.setState(feedbackDTO.getStatus());//反馈状态
            feedback.setFeedBackType(feedbackDTO.getFeedbackType());//反馈类型
            feedback.setMemo(feedbackDTO.getMemo());//来源
            feedback.setMobile(feedbackDTO.getPhone());//电话
            feedback.setContent(feedbackDTO.getContent());//内容
            feedback.setCreateBy(feedbackDTO.getStartDate());//开始时间(替用字段)
            feedback.setModifyBy(feedbackDTO.getEndDate());//结束时间(替用字段)
            feedback.setFbClassification(feedbackDTO.getFbClassification());
        }
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = feedbackDTO.getRoleScopeList();
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
        //获取反馈信息
        List<UserFeedbackEntity> feedbackList = userFeedbackRepository.getFeedbackList(feedback, webPage, roleScopes);
        //页面内容：封装到dto里
        List<UserFeedbackSearchDTO> userFeedbackDTO = new ArrayList<UserFeedbackSearchDTO>();
        if (feedbackList.size() > 0) {
            for (UserFeedbackEntity userFeedback : feedbackList) {
                UserFeedbackSearchDTO feedbackDTOs = new UserFeedbackSearchDTO();
                feedbackDTOs.setId(userFeedback.getId());
                feedbackDTOs.setPhone(userFeedback.getMobile());
                if (userFeedback.getUserType().equals("2")) {
                    feedbackDTOs.setUserType("普通用户");
                } else if (userFeedback.getUserType().equals("3")) {
                    feedbackDTOs.setUserType("业主");
                } else if (userFeedback.getUserType().equals("4")) {
                    feedbackDTOs.setUserType("同住人");
                } else if (userFeedback.getUserType().equals("6")) {
                    feedbackDTOs.setUserType("虚拟用户");
                }
                if (userFeedback.getFbClassification() != null) {
                    if (userFeedback.getFbClassification().equals("1")) {
                        feedbackDTOs.setFbClassification("便民信息纠错");
                    } else if (userFeedback.getFbClassification().equals("2")) {
                        feedbackDTOs.setFbClassification("意见反馈");
                    }
                }
                feedbackDTOs.setContent(userFeedback.getContent());
                feedbackDTOs.setStartDate(DateUtils.format(userFeedback.getCreateOn(), "yyyy-MM-dd"));
                feedbackDTOs.setQueryScope(userFeedback.getProjectName());
              /*  if(userFeedback.getFeedBackType().equals("1")){
                    feedbackDTOs.setFeedbackType("APP");
                }else if(userFeedback.getFeedBackType().equals("3")){
                    feedbackDTOs.setFeedbackType("微信");
                }*/
                if (userFeedback.getFeedBackType().equals("1")) {
                    feedbackDTOs.setFeedbackType("APP");
                } else if (userFeedback.getFeedBackType().equals("3")) {
                    feedbackDTOs.setFeedbackType("微信");
                }
                if (userFeedback.getMemo().equals("1")) {
                    feedbackDTOs.setMemo("APP");
                } else if (userFeedback.getMemo().equals("2")) {
                    feedbackDTOs.setMemo("微信");
                }
                if (userFeedback.getState().equals("1")) {
                    feedbackDTOs.setStatus("未处理");
                } else if (userFeedback.getState().equals("2")) {
                    feedbackDTOs.setStatus("已处理");
                }
                feedbackDTOs.setState(userFeedback.getState());
                feedbackDTOs.setAddress(userFeedback.getAddress());
                userFeedbackDTO.add(feedbackDTOs);
            }
        }
        return userFeedbackDTO;
    }

    /**
     * 通过主键Id获取意见反馈信息
     *
     * @param feedbackId 主键Id
     * @return UserFeedbackEntity
     */
    public UserFeedbackEntity getFeedbackById(String feedbackId) {
        UserFeedbackEntity userFeedbackEntity = userFeedbackRepository.getFeedbackInfo(feedbackId);
        return userFeedbackEntity;
    }

    /**
     * 详情
     * param feedbackDTO
     * return
     */
    @Override
    public UserFeedbackSearchDTO feedInfo(UserFeedbackSearchDTO feedbackDTO) {
        //封装到dto
        UserFeedbackSearchDTO feedbackDTOs = new UserFeedbackSearchDTO();
        if (!StringUtil.isEmpty(feedbackDTO.getId())) {
            UserFeedbackEntity feedback = userFeedbackRepository.getFeedbackInfo(feedbackDTO.getId());
            if (feedback != null) {
                UserInfoEntity info = userInfoRepository.get(feedback.getUserId());
                if (info != null) {
                    feedbackDTOs.setUserName(info.getRealName());
                } else {
                    feedbackDTOs.setUserName(feedback.getCreateBy());
                }
                feedbackDTOs.setContent(feedback.getContent());
                feedbackDTOs.setUserType(feedback.getAddress());
                feedbackDTOs.setPhone(feedback.getMobile());
                feedbackDTOs.setStartDate(DateUtils.format(feedback.getCreateOn()));//反馈时间

                if (feedback.getFeedBackType().equals("1")) {
                    feedbackDTOs.setFeedbackType("APP");
                } else if (feedback.getFeedBackType().equals("3")) {
                    feedbackDTOs.setFeedbackType("微信");
                }
                //处理时间根据处理状态回显,已处理状态记录的处理时间即为修改时间,未处理状态记录无处理时间
                if (feedback.getState().equals("1")) {
                    feedbackDTOs.setStatus("未处理");
                    feedbackDTOs.setMemo(null);
                    feedbackDTOs.setEndDate(null);
                } else if (feedback.getState().equals("2")) {
                    feedbackDTOs.setStatus("已处理");
                    feedbackDTOs.setMemo("客服人员");
                    feedbackDTOs.setEndDate(DateUtils.format(feedback.getModifyOn()));
                }
            }
        }
        return feedbackDTOs;
    }

    /**
     * 修改
     * param feedbackDTO
     * return
     */
    @Override
    public String feedUpdate(UserPropertyStaffEntity user, UserFeedbackSearchDTO feedbackDTO) {
        //判断是否执行更新成功
        String resultMessage = "";
        if (feedbackDTO != null && !StringUtil.isEmpty(feedbackDTO.getId())) {
            UserFeedbackEntity feedback = userFeedbackRepository.getFeedbackInfo(feedbackDTO.getId());
            if (feedback != null) {
                feedback.setModifyBy(user.getStaffId());
                feedback.setModifyOn(new Date());
                feedback.setState("2");
                boolean result = userFeedbackRepository.update(feedback);
                if (result) {
                    resultMessage = "0";//成功
                } else {
                    resultMessage = "1";//失败
                }
            }
        }
        return resultMessage;
    }

    @Override
    public ApiResult searchFeedBack(String feedbackId) {
        ModelMap modelMap = new ModelMap();
        if (feedbackId != null && !StringUtil.isEmpty(feedbackId)) {
            UserFeedbackEntity feedback = userFeedbackRepository.getFeedbackInfo(feedbackId);
            if (feedback != null) {
                if (feedback.getContent() != null && !feedback.getContent().equals("")) {
                    String[] str = feedback.getContent().split(":");
                        String idCard = str[2].toString();
                        if (idCard != null && !idCard.equals("")) {
                        UserInfoEntity userInfoEntity = userInfoRepository.get(feedback.getUserId());
                        UserCRMEntity userCRMEntity = userCRMRepository.getByIdCard(idCard);
                        if (userCRMEntity != null) {
                            userInfoEntity.setIdCard(userCRMEntity.getIdCard());
                            userInfoEntity.setId(userCRMEntity.getMemberId());
                            userInfoEntity.setRealName(userCRMEntity.getRealName());
                            userInfoEntity.setUserType(UserInfoEntity.USER_TYPE_OWNER);  //认证成功 将用户类型变更为业主
                            userInfoEntity.setJump("4");
                            userInfoRepository.update(userInfoEntity);
                            modelMap.addAttribute("success", "已成功变更为业主");
                            //检索用户房产列表
                            List<HouseInfoEntity> houseInfoEntityList = houseInfoRepository.getHouseByUserMemberId(userCRMEntity.getMemberId());
                            //门禁分配
                            HouseInfoEntity houseInfoEntity = null;
                            if (!houseInfoEntityList.isEmpty() && houseInfoEntityList.size() > 0) {
                                for (int i = 0,length = houseInfoEntityList.size(); i<length; i++){
                                    houseInfoEntity = houseInfoEntityList.get(i);
                                    houseInfoService.assignDoorByHouse(houseInfoEntity.getProjectNum(),houseInfoEntity.getArea(),houseInfoEntity.getBuildingNum(),houseInfoEntity.getUnitNum(),userInfoEntity);
                                }
                            }
                        } else {
                            modelMap.addAttribute("error", "该用户在CRM系统中身份信息不存在，请确认CRM与业主真实信息一致，并确认该信息在CRM中已处理。");
                        }
                    } else {
                        modelMap.addAttribute("error", "该用户在CRM系统中身份信息不存在，请确认CRM与业主真实信息一致，并确认该信息在CRM中已处理。");
                    }
                } else {
                    modelMap.addAttribute("error", "该用户在CRM系统中身份信息不存在，请确认CRM与业主真实信息一致，并确认该信息在CRM中已处理。");
                }
//
//                UserInfoEntity userInfoEntity = userInfoRepository.get(feedback.getUserId());
//                if(idCard != null && !idCard.equals("")){
//                    if(userInfoEntity.getIdCard() != null && !userInfoEntity.getIdCard().equals("")){
//                        UserCRMEntity userCRMEntity = userCRMRepository.getByIdCard(userInfoEntity.getIdCard());
//                        if(userCRMEntity != null){
//                            userInfoEntity.setIdCard(userCRMEntity.getIdCard());
//                            userInfoEntity.setId(userCRMEntity.getMemberId());
//                            userInfoEntity.setRealName(userCRMEntity.getRealName());
//                            userInfoEntity.setUserType(UserInfoEntity.USER_TYPE_OWNER);  //认证成功 将用户类型变更为业主
//                            userInfoRepository.update(userInfoEntity);
//
//                            modelMap.addAttribute("success","已成功变更为业主");
//                        }else{
//                            modelMap.addAttribute("error","该用户在CRM中身份信息不完整，请确认信息已在CRM中处理");
//                        }
//                    }else{
//                        modelMap.addAttribute("error","该用户在CRM中身份信息不完整，请确认信息已在CRM中处理");
//                    }
//                }
//                UserCRMEntity userCRMEntity = userCRMRepository.get(feedback.getUserId());
//                if(userCRMEntity != null){
//                    if(userCRMEntity.getIdCard() != null && !userCRMEntity.getIdCard().equals("")){
//                        UserInfoEntity userInfoEntity = userInfoRepository.get(userCRMEntity.getUserId());
//                        if(userInfoEntity != null){
//                            userInfoEntity.setIdCard(userCRMEntity.getIdCard());
//                            userInfoEntity.setId(userCRMEntity.getMemberId());
//                            userInfoEntity.setRealName(userCRMEntity.getRealName());
//                            userInfoEntity.setUserType(UserInfoEntity.USER_TYPE_OWNER);  //认证成功 将用户类型变更为业主
//                            userInfoRepository.update(userInfoEntity);
//
//                            modelMap.addAttribute("success","已成功变更为业主");
//                        }
//                    }else{
//                        modelMap.addAttribute("error","该用户在CRM中身份信息不完整，请确认信息已在CRM中处理");
//                    }
//                }else{
//                    modelMap.addAttribute("error","CRM系统里无此用户信息");
//                }
            } else {
                modelMap.addAttribute("error", "没有找到对应的用户信息");
            }
        }
        return new SuccessApiResult(modelMap);
    }

    /**
     * 保存意见反馈
     * @param user 用户
     * @return ApiResult
     */
    public ApiResult saveFeedback(UserInfoEntity user, FeedbackParamJsonDTO feedbackParamJsonDTO) {
        if (!VerifyUtils.isMobile(feedbackParamJsonDTO.getMobile())) {
            return new ErrorApiResult("tip_00000283");
        }
        UserFeedbackEntity userFeedbackEntity = new UserFeedbackEntity();
        userFeedbackEntity.setId(IdGen.uuid());
        userFeedbackEntity.setUserId(user.getUserId());
        userFeedbackEntity.setState("1");   //未处理
        userFeedbackEntity.setContent(feedbackParamJsonDTO.getContent());
        userFeedbackEntity.setMobile(feedbackParamJsonDTO.getMobile());
        userFeedbackEntity.setCreateBy(user.getUserId());
        userFeedbackEntity.setCreateOn(new Date());
        userFeedbackEntity.setModifyBy(null);
        userFeedbackEntity.setModifyOn(null);
        userFeedbackEntity.setFeedBackType("1");    //金茂会员
        userFeedbackEntity.setMemo("2");    //意见反馈
        userFeedbackEntity.setAddress("");
        userFeedbackEntity.setUserType(user.getUserType());
        userFeedbackEntity.setFbClassification("2");        //分类:意见反馈
        UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(user.getUserId());
        if (null != userSettingEntity) {
            userFeedbackEntity.setProjectId(userSettingEntity.getPinyinCode());
            userFeedbackEntity.setProjectName(userSettingEntity.getProjectName());
        }
        userFeedbackRepository.create(userFeedbackEntity);


        //调用单据统计
        String projectId = "";
        String dateNow = DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
        //反馈统计已存在项目
        InvoicesTotalEntity invoices = invoicesTotalRepository.getTotalInfo(dateNow, projectId);
        //如果存在，更新反馈数量
        if (invoices != null) {
            if (!StringUtil.isEmpty(projectId)) {
                invoices.setFeedbackTotal(invoices.getFeedbackTotal() + 1);
                //invoices.setCity(cityId);
            } else {
                invoices.setFeedbackNum(invoices.getFeedbackNum() + 1);
            }
            invoicesTotalRepository.update(invoices);
        } else {//没有则创建
            InvoicesTotalEntity invoicesTotal = new InvoicesTotalEntity();
            invoicesTotal.setId(IdGen.uuid());
            invoicesTotal.setCreateDate(new Date());
            if (!StringUtil.isEmpty(projectId)) {
                //invoicesTotal.setCity(cityId);
                //invoicesTotal.setProject(projectId);
                invoicesTotal.setFeedbackTotal(1);
                invoicesTotal.setRepairNum(0);
                invoicesTotal.setFeedbackNum(0);
                invoicesTotal.setPaymentNum(0);
                invoicesTotal.setVisitorNum(0);
            } else {
                invoicesTotal.setFeedbackNum(1);
                invoicesTotal.setFeedbackTotal(0);
                invoicesTotal.setRepairNum(0);
                invoicesTotal.setPaymentNum(0);
                invoicesTotal.setVisitorNum(0);
            }
            invoicesTotalRepository.create(invoicesTotal);
        }

        //增加日志
        UserDocumentsEntity userDocumentsLog = new UserDocumentsEntity();
        userDocumentsLog.setLogId(IdGen.uuid());
        userDocumentsLog.setLogTime(new Date());//注册日期
        userDocumentsLog.setUserName(user.getNickName());//用户昵称
        userDocumentsLog.setUserType(user.getUserType());//用户类型
        userDocumentsLog.setUserMobile(user.getMobile());//手机号
        userDocumentsLog.setSourceFrom(user.getSourceType());//来源
        userDocumentsLog.setThisType("关于app-对软件反馈");//单据类型
        userDocumentsLog.setDocNum(userFeedbackEntity.getId());//单据编号
        userDocumentsLog.setLogContent(userFeedbackEntity.getContent());//单据内容
        UserSettingEntity userSettingEntitys = userSettingService.getUserSettingEntityByUserId(user.getUserId());
        HouseInfoEntity houseInfoEntity = houseInfoRepository.getHouseByMemberId(user.getId());
        if (null != userSettingEntitys) {
            userDocumentsLog.setProjectId(userSettingEntitys.getProjectName());
        } else {
            userDocumentsLog.setProjectId("");//项目
        }
        if (null != houseInfoEntity) {
            userDocumentsLog.setRealEstate(houseInfoEntity.getAddress());
        }
        systemLogRepository.addUserDocumentsLog(userDocumentsLog);
        return new SuccessApiResult();
    }

    /**
     * 保存身份申述
     *
     * @param user 用户
     * @return ApiResult
     */
    public ApiResult saveUserAppeal(UserInfoEntity user, AppealDTO appealDTO) {
        if (!VerifyUtils.isMobile(appealDTO.getMobile())) {
            return new ErrorApiResult("tip_00000283");
        }
        UserFeedbackEntity userFeedbackEntity = new UserFeedbackEntity();
        userFeedbackEntity.setId(IdGen.uuid());
        userFeedbackEntity.setUserId(user.getUserId());
        userFeedbackEntity.setState("1");   //未处理
        userFeedbackEntity.setContent("姓名:" + appealDTO.getRealName() + "，证件号:" + appealDTO.getIdCard());
        userFeedbackEntity.setMobile(user.getMobile());
        userFeedbackEntity.setCreateBy(user.getUserId());
        userFeedbackEntity.setCreateOn(new Date());
        userFeedbackEntity.setModifyBy(null);
        userFeedbackEntity.setModifyOn(null);
        userFeedbackEntity.setFeedBackType("1");    //金茂会员
        userFeedbackEntity.setMemo("1");    //身份申诉
        userFeedbackEntity.setAddress(appealDTO.getAddress());
        userFeedbackEntity.setUserType(user.getUserType());
        userFeedbackEntity.setProjectId(appealDTO.getProjectId());      //项目编码
        userFeedbackEntity.setProjectName(appealDTO.getProjectName());  //项目名称

//        UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(user.getUserId());
//        if (null != userSettingEntity) {
//            userFeedbackEntity.setProjectId(userSettingEntity.getPinyinCode());
//            userFeedbackEntity.setProjectName(userSettingEntity.getProjectName());
//        }

        //发送短信消息提醒
        List<SMSPeopleAlertsEntity> smsPeopleAlertsEntityList = smsAlertsService.getAllByModel("", "身份申诉管理");

        //同一个人，如果是不同项目，相同模块，选择一个，相同的name、phone、model
        for (int i=0; i<smsPeopleAlertsEntityList.size()-1; i++) {
            SMSPeopleAlertsEntity smsPeopleAlertsEntity = smsPeopleAlertsEntityList.get(i);
            for (int j=smsPeopleAlertsEntityList.size()-1; j>i; j--) {
                SMSPeopleAlertsEntity smsPeopleAlertsEntity1 = smsPeopleAlertsEntityList.get(j);
                if ((smsPeopleAlertsEntity.getName()).equals(smsPeopleAlertsEntity1.getName()) && (smsPeopleAlertsEntity.getPhone()).equals(smsPeopleAlertsEntity1.getPhone()) && (smsPeopleAlertsEntity.getModel()).equals(smsPeopleAlertsEntity1.getModel())) {
                    smsPeopleAlertsEntityList.remove(smsPeopleAlertsEntity1);
                }
            }
        }
        for (SMSPeopleAlertsEntity smsPeopleAlertsEntity : smsPeopleAlertsEntityList) {
            smsPeopleAlertsEntity.setContent(smsPeopleAlertsEntity.getContent() + " ," + userFeedbackEntity.getContent() + " ,手机号:" + userFeedbackEntity.getMobile() + "。");
            smsAuthService.sendSMSAlerts(smsPeopleAlertsEntity);
        }

        userFeedbackRepository.create(userFeedbackEntity);
        return new SuccessApiResult();
    }

    /**
     * 导出excel
     * param:user
     * param:httpServletResponse
     * return
     */
    @Override
    public String exportExcel(UserPropertyStaffEntity user, UserFeedbackSearchDTO feedbackDTO,
                              HttpServletResponse httpServletResponse, String type, HttpServletRequest httpServletRequest) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);

        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        if ("1".equals(type)) {
            if ("1".equals(feedbackDTO.getMemo())) {
                feedbackDTO.setFeedbackType("1");
            } else if ("2".equals(feedbackDTO.getMemo())) {
                feedbackDTO.setFeedbackType("3");
            }
            feedbackDTO.setMemo("2");
            List<UserFeedbackSearchDTO> userFeedDTO = getFeedbackList(user, feedbackDTO, webPage);

            XSSFSheet sheet = workBook.createSheet("意见反馈统计");

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

            String[] titles = {"序号", "手机号", "用户类型", "内容", "反馈时间", "项目", "来源", "处理状态", "意见反馈来源分类"};
            XSSFRow headRow = sheet.createRow(0);

            if (userFeedDTO.size() > 0) {

                userFeedDTO.forEach(userDTO -> {

                    XSSFCell cell = null;
                    for (int i = 0; i < titles.length; i++) {
                        cell = headRow.createCell(i);
                        headRow.createCell(i).setCellValue(titles.length);
                        sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                        cell.setCellStyle(headStyle);
                        cell.setCellValue(titles[i]);
                    }

                    if (userFeedDTO.size() > 0) {
                        for (int i = 0; i < userFeedDTO.size(); i++) {
                            XSSFRow bodyRow = sheet.createRow(i + 1);
                            UserFeedbackSearchDTO userDto = userFeedDTO.get(i);
                            cell = bodyRow.createCell(0);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(i + 1);//序号

                            cell = bodyRow.createCell(1);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getPhone());//手机号

                            cell = bodyRow.createCell(2);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getUserType());//用户类型

                            cell = bodyRow.createCell(3);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getContent());//内容

                            cell = bodyRow.createCell(4);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getStartDate());//反馈时间

                            cell = bodyRow.createCell(5);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getQueryScope());//项目

                            cell = bodyRow.createCell(6);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getFeedbackType());//来源

                            cell = bodyRow.createCell(7);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getStatus());//处理状态

                            cell = bodyRow.createCell(8);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getFbClassification());//意见反馈来源分类
                        }
                    }
                });
            }
            try {
                //String fileName = new String(("意见反馈管理").getBytes(), "ISO8859_1");
                String fileName = new String(("意见反馈管理").getBytes(), "ISO8859-1");
                String agent = httpServletRequest.getHeader("USER-AGENT");
                if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                        && -1 != agent.indexOf("Trident")) {// ie

                    fileName = java.net.URLEncoder.encode("意见反馈管理", "UTF8");
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
            if ("1".equals(feedbackDTO.getMemo())) {
                feedbackDTO.setFeedbackType("1");
            } else if ("2".equals(feedbackDTO.getMemo())) {
                feedbackDTO.setFeedbackType("3");
            }
            feedbackDTO.setMemo("1");
            List<UserFeedbackSearchDTO> userFeedDTO = getFeedbackList(user, feedbackDTO, webPage);

            XSSFSheet sheet = workBook.createSheet("身份申诉列表");

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

            String[] titles = {"序号", "手机号", "用户类型", "内容", "申诉时间", "项目", "房间号", "来源", "处理状态"};
            XSSFRow headRow = sheet.createRow(0);

            if (userFeedDTO.size() > 0) {

                userFeedDTO.forEach(userDTO -> {

                    XSSFCell cell = null;
                    for (int i = 0; i < titles.length; i++) {
                        cell = headRow.createCell(i);
                        headRow.createCell(i).setCellValue(titles.length);
                        sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                        cell.setCellStyle(headStyle);
                        cell.setCellValue(titles[i]);
                    }

                    if (userFeedDTO.size() > 0) {
                        for (int i = 0; i < userFeedDTO.size(); i++) {
                            XSSFRow bodyRow = sheet.createRow(i + 1);
                            UserFeedbackSearchDTO userDto = userFeedDTO.get(i);
                            cell = bodyRow.createCell(0);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(i + 1);//序号

                            cell = bodyRow.createCell(1);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getPhone());//手机号

                            cell = bodyRow.createCell(2);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getUserType());//用户类型

                            cell = bodyRow.createCell(3);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getContent());//内容

                            cell = bodyRow.createCell(4);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getStartDate());//申诉时间

                            cell = bodyRow.createCell(5);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getQueryScope());//项目

                            cell = bodyRow.createCell(6);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getAddress());//房间号

                            cell = bodyRow.createCell(7);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getFeedbackType());//来源

                            cell = bodyRow.createCell(8);
                            cell.setCellStyle(bodyStyle);// 表格黑色边框
                            cell.setCellValue(userDto.getStatus());//处理状态
                        }
                    }
                });
            }
            try {
                //String fileName = new String(("身份申诉管理").getBytes(), "ISO8859_1");
                String fileName = new String(("身份申诉管理").getBytes(), "ISO8859-1");
                String agent = httpServletRequest.getHeader("USER-AGENT");
                if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                        && -1 != agent.indexOf("Trident")) {// ie

                    fileName = java.net.URLEncoder.encode("身份申诉管理", "UTF8");
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
        }
        return null;
    }
}
