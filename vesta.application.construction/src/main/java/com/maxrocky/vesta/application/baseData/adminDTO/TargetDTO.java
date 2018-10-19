package com.maxrocky.vesta.application.baseData.adminDTO;

/**
 * Created by chen on 2016/11/21.
 */
public class TargetDTO {
    private String id;
    private String categoryId;//分类ID
    private String name;//指标名称
    private String description;//描述

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
