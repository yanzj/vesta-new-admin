package com.maxrocky.vesta.application.admin.dto;

/**
 * Created by liuwei on 2016/1/15.
 */
public class HomeActivityDto {
        private String url; //活动url
        private String imgUrl; //图片url
        private String desc; //描述

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

