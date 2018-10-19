package com.maxrocky.vesta.application.baseData.adminDTO;

/**
 * Created by chen on 2016/11/9.
 */
public class SupplierCategoryDTO {
    private String categoryId;      //检查项ID
    private String categoryName;    //名称
    private String supplierId;      //责任单位Id
    private String supplierName;    //责任单位名
    private String defManageId;     //默认责任人ID
    private String defManageName;   //责任人名字
    private String defSupervisorId; //默认监理ID
    private String defSupervisorName;//监理人名
    private String defOwnerId;      //默认抄送人ID
    private String defOwnerName;    //抄送人名
    private String domain;          //所属模块 1日常巡检 2检查验收,关键工序 3样板点评 4材料验收 5旁站

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDefManageId() {
        return defManageId;
    }

    public void setDefManageId(String defManageId) {
        this.defManageId = defManageId;
    }

    public String getDefManageName() {
        return defManageName;
    }

    public void setDefManageName(String defManageName) {
        this.defManageName = defManageName;
    }

    public String getDefSupervisorId() {
        return defSupervisorId;
    }

    public void setDefSupervisorId(String defSupervisorId) {
        this.defSupervisorId = defSupervisorId;
    }

    public String getDefSupervisorName() {
        return defSupervisorName;
    }

    public void setDefSupervisorName(String defSupervisorName) {
        this.defSupervisorName = defSupervisorName;
    }

    public String getDefOwnerId() {
        return defOwnerId;
    }

    public void setDefOwnerId(String defOwnerId) {
        this.defOwnerId = defOwnerId;
    }

    public String getDefOwnerName() {
        return defOwnerName;
    }

    public void setDefOwnerName(String defOwnerName) {
        this.defOwnerName = defOwnerName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
