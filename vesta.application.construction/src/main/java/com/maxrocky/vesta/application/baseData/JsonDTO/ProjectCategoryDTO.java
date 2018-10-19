package com.maxrocky.vesta.application.baseData.JsonDTO;

/**
 * Created by chen on 2016/11/1.
 */
public class ProjectCategoryDTO {
    private String categoryId="";           //主键
    private String categoryName="";         //名称
    private int level;                      //级别
    private String domain="";               //所属模块 1日常巡检 2检查验收、关键工序 3样板点评 4材料验收 5旁站
    private String parentId="";             //父级ID
    private String categoryState="";        //状态 1正常 0删除
    private String freeFiled;
    private String timeSpace;

    public String getCategoryState() {
        return categoryState;
    }

    public void setCategoryState(String categoryState) {
        this.categoryState = categoryState;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getFreeFiled() {
        return freeFiled;
    }

    public void setFreeFiled(String freeFiled) {
        this.freeFiled = freeFiled;
    }

    public String getTimeSpace() {
        return timeSpace;
    }

    public void setTimeSpace(String timeSpace) {
        this.timeSpace = timeSpace;
    }
}
