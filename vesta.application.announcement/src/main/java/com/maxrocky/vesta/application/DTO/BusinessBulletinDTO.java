package com.maxrocky.vesta.application.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商业公告DTO
 * Created by WeiYangDong on 2017/9/18.
 */
public class BusinessBulletinDTO {
    private String menuId;//菜单ID
    private List<Map<String,Object>> roleScopeList;//用户权限范围

    private String id;//主键ID
    private String title;//公告标题
    private String content;//公告内容
    private String infoSignImgUrl;//公告信息标识图
    private MultipartFile infoSignImgFile;//公告信息标识图上传文件
    private Integer isBanner;//是否作为banner展示(0,否/1,是)
    private Integer isLink;//是否外链
    private String linkSrc;//外链地址
    private Integer releaseStatus;//发布状态(0,未发布/1,已发布)
    private Date releaseDate;//发布日期
    private String releaseStartDate;//发布检索开始时间
    private String releaseEndDate;//发布检索结束时间
    private String releasePerson;//发布人

    private String cityId;//城市ID
    private String cityName;//城市名称
    private String projectCode;//工程项目编码
    private String projectName;//工程项目名称

    private String createBy;    //创建人
    private Date createOn;      //创建时间
    private String modifyBy;    //修改人
    private Date modifyOn;      //修改时间

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public List<Map<String, Object>> getRoleScopeList() {
        return roleScopeList;
    }

    public void setRoleScopeList(List<Map<String, Object>> roleScopeList) {
        this.roleScopeList = roleScopeList;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInfoSignImgUrl() {
        return infoSignImgUrl;
    }

    public void setInfoSignImgUrl(String infoSignImgUrl) {
        this.infoSignImgUrl = infoSignImgUrl;
    }

    public Integer getIsBanner() {
        return isBanner;
    }

    public void setIsBanner(Integer isBanner) {
        this.isBanner = isBanner;
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

    public Integer getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Integer releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseStartDate() {
        return releaseStartDate;
    }

    public void setReleaseStartDate(String releaseStartDate) {
        this.releaseStartDate = releaseStartDate;
    }

    public String getReleaseEndDate() {
        return releaseEndDate;
    }

    public void setReleaseEndDate(String releaseEndDate) {
        this.releaseEndDate = releaseEndDate;
    }

    public String getReleasePerson() {
        return releasePerson;
    }

    public void setReleasePerson(String releasePerson) {
        this.releasePerson = releasePerson;
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

    public MultipartFile getInfoSignImgFile() {
        return infoSignImgFile;
    }

    public void setInfoSignImgFile(MultipartFile infoSignImgFile) {
        this.infoSignImgFile = infoSignImgFile;
    }
}
