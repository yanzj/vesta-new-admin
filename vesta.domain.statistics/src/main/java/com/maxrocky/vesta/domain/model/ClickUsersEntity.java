package com.maxrocky.vesta.domain.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by liudongxin on 2016/6/5.
 * 点击人统计
 */
@Entity
@javax.persistence.Table(name = "click_user")
public class ClickUsersEntity {
    private String id;
    private Date createDate;//创建时间
    private Integer clicks;//点击量
    private String userId;//点击人
    private String foreignId;//外键id(菜单统计id)
    private String startTime;
    private String endTime;

    @Id
    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @javax.persistence.Column(name = "CREATE_DATE", nullable = true, insertable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @javax.persistence.Column(name = "CLICKS", nullable = true, insertable = true, updatable = true, length = 10)
    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    @Basic
    @javax.persistence.Column(name = "USER_ID", nullable = true, insertable = true, updatable = true, length = 50)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @javax.persistence.Column(name = "FOREIGN_ID", nullable = true, insertable = true, updatable = true, length = 50)
    public String getForeignId() {
        return foreignId;
    }

    public void setForeignId(String foreignId) {
        this.foreignId = foreignId;
    }

    @Transient
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Transient
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
