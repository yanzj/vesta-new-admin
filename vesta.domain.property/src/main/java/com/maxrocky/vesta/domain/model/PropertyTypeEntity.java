package com.maxrocky.vesta.domain.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import javax.persistence.Basic;


/**
 * Created by sunmei on 2016/2/1.
 */
@Entity
@Table(name = "property_type")
public class PropertyTypeEntity {
    private String typeId;// 类型id
    private String type;     //公告类型
    private String propertyRange;// 公告范围
    private String projectId;//项目

    @Id
    @Column(name = "TYPE_ID", nullable = false, insertable = true, updatable = true, length = 32)
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    @Basic
    @Column(name = "TYPE", length = 200)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Basic
    @Column(name = "Property_Range", length = 200)
    public String getPropertyRange() {
        return propertyRange;
    }

    public void setPropertyRange(String propertyRange) {
        this.propertyRange = propertyRange;
    }
    @Basic
    @Column(name = "Project_Id", length = 200)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
