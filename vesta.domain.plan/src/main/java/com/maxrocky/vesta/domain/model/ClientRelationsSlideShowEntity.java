package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 轮播图管理
 * Created by yuanyn on 2018/6/7 0007.
 */

@Entity
@Table(name = "qc_slide_show")
public class ClientRelationsSlideShowEntity {
    private String slideShowId;        //轮播图ID
    private String slideShowTitle;     //轮播图标题
    private String slideShowImgUrl;    //轮播图插图
    private String slideShowContent;   //轮播图内容
    private String slideShowSource;    //来源
    private String slideShowType;      //type值  1： 图文  2： 视频
    private String videoUrl;      //视频链接
    private String outUrl;      //H5外部链接

    private String state;        // 状态  0 删除  1 正常
    private String isSlideShow;  // 是否轮播  0 不轮播  1 轮播
    private String createName;    //创建人
    private String createBy;    //创建人id
    private Date createDate;    //创建时间
    private String modifyName;    //修改人
    private String modifyBy;    //修改人id
    private Date modifyDate;    //修改时间
    private Date releaseDate;   //发布时间

    @Id
    @Column(name = "SLIDE_SHOW_ID",length = 32)
    public String getSlideShowId() {
        return slideShowId;
    }

    public void setSlideShowId(String slideShowId) {
        this.slideShowId = slideShowId;
    }
    @Basic
    @Column(name = "SLIDE_SHOW_TITLE",length = 200)
    public String getSlideShowTitle() {
        return slideShowTitle;
    }

    public void setSlideShowTitle(String slideShowTitle) {
        this.slideShowTitle = slideShowTitle;
    }
    @Basic
    @Column(name = "SLIDE_SHOW_IMGURL",length = 200)
    public String getSlideShowImgUrl() {
        return slideShowImgUrl;
    }

    public void setSlideShowImgUrl(String slideShowImgUrl) {
        this.slideShowImgUrl = slideShowImgUrl;
    }
    @Basic
    @Column(name = "SLIDE_SHOW_CONTENT",length = 5000)
    public String getSlideShowContent() {
        return slideShowContent;
    }

    public void setSlideShowContent(String slideShowContent) {
        this.slideShowContent = slideShowContent;
    }
    @Basic
    @Column(name = "SLIDE_SHOW_SOURCE",length = 100)
    public String getSlideShowSource() {
        return slideShowSource;
    }

    public void setSlideShowSource(String slideShowSource) {
        this.slideShowSource = slideShowSource;
    }

    @Basic
    @Column(name = "SLIDE_SHOW_TYPE",length = 50)
    public String getSlideShowType() {
        return slideShowType;
    }

    public void setSlideShowType(String slideShowType) {
        this.slideShowType = slideShowType;
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
    @Column(name = "IS_SLIDE_SHOW",length = 50)
    public String getIsSlideShow() {
        return isSlideShow;
    }

    public void setIsSlideShow(String isSlideShow) {
        this.isSlideShow = isSlideShow;
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

}
