package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by liudongxin on 2016/1/14.
 * 物业报修主表
 */
@Entity
@javax.persistence.Table(name = "property_repair")
public class PropertyRepairEntity {
    private Long id;
    private String repairId;//报修id
    private String content;//内容
    private String createBy;//创建人(业主)
    private Date createDate;//创建日期
    private String modifyBy;//修改人
    private Date modifyDate;//修改日期
    private Date completeDate;//完成日期(用于判断工单未评价是否超过24小时)
    private String userName;//报修人姓名
    private String userPhone;//报修人电话
    private String userAddress;//报修人地址
    private String regionId;//区域id
    private String regionName;//地区、区域名称
    private String types;//类型：0：未完成报修;1：已完成报修;3：已删除(逻辑)
    private String state;//状态:已创建;处理中;未评价;已完成;已关闭;已评价
    private String userId;//用户id(接单人)
    private String customerId;//超过24小时未评价，待回访接单人
    /**
     * 维修任务状态：0为用户提交报修(业主);4为自主抢单(员工端)/接单(员工端);
     * 6为退单(员工端);7为维修中(员工端/管理端)/继续维修(员工端/业主点击则从状态0开始);9为暂停维修(员工端);
     * 10为维修完成(员工端：维修人员点击，业主可以评价);12为待客服回访(24小时后业主未评价);
     * 13为回访完成(结束);14为业主评价(业主)/终止维修(业主)/结束;
     */
    /**
     * 投诉任务状态：1为用户提交投诉(业主);2为自主抢单(员工端)/接单(员工端);
     * 3为退单(员工端);5为处理中(员工端/管理端)/继续处理(员工端/业主点击则从状态0开始);8为暂停处理(员工端);
     * 11为处理完成(员工端：维修人员点击，业主可以评价);15为客服回访(员工端：客服);
     * 16为回访完成(结束：可评价，也可以不评价);17为业主评价(业主)/终止处理(业主)/结束;
     */
    private String taskStatus;//任务状态
    private String memo;//备注：报修、投诉、维修、客服、秩序、环境
    private String repairWay;//报修方式：APP/微信/呼叫中心/网站/项目前台/物业单据
    private String department;//部门
    private String roomId;//房间id

    @Id
    @Column(name = "ID",nullable = false, length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @javax.persistence.Column(name = "REPAIR_ID", nullable = false, insertable = true, updatable = true, length = 50)
    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    @Basic
    @javax.persistence.Column(name = "CONTENT", nullable = true, insertable = true, updatable = true, length = 1000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @javax.persistence.Column(name = "CREATE_BY", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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
    @javax.persistence.Column(name = "MODIFY_BY", nullable = true, insertable = true, updatable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
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
    @javax.persistence.Column(name = "COMPLETE_DATE", nullable = true, insertable = true, updatable = true)
    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    @Basic
    @javax.persistence.Column(name = "STATE", nullable = true, insertable = true, updatable = true, length = 10)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @javax.persistence.Column(name = "USER_ID", nullable = true, insertable = true, updatable = true, length = 50)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @javax.persistence.Column(name = "REGION_ID", nullable = true, insertable = true, updatable = true, length = 50)
    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    @Basic
    @javax.persistence.Column(name = "REGION_NAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Basic
    @javax.persistence.Column(name = "TYPES", nullable = true, insertable = true, updatable = true, length = 10)
    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    @Basic
    @javax.persistence.Column(name = "USER_NAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @javax.persistence.Column(name = "USER_PHONE", nullable = true, insertable = true, updatable = true, length = 12)
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Basic
    @javax.persistence.Column(name = "USER_ADDRESS", nullable = true, insertable = true, updatable = true, length = 100)
    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    @Basic
    @javax.persistence.Column(name = "CUSTOMER_ID", nullable = true, insertable = true, updatable = true, length = 50)
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Basic
    @javax.persistence.Column(name = "TASK_STATUS", nullable = true, insertable = true, updatable = true, length = 10)
    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Basic
    @javax.persistence.Column(name = "MEMO", nullable = true, insertable = true, updatable = true, length = 100)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Basic
    @javax.persistence.Column(name = "REPAIR_WAY", nullable = true, insertable = true, updatable = true, length = 30)
    public String getRepairWay() {
        return repairWay;
    }

    public void setRepairWay(String repairWay) {
        this.repairWay = repairWay;
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
}