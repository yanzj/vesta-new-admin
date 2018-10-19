package com.maxrocky.vesta.domain.projectKeyProcesses.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 关键工序指标详情实体
 * Created by Talent on 2016/11/22.
 */
@Entity
@Table(name = "project_key_processes_target_details")
public class ProjectKeyProcessesTargetDetailsEntity {
    private String id;//ID
    private String processTargetId;//检查验收指标ID
    private String description;//描述
    private Date changeTime;//整改时间
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String type;//0 乙方整改  1 第三方监理整改   2 甲方整改
    private String state;//指标状态
    private String serialNumber;//排序号

    @Id
    @Column(name = "ID", nullable = false, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PROCESS_TARGET_ID", nullable = false, length = 50)
    public String getProcessTargetId() {
        return processTargetId;
    }

    public void setProcessTargetId(String processTargetId) {
        this.processTargetId = processTargetId;
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
    @Column(name = "CHANGE_TIME", nullable = true)
    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
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
    @Column(name = "TYPE", nullable = true, length = 10)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "STATE", nullable = true, length = 50)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "SERIAL_NUMBER", nullable = true, length = 50)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
