package com.maxrocky.vesta.application.DTO;

/**
 * 图片dto
 * Created by Magic on 2018/5/21.
 */
public class PropertyImageDTO {
    private String imageUrl;//图片url
    private String status;//状态 0 删除  1.正常
    private String imageType;//属性  // 31 保修签名  32 保修签字


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}
