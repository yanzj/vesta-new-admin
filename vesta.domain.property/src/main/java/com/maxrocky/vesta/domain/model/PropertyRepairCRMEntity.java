package com.maxrocky.vesta.domain.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liudongxin on 2016/4/23.
 * 用于保存CRM报修单数据
 */
@Entity
@javax.persistence.Table(name = "property_repair_crm")
public class PropertyRepairCRMEntity {
    private String repairId;////报修单号
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
    private String duty;//责任判定:主责(true)、非主责(false)
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
    private String dealState;//处理状态(处理/暂不处理)
    private String dealResult;//处理结果
    private Date dealCompleteDate;//受理人完工时间
    private String visitOpinion;//回访意见
    private String visitEvaluate;//回访评价:非常满意/满意/一般/不满意/非常不满意
    private Date visitDate;//回访日期
    private String visitType;//电话/短信/其他
    private Date modifyDate;//修改时间

    private String repairManagerId;//维修负责人
    private String dutyCompanyOneUserId;//责任单位负责人
    private String dutyCompanyOneUser;//责任单位负责人
    private String sendName;//派单人姓名
    private Date sendDate;//派单时间
    private String createUserName;//创建人姓名
    private String createUserPhone;//创建人电话
    private String updateUserName;//填报人
    private Date updateUserDate;// 填报时间

    private BigDecimal xCoordinates;//X坐标
    private BigDecimal yCoordinates;//Y坐标
    private String appId;//app端传入id

    private String failType;//失败标识   1失败  0或空 成功
    private Integer failNum;//失败次数

    private Date reminderTime;//提醒时间

    private String thumbnails;//缩略图

    private Date predictedDate;//预计完成时间
    private String classifyOneName;//一级分类名称
    private String classifyTwoName;//二级分类名称
    private String classifyThreeName;//三级分类名称
    @Id
    @Column(name = "REPAIR_ID", nullable = false, insertable = true, updatable = true, length = 50)
    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    @Basic
    @javax.persistence.Column(name = "DEPARTMENT", nullable = true, insertable = true, updatable = true, length = 50)
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Basic
    @javax.persistence.Column(name = "ROOM_ID", nullable = true, insertable = true, updatable = true, length = 50)
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Basic
    @javax.persistence.Column(name = "ROOM_NUM", nullable = true, insertable = true, updatable = true, length = 50)
    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    @Basic
    @javax.persistence.Column(name = "MEMBER_ID", nullable = true, insertable = true, updatable = true, length = 50)
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Basic
    @javax.persistence.Column(name = "USER_NAME", nullable = true, insertable = true, updatable = true, length = 30)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @javax.persistence.Column(name = "USER_PHONE", nullable = true, insertable = true, updatable = true, length = 20)
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
//
//    @Basic
//    @javax.persistence.Column(name ="USER_NAME", nullable = true, insertable = true, updatable = true, length = 50)
//    public  String getUsername(){return username;}
//
//    public void setUsername(String username) { this.username = username; }

