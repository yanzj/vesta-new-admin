package com.maxrocky.vesta.application.adminDTO;

import java.util.Date;

/**
 * 轮播图DTO
 * Created by yuanyn on 2018/6/11 0011.
 */
public class SlideShowDTO {

    private String slideShowId;        //轮播图ID
    private String slideShowTitle;     //轮播图标题
    private String slideShowImgUrl;    //轮播图插图
    private String slideShowContent;   //轮播图内容
    private String slideShowSource;    //来源
    private String slideShowType;      //type值  1： 图文  2： 视频
    private String videoUrl;      //视频链接
    private String outUrl;      //H5外部链接
    private String isSlideShow;  // 是否轮播  0 不轮播  1 轮播
    private String createName;    //创建人
    private String sCreateDate;    //查询时输入的创建时间
    private Date createDate;    //创建时间
    private Date releaseDate;   //发布时间
    private String sReleaseDate;   //查询时输入的发布时间
    private String isShow;   //创建时判断是否已经有5条上架的轮播图字段


    public String getSlideShowId() {
        return slideShowId;
    }

    public void setSlideShowId(String slideShowId) {
        this.slideShowId = slideShowId;
    }

    public String getSlideShowTitle() {
        return slideShowTitle;
    }

    public void setSlideShowTitle(String slideShowTitle) {
        this.slideShowTitle = slideShowTitle;
    }

    public String getSlideShowImgUrl() {
        return slideShowImgUrl;
    }

    public void setSlideShowImgUrl(String slideShowImgUrl) {
        this.slideShowImgUrl = slideShowImgUrl;
    }

    public String getSlideShowContent() {
        return slideShowContent;
    }

    public void setSlideShowContent(String slideShowContent) {
        this.slideShowContent = slideShowContent;
    }

    public String getSlideShowSource() {
        return slideShowSource;
    }

    public void setSlideShowSource(String slideShowSource) {
        this.slideShowSource = slideShowSource;
    }

    public String getSlideShowType() {
        return slideShowType;
    }

    public void setSlideShowType(String slideShowType) {
        this.slideShowType = slideShowType;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getOutUrl() {
        return outUrl;
    }

    public void setOutUrl(String outUrl) {
        this.outUrl = outUrl;
    }

    public String getIsSlideShow() {
        return isSlideShow;
    }

    public void setIsSlideShow(String isSlideShow) {
        this.isSlideShow = isSlideShow;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getsCreateDate() {
        return sCreateDate;
    }

    public void setsCreateDate(String sCreateDate) {
        this.sCreateDate = sCreateDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getsReleaseDate() {
        return sReleaseDate;
    }

    public void setsReleaseDate(String sReleaseDate) {
        this.sReleaseDate = sReleaseDate;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }
}
