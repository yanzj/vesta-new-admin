package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by Tom on 2016/1/14 21:17.
 * Describe:业态实体类
 */
@Entity
@Table(name = "house_format")
public class HouseFormatEntity {

    private String id;//主键
    private String name;//名称
    private String description;//描述
    private String projectId;//项目Id

    @Id
    @Column(name = "ID",nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME",nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DESCRIPTION", length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "PROJECT_ID",nullable = false, length = 32)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
