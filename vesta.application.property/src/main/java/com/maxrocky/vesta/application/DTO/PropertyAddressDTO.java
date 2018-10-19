package com.maxrocky.vesta.application.DTO;

/**
 * Created by liudongxin on 2016/1/23.
 * 业主信息：地址 业主房屋ID
 */
public class PropertyAddressDTO {
    private String address;//业主地址
    private String houseInfoId;//业主房屋ID

    public String getHouseInfoId() {
        return houseInfoId;
    }

    public void setHouseInfoId(String houseInfoId) {
        this.houseInfoId = houseInfoId;
    }

    public PropertyAddressDTO() {

        this.address = "";
        this.houseInfoId = "";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}