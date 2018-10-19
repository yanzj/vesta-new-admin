package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tom on 2016/1/21 0:02.
 * Describe:业主注册信息实体类
 */
@Entity
@Table(name = "user_authInfo")
public class UserAuthInfoEntity {

    /* 状态 */
    public final static String STATE_AUTO_SUCCEED = "1";//自动通过
    public final static String STATE_AUTO_FAIL = "2";//自动失败
    public final static String STATE_AUDIT_WAIT = "3";//待审核
    public final static String STATE_AUDIT_SUCCEED = "4";//审核通过
    public final static String STATE_AUDIT_FAIL = "5";//审核失败
    public final static String STATE_AUDIT_TENANT = "6";//租户注册

    private String id;//主键
    private String state;//状态
    private String content;//
    private String projectName;//项目id
    private String formatName;//业态Id
    private String buildingName;//楼Id
    private String unitName;//单元Id
    private String roomName;//房间号Id
    private String name;//真实姓名
    private String idNumber;//证件号码
    private String mobile;//手机号码
    private String form;//来源
    private String phoneUUID;//手机唯一标识
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间

    @Id
    @Column(name = "ID",nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "STATE",nullable = false, length = 10)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "CONTENT", length = 20)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "PROJECT_NAME", length = 50)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "FORMAT_NAME", length = 50)
    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    @Basic
    @Column(name = "BUILDING_NAME", length = 50)
    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    @Basic
    @Column(name = "UNIT_NAME", length = 50)
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Basic
    @Column(name = "ROOM_NAME", length = 50)
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Basic
    @Column(name = "NAME",nullable = false, length = 60)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ID_NUMBER",nullable = false, length = 30)
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Basic
    @Column(name = "MOBILE",nullable = false, length = 11)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "FORM", length = 50)
    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    @Basic
    @Column(name = "PHONE_UUID", length = 100)
    public String getPhoneUUID() {
        return phoneUUID;
    }

    public void setPhoneUUID(String phoneUUID) {
        this.phoneUUID = phoneUUID;
    }

    @Basic
    @Column(name = "CREATE_BY",nullable = false, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_ON",nullable = false, length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MODIFY_BY",nullable = false, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "MODIFY_ON",nullable = false)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
}