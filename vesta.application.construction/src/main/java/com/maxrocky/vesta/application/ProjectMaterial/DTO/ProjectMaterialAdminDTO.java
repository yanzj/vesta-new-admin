package com.maxrocky.vesta.application.ProjectMaterial.DTO;


/**
 * Created by Magic on 2016/12/12.
 */
public class ProjectMaterialAdminDTO {
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String classTwoName;//材料类型
    private String approachTime;//进场时间
    private String supplierName;//供应商名字
    private String assignName;//材料负责人
    private String createName;//创建人名字
    private String firstPartyName;//甲方负责人名字
    private String supervisorName;//第三方监理名字
    private String state;//状态
    private String materialId;//材料验收Id

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getClassTwoName() {
        return classTwoName;
    }

    public void setClassTwoName(String classTwoName) {
        this.classTwoName = classTwoName;
    }

    public String getApproachTime() {
        return approachTime;
    }

    public void setApproachTime(String approachTime) {
        this.approachTime = approachTime;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getFirstPartyName() {
        return firstPartyName;
    }

    public void setFirstPartyName(String firstPartyName) {
        this.firstPartyName = firstPartyName;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
