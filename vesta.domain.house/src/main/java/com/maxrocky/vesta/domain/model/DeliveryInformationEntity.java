package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Magic on 2016/5/23.
 */
@Entity
@Table(name = "delivery_Information")
public class DeliveryInformationEntity {
    private String id;
    private String roomid;  //房间id
    private String roomaddress;//房间位置
    private String ownername;//业主姓名
    private String mobile;//联系电话
    private Date  deliveryDate ;//交房时间

    private String meternumber;//电表号
    private String meterbase;//电表底数
    private String waternumber;//水表号
    private String waterbase;//水表底数
    private String gastablenumber;//气表号
    private String gastablebase;//气表底数
    private String retainkey;//钥匙是否留用
    private String overallfeeling;//总体感觉

    private String deliveryPlan;//关联计划
    private String roomnum;
    @Id
    @Column(name = "ID",nullable = false, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "ROOM_ID",nullable = true, length = 50)
    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }
    @Basic
    @Column(name = "ROOM_ADDRESS",nullable = true, length = 50)
    public String getRoomaddress() {
        return roomaddress;
    }

    public void setRoomaddress(String roomaddress) {
        this.roomaddress = roomaddress;
    }
    @Basic
    @Column(name = "OWNER_NAME",nullable = true, length = 50)
    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }
    @Basic
    @Column(name = "MOBILE",nullable = true, length = 50)

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    @Basic
    @Column(name = "DELIVERY_DATE",nullable = true, length = 50)
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    @Basic
    @Column(name = "METER_BASE",nullable = true, length = 50)
    public String getMeterbase() {
        return meterbase;
    }

    public void setMeterbase(String meterbase) {
        this.meterbase = meterbase;
    }
    @Basic
    @Column(name = "WATER_BASE",nullable = true, length = 50)
    public String getWaterbase() {
        return waterbase;
    }

    public void setWaterbase(String waterbase) {
        this.waterbase = waterbase;
    }
    @Basic
    @Column(name = "GAS_TABLE_BASE",nullable = true, length = 50)
    public String getGastablebase() {
        return gastablebase;
    }

    public void setGastablebase(String gastablebase) {
        this.gastablebase = gastablebase;
    }
    @Basic
    @Column(name = "RETAIN_KEY",nullable = true, length = 50)
    public String getRetainkey() {
        return retainkey;
    }

    public void setRetainkey(String retainkey) {
        this.retainkey = retainkey;
    }
    @Basic
    @Column(name = "OVERALL_FEELING",nullable = true, length = 50)
    public String getOverallfeeling() {
        return overallfeeling;
    }

    public void setOverallfeeling(String overallfeeling) {
        this.overallfeeling = overallfeeling;
    }
    @Basic
    @Column(name = "METER_NUMBER",nullable = true, length = 50)
    public String getMeternumber() {
        return meternumber;
    }

    public void setMeternumber(String meternumber) {
        this.meternumber = meternumber;
    }
    @Basic
    @Column(name = "WATER_NUMBER",nullable = true, length = 50)
    public String getWaternumber() {
        return waternumber;
    }

    public void setWaternumber(String waternumber) {
        this.waternumber = waternumber;
    }
    @Basic
    @Column(name = "GAS_TABLE_NUMBER",nullable = true, length = 50)
    public String getGastablenumber() {
        return gastablenumber;
    }

    public void setGastablenumber(String gastablenumber) {
        this.gastablenumber = gastablenumber;
    }
    @Basic
    @Column(name = "DELIVERY_PLAN",nullable = true, length = 50)
    public String getDeliveryPlan() {
        return deliveryPlan;
    }

    public void setDeliveryPlan(String deliveryPlan) {
        this.deliveryPlan = deliveryPlan;
    }
    @Basic
    @Column(name = "ROOM_NUM",nullable = true, length = 50)
    public String getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
    }

}
