package com.maxrocky.vesta.application.admin.dto;

/**
 * Created by liuwei on 2016/2/14.
 */
public class PaymentDetailResDto {

    private String paymentId;
    private String paymentDate;
    private String payType; //支付类型
    private String payMoney; //待缴金额
    private String price; //单价
    private String houseInfo;
    private String area; //面积

    public String getPaymentId() {
        return paymentId;
    }

    public PaymentDetailResDto setPaymentId(String paymentId) {
        this.paymentId = paymentId;
        return this;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public PaymentDetailResDto setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
        return this;
    }

    public String getPayType() {
        return payType;
    }

    public PaymentDetailResDto setPayType(String payType) {
        this.payType = payType;
        return this;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public PaymentDetailResDto setPayMoney(String payMoney) {
        this.payMoney = payMoney;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public PaymentDetailResDto setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getHouseInfo() {
        return houseInfo;
    }

    public PaymentDetailResDto setHouseInfo(String houseInfo) {
        this.houseInfo = houseInfo;
        return this;
    }

    public String getArea() {
        return area;
    }

    public PaymentDetailResDto setArea(String area) {
        this.area = area;
        return this;
    }
}
