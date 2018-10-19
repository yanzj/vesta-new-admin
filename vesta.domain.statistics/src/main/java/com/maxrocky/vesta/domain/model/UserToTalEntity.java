package com.maxrocky.vesta.domain.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by liudongxin on 2016/6/4.
 * 用户统计
 */
@Entity
@javax.persistence.Table(name = "user_total")
public class UserToTalEntity {
    private String id;
    private Date createDate;//创建时间
    private Integer commonUser;//普通用户
    private Integer ownerUser;//业主用户
    private Integer android;//安卓用户
    private Integer ios;//ios用户
    private Integer weChat;//微信用户
    private Integer APP;//app用户
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
    @javax.persistence.Column(name = "COMMON_USER", nullable = true, insertable = true, updatable = true, length = 10)
    public Integer getCommonUser() {
        return commonUser;
    }

    public void setCommonUser(Integer commonUser) {
        this.commonUser = commonUser;
    }

    @Basic
    @javax.persistence.Column(name = "OWNER_USER", nullable = true, insertable = true, updatable = true, length = 10)
    public Integer getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(Integer ownerUser) {
        this.ownerUser = ownerUser;
    }

    @Basic
    @javax.persistence.Column(name = "ANDROID", nullable = true, insertable = true, updatable = true, length = 10)
    public Integer getAndroid() {
        return android;
    }

    public void setAndroid(Integer android) {
        this.android = android;
    }

    @Basic
    @javax.persistence.Column(name = "IOS", nullable = true, insertable = true, updatable = true, length = 10)
    public Integer getIos() {
        return ios;
    }

    public void setIos(Integer ios) {
        this.ios = ios;
    }

    @Basic
    @javax.persistence.Column(name = "WECHAT", nullable = true, insertable = true, updatable = true, length = 10)
    public Integer getWeChat() {
        return weChat;
    }

    public void setWeChat(Integer weChat) {
        this.weChat = weChat;
    }

    @Basic
    @javax.persistence.Column(name = "APP", nullable = true, insertable = true, updatable = true, length = 10)
    public Integer getAPP() {
        return APP;
    }

    public void setAPP(Integer APP) {
        this.APP = APP;
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
