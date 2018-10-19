package com.maxrocky.vesta.application.DTO.json.UI0012;

/**
 * Created by Tom on 2016/2/21 11:37.
 * Describe:UI00012返回实体类
 */
public class HouseUserReturnJsonDTO {

    private String id;//授权ID
    private String address;//房屋地址
    private String role;//授权角色
    private String realName;//真实姓名
    private String mobile;//手机
    private Boolean isPay;//授权缴费

    public HouseUserReturnJsonDTO(){
        this.id = "";
        this.address = "";
        this.role = "";
        this.realName = "";
        this.mobile = "";
        this.isPay = false;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getIsPay() {
        return isPay;
    }

    public void setIsPay(Boolean isPay) {
        this.isPay = isPay;
    }
}
