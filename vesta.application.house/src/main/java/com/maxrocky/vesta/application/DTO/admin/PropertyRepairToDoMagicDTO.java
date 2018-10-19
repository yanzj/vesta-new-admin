package com.maxrocky.vesta.application.DTO.admin;


import com.maxrocky.vesta.application.DTO.json.HI0012.ProjectReturnImageJsonDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2017/1/17.
 */
public class PropertyRepairToDoMagicDTO {
    private String id;//自增长id
    private String repairId;//报修单号
    private String department;//部门      必填
    private String projectNum;//项目编码
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String buildNum;//楼栋编码
    private String roomId;//房间id
    private String roomNum;//房间编码     必填
    private String address;//房屋地址
    private String memberId;//报修人会员编号
    private String userName;//报修人姓名      必填
    private String userPhone;//报修人电话     必填
    private String userAddress;//报修人地址
    private String createDate;//报修时间
    private String classifyOne;//一级分类     必填
    private String classifyTwo;//二级分类     必填
    private String classifyThree;//三级分类     必填
    private String mode;//维修方式：换、维修     必填
    private String repairDate;//维修工时(分类绑定)：限期整改，工时是否超时     必填
    private String roomLocation;//房屋位置     必填
    private String state;//单据状态
    private String duty;//责任判定:主责(true)、非主责(false)
    private String problemLevel;//问题级别：紧急/非紧急
    private String repairWay;//报修方式：会员APP/微信/呼叫中心
    private String content;//基本内容     必填
    private String taskDate;//接单时间
    private String dealWay;//处理方案
    private String dealMode;//处理方式:内部/责任单位/第三方
    private String dutyCompanyOne;//责任单位1
    private String dutyCompanyTwo;//责任单位2
    private String dutyCompanyThree;//责任单位3
    private String repairCompany;//维修单位
    private String dutyTaskDate;//责任单位接单时间
    private String repairManagerId;//维修负责人id     必填  直接拍单到人  人的id
    private String repairManager;//维修负责人账号
    private String supplierId;//责任单位负责人
    private String dealPeoplename;//处理人姓名
    private String dealPeople;//处理人id
    private String dealPhone;//处理人电话
    private String dealState;//处理状态(1：处理/0：暂不处理)
    private String dealResult;//处理结果
    private String dealCompleteDate;//受理人完工时间
    private String sendName;//派单人姓名
    private String sendDate;//派单时间

