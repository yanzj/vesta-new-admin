package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author WeiYangDong
 * @Description 类APP内容表
 * @data 2018/5/28
 */
@Entity
@Table(name = "similar_app_content")
public class SimilarAppContentEntity {

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
    @Column(name = "position_type", length = 20)
    private String positionType;//轮播图LBT/资讯栏ZXL
    @Basic
    @Column(name = "main_title", length = 150)
    private String mainTitle;//主标题
    @Basic
    @Column(name = "sub_title", length = 150)
    private String subTitle;//副标题
    @Basic
    @Column(name = "info_sign_img")
    private String infoSignImgUrl;//信息标识图
    @Basic
    @Column(name = "link_src")
    private String linkSrc;//链接地址
    @Basic
    @Column(name = "sort_num",length = 2)
    private Integer sortNum;//排序序号
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

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getInfoSignImgUrl() {
        return infoSignImgUrl;
    }

    public void setInfoSignImgUrl(String infoSignImgUrl) {
        this.infoSignImgUrl = infoSignImgUrl;
    }

    public String getLinkSrc() {
        return linkSrc;
    }

    public void setLinkSrc(String linkSrc) {
        this.linkSrc = linkSrc;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
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
