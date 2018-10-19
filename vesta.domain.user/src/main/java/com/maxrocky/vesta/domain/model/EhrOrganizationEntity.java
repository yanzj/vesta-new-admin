package com.maxrocky.vesta.domain.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by zhanghj on 2016/2/19.
 */
@Entity
@javax.persistence.Table(name = "ehr_organization")
public class EhrOrganizationEntity {
    private String mdpGuid;

    @Id
    @javax.persistence.Column(name = "MDP_GUID", nullable = false, insertable = true, updatable = true, length = 100)
    public String getMdpGuid() {
        return mdpGuid;
    }

    public void setMdpGuid(String mdpGuid) {
        this.mdpGuid = mdpGuid;
    }

    private String orgId;

    @Basic
    @javax.persistence.Column(name = "orgID", nullable = true, insertable = true, updatable = true, length = 100)
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    private String orgName;

    @Basic
    @javax.persistence.Column(name = "orgName", nullable = true, insertable = true, updatable = true, length = 100)
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    private String orgCode;

    @Basic
    @javax.persistence.Column(name = "orgCode", nullable = true, insertable = true, updatable = true, length = 100)
    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    private String orgCategory;

    @Basic
    @javax.persistence.Column(name = "orgCategory", nullable = true, insertable = true, updatable = true, length = 255)
    public String getOrgCategory() {
        return orgCategory;
    }

    public void setOrgCategory(String orgCategory) {
        this.orgCategory = orgCategory;
    }

    private String isRoot;

    @Basic
    @javax.persistence.Column(name = "isRoot", nullable = true, insertable = true, updatable = true, length = 255)
    public String getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(String isRoot) {
        this.isRoot = isRoot;
    }

    private String officeTel;

