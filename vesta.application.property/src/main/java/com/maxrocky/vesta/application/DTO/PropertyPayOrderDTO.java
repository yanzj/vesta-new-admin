package com.maxrocky.vesta.application.DTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 物业缴费订单信息DTO
 * Created by WeiYangDong on 2016/10/9.
 */
public class PropertyPayOrderDTO {

    private String menuId;          //菜单ID
    /* 缴费单检索条件 */
    private String scopeId;         //区域ID
    private String projectCode;     //项目名称
    private String address;         //房间地址
    private String cstName;         //业主
    private String invoiceState;    //票据状态(0:未开票,1:已开票)
    private String payOrderState;   //单据状态
    /* ----------- */
    /* 新增查询字段(用户权限范围)_2016-11-18_Wyd */
    private List<Map<String,Object>> roleScopeList;
    /* ------------------------------------- */
    private String payOrderIdDto;               //缴费订单ID

    private double principalReceivableDto;    //本金应收

    private String roomId;                  //房产Id

    private String payOrderListState;       //缴费单列表状态(NOPAY/HISTORY)

    private String paymentId;           //支付订单ID

    private String operationType;       //操作状态(0:录入发票,1:详情)

    private String repYears;            //订单所属账期

    private String payDate;             //缴费时间
    /* 生成支付订单所需 */
    private List<String> payOrderIdDtoList;     //缴费订单ID集合
    private BigDecimal totalFee;                 //支付金额
    private String paymentType;                 //支付方式
    private String createBy;                    //创建人
    /* ------------- */
    /* 票据相关信息 */
    private String invoiceId;       //票据Id
    private String invoiceType;     //票据类型(1:普通发票,2:增值专票)
    private String invoiceMail;     //发票递取方式
    private String expressAddress;  //快递地址
    private String invoiceHeader;   //发票抬头
    private String invoiceNum;      //发票号码
    private String realName;        //真实姓名
    /* ---------- */

    /* 生成支付订单所需参数补充 WeiYangDong 2016-11-22 */
    private String StartDate;       //所属账期检索开始日期
    private String EndDate;         //所属账期检索结束日期
    private String IpItemID;        //收费类目ID
    private String IpItemName;      //收费类目名称
    private String isYearOrMonth;  //按年缴费或按月缴费(1:按年缴费,2:按月缴费)
    private String PaymentState;    //缴费状态标示(已交清0/未交清1)
    private String roomNum;         //房产编码
    /* ------------------------------------------ */


    public String getPayOrderIdDto() {
        return payOrderIdDto;
    }

    public void setPayOrderIdDto(String payOrderIdDto) {
        this.payOrderIdDto = payOrderIdDto;
    }

    public double getPrincipalReceivableDto() {
        return principalReceivableDto;
    }

    public void setPrincipalReceivableDto(double principalReceivableDto) {
        this.principalReceivableDto = principalReceivableDto;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public List<String> getPayOrderIdDtoList() {
        return payOrderIdDtoList;
    }

    public void setPayOrderIdDtoList(List<String> payOrderIdDtoList) {
        this.payOrderIdDtoList = payOrderIdDtoList;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public String getPayOrderListState() {
        return payOrderListState;
    }

    public void setPayOrderListState(String payOrderListState) {
        this.payOrderListState = payOrderListState;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceHeader() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(String invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public List<Map<String, Object>> getRoleScopeList() {
        return roleScopeList;
    }

    public void setRoleScopeList(List<Map<String, Object>> roleScopeList) {
        this.roleScopeList = roleScopeList;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCstName() {
        return cstName;
    }

    public void setCstName(String cstName) {
        this.cstName = cstName;
    }

    public String getInvoiceState() {
        return invoiceState;
    }

    public void setInvoiceState(String invoiceState) {
        this.invoiceState = invoiceState;
    }

    public String getPayOrderState() {
        return payOrderState;
    }

    public void setPayOrderState(String payOrderState) {
        this.payOrderState = payOrderState;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getIpItemID() {
        return IpItemID;
    }

    public void setIpItemID(String ipItemID) {
        IpItemID = ipItemID;
    }

    public String getIsYearOrMonth() {
        return isYearOrMonth;
    }

    public void setIsYearOrMonth(String isYearOrMonth) {
        this.isYearOrMonth = isYearOrMonth;
    }

    public String getPaymentState() {
        return PaymentState;
    }

    public void setPaymentState(String paymentState) {
        PaymentState = paymentState;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getInvoiceMail() {
        return invoiceMail;
    }

    public void setInvoiceMail(String invoiceMail) {
        this.invoiceMail = invoiceMail;
    }

    public String getExpressAddress() {
        return expressAddress;
    }

    public void setExpressAddress(String expressAddress) {
        this.expressAddress = expressAddress;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getRepYears() {
        return repYears;
    }

    public void setRepYears(String repYears) {
        this.repYears = repYears;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getIpItemName() {
        return IpItemName;
    }

    public void setIpItemName(String ipItemName) {
        IpItemName = ipItemName;
    }
}
