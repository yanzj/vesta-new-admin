package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 极光推送
 * 消息推送记录 报修单
 * Created by Magic on 2017/7/13.
 */
@Entity
@Table(name = "message_push_repair")
public class MessagePushRepairEntity {
    private String messageMouldId;      //消息模板Id
    private Date messageCreateTime;     //消息创建时间
    private String pushStatus;//消息推送状态   0-未推送，1-已推送
    private String repairId;//报修单Id
    private String projectNum;//项目编码
    private String userId;//用户id
    private String staffName;//用户
    private String alias;//别名
    @Id
    @Column(name="MESSAGEMOULD_ID",nullable = false,insertable = true,updatable = false,length = 100)
    public String getMessageMouldId() {
        return messageMouldId;
    }

    public void setMessageMouldId(String messageMouldId) {
        this.messageMouldId = messageMouldId;
    }
    @Basic
    @Column(name="MESSAGE_CREATETIME",length = 32)
    public Date getMessageCreateTime() {
        return messageCreateTime;
    }

    public void setMessageCreateTime(Date messageCreateTime) {
        this.messageCreateTime = messageCreateTime;
    }
    @Basic
    @Column(name="PUSH_STATUS",length = 10)
    public String getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(String pushStatus) {
        this.pushStatus = pushStatus;
    }
    @Basic
    @Column(name="REPAIR_ID",length = 100)
    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }
    @Basic
    @Column(name="PROJECT_NUM",length = 100)
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }
    @Basic
    @Column(name="USER_ID",length = 100)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Basic
    @Column(name="ALIAS",length = 200)
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    @Basic
    @Column(name="STAFF_NAME",length = 200)
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}
