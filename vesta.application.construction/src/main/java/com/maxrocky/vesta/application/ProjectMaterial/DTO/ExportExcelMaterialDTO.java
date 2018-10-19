package com.maxrocky.vesta.application.ProjectMaterial.DTO;

/**
 * Created by Magic on 2016/12/12.
 */
public class ExportExcelMaterialDTO {
    private int num;//序号
//    private String materialId;//材料验收Id
    private String projectName;//项目名称
    private String classifyTwoName;//材料类型
    private String batchName;//批次名称
    private String approachTime;//进场时间
    private String approachNumber;//进场批量
    private String usedPart;//准备使用部位
    private String supplierName;//供应商名字
    private String assignName;//材料负责人
    private String firstPartyName;          //甲方负责人名字
    private String supervisorName;//第三方监理名字
    private String createName;//创建人名字
    private String state;//状态

//    public String getMaterialId() {
//        return materialId;
//    }
//
//    public void setMaterialId(String materialId) {
//        this.materialId = materialId;
//    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getClassifyTwoName() {
        return classifyTwoName;
    }

    public void setClassifyTwoName(String classifyTwoName) {
        this.classifyTwoName = classifyTwoName;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getApproachTime() {
        return approachTime;
    }

    public void setApproachTime(String approachTime) {
        this.approachTime = approachTime;
    }

    public String getApproachNumber() {
        return approachNumber;
    }

    public void setApproachNumber(String approachNumber) {
        this.approachNumber = approachNumber;
    }

    public String getUsedPart() {
        return usedPart;
    }

    public void setUsedPart(String usedPart) {
        this.usedPart = usedPart;
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

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
