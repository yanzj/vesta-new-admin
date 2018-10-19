package com.maxrocky.vesta.application.DTO.adminDTO;

/**
 * Created by zhanghj on 2016/1/15.
 */
public class MessageInsertDTO {

    private  String messageTitle;   //必填！！！，消息标题

    private  String messageContent;     //非必填，消息内容

    private String messageUrl;          //必填！！！，消息跳转网址,其实是对应板块的Id

    private String messageSenderName;       //非必填，发送人称呼

    private String messageUserType;         //必填！！！，发送人类型，业主，员工

    private String messageType;         //必填！！！，消息类型

    private String messageTypeState;        //必填！！！，消息类型对应状态

    private String isPush;     //是否推送，如果false就不推

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

    public String getMessageUrl() {
        return messageUrl;
    }

    public void setMessageUrl(String messageUrl) {
        this.messageUrl = messageUrl;
    }

    public String getMessageSenderName() {
        return messageSenderName;
    }

    public void setMessageSenderName(String messageSenderName) {
        this.messageSenderName = messageSenderName;
    }

    public String getMessageUserType() {
        return messageUserType;
    }

    public void setMessageUserType(String messageUserType) {
        this.messageUserType = messageUserType;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageTypeState() {
        return messageTypeState;
    }

    public void setMessageTypeState(String messageTypeState) {
        this.messageTypeState = messageTypeState;
    }

    public String getIsPush() {
        return isPush;
    }

    public void setIsPush(String isPush) {
        this.isPush = isPush;
    }
}
