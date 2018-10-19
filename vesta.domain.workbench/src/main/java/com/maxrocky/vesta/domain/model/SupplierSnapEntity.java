package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mql on 2016/6/15.
 * 供应商快照
 */
@Entity
@Table(name = "supplier_snap")
public class SupplierSnapEntity {
    private long id;//自增ID
    private String businessId;//业务ID
    private String name;//供应商名称
    private String type;//类别
    private String state;//状态；0无效1有效
    private Date createDate;//创建时间
    private Date modifyDate;//修改时间
    private String graded; //级别
    private String parentId;//父级id

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
    @Column(name = "NAME",nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "TYPE",nullable = true, length = 50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    @javax.persistence.Column(name = "BUSINESS_ID", length = 50)
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
    @Basic
    @Column(name = "GRADED",nullable = true, length = 100)
    public String getGraded() {
        return graded;
    }

    public void setGraded(String graded) {
        this.graded = graded;
    }
    @Basic
    @Column(name = "PARENT_ID",nullable = true, length = 100)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
