package com.maxrocky.vesta.domain.projectMaterial.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 材料验收实体表
 * Created by Magic on 2016/11/24.
 */

@Entity
@Table(name = "project_material")
public class ProjectMaterialEntity {
    private Long id;//自增长id
    private String materialId;//材料验收Id
    private String projectId;// 项目id
    private String projectName;//项目名称
    private String supplierId;//供应商id
    private String supplierName;//供应商名字
    private String assignId;//材料负责人Id
    private String assignName;//材料负责人
    private String approachNumber;//进场批量
    private String batchName;//批次名称
    private Date approachTime;//进场时间
    private String usedPart;//准备使用部位
    private String state;//状态
    private String createBy;//创建人
    private String createName;//创建人名字
    private Date createOn;//创建时间
    private String isQualified;//合格
    private Date modifyDate;//修改时间
    private String appId;//手机端id
    private Date inspectedTime;//验收时间

    private String classifyOne;//一级分类
    private String classifyTwo;//二级分类

    private String classifyOneName;//一级分类名称
    private String classifyTwoName;//二级分类名称

    private String supervisor;//第三方监理id
    private String supervisorName;//第三方监理名字

    private String dealPeople;//处理人

    private String firstParty;              //甲方负责人ID
    private String firstPartyName;          //甲方负责人名字

    private String shutDown;//关闭理由

    private String shutDownBy;//关闭人
    private Date shutDownOn;//关闭时间

    @Id
    @Column(name = "ID", length = 32)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "MATERIAL_ID", length = 32, nullable = true, insertable = true, updatable = true)
    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }
    @Basic
    @Column(name = "PROJECT_ID", length = 32, nullable = true, insertable = true, updatable = true)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    @Basic
    @Column(name = "PROJECT_NAME", length = 100, nullable = true, insertable = true, updatable = true)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    @Basic
    @Column(name = "SUPPLIER_ID", length = 32, nullable = true, insertable = true, updatable = true)
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    @Basic
    @Column(name = "SUPPLIER_NAME", length = 100, nullable = true, insertable = true, updatable = true)
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    @Basic
    @Column(name = "ASSIGN_ID", length = 32, nullable = true, insertable = true, updatable = true)
    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId;
    }
    @Basic
    @Column(name = "ASSIGN_NAME", length = 100, nullable = true, insertable = true, updatable = true)
    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }
    @Basic
    @Column(name = "APPROACH_NUMBER", length = 200, nullable = true, insertable = true, updatable = true)
    public String getApproachNumber() {
        return approachNumber;
    }

    public void setApproachNumber(String approachNumber) {
        this.approachNumber = approachNumber;
    }
    @Basic
    @Column(name = "APPROACH_TIME", length = 50, nullable = true, insertable = true, updatable = true)
    public Date getApproachTime() {
        return approachTime;
    }

    public void setApproachTime(Date approachTime) {
        this.approachTime = approachTime;
    }
    @Basic
    @Column(name = "USED_PART", length = 50, nullable = true, insertable = true, updatable = true)
    public String getUsedPart() {
        return usedPart;
    }

    public void setUsedPart(String usedPart) {
        this.usedPart = usedPart;
    }
    @Basic
    @Column(name = "STATE", length = 32, nullable = true, insertable = true, updatable = true)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "CREATE_BY", length = 100, nullable = true, insertable = true, updatable = true)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "IS_QUALIFIED", length = 200, nullable = true, insertable = true, updatable = true)
    public String getIsQualified() {
        return isQualified;
    }

    public void setIsQualified(String isQualified) {
        this.isQualified = isQualified;
    }
    @Basic
    @Column(name = "MODIFYDATE", length = 32, nullable = true, insertable = true, updatable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    @Basic
    @Column(name = "APP_ID", length = 100, nullable = true, insertable = true, updatable = true)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    @Basic
    @Column(name = "BATCH_NAME", length = 200, nullable = true, insertable = true, updatable = true)
    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    @Basic
    @Column(name = "CLASSIFY_ONE", length = 50, nullable = true, insertable = true, updatable = true)
    public String getClassifyOne() {
        return classifyOne;
    }

    public void setClassifyOne(String classifyOne) {
        this.classifyOne = classifyOne;
    }
    @Basic
    @Column(name = "CLASSIFY_TWO", length = 50, nullable = true, insertable = true, updatable = true)
    public String getClassifyTwo() {
        return classifyTwo;
    }

    public void setClassifyTwo(String classifyTwo) {
        this.classifyTwo = classifyTwo;
    }
    @Basic
    @Column(name = "CLASSIFY_ONE_NAME", length = 200, nullable = true, insertable = true, updatable = true)
    public String getClassifyOneName() {
        return classifyOneName;
    }

    public void setClassifyOneName(String classifyOneName) {
        this.classifyOneName = classifyOneName;
    }
    @Basic
    @Column(name = "CLASSIFY_TWO_NAME", length = 200, nullable = true, insertable = true, updatable = true)
    public String getClassifyTwoName() {
        return classifyTwoName;
    }

    public void setClassifyTwoName(String classifyTwoName) {
        this.classifyTwoName = classifyTwoName;
    }
    @Basic
    @Column(name = "CREATE_NAME", length = 100, nullable = true, insertable = true, updatable = true)
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
    @Basic
    @Column(name = "INSPECTED_TIME", length = 50, nullable = true, insertable = true, updatable = true)
    public Date getInspectedTime() {
        return inspectedTime;
    }

    public void setInspectedTime(Date inspectedTime) {
        this.inspectedTime = inspectedTime;
    }

    @Basic
    @Column(name = "SUPERVISOR", length = 32, nullable = true, insertable = true, updatable = true)
    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
    @Basic
    @Column(name = "SUPERVISOR_NAME", length = 100, nullable = true, insertable = true, updatable = true)
    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }
    @Basic
    @Column(name = "DEAL_PEOPLE", length = 200, nullable = true, insertable = true, updatable = true)
    public String getDealPeople() {
        return dealPeople;
    }

    public void setDealPeople(String dealPeople) {
        this.dealPeople = dealPeople;
    }
    @Basic
    @Column(name = "CREATE_ON", length = 32, nullable = true, insertable = true, updatable = true)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
    @Basic
    @Column(name = "FIRST_PARTY", length = 200, nullable = true, insertable = true, updatable = true)
    public String getFirstParty() {
        return firstParty;
    }

    public void setFirstParty(String firstParty) {
        this.firstParty = firstParty;
    }
    @Basic
    @Column(name = "FIRST_PARTY_NAME", length = 200, nullable = true, insertable = true, updatable = true)
    public String getFirstPartyName() {
        return firstPartyName;
    }

    public void setFirstPartyName(String firstPartyName) {
        this.firstPartyName = firstPartyName;
    }
    @Basic
    @Column(name = "SHUT_DOWN", length = 100, nullable = true, insertable = true, updatable = true)
    public String getShutDown() {
        return shutDown;
    }

    public void setShutDown(String shutDown) {
        this.shutDown = shutDown;
    }
    @Basic
    @Column(name = "SHUT_DOWN_BY", length = 100, nullable = true, insertable = true, updatable = true)
    public String getShutDownBy() {
        return shutDownBy;
    }

    public void setShutDownBy(String shutDownBy) {
        this.shutDownBy = shutDownBy;
    }
    @Basic
    @Column(name = "SHUT_DOWN_ON", length = 50, nullable = true, insertable = true, updatable = true)
    public Date getShutDownOn() {
        return shutDownOn;
    }

    public void setShutDownOn(Date shutDownOn) {
        this.shutDownOn = shutDownOn;
    }
}
