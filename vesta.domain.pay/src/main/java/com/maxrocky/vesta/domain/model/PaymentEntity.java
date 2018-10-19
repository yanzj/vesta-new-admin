package com.maxrocky.vesta.domain.model;

import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Tom on 2016/1/26 9:48.
 * Describe:支付实体类
 */
@Entity
@Table(name = "pay_payment")
public class PaymentEntity {

    /* 支付类型 */
    public final static String TYPE_ALI_PAY_APP = "ALI_APP";//支付宝APP支付
    public final static String TYPE_WECHAT_PAY_APP = "WECHAT_APP";//微信APP支付
    public final static String TYPE_BILL_PAY_WAP = "BILL_WAP";//快钱手机网关支付

    /* 支付状态 */
    public final static String STATE_WAIT = "WAIT";//等待回调
    public final static String STATE_SUCCESS = "SUCCESS";//支付成功
    public final static String STATE_FAIL = "FAIL";//支付失败

    private String id;//主键
    private String payOrderId;//支付单号
    private BigDecimal totalFee;//支付金额
    private String subject;//支付标题
    private String body;//支付描述
    private String type;//支付类型
    private String state;//状态
    private String transActionId;//微信支付订单号
    private String billDealId;//快钱交易号，商户每一笔交易都会在快钱生成一个交易号。
    private String billBankDealId;//银行交易号 ，快钱交易在银行支付时对应的交易号，如果不是通过银行卡支付，则为空
    private Date receiptTime;//接收时间
    private Date callBackTime;//回调时间
    private String bankAccount;//银行账号

    @Id
    @Column(name = "ID",nullable = false, length = 100)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PAY_ORDER_ID",nullable = false, length = 1000)
    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }

    @Basic
    @Column(name = "TOTAL_FEE",nullable = false, precision = 18, scale = 4)
    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    @Basic
    @Column(name = "SUBJECT", length = 1000)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "BODY", length = 1000)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "TYPE",nullable = false, length = 20)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "STATE",nullable = false, length = 20)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "TRANS_ACTION_ID", length = 100)
    public String getTransActionId() {
        return transActionId;
    }

    public void setTransActionId(String transActionId) {
        this.transActionId = transActionId;
    }

    @Basic
    @Column(name = "BILL_DEAL_ID", length = 200)
    public String getBillDealId() {
        return billDealId;
    }

    public void setBillDealId(String billDealId) {
        this.billDealId = billDealId;
    }

    @Basic
    @Column(name = "BILL_BANK_DEAL_ID", length = 200)
    public String getBillBankDealId() {
        return billBankDealId;
    }

    public void setBillBankDealId(String billBankDealId) {
        this.billBankDealId = billBankDealId;
    }

    @Basic
    @Column(name = "RECEIPT_TIME",nullable = false)
    public Date getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }

    @Basic
    @Column(name = "CALL_BACK_TIME")
    public Date getCallBackTime() {
        return callBackTime;
    }

    public void setCallBackTime(Date callBackTime) {
        this.callBackTime = callBackTime;
    }

    @Basic
    @Column(name = "BANK_ACCOUNT", length = 100)
    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    /**
     * 等待结果
     */
    @Transient
    public Boolean isWait(){
        if(StringUtil.isEqual(state, STATE_WAIT)){
            return true;
        }
        return false;
    }

    /**
     * 支付成功
     */
    @Transient
    public void paySuccess(String transActionId){
        this.transActionId = transActionId;
        this.state = STATE_SUCCESS;
        this.callBackTime = DateUtils.getDate();
    }

    /**
     * 快钱支付成功
     */
    @Transient
    public void paySuccessByBill(String dealId, String bankDealId){
        this.billDealId = dealId;
        this.billBankDealId = bankDealId;
        this.state = STATE_SUCCESS;
        this.callBackTime = DateUtils.getDate();
    }

    /**
     * 支付失败
     */
    @Transient
    public void payFail(){
        this.state = STATE_FAIL;
        this.callBackTime = DateUtils.getDate();
    }

    @Transient
    public static Boolean payWay(String payType){
        if(!StringUtil.isEqual(payType, PaymentEntity.TYPE_ALI_PAY_APP)
                || !StringUtil.isEqual(payType, PaymentEntity.TYPE_WECHAT_PAY_APP)
                || !StringUtil.isEqual(payType, PaymentEntity.TYPE_BILL_PAY_WAP)){
            return true;
        }
        return false;
    }

}
