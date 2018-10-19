package com.maxrocky.vesta.application.DTO;

/**
 * Created by JillChen on 2016/2/18.
 */
public class MenuDTO {
//    {url:'/property/notice/list',imgUrl:'/images/index/icon_01.png',name:'物业公告'}

    private String url;
    private String imgUrl;
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
