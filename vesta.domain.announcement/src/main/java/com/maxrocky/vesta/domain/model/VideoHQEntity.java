package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 视频(总部)表
 * Created by WeiYangDong on 2017/9/27.
 */
@Entity
@Table(name = "video_hq")
public class VideoHQEntity {

    private String id;//主键ID
    private String videoName;//视频名称
    private String videoMapImgUrl;//视频导图
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

    @Id
    @Column(name = "id",length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "video_name",length = 100)
    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    @Basic
    @Column(name = "video_map_img",length = 100)
    public String getVideoMapImgUrl() {
        return videoMapImgUrl;
    }

    public void setVideoMapImgUrl(String videoMapImgUrl) {
        this.videoMapImgUrl = videoMapImgUrl;
    }

    @Basic
    @Column(name = "video_id",length = 50)
    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Basic
    @Column(name = "video_link_src",length = 500)
    public String getVideoLinkSrc() {
        return videoLinkSrc;
    }

    public void setVideoLinkSrc(String videoLinkSrc) {
        this.videoLinkSrc = videoLinkSrc;
    }

    @Basic
    @Column(name = "video_synopsis",length = 16777216)
    public String getVideoSynopsis() {
        return videoSynopsis;
    }

    public void setVideoSynopsis(String videoSynopsis) {
        this.videoSynopsis = videoSynopsis;
    }

    @Basic
    @Column(name = "release_status",length = 5)
    public Integer getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Integer releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "release_date", nullable = true, length = 50)
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Basic
    @Column(name = "release_person",length = 50)
    public String getReleasePerson() {
        return releasePerson;
    }

    public void setReleasePerson(String releasePerson) {
        this.releasePerson = releasePerson;
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
