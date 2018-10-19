package com.maxrocky.vesta.domain.projectMaterial.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 材料验收验收指标实体表
 * Created by Magic on 2016/11/24.
 */
@Entity
@Table(name = "project_material_details")
public class ProjectMaterialDetailsEntity {

    private String id;//id
    private String materialId;//材料验收ID
    private String targetId;//指标ID
    private String targetName;//指标名
    private String description;//描述
    private String guide;//指引
    private String isQualified;//合格
    private String detailsUrl;//指标url
    private Date createOn;//创建时间
    private Date modifyDate;//修改时间
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
    @Column(name = "TARGET_ID", length = 32, nullable = true, insertable = true, updatable = true)
    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
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
    @Column(name = "IS_QUALIFIED", length = 32, nullable = true, insertable = true, updatable = true)
    public String getIsQualified() {
        return isQualified;
    }

    public void setIsQualified(String isQualified) {
        this.isQualified = isQualified;
    }
    @Basic
    @Column(name = "TARGET_NAME", length = 200, nullable = true, insertable = true, updatable = true)
    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
    @Basic
    @Column(name = "DETAILSURL", length = 2000, nullable = true, insertable = true, updatable = true)
    public String getDetailsUrl() {
        return detailsUrl;
    }

    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
    }
    @Basic
    @Column(name = "CREATEON", length = 200, nullable = true, insertable = true, updatable = true)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
    @Basic
    @Column(name = "MODIFY_DATE", length = 200, nullable = true, insertable = true, updatable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    @Basic
    @Column(name = "GUIDE", length = 200, nullable = true, insertable = true, updatable = true)
    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }
}
