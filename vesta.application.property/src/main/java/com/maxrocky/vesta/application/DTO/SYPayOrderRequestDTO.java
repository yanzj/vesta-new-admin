package com.maxrocky.vesta.application.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 思源缴费单接口请求参数DTO
 * Created by WeiYangDong on 2017/1/5.
 */
public class SYPayOrderRequestDTO {

    @JsonProperty(value = "StartDate")
    private String startDate;       //检索开始时间(2016-01)
    @JsonProperty(value = "EndDate")
    private String endDate;         //检索结束时间(2016-12)
    @JsonProperty(value = "OrgID")
    private String orgID;           //项目编码(01)
    @JsonProperty(value = "CRMResName")
    private String crmResName;      //CRM房间编码(CQ-JMLY-NQ-15-#-30F-3002)
    @JsonProperty(value = "PaymentState")
    private String paymentState;    //缴费状态标示(已交清0/未交清1)
    @JsonProperty(value = "IpItemID")
    private List<String> ipItemID;        //收费项目ID(物业费:14061015341300000000)

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOrgID() {
        return orgID;
    }

    public void setOrgID(String orgID) {
        this.orgID = orgID;
    }

    public String getCrmResName() {
        return crmResName;
    }

    public void setCrmResName(String crmResName) {
        this.crmResName = crmResName;
    }

    public String getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    public List<String> getIpItemID() {
        return ipItemID;
    }

    public void setIpItemID(List<String> ipItemID) {
        this.ipItemID = ipItemID;
    }
}
