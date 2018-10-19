package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by JillChen on 2016/1/4.
 */
@Entity
@Table(name = "community_activity_apply_info")
public class CommunityActivityApplyInfoEntity {

    /***
     * 报名状态
     */
    public class applyStatus{
        public static final  int apply_do = 1; //已报名
        public static final  int apply_do_not = 0; //未报名
    }
    private String applyId;
    private String activityId;//活动id
    private String userId;//用户id
    private Date makedate;//报名时间
    private String applyDesc;//报名状态

    private String applyPhone;//报名人联系方式
    private Integer applyCount;//报名人数
    private String applyInfo; /* 报名信息 */
    private String applyNote;//报名备注
    /* 新增字段_报名地址_2016-08-05_Wyd */
    /* 新增字段_城市项目信息_2017-08-15_Wyd */
    private String applyAddress;//联系地址
    private String projectNum;//项目编码
    private String projectName;//项目名称
    private String cityId;//城市Id
    private String cityName;//城市名称
    /* ============================= */

    private String readStatus;//消息是否已读(1-已读;0-未读)
    private Integer isSignIn;//是否签到(0,未签到;1,已签到)

    private String applyTimeRangeId;//报名时间段ID
    private String applyTimeRange;//报名时间段

    @Basic
    @Column(name = "apply_timeRange_id",length = 32)
    public String getApplyTimeRangeId() {
        return applyTimeRangeId;
    }

    public void setApplyTimeRangeId(String applyTimeRangeId) {
        this.applyTimeRangeId = applyTimeRangeId;
    }

    @Basic
    @Column(name = "apply_timeRange",length = 50)
    public String getApplyTimeRange() {
        return applyTimeRange;
    }

    public void setApplyTimeRange(String applyTimeRange) {
        this.applyTimeRange = applyTimeRange;
    }

    @Basic
    @Column(name = "read_status", columnDefinition="VARCHAR(80) default 0")
    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    @Basic
    @Column(name = "apply_note",length = 300)
    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    @Basic
    @Column(name = "apply_info",length = 16777216)
    public String getApplyInfo() {
        return applyInfo;
    }

    public void setApplyInfo(String applyInfo) {
        this.applyInfo = applyInfo;
    }

    @Basic
    @Column(name = "apply_phone",length = 50)
    public String getApplyPhone() {
        return applyPhone;
    }

    public void setApplyPhone(String applyPhone) {
        this.applyPhone = applyPhone;
    }


    @Basic
    @Column(name = "apply_count",length = 50)
    public Integer getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Integer applyCount) {
        this.applyCount = applyCount;
    }

    @Basic
    @Column(name = "apply_address",length = 500)
    public String getApplyAddress() {
        return applyAddress;
    }

    public void setApplyAddress(String applyAddress) {
        this.applyAddress = applyAddress;
    }

    @Id
    @Column(name = "apply_id",length = 32)
    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    @Basic
    @Column(name = "activity_id",length = 32)
    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Basic
    @Column(name = "user_id",length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

//    @Basic
//    @Column(name = "makedate",length = 50)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "makedate", nullable = true, length = 50)
    public Date getMakedate() {
        return makedate;
    }

    public void setMakedate(Date makedate) {
        this.makedate = makedate;
    }

    @Basic
    @Column(name = "apply_desc",length = 50)
    public String getApplyDesc() {
        return applyDesc;
    }

    public void setApplyDesc(String applyDesc) {
        this.applyDesc = applyDesc;
    }

    @Basic
    @Column(name = "project_name",length = 50)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "project_num",length = 50)
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    @Basic
    @Column(name = "city_id",length = 50)
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "city_name",length = 50)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Basic
    @Column(name = "is_sign",length = 1)
    public Integer getIsSignIn() {
        return isSignIn;
    }

    public void setIsSignIn(Integer isSignIn) {
        this.isSignIn = isSignIn;
    }
}
