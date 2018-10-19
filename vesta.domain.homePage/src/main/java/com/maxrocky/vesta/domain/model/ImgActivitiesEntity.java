package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by sunmei on 2016/2/19.
 */
@Entity
@Table(name = "img_activities")
public class ImgActivitiesEntity {
    private String id;
    private String imgId;
    private String imgUrl;

    @Id
    @Column(name = "id",nullable = false,insertable = true,updatable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Basic
    @Column(name = "img_url",length = 300)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Basic
    @Column(name = "img_id",length = 300)
    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }
}
