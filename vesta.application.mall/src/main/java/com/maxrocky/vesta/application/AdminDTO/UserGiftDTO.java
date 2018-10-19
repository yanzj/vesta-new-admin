package com.maxrocky.vesta.application.AdminDTO;

import javax.persistence.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/16.
 */
public class UserGiftDTO {


    private String userGiftId;//ID

    private String giftType; //礼物类型  1积分 2商品

    private String number; //积分数量

    private String productId; //商品ID

    public String getUserGiftId() {
        return userGiftId;
    }

    public void setUserGiftId(String userGiftId) {
        this.userGiftId = userGiftId;
    }

    public String getGiftType() {
        return giftType;
    }

    public void setGiftType(String giftType) {
        this.giftType = giftType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
