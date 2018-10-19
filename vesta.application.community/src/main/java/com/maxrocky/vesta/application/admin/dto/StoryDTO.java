package com.maxrocky.vesta.application.admin.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author WeiYangDong
 * @date 2018/3/22 17:16
 * @deprecated 故事荟DTO
 */
public class StoryDTO {

    private String menuId;//菜单ID
    private String cityId;//城市ID
    private String projectNum;//项目编码
    private String citys;//城市集合
    private String projects;//项目集合
    private String cityIds;//城市Id集合
    private String projectIds;//项目Id集合
    private List<Map<String,Object>> roleScopeList;//用户权限范围
//    private String cityName;//城市名称
//    private String projectName;//项目名称

    private String id;//主键ID
    private String title;//故事标题
    private String content;//故事内容
    private String infoSignImgUrl;//故事标识图
    private Integer clientId;//故事所属客户端ID
    private Integer isLink;//是否有外链(0,否1,是)
    private String linkSrc;//外链地址
    private Integer orderNum;//信息排序字段
    private Integer infoStatus;//促销信息状态(0,未删除/1,已删除)

    private Integer releaseStatus;//发布状态(0,未发布/1,已发布)
    private String releaseDate;//发布日期
    private String releasePerson;//发布人
    private String releaseProjectScopes;//发布项目范围
    private String releaseStaDate;//发布开始日期
    private String releaseEndDate;//发布结束日期

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

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getCitys() {
        return citys;
    }

    public void setCitys(String citys) {
        this.citys = citys;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getCityIds() {
        return cityIds;
    }

    public void setCityIds(String cityIds) {
        this.cityIds = cityIds;
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

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
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

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(Integer infoStatus) {
        this.infoStatus = infoStatus;
    }

    public Integer getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Integer releaseStatus) {
        this.releaseStatus = releaseStatus;
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

    public String getReleaseProjectScopes() {
        return releaseProjectScopes;
    }

    public void setReleaseProjectScopes(String releaseProjectScopes) {
        this.releaseProjectScopes = releaseProjectScopes;
    }

    public String getReleaseStaDate() {
        return releaseStaDate;
    }

    public void setReleaseStaDate(String releaseStaDate) {
        this.releaseStaDate = releaseStaDate;
    }

    public String getReleaseEndDate() {
        return releaseEndDate;
    }

    public void setReleaseEndDate(String releaseEndDate) {
        this.releaseEndDate = releaseEndDate;
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
}