    @Basic
    @javax.persistence.Column(name = "officeTel", nullable = true, insertable = true, updatable = true, length = 255)
    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel;
    }

    private String fax;

    @Basic
    @javax.persistence.Column(name = "fax", nullable = true, insertable = true, updatable = true, length = 255)
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    private String address;

    @Basic
    @javax.persistence.Column(name = "address", nullable = true, insertable = true, updatable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String postcode;

    @Basic
    @javax.persistence.Column(name = "postcode", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    private String email;

    @Basic
    @javax.persistence.Column(name = "email", nullable = true, insertable = true, updatable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String web;

    @Basic
    @javax.persistence.Column(name = "web", nullable = true, insertable = true, updatable = true, length = 255)
    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    private String introduction;

    @Basic
    @javax.persistence.Column(name = "introduction", nullable = true, insertable = true, updatable = true, length = 255)
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    private String remark;

    @Basic
    @javax.persistence.Column(name = "remark", nullable = true, insertable = true, updatable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private String operateTime;

    @Basic
    @javax.persistence.Column(name = "operateTime", nullable = true, insertable = true, updatable = true, length = 255)
    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    private String orgOperator;

    @Basic
    @javax.persistence.Column(name = "orgOperator", nullable = true, insertable = true, updatable = true, length = 255)
    public String getOrgOperator() {
        return orgOperator;
    }

    public void setOrgOperator(String orgOperator) {
        this.orgOperator = orgOperator;
    }

    private String status;

    @Basic
    @javax.persistence.Column(name = "status", nullable = true, insertable = true, updatable = true, length = 255)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String validDate;

    @Basic
    @javax.persistence.Column(name = "validDate", nullable = true, insertable = true, updatable = true, length = 255)
    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    private String beginDate;

    @Basic
    @javax.persistence.Column(name = "beginDate", nullable = true, insertable = true, updatable = true, length = 255)
    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    private String businessArea;

    @Basic
    @javax.persistence.Column(name = "businessArea", nullable = true, insertable = true, updatable = true, length = 255)
    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    private String shortName;

    @Basic
    @javax.persistence.Column(name = "shortName", nullable = true, insertable = true, updatable = true, length = 255)
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    private String busType;

    @Basic
    @javax.persistence.Column(name = "busType", nullable = true, insertable = true, updatable = true, length = 255)
    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    private String orgSort;

    @Basic
    @javax.persistence.Column(name = "orgSort", nullable = true, insertable = true, updatable = true, length = 255)
    public String getOrgSort() {
        return orgSort;
    }

    public void setOrgSort(String orgSort) {
        this.orgSort = orgSort;
    }

    private String orgUnitStatus;

    @Basic
    @javax.persistence.Column(name = "orgUnitStatus", nullable = true, insertable = true, updatable = true, length = 255)
    public String getOrgUnitStatus() {
        return orgUnitStatus;
    }

    public void setOrgUnitStatus(String orgUnitStatus) {
        this.orgUnitStatus = orgUnitStatus;
    }

    private String cadOrgType;

    @Basic
    @javax.persistence.Column(name = "cadOrgType", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCadOrgType() {
        return cadOrgType;
    }

    public void setCadOrgType(String cadOrgType) {
        this.cadOrgType = cadOrgType;
    }

    private String openDate;

    @Basic
    @javax.persistence.Column(name = "openDate", nullable = true, insertable = true, updatable = true, length = 255)
    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    private String parentUnitId;

    @Basic
    @javax.persistence.Column(name = "parentUnitID", nullable = true, insertable = true, updatable = true, length = 255)
    public String getParentUnitId() {
        return parentUnitId;
    }

    public void setParentUnitId(String parentUnitId) {
        this.parentUnitId = parentUnitId;
    }

    private String parentUnitCode;

    @Basic
    @javax.persistence.Column(name = "parentUnitCode", nullable = true, insertable = true, updatable = true, length = 255)
    public String getParentUnitCode() {
        return parentUnitCode;
    }

    public void setParentUnitCode(String parentUnitCode) {
        this.parentUnitCode = parentUnitCode;
    }

    private String parentStatus;

    @Basic
    @javax.persistence.Column(name = "parentStatus", nullable = true, insertable = true, updatable = true, length = 255)
    public String getParentStatus() {
        return parentStatus;
    }

    public void setParentStatus(String parentStatus) {
        this.parentStatus = parentStatus;
    }

    private String orgOrder;

    @Basic
    @javax.persistence.Column(name = "orgOrder", nullable = true, insertable = true, updatable = true, length = 255)
    public String getOrgOrder() {
        return orgOrder;
    }

    public void setOrgOrder(String orgOrder) {
        this.orgOrder = orgOrder;
    }

    private String fullPath;

    @Basic
    @javax.persistence.Column(name = "fullPath", nullable = true, insertable = true, updatable = true, length = 255)
    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    private String companyCategoryId;

    @Basic
    @javax.persistence.Column(name = "companyCategoryID", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCompanyCategoryId() {
        return companyCategoryId;
    }

    public void setCompanyCategoryId(String companyCategoryId) {
        this.companyCategoryId = companyCategoryId;
    }

    private String mdpBatchTime;

    @Basic
    @javax.persistence.Column(name = "MDP_BatchTime", nullable = true, insertable = true, updatable = true, length = 255)
    public String getMdpBatchTime() {
        return mdpBatchTime;
    }

    public void setMdpBatchTime(String mdpBatchTime) {
        this.mdpBatchTime = mdpBatchTime;
    }

    private String mdpOperationtype;

    @Basic
    @javax.persistence.Column(name = "MDP_OPERATIONTYPE", nullable = true, insertable = true, updatable = true, length = 255)
    public String getMdpOperationtype() {
        return mdpOperationtype;
    }

    public void setMdpOperationtype(String mdpOperationtype) {
        this.mdpOperationtype = mdpOperationtype;
    }

    private String mdpResult;

    @Basic
    @javax.persistence.Column(name = "MDP_RESULT", nullable = true, insertable = true, updatable = true, length = 255)
    public String getMdpResult() {
        return mdpResult;
    }

    public void setMdpResult(String mdpResult) {
        this.mdpResult = mdpResult;
    }
}
