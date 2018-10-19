package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by luxinxin on 2016/8/31.
 */
@Entity
@Table(name = "navigation_menu")
public class NavigationMenuEntity {
    private String navigationId;//导航菜单id
    private String navigationName;//导航菜单名称
    private String navigationUrl;//导航菜单访问路径
    private String navigationImg;//导航菜单图片

    @Id
    @Column(name = "navigation_menuID", nullable = false, insertable = true, updatable = true, length = 32)
    public String getNavigationId() {
        return navigationId;
    }

    public void setNavigationId(String navigationId) {
        this.navigationId = navigationId;
    }

    @Basic
    @Column(name = "navigation_name", length = 100)
    public String getNavigationName() {
        return navigationName;
    }

    public void setNavigationName(String navigationName) {
        this.navigationName = navigationName;
    }

    @Basic
    @Column(name = "navigation_url", length = 100)
    public String getNavigationUrl() {
        return navigationUrl;
    }

    public void setNavigationUrl(String navigationUrl) {
        this.navigationUrl = navigationUrl;
    }

    @Basic
    @Column(name = "navigation_img", length = 100)
    public String getNavigationImg() {
        return navigationImg;
    }

    public void setNavigationImg(String navigationImg) {
        this.navigationImg = navigationImg;
    }
}
