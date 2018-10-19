package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/5/21.
 * 材料验收 退场记录实体
 */
@Entity
@Table(name = "material_exit")
public class MaterialExitEntity {
    private String exitId;           //主键
    private String imgUrl;           //图片附件地址
    private String problem;          //问题描述
    private String materialId;       //材料ID
    private String materialType;     //材料类型
    private String projectId;        //项目ID
    private String checkPerson;      //验收人
    private Date checkTime;          //验收时间
    private String supplier;         //供应商
    private String dutyUnit;         //责任单位


    @Basic
    @Column(name = "CHECK_PERSON",length = 32)
    public String getCheckPerson() {
        return checkPerson;
    }

    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    @Basic
    @Column(name = "CHECK_TIME")
    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    @Basic
    @Column(name = "DUTY_UNIT",length = 60)
    public String getDutyUnit() {
        return dutyUnit;
    }

    public void setDutyUnit(String dutyUnit) {
        this.dutyUnit = dutyUnit;
    }

    @Id
    @Column(name = "EXIT_ID",length = 60,unique = true,nullable = false)
    public String getExitId() {
        return exitId;
    }

    public void setExitId(String exitId) {
        this.exitId = exitId;
    }

    @Basic
    @Column(name = "IMG_URL",length = 500)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Basic
    @Column(name = "MATERIAL_ID",length = 60)
    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    @Basic
    @Column(name = "MATERIAL_TYPE",length = 30)
    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    @Basic
    @Column(name = "PROBLEM",length = 100)
    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    @Basic
    @Column(name = "PROJECT_ID",length = 60)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "SUPPLIER",length = 80)
    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
