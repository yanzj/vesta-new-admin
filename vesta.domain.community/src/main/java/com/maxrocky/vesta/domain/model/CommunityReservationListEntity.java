package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yifan on 2016/4/28.
 * 预约单
 */
@Entity
@Table(name = "community_reservation_list")
public class CommunityReservationListEntity {

    private String id;         //房屋预约单id
    private String userName;      //用户姓名
    private String mobile;      //手机号
    private String idCard;      //身份证号
    private String projectName;      //项目名称
    private String buildingName;    //楼号
    private String unitName;    //单元号
    private String roomName;    //房间编号
    private Date reservationDate;   //业主预约时间
    private String amOrPm;     //0,上午  1，下午
    private String planName;   //交付批次（计划）名称
    private String reservationStatus;  //预约状态 0，未预约  1，已预约
    private String status;     //标识状态  0，不启用  1，启用
    private String userId;      //作为标示防止重名

    private Date operatorDate;    //操作时间
    private String operator;        //操作人
    private Date createDate;      //创建时间
    private String createPerson;     //创建人


    @Id
    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USER_NAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "MOBILE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "ID_CARD", nullable = true, insertable = true, updatable = true, length = 50)
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Basic
    @Column(name = "PROJECT_NAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "BUILDING_NAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    @Basic
    @Column(name = "UNIT_NAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Basic
    @Column(name = "ROOM_NAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "RESERVATION_Date", nullable = true, insertable = true, updatable = true, length = 50)
    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Basic
    @Column(name = "AM_OR_PM", nullable = true, insertable = true, updatable = true, length = 5)
    public String getAmOrPm() {
        return amOrPm;
    }

    public void setAmOrPm(String amOrPm) {
        this.amOrPm = amOrPm;
    }

    @Basic
    @Column(name = "PLAN_NAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    @Basic
    @Column(name = "RESERVATION_STATUS", nullable = true, insertable = true, updatable = true, length = 5)
    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    @Basic
    @Column(name = "STATUS", nullable = true, insertable = true, updatable = true, length = 5)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "USER_ID", nullable = true, insertable = true, updatable = true, length = 50)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = true, length = 50)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "create_person", length = 50)
    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "operator_date", nullable = true, length = 50)
    public Date getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(Date operatorDate) {
        this.operatorDate = operatorDate;
    }

    @Basic
    @Column(name = "operator", length = 50)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

}

