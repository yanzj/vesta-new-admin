package com.maxrocky.vesta.application.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 思源支付单接口响应参数DTO
 * Created by WeiYangDong on 2017/1/20.
 */
public class SYPaymentResultDTO {

    @JsonProperty(value = "RevID")
    private String revID;               //应收款ID
    @JsonProperty(value = "Code")
    private String code;                //数据回执
    @JsonProperty(value = "CRMResName")
    private String cRMResName;          //房间编码(CRM)
    @JsonProperty(value = "RevMoney")
    private String revMoney;            //实际收款金额
    @JsonProperty(value = "PaymentState")
    private String paymentState;        //缴费单状态(已交清0/未交清1)

    public String getRevID() {
        return revID;
    }

    public void setRevID(String revID) {
        this.revID = revID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getcRMResName() {
        return cRMResName;
    }

    public void setcRMResName(String cRMResName) {
        this.cRMResName = cRMResName;
    }

    public String getRevMoney() {
        return revMoney;
    }

    public void setRevMoney(String revMoney) {
        this.revMoney = revMoney;
    }

    public String getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }
}
