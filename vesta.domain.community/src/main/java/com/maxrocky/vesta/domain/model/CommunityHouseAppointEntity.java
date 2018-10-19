package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yifan on 2016/4/1.
 */
@Entity
@Table(name = "community_house_appoint")
public class CommunityHouseAppointEntity {

    private String id;         //房屋预约单id
    private String userId;      //用户id
    private String communityId;      //社区id
    private String batchManageId;   //批次管理id
    private String houseInfoId;     //用户预约房间Id

    private Date payStaTime;      //集中交付开始时间
    private Date payEndTime;     //集中交付结束时间
    private Date appointStaTime;    //预约交付开始时间
    private Date appointEndTime;        //预约交付结束时间
    private Date operatorDate;    //操作时间
    private String operator;        //操作人
    private Date createDate;      //创建时间、
    private String createPerson;     //创建人
    private Integer appointStatus;      //预约状态      //0,为预约，1,已预约
    private Integer payStatus;         //支付状态      //0,进行中/带交付 1，已完成/已交付
    private Integer amOrPm;                   //0:上午，1：下午
    private String deliveryBatch;     //交付批次名称      业主输入 //应该取消

    private Integer status;         //预约单状态，在用户端是否显示

    //屏蔽以下数据，数据信息从下面 batchManageId 中获取


    //private String description;     //描述               业主输入

    //private String maxUser;     //每时间段最大人数        业主输入

    //取消
   // private String note;            //备注
    //private String houseName;               //房屋名称，手动添加
    //private Integer type;           //预约单类型 0，交付批次信息  1，预约单信息

    @Id
    @Column(name = "id", length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "community_id", length = 32)
    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    @Basic
    @Column(name = "batch_manage_id", length = 32)
    public String getBatchManageId() {
        return batchManageId;
    }

    public void setBatchManageId(String batchManageId) {
        this.batchManageId = batchManageId;
    }

    @Basic
    @Column(name = "house_info_id",length = 32)
    public String getHouseInfoId() {
        return houseInfoId;
    }

    public void setHouseInfoId(String houseInfoId) {
        this.houseInfoId = houseInfoId;
    }

    @Basic
    @Column(name = "user_id",length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pay_sta_time", nullable = true,length = 50)
    public Date getPayStaTime() {
        return payStaTime;
    }

    public void setPayStaTime(Date payStaTime) {
        this.payStaTime = payStaTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pay_end_time", nullable = true,length = 50)
    public Date getPayEndTime() {
        return payEndTime;
    }

    public void setPayEndTime(Date payEndTime) {
        this.payEndTime = payEndTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "appoint_sta_time", nullable = true,length = 50)
    public Date getAppointStaTime() {
        return appointStaTime;
    }

    public void setAppointStaTime(Date appointStaTime) {
        this.appointStaTime = appointStaTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "appoint_end_time", nullable = true,length = 50)
    public Date getAppointEndTime() {
        return appointEndTime;
    }

    public void setAppointEndTime(Date appointEndTime) {
        this.appointEndTime = appointEndTime;
    }

    @Basic
    @Column(name = "appoint_status",length = 5)
    public Integer getAppointStatus() {
        return appointStatus;
    }

    public void setAppointStatus(Integer appointStatus) {
        this.appointStatus = appointStatus;
    }

    @Basic
    @Column(name = "pay_status",length = 5)
    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }


    @Basic
    @Column(name = "am_or_pm",length = 5)
    public Integer getAmOrPm() {
        return amOrPm;
    }

    public void setAmOrPm(Integer amOrPm) {
        this.amOrPm = amOrPm;
    }
/*
    @Basic
    @Column(name = "house_hame",length = 50)
    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }*/

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = true,length = 50)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "create_person",length = 50)
    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "operator_date", nullable = true,length = 50)
    public Date getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(Date operatorDate) {
        this.operatorDate = operatorDate;
    }

    @Basic
    @Column(name = "operator",length = 50)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }


/*
    @Basic
    @Column(name = "description",length = 50)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
*/

/*
    @Basic
    @Column(name = "max_user",length = 50)
    public String getMaxUser() {
        return maxUser;
    }

    public void setMaxUser(String maxUser) {
        this.maxUser = maxUser;
    }
*/

    @Basic
    @Column(name = "delivery_batch",length = 50)
    public String getDeliveryBatch() {
        return deliveryBatch;
    }

    public void setDeliveryBatch(String deliveryBatch) {
        this.deliveryBatch = deliveryBatch;
    }

/*    @Basic
    @Column(name = "type",length = 10)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }*/

    @Basic
    @Column(name = "status",length = 10)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

/*    @Basic
    @Column(name = "note",length = 50)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }*/


}

