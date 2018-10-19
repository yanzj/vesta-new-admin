package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by sunmei on 2016/2/19.
 */
@Entity
@Table(name = "clickpage_times")
public class ClickTimesPageEntity {

    /*首页模块 */
    public static final String PROPERTY = "5";//公告
    public static final String CONSULTATION = "6";//咨询
    public static final String PRAISE = "7";//表扬
    public static final String COMPLAINT = "8";//投诉
    public static final String REPAIR = "9";//报修
    public static final String EVALUATE = "10";//评价

    private String id;
    private String project;//项目小区
    private String projectId;//项目id
    private String companyId;//公司id
    private String regionId;//区域id
    private int property;//公告
    private int consultation;//咨询
    private int praise;//表扬
    private int complaint;//投诉
    private int repair;//报修
    private int evaluate;//评价

    @Id
    @Column(name = "id", nullable = false,insertable = true,updatable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "property",  length = 100)
    public int getProperty() {
        return property;
    }

    public void setProperty(int property) {
        this.property = property;
    }
    @Basic
    @Column(name = "consultation",  length = 100)
    public int getConsultation() {
        return consultation;
    }

    public void setConsultation(int consultation) {
        this.consultation = consultation;
    }
    @Basic
    @Column(name = "praise",  length = 100)
    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }
    @Basic
    @Column(name = "complaint",  length = 100)
    public int getComplaint() {
        return complaint;
    }

    public void setComplaint(int complaint) {
        this.complaint = complaint;
    }
    @Basic
    @Column(name = "repair",  length = 100)
    public int getRepair() {
        return repair;
    }

    public void setRepair(int repair) {
        this.repair = repair;
    }
    @Basic
    @Column(name = "evaluate",  length = 100)
    public int getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(int evaluate) {
        this.evaluate = evaluate;
    }

    @Basic
    @Column(name = "project_Id",  length = 100)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    @Basic
    @Column(name = "company_Id",  length = 100)
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    @Basic
    @Column(name = "region_Id",  length = 100)
    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    @Basic
    @Column(name = "project",  length = 100)
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
