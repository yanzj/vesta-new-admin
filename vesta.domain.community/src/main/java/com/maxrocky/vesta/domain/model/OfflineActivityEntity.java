package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 线下活动实体类
 * Created by WeiYangDong on 2017/8/21.
 */
@Entity
@Table(name = "offline_activity")
public class OfflineActivityEntity {

    private String activityId;//活动主键ID
    private String activityTitle;//活动标题
    private String cityId;//城市ID
    private String cityName;//城市名称
    private String activityPlace;//活动地点
    private Integer partakeNumber;//参与人数
    private Date activityStartTime;//活动开始时间
    private Date activityEndTime;//活动结束时间

    private Integer isLuckDraw;//是否抽奖
    private Integer onePrizeNumber;//一等奖人数
    private Integer twoPrizeNumber;//二等奖人数
    private Integer threePrizeNumber;//三等奖人数

    private Integer signNumber;//签到人数

    private String createBy;    //创建人
    private Date createOn;      //创建时间
    private String modifyBy;    //修改人
    private Date modifyOn;      //修改时间

    @Id
    @Column(name = "activity_id",length = 32)
    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Basic
    @Column(name = "activity_title",length = 150)
    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    @Basic
    @Column(name = "city_id",length = 50)
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "city_name",length = 100)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Basic
    @Column(name = "activity_place",length = 200)
    public String getActivityPlace() {
        return activityPlace;
    }

    public void setActivityPlace(String activityPlace) {
        this.activityPlace = activityPlace;
    }

    @Basic
    @Column(name = "partake_number",length = 6)
    public Integer getPartakeNumber() {
        return partakeNumber;
    }

    public void setPartakeNumber(Integer partakeNumber) {
        this.partakeNumber = partakeNumber;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "activity_start_time", nullable = true, length = 50)
    public Date getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Date activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "activity_end_time", nullable = true, length = 50)
    public Date getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Date activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    @Basic
    @Column(name = "is_luckdraw",length = 3,columnDefinition="INT default 0")
    public Integer getIsLuckDraw() {
        return isLuckDraw;
    }

    public void setIsLuckDraw(Integer isLuckDraw) {
        this.isLuckDraw = isLuckDraw;
    }

    @Basic
    @Column(name = "one_prize_number",length = 5,columnDefinition="INT default 0")
    public Integer getOnePrizeNumber() {
        return onePrizeNumber;
    }

    public void setOnePrizeNumber(Integer onePrizeNumber) {
        this.onePrizeNumber = onePrizeNumber;
    }

    @Basic
    @Column(name = "two_prize_number",length = 5,columnDefinition="INT default 0")
    public Integer getTwoPrizeNumber() {
        return twoPrizeNumber;
    }

    public void setTwoPrizeNumber(Integer twoPrizeNumber) {
        this.twoPrizeNumber = twoPrizeNumber;
    }

    @Basic
    @Column(name = "three_prize_number",length = 5,columnDefinition="INT default 0")
    public Integer getThreePrizeNumber() {
        return threePrizeNumber;
    }

    public void setThreePrizeNumber(Integer threePrizeNumber) {
        this.threePrizeNumber = threePrizeNumber;
    }

    @Basic
    @Column(name = "sign_number",length = 5,columnDefinition="INT default 0")
    public Integer getSignNumber() {
        return signNumber;
    }

    public void setSignNumber(Integer signNumber) {
        this.signNumber = signNumber;
    }

    @Basic
    @Column(name = "create_by", nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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
    @Column(name = "modify_by", nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_on", nullable = true, length = 50)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
}
