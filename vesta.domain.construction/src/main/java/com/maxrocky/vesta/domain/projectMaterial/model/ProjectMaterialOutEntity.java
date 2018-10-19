package com.maxrocky.vesta.domain.projectMaterial.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 材料验收退场纪录实体表
 * Created by Magic on 2016/11/24.
 */

@Entity
@Table(name = "project_material_out")
public class ProjectMaterialOutEntity {
    private String id;
    private String materialId;//材料验收ID
    private Date outTime;//退场时间
    private String description;//退场描述
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间
    private String appId;//APP传入id唯一校验防止重复

    @Id
    @Column(name = "ID", length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "MATERIAL_ID", length = 32, nullable = true, insertable = true, updatable = true)
    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }
    @Basic
    @Column(name = "OUT_TIME", length = 32, nullable = true, insertable = true, updatable = true)
    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }
    @Basic
    @Column(name = "DESCRIPTION", length = 500, nullable = true, insertable = true, updatable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Basic
    @Column(name = "CREATE_BY", length = 32, nullable = true, insertable = true, updatable = true)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    @Basic
    @Column(name = "CREATE_ON", length = 32, nullable = true, insertable = true, updatable = true)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
    @Basic
    @Column(name = "MODIFY_BY", length = 32, nullable = true, insertable = true, updatable = true)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }
    @Basic
    @Column(name = "MODIFY_ON", length = 32, nullable = true, insertable = true, updatable = true)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
    @Basic
    @Column(name = "APP_ID", length = 100, nullable = true, insertable = true, updatable = true)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
