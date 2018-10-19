package com.maxrocky.vesta.application.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * 电子杂志数据封装类
 * Created by WeiYangDong on 2017/9/25.
 */
public class ElectronicMagazineDTO {

    private String menuId;//菜单ID

    private String id;//主键ID
    private Integer clientId;//微信AppId
    private String clientName;//客户端名称
    private String magazineName;//杂志名称
    private String mapImgUrl;//杂志导图
    private MultipartFile mapImgFile;//杂志导图上传文件
    private String coverImgUrl;//封面
    private MultipartFile coverImgFile;//封面上传文件
    private String catalogImgUrl;//目录
    private MultipartFile catalogImgFile;//目录上传文件
    private String backCoverImgUrl;//封底
    private MultipartFile backCoverImgFile;//封底上传文件

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

    public String getMagazineName() {
        return magazineName;
    }

    public void setMagazineName(String magazineName) {
        this.magazineName = magazineName;
    }

    public String getMapImgUrl() {
        return mapImgUrl;
    }

    public void setMapImgUrl(String mapImgUrl) {
        this.mapImgUrl = mapImgUrl;
    }

    public MultipartFile getMapImgFile() {
        return mapImgFile;
    }

    public void setMapImgFile(MultipartFile mapImgFile) {
        this.mapImgFile = mapImgFile;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public MultipartFile getCoverImgFile() {
        return coverImgFile;
    }

    public void setCoverImgFile(MultipartFile coverImgFile) {
        this.coverImgFile = coverImgFile;
    }

    public String getCatalogImgUrl() {
        return catalogImgUrl;
    }

    public void setCatalogImgUrl(String catalogImgUrl) {
        this.catalogImgUrl = catalogImgUrl;
    }

    public MultipartFile getCatalogImgFile() {
        return catalogImgFile;
    }

    public void setCatalogImgFile(MultipartFile catalogImgFile) {
        this.catalogImgFile = catalogImgFile;
    }

    public String getBackCoverImgUrl() {
        return backCoverImgUrl;
    }

    public void setBackCoverImgUrl(String backCoverImgUrl) {
        this.backCoverImgUrl = backCoverImgUrl;
    }

    public MultipartFile getBackCoverImgFile() {
        return backCoverImgFile;
    }

    public void setBackCoverImgFile(MultipartFile backCoverImgFile) {
        this.backCoverImgFile = backCoverImgFile;
    }

    public String getColumnContentJson() {
        return columnContentJson;
    }

    public void setColumnContentJson(String columnContentJson) {
        this.columnContentJson = columnContentJson;
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

    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
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
}
