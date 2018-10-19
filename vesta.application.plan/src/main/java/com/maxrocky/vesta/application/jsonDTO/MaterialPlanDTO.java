package com.maxrocky.vesta.application.jsonDTO;

import java.util.List;

/**
 * Created by chen on 2016/5/23.
 */
public class MaterialPlanDTO {
    private String materialSpec;           //材料规格
    private String batchName;              //批次名称
    private String projectName;            //项目名称
    private String projectId;              //项目ID
    private String paradeNum;              //进场批量
    private String paradeTime;             //进场时间
    private String eligibility;            //有无合格证
    private String usePlace;               //使用部位
    private String materialType;           //材料类型
    private String supplier;               //供应商
    private String personName;             //材料负责人
    private String checkPerson;            //验收人
    private String personRole;             //验收人角色
    private String personMobile;           //验收人手机号码
    private String checkTime;              //验收时间
    private String checkStatus;            //验收状态
    private List<QuotaDTO> quotaList;      //指标列表

    public String getCheckPerson() {
        return checkPerson;
    }

    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getMaterialSpec() {
        return materialSpec;
    }

    public void setMaterialSpec(String materialSpec) {
        this.materialSpec = materialSpec;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getParadeNum() {
        return paradeNum;
    }

    public void setParadeNum(String paradeNum) {
        this.paradeNum = paradeNum;
    }

    public String getParadeTime() {
        return paradeTime;
    }

    public void setParadeTime(String paradeTime) {
        this.paradeTime = paradeTime;
    }

    public String getPersonMobile() {
        return personMobile;
    }

    public void setPersonMobile(String personMobile) {
        this.personMobile = personMobile;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonRole() {
        return personRole;
    }

    public void setPersonRole(String personRole) {
        this.personRole = personRole;
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

    public List<QuotaDTO> getQuotaList() {
        return quotaList;
    }

    public void setQuotaList(List<QuotaDTO> quotaList) {
        this.quotaList = quotaList;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getUsePlace() {
        return usePlace;
    }

    public void setUsePlace(String usePlace) {
        this.usePlace = usePlace;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }
}
