package com.maxrocky.vesta.domain.model;



import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhanghj on 2016/1/13.
 */

@Entity
@Table(name ="message_detail")
public class MessageDetailEntity {

    private String messageDetailId;       //消息Id

    private String messageTitle;    //消息标题

    private String messageContent;      //消息内容

    private Date messageCreateTime;     //消息创建时间

    private String messageTargetUrl;        //消息目标网址

    private String messageSenderName;            //消息发件人

    private String messageType;                 //消息类型  员工端：公区，户内，通知，会议，投诉；业主端：分类一，分类二

    @Id
    @Column(name="MESSAGE_DETAILID",nullable = false,insertable = true,updatable = false,length = 32)
    public String getMessageDetailId() {
        return messageDetailId;
    }

    public void setMessageDetailId(String messageDetailId) {
        this.messageDetailId = messageDetailId;
    }

    @Basic
    @Column(name="MESSAGE_TITLE",length = 32)
    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }
    @Basic
    @Column(name="MESSAGE_CONTENT",length = 200)
    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
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
    @Column(name="MESSAGE_TARGETURL",length = 100)
    public String getMessageTargetUrl() {
        return messageTargetUrl;
    }

    public void setMessageTargetUrl(String messageTargetUrl) {
        this.messageTargetUrl = messageTargetUrl;
    }

    @Basic
    @Column(name="MESSAGE_SENDERNAME",length = 100)
    public String getMessageSenderName() {
        return messageSenderName;
    }

    public void setMessageSenderName(String messageSenderName) {
        this.messageSenderName = messageSenderName;
    }
    @Basic
    @Column(name="MESSAGE_TYPE",length = 100)
    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