    private String xCoordinates;//X坐标     必填
    private String yCoordinates;//Y坐标     必填
    private List<ProjectReturnImageJsonDTO> imgList;//报修完成后list
    private List<ProjectReturnImageJsonDTO> imageList;//报修完成前list
    private String modifyDate;//修改时间
    private String appId;//校验唯一性
    private String deType;//处理标识 0不是代办  1代办
    private String reminderTime;//t提醒时间
    public PropertyRepairToDoMagicDTO() {
        this.reminderTime="";
        this.supplierId="";
        this.id="";
        this.projectId="";
        this.projectName="";
        this.buildNum="";
        this.deType="";
        this.imgList=new ArrayList<>();
        this.imageList=new ArrayList<>();
        this.appId="";
        this.modifyDate="";//修改时间
        this.repairId="";//报修单号
        this.department="";//部门      必填
        this.roomId="";//房间id
        this.roomNum="";//房间编码     必填
        this.address="";//房屋地址
        this.memberId="";//报修人会员编号
        this.userName="";//报修人姓名      必填
        this.userPhone="";//报修人电话     必填
        this.userAddress="";//报修人地址
        this.createDate="";//报修时间
        this.classifyOne="";//一级分类     必填
        this.classifyTwo="";//二级分类     必填
        this.classifyThree="";//三级分类     必填
        this.mode="";//维修方式：换、维修     必填
        this.repairDate="";//维修工时(分类绑定)：限期整改，工时是否超时     必填
        this.roomLocation="";//房屋位置     必填
        this.state="";//单据状态
        this.duty="";//责任判定:主责(true)、非主责(false)
        this.problemLevel="";//问题级别：紧急/非紧急
        this.repairWay="";//报修方式：会员APP/微信/呼叫中心
        this.content="";//基本内容     必填
        this.taskDate="";//接单时间
        this.dealWay="";//处理方案
        this.dealMode="";//处理方式:内部/责任单位/第三方
        this.dutyCompanyOne="";//责任单位1
        this.dutyCompanyTwo="";//责任单位2
        this.dutyCompanyThree="";//责任单位3
        this.repairCompany="";//维修单位
        this.dutyTaskDate="";//责任单位接单时间
        this.repairManagerId="";//维修负责人id     必填  直接拍单到人  人的id
        this.repairManager="";//维修负责人账号
        this.dealPeoplename="";//处理人姓名
        this.dealPeople="";//处理人id
        this.dealPhone="";//处理人电话
        this.dealState="";//处理状态(1：处理/0：暂不处理)
        this.dealResult="";//处理结果
        this.dealCompleteDate="";//受理人完工时间
        this.sendName="";//派单人姓名
        this.sendDate="";//派单时间
        this.projectNum="";//项目编码
        this.xCoordinates="";//X坐标     必填
        this.yCoordinates="";//Y坐标     必填
    }
    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }


    public String getClassifyOne() {
        return classifyOne;
    }

    public void setClassifyOne(String classifyOne) {
        this.classifyOne = classifyOne;
    }

    public String getClassifyTwo() {
        return classifyTwo;
    }

    public void setClassifyTwo(String classifyTwo) {
        this.classifyTwo = classifyTwo;
    }

    public String getClassifyThree() {
        return classifyThree;
    }

    public void setClassifyThree(String classifyThree) {
        this.classifyThree = classifyThree;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(String repairDate) {
        this.repairDate = repairDate;
    }

    public String getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(String roomLocation) {
        this.roomLocation = roomLocation;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProblemLevel() {
        return problemLevel;
    }

    public void setProblemLevel(String problemLevel) {
        this.problemLevel = problemLevel;
    }

    public String getRepairWay() {
        return repairWay;
    }

    public void setRepairWay(String repairWay) {
        this.repairWay = repairWay;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getDealWay() {
        return dealWay;
    }

    public void setDealWay(String dealWay) {
        this.dealWay = dealWay;
    }

    public String getDealMode() {
        return dealMode;
    }

    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    public String getDutyCompanyOne() {
        return dutyCompanyOne;
    }

    public void setDutyCompanyOne(String dutyCompanyOne) {
        this.dutyCompanyOne = dutyCompanyOne;
    }

    public String getDutyCompanyTwo() {
        return dutyCompanyTwo;
    }

    public void setDutyCompanyTwo(String dutyCompanyTwo) {
        this.dutyCompanyTwo = dutyCompanyTwo;
    }

    public String getDutyCompanyThree() {
        return dutyCompanyThree;
    }

    public void setDutyCompanyThree(String dutyCompanyThree) {
        this.dutyCompanyThree = dutyCompanyThree;
    }

    public String getRepairCompany() {
        return repairCompany;
    }

    public void setRepairCompany(String repairCompany) {
        this.repairCompany = repairCompany;
    }



    public String getRepairManager() {
        return repairManager;
    }

    public void setRepairManager(String repairManager) {
        this.repairManager = repairManager;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getDutyTaskDate() {
        return dutyTaskDate;
    }

    public void setDutyTaskDate(String dutyTaskDate) {
        this.dutyTaskDate = dutyTaskDate;
    }

    public String getDealPeople() {
        return dealPeople;
    }

    public void setDealPeople(String dealPeople) {
        this.dealPeople = dealPeople;
    }

    public String getDealPhone() {
        return dealPhone;
    }

    public void setDealPhone(String dealPhone) {
        this.dealPhone = dealPhone;
    }

    public String getDealState() {
        return dealState;
    }

    public void setDealState(String dealState) {
        this.dealState = dealState;
    }

    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDealCompleteDate() {
        return dealCompleteDate;
    }

    public void setDealCompleteDate(String dealCompleteDate) {
        this.dealCompleteDate = dealCompleteDate;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getRepairManagerId() {
        return repairManagerId;
    }

    public void setRepairManagerId(String repairManagerId) {
        this.repairManagerId = repairManagerId;
    }

    public String getDealPeoplename() {
        return dealPeoplename;
    }

    public void setDealPeoplename(String dealPeoplename) {
        this.dealPeoplename = dealPeoplename;
    }

    public String getxCoordinates() {
        return xCoordinates;
    }

    public void setxCoordinates(String xCoordinates) {
        this.xCoordinates = xCoordinates;
    }

    public String getyCoordinates() {
        return yCoordinates;
    }

    public void setyCoordinates(String yCoordinates) {
        this.yCoordinates = yCoordinates;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDeType() {
        return deType;
    }

    public void setDeType(String deType) {
        this.deType = deType;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getBuildNum() {
        return buildNum;
    }

    public void setBuildNum(String buildNum) {
        this.buildNum = buildNum;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }



    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public List<ProjectReturnImageJsonDTO> getImgList() {
        return imgList;
    }

    public void setImgList(List<ProjectReturnImageJsonDTO> imgList) {
        this.imgList = imgList;
    }

    public List<ProjectReturnImageJsonDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<ProjectReturnImageJsonDTO> imageList) {
        this.imageList = imageList;
    }
}
