package com.maxrocky.vesta.application.adminDTO;

/**
 * Created by mql on 2016/6/6.
 * 供应商
 */
public class SupplierDTO {
    public String supplierId;
    public String supplierName;
    public String threeType;//三级分类
    public String projectId;//项目ID

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

    public String getThreeType() {
        return threeType;
    }

    public void setThreeType(String threeType) {
        this.threeType = threeType;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
