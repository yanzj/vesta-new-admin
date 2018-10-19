package com.maxrocky.vesta.application.DTO;


import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 视频(总部)DTO
 * Created by WeiYangDong on 2017/9/27.
 */
public class VideoHQDTO {
    private String menuId;//菜单ID

    private String id;//主键ID
    private String videoName;//视频名称
    private String videoMapImgUrl;//视频导图
    private MultipartFile videoMapImgFile;//视频导图上传文件
    private String videoId;//视频ID
    private String videoLinkSrc;//视频链接
    private String videoSynopsis;//视频简介

    private Integer releaseStatus;//发布状态(0,未发布/1,已发布)
    private Date releaseDate;//发布日期
    private String releasePerson;//发布人

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoMapImgUrl() {
        return videoMapImgUrl;
    }

    public void setVideoMapImgUrl(String videoMapImgUrl) {
        this.videoMapImgUrl = videoMapImgUrl;
    }

    public MultipartFile getVideoMapImgFile() {
        return videoMapImgFile;
    }

    public void setVideoMapImgFile(MultipartFile videoMapImgFile) {
        this.videoMapImgFile = videoMapImgFile;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoLinkSrc() {
        return videoLinkSrc;
    }

    public void setVideoLinkSrc(String videoLinkSrc) {
        this.videoLinkSrc = videoLinkSrc;
    }

    public String getVideoSynopsis() {
        return videoSynopsis;
    }

    public void setVideoSynopsis(String videoSynopsis) {
        this.videoSynopsis = videoSynopsis;
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

    public String getReleasePerson() {
        return releasePerson;
    }

    public void setReleasePerson(String releasePerson) {
        this.releasePerson = releasePerson;
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
