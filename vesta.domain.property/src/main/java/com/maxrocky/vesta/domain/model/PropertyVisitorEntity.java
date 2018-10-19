package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 物业门禁管理-访客表
 * Created by WeiYangDong on 2016/11/1.
 */
@Entity
@Table(name = "property_visitor")
public class PropertyVisitorEntity implements Serializable{

    private String id;          //主键Id

    private String projectCode; //项目Code
    private String projectName; //项目名称

    private String houseId;     //房产Id
    private String address;     //房产地址

    private String ownerUserId; //业主Id
    private String ownerName;   //业主姓名
    private String ownerMobile; //业主手机

    private String visitorName;     //访客姓名
    private String visitorMobile;   //访客手机

    private String propertyDoorId;  //门禁Id
    private String url;             //二维码Url地址

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
    @Column(name = "house_id",nullable = true,length = 100)
    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    @Basic
    @Column(name = "address",nullable = true,length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "owner_user_id",nullable = true,length = 100)
    public String getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    @Basic
    @Column(name = "owner_name",nullable = true,length = 100)
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Basic
    @Column(name = "owner_mobile",nullable = true,length = 100)
    public String getOwnerMobile() {
        return ownerMobile;
    }

    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile;
    }

    @Basic
    @Column(name = "visitor_name",nullable = true,length = 100)
    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    @Basic
    @Column(name = "visitor_mobile",nullable = true,length = 100)
    public String getVisitorMobile() {
        return visitorMobile;
    }

    public void setVisitorMobile(String visitorMobile) {
        this.visitorMobile = visitorMobile;
    }

    @Basic
    @Column(name = "property_door_id",nullable = true,length = 100)
    public String getPropertyDoorId() {
        return propertyDoorId;
    }

    public void setPropertyDoorId(String propertyDoorId) {
        this.propertyDoorId = propertyDoorId;
    }

    @Basic
    @Column(name = "url",nullable = true,length = 100)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
