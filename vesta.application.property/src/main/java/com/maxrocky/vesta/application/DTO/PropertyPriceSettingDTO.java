package com.maxrocky.vesta.application.DTO;

/**
 * Created by ZhangBailiang on 2016/2/15.
 * 物业价格设置  DTO
 */
public class PropertyPriceSettingDTO {

    private String priceSettingId;//维修价格id
    private String projectId;//项目ID
    private String projectName;// 项目名称
    private String priceSettingCount;//价格设置

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPriceSettingId() {
        return priceSettingId;
    }

    public void setPriceSettingId(String priceSettingId) {
        this.priceSettingId = priceSettingId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPriceSettingCount() {
        return priceSettingCount;
    }

    public void setPriceSettingCount(String priceSettingCount) {
        this.priceSettingCount = priceSettingCount;
    }
}
