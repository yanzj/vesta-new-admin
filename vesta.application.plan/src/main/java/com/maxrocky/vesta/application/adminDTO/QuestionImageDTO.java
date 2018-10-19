package com.maxrocky.vesta.application.adminDTO;

/**
 * Created by mql on 2016/5/17.
 * 问题图片DTO
 */
public class QuestionImageDTO {

    private String imageId;           //主键
    private String caseId;            //检查项ID
    private String imageUrl;          //图片地址
    private String image;               //图片流

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
