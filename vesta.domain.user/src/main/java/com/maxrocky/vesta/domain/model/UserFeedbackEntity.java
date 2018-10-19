package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tom on 2016/1/21 17:44.
 * Describe:意见反馈实体类
 */
@Entity
@Table(name = "user_feedback")
public class UserFeedbackEntity {

    /* 状态 */
    public final static String STATE_NEW = "NEW";

    private String id;//主键
    private String userId;//用户Id
    private String state;//状态:1为未处理;2为已处理
    private String content;//内容
    private String mobile;//手机
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间
    private String feedBackType;//类型  1金茂会员 2金茂质检 3微信
    private String memo;//类别：1为身份申诉、2为意见反馈
    private Date startDate;//反馈开始时间
    private Date endDate;//反馈结束时间
    private String userType;//用户类型:2为普通会员;3为业主;4为同住人
    private String projectId;//项目id
    private String projectName;//项目名称
    private String address;//地址
    private String fbClassification;//意见反馈来源分类     1便民信息纠错 2:意见反馈

    @Basic
    @Column(name = "FBCLASSIFICATION", nullable = true, length = 10)
    public String getFbClassification() {
        return fbClassification;
    }

    public void setFbClassification(String fbClassification) {
        this.fbClassification = fbClassification;
    }

    @Id
    @Column(name = "ID",nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USER_ID", nullable = true, length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "STATE", nullable = true, length = 10)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "CONTENT", nullable = false, length = 1400)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "MOBILE", nullable = true, length = 11)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "CREATE_BY", nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_ON", nullable = true)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MODIFY_BY", nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "MODIFY_ON", nullable = true)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
    @Transient
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    @Transient
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    @Basic
    @Column(name = "PROJECT_ID",  length = 100)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "FEEDBACK_TYPE",length = 2)
    public String getFeedBackType() {
        return feedBackType;
    }

    public void setFeedBackType(String feedBackType) {
        this.feedBackType = feedBackType;
    }

    @Basic
    @Column(name = "MEMO",length = 2)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Basic
    @Column(name = "USER_TYPE",length = 2)
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "PROJECT_NAME",length = 50)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "ADDRESS",  length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
