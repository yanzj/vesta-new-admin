package com.maxrocky.vesta.domain.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.util.Date;

/**
 * Created by liudongxin on 2016/4/13.
 */
public class HouseFamilyCRMEntity {
    private String Id;//家庭id
    private String memberId;//会员编号
    private String relationsWithMember;//会员关系
    private Date birthDate;//日期
    private String phoneNumber;//手机联系方式
    private String name;//姓名
    private String state;//数据删除标识：0可用，1 停用

    @javax.persistence.Id
    @Column(name = "ID",nullable = false, length = 50)
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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
    @Column(name = "RELATIONS_MEMBER",nullable = true, length = 50)
    public String getRelationsWithMember() {
        return relationsWithMember;
    }

    public void setRelationsWithMember(String relationsWithMember) {
        this.relationsWithMember = relationsWithMember;
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
    @Column(name = "PHONE",nullable = true, length = 20)
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
    @Column(name = "STATE",nullable = true, length = 10)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
