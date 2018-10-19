package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dl on 2016/4/26.
 * 会员家庭信息
 */
@Entity
@Table(name = "family_crm")
public class FamilyCRMEntity {
    private String id;
    private String memberId;//会员编号
    private Date birthDate;//出生日期
    private String membership;//与会员关系
    private String phoneNumber;//手机联系方式
    private String name;//名称
    private String associateMemberId;//关联会员ID
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
    @Column(name = "MEMBER_ID",nullable = true,length = 50)
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }


    @Basic
    @Column(name = "BIRTH_DATE",nullable = true)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @Column(name = "MEMBERSHIP",nullable = true,length = 50)
    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    @Basic
    @Column(name = "PHONE_NUMBER",nullable = true, length = 15)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "NAME",nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Basic
    @Column(name = "ASS0CIATEMEMBER_ID",nullable = true,length = 50)
    public String getAssociateMemberId() {
        return associateMemberId;
    }

    public void setAssociateMemberId(String associateMemberId) {
        this.associateMemberId = associateMemberId;
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
