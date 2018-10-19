package com.maxrocky.vesta.domain.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by liudongxin on 2016/1/21.
 * 物业报修任务环节表
 */
@Entity
@javax.persistence.Table(name = "property_repair_task")
public class PropertyRepairTaskEntity {
    private String taskId;//任务id
    private String taskName;//任务名称:提交报修/派工信息/维修进展/业主回访/维修完成(业主端)
    private String taskNode;//任务节点：客服派单/自主抢单/班长派单/退单/维修中/暂停/继续维修/回访等(员工端)
    private String taskContent;//任务内容
    private Date taskDate;//任务时间
    private String taskUserId;//任务人(维修人员)
    private String customerServiceId;//客服人员
    private String taskUserType;//任务用户类型：0为维修人员;1为客服人员
    /**
     * 维修任务状态：0为用户提交报修(业主);4为自主抢单(员工端)/接单(员工端);
     * 6为退单(员工端);7为维修中(员工端/管理端)/继续维修(员工端/业主点击则从状态0开始);9为暂停维修(员工端);
     * 10为维修完成(员工端：维修人员，业主可以评价);12为待客服回访(24小时后业主未评价);
     * 13为回访完成(结束);14为业主评价(业主)/终止维修(业主)/结束;
     */
    /**
     * 投诉任务状态：1为用户提交投诉(业主);2为自主抢单(员工端)/接单(员工端);
     * 3为退单(员工端);5为处理中(员工端/管理端)/继续维修(员工端/业主点击则从状态0开始);8为暂停维修(员工端);
     * 11为处理完成(员工端：维修人员，业主可以评价);15为客服回访(员工端：客服);
     * 16为回访完成(结束：可评价，也可以不评价);17为业主评价(业主)/终止维修(业主)/结束;
     */
    private String taskStatus;//任务状态
    private String taskManagerId;//管理人：维修主管/客服主管
    private String taskUserPhone;//任务人电话
    private String repairId;//报修单id
    private String readStatus;//消息是否已读  1-已读  0-未读

    @Id
    @javax.persistence.Column(name = "TASK_ID", nullable = false, insertable = true, updatable = true, length = 32)
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Basic
    @javax.persistence.Column(name = "TASK_NAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Basic
    @javax.persistence.Column(name = "TASK_NODE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getTaskNode() {
        return taskNode;
    }

    public void setTaskNode(String taskNode) {
        this.taskNode = taskNode;
    }

    @Basic
    @javax.persistence.Column(name = "TASK_CONTENT", nullable = true, insertable = true, updatable = true, length = 200)
    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
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
    @javax.persistence.Column(name = "TASK_USERID", nullable = true, insertable = true, updatable = true, length = 32)
    public String getTaskUserId() {
        return taskUserId;
    }

    public void setTaskUserId(String taskUserId) {
        this.taskUserId = taskUserId;
    }

    @Basic
    @javax.persistence.Column(name = "CUSTOMER_SERVICE_ID", nullable = true, insertable = true, updatable = true, length = 32)
    public String getCustomerServiceId() {
        return customerServiceId;
    }

    public void setCustomerServiceId(String customerServiceId) {
        this.customerServiceId = customerServiceId;
    }

    @Basic
    @javax.persistence.Column(name = "TASK_USER_TYPE", nullable = true, insertable = true, updatable = true, length = 10)
    public String getTaskUserType() {
        return taskUserType;
    }

    public void setTaskUserType(String taskUserType) {
        this.taskUserType = taskUserType;
    }

    @Basic
    @javax.persistence.Column(name = "REPAIR_ID", nullable = true, insertable = true, updatable = true, length = 32)
    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
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
    @javax.persistence.Column(name = "TASK_MANAGER_ID", nullable = true, insertable = true, updatable = true, length = 32)
    public String getTaskManagerId() {
        return taskManagerId;
    }

    public void setTaskManagerId(String taskManagerId) {
        this.taskManagerId = taskManagerId;
    }

    @Basic
    @javax.persistence.Column(name = "TASK_USER_PHONE", nullable = true, insertable = true, updatable = true, length = 12)
    public String getTaskUserPhone() {
        return taskUserPhone;
    }

    public void setTaskUserPhone(String taskUserPhone) {
        this.taskUserPhone = taskUserPhone;
    }

    @Basic
    @javax.persistence.Column(name = "READ_STATUS", nullable = true, insertable = true, updatable = true, length = 50)
    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }
}