package com.maxrocky.vesta.domain.dailyPatrolInspection.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 日常巡检实体详情表
 * Created by Magic on 2016/10/17.
 */
@Entity
@Table(name = "project_inspection_details")
public class DailyPatrolInspectionDetailsEntity {
    private String id;//ID
    private String inspectionId;//巡检_ID  关联 project_inspection ——id
    private String description;//描述
    private String createBy;//创建人
    private String createName;//创建人姓名
    private Date createOn;//创建时间
    private String type;//0 乙方整改  1 第三方监理整改   2 甲方整改
    private String appId;//appId  校验唯一性 防止重复
    private int frequency;//次数
    private String detailsState;//记录状态


    @Id
    @Column(name = "ID", length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "INSPECTION_ID", length = 32, nullable = true, insertable = true, updatable = true)
    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
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
    @Column(name = "CREATE_ON", length = 50, nullable = true, insertable = true, updatable = true)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
    @Basic
    @Column(name = "CREATE_NAME", length = 100, nullable = true, insertable = true, updatable = true)
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
    @Basic
    @Column(name = "TYPE", length = 10, nullable = true, insertable = true, updatable = true)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Basic
    @javax.persistence.Column(name = "APP_ID", unique = true ,length = 100)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
    @Basic
    @Column(name = "FREQUENCY", length = 100, nullable = true, insertable = true, updatable = true)
    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    @Basic
    @Column(name = "DETAILS_STATE", length = 100, nullable = true, insertable = true, updatable = true)
    public String getDetailsState() {
        return detailsState;
    }

    public void setDetailsState(String detailsState) {
        this.detailsState = detailsState;
    }
}
