package com.maxrocky.vesta.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


//@Entity
//@Table(name = "View_App_BillDetailInfo" )
public class ViewAppBillDetailInfo {

    //session中的标记位
    public static final String pay_detail_flag = "pay_detail_flag";



    public static final String pay_bill_info_flag = "pay_bill_info_flag";
    //支付过期时间
    public static final long pay_post_time = 1000 * 60 * 30;

    public static final int call_back_status_success = 1000;

    //是否同意
    public static final int agress = 1;


    public static class payTypeDefine {
        /*快钱*/
        public static String type_QuicklyCash = "QuicklyCash";
        /*微信*/
        public static String type_MicroPay = "MicroPay";
        /*支付宝*/
        public static String type_AliPay = "AliPay";
        /*银联*/
        public static String type_UnionBunk = "UnionBunk";
    }

    @Column(name = "MDP_GUID")
    private String MDPGUID;

    @Id
    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "pay_start_date")
    private String payStartDate;

    @Column(name = "pay_end_date")
    private String payEndDate;

    @Column(name = "pay_money")
    private String payMoney;

    @Column(name = "ProjectID")
    private String projectId;

    @Column(name = "price")
    private String price ;

    @Column(name = "pay_type")
    private String payType;

    @Column(name = "hourse_id")
    private String hourseId;

    @Column(name = "pay_desc")
    private String payDesc;

    @Column(name = "MDP_BatchTime")
    private String MDPBatchTime;

    @Column(name = "MDP_OPERATIONTYPE")
    private String MDPOPERATIONTYPE;

    @Column(name = "MDP_RESULT")
    private String MDP_RESULT;


    @Column(name = "OwnerID")
    private String ownerId;





    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getMDPGUID() {
        return MDPGUID;
    }

    public void setMDPGUID(String MDPGUID) {
        this.MDPGUID = MDPGUID;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPayStartDate() {
        return payStartDate;
    }

    public void setPayStartDate(String payStartDate) {
        this.payStartDate = payStartDate;
    }

    public String getPayEndDate() {
        return payEndDate;
    }

    public void setPayEndDate(String payEndDate) {
        this.payEndDate = payEndDate;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getHourseId() {
        return hourseId;
    }

    public void setHourseId(String hourseId) {
        this.hourseId = hourseId;
    }

    public String getPayDesc() {
        return payDesc;
    }

    public void setPayDesc(String payDesc) {
        this.payDesc = payDesc;
    }

    public String getMDPBatchTime() {
        return MDPBatchTime;
    }

    public void setMDPBatchTime(String MDPBatchTime) {
        this.MDPBatchTime = MDPBatchTime;
    }

    public String getMDPOPERATIONTYPE() {
        return MDPOPERATIONTYPE;
    }

    public void setMDPOPERATIONTYPE(String MDPOPERATIONTYPE) {
        this.MDPOPERATIONTYPE = MDPOPERATIONTYPE;
    }

    public String getMDP_RESULT() {
        return MDP_RESULT;
    }

    public void setMDP_RESULT(String MDP_RESULT) {
        this.MDP_RESULT = MDP_RESULT;
    }
}
