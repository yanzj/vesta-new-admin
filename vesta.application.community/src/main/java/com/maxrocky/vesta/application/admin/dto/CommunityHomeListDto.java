package com.maxrocky.vesta.application.admin.dto;

/**
 * Created by liuwei on 2016/2/18.
 */
public class CommunityHomeListDto {

    private String id;//活动id
    private String title;//活动标题
    private String src;//活动图片
    private String publishDate;//发布日期
    private String date;//当前时间
    private String scope;//活动范围
    private String headCount;//总人数
    private String people;//剩余人数
    private String cancelNumber;//取消次数
    private String state;//状态：0是为报名;1是已报名

    private String activityEndDate;   /* 活动截止时间 */
    private String applyStartTime; /* 报名开始时间 */
    private String applyEndTime; /* 报名截止时间 */

    private String createTime;
    private String content;

    private String types;

    public String getHeadCount() {
        return headCount;
    }

    public void setHeadCount(String headCount) {
        this.headCount = headCount;
    }

    public String getActivityEndDate() {
        return activityEndDate;
    }

    public void setActivityEndDate(String activityEndDate) {
        this.activityEndDate = activityEndDate;
    }

    public String getApplyStartTime() {
        return applyStartTime;
    }

    public void setApplyStartTime(String applyStartTime) {
        this.applyStartTime = applyStartTime;
    }

    public String getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(String applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public CommunityHomeListDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getId() {
        return id;
    }

    public CommunityHomeListDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getCancelNumber() {
        return cancelNumber;
    }

    public void setCancelNumber(String cancelNumber) {
        this.cancelNumber = cancelNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CommunityHomeListDto() {
        this.id = "";
        this.src = "";
        this.publishDate = "";
        this.scope = "";
        this.people = "";
        this.cancelNumber="";
        this.state="";
        this.date="";
    }
}
