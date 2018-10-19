package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/1/20.
 * 我想说图片实体
 */

@Entity
@Table(name="isay_image")
public class IsayImageEntity {
    private String id;              //图片ID
    private String bussinessId;     //关联ID
    private String url;             //图片地址
    private Date crtTime;           //创建时间

    @Basic
    @Column(name="BUSSINESS_ID",length = 32)
    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
    }

    @Basic
    @Column(name="CREATE_TIME",length = 20)
    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    @Id
    @Column(name="ID",nullable = false,unique = true,length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "URL",nullable = false,length = 50)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
