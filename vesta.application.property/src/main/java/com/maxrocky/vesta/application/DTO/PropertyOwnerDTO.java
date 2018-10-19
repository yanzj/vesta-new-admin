package com.maxrocky.vesta.application.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudongxin on 2016/1/14.
 * 业主端：添加报修单(业主信息)、获取报修价格/员工端：添加报修单(员工信息)
 */
public class PropertyOwnerDTO {
    private List<PropertyAddressDTO> addressList;//地址
    private String userName;//用户姓名
    private String userDepartment;//部门
    private String userPhone;//用户电话
    private String priceContent;//报修价格内容
    public PropertyOwnerDTO() {
        this.userName = "";
        this.userDepartment="";
        this.userPhone = "";
        this.priceContent="";
        this.addressList=new ArrayList<PropertyAddressDTO>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public List<PropertyAddressDTO> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<PropertyAddressDTO> addressList) {
        this.addressList = addressList;
    }

    public String getPriceContent() {
        return priceContent;
    }

    public void setPriceContent(String priceContent) {
        this.priceContent = priceContent;
    }
}