package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tom on 2016/1/14 20:10.
 * Describe:楼实体类
 */
@Entity
@Table(name = "house_building")
public class HouseBuildingEntity {

    private String id;//主键  (buildingId)
    private String name;//名称
    private String buildingNum;//预售编码
    private String recordBuildingCode;//备案编码
    private String saleBuildingName;//预售楼栋名称
    private String recordBuildingName;//备案楼栋名称
    private String propertyType;//房产类型
    private String constructionType;//建筑类型
    private String decorationStandard;//销售装修类型 精装/毛坯
    private Double floorHeight;//层高（米
    private String totalFloor;//总层数
    private String totalHouse;//总户数
    private String totalUnit;//单元总数
    private Double totalBuildingArea;//建筑面积总和（平方米）
    private Double totalGreenArea;//绿化面积总和（平方米）
    private Double totalGardenArea;//花园面积总和（平方米）
    private Double totalInternalArea;//套内面积总和（平方米）
    private String groupCode;//组团编码
    private String groupName;//组团名称
    private String stateCode;//状态 0:有效 1:失效

    private String area;//地块
    private String buildingSale;//预售楼号
    private String buildingRecord;//备案楼号
    private String projectId;//项目Id
    private String formatId;//业态Id
    private String street;//街道
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间
    private String projectNum;//项目编号
    private String blockId;//关联地块Id
    private String blockNum;//关联地块的编码



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
    @Column(name = "PROJECT_ID",nullable = true, length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "FORMAT_ID", length = 50)
    public String getFormatId() {
        return formatId;
    }

    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    @Basic
    @Column(name = "STREET", length = 100)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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
    @Column(name = "AREA",nullable = true, length = 50)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "BUILDING_NUM",length = 50)
    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    @Basic
    @Column(name = "BUILDING_RECORD", length = 50)
    public String getBuildingRecord() {
        return buildingRecord;
    }

    public void setBuildingRecord(String buildingRecord) {
        this.buildingRecord = buildingRecord;
    }

    @Basic
    @Column(name = "PROJECT_NUM", length = 50)
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    @Basic
    @Column(name = "BUILDING_SALE", length = 50)
    public String getBuildingSale() {
        return buildingSale;
    }

    public void setBuildingSale(String buildingSale) {
        this.buildingSale = buildingSale;
    }

    @Basic
    @Column(name = "BLOCK_ID", length = 50)
    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    @Basic
    @Column(name = "BLOCK_NUM", length = 50)
    public String getBlockNum() {
        return blockNum;
    }

    public void setBlockNum(String blockNum) {
        this.blockNum = blockNum;
    }
    @Basic
    @Column(name = "RECORD_BUILDINGCODE", length = 50)
    public String getRecordBuildingCode() {
        return recordBuildingCode;
    }

    public void setRecordBuildingCode(String recordBuildingCode) {
        this.recordBuildingCode = recordBuildingCode;
    }
    @Basic
    @Column(name = "SALE_BUILDINGNAME", length = 50)
    public String getSaleBuildingName() {
        return saleBuildingName;
    }

    public void setSaleBuildingName(String saleBuildingName) {
        this.saleBuildingName = saleBuildingName;
    }
    @Basic
    @Column(name = "RECORD_BUILDINGNAME", length = 50)
    public String getRecordBuildingName() {
        return recordBuildingName;
    }

    public void setRecordBuildingName(String recordBuildingName) {
        this.recordBuildingName = recordBuildingName;
    }
    @Basic
    @Column(name = "PROPERTYTYPE", length = 50)
    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
    @Basic
    @Column(name = "CONSTRUCTIONTYPE", length = 50)
    public String getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(String constructionType) {
        this.constructionType = constructionType;
    }
    @Basic
    @Column(name = "DECORATIONSTANDARD", length = 50)
    public String getDecorationStandard() {
        return decorationStandard;
    }

    public void setDecorationStandard(String decorationStandard) {
        this.decorationStandard = decorationStandard;
    }
    @Basic
    @Column(name = "FLOORHEIGHT", length = 50)
    public Double getFloorHeight() {
        return floorHeight;
    }

    public void setFloorHeight(Double floorHeight) {
        this.floorHeight = floorHeight;
    }
    @Basic
    @Column(name = "TOTAL_FLOOR", length = 50)
    public String getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(String totalFloor) {
        this.totalFloor = totalFloor;
    }
    @Basic
    @Column(name = "TOTAL_HOUSE", length = 50)
    public String getTotalHouse() {
        return totalHouse;
    }

    public void setTotalHouse(String totalHouse) {
        this.totalHouse = totalHouse;
    }
    @Basic
    @Column(name = "TOTAL_UNIT", length = 50)
    public String getTotalUnit() {
        return totalUnit;
    }

    public void setTotalUnit(String totalUnit) {
        this.totalUnit = totalUnit;
    }
    @Basic
    @Column(name = "TOTAL_BUILDINGAREA", length = 50)
    public Double getTotalBuildingArea() {
        return totalBuildingArea;
    }

    public void setTotalBuildingArea(Double totalBuildingArea) {
        this.totalBuildingArea = totalBuildingArea;
    }
    @Basic
    @Column(name = "TOTAL_GREENAREA", length = 50)
    public Double getTotalGreenArea() {
        return totalGreenArea;
    }

    public void setTotalGreenArea(Double totalGreenArea) {
        this.totalGreenArea = totalGreenArea;
    }
    @Basic
    @Column(name = "TOTAL_GARDENAREA", length = 50)
    public Double getTotalGardenArea() {
        return totalGardenArea;
    }

    public void setTotalGardenArea(Double totalGardenArea) {
        this.totalGardenArea = totalGardenArea;
    }
    @Basic
    @Column(name = "TOTAL_INTERNALAREA", length = 50)
    public Double getTotalInternalArea() {
        return totalInternalArea;
    }

    public void setTotalInternalArea(Double totalInternalArea) {
        this.totalInternalArea = totalInternalArea;
    }
    @Basic
    @Column(name = "GROUPCODE", length = 50)
    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
    @Basic
    @Column(name = "GROUPNAME", length = 50)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
