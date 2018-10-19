package com.maxrocky.vesta.application.admin.dto;

/**
 * Created by liuwei on 2016/3/4.
 */
public class PaymentHistoryListResDto {

    private String paymentId;
    private String payMoney;
    private String payDateDetail;
    private String payUserName;
    private String houseAddress;
    private String payDate;
    private String payTypeName ; //缴费类型名字
    private Boolean isEffect; //是否已经通知到业务系统，是否有效

    public String getPayTypeName() {
        return payTypeName;
    }

    public PaymentHistoryListResDto setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
        return this;
    }

    public Boolean getEffect() {
        return isEffect;
    }

    public PaymentHistoryListResDto setEffect(Boolean effect) {
        isEffect = effect;
        return this;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public PaymentHistoryListResDto setPaymentId(String paymentId) {
        this.paymentId = paymentId;
        return this;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public PaymentHistoryListResDto setPayMoney(String payMoney) {
        this.payMoney = payMoney;
        return this;
    }

    public String getPayDateDetail() {
        return payDateDetail;
    }

    public PaymentHistoryListResDto setPayDateDetail(String payDateDetail) {
        this.payDateDetail = payDateDetail;
        return this;
    }

    public String getPayUserName() {
        return payUserName;
    }

    public PaymentHistoryListResDto setPayUserName(String payUserName) {
        this.payUserName = payUserName;
        return this;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public PaymentHistoryListResDto setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
        return this;
    }

    public String getPayDate() {
        return payDate;
    }

    public PaymentHistoryListResDto setPayDate(String payDate) {
        this.payDate = payDate;
        return this;
    }
}
