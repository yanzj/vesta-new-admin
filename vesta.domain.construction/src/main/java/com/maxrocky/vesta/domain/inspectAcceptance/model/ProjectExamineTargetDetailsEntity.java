package com.maxrocky.vesta.domain.inspectAcceptance.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 工程检查验收指标详情
 * Created by jiazefeng on 2016/10/19.
 */
@Entity
@Table(name = "project_examine_target_details")
public class ProjectExamineTargetDetailsEntity {
    private String id;//ID
    private String examineTargetId;//检查验收指标ID
    private String description;//描述
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String rectification;//整改记录标示
    private String acceptance;//验收记录标示
    private Date changeTime;//整改时间
    private String state;//状态

    @Id
    @Column(name = "ID", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "EXAMINE_TARGET_ID", nullable = true, length = 32)
    public String getExamineTargetId() {
        return examineTargetId;
    }

    public void setExamineTargetId(String examineTargetId) {
        this.examineTargetId = examineTargetId;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 500)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "CREATE_BY", nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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
    @Column(name = "RECTIFICATION", nullable = true, length = 10)
    public String getRectification() {
        return rectification;
    }

    public void setRectification(String rectification) {
        this.rectification = rectification;
    }

    @Basic
    @Column(name = "ACCEPTANCE", nullable = true, length = 10)
    public String getAcceptance() {
        return acceptance;
    }

    public void setAcceptance(String acceptance) {
        this.acceptance = acceptance;
    }

    @Basic
    @Column(name = "CHANGE_TIME", nullable = true)
    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }
    @Basic
    @Column(name = "STATE", nullable = true, length = 50)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
