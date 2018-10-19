package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/16.
 */
@Entity
@Table(name = "user_gift")
public class UserGiftEntity {

    @Id
    @Column(name = "user_gift_id", length = 32)
    private String userGiftId;//ID

    @Basic
    @Column(name = "gift_type",length = 50)
    private String giftType; //礼物类型  1积分 2商品

    @Basic
    @Column(name = "number",length = 50)
    private String number; //积分数量

    @Basic
    @Column(name = "product_id",length = 50)
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
