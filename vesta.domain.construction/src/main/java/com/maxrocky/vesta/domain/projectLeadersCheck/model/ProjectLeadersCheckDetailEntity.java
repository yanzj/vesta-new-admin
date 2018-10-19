package com.maxrocky.vesta.domain.projectLeadersCheck.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 领导检查详情
 * Created by Talent on 2017/1/16.
 */
@Entity
@Table(name = "project_leaders_check_detail")
public class ProjectLeadersCheckDetailEntity {
    private String id;//详情ID
    private String checkId;//检查ID
    private String description;//描述
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String type;//0：项目经理
    private String serialNumber;//排序号
    @Id
    @Column(name = "ID", nullable = false, length = 64)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "CHECK_ID", nullable = false, length = 64)
    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }
    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 2000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Basic
    @Column(name = "CREATE_BY", nullable = true, length = 64)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    @Basic
    @Column(name = "CREATE_DATE", nullable = true)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
    @Basic
    @Column(name = "TYPE", nullable = true, length = 32)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Basic
    @Column(name = "SERIAL_NUMBER", nullable = true, length = 32)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
