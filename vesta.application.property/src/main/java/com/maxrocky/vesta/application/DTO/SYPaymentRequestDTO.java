package com.maxrocky.vesta.application.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * 思源支付单接口请求参数DTO
 * Created by WeiYangDong on 2017/1/20.
 */
public class SYPaymentRequestDTO {

    @JsonProperty(value = "RevID")
    private String revID;           //应收款ID

    @JsonProperty(value = "RevMoney")
    private BigDecimal revMoney;    //实际收款金额

    @JsonProperty(value = "LFMoney")
    private BigDecimal lFMoney;         //滞纳金金额

    @JsonProperty(value = "Payment")
    private String payment;         //交款人

    @JsonProperty(value = "TradingID")
    private String tradingID;       //收付方式ID

    @JsonProperty(value = "Trading")
    private String trading;         //收付方式

    @JsonProperty(value = "Filldate")
    private String filldate;        //实收日期

    @JsonProperty(value = "Rbank")
    private String rbank;           //银行ID

    @JsonProperty(value = "RAccount")
    private String rAccount;        //银行账号

    @JsonProperty(value = "WX_BillNo")
    private String wXBillNo;       //微信支付单号

    @JsonProperty(value = "OrgID")
    private String orgID;           //项目ID

    public String getRevID() {
        return revID;
    }

    public void setRevID(String revID) {
        this.revID = revID;
    }

    public BigDecimal getRevMoney() {
        return revMoney;
    }

    public void setRevMoney(BigDecimal revMoney) {
        this.revMoney = revMoney;
    }

    public BigDecimal getlFMoney() {
        return lFMoney;
    }

    public void setlFMoney(BigDecimal lFMoney) {
        this.lFMoney = lFMoney;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getTradingID() {
        return tradingID;
    }

    public void setTradingID(String tradingID) {
        this.tradingID = tradingID;
    }

    public String getTrading() {
        return trading;
    }

    public void setTrading(String trading) {
        this.trading = trading;
    }

    public String getFilldate() {
        return filldate;
    }

    public void setFilldate(String filldate) {
        this.filldate = filldate;
    }

    public String getRbank() {
        return rbank;
    }

    public void setRbank(String rbank) {
        this.rbank = rbank;
    }

    public String getrAccount() {
        return rAccount;
    }

    public void setrAccount(String rAccount) {
        this.rAccount = rAccount;
    }

    public String getwXBillNo() {
        return wXBillNo;
    }

    public void setwXBillNo(String wXBillNo) {
        this.wXBillNo = wXBillNo;
    }

    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }
}
