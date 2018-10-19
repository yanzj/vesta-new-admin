package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by chen on 2016/6/1.
 * 组 项目 关联关系 实体
 */
@Entity
@Table(name = "organize_projectmap")
public class OrganizeProjectmapEntity {
    private String id;           //主键
    private String projectId;    //项目ID
    private String projectCode;  //项目编码
    private String organizeId;   //组ID
    private String proStatus;    //状态 0删除  1正常

    @Id
    @Column(name = "ID",length = 60,unique = true,nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ORGANIZE_ID",length = 60,nullable = false)
    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    @Basic
    @Column(name = "PROJECT_ID",length = 60,nullable = false)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "PROJECT_CODE",length = 80)
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    @Basic
    @Column(name = "PRO_STATUS",length = 2)
    public String getProStatus() {
        return proStatus;
    }

    public void setProStatus(String proStatus) {
        this.proStatus = proStatus;
    }
}
