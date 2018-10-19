package com.maxrocky.vesta.application.admin.dto;

import java.util.List;

/**
 * Created by liuwei on 2016/1/18.
 */
public class ParamsVo {

    private String RECEIPTID; //用于冲抵的收款记录ID，新增收款时为NULL
    private String DOCUMENTAMT; //本次收款总额
    private String DOCUMENTDATE; //收款日期
    private String PAYMETHOD; //付款方式，例如现金收款传值：CASHPAYMENT
    private String OWNERID; //业主ID
    private String CREATEUSERID; //数据创建平台ID（物管系统注册用户ID）
    private String BANKACCOUNTNO;
    private String COMPANYID;
    private String REMARK;
    private String PROJECTID; //管理处编号（主键）
    private String COMPANYNAME; //公司名称
    private String BLOCKNO ; //楼栋号
    private String UNITNO ;// 房间号
    private String OWNERNAME ;//业主名称
    private List<LG_RECEIPTDETAILVo> LG_RECEIPTDETAIL ;

    private String PayByWho ; //缴款方
    private String AppOrderNum ; //订单号
    private String ThirdOrderNum; // 支付流水号


    public String getPayByWho() {
        return PayByWho;
    }

    public ParamsVo setPayByWho(String payByWho) {
        PayByWho = payByWho;
        return this;
    }

    public String getAppOrderNum() {
        return AppOrderNum;
    }

    public ParamsVo setAppOrderNum(String appOrderNum) {
        AppOrderNum = appOrderNum;
        return this;
    }

    public String getThirdOrderNum() {
        return ThirdOrderNum;
    }

    public ParamsVo setThirdOrderNum(String thirdOrderNum) {
        ThirdOrderNum = thirdOrderNum;
        return this;
    }

    public String getCOMPANYNAME() {
        return COMPANYNAME;
    }

    public ParamsVo setCOMPANYNAME(String COMPANYNAME) {
        this.COMPANYNAME = COMPANYNAME;
        return this;
    }

    public String getBLOCKNO() {
        return BLOCKNO;
    }

    public ParamsVo setBLOCKNO(String BLOCKNO) {
        this.BLOCKNO = BLOCKNO;
        return this;
    }

    public String getUNITNO() {
        return UNITNO;
    }

    public ParamsVo setUNITNO(String UNITNO) {
        this.UNITNO = UNITNO;
        return this;
    }

    public String getOWNERNAME() {
        return OWNERNAME;
    }

    public ParamsVo setOWNERNAME(String OWNERNAME) {
        this.OWNERNAME = OWNERNAME;
        return this;
    }

    public String getPROJECTID() {
        return PROJECTID;
    }

    public void setPROJECTID(String PROJECTID) {
        this.PROJECTID = PROJECTID;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getCOMPANYID() {
        return COMPANYID;
    }

    public void setCOMPANYID(String COMPANYID) {
        this.COMPANYID = COMPANYID;
    }

    public String getBANKACCOUNTNO() {
        return BANKACCOUNTNO;
    }

    public void setBANKACCOUNTNO(String BANKACCOUNTNO) {
        this.BANKACCOUNTNO = BANKACCOUNTNO;
    }


    public String getRECEIPTID() {
        return RECEIPTID;
    }

    public void setRECEIPTID(String RECEIPTID) {
        this.RECEIPTID = RECEIPTID;
    }

    public String getDOCUMENTAMT() {
        return DOCUMENTAMT;
    }

    public void setDOCUMENTAMT(String DOCUMENTAMT) {
        this.DOCUMENTAMT = DOCUMENTAMT;
    }

    public String getDOCUMENTDATE() {
        return DOCUMENTDATE;
    }

    public void setDOCUMENTDATE(String DOCUMENTDATE) {
        this.DOCUMENTDATE = DOCUMENTDATE;
    }

    public String getPAYMETHOD() {
        return PAYMETHOD;
    }

    public void setPAYMETHOD(String PAYMETHOD) {
        this.PAYMETHOD = PAYMETHOD;
    }


    public String getOWNERID() {
        return OWNERID;
    }

    public void setOWNERID(String OWNERID) {
        this.OWNERID = OWNERID;
    }

    public String getCREATEUSERID() {
        return CREATEUSERID;
    }

    public void setCREATEUSERID(String CREATEUSERID) {
        this.CREATEUSERID = CREATEUSERID;
    }


    public List<LG_RECEIPTDETAILVo> getLG_RECEIPTDETAIL() {
        return LG_RECEIPTDETAIL;
    }

    public void setLG_RECEIPTDETAIL(List<LG_RECEIPTDETAILVo> LG_RECEIPTDETAIL) {
        this.LG_RECEIPTDETAIL = LG_RECEIPTDETAIL;
    }
}
