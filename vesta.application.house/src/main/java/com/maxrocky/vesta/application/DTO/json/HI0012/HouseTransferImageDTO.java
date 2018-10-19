package com.maxrocky.vesta.application.DTO.json.HI0012;

/**
 * Created by zhangzhaowen on 2016/9/27.10:07
 * Describe:
 */
public class HouseTransferImageDTO {
    private String imageId;           //主键
    private String caseId;            //检查项ID
    private String imageUrl;          //图片地址
    private String image;             //图片流

    public HouseTransferImageDTO() {
        this.imageId = "";
        this.caseId = "";
        this.imageUrl = "";
        this.image = "";
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
