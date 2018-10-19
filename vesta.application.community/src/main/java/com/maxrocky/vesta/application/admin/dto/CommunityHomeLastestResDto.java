package com.maxrocky.vesta.application.admin.dto;

import java.util.List;

/**
 * Created by liuwei on 2016/2/21.
 */
public class CommunityHomeLastestResDto {

    private String id;
    private String title;
    private String imageUrl;
    private String src;//活动图片
    private String content;//活动内容
    private String publishDate;//发布日期
    private String date;//当前时间
    private String address;//活动地址
    private String scope;//活动范围
    private String phone;//活动电话
    private String status;//报名状态:0为未报名;1为已报名
    private String userType;//用户类型
    private String headCount;//预报名总人数
    private String people;//剩余人数
    private String applyPhone;//报名人联系方式
    private Integer applyCount;//报名人数
    private Integer applyMaxNum;//每户报名人数上限
    /* 新增字段_报名地址_2016-08-05_Wyd */
    private String applyAddress;//联系地址
    /* ============================= */
    private List<String> userHouseAddress;//业主所属房产地址列表

    private String activityEndDate;   /* 活动截止时间 */
    private String applyStartTime; /* 报名开始时间 */
    private String applyEndTime; /* 报名截止时间 */
    private String applyInfo; /* 活动信息 */

    public String getHeadCount() {
        return headCount;
    }

    public void setHeadCount(String headCount) {
        this.headCount = headCount;
    }

    public String getApplyInfo() {
        return applyInfo;
    }

    public void setApplyInfo(String applyInfo) {
        this.applyInfo = applyInfo;
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

    public List<String> getUserHouseAddress() {
        return userHouseAddress;
    }

    public void setUserHouseAddress(List<String> userHouseAddress) {
        this.userHouseAddress = userHouseAddress;
    }

    public String getApplyAddress() {
        return applyAddress;
    }

    public void setApplyAddress(String applyAddress) {
        this.applyAddress = applyAddress;
    }

    public String getApplyPhone() {
        return applyPhone;
    }

    public void setApplyPhone(String applyPhone) {
        this.applyPhone = applyPhone;
    }

    public Integer getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Integer applyCount) {
        this.applyCount = applyCount;
    }

    public String getId() {
        return id;
    }

    public CommunityHomeLastestResDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CommunityHomeLastestResDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CommunityHomeLastestResDto setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public Integer getApplyMaxNum() {
        return applyMaxNum;
    }

    public void setApplyMaxNum(Integer applyMaxNum) {
        this.applyMaxNum = applyMaxNum;
    }
}
