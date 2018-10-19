package com.maxrocky.vesta.application.DTO;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by luxinxin on 2016/8/31.
 * 导航菜单数据传输封装类
 */
public class NavigationMenuDTO {
    private String navigationId;//导航菜单id
    private String navigationName;//导航菜单名称
    private String navigationUrl;//导航菜单访问路径
    private MultipartFile navigationImg;//导航菜单图片
    private String imgUrl;//图片url
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public MultipartFile getNavigationImg() {
        return navigationImg;
    }

    public void setNavigationImg(MultipartFile navigationImg) {
        this.navigationImg = navigationImg;
    }

    public String getNavigationId() {
        return navigationId;
    }

    public void setNavigationId(String navigationId) {
        this.navigationId = navigationId;
    }

    public String getNavigationName() {
        return navigationName;
    }

    public void setNavigationName(String navigationName) {
        this.navigationName = navigationName;
    }

    public String getNavigationUrl() {
        return navigationUrl;
    }

    public void setNavigationUrl(String navigationUrl) {
        this.navigationUrl = navigationUrl;
    }


}