    @Basic
    @javax.persistence.Column(name = "USER_ADDRESS", nullable = true, insertable = true, updatable = true, length = 100)
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Basic
    @javax.persistence.Column(name = "CREATE_DATE", nullable = true, insertable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @javax.persistence.Column(name = "CLASSIFY_ONE", nullable = true, insertable = true, updatable = true,length = 50)
    public String getClassifyOne() {
        return classifyOne;
    }

    public void setClassifyOne(String classifyOne) {
        this.classifyOne = classifyOne;
    }

    @Basic
    @javax.persistence.Column(name = "CLASSIFY_TWO", nullable = true, insertable = true, updatable = true,length = 50)
    public String getClassifyTwo() {
        return classifyTwo;
    }

    public void setClassifyTwo(String classifyTwo) {
        this.classifyTwo = classifyTwo;
    }

    @Basic
    @javax.persistence.Column(name = "CLASSIFY_THREE", nullable = true, insertable = true, updatable = true,length = 50)
    public String getClassifyThree() {
        return classifyThree;
    }

    public void setClassifyThree(String classifyThree) {
        this.classifyThree = classifyThree;
    }

    @Basic
    @javax.persistence.Column(name = "MODE", nullable = true, insertable = true, updatable = true,length = 50)
    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Basic
    @javax.persistence.Column(name = "REPAIR_DATE", nullable = true, insertable = true, updatable = true,length=30)
    public String getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(String repairDate) {
        this.repairDate = repairDate;
    }

    @Basic
    @javax.persistence.Column(name = "ROOM_LOCATION", nullable = true, insertable = true, updatable = true,length = 50)
    public String getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(String roomLocation) {
        this.roomLocation = roomLocation;
    }

    @Basic
    @javax.persistence.Column(name = "STATE", nullable = true, insertable = true, updatable = true,length = 50)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @javax.persistence.Column(name = "DUTY", nullable = true, insertable = true, updatable = true,length = 20)
    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    @Basic
    @javax.persistence.Column(name = "PROBLEM_LEVEL", nullable = true, insertable = true, updatable = true,length = 20)
    public String getProblemLevel() {
        return problemLevel;
    }

    public void setProblemLevel(String problemLevel) {
        this.problemLevel = problemLevel;
    }

    @Basic
    @javax.persistence.Column(name = "REPAIR_WAY", nullable = true, insertable = true, updatable = true,length = 20)
    public String getRepairWay() {
        return repairWay;
    }

    public void setRepairWay(String repairWay) {
        this.repairWay = repairWay;
    }

    @Basic
    @javax.persistence.Column(name = "CONTENT", nullable = true, insertable = true, updatable = true,length = 500)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @javax.persistence.Column(name = "TASK_DATE", nullable = true, insertable = true, updatable = true)
    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    @Basic
    @javax.persistence.Column(name = "DEAL_WAY", nullable = true, insertable = true, updatable = true,length = 1000)
    public String getDealWay() {
        return dealWay;
    }

    public void setDealWay(String dealWay) {
        this.dealWay = dealWay;
    }

    @Basic
    @javax.persistence.Column(name = "DEAL_MODE", nullable = true, insertable = true, updatable = true,length = 50)
    public String getDealMode() {
        return dealMode;
    }

    public void setDealMode(String dealMode) {
        this.dealMode = dealMode;
    }

    @Basic
    @javax.persistence.Column(name = "DUTY_COMPANY_ONE", nullable = true, insertable = true, updatable = true,length = 50)
    public String getDutyCompanyOne() {
        return dutyCompanyOne;
    }

    public void setDutyCompanyOne(String dutyCompanyOne) {
        this.dutyCompanyOne = dutyCompanyOne;
    }

    @Basic
    @javax.persistence.Column(name = "DUTY_COMPANY_TWO", nullable = true, insertable = true, updatable = true,length = 50)
    public String getDutyCompanyTwo() {
        return dutyCompanyTwo;
    }

    public void setDutyCompanyTwo(String dutyCompanyTwo) {
        this.dutyCompanyTwo = dutyCompanyTwo;
    }

    @Basic
    @javax.persistence.Column(name = "DUTY_COMPANY_THREE", nullable = true, insertable = true, updatable = true,length = 50)
    public String getDutyCompanyThree() {
        return dutyCompanyThree;
    }

    public void setDutyCompanyThree(String dutyCompanyThree) {
        this.dutyCompanyThree = dutyCompanyThree;
    }

    @Basic
    @javax.persistence.Column(name = "REPAIR_COMPANY", nullable = true, insertable = true, updatable = true,length = 50)
    public String getRepairCompany() {
        return repairCompany;
    }

    public void setRepairCompany(String repairCompany) {
        this.repairCompany = repairCompany;
    }

    @Basic
    @javax.persistence.Column(name = "DUTY_TASK_DATE", nullable = true, insertable = true, updatable = true)
    public Date getDutyTaskDate() {
        return dutyTaskDate;
    }

    public void setDutyTaskDate(Date dutyTaskDate) {
        this.dutyTaskDate = dutyTaskDate;
    }

    @Basic
    @javax.persistence.Column(name = "DUTY_RETURN_DATE", nullable = true, insertable = true, updatable = true)
    public Date getDutyReturnDate() {
        return dutyReturnDate;
    }

    public void setDutyReturnDate(Date dutyReturnDate) {
        this.dutyReturnDate = dutyReturnDate;
    }

    @Basic
    @javax.persistence.Column(name = "REPAIR_MANAGER", nullable = true, insertable = true, updatable = true,length = 50)
    public String getRepairManager() {
        return repairManager;
    }

    public void setRepairManager(String repairManager) {
        this.repairManager = repairManager;
    }

    @Basic
    @javax.persistence.Column(name = "DEAL_PEOPLE", nullable = true, insertable = true, updatable = true,length = 50)
    public String getDealPeople() {
        return dealPeople;
    }

    public void setDealPeople(String dealPeople) {
        this.dealPeople = dealPeople;
    }

    @Basic
    @javax.persistence.Column(name = "DEAL_PHONE", nullable = true, insertable = true, updatable = true,length = 20)
    public String getDealPhone() {
        return dealPhone;
    }

    public void setDealPhone(String dealPhone) {
        this.dealPhone = dealPhone;
    }

    @Basic
    @javax.persistence.Column(name = "DEAL_PEOPLE_TWO", nullable = true, insertable = true, updatable = true,length = 50)
    public String getDealPeopleTwo() {
        return dealPeopleTwo;
    }

    public void setDealPeopleTwo(String dealPeopleTwo) {
        this.dealPeopleTwo = dealPeopleTwo;
    }

    @Basic
    @javax.persistence.Column(name = "DEAL_PHONE_TWO", nullable = true, insertable = true, updatable = true,length = 20)
    public String getDealPhoneTwo() {
        return dealPhoneTwo;
    }

    public void setDealPhoneTwo(String dealPhoneTwo) {
        this.dealPhoneTwo = dealPhoneTwo;
    }

    @Basic
    @javax.persistence.Column(name = "FIRST_VISIT_DATE", nullable = true, insertable = true, updatable = true)
    public Date getFirstVisitDate() {
        return firstVisitDate;
    }

    public void setFirstVisitDate(Date firstVisitDate) {
        this.firstVisitDate = firstVisitDate;
    }

    @Basic
    @javax.persistence.Column(name = "BACKUP_VISIT_DATE", nullable = true, insertable = true, updatable = true)
    public Date getBackupVisitDate() {
        return backupVisitDate;
    }

    public void setBackupVisitDate(Date backupVisitDate) {
        this.backupVisitDate = backupVisitDate;
    }

    @Basic
    @javax.persistence.Column(name = "DEAL_STATE", nullable = true, insertable = true, updatable = true,length = 20)
    public String getDealState() {
        return dealState;
    }

    public void setDealState(String dealState) {
        this.dealState = dealState;
    }

    @Basic
    @javax.persistence.Column(name = "DEAL_RESULT", nullable = true, insertable = true, updatable = true,length = 30)
    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
    }

