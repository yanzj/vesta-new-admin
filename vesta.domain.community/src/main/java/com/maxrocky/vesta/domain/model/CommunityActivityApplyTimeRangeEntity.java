package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 业主活动报名时间段表
 * Created by WeiYangDong on 2017/12/10.
 */
@Entity
@Table(name = "community_activity_apply_timeRange")
public class CommunityActivityApplyTimeRangeEntity {

    private String id;//主键ID
    private String activityId;//活动ID
    private Date applyDate;//报名日期
    private String startTime;//起始时间
    private String endTime;//截止时间
    private Integer maxUser;//人数配额
    private Integer applyNum;//已报名人数

    private Date createOn;      //创建时间
    private String createBy;    //创建人

    @Id
    @Column(name = "id", length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "activity_id",length = 50)
    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "apply_date", nullable = true, length = 50)
    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    @Basic
    @Column(name = "start_time",length = 10)
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time",length = 10)
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "max_user",length = 10)
    public Integer getMaxUser() {
        return maxUser;
    }

    public void setMaxUser(Integer maxUser) {
        this.maxUser = maxUser;
    }

    @Basic
    @Column(name = "apply_num",length = 10)
    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "create_by", nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
