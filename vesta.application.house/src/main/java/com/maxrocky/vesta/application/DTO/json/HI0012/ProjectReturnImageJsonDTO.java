package com.maxrocky.vesta.application.DTO.json.HI0012;

/**
 * Created by Magic on 2016/6/2.
 */
public class ProjectReturnImageJsonDTO {
    private String imageId;//图片Id
    private String imageUrl;//图片url
    private String image; //图片流
    private String caseId;   //检查项ID
    public ProjectReturnImageJsonDTO(){
        this.imageId="";
        this.imageUrl="";
        this.image="";
        this.caseId="";
    }
    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
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

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }
}
