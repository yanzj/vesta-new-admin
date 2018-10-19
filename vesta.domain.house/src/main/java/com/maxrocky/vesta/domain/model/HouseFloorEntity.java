package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by langmafeng on 2016/5/7/11:16.
 * 楼层实体
 */

@Entity
@Table(name = "house_floor")
public class HouseFloorEntity {
    private String id;
    private String floorCode;//预售楼层编码
    private String recordFloorcode;//备案楼层编码
    private String floorName;//预售楼层名称
    private String recordFloorName;//备案楼层名称
    private String floorNum;//预售楼层号
    private String recordFloorNum;//备案楼层号
    private String propertyType;//房产类型
    private String unitId;//关联单元ID
    private String unitCode;//关联预售单元编码
    private String recordUnitCode;//关联备案单元编码
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间
    private String stateCode;//状态0:有效 1:失效

    @Id
    @Column(name = "ID",nullable = false, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "FLOOR_CODE", length = 50)
    public String getFloorCode() {
        return floorCode;
    }

    public void setFloorCode(String floorCode) {
        this.floorCode = floorCode;
    }
    @Basic
    @Column(name = "FLOOR_NAME", length = 50)
    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    @Basic
    @Column(name = "UNIT_ID", length = 50)
    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }
    @Basic
    @Column(name = "UNIT_CODE", length = 50)
    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    @Basic
    @Column(name = "CREATE_BY",nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_ON",nullable = true, length = 50)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MODIFY_BY",nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "MODIFY_ON",nullable = true)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
    @Basic
    @Column(name = "RECORD_FLOORCODE", length = 50)
    public String getRecordFloorcode() {
        return recordFloorcode;
    }

    public void setRecordFloorcode(String recordFloorcode) {
        this.recordFloorcode = recordFloorcode;
    }
    @Basic
    @Column(name = "RECORD_FLOORNAME", length = 100)
    public String getRecordFloorName() {
        return recordFloorName;
    }

    public void setRecordFloorName(String recordFloorName) {
        this.recordFloorName = recordFloorName;
    }
    @Basic
    @Column(name = "FLOORNUM", length = 50)
    public String getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
    }
    @Basic
    @Column(name = "RECORD_FLOORNUM", length = 50)
    public String getRecordFloorNum() {
        return recordFloorNum;
    }

    public void setRecordFloorNum(String recordFloorNum) {
        this.recordFloorNum = recordFloorNum;
    }
    @Basic
    @Column(name = "PROPERTY_TYPE", length = 50)
    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
    @Basic
    @Column(name = "RECORD_UNITCODE", length = 50)
    public String getRecordUnitCode() {
        return recordUnitCode;
    }

    public void setRecordUnitCode(String recordUnitCode) {
        this.recordUnitCode = recordUnitCode;
    }
    @Basic
    @Column(name = "STATECODE", length = 50)
    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}
