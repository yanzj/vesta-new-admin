package com.maxrocky.vesta.domain.model;

        import javax.persistence.*;
        import java.math.BigDecimal;
        import java.util.Date;

/**
 * Created by liudongxin on 2016/4/23.
 * 用于保存CRM整改单数据
 */
@Entity
@javax.persistence.Table(name = "property_rectify_crm")
public class PropertyRectifyCRMEntity {
    private String rectifyId;//整改单号
    private String department;//部门,即派单部门
    private String roomId;//房间id
    private String roomNum;//房间编码
    private String planNum;//房间计划编码
    private Date acceptanceDate;//内部预验房日期
    private String problemType;//问题类型
    private String classifyOne;//一级分类
    private String classifyTwo;//二级分类
    private String classifyThree;//三级分类
    private Date registerDate;//登记日期
    private String rectifyState;//整改状态
    private String roomLocation;//房屋位置
    private String supplier;//供应商
    private Date rectifyCompleteDate;//整改完成时间
    private Date realityDate;//实际完成时间
    private String problemDescription;//问题描述
    private String dealResult;//处理结果
    private Date createDate;//创建时间
    private Date modifyDate;//修改时间
    private String createBy;//创建人

    private String createPhone;//创建人联系电话
    private String planType;//活动类型
    private String repairManager;//整改负责人
    private String repairPhone;//整改人联系电话
    private Date dutyTaskDate;//接单时间
    private Date limitDate;//整改时限
    private BigDecimal xCoordinates;//X坐标
    private BigDecimal yCoordinates;//Y坐标
    private String projectNum;//项目编码
    private String repairDescription;//整改描述
    private String updateFlag;//更新标志：0新建；1更新
    private long id;//自增ID
    private String serialNumber;//问题编号

    private String dealPeople;//处理人
    private String repairManagerId;//维修负责人
    private String supplierID;//责任单位负责人
    private String supplierName;//责任单位负责人
    private String sendName;//派单人姓名
    private Date sendDate;//派单时间
    private String updateUserName;//填报人
    private Date updateUserDate;// 填报时间
    private Date reminderTime;//提醒时间


    private String createByName;//创建人姓名(非账号)
    private String dealPeopleName;//处理人姓名（非账号）
    private String senUserName;//派单人姓名（非账号）

    private String appId;//app端传入id


    private String failType;//失败标识   1失败  0或空 成功
    private int failNum;//失败次数

    private String forceClose;//强制关闭 原因
    private String forceCloseUserName;  //强制关闭 员工账号
    private String forceCloseName;  //强制关闭 人员名字
    private Date forceCloseDate;    //强制关闭时间

    private Date  toVoidDate;//作废时间
    private String toVoidBy;//作废人
    @Basic
    @javax.persistence.Column(name = "RECTIFY_ID", nullable = false, insertable = true, updatable = true, length = 100)
    public String getRectifyId() {
        return rectifyId;
    }

    public void setRectifyId(String rectifyId) {
        this.rectifyId = rectifyId;
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
    @javax.persistence.Column(name = "PLAN_NUM", nullable = true, insertable = true, updatable = true, length = 50)
    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }

