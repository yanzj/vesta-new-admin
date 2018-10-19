package com.maxrocky.vesta.domain.projectSideStation.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

/**
 * 工程旁站详情信息
 * Created by Talent on 2016/11/8.
 */
@Entity
@Table(name = "project_side_station_details")
public class ProjectSideStationDetailsEntity {
    private String id;//旁站详情ID
    private String sideStationId;//旁站ID
    private String description;//描述
    private String createById;//创建人ID
    private String createByName;//创建人名称
    private Time recordTime;//旁站记录时间
    private Date createOn;//创建时间
    private String serialNumber;//排序号

    @Id
    @Column(name = "ID", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SIDE_STATION_ID", nullable = true, length = 32)
    public String getSideStationId() {
        return sideStationId;
    }

    public void setSideStationId(String sideStationId) {
        this.sideStationId = sideStationId;
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
    @Column(name = "CREATE_BY_ID", nullable = true, length = 32)
    public String getCreateById() {
        return createById;
    }

    public void setCreateById(String createById) {
        this.createById = createById;
    }

    @Basic
    @Column(name = "CREATE_BY_NAME", nullable = true, length = 50)
    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
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
    @Column(name = "RECORD_TIME", nullable = true)
    public Time getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Time recordTime) {
        this.recordTime = recordTime;
    }
    @Basic
    @Column(name = "SERIAL_NUMBER", nullable = true,length = 50)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}