package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 物业缴费支付日志表
 * Created by WeiYangDong on 2016/10/9.
 */
@Entity
@Table(name = "property_payment")
public class PropertyPaymentEntity {
    /* 支付类型 */
    public final static String TYPE_WECHAT_PAY_APP = "WECHAT_APP";  //微信APP支付
//    public final static String TYPE_ALI_PAY_APP = "ALI_APP";        //支付宝APP支付
//    public final static String TYPE_BILL_PAY_WAP = "POS";           //POS机刷卡

    /* 支付状态:未支付,处理中,已支付,已开票 */
    public final static String STATE_NO_PAY = "NOPAY";//未支付
    public final static String STATE_HANDLE = "HANDLE";//处理中
    public final static String STATE_SUC_PAY = "SUCPAY";//已支付

    private String paymentId;      //支付日志Id
    private BigDecimal totalFee;   //支付金额
    private String transActionId;  //微信支付订单号
    private String paymentType;    //支付方式(类型)
    private String paymentState;   //支付状态

    private Date createDate;        //创建时间
    private String createBy;        //创建人
    private String modifyBy;        //修改人
    private Date modifyOn;          //修改时间

    @Id
    @Column(name = "payment_id",nullable = false, length = 100)
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @Basic
    @Column(name = "totalFee", precision = 18, scale = 2)
    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    @Basic
    @Column(name = "trans_action_id", length = 100)
    public String getTransActionId() {
        return transActionId;
    }

    public void setTransActionId(String transActionId) {
        this.transActionId = transActionId;
    }

    @Basic
    @Column(name = "payment_type", length = 100)
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Basic
    @Column(name = "payment_state", length = 100)
    public String getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    @Basic
    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "create_by", length = 100)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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
