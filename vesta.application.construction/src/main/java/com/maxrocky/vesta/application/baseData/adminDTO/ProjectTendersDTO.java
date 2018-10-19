package com.maxrocky.vesta.application.baseData.adminDTO;


import java.util.List;

/**
 * Created by chen on 2016/10/24.
 */
public class ProjectTendersDTO {
    private String tenderId;      //标段ID
    private String projectId;     //项目ID
    private String buildIds;      //楼栋ID
    private List buildingIds;    //楼栋Id集合
    private String module;        //所属模块
    private String categoryIds;   //检查项ID
    private String projectName;   //项目名
    private String supplierName;  //总包名
    private String supplierId;    //总包ID
    private String tenderName;    //名称
    private String createBy;      //创建人
    private String modifyBy;      //修改人
    private String modifyOn;      //修改时间

    public List getBuildingIds() {
        return buildingIds;
    }

    public void setBuildingIds(List buildingIds) {
        this.buildingIds = buildingIds;
    }

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
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

    public String getTenderName() {
        return tenderName;
    }

    public void setTenderName(String tenderName) {
        this.tenderName = tenderName;
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getBuildIds() {
        return buildIds;
    }

    public void setBuildIds(String buildIds) {
        this.buildIds = buildIds;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
}
