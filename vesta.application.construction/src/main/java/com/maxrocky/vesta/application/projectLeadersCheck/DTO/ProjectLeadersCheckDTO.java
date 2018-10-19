package com.maxrocky.vesta.application.projectLeadersCheck.DTO;

import com.maxrocky.vesta.application.dailyPatrolInspection.DTO.CopyDetailsListDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Talent on 2017/1/16.
 */
public class ProjectLeadersCheckDTO {
    private String groupId;//z总部
    private String regionId;//区域
    private String cityId;//城市
    private String title;//巡检标题
    private String classifyOne;//一级分类
    private String classifyOneName;//一级分类名称
    private String classifyTwo;//二级分类
    private String classifyTwoName;//二级分类名称
    private String classifyThree;//三级分类
    private String classifyThreeName;//三级分类名称
    private String point;//详细位置
    private String buildingId;//楼栋id
    private String buildingName;//楼栋编码
    private String floorId;//楼层id
    private String floorName;//楼层编码
    private String severityLevel;//严重等级
    private String supplierId;//责任单位ID
    private String firstParty;              //甲方负责人ID
    private String supervisor;//第三方监理id
    private List<CopyDetailsListDTO> idList;//抄送人list
    private String rectificationPeriod;//整改时限
    private String xCoordinate;//x坐标
    private String yCoordinate;//y坐标
    //以上部分为新增是APP传入
    private String supervisorName;//第三方监理名字
    private String firstPartyName;          //甲方负责人名字
    private String supplier;//责任单位名字


    //以上部分为日常巡检


    private String id;//自增长ID
    private String appId;//唯一校验，防止重复
    private String checkId;//检查ID
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String assignId;//整改人ID
    private String assignName;//整改人名称
    private String dealPeople;//处理人ID
    private String createBy;//创建人ID
    private String createName;//创建人名称
    private String createDate;//创建时间
    private String modifyBy;//修改人Id
    private String modifyName;//修改人名称
    private String modifyDate;//修改时间
    private String state;//状态
    private List<ProjectLeadersCheckDetailDTO> leaderList;//领导详情
    private List<ProjectLeadersCheckDetailDTO> managerList;//经理详情
    private Map<String, String> projects; // 项目名称
    private String startDate;//开始时间
    private String endDate;//结束时间
    private String shutDown;//关闭理由
    private String shutDownBy;//关闭人姓名
    private String shutDownOn;//关闭时间
    public ProjectLeadersCheckDTO() {
        this.id = "";//自增长ID
        this.appId = "";//唯一校验，防止重复
        this.checkId = "";//检查ID
        this.projectId = "";//项目ID
        this.projectName = "";//项目名称
        this.assignId = "";//整改人ID
        this.assignName = "";//整改人名称
        this.dealPeople = "";//处理人ID
        this.createBy = "";//创建人ID
        this.createName = "";//创建人名称
        this.createDate = "";//创建时间
        this.modifyBy = "";//修改人Id
        this.modifyName = "";//修改人名称
        this.modifyDate = "";//修改时间
        this.state = "";//状态
        this.leaderList = new ArrayList<ProjectLeadersCheckDetailDTO>();//领导详情
        this.managerList = new ArrayList<ProjectLeadersCheckDetailDTO>();//经理详情


        this.title = "";//巡检标题
        this.classifyOne = "";//一级分类
        this.classifyOneName = "";//一级分类名称
        this.classifyTwo = "";//二级分类
        this.classifyTwoName = "";//二级分类名称
        this.classifyThree = "";//三级分类
        this.classifyThreeName = "";//三级分类名称
        this.point = "";//详细位置
        this.buildingId = "";//楼栋id
        this.buildingName = "";//楼栋编码
        this.floorId = "";//楼层id
        this.floorName = "";//楼层编码
        this.severityLevel = "";//严重等级
        this.supplierId = "";//责任单位ID
        this.firstParty = "";//甲方负责人ID
        this.supervisor = "";//第三方监理id
        this.idList = new ArrayList<>();//抄送人list
        this.rectificationPeriod = "";//整改时限
        this.xCoordinate = "";//x坐标
        this.yCoordinate = "";//y坐标
        //以上部分为新增是APP传入
        this.supervisorName = "";//第三方监理名字
        this.firstPartyName = "";//甲方负责人名字
        this.supplier = "";//责任单位名字
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }


    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ProjectLeadersCheckDetailDTO> getLeaderList() {
        return leaderList;
    }

    public void setLeaderList(List<ProjectLeadersCheckDetailDTO> leaderList) {
        this.leaderList = leaderList;
    }

    public List<ProjectLeadersCheckDetailDTO> getManagerList() {
        return managerList;
    }

    public void setManagerList(List<ProjectLeadersCheckDetailDTO> managerList) {
        this.managerList = managerList;
    }

    public String getDealPeople() {
        return dealPeople;
    }

    public void setDealPeople(String dealPeople) {
        this.dealPeople = dealPeople;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassifyOne() {
        return classifyOne;
    }

    public void setClassifyOne(String classifyOne) {
        this.classifyOne = classifyOne;
    }

    public String getClassifyOneName() {
        return classifyOneName;
    }

    public void setClassifyOneName(String classifyOneName) {
        this.classifyOneName = classifyOneName;
    }

    public String getClassifyTwo() {
        return classifyTwo;
    }

    public void setClassifyTwo(String classifyTwo) {
        this.classifyTwo = classifyTwo;
    }

    public String getClassifyTwoName() {
        return classifyTwoName;
    }

    public void setClassifyTwoName(String classifyTwoName) {
        this.classifyTwoName = classifyTwoName;
    }

    public String getClassifyThree() {
        return classifyThree;
    }

    public void setClassifyThree(String classifyThree) {
        this.classifyThree = classifyThree;
    }

    public String getClassifyThreeName() {
        return classifyThreeName;
    }

    public void setClassifyThreeName(String classifyThreeName) {
        this.classifyThreeName = classifyThreeName;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getFirstParty() {
        return firstParty;
    }

    public void setFirstParty(String firstParty) {
        this.firstParty = firstParty;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public List<CopyDetailsListDTO> getIdList() {
        return idList;
    }

    public void setIdList(List<CopyDetailsListDTO> idList) {
        this.idList = idList;
    }

    public String getRectificationPeriod() {
        return rectificationPeriod;
    }

    public void setRectificationPeriod(String rectificationPeriod) {
        this.rectificationPeriod = rectificationPeriod;
    }

    public String getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(String xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public String getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(String yCoordinate) {
        this.yCoordinate = yCoordinate;
    }


    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getFirstPartyName() {
        return firstPartyName;
    }

    public void setFirstPartyName(String firstPartyName) {
        this.firstPartyName = firstPartyName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Map<String, String> getProjects() {
        return projects;
    }

    public void setProjects(Map<String, String> projects) {
        this.projects = projects;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getShutDown() {
        return shutDown;
    }

    public void setShutDown(String shutDown) {
        this.shutDown = shutDown;
    }

    public String getShutDownBy() {
        return shutDownBy;
    }

    public void setShutDownBy(String shutDownBy) {
        this.shutDownBy = shutDownBy;
    }

    public String getShutDownOn() {
        return shutDownOn;
    }

    public void setShutDownOn(String shutDownOn) {
        this.shutDownOn = shutDownOn;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
}
