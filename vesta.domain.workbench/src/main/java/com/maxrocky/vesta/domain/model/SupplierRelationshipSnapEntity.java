package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mql on 2016/6/15.
 * 供应商关系快照
 */
@Entity
@Table(name = "supplier_relationship_snap")
public class SupplierRelationshipSnapEntity {
    private long id;//自增ID
    private String supplierId;//供应商id
    private String projectId;//项目id
    private String projectNum;//项目编码
    private String thirdId;//三级分类id
    private String state;//状态0无效1有效
    private Date createDate;//创建时间
    private Date modifyDate;//修改时间
    private String businessId;//业务

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",length = 50,nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SUPPLIER_ID",nullable = true, length = 50)
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Basic
    @Column(name = "PROJECT_ID",nullable = true, length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "PROJECT_NUM",nullable = true, length = 50)
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    @Basic
    @Column(name = "THIRD_ID",nullable = true, length = 50)
    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    @Basic
    @javax.persistence.Column(name = "CREATE_DATE", nullable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @javax.persistence.Column(name = "MODIFY_DATE", nullable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Basic
    @Column(name = "BUSINESS_ID",nullable = true, length = 50)
    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    @Basic
    @javax.persistence.Column(name = "STATE", length = 50)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
