package com.maxrocky.vesta.application.DTO.json;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.PaymentEntity;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;

import java.math.BigDecimal;

/**
 * Created by Tom on 2016/2/1 14:45.
 * Describe:PM0001支付输入参数实体类
 */
public class PayJsonDTO {

    private String paymentId;//支付单号，由支付系统生成
    private String payOrderId;//支付订单号
    private String totalFee;//支付金额
    private String subject;//支付标题
    private String body;//支付描述
    private String type;//支付类型
    private String domain;//支付域（暂时传递公司ID）
    private String extraParam; //附带参数
    private String bankAccount;//银行账号

    public String getExtraParam() {
        return extraParam;
    }

    public PayJsonDTO setExtraParam(String extraParam) {
        this.extraParam = extraParam;
        return this;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    /**
     * 基础验证
     */
    public ApiResult check(){
        if(StringUtil.isEmpty(payOrderId)){
            return new ErrorApiResult("tip_PM000001");
        }
        if(StringUtil.isEmpty(totalFee)){
            return new ErrorApiResult("tip_PM000002");
        }
        if(StringUtil.toBigDecimal(totalFee).compareTo(BigDecimal.ZERO) <= 0){
            return new ErrorApiResult("tip_PM000002");
        }
        if(StringUtil.isEmpty(type)){
            return new ErrorApiResult("tip_PM000003");
        }
        if(StringUtil.isEmpty(domain)){
            return new ErrorApiResult("tip_PM000004");
        }
        if(!PaymentEntity.payWay(type)){
            return new ErrorApiResult("tip_PM000005");
        }

        return new SuccessApiResult();
    }

    /**
     * to PaymentEntity
     */
    public PaymentEntity toPaymentEntity(){
        PaymentEntity paymentEntity =  new PaymentEntity();
        paymentEntity.setId(paymentId);
        paymentEntity.setPayOrderId(payOrderId);
        paymentEntity.setSubject(subject);
        paymentEntity.setBody(body);
        paymentEntity.setTotalFee(StringUtil.toBigDecimal(totalFee));
        paymentEntity.setState(PaymentEntity.STATE_WAIT);
        paymentEntity.setType(type);
        paymentEntity.setReceiptTime(DateUtils.getDate());
        paymentEntity.setBankAccount(bankAccount);
        return paymentEntity;
    }

}
