package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 管家评分日志表
 * Created by WeiYangDong on 2017/5/10.
 */
@Entity
@Table(name = "butler_score_log")
public class ButlerScoreLogEntity {
    private String logId;//主键ID
    private String userId;//用户ID
    private String userName;//用户姓名
    private String realName;//真实姓名
    private String mobile;//电话号码

    private String butlerId;//管家ID
    private String butlerScore;//管家评分
    private String comment;//评价内容

    private String createBy;    //创建人
    private Date createOn;      //创建时间
    private String modifyBy;    //修改人
    private Date modifyOn;      //修改时间

    @Id
    @Column(name = "log_id", length = 32)
    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    @Basic
    @Column(name = "user_id",length = 50)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name",nullable = true, length = 32)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "real_name", length = 60)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Basic
    @Column(name = "mobile",nullable = false, length = 11)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "butler_id", length = 32)
    public String getButlerId() {
        return butlerId;
    }

    public void setButlerId(String butlerId) {
        this.butlerId = butlerId;
    }

    @Basic
    @Column(name = "butler_score",length = 10)
    public String getButlerScore() {
        return butlerScore;
    }

    public void setButlerScore(String butlerScore) {
        this.butlerScore = butlerScore;
    }

    @Basic
    @Column(name = "comment",length = 101)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "create_by", nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "modify_by", nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_on", nullable = true, length = 50)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
}
