package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 黑名单表
 * Created by WeiYangDong on 2017/11/21.
 */
@Entity
@Table(name = "blacklist")
public class BlacklistEntity {

    private String id;//主键ID
    private String listName;//名单名称
    private String cityId;//城市ID
    private String cityName;//城市名称
    private String projectCode;//项目编码
    private String projectName;//项目名称
    private String promptContent;//提示内容
    private Integer listType;//名单类型(1,手机号码集合;2,房产集合)
    private String phoneCollection;//手机号码集合
    private String houseCollection;//房产集合

    private String createBy;    //创建人
    private Date createOn;      //创建时间
    private String modifyBy;    //修改人
    private Date modifyOn;      //修改时间

    @Id
    @Column(name = "id",length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "list_name",length = 150)
    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
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
    @Column(name = "project_code",length = 50)
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
    @Column(name = "prompt_content",length = 150)
    public String getPromptContent() {
        return promptContent;
    }

    public void setPromptContent(String promptContent) {
        this.promptContent = promptContent;
    }

    @Basic
    @Column(name = "list_type",length = 1)
    public Integer getListType() {
        return listType;
    }

    public void setListType(Integer listType) {
        this.listType = listType;
    }

    @Basic
    @Column(name = "phone_collection",length = 16777216)
    public String getPhoneCollection() {
        return phoneCollection;
    }

    public void setPhoneCollection(String phoneCollection) {
        this.phoneCollection = phoneCollection;
    }

    @Basic
    @Column(name = "house_collection",length = 16777216)
    public String getHouseCollection() {
        return houseCollection;
    }

    public void setHouseCollection(String houseCollection) {
        this.houseCollection = houseCollection;
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
}
