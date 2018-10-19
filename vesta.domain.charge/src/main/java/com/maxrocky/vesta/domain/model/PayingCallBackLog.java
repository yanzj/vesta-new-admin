package com.maxrocky.vesta.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by liuwei on 2016/2/24.
 */
//@Entity
//@Table(name = "paying_call_back_log")
public class PayingCallBackLog {


    //当前日志记录的执行阶段
    public static class log_process{
        //支付成功回调阶段的日志
        public static final String log_proccess_payment_callBack = "payement_callback";

        //回调物业系统时的日志
        public static final String log_proccess_payment_requestBussinessSystem = "requestBussinessSystem";

    }


    //日志结果类型
    public static class executeResult{
        //成功
        public static final String execute_success = "success";

        //失败
        public static final String execute_fail = "fail";
    }


    @Id
    @Column
    private String id;

    @Column(name = "call_back_time" )
    private String callBackTime;

    @Column(name = "call_paying_id")
    private String callPayingId;

    @Column(name = "status")
    private String status;

    @Column(name = "bill_info_id")
    private String billInfoId; //系统保存的支付详情 id

    @Column(name = "fail_reason")
    private String failReanson;

    @Column(name = "req_url")
    private String reqUrl;

    @Column(name = "req_body")
    private String reqBody;

    @Column(name = "md5_value")
    private String md5Value;


    @Column(name = "call_back_result")
    private String callBackResult;




    @Column(name = "app_order_no")
    private String appOrderNo;  //系统app生成的订单流水号

    @Column(name = "third_order_no")
    private String thirdOrderNo; //支付流水号  微信 以及 支付宝端生成

    @Column(name = "bank_account_no")
    private String bankAccountNo; //收款方银行账号信息

    @Column(name = "payment_receive_account")
    private String paymentReceiveAccount; //收款方支付宝/微信账号

    //类型区分

    @Column(name = "log_proeccess")
    private String logProccess; //记录日志发生的阶段


    @Column(name = "log_status")
    private String logStatus;


    public String getCallBackResult() {
        return callBackResult;
    }

    public PayingCallBackLog setCallBackResult(String callBackResult) {
        this.callBackResult = callBackResult;
        return this;
    }

    public String getLogStatus() {
        return logStatus;
    }

    public PayingCallBackLog setLogStatus(String logStatus) {
        this.logStatus = logStatus;
        return this;
    }

    public String getLogProccess() {
        return logProccess;
    }

    public PayingCallBackLog setLogProccess(String logProccess) {
        this.logProccess = logProccess;
        return this;
    }

    public String getAppOrderNo() {
        return appOrderNo;
    }

    public PayingCallBackLog setAppOrderNo(String appOrderNo) {
        this.appOrderNo = appOrderNo;
        return this;
    }

    public String getThirdOrderNo() {
        return thirdOrderNo;
    }

    public PayingCallBackLog setThirdOrderNo(String thirdOrderNo) {
        this.thirdOrderNo = thirdOrderNo;
        return this;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public PayingCallBackLog setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
        return this;
    }

    public String getPaymentReceiveAccount() {
        return paymentReceiveAccount;
    }

    public PayingCallBackLog setPaymentReceiveAccount(String paymentReceiveAccount) {
        this.paymentReceiveAccount = paymentReceiveAccount;
        return this;
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public PayingCallBackLog setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
        return this;
    }

    public String getReqBody() {
        return reqBody;
    }

    public PayingCallBackLog setReqBody(String reqBody) {
        this.reqBody = reqBody;
        return this;
    }

    public String getMd5Value() {
        return md5Value;
    }

    public PayingCallBackLog setMd5Value(String md5Value) {
        this.md5Value = md5Value;
        return this;
    }

    public String getBillInfoId() {
        return billInfoId;
    }

    public PayingCallBackLog setBillInfoId(String billInfoId) {
        this.billInfoId = billInfoId;
        return this;
    }

    public String getId() {
        return id;
    }

    public PayingCallBackLog setId(String id) {
        this.id = id;
        return this;
    }

    public String getCallBackTime() {
        return callBackTime;
    }

    public PayingCallBackLog setCallBackTime(String callBackTime) {
        this.callBackTime = callBackTime;
        return this;
    }

    public String getCallPayingId() {
        return callPayingId;
    }

    public PayingCallBackLog setCallPayingId(String callPayingId) {
        this.callPayingId = callPayingId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public PayingCallBackLog setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getFailReanson() {
        return failReanson;
    }

    public PayingCallBackLog setFailReanson(String failReanson) {
        this.failReanson = failReanson;
        return this;
    }
}
