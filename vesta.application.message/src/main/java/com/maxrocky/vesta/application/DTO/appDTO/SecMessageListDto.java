package com.maxrocky.vesta.application.DTO.appDTO;

import java.util.Date;

/**
 * Created by zhanghj on 2016/1/20.
 */
public class SecMessageListDto {

    private String messageDetailId;       //消息Id

    private String messageTitle;    //消息标题

    private String messageContent;      //消息内容

    private String messageCreateTime;     //消息创建时间

    private String messageTargetUrl;        //消息目标网址

    private String messageSenderName;            //消息发件人

    private String messageType;                 //消息类型  员工端：公区，户内，通知，会议，投诉；业主端：分类一，分类二

    private String messageTargetId;//消息目标信息Id

    private String userId;//接收人Id

    private String targetCreateTime;//消息创建时间

    private String messagePushStatus;//消息推送状态   0-未推送，1-已推送

    private String messagePushTime;//消息推送时间

    private String messageReadStatus;//消息已读状态   0-未读，1-已读

    private String messageReadTime;//消息读取时间

    public String getMessageDetailId() {
        return messageDetailId;
    }

    public void setMessageDetailId(String messageDetailId) {
        this.messageDetailId = messageDetailId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageCreateTime() {
        return messageCreateTime;
    }

    public void setMessageCreateTime(String messageCreateTime) {
        this.messageCreateTime = messageCreateTime;
    }

    public String getMessageTargetUrl() {
        return messageTargetUrl;
    }

    public void setMessageTargetUrl(String messageTargetUrl) {
        this.messageTargetUrl = messageTargetUrl;
    }

    public String getMessageSenderName() {
        return messageSenderName;
    }

    public void setMessageSenderName(String messageSenderName) {
        this.messageSenderName = messageSenderName;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageTargetId() {
        return messageTargetId;
    }

    public void setMessageTargetId(String messageTargetId) {
        this.messageTargetId = messageTargetId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTargetCreateTime() {
        return targetCreateTime;
    }

    public void setTargetCreateTime(String targetCreateTime) {
        this.targetCreateTime = targetCreateTime;
    }

    public String getMessagePushStatus() {
        return messagePushStatus;
    }

    public void setMessagePushStatus(String messagePushStatus) {
        this.messagePushStatus = messagePushStatus;
    }

    public String getMessagePushTime() {
        return messagePushTime;
    }

    public void setMessagePushTime(String messagePushTime) {
        this.messagePushTime = messagePushTime;
    }

    public String getMessageReadStatus() {
        return messageReadStatus;
    }

    public void setMessageReadStatus(String messageReadStatus) {
        this.messageReadStatus = messageReadStatus;
    }

    public String getMessageReadTime() {
        return messageReadTime;
    }

    public void setMessageReadTime(String messageReadTime) {
        this.messageReadTime = messageReadTime;
    }
}
