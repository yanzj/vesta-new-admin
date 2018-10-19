package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 工程项目表(工程进展自定义项目表)
 * Created by WeiYangDong on 2017/6/8.
 */
@Entity
@Table(name = "engineering_project")
public class EngineeringProjectEntity {

    private String engineeringProjectId;//主键ID
    private String cityId;//城市ID
    private String cityName;//城市名称
    private String projectName;//工程项目名称

    private String createBy;    //创建人
    private Date createOn;      //创建时间

    @Id
    @Column(name = "engineeringProject_id", length = 32)
    public String getEngineeringProjectId() {
        return engineeringProjectId;
    }

    public void setEngineeringProjectId(String engineeringProjectId) {
        this.engineeringProjectId = engineeringProjectId;
    }

    @Basic
    @Column(name = "city_id",length = 100)
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "city_name",length = 100)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Basic
    @Column(name = "project_name",length = 100)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "create_by", nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
}
