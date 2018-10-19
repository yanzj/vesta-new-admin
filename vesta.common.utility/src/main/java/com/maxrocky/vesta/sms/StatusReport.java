package com.maxrocky.vesta.sms;// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2016/4/26 13:37:38
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   StatusReport.java

import java.io.Serializable;

public class StatusReport
    implements Serializable
{

    public StatusReport()
    {
    }

    public StatusReport(String errorCode, String memo, String mobile, String receiveDate, int reportStatus, long seqID,
                        String serviceCodeAdd, String submitDate)
    {
        this.errorCode = errorCode;
        this.memo = memo;
        this.mobile = mobile;
        this.receiveDate = receiveDate;
        this.reportStatus = reportStatus;
        this.seqID = seqID;
        this.serviceCodeAdd = serviceCodeAdd;
        this.submitDate = submitDate;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getMemo()
    {
        return memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getReceiveDate()
    {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate)
    {
        this.receiveDate = receiveDate;
    }

    public int getReportStatus()
    {
        return reportStatus;
    }

    public void setReportStatus(int reportStatus)
    {
        this.reportStatus = reportStatus;
    }

    public long getSeqID()
    {
        return seqID;
    }

    public void setSeqID(long seqID)
    {
        this.seqID = seqID;
    }

    public String getServiceCodeAdd()
    {
        return serviceCodeAdd;
    }

    public void setServiceCodeAdd(String serviceCodeAdd)
    {
        this.serviceCodeAdd = serviceCodeAdd;
    }

    public String getSubmitDate()
    {
        return submitDate;
    }

    public void setSubmitDate(String submitDate)
    {
        this.submitDate = submitDate;
    }

    private String errorCode;
    private String memo;
    private String mobile;
    private String receiveDate;
    private int reportStatus;
    private long seqID;
    private String serviceCodeAdd;
    private String submitDate;
}