package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by JillChen on 2016/1/4.
 */
@Entity
@Table(name = "community_activity_info")
public  class  CommunityActivityInfoEntity {

    public static class CommunityActivityStatus {
        //进行中
        public static final int activity_doing = 1;
        //已结束
        public static final int activity_end = 2;
    }
    private String activityId;//活动id
    private String title;//标题
    private String activityDesc;//活动描述
    private String activityDate;//活动时间
    private String address;//地址
    private String hotline;//热线电话
    private String makedate;//创建时间
    private String modifydate;//修改时间
    private String operator;//操作人
    private String status;//状态          1-报名中，2-已报满，3-报名结束，4-活动结束
    private String state;//是否删除     0-已删，1-未删
    private String pushState;//是否发布     0-未发布，1-已发布
    private String projectId;//项目id
    private Integer HeadCount;//总人数

    private Integer people;//人数
    private String scope;//活动范围
    private String types;//类型：1为公开;2为私有

    private String applyPhone;//报名人联系方式
    private Integer applyCount;//报名人数
    private Integer applyMaxNum;//每户报名人数上限

    private String activityEndDate;   /* 活动截止时间 */
    private String applyStartTime; /* 报名开始时间 */
    private String applyEndTime; /* 报名截止时间 */
    private String applyInfo; /* 活动信息 */
    private Integer applyInfoMaxNum;//报名信息最大组数

    /*
    * 追加字段:活动类型,是否作为Banner,是否有外链,外链地址
    */
    private static final int activity_type_common = 1;//普通活动
    private static final int activity_type_marketing = 2;//营销活动
    private static final int activity_type_business = 3;//商业活动
    private static final int activity_type_headquarters = 4;//总部活动

    private Integer activityType;//活动类型(1,普通活动;2,营销活动;3,商业活动;4,总部活动)
    private Integer themeType;//主题类型(1,家兴业茂;2,穆如清风;3,优居计划;4,金彩有你)
    private Integer isBanner;//是否作为宣传位(0,否1,是)
    private Integer isLink;//是否有外链(0,否1,是)
    private String linkSrc;//外链地址
    private Integer isBlacklist;//是否使用黑名单(0,否1,是)
    private String blacklistId;//黑名单ID
    private Integer isHouseScope;//是否发布房产范围(0,否1,是)
    private String houseScope;//房产范围
    private Integer isTimeRange;//是否配置活动报名时间段(0,否1,是)

    @Basic
    @Column(name = "is_timeRange",length = 1)
    public Integer getIsTimeRange() {
        return isTimeRange;
    }

    public void setIsTimeRange(Integer isTimeRange) {
        this.isTimeRange = isTimeRange;
    }

    @Basic
    @Column(name = "is_houseScope",length = 1)
    public Integer getIsHouseScope() {
        return isHouseScope;
    }

    public void setIsHouseScope(Integer isHouseScope) {
        this.isHouseScope = isHouseScope;
    }

    @Basic
    @Column(name = "house_scope",length = 16777216)
    public String getHouseScope() {
        return houseScope;
    }

    public void setHouseScope(String houseScope) {
        this.houseScope = houseScope;
    }

    @Basic
    @Column(name = "activity_type",length = 10)
    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    @Basic
    @Column(name = "theme_type",length = 1)
    public Integer getThemeType() {
        return themeType;
    }

    public void setThemeType(Integer themeType) {
        this.themeType = themeType;
    }

    @Basic
    @Column(name = "is_blacklist",length = 1)
    public Integer getIsBlacklist() {
        return isBlacklist;
    }

    public void setIsBlacklist(Integer isBlacklist) {
        this.isBlacklist = isBlacklist;
    }

    @Basic
    @Column(name = "blacklist_id",length = 32)
    public String getBlacklistId() {
        return blacklistId;
    }

    public void setBlacklistId(String blacklistId) {
        this.blacklistId = blacklistId;
    }

    @Basic
    @Column(name = "is_banner",length = 5)
    public Integer getIsBanner() {
        return isBanner;
    }

    public void setIsBanner(Integer isBanner) {
        this.isBanner = isBanner;
    }

    @Basic
    @Column(name = "is_link",length = 5)
    public Integer getIsLink() {
        return isLink;
    }

    public void setIsLink(Integer isLink) {
        this.isLink = isLink;
    }

    @Basic
    @Column(name = "link_src",length = 255)
    public String getLinkSrc() {
        return linkSrc;
    }

    public void setLinkSrc(String linkSrc) {
        this.linkSrc = linkSrc;
    }

    @Basic
    @Column(name = "activity_EndDate")
    public String getActivityEndDate() {
        return activityEndDate;
    }

    public void setActivityEndDate(String activityEndDate) {
        this.activityEndDate = activityEndDate;
    }

    @Basic
    @Column(name = "apply_StartTime")
    public String getApplyStartTime() {
        return applyStartTime;
    }

    public void setApplyStartTime(String applyStartTime) {
        this.applyStartTime = applyStartTime;
    }

    @Basic
    @Column(name = "apply_EndTime")
    public String getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(String applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    @Basic
    @Column(name = "apply_Info",length = 16777216)
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
    @Column(name = "apply_max_num",length = 20)
    public Integer getApplyMaxNum() {
        return applyMaxNum;
    }

    public void setApplyMaxNum(Integer applyMaxNum) {
        this.applyMaxNum = applyMaxNum;
    }

    @Id
    @Column(name = "activity_id",length = 32)
    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Basic
    @Column(name = "title",length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "activity_desc",length = 300)
    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }


    @Basic
    @Column(name = "address",length = 50)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "hotline",length = 50)
    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }


    @Basic
    @Column(name = "operator",length = 50)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "status",length = 50)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "acitivity_date",length = 50)
    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    @Basic
    @Column(name = "makedate",length = 50)
    public String getMakedate() {
        return makedate;
    }

    public void setMakedate(String makedate) {
        this.makedate = makedate;
    }

    @Basic
    @Column(name = "modifydate",length = 50)
    public String getModifydate() {
        return modifydate;
    }

    public void setModifydate(String modifydate) {
        this.modifydate = modifydate;
    }

    @Basic
    @Column(name = "project_id",length = 32)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "head_count",length = 50)
    public Integer getHeadCount() {
        return HeadCount;
    }

    public void setHeadCount(Integer headCount) {
        this.HeadCount = headCount;
    }

    @Basic
    @Column(name = "people",length = 50)
    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    @Basic
    @Column(name = "scope",length = 50)
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Basic
    @Column(name = "types",length = 50)
    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    @Basic
    @Column(name = "STATE",length = 50)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "PUSHSTATE",length = 50)
    public String getPushState() {
        return pushState;
    }

    public void setPushState(String pushState) {
        this.pushState = pushState;
    }

    @Basic
    @Column(name = "applyInfo_max_num",length = 20)
    public Integer getApplyInfoMaxNum() {
        return applyInfoMaxNum;
    }

    public void setApplyInfoMaxNum(Integer applyInfoMaxNum) {
        this.applyInfoMaxNum = applyInfoMaxNum;
    }
}
