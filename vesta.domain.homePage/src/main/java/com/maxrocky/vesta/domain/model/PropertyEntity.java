package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by sunmei on 2016/2/26.
 */
@Entity
@Table(name = "property_page")
public class PropertyEntity {
    private String id;
    private String url;
    private String imgUrl;
    private String name;
    private int sort;
    private String projectId;

    @Id
    @Column(name = "id",nullable = false,insertable = true,updatable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "url",length = 300)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @Basic
    @Column(name = "Img_Url",length = 300)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    @Basic
    @Column(name = "name",length = 300)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Basic
    @Column(name = "project_Id",length = 300)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    @Basic
    @Column(name = "sort",length = 300)
    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
