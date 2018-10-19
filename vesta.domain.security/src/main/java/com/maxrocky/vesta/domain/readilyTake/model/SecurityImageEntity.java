package com.maxrocky.vesta.domain.readilyTake.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 安全app图片表
 * Created by magic on 2017/6/5.
 */
@Entity
@Table(name = "st_security_image")
public class SecurityImageEntity {
    private String id;//uuid
    private String businessId;//业务id
    private String url;//图片链接
    private String type;//类型 1.随手拍问题图片  2.随手拍整改图片 3.检查计划签字图片 4.检查单位签字图片 5.检查详情的问题图片
    private String state;//状态
    private Date createOn;//创建时间
    private Date ModifyOn;//修改时间
    @Id
    @Column(name = "ID", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "BUSINESS_ID", nullable = true, length = 200)
    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
    @Basic
    @Column(name = "IMAGE_URL", nullable = true, length = 500)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @Basic
    @Column(name = "IMAGE_TYPE", nullable = true, length = 10)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Basic
    @Column(name = "STATE", nullable = true, length = 10)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "CREATE_ON", nullable = true)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
    @Basic
    @Column(name = "MODIFY_ON", nullable = true)
    public Date getModifyOn() {
        return ModifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        ModifyOn = modifyOn;
    }
}
