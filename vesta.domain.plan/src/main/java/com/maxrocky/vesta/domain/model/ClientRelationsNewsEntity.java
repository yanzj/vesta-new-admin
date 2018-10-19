package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 新闻管理
 * Created by yuanyn on 2018/6/7 0007.
 */

@Entity
@Table(name = "qc_news")
public class ClientRelationsNewsEntity {
    private String newsId;        //新闻ID
    private String newsTitle;     //新闻标题
    private String newsImgUrl;    //新闻插图
    private String newsContent;   //新闻内容
    private String newsSource;    //所属区域
    private String projectId;    //所属区域id
    private String newsType;          //type值  1： 图文  2： 视频   3: 外链
    private String state;        // 状态  0 删除  1 正常

    private String videoUrl;      //视频链接
    private String outUrl;      //H5外链
    private String createName;    //创建人
    private String createBy;    //创建人id
    private Date createDate;    //创建时间
    private String modifyName;    //修改人
    private String modifyBy;    //修改人id
    private Date modifyDate;    //修改时间
    private Date releaseDate;   //发布时间
    private Long amountAccess;  //访问量

    @Id
    @Column(name = "NEWS_ID",length = 32)
    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }
    @Basic
    @Column(name = "NEWS_TITLE",length = 200)
    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    @Basic
    @Column(name = "NEWS_IMGURL",length = 200)
    public String getNewsImgUrl() {
        return newsImgUrl;
    }

    public void setNewsImgUrl(String newsImgUrl) {
        this.newsImgUrl = newsImgUrl;
    }
    @Basic
    @Column(name = "NEWS_CONTENT",length = 5000)
    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }
    @Basic
    @Column(name = "NEWS_TYPE",length = 50)
    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }
    @Basic
    @Column(name = "NEWS_SOURCE",length = 100)
    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }
    @Basic
    @Column(name = "PROJECT_ID",length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    @Basic
    @Column(name = "CREATE_NAME",length = 50)
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
    @Basic
    @Column(name = "CREATE_DATE",length = 50)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @Basic
    @Column(name = "MODIFY_NAME",length = 50)
    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }
    @Basic
    @Column(name = "MODIFY_DATE",length = 50)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    @Basic
    @Column(name = "VIDEO_URL",length = 200)
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    @Basic
    @Column(name = "OUT_URL",length = 200)
    public String getOutUrl() {
        return outUrl;
    }

    public void setOutUrl(String outUrl) {
        this.outUrl = outUrl;
    }
    @Basic
    @Column(name = "STATE",length = 50)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "CREATE_BY",length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    @Basic
    @Column(name = "MODIFY_BY",length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "RELEASE_DATE",length = 50)
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    @Basic
    @Column(name = "AMOUNT_ACCESS")
    public Long getAmountAccess() {
        return amountAccess;
    }

    public void setAmountAccess(Long amountAccess) {
        this.amountAccess = amountAccess;
    }
}
