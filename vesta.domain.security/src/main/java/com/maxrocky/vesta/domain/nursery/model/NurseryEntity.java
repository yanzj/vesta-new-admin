package com.maxrocky.vesta.domain.nursery.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 苗木实体
 * Created by yuanyn on 2017/9/29.
 */
@Entity
@Table(name = "st_nursery")
public class NurseryEntity {
    private String id; //uuid
    private String nurseryName;//苗木名称
    private String num; //数量
    private String height; //高度
    private String remark; //备注
    private Date modifyDate; //修改时间
    private String imageUrl; //图片路径

    @Id
    @Column(name = "ID", length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NURSERY_NAME", length = 200, nullable = false)
    public String getNurseryName() {
        return nurseryName;
    }

    public void setNurseryName(String nurseryName) {
        this.nurseryName = nurseryName;
    }
    @Basic
    @Column(name = "NURSERY_NUM", length = 50, nullable = false)
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
    @Basic
    @Column(name = "NURSERY_HEIGHT", length = 50, nullable = false)
    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    @Basic
    @Column(name = "REMARK", length = 500, nullable = false)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Basic
    @Column(name = "MODIFY_DATE",length = 50)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    @Basic
    @Column(name = "IMAGE_URL", length = 200, nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
