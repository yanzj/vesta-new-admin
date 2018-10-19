package com.maxrocky.vesta.domain.model;

/**
 * Created by zhanghj on 2016/1/15.
 */

import javax.persistence.*;

/**
 * 消息模板配置实体，所有数据都要在数据库手动添加，然后要把数据与前段同步
 */

@Entity
@Table(name ="message_mould")
public class MessageMouldEntity {

    private String messageMouldId;      //消息模板Id

    private String messageUserType;     //用户类型 ，1-业主，2-员工

    private String messageUserTypeRemark;   //用户类型备注

    private String messageType;         //消息类型，如业主的通知，申请等，员工的会议，投诉等

    private String messageTypeRemark;       //消息类型备注

    private String messageTypeState;        //消息里该类型下不同的状态，如报修各个状态等

    private String messageTypeStateRemark;  //消息里该类型下不同的状态

    private String messageMouldTitle;           //消息标题模板

    private String messageMouldContent;         //消息内容模板

    private String messageMouldUrl;             //消息跳转页面网址

    private String messageMouldRemark;          //消息模板的总备注，手动更新数据库数据时填写，如注意事项等

    @Id
    @Column(name="MESSAGE_MOULDID",nullable = false,insertable = true,updatable = false,length = 32)
    public String getMessageMouldId() {
        return messageMouldId;
    }

    public void setMessageMouldId(String messageMouldId) {
        this.messageMouldId = messageMouldId;
    }

    @Basic
    @Column(name="MESSAGE_USERTYPE",length = 10)
    public String getMessageUserType() {
        return messageUserType;
    }

    public void setMessageUserType(String messageUserType) {
        this.messageUserType = messageUserType;
    }
    @Basic
    @Column(name="MESSAGE_USERTYPEREMARK",length = 32)
    public String getMessageUserTypeRemark() {
        return messageUserTypeRemark;
    }

    public void setMessageUserTypeRemark(String messageUserTypeRemark) {
        this.messageUserTypeRemark = messageUserTypeRemark;
    }
    @Basic
    @Column(name="MESSAGE_TYPE",length = 10)
    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
    @Basic
    @Column(name="MESSAGE_TYPEREMARK",length = 32)
    public String getMessageTypeRemark() {
        return messageTypeRemark;
    }

    public void setMessageTypeRemark(String messageTypeRemark) {
        this.messageTypeRemark = messageTypeRemark;
    }

    @Basic
    @Column(name="MESSAGE_TYPESTATE",length = 10)
    public String getMessageTypeState() {
        return messageTypeState;
    }

    public void setMessageTypeState(String messageTypeState) {
        this.messageTypeState = messageTypeState;
    }
    @Basic
    @Column(name="MESSAGE_TYPESTATEREMARK",length = 32)
    public String getMessageTypeStateRemark() {
        return messageTypeStateRemark;
    }

    public void setMessageTypeStateRemark(String messageTypeStateRemark) {
        this.messageTypeStateRemark = messageTypeStateRemark;
    }
    @Basic
    @Column(name="MESSAGE_MOULDTITLE",length = 100)
    public String getMessageMouldTitle() {
        return messageMouldTitle;
    }

    public void setMessageMouldTitle(String messageMouldTitle) {
        this.messageMouldTitle = messageMouldTitle;
    }

    @Basic
    @Column(name="MESSAGE_MOULDCONTENT",length = 256)
    public String getMessageMouldContent() {
        return messageMouldContent;
    }

    public void setMessageMouldContent(String messageMouldContent) {
        this.messageMouldContent = messageMouldContent;
    }

    @Basic
    @Column(name="MESSAGE_MOULDURL",length = 50)
    public String getMessageMouldUrl() {
        return messageMouldUrl;
    }

    public void setMessageMouldUrl(String messageMouldUrl) {
        this.messageMouldUrl = messageMouldUrl;
    }

    @Basic
    @Column(name="MESSAGE_MOULDREMARK",length = 100)
    public String getMessageMouldRemark() {
        return messageMouldRemark;
    }

    public void setMessageMouldRemark(String messageMouldRemark) {
        this.messageMouldRemark = messageMouldRemark;
    }
}
