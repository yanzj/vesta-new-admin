package com.maxrocky.vesta.application.DTO;

import java.util.Date;
import java.util.List;

/**
 * Created by liudongxin on 2016/4/22.
 * 物业报修
 */
public class RepairDTO {
    private String repairId;//报修单号
    private String department;//部门
    private String roomId;//房间id
    private String roomNum;//房间编码
    private String memberId;//报修人会员编号
    private String userName;//报修人姓名
    private String userPhone;//报修人电话
    private String userAddress;//报修人地址
    private Date createDate;//报修时间
    private String classifyOne;//一级分类
    private String classifyTwo;//二级分类
    private String classifyThree;//三级分类
    private String mode;//维修方式：换、维修
    private String repairDate;//维修工时(分类绑定)：限期整改，工时是否超时
    private String roomLocation;//房屋位置id
    private String state;//单据状态
    private String duty;//责任判定:主责(1：true)、非主责(0：false)
    private String problemLevel;//问题级别：紧急/非紧急
    private String repairWay;//报修方式：会员APP/微信/呼叫中心
    private String content;//基本内容
    private Date taskDate;//接单时间
    private String dealWay;//处理方案
    private String dealMode;//处理方式:内部/责任单位/第三方
    private String dutyCompanyOne;//责任单位1
    private String dutyCompanyTwo;//责任单位2
    private String dutyCompanyThree;//责任单位3
    private String repairCompany;//维修单位
    private Date dutyTaskDate;//责任单位接单时间
    private Date dutyReturnDate;//责任单位返单时间
    private String repairManager;//维修负责人
    private String dealPeople;//处理人
    private String dealPhone;//处理人电话
    private String dealPeopleTwo;//处理人2
    private String dealPhoneTwo;//处理人电话2
    private Date firstVisitDate;//首选上门日期
    private Date backupVisitDate;//备选上门日期
    private String dealState;//处理状态(1：处理/0：暂不处理)
    private String dealResult;//处理结果
    private Date dealCompleteDate;//受理人完工时间
    private String visitOpinion;//回访意见
    private String visitEvaluate;//回访评价:非常满意/满意/一般/不满意/非常不满意
    private Date visitDate;//回访日期
    private String visitType;//电话/短信/其他
    private String sendName;//派单人姓名
    private Date sendDate;//派单时间

    private Date predictedDate;//预计完成时间


    private List<PropertyImageDTO> imageList; //图片list


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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
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

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
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

    public Date getDutyTaskDate() {
        return dutyTaskDate;
    }

    public void setDutyTaskDate(Date dutyTaskDate) {
        this.dutyTaskDate = dutyTaskDate;
    }

    public Date getDutyReturnDate() {
        return dutyReturnDate;
    }

    public void setDutyReturnDate(Date dutyReturnDate) {
        this.dutyReturnDate = dutyReturnDate;
    }

    public String getRepairManager() {
        return repairManager;
    }

    public void setRepairManager(String repairManager) {
        this.repairManager = repairManager;
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

    public String getDealPeopleTwo() {
        return dealPeopleTwo;
    }

    public void setDealPeopleTwo(String dealPeopleTwo) {
        this.dealPeopleTwo = dealPeopleTwo;
    }

    public String getDealPhoneTwo() {
        return dealPhoneTwo;
    }

    public void setDealPhoneTwo(String dealPhoneTwo) {
        this.dealPhoneTwo = dealPhoneTwo;
    }

    public Date getFirstVisitDate() {
        return firstVisitDate;
    }

    public void setFirstVisitDate(Date firstVisitDate) {
        this.firstVisitDate = firstVisitDate;
    }

    public Date getBackupVisitDate() {
        return backupVisitDate;
    }

    public void setBackupVisitDate(Date backupVisitDate) {
        this.backupVisitDate = backupVisitDate;
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

    public Date getDealCompleteDate() {
        return dealCompleteDate;
    }

    public void setDealCompleteDate(Date dealCompleteDate) {
        this.dealCompleteDate = dealCompleteDate;
    }

    public String getVisitOpinion() {
        return visitOpinion;
    }

    public void setVisitOpinion(String visitOpinion) {
        this.visitOpinion = visitOpinion;
    }

    public String getVisitEvaluate() {
        return visitEvaluate;
    }

    public void setVisitEvaluate(String visitEvaluate) {
        this.visitEvaluate = visitEvaluate;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getPredictedDate() {
        return predictedDate;
    }

    public void setPredictedDate(Date predictedDate) {
        this.predictedDate = predictedDate;
    }

    public List<PropertyImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<PropertyImageDTO> imageList) {
        this.imageList = imageList;
    }
}
