package com.maxrocky.vesta.domain.inspectAcceptance.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 工程检查验收指标
 * Created by jiazefeng on 2016/10/19.
 */
@Entity
@Table(name = "project_examine_target")
public class ProjectExamineTargetEntity {
    private String id;//ID
    private String examineId;//检查验收ID
    private String targetId;//检查指标ID
    private String targetName;//检查指标名称
    private String isQualified;//合格
    private String state;//状态
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
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
    @Column(name = "EXAMINE_ID", nullable = true, length = 32)
    public String getExamineId() {
        return examineId;
    }

    public void setExamineId(String examineId) {
        this.examineId = examineId;
    }
    @Basic
    @Column(name = "TARGET_ID", nullable = true, length = 32)
    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
    @Basic
    @Column(name = "TARGET_NAME", nullable = true, length = 50)
    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    @Basic
    @Column(name = "IS_QUALIFIED_ID", nullable = true, length = 10)
    public String getIsQualified() {
        return isQualified;
    }

    public void setIsQualified(String isQualified) {
        this.isQualified = isQualified;
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
    @Column(name = "MODIFY_BY", nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }
    @Basic
    @Column(name = "MODIFY_ON", nullable = true)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
}
