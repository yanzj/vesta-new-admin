package com.maxrocky.vesta.application.baseData.JsonDTO;

/**
 * Created by chen on 2016/11/8.
 * 项目人员基础数据封装类
 */
public class ProjectPeopleDTO {
    private long id;
    private String peopleId="";           //人员ID
    private String peopleName="";         //人员名称
    private String supplierId="";         //责任单位或监理ID
    private String supplierName="";       //责任单位名称
    private String supplierType="";       //类型 1责任人 2监理
    private String nature="";                // 1总包 2分包
    private String projectId="";          //项目ID
    private String projectName="";        //项目名
    private String status="";             //状态 0删除 1正常

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
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

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }
}
