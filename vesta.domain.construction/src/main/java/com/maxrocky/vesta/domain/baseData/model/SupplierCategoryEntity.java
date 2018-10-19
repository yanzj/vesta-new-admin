package com.maxrocky.vesta.domain.baseData.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/10/18.
 * 责任单位检查项关联关系实体
 */

@Entity
@Table(name = "project_supplier_category",uniqueConstraints = {@UniqueConstraint(columnNames = {"SUPPLIER_ID","CATEGORY_ID","DOMAIN"})})
public class SupplierCategoryEntity {
    private long id;                //主键
    private String supplierId;      //责任单位ID
    private String categoryId;      //检查项ID
    private String domain;          //所属模块  1日常巡检 2检查验收 3样板点评 4材料验收 5旁站 6关键工序
    private String defManageId;     //默认责任人ID
    private String defSupervisorId; //默认监理ID
    private String defOwnerId;      //默认抄送人ID
    private String status;          //状态
    private Date modifyTime;        //修改时间
    private String ckState;         //勾选状态 0半勾选表示无用

    @Id
    @Column(name = "ID",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SUPPLIER_ID",length = 50)
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Basic
    @Column(name = "CATEGORY_ID")
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "DEF_MANAGE_ID",length = 50)
    public String getDefManageId() {
        return defManageId;
    }

    public void setDefManageId(String defManageId) {
        this.defManageId = defManageId;
    }

    @Basic
    @Column(name = "DEF_SUPERVISOR_ID",length = 50)
    public String getDefSupervisorId() {
        return defSupervisorId;
    }

    public void setDefSupervisorId(String defSupervisorId) {
        this.defSupervisorId = defSupervisorId;
    }

    @Basic
    @Column(name = "DEF_OWNER_ID",length = 50)
    public String getDefOwnerId() {
        return defOwnerId;
    }

    public void setDefOwnerId(String defOwnerId) {
        this.defOwnerId = defOwnerId;
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
    @Column(name = "MODIFY_TIME")
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Basic
    @Column(name = "DOMAIN",length = 2)
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Basic
    @Column(name = "CHECK_STATE",length = 1)
    public String getCkState() {
        return ckState;
    }

    public void setCkState(String ckState) {
        this.ckState = ckState;
    }
}
