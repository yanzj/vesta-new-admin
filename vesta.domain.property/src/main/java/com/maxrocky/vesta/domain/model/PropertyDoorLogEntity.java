package com.maxrocky.vesta.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 物业门禁开门日志表
 * Created by 27978 on 2016/11/16.
 */
@Entity
@Table(name = "property_door_log")
public class PropertyDoorLogEntity {
    private String id;               //日志id

    private String projectCode;     //项目编码
    private String projectName;     //项目名称

    private String userId;          //用户id
    private String userName;       //用户名称
    private String userPhone;      //用户电话

    private String macName;        //设备Mac名称
    private String macAddress;     //设备Mac地址
    private String deviceDesc;     //设备描述
    private String deviceType;     //设备类型(仅提供蓝牙的门禁设备:1,提供蓝牙及无线网络的门禁设备:2)

    private Date openDateTime;   //开门时间

    @Id
    @Column(name = "id", nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "project_code", nullable = true)
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    @Column(name = "project_name", nullable = true)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Column(name = "user_id", nullable = true)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "user_name", nullable = true)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "user_phone", nullable = true)
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Column(name = "mac_name", nullable = true)
    public String getMacName() {
        return macName;
    }

    public void setMacName(String macName) {
        this.macName = macName;
    }

    @Column(name = "mac_address", nullable = true)
    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    @Column(name = "device_desc", nullable = true)
    public String getDeviceDesc() {
        return deviceDesc;
    }

    public void setDeviceDesc(String deviceDesc) {
        this.deviceDesc = deviceDesc;
    }

    @Column(name = "device_type", nullable = true)
    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @Column(name = "open_dateTime", nullable = true)
    public Date getOpenDateTime() {
        return openDateTime;
    }

    public void setOpenDateTime(Date openDateTime) {
        this.openDateTime = openDateTime;
    }
}
