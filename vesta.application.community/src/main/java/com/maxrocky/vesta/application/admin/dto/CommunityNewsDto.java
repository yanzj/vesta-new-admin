package com.maxrocky.vesta.application.admin.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/3/29
 * Time: 21:51
 * 新闻公告详情
 */
public class CommunityNewsDto implements Serializable{

    public CommunityNewsDto() {
    }

    public CommunityNewsDto(String id, String imgTitle, String newsImg) {
        this.id = id;
        this.imgTitle = imgTitle;
        this.newsImg = newsImg;
    }
    

    private String id;              //id

    private String title;           //新闻标题

    private String newsImg;         //新闻图片

    private Integer type;            //新闻类型 0.一级图片，1.二级，2.新闻 3.新闻子标题

    private String operator;        //操作人

    private String operatorTimeString;    //修改时间

    private String imgTitle;        //图片标题

    private String overview;        //新闻概述

    private String comment;         //新闻内容

    private String releaseDate;   //发布日期

    private String releasePerson;   //发布人

    private String staDate;     //开始日期

    private String endDate;     //结束日期

    private Integer releaseStatus; //发布状态

    private String createPerson;//创建人

    private MultipartFile homePageimgpath;//图片地址(首页推荐图)
    private MultipartFile activityimgpath;//图片地址(新增)
    private Integer imgStatus;//图片状态


    List<CommunityNewsDto> imgList = new ArrayList<CommunityNewsDto>(); //二级标题存储

    List<CommunityNewsDto> newsList =  new ArrayList<CommunityNewsDto>(); //新闻详情标题存储

    private String city;        //城市
    private String citys;        //城市集合
    private String cityIds;      //城市Id集合
    private String project;     //项目
    private String projects;     //项目集合
    private String projectIds;  //项目Id集合

    private String scopeId;         //区域ID
    private String projectCode;     //项目ID
    private String menuId;          //菜单ID

    /* 新增查询字段(用户权限范围)_2016-08-30_Wyd */
    private List<Map<String,Object>> roleScopeList;
    /* ------------------------------------- */


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsImg() {
        return newsImg;
    }

    public void setNewsImg(String newsImg) {
        this.newsImg = newsImg;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorTimeString() {
        return operatorTimeString;
    }

    public void setOperatorTimeString(String operatorTimeString) {
        this.operatorTimeString = operatorTimeString;
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<CommunityNewsDto> getImgList() {
        return imgList;
    }

    public void setImgList(List<CommunityNewsDto> imgList) {
        this.imgList = imgList;
    }

    public List<CommunityNewsDto> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<CommunityNewsDto> newsList) {
        this.newsList = newsList;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleasePerson() {
        return releasePerson;
    }

    public void setReleasePerson(String releasePerson) {
        this.releasePerson = releasePerson;
    }

    public String getStaDate() {
        return staDate;
    }

    public void setStaDate(String staDate) {
        this.staDate = staDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Integer releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    public MultipartFile getHomePageimgpath() {
        return homePageimgpath;
    }

    public void setHomePageimgpath(MultipartFile homePageimgpath) {
        this.homePageimgpath = homePageimgpath;
    }

    public MultipartFile getActivityimgpath() {
        return activityimgpath;
    }

    public void setActivityimgpath(MultipartFile activityimgpath) {
        this.activityimgpath = activityimgpath;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Integer getImgStatus() {
        return imgStatus;
    }

    public void setImgStatus(Integer imgStatus) {
        this.imgStatus = imgStatus;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCitys() {
        return citys;
    }

    public void setCitys(String citys) {
        this.citys = citys;
    }

    public String getCityIds() {
        return cityIds;
    }

    public void setCityIds(String cityIds) {
        this.cityIds = cityIds;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(String projectIds) {
        this.projectIds = projectIds;
    }

    public List<Map<String, Object>> getRoleScopeList() {
        return roleScopeList;
    }

    public void setRoleScopeList(List<Map<String, Object>> roleScopeList) {
        this.roleScopeList = roleScopeList;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
