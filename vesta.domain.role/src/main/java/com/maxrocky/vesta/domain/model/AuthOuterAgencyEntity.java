package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 外部合作单位组织机构表（新权限OA同步已取消组织机构概念  外部合作单位只能新建一张组织机构表）
 * Created by yuanyn on 2017/12/25.
 */
@Entity
@Table(name = "auth_outer_role_agency")
public class AuthOuterAgencyEntity {
    private String agencyId;           //主键 uuid
    private String agencyName;         //组织机构名
    private String parentId;           //上级ID
    private Date createTime;           //创建时间
    private String createBy;           //创建人
    private Date modifyTime;           //修改时间
    private String modifyBy;           //修改人
    private String agencyPath;         //路径
    private String status;             //状态 0删除 1正常
    private String agencyDesc;         //机构描述
    private Integer orderNum;          //排序
    private Integer level;              //等级
    private String outEmploy;          //是否为外部创建的组织机构  0不是 1是
    private String isTemporary;        //是否为临时组   1: 是  0 : 不是
    private String temporaryScope;     //临时组生效范围  安全  st   工程 es  客观  qc
    private String agencyType;         //机构类型  2分部 1部门 3临时组 和 外部合作单位

    @Basic
    @Column(name = "AGENCY_DESC",length = 200)
    public String getAgencyDesc() {
        return agencyDesc;
    }

    public void setAgencyDesc(String agencyDesc) {
        this.agencyDesc = agencyDesc;
    }

    @Id
    @Column(name = "AGENCY_ID",unique = true,nullable = false,length = 50)
    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    @Basic
    @Column(name = "AGENCY_NAME",length = 30)
    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    @Basic
    @Column(name = "AGENCY_PATH",length = 200)
    public String getAgencyPath() {
        return agencyPath;
    }

    public void setAgencyPath(String agencyPath) {
        this.agencyPath = agencyPath;
    }

    @Basic
    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "CREATE_BY",length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "MODIFY_TIME")
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Basic
    @Column(name = "MODIFY_BY",length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "PARENT_ID",length = 50)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "STATUS",length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "ORDER_NUMBER")
    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }


    @Basic
    @Column(name = "AGENCY_LEVEL",length = 32)
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "OUT_EMPLOY",length = 2)
    public String getOutEmploy() {
        return outEmploy;
    }

    public void setOutEmploy(String outEmploy) {
        this.outEmploy = outEmploy;
    }

    @Basic
    @Column(name = "IS_TEMPORARY",length = 2)
    public String getIsTemporary() {
        return isTemporary;
    }

    public void setIsTemporary(String isTemporary) {
        this.isTemporary = isTemporary;
    }

    @Basic
    @Column(name = "TEMPORARY_SCOPE",length = 10)
    public String getTemporaryScope() {
        return temporaryScope;
    }

    public void setTemporaryScope(String temporaryScope) {
        this.temporaryScope = temporaryScope;
    }

    @Basic
    @Column(name = "AGENCY_TYPE",length = 2)
    public String getAgencyType() {
        return agencyType;
    }

    public void setAgencyType(String agencyType) {
        this.agencyType = agencyType;
    }
}
