package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tom on 2016/1/14 20:08.
 * Describe:单元实体类
 */
@Entity
@Table(name = "house_unit")
public class HouseUnitEntity {

    private String id;//主键
    private String unitCode ;//预售单元编码
    private String recordUnitCode;//备案单元编码
    private String name;//预售单元名称
    private String recordUnitName;//备案单元名称
    private String unitNum;//预售单元号
    private String recordUnitNum;//备案单元号
    private String propertyType;//房产类型
    private String buildingId;//楼Id
    private String buildingCode;//关联预售楼栋编码
    private String recordBuildingCode;//关联备案楼栋编码
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
    @Column(name = "NAME",nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "BUILDING_ID",nullable = true, length = 50)
    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
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
    @Column(name = "UNIT_CODE",nullable = true,length = 50)
    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    @Basic
    @Column(name = "BUILDING_CODE",nullable = true,length = 50)
    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }
    @Basic
    @Column(name = "RECORD_UNITCODE",length = 50)
    public String getRecordUnitCode() {
        return recordUnitCode;
    }

    public void setRecordUnitCode(String recordUnitCode) {
        this.recordUnitCode = recordUnitCode;
    }
    @Basic
    @Column(name = "RECORD_UNITNAME",length = 100)
    public String getRecordUnitName() {
        return recordUnitName;
    }

    public void setRecordUnitName(String recordUnitName) {
        this.recordUnitName = recordUnitName;
    }
    @Basic
    @Column(name = "UNITNUM",length = 50)
    public String getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(String unitNum) {
        this.unitNum = unitNum;
    }
    @Basic
    @Column(name = "RECORD_UNITNUM",length = 50)
    public String getRecordUnitNum() {
        return recordUnitNum;
    }

    public void setRecordUnitNum(String recordUnitNum) {
        this.recordUnitNum = recordUnitNum;
    }
    @Basic
    @Column(name = "PROPERTY_TYPE",length = 50)
    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
    @Basic
    @Column(name = "RECORD_BUILDINGCODE",length = 50)
    public String getRecordBuildingCode() {
        return recordBuildingCode;
    }

    public void setRecordBuildingCode(String recordBuildingCode) {
        this.recordBuildingCode = recordBuildingCode;
    }
    @Basic
    @Column(name = "STATECODE",length = 50)
    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}
