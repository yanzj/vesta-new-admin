package com.maxrocky.vesta.application.DTO.adminDTO;

import java.util.Date;

/**
 * Created by zhanghj on 2016/1/14.
 */
public class MessageTargetDTO {

    private String messageTargetId;//消息目标信息Id

    private String messageDetailId;//消息详情Id

    private String messageType;//消息标题Id

    private String userId;//接收人Id

    private Date messageCreateTime;//消息创建时间

    private String messagePushStatus;//消息推送状态

    private Date messagePushTime;//消息推送时间

    private String messageReadStatus;//消息已读状态

    private Date messageReadTime;//消息读取时间

    private String messageDeleteStatue;//消息删除状态

    private Date messageDeleteTime;//消息删除时间

    private String userType;//用户类型

    private String isPush;//是否推送

    public String getMessageTargetId() {
        return messageTargetId;
    }

    public void setMessageTargetId(String messageTargetId) {
        this.messageTargetId = messageTargetId;
    }

    public String getMessageDetailId() {
        return messageDetailId;
    }

    public void setMessageDetailId(String messageDetailId) {
        this.messageDetailId = messageDetailId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getMessageCreateTime() {
        return messageCreateTime;
    }

    public void setMessageCreateTime(Date messageCreateTime) {
        this.messageCreateTime = messageCreateTime;
    }

    public String getMessagePushStatus() {
        return messagePushStatus;
    }

    public void setMessagePushStatus(String messagePushStatus) {
        this.messagePushStatus = messagePushStatus;
    }

    public Date getMessagePushTime() {
        return messagePushTime;
    }

    public void setMessagePushTime(Date messagePushTime) {
        this.messagePushTime = messagePushTime;
    }

    public String getMessageReadStatus() {
        return messageReadStatus;
    }

    public void setMessageReadStatus(String messageReadStatus) {
        this.messageReadStatus = messageReadStatus;
    }

    public Date getMessageReadTime() {
        return messageReadTime;
    }

    public void setMessageReadTime(Date messageReadTime) {
        this.messageReadTime = messageReadTime;
    }

    public String getMessageDeleteStatue() {
        return messageDeleteStatue;
    }

    public void setMessageDeleteStatue(String messageDeleteStatue) {
        this.messageDeleteStatue = messageDeleteStatue;
    }

    public Date getMessageDeleteTime() {
        return messageDeleteTime;
    }

    public void setMessageDeleteTime(Date messageDeleteTime) {
        this.messageDeleteTime = messageDeleteTime;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getIsPush() {
        return isPush;
    }

    public void setIsPush(String isPush) {
        this.isPush = isPush;
    }
}
