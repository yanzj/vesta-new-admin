package com.maxrocky.vesta.application.news.DTO;

import java.util.Date;

/**
 * Created by yuanyn on 2017/6/13.
 */
public class NewsDTO {

    private String newsId;        //新闻ID
    private String newsTitle;     //新闻标题
    private String newsImgUrl;    //新闻插图
    private String newsContent;   //新闻内容
    private String newsSource;    //来源
    private String slideShow;     //"1"设置为轮播图

    private String createName;    //创建人
    private Date createDate;      //创建时间
    private String modifyName;    //修改人
    private Date modifyDate;      //修改时间


    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsImgUrl() {
        return newsImgUrl;
    }

    public void setNewsImgUrl(String newsImgUrl) {
        this.newsImgUrl = newsImgUrl;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public String getSlideShow() {
        return slideShow;
    }

    public void setSlideShow(String slideShow) {
        this.slideShow = slideShow;
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

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
