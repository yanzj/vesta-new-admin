package com.maxrocky.vesta.sms;// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2016/4/26 13:37:13
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Mo.java

import java.io.Serializable;

public class Mo
    implements Serializable
{

    public Mo()
    {
    }

    public Mo(String addSerial, String addSerialRev, String channelnumber, String mobileNumber, String sentTime, String smsContent)
    {
        this.addSerial = addSerial;
        this.addSerialRev = addSerialRev;
        this.channelnumber = channelnumber;
        this.mobileNumber = mobileNumber;
        this.sentTime = sentTime;
        this.smsContent = smsContent;
    }

    public String getAddSerial()
    {
        return addSerial;
    }

    public void setAddSerial(String addSerial)
    {
        this.addSerial = addSerial;
    }

    public String getAddSerialRev()
    {
        return addSerialRev;
    }

    public void setAddSerialRev(String addSerialRev)
    {
        this.addSerialRev = addSerialRev;
    }

    public String getChannelnumber()
    {
        return channelnumber;
    }

    public void setChannelnumber(String channelnumber)
    {
        this.channelnumber = channelnumber;
    }

    public String getMobileNumber()
    {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber)
    {
        this.mobileNumber = mobileNumber;
    }

    public String getSentTime()
    {
        return sentTime;
    }

    public void setSentTime(String sentTime)
    {
        this.sentTime = sentTime;
    }

    public String getSmsContent()
    {
        return smsContent;
    }

    public void setSmsContent(String smsContent)
    {
        this.smsContent = smsContent;
    }

    public String toString()
    {
        return (new StringBuilder(String.valueOf(addSerial))).append("|").append(addSerialRev).append("|").append(channelnumber).append("|").append(mobileNumber).append("|").append(sentTime).append("|").append(smsContent).toString();
    }

    private String addSerial;
    private String addSerialRev;
    private String channelnumber;
    private String mobileNumber;
    private String sentTime;
    private String smsContent;
}