package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dl on 2016/5/9.
 * 业主开放日实体类
 */
@Entity
@Table(name = "house_open")
public class HouseOpenEntity {
    private Long id;
    private String deliveryPlan;//计划
    private String houseCode;//房间
    private String completedStatus;//业主开放日状态
    private String describes;//业主开放日描述
    private String quality;//工程质量
    private String schedulesatisfied;//活动组织
    private String creaName;//创建人
    private Date creaTime;//创建时间
    private String updateName;//修改人
    private Date updateTime;//修改时间
    private String customerName;//客户姓名
    private Date inspectionTime;//验放时间   签字时间

    @Id
    @Column(name = "ID",nullable = false, length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "DELIVERY_PLAN",nullable = true, length = 50)
    public String getDeliveryPlan() {
        return deliveryPlan;
    }

    public void setDeliveryPlan(String deliveryPlan) {
        this.deliveryPlan = deliveryPlan;
    }

    @Basic
    @Column(name = "HOUSE_CODE",nullable = true, length = 50)
    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    @Basic
    @Column(name = "COMPLETED_STATUS",nullable = true, length = 50)
    public String getCompletedStatus() {
        return completedStatus;
    }

    public void setCompletedStatus(String completedStatus) {
        this.completedStatus = completedStatus;
    }

    @Basic
    @Column(name = "DESCRIBES",nullable = true, length = 50)
    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    @Basic
    @Column(name = "QUALITY",nullable = true, length = 50)
    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    @Basic
    @Column(name = "ACTIVITY",nullable = true, length = 50)
    public String getSchedulesatisfied() {
        return schedulesatisfied;
    }

    public void setSchedulesatisfied(String schedulesatisfied) {
        this.schedulesatisfied = schedulesatisfied;
    }
    @Basic
    @Column(name = "UPDATETIME",nullable = true, length = 100)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    @Basic
    @Column(name = "CREANAME",nullable = true, length = 100)
    public String getCreaName() {
        return creaName;
    }

    public void setCreaName(String creaName) {
        this.creaName = creaName;
    }
    @Basic
    @Column(name = "UPDATENAME",nullable = true, length = 100)
    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }
    @Basic
    @Column(name = "CREATIME",nullable = true, length = 100)
    public Date getCreaTime() {
        return creaTime;
    }

    public void setCreaTime(Date creaTime) {
        this.creaTime = creaTime;
    }
    @Basic
    @Column(name = "CUSTOMER_NAME",nullable = true, length = 100)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    @Basic
    @Column(name = "INSPECTION_TIME",nullable = true, length = 100)
    public Date getInspectionTime() {
        return inspectionTime;
    }

    public void setInspectionTime(Date inspectionTime) {
        this.inspectionTime = inspectionTime;
    }
}
