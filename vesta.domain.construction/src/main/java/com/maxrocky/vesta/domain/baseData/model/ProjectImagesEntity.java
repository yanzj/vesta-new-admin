package com.maxrocky.vesta.domain.baseData.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 工程图片
 * Created by jiazefeng on 2016/10/20.
 */
@Entity
@Table(name = "project_images")
public class ProjectImagesEntity {
    private String id;
    private String businessId;//业务id
    private String url;//图片地址
    private String type;//类型 1:日常巡检；2检查验收；3：关键工序；4：样板点评；5：材料验收；6：旁站；7：户型图
    private String state;//状态 0:不可用；1：可用
    private String qualifiedState;//合格 不合格
    private Date createOn;//创建时间
    private Date modifyOn;//修改时间
    @Id
    @Column(name = "ID", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "BUSINESS_ID", nullable = true, length = 32)
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
    @Column(name = "TYPE", nullable = true, length = 10)
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
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
    @Basic
    @Column(name = "QUALIFIED_STATE", nullable = true, length = 32)
    public String getQualifiedState() {
        return qualifiedState;
    }

    public void setQualifiedState(String qualifiedState) {
        this.qualifiedState = qualifiedState;
    }
}
