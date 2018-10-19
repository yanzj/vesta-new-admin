package com.maxrocky.vesta.application.admin.dto;

/**
 * Created by liuwei on 2016/3/5.
 */
public class BillInfoAdminListResDto {

    private String billInfoId;
    private String payerName;
    private String payerPhone;
    private String payType;
    private String houseInfo;
    private String payTime;
    private String billSendWay;
    private String billTitle;
    private String processStatus;
    private String processUser;
    private String processTime;
    private String processStatusValue;

    public String getProcessStatusValue() {
        return processStatusValue;
    }

    public BillInfoAdminListResDto setProcessStatusValue(String processStatusValue) {
        this.processStatusValue = processStatusValue;
        return this;
    }

    public String getBillInfoId() {
        return billInfoId;
    }

    public BillInfoAdminListResDto setBillInfoId(String billInfoId) {
        this.billInfoId = billInfoId;
        return this;
    }

    public String getPayerName() {
        return payerName;
    }

    public BillInfoAdminListResDto setPayerName(String payerName) {
        this.payerName = payerName;
        return this;
    }

    public String getPayerPhone() {
        return payerPhone;
    }

    public BillInfoAdminListResDto setPayerPhone(String payerPhone) {
        this.payerPhone = payerPhone;
        return this;
    }

    public String getPayType() {
        return payType;
    }

    public BillInfoAdminListResDto setPayType(String payType) {
        this.payType = payType;
        return this;
    }

    public String getHouseInfo() {
        return houseInfo;
    }

    public BillInfoAdminListResDto setHouseInfo(String houseInfo) {
        this.houseInfo = houseInfo;
        return this;
    }

    public String getPayTime() {
        return payTime;
    }

    public BillInfoAdminListResDto setPayTime(String payTime) {
        this.payTime = payTime;
        return this;
    }

    public String getBillSendWay() {
        return billSendWay;
    }

    public BillInfoAdminListResDto setBillSendWay(String billSendWay) {
        this.billSendWay = billSendWay;
        return this;
    }

    public String getBillTitle() {
        return billTitle;
    }

    public BillInfoAdminListResDto setBillTitle(String billTitle) {
        this.billTitle = billTitle;
        return this;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public BillInfoAdminListResDto setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
        return this;
    }

    public String getProcessUser() {
        return processUser;
    }

    public BillInfoAdminListResDto setProcessUser(String processUser) {
        this.processUser = processUser;
        return this;
    }

    public String getProcessTime() {
        return processTime;
    }

    public BillInfoAdminListResDto setProcessTime(String processTime) {
        this.processTime = processTime;
        return this;
    }
}
