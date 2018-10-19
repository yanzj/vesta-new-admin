package com.maxrocky.vesta.application.baseData.adminDTO;

import java.util.List;

/**
 * Created by 27978 on 2016/11/3.
 * ss
 */
public class ProjectTendersCategoryDTO {
    private String tenderId;      //标段ID
    private String tenderName;    //标段名称
    private String projectId;     //项目ID
    private String buildId;        //楼栋id
    private List<String> buildIds;      //楼栋ID集合
    private String module;        //所属模块
    private String categoryId;    //检查项id
    private List<String> categoryIds;   //检查项ID集合
    private String projectName;   //项目名
    private String supplierName;  //总包名
    private String supplierId;    //总包ID
    private String createBy;      //创建人
    private String modifyBy;      //修改人
    private String modifyOn;      //修改时间

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
    }

    public String getTenderName() {
        return tenderName;
    }

    public void setTenderName(String tenderName) {
        this.tenderName = tenderName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public List<String> getBuildIds() {
        return buildIds;
    }

    public void setBuildIds(List<String> buildIds) {
        this.buildIds = buildIds;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<String> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(String modifyOn) {
        this.modifyOn = modifyOn;
    }
}
