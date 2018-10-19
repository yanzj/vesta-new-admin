package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by JillChen on 2016/1/4.
 */

/**
 * 角色表
 */
@Entity
@Table(name = "role_roleset")
public class RoleRolesetEntity {
    private String setId;
    private String roledesc;        //角色描述
    /* 新增字段:角色备注_2016-08-04_Wyd*/
    private String remarks;         //角色备注
    /* ============================ */
    private Date makeDate;          //角色创建日期
    private Time makeTime;          //角色创建时间
    private Date modifyDate;        //角色修改日期
    private Time modifyTime;        //角色修改时间
    private String operator;        //角色修改人
    private String setState;        //是否有效      0-无效，1-有效
    private String setType;         //角色类型      1--金茂会员   2--金茂质检  3--全部
    private String companyId;       //公司Id
    private String isallot ;        //是否允许被分配   0-不允许，1-允许

    @Id
    @Column(name = "SetId",length = 50)
    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    @Basic
    @Column(name = "roledesc",length = 50)
    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    @Basic
    @Column(name = "remarks",length = 255)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "MakeDate",length = 50)
    public Date getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(Date makeDate) {
        this.makeDate = makeDate;
    }

    @Basic
    @Column(name = "MakeTime",length = 50)
    public Time getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(Time makeTime) {
        this.makeTime = makeTime;
    }

    @Basic
    @Column(name = "ModifyDate",length = 50)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Basic
    @Column(name = "ModifyTime",length = 50)
    public Time getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Time modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Basic
    @Column(name = "Operator",length = 50)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Basic
    @Column(name = "SetState",length = 50)
    public String getSetState() {
        return setState;
    }

    public void setSetState(String setState) {
        this.setState = setState;
    }

    @Basic
    @Column(name = "SetType",length = 50)
    public String getSetType() {
        return setType;
    }

    public void setSetType(String setType) {
        this.setType = setType;
    }

    @Basic
    @Column(name = "CompanyId",length = 50)
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "isallot",length = 50)
    public String getIsallot() {
        return isallot;
    }

    public void setIsallot(String isallot) {
        this.isallot = isallot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleRolesetEntity that = (RoleRolesetEntity) o;

        if (setId != null ? !setId.equals(that.setId) : that.setId != null) return false;
        if (roledesc != null ? !roledesc.equals(that.roledesc) : that.roledesc != null) return false;
        if (makeDate != null ? !makeDate.equals(that.makeDate) : that.makeDate != null) return false;
        if (makeTime != null ? !makeTime.equals(that.makeTime) : that.makeTime != null) return false;
        if (modifyDate != null ? !modifyDate.equals(that.modifyDate) : that.modifyDate != null) return false;
        if (modifyTime != null ? !modifyTime.equals(that.modifyTime) : that.modifyTime != null) return false;
        if (operator != null ? !operator.equals(that.operator) : that.operator != null) return false;
        if (setState != null ? !setState.equals(that.setState) : that.setState != null) return false;
        if (setType != null ? !setType.equals(that.setType) : that.setType != null) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = setId != null ? setId.hashCode() : 0;
        result = 31 * result + (roledesc != null ? roledesc.hashCode() : 0);
        result = 31 * result + (makeDate != null ? makeDate.hashCode() : 0);
        result = 31 * result + (makeTime != null ? makeTime.hashCode() : 0);
        result = 31 * result + (modifyDate != null ? modifyDate.hashCode() : 0);
        result = 31 * result + (modifyTime != null ? modifyTime.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (setState != null ? setState.hashCode() : 0);
        result = 31 * result + (setType != null ? setType.hashCode() : 0);
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        return result;
    }
}
