package com.maxrocky.vesta.application.AdminDTO;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by chen on 2016/1/26.
 */
public class SellerReceiveDTO {
    private String sellerId;           //商户ID
    private String sellerName;         //商户名
    private String sellerTel;          //商户电话
    private String sellerDirector;     //商户联系人
    private String sellerDirectorTel;  //商户联系人电话
    private String sellerType;         //商户分类
    private String sellerAddress;      //商户地址
    private String range;              //商户距离
    private String logo;               //商户logo
    private Boolean isTop=false;       //是否置顶
    private MultipartFile imgFile;     //logo文件
    private String projectId;          //项目ID

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public Boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerDirector() {
        return sellerDirector;
    }

    public void setSellerDirector(String sellerDirector) {
        this.sellerDirector = sellerDirector;
    }

    public String getSellerDirectorTel() {
        return sellerDirectorTel;
    }

    public void setSellerDirectorTel(String sellerDirectorTel) {
        this.sellerDirectorTel = sellerDirectorTel;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerTel() {
        return sellerTel;
    }

    public void setSellerTel(String sellerTel) {
        this.sellerTel = sellerTel;
    }

    public String getSellerType() {
        return sellerType;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
