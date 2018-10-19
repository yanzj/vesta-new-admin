package com.maxrocky.vesta.application.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;

/**
 * 首页轮播图_DTO
 * 2016/6/24_Wyd
 */
public class HomePageCarouselDTO {

    private String id;          //主键
    private String imgUrl;      //图片Url
    private String linkUrl;     //链接Url
    private Integer carouselOrder;      //轮播排序
    private String projectCode;   //项目Code
    private String projectName;   //项目名称
    private String createBy;    //创建人
    private Date createOn;      //创建时间
    private String modifyBy;    //修改人
    private Date modifyOn;      //修改时间
    private String cityId;    //城市名称

    private String cityList;        //城市集合
    private String cityIds;      //城市Id集合

    private String projectList;     //项目集合
    private String projectIds;  //项目Id集合

    //文件流(数组)
    private MultipartFile[] multipartFiles;
    //文件Url(集合)
    private ArrayList<String> imgUrls;
    //链接Url(集合)
    private ArrayList<String> linkUrls;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getCarouselOrder() {
        return carouselOrder;
    }

    public void setCarouselOrder(Integer carouselOrder) {
        this.carouselOrder = carouselOrder;
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


    public String getCityIds() {
        return cityIds;
    }

    public void setCityIds(String cityIds) {
        this.cityIds = cityIds;
    }

    public String getCityList() {
        return cityList;
    }

    public void setCityList(String cityList) {
        this.cityList = cityList;
    }

    public String getProjectList() {
        return projectList;
    }

    public void setProjectList(String projectList) {
        this.projectList = projectList;
    }

    public String getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(String projectIds) {
        this.projectIds = projectIds;
    }

    public MultipartFile[] getMultipartFiles() {
        return multipartFiles;
    }

    public void setMultipartFiles(MultipartFile[] multipartFiles) {
        this.multipartFiles = multipartFiles;
    }

    public ArrayList<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(ArrayList<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public ArrayList<String> getLinkUrls() {
        return linkUrls;
    }

    public void setLinkUrls(ArrayList<String> linkUrls) {
        this.linkUrls = linkUrls;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
