package com.maxrocky.vesta.application.DTO;

import java.util.Date;

/**
 * Created by liudongxin on 2016/4/13.
 * 会员家庭信息
 */
public class FamilyDTO {
    private String id;//家庭id
    private String memberId;//会员编号
    private String relationsWithMember;//会员关系
    private Date birthDate;//日期
    private String phoneNumber;//手机联系方式
    private String name;//姓名
    private String stateCode;//数据删除标识：0可用，1 停用

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getRelationsWithMember() {
        return relationsWithMember;
    }

    public void setRelationsWithMember(String relationsWithMember) {
        this.relationsWithMember = relationsWithMember;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}
