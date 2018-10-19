package com.maxrocky.vesta.domain.projectSampleCheck.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Magic on 2017/1/3.
 * 样板点评检查指标
 */
@Entity
@Table(name = "project_sample_check_details")
public class ProjectSampleCheckDetailsEntity {
    private String id;//id
    private String sampleCheckId;//样板点评ID
    private String targetId;//指标ID
    private String targetName;//指标名
    private String description;//描述
    private String guide;//指引
    private String state;//状态
    private Date createOn;//创建时间
    private Date modifyDate;//修改时间
    private String flag;//指标整改审核标识 0 乙方整改  1 第三方监理整改   2 甲方整改

    @Id
    @javax.persistence.Column(name = "ID", unique = true ,length = 64)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "SAMPLE_CHECK_ID", length = 200, nullable = true, insertable = true, updatable = true)
    public String getSampleCheckId() {
        return sampleCheckId;
    }

    public void setSampleCheckId(String sampleCheckId) {
        this.sampleCheckId = sampleCheckId;
    }
    @Basic
    @Column(name = "TARGET_ID", length = 200, nullable = true, insertable = true, updatable = true)
    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
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
    @Column(name = "DESCRIPTION", length = 2000, nullable = true, insertable = true, updatable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Basic
    @Column(name = "GUIDE", length = 2000, nullable = true, insertable = true, updatable = true)
    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }
    @Basic
    @Column(name = "STATE", length = 20, nullable = true, insertable = true, updatable = true)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
    @Column(name = "MODIFYDATE", length = 200, nullable = true, insertable = true, updatable = true)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    @Basic
    @Column(name = "FLAG", nullable = true, length = 10)
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
