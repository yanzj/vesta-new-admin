package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 促销信息表
 * Created by WeiYangDong on 2017/5/11.
 */
@Entity
@Table(name = "sales_promotion_info")
public class SalesPromotionInfoEntity {

    private String id;//主键ID
    private String title;//促销标题
    private String content;//促销内容
    private String infoSignImgUrl;//促销信息标识图
    private Integer clientId;//信息所属客户端ID
    private Integer isBanner;//是否作为banner展示(0,否/1,是)
    private Integer isLink;//是否有外链(0,否1,是)
    private String linkSrc;//外链地址
    private Integer orderNum;//信息排序字段
    private Integer infoStatus;//促销信息状态(0,未删除/1,已删除)

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
    @Column(name = "title",length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content",length = 16777216)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "info_sign_img",length = 100)
    public String getInfoSignImgUrl() {
        return infoSignImgUrl;
    }

    public void setInfoSignImgUrl(String infoSignImgUrl) {
        this.infoSignImgUrl = infoSignImgUrl;
    }

    @Basic
    @Column(name = "client_id",length = 5)
    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "isBanner",length = 5)
    public Integer getIsBanner() {
        return isBanner;
    }

    public void setIsBanner(Integer isBanner) {
        this.isBanner = isBanner;
    }

    @Basic
    @Column(name = "order_num",length = 5)
    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Basic
    @Column(name = "info_status",length = 5)
    public Integer getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(Integer infoStatus) {
        this.infoStatus = infoStatus;
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

    @Basic
    @Column(name = "is_link",length = 1)
    public Integer getIsLink() {
        return isLink;
    }

    public void setIsLink(Integer isLink) {
        this.isLink = isLink;
    }

    @Basic
    @Column(name = "link_src",length = 255)
    public String getLinkSrc() {
        return linkSrc;
    }

    public void setLinkSrc(String linkSrc) {
        this.linkSrc = linkSrc;
    }
}
