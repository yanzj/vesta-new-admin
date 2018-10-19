package com.maxrocky.vesta.application.admin.dto;

import java.util.Date;
import java.util.List;

/**
 * 黑名单数据信息DTO
 * Created by WeiYangDong on 2017/11/21.
 */
public class BlacklistDTO {

    private String menuId;//菜单ID

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

    private List<String> phoneList;//手机号码List

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
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

    public String getPromptContent() {
        return promptContent;
    }

    public void setPromptContent(String promptContent) {
        this.promptContent = promptContent;
    }

    public Integer getListType() {
        return listType;
    }

    public void setListType(Integer listType) {
        this.listType = listType;
    }

    public String getPhoneCollection() {
        return phoneCollection;
    }

    public void setPhoneCollection(String phoneCollection) {
        this.phoneCollection = phoneCollection;
    }

    public String getHouseCollection() {
        return houseCollection;
    }

    public void setHouseCollection(String houseCollection) {
        this.houseCollection = houseCollection;
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

    public List<String> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<String> phoneList) {
        this.phoneList = phoneList;
    }
}
