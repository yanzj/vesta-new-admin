package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 收藏表
 * Created by liangpc on 2016/6/3.
 */
@Entity
@Table(name = "collection")
public class CollectionEntity {

    private String id;//收藏Id
    private String messageId;//收藏内容Id
    private String messageType;//收藏类型1_公告,2_活动,3_电子杂志
    private String userId;//用户Id
    private Date createDate;//收藏时间

    @Id
    @Column(name = "ID",nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MESSAGE_ID",nullable = true, length = 50)
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "MESSAGE_TYPE",nullable = true, length = 50)
    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Basic
    @Column(name = "USER_ID",nullable = true, length = 50)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "CREATE_DATE",nullable = true, length = 50)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
