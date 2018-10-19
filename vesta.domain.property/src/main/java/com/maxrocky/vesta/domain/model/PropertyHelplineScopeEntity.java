package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by luxinxin on 2016/7/3.
 */
@Entity
@Table(name = "propertyHelpline_scope")
public class PropertyHelplineScopeEntity implements Serializable {
    private String id;         //主键
    private String propertyHelplineId; //   便民id
    private Integer isAll;       //全部        0,不是全部 1,全部
    private String city;     //所属城市
    private String cityId;  //所属城市Id
    private String projectName;     //项目名称
    private String projectId;       //项目code
    private Integer status;     // 0,启用 1，不启用
    private Date createDate;      //创建日期
    private String createPerson;    //创建人
    private Date operateDate;      //操作日期
    private String operatePerson;    //操作人

    @Id
    @Column(name = "id", length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "propertyHelplineId", length = 50, nullable = true, updatable = true)
    public String getPropertyHelplineId() {
        return propertyHelplineId;
    }

    public void setPropertyHelplineId(String propertyHelplineId) {
        this.propertyHelplineId = propertyHelplineId;
    }

    @Basic
    @Column(name = "is_all", length = 5, nullable = true, updatable = true)
    public Integer getIsAll() {
        return isAll;
    }

    public void setIsAll(Integer isAll) {
        this.isAll = isAll;
    }

    @Basic
    @Column(name = "city_name", length = 50, nullable = true, updatable = true)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "city_id",length = 50,nullable = true,updatable = true)
    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "project_name", length = 50, nullable = true, updatable = true)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "project_id",length = 50,nullable = true,updatable = true)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "status", length = 5, nullable = true, updatable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", length = 50, nullable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "create_person", length = 50, nullable = true, updatable = true)
    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "operate_date", length = 50, nullable = true, updatable = true)
    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    @Basic
    @Column(name = "operate_person", length = 50, nullable = true, updatable = true)
    public String getOperatePerson() {
        return operatePerson;
    }

    public void setOperatePerson(String operatePerson) {
        this.operatePerson = operatePerson;
    }
}
