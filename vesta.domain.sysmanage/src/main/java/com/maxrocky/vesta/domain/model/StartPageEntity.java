package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sunmei on 2016/2/29.
 */
@Entity
@Table(name = "start_page")
public class StartPageEntity {
    private String id;
    private String imgUrl;
    private String publishBy;//发布人
    private Date publishdate;//发布时间

    @Id
    @Column(name = "id",nullable = false,insertable = true,updatable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "Img_Url",length = 300)
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    @Basic
    @Column(name = "publishBy",length = 300)
    public String getPublishBy() {
        return publishBy;
    }

    public void setPublishBy(String publishBy) {
        this.publishBy = publishBy;
    }
    @Basic
    @Column(name = "Publishdate",length = 300)
    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }
}
