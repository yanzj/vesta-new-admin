package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/4/22.
 * 旁站记录实体
 */

@Entity
@Table(name = "stand_recode")
public class StandRecodeEntity {
    private String id;               //主键
    private String standId;          //旁站ID
    private String imageUrl;         //图片
    private String recodeDesc;       //旁站说明
    private Date createDate;         //创建时间
    private String createBy;         //创建人

    @Basic
    @Column(name = "CREATE_BY",length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Id
    @Column(name = "ID",length = 50,nullable = false,unique = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "IMAGE_URL",length = 200)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Basic
    @Column(name = "RECODE_DESC",length = 500)
    public String getRecodeDesc() {
        return recodeDesc;
    }

    public void setRecodeDesc(String recodeDesc) {
        this.recodeDesc = recodeDesc;
    }

    @Basic
    @Column(name = "STAND_ID",length = 50)
    public String getStandId() {
        return standId;
    }

    public void setStandId(String standId) {
        this.standId = standId;
    }
}
