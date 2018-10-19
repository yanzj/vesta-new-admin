package com.maxrocky.vesta.domain.readilyTake.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 随手拍 实体表
 * Created by Magic on 2017/6/5.
 */
@Entity
@Table(name = "st_readily_take")
public class ReadilyTakeEntity {
    private String appId;//appId  唯一性
    private Long id;// 返回查询数据使用 自增id
    private Date modifyDate;// 返回查询数据使用 修改时间
    private String patId;// 随手拍id   uuid
    private String examinationParts;//检查部位
    private String severityLevel;//严重等级
    private String createId;//创建人ID
    private String createName;//创建人姓名
    private Date createDate;//创建时间
    private String createUnitId;//创建人所属单位id
    private String createUnitName;//创建人所属单位名称
    private String description;//问题描述
    private String internalPeopleId;//内部负责人id   ----->  总包负责人id
    private String internalPeople;//内部负责人名字吗  ----->  总包负责人
    private String externalPeopleId;//外部负责人id    ----->  监理负责人id
    private String externalPeople;//外部负责人名字    ----->  监理负责人
    private String state;//状态   waiting:待确认   finshed 处理中  reform  已完成  twiceReform 二次整改
    private String rectificationDescription;//整改描述
    private Date rectificationDate;//整改时间
    private String rectificationPeople;//整改负责人
    private String supplementaryDescription;//补充描述 //废弃用
    private String securityAdministratorId;//安全管理员id
    private String securityAdministrator;//安全管理员名字  --->  项目安全员
    private String address;//地址
    private String projectId;//项目公司ID
    private String projectName;//项目公司名称
    private String additional;//追加字段  大比武

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",length = 50,nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "APP_ID", unique = true ,length = 100)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    @Basic
    @Column(name = "MODIFY_DATE", nullable = true, insertable = true, updatable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    @Basic
    @Column(name = "PAT_ID", nullable = false, insertable = true, updatable = true, length = 100)
    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }
    @Basic
    @Column(name = "EXAMINATION_PARTS", nullable = true, insertable = true, updatable = true, length = 50)
    public String getExaminationParts() {
        return examinationParts;
    }

    public void setExaminationParts(String examinationParts) {
        this.examinationParts = examinationParts;
    }
    @Basic
    @Column(name = "SEVERITY_LEVEL", nullable = true, insertable = true, updatable = true, length = 50)
    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }
    @Basic
    @Column(name = "CREATE_ID", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }
    @Basic
    @Column(name = "CREATE_NAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
    @Basic
    @Column(name = "CREATE_DATE", nullable = true, insertable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @Basic
    @Column(name = "CREATE_UNIT_ID", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCreateUnitId() {
        return createUnitId;
    }

    public void setCreateUnitId(String createUnitId) {
        this.createUnitId = createUnitId;
    }
    @Basic
    @Column(name = "CREATE_UNIT_NAME", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCreateUnitName() {
        return createUnitName;
    }

    public void setCreateUnitName(String createUnitName) {
        this.createUnitName = createUnitName;
    }
    @Basic
    @Column(name = "DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 2000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Basic
    @Column(name = "INTERNAL_PEOPLE_ID", nullable = true, insertable = true, updatable = true, length = 100)
    public String getInternalPeopleId() {
        return internalPeopleId;
    }

    public void setInternalPeopleId(String internalPeopleId) {
        this.internalPeopleId = internalPeopleId;
    }
    @Basic
    @Column(name = "INTERNAL_PEOPLE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getInternalPeople() {
        return internalPeople;
    }

    public void setInternalPeople(String internalPeople) {
        this.internalPeople = internalPeople;
    }
    @Basic
    @Column(name = "EXTERNAL_PEOPLE_ID", nullable = true, insertable = true, updatable = true, length = 100)
    public String getExternalPeopleId() {
        return externalPeopleId;
    }

    public void setExternalPeopleId(String externalPeopleId) {
        this.externalPeopleId = externalPeopleId;
    }
    @Basic
    @Column(name = "EXTERNAL_PEOPLE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getExternalPeople() {
        return externalPeople;
    }

    public void setExternalPeople(String externalPeople) {
        this.externalPeople = externalPeople;
    }
    @Basic
    @Column(name = "STATE", nullable = true, insertable = true, updatable = true, length = 100)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "RECTIFICATION_DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 2000)
    public String getRectificationDescription() {
        return rectificationDescription;
    }

    public void setRectificationDescription(String rectificationDescription) {
        this.rectificationDescription = rectificationDescription;
    }
    @Basic
    @Column(name = "RECTIFICATION_DATE", nullable = true, insertable = true, updatable = true)
    public Date getRectificationDate() {
        return rectificationDate;
    }

    public void setRectificationDate(Date rectificationDate) {
        this.rectificationDate = rectificationDate;
    }
    @Basic
    @Column(name = "RECTIFICATION_PEOPLE", nullable = true, insertable = true, updatable = true, length = 2000)
    public String getRectificationPeople() {
        return rectificationPeople;
    }

    public void setRectificationPeople(String rectificationPeople) {
        this.rectificationPeople = rectificationPeople;
    }
    @Basic
    @Column(name = "SUPPLEMENTARY_DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 2000)
    public String getSupplementaryDescription() {
        return supplementaryDescription;
    }

    public void setSupplementaryDescription(String supplementaryDescription) {
        this.supplementaryDescription = supplementaryDescription;
    }
    @Basic
    @Column(name = "SECURITY_ADMINISTRATOR_ID", nullable = true, insertable = true, updatable = true, length = 100)
    public String getSecurityAdministratorId() {
        return securityAdministratorId;
    }

    public void setSecurityAdministratorId(String securityAdministratorId) {
        this.securityAdministratorId = securityAdministratorId;
    }
    @Basic
    @Column(name = "SECURITY_ADMINISTRATOR", nullable = true, insertable = true, updatable = true, length = 100)
    public String getSecurityAdministrator() {
        return securityAdministrator;
    }

    public void setSecurityAdministrator(String securityAdministrator) {
        this.securityAdministrator = securityAdministrator;
    }
    @Basic
    @Column(name = "ADDRESS", nullable = true, insertable = true, updatable = true, length = 500)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Basic
    @Column(name = "PROJECT_ID", nullable = true, insertable = true, updatable = true, length = 200)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    @Basic
    @Column(name = "PROJECT_NAME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    @Basic
    @javax.persistence.Column(name = "ADDITIONAL", columnDefinition = "varchar(32)default 0", insertable = true, updatable = true)
    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }
}
