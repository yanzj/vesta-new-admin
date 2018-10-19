package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.complaint.ComplaintWay;
import com.maxrocky.vesta.application.inf.IBasicService;
import com.maxrocky.vesta.application.inf.RepairClientService;
import com.maxrocky.vesta.application.publicModel.*;
import com.maxrocky.vesta.application.repair.RepairRequest;
import com.maxrocky.vesta.application.repair.RepairRequestBody;
import com.maxrocky.vesta.application.repair.RepairRequestHeader;
import com.maxrocky.vesta.application.repair.RepairResponse;
import com.maxrocky.vesta.domain.model.InterfaceLogEntity;
import com.maxrocky.vesta.domain.model.PropertyImageEntity;
import com.maxrocky.vesta.domain.model.PropertyRectifyCRMEntity;
import com.maxrocky.vesta.domain.model.PropertyRepairCRMEntity;
import com.maxrocky.vesta.domain.repository.InterfaceLogRepository;
import com.maxrocky.vesta.domain.repository.PropertyImageRepository;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by liudongxin on 2016/5/6.
 * 报修/整改信息同步接口
 */
@Service
public class RepairClientServiceImpl implements RepairClientService {
    @Autowired
    private PropertyImageRepository propertyImageRepository;
    @Autowired
    private InterfaceLogRepository interfaceLogRepository;


