package com.maxrocky.vesta.application.admin.dto;

/**
 * Created by 27978 on 2016/9/29.
 * 个人活动消息提醒
 */
public class CommunityActivityMessageDto {

    private String activityId;//活动id
    private String title;//活动名称
    private String activityDate;//活动开始时间
    private String applyStartTime;//报名开始时间
    private String applyEndTime;//报名结束时间
    private String activityEndDate;//活动结束时间
    private String readStatus;//消息是否已读 0未读，1已读

    public String getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(String applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public String getApplyStartTime() {
        return applyStartTime;
    }

    public void setApplyStartTime(String applyStartTime) {
        this.applyStartTime = applyStartTime;
    }

    public String getActivityEndDate() {
        return activityEndDate;
    }

    public void setActivityEndDate(String activityEndDate) {
        this.activityEndDate = activityEndDate;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }
}
