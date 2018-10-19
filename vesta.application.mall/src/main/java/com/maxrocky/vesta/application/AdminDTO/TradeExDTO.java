package com.maxrocky.vesta.application.AdminDTO;

import java.util.Date;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/16.
 */
public class TradeExDTO {


    private String tradeNo;//订单号

    private String productName; //商品名称

    private Date createOn; //创建日期

    private String number;//数量

    private String amount;//支付积分

    private String payment;//支付金额

    private String name;//业主名称

    private String phone;//手机号

    private String tradeStatus; //订单状态 1已支付 2已发货

    private String address; //地址


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        if(tradeStatus.equals("1")){
            this.tradeStatus = "已付款";
        }else if(tradeStatus.equals("2")){
            this.tradeStatus = "已发货";
        }else{
            this.tradeStatus = "";
        }

    }
}
