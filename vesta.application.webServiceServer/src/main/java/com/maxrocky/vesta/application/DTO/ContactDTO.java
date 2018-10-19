package com.maxrocky.vesta.application.DTO;

/**
 * Created by liudongxin on 2016/4/13.
 * 通讯信息
 */
public class ContactDTO {
    //private String memberId;//会员编号
    private String mailbox;//电子邮箱
    private String weiboid;//微博ID
    private String weixinid;//微信号
    private String qqid;//QQ
    private String CommunicationAddress;//通讯地址
    private String AlternateContactinfor1;//备用联系方式1
    private String AlternateContactinfor2;//备用联系方式2
    private String AlternateContactinfor3;//备用联系方式3
    private String AlternateContactinfor4;//备用联系方式4
    private String AlternateContactinfor5;//备用联系方式5
    private String PhoneNumber;//手机号码
    private String stateCode;//数据删除标识

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getWeiboid() {
        return weiboid;
    }

    public void setWeiboid(String weiboid) {
        this.weiboid = weiboid;
    }

    public String getWeixinid() {
        return weixinid;
    }

    public void setWeixinid(String weixinid) {
        this.weixinid = weixinid;
    }

    public String getQqid() {
        return qqid;
    }

    public void setQqid(String qqid) {
        this.qqid = qqid;
    }

    public String getCommunicationAddress() {
        return CommunicationAddress;
    }

    public void setCommunicationAddress(String communicationAddress) {
        CommunicationAddress = communicationAddress;
    }

    public String getAlternateContactinfor1() {
        return AlternateContactinfor1;
    }

    public void setAlternateContactinfor1(String alternateContactinfor1) {
        AlternateContactinfor1 = alternateContactinfor1;
    }

    public String getAlternateContactinfor2() {
        return AlternateContactinfor2;
    }

    public void setAlternateContactinfor2(String alternateContactinfor2) {
        AlternateContactinfor2 = alternateContactinfor2;
    }

    public String getAlternateContactinfor3() {
        return AlternateContactinfor3;
    }

    public void setAlternateContactinfor3(String alternateContactinfor3) {
        AlternateContactinfor3 = alternateContactinfor3;
    }

    public String getAlternateContactinfor4() {
        return AlternateContactinfor4;
    }

    public void setAlternateContactinfor4(String alternateContactinfor4) {
        AlternateContactinfor4 = alternateContactinfor4;
    }

    public String getAlternateContactinfor5() {
        return AlternateContactinfor5;
    }

    public void setAlternateContactinfor5(String alternateContactinfor5) {
        AlternateContactinfor5 = alternateContactinfor5;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}