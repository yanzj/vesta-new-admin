package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/5/20.
 * 材料验收 材料计划实体
 */
@Entity
@Table(name = "material_plan")
public class MaterialPlanEntity {
    private String materialId;         //材料ID 主键
    private String batchName;          //批次名称
    private String projectId;          //所属项目
    private String personId;           //负责人Id
    private String personName;         //负责人
    private Date paradeTime;           //进场时间
    private Date checkTime;            //验收时间
    private String checkUserId;        //验收人ID
    private String checkPerson;        //验收人
    private String materialType;       //材料类型
    private String paradeNum;          //进场批量
    private String usePlace;           //使用部位
    private String supplier;           //供应商
    private String materialSpec;       //材料规格
    private String eligibility;        //是否有合格证 0、没有 1、有

    @Basic
    @Column(name = "BATCH_NAME",length = 80)
    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    @Basic
    @Column(name = "CHECK_PERSON",length = 50)
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
    @Column(name = "CHECK_USERID",length = 50)
    public String getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(String checkUserId) {
        this.checkUserId = checkUserId;
    }

    @Basic
    @Column(name = "ELIGIBILITY",length = 10)
    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    @Id
    @Column(name = "MATERIAL_ID",length = 50,unique = true,nullable = false)

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    @Basic
    @Column(name = "MATERIAL_SPEC")
    public String getMaterialSpec() {
        return materialSpec;
    }

    public void setMaterialSpec(String materialSpec) {
        this.materialSpec = materialSpec;
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
    @Column(name = "PARADE_NUM",length = 100)
    public String getParadeNum() {
        return paradeNum;
    }

    public void setParadeNum(String paradeNum) {
        this.paradeNum = paradeNum;
    }

    @Basic
    @Column(name = "PARADE_TIME")
    public Date getParadeTime() {
        return paradeTime;
    }

    public void setParadeTime(Date paradeTime) {
        this.paradeTime = paradeTime;
    }

    @Basic
    @Column(name = "PERSON_ID",length = 50)
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "PERSON_NAME",length = 80)
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @Basic
    @Column(name = "PROJECT_ID",length = 50)
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

    @Basic
    @Column(name = "USE_PLACE",length = 60)
    public String getUsePlace() {
        return usePlace;
    }

    public void setUsePlace(String usePlace) {
        this.usePlace = usePlace;
    }
}
