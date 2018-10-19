package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/16.
 */
@Entity
@Table(name = "trade")
public class TradeEntity {
    @Id
    @Column(name = "trade_id", length = 32)
    private String tradeId;//ID

    @Basic
    @Column(name = "trade_no",length = 50)
    private String tradeNo;//订单号

    @Basic
    @Column(name = "product_id",length = 50)
    private String productId; //商品ID

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    private Date createOn; //创建日期

    @Basic
    @Column(name = "number",length = 50)
    private String number;//数量

    @Basic
    @Column(name = "amount",length = 50)
    private String amount;//支付积分

    @Basic
    @Column(name = "payment",length = 50)
    private String payment;//支付金额

    @Basic
    @Column(name = "name",length = 50)
    private String name;//业主名称

    @Basic
    @Column(name = "phone",length = 50)
    private String phone;//手机号

    @Basic
    @Column(name = "address",length = 50)
    private String address; //收货地址

    @Basic
    @Column(name = "message",length = 50)
    private String message; //备注

    @Basic
    @Column(name = "trade_status",length = 50)
    private String tradeStatus; //订单状态 1已支付 2已发货

    @Basic
    @Column(name = "user_id",length = 50)
    private String userId; //用户信息


    @Basic
    @Column(name = "weChatAppId",length = 250)
    private String weChatAppId; //微信号

    public String getWeChatAppId() {
        return weChatAppId;
    }

    public void setWeChatAppId(String weChatAppId) {
        this.weChatAppId = weChatAppId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