    @Basic
    @javax.persistence.Column(name = "DEAL_COMPLETE_DATE", nullable = true, insertable = true, updatable = true)
    public Date getDealCompleteDate() {
        return dealCompleteDate;
    }

    public void setDealCompleteDate(Date dealCompleteDate) {
        this.dealCompleteDate = dealCompleteDate;
    }

    @Basic
    @javax.persistence.Column(name = "VISIT_OPINION", nullable = true, insertable = true, updatable = true,length = 200)
    public String getVisitOpinion() {
        return visitOpinion;
    }

    public void setVisitOpinion(String visitOpinion) {
        this.visitOpinion = visitOpinion;
    }

    @Basic
    @javax.persistence.Column(name = "VISIT_EVALUATE", nullable = true, insertable = true, updatable = true,length = 50)
    public String getVisitEvaluate() {
        return visitEvaluate;
    }

    public void setVisitEvaluate(String visitEvaluate) {
        this.visitEvaluate = visitEvaluate;
    }

    @Basic
    @javax.persistence.Column(name = "VISIT_DATE", nullable = true, insertable = true, updatable = true)
    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    @Basic
    @javax.persistence.Column(name = "VISIT_TYPE", nullable = true, insertable = true, updatable = true,length = 20)
    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    @Basic
    @javax.persistence.Column(name = "MODIFY_DATE", nullable = true, insertable = true, updatable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    @Basic
    @javax.persistence.Column(name = "DUTY_COMPANY_ONE_USER",length = 200)
    public String getDutyCompanyOneUser() {
        return dutyCompanyOneUser;
    }

    public void setDutyCompanyOneUser(String dutyCompanyOneUser) {
        this.dutyCompanyOneUser = dutyCompanyOneUser;
    }

    @Basic
    @javax.persistence.Column(name = "SEND_NAME",length = 200)
    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }
    @Basic
    @javax.persistence.Column(name = "SEND_DATE", length = 200)
    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
    @Basic
    @javax.persistence.Column(name = "CREATE_USER_NAME",length = 200)
    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }
    @Basic
    @javax.persistence.Column(name = "CREATE_USER_PHONE",length = 200)
    public String getCreateUserPhone() {
        return createUserPhone;
    }

    public void setCreateUserPhone(String createUserPhone) {
        this.createUserPhone = createUserPhone;
    }
    @Basic
    @javax.persistence.Column(name = "UPDATE_USER_NAME", length = 200)
    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
    @Basic
    @javax.persistence.Column(name = "UPDATE_USER_DATE",length = 200)
    public Date getUpdateUserDate() {
        return updateUserDate;
    }

    public void setUpdateUserDate(Date updateUserDate) {
        this.updateUserDate = updateUserDate;
    }
    @Basic
    @javax.persistence.Column(name = "REPAIR_MANAGER_ID", length = 200)
    public String getRepairManagerId() {
        return repairManagerId;
    }

    public void setRepairManagerId(String repairManagerId) {
        this.repairManagerId = repairManagerId;
    }
    @Basic
    @javax.persistence.Column(name = "DUTY_COMPANY_ONE_USER_ID", length = 200)
    public String getDutyCompanyOneUserId() {
        return dutyCompanyOneUserId;
    }

    public void setDutyCompanyOneUserId(String dutyCompanyOneUserId) {
        this.dutyCompanyOneUserId = dutyCompanyOneUserId;
    }
    @Basic
    @Column(name = "X_COORDINATES", precision = 16, scale = 4)
    public BigDecimal getxCoordinates() {
        return xCoordinates;
    }

    public void setxCoordinates(BigDecimal xCoordinates) {
        this.xCoordinates = xCoordinates;
    }

    @Basic
    @Column(name = "Y_COORDINATES", precision = 16, scale = 4)
    public BigDecimal getyCoordinates() {
        return yCoordinates;
    }

    public void setyCoordinates(BigDecimal yCoordinates) {
        this.yCoordinates = yCoordinates;
    }
    @Basic
    @javax.persistence.Column(name = "APP_ID", unique = true ,length = 100)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Basic
    @Column(name = "FAIL_TYPE",length = 50)
    public String getFailType() {
        return failType;
    }

    public void setFailType(String failType) {
        this.failType = failType;
    }

    @Basic
    @Column(name = "FAIL_NUM",length = 100)
    public Integer getFailNum() {
        return failNum;
    }

    public void setFailNum(Integer failNum) {
        this.failNum = failNum;
    }

    @Basic
    @javax.persistence.Column(name = "REMINDER_TIME", length = 200)
    public Date getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(Date reminderTime) {
        this.reminderTime = reminderTime;
    }

    @Basic
    @javax.persistence.Column(name = "THUMBNAILS", nullable = true, insertable = true, updatable = true,length = 100)
    public String getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(String thumbnails) {
        this.thumbnails = thumbnails;
    }
    @Basic
    @javax.persistence.Column(name = "PREDICTED_DATE",length = 200)
    public Date getPredictedDate() {
        return predictedDate;
    }

    public void setPredictedDate(Date predictedDate) {
        this.predictedDate = predictedDate;
    }
    @Basic
    @javax.persistence.Column(name = "CLASSIFY_ONE_NAME", nullable = true, insertable = true, updatable = true,length = 200)
    public String getClassifyOneName() {
        return classifyOneName;
    }

    public void setClassifyOneName(String classifyOneName) {
        this.classifyOneName = classifyOneName;
    }
    @Basic
    @javax.persistence.Column(name = "CLASSIFY_TWO_NAME", nullable = true, insertable = true, updatable = true,length = 200)
    public String getClassifyTwoName() {
        return classifyTwoName;
    }

    public void setClassifyTwoName(String classifyTwoName) {
        this.classifyTwoName = classifyTwoName;
    }
    @Basic
    @javax.persistence.Column(name = "CLASSIFY_THREE_NAME", nullable = true, insertable = true, updatable = true,length = 200)
    public String getClassifyThreeName() {
        return classifyThreeName;
    }

    public void setClassifyThreeName(String classifyThreeName) {
        this.classifyThreeName = classifyThreeName;
    }
}
