package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/6/16.
 * 组织机构实体
 */
@Entity
@Table(name = "role_agency")
public class AgencyEntity {
    private String agencyId;           //主键
    private String agencyName;         //组织机构名
    private String abbreviationName;   //机构简称
    private String agencyDesc;         //机构描述 备注
    private String agencyType;         //机构类型 3总部  2公司 1部门 4责任单位 5监理
    private String parentId;           //上级ID
    private String isRoot;             //是否为根目录 0不是 1是
    private Date createTime;           //创建时间
    private Date modifyTime;           //修改时间
    private String agencyPath;         //路径
    private String status;             //状态 0删除 1正常
    private Integer orderNum;          //排序
    private String outEmploy;          //是否为外部合作单位  0不是 1是
    private String nature;             //工程阶段责任单位性质 1总包 2分包
    private Integer level;              //等级

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
    @Column(name = "IS_ROOT",length = 2)
    public String getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(String isRoot) {
        this.isRoot = isRoot;
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
    @Column(name = "PARENT_ID",length = 50)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "STATUS",length = 2)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "AGENCY_TYPE",length = 2)
    public String getAgencyType() {
        return agencyType;
    }

    public void setAgencyType(String agencyType) {
        this.agencyType = agencyType;
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
    @Column(name = "OUT_EMPLOY",length = 2)
    public String getOutEmploy() {
        return outEmploy;
    }

    public void setOutEmploy(String outEmploy) {
        this.outEmploy = outEmploy;
    }

    @Basic
    @Column(name = "NATURE",length = 2)
    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
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
    @Column(name = "ABBREVIATION_NAME",length = 32)
    public String getAbbreviationName() {
        return abbreviationName;
    }

    public void setAbbreviationName(String abbreviationName) {
        this.abbreviationName = abbreviationName;
    }
}
