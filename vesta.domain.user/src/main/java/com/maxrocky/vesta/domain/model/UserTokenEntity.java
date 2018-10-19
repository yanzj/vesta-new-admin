package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by Chen on 2016/04/05.
 */
@Entity
@Table(name = "user_token")
public class UserTokenEntity {

    private String tokenId;//token ID
    private String userId;//用户ID
    private String openId;//OpenID
    private String isActive;//本token是否有效标记     1 有效  2  无效
    private String createBy;//创建人
    private Date createDate;//创建日期
    private Time createTime;//创建时间

    @Id
    @Column(name = "TokenId", nullable = false, unique = true,length = 50)
    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    @Basic
    @Column(name = "UserId", length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "OpenId", length = 32)
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Basic
    @Column(name = "IsActive", length = 2)
    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "CreateBy", length = 30)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CreateDate")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "CreateTime")
    public Time getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Time createTime) {
        this.createTime = createTime;
    }
}
