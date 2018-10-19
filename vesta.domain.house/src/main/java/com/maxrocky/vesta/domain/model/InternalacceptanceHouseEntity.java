package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dl on 2016/5/9.
 *  Describe:房屋内部预验实体类
 */
@Entity
@Table(name = "house_acceptance")
public class InternalacceptanceHouseEntity {
    private Long id;
    private String interdeliveryPla;//计划
    private String houseCode;//房间
    private String acceptanceStatus;//内部预验状态
    private String creaName;//创建人
    private Date creaTime;//创建时间
    private String updateName;//陪验人 = 最后修改人
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
    @Column(name = "INTERDELIVERYPLA",nullable = true, length = 50)
    public String getInterdeliveryPla() {
        return interdeliveryPla;
    }

    public void setInterdeliveryPla(String interdeliveryPla) {
        this.interdeliveryPla = interdeliveryPla;
    }
    @Basic
    @Column(name = "HOUSECODE",nullable = true, length = 50)
    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }
    @Basic
    @Column(name = "ACCEPTANCESTATUS",nullable = true, length = 50)
    public String getAcceptanceStatus() {
        return acceptanceStatus;
    }
    public void setAcceptanceStatus(String acceptanceStatus) {
        this.acceptanceStatus = acceptanceStatus;
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
