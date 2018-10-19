package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by dl on 2016/4/26.
 */
@Entity
@Table(name = "grade_crm")
public class GradeCRMEntity {
    private String memberId;//会员编号
    private String memberLevel;//会员等级
    private String formats;//所属业态


    @Id
    @Column(name = "MEMBER_ID",nullable = true,length = 50)
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Basic
    @Column(name = "MEMBER_LEVEL",nullable = true,length = 50)
    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    @Basic
    @Column(name = "FORMATS",nullable = true,length = 50)
    public String getFormats() {
        return formats;
    }

    public void setFormats(String formats) {
        this.formats = formats;
    }
}
