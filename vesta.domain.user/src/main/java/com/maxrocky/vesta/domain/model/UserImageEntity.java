package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by Tom on 2016/1/21 17:46.
 * Describe:用户图片实体类
 */
@Entity
@Table(name = "user_image")
public class UserImageEntity {

    /* 图片类型 */
    public final static String TYPE_FEEDBACK = "FEEDBACK";//意见反馈

    private String id;//主键
    private String name;//名称
    private String url;//地址
    private String jumpUrl;//跳转地址
    private String businessId;//业务Id
    private String type;//类型

    @Id
    @Column(name = "ID",nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "URL", length = 500)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "JUMP_URL", length = 500)
    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    @Basic
    @Column(name = "BUSINESS_ID", length = 32)
    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    @Basic
    @Column(name = "TYPE", length = 10)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
