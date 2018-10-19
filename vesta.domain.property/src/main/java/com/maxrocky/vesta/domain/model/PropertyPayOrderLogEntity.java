package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 物业缴费单日志表(数据来源:思源系统缴费单同步接口1.1)
 * Created by WeiYangDong on 2016/11/16.
 */
@Entity
@Table(name = "property_pay_order_log")
public class PropertyPayOrderLogEntity {

    /* 缴费单状态:未支付,处理中,已支付 */
    public final static String STATE_NO_PAY = "NOPAY";//未支付
    public final static String STATE_HANDLE = "HANDLE";//处理中
    public final static String STATE_SUC_PAY = "SUCPAY";//已支付

    private String id;          //缴费单日志主键ID

    private String RevID;       //缴费单编码 from 思源
    private String SYResName;   //资源编码(思源房屋编码信息) from 思源
    private String CRMResName;  //房间编码(思源同步CRM的房屋编码信息) from 思源
    private String OrgID;       //项目编码 from 思源
    private String CstName;     //房屋业主(应缴费人姓名) from 思源
    private String IpItemName;  //收费类目 from 思源
    private String PendingLogo; //挂起标志(挂起状态无需支付) from 思源
    private String RepYears;    //所属账期 from 思源
    private BigDecimal PPlanBal;//预缴费金额 from 思源
    private BigDecimal PriRev;      //本金应收 from 思源
    private BigDecimal PriPaid;     //本金已收 from 思源
    private BigDecimal PriFailures; //本金欠收 from 思源
    private BigDecimal PriRevTolal; //本金已收合计

//    private String Payment;     //交款人 from 思源
//    private String Filldate;    //交款日期 from 思源
//    private String LockLogo;    //收款状态 from 思源
//    private String FNPaidCode;  //实收编码 from 思源
//    private BigDecimal LFRev;       //滞纳金 from 思源
//    private BigDecimal LFPaid;      //滞纳金已收 from 思源
//    private BigDecimal PriRelief;   //本金减免 from 思源
//    private BigDecimal LFRelief;    //滞纳金减免 from 思源
//    private String PRemarks;    //实收摘要 from 思源
//    private String Trading;     //收款方式 from 思源

    private String RevAbstract; //应收摘要 from 思源
    private String StaNmarks;   //收费标准 from 思源
    private String DateRecord;  //录入账期 from 思源
    private String RevPSDate;   //应收时间开始日期 from 思源
    private String RevPEDate;   //应收时间结束日期 from 思源

    private String paymentId;       //支付记录Id(对应支付日志记录)
    private String payOrderState;   //缴费单状态
//    private String invoiceId;       //票据信息Id(对应票据信息) -2016-11-23 该字段对应支付订单,不应对应缴费单

    private String createBy;    //创建人
    private Date createOn;      //创建时间
    private String modifyBy;    //修改人
    private Date modifyOn;      //修改时间