    @Override
    public String getPropertyRepair(PropertyRepairCRMEntity propertyRepair, PropertyRectifyCRMEntity propertyRectify) {
        try {
            RepairRequest request = new RepairRequest();
            RepairRequestBody repairRequestBody=new RepairRequestBody();
            //日期转换
//            Calendar calendar = Calendar.getInstance();
            //报修单
            if (propertyRepair != null) {
                CustomerComplaints customerComplaints = new CustomerComplaints();
                if(!StringUtil.isEmpty(propertyRepair.getRepairId())) {
                    customerComplaints.setName(propertyRepair.getRepairId());
                }
                if(!StringUtil.isEmpty(propertyRepair.getRoomNum())) {
                    customerComplaints.setUnithousecode(propertyRepair.getRoomNum());
                }
                if(!StringUtil.isEmpty(propertyRepair.getMemberId())){
                    customerComplaints.setCustomerProperty(propertyRepair.getMemberId());
                }
                if(!StringUtil.isEmpty(propertyRepair.getUserName())){
                    customerComplaints.setCustomerName(propertyRepair.getUserName());
                }
                if(!StringUtil.isEmpty(propertyRepair.getUserPhone())){
                    customerComplaints.setIncomingCall(propertyRepair.getUserPhone());
                }
                if(propertyRepair.getSendDate()!=null){
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(propertyRepair.getSendDate());
                    customerComplaints.setSendTime(calendar);
                }
                if(propertyRepair.getSendName()!=null){
                    customerComplaints.setSendName(propertyRepair.getSendName());
                }
                if(propertyRepair.getCreateDate() !=null){
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(propertyRepair.getCreateDate());
                    /*System.out.println("calendar1:" + calendar);
                    System.out.println("calendar.getTime()1:" + calendar.getTime());
                    calendar.getTime();*/
                    //calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    //System.out.println("calendar2:" + calendar);
                    calendar.getTime();
                    //System.out.println("calendar.getTime()2:" + calendar.getTime());
                    customerComplaints.setPetitionTime(calendar);
                    System.out.println(calendar.getTime() + "calendar.getTime():++++++++++++++++++++++++++++++++++++" + calendar + "calendar++++++++++++++");
                }
                if(!StringUtil.isEmpty(propertyRepair.getClassifyOne())){
                    customerComplaints.setComplaintClassification(propertyRepair.getClassifyOne());
                }
                if(!StringUtil.isEmpty(propertyRepair.getClassifyTwo())){
                    customerComplaints.setComplaintsReasonsTwo(propertyRepair.getClassifyTwo());
                }
                if(!StringUtil.isEmpty(propertyRepair.getClassifyThree())){
                    customerComplaints.setComplaintsReasonsThree(propertyRepair.getClassifyThree());
                }
                if(!StringUtil.isEmpty(propertyRepair.getMode())){
                    customerComplaints.setMaintenanceMode(propertyRepair.getMode());
                }
                if(propertyRepair.getRepairDate()!=null){
                    customerComplaints.setMaintenanceTime(propertyRepair.getRepairDate());
                }
                if(!StringUtil.isEmpty(propertyRepair.getUserAddress())) {
                    customerComplaints.setContactAddress(propertyRepair.getUserAddress());
                }
                if(!StringUtil.isEmpty(propertyRepair.getRoomLocation())) {
                    customerComplaints.setLocation(propertyRepair.getRoomLocation());
                }
                if(!StringUtil.isEmpty(propertyRepair.getState())) {
                    if (propertyRepair.getState().equals("create")) {//创建
                        customerComplaints.setCStatus(CStatus.create);
                    }if(propertyRepair.getState().equals("accepted")){//受理
                        customerComplaints.setCStatus(CStatus.accepted);
                    }if(propertyRepair.getState().equals("processing")){//处理中
                        customerComplaints.setCStatus(CStatus.processing);
                    }if(propertyRepair.getState().equals("completed")){//已完成
                        customerComplaints.setCStatus(CStatus.completed);
                    }if(propertyRepair.getState().equals("closed")){//已作废
                        customerComplaints.setCStatus(CStatus.closed);
                    }if(propertyRepair.getState().equals("returnVisit")){//已评价
                        customerComplaints.setCStatus(CStatus.returnVisit);
                    }
                }
                if(!StringUtil.isEmpty(propertyRepair.getDuty())) {
                    if(propertyRepair.getDuty().equals("0")) {
                        customerComplaints.setIfResponsibility(false);
                    }else{
                        customerComplaints.setIfResponsibility(true);
                    }
                }
                if(!StringUtil.isEmpty(propertyRepair.getProblemLevel())){
                    if(propertyRepair.getProblemLevel().equals("urgent")) {//紧急
                        customerComplaints.setProblemLevel(ProblemLevel.urgent);
                    }else{
                        customerComplaints.setProblemLevel(ProblemLevel.nonUrgent);//非紧急
                    }
                }
                if(!StringUtil.isEmpty(propertyRepair.getRepairWay())) {
                    if (propertyRepair.getRepairWay().equals("wechat")) {//微信
                        customerComplaints.setComplaintWay(ComplaintWay.wechat);
                    }else if(propertyRepair.getRepairWay().equals("APP")){
                        customerComplaints.setComplaintWay(ComplaintWay.App);//app
                    }else if(propertyRepair.getRepairWay().equals("website")){
                        customerComplaints.setComplaintWay(ComplaintWay.website);//网页
                    }else if(propertyRepair.getRepairWay().equals("callCenter")){
                        customerComplaints.setComplaintWay(ComplaintWay.callCenter);//呼叫中心
                    }else if(propertyRepair.getRepairWay().equals("receptionist")){
                        customerComplaints.setComplaintWay(ComplaintWay.receptionist);//项目前台
                    }else if(propertyRepair.getRepairWay().equals("propertyDocuments")){
                        customerComplaints.setComplaintWay(ComplaintWay.propertyDocuments);//物业单据
                    }
                }
                if(!StringUtil.isEmpty(propertyRepair.getContent())) {
                    customerComplaints.setBasicContent(propertyRepair.getContent());
                }
                if(propertyRepair.getTaskDate()!=null) {
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTime(propertyRepair.getTaskDate());
                    //calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    calendar1.getTime();
                    customerComplaints.setSingleTimeWithComplaints(calendar1);
                    System.out.println(calendar1.getTime() + "calendar1.getTime()++++++++++++++" + calendar1 + "calendar1++++++++++++++++++");
                }
                if(!StringUtil.isEmpty(propertyRepair.getDealWay())) {
                    customerComplaints.setDetailProblem(propertyRepair.getDealWay());
                }
                if(!StringUtil.isEmpty(propertyRepair.getDealMode())) {
                    if(propertyRepair.getDealMode().equals("interior")) {//内部
                        customerComplaints.setProcessingWay(ProcessingWay.interior);
                    }else if(propertyRepair.getDealMode().equals("accountabilityUnit")) {//责任单位
                        customerComplaints.setProcessingWay(ProcessingWay.accountabilityUnit);
                    }else if(propertyRepair.getDealMode().equals("thirdParty")) {//第三方
                        customerComplaints.setProcessingWay(ProcessingWay.thirdParty);
                    }else if(propertyRepair.getDealMode().equals("mutualProcess")) {//责任单位和第三方同处理
                        customerComplaints.setProcessingWay(ProcessingWay.mutualProcess);
                    }
                }
                if(!StringUtil.isEmpty(propertyRepair.getDutyCompanyOne())) {
                    customerComplaints.setAccountability(propertyRepair.getDutyCompanyOne());
                }
                if(!StringUtil.isEmpty(propertyRepair.getDutyCompanyTwo())) {
                    customerComplaints.setAccountability2(propertyRepair.getDutyCompanyTwo());
                }
                if(!StringUtil.isEmpty(propertyRepair.getDutyCompanyThree())) {
                    customerComplaints.setAccountability3(propertyRepair.getDutyCompanyThree());
                }
                if(!StringUtil.isEmpty(propertyRepair.getRepairCompany())) {
                    customerComplaints.setCompairCompany(propertyRepair.getRepairCompany());
                }
                if(propertyRepair.getDutyTaskDate()!=null) {
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTime(propertyRepair.getDutyTaskDate());
                    //calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    calendar2.getTime();
                    customerComplaints.setResponsibilityReturnTime(calendar2);
                    System.out.println(calendar2.getTime() + "calendar2.getTime();++++++++++++++++" + calendar2 + "calendar2+++++++++++++++++++++++");
                }
                if(!StringUtil.isEmpty(propertyRepair.getRepairManager())){
                    customerComplaints.setMaintenanceOwner(propertyRepair.getRepairManager());
                }
                if(!StringUtil.isEmpty(propertyRepair.getDealPeople())){
                    customerComplaints.setDealingPeople(propertyRepair.getDealPeople());
                }
                if(!StringUtil.isEmpty(propertyRepair.getDealPhone())) {
                    customerComplaints.setDealingmobile(propertyRepair.getDealPhone());
                }
                if(!StringUtil.isEmpty(propertyRepair.getDealPeopleTwo())) {
                    customerComplaints.setDealingPeople2(propertyRepair.getDealPeopleTwo());
                }
                if(!StringUtil.isEmpty(propertyRepair.getDealPhoneTwo())) {
                    customerComplaints.setDealingmobile2(propertyRepair.getDealPhoneTwo());
                }
                if(propertyRepair.getFirstVisitDate()!=null) {
                    Calendar calendar3 = Calendar.getInstance();
                    calendar3.setTime(propertyRepair.getFirstVisitDate());
                    //calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    calendar3.getTime();
                    customerComplaints.setEsTimatedoTime(calendar3);
                    System.out.println(calendar3.getTime() + "calendar3.getTime();+++++++++++++++++++++" + calendar3 + "calendar3++++++++++++++++");
                }
                if(propertyRepair.getBackupVisitDate()!=null) {
                    Calendar calendar4 = Calendar.getInstance();
                    calendar4.setTime(propertyRepair.getBackupVisitDate());
                    //calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    calendar4.getTime();
                    customerComplaints.setAlternativeTime(calendar4);
                    System.out.println(calendar4.getTime() + " calendar4.getTime();++++++++++" + calendar4 + "calendar4+++++++++++++++++++++++++++++++");
                }
                if(!StringUtil.isEmpty(propertyRepair.getDealState())) {
                    if(propertyRepair.getDealState().equals("0")) {//暂不处理
                        customerComplaints.setIssuspende(false);
                    }else{
                        customerComplaints.setIssuspende(true);//处理
                    }
                }
                if(!StringUtil.isEmpty(propertyRepair.getDealResult())) {
                    customerComplaints.setProcessingScheme(propertyRepair.getDealResult());
                }
                if(propertyRepair.getDealCompleteDate()!=null) {
                    Calendar calendar5 = Calendar.getInstance();
                    calendar5.setTime(propertyRepair.getDealCompleteDate());
                    //calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    calendar5.getTime();
                    customerComplaints.setCompletionTime(calendar5);
                    System.out.println(calendar5.getTime() + "calendar5.getTime()++++++++++++" + calendar5 + "calendar5+++++++++++++");
                }
                if(!StringUtil.isEmpty(propertyRepair.getVisitOpinion())) {
                    customerComplaints.setCustomerVisitOpinion(propertyRepair.getVisitOpinion());
                }
                if(!StringUtil.isEmpty(propertyRepair.getVisitEvaluate())) {
                    if(propertyRepair.getVisitEvaluate().equals("highlySatisfied")) {//非常满意
                        customerComplaints.setVisitEvaluation(VisitEvaluation.highlySatisfied);
                    }else if(propertyRepair.getVisitEvaluate().equals("satisfied")){//满意
                        customerComplaints.setVisitEvaluation(VisitEvaluation.satisfied);
                    }else if(propertyRepair.getVisitEvaluate().equals("commonSatisfied")){//一般
                        customerComplaints.setVisitEvaluation(VisitEvaluation.commonSatisfied);
                    }else if(propertyRepair.getVisitEvaluate().equals("dissatisfied")){//不满意
                        customerComplaints.setVisitEvaluation(VisitEvaluation.dissatisfied);
                    }else if(propertyRepair.getVisitEvaluate().equals("highlyDissatisfied")){//非常不满意
                        customerComplaints.setVisitEvaluation(VisitEvaluation.highlyDissatisfied);
                    }
                }
                if(propertyRepair.getVisitDate()!=null) {
                    Calendar calendar6 = Calendar.getInstance();
                    calendar6.setTime(propertyRepair.getVisitDate());
                    //calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    calendar6.getTime();
                    customerComplaints.setReturnTime(calendar6);
                    System.out.println(calendar6.getTime() + "calendar6.getTime()+++++++++++++++++++++" + calendar6 + "calendar6+++++++++++++++++++++");
                }
                if(!StringUtil.isEmpty(propertyRepair.getVisitType())) {
                    if(propertyRepair.getVisitType().equals("phone")) {//电话
                        customerComplaints.setVisitType(VisitType.phone);
                    }else if(propertyRepair.getVisitType().equals("interview")){//面谈
                        customerComplaints.setVisitType(VisitType.interview);
                    }else if(propertyRepair.getVisitType().equals("shortMessage")){//短信
                        customerComplaints.setVisitType(VisitType.shortMessage);
                    }else if(propertyRepair.getVisitType().equals("email")){//电子邮件
                        customerComplaints.setVisitType(VisitType.email);
                    }else if(propertyRepair.getVisitType().equals("others")){//其他
                        customerComplaints.setVisitType(VisitType.others);
                    }
                }
                if(!StringUtil.isEmpty(propertyRepair.getDepartment())){
                    if("100000000".equals(propertyRepair.getDepartment())){
                        customerComplaints.setServiceGroup(ServiceGroup.developmentGroup);
                    }else if("100000001".equals(propertyRepair.getDepartment())){
                        customerComplaints.setServiceGroup(ServiceGroup.propertyGroup);
                    }
                }
                List<PropertyImageEntity> imageEntityList = propertyImageRepository.getImageUrl(propertyRepair.getRepairId());
                List<ImageModel> imageList = new ArrayList<ImageModel>();
                ImageModel[] imageModels;
                if (imageEntityList.size() > 0) {
                    for (PropertyImageEntity propertyImage : imageEntityList) {
                        ImageModel imageModel = new ImageModel();
                        imageModel.setImageId(propertyImage.getImageId());
                        imageModel.setImageUrl(propertyImage.getImagePath());
                        imageModel.setImageStatus("0");
                        imageList.add(imageModel);
                        int size=imageEntityList.size();
                        imageModels=(ImageModel[])imageList.toArray(new ImageModel[size]);
                        customerComplaints.setImageList(imageModels);
                    }
                }
                List<PropertyImageEntity> imageEntityListend = propertyImageRepository.getImagedUrl(propertyRepair.getRepairId());
                List<ImageModel> imageListend = new ArrayList<ImageModel>();
                ImageModel[] imageModelsend;
                if (imageEntityListend.size() > 0) {
                    for (PropertyImageEntity propertyImage : imageEntityListend) {
                        ImageModel imageModel = new ImageModel();
                        imageModel.setImageId(propertyImage.getImageId());
                        imageModel.setImageUrl(propertyImage.getImagePath());
                        imageModel.setImageStatus("1");
                        imageList.add(imageModel);
                        int size=imageEntityListend.size();
                        imageModels=(ImageModel[])imageList.toArray(new ImageModel[size]);
                        customerComplaints.setImageList(imageModels);
                    }
                }

                System.out.println("===================================================================================");
                System.out.println(customerComplaints);
                System.out.println("===================================================================================");
                repairRequestBody.setCustomerComplaint(customerComplaints);

                //调用日志
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供报修单同步接口:同步数据:写入调用crm！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("property_repair_crm");
                interfaceLogEntity.setEntityId(propertyRepair.getRepairId());
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogEntity.setMemberId(propertyRepair.getMemberId());
                interfaceLogRepository.create(interfaceLogEntity);
            }
            //整改单
            if (propertyRectify != null) {
                Innerrectify innerrectify = new Innerrectify();
                if(!StringUtil.isEmpty(propertyRectify.getCreateByName())){
                    innerrectify.setCreatorName(propertyRectify.getCreateByName());
                }
                if(!StringUtil.isEmpty(propertyRectify.getUpdateUserName())){
                    innerrectify.setHandlerName(propertyRectify.getUpdateUserName());
                }
                if(!StringUtil.isEmpty(propertyRectify.getSupplierName())){
                    innerrectify.setSupplierName(propertyRectify.getSupplierName());
                }
                if(!StringUtil.isEmpty(propertyRectify.getRepairPhone())){
                    innerrectify.setSupplierPhone(propertyRectify.getRepairPhone());
                }
                if(!StringUtil.isEmpty(propertyRectify.getRectifyId())) {
                    innerrectify.setCode(propertyRectify.getRectifyId());
                }
                if(!StringUtil.isEmpty(propertyRectify.getClassifyOne())) {
                    innerrectify.setComplaintClassification(propertyRectify.getClassifyOne());
                }
                if(!StringUtil.isEmpty(propertyRectify.getClassifyTwo())) {
                    innerrectify.setComplaintsReasonsTwo(propertyRectify.getClassifyTwo());
                }
                if(!StringUtil.isEmpty(propertyRectify.getClassifyThree())) {
                    innerrectify.setComplaintsReasonsThree(propertyRectify.getClassifyThree());
                }
                if(propertyRectify.getRectifyCompleteDate()!=null) {
                    Calendar calendar7 = Calendar.getInstance();
                    calendar7.setTime(propertyRectify.getRectifyCompleteDate());
                    //calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    calendar7.getTime();
                    innerrectify.setDeadline(calendar7);
                }

                if(propertyRectify.getRealityDate()!=null) {
                    Calendar calendar8 = Calendar.getInstance();
                    calendar8.setTime(propertyRectify.getRealityDate());
                    //calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    /*System.out.println("==========shi jin wan cheng shijian :"+propertyRectify.getRealityDate());
                    System.out.println("==========shi jin wan cheng shijian :"+calendar8.getTime());*/
                    innerrectify.setDaedline(calendar8);
                }

                if(propertyRectify.getCreateDate()!=null) {
                    Calendar calendar9 = Calendar.getInstance();
                    calendar9.setTime(propertyRectify.getCreateDate());
                    /*System.out.println("==========create date  :" + propertyRectify.getCreateDate());
                    System.out.println("==========create date  :" + calendar9.getTime());*/
                    //calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    calendar9.getTime();
                    innerrectify.setBegienTime(calendar9);
                }

                if(propertyRectify.getLimitDate()!=null) {
                    Calendar calendar10 = Calendar.getInstance();
                    calendar10.setTime(propertyRectify.getLimitDate());
                    /*System.out.println("==========limit date  :" + propertyRectify.getLimitDate());
                    System.out.println("==========limit date  :" + calendar10.getTime());*/
                    //calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    calendar10.getTime();
                    innerrectify.setDeadline(calendar10);
                }

                if(!StringUtil.isEmpty(propertyRectify.getPlanNum())) {
                    innerrectify.setDeliveryPlane(propertyRectify.getPlanNum());
                }
                if(!StringUtil.isEmpty(propertyRectify.getProblemDescription())) {
                    innerrectify.setDescription(propertyRectify.getProblemDescription());
                }
                if(!StringUtil.isEmpty(propertyRectify.getRectifyState())) {
                    if(propertyRectify.getRectifyState().equals("已完成")){
                        innerrectify.setInternalAcceptanceStatus(InternalAcceptanceStatus.finish);//完成
                    }else if(propertyRectify.getRectifyState().equals("已废弃")){
                        innerrectify.setInternalAcceptanceStatus(InternalAcceptanceStatus.abandoned);//已废弃
                    }else if(propertyRectify.getRectifyState().equals("处理中")){
                        innerrectify.setInternalAcceptanceStatus(InternalAcceptanceStatus.processing);//处理中
                    }else if(propertyRectify.getRectifyState().equals("强制关闭")){
                        innerrectify.setInternalAcceptanceStatus(InternalAcceptanceStatus.forceClose);//强制关闭
                    }else {
                        innerrectify.setInternalAcceptanceStatus(InternalAcceptanceStatus.start);//开始
                    }
                }
                if(propertyRectify.getAcceptanceDate() != null) {
                    Calendar calendar9 = Calendar.getInstance();
                    calendar9.setTime(propertyRectify.getAcceptanceDate());
                    //calendar.add(Calendar.HOUR_OF_DAY, 8);//在小时上加8小时
                    calendar9.getTime();
                    innerrectify.setInternalAcceptanceDate(calendar9);
                }
                if(!StringUtil.isEmpty(propertyRectify.getRoomLocation())) {
                    innerrectify.setLocation(propertyRectify.getRoomLocation());
                }
                if(!StringUtil.isEmpty(propertyRectify.getProblemType())) {
                    innerrectify.setQuestionCategory(propertyRectify.getProblemType());
                }
                if(!StringUtil.isEmpty(propertyRectify.getDealResult())) {
                    innerrectify.setResult(propertyRectify.getDealResult());
                }
                if(!StringUtil.isEmpty(propertyRectify.getSupplier())) {
                    innerrectify.setSupplier(propertyRectify.getSupplier());
                }
                if(!StringUtil.isEmpty(propertyRectify.getRoomNum())) {
                    innerrectify.setUnitHouseCode(propertyRectify.getRoomNum());
                }
                if(!StringUtil.isEmpty(propertyRectify.getRepairManager())){
                    innerrectify.setRepairManager(propertyRectify.getRepairManager());
                }
                List<PropertyImageEntity> imageEntityList = propertyImageRepository.getImageByType(propertyRectify.getRectifyId(),"5");
                List<ImageModel> imageList = new ArrayList<ImageModel>();
                ImageModel[] imageModels;
                if (imageEntityList.size() > 0) {
                    for (PropertyImageEntity propertyImage : imageEntityList) {
                        ImageModel imageModel = new ImageModel();
                        imageModel.setImageId(propertyImage.getImageId());
                        imageModel.setImageUrl(propertyImage.getImagePath());
                        imageModel.setImageStatus("0");//0代表整改问题图片
                        imageList.add(imageModel);
                        /*int size=imageEntityList.size();
                        imageModels=(ImageModel[])imageList.toArray(new ImageModel[size]);
                        innerrectify.setImageList(imageModels);*/
                    }
                }

                List<PropertyImageEntity> reviewImageEntityList = propertyImageRepository.getImageByType(propertyRectify.getRectifyId(),"6");
                if (reviewImageEntityList.size() > 0) {
                    for (PropertyImageEntity propertyImage : reviewImageEntityList) {
                        ImageModel imageModel = new ImageModel();
                        imageModel.setImageId(propertyImage.getImageId());
                        imageModel.setImageUrl(propertyImage.getImagePath());
                        imageModel.setImageStatus("1");//1代表整改记录图片
                        imageList.add(imageModel);
                    }
                }

                if(imageList.size()>0){
                    int size=imageEntityList.size();
                    imageModels=(ImageModel[])imageList.toArray(new ImageModel[size]);
                    innerrectify.setImageList(imageModels);
                }

                repairRequestBody.setInnerrectify(innerrectify);
                //调用日志
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供整改单同步接口:同步数据:写入调用crm！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("property_rectify_crm");
                interfaceLogEntity.setEntityId(propertyRectify.getRectifyId());
                interfaceLogEntity.setErrorDate(new Date());
                //interfaceLogEntity.setMemberId(propertyRepair.getMemberId());
                interfaceLogRepository.create(interfaceLogEntity);
            }
            request.setBody(repairRequestBody);
            RepairRequestHeader repairRequestHeader=new RepairRequestHeader();
            repairRequestHeader.setSourceIdentifier(SourceIdentifier.QualityAPP);
            request.setHeader(repairRequestHeader);
            BasicServiceLocator basicService = new BasicServiceLocator();
            IBasicService iBasicService = basicService.getBasicHttpBinding_IBasicService();
            RepairResponse response=iBasicService.customerRepair(request);
            if(response.getHeader().getStatus().equals("0")){//失败
                //调用日志
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供报修/整改同步接口:同步数据:返回！");
                interfaceLogEntity.setCode("500");
                interfaceLogEntity.setEntityName("property_rectify_crm/property_repair_crm");
                interfaceLogEntity.setErrorDate(new Date());
                if(propertyRepair!=null){
                    if(!StringUtil.isEmpty(propertyRepair.getRepairId())){
                        interfaceLogEntity.setEntityId(propertyRepair.getRepairId());
                    }
                }
                if(propertyRectify!=null){
                    if(!StringUtil.isEmpty(propertyRectify.getRectifyId())){
                        interfaceLogEntity.setEntityId(propertyRectify.getRectifyId());
                    }
                }
                //interfaceLogEntity.setMemberId(propertyRepair.getMemberId());
                interfaceLogEntity.setMessage(response.getHeader().getErrorMessage());
                interfaceLogRepository.create(interfaceLogEntity);
                return "500";
            }else if(response.getHeader().getStatus().equals("1")){//成功
                //调用日志
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("提供报修/整改同步接口:同步数据:返回！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("property_rectify_crm/property_repair_crm");
                interfaceLogEntity.setErrorDate(new Date());
                if(propertyRepair!=null){
                    if(!StringUtil.isEmpty(propertyRepair.getRepairId())){
                        interfaceLogEntity.setEntityId(propertyRepair.getRepairId());
                    }
                }
                if(propertyRectify!=null){
                    if(!StringUtil.isEmpty(propertyRectify.getRectifyId())){
                        interfaceLogEntity.setEntityId(propertyRectify.getRectifyId());
                    }
                }
                //interfaceLogEntity.setMemberId(propertyRepair.getMemberId());
                interfaceLogEntity.setMessage(response.getHeader().getErrorMessage());
                interfaceLogRepository.create(interfaceLogEntity);
            }
            return "200";
        } catch (Exception e) {
            e.printStackTrace();
            return "500";
        }
    }

    /*@Override
    public String getRepair(PropertyRepairCRMEntity propertyRepair) {
        URL url = null;
        try {
            url=new URL("http://172.16.124.55/WCFBasicService/BasicService.svc?singleWsdl");
            Call soapCall = new Call();
            soapCall.setEncodingStyleURI(Constants.NS_URI_SOAP_ENC);
            soapCall.setTargetObjectURI("http://tempuri.org/IBasicService/CustomerRepair");
            soapCall.setMethodName("CustomerRepair");
            Vector soapParams = new Vector();
            soapParams.addElement(new Parameter("name", String.class, propertyRepair.getRepairId(), null));
            soapParams.addElement(new Parameter("unithousecode", String.class, propertyRepair.getRoomNum(), null));
            soapParams.addElement(new Parameter("customerProperty", String.class, propertyRepair.getMemberId(), null));
            soapParams.addElement(new Parameter("incomingCall", String.class, propertyRepair.getUserPhone(), null));
            soapParams.addElement(new Parameter("maintenanceMode", String.class, propertyRepair.getUserPhone(), null));
            soapParams.addElement(new Parameter("cStatus", String.class, propertyRepair.getState(), null));
            soapParams.addElement(new Parameter("complaintWay", String.class, propertyRepair.getRepairWay(), null));
            soapParams.addElement(new Parameter("basicContent", String.class, propertyRepair.getContent(), null));
            soapParams.addElement(new Parameter("basicContent", String.class, propertyRepair.getContent(), null));
            soapCall.setParams(soapParams);
            Response soapResponse = soapCall.invoke(url,"");
            if (soapResponse.generatedFault()) {
                Fault fault = soapResponse.getFault();
                String f = fault.getFaultString();
                return f;
            } else {
                Parameter soapResult = soapResponse.getReturnValue ();
                return soapResult.getValue().toString();
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return null;
    }*/
}
