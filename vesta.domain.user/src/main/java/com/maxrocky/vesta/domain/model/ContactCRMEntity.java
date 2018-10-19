package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by liudongxin on 2016/4/28.
 * 会员通讯信息
 */
@Entity
@Table(name = "contact_crm")
public class ContactCRMEntity {
    private String id;
    private String memberId;//会员编号
    private String EMail;//电子邮箱
    private String weiBo;//微博ID
    private String weiXin;//微信号
    private String qq;//QQ
    private String address;//通讯地址
    private String backUpPhoneOne;//备用联系方式1
    private String backUpPhoneTwo;//备用联系方式2
    private String backUpPhoneThree;//备用联系方式3
    private String backUpPhoneFour;//备用联系方式4
    private String backUpPhoneFive;//备用联系方式5
    private String phoneNumber;//手机号码
    private String stateCode;//删除标记 1:删除，0：正常


    @Id
    @Column(name = "ID",nullable = false, length = 32)
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MEMBER_ID",nullable = true, length = 50)
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Basic
    @Column(name = "E_MAIL",nullable = true, length = 30)
    public String getEMail() {
        return EMail;
    }

    public void setEMail(String EMail) {
        this.EMail = EMail;
    }

    @Basic
    @Column(name = "WEIBO",nullable = true, length = 50)
    public String getWeiBo() {
        return weiBo;
    }

    public void setWeiBo(String weiBo) {
        this.weiBo = weiBo;
    }

    @Basic
    @Column(name = "WEIXIN",nullable = true, length = 50)
    public String getWeiXin() {
        return weiXin;
    }

    public void setWeiXin(String weiXin) {
        this.weiXin = weiXin;
    }

    @Basic
    @Column(name = "QQ",nullable = true, length = 20)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "ADDRESS",nullable = true, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "BACKUP_PHONE_ONE",nullable = true, length = 20)
    public String getBackUpPhoneOne() {
        return backUpPhoneOne;
    }

    public void setBackUpPhoneOne(String backUpPhoneOne) {
        this.backUpPhoneOne = backUpPhoneOne;
    }

    @Basic
    @Column(name = "BACKUP_PHONE_TWO",nullable = true, length = 20)
    public String getBackUpPhoneTwo() {
        return backUpPhoneTwo;
    }

    public void setBackUpPhoneTwo(String backUpPhoneTwo) {
        this.backUpPhoneTwo = backUpPhoneTwo;
    }

    @Basic
    @Column(name = "BACKUP_PHONE_THREE",nullable = true, length = 20)
    public String getBackUpPhoneThree() {
        return backUpPhoneThree;
    }

    public void setBackUpPhoneThree(String backUpPhoneThree) {
        this.backUpPhoneThree = backUpPhoneThree;
    }

    @Basic
    @Column(name = "BACKUP_PHONE_FOUR",nullable = true, length = 20)
    public String getBackUpPhoneFour() {
        return backUpPhoneFour;
    }

    public void setBackUpPhoneFour(String backUpPhoneFour) {
        this.backUpPhoneFour = backUpPhoneFour;
    }

    @Basic
    @Column(name = "BACKUP_PHONE_FIVE",nullable = true, length = 20)
    public String getBackUpPhoneFive() {
        return backUpPhoneFive;
    }

    public void setBackUpPhoneFive(String backUpPhoneFive) {
        this.backUpPhoneFive = backUpPhoneFive;
    }

    @Basic
    @Column(name = "PHONE_NUMBER",nullable = true, length = 50)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;

    }

    @Basic
    @Column(name = "STATE_CODE",nullable = true,length = 2)
    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}
