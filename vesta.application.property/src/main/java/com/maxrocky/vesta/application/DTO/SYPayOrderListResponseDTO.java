package com.maxrocky.vesta.application.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.maxrocky.vesta.axis.DevelopReceiveServerStub;

import java.math.BigDecimal;
import java.util.List;

/**
 * 思源缴费单接口响应参数DTO(JSON—->第四层)
 * Created by WeiYangDong on 2017/1/9.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SYPayOrderListResponseDTO {

    public SYPayOrderListResponseDTO(){}

    @JsonProperty(value = "RevID")
    private String revID;           //缴费单ID

    @JsonProperty(value = "SYResName")
    private String sYResName;       //资源编码(思源)

    @JsonProperty(value = "CRMResName")
    private String cRMResName;      //房间编码(CRM)

    @JsonProperty(value = "OrgID")
    private String orgID;           //项目编码

    @JsonProperty(value = "CstName")
    private String cstName;         //当前房间业主

    @JsonProperty(value = "IpItemName")
    private String ipItemName;      //收费类目

    @JsonProperty(value = "PendingLogo")
    private int pendingLogo;        //挂起标志

    @JsonProperty(value = "RepYears")
    private String repYears;        //所属账期

    @JsonProperty(value = "PPlanBal")
    private BigDecimal pPlanBal;        //预缴费余额

    @JsonProperty(value = "PriRev")
    private BigDecimal priRev;          //本金应收(物业缴费金额,到两位小数,单位为元)

    @JsonProperty(value = "PriPaid")
    private BigDecimal priPaid;         //本金已收

    @JsonProperty(value = "PriFailures")
    private BigDecimal priFailures;     //本金欠收

    @JsonProperty(value = "PriRevTolal")
    private BigDecimal priRevTolal;     //本金已收合计

    @JsonProperty(value = "RevAbstract")
    private String revAbstract;         //应收摘要

    @JsonProperty(value = "StaNmarks")
    private String staNmarks;           //收费标准相关信息

    @JsonProperty(value = "DateRecord")
    private String dateRecord;          //录入账期

    @JsonProperty(value = "RevPSDate")
    private String revPSDate;           //应收时间开始日期

    @JsonProperty(value = "RevPEDate")
    private String revPEDate;           //应收时间结束日期

    @JsonProperty(value = "PriPaidTolalList")
    private List<SYPriPaidListResponseDTO> priPaidTolalList;    //本金已收列表

    public String getcRMResName() {
        return cRMResName;
    }

    public void setcRMResName(String cRMResName) {
        this.cRMResName = cRMResName;
    }

    public String getRevID() {
        return revID;
    }

    public void setRevID(String revID) {
        this.revID = revID;
    }

    public String getsYResName() {
        return sYResName;
    }

    public void setsYResName(String sYResName) {
        this.sYResName = sYResName;
    }

    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public String getRevPEDate() {
        return revPEDate;
    }

    public void setRevPEDate(String revPEDate) {
        this.revPEDate = revPEDate;
    }

    public String getCstName() {
        return cstName;
    }

    public void setCstName(String cstName) {
        this.cstName = cstName;
    }

    public String getIpItemName() {
        return ipItemName;
    }

    public void setIpItemName(String ipItemName) {
        this.ipItemName = ipItemName;
    }

    public int getPendingLogo() {
        return pendingLogo;
    }

    public void setPendingLogo(int pendingLogo) {
        this.pendingLogo = pendingLogo;
    }

    public String getRepYears() {
        return repYears;
    }

    public void setRepYears(String repYears) {
        this.repYears = repYears;
    }

    public BigDecimal getpPlanBal() {
        return pPlanBal;
    }

    public void setpPlanBal(BigDecimal pPlanBal) {
        this.pPlanBal = pPlanBal;
    }

    public BigDecimal getPriRev() {
        return priRev;
    }

    public void setPriRev(BigDecimal priRev) {
        this.priRev = priRev;
    }

    public BigDecimal getPriPaid() {
        return priPaid;
    }

    public void setPriPaid(BigDecimal priPaid) {
        this.priPaid = priPaid;
    }

    public BigDecimal getPriFailures() {
        return priFailures;
    }

    public void setPriFailures(BigDecimal priFailures) {
        this.priFailures = priFailures;
    }

    public BigDecimal getPriRevTolal() {
        return priRevTolal;
    }

    public void setPriRevTolal(BigDecimal priRevTolal) {
        this.priRevTolal = priRevTolal;
    }

    public String getRevAbstract() {
        return revAbstract;
    }

    public void setRevAbstract(String revAbstract) {
        this.revAbstract = revAbstract;
    }

    public String getStaNmarks() {
        return staNmarks;
    }

    public void setStaNmarks(String staNmarks) {
        this.staNmarks = staNmarks;
    }

    public String getDateRecord() {
        return dateRecord;
    }

    public void setDateRecord(String dateRecord) {
        this.dateRecord = dateRecord;
    }

    public String getRevPSDate() {
        return revPSDate;
    }

    public void setRevPSDate(String revPSDate) {
        this.revPSDate = revPSDate;
    }

    public List<SYPriPaidListResponseDTO> getPriPaidTolalList() {
        return priPaidTolalList;
    }

    public void setPriPaidTolalList(List<SYPriPaidListResponseDTO> priPaidTolalList) {
        this.priPaidTolalList = priPaidTolalList;
    }
}
