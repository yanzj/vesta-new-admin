package com.maxrocky.vesta.application.baseData.adminDTO;

/**
 * Created by chen on 2016/11/9.
 * 检查项设置接收数据封装类
 */
public class CategoryReceiveDTO {
    private String employId;         //责任单位ID
    private String domain;           //所属模块
    private String categoryId;       //检查项ID
    private String projectId;        //项目ID
    private String ckState;         //半勾选ID
    private String tenderId;        //标段id

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
    }

    public String getEmployId() {
        return employId;
    }

    public void setEmployId(String employId) {
        this.employId = employId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCkState() {
        return ckState;
    }

    public void setCkState(String ckState) {
        this.ckState = ckState;
    }
}
