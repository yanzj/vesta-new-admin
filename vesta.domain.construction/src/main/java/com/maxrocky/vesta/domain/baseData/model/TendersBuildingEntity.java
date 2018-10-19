package com.maxrocky.vesta.domain.baseData.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/10/18.
 * 工程标段楼栋关联关系实体
 */

@Entity
@Table(name = "project_tenders_building",uniqueConstraints = {@UniqueConstraint(columnNames = {"TENDERS_ID","BUILDING_ID"})})
public class TendersBuildingEntity {
    private long id;             //主键
    private String tendersId;    //标段ID
    private String buildingId;   //楼栋ID
    private String tenderStatus; //状态
    private Date modifyTime;     //修改时间

    @Id
    @Column(name = "ID",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TENDERS_ID",nullable = false,length = 50)
    public String getTendersId() {
        return tendersId;
    }

    public void setTendersId(String tendersId) {
        this.tendersId = tendersId;
    }

    @Basic
    @Column(name = "BUILDING_ID",nullable = false,length = 50)
    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    @Basic
    @Column(name = "TENDER_STATUS",length = 2)
    public String getTenderStatus() {
        return tenderStatus;
    }

    public void setTenderStatus(String tenderStatus) {
        this.tenderStatus = tenderStatus;
    }

    @Basic
    @Column(name = "MODIFY_TIME")
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
