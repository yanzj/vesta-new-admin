package com.maxrocky.vesta.application.baseData.JsonDTO;

/**
 * Created by chen on 2016/11/4.
 */
public class TargetDataDTO {
    private String targetId="";     //指标ID
    private String categoryId="";   //分类ID
    private String targetName="";   //指标名称
    private String description="";  //描述
    private String status="";       //状态
    private String havePhoto;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getHavePhoto() {
        return havePhoto;
    }

    public void setHavePhoto(String havePhoto) {
        this.havePhoto = havePhoto;
    }
}
