package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.PropertyImageDTO;
import com.maxrocky.vesta.application.DTO.RectifyDTO;
import com.maxrocky.vesta.application.DTO.RepairDTO;
import com.maxrocky.vesta.application.DTO.RepairInfoDTO;
import com.maxrocky.vesta.application.inf.BasicRepairInfoService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.utility.AppConfig;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import com.maxrocky.vesta.weixin.WxMessage;
import com.maxrocky.vesta.weixin.WxMessagePush;
import org.apache.axiom.util.stax.wrapper.WrappingXMLInputFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by liudongxin on 2016/4/10.
 * Description:
 * webService:接收金茂项目CRM传递的报修信息
 * ModifyBy:
 */
@Service("basicRepairInfoService")
public class BasicRepairInfoServiceImpl implements BasicRepairInfoService {
    @Autowired
    private FirstClassificationCRMRepository firstClassificationCRMRepository;
    @Autowired
    private SecondClassificationCRMRepository secondClassificationCRMRepository;
    @Autowired
    private ThirdClassificationCRMRepository thirdClassificationCRMRepository;
    @Autowired
    private PropertyRepairRepository propertyRepairRepository;
    @Autowired
    private PropertyRepairCRMRepository propertyRepairCRMRepository;
    @Autowired
    private PropertyRectifyCRMRepository propertyRectifyCRMRepository;
    @Autowired
    private InterfaceLogRepository interfaceLogRepository;
    @Autowired
    private HouseInfoRepository houseInfoRepository;//房屋业主地址
    @Autowired
    private UserCRMRepository userCRMRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PropertyRepairTaskRepository propertyRepairTaskRepository;
    @Autowired
    private PropertyImageRepository propertyImageRepository;
    /**
     * CreateBy:liudongxin
     * Description:接收报修/整改单信息
     * param repairInfo：报修集合
     * ModifyBy:
     */
    @Override
    public String repairInfo(RepairInfoDTO repairInfo) {
        Calendar cal = Calendar.getInstance();
        String errorRepairId="";
        String errorRetifyId="";
            try{
                if (repairInfo != null && repairInfo.getRepairList().size() > 0) {
                    //创建报修单
                    for (RepairDTO repair : repairInfo.getRepairList()) {
                        errorRepairId=repair.getRepairId();
                        PropertyRepairCRMEntity propertyRepair = propertyRepairCRMRepository.getById(repair.getRepairId());
                        //如果有数据，则更新;如果无，则创建
                        if (propertyRepair != null) {
                            if (!StringUtil.isEmpty(repair.getDepartment())) {
                                propertyRepair.setDepartment(repair.getDepartment());
                            }
                            if (!StringUtil.isEmpty(repair.getRoomId())) {
                                propertyRepair.setRoomId(repair.getRoomId());
                            }
                            if (!StringUtil.isEmpty(repair.getRoomNum())) {
                                propertyRepair.setRoomNum(repair.getRoomNum());
                            }
                            if (!StringUtil.isEmpty(repair.getMemberId())) {
                                propertyRepair.setMemberId(repair.getMemberId());
                            }
                            if (!StringUtil.isEmpty(repair.getUserName())) {
                                propertyRepair.setUserName(repair.getUserName());
                            }
                            if (!StringUtil.isEmpty(repair.getUserPhone())) {
                                propertyRepair.setUserPhone(repair.getUserPhone());
                            }
                            if (!StringUtil.isEmpty(repair.getUserAddress())) {
                                propertyRepair.setUserAddress(repair.getUserAddress());
                            }
                            if (!StringUtil.isEmpty(repair.getSendName())) {
                                propertyRepair.setSendName(repair.getSendName());
                            }
                            if (repair.getSendDate() != null) {
                                cal.setTime(repair.getSendDate());
                                //cal.add(Calendar.HOUR_OF_DAY);
                                propertyRepair.setSendDate(cal.getTime());
                            }
                            if (repair.getCreateDate() != null) {
                                cal.setTime(repair.getCreateDate());
                                //cal.add(Calendar.HOUR_OF_DAY);
                                propertyRepair.setCreateDate(cal.getTime());
                            }
                            if(repair.getPredictedDate() != null){//预计完成时间
                                cal.setTime(repair.getPredictedDate());
                                //cal.add(Calendar.HOUR_OF_DAY);
                                propertyRepair.setPredictedDate(cal.getTime());
                            }

                            if (!StringUtil.isEmpty(repair.getClassifyOne())) {
                                FirstClassificationEntity firstClass = firstClassificationCRMRepository.get(repair.getClassifyOne());
                                if(firstClass!=null){
                                    propertyRepair.setClassifyOneName(firstClass.getLabel());
                                }
                                propertyRepair.setClassifyOne(repair.getClassifyOne());
                            }
                            if (!StringUtil.isEmpty(repair.getClassifyTwo())) {
                                SecondClassificationEntity secondClass = secondClassificationCRMRepository.get(repair.getClassifyTwo());
                                if(secondClass!=null){
                                    propertyRepair.setClassifyTwoName(secondClass.getLabel());
                                }
                                propertyRepair.setClassifyTwo(repair.getClassifyTwo());
                            }
                            if (!StringUtil.isEmpty(repair.getClassifyThree())) {
                                ThirdClassificationEntity thirdClass = thirdClassificationCRMRepository.get(repair.getClassifyThree());
                                if(thirdClass!=null){
                                    propertyRepair.setClassifyThreeName(thirdClass.getLabel());
                                }
                                propertyRepair.setClassifyThree(repair.getClassifyThree());
                            }
                            if (!StringUtil.isEmpty(repair.getMode())) {
                                propertyRepair.setMode(repair.getMode());
                            }
                            if (!StringUtil.isEmpty(repair.getRepairDate())) {
                                propertyRepair.setRepairDate(repair.getRepairDate());
                            }
                            if (!StringUtil.isEmpty(repair.getRoomLocation())) {
                                propertyRepair.setRoomLocation(repair.getRoomLocation());
                            }
                            if (!StringUtil.isEmpty(repair.getState())) {
                                propertyRepair.setState(repair.getState());
                            }
                            if (!StringUtil.isEmpty(repair.getDuty())) {
                                propertyRepair.setDuty(repair.getDuty());
                            }
                            if (!StringUtil.isEmpty(repair.getProblemLevel())) {
                                propertyRepair.setProblemLevel(repair.getProblemLevel());
                            }
                            if (!StringUtil.isEmpty(repair.getRepairWay())) {
                                propertyRepair.setRepairWay(repair.getRepairWay());
                            }
                            if (!StringUtil.isEmpty(repair.getContent())) {
                                propertyRepair.setContent(repair.getContent());
                            }
                            if (repair.getTaskDate() != null) {
                                cal.setTime(repair.getTaskDate());
                                //cal.add(Calendar.HOUR_OF_DAY, 8);
                                propertyRepair.setTaskDate(cal.getTime());
                            }
                            if (!StringUtil.isEmpty(repair.getDealWay())) {
                                propertyRepair.setDealWay(repair.getDealWay());
                            }
                            if (!StringUtil.isEmpty(repair.getDealMode())) {
                                propertyRepair.setDealMode(repair.getDealMode());
                            }
                            if (!StringUtil.isEmpty(repair.getDutyCompanyOne())) {
                                propertyRepair.setDutyCompanyOne(repair.getDutyCompanyOne());
                            }
                            if (!StringUtil.isEmpty(repair.getDutyCompanyTwo())) {
                                propertyRepair.setDutyCompanyTwo(repair.getDutyCompanyTwo());
                            }
                            if (!StringUtil.isEmpty(repair.getDutyCompanyThree())) {
                                propertyRepair.setDutyCompanyThree(repair.getDutyCompanyThree());
                            }
                            if (!StringUtil.isEmpty(repair.getRepairCompany())) {
                                propertyRepair.setRepairCompany(repair.getRepairCompany());
                            }
                            if (repair.getDutyTaskDate() != null) {
                                cal.setTime(repair.getDutyTaskDate());
                                //cal.add(Calendar.HOUR_OF_DAY, 8);
                                propertyRepair.setDutyTaskDate(cal.getTime());
                            }
                            if (repair.getDutyReturnDate() != null) {
                                cal.setTime(repair.getDutyReturnDate());
                                //cal.add(Calendar.HOUR_OF_DAY, 8);
                                propertyRepair.setDutyReturnDate(cal.getTime());
                            }
                            propertyRepair.setRepairManager("");
                            propertyRepair.setDealPeople("");
                            propertyRepair.setFailNum(0);
                            propertyRepair.setFailType("0");
                            if (!StringUtil.isEmpty(repair.getRepairManager())) {
                                propertyRepair.setRepairManager(repair.getRepairManager());
//                                UserPropertyStaffEntity user = propertyRepairCRMRepository.getUser(repair.getRepairManager());
                                UserInformationEntity user = propertyRepairCRMRepository.getUserByUserName(repair.getRepairManager());
                                if (user != null) {
                                    propertyRepair.setRepairManagerId(user.getStaffId());
                                    propertyRepair.setDealPeople(user.getStaffId());
                                }
                            }
//                        if(!StringUtil.isEmpty(repair.getDealPeople())){
//                            propertyRepair.setDealPeople(repair.getDealPeople());
//                        }
                            if (!StringUtil.isEmpty(repair.getDealPhone())) {
                                propertyRepair.setDealPhone(repair.getDealPhone());
                            }
                            if (!StringUtil.isEmpty(repair.getDealPeopleTwo())) {
                                propertyRepair.setDealPeopleTwo(repair.getDealPeopleTwo());
                            }
                            if (!StringUtil.isEmpty(repair.getDealPhoneTwo())) {
                                propertyRepair.setDealPhoneTwo(repair.getDealPhoneTwo());
                            }
                            if (repair.getFirstVisitDate() != null) {
                                cal.setTime(repair.getFirstVisitDate());
                                //cal.add(Calendar.HOUR_OF_DAY, 8);
                                propertyRepair.setFirstVisitDate(cal.getTime());
                            }
                            if (repair.getBackupVisitDate() != null) {
                                cal.setTime(repair.getBackupVisitDate());
                                //cal.add(Calendar.HOUR_OF_DAY, 8);
                                propertyRepair.setBackupVisitDate(cal.getTime());
                            }
                            if (!StringUtil.isEmpty(repair.getDealState())) {
                                propertyRepair.setDealState(repair.getDealState());
                            }
                            if (!StringUtil.isEmpty(repair.getDealResult())) {
                                propertyRepair.setDealResult(repair.getDealResult());
                            }
                            if (repair.getDealCompleteDate() != null) {
                                propertyRepair.setDealCompleteDate(repair.getDealCompleteDate());
                            }
                            if (!StringUtil.isEmpty(repair.getVisitOpinion())) {
                                propertyRepair.setVisitOpinion(repair.getVisitOpinion());
                            }
                            if (!StringUtil.isEmpty(repair.getVisitEvaluate())) {
                                if (repair.getVisitEvaluate().equals("highlySatisfied")) {//非常满意
                                    propertyRepair.setVisitEvaluate("5");
                                } else if (repair.getVisitEvaluate().equals("satisfied")) {//满意
                                    propertyRepair.setVisitEvaluate("4");
                                } else if (repair.getVisitEvaluate().equals("commonSatisfied")) {//一般
                                    propertyRepair.setVisitEvaluate("3");
                                } else if (repair.getVisitEvaluate().equals("dissatisfied")) {//不满意
                                    propertyRepair.setVisitEvaluate("2");
                                } else if (repair.getVisitEvaluate().equals("highlyDissatisfied")) {//非常不满意
                                    propertyRepair.setVisitEvaluate("1");
                                }
                            }
                            if (repair.getVisitDate() != null) {
                                cal.setTime(repair.getVisitDate());
                                //cal.add(Calendar.HOUR_OF_DAY, 8);
                                propertyRepair.setVisitDate(cal.getTime());
                            }
                            if (!StringUtil.isEmpty(repair.getVisitType())) {
                                propertyRepair.setVisitType(repair.getVisitType());
                            }
                            propertyRepair.setModifyDate(new Date());
                            List list2 = new ArrayList();
                            propertyRepairCRMRepository.update(propertyRepair);
                            if(repair.getImageList()!=null && repair.getImageList().size()>0){
                                for(PropertyImageDTO imageDTO:repair.getImageList()){
                                    PropertyImageEntity propertyImage=propertyImageRepository.checkRepairImageByURL(repair.getRepairId(),imageDTO.getImageUrl(),imageDTO.getImageType());
                                    if(propertyImage!=null){
                                        //删除状态
                                        if("0".equals(imageDTO.getStatus())){
                                            propertyImage.setState("1");
                                            propertyImageRepository.updateImage(propertyImage);
                                        }
                                    }else{
                                        PropertyImageEntity newRepairImage=new PropertyImageEntity();
                                        newRepairImage.setImageId(IdGen.uuid());
                                        newRepairImage.setUploadDate(new Date());
                                        newRepairImage.setModifyDate(new Date());
                                        newRepairImage.setImagePath(imageDTO.getImageUrl());
                                        newRepairImage.setImageType(imageDTO.getImageType());
                                        newRepairImage.setImgFkId(repair.getRepairId());
                                        newRepairImage.setState("0");
                                        propertyImageRepository.updateImage(newRepairImage);
                                    }
                                }
                            }
                            //调用日志
                            InterFaceRepairLogsEntity repairLogsEntity=new InterFaceRepairLogsEntity();
                            repairLogsEntity.setId(IdGen.uuid());//日志id
                            repairLogsEntity.setCode("200");//返回码
                            repairLogsEntity.setCreaDate(new Date());//日志创建日期
                            repairLogsEntity.setInterfaceClass("BasicRepairInfoServiceImpl.classes");
                            repairLogsEntity.setInterfaceName("crm调用报修接口，推送报修单数据：更新报修信息！");
                            repairLogsEntity.setRepairId(repair.getRepairId());//报修单ID
                            repairLogsEntity.setRepairState(repair.getState());//保修单状态
                            repairLogsEntity.setCreaBy(repair.getUserName());//报修人
                            repairLogsEntity.setInfo("crm推送报修单数据：房间编码'" +repair.getRoomNum()+"' 报修内容：'"+repair.getContent()+"'");
                            interfaceLogRepository.createRepair(repairLogsEntity);
                        } else {
                            PropertyRepairCRMEntity propertyRepairCRMEntity = new PropertyRepairCRMEntity();
                            propertyRepairCRMEntity.setRepairId(repair.getRepairId());
                            propertyRepairCRMEntity.setAppId(repair.getRepairId() + "C");
                            propertyRepairCRMEntity.setDepartment(repair.getDepartment());
                            propertyRepairCRMEntity.setRoomId(repair.getRoomId());
                            propertyRepairCRMEntity.setRoomNum(repair.getRoomNum());
                            propertyRepairCRMEntity.setMemberId(repair.getMemberId());
                            propertyRepairCRMEntity.setUserName(repair.getUserName());
                            propertyRepairCRMEntity.setUserPhone(repair.getUserPhone());
                            propertyRepairCRMEntity.setUserAddress(repair.getUserAddress());
                            propertyRepairCRMEntity.setFailNum(0);
                            if (repair.getCreateDate() != null) {
                                cal.setTime(repair.getCreateDate());
                                //cal.add(Calendar.HOUR_OF_DAY, 8);
                                propertyRepairCRMEntity.setCreateDate(cal.getTime());
                            } else {
                                propertyRepairCRMEntity.setCreateDate(new Date());
                            }
                            if(repair.getPredictedDate() != null){//预计完成时间
                                cal.setTime(repair.getPredictedDate());
                                //cal.add(Calendar.HOUR_OF_DAY);
                                propertyRepairCRMEntity.setPredictedDate(cal.getTime());
                            }
//
//                            propertyRepairCRMEntity.setClassifyOne(repair.getClassifyOne());
//                            propertyRepairCRMEntity.setClassifyTwo(repair.getClassifyTwo());
//                            propertyRepairCRMEntity.setClassifyThree(repair.getClassifyThree());
                            if (!StringUtil.isEmpty(repair.getClassifyOne())) {
                                FirstClassificationEntity firstClass = firstClassificationCRMRepository.get(repair.getClassifyOne());
                                if(firstClass!=null){
                                    propertyRepairCRMEntity.setClassifyOneName(firstClass.getLabel());
                                }
                                propertyRepairCRMEntity.setClassifyOne(repair.getClassifyOne());
                            }
                            if (!StringUtil.isEmpty(repair.getClassifyTwo())) {
                                SecondClassificationEntity secondClass = secondClassificationCRMRepository.get(repair.getClassifyTwo());
                                if(secondClass!=null){
                                    propertyRepairCRMEntity.setClassifyTwoName(secondClass.getLabel());
                                }
                                propertyRepairCRMEntity.setClassifyTwo(repair.getClassifyTwo());
                            }
                            if (!StringUtil.isEmpty(repair.getClassifyThree())) {
                                ThirdClassificationEntity thirdClass = thirdClassificationCRMRepository.get(repair.getClassifyThree());
                                if(thirdClass!=null){
                                    propertyRepairCRMEntity.setClassifyThreeName(thirdClass.getLabel());
                                }
                                propertyRepairCRMEntity.setClassifyThree(repair.getClassifyThree());
                            }

                            propertyRepairCRMEntity.setMode(repair.getMode());
                            propertyRepairCRMEntity.setRepairDate(repair.getRepairDate());
                            propertyRepairCRMEntity.setRoomLocation(repair.getRoomLocation());
                            propertyRepairCRMEntity.setState(repair.getState());
                            propertyRepairCRMEntity.setDuty(repair.getDuty());
                            propertyRepairCRMEntity.setProblemLevel(repair.getProblemLevel());
                            propertyRepairCRMEntity.setRepairWay(repair.getRepairWay());
                            propertyRepairCRMEntity.setContent(repair.getContent());
                            if (repair.getTaskDate() != null) {
                                cal.setTime(repair.getTaskDate());
                                //cal.add(Calendar.HOUR_OF_DAY, 8);
                                propertyRepairCRMEntity.setTaskDate(cal.getTime());
                            }
                            propertyRepairCRMEntity.setDealWay(repair.getDealWay());
                            propertyRepairCRMEntity.setDealMode(repair.getDealMode());
                            propertyRepairCRMEntity.setDutyCompanyOne(repair.getDutyCompanyOne());
                            propertyRepairCRMEntity.setDutyCompanyTwo(repair.getDutyCompanyTwo());
                            propertyRepairCRMEntity.setDutyCompanyThree(repair.getDutyCompanyThree());
                            propertyRepairCRMEntity.setRepairCompany(repair.getRepairCompany());
                            if (repair.getDutyTaskDate() != null) {
                                cal.setTime(repair.getDutyTaskDate());
                                //cal.add(Calendar.HOUR_OF_DAY, 8);
                                propertyRepairCRMEntity.setDutyTaskDate(cal.getTime());
                            }
                            if (repair.getDutyReturnDate() != null) {
                                cal.setTime(repair.getDutyReturnDate());
                                //cal.add(Calendar.HOUR_OF_DAY, 8);
                                propertyRepairCRMEntity.setDutyReturnDate(cal.getTime());
                            }
                            if (!StringUtil.isEmpty(repair.getSendName())) {
                                propertyRepairCRMEntity.setSendName(repair.getSendName());
                            }
                            if (repair.getSendDate() != null) {
                                cal.setTime(repair.getSendDate());
                                //cal.add(Calendar.HOUR_OF_DAY);
                                propertyRepairCRMEntity.setSendDate(cal.getTime());
                            }
                            if (repair.getRepairManager() != null) {
                                propertyRepairCRMEntity.setRepairManager(repair.getRepairManager());
//                                UserPropertyStaffEntity user = propertyRepairCRMRepository.getUser(repair.getRepairManager());
                                UserInformationEntity user = propertyRepairCRMRepository.getUserByUserName(repair.getRepairManager());
                                if (user != null) {
                                    propertyRepairCRMEntity.setDealPeople(user.getStaffId());
                                }
                            }//UserPropertyStaffEntity
                            propertyRepairCRMEntity.setDealPhone(repair.getDealPhone());
                            propertyRepairCRMEntity.setDealPeopleTwo(repair.getDealPeopleTwo());
                            propertyRepairCRMEntity.setDealPhoneTwo(repair.getDealPhoneTwo());
                            if (repair.getFirstVisitDate() != null) {
                                cal.setTime(repair.getFirstVisitDate());
                                //cal.add(Calendar.HOUR_OF_DAY, 8);
                                propertyRepairCRMEntity.setFirstVisitDate(cal.getTime());
                            }
                            if (repair.getBackupVisitDate() != null) {
                                cal.setTime(repair.getBackupVisitDate());
                                //cal.add(Calendar.HOUR_OF_DAY, 8);
                                propertyRepairCRMEntity.setBackupVisitDate(cal.getTime());
                            }
                            propertyRepairCRMEntity.setDealState(repair.getDealState());
                            propertyRepairCRMEntity.setDealResult(repair.getDealResult());
                            if (repair.getDealCompleteDate() != null) {
                                cal.setTime(repair.getDealCompleteDate());
                                //cal.add(Calendar.HOUR_OF_DAY, 8);
                                propertyRepairCRMEntity.setDealCompleteDate(cal.getTime());
                            }
                            propertyRepairCRMEntity.setVisitOpinion(repair.getVisitOpinion());
                            if (!StringUtil.isEmpty(repair.getVisitEvaluate())) {
                                if (repair.getVisitEvaluate().equals("highlySatisfied")) {//非常满意
                                    propertyRepairCRMEntity.setVisitEvaluate("5");
                                } else if (repair.getVisitEvaluate().equals("satisfied")) {//满意
                                    propertyRepairCRMEntity.setVisitEvaluate("4");
                                } else if (repair.getVisitEvaluate().equals("commonSatisfied")) {//一般
                                    propertyRepairCRMEntity.setVisitEvaluate("3");
                                } else if (repair.getVisitEvaluate().equals("dissatisfied")) {//不满意
                                    propertyRepairCRMEntity.setVisitEvaluate("2");
                                } else if (repair.getVisitEvaluate().equals("highlyDissatisfied")) {//非常不满意
                                    propertyRepairCRMEntity.setVisitEvaluate("1");
                                }
                            }
                            if (repair.getVisitDate() != null) {
                                cal.setTime(repair.getVisitDate());
                                //cal.add(Calendar.HOUR_OF_DAY, 8);
                                propertyRepairCRMEntity.setVisitDate(cal.getTime());
                            }
                            propertyRepairCRMEntity.setVisitType(repair.getVisitType());
                            propertyRepairCRMRepository.create(propertyRepairCRMEntity);

                            if(repair.getImageList()!=null && repair.getImageList().size()>0){
                                for(PropertyImageDTO imageDTO:repair.getImageList()){
                                    PropertyImageEntity newRepairImage=new PropertyImageEntity();
                                    newRepairImage.setImageId(IdGen.uuid());
                                    newRepairImage.setUploadDate(new Date());
                                    newRepairImage.setModifyDate(new Date());
                                    newRepairImage.setImagePath(imageDTO.getImageUrl());
                                    newRepairImage.setImageType(imageDTO.getImageType());
                                    newRepairImage.setImgFkId(propertyRepairCRMEntity.getRepairId());
                                    newRepairImage.setState("0");
                                    propertyImageRepository.updateImage(newRepairImage);
                                }
                            }

                            //调用日志
                            InterFaceRepairLogsEntity repairLogsEntity=new InterFaceRepairLogsEntity();
                            repairLogsEntity.setId(IdGen.uuid());//日志id
                            repairLogsEntity.setCode("200");//返回码
                            repairLogsEntity.setCreaDate(new Date());//日志创建日期
                            repairLogsEntity.setInterfaceClass("BasicRepairInfoServiceImpl.classes");
                            repairLogsEntity.setInterfaceName("crm调用报修接口，推送报修单数据：创建报修信息！");
                            repairLogsEntity.setRepairId(repair.getRepairId());//报修单ID
                            repairLogsEntity.setRepairState(repair.getState());//保修单状态
                            repairLogsEntity.setCreaBy(repair.getUserName());//报修人
                            repairLogsEntity.setInfo("crm推送报修单数据：房间编码'" +repair.getRoomNum()+"' 报修内容：'"+repair.getContent()+"'");
                            interfaceLogRepository.createRepair(repairLogsEntity);
                        }
                        PropertyRepairEntity propertyRepairEntity = propertyRepairRepository.getRepairDetail(repair.getRepairId());
                        if (propertyRepairEntity != null) {
                            if (!StringUtil.isEmpty(repair.getDepartment())) {
                                propertyRepairEntity.setDepartment(repair.getDepartment());
                            }
                            if (!StringUtil.isEmpty(repair.getState())) {
                                if (repair.getState().equals("create")) {
                                    propertyRepairEntity.setState("已创建");
                                    if (!StringUtil.isEmpty(repair.getMemberId())) {
                                        UserInfoEntity user = userInfoRepository.getById(repair.getMemberId());
                                        if (user != null) {
                                            propertyRepairEntity.setCreateBy(user.getUserId());
                                        }
                                    }
                                    if (!StringUtil.isEmpty(repair.getUserName())) {
                                        propertyRepairEntity.setUserName(repair.getUserName());
                                    }
                                    if (!StringUtil.isEmpty(repair.getUserPhone())) {
                                        propertyRepairEntity.setUserPhone(repair.getUserPhone());
                                    }
                                    if (!StringUtil.isEmpty(repair.getUserAddress())) {
                                        propertyRepairEntity.setUserAddress(repair.getUserAddress());
                                    }
                                    if (!StringUtil.isEmpty(repair.getContent())) {
                                        propertyRepairEntity.setContent(repair.getContent());
                                    }
                                    if (!StringUtil.isEmpty(repair.getRoomId())) {
                                        propertyRepairEntity.setRoomId(repair.getRoomId());
                                    }
                                    if (repair.getCreateDate() != null) {
                                        cal.setTime(repair.getCreateDate());
                                        //cal.add(Calendar.HOUR_OF_DAY, 8);
                                        propertyRepairEntity.setCreateDate(cal.getTime());//创建时间
                                    }
                                    propertyRepairEntity.setTypes("0");//0代表未完成；1代表已完成
                                    propertyRepairEntity.setMemo("报修");
                                    propertyRepairEntity.setTaskStatus("0");//0为用户提交报修(业主)
                                    PropertyRepairTaskEntity propertyRepairTaskEntity = new PropertyRepairTaskEntity();
                                    propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                                    propertyRepairTaskEntity.setRepairId(propertyRepairEntity.getRepairId());//报修单id
                                    propertyRepairTaskEntity.setTaskNode("提交报修");//任务节点
                                    propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                                    propertyRepairTaskEntity.setTaskName("已创建");
                                    propertyRepairTaskEntity.setTaskStatus("0");//0为用户提交报修(业主)
                                    propertyRepairTaskEntity.setTaskContent("您的报修信息已创建。");//任务内容
                                    propertyRepairTaskEntity.setReadStatus("0");//0为未读
                                    propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                                } else if (propertyRepair.getState().equals("accepted")) {
                                    propertyRepairEntity.setState("已受理");
                                    if (!StringUtil.isEmpty(repair.getMemberId())) {
                                        UserInfoEntity user = userInfoRepository.getById(repair.getMemberId());
                                        if (user != null) {
                                            propertyRepairEntity.setCreateBy(user.getUserId());
                                        }
                                    }
                                    if (!StringUtil.isEmpty(repair.getUserName())) {
                                        propertyRepairEntity.setUserName(repair.getUserName());
                                    }
                                    if (!StringUtil.isEmpty(repair.getUserPhone())) {
                                        propertyRepairEntity.setUserPhone(repair.getUserPhone());
                                    }
                                    if (!StringUtil.isEmpty(repair.getUserAddress())) {
                                        propertyRepairEntity.setUserAddress(repair.getUserAddress());
                                    }
                                    if (!StringUtil.isEmpty(repair.getContent())) {
                                        propertyRepairEntity.setContent(repair.getContent());
                                    }
                                    if (!StringUtil.isEmpty(repair.getRoomId())) {
                                        propertyRepairEntity.setRoomId(repair.getRoomId());
                                    }
                                    if (repair.getCreateDate() != null) {
                                        cal.setTime(repair.getCreateDate());
                                        //cal.add(Calendar.HOUR_OF_DAY, 8);
                                        propertyRepairEntity.setCreateDate(cal.getTime());//创建时间
                                    }
                                    propertyRepairEntity.setTypes("0");//0代表未完成；1代表已完成
                                    propertyRepairEntity.setMemo("报修");
                                    propertyRepairEntity.setTaskStatus("4");
                                    PropertyRepairTaskEntity propertyRepairTaskEntity = new PropertyRepairTaskEntity();
                                    propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                                    propertyRepairTaskEntity.setRepairId(propertyRepairEntity.getRepairId());//报修单id
                                    propertyRepairTaskEntity.setTaskNode("受理成功");//任务节点
                                    propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                                    propertyRepairTaskEntity.setTaskName("已受理");
                                    propertyRepairTaskEntity.setTaskStatus("6");//6为已受理
                                    propertyRepairTaskEntity.setTaskContent("您的报修信息已受理。");//任务内容
                                    propertyRepairTaskEntity.setReadStatus("0");
                                    propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                                } else if (propertyRepair.getState().equals("processing")) {
                                    propertyRepairEntity.setState("处理中");
                                    if (!StringUtil.isEmpty(repair.getMemberId())) {
                                        UserInfoEntity user = userInfoRepository.getById(repair.getMemberId());
                                        if (user != null) {
                                            propertyRepairEntity.setCreateBy(user.getUserId());
                                        }
                                    }
                                    if (!StringUtil.isEmpty(repair.getUserName())) {
                                        propertyRepairEntity.setUserName(repair.getUserName());
                                    }
                                    if (!StringUtil.isEmpty(repair.getUserPhone())) {
                                        propertyRepairEntity.setUserPhone(repair.getUserPhone());
                                    }
                                    if (!StringUtil.isEmpty(repair.getUserAddress())) {
                                        propertyRepairEntity.setUserAddress(repair.getUserAddress());
                                    }
                                    if (!StringUtil.isEmpty(repair.getContent())) {
                                        propertyRepairEntity.setContent(repair.getContent());
                                    }
                                    if (!StringUtil.isEmpty(repair.getRoomId())) {
                                        propertyRepairEntity.setRoomId(repair.getRoomId());
                                    }
                                    if (repair.getCreateDate() != null) {
                                        cal.setTime(repair.getCreateDate());
                                        //cal.add(Calendar.HOUR_OF_DAY, 8);
                                        propertyRepairEntity.setCreateDate(cal.getTime());//创建时间
                                    }
                                    propertyRepairEntity.setTypes("0");//0代表未完成；1代表已完成
                                    propertyRepairEntity.setMemo("报修");
                                    propertyRepairEntity.setTaskStatus("7");
                                    PropertyRepairTaskEntity propertyRepairTaskEntity = new PropertyRepairTaskEntity();
                                    propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                                    propertyRepairTaskEntity.setRepairId(propertyRepairEntity.getRepairId());//报修单id
                                    propertyRepairTaskEntity.setTaskNode("抢单成功");//任务节点
                                    propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                                    propertyRepairTaskEntity.setTaskName("处理中");
                                    propertyRepairTaskEntity.setTaskStatus("4");
                                    propertyRepairTaskEntity.setTaskContent("您的报修信息正在处理中。");//任务内容
                                    propertyRepairTaskEntity.setReadStatus("0");
                                    propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                                } else if (propertyRepair.getState().equals("completed")) {
                                    propertyRepairEntity.setState("已完成");
                                    propertyRepairEntity.setCompleteDate(DateUtils.getDate());
                                    UserInfoEntity user = null;
                                    if (!StringUtil.isEmpty(repair.getMemberId())) {
                                        user = userInfoRepository.getById(repair.getMemberId());
                                        if (user != null) {
                                            propertyRepairEntity.setCreateBy(user.getUserId());
                                        }
                                    }
                                    if (!StringUtil.isEmpty(repair.getUserName())) {
                                        propertyRepairEntity.setUserName(repair.getUserName());
                                    }
                                    if (!StringUtil.isEmpty(repair.getUserPhone())) {
                                        propertyRepairEntity.setUserPhone(repair.getUserPhone());
                                    }
                                    if (!StringUtil.isEmpty(repair.getUserAddress())) {
                                        propertyRepairEntity.setUserAddress(repair.getUserAddress());
                                    }
                                    if (!StringUtil.isEmpty(repair.getContent())) {
                                        propertyRepairEntity.setContent(repair.getContent());
                                    }
                                    if (!StringUtil.isEmpty(repair.getRoomId())) {
                                        propertyRepairEntity.setRoomId(repair.getRoomId());
                                    }
                                    if (repair.getCreateDate() != null) {
                                        cal.setTime(repair.getCreateDate());
                                        //cal.add(Calendar.HOUR_OF_DAY, 8);
                                        propertyRepairEntity.setCreateDate(cal.getTime());//创建时间
                                    }
                                    propertyRepairEntity.setTypes("1");//0代表未完成；1代表已完成
                                    propertyRepairEntity.setMemo("报修");
                                    propertyRepairEntity.setTaskStatus("10");
                                    PropertyRepairTaskEntity propertyRepairTaskEntity = new PropertyRepairTaskEntity();
                                    propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                                    propertyRepairTaskEntity.setRepairId(propertyRepairEntity.getRepairId());//报修单id
                                    propertyRepairTaskEntity.setTaskNode("维修完成");//任务节点
                                    propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                                    propertyRepairTaskEntity.setTaskName("已完成");
                                    propertyRepairTaskEntity.setTaskStatus("10");//10为已完成
                                    propertyRepairTaskEntity.setTaskContent("您的报修信息已完成。");//任务内容
                                    propertyRepairTaskEntity.setReadStatus("0");
                                    propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                                    //执行模板消息通知请求
                                    List<HouseUserCRMEntity> userCRMEntityList = houseInfoRepository.getHouseUserCRMListByRoomId(propertyRepair.getRoomId());
                                    if (null != userCRMEntityList && userCRMEntityList.size() > 0){
                                        String address = userCRMEntityList.get(0).getHouseAddress();
                                        UserLoginBookEntity userLoginBookEntity = null;
                                        for (HouseUserCRMEntity userCRMEntity : userCRMEntityList){
                                            userLoginBookEntity = userInfoRepository.getUserLoginBookByMemberId(userCRMEntity.getMemberId(), "WC");
                                            if (userLoginBookEntity != null && userLoginBookEntity.getUnionId() != null){
                                                WxMessage wxMessage = new WxMessage();
                                                wxMessage.setAppID(AppConfig.AppID);//AppId
                                                wxMessage.setAppSecret(AppConfig.AppSecret);//AppSecret
                                                wxMessage.setOpenId(userLoginBookEntity.getUnionId());//目标人openId
//                                                wxMessage.setTemplate_id("0bvK_FgizyD0iwsuW-qzWOjCCAH-zt7qyojp6xAlVXQ");//模板Id(测试环境)
                                                wxMessage.setTemplate_id("a9fkptScFnVPWkQGnBjbr_CG5VwnVp1gG8pBltKOy9Y");//模板Id(正式环境)
//                                                wxMessage.setTurnUrl("http://ast.chinajinmao.cn/wechat/indexTest.html?goTo=propertyRepair/weChatRepairInfo/"+propertyRepairEntity.getRepairId()+"&cityid=BJ");//消息跳转网页(测试环境)
                                                wxMessage.setTurnUrl("http://as.chinajinmao.cn/wechat/index3.html?goTo=evaluate?"+propertyRepairEntity.getRepairId()+"&cityid=BJ");//消息跳转网页(正式环境)
//                                            wxMessage.setTurnUrl("https://as.chinajinmao.cn/wechat/index3.html?goTo=propertyRepair/weChatRepairInfo/"+propertyRepairEntity.getRepairId()+"&cityid=BJ");//消息跳转网页(正式环境)
                                                wxMessage.setFirst("尊敬的"+userCRMEntity.getRealName()+"先生/女士，您的报修问题，已经为您处理完毕");
                                                wxMessage.setKeyword1(address);
                                                wxMessage.setKeyword2(propertyRepair.getContent());
//                                                UserPropertyStaffEntity userStaffEntity = propertyRepairCRMRepository.getUser(repair.getRepairManager());
//                                                if (null != userStaffEntity){
//                                                    wxMessage.setKeyword3(userStaffEntity.getStaffName());
//                                                }else{
//                                                    wxMessage.setKeyword3("无");
//                                                }
//                                                wxMessage.setKeyword4(DateUtils.format(propertyRepair.getModifyDate(),"yyyy/MM/dd"));
//                                                wxMessage.setRemark("请您点开“详情”对我们的维修工作进行评价，以便我们提供更加优质的服务，评价完成还有【金茂币】赠送哦~感谢您的配合，祝您生活愉快！");
                                                wxMessage.setRemark("【开始评价】");
                                                WxMessagePush.pushMessage(wxMessage);
                                            }
                                        }
                                    }
                                    /*
                                    if (user != null) {
                                        UserLoginBookEntity userLoginBookEntity = userInfoRepository.getUserLoginBookByUserId(user.getUserId(), "WC");
                                        if (null != userLoginBookEntity){
                                            String userName = "";
                                            String address = "";
                                            List<HouseUserCRMEntity> userCRMEntityList = houseInfoRepository.getHouseUserCRMListByRoomId(propertyRepair.getRoomId());
                                            if (null != userCRMEntityList && userCRMEntityList.size() > 0){
                                                address = userCRMEntityList.get(0).getHouseAddress();
                                                for (HouseUserCRMEntity userCRMEntity : userCRMEntityList){
                                                    userName += (userCRMEntity.getRealName() + ",");
                                                }
                                                userName = userName.substring(0,userName.length()-1);
                                            }else{
                                                userName = propertyRepair.getUserName();
                                                address = propertyRepair.getUserAddress();
                                            }
                                            WxMessage wxMessage = new WxMessage();
                                            wxMessage.setAppID(AppConfig.AppID);//AppId
                                            wxMessage.setAppSecret(AppConfig.AppSecret);//AppSecret
                                            wxMessage.setOpenId(userLoginBookEntity.getUnionId());//目标人openId
                                            wxMessage.setTemplate_id("0bvK_FgizyD0iwsuW-qzWOjCCAH-zt7qyojp6xAlVXQ");//模板Id
//                                            wxMessage.setTemplate_id("WcGOGxuaIrc1g44Lxr72y56IFIIMKtk1YHdEH0ZtjBE");//模板Id
                                            wxMessage.setTurnUrl("http://ast.chinajinmao.cn/wechat/indexTest.html?goTo=propertyRepair/weChatRepairInfo/"+propertyRepairEntity.getRepairId()+"&cityid=BJ");//消息跳转网页(测试环境)
//                                            wxMessage.setTurnUrl("https://as.chinajinmao.cn/wechat/index3.html?goTo=propertyRepair/weChatRepairInfo/"+propertyRepairEntity.getRepairId()+"&cityid=BJ");//消息跳转网页(正式环境)
                                            wxMessage.setFirst("尊敬的"+userName+"先生/女士，您的报修问题，已经为您处理完毕");
                                            wxMessage.setKeyword1(address);
                                            wxMessage.setKeyword2(propertyRepair.getContent());
                                            UserPropertyStaffEntity userStaffEntity = propertyRepairCRMRepository.getUser(repair.getRepairManager());
                                            if (null != userStaffEntity){
                                                wxMessage.setKeyword3(userStaffEntity.getStaffName());
                                            }else{
                                                wxMessage.setKeyword3("无");
                                            }
                                            wxMessage.setKeyword4(DateUtils.format(propertyRepair.getModifyDate(),"yyyy/MM/dd"));
                                            wxMessage.setRemark("请您点开“详情”对我们的维修工作进行评价，以便我们提供更加优质的服务，评价完成还有【金茂币】赠送哦~感谢您的配合，祝您生活愉快！");
                                            WxMessagePush.pushMessage(wxMessage);
                                        }
                                    }
                                    */
                                } else if (propertyRepair.getState().equals("closed")) {
                                    propertyRepairEntity.setState("已作废");
                                    propertyRepairEntity.setTypes("3");//3代表已删除(逻辑)
                                    if (!StringUtil.isEmpty(repair.getMemberId())) {
                                        UserInfoEntity user = userInfoRepository.getById(repair.getMemberId());
                                        if (user != null) {
                                            propertyRepairEntity.setCreateBy(user.getUserId());
                                        }
                                    }
                                    if (!StringUtil.isEmpty(repair.getUserName())) {
                                        propertyRepairEntity.setUserName(repair.getUserName());
                                    }
                                    if (!StringUtil.isEmpty(repair.getUserPhone())) {
                                        propertyRepairEntity.setUserPhone(repair.getUserPhone());
                                    }
                                    if (!StringUtil.isEmpty(repair.getUserAddress())) {
                                        propertyRepairEntity.setUserAddress(repair.getUserAddress());
                                    }
                                    if (!StringUtil.isEmpty(repair.getContent())) {
                                        propertyRepairEntity.setContent(repair.getContent());
                                    }
                                    if (!StringUtil.isEmpty(repair.getRoomId())) {
                                        propertyRepairEntity.setRoomId(repair.getRoomId());
                                    }
                                    if (repair.getCreateDate() != null) {
                                        cal.setTime(repair.getCreateDate());
                                        //cal.add(Calendar.HOUR_OF_DAY, 8);
                                        propertyRepairEntity.setCreateDate(cal.getTime());//创建时间
                                    }
                                    propertyRepairEntity.setMemo("报修");
                                    propertyRepairEntity.setTaskStatus("14");
                                    PropertyRepairTaskEntity propertyRepairTaskEntity = new PropertyRepairTaskEntity();
                                    propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                                    propertyRepairTaskEntity.setRepairId(propertyRepairEntity.getRepairId());//报修单id
                                    propertyRepairTaskEntity.setTaskNode("关闭成功");//任务节点
                                    propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                                    propertyRepairTaskEntity.setTaskName("已作废");
                                    propertyRepairTaskEntity.setTaskStatus("14");//14为终止维修
                                    propertyRepairTaskEntity.setTaskContent("您的报修信息已作废。");//任务内容
                                    propertyRepairTaskEntity.setReadStatus("0");
                                    propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                                } else if (propertyRepair.getState().equals("returnVisit")) {
                                    propertyRepairEntity.setState("已评价");
                                    if (!StringUtil.isEmpty(repair.getMemberId())) {
                                        UserInfoEntity user = userInfoRepository.getById(repair.getMemberId());
                                        if (user != null) {
                                            propertyRepairEntity.setCreateBy(user.getUserId());
                                        }
                                    }
                                    if (!StringUtil.isEmpty(repair.getUserName())) {
                                        propertyRepairEntity.setUserName(repair.getUserName());
                                    }
                                    if (!StringUtil.isEmpty(repair.getUserPhone())) {
                                        propertyRepairEntity.setUserPhone(repair.getUserPhone());
                                    }
                                    if (!StringUtil.isEmpty(repair.getUserAddress())) {
                                        propertyRepairEntity.setUserAddress(repair.getUserAddress());
                                    }
                                    if (!StringUtil.isEmpty(repair.getContent())) {
                                        propertyRepairEntity.setContent(repair.getContent());
                                    }
                                    if (!StringUtil.isEmpty(repair.getRoomId())) {
                                        propertyRepairEntity.setRoomId(repair.getRoomId());
                                    }
                                    if (repair.getCreateDate() != null) {
                                        cal.setTime(repair.getCreateDate());
                                        //cal.add(Calendar.HOUR_OF_DAY, 8);
                                        propertyRepairEntity.setCreateDate(cal.getTime());//创建时间
                                    }
                                    propertyRepairEntity.setTypes("1");//0代表未完成；1代表已完成
                                    propertyRepairEntity.setMemo("报修");
                                    propertyRepairEntity.setTaskStatus("14");//已评价
                                    PropertyRepairTaskEntity propertyRepairTaskEntity = new PropertyRepairTaskEntity();
                                    propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                                    propertyRepairTaskEntity.setRepairId(propertyRepairEntity.getRepairId());//报修单id
                                    propertyRepairTaskEntity.setTaskNode("评价成功");//任务节点
                                    propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                                    propertyRepairTaskEntity.setTaskName("已评价");
                                    propertyRepairTaskEntity.setTaskStatus("14");
                                    propertyRepairTaskEntity.setTaskContent("已与业主沟通，维修工作已完成。");//任务内容
                                    propertyRepairTaskEntity.setReadStatus("0");
                                    propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                                }
                            }
                            if (!StringUtil.isEmpty(repair.getDepartment())) {
                                propertyRepairEntity.setDepartment(repair.getDepartment());
                            }
                            propertyRepairEntity.setModifyDate(new Date());
                            propertyRepairRepository.updateRepair(propertyRepairEntity);
                        } else {
                            PropertyRepairEntity propertyRepairs = new PropertyRepairEntity();
                            if (!StringUtil.isEmpty(repair.getState())) {
                                propertyRepairs.setRepairId(repair.getRepairId());//报修单id
                                if (!StringUtil.isEmpty(repair.getMemberId())) {
                                    UserInfoEntity user = userInfoRepository.getById(repair.getMemberId());
                                    if (user != null) {
                                        propertyRepairs.setCreateBy(user.getUserId());
                                    }
                                }
                                HouseInfoEntity house = houseInfoRepository.getHouseByRoomId(repair.getRoomId());
                                if (house != null) {
                                    propertyRepairs.setUserAddress(house.getAddress());//业主地址
                                    propertyRepairs.setRegionId(house.getProjectId());//项目id
                                    propertyRepairs.setRegionName(house.getProjectName());//项目名称
                                }
                                if (!StringUtil.isEmpty(repair.getUserName())) {
                                    propertyRepairs.setUserName(repair.getUserName());//业主姓名
                                }
                                if (!StringUtil.isEmpty(repair.getUserPhone())) {
                                    propertyRepairs.setUserPhone(repair.getUserPhone());//业主电话
                                }
                                if (!StringUtil.isEmpty(repair.getUserAddress())) {
                                    propertyRepairs.setUserAddress(repair.getUserAddress());
                                }
                                if (!StringUtil.isEmpty(repair.getDepartment())) {
                                    propertyRepairs.setDepartment(repair.getDepartment());
                                }
                                if (!StringUtil.isEmpty(repair.getContent())) {
                                    propertyRepairs.setContent(repair.getContent());
                                }
                                if (!StringUtil.isEmpty(repair.getRoomId())) {
                                    propertyRepairs.setRoomId(repair.getRoomId());
                                }
                                if (repair.getCreateDate() != null) {
                                    cal.setTime(repair.getCreateDate());
                                    //cal.add(Calendar.HOUR_OF_DAY, 8);
                                    propertyRepairs.setCreateDate(cal.getTime());//创建时间
                                }
                                propertyRepairs.setModifyDate(new Date());
                                propertyRepairs.setTypes("0");//0代表未完成；1代表已完成
                                if (!StringUtil.isEmpty(repair.getRepairWay())) {
                                    if (repair.getRepairWay().equals("wechat")) {//微信
                                        propertyRepairs.setRepairWay("微信");
                                    } else if (repair.getRepairWay().equals("App")) {
                                        propertyRepairs.setRepairWay("APP");//app
                                    } else if (repair.getRepairWay().equals("website")) {
                                        propertyRepairs.setRepairWay("网页");//网页
                                    } else if (repair.getRepairWay().equals("callCenter")) {
                                        propertyRepairs.setRepairWay("呼叫中心");//呼叫中心
                                    } else if (repair.getRepairWay().equals("receptionist")) {
                                        propertyRepairs.setRepairWay("项目前台");//项目前台
                                    } else if (repair.getRepairWay().equals("propertyDocuments")) {
                                        propertyRepairs.setRepairWay("物业单据");//物业单据
                                    } else if (repair.getRepairWay().equals("propertywechat")) {
                                        propertyRepairs.setRepairWay("物业微信");//物业微信
                                    }
                                }
                                propertyRepairs.setMemo("报修");
                                propertyRepairs.setTaskStatus("0");//0为用户提交报修(业主)
                                if (repair.getState().equals("create")) {
                                    propertyRepairs.setState("已创建");
                                    PropertyRepairTaskEntity propertyRepairTaskEntity = new PropertyRepairTaskEntity();
                                    propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                                    propertyRepairTaskEntity.setRepairId(propertyRepairs.getRepairId());//报修单id
                                    propertyRepairTaskEntity.setTaskNode("提交报修");//任务节点
                                    propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                                    propertyRepairTaskEntity.setTaskName("已创建");
                                    propertyRepairTaskEntity.setTaskStatus("0");//0为用户提交报修(业主)
                                    propertyRepairTaskEntity.setTaskContent("您的投诉信息已成功提交。");//任务内容
                                    propertyRepairTaskEntity.setReadStatus("0");//0为未读
                                    propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                                } else if (repair.getState().equals("accepted")) {
                                    propertyRepairs.setState("已受理");
                                    PropertyRepairTaskEntity propertyRepairTaskEntity = new PropertyRepairTaskEntity();
                                    propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                                    propertyRepairTaskEntity.setRepairId(propertyRepairs.getRepairId());//报修单id
                                    propertyRepairTaskEntity.setTaskNode("提交报修");//任务节点
                                    propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                                    propertyRepairTaskEntity.setTaskName("已创建");
                                    propertyRepairTaskEntity.setTaskStatus("6");
                                    propertyRepairTaskEntity.setTaskContent("您的报修信息已受理。");//任务内容
                                    propertyRepairTaskEntity.setReadStatus("0");//0为未读
                                    propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                                } else if (repair.getState().equals("processing")) {
                                    propertyRepairs.setState("处理中");
                                    PropertyRepairTaskEntity propertyRepairTaskEntity = new PropertyRepairTaskEntity();
                                    propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                                    propertyRepairTaskEntity.setRepairId(repair.getRepairId());//报修单id
                                    propertyRepairTaskEntity.setTaskNode("抢单成功");//任务节点
                                    propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                                    propertyRepairTaskEntity.setTaskName("处理中");
                                    propertyRepairTaskEntity.setTaskStatus("4");
                                    propertyRepairTaskEntity.setTaskContent("您的报修信息正在处理中。");//任务内容
                                    propertyRepairTaskEntity.setReadStatus("0");
                                    propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                                } else if (repair.getState().equals("completed")) {
                                    propertyRepairs.setState("已完成");
                                    PropertyRepairTaskEntity propertyRepairTaskEntity = new PropertyRepairTaskEntity();
                                    propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                                    propertyRepairTaskEntity.setRepairId(repair.getRepairId());//报修单id
                                    propertyRepairTaskEntity.setTaskNode("维修完成");//任务节点
                                    propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                                    propertyRepairTaskEntity.setTaskName("已完成");
                                    propertyRepairTaskEntity.setTaskStatus("10");//10为已完成
                                    propertyRepairTaskEntity.setTaskContent("您的报修信息已完成。");//任务内容
                                    propertyRepairTaskEntity.setReadStatus("0");
                                    propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                                } else if (repair.getState().equals("closed")) {
                                    propertyRepairs.setState("已作废");
                                    PropertyRepairTaskEntity propertyRepairTaskEntity = new PropertyRepairTaskEntity();
                                    propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                                    propertyRepairTaskEntity.setRepairId(repair.getRepairId());//报修单id
                                    propertyRepairTaskEntity.setTaskNode("关闭成功");//任务节点
                                    propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                                    propertyRepairTaskEntity.setTaskName("已作废");
                                    propertyRepairTaskEntity.setTaskStatus("9");//9为已关闭
                                    propertyRepairTaskEntity.setTaskContent("您的报修信息已作废。");//任务内容
                                    propertyRepairTaskEntity.setReadStatus("0");
                                    propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                                } else if (repair.getState().equals("returnVisit")) {
                                    propertyRepairs.setState("已评价");
                                    PropertyRepairTaskEntity propertyRepairTaskEntity = new PropertyRepairTaskEntity();
                                    propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
                                    propertyRepairTaskEntity.setRepairId(repair.getRepairId());//报修单id
                                    propertyRepairTaskEntity.setTaskNode("评价成功");//任务节点
                                    propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
                                    propertyRepairTaskEntity.setTaskName("已评价");
                                    propertyRepairTaskEntity.setTaskStatus("14");
                                    propertyRepairTaskEntity.setTaskContent("已与业主沟通，维修工作已完成。");//任务内容
                                    propertyRepairTaskEntity.setReadStatus("0");
                                    propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
                                }
                            }
                            propertyRepairRepository.saveRepair(propertyRepairs);
                        }
                        if (!StringUtil.isEmpty(repair.getDepartment()) && !StringUtil.isEmpty(repair.getRoomNum())) {
                            List<Object> listzu = propertyRepairCRMRepository.getStafidForzu(repair.getDepartment());
                            List<Object> listnum = propertyRepairCRMRepository.getStafidForNum(repair.getRoomNum());
                            if (listnum != null && listzu != null) {
                                listzu.retainAll(listnum);
                                if (listzu != null) {
                                    for (Object obj : listzu) {//listzu  拥有查看该保修单权限的员工id
                                        List<MessageTokenEntity> MessageTokenlist = propertyRepairCRMRepository.getMessageToken(obj.toString());
                                        if (MessageTokenlist != null) {
                                            for (MessageTokenEntity MessageToken : MessageTokenlist) { //2 0IOS 1 AD
                                                Random random = new Random();
                                                String result = "";
                                                for (int i = 0; i < 6; i++) {
                                                    result += random.nextInt(10);
                                                }
                                                String messid = repair.getRepairId() + "/" + DateUtils.format(new Date(), "yyyyMMddHHmmss" + result);
                                                MessageTargetEntity messageTargetEntity = new MessageTargetEntity();
                                                messageTargetEntity.setUserId(MessageToken.getUserId());
                                                messageTargetEntity.setMessageTargetId(messid);
                                                messageTargetEntity.setMessageDetailId(messid);
                                                messageTargetEntity.setMessageType("1");
                                                messageTargetEntity.setTargetCreateTime(new Date());
                                                messageTargetEntity.setMessagePushStatus("0");
                                                messageTargetEntity.setMessageReadStatus("0");
                                                messageTargetEntity.setMessageDeleteStatue("1");
                                                messageTargetEntity.setMessageTokenNum(MessageToken.getMessageTokenNum());
                                                messageTargetEntity.setUserType(String.valueOf(MessageToken.getMobileType()));
                                                propertyRepairCRMRepository.savemessageTarget(messageTargetEntity);
                                                MessageDetailEntity messageDetailEntity = new MessageDetailEntity();
                                                messageDetailEntity.setMessageDetailId(messid);
                                                messageDetailEntity.setMessageTitle("报修单");
                                                if (repair.getState().equals("completed")) {
                                                    messageDetailEntity.setMessageContent("您的报修信息已完成!");
                                                } else {
                                                    messageDetailEntity.setMessageContent("您有新的报修信息");
                                                }
                                                messageDetailEntity.setMessageCreateTime(new Date());
                                                messageDetailEntity.setMessageType("1");
                                                propertyRepairCRMRepository.saveMessageDetail(messageDetailEntity);

                                                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                                                interfaceLogEntity.setId(IdGen.uuid());
                                                interfaceLogEntity.setInterfaceName("调用报修接口:创建推送消息信息");
                                                interfaceLogEntity.setCode("200");
                                                interfaceLogEntity.setEntityName("MessageTargetEntity + MessageDetailEntity");
                                                interfaceLogEntity.setErrorDate(new Date());
                                                interfaceLogRepository.create(interfaceLogEntity);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                try{
                    if (repairInfo != null && repairInfo.getRectifyList().size() > 0) {
                        for (RectifyDTO rectify : repairInfo.getRectifyList()) {
                            errorRetifyId=rectify.getRectifyId();
                            PropertyRectifyCRMEntity propertyRectify = propertyRectifyCRMRepository.getById(rectify.getRectifyId());
                            //如果有数据，则更新;如果无，则创建
                            if (propertyRectify != null) {
                                if (!StringUtil.isEmpty(rectify.getDepartment())) {
                                    propertyRectify.setDepartment(rectify.getDepartment());
                                }
                                if (!StringUtil.isEmpty(rectify.getCreatorName())) {
                                    propertyRectify.setCreateByName(rectify.getCreatorName());
                                }
                                if (!StringUtil.isEmpty(rectify.getHandlerName())) {
                                    propertyRectify.setUpdateUserName(rectify.getHandlerName());
                                }
                                propertyRectify.setDepartment("2");
                                if (!StringUtil.isEmpty(rectify.getRoomId())) {
                                    propertyRectify.setRoomId(rectify.getRoomId());
                                }
                                if (!StringUtil.isEmpty(rectify.getRoomNum())) {
                                    propertyRectify.setRoomNum(rectify.getRoomNum());
                                    String project = propertyRectifyCRMRepository.getprojectNumForRoom(rectify.getRoomNum());
                                    if (project != null && !"".equals(project)) {
                                        propertyRectify.setProjectNum(project);
                                    }
                                }
                                if (!StringUtil.isEmpty(rectify.getPlanNum())) {
                                    propertyRectify.setPlanNum(rectify.getPlanNum());
                                    String plan = propertyRectifyCRMRepository.getplanTypeForPlan(rectify.getPlanNum());
                                    if (plan != null && !"".equals(plan)) {
                                        propertyRectify.setPlanType(plan);
                                    }
                                }
                                if (rectify.getAcceptanceDate() != null) {
                                    cal.setTime(rectify.getAcceptanceDate());
                                    //cal.add(Calendar.HOUR_OF_DAY, 8);
                                    propertyRectify.setAcceptanceDate(cal.getTime());
                                }
                                if (!StringUtil.isEmpty(rectify.getProblemType())) {
                                    propertyRectify.setProblemType(rectify.getProblemType());
                                }
                                if (!StringUtil.isEmpty(rectify.getClassifyOne())) {
                                    propertyRectify.setClassifyOne(rectify.getClassifyOne());
                                }
                                if (!StringUtil.isEmpty(rectify.getClassifyTwo())) {
                                    propertyRectify.setClassifyTwo(rectify.getClassifyTwo());
                                }
                                if (!StringUtil.isEmpty(rectify.getClassifyThree())) {
                                    propertyRectify.setClassifyThree(rectify.getClassifyThree());
                                }
                                if (rectify.getRegisterDate() != null) {
                                    cal.setTime(rectify.getRegisterDate());
                                    //cal.add(Calendar.HOUR_OF_DAY, 8);
                                    propertyRectify.setRegisterDate(cal.getTime());
                                }
                                propertyRectify.setFailType("0");
                                propertyRectify.setFailNum(0);
                                if (!StringUtil.isEmpty(rectify.getRectifyState())) {
                                    if ("start".equals(rectify.getRectifyState())) {
                                        propertyRectify.setRectifyState("待接单");
                                    } else if ("processing".equals(rectify.getRectifyState())) {
                                        propertyRectify.setRectifyState("处理中");
                                    } else if ("finish".equals(rectify.getRectifyState())) {
                                        propertyRectify.setRectifyState("已完成");
                                    } else if ("forceClose".equals(rectify.getRectifyState())) {
                                        propertyRectify.setRectifyState("强制关闭");
                                    } else if ("abandoned".equals(rectify.getRectifyState())) {
                                        propertyRectify.setRectifyState("已废弃");
                                    } else if (!StringUtil.isEmpty(rectify.getRectifyState())) {
                                        propertyRectify.setRectifyState(rectify.getRectifyState());
                                    }
                                }
                                if (!StringUtil.isEmpty(rectify.getRoomLocation())) {
                                    propertyRectify.setRoomLocation(rectify.getRoomLocation());
                                }
                                if (!StringUtil.isEmpty(rectify.getSupplier())) {
                                    propertyRectify.setSupplier(rectify.getSupplier());
                                }
                                if (rectify.getRectifyCompleteDate() != null) {
                                    cal.setTime(rectify.getRectifyCompleteDate());
                                    propertyRectify.setLimitDate(cal.getTime());
                                    propertyRectify.setRectifyCompleteDate(cal.getTime());
                                }
                                if (rectify.getRealityDate() != null) {
                                    cal.setTime(rectify.getRealityDate());
                                    //cal.add(Calendar.HOUR_OF_DAY, 8);
                                    propertyRectify.setRealityDate(cal.getTime());
                                }
                                if (!StringUtil.isEmpty(rectify.getProblemDescription())) {
                                    propertyRectify.setProblemDescription(rectify.getProblemDescription());
                                }
                                if (!StringUtil.isEmpty(rectify.getDealResult())) {
                                    propertyRectify.setDealResult(rectify.getDealResult());
                                }
                                propertyRectify.setModifyDate(new Date());
                                propertyRectifyCRMRepository.update(propertyRectify);
                                //调用日志
                                InterFaceRectifyLogsEntity rectifyLogsEntity=new InterFaceRectifyLogsEntity();
                                rectifyLogsEntity.setId(IdGen.uuid());//日志id
                                rectifyLogsEntity.setCode("200");//返回码
                                rectifyLogsEntity.setCreaDate(new Date());//日志创建日期
                                rectifyLogsEntity.setInterfaceClass("BasicRepairInfoServiceImpl.classes");
                                rectifyLogsEntity.setInterfaceName("crm调用整改接口，推送整改单数据：修改整改信息！");
                                rectifyLogsEntity.setRectifyId(rectify.getRectifyId());//整改单ID
                                rectifyLogsEntity.setRectifyState(rectify.getRectifyState());//整改状态
                                rectifyLogsEntity.setCreaBy(rectify.getCreatorName());//报修人
                                rectifyLogsEntity.setInfo("crm推送整改单数据：房间编码'" +rectify.getRoomNum()+"' 报修内容：'"+rectify.getProblemDescription()+"'");
                                interfaceLogRepository.createRectify(rectifyLogsEntity);
                            } else {
                                PropertyRectifyCRMEntity propertyRectifyCRMEntity = new PropertyRectifyCRMEntity();
                                propertyRectifyCRMEntity.setDepartment("2");
                                propertyRectifyCRMEntity.setFailType("0");
                                propertyRectifyCRMEntity.setFailNum(0);
                                if (!StringUtil.isEmpty(rectify.getCreatorName())) {
                                    propertyRectifyCRMEntity.setCreateByName(rectify.getCreatorName());
                                }
                                if (!StringUtil.isEmpty(rectify.getHandlerName())) {
                                    propertyRectifyCRMEntity.setUpdateUserName(rectify.getHandlerName());
                                }
                                String roomSequence = propertyRectifyCRMRepository.getRoomSequence(rectify.getRoomNum());
                                propertyRectifyCRMEntity.setSerialNumber(roomSequence);//当前房间序列号
                                propertyRectifyCRMEntity.setRectifyId(rectify.getRectifyId());
                                propertyRectifyCRMEntity.setAppId(rectify.getRectifyId() + "C");//手机唯一
//                        propertyRectifyCRMEntity.setDepartment(rectify.getDepartment());
                                propertyRectifyCRMEntity.setRoomId(rectify.getRoomId());
                                if (!StringUtil.isEmpty(rectify.getRoomNum())) {
                                    propertyRectifyCRMEntity.setRoomNum(rectify.getRoomNum());
                                    String project = propertyRectifyCRMRepository.getprojectNumForRoom(rectify.getRoomNum());
                                    if (project != null && !"".equals(project)) {
                                        propertyRectifyCRMEntity.setProjectNum(project);
                                    }
                                }
                                propertyRectifyCRMEntity.setCreateDate(new Date());
                                if (!StringUtil.isEmpty(rectify.getPlanNum())) {
                                    propertyRectifyCRMEntity.setPlanNum(rectify.getPlanNum());
                                    String plan = propertyRectifyCRMRepository.getplanTypeForPlan(rectify.getPlanNum());
                                    if (plan != null && !"".equals(plan)) {
                                        propertyRectifyCRMEntity.setPlanType(plan);
                                    }
                                }
                                if (rectify.getAcceptanceDate() != null) {
                                    cal.setTime(rectify.getAcceptanceDate());
                                    //cal.add(Calendar.HOUR_OF_DAY, 8);
                                    propertyRectifyCRMEntity.setAcceptanceDate(cal.getTime());
                                }
                                propertyRectifyCRMEntity.setProblemType(rectify.getProblemType());
                                propertyRectifyCRMEntity.setClassifyOne(rectify.getClassifyOne());
                                propertyRectifyCRMEntity.setClassifyTwo(rectify.getClassifyTwo());
                                propertyRectifyCRMEntity.setClassifyThree(rectify.getClassifyThree());
                                if (rectify.getRegisterDate() != null) {
                                    cal.setTime(rectify.getRegisterDate());
                                    //cal.add(Calendar.HOUR_OF_DAY, 8);
                                    propertyRectifyCRMEntity.setRegisterDate(cal.getTime());
                                }
                                if (!StringUtil.isEmpty(rectify.getRectifyState())) {
                                    if ("start".equals(rectify.getRectifyState())) {
                                        propertyRectifyCRMEntity.setRectifyState("待接单");
                                    } else if ("processing".equals(rectify.getRectifyState())) {
                                        propertyRectifyCRMEntity.setRectifyState("处理中");
                                    } else if ("finish".equals(rectify.getRectifyState())) {
                                        propertyRectifyCRMEntity.setRectifyState("已完成");
                                    } else if ("forceClose".equals(rectify.getRectifyState())) {
                                        propertyRectifyCRMEntity.setRectifyState("强制关闭");
                                    } else if ("abandoned".equals(rectify.getRectifyState())) {
                                        propertyRectifyCRMEntity.setRectifyState("已废弃");
                                    } else if (!StringUtil.isEmpty(rectify.getRectifyState())) {
                                        propertyRectifyCRMEntity.setRectifyState(rectify.getRectifyState());
                                    }
                                }
                                propertyRectifyCRMEntity.setRoomLocation(rectify.getRoomLocation());
                                propertyRectifyCRMEntity.setSupplier(rectify.getSupplier());
                                if (rectify.getRectifyCompleteDate() != null) {
                                    cal.setTime(rectify.getRectifyCompleteDate());
                                    propertyRectifyCRMEntity.setLimitDate(cal.getTime());
                                    propertyRectifyCRMEntity.setRectifyCompleteDate(rectify.getRectifyCompleteDate());
                                }
                                if (rectify.getRealityDate() != null) {
                                    cal.setTime(rectify.getRealityDate());
                                    //cal.add(Calendar.HOUR_OF_DAY, 8);
                                    propertyRectifyCRMEntity.setRealityDate(cal.getTime());
                                }
                                propertyRectifyCRMEntity.setModifyDate(new Date());
                                propertyRectifyCRMEntity.setProblemDescription(rectify.getProblemDescription());
                                propertyRectifyCRMEntity.setDealResult(rectify.getDealResult());
                                propertyRectifyCRMRepository.create(propertyRectifyCRMEntity);
                                //调用日志
                                InterFaceRectifyLogsEntity rectifyLogsEntity=new InterFaceRectifyLogsEntity();
                                rectifyLogsEntity.setId(IdGen.uuid());//日志id
                                rectifyLogsEntity.setCode("200");//返回码
                                rectifyLogsEntity.setCreaDate(new Date());//日志创建日期
                                rectifyLogsEntity.setInterfaceClass("BasicRepairInfoServiceImpl.classes");
                                rectifyLogsEntity.setInterfaceName("crm调用整改接口，推送整改单数据：创建整改信息！");
                                rectifyLogsEntity.setRectifyId(rectify.getRectifyId());//整改单ID
                                rectifyLogsEntity.setRectifyState(rectify.getRectifyState());//整改状态
                                rectifyLogsEntity.setCreaBy(rectify.getCreatorName());//报修人
                                rectifyLogsEntity.setInfo( " crm推送整改单数据：房间编码'" +rectify.getRoomNum()+"' 报修内容：'"+rectify.getProblemDescription()+"'");
                                interfaceLogRepository.createRectify(rectifyLogsEntity);
                            }
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    //调用日志
                    InterFaceRectifyLogsEntity rectifyLogsEntity=new InterFaceRectifyLogsEntity();
                    rectifyLogsEntity.setId(IdGen.uuid());//日志id
                    rectifyLogsEntity.setCode("500");//返回码
                    rectifyLogsEntity.setCreaDate(new Date());//日志创建日期
                    rectifyLogsEntity.setInterfaceClass("BasicRepairInfoServiceImpl.classes");
                    rectifyLogsEntity.setInterfaceName("crm调用整改接口，推送整改单数据：失败！");
                    rectifyLogsEntity.setRectifyId(errorRetifyId);//整改单ID
                    rectifyLogsEntity.setInfo("crm推送整改单数据：失败");
                    interfaceLogRepository.createRectify(rectifyLogsEntity);
                    return "500";
                }
                return "200";
            }catch (Exception e){
                e.printStackTrace();
                //调用日志
                InterFaceRepairLogsEntity repairLogsEntity=new InterFaceRepairLogsEntity();
                repairLogsEntity.setId(IdGen.uuid());//日志id
                repairLogsEntity.setCode("500");//返回码
                repairLogsEntity.setCreaDate(new Date());//日志创建日期
                repairLogsEntity.setInterfaceClass("BasicRepairInfoServiceImpl.classes");
                repairLogsEntity.setInterfaceName("crm调用报修接口，推送报修单数据：失败！");
                repairLogsEntity.setRepairId(errorRepairId);//报修单ID
                repairLogsEntity.setInfo("crm推送报修单数据： 推送失败");
                interfaceLogRepository.createRepair(repairLogsEntity);
                return "500";
            }
    }
}