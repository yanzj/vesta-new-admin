package com.maxrocky.vesta.application.DTO.json.HI0008;

/**
 * Created by Tom on 2016/1/20 13:51.
 * Describe:HI0008返回实体类
 */
public class MyHouseReturnJsonDTO {

    private String houseId;//房屋ID
    private String address;//房屋地址

    public MyHouseReturnJsonDTO(){
        houseId = "";
        address = "";
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
