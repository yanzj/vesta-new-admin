package com.maxrocky.vesta.application.jsonDTO;

import java.util.List;

/**
 * Created by chen on 2016/5/23.
 * 接收参数封装类
 */
public class MaterialReceiveDTO {
    private String paradeTime;        //进场时间
    private String projectId;         //所属项目
    private String paradeNum;         //进场批量
    private String usePlace;          //使用部位
    private String supplier;          //供应商
    private String personName;        //负责人
    private String personId;          //负责人ID
    private String checkPerson;       //验收人
    private String checkUserId;       //验收人ID
    private String checkTime;         //验收时间
    private String materialSpec;      //材料规格
    private String batchName;         //批次名称
    private List<QuotaDTO> quotaList; //指标列表


    public String getCheckPerson() {
        return checkPerson;
    }

    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(String checkUserId) {
        this.checkUserId = checkUserId;
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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public String getMaterialSpec() {
        return materialSpec;
    }

    public void setMaterialSpec(String materialSpec) {
        this.materialSpec = materialSpec;
    }
}
