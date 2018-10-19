package com.maxrocky.vesta.application.DTO;

/**
 * Created by Admin on 2016/5/21.
 * 系统通知
 */
public class SystemNotificationDTO {

    private String notificationId;//系统通知ID
    private String notificationTitle;  //系统通知标题
    private String notificationContent; //系统通知内容
    private String notificationType; //系统通知类型
    private String notificationStatus; //系统通知发布类型
    private String notificationTopStatus;//系统通知置顶状态
    private String notificationCreater; //系统通知创建人
    private String notificationCreateTime; // 系统通知创建时间
    private String notificationRemarks; //备注
    private String notificationCreateBeginTime; // 查询条件（创建开始时间）数据库不存在
    private String notificationCreateEndTime;// 查询条件（创建结束时间）数据库不存在


    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(String notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    public String getNotificationTopStatus() {
        return notificationTopStatus;
    }

    public void setNotificationTopStatus(String notificationTopStatus) {
        this.notificationTopStatus = notificationTopStatus;
    }

    public String getNotificationCreater() {
        return notificationCreater;
    }

    public void setNotificationCreater(String notificationCreater) {
        this.notificationCreater = notificationCreater;
    }

    public String getNotificationCreateTime() {
        return notificationCreateTime;
    }

    public void setNotificationCreateTime(String notificationCreateTime) {
        this.notificationCreateTime = notificationCreateTime;
    }

    public String getNotificationRemarks() {
        return notificationRemarks;
    }

    public void setNotificationRemarks(String notificationRemarks) {
        this.notificationRemarks = notificationRemarks;
    }

    public String getNotificationCreateEndTime() {
        return notificationCreateEndTime;
    }

    public void setNotificationCreateEndTime(String notificationCreateEndTime) {
        this.notificationCreateEndTime = notificationCreateEndTime;
    }

    public String getNotificationCreateBeginTime() {
        return notificationCreateBeginTime;
    }

    public void setNotificationCreateBeginTime(String notificationCreateBeginTime) {
        this.notificationCreateBeginTime = notificationCreateBeginTime;
    }

}
