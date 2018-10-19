package com.maxrocky.vesta.domain.baseData.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/11/4.
 * 楼栋与责任单位关系实体
 */
@Entity
@Table(name = "building_supplier",uniqueConstraints = {@UniqueConstraint(columnNames = {"BUILD_ID","AGENCY_ID"})})
public class BuildingSupplierEntity {
    private long autoId;         //主键
    private String buildId;      //楼栋ID
    private String agencyId;     //责任单位ID
    private String status;       //状态
    private Date modifyTime;     //修改时间

    @Id
    @Column(name = "ID",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getAutoId() {
        return autoId;
    }

    public void setAutoId(long autoId) {
        this.autoId = autoId;
    }

    @Basic
    @Column(name = "BUILD_ID",length = 50)
    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    @Basic
    @Column(name = "AGENCY_ID",length = 50)
    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    @Basic
    @Column(name = "STATUS",length = 2)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
