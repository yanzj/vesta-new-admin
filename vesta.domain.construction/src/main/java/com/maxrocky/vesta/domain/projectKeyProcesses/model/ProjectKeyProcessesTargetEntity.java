package com.maxrocky.vesta.domain.projectKeyProcesses.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 关键工序指标实体
 * Created by Talent on 2016/11/22.
 */
@Entity
@Table(name = "project_key_processes_target")
public class ProjectKeyProcessesTargetEntity {
    private String id;//ID
    private String processId;//工序ID
    private String targetId;//检查指标ID
    private String targetName;//检查指标名称
    private String qualifiedState;//合格(合格、不合格)
    private String state;//状态
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间
    private String flag;//指标整改审核标识 0 乙方整改  1 第三方监理整改   2 甲方整改

    @Id
    @Column(name = "ID", nullable = false, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PROCESS_ID", nullable = false, length = 50)
    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    @Basic
    @Column(name = "TARGET_ID", nullable = false, length = 50)
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
    @Column(name = "QUALIFIED_STATE", nullable = true, length = 16)
    public String getQualifiedState() {
        return qualifiedState;
    }

    public void setQualifiedState(String qualifiedState) {
        this.qualifiedState = qualifiedState;
    }

    @Basic
    @Column(name = "STATE", nullable = true, length = 16)
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

    @Basic
    @Column(name = "FLAG", nullable = true, length = 10)
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
