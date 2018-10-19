package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 工程进展表
 * Created by WeiYangDong on 2017/5/16.
 */
@Entity
@Table(name = "engineering_progress")
public class EngineeringProgressEntity {

    private String engineeringProgressId;//工程进展ID
    private String engineeringProgressTitle;//工程进展标题
    private String engineeringProgressSignImgUrl;//工程进展展示图
    private String engineeringProgressContent;//工程进展内容

    private String cityId;//城市ID
    private String cityName;//城市名称
    private String projectCode;//工程项目编码
    private String projectName;//工程项目名称
    private Integer isLink;//是否有外链(0,否1,是)
    private String linkSrc;//外链地址

    private String createBy;    //创建人
    private Date createOn;      //创建时间
    private String modifyBy;    //修改人
    private Date modifyOn;      //修改时间

    @Id
    @Column(name = "engineeringProgress_id", length = 32)
    public String getEngineeringProgressId() {
        return engineeringProgressId;
    }

    public void setEngineeringProgressId(String engineeringProgressId) {
        this.engineeringProgressId = engineeringProgressId;
    }

    @Basic
    @Column(name = "engineeringProgress_title",length = 200)
    public String getEngineeringProgressTitle() {
        return engineeringProgressTitle;
    }

    public void setEngineeringProgressTitle(String engineeringProgressTitle) {
        this.engineeringProgressTitle = engineeringProgressTitle;
    }

    @Basic
    @Column(name = "engineeringProgress_sign_img",length = 200)
    public String getEngineeringProgressSignImgUrl() {
        return engineeringProgressSignImgUrl;
    }

    public void setEngineeringProgressSignImgUrl(String engineeringProgressSignImgUrl) {
        this.engineeringProgressSignImgUrl = engineeringProgressSignImgUrl;
    }

    @Basic
    @Column(name = "engineeringProgress_content",length = 16777216)
    public String getEngineeringProgressContent() {
        return engineeringProgressContent;
    }

    public void setEngineeringProgressContent(String engineeringProgressContent) {
        this.engineeringProgressContent = engineeringProgressContent;
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
    @Column(name = "project_code",length = 100)
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
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

    @Basic
    @Column(name = "modify_by", nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_on", nullable = true, length = 50)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Basic
    @Column(name = "is_link",length = 1)
    public Integer getIsLink() {
        return isLink;
    }

    public void setIsLink(Integer isLink) {
        this.isLink = isLink;
    }

    @Basic
    @Column(name = "link_src",length = 500)
    public String getLinkSrc() {
        return linkSrc;
    }

    public void setLinkSrc(String linkSrc) {
        this.linkSrc = linkSrc;
    }
}
