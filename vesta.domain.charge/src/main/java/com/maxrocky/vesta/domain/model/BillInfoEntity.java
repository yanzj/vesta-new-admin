package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.sql.Timestamp;

/***
 * 本地保存的订单信息信息
 */
//@Entity
//@Table(name = "bill_info")
public class BillInfoEntity {

    //发票状态
    public static class BillInfoProcessStatus{
        //已处理
        public static final String proccess_did = "proccess_did";
        //未处理
        public static final String proccess_not = "proccess_not";
    }

    @Id
    @Column
    private String id;
    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "bill_info")
    private String billInfo;  //发票信息

    @Column(name = "pay_type")
    private String payType;//付款方式

    @Column(name = "send_way")
    private String sendWay;//配送方式

    @Column(name = "create_time")
    private String createTime;//生成时间

    @Column(name = "paying_start_time")
    private String payingStartTime;//支付开始时间

    @Column(name = "pay_result")
    private String payResult; //支付结果

    @Column(name = "call_back_time")
    private String callBackTime; //支付回调时间

    @Column(name = "payer_type")
    private String payerType;//支付人类型

    @Column(name = "payer_phone")
    private String payerPhone; //支付人联系方式

    @Column(name = "send_param_str")
    private String sendParamStr;

    @Column(name = "pay_user_id")
    private String payerUserId;

    @Column(name = "pay_user_name")
    private String payerUserName;

    @Column(name = "pay_user_type")
    private String payUserType; //缴费人的角色类型，用来区分是待缴还是业主缴费

    @Column(name = "project_id")
    private String projectId; //支付人所在项目id 现在是存储的是业务系统的projectId

    @Column(name = "company_name")
    private String companyName ; //公司名称

    @Column(name = "block_no")
    private String blockNo; //楼栋号

    @Column(name = "unit_no")
    private String unitNo; //房间号

    @Column(name = "owner_name")
    private String ownerName ; //业主名称

    @Column(name = "bill_item_id")
    private String billitemId ;//费项编号

    @Column(name = "bill_item_name")
    private String billitemName ;//费项名称

    @Column(name = "pay_by_who")
    private String payByWho; //代缴时需要传缴费人名字

    @Column(name = "app_order_no")
    private String appOrderNo; //系统生成的订单号

    @Column(name = "third_order_no")
    private String thirdOrderNo ; //支付流水号 ，微信端/支付宝端生成


    @Column(name = "bank_account_no")
    private String bankAcountNo;  //收款人银行账号

    //***********************用于缴费历史查询的冗余字段
    @Column(name = "pay_start_date")
    private Timestamp payStartDate;
    @Column(name = "pay_end_date")
    private Timestamp payEndDate;
    @Column(name = "pay_money")
    private String payMoney;
    @Column(name = "house_id")
    private String houseId;
    @Column(name = "payment_type_name")
    private String paymentTypeName;

    //**********************发票的处理信息
    @Column(name = "proccess_status")
    private String processStatus; //处理状态
    @Column(name = "process_user_name")
    private String processUserName;//处理人
    @Column(name = "process_user_id")
    private String processUserId;//处理人id
    @Column(name = "process_time")
    private String processTime;//处理时间



    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public BillInfoEntity setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
        return this;
    }

    @Transient
    public String getProcessStatusDes(){
        if(BillInfoProcessStatus.proccess_did.equals(this.getProcessStatus())){

            return "已处理";
        }else{
            return "未处理";
        }
    }

    public String getPayerPhone() {
        return payerPhone;
    }

    public BillInfoEntity setPayerPhone(String payerPhone) {
        this.payerPhone = payerPhone;
        return this;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public BillInfoEntity setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
        return this;
    }

    public String getProcessUserName() {
        return processUserName;
    }

    public BillInfoEntity setProcessUserName(String processUserName) {
        this.processUserName = processUserName;
        return this;
    }

    public String getProcessUserId() {
        return processUserId;
    }

    public BillInfoEntity setProcessUserId(String processUserId) {
        this.processUserId = processUserId;
        return this;
    }

    public String getProcessTime() {
        return processTime;
    }

    public BillInfoEntity setProcessTime(String processTime) {
        this.processTime = processTime;
        return this;
    }

    public Timestamp getPayStartDate() {
        return payStartDate;
    }

    public BillInfoEntity setPayStartDate(Timestamp payStartDate) {
        this.payStartDate = payStartDate;
        return this;
    }

    public Timestamp getPayEndDate() {
        return payEndDate;
    }

    public BillInfoEntity setPayEndDate(Timestamp payEndDate) {
        this.payEndDate = payEndDate;
        return this;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public BillInfoEntity setPayMoney(String payMoney) {
        this.payMoney = payMoney;
        return this;
    }

    public String getHouseId() {
        return houseId;
    }

    public BillInfoEntity setHouseId(String houseId) {
        this.houseId = houseId;
        return this;
    }

    public String getBankAcountNo() {
        return bankAcountNo;
    }

    public BillInfoEntity setBankAcountNo(String bankAcountNo) {
        this.bankAcountNo = bankAcountNo;
        return this;
    }

    public String getAppOrderNo() {
        return appOrderNo;
    }

    public BillInfoEntity setAppOrderNo(String appOrderNo) {
        this.appOrderNo = appOrderNo;
        return this;
    }

    public String getThirdOrderNo() {
        return thirdOrderNo;
    }

    public BillInfoEntity setThirdOrderNo(String thirdOrderNo) {
        this.thirdOrderNo = thirdOrderNo;
        return this;
    }

    public String getPayByWho() {
        return payByWho;
    }

    public BillInfoEntity setPayByWho(String payByWho) {
        this.payByWho = payByWho;
        return this;
    }

    public String getPayUserType() {
        return payUserType;
    }

    public BillInfoEntity setPayUserType(String payUserType) {
        this.payUserType = payUserType;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public BillInfoEntity setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getBlockNo() {
        return blockNo;
    }

    public BillInfoEntity setBlockNo(String blockNo) {
        this.blockNo = blockNo;
        return this;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public BillInfoEntity setUnitNo(String unitNo) {
        this.unitNo = unitNo;
        return this;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public BillInfoEntity setOwnerName(String ownerName) {
        this.ownerName = ownerName;
        return this;
    }

    public String getBillitemId() {
        return billitemId;
    }

    public BillInfoEntity setBillitemId(String billitemId) {
        this.billitemId = billitemId;
        return this;
    }

    public String getBillitemName() {
        return billitemName;
    }

    public BillInfoEntity setBillitemName(String billitemName) {
        this.billitemName = billitemName;
        return this;
    }

    public String getProjectId() {
        return projectId;
    }

    public BillInfoEntity setProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public String getPayerUserId() {
        return payerUserId;
    }

    public BillInfoEntity setPayerUserId(String payerUserId) {
        this.payerUserId = payerUserId;
        return this;
    }

    public String getPayerUserName() {
        return payerUserName;
    }

    public BillInfoEntity setPayerUserName(String payerUserName) {
        this.payerUserName = payerUserName;
        return this;
    }

    public String getPayingStartTime() {
        return payingStartTime;
    }

    public BillInfoEntity setPayingStartTime(String payingStartTime) {
        this.payingStartTime = payingStartTime;
        return this;
    }

    public String getPayType() {
        return payType;
    }

    public BillInfoEntity setPayType(String payType) {
        this.payType = payType;
        return this;
    }

    public String getSendWay() {
        return sendWay;
    }

    public BillInfoEntity setSendWay(String sendWay) {
        this.sendWay = sendWay;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public BillInfoEntity setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getPayResult() {
        return payResult;
    }

    public BillInfoEntity setPayResult(String payResult) {
        this.payResult = payResult;
        return this;
    }

    public String getCallBackTime() {
        return callBackTime;
    }

    public BillInfoEntity setCallBackTime(String callBackTime) {
        this.callBackTime = callBackTime;
        return this;
    }

    public String getPayerType() {
        return payerType;
    }

    public BillInfoEntity setPayerType(String payerType) {
        this.payerType = payerType;
        return this;
    }

    public String getSendParamStr() {
        return sendParamStr;
    }

    public BillInfoEntity setSendParamStr(String sendParamStr) {
        this.sendParamStr = sendParamStr;
        return this;
    }

    public String getId() {
        return id;
    }

    public BillInfoEntity setId(String id) {
        this.id = id;
        return this;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public BillInfoEntity setPaymentId(String paymentId) {
        this.paymentId = paymentId;
        return this;
    }

    public String getBillInfo() {
        return billInfo;
    }

    public BillInfoEntity setBillInfo(String billInfo) {
        this.billInfo = billInfo;
        return this;
    }
}
