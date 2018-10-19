package com.maxrocky.vesta.domain.baseData.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/11/8.
 * 工程阶段与前端同步人员基础数据表
 */

@Entity
@Table(name = "base_project_people")
public class BaseProjectPeopleEntity {
    private long autoId;               //主键
    private String peopleId;           //人员ID
    private String peopleName;         //人员名称
    private String supplierId;         //责任单位或监理ID
    private String supplierName;       //责任单位名称
    private String supplierType;       //类型 1总包  2分包   3.监理
    private String projectId;          //项目ID
    private String projectName;        //项目名
    private Date modifyTime;           //修改时间
    private String status;             //状态 0删除 1正常
    private String abbreviationName;   //责任单位简称

    @Id
    @Column(name = "AUTO_ID",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getAutoId() {
        return autoId;
    }

    public void setAutoId(long autoId) {
        this.autoId = autoId;
    }

    @Basic
    @Column(name = "PEOPLE_ID",length = 50,nullable = false)
    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    @Basic
    @Column(name = "PEOPLE_NAME",length = 50,nullable = false)
    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    @Basic
    @Column(name = "SUPPLIER_ID",length = 50,nullable = false)
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Basic
    @Column(name = "SUPPLIER_NAME",length = 32,nullable = false)
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Basic
    @Column(name = "SUPPLIER_TYPE",length = 2,nullable = false)
    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }

    @Basic
    @Column(name = "PROJECT_ID",length = 50,nullable = false)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "PROJECT_NAME",length = 32)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "MODIFY_TIME")
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Basic
    @Column(name = "STATUS",length = 2)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Basic
    @Column(name = "ABBREVIATION_NAME",length = 32)
    public String getAbbreviationName() {
        return abbreviationName;
    }

    public void setAbbreviationName(String abbreviationName) {
        this.abbreviationName = abbreviationName;
    }
}
