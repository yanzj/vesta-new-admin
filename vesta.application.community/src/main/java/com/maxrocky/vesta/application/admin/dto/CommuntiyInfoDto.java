package com.maxrocky.vesta.application.admin.dto;

/**
 * Created by liuwei on 2016/1/14.
 */
public class CommuntiyInfoDto {
    private String activityId; //活动id
    private String makeDate;  //发布时间
    private String title;  //活动标题
    private String des;  //活动描述
    private String imageUrl; //活动背景图片

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(String makeDate) {
        this.makeDate = makeDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
