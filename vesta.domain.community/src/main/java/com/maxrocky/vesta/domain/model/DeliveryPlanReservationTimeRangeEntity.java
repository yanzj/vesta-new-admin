package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 交房计划预约时间段表
 * Created by WeiYangDong on 2017/6/15.
 */
@Entity
@Table(name = "delivery_plan_reservation_timeRange")
public class DeliveryPlanReservationTimeRangeEntity {

    private String id;//主键ID
    private String deliveryPlanId;//交房计划ID
    private Date reservationDate;//预约日期
    private String startTime;//起始时间
    private String endTime;//截止时间
    private Integer maxUser;//人数配额
    private Integer reservationNum;//已预约人数

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
    @Column(name = "delivery_plan_id",length = 50)
    public String getDeliveryPlanId() {
        return deliveryPlanId;
    }

    public void setDeliveryPlanId(String deliveryPlanId) {
        this.deliveryPlanId = deliveryPlanId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reservation_date", nullable = true, length = 50)
    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
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
    @Column(name = "reservation_num",length = 10)
    public Integer getReservationNum() {
        return reservationNum;
    }

    public void setReservationNum(Integer reservationNum) {
        this.reservationNum = reservationNum;
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
