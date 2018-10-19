package com.maxrocky.vesta.application.DTO.admin;

import com.maxrocky.vesta.domain.model.HouseUserBookEntity;
import com.maxrocky.vesta.utility.StringUtil;

/**
 * Created by Tom on 2016/2/21 11:45.
 * Describe:授权信息
 */
public class HouseUserBookAdminDTO {

    private String id;//主键
    private String userId;//用户Id
    private String houseId;//房屋Id
    private String address;//地址
    private String userType;//用户类型
    private String state;//状态
    private String isPay;//是否可缴费

    public HouseUserBookAdminDTO(){
        this.id = "";
        this.userId = "";
        this.houseId = "";
        this.address = "";
        this.userType = "";
        this.state = "";
        this.isPay = "";
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    /**
     * 是否有效
     */
    public Boolean isNormal(){
        if(StringUtil.isEqual(state, HouseUserBookEntity.STATE_NORMAL)){
            return true;
        }
        return false;
    }

    /**
     * 授权缴费
     */
    public Boolean isPayment(){
        if(StringUtil.isEqual(isPay, HouseUserBookEntity.IS_PAY_YES)){
            return true;
        }
        return false;
    }

}
