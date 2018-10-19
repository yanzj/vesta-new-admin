package com.maxrocky.vesta.application.DTO.adminDTO;

import java.util.Date;

/**
 * Created by zhanghj on 2016/1/14.
 */
public class MessageDetailDTO {

    private String messageDetailId;       //消息Id

    private String messageTitle;    //消息标题Id

    private String messageContent;      //消息内容

    private Date messageCreateTime;     //消息创建时间

    private String messageTargetUrl;        //消息目标网址

    private String messageSenderName;            //消息发件人

    private String messageType;                 //消息类型

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

    public Date getMessageCreateTime() {
        return messageCreateTime;
    }

    public void setMessageCreateTime(Date messageCreateTime) {
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
}
