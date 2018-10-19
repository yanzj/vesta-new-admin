package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by zhanghj on 2016/2/19.
 */
@Entity
@Table(name = "ehr_tree")
public class EhrTreeEntity {
    private String mdpGuid;
    private String employeeId;
    private String employeeCode;
    private String unitId;
    private String unitCode;
    private String isMainOrg;
    private String mdpBatchTime;
    private String mdpOperationtype;
    private String mdpResult;

    @Id
    @Column(name = "MDP_GUID", nullable = false, insertable = true, updatable = true, length = 32)
    public String getMdpGuid() {
        return mdpGuid;
    }

    public void setMdpGuid(String mdpGuid) {
        this.mdpGuid = mdpGuid;
    }

    @Basic
    @Column(name = "employeeID", nullable = true, insertable = true, updatable = true, length = 32)
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "employeeCode", nullable = true, insertable = true, updatable = true, length = 32)
    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    @Basic
    @Column(name = "unitID", nullable = true, insertable = true, updatable = true, length = 32)
    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    @Basic
    @Column(name = "unitCode", nullable = true, insertable = true, updatable = true, length = 32)
    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    @Basic
    @Column(name = "isMainOrg", nullable = true, insertable = true, updatable = true, length = 32)
    public String getIsMainOrg() {
        return isMainOrg;
    }

    public void setIsMainOrg(String isMainOrg) {
        this.isMainOrg = isMainOrg;
    }

    @Basic
    @Column(name = "MDP_BatchTime", nullable = true, insertable = true, updatable = true, length = 32)
    public String getMdpBatchTime() {
        return mdpBatchTime;
    }

    public void setMdpBatchTime(String mdpBatchTime) {
        this.mdpBatchTime = mdpBatchTime;
    }

    @Basic
    @Column(name = "MDP_OPERATIONTYPE", nullable = true, insertable = true, updatable = true, length = 32)
    public String getMdpOperationtype() {
        return mdpOperationtype;
    }

    public void setMdpOperationtype(String mdpOperationtype) {
        this.mdpOperationtype = mdpOperationtype;
    }

    @Basic
    @Column(name = "MDP_RESULT", nullable = true, insertable = true, updatable = true, length = 32)
    public String getMdpResult() {
        return mdpResult;
    }

    public void setMdpResult(String mdpResult) {
        this.mdpResult = mdpResult;
    }

}
