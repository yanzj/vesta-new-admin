package com.maxrocky.vesta.application.DTO.jsonDTO;

/**
 * Created by mql on 2016/6/13.
 */
public class SupplierRelationshipDTO {
    private String id;
    private String supplierId;//供应商id
    private String projectId;//项目id
    private String projectNum;//项目编码
    private String thirdId;//三级分类id
    private String state;//状态0无效1有效
    private String modifyDate;//修改时间

    public SupplierRelationshipDTO() {
        this.id = "";
        this.supplierId = "";
        this.projectId = "";
        this.projectNum = "";
        this.thirdId = "";
        this.state = "";
        this.modifyDate="";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
}
