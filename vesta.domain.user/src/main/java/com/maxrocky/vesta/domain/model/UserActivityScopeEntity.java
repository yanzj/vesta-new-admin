package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by luxinxin on 2016/7/21.
 */
@Entity
@Table(name = "user_activity_scope")
public class UserActivityScopeEntity {
    private String userId;//用户id
    private String activityId;//活动id
    private String readed;//标识 1:已读

    @Id
    @Column(name = "userId",nullable = false, length = 100)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "activityId",nullable = true, length = 100)
    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Basic
    @Column(name = "readed",nullable = true, length = 32)
    public String getReaded() {
        return readed;
    }

    public void setReaded(String readed) {
        this.readed = readed;
    }
}
