package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Admin on 2016/5/21.
 */
@Entity
@Table(name = "system_notification")
public class SystemNotificationEntity {

    public final static String NOTIFICATION_TYPE_NOTICE = "10001";  // 系统通知类型  （通知）
    public final static String NOTIFICATION_TYPE_OTHER  = "10000";  // 系统通知类型  （其他）

    public final static String NOTIFICATION_TOPSTATUS_Y= "10001"; // 系统通知置顶类型  （已置顶）
    public final static String NOTIFICATION_TOPSTATUS_N = "10000"; // 系统通知置顶类型  （未置顶）

    public final static String NOTIFICATION_STATUS_Y= "10001"; // 系统通知发布类型  （已发布）
    public final static String NOTIFICATION_STATUS_N = "10000"; // 系统通发布顶类型  （未发布）


    private String notificationId;//系统通知ID
    private String notificationTitle;  //系统通知标题
    private String notificationContent; //系统通知内容
    private String notificationType; //系统通知类型
    private String notificationStatus; //系统通知发布类型
    private String notificationTopStatus;//系统通知置顶状态
    private String notificationCreater; //系统通知创建人
    private Date notificationCreateTime; // 系统通知创建时间
    private String status; //状态， 1--有效  0 --已删除
    private String notificationRemarks; //备注
    private String notificationCreateBeginTime; // 查询条件（创建开始时间）数据库不存在
    private String notificationCreateEndTime;// 查询条件（创建结束时间）数据库不存在

    @Id
    @Column(name = "NOTIFICATION_ID", nullable = false, insertable = true, updatable = true, length = 32)
    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    @Basic
    @Column(name = "NOTIFICATION_TITLE", length = 500)
    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    @Basic
    @Column(name = "NOTIFICATION_CONTENT", length = 2000)
    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    @Basic
    @Column(name = "NOTIFICATION_TYPE", length = 10)
    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    @Basic
    @Column(name = "NOTIFICATION_STATUS", length = 10)
    public String getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(String notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    @Basic
    @Column(name = "NOTIFICATION_TOPSTATUS", length = 10)
    public String getNotificationTopStatus() {
        return notificationTopStatus;
    }

    public void setNotificationTopStatus(String notificationTopStatus) {
        this.notificationTopStatus = notificationTopStatus;
    }

    @Basic
    @Column(name = "NOTIFICATION_CREATER", length = 100)
    public String getNotificationCreater() {
        return notificationCreater;
    }

    public void setNotificationCreater(String notificationCreater) {
        this.notificationCreater = notificationCreater;
    }

    @Basic
    @Column(name = "NOTIFICATION_CREATETIME", length = 20)
    public Date getNotificationCreateTime() {
        return notificationCreateTime;
    }

    public void setNotificationCreateTime(Date notificationCreateTime) {
        this.notificationCreateTime = notificationCreateTime;
    }

    @Basic
    @Column(name = "STATUS", length = 2)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "NOTIFICATION_REMARKS", length = 500)
    public String getNotificationRemarks() {
        return notificationRemarks;
    }

    public void setNotificationRemarks(String notificationRemarks) {
        this.notificationRemarks = notificationRemarks;
    }

    @Transient
    public String getNotificationCreateEndTime() {
        return notificationCreateEndTime;
    }

    public void setNotificationCreateEndTime(String notificationCreateEndTime) {
        this.notificationCreateEndTime = notificationCreateEndTime;
    }

    @Transient
    public String getNotificationCreateBeginTime() {
        return notificationCreateBeginTime;
    }

    public void setNotificationCreateBeginTime(String notificationCreateBeginTime) {
        this.notificationCreateBeginTime = notificationCreateBeginTime;
    }



}
