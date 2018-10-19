package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author WeiYangDong
 * @Description 类APP图片表
 * @data 2018/5/28
 */
@Entity
@Table(name = "similar_app_picture")
public class SimilarAppPictureEntity {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    @Basic
    @Column(name = "client_id")
    private Integer clientId;//微信AppId

    @Basic
    @Column(name = "client_name", length = 50)
    private String clientName;//客户端名称

    @Basic
    @Column(name = "is_link",length = 1)
    private Integer isLink;//是否跳转详情(0,否;1,是)

    @Basic
    @Column(name = "link_src",length = 255)
    private String linkSrc;//链接地址

    @Basic
    @Column(name = "info_sign_img",length = 255)
    private String infoSignImgUrl;//信息标识图

    @Basic
    @Column(name = "create_by", nullable = true, length = 50)
    private String createBy;    //创建人

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    private Date createOn;      //创建时间

    @Basic
    @Column(name = "modify_by", nullable = true, length = 50)
    private String modifyBy;    //修改人

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_on", nullable = true, length = 50)
    private Date modifyOn;      //修改时间

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

    public String getInfoSignImgUrl() {
        return infoSignImgUrl;
    }

    public void setInfoSignImgUrl(String infoSignImgUrl) {
        this.infoSignImgUrl = infoSignImgUrl;
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
