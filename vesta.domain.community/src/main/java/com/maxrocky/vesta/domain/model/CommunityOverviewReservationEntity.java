package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 楼盘预约表
 * Created by WeiYangDong on 2017/5/23.
 */
@Entity
@Table(name = "community_overview_reservation")
public class CommunityOverviewReservationEntity {

    private String id;//主键ID
    private String overviewId;//楼盘ID
    private String overviewName;//楼盘项目名称
    private String userId;//用户ID
    private String reservationPer;//预约人
    private String reservationTel;//联系方式
    private Date reservationDate;//预约日期

    @Id
    @Column(name = "id", length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "overview_id",length = 50)
    public String getOverviewId() {
        return overviewId;
    }

    public void setOverviewId(String overviewId) {
        this.overviewId = overviewId;
    }

    @Basic
    @Column(name = "overview_name",length = 100)
    public String getOverviewName() {
        return overviewName;
    }

    public void setOverviewName(String overviewName) {
        this.overviewName = overviewName;
    }

    @Basic
    @Column(name = "user_id",length = 50)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "reservation_per",length = 50)
    public String getReservationPer() {
        return reservationPer;
    }

    public void setReservationPer(String reservationPer) {
        this.reservationPer = reservationPer;
    }

    @Basic
    @Column(name = "reservation_tel",length = 50)
    public String getReservationTel() {
        return reservationTel;
    }

    public void setReservationTel(String reservationTel) {
        this.reservationTel = reservationTel;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reservation_date", nullable = false,length = 50)
    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
}
