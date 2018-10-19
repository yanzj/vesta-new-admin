package com.maxrocky.vesta.application.inspectionPlan.DTO;

/**
 * Created by Magic on 2017/6/6.
 */
public class ImageDTO {
    private String id;//uuid
    private String businessId;//业务id
    private String imageUrl;//图片链接
    private String type;//类型  1.随手拍问题图片  2.随手拍整改图片   3.检查计划签字图片 4.检查单位签字图片 5.检查详情的问题图片
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
