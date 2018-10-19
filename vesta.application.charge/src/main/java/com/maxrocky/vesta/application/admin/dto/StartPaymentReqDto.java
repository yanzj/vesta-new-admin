package com.maxrocky.vesta.application.admin.dto;

/**
 * Created by liuwei on 2016/2/14.
 */
public class StartPaymentReqDto {

    private String paymentId;

    private String billInfo; //发票信息

    private String sendWay;

    public String getSendWay() {
        return sendWay;
    }

    public StartPaymentReqDto setSendWay(String sendWay) {
        this.sendWay = sendWay;
        return this;
    }

    public StartPaymentReqDto setBillInfo(String billInfo) {
        this.billInfo = billInfo;
        return this;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public StartPaymentReqDto setPaymentId(String paymentId) {
        this.paymentId = paymentId;
        return this;
    }

    public String getBillInfo() {
        return billInfo;
    }
}
