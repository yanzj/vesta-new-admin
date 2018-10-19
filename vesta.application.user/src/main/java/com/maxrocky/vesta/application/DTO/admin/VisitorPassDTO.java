package com.maxrocky.vesta.application.DTO.admin;

import java.util.Date;

/**
 * 访客通行数据DTO
 * Created by WeiYangDong on 2017/12/19.
 */
public class VisitorPassDTO {

    private String menuId;

    private String cityId;

    private String id;
    private String projectCode;
    private String projectName;
    private String houseRoomId;
    private String houseAddress;
    private String ownerName;
    private String ownerPhone;
    private String visitorName;
    private String visitorPhone;
    private Date visitDate;
    private String visitDateStr;
    private String pathQRCode;
    private Integer numQRCode;
    private String createBy;
    private Date createOn;
    private String createOnStr;
    private String createOnEnd;

    private String visitorPassId;
    private String guardName;
    private Integer isPass;
    private Date passDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorPhone() {
        return visitorPhone;
    }

    public void setVisitorPhone(String visitorPhone) {
        this.visitorPhone = visitorPhone;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getPathQRCode() {
        return pathQRCode;
    }

    public void setPathQRCode(String pathQRCode) {
        this.pathQRCode = pathQRCode;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public String getHouseRoomId() {
        return houseRoomId;
    }

    public void setHouseRoomId(String houseRoomId) {
        this.houseRoomId = houseRoomId;
    }

    public String getVisitDateStr() {
        return visitDateStr;
    }

    public void setVisitDateStr(String visitDateStr) {
        this.visitDateStr = visitDateStr;
    }

    public String getGuardName() {
        return guardName;
    }

    public void setGuardName(String guardName) {
        this.guardName = guardName;
    }

    public Integer getIsPass() {
        return isPass;
    }

    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }

    public Integer getNumQRCode() {
        return numQRCode;
    }

    public void setNumQRCode(Integer numQRCode) {
        this.numQRCode = numQRCode;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCreateOnStr() {
        return createOnStr;
    }

    public void setCreateOnStr(String createOnStr) {
        this.createOnStr = createOnStr;
    }

    public String getCreateOnEnd() {
        return createOnEnd;
    }

    public void setCreateOnEnd(String createOnEnd) {
        this.createOnEnd = createOnEnd;
    }

    public String getVisitorPassId() {
        return visitorPassId;
    }

    public void setVisitorPassId(String visitorPassId) {
        this.visitorPassId = visitorPassId;
    }

    public Date getPassDate() {
        return passDate;
    }

    public void setPassDate(Date passDate) {
        this.passDate = passDate;
    }
}
