package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Magic on 2018/3/22.
 */
@Entity
@Table(name = "auth_role_agencyes")
public class AuthAgencyESEntity {
    private Long id; //自增长id
    private String agencyId;           //主键
    private String agencyName;         //组织机构名
    private String agencyType;         //机构类型 100000000：总部 100000001：区域 100000003：城市 100000002：项目
    private String parentId;           //上级ID
    private Date createTime;           //创建+第一次同步时间
    private Date modifyTime;           //修改时间
    private String agencyPath;         //路径
    private String status;             //状态 0删除 1正常
    private String businesssource;    //业态
    //以上信息均有crm同步
    private String agencyDesc;         //机构描述 备注
    private String isRoot;             //是否为根目录 0不是 1是
    private Integer orderNum;          //排序
    private String outEmploy;          //是否为外部合作单位  0不是 1是
    private String nature;             //工程阶段责任单位性质 1总包 2分包
    private Integer level;              //等级
    private Integer sort;               //排序

    public AuthAgencyESEntity() {
    }

    public AuthAgencyESEntity(String agencyId, String agencyName, String parentId, Integer level) {
        this.agencyId = agencyId;
        this.agencyName = agencyName;
        this.parentId = parentId;
        this.level = level;
    }

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "AGENCY_DESC",length = 200)
    public String getAgencyDesc() {
        return agencyDesc;
    }

    public void setAgencyDesc(String agencyDesc) {
        this.agencyDesc = agencyDesc;
    }

    @Basic
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
    @Column(name = "IS_ROOT",length = 10)
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
    @Column(name = "STATUS",length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "AGENCY_TYPE",length = 10)
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
    @Column(name = "OUT_EMPLOY",length = 10)
    public String getOutEmploy() {
        return outEmploy;
    }

    public void setOutEmploy(String outEmploy) {
        this.outEmploy = outEmploy;
    }

    @Basic
    @Column(name = "NATURE",length = 10)
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
    @Column(name = "BUSINESSSOURCE",length = 100)
    public String getBusinesssource() {
        return businesssource;
    }

    public void setBusinesssource(String businesssource) {
        this.businesssource = businesssource;
    }

    @Basic
    @Column(name = "SORT",length = 32)
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthAgencyESEntity that = (AuthAgencyESEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (agencyId != null ? !agencyId.equals(that.agencyId) : that.agencyId != null) return false;
        if (agencyName != null ? !agencyName.equals(that.agencyName) : that.agencyName != null) return false;
        if (agencyType != null ? !agencyType.equals(that.agencyType) : that.agencyType != null) return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (modifyTime != null ? !modifyTime.equals(that.modifyTime) : that.modifyTime != null) return false;
        if (agencyPath != null ? !agencyPath.equals(that.agencyPath) : that.agencyPath != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (businesssource != null ? !businesssource.equals(that.businesssource) : that.businesssource != null)
            return false;
        if (agencyDesc != null ? !agencyDesc.equals(that.agencyDesc) : that.agencyDesc != null) return false;
        if (isRoot != null ? !isRoot.equals(that.isRoot) : that.isRoot != null) return false;
        if (orderNum != null ? !orderNum.equals(that.orderNum) : that.orderNum != null) return false;
        if (outEmploy != null ? !outEmploy.equals(that.outEmploy) : that.outEmploy != null) return false;
        if (nature != null ? !nature.equals(that.nature) : that.nature != null) return false;
        return level != null ? level.equals(that.level) : that.level == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (agencyId != null ? agencyId.hashCode() : 0);
        result = 31 * result + (agencyName != null ? agencyName.hashCode() : 0);
        result = 31 * result + (agencyType != null ? agencyType.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (modifyTime != null ? modifyTime.hashCode() : 0);
        result = 31 * result + (agencyPath != null ? agencyPath.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (businesssource != null ? businesssource.hashCode() : 0);
        result = 31 * result + (agencyDesc != null ? agencyDesc.hashCode() : 0);
        result = 31 * result + (isRoot != null ? isRoot.hashCode() : 0);
        result = 31 * result + (orderNum != null ? orderNum.hashCode() : 0);
        result = 31 * result + (outEmploy != null ? outEmploy.hashCode() : 0);
        result = 31 * result + (nature != null ? nature.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "agencyId:'" + agencyId + '\'' +
                ", agencyName:'" + agencyName + '\'' +
                ", agencyType:'" + agencyType + '\'' +
                ", parentId:'" + parentId + '\'' +
                ", agencyPath:'" + agencyPath + '\'' +
                ", status:'" + status + '\'' +
                ", businesssource:'" + businesssource + '\'' +
                ", agencyDesc:'" + agencyDesc + '\'' +
                ", isRoot:'" + isRoot + '\'' +
                ", orderNum:" + orderNum +
                ", outEmploy:'" + outEmploy + '\'' +
                ", nature:'" + nature + '\'' +
                ", level:'" +level+"'" +
                '}';
    }
}
