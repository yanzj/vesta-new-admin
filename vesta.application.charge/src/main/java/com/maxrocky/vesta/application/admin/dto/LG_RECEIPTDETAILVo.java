package com.maxrocky.vesta.application.admin.dto;

/**
 * Created by liuwei on 2016/1/18.
 */
public class LG_RECEIPTDETAILVo {
    private String BILLDETAILID;
    private String DOCUMENTAMT;

    private String BILLITEMID;//费项编号
    private String BILLITEMNAME ;//费项名称

    public String getBILLITEMID() {
        return BILLITEMID;
    }

    public LG_RECEIPTDETAILVo setBILLITEMID(String BILLITEMID) {
        this.BILLITEMID = BILLITEMID;
        return this;
    }

    public String getBILLITEMNAME() {
        return BILLITEMNAME;
    }

    public LG_RECEIPTDETAILVo setBILLITEMNAME(String BILLITEMNAME) {
        this.BILLITEMNAME = BILLITEMNAME;
        return this;
    }

    public String getBILLDETAILID() {
        return BILLDETAILID;
    }

    public void setBILLDETAILID(String BILLDETAILID) {
        this.BILLDETAILID = BILLDETAILID;
    }

    public String getDOCUMENTAMT() {
        return DOCUMENTAMT;
    }

    public void setDOCUMENTAMT(String DOCUMENTAMT) {
        this.DOCUMENTAMT = DOCUMENTAMT;
    }
}
