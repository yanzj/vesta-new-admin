package com.maxrocky.vesta.application.admin.dto;
/**
 * Created by yifan on 2016/4/28.
 * 预约单
 */
public class CommunityReservationListDto {

    private String reservationId;         //房屋预约单id
    private String userName;      //用户姓名
    private String mobile;      //手机号
    private String idCard;      //身份证号
    private String projectName;      //项目名称
    private String buildingName;    //楼号
    private String unitName;    //单元号
    private String roomName;    //房间编号
    private String reservationDate;   //业主预约时间
    private String amOrPm;     //0,上午  1，下午
    private String planName;   //交付批次（计划）名称
    private String reservationStatus;  //预约状态 0，未预约  1，已预约

    public String getReservationId() {
        return reservationId;
    }
    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getAmOrPm() {
        return amOrPm;
    }

    public void setAmOrPm(String amOrPm) {
        this.amOrPm = amOrPm;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}

