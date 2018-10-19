package com.maxrocky.vesta.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by liuwei on 2016/3/4.
 */

//@Entity
//@Table(name = "View_App_HadBillDetailInfo")
public class ViewAppHadBillDetialInfoEntity {

    @Id
    @Column(name = "MDP_GUID")
    private String MDPGUID;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "hourse_id")
    private Integer houseId;
    @Column(name = "have_bill")
    private String haveBill;
    @Column(name = "pay_source")
    private String paySource;
    @Column(name = "hourse_desc")
    private String houseDesc;
    @Column(name = "payment_id")
    private String payementId;
    @Column(name = "pay_start_date")
    private Timestamp payStartDate;
    @Column(name = "pay_end_date")
    private Timestamp payEndDate;
    @Column(name = "pay_money")
    private String payMoney ;

    @Column(name = "pay_type")
    private String payType;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "pay_months")
    private Integer payMonths;
    @Column(name = "CompanyID")
    private Integer companyId;
    @Column(name = "ReceiptID")
    private Integer receiptId;
    @Column(name = "pay_time")
    private Timestamp payTime;
    @Column(name = "MDP_BatchTime")
    private Timestamp MDP_BatchTime;
    @Column(name = "MDP_OPERATIONTYPE")
    private String MDP_OPERATIONTYPE;
    @Column(name = "MDP_RESULT")
    private String MDP_RESULT;


    public String getPayMoney() {
        return payMoney;
    }

    public ViewAppHadBillDetialInfoEntity setPayMoney(String payMoney) {
        this.payMoney = payMoney;
        return this;
    }

    public String getMDPGUID() {
        return MDPGUID;
    }

    public ViewAppHadBillDetialInfoEntity setMDPGUID(String MDPGUID) {
        this.MDPGUID = MDPGUID;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public ViewAppHadBillDetialInfoEntity setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public ViewAppHadBillDetialInfoEntity setHouseId(Integer houseId) {
        this.houseId = houseId;
        return this;
    }

    public String getHaveBill() {
        return haveBill;
    }

    public ViewAppHadBillDetialInfoEntity setHaveBill(String haveBill) {
        this.haveBill = haveBill;
        return this;
    }

    public String getPaySource() {
        return paySource;
    }

    public ViewAppHadBillDetialInfoEntity setPaySource(String paySource) {
        this.paySource = paySource;
        return this;
    }

    public String getHouseDesc() {
        return houseDesc;
    }

    public ViewAppHadBillDetialInfoEntity setHouseDesc(String houseDesc) {
        this.houseDesc = houseDesc;
        return this;
    }

    public String getPayementId() {
        return payementId;
    }

    public ViewAppHadBillDetialInfoEntity setPayementId(String payementId) {
        this.payementId = payementId;
        return this;
    }

    public Timestamp getPayStartDate() {
        return payStartDate;
    }

    public ViewAppHadBillDetialInfoEntity setPayStartDate(Timestamp payStartDate) {
        this.payStartDate = payStartDate;
        return this;
    }

    public Timestamp getPayEndDate() {
        return payEndDate;
    }

    public ViewAppHadBillDetialInfoEntity setPayEndDate(Timestamp payEndDate) {
        this.payEndDate = payEndDate;
        return this;
    }

    public String getPayType() {
        return payType;
    }

    public ViewAppHadBillDetialInfoEntity setPayType(String payType) {
        this.payType = payType;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ViewAppHadBillDetialInfoEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getPayMonths() {
        return payMonths;
    }

    public ViewAppHadBillDetialInfoEntity setPayMonths(Integer payMonths) {
        this.payMonths = payMonths;
        return this;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public ViewAppHadBillDetialInfoEntity setCompanyId(Integer companyId) {
        this.companyId = companyId;
        return this;
    }

    public Integer getReceiptId() {
        return receiptId;
    }

    public ViewAppHadBillDetialInfoEntity setReceiptId(Integer receiptId) {
        this.receiptId = receiptId;
        return this;
    }

    public Timestamp getPayTime() {
        return payTime;
    }

    public ViewAppHadBillDetialInfoEntity setPayTime(Timestamp payTime) {
        this.payTime = payTime;
        return this;
    }

    public Timestamp getMDP_BatchTime() {
        return MDP_BatchTime;
    }

    public ViewAppHadBillDetialInfoEntity setMDP_BatchTime(Timestamp MDP_BatchTime) {
        this.MDP_BatchTime = MDP_BatchTime;
        return this;
    }

    public String getMDP_OPERATIONTYPE() {
        return MDP_OPERATIONTYPE;
    }

    public ViewAppHadBillDetialInfoEntity setMDP_OPERATIONTYPE(String MDP_OPERATIONTYPE) {
        this.MDP_OPERATIONTYPE = MDP_OPERATIONTYPE;
        return this;
    }

    public String getMDP_RESULT() {
        return MDP_RESULT;
    }

    public ViewAppHadBillDetialInfoEntity setMDP_RESULT(String MDP_RESULT) {
        this.MDP_RESULT = MDP_RESULT;
        return this;
    }
}
