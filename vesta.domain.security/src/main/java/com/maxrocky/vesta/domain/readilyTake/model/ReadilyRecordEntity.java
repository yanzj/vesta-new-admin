package com.maxrocky.vesta.domain.readilyTake.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 随手拍整改记录 实体表
 * Created by yuanyn on 2017/7/12.
 */
@Entity
@Table(name = "st_readily_record")
public class ReadilyRecordEntity {
    private String id;  //uuid
    private String businessId;//业务id
    private String rectificationDescription;  //整改描述
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
    @Column(name = "RECTIFICATION_DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 2000)
    public String getRectificationDescription() {
        return rectificationDescription;
    }

    public void setRectificationDescription(String rectificationDescription) {
        this.rectificationDescription = rectificationDescription;
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
