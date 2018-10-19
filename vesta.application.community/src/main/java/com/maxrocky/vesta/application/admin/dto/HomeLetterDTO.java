package com.maxrocky.vesta.application.admin.dto;

import java.util.Date;

/**
 * 家书管理模块DTO
 * Created by WeiYangDong on 2017/5/16.
 */
public class HomeLetterDTO {
    private String menuId;//菜单ID

    private String engineeringProgressId;//工程进展ID
    private String engineeringProgressTitle;//工程进展标题
    private String engineeringProgressSignImgUrl;//工程进展展示图
    private String engineeringProgressContent;//工程进展内容

    private String engineeringProjectId;//工程项目ID
    private String cityId;//城市ID
    private String cityName;//城市名称
    private String projectCode;//项目编码
    private String projectName;//项目名称

    private String createBy;    //创建人
    private Date createOn;      //创建时间
    private String modifyBy;    //修改人
    private Date modifyOn;      //修改时间

    private Integer isLink;//是否有外链(0,否1,是)
    private String linkSrc;//外链地址

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    public String getEngineeringProgressId() {
        return engineeringProgressId;
    }

    public void setEngineeringProgressId(String engineeringProgressId) {
        this.engineeringProgressId = engineeringProgressId;
    }

    public String getEngineeringProgressTitle() {
        return engineeringProgressTitle;
    }

    public void setEngineeringProgressTitle(String engineeringProgressTitle) {
        this.engineeringProgressTitle = engineeringProgressTitle;
    }

    public String getEngineeringProgressSignImgUrl() {
        return engineeringProgressSignImgUrl;
    }

    public void setEngineeringProgressSignImgUrl(String engineeringProgressSignImgUrl) {
        this.engineeringProgressSignImgUrl = engineeringProgressSignImgUrl;
    }

    public String getEngineeringProgressContent() {
        return engineeringProgressContent;
    }

    public void setEngineeringProgressContent(String engineeringProgressContent) {
        this.engineeringProgressContent = engineeringProgressContent;
    }

    public String getEngineeringProjectId() {
        return engineeringProjectId;
    }

    public void setEngineeringProjectId(String engineeringProjectId) {
        this.engineeringProjectId = engineeringProjectId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getIsLink() {
        return isLink;
    }

    public void setIsLink(Integer isLink) {
        this.isLink = isLink;
    }

    public String getLinkSrc() {
        return linkSrc;
    }

    public void setLinkSrc(String linkSrc) {
        this.linkSrc = linkSrc;
    }
}
