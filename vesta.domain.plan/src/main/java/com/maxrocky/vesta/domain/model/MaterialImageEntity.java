package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/5/21.
 */
@Entity
@Table(name = "material_image")
public class MaterialImageEntity {
    private String id;
    private String bussinessId;
    private String url;
    private Date crtTime;

    @Basic
    @Column(name = "BUSSINESS_ID",length = 60)
    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
    }

    @Basic
    @Column(name = "CREATE_TIME")
    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    @Id
    @Column(name = "ID",length = 60,unique = true,nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "URL",length = 300)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
