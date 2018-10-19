package com.maxrocky.vesta.domain.project.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 安全项目实体类
 * Created by Jason on 2017/6/5.
 */
@Entity
@Table(name = "st_project")
public class SecurityProjectEntity {
    private int id;      //自增长数
    private String projectId;        //主键
    private String projectName;      //名称
    private String state;     //状态
    private String groupId;//集团ID
    private String groupName;//集团名称
    private String areaId;    //区域ID
    private String areaName;//区域名称
    private Date createOn;    //创建时间
    private Date modifyOn;    //修改时间

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PROJECT_ID", length = 50, nullable = false)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "PROJECT_NAME", length = 50, nullable = false)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "STATE", length = 6)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "GROUP_ID", length = 50, nullable = false)
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "AREA_ID", length = 50, nullable = false)
    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    @Basic
    @Column(name = "CREATE_ON")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MODIFY_ON")
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Basic
    @Column(name = "GROUP_NAME", length = 50)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Basic
    @Column(name = "AREA_NAME", length = 50)
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
