package com.maxrocky.vesta.domain.projectSampleCheck.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Magic on 2017/1/3.
 * 样板点评指标整改信息表
 */
@Entity
@Table(name = "project_sample_check_changed")
public class ProjectSampleCheckChangedEntity {
    private String id;//ID
    private String checkDetailsId;//指标ID
    private String description;//描述
    private Date changeTime;//整改时间
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String type;//3 乙方整改  2 第三方监理整改   1 甲方整改
    private String state;//指标状态
    private String serialNumber;//排序号
    @Id
    @javax.persistence.Column(name = "ID", unique = true ,length = 64)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "CHECK_DETAILS_ID", length = 200, nullable = true, insertable = true, updatable = true)
    public String getCheckDetailsId() {
        return checkDetailsId;
    }

    public void setCheckDetailsId(String checkDetailsId) {
        this.checkDetailsId = checkDetailsId;
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
    @Column(name = "CHANGETIME", length = 200, nullable = true, insertable = true, updatable = true)
    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }
    @Basic
    @Column(name = "CREATEBY", length = 200, nullable = true, insertable = true, updatable = true)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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
    @Column(name = "TYPE", length = 200, nullable = true, insertable = true, updatable = true)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Basic
    @Column(name = "STATE", length = 200, nullable = true, insertable = true, updatable = true)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "SERIALNUMBER", length = 200, nullable = true, insertable = true, updatable = true)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
