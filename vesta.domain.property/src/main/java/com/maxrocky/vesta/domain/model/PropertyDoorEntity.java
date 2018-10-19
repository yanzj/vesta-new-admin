package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 物业门禁管理-门禁表
 * Created by WeiYangDong on 2016/10/31.
 */
@Entity
@Table(name = "property_door")
public class PropertyDoorEntity implements Serializable{

    private String id;          //主键Id

    private String macAddress;  //设备Mac地址
    private String devicePwd;   //设备密码
    private String deviceDesc;  //设备描述
    private String deviceType;  //设备类型(仅提供蓝牙的门禁设备:1,提供蓝牙及无线网络的门禁设备:2)

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

    private String createBy;    //创建人
    private Date createOn;      //创建时间
    private String modifyBy;    //修改人
    private Date modifyOn;      //修改时间

    @Id
    @Column(name = "id",nullable = false,length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "mac_address",nullable = true,length = 100)
    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    @Basic
    @Column(name = "device_pwd",nullable = true,length = 100)
    public String getDevicePwd() {
        return devicePwd;
    }

    public void setDevicePwd(String devicePwd) {
        this.devicePwd = devicePwd;
    }

    @Basic
    @Column(name = "device_desc",nullable = true,length = 500)
    public String getDeviceDesc() {
        return deviceDesc;
    }

    public void setDeviceDesc(String deviceDesc) {
        this.deviceDesc = deviceDesc;
    }

    @Basic
    @Column(name = "device_type",nullable = true,length = 20)
    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
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
