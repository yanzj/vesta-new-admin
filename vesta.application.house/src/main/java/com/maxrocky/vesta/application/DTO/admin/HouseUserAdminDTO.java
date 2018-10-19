package com.maxrocky.vesta.application.DTO.admin;

/**
 * Created by Tom on 2016/1/20 18:29.
 * Describe:For Service
 */
public class HouseUserAdminDTO {

    private String id;//房产业主关系
    private String userId;//用户Id
    private String userType;//用户类型
    private String address;//房产地址
    private String state;//状态
    private String name;//姓名
    private String phone;//电话
    private String idCardNumber;//身份证号

    public HouseUserAdminDTO(){
        id = "";
        userId = "";
        userType = "";
        address = "";
        state="";
        name="";
        phone="";
        idCardNumber="";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }
}
