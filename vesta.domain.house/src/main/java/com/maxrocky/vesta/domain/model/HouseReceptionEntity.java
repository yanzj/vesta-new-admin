package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Magic on 2016/5/23.
 */
@Entity
@Table(name = "house_reception")
public class HouseReceptionEntity {
    private String id;
    private String roomid;  //房间id
    private String roomaddress;//房间位置
    private String ownername;//业主姓名
    private String mobile;//联系电话
    /**评价细节
     * highlySatisfied          五星
     * satisfied                四星
     * qualified                三星
     * dissatisfied             两星
     * highlyDissatisfied       一星
     * */
    private String overallfeeling;//总体感觉
    private String detailprocessing;//细节处理
    private String serviceattitude;//服务态度
    private String rectificationspeed;//整改速度
    private String professionaldegree;//专业程度

    private String ownersignature;//业主签字
    private Date datetime;//时间
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
    @Column(name = "OVERALL_FEELING",nullable = true, length = 50)
    public String getOverallfeeling() {
        return overallfeeling;
    }

    public void setOverallfeeling(String overallfeeling) {
        this.overallfeeling = overallfeeling;
    }
    @Basic
    @Column(name = "DETAIL_PROCESSING",nullable = true, length = 50)
    public String getDetailprocessing() {
        return detailprocessing;
    }

    public void setDetailprocessing(String detailprocessing) {
        this.detailprocessing = detailprocessing;
    }
    @Basic
    @Column(name = "SERVICE_ATTITUDE",nullable = true, length = 50)
    public String getServiceattitude() {
        return serviceattitude;
    }

    public void setServiceattitude(String serviceattitude) {
        this.serviceattitude = serviceattitude;
    }
    @Basic
    @Column(name = "RECTIFICATION_SPEED",nullable = true, length = 50)
    public String getRectificationspeed() {
        return rectificationspeed;
    }

    public void setRectificationspeed(String rectificationspeed) {
        this.rectificationspeed = rectificationspeed;
    }
    @Basic
    @Column(name = "PROFESSIONAL_DEGREE",nullable = true, length = 50)
    public String getProfessionaldegree() {
        return professionaldegree;
    }

    public void setProfessionaldegree(String professionaldegree) {
        this.professionaldegree = professionaldegree;
    }

    @Basic
    @Column(name = "OWNER_SIGNATURE",nullable = true, length = 50)
    public String getOwnersignature() {
        return ownersignature;
    }

    public void setOwnersignature(String ownersignature) {
        this.ownersignature = ownersignature;
    }
    @Basic
    @Column(name = "DATE_TIME",nullable = true, length = 50)
    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
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
