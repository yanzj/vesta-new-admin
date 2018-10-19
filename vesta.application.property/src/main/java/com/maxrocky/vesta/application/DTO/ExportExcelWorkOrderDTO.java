package com.maxrocky.vesta.application.DTO;

/**
 * 物业报修单列表导出Excel_DTO
 * Created by WeiYangDong on 2017/2/22.
 * Modified by WeiYangDong on 2018/1/11.
 */
public class ExportExcelWorkOrderDTO {

//    private int num;                 //序号
//    private String id;               //报修单id
//    private String project;         //项目
//    private String userAddress;     //业主地址(员工端：员工部门)
//    private String userName;        //业主姓名(员工端：员工姓名)
//    private String userPhone;       //业主电话(员工端：员工电话)
//    private String createDate;      //创建日期
//    private String repairWay;       //报修方式：APP/微信/呼叫中心/网站/项目前台/物业单
//    private String status;          //状态
//    private String memo;            //备注：报修、投诉、维修、客服、秩序、环境
//    private String content;         //内容

    private int num;//序号

    private String projectName;//项目名称
    private String buildingRecord;//楼号
    private String unit;//单元号
    private String roomName;//房号
    private String repairManager;//负责人

    private String labelOne;//投诉一级分类
    private String labelTwo;//投诉二级分类
    private String labelThree;//投诉三级分类
    private String content;//基本内容
    private String userName;//诉求提出人
    private String userPhone;//回访电话
    private String dealWay;//问题详细与处理方案
    private String state;//单据状态
    private String repairId;//诉求单号

    private String dealMode;//处理方式
    private String dutyCompanyOneName;//责任单位1
    private String dutyCompanyTwoName;//责任单位2
    private String dutyCompanyThreeName;//责任单位3
    private String repairCompany;//维修单位
    private String problemLevel;//问题程度

    private String createDate;//创建时间
    private String sendDate;//前台派单给负责人时间
    private String taskDate;//投诉单负责人接单时间
    private String dealCompleteDate;//受理人员完工时间

    private String feedbackDate;//评价时间
    private String grade;//维修服务的整体满意程度
    private String serviceQuestion1;
    private String serviceQuestion2;
    private String serviceQuestion3;
    private String serviceQuestion4;
    private String serviceQuestion5;
    private String serviceQuestion6;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBuildingRecord() {
        return buildingRecord;
    }

    public void setBuildingRecord(String buildingRecord) {
        this.buildingRecord = buildingRecord;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRepairManager() {
        return repairManager;
    }

    public void setRepairManager(String repairManager) {
        this.repairManager = repairManager;
    }

    public String getLabelOne() {
        return labelOne;
    }

    public void setLabelOne(String labelOne) {
        this.labelOne = labelOne;
    }

    public String getLabelTwo() {
        return labelTwo;
    }

    public void setLabelTwo(String labelTwo) {
        this.labelTwo = labelTwo;
    }

    public String getLabelThree() {
        return labelThree;
    }

    public void setLabelThree(String labelThree) {
        this.labelThree = labelThree;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getDealWay() {
        return dealWay;
    }

    public void setDealWay(String dealWay) {
        this.dealWay = dealWay;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public String getDealMode() {
        return dealMode;
    }

    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    public String getDutyCompanyOneName() {
        return dutyCompanyOneName;
    }

    public void setDutyCompanyOneName(String dutyCompanyOneName) {
        this.dutyCompanyOneName = dutyCompanyOneName;
    }

    public String getDutyCompanyTwoName() {
        return dutyCompanyTwoName;
    }

    public void setDutyCompanyTwoName(String dutyCompanyTwoName) {
        this.dutyCompanyTwoName = dutyCompanyTwoName;
    }

    public String getDutyCompanyThreeName() {
        return dutyCompanyThreeName;
    }

    public void setDutyCompanyThreeName(String dutyCompanyThreeName) {
        this.dutyCompanyThreeName = dutyCompanyThreeName;
    }

    public String getRepairCompany() {
        return repairCompany;
    }

    public void setRepairCompany(String repairCompany) {
        this.repairCompany = repairCompany;
    }

    public String getProblemLevel() {
        return problemLevel;
    }

    public void setProblemLevel(String problemLevel) {
        this.problemLevel = problemLevel;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public String getDealCompleteDate() {
        return dealCompleteDate;
    }

    public void setDealCompleteDate(String dealCompleteDate) {
        this.dealCompleteDate = dealCompleteDate;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getServiceQuestion1() {
        return serviceQuestion1;
    }

    public void setServiceQuestion1(String serviceQuestion1) {
        this.serviceQuestion1 = serviceQuestion1;
    }

    public String getServiceQuestion2() {
        return serviceQuestion2;
    }

    public void setServiceQuestion2(String serviceQuestion2) {
        this.serviceQuestion2 = serviceQuestion2;
    }

    public String getServiceQuestion3() {
        return serviceQuestion3;
    }

    public void setServiceQuestion3(String serviceQuestion3) {
        this.serviceQuestion3 = serviceQuestion3;
    }

    public String getServiceQuestion4() {
        return serviceQuestion4;
    }

    public void setServiceQuestion4(String serviceQuestion4) {
        this.serviceQuestion4 = serviceQuestion4;
    }

    public String getServiceQuestion5() {
        return serviceQuestion5;
    }

    public void setServiceQuestion5(String serviceQuestion5) {
        this.serviceQuestion5 = serviceQuestion5;
    }

    public String getServiceQuestion6() {
        return serviceQuestion6;
    }

    public void setServiceQuestion6(String serviceQuestion6) {
        this.serviceQuestion6 = serviceQuestion6;
    }

}
