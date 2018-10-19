package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chen on 2016/9/7.
 * 供应商组织机构关联关系实体
 */
@Entity
@Table(name = "supplier_agency_map",uniqueConstraints = {@UniqueConstraint(columnNames = {"SUPPLIER_ID","AGENCY_ID"})})
public class SupplierAgencyMapEntity {
    private String mapId;           //主键
    private String agencyId;        //机构ID
    private String supplierId;      //供应商ID
    private String status;          //状态
    private Date modifyTime;        //修改时间

    @Basic
    @Column(name = "AGENCY_ID",length = 50,nullable = false)
    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    @Id
    @Column(name = "MAP_ID",unique = true,nullable = false,length = 50)
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
    @Column(name = "MAP_STATUS",length = 3)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "SUPPLIER_ID",length = 50,nullable = false)
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
}
