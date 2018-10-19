package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 访客通行表
 * Created by WeiYangDong on 2017/12/18.
 */
@Entity
@Table(name = "visitor_pass")
public class VisitorPassEntity {

    //主键ID
    @Id
    @Column(name = "id",nullable = false, length = 32)
    private String id;
    //项目编码
    @Basic
    @Column(name = "project_code", length = 50)
    private String projectCode;
    //项目名称
    @Basic
    @Column(name = "project_name", length = 50)
    private String projectName;
    //房产ID
    @Basic
    @Column(name = "house_room_id", length = 50)
    private String houseRoomId;
    //房产地址
    @Basic
    @Column(name = "house_address", length = 255)
    private String houseAddress;
    //业主姓名
    @Basic
    @Column(name = "owner_name", length = 20)
    private String ownerName;
    //业主电话
    @Basic
    @Column(name = "owner_phone", length = 20)
    private String ownerPhone;
    //访客姓名
    @Basic
    @Column(name = "visitor_name", length = 20)
    private String visitorName;
    //访客电话
    @Basic
    @Column(name = "visitor_phone", length = 20)
    private String visitorPhone;
    //拜访日期
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "visit_date", nullable = true, length = 50)
    private Date visitDate;
    //二维码图片路径
    @Basic
    @Column(name = "path_QRCode", length = 150)
    private String pathQRCode;
    //二维码扫描次数
    @Basic
    @Column(name = "num_QRCode", length = 2)
    private Integer numQRCode;
    //创建人(用户ID)
    @Basic
    @Column(name = "create_by", nullable = true, length = 50)
    private String createBy;
    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    private Date createOn;

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

    public String getPathQRCode() {
        return pathQRCode;
    }

    public void setPathQRCode(String pathQRCode) {
        this.pathQRCode = pathQRCode;
    }

    public String getHouseRoomId() {
        return houseRoomId;
    }

    public void setHouseRoomId(String houseRoomId) {
        this.houseRoomId = houseRoomId;
    }

    public Integer getNumQRCode() {
        return numQRCode;
    }

    public void setNumQRCode(Integer numQRCode) {
        this.numQRCode = numQRCode;
    }
}
