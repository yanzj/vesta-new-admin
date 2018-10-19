package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.application.inf.*;
import com.maxrocky.vesta.application.service.config.ImgType;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.message.success.SuccessResource;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.*;
import com.maxrocky.vesta.weixin.WxImage;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by liudongxin on 2016/1/14.
 * 物业报修实现方法类
 */
@Service
public class PropertyRepairServiceImpl implements PropertyRepairService {
    @Autowired
    private PropertyRepairRepository propertyRepairRepository;
    @Autowired
    private PropertyImageRepository propertyImageRepository;
    @Autowired
    private PropertyRepairTaskRepository propertyRepairTaskRepository;
    @Autowired
    private PropertyFeedbackRepository propertyFeedbackRepository;
    @Autowired
    private HouseUserBookRepository houseUserBookRepository;//房屋业主关系
    @Autowired
    private HouseInfoRepository houseInfoRepository;//房屋业主地址
    @Autowired
    private HouseProjectRepository houseProjectRepository;//项目
    @Autowired
    private HouseSectionRepository houseSectionRepository;//部门
    @Autowired
    private UserSettingRepository userSettingRepository;
    @Autowired
    private PropertyPriceSettingRepository propertyPriceSettingRepository;//报修价格
    @Autowired
    private OperationLogService operationLogService;//管理端操作日志
    @Autowired
    private RepairClientService repairClientService;
    @Autowired
    private PropertyRepairCRMRepository propertyRepairCRMRepository;
    @Autowired
    private InvoicesTotalRepository invoicesTotalRepository;
    @Autowired
    private ClickUserRepository clickUserRepository;
    @Autowired
    private SystemLogRepository systemLogRepository;
    @Autowired
    UserSettingService userSettingService;
    @Autowired
    SMSAlertsService smsAlertsService;
    @Autowired
    SMSAuthService smsAuthService;
    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;
    /**
     * 历史报修列表：未完成/完成
     * @param type
     * @return
     */
    @Override
    public ApiResult getRepairHistory(String type,UserInfoEntity user,Page page) throws GeneralException{
        if(StringUtil.isEmpty(type)){
            return ErrorResource.getError("tip_00000055");//参数不能为空
        }
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        try{
            List<PropertyHistoriesDTO> historyDTOs=new ArrayList<PropertyHistoriesDTO>();
            //根据房屋查询报修单
            List<Object[]> houseList=new ArrayList<>();
            if(user.getUserType().equals("3") || user.getUserType().equals("6")){//业主
                houseList = houseInfoRepository.getAllHouse(user.getId());
            }else if(user.getUserType().equals("4")) {//同住人
                houseList=houseInfoRepository.getHousemateHouse(user.getUserId());
            }
            if(houseList.size()>0) {
                for(Object[] houseInfo : houseList) {
                    //查询历史记录信息
                    List<Object[]> historyList = propertyRepairRepository.getHistory(type, (String) houseInfo[0], page);
                    if (historyList.size() > 0) {
                        for (Object[] obj : historyList) {
                            PropertyHistoriesDTO historyDTO = new PropertyHistoriesDTO();
                            historyDTO.setId((String) obj[0]);
                            historyDTO.setContent((String) obj[1]);
                            historyDTO.setStatus((String) obj[2]);
                            //查询物业报修图片表，取第一张
                            List<PropertyImageEntity> imageEntity = propertyImageRepository.getImageUrl((String) obj[0]);//报修单id
                            if (imageEntity.size() > 0) {
                                if (imageEntity.get(0).getImagePath() != null && !"".equals(imageEntity.get(0).getImagePath())) {
                                    historyDTO.setImageUrl(imageEntity.get(0).getImagePath());
                                    historyDTO.setSrc(imageEntity.get(0).getImagePath());
                                } else {
                                    historyDTO.setImageUrl("http://123.57.160.155:8081/images/baoxiuwutupian1.jpg");
                                    historyDTO.setSrc("http://123.57.160.155:8081/images/baoxiuwutupian1.jpg");
                                }
                            } else {
                                historyDTO.setImageUrl("http://123.57.160.155:8081/images/baoxiuwutupian1.jpg");
                                historyDTO.setSrc("http://123.57.160.155:8081/images/baoxiuwutupian1.jpg");
                            }
                            historyDTOs.add(historyDTO);
                        }
                    }
                }
            }
            //调用点击人统计
            String dateNow=DateUtils.format(new Date(),DateUtils.FORMAT_SHORT);
            ClickUsersEntity clickUserEntity=clickUserRepository.getTotalInfo(dateNow, "7", user.getUserId());
            if(clickUserEntity==null){
                ClickUsersEntity clickUser=new ClickUsersEntity();
                clickUser.setId(IdGen.uuid());
                clickUser.setCreateDate(new Date());
                clickUser.setClicks(1);
                clickUser.setUserId(user.getUserId());
                clickUser.setForeignId("7");
                clickUserRepository.create(clickUser);
            }else{
                clickUserEntity.setClicks(clickUserEntity.getClicks()+1);
                clickUserRepository.update(clickUserEntity);
            }
            return new SuccessApiResult(historyDTOs);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 获取未报修数量
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult getRepairHistoryNum(UserInfoEntity user) throws GeneralException {
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        try {
            Integer num = propertyRepairRepository.getRepairHistoryNum(user);
            Map map = new HashMap();
            map.put("num", num.toString());
            return new SuccessApiResult(map);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 添加物业报修(维修和投诉)
     * @param user
     * @param workOrderDetailDTO
     * @return
     */
    @Override
    public ApiResult createRepairOrder(UserInfoEntity user,WorkOrderDetailDTO workOrderDetailDTO) throws GeneralException{
        if (workOrderDetailDTO == null) {
            return ErrorResource.getError("tip_00000040");//报修信息为空
        }
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(user.getUserId())){
            return ErrorResource.getError("tip_00000486");//用户信息为空
        }
        if(StringUtil.isEmpty(workOrderDetailDTO.getContent())){
            return ErrorResource.getError("tip_00000039");//报修内容为空
        }
        /*if(StringUtil.isEmpty(workOrderDetailDTO.getUserAddress())){
            return ErrorResource.getError("tip_pe00000010");//业主地址为空
        }*/
        if(StringUtil.isEmpty(workOrderDetailDTO.getUserName())){
            return ErrorResource.getError("tip_pe00000011");//业主姓名为空
        }
        if(StringUtil.isEmpty(workOrderDetailDTO.getUserPhone())){
            return ErrorResource.getError("tip_pe00000012");//业主电话为空
        }
        if(workOrderDetailDTO.getUserPhone().length()<11){
            return ErrorResource.getError("tip_pe00000032");//电话位数低于11位
        }
        if(workOrderDetailDTO.getUserPhone().length()>12){
            return ErrorResource.getError("tip_pe00000033");//电话位数打于12位
        }
        //正则表达式判断0-9纯数字
        boolean isNum =workOrderDetailDTO.getUserPhone().matches("[0-9]+");
        if(isNum==false){
            return ErrorResource.getError("tip_pe00000034");//电话不是纯数字
        }
        if(StringUtil.isEmpty(workOrderDetailDTO.getType())){
            return ErrorResource.getError("tip_pe00000013");//报修类型为空
        }
        if(StringUtil.isEmpty(workOrderDetailDTO.getRoomId())){
            return ErrorResource.getError("tip_pe00000030");//房屋id为空
        }
        try{
            //添加报修单
            PropertyRepairEntity propertyRepairEntity=new PropertyRepairEntity();
//            String id=IdGen.getDateId();
            HouseInfoEntity house=houseInfoRepository.get(workOrderDetailDTO.getRoomId());
            if(house==null) {
                return ErrorResource.getError("tip_pe00000031");//房屋信息为空
            }
            String []ss=house.getProjectNum().split("-");
            String repair=houseInfoRepository.getRepairidNew(ss[ss.length-1]+"-B");
            if(repair.length()==1){
                repair="000"+repair;
            }
            if(repair.length()==2){
                repair="00"+repair;
            }
            if(repair.length()==3){
                repair="0"+repair;
            }
            String id=ss[ss.length-1]+"-B-"+DateUtils.getNow("yyyyMMdd")+"-A-"+repair;
            System.out.println(id+"!~~~~~~~~~~idbianmaxian !!!!!!!!!!!!!!!!!!!~~~~~~~~~");
            propertyRepairEntity.setRepairId(id);//报修单id
            propertyRepairEntity.setContent(workOrderDetailDTO.getContent());//用户填写的报修内容
            propertyRepairEntity.setCreateBy(user.getUserId());//创建人
            propertyRepairEntity.setCreateDate(DateUtils.getDate());//创建时间
            propertyRepairEntity.setTypes("0");//0代表未完成；1代表已完成
            propertyRepairEntity.setState("已创建");//报修单状态

            propertyRepairEntity.setRoomId(house.getRoomId());//房间id
            propertyRepairEntity.setUserAddress(house.getAddress());//业主地址
            //获取业主的所在项目
            propertyRepairEntity.setRegionId(house.getProjectId());//项目id
            propertyRepairEntity.setRegionName(house.getProjectName());//项目名称
            propertyRepairEntity.setUserName(workOrderDetailDTO.getUserName());//业主姓名
            propertyRepairEntity.setUserPhone(workOrderDetailDTO.getUserPhone());//业主电话
            //添加任务
            PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
            propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
            propertyRepairTaskEntity.setRepairId(id);//报修单id
            propertyRepairTaskEntity.setTaskNode("用户提交");//任务节点名称
            propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
            propertyRepairTaskEntity.setReadStatus("0");
            propertyRepairEntity.setRepairWay("APP");
            if(workOrderDetailDTO.getType().equals("0")){
                propertyRepairEntity.setTaskStatus("0");//0为用户提交报修(业主)
                propertyRepairTaskEntity.setTaskName("已提交");
                propertyRepairTaskEntity.setTaskStatus("0");//0为用户提交报修(业主)
                propertyRepairTaskEntity.setTaskContent("您的报修信息已成功提交。");//任务内容
                propertyRepairEntity.setMemo("报修");
            }else if(workOrderDetailDTO.getType().equals("1")){
                propertyRepairEntity.setTaskStatus("1");//0为用户提交投诉(业主)
                propertyRepairTaskEntity.setTaskName("已提交");
                propertyRepairTaskEntity.setTaskStatus("1");//0为用户提交投诉(业主)
                propertyRepairTaskEntity.setTaskContent("您的投诉信息已成功提交。");//任务内容
                propertyRepairEntity.setMemo("投诉");
            }
            //添加报修图片
            if(workOrderDetailDTO.getImageList().size()>0){
                for(PropertyImageDTO propertyImage:workOrderDetailDTO.getImageList()){
                    PropertyImageEntity propertyImageEntity=new PropertyImageEntity();
                    propertyImageEntity.setImageId(IdGen.uuid());
                    propertyImageEntity.setUploadDate(DateUtils.getDate());
                    propertyImageEntity.setImgFkId(id);
                    propertyImageEntity.setImagePath(propertyImage.getImageUrl());
                    propertyImageEntity.setState("0");//0代表有效;1代表无效
                    if(workOrderDetailDTO.getType().equals("0")){
                        propertyImageEntity.setImageType("0");//0为报修
                    }else if(workOrderDetailDTO.getType().equals("1")){
                        propertyImageEntity.setImageType("1");//1为投诉
                    }
                    propertyImageEntity.setUploadName(propertyImage.getImageUrl());
                    propertyImageRepository.saveImage(propertyImageEntity);
                }
            }
            PropertyRepairCRMEntity propertyRepairCRMEntity = new PropertyRepairCRMEntity();
            propertyRepairCRMEntity.setRepairId(propertyRepairEntity.getRepairId());
            propertyRepairCRMEntity.setRoomId(house.getRoomId());
            propertyRepairCRMEntity.setRoomNum(house.getRoomNum());
            propertyRepairCRMEntity.setMemberId(house.getMemberId());
            propertyRepairCRMEntity.setUserName(propertyRepairEntity.getUserName());
            propertyRepairCRMEntity.setUserPhone(propertyRepairEntity.getUserPhone());
            propertyRepairCRMEntity.setUserAddress(propertyRepairEntity.getUserAddress());
            propertyRepairCRMEntity.setCreateDate(propertyRepairEntity.getCreateDate());
            propertyRepairCRMEntity.setAppId(propertyRepairEntity.getRepairId()+"H");//增加appId
            //propertyRepairCRMEntity.setMode("维修");
            //propertyRepairCRMEntity.setRoomLocation("4A65497C-18FB-4A3F-9D71-0D00F377B87A");//house.getRoomId()
            propertyRepairCRMEntity.setState("create");
            propertyRepairCRMEntity.setRepairWay("APP");
            propertyRepairCRMEntity.setContent(propertyRepairEntity.getContent());
            propertyRepairCRMRepository.create(propertyRepairCRMEntity);
            //调用webService接口,向CRM传送数据
            repairClientService.getPropertyRepair(propertyRepairCRMEntity, null);
            propertyRepairRepository.saveRepair(propertyRepairEntity);
            propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
            //调用单据统计
            String dateNow=DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
//            InvoicesTotalEntity invoices=invoicesTotalRepository.getTotalInfo(dateNow,propertyRepairEntity.getRegionId());
            InvoicesTotalEntity invoices=invoicesTotalRepository.getTotalInfo(dateNow,"");
            //如果存在，更新报修数量
            if(invoices!=null){
                invoices.setRepairNum(invoices.getRepairNum() + 1);
                invoicesTotalRepository.update(invoices);
            }else{//没有则创建
                InvoicesTotalEntity invoicesTotal=new InvoicesTotalEntity();
                invoicesTotal.setId(IdGen.uuid());
                invoicesTotal.setCreateDate(new Date());
                invoicesTotal.setRepairNum(1);
                invoicesTotal.setFeedbackTotal(0);
                invoicesTotal.setFeedbackNum(0);
                invoicesTotal.setPaymentNum(0);
                invoicesTotal.setVisitorNum(0);
                //invoicesTotal.setCity(house.getCityId());
                //invoicesTotal.setProject(propertyRepairEntity.getRegionId());
                invoicesTotalRepository.create(invoicesTotal);
            }

            //增加日志
            UserDocumentsEntity userDocumentsLog=new UserDocumentsEntity();
            userDocumentsLog.setLogId(IdGen.uuid());
            userDocumentsLog.setLogTime(new Date());//注册日期
            userDocumentsLog.setUserName(user.getNickName());//用户昵称
            userDocumentsLog.setUserType(user.getUserType());//用户类型
            userDocumentsLog.setUserMobile(user.getMobile());//手机号
            userDocumentsLog.setSourceFrom(user.getSourceType());//来源
            userDocumentsLog.setThisType("物业报修");//单据类型
            userDocumentsLog.setDocNum(id);//单据编号
            userDocumentsLog.setLogContent(workOrderDetailDTO.getContent());//单据内容
            UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(user.getUserId());
            HouseInfoEntity houseInfoEntity=houseInfoRepository.getHouseByMemberId(user.getId());
            if (null != userSettingEntity) {
                userDocumentsLog.setProjectId(userSettingEntity.getProjectName());
            } else{
                userDocumentsLog.setProjectId("");//项目
            }
            if(null!=houseInfoEntity){
                userDocumentsLog.setRealEstate(houseInfoEntity.getAddress());
            }
            systemLogRepository.addUserDocumentsLog(userDocumentsLog);

            //发送消息提醒，根据数据范围以及提醒模块获取人员
            List<SMSPeopleAlertsEntity> smsPeopleAlertsEntityList = smsAlertsService.getAllByModel(house.getProjectNum(), "房屋报修管理");
            for (SMSPeopleAlertsEntity smsPeopleAlertsEntity : smsPeopleAlertsEntityList) {
                smsPeopleAlertsEntity.setContent(smsPeopleAlertsEntity.getContent()+" ,报修单号:"+propertyRepairEntity.getRepairId()+" ,报修人:" +propertyRepairEntity.getUserName()+" ,报修人联系方式:" + propertyRepairEntity.getUserPhone()+"。");
                //给每一个人发送短信
                smsAuthService.sendSMSAlerts(smsPeopleAlertsEntity);
            }

            return new SuccessApiResult(SuccessResource.getResource("tip_ps00000001"), "物业报修成功！");//添加成功
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 微信添加物业报修(维修和投诉)
     * @param user
     * @param workOrderDetailDTO
     * @return
     */
    @Override
    public ApiResult createWeChatRepairOrder(UserInfoEntity user,WorkOrderDetailDTO workOrderDetailDTO) throws GeneralException{
        if (workOrderDetailDTO == null) {
            return ErrorResource.getError("tip_00000040");//报修信息为空
        }
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(user.getUserId())){
            return ErrorResource.getError("tip_00000486");//用户信息为空
        }
        if(StringUtil.isEmpty(workOrderDetailDTO.getContent())){
            return ErrorResource.getError("tip_00000039");//报修内容为空
        }
        /*if(StringUtil.isEmpty(workOrderDetailDTO.getUserAddress())){
            return ErrorResource.getError("tip_pe00000010");//业主地址为空
        }*/
        if(StringUtil.isEmpty(workOrderDetailDTO.getUserName())){
            return ErrorResource.getError("tip_pe00000011");//业主姓名为空
        }
        if(StringUtil.isEmpty(workOrderDetailDTO.getUserPhone())){
            return ErrorResource.getError("tip_pe00000012");//业主电话为空
        }
        if(workOrderDetailDTO.getUserPhone().length()<11){
            return ErrorResource.getError("tip_pe00000032");//电话位数低于11位
        }
        if(workOrderDetailDTO.getUserPhone().length()>12){
            return ErrorResource.getError("tip_pe00000033");//电话位数打于12位
        }
        //正则表达式判断0-9纯数字
        boolean isNum =workOrderDetailDTO.getUserPhone().matches("[0-9]+");
        if(isNum==false){
            return ErrorResource.getError("tip_pe00000034");//电话不是纯数字
        }
        if(StringUtil.isEmpty(workOrderDetailDTO.getType())){
            return ErrorResource.getError("tip_pe00000013");//报修类型为空
        }
        if(StringUtil.isEmpty(workOrderDetailDTO.getRoomId())){
            return ErrorResource.getError("tip_pe00000030");//房屋id为空
        }
        try{
            //添加报修单
            PropertyRepairEntity propertyRepairEntity=new PropertyRepairEntity();
//            String id=IdGen.getDateId();
            HouseInfoEntity house=houseInfoRepository.get(workOrderDetailDTO.getRoomId());
            String []ss=house.getProjectNum().split("-");
            if(house==null) {
                return ErrorResource.getError("tip_pe00000031");//房屋信息为空
            }
            String repair=houseInfoRepository.getRepairidNew(ss[ss.length-1]+"-B");
            if(repair.length()==1){
                repair="000"+repair;
            }
            if(repair.length()==2){
                repair="00"+repair;
            }
            if(repair.length()==3){
                repair="0"+repair;
            }

            String id=ss[ss.length-1]+"-B-"+DateUtils.getNow("yyyyMMdd")+"-A-"+repair;
            System.out.println(id+"!~~~~~~~~~~idbianmaxian !!!!!!!!!!!!!!!!!!!~~~~~~~~~");
            propertyRepairEntity.setRepairId(id);//报修单id
            propertyRepairEntity.setContent(workOrderDetailDTO.getContent());//用户填写的报修内容
            propertyRepairEntity.setCreateBy(user.getUserId());//创建人
            propertyRepairEntity.setCreateDate(DateUtils.getDate());//创建时间
            propertyRepairEntity.setTypes("0");//0代表未完成；1代表已完成
            propertyRepairEntity.setState("已创建");//报修单状态
//            HouseInfoEntity house=houseInfoRepository.get(workOrderDetailDTO.getRoomId());//  移动到上面
//            if(house==null) {
//                return ErrorResource.getError("tip_pe00000031");//房屋信息为空
//            }
            propertyRepairEntity.setRoomId(house.getRoomId());//房间id
            propertyRepairEntity.setUserAddress(house.getAddress());//业主地址
            //获取业主的所在项目
            propertyRepairEntity.setRegionId(house.getProjectId());//项目id
            propertyRepairEntity.setRegionName(house.getProjectName());//项目名称
            propertyRepairEntity.setUserName(workOrderDetailDTO.getUserName());//业主姓名
            propertyRepairEntity.setUserPhone(workOrderDetailDTO.getUserPhone());//业主电话
            //添加任务
            PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
            propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
            propertyRepairTaskEntity.setRepairId(id);//报修单id
            propertyRepairTaskEntity.setTaskNode("用户提交");//任务节点名称
            propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
            propertyRepairTaskEntity.setReadStatus("0");
            propertyRepairEntity.setRepairWay("微信");
            if(workOrderDetailDTO.getType().equals("0")){
                propertyRepairEntity.setTaskStatus("0");//0为用户提交报修(业主)
                propertyRepairTaskEntity.setTaskName("已提交");
                propertyRepairTaskEntity.setTaskStatus("0");//0为用户提交报修(业主)
                propertyRepairTaskEntity.setTaskContent("您的报修信息已成功提交。");//任务内容
                propertyRepairEntity.setMemo("报修");
            }else if(workOrderDetailDTO.getType().equals("1")){
                propertyRepairEntity.setTaskStatus("1");//0为用户提交投诉(业主)
                propertyRepairTaskEntity.setTaskName("已提交");
                propertyRepairTaskEntity.setTaskStatus("1");//0为用户提交投诉(业主)
                propertyRepairTaskEntity.setTaskContent("您的投诉信息已成功提交。");//任务内容
                propertyRepairEntity.setMemo("投诉");
            }
            //添加报修图片
            if(workOrderDetailDTO.getImageServiceList().size()>0){
                for(PropertyServicesDTO image:workOrderDetailDTO.getImageServiceList()){
                    String path=WxImage.wechatToServer(image.getServicesId(), ImgType.REPAIRS);
                    PropertyImageEntity propertyImageEntity=new PropertyImageEntity();
                    propertyImageEntity.setImageId(IdGen.uuid());
                    propertyImageEntity.setUploadDate(DateUtils.getDate());
                    propertyImageEntity.setImgFkId(id);
                    propertyImageEntity.setImagePath(path);
                    propertyImageEntity.setState("0");//0代表有效;1代表无效
                    if(workOrderDetailDTO.getType().equals("0")){
                        propertyImageEntity.setImageType("0");//0为报修
                    }else if(workOrderDetailDTO.getType().equals("1")){
                        propertyImageEntity.setImageType("1");//1为投诉
                    }
                    propertyImageEntity.setUploadName(path);
                    propertyImageRepository.saveImage(propertyImageEntity);
                    //System.out.println("图片路径："+path);
                }
            }
            PropertyRepairCRMEntity propertyRepairCRMEntity = new PropertyRepairCRMEntity();
            propertyRepairCRMEntity.setRepairId(propertyRepairEntity.getRepairId());
            propertyRepairCRMEntity.setRoomId(house.getRoomId());
            propertyRepairCRMEntity.setRoomNum(house.getRoomNum());
            propertyRepairCRMEntity.setMemberId(house.getMemberId());
            propertyRepairCRMEntity.setUserName(propertyRepairEntity.getUserName());
            propertyRepairCRMEntity.setUserPhone(propertyRepairEntity.getUserPhone());
            propertyRepairCRMEntity.setUserAddress(propertyRepairEntity.getUserAddress());
            propertyRepairCRMEntity.setCreateDate(propertyRepairEntity.getCreateDate());
            propertyRepairCRMEntity.setAppId(propertyRepairEntity.getRepairId()+"W");
            //propertyRepairCRMEntity.setMode("维修");
            //propertyRepairCRMEntity.setRoomLocation("4A65497C-18FB-4A3F-9D71-0D00F377B87A");//house.getRoomId()
            propertyRepairCRMEntity.setState("create");
            propertyRepairCRMEntity.setRepairWay("wechat");
            propertyRepairCRMEntity.setContent(propertyRepairEntity.getContent());
            propertyRepairCRMRepository.create(propertyRepairCRMEntity);
            //调用webService接口,向CRM传送数据
            repairClientService.getPropertyRepair(propertyRepairCRMEntity, null);
            propertyRepairRepository.saveRepair(propertyRepairEntity);
            propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);

            //发送消息提醒，根据数据范围以及提醒模块获取人员
            List<SMSPeopleAlertsEntity> smsPeopleAlertsEntityList = smsAlertsService.getAllByModel(house.getProjectNum(), "房屋报修管理");
            for (SMSPeopleAlertsEntity smsPeopleAlertsEntity : smsPeopleAlertsEntityList) {
                smsPeopleAlertsEntity.setContent(smsPeopleAlertsEntity.getContent()+" ,报修单号:"+propertyRepairEntity.getRepairId()+" ,报修人:" +propertyRepairEntity.getUserName()+" ,报修人联系方式:" + propertyRepairEntity.getUserPhone()+"。");
                //给每一个人发送短信
                smsAuthService.sendSMSAlerts(smsPeopleAlertsEntity);
            }

            //调用单据统计
            String dateNow=DateUtils.format(new Date(), DateUtils.FORMAT_SHORT);
//            InvoicesTotalEntity invoices=invoicesTotalRepository.getTotalInfo(dateNow,propertyRepairEntity.getRegionId());
            InvoicesTotalEntity invoices=invoicesTotalRepository.getTotalInfo(dateNow,"");
            //如果存在，更新报修数量
            if(invoices!=null){
                invoices.setRepairNum(invoices.getRepairNum()+1);
                invoicesTotalRepository.update(invoices);
            }else{//没有则创建
                InvoicesTotalEntity invoicesTotal=new InvoicesTotalEntity();
                invoicesTotal.setId(IdGen.uuid());
                invoicesTotal.setCreateDate(new Date());
                invoicesTotal.setRepairNum(1);
                invoicesTotal.setFeedbackTotal(0);
                invoicesTotal.setFeedbackNum(0);
                invoicesTotal.setPaymentNum(0);
                invoicesTotal.setVisitorNum(0);
                //invoicesTotal.setCity(house.getCityId());
                //invoicesTotal.setProject(propertyRepairEntity.getRegionId());
                invoicesTotalRepository.create(invoicesTotal);
            }

            //增加日志
            UserDocumentsEntity userDocumentsLog=new UserDocumentsEntity();
            userDocumentsLog.setLogId(IdGen.uuid());
            userDocumentsLog.setLogTime(new Date());//注册日期
            userDocumentsLog.setUserName(user.getNickName());//用户昵称
            userDocumentsLog.setUserType(user.getUserType());//用户类型
            userDocumentsLog.setUserMobile(user.getMobile());//手机号
            userDocumentsLog.setSourceFrom("2");//来源:1-App,2-微信   微信绑定app账户，用户的sourceType不变，但这里日志来源要显示为微信
            userDocumentsLog.setThisType("物业报修");//单据类型
            userDocumentsLog.setDocNum(id);//单据编号
            userDocumentsLog.setLogContent(workOrderDetailDTO.getContent());//单据内容
            UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(user.getUserId());
            HouseInfoEntity houseInfoEntity=houseInfoRepository.getHouseByMemberId(user.getId());
            if (null != userSettingEntity) {
                userDocumentsLog.setProjectId(userSettingEntity.getProjectName());
            } else{
                if(null!=houseInfoEntity){
                    userDocumentsLog.setProjectId(houseInfoEntity.getProjectNum());//项目
                    userDocumentsLog.setRealEstate(houseInfoEntity.getAddress());
                }
            }
            systemLogRepository.addUserDocumentsLog(userDocumentsLog);
            return new SuccessApiResult(SuccessResource.getResource("tip_ps00000001"), null);//添加成功
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 获取业主信息
     * @param user
     * @return
     */
    @Override
    public ApiResult getOwnerInfo(UserInfoEntity user) throws GeneralException{
        if (user == null) {
            return ErrorResource.getError("tip_00000484");
        }
        if(StringUtil.isEmpty(user.getUserId())){
            return ErrorResource.getError("tip_00000486");
        }
        try{
            PropertyOwnerDTO ownerDTO=new PropertyOwnerDTO();
            List<PropertyAddressDTO> addressDTOs=new ArrayList<PropertyAddressDTO>();
            ownerDTO.setUserName(user.getRealName());//用户名称
            ownerDTO.setUserPhone(user.getMobile());//用户电话
            //获取房主关系
            /*List<HouseUserBookEntity> houseUserList=houseUserBookRepository.getListByUserId(user.getUserId());
            if(houseUserList.size()>0){
                for(HouseUserBookEntity houseUser:houseUserList){
                    //获取地址信息
                    HouseInfoEntity houseInfoEntity=houseInfoRepository.get(houseUser.getHouseId());
                    PropertyAddressDTO address=new PropertyAddressDTO();
                    address.setAddress(houseInfoEntity.getAddress());//用户地址
                    address.setHouseInfoId(houseInfoEntity.getId());//业主房屋ID
                    addressDTOs.add(address);//地址集合
                }
            }*/
            //获取业主的所在项目
            UserSettingEntity userSetting=userSettingRepository.get(user.getUserId());
            if(userSetting!=null && userSetting.getProjectId()!=null && !"".equals(userSetting.getProjectId())){
                //获取地址信息
                List<HouseInfoEntity> houseInfo=houseInfoRepository.getListByUserIdAndProjectId(user.getUserId(), userSetting.getProjectId());
                if(houseInfo.size()>0){
                    for(HouseInfoEntity house:houseInfo){
                        PropertyAddressDTO address=new PropertyAddressDTO();
                        address.setAddress(house.getAddress());//用户地址
                        address.setHouseInfoId(house.getId());//业主房屋ID
                        addressDTOs.add(address);//地址集合
                    }
                }
            }
            ownerDTO.setAddressList(addressDTOs);
            return new SuccessApiResult(ownerDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * 微信获取业主信息
     * @param user
     * @return
     */
    @Override
    public ApiResult getWeChatOwnerInfo(UserInfoEntity user) throws GeneralException {
        if (user == null) {
            return ErrorResource.getError("tip_00000484");
        }
        if(StringUtil.isEmpty(user.getUserId())){
            return ErrorResource.getError("tip_00000486");
        }
        try{
            List<PropertyAddressDTO> addressDTOs=new ArrayList<PropertyAddressDTO>();
            //获取业主/同住人的所在项目
            List<Object[]> houseInfo;
            if(user.getUserType().equals("3") || user.getUserType().equals("6")) {//业主
                houseInfo = houseInfoRepository.getAllHouse(user.getId());
                if (houseInfo.size() > 0) {
                    for (Object[] house : houseInfo) {
                        PropertyAddressDTO address = new PropertyAddressDTO();
                        address.setHouseInfoId((String) house[0]);//业主房屋ID
                        address.setAddress((String) house[1]);//用户地址
                        addressDTOs.add(address);//地址集合
                    }
                }
            }else if(user.getUserType().equals("4")){//同住人
                houseInfo=houseInfoRepository.getHousemateHouse(user.getUserId());
                if (houseInfo.size() > 0) {
                    for (Object[] house : houseInfo) {
                        PropertyAddressDTO address = new PropertyAddressDTO();
                        address.setHouseInfoId((String) house[0]);//业主房屋ID
                        address.setAddress((String) house[1]);//用户地址
                        addressDTOs.add(address);//地址集合
                    }
                }
            }

            UserVisitLogEntity userVisitLogEntity=new UserVisitLogEntity();
            userVisitLogEntity.setLogId(IdGen.uuid());
            userVisitLogEntity.setLogTime(new Date());//注册日期
            userVisitLogEntity.setUserName(user.getNickName());//用户昵称
            userVisitLogEntity.setUserType(user.getUserType());//用户类型
            userVisitLogEntity.setUserMobile(user.getMobile());//手机号
            userVisitLogEntity.setSourceFrom(user.getSourceType());//来源
            userVisitLogEntity.setThisSection("服务");
            userVisitLogEntity.setThisFunction("物业报修");
            userVisitLogEntity.setLogContent("");
            if(user!=null){
                UserSettingEntity userSettingEntity = userSettingService.getUserSettingEntityByUserId(user.getUserId());
                if (null != userSettingEntity) {
                    userVisitLogEntity.setProjectId(userSettingEntity.getProjectName());
                }
            }else{
                userVisitLogEntity.setProjectId("");//项目
            }
            systemLogRepository.addUserVisitLog(userVisitLogEntity);
            return new SuccessApiResult(addressDTOs);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 获取报修价格
     * @param projectId
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult repairPrice(String projectId) throws GeneralException {
        if(StringUtil.isEmpty(projectId)){
            return ErrorResource.getError("tip_pe00000009");//业主项目为空
        }
        try{
            // 查询项目数据
            PropertyPriceSettingEntity propertyPrice= propertyPriceSettingRepository.queryPriceSetting(projectId);
            //价格内容
            PropertyOwnerDTO propertyOwnerDTO=new PropertyOwnerDTO();
            if(propertyPrice!=null){
                propertyOwnerDTO.setPriceContent(propertyPrice.getPriceSettingCount());
            }
            return new SuccessApiResult(propertyOwnerDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 获取历史报修详情信息
     * @param id：报修单id
     * @return
     */
    @Override
    public ApiResult getRepairDetail(String id) throws GeneralException{
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        try{
            PropertyRepairDTO propertyRepair = new PropertyRepairDTO();
            List<PropertyImageDTO> imageDTOs = new ArrayList<PropertyImageDTO>();
            List<PropertyImageDTO> imageDTO=new ArrayList<PropertyImageDTO>();
            //查询报修所需要的详情信息
            PropertyRepairEntity propertyRepairEntity = propertyRepairRepository.getRepairDetail(id);
            if (propertyRepairEntity!=null){
                propertyRepair.setId(propertyRepairEntity.getRepairId());
                propertyRepair.setContent(propertyRepairEntity.getContent());
            }
            //查询评价信息
            PropertyFeedbackEntity propertyFeedback=propertyFeedbackRepository.getFeedback(propertyRepairEntity.getRepairId());
            if (propertyFeedback !=null){
                propertyRepair.setGrade(propertyFeedback.getGrade());
                propertyRepair.setEvaluateContent(propertyFeedback.getContent());
            }
            //获取报修类型
            List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairEntity.getRepairId());
            if(repairTaskEntities.size()>0) {
                if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {//报修
                    //查询图片集合(创建报修)
                    List<PropertyImageEntity> imageEntity = propertyImageRepository.getImageUrl(propertyRepairEntity.getRepairId());
                    if (imageEntity.size() > 0) {
                        for (PropertyImageEntity images : imageEntity) {
                            PropertyImageDTO propertyImageDTO = new PropertyImageDTO();
                            propertyImageDTO.setImageUrl(images.getImagePath());
                            propertyImageDTO.setSrc(images.getImagePath());
                            imageDTOs.add(propertyImageDTO);
                        }
                    }
                }else if(repairTaskEntities.get(0).getTaskStatus().equals("1")){//投诉
                    //查询图片集合(创建投诉)
                    List<PropertyImageEntity> imageEntity = propertyImageRepository.getComplaintImage(propertyRepairEntity.getRepairId());
                    if (imageEntity.size() > 0) {
                        for (PropertyImageEntity images : imageEntity) {
                            PropertyImageDTO propertyImageDTO = new PropertyImageDTO();
                            propertyImageDTO.setImageUrl(images.getImagePath());
                            propertyImageDTO.setSrc(images.getImagePath());
                            imageDTOs.add(propertyImageDTO);
                        }
                    }
                }
            }
            //查询图片集合(维修完成)
            List<PropertyImageEntity> imageEntities=propertyImageRepository.getImagedUrl(propertyRepairEntity.getRepairId());
            if(imageEntities.size()>0) {
                for (PropertyImageEntity images : imageEntities) {
                    PropertyImageDTO propertyImageDTO = new PropertyImageDTO();
                    propertyImageDTO.setImageUrl(images.getImagePath());
                    propertyImageDTO.setSrc(images.getImagePath());
                    imageDTO.add(propertyImageDTO);
                }
            }
            propertyRepair.setImageList(imageDTOs);
            propertyRepair.setImagedList(imageDTO);
            return new SuccessApiResult(propertyRepair);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**-------------------------------------业主端：投诉接口--------------------------------------------------*/
    /**
     * 投诉列表
     * @param user
     * @param page
     * @return
     */
    @Override
    public ApiResult getComplaint(UserInfoEntity user, Page page) {
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        try{
            List<WorkOrderInfoDTO> workOrderInfoDTO=new ArrayList<WorkOrderInfoDTO>();
            //获取投诉列表
            List<PropertyRepairEntity> repairList=propertyRepairRepository.getComplaint(user,page);
            if(repairList.size()>0){
                for(PropertyRepairEntity repair:repairList){
                    WorkOrderInfoDTO workOrderInfo=new WorkOrderInfoDTO();
                    workOrderInfo.setId(repair.getRepairId());//报修单id
                    workOrderInfo.setOwner("投诉人：" + repair.getUserName());//业主姓名
                    workOrderInfo.setContent(repair.getContent());//报修内容
                    workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
                    workOrderInfo.setStatus(repair.getState());
                    List<PropertyImageDTO> imageDTOs=new ArrayList<PropertyImageDTO>();
                    //查询图片集合
                    List<PropertyImageEntity> imageEntity=propertyImageRepository.getComplaintImage(repair.getRepairId());
                    if(imageEntity.size()>0) {
                        for (PropertyImageEntity images : imageEntity) {
                            PropertyImageDTO propertyImageDTO = new PropertyImageDTO();
                            propertyImageDTO.setImageUrl(images.getImagePath());
                            imageDTOs.add(propertyImageDTO);
                        }
                        workOrderInfo.setImageList(imageDTOs);
                    }
                    workOrderInfoDTO.add(workOrderInfo);
                }
            }
            return new SuccessApiResult(workOrderInfoDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }
    /**-------------------------------------员工端：工单管理接口----------------------------------------------*/
    /**
     * 获取工单列表(维修和投诉)
     * @param type：0为维修待分配；1为维修进行中；2为投诉待分配；3为投诉进行中
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult getWorkOrders(String type,UserPropertyStaffEntity user,Page page) throws GeneralException {
        if(StringUtil.isEmpty(type)){
            return ErrorResource.getError("tip_00000055");//参数不能为空
        }
        try{
            List<WorkOrderInfoDTO> workOrderInfoDTO=new ArrayList<WorkOrderInfoDTO>();
            if(type.equals("0")){
                //查询待分配的维修工单(维修人员/维修主管)
                List<PropertyRepairEntity> repairList=propertyRepairRepository.getRepairPending(user.getProjectId(),page);
                if(repairList.size()>0){
                    for(PropertyRepairEntity repair:repairList){
                        WorkOrderInfoDTO workOrderInfo=new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " + repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("1")){
                //查询进行中的维修工单(维修主管)
                List<PropertyRepairEntity> repairList = propertyRepairRepository.getRepairUnderway(user.getProjectId(),page);
                if (repairList.size()> 0) {
                    for (PropertyRepairEntity repair : repairList) {
                        WorkOrderInfoDTO workOrderInfo = new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " +repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("2")){
                //查询自己进行中的维修工单(维修人员)
                List<PropertyRepairEntity> repairList = propertyRepairRepository.getMyRepairUnderway(user,page);
                if (repairList.size()> 0) {
                    for (PropertyRepairEntity repair : repairList) {
                        WorkOrderInfoDTO workOrderInfo = new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " +repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("3")){
                //查询待分配的投诉工单(客服主管)
                List<Object[]> repairList=propertyRepairRepository.getComplaintPending(user.getProjectId(),page);
                if(repairList.size()>0){
                    for(Object[] repair:repairList){
                        WorkOrderInfoDTO workOrderInfo=new WorkOrderInfoDTO();
                        workOrderInfo.setId((String) repair[0]);//报修单id
                        workOrderInfo.setOwner(repair[1] + " " +repair[2]);//业主姓名+电话
                        workOrderInfo.setContent((String)repair[3]);//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format((Date) repair[4], "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress((String)repair[5]);
                        workOrderInfo.setStatus((String)repair[6]);
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("4")){
                //查询进行中的投诉工单(客服主管)
                List<PropertyRepairEntity> repairList=propertyRepairRepository.getComplaintUnderway(user.getProjectId(),page);
                if(repairList.size()>0){
                    for(PropertyRepairEntity repair:repairList){
                        WorkOrderInfoDTO workOrderInfo=new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " +repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("5")){
                //查询自己进行中的投诉工单(客服人员)
                List<PropertyRepairEntity> repairList=propertyRepairRepository.getMyComplaintUnderway(user, page);
                if(repairList.size()>0){
                    for(PropertyRepairEntity repair:repairList){
                        WorkOrderInfoDTO workOrderInfo=new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " + repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("6")){
                //查询待分配的秩序工单(秩序主管)
                List<PropertyRepairEntity> repairList=propertyRepairRepository.getSequencePending(user.getProjectId(), page);
                if(repairList.size()>0) {
                    for (PropertyRepairEntity repair : repairList) {
                        WorkOrderInfoDTO workOrderInfo = new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " + repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("7")){
                //查询进行中的秩序工单(秩序主管)
                List<PropertyRepairEntity> repairList=propertyRepairRepository.getSequenceUnderway(user.getProjectId(), page);
                if(repairList.size()>0) {
                    for (PropertyRepairEntity repair : repairList) {
                        WorkOrderInfoDTO workOrderInfo = new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " + repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("8")){
                //查询自己进行中的秩序工单(秩序人员)
                List<PropertyRepairEntity> repairList=propertyRepairRepository.getMySequenceUnderway(user, page);
                if(repairList.size()>0) {
                    for (PropertyRepairEntity repair : repairList) {
                        WorkOrderInfoDTO workOrderInfo = new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " + repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("9")){
                //查询待分配的环境工单(环境主管)
                List<PropertyRepairEntity> repairList=propertyRepairRepository.getEnvironmentPending(user.getProjectId(), page);
                if(repairList.size()>0) {
                    for (PropertyRepairEntity repair : repairList) {
                        WorkOrderInfoDTO workOrderInfo = new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " + repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("10")){
                //查询进行中的环境工单(环境主管)
                List<PropertyRepairEntity> repairList=propertyRepairRepository.getEnvironmentUnderway(user.getProjectId(), page);
                if(repairList.size()>0) {
                    for (PropertyRepairEntity repair : repairList) {
                        WorkOrderInfoDTO workOrderInfo = new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " + repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("11")){
                //查询自己进行中的环境工单(环境人员)
                List<PropertyRepairEntity> repairList=propertyRepairRepository.getMyEnvironmentUnderway(user, page);
                if(repairList.size()>0) {
                    for (PropertyRepairEntity repair : repairList) {
                        WorkOrderInfoDTO workOrderInfo = new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " + repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("12")){
                //查询待分配的所有工单(管理员)
                List<PropertyRepairEntity> repairList=propertyRepairRepository.getAllPending(user.getProjectId(), page);
                if(repairList.size()>0) {
                    for (PropertyRepairEntity repair : repairList) {
                        WorkOrderInfoDTO workOrderInfo = new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " + repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("13")) {
                //查询进行中的所有工单(管理员)
                List<PropertyRepairEntity> repairList = propertyRepairRepository.getAllUnderway(user.getProjectId(), page);
                if (repairList.size() > 0) {
                    for (PropertyRepairEntity repair : repairList) {
                        WorkOrderInfoDTO workOrderInfo = new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " + repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }
            return new SuccessApiResult(workOrderInfoDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 获取工单已完成列表(维修和投诉)
     * @param type：0为维修已完成；1为投诉已完成
     * @param page
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult getRepairsComplete(String type,UserPropertyStaffEntity user,Page page) throws GeneralException {
        if(StringUtil.isEmpty(type)){
            return ErrorResource.getError("tip_00000055");//参数不能为空
        }
        try{
            List<WorkOrderInfoDTO> workOrderInfoDTO=new ArrayList<WorkOrderInfoDTO>();
            if(type.equals("0")) {
                //查询已完成的维修工单(维修主管)
                List<PropertyRepairEntity> repairList = propertyRepairRepository.getRepairComplete(user.getProjectId(),page);
                if (repairList.size() > 0) {
                    for (PropertyRepairEntity repair : repairList) {
                        WorkOrderInfoDTO workOrderInfo = new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " + repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("1")){
                //查询自己已完成的维修工单(维修人员)
                List<PropertyRepairEntity> repairList = propertyRepairRepository.getMyRepairComplete(user,page);
                if (repairList.size() > 0) {
                    for (PropertyRepairEntity repair : repairList) {
                        WorkOrderInfoDTO workOrderInfo = new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " + repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("2")){
                //查询已完成的投诉工单(客服主管)
                List<PropertyRepairEntity> repairList = propertyRepairRepository.getComplaintComplete(user.getProjectId(),page);
                if (repairList.size() > 0) {
                    for (PropertyRepairEntity repair : repairList) {
                        WorkOrderInfoDTO workOrderInfo = new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " + repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("3")){
                //查询自己已完成的投诉工单(客服人员)
                List<PropertyRepairEntity> repairList = propertyRepairRepository.getMyComplaintComplete(user,page);
                if (repairList.size() > 0) {
                    for (PropertyRepairEntity repair : repairList) {
                        WorkOrderInfoDTO workOrderInfo = new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " + repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("4")){
                //查询已完成的秩序工单(秩序主管)
                List<PropertyRepairEntity> repairList = propertyRepairRepository.getSequenceComplete(user.getProjectId(), page);
                if (repairList.size() > 0) {
                    for (PropertyRepairEntity repair : repairList) {
                        WorkOrderInfoDTO workOrderInfo = new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " + repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("5")){
                //查询自己已完成的秩序工单(秩序人员)
                List<PropertyRepairEntity> repairList = propertyRepairRepository.getMySequenceComplete(user, page);
                if (repairList.size() > 0) {
                    for (PropertyRepairEntity repair : repairList) {
                        WorkOrderInfoDTO workOrderInfo = new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " + repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("6")){
                //查询已完成的环境工单(环境主管)
                List<PropertyRepairEntity> repairList = propertyRepairRepository.getEnvironmentComplete(user.getProjectId(), page);
                if (repairList.size() > 0) {
                    for (PropertyRepairEntity repair : repairList) {
                        WorkOrderInfoDTO workOrderInfo = new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " + repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("7")){
                //查询已完成的环境工单(环境人员)
                List<PropertyRepairEntity> repairList = propertyRepairRepository.getMyEnvironmentComplete(user,page);
                if (repairList.size() > 0) {
                    for (PropertyRepairEntity repair : repairList) {
                        WorkOrderInfoDTO workOrderInfo = new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " + repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }else if(type.equals("8")){
                //查询已完成的所有工单(管理员)
                List<PropertyRepairEntity> repairList = propertyRepairRepository.getAllComplete(user.getProjectId(),page);
                if (repairList.size() > 0) {
                    for (PropertyRepairEntity repair : repairList) {
                        WorkOrderInfoDTO workOrderInfo = new WorkOrderInfoDTO();
                        workOrderInfo.setId(repair.getRepairId());//报修单id
                        workOrderInfo.setOwner(repair.getUserName() + " " + repair.getUserPhone());//业主姓名+电话
                        workOrderInfo.setContent(repair.getContent());//报修内容
                        workOrderInfo.setCreateDate(DateUtils.format(repair.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        workOrderInfo.setAddress(repair.getUserAddress());
                        workOrderInfo.setStatus(repair.getTaskStatus());
                        workOrderInfoDTO.add(workOrderInfo);
                    }
                }
            }
            return new SuccessApiResult(workOrderInfoDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 获取工单详情信息
     * @param id
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult getRepairsInfo(String id) throws GeneralException {
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        try{
            WorkOrderDetailDTO workOrderDetail=new WorkOrderDetailDTO();
            List<PropertyImageDTO> imageDTOs=new ArrayList<PropertyImageDTO>();
            List<PropertyImageDTO> imageDTO=new ArrayList<PropertyImageDTO>();
            //查询报修所需要的详情信息
            PropertyRepairEntity propertyRepairEntity=propertyRepairRepository.getRepairDetail(id);
            if (propertyRepairEntity!=null){
                workOrderDetail.setId(propertyRepairEntity.getRepairId());
                workOrderDetail.setContent(propertyRepairEntity.getContent());
                workOrderDetail.setUserAddress(propertyRepairEntity.getUserAddress());
                workOrderDetail.setUserName(propertyRepairEntity.getUserName());
                workOrderDetail.setUserPhone(propertyRepairEntity.getUserPhone());
                workOrderDetail.setStatus(propertyRepairEntity.getTaskStatus());
                workOrderDetail.setMemo(propertyRepairEntity.getMemo());
                //获取报修类型
                List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairEntity.getRepairId());
                if(repairTaskEntities.size()>0) {
                    if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {
                        //查询图片集合(创建报修单)
                        List<PropertyImageEntity> imageEntity = propertyImageRepository.getImageUrl(propertyRepairEntity.getRepairId());
                        if (imageEntity.size() > 0) {
                            for (PropertyImageEntity images : imageEntity) {
                                PropertyImageDTO propertyImageDTO = new PropertyImageDTO();
                                propertyImageDTO.setImageUrl(images.getImagePath());
                                imageDTOs.add(propertyImageDTO);
                            }
                        }
                    }else if(repairTaskEntities.get(0).getTaskStatus().equals("1")){
                        //查询图片集合(创建投诉单)
                        List<PropertyImageEntity> imageEntity = propertyImageRepository.getComplaintImage(propertyRepairEntity.getRepairId());
                        if (imageEntity.size() > 0) {
                            for (PropertyImageEntity images : imageEntity) {
                                PropertyImageDTO propertyImageDTO = new PropertyImageDTO();
                                propertyImageDTO.setImageUrl(images.getImagePath());
                                imageDTOs.add(propertyImageDTO);
                            }
                        }
                    }
                }
                //查询图片集合(维修完成)
                List<PropertyImageEntity> imageEntities=propertyImageRepository.getImagedUrl(propertyRepairEntity.getRepairId());
                if(imageEntities.size()>0) {
                    for (PropertyImageEntity images : imageEntities) {
                        PropertyImageDTO propertyImageDTO = new PropertyImageDTO();
                        propertyImageDTO.setImageUrl(images.getImagePath());
                        imageDTO.add(propertyImageDTO);
                    }
                }
                workOrderDetail.setImageList(imageDTOs);
                workOrderDetail.setImagedList(imageDTO);
            }
            return new SuccessApiResult(workOrderDetail);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**-------------------------------------员工端：随手报接口----------------------------------------------*/
    /**
     * 添加工单
     * @param user
     * @param workOrderDetailDTO
     * @return
     */
    @Override
    public ApiResult repairOrders(UserPropertyStaffEntity user, WorkOrderDetailDTO workOrderDetailDTO) throws GeneralException {
        if (workOrderDetailDTO == null) {
            return ErrorResource.getError("tip_00000040");//报修信息为空
        }
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        if(StringUtil.isEmpty(user.getStaffId())){
            return ErrorResource.getError("tip_00000486");//用户信息为空
        }
        if(StringUtil.isEmpty(workOrderDetailDTO.getContent())){
            return ErrorResource.getError("tip_00000039");//报修内容为空
        }
        if(StringUtil.isEmpty(workOrderDetailDTO.getUserAddress())){
            return ErrorResource.getError("tip_pe00000021");//部门为空
        }
        if(StringUtil.isEmpty(workOrderDetailDTO.getUserName())){
            return ErrorResource.getError("tip_pe00000011");//姓名为空
        }
        if(StringUtil.isEmpty(workOrderDetailDTO.getUserPhone())){
            return ErrorResource.getError("tip_pe00000012");//电话为空
        }
        if(StringUtil.isEmpty(workOrderDetailDTO.getType())){
            return ErrorResource.getError("tip_pe00000013");//报修类型为空
        }
        try{
            //添加报修单
            PropertyRepairEntity propertyRepairEntity=new PropertyRepairEntity();
            String id=IdGen.getDateId();
            propertyRepairEntity.setRepairId(id);//报修单id
            propertyRepairEntity.setContent(workOrderDetailDTO.getContent());//用户填写的报修内容
            propertyRepairEntity.setCreateBy(user.getStaffId());//创建人
            propertyRepairEntity.setCreateDate(DateUtils.getDate());//创建时间
            propertyRepairEntity.setTypes("0");//0代表未完成；1代表已完成
            propertyRepairEntity.setState("正在派工");//报修单状态
            propertyRepairEntity.setUserAddress(workOrderDetailDTO.getUserAddress());//部门
            propertyRepairEntity.setUserName(workOrderDetailDTO.getUserName());//姓名
            propertyRepairEntity.setUserPhone(workOrderDetailDTO.getUserPhone());//电话
            propertyRepairEntity.setRegionId(user.getProjectId());//项目id
            //获取项目名称
            HouseProjectEntity houseProject=houseProjectRepository.get(user.getProjectId());
            if(houseProject.getName()!=null) {
                propertyRepairEntity.setRegionName(houseProject.getName());//项目名称
            }

            //添加任务
            PropertyRepairTaskEntity propertyRepairTaskEntity=new PropertyRepairTaskEntity();
            propertyRepairTaskEntity.setTaskId(IdGen.uuid());//任务id
            propertyRepairTaskEntity.setRepairId(id);//报修单id
            propertyRepairTaskEntity.setTaskNode("用户提交");//任务节点名称
            propertyRepairTaskEntity.setTaskDate(DateUtils.getDate());//任务日期
            if(workOrderDetailDTO.getType().equals("1")){//随手报维修
                propertyRepairEntity.setTaskStatus("0");
                propertyRepairEntity.setMemo("维修");
                propertyRepairTaskEntity.setTaskName("提交报修");
                propertyRepairTaskEntity.setTaskStatus("0");
                propertyRepairTaskEntity.setTaskContent("您的报修信息已成功提交。");
            }else if(workOrderDetailDTO.getType().equals("2")){//随手报客服
                propertyRepairEntity.setTaskStatus("1");
                propertyRepairEntity.setMemo("客服");
                propertyRepairTaskEntity.setTaskName("提交投诉");
                propertyRepairTaskEntity.setTaskStatus("1");
                propertyRepairTaskEntity.setTaskContent("您的投诉信息已成功提交。");
            }else if(workOrderDetailDTO.getType().equals("3")){//随手报秩序
                propertyRepairEntity.setTaskStatus("1");
                propertyRepairEntity.setMemo("秩序");
                propertyRepairTaskEntity.setTaskName("提交投诉");
                propertyRepairTaskEntity.setTaskStatus("1");
                propertyRepairTaskEntity.setTaskContent("您的投诉信息已成功提交。");
            }else if(workOrderDetailDTO.getType().equals("4")){//随手报环境
                propertyRepairEntity.setTaskStatus("1");
                propertyRepairEntity.setMemo("环境");
                propertyRepairTaskEntity.setTaskName("提交投诉");
                propertyRepairTaskEntity.setTaskStatus("1");
                propertyRepairTaskEntity.setTaskContent("您的投诉信息已成功提交。");
            }
            propertyRepairRepository.saveRepair(propertyRepairEntity);
            propertyRepairTaskRepository.createRepairTask(propertyRepairTaskEntity);
            //添加报修图片
            if(workOrderDetailDTO.getImageList().size()>0){
                for(PropertyImageDTO propertyImage:workOrderDetailDTO.getImageList()){
                    PropertyImageEntity propertyImageEntity=new PropertyImageEntity();
                    propertyImageEntity.setImageId(IdGen.uuid());
                    propertyImageEntity.setUploadDate(DateUtils.getDate());
                    propertyImageEntity.setImgFkId(id);
                    propertyImageEntity.setImagePath(propertyImage.getImageUrl());
                    propertyImageEntity.setState("0");//0代表有效;1代表无效
                    if(workOrderDetailDTO.getType().equals("1")){
                        propertyImageEntity.setImageType("0");
                    }else if(workOrderDetailDTO.getType().equals("2") || workOrderDetailDTO.getType().equals("3")
                            || workOrderDetailDTO.getType().equals("4")){
                        propertyImageEntity.setImageType("1");
                    }
                    propertyImageEntity.setUploadName(propertyImage.getImageUrl());
                    propertyImageRepository.saveImage(propertyImageEntity);
                }
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_ps00000001"), null);//添加成功
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 获取员工信息
     * @param user
     * @return
     */
    @Override
    public ApiResult getEmployeeInfo(UserPropertyStaffEntity user) throws GeneralException {
        if (user == null) {
            return ErrorResource.getError("tip_00000484");
        }
        if(StringUtil.isEmpty(user.getStaffId())){
            return ErrorResource.getError("tip_00000486");
        }
        try{
            PropertyOwnerDTO ownerDTO=new PropertyOwnerDTO();
            ownerDTO.setUserName(user.getStaffName());//用户名称
            ownerDTO.setUserPhone(user.getMobile());//用户电话
            //获取部门名称
            HouseSectionEntity houseSectionEntity = houseSectionRepository.getSectionById(user.getDepartmentId());
            if (houseSectionEntity == null) {
                return ErrorResource.getError("tip_pe00000021");
            }
            ownerDTO.setUserDepartment(houseSectionEntity.getSectionName());//部门名称
            return new SuccessApiResult(ownerDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 随手报历史列表
     * @param user
     * @param page
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult getReports(UserPropertyStaffEntity user, Page page) throws GeneralException {
        if (user == null) {
            return ErrorResource.getError("tip_00000484");//用户为空
        }
        try{
            List<PropertyHistoriesDTO> historyDTOs=new ArrayList<PropertyHistoriesDTO>();
            //查询历史记录信息
            List<PropertyRepairEntity> historyList=propertyRepairRepository.getReportsHistory(user, page);
            if(historyList.size()>0){
                for(PropertyRepairEntity repair:historyList){
                    PropertyHistoriesDTO historyDTO=new PropertyHistoriesDTO();
                    historyDTO.setId(repair.getRepairId());
                    historyDTO.setContent(repair.getContent());
                    historyDTO.setStatus(repair.getState());
                    historyDTO.setMemo(repair.getMemo());
                    //获取报修类型
                    List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(repair.getRepairId());
                    if(repairTaskEntities.size()>0) {
                        if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {
                            //查询维修图片，取第一张
                            List<PropertyImageEntity> imageEntity=propertyImageRepository.getImageUrl(repair.getRepairId());//报修单id
                            if(imageEntity.size()>0) {
                                if(imageEntity.get(0).getImagePath()!=null && !"".equals(imageEntity.get(0).getImagePath())) {
                                    historyDTO.setImageUrl(imageEntity.get(0).getImagePath());
                                }else{
                                    historyDTO.setImageUrl("http://lifestatic.wanda.cn/frontimg/images/default/baoxiu_default.jpg");
                                }
                            }else{
                                historyDTO.setImageUrl("http://lifestatic.wanda.cn/frontimg/images/default/baoxiu_default.jpg");
                            }
                        }else if(repairTaskEntities.get(0).getTaskStatus().equals("1")){
                            //查询投诉图片，取第一张
                            List<PropertyImageEntity> imageEntity=propertyImageRepository.getComplaintImage(repair.getRepairId());//报修单id
                            if(imageEntity.size()>0) {
                                if(imageEntity.get(0).getImagePath()!=null && !"".equals(imageEntity.get(0).getImagePath())){
                                    historyDTO.setImageUrl(imageEntity.get(0).getImagePath());
                                }else{
                                    historyDTO.setImageUrl("http://lifestatic.wanda.cn/frontimg/images/default/baoxiu_default.jpg");
                                }
                            }else{
                                historyDTO.setImageUrl("http://lifestatic.wanda.cn/frontimg/images/default/baoxiu_default.jpg");
                            }
                        }
                    }
                    historyDTOs.add(historyDTO);
                }
            }
            return new SuccessApiResult(historyDTOs);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     * 获取随手报详情信息
     * @param id
     * @return
     */
    @Override
    public ApiResult getReportsDetail(String id) throws GeneralException {
        if(StringUtil.isEmpty(id)){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        try{
            PropertyRepairDTO propertyRepair = new PropertyRepairDTO();
            List<PropertyImageDTO> imageDTOs = new ArrayList<PropertyImageDTO>();
            List<PropertyImageDTO> imageDTO=new ArrayList<PropertyImageDTO>();
            //查询报修所需要的详情信息
            PropertyRepairEntity propertyRepairEntity = propertyRepairRepository.getRepairDetail(id);
            if (propertyRepairEntity==null){
                return ErrorResource.getError("tip_pe00000018");//报修信息不存在
            }
            propertyRepair.setId(propertyRepairEntity.getRepairId());
            propertyRepair.setContent(propertyRepairEntity.getContent());
            //查询评价信息
            PropertyFeedbackEntity propertyFeedback=propertyFeedbackRepository.getFeedback(propertyRepairEntity.getRepairId());
            if (propertyFeedback !=null){
                propertyRepair.setGrade(propertyFeedback.getGrade());
                propertyRepair.setEvaluateContent(propertyFeedback.getContent());
            }
            //获取报修类型
            List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairEntity.getRepairId());
            if(repairTaskEntities.size()>0) {
                if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {
                    //查询维修图片
                    List<PropertyImageEntity> imageEntity=propertyImageRepository.getImageUrl(propertyRepairEntity.getRepairId());//报修单id
                    if(imageEntity.size()>0) {
                        for (PropertyImageEntity images : imageEntity) {
                            PropertyImageDTO propertyImageDTO = new PropertyImageDTO();
                            propertyImageDTO.setImageUrl(images.getImagePath());
                            imageDTOs.add(propertyImageDTO);
                        }
                    }
                }else if(repairTaskEntities.get(0).getTaskStatus().equals("1")){
                    //查询投诉图片
                    List<PropertyImageEntity> imageEntity=propertyImageRepository.getComplaintImage(propertyRepairEntity.getRepairId());//报修单id
                    if(imageEntity.size()>0) {
                        for (PropertyImageEntity images : imageEntity) {
                            PropertyImageDTO propertyImageDTO = new PropertyImageDTO();
                            propertyImageDTO.setImageUrl(images.getImagePath());
                            imageDTOs.add(propertyImageDTO);
                        }
                    }
                }
            }
            //查询图片集合(维修完成)
            List<PropertyImageEntity> imageEntities=propertyImageRepository.getImagedUrl(propertyRepairEntity.getRepairId());
            if(imageEntities.size()>0) {
                for (PropertyImageEntity images : imageEntities) {
                    PropertyImageDTO propertyImageDTO = new PropertyImageDTO();
                    propertyImageDTO.setImageUrl(images.getImagePath());
                    imageDTO.add(propertyImageDTO);
                }
            }
            propertyRepair.setImageList(imageDTOs);
            propertyRepair.setImagedList(imageDTO);
            return new SuccessApiResult(propertyRepair);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**-------------------------------------管理系统端：工单管理接口----------------------------------------------*/
    /**
     * 获取报修信息列表
     * @param workOrder
     * @param webPage
     * @return
     */
    @Override
    public List<WorkOrderDetailDTO> repairInfoList(UserPropertyStaffEntity user,WorkOrderDetailDTO workOrder, WebPage webPage) {
        //检索条件
        PropertyRepairEntity repairSearch=new PropertyRepairEntity();
        if(workOrder!=null) {
//            if (!StringUtil.isEmpty(workOrder.getProject())) {
//                repairSearch.setRegionName(workOrder.getProject());//项目
//            }
            //追加区域项目检索条件-Wyd20170407
            //如果检索项目不为Null,直接set repairSearch的RegionName
            //如果检索项目为Null,城市不为Null,set repairSearch的RegionName为该城市下所有项目Code,逗号间隔
            if (null != workOrder.getProjectCode() && !"0".equals(workOrder.getProjectCode()) && !"".equals(workOrder.getProjectCode())){
                repairSearch.setRegionName("'" + workOrder.getProjectCode() + "',");
            }else if (null != workOrder.getScopeId() && !"0".equals(workOrder.getScopeId()) && !"".equals(workOrder.getScopeId())){
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(workOrder.getScopeId());
                String projectIds = "";
                for (Object[] project : projectList) {
                    projectIds += "'" + project[0].toString() + "',";
                }
                repairSearch.setRegionName(projectIds);
            }
            if (!StringUtil.isEmpty(workOrder.getRepairWay())) {
                repairSearch.setRepairWay(workOrder.getRepairWay());//来源
            }
            if (!StringUtil.isEmpty(workOrder.getId())) {
                repairSearch.setRepairId(workOrder.getId());//单号
            }
            if (!StringUtil.isEmpty(workOrder.getUserName())) {
                repairSearch.setUserName(workOrder.getUserName());//业主姓名
            }
            if (!StringUtil.isEmpty(workOrder.getUserPhone())) {
                repairSearch.setUserPhone(workOrder.getUserPhone());//业主电话
            }
            if (!StringUtil.isEmpty(workOrder.getStartDate())) {
                repairSearch.setCreateBy(workOrder.getStartDate());//开始时间(替用字段)
            }
            if (!StringUtil.isEmpty(workOrder.getEndDate())) {
                repairSearch.setModifyBy(workOrder.getEndDate());//结束时间(替用字段)
            }
            if (!StringUtil.isEmpty(workOrder.getStatus())) {
                repairSearch.setTaskStatus(workOrder.getStatus());//工单状态(替用字段)
            }
            if (!StringUtil.isEmpty(workOrder.getType())) {
                repairSearch.setContent(workOrder.getType());//工单类型(替用字段)
            }
            if (!StringUtil.isEmpty(workOrder.getCompleteStartDate())){
                repairSearch.setUserAddress(workOrder.getCompleteStartDate());//完成开始时间(替用字段)
            }
            if (!StringUtil.isEmpty(workOrder.getCompleteEndDate())){
                repairSearch.setMemo(workOrder.getCompleteEndDate());//完成结束时间(替用字段)
            }
        }
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = workOrder.getRoleScopeList();
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
        //获取报修信息
        List<PropertyRepairEntity> repairs=propertyRepairRepository.getWorkOrderList(repairSearch,webPage,roleScopes);
        //页面内容：封装到dto里
        List<WorkOrderDetailDTO> workOrderDetailDTOs=new ArrayList<WorkOrderDetailDTO>();
        if(repairs.size()>0) {
            for (PropertyRepairEntity propertyRepair : repairs) {
                WorkOrderDetailDTO workOrderDetailDTO = new WorkOrderDetailDTO();
                /* 如果项目名称和房产地址为空，则去级联查询 */
                if (null != propertyRepair.getRegionName() && !"".equals(propertyRepair.getRegionName())){
                    workOrderDetailDTO.setProject(propertyRepair.getRegionName());//小区
                    workOrderDetailDTO.setUserAddress(propertyRepair.getUserAddress());//业主地址
                }else{
                    HouseInfoEntity houseInfoEntity = houseInfoRepository.getHouseInfoByRoomId(propertyRepair.getRoomId());
                    if(null != houseInfoEntity){
                        workOrderDetailDTO.setProject(houseInfoEntity.getProjectName());
                        workOrderDetailDTO.setUserAddress(houseInfoEntity.getAddress());
                    }
                }
                /* ================================= */

                workOrderDetailDTO.setContent(propertyRepair.getContent());//报修内容
                workOrderDetailDTO.setUserName(propertyRepair.getUserName());//业主姓名
                workOrderDetailDTO.setUserPhone(propertyRepair.getUserPhone());//业主电话
                workOrderDetailDTO.setCreateDate(DateUtils.format(propertyRepair.getCreateDate(), DateUtils.FORMAT_SHORT));//报修时间
                workOrderDetailDTO.setId(propertyRepair.getRepairId());//工单号
                workOrderDetailDTO.setMemo(propertyRepair.getMemo());//类型
                workOrderDetailDTO.setRepairWay(propertyRepair.getRepairWay());//来源
                workOrderDetailDTO.setStatus(propertyRepair.getState());//工单状态
                workOrderDetailDTO.setState(propertyRepair.getTaskStatus());//任务状态
                workOrderDetailDTOs.add(workOrderDetailDTO);
            }
        }
        return workOrderDetailDTOs;
    }

    @Override
    public List<WorkOrderDetailDTO> repairList(UserPropertyStaffEntity user, WorkOrderDetailDTO workOrder, WebPage webPage) {

        Object[] obj1 = {workOrder.getProject(),workOrder.getBuildingNum(),workOrder.getBuildingFloor(),workOrder.getFirstType(),workOrder.getSecondType(),workOrder.getType(),workOrder.getState()};
        //获取报修信息
        List<Object[]> repairs=propertyRepairRepository.getRepairList(obj1, webPage);
        //页面内容：封装到dto里
        List<WorkOrderDetailDTO> workOrderDetailDTOs=new ArrayList<WorkOrderDetailDTO>();
        if(repairs.size()>0) {
            for (Object[] obj : repairs) {
                WorkOrderDetailDTO workOrderDetailDTO = new WorkOrderDetailDTO();
                workOrderDetailDTO.setId(obj[0] == null ? "" : obj[0].toString());
                workOrderDetailDTO.setContent(obj[1] == null ? "" : obj[1].toString());
                workOrderDetailDTO.setUserAddress(obj[2] == null ? "" : obj[2].toString());
                workOrderDetailDTO.setUserName(obj[3] == null ? "" : obj[3].toString());
                workOrderDetailDTO.setUserPhone(obj[4] == null ? "" : obj[4].toString());
                workOrderDetailDTO.setProject(obj[5] == null ? "" : obj[5].toString());
                workOrderDetailDTO.setState(obj[6] == null ? "" : obj[6].toString());
                workOrderDetailDTOs.add(workOrderDetailDTO);
            }
        }
        return workOrderDetailDTOs;
    }

    /**
     * 删除
     * @param user
     * @param repairId
     * @return
     */
    @Override
    public String removeRepairById(UserPropertyStaffEntity user, String repairId) {
        // 判断是否执行删除成功
        String resultMessage = "";
        //查询报修所需要的详情信息
        PropertyRepairEntity propertyRepairEntity=propertyRepairRepository.getRepairDetail(repairId);
        if (propertyRepairEntity!=null){
            propertyRepairEntity.setTypes("3");//已删除(逻辑)
            propertyRepairEntity.setModifyBy(user.getStaffId());
            propertyRepairEntity.setModifyDate(DateUtils.getDate());
            //执行删除操作，加入日志
            OperationLogDTO operationLogDTO=new OperationLogDTO();
            operationLogDTO.setContent("删除工单");
            operationLogDTO.setProjectId(user.getProjectId());
            operationLogDTO.setUserName(user.getStaffName());
            operationLogService.addOperationLog(operationLogDTO);
            //修改报修单状态
            boolean repair=propertyRepairRepository.updateRepaired(propertyRepairEntity);
            if(repair){
                resultMessage = "0";//此信息删除成功!
            }else {
                resultMessage = "1";//此信息删除失败!
            }
        }else{
            resultMessage = "2";//此信息不存在!
        }
        return resultMessage;
    }

    /**
     * 获取报修信息
     * @param propertyRepairDTO
     * @return
     */
    @Override
    public PropertyRepairDTO repairInfo(PropertyRepairDTO propertyRepairDTO) {
        //封装到dto
        PropertyRepairDTO propertyRepair = new PropertyRepairDTO();
        if(propertyRepairDTO.getId()!=null) {
            //创建报修图片集合
            List<PropertyImageDTO> imageDTOs = new ArrayList<PropertyImageDTO>();
            //维修完成图片集合
            //List<PropertyImageDTO> imageDTO = new ArrayList<PropertyImageDTO>();
            //查询报修所需要的详情信息
            PropertyRepairEntity propertyRepairEntity = propertyRepairRepository.getRepairDetail(propertyRepairDTO.getId());
            if (propertyRepairEntity != null) {
                propertyRepair.setId(propertyRepairEntity.getRepairId());//报修id
                propertyRepair.setContent(propertyRepairEntity.getContent());//内容
                propertyRepair.setRepairWay(propertyRepairEntity.getRepairWay());//来源
                propertyRepair.setUserName(propertyRepairEntity.getUserName());//业主姓名
                propertyRepair.setUserAddress(propertyRepairEntity.getUserAddress());//业主地址
            }
            //查询评价信息
            PropertyFeedbackEntity propertyFeedback = propertyFeedbackRepository.getFeedback(propertyRepairEntity.getRepairId());
            if (propertyFeedback != null) {
                propertyRepair.setGrade(propertyFeedback.getGrade());
                //propertyRepair.setEvaluateContent(propertyFeedback.getContent());
            }
            //获取报修类型
            List<PropertyRepairTaskEntity> repairTaskEntities = propertyRepairTaskRepository.getTaskDateOne(propertyRepairEntity.getRepairId());
            if(repairTaskEntities.size()>0) {
                if (repairTaskEntities.get(0).getTaskStatus().equals("0")) {
                    //查询创建报修图片集合
                    List<PropertyImageEntity> imageEntity = propertyImageRepository.getImageUrl(propertyRepairEntity.getRepairId());
                    if (imageEntity.size() > 0) {
                        for (PropertyImageEntity images : imageEntity) {
                            PropertyImageDTO propertyImageDTO = new PropertyImageDTO();
                            propertyImageDTO.setImageUrl(images.getImagePath());
                            imageDTOs.add(propertyImageDTO);
                        }
                    }
                }else if(repairTaskEntities.get(0).getTaskStatus().equals("1")){
                    //查询创建投诉图片集合
                    List<PropertyImageEntity> imageEntity = propertyImageRepository.getComplaintImage(propertyRepairEntity.getRepairId());
                    if (imageEntity.size() > 0) {
                        for (PropertyImageEntity images : imageEntity) {
                            PropertyImageDTO propertyImageDTO = new PropertyImageDTO();
                            propertyImageDTO.setImageUrl(images.getImagePath());
                            imageDTOs.add(propertyImageDTO);
                        }
                    }
                }
            }
            propertyRepair.setImageList(imageDTOs);
            //查询维修后图片集合
            /*List<PropertyImageEntity> imageEntities= propertyImageRepository.getImagedUrl(propertyRepairEntity.getRepairId());
            if (imageEntities.size() > 0) {
                for (PropertyImageEntity images : imageEntities) {
                    PropertyImageDTO propertyImageDTO = new PropertyImageDTO();
                    propertyImageDTO.setImageUrl(images.getImagePath());
                    imageDTO.add(propertyImageDTO);
                }
            }
            propertyRepair.setImagedList(imageDTO);*/
        }
        return propertyRepair;
    }

    /**
     * 优化导出Excel
     * @param title
     * @param headers
     * @param out
     * @param workOrderDetailDTO
     * @param user
     */
    public void exportExcel2(String title, String[] headers, ServletOutputStream out,WorkOrderDetailDTO workOrderDetailDTO,UserPropertyStaffEntity user){
        //设置分页
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(2000);
        //查询结果列表
        List<Map<String,Object>> repairList = getPropertyRepairCrmList(user, workOrderDetailDTO, webPage);
        List<ExportExcelWorkOrderDTO> exportExcelWorkOrderDTOs = new ArrayList<>();
        if (null != repairList){
            int num = 1;
            ExportExcelWorkOrderDTO exportExcelWorkOrderDTO = null;
            Map<String,Object> repairMap = null;
            for (int i = 0,length = repairList.size(); i<length; i++){
                repairMap = repairList.get(i);
                exportExcelWorkOrderDTO = new ExportExcelWorkOrderDTO();
                //序号
                exportExcelWorkOrderDTO.setNum(num++);
                //项目名称
                exportExcelWorkOrderDTO.setProjectName(repairMap.get("projectName") == null ? "" : repairMap.get("projectName").toString());
                //楼号
                exportExcelWorkOrderDTO.setBuildingRecord(repairMap.get("buildingRecord") == null ? "" : repairMap.get("buildingRecord").toString());
                //单元号
                exportExcelWorkOrderDTO.setUnit(repairMap.get("unit") == null ? "" : repairMap.get("unit").toString());
                //房号
                exportExcelWorkOrderDTO.setRoomName(repairMap.get("roomName") == null ? "" : repairMap.get("roomName").toString());
                //负责人
                exportExcelWorkOrderDTO.setRepairManager(repairMap.get("repairManager") == null ? "" : repairMap.get("repairManager").toString());

                //投诉一级分类
                exportExcelWorkOrderDTO.setLabelOne(repairMap.get("labelOne") == null ? "" : repairMap.get("labelOne").toString());
                //投诉二级分类
                exportExcelWorkOrderDTO.setLabelTwo(repairMap.get("labelTwo") == null ? "" : repairMap.get("labelTwo").toString());
                //投诉三级分类
                exportExcelWorkOrderDTO.setLabelThree(repairMap.get("labelThree") == null ? "" : repairMap.get("labelThree").toString());
                //基本内容
                exportExcelWorkOrderDTO.setContent(repairMap.get("content") == null ? "" : repairMap.get("content").toString());
                //诉求提出人
                exportExcelWorkOrderDTO.setUserName(repairMap.get("userName") == null ? "" : repairMap.get("userName").toString());
                //回访电话
                exportExcelWorkOrderDTO.setUserPhone(repairMap.get("userPhone") == null ? "" : repairMap.get("userPhone").toString());
                //问题详细与处理方案
                exportExcelWorkOrderDTO.setDealWay(repairMap.get("dealWay") == null ? "" : repairMap.get("dealWay").toString());
                //单据状态
                exportExcelWorkOrderDTO.setState(repairMap.get("state") == null ? "" : repairMap.get("state").toString());
                //诉求单号
                exportExcelWorkOrderDTO.setRepairId(repairMap.get("repairId") == null ? "" : repairMap.get("repairId").toString());

                //处理方式:accountabilityUnit/thirdParty/interior
                if (repairMap.get("dealMode") != null){
                    switch (repairMap.get("dealMode").toString()){
                        case "accountabilityUnit":
                            exportExcelWorkOrderDTO.setDealMode("责任单位");
                            break;
                        case "thirdParty":
                            exportExcelWorkOrderDTO.setDealMode("第三方");
                            break;
                        case "interior":
                            exportExcelWorkOrderDTO.setDealMode("内部");
                            break;
                    }
                }else{
                    exportExcelWorkOrderDTO.setDealMode("");
                }
                //责任单位
                exportExcelWorkOrderDTO.setDutyCompanyOneName(repairMap.get("dutyCompanyOneName") == null ? "" : repairMap.get("dutyCompanyOneName").toString());
                //责任单位2
                exportExcelWorkOrderDTO.setDutyCompanyTwoName(repairMap.get("dutyCompanyTwoName") == null ? "" : repairMap.get("dutyCompanyTwoName").toString());
                //责任单位3
                exportExcelWorkOrderDTO.setDutyCompanyThreeName(repairMap.get("dutyCompanyThreeName") == null ? "" : repairMap.get("dutyCompanyThreeName").toString());
                //维修单位
                exportExcelWorkOrderDTO.setRepairCompany(repairMap.get("repairCompany") == null ? "" : repairMap.get("repairCompany").toString());
                //问题程度
                if (repairMap.get("problemLevel") != null){
                    String problemLevel = repairMap.get("problemLevel").toString();
                    if (problemLevel.equals("nonUrgent")){
                        exportExcelWorkOrderDTO.setProblemLevel("非紧急");
                    }else{
                        exportExcelWorkOrderDTO.setProblemLevel("紧急");
                    }
                }else{
                    exportExcelWorkOrderDTO.setProblemLevel("");
                }
                //创建时间
                exportExcelWorkOrderDTO.setCreateDate(repairMap.get("createDate") == null ? "" : repairMap.get("createDate").toString());
                //前台派单给负责人时间
                exportExcelWorkOrderDTO.setSendDate(repairMap.get("sendDate") == null ? "" : repairMap.get("sendDate").toString());
                //投诉单负责人接单时间
                exportExcelWorkOrderDTO.setTaskDate(repairMap.get("taskDate") == null ? "" : repairMap.get("taskDate").toString());
                //受理人员完工时间
                exportExcelWorkOrderDTO.setDealCompleteDate(repairMap.get("dealCompleteDate") == null ? "" : repairMap.get("dealCompleteDate").toString());
                exportExcelWorkOrderDTOs.add(exportExcelWorkOrderDTO);
            }
            ExportExcel<ExportExcelWorkOrderDTO> ex = new ExportExcel<ExportExcelWorkOrderDTO>();
            ex.exportExcel(title, headers, exportExcelWorkOrderDTOs, out, "yyyy-MM-dd");
            System.out.println("excel导出成功！");
        }
    }

    /**
     * 导出excel
     * param:user
     * param:httpServletResponse
     * return
     */
    @Override
    public String exportExcel(UserPropertyStaffEntity user,WorkOrderDetailDTO workOrderDetailDTO,
                              HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException {

        OutputStream outputStream = httpServletResponse.getOutputStream();
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        List<WorkOrderDetailDTO> repairDTO = repairInfoList(user, workOrderDetailDTO, webPage);

        XSSFSheet sheet = workBook.createSheet("报事报修列表");

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

        String[] titles = {"序号", "单号", "项目", "地址", "姓名", "电话", "报修时间", "来源", "状态", "类型","报修内容"};
        XSSFRow headRow = sheet.createRow(0);


        if (repairDTO.size() > 0) {

            repairDTO.forEach(userDTO -> {

                XSSFCell cell=null;
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }

                if (repairDTO.size() > 0) {
                    for (int i = 0; i < repairDTO.size(); i++) {
                        XSSFRow bodyRow = sheet.createRow(i + 1);
                        WorkOrderDetailDTO WorkOrder = repairDTO.get(i);
                        cell = bodyRow.createCell(0);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(i + 1);//序号

                        cell = bodyRow.createCell(1);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(WorkOrder.getId());//单号

                        cell = bodyRow.createCell(2);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(WorkOrder.getProject());//项目

                        cell = bodyRow.createCell(3);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(WorkOrder.getUserAddress());//地址

                        cell = bodyRow.createCell(4);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(WorkOrder.getUserName());//姓名

                        cell = bodyRow.createCell(5);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(WorkOrder.getUserPhone());//电话

                        cell = bodyRow.createCell(6);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(WorkOrder.getCreateDate());//报修时间

                        cell = bodyRow.createCell(7);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(WorkOrder.getRepairWay());//来源

                        cell = bodyRow.createCell(8);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(WorkOrder.getStatus());//状态

                        cell = bodyRow.createCell(9);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(WorkOrder.getMemo());//类型

                        cell = bodyRow.createCell(10);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(WorkOrder.getContent());//报修内容
                    }
                }
            });
        }
        try {
            //String fileName = new String(("报事报修管理").getBytes(), "ISO8859_1");
            String fileName = new String(("报事报修管理").getBytes(), "ISO8859-1");
            String agent = httpServletRequest.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                fileName = java.net.URLEncoder.encode("报事报修管理", "UTF8");
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

    /**
     * 检索物业报修服务问题答应列表
     * @param repairId 报修单ID
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getRepairServiceQuestionList(String repairId){
        return propertyRepairRepository.getRepairServiceQuestionList(repairId);
    }

    /**
     * 获取物业报修完整数据列表
     */
    @Override
    public List<Map<String,Object>> getPropertyRepairCrmList(UserPropertyStaffEntity user,WorkOrderDetailDTO workOrder, WebPage webPage) {
        //检索条件
        Map<String,Object> params = new HashedMap();
        if(workOrder!=null) {
            if (null != workOrder.getProjectCode() && !"0".equals(workOrder.getProjectCode()) && !"".equals(workOrder.getProjectCode())){
                params.put("projectIds","'" + workOrder.getProjectCode() + "',");
            }else if (null != workOrder.getScopeId() && !"0".equals(workOrder.getScopeId()) && !"".equals(workOrder.getScopeId())){
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(workOrder.getScopeId());
                String projectIds = "";
                for (Object[] project : projectList) {
                    projectIds += "'" + project[0].toString() + "',";
                }
                params.put("projectIds",projectIds);
            }
            if (!StringUtil.isEmpty(workOrder.getRepairWay())) {
                params.put("repairWay",workOrder.getRepairWay());//来源
            }
            if (!StringUtil.isEmpty(workOrder.getId())) {
                params.put("id",workOrder.getId());//单号
            }
            if (!StringUtil.isEmpty(workOrder.getUserName())) {
                params.put("userName",workOrder.getUserName());//业主姓名
            }
            if (!StringUtil.isEmpty(workOrder.getUserPhone())) {
                params.put("userPhone",workOrder.getUserPhone());//业主电话
            }
            if (!StringUtil.isEmpty(workOrder.getStartDate())) {
                params.put("startDate",workOrder.getStartDate());//开始时间
            }
            if (!StringUtil.isEmpty(workOrder.getEndDate())) {
                params.put("endDate",workOrder.getEndDate());//结束时间
            }
            if (!StringUtil.isEmpty(workOrder.getStatus())) {
                params.put("status",workOrder.getStatus());//工单状态
            }
            if (!StringUtil.isEmpty(workOrder.getType())) {
                params.put("type",workOrder.getType());//工单类型
            }
            if (!StringUtil.isEmpty(workOrder.getCompleteStartDate())){
                params.put("completeStartDate",workOrder.getCompleteStartDate());//完成开始时间
            }
            if (!StringUtil.isEmpty(workOrder.getCompleteEndDate())){
                params.put("completeEndDate",workOrder.getCompleteEndDate());//完成结束时间
            }
        }
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = workOrder.getRoleScopeList();
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
        params.put("roleScopes",roleScopes);
        //获取物业报修完整数据列表
        List<Map<String,Object>> propertyRepairCrmList=propertyRepairRepository.getPropertyRepairCrmList(params,webPage);
        return propertyRepairCrmList;
    }

    /**
     * 导出物业报修完整数据列表Excel
     */
    @Override
    public void exportExcelRepairAllDate(String title, String[] headers, ServletOutputStream out,WorkOrderDetailDTO workOrderDetailDTO,UserPropertyStaffEntity user){
        //设置分页
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(2000);
        //查询结果列表
        List<Map<String,Object>> repairList = getPropertyRepairCrmList(user, workOrderDetailDTO, webPage);
        List<ExportExcelWorkOrderDTO> exportExcelWorkOrderDTOs = new ArrayList<>();
        if (null != repairList){
            int num = 1;
            ExportExcelWorkOrderDTO exportExcelWorkOrderDTO = null;
            Map<String,Object> repairMap = null;
            for (int i = 0,length = repairList.size(); i<length; i++){
                repairMap = repairList.get(i);
                exportExcelWorkOrderDTO = new ExportExcelWorkOrderDTO();
                //序号
                exportExcelWorkOrderDTO.setNum(num++);
                //项目名称
                exportExcelWorkOrderDTO.setProjectName(repairMap.get("projectName") == null ? "" : repairMap.get("projectName").toString());
                //楼号
                exportExcelWorkOrderDTO.setBuildingRecord(repairMap.get("buildingRecord") == null ? "" : repairMap.get("buildingRecord").toString());
                //单元号
                exportExcelWorkOrderDTO.setUnit(repairMap.get("unit") == null ? "" : repairMap.get("unit").toString());
                //房号
                exportExcelWorkOrderDTO.setRoomName(repairMap.get("roomName") == null ? "" : repairMap.get("roomName").toString());
                //负责人
                exportExcelWorkOrderDTO.setRepairManager(repairMap.get("repairManager") == null ? "" : repairMap.get("repairManager").toString());

                //投诉一级分类
                exportExcelWorkOrderDTO.setLabelOne(repairMap.get("labelOne") == null ? "" : repairMap.get("labelOne").toString());
                //投诉二级分类
                exportExcelWorkOrderDTO.setLabelTwo(repairMap.get("labelTwo") == null ? "" : repairMap.get("labelTwo").toString());
                //投诉三级分类
                exportExcelWorkOrderDTO.setLabelThree(repairMap.get("labelThree") == null ? "" : repairMap.get("labelThree").toString());
                //基本内容
                exportExcelWorkOrderDTO.setContent(repairMap.get("content") == null ? "" : repairMap.get("content").toString());
                //诉求提出人
                exportExcelWorkOrderDTO.setUserName(repairMap.get("userName") == null ? "" : repairMap.get("userName").toString());
                //回访电话
                exportExcelWorkOrderDTO.setUserPhone(repairMap.get("userPhone") == null ? "" : repairMap.get("userPhone").toString());
                //问题详细与处理方案
                exportExcelWorkOrderDTO.setDealWay(repairMap.get("dealWay") == null ? "" : repairMap.get("dealWay").toString());
                //单据状态
                exportExcelWorkOrderDTO.setState(repairMap.get("state") == null ? "" : repairMap.get("state").toString());
                //诉求单号
                exportExcelWorkOrderDTO.setRepairId(repairMap.get("repairId") == null ? "" : repairMap.get("repairId").toString());

                //处理方式:accountabilityUnit/thirdParty/interior
                if (repairMap.get("dealMode") != null){
                    switch (repairMap.get("dealMode").toString()){
                        case "accountabilityUnit":
                            exportExcelWorkOrderDTO.setDealMode("责任单位");
                            break;
                        case "thirdParty":
                            exportExcelWorkOrderDTO.setDealMode("第三方");
                            break;
                        case "interior":
                            exportExcelWorkOrderDTO.setDealMode("内部");
                            break;
                    }
                }else{
                    exportExcelWorkOrderDTO.setDealMode("");
                }
                //责任单位
                exportExcelWorkOrderDTO.setDutyCompanyOneName(repairMap.get("dutyCompanyOneName") == null ? "" : repairMap.get("dutyCompanyOneName").toString());
                //责任单位2
                exportExcelWorkOrderDTO.setDutyCompanyTwoName(repairMap.get("dutyCompanyTwoName") == null ? "" : repairMap.get("dutyCompanyTwoName").toString());
                //责任单位3
                exportExcelWorkOrderDTO.setDutyCompanyThreeName(repairMap.get("dutyCompanyThreeName") == null ? "" : repairMap.get("dutyCompanyThreeName").toString());
                //维修单位
                exportExcelWorkOrderDTO.setRepairCompany(repairMap.get("repairCompany") == null ? "" : repairMap.get("repairCompany").toString());
                //问题程度
                if (repairMap.get("problemLevel") != null){
                    String problemLevel = repairMap.get("problemLevel").toString();
                    if (problemLevel.equals("nonUrgent")){
                        exportExcelWorkOrderDTO.setProblemLevel("非紧急");
                    }else{
                        exportExcelWorkOrderDTO.setProblemLevel("紧急");
                    }
                }else{
                    exportExcelWorkOrderDTO.setProblemLevel("");
                }

                //创建时间
                exportExcelWorkOrderDTO.setCreateDate(repairMap.get("createDate") == null ? "" : repairMap.get("createDate").toString());
                //前台派单给负责人时间
                exportExcelWorkOrderDTO.setSendDate(repairMap.get("sendDate") == null ? "" : repairMap.get("sendDate").toString());
                //投诉单负责人接单时间
                exportExcelWorkOrderDTO.setTaskDate(repairMap.get("taskDate") == null ? "" : repairMap.get("taskDate").toString());
                //受理人员完工时间
                exportExcelWorkOrderDTO.setDealCompleteDate(repairMap.get("dealCompleteDate") == null ? "" : repairMap.get("dealCompleteDate").toString());

                //评价时间;总评分
                PropertyFeedbackEntity propertyFeedback = propertyFeedbackRepository.getFeedback(repairMap.get("repairId").toString());
                if (propertyFeedback != null) {
                    exportExcelWorkOrderDTO.setFeedbackDate(propertyFeedback.getCreateDate().toString());
                    exportExcelWorkOrderDTO.setGrade(propertyFeedback.getGrade());
                }
                //获取报修服务评价
                List<Map<String, Object>> repairServiceQuestionList = propertyRepairRepository.getRepairServiceQuestionList(repairMap.get("repairId").toString());
                if (!repairServiceQuestionList.isEmpty() && repairServiceQuestionList.size() > 0){
                    Map<String, Object> repairServiceQuestion = null;
                    for (int m=0,length_m=repairServiceQuestionList.size();m<length_m;m++){
                        repairServiceQuestion = repairServiceQuestionList.get(m);
                        if (repairServiceQuestion.get("questionContent").toString().contains("报修渠道通畅")){
                            exportExcelWorkOrderDTO.setServiceQuestion1(repairServiceQuestion.get("scoreLev") == null ? "" : repairServiceQuestion.get("scoreLev").toString());
                        }
                        if (repairServiceQuestion.get("questionContent").toString().contains("响应反馈及时")){
                            exportExcelWorkOrderDTO.setServiceQuestion2(repairServiceQuestion.get("scoreLev") == null ? "" : repairServiceQuestion.get("scoreLev").toString());
                        }
                        if (repairServiceQuestion.get("questionContent").toString().contains("着装礼仪规范")){
                            exportExcelWorkOrderDTO.setServiceQuestion3(repairServiceQuestion.get("scoreLev") == null ? "" : repairServiceQuestion.get("scoreLev").toString());
                        }
                        if (repairServiceQuestion.get("questionContent").toString().contains("维修整改高效")){
                            exportExcelWorkOrderDTO.setServiceQuestion4(repairServiceQuestion.get("scoreLev") == null ? "" : repairServiceQuestion.get("scoreLev").toString());
                        }
                        if (repairServiceQuestion.get("questionContent").toString().contains("保护清洁到位")){
                            exportExcelWorkOrderDTO.setServiceQuestion5(repairServiceQuestion.get("scoreLev") == null ? "" : repairServiceQuestion.get("scoreLev").toString());
                        }
                        if (repairServiceQuestion.get("questionContent").toString().contains("您觉得还有哪些方面需要改进")){
                            exportExcelWorkOrderDTO.setServiceQuestion6(repairServiceQuestion.get("suggestion") == null ? "" : repairServiceQuestion.get("suggestion").toString());
                        }
                    }
                }
                exportExcelWorkOrderDTOs.add(exportExcelWorkOrderDTO);
            }
            ExportExcel<ExportExcelWorkOrderDTO> ex = new ExportExcel<ExportExcelWorkOrderDTO>();
            ex.exportExcel(title, headers, exportExcelWorkOrderDTOs, out, "yyyy-MM-dd");
            System.out.println("excel导出成功！");
        }
    }

}