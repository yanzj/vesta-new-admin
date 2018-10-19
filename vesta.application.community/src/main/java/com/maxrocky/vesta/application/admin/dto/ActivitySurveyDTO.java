package com.maxrocky.vesta.application.admin.dto;

import java.util.Date;
import java.util.List;

/**
 * @author WeiYangDong
 * @date 2018/5/10 14:14
 * @deprecated 线下活动调查DTO
 */
public class ActivitySurveyDTO {

    private String menuId;//菜单ID
    private String id;//主键ID

    private String title;//标题
    private String content;//内容


    private String cityId;//城市ID
    private String cityName;//城市名称
    private String projectCode;//项目编码
    private String projectName;//项目名称

    private Date workOn;//当班日期
    private String workContent;//当班内容
    private String feel;//个人感受
    private String proposal;//提升建议

    private String projectPhoto;//项目照片
    private String autograph;//电子签名

    private String createBy;    //创建人
    private Date createOn;      //创建时间
    private String modifyBy;    //修改人
    private Date modifyOn;      //修改时间

    private String workOnSta;
    private String workOnEnd;
    private List<String> projectPhotoList;

    public List<String> getProjectPhotoList() {
        return projectPhotoList;
    }

    public void setProjectPhotoList(List<String> projectPhotoList) {
        this.projectPhotoList = projectPhotoList;
    }

    public String getWorkOnSta() {
        return workOnSta;
    }

    public void setWorkOnSta(String workOnSta) {
        this.workOnSta = workOnSta;
    }

    public String getWorkOnEnd() {
        return workOnEnd;
    }

    public void setWorkOnEnd(String workOnEnd) {
        this.workOnEnd = workOnEnd;
    }

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

    public Date getWorkOn() {
        return workOn;
    }

    public void setWorkOn(Date workOn) {
        this.workOn = workOn;
    }

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getFeel() {
        return feel;
    }

    public void setFeel(String feel) {
        this.feel = feel;
    }

    public String getProposal() {
        return proposal;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }

    public String getProjectPhoto() {
        return projectPhoto;
    }

    public void setProjectPhoto(String projectPhoto) {
        this.projectPhoto = projectPhoto;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
}
