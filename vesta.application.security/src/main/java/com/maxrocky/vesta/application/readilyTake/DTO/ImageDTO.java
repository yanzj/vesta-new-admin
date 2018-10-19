package com.maxrocky.vesta.application.readilyTake.DTO;

/**
 * Created by yuanyn on 2017/7/16.
 */
public class ImageDTO {
    private String id;//uuid
    private String businessId;//业务id
    private String imageUrl;//图片链接
    private String type;//类型
    public ImageDTO(){
        this.type="";
        this.id="";
        this.businessId="";
        this.imageUrl="";
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
