package com.maxrocky.vesta.application.DTO;

/**
 * Created by liudongxin on 2016/1/14.
 * 图片上传/展示
 */
public class PropertyImageDTO {
    private String imageUrl;//图片路径
    private String src;//图片路径

    public PropertyImageDTO() {
        this.imageUrl = "";
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
