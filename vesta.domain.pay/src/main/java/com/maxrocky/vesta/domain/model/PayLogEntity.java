package com.maxrocky.vesta.domain.model;

import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tom on 2016/1/27 16:06.
 * Describe:支付日志实体类
 */
@Entity
@Table(name = "pay_payLog")
public class PayLogEntity {

    /* 日志类型 */
    public final static String TYPE_REQUEST = "REQUEST";//接到的请求
    public final static String TYPE_REQUEST_ALI = "ALI_PAY";//支付宝支付请求参数
    public final static String TYPE_REQUEST_BILL = "BILL_PAY";//快钱支付请求参数
    public final static String TYPE_REQUEST_WECHAT_UNIFIEDORDER = "WECHAT_ORDER";//微信请求下单参数
    public final static String TYPE_RESPONSE_WECHAT_UNIFIEDORDER = "WECHAT_ORDER_RES";//微信请求下单返回
    public final static String TYPE_REQUEST_WECHAT_PAY = "WECHAT_PAY";//微信支付请求参数
    public final static String TYPE_WECHAT_CALLBACK = "WECHAT_CALLBACK";//微信支付回调
    public final static String TYPE_BILL_CALLBACK = "BILL_CALLBACK";//快钱支付回调
    public final static String TYPE_ALI_CALLBACK = "ALI_CALLBACK";//支付宝支付回调

    private String id;//Id
    private String paymentId;//支付Id
    private String content;//内容
    private String type;//类型
    private Date createOn;//创建时间

    public PayLogEntity(){}

    public PayLogEntity(String paymentId, String content, String type){
        createBasic();
        this.paymentId = paymentId;
        this.content = content;
        this.type = type;
    }

    @Id
    @Column(name = "ID",nullable = false, length = 100)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PAYMENT_ID",nullable = false, length = 100)
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @Basic
    @Column(name = "CONTENT",nullable = false, length = 10000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    @Column(name = "CREATE_ON",nullable = false)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    /**
     * 创建基础数据
     */
    @Transient
    public void createBasic(){
        this.id = IdGen.uuid();
        this.createOn = DateUtils.getDate();
    }

}
