package com.maxrocky.vesta.application.admin.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuwei on 2016/1/14.
 * 社区活动详情反显dto
 */
public class CommuntiyDetalInfo {
    private String activityId;
    private Boolean ifLike ;//点赞信息
    private String des;   //描述信息
    private String address; //活动地点
    private String activityDate ; //活动日期
    private boolean status ;//活动状态
    private Boolean apply ; //是否已报名
    private String hotline ; //咨询热线
    private String title;
    private String likes;



    private List<CommuntiyDetalImageInfo> images = new ArrayList<CommuntiyDetalImageInfo>();


    public boolean isStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public CommuntiyDetalInfo setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getLikes() {
        return likes;
    }

    public CommuntiyDetalInfo setLikes(String likes) {
        this.likes = likes;
        return this;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Boolean getIfLike() {
        return ifLike;
    }

    public CommuntiyDetalInfo setIfLike(Boolean ifLike) {
        this.ifLike = ifLike;
        return this;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Boolean getApply() {
        return apply;
    }

    public CommuntiyDetalInfo setApply(Boolean apply) {
        this.apply = apply;
        return this;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public List<CommuntiyDetalImageInfo> getImages() {
        return images;
    }

    public CommuntiyDetalInfo setImages(List<CommuntiyDetalImageInfo> images) {
        this.images = images;
        return this;
    }
}
