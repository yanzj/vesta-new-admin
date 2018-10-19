package com.maxrocky.vesta.application.DTO.json.HI0007;

/**
 * Created by Tom on 2016/1/20 12:01.
 * Describe:UI0007返回接口
 */
public class HouseInfoReturnJsonDTO {

    private String id;//ID
    private String address;//地址

    public HouseInfoReturnJsonDTO(){
        id = "";
        address = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
