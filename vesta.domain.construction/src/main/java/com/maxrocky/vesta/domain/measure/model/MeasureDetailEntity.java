package com.maxrocky.vesta.domain.measure.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Itzxs on 2018/7/9.
 * 实测实量导入表格内容
 */
@Entity
@Table(name = "measure_detail")
public class MeasureDetailEntity {

    private String id;
    private String measureId;//实测实量ID
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String buildingId;//楼栋ID
    private String buildingName;//楼栋名称
    private String floorId;//楼层ID
    private String floorName;//楼层名称
    private String unitId;//单元ID
    private String unitName;//单元名称
    private String inspectionPhaseId;//检查阶段ID
    private String inspectionPhaseName;//检查阶段名称
    private String inspectionContentId;//检查内容
    private String inspectionContentName;//检查内容名称
    private int checkPoints;//检查点数
    private double qualificationRate;//合格率
    private String state;//状态  0 可用  1 删除
    private String serialNum;//序号
    private String isOpenQrCode;//是否公开二维码
    private Date createDate;//创建时间
    private int acceptanceNum;//合格数
    private int totalNum;//总数

    @Id
    @Column(name = "ID", length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MEASURE_ID", length = 50,nullable = true, insertable = true, updatable = true)
    public String getMeasureId() {
        return measureId;
    }

    public void setMeasureId(String measureId) {
        this.measureId = measureId;
    }

    @Basic
    @Column(name = "BUILDING_ID", length = 50,nullable = true, insertable = true, updatable = true)
    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    @Basic
    @Column(name = "BUILDING_NAME", length = 50,nullable = true, insertable = true, updatable = true)
    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    @Basic
    @Column(name = "FLOOR_ID", length = 50,nullable = true, insertable = true, updatable = true)
    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    @Basic
    @Column(name = "FLOOR_NAME", length = 50,nullable = true, insertable = true, updatable = true)
    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    @Basic
    @Column(name = "INSPECTION_PHASE_ID", length = 50,nullable = true, insertable = true, updatable = true)
    public String getInspectionPhaseId() {
        return inspectionPhaseId;
    }

    public void setInspectionPhaseId(String inspectionPhaseId) {
        this.inspectionPhaseId = inspectionPhaseId;
    }

    @Basic
    @Column(name = "INSPECTION_CONTENT_ID", length = 100,nullable = true, insertable = true, updatable = true)
    public String getInspectionContentId() {
        return inspectionContentId;
    }

    public void setInspectionContentId(String inspectionContentId) {
        this.inspectionContentId = inspectionContentId;
    }

    @Basic
    @Column(name = "CHECK_POINTS", nullable = true, insertable = true, updatable = true)
    public int getCheckPoints() {
        return checkPoints;
    }

    public void setCheckPoints(int checkPoints) {
        this.checkPoints = checkPoints;
    }

    @Basic
    @Column(name = "QUALIFICATION_RATE", nullable = true, insertable = true, updatable = true)
    public double getQualificationRate() {
        return qualificationRate;
    }

    public void setQualificationRate(double qualificationRate) {
        this.qualificationRate = qualificationRate;
    }

    @Basic
    @Column(name = "STATE", length = 10,nullable = true, insertable = true, updatable = true)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "SERIAL_NUM", length = 10,nullable = true, insertable = true, updatable = true)
    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    @Basic
    @Column(name = "IS_OPEN_QR_CODE", length = 10,nullable = true, insertable = true, updatable = true)
    public String getIsOpenQrCode() {
        return isOpenQrCode;
    }

    public void setIsOpenQrCode(String isOpenQrCode) {
        this.isOpenQrCode = isOpenQrCode;
    }

    @Basic
    @Column(name = "PROJECT_ID", length = 50,nullable = true, insertable = true, updatable = true)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "INSPECTION_PHASE_NAME", length = 50,nullable = true, insertable = true, updatable = true)
    public String getInspectionPhaseName() {
        return inspectionPhaseName;
    }

    public void setInspectionPhaseName(String inspectionPhaseName) {
        this.inspectionPhaseName = inspectionPhaseName;
    }

    @Basic
    @Column(name = "UNIT_ID", length = 50,nullable = true, insertable = true, updatable = true)
    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    @Basic
    @Column(name = "UNIT_NAME", length = 50,nullable = true, insertable = true, updatable = true)
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Basic
    @Column(name = "CREATE_DATE", length = 50,nullable = true, insertable = true, updatable = true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "ACCEPTANCE_NUM", nullable = true, insertable = true, updatable = true)
    public int getAcceptanceNum() {
        return acceptanceNum;
    }

    public void setAcceptanceNum(int acceptanceNum) {
        this.acceptanceNum = acceptanceNum;
    }

    @Basic
    @Column(name = "TOTAL_NUM", nullable = true, insertable = true, updatable = true)
    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    @Basic
    @Column(name = "PROJECT_NAME", length = 50,nullable = true, insertable = true, updatable = true)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "INSPECTION_CONTENT_NAME", length = 50,nullable = true, insertable = true, updatable = true)
    public String getInspectionContentName() {
        return inspectionContentName;
    }

    public void setInspectionContentName(String inspectionContentName) {
        this.inspectionContentName = inspectionContentName;
    }
}
