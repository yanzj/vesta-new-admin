package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/6/16.
 * 人员组织关联关系实体
 */
@Entity
@Table(name = "user_agency_map",uniqueConstraints = {@UniqueConstraint(columnNames = {"STAFF_ID","AGENCY_ID"})})
public class UserAgencymapEntity {
    private String mapId;           //主键
    private String staffId;         //用户ID
    private String agencyId;        //机构ID
    private Date modifyTime;        //修改时间
    private String status;          //状态 0删除 1正常
    private String sourceFrom;      //来源  同步OA数据的时候用到 其他并不需要


    @Basic
    @Column(name = "AGENCY_ID",length = 50)
    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    @Id
    @Column(name = "MAP_ID",length = 50)
    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    @Basic
    @Column(name = "MODIFY_TIME")
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Basic
    @Column(name = "STAFF_ID",length = 50)
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
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
    @Column(name = "SOURCE_FROM",length = 10)
    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }
}
