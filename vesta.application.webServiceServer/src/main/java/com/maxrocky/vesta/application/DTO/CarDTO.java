package com.maxrocky.vesta.application.DTO;

/**
 * Created by liudongxin on 2016/4/13.
 * 会员车辆信息
 */
public class CarDTO {

    private String id;//车辆id
    private String memberId;//会员编号
    private String licenseId;//车牌号
    private String carType;//车辆类型
    private String carPower;//车辆动力
    private String stateCode;//数据删除标识:0可用，1 停用

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarPower() {
        return carPower;
    }

    public void setCarPower(String carPower) {
        this.carPower = carPower;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}
