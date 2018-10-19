package com.maxrocky.vesta.application.DTO.admin;

/**
 * Created by Magic on 2016/9/2.
 */
public class RePieOrderDTO {
    private String repairId;//报修整改单id
    private String department;//内部负责人组
    private String repairManagerId;//内部负责人id
    private String supplier;//供应商id
    private String supplierId;//供应商负责人id
    private String classifyOne;//一级分类id
    private String classifyTwo;//二级分类id
    private String classifyThree;//三级分类id
    private String type;//	类型


    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRepairManagerId() {
        return repairManagerId;
    }

    public void setRepairManagerId(String repairManagerId) {
        this.repairManagerId = repairManagerId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getClassifyOne() {
        return classifyOne;
    }

    public void setClassifyOne(String classifyOne) {
        this.classifyOne = classifyOne;
    }

    public String getClassifyTwo() {
        return classifyTwo;
    }

    public void setClassifyTwo(String classifyTwo) {
        this.classifyTwo = classifyTwo;
    }

    public String getClassifyThree() {
        return classifyThree;
    }

    public void setClassifyThree(String classifyThree) {
        this.classifyThree = classifyThree;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
