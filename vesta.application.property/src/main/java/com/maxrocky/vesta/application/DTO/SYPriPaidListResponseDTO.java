package com.maxrocky.vesta.application.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * 思源缴费单接口响应参数DTO(JSON—->第五层)
 * Created by WeiYangDong on 2017/1/9.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SYPriPaidListResponseDTO {

    public SYPriPaidListResponseDTO(){}

    @JsonProperty(value = "PriPaid")
    private BigDecimal priPaid;     //本金已收

    @JsonProperty(value = "Payment")
    private String payment;         //交款人

    @JsonProperty(value = "Filldate")
    private String filldate;        //交款日期

    @JsonProperty(value = "LockLogo")
    private int lockLogo;           //收款状态

    @JsonProperty(value = "FNPaidCode")
    private String fNPaidCode;      //实收编码

    @JsonProperty(value = "LFRev")
    private BigDecimal fFRev;       //滞纳金

    @JsonProperty(value = "LFPaid")
    private BigDecimal lFPaid;      //滞纳金已收

    @JsonProperty(value = "PriRelief")
    private BigDecimal priRelief;   //本金减免

    @JsonProperty(value = "LFRelief")
    private BigDecimal lFRelief;    //滞纳金减免

    @JsonProperty(value = "PRemarks")
    private String pRemarks;        //实收摘要

    @JsonProperty(value = "Trading")
    private String trading;         //收款方式

    public BigDecimal getPriPaid() {
        return priPaid;
    }

    public void setPriPaid(BigDecimal priPaid) {
        this.priPaid = priPaid;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getFilldate() {
        return filldate;
    }

    public void setFilldate(String filldate) {
        this.filldate = filldate;
    }

    public int getLockLogo() {
        return lockLogo;
    }

    public void setLockLogo(int lockLogo) {
        this.lockLogo = lockLogo;
    }

    public String getfNPaidCode() {
        return fNPaidCode;
    }

    public void setfNPaidCode(String fNPaidCode) {
        this.fNPaidCode = fNPaidCode;
    }

    public BigDecimal getfFRev() {
        return fFRev;
    }

    public void setfFRev(BigDecimal fFRev) {
        this.fFRev = fFRev;
    }

    public BigDecimal getlFPaid() {
        return lFPaid;
    }

    public void setlFPaid(BigDecimal lFPaid) {
        this.lFPaid = lFPaid;
    }

    public BigDecimal getPriRelief() {
        return priRelief;
    }

    public void setPriRelief(BigDecimal priRelief) {
        this.priRelief = priRelief;
    }

    public BigDecimal getlFRelief() {
        return lFRelief;
    }

    public void setlFRelief(BigDecimal lFRelief) {
        this.lFRelief = lFRelief;
    }

    public String getpRemarks() {
        return pRemarks;
    }

    public void setpRemarks(String pRemarks) {
        this.pRemarks = pRemarks;
    }

    public String getTrading() {
        return trading;
    }

    public void setTrading(String trading) {
        this.trading = trading;
    }
}
