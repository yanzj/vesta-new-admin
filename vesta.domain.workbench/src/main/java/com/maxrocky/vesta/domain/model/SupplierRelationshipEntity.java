package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liudongxin on 2016/6/7.
 * 供应商关系实体
 */
@Entity
@Table(name = "supplier_relationship")
public class SupplierRelationshipEntity {
    private String id;
    private String supplierId;//供应商id
    private String projectId;//项目id
    private String projectNum;//项目编码
    private String thirdId;//三级分类id
    private Date createDate;//创建时间
    private Date modifyDate;//修改时间

    @Id
    @Column(name = "ID",length = 50,nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
