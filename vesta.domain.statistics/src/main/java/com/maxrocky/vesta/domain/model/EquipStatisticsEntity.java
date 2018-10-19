package com.maxrocky.vesta.domain.model;


import javax.persistence.*;

/**
 * Created by sunmei on 2016/2/17.
 * 各设备统计表
 */
@Entity
@Table(name = "equip_statistics")
public class EquipStatisticsEntity {

    private String id;
    private String project;//项目小区
    private String projectId;//项目id
    private String companyId;//公司id
    private String regionId;//区域id
    private String userId;//用户id
    private String type;//设备类型
    private String counts;//个数

    @Id
    @Column(name = "id", nullable = false,insertable = true,updatable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "project",  length = 100)
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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
    @Column(name = "type",  length = 32)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Basic
    @Column(name = "user_Id",  length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Basic
    @Column(name = "counts",  length = 100)
    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }
}