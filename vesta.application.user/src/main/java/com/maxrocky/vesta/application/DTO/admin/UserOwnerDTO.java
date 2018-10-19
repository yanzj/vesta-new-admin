package com.maxrocky.vesta.application.DTO.admin;

import java.util.Date;

/**
 * Created by zhanghj on 2016/1/26.
 */
public class UserOwnerDTO {

    private String userIdDto;//用户主键Id
    private String houseInfoid;//房屋主键Id
    private String houseUserid;//用户房屋关系Id
    private String userNameDto;//用户名
    private String userType;//用户类型
    private String mobileDto;//手机
    private String realNameDto;//真实姓名
    private String registerDate;//注册时间
    private String companyId;//公司Id
    private String companyName;//公司名称
    private String projectIdDto;//项目Id
    private String projectName;//项目名称
    private String buildingId;//楼Id
    private String buildingName;//楼名称
    private String unitId;//单元Id
    private String unitName;//单元名称
    private String roomId;//房间Id
    private String roomName;//房间号
    private String createBy;//创建人
    private String password;//密码
    private String ispay;//是否有付款权限
    private String ownerName;//业主姓名
    private String ownerMobile;//业主联系方式

    private String beginTime;//注册开始时间

    private String endTime;//注册结束时间



    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getHouseInfoid() {
        return houseInfoid;
    }

    public void setHouseInfoid(String houseInfoid) {
        this.houseInfoid = houseInfoid;
    }

    public String getUserIdDto() {
        return userIdDto;
    }

    public void setUserIdDto(String userIdDto) {
        this.userIdDto = userIdDto;
    }

    public String getProjectIdDto() {
        return projectIdDto;
    }

    public void setProjectIdDto(String projectIdDto) {
        this.projectIdDto = projectIdDto;
    }

    public String getRealNameDto() {
        return realNameDto;
    }

    public void setRealNameDto(String realNameDto) {
        this.realNameDto = realNameDto;
    }

    public String getMobileDto() {
        return mobileDto;
    }

    public void setMobileDto(String mobileDto) {
        this.mobileDto = mobileDto;
    }

    public String getUserNameDto() {
        return userNameDto;
    }

    public void setUserNameDto(String userNameDto) {
        this.userNameDto = userNameDto;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHouseUserid() {
        return houseUserid;
    }

    public void setHouseUserid(String houseUserid) {
        this.houseUserid = houseUserid;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getIspay() {
        return ispay;
    }

    public void setIspay(String ispay) {
        this.ispay = ispay;
    }

    public String getOwnerMobile() {
        return ownerMobile;
    }

    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile;
    }
}
