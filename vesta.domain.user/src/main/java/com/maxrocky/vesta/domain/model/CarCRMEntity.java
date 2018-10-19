package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liudongxin on 2016/4/28.
 * 车辆信息
 */
@Entity
@Table(name = "car_crm")
public class CarCRMEntity {
    private String id;
    private String memberId;//会员编号
    private String licenseId;//车牌号
    private String carType;//车辆类型
    private String carPower;//车辆动力
    private Date modifyDate;//记录更新日期
    private String stateCode;//删除标记 1:删除，0：正常

    @Id
    @Column(name = "ID",nullable = false, length = 32)
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "MEMBER_ID",nullable = true,length = 50)
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    @Basic
    @Column(name = "LICENSE_ID",nullable = true , length = 10)
    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }
    @Basic
    @Column(name = "CAR_TYPE",nullable = true,length = 30)
    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
    @Basic
    @Column(name = "CAR_POWER",nullable = true,length = 30)
    public String getCarPower() {
        return carPower;
    }

    public void setCarPower(String carPower) {
        this.carPower = carPower;
    }

    @Basic
    @Column(name = "MODIFY_DATE",nullable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Basic
    @Column(name = "STATE_CODE",nullable = true,length = 2)
    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}
