package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 报修单 登录记录人员设备信息
 * Created by Magic on 2017/7/11.
 */
@Entity
@Table(name = "message_push_user")
public class MessagePushUserEntity {
    private String registrationId;//设备码 唯一
    private String alias;//别名
    private String tag;//标签
    private String userId;//用户id
    private String userName;//用户账号
    private String staffName;//用户姓名
    private String type;// 1.IOS 2.Android
    private String messageType;//消息模块消息类型 1.交付app 2.工程APP 3.安全app
    private Date createDate;//创建时间
    private Date modifyDate;//修改时间


    @Id
    @Column(name="REGISTRATION_ID",nullable = false,insertable = true,updatable = false,length = 100)
    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
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
    @Column(name="USER_NAME",length = 100)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Basic
    @Column(name="STAFF_NAME",length = 200)
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Basic
    @Column(name="TYPE",length = 10)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    @Column(name="ALIAS",length = 200)
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    @Basic
    @Column(name="TAG",length = 200)
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    @Basic
    @Column(name = "MODIFY_DATE", nullable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    @Basic
    @Column(name = "CREATE_DATE", nullable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