    @Basic
    @javax.persistence.Column(name = "ACCEPTANCE_DATE", nullable = true, insertable = true, updatable = true)
    public Date getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(Date acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    @Basic
    @javax.persistence.Column(name = "PROBLEM_TYPE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    @Basic
    @javax.persistence.Column(name = "CLASSIFY_ONE", nullable = true, insertable = true, updatable = true, length = 50)
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
    @javax.persistence.Column(name = "REGISTER_DATE", nullable = true, insertable = true, updatable = true)
    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Basic
    @javax.persistence.Column(name = "RECTIFY_STATE", nullable = true, insertable = true, updatable = true,length = 20)
    public String getRectifyState() {
        return rectifyState;
    }

    public void setRectifyState(String rectifyState) {
        this.rectifyState = rectifyState;
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
    @javax.persistence.Column(name = "SUPPLIER", nullable = true, insertable = true, updatable = true,length = 50)
    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Basic
    @javax.persistence.Column(name = "RECTIFY_COMPLETE_DATE", nullable = true, insertable = true, updatable = true)
    public Date getRectifyCompleteDate() {
        return rectifyCompleteDate;
    }

    public void setRectifyCompleteDate(Date rectifyCompleteDate) {
        this.rectifyCompleteDate = rectifyCompleteDate;
    }

    @Basic
    @javax.persistence.Column(name = "REALITY_DATE", nullable = true, insertable = true, updatable = true)
    public Date getRealityDate() {
        return realityDate;
    }

    public void setRealityDate(Date realityDate) {
        this.realityDate = realityDate;
    }

    @Basic
    @javax.persistence.Column(name = "PROBLEM_DESCRIPTION", nullable = true, insertable = true, updatable = true,length = 200)
    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    @Basic
    @javax.persistence.Column(name = "DEAL_RESULT", nullable = true, insertable = true, updatable = true,length = 500)
    public String getDealResult() {
        return dealResult;
    }

    public void setDealResult(String dealResult) {
        this.dealResult = dealResult;
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
    @javax.persistence.Column(name = "MODIFY_DATE", nullable = true, insertable = true, updatable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Basic
    @Column(name = "PLAN_TYPE",length = 50)
    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    @Basic
    @Column(name = "REPAIR_MANAGER",length = 255)
    public String getRepairManager() {
        return repairManager;
    }

    public void setRepairManager(String repairManager) {
        this.repairManager = repairManager;
    }

    @Basic
    @Column(name = "DUTYTASK_DATE")
    public Date getDutyTaskDate() {
        return dutyTaskDate;
    }

    public void setDutyTaskDate(Date dutyTaskDate) {
        this.dutyTaskDate = dutyTaskDate;
    }

    @Basic
    @Column(name = "LIMIT_DATE")
    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    @Basic
    @Column(name = "CREATE_BY",length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_PHONE",length = 50)
    public String getCreatePhone() {
        return createPhone;
    }

    public void setCreatePhone(String createPhone) {
        this.createPhone = createPhone;
    }

    @Basic
    @Column(name = "REPAIR_PHONE",length = 50)
    public String getRepairPhone() {
        return repairPhone;
    }

    public void setRepairPhone(String repairPhone) {
        this.repairPhone = repairPhone;
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
    @Column(name = "PROJECT_NUM",length = 50)
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    @Basic
    @Column(name = "REPAIR_DESCRIPTION",length = 255)
    public String getRepairDescription() {
        return repairDescription;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }

    @Basic
    @Column(name = "UPDATE_FLAG",length = 50)
    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",length = 50,nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "SERIAL_NUMBER",length = 100)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Basic
    @javax.persistence.Column(name = "SEND_NAME", length = 200)
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
    @javax.persistence.Column(name = "REPAIR_MANAGER_ID",length = 200)
    public String getRepairManagerId() {
        return repairManagerId;
    }

    public void setRepairManagerId(String repairManagerId) {
        this.repairManagerId = repairManagerId;
    }
    @Basic
    @javax.persistence.Column(name = "SUPPLIER_ID",length = 200)
    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }
    @Basic
    @javax.persistence.Column(name = "SUPPLIER_NAME",length = 200)

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Basic
    @javax.persistence.Column(name = "DEAL_PEOPLE", length = 200)
    public String getDealPeople() {
        return dealPeople;
    }

    public void setDealPeople(String dealPeople) {
        this.dealPeople = dealPeople;
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
    @javax.persistence.Column(name = "CREATE_BY_NAME", length = 100)
    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }
    @Basic
    @javax.persistence.Column(name = "DEAL_PEOPLE_NAME", length = 100)
    public String getDealPeopleName() {
        return dealPeopleName;
    }

    public void setDealPeopleName(String dealPeopleName) {
        this.dealPeopleName = dealPeopleName;
    }
    @Basic
    @javax.persistence.Column(name = "SEND_USER_NAME", length = 100)
    public String getSenUserName() {
        return senUserName;
    }

    public void setSenUserName(String senUserName) {
        this.senUserName = senUserName;
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
    public int getFailNum() {
        return failNum;
    }

    public void setFailNum(int failNum) {
        this.failNum = failNum;
    }
    @Basic
    @Column(name = "FORCE_CLOSE_USERNAME",length = 100)
    public String getForceCloseUserName() {
        return forceCloseUserName;
    }

    public void setForceCloseUserName(String forceCloseUserName) {
        this.forceCloseUserName = forceCloseUserName;
    }
    @Basic
    @Column(name = "FORCE_CLOSE_NAME",length = 100)
    public String getForceCloseName() {
        return forceCloseName;
    }

    public void setForceCloseName(String forceCloseName) {
        this.forceCloseName = forceCloseName;
    }
    @Basic
    @Column(name = "FORCE_CLOSE_DATE",length = 100)
    public Date getForceCloseDate() {
        return forceCloseDate;
    }

    public void setForceCloseDate(Date forceCloseDate) {
        this.forceCloseDate = forceCloseDate;
    }
    @Basic
    @Column(name = "FORCE_CLOSE",length = 1000)
    public String getForceClose() {
        return forceClose;
    }

    public void setForceClose(String forceClose) {
        this.forceClose = forceClose;
    }
    @Basic
    @javax.persistence.Column(name = "TO_VOID_DATE", length = 100)
    public Date getToVoidDate() {
        return toVoidDate;
    }

    public void setToVoidDate(Date toVoidDate) {
        this.toVoidDate = toVoidDate;
    }
    @Basic
    @javax.persistence.Column(name = "TO_VOID_BY", length = 100)
    public String getToVoidBy() {
        return toVoidBy;
    }

    public void setToVoidBy(String toVoidBy) {
        this.toVoidBy = toVoidBy;
    }
}
