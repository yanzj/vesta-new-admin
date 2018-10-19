package com.maxrocky.vesta.application.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author WeiYangDong
 * @Description 类APP图片DTO
 * @data 2018/5/28
 */
public class SimilarAppPictureDTO {

    private String menuId;
    private List<Integer> clientIdList;//客户端ID集合

    private String id;
    private Integer clientId;//微信AppId
    private String clientName;//客户端名称
    private Integer isLink;//是否跳转详情(0,否;1,是)
    private String linkSrc;//链接地址
    private String infoSignImgUrl;//信息标识图
    private MultipartFile infoSignImgFile;//信息标识图上传文件
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

    public List<Integer> getClientIdList() {
        return clientIdList;
    }

    public void setClientIdList(List<Integer> clientIdList) {
        this.clientIdList = clientIdList;
    }

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

    public MultipartFile getInfoSignImgFile() {
        return infoSignImgFile;
    }

    public void setInfoSignImgFile(MultipartFile infoSignImgFile) {
        this.infoSignImgFile = infoSignImgFile;
    }
}
