package com.maxrocky.vesta.application.measure.DTO;

import java.util.Map;

/**
 * Created by Itzxs on 2018/7/9.
 */
public class MeasureDTO {
    private String groupId;//z总部
    private String regionId;//区域
    private String regionName;//区域名称
    private String cityId;//城市
    private String cityName;//城市名称
    private String projectId;//项目id
    private String projectNum;//项目编码
    private String projectName;//项目名称
    private String projectTotalScore;//项目总得分
    private String buildingId;//楼栋id
    private String buildingNum;//楼栋编码
    private String buildingName;//楼栋名称
    private String buildingTotalScode;//楼栋总得分
    private String floorId;//楼层ID
    private String floorCode;//楼层编码
    private String floorName;//楼层名称
    private String mensurePercentOfPass;//标段实测合格率
    private String modifyDate;//变更时间
    private String inspectionPhaseId;//检查阶段ID
    private String inspectionPhaseName;//检查阶段名称
    private String projectPercentOfPass;//项目合格率
    private String buildPercentOfPass;//楼栋合格率
    private String createBy;//创建人

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectTotalScore() {
        return projectTotalScore;
    }

    public void setProjectTotalScore(String projectTotalScore) {
        this.projectTotalScore = projectTotalScore;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingTotalScode() {
        return buildingTotalScode;
    }

    public void setBuildingTotalScode(String buildingTotalScode) {
        this.buildingTotalScode = buildingTotalScode;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getFloorCode() {
        return floorCode;
    }

    public void setFloorCode(String floorCode) {
        this.floorCode = floorCode;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getMensurePercentOfPass() {
        return mensurePercentOfPass;
    }

    public void setMensurePercentOfPass(String mensurePercentOfPass) {
        this.mensurePercentOfPass = mensurePercentOfPass;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getInspectionPhaseId() {
        return inspectionPhaseId;
    }

    public void setInspectionPhaseId(String inspectionPhaseId) {
        this.inspectionPhaseId = inspectionPhaseId;
    }

    public String getInspectionPhaseName() {
        return inspectionPhaseName;
    }

    public void setInspectionPhaseName(String inspectionPhaseName) {
        this.inspectionPhaseName = inspectionPhaseName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProjectPercentOfPass() {
        return projectPercentOfPass;
    }

    public void setProjectPercentOfPass(String projectPercentOfPass) {
        this.projectPercentOfPass = projectPercentOfPass;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getBuildPercentOfPass() {
        return buildPercentOfPass;
    }

    public void setBuildPercentOfPass(String buildPercentOfPass) {
        this.buildPercentOfPass = buildPercentOfPass;
    }

    @Override
    public String toString() {
        return "{" +
                "groupId:" + groupId +
                ", regionId:" + regionId +
                ", cityId:" + cityId +
                ", projectId:" + projectId +
                ", projectNum:" + projectNum +
                ", projectName:" + projectName +
                ", projectTotalScore:" + projectTotalScore +
                ", buildingId:" + buildingId +
                ", buildingNum:" + buildingNum +
                ", buildingName:" + buildingName +
                ", buildingTotalScode:" + buildingTotalScode +
                ", floorId:" + floorId +
                ", floorCode:" + floorCode +
                ", floorName:" + floorName +
                ", mensurePercentOfPass:" + mensurePercentOfPass +
                ", modifyDate:" + modifyDate +
                ", inspectionPhaseId:" + inspectionPhaseId +
                ", inspectionPhaseName:" + inspectionPhaseName +
                '}';
    }
}
