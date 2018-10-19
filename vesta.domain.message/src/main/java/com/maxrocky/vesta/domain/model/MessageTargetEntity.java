package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhanghj on 2016/1/13.
 */

@Entity
@Table(name = "message_target")
public class MessageTargetEntity {

    private String messageTargetId;//消息目标信息Id

    private String messageDetailId;//消息详情Id

    private String userId;//接收人Id

    private String messageType;//消息模块消息类型  员工端：公区，户内，通知，会议，投诉；业主端：分类一，分类二

    private Date targetCreateTime;//消息创建时间

    private String messagePushStatus;//消息推送状态   0-未推送，1-已推送

    private Date messagePushTime;//消息推送时间

    private String messageReadStatus;//消息已读状态   0-未读，1-已读

    private Date messageReadTime;//消息读取时间

    private String messageDeleteStatue;//消息删除状态 0-删除，1-未删

    private Date messageDeleteTime;//消息删除时间

    private String userType;//用户类型

    private String messageTokenNum;//手机设备码


    @Id
    @Column(name="MESSAGE_TARGETID",nullable = false,insertable = true,updatable = false,length = 200)
    public String getMessageTargetId() {
        return messageTargetId;
    }

    public void setMessageTargetId(String messageTargetId) {
        this.messageTargetId = messageTargetId;
    }
    @Basic
    @Column(name="MESSAGE_DETAILID",length = 100)
    public String getMessageDetailId() {
        return messageDetailId;
    }

    public void setMessageDetailId(String messageDetailId) {
        this.messageDetailId = messageDetailId;
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
    @Column(name="MESSAGE_TYPE",length = 32)
    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Basic
    @Column(name="TARGET_CREATETIME",length = 32)
    public Date getTargetCreateTime() {
        return targetCreateTime;
    }

    public void setTargetCreateTime(Date targetCreateTime) {
        this.targetCreateTime = targetCreateTime;
    }




    @Basic
    @Column(name="MESSAGE_PUSHSTATUS",length = 32)
    public String getMessagePushStatus() {
        return messagePushStatus;
    }

    public void setMessagePushStatus(String messagePushStatus) {
        this.messagePushStatus = messagePushStatus;
    }
    @Basic
    @Column(name="MESSAGE_PUSHTIME",length = 32)
    public Date getMessagePushTime() {
        return messagePushTime;
    }

    public void setMessagePushTime(Date messagePushTime) {
        this.messagePushTime = messagePushTime;
    }
    @Basic
    @Column(name="MESSAGE_READSTATUS",length = 32)
    public String getMessageReadStatus() {
        return messageReadStatus;
    }

    public void setMessageReadStatus(String messageReadStatus) {
        this.messageReadStatus = messageReadStatus;
    }

    @Basic
    @Column(name="MESSAGE_READTIME",length = 32)
    public Date getMessageReadTime() {
        return messageReadTime;
    }

    public void setMessageReadTime(Date messageReadTime) {
        this.messageReadTime = messageReadTime;
    }

    @Basic
    @Column(name="MESSAGE_DELETESTATUS",length = 32)
    public String getMessageDeleteStatue() {
        return messageDeleteStatue;
    }

    public void setMessageDeleteStatue(String messageDeleteStatue) {
        this.messageDeleteStatue = messageDeleteStatue;
    }

    @Basic
    @Column(name="MESSAGE_DELETETIME",length = 32)
    public Date getMessageDeleteTime() {
        return messageDeleteTime;
    }

    public void setMessageDeleteTime(Date messageDeleteTime) {
        this.messageDeleteTime = messageDeleteTime;
    }

    public String getUserType() {
        return userType;
    }
    @Basic
    @Column(name="MESSAGE_USERTYPE",length = 32)
    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name="MESSAGE_TOKENNUM",length = 100)
    public String getMessageTokenNum() {
        return messageTokenNum;
    }

    public void setMessageTokenNum(String messageTokenNum) {
        this.messageTokenNum = messageTokenNum;
    }
}
