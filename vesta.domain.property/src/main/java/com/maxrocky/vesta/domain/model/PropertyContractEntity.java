package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author WeiYangDong
 * @date 2018/3/8 17:00
 * @deprecated 合同表
 */
@Entity
@Table(name = "property_contract")
public class PropertyContractEntity {
    public static final String HANDLEMODE_AGENCY = "AGENCY";//办理方式_代办
    public static final String HANDLEMODE_SELF = "SELF";//办理方式_自办

    private String propertyId;//主键Id

    private String projectCode; //项目编码
    private String projectName; //项目名称

    private String blockCode;   //地块编码
    private String area;        //地块

    private String buildingNum;     //楼号编码
    private String buildingRecord;  //备案楼号
    private String buildingSale;    //预售楼号

    private String unitCode;        //单元编码
    private String unit;            //单元

    private String floorCode;       //楼层编码
    private String floor;           //楼层

    private String roomNum;         //房屋编码
    private String roomName;        //房屋号
    private String roomAddress;     //房屋地址
    private String ownerName;       //业主姓名

    private String propertyType;    //合同类型
    private String handleMode;      //办理方式
    private String handleProgress;  //办理进度
    private String handleStatus;    //办理状态(0,正常状态;1,待提交资料)

    private String createBy;    //创建人
    private Date createOn;      //创建时间
    private String modifyBy;    //修改人
    private Date modifyOn;      //修改时间

    @Id
    @Column(name = "property_id",nullable = false,length = 32)
    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    @Basic
    @Column(name = "project_code",nullable = true,length = 100)
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    @Basic
    @Column(name = "project_name",nullable = true,length = 100)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "block_code",nullable = true,length = 100)
    public String getBlockCode() {
        return blockCode;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }

    @Basic
    @Column(name = "area",nullable = true,length = 100)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "building_num",nullable = true,length = 100)
    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    @Basic
    @Column(name = "building_record",nullable = true,length = 20)
    public String getBuildingRecord() {
        return buildingRecord;
    }

    public void setBuildingRecord(String buildingRecord) {
        this.buildingRecord = buildingRecord;
    }

    @Basic
    @Column(name = "building_sale",nullable = true,length = 20)
    public String getBuildingSale() {
        return buildingSale;
    }

    public void setBuildingSale(String buildingSale) {
        this.buildingSale = buildingSale;
    }

    @Basic
    @Column(name = "unit_code",nullable = true,length = 100)
    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    @Basic
    @Column(name = "unit",nullable = true,length = 20)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "floor_code",nullable = true,length = 100)
    public String getFloorCode() {
        return floorCode;
    }

    public void setFloorCode(String floorCode) {
        this.floorCode = floorCode;
    }

    @Basic
    @Column(name = "floor",nullable = true,length = 20)
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @Basic
    @Column(name = "room_num",nullable = true,length = 50)
    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    @Basic
    @Column(name = "room_name",nullable = true,length = 20)
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Basic
    @Column(name = "room_address",nullable = true,length = 100)
    public String getRoomAddress() {
        return roomAddress;
    }

    public void setRoomAddress(String roomAddress) {
        this.roomAddress = roomAddress;
    }

    @Basic
    @Column(name = "owner_name",nullable = true,length = 20)
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Basic
    @Column(name = "property_type",nullable = true,length = 2)
    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    @Basic
    @Column(name = "handle_mode",nullable = true,length = 20)
    public String getHandleMode() {
        return handleMode;
    }

    public void setHandleMode(String handleMode) {
        this.handleMode = handleMode;
    }

    @Basic
    @Column(name = "handle_progress",nullable = true,length = 50)
    public String getHandleProgress() {
        return handleProgress;
    }

    public void setHandleProgress(String handleProgress) {
        this.handleProgress = handleProgress;
    }

    @Basic
    @Column(name = "handle_status",nullable = true,length = 2)
    public String getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus;
    }

    @Basic
    @Column(name = "create_by", nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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
    @Column(name = "modify_by", nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_on", nullable = true, length = 50)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
}
