package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 物业缴费票据表
 * Created by WeiYangDong on 2016/10/9.
 */
@Entity
@Table(name = "property_pay_invoice")
public class PropertyPayInvoiceEntity {
    /* 发票状态 */
    public final static String TYPE_ELECTRON_INVOICE = "0";     //电子发票
    public final static String TYPE_ORDINARY_INVOICE = "1";     //普通发票
    public final static String TYPE_INCREMENT_INVOICE = "2";    //增值专票
    public final static String TYPE_ORDINARY_INVOICE_AL = "3";  //普票已开
    public final static String TYPE_INCREMENT_INVOICE_AL = "4"; //专票已开

    /* 发票递取方式 */
    public final static String MODE_ONESELF = "ONESELF";        //自取
    public final static String MODE_BUTLER = "BUTLER";          //管家递送
    public final static String MODE_EXPRESS = "EXPRESS";        //快递

    private String invoiceId;       //票据Id
    private String paymentId;       //支付订单Id
    private String invoiceType;     //票据类型
    private String invoiceHeader;   //发票抬头
    private String invoiceNum;      //发票号码
    private String invoiceMail;     //发票递取方式
    private String expressAddress;  //快递地址
    private Integer invoiceState;   //票据状态(0:未开票,1:已开票)

    private String createBy;    //创建人
    private Date createOn;      //创建时间
    private String modifyBy;    //修改人
    private Date modifyOn;      //修改时间


    @Id
    @Column(name = "invoice_id",nullable = false, length = 100)
    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Basic
    @Column(name = "payment_id", length = 100)
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @Basic
    @Column(name = "invoice_type", length = 100)
    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    @Basic
    @Column(name = "invoice_header", length = 1000)
    public String getInvoiceHeader() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(String invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }

    @Basic
    @Column(name = "invoice_num", length = 200)
    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    @Basic
    @Column(name = "invoice_mail", length = 100)
    public String getInvoiceMail() {
        return invoiceMail;
    }

    public void setInvoiceMail(String invoiceMail) {
        this.invoiceMail = invoiceMail;
    }

    @Basic
    @Column(name = "express_address", length = 500)
    public String getExpressAddress() {
        return expressAddress;
    }

    public void setExpressAddress(String expressAddress) {
        this.expressAddress = expressAddress;
    }

    @Basic
    @Column(name = "invoice_state")
    public Integer getInvoiceState() {
        return invoiceState;
    }

    public void setInvoiceState(Integer invoiceState) {
        this.invoiceState = invoiceState;
    }

    @Basic
    @Column(name = "create_by", nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "modify_by", nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_on", nullable = true, length = 50)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
}
