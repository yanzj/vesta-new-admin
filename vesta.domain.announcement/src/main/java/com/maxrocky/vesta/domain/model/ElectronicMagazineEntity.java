package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 电子杂志表
 * Created by WeiYangDong on 2017/9/25.
 */
@Entity
@Table(name = "electronic_magazine")
public class ElectronicMagazineEntity {
    private String id;//主键ID
    @Basic
    @Column(name = "client_id")
    private Integer clientId;//微信AppId
    @Basic
    @Column(name = "client_name", length = 50)
    private String clientName;//客户端名称
    private String magazineName;//杂志名称
    private String mapImgUrl;//杂志导图
    private String coverImgUrl;//封面
    private String catalogImgUrl;//目录
    private String backCoverImgUrl;//封底
    private String columnContentJson;//栏目内容(JSON)

    private Integer isLink;//是否外链(0,否/1,是)
    private String linkSrc;//外链地址
    private Long likeNum;//点赞数
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

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Basic
    @Column(name = "magazine_name",length = 100)
    public String getMagazineName() {
        return magazineName;
    }

    public void setMagazineName(String magazineName) {
        this.magazineName = magazineName;
    }

    @Basic
    @Column(name = "map_img",length = 100)
    public String getMapImgUrl() {
        return mapImgUrl;
    }

    public void setMapImgUrl(String mapImgUrl) {
        this.mapImgUrl = mapImgUrl;
    }

    @Basic
    @Column(name = "cover_img",length = 100)
    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    @Basic
    @Column(name = "catalog_img",length = 100)
    public String getCatalogImgUrl() {
        return catalogImgUrl;
    }

    public void setCatalogImgUrl(String catalogImgUrl) {
        this.catalogImgUrl = catalogImgUrl;
    }

    @Basic
    @Column(name = "back_cover_img",length = 100)
    public String getBackCoverImgUrl() {
        return backCoverImgUrl;
    }

    public void setBackCoverImgUrl(String backCoverImgUrl) {
        this.backCoverImgUrl = backCoverImgUrl;
    }

    @Basic
    @Column(name = "column_content_json",length = 16777216)
    public String getColumnContentJson() {
        return columnContentJson;
    }

    public void setColumnContentJson(String columnContentJson) {
        this.columnContentJson = columnContentJson;
    }

    @Basic
    @Column(name = "is_link",length = 5)
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

    @Basic
    @Column(name = "like_num", length = 20)
    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
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