    @Id
    @Column(name = "id",nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Rev_ID", length = 100)
    public String getRevID() {
        return RevID;
    }

    public void setRevID(String revID) {
        RevID = revID;
    }

    @Basic
    @Column(name = "SYRes_Name", length = 100)
    public String getSYResName() {
        return SYResName;
    }

    public void setSYResName(String SYResName) {
        this.SYResName = SYResName;
    }

    @Basic
    @Column(name = "CRMRes_Name", length = 100)
    public String getCRMResName() {
        return CRMResName;
    }

    public void setCRMResName(String CRMResName) {
        this.CRMResName = CRMResName;
    }

    @Basic
    @Column(name = "Org_ID", length = 100)
    public String getOrgID() {
        return OrgID;
    }

    public void setOrgID(String orgID) {
        OrgID = orgID;
    }

    @Basic
    @Column(name = "Cst_Name", length = 100)
    public String getCstName() {
        return CstName;
    }

    public void setCstName(String cstName) {
        CstName = cstName;
    }

    @Basic
    @Column(name = "IpItem_Name", length = 100)
    public String getIpItemName() {
        return IpItemName;
    }

    public void setIpItemName(String ipItemName) {
        IpItemName = ipItemName;
    }

    @Basic
    @Column(name = "Pending_Logo", length = 50)
    public String getPendingLogo() {
        return PendingLogo;
    }

    public void setPendingLogo(String pendingLogo) {
        PendingLogo = pendingLogo;
    }

    @Basic
    @Column(name = "Rep_Years", length = 100)
    public String getRepYears() {
        return RepYears;
    }

    public void setRepYears(String repYears) {
        RepYears = repYears;
    }

    @Basic
    @Column(name = "PPlan_Bal")
    public BigDecimal getPPlanBal() {
        return PPlanBal;
    }

    public void setPPlanBal(BigDecimal PPlanBal) {
        this.PPlanBal = PPlanBal;
    }

    @Basic
    @Column(name = "Pri_Rev")
    public BigDecimal getPriRev() {
        return PriRev;
    }

    public void setPriRev(BigDecimal priRev) {
        PriRev = priRev;
    }

    @Basic
    @Column(name = "Pri_Paid")
    public BigDecimal getPriPaid() {
        return PriPaid;
    }

    public void setPriPaid(BigDecimal priPaid) {
        PriPaid = priPaid;
    }

    @Basic
    @Column(name = "Pri_Failures")
    public BigDecimal getPriFailures() {
        return PriFailures;
    }

    public void setPriFailures(BigDecimal priFailures) {
        PriFailures = priFailures;
    }

//    @Basic
//    @Column(name = "Lock_Logo", length = 50)
//    public String getLockLogo() {
//        return LockLogo;
//    }
//
//    public void setLockLogo(String lockLogo) {
//        LockLogo = lockLogo;
//    }
//
//    @Basic
//    @Column(name = "FNPaid_Code", length = 100)
//    public String getFNPaidCode() {
//        return FNPaidCode;
//    }
//
//    public void setFNPaidCode(String FNPaidCode) {
//        this.FNPaidCode = FNPaidCode;
//    }
//
//    @Basic
//    @Column(name = "LF_Rev")
//    public BigDecimal getLFRev() {
//        return LFRev;
//    }
//
//    public void setLFRev(BigDecimal LFRev) {
//        this.LFRev = LFRev;
//    }
//
//    @Basic
//    @Column(name = "LF_Paid")
//    public BigDecimal getLFPaid() {
//        return LFPaid;
//    }
//
//    public void setLFPaid(BigDecimal LFPaid) {
//        this.LFPaid = LFPaid;
//    }
//
//    @Basic
//    @Column(name = "Pri_Relief")
//    public BigDecimal getPriRelief() {
//        return PriRelief;
//    }
//
//    public void setPriRelief(BigDecimal priRelief) {
//        PriRelief = priRelief;
//    }
//
//    @Basic
//    @Column(name = "LF_Relief")
//    public BigDecimal getLFRelief() {
//        return LFRelief;
//    }
//
//    public void setLFRelief(BigDecimal LFRelief) {
//        this.LFRelief = LFRelief;
//    }
//
//    @Basic
//    @Column(name = "P_Remarks", length = 100)
//    public String getPRemarks() {
//        return PRemarks;
//    }
//
//    public void setPRemarks(String PRemarks) {
//        this.PRemarks = PRemarks;
//    }
//    @Basic
//    @Column(name = "Trading", length = 100)
//    public String getTrading() {
//        return Trading;
//    }
//
//    public void setTrading(String trading) {
//        Trading = trading;
//    }

    @Basic
    @Column(name = "Rev_Abstract", length = 100)
    public String getRevAbstract() {
        return RevAbstract;
    }

    public void setRevAbstract(String revAbstract) {
        RevAbstract = revAbstract;
    }

    @Basic
    @Column(name = "Sta_Nmarks", length = 100)
    public String getStaNmarks() {
        return StaNmarks;
    }

    public void setStaNmarks(String staNmarks) {
        StaNmarks = staNmarks;
    }

    @Basic
    @Column(name = "Date_Record", length = 100)
    public String getDateRecord() {
        return DateRecord;
    }

    public void setDateRecord(String dateRecord) {
        DateRecord = dateRecord;
    }

    @Basic
    @Column(name = "RevPS_Date", length = 100)
    public String getRevPSDate() {
        return RevPSDate;
    }

    public void setRevPSDate(String revPSDate) {
        RevPSDate = revPSDate;
    }

    @Basic
    @Column(name = "RevPE_Date", length = 100)
    public String getRevPEDate() {
        return RevPEDate;
    }

    public void setRevPEDate(String revPEDate) {
        RevPEDate = revPEDate;
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
    @Column(name = "payOrder_State", length = 50)
    public String getPayOrderState() {
        return payOrderState;
    }

    public void setPayOrderState(String payOrderState) {
        this.payOrderState = payOrderState;
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

    @Basic
    @Column(name = "Pri_Rev_Tolal")
    public BigDecimal getPriRevTolal() {
        return PriRevTolal;
    }

    public void setPriRevTolal(BigDecimal priRevTolal) {
        PriRevTolal = priRevTolal;
    }
}
