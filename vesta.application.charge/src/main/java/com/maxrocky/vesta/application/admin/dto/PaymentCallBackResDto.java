package com.maxrocky.vesta.application.admin.dto;

/**
 * Created by liuwei on 2016/3/10.
 */
public class PaymentCallBackResDto {

    private String paymentId;
    private String appOrderNum;
    private String thirdOrderNum;
    private String paymentReceiveAccount;
    private String status;
    private String bankAccountNo;


    public String getPaymentId() {
        return paymentId;
    }

    public PaymentCallBackResDto setPaymentId(String paymentId) {
        this.paymentId = paymentId;
        return this;
    }

    public String getAppOrderNum() {
        return appOrderNum;
    }

    public PaymentCallBackResDto setAppOrderNum(String appOrderNum) {
        this.appOrderNum = appOrderNum;
        return this;
    }

    public String getThirdOrderNum() {
        return thirdOrderNum;
    }

    public PaymentCallBackResDto setThirdOrderNum(String thirdOrderNum) {
        this.thirdOrderNum = thirdOrderNum;
        return this;
    }

    public String getPaymentReceiveAccount() {
        return paymentReceiveAccount;
    }

    public PaymentCallBackResDto setPaymentReceiveAccount(String paymentReceiveAccount) {
        this.paymentReceiveAccount = paymentReceiveAccount;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public PaymentCallBackResDto setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public PaymentCallBackResDto setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
        return this;
    }
}
