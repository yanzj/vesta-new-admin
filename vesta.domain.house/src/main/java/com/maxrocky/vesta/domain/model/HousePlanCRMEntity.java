package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liudongxin on 2016/4/22.
 * 交付计划：房产列表
 */
@Entity
@Table(name = "house_plan_crm")
public class HousePlanCRMEntity {
    private Long id;//自增长id
    private String roomId;//房屋主键
    private String roomNum;//房屋编号
    private String planId;//计划id
    private Date createOn;//创建时间
    private Date modifyOn;//修改时间
    private String state;//0为有效;1为无效

    @Id
    @Column(name = "ID",nullable = false, length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ROOM_ID", nullable = false, insertable = true, updatable = true, length = 50)
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Column(name = "ROOM_NUM", nullable = true, insertable = true, updatable = true, length = 50)
    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    @Basic
    @Column(name = "PLAN_ID", nullable = true, insertable = true, updatable = true, length = 50)
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    @Basic
    @Column(name = "CREATE_ON", nullable = true, insertable = true, updatable = true)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MODIFY_ON", nullable = true, insertable = true, updatable = true)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Basic
    @Column(name = "STATE", nullable = true, insertable = true, updatable = true,length =5)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
