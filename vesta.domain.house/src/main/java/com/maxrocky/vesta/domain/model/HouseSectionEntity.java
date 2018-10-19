package com.maxrocky.vesta.domain.model;

/**
 * Created by zhanghj on 2016/1/22.
 */

import javax.persistence.*;
import java.util.Date;

/**
 * 项目公司部门表
 */
@Entity
@Table(name = "house_section")
public class HouseSectionEntity {

    public final static  String   SECTION_STATE_YES = "YES";//有效
    public final static  String   SECTION_STATE_NO="NO";//无效

    private String sectionId;//部门Id

    private String sectionName;//部门名称

    private int level;//当前级别

    private String state;//状态

    private String createBy;//创建人

    private Date createOn;//创建时间

    private String modifyBy;//修改人

    private Date modifyOn;//修改时间

    private String companyId;//公司Id

    private String projectId;//项目Id


    @Id
    @Column(name = "SECTION_ID",nullable = false, length = 32)
    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }



    @Basic
    @Column(name = "SECTION_NAME",nullable = false, length = 50)
    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    @Basic
    @Column(name = "SECTION_LEVEL",nullable = false, length = 50)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    @Basic
    @Column(name = "SECTION_STATE",nullable = false, length = 50)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "SECTION_CREATEBY",nullable = false, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "SECTION_CREATEON",nullable = false, length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "SECTION_MODIFYBY",nullable = false, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "SECTION_MODIFYON",nullable = false, length = 50)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Basic
    @Column(name = "COMPANY_ID",nullable = false, length = 50)
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    @Basic
    @Column(name = "PROJECT_ID",nullable = false, length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
