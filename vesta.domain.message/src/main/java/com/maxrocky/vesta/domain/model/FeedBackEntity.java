package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Itzxs on 2018/5/22.
 * 意见反馈实体类
 */
@Entity
@Table(name ="feed_back")
public class FeedBackEntity {
    private String id;
    private String userId;//用户id
    private String content;//反馈内容
    private Date createDate;//创建时间
    private String state;//处理状态

    public FeedBackEntity() {
    }

    public FeedBackEntity(String id, String userId, String content, Date createDate, String state) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.createDate = createDate;
        this.state = state;
    }
    @Id
    @Column(name="ID",nullable = false,insertable = true,updatable = false,length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name="USER_ID",length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name="CONTENT",length = 400)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name="CREATE_DATE",length = 32)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name="STATE",length = 10)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
