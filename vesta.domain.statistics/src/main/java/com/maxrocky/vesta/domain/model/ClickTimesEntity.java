package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by sunmei on 2016/2/15.
 * 点击量统计表
 */
@Entity
@Table(name = "click_times")
public class ClickTimesEntity {
    /*模块 */
    public static final String CIRCLE = "1";//邻里圈
    public static final String SERVICE = "2";//服务信息
    public static final String PERIPHERY = "3";//周边
    public static final String HOME = "4";//我的



    private String id;
    private String project;//项目小区
    private String projectId;//项目id
    private String companyId;//公司id
    private String regionId;//区域id
    private int circle;//邻里圈
    private int service;//服务信息
    private int periphery;//周边
    private int home;//我的





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
    @Column(name = "circle",  length = 100)
    public int getCircle() {
        return circle;
    }

    public void setCircle(int circle) {
        this.circle = circle;
    }
    @Basic
    @Column(name = "service",  length = 100)
    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }
    @Basic
    @Column(name = "periphery",  length = 100)
    public int getPeriphery() {
        return periphery;
    }

    public void setPeriphery(int periphery) {
        this.periphery = periphery;
    }
    @Basic
    @Column(name = "home",  length = 100)
    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
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
}
