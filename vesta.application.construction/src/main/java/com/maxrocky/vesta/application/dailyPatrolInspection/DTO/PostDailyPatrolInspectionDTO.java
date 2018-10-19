package com.maxrocky.vesta.application.dailyPatrolInspection.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/10/24.
 */
public class PostDailyPatrolInspectionDTO {
    private String title;//巡检标题
    private String classifyOne;//一级分类
    private String classifyOneName;//一级分类名称
    private String classifyTwo;//二级分类
    private String classifyTwoName;//二级分类名称
    private String classifyThree;//三级分类
    private String classifyThreeName;//三级分类名称
    private String projectId;//工程项目_ID
    private String projectName;//工程项目编码
    private String point;//详细位置
    private String buildingId;//楼栋id
    private String buildingName;//楼栋编码
    private String floorId;//楼层id
    private String floorName;//楼层编码
    private String severityLevel;//严重等级
    private String description;//描述
    private List<InspectionImageDTO> imageList;//整改图片
    private String supplierId;//责任单位ID
    private String assignId;//整改人ID
    private String dealPeople;              //处理人ID
    private String firstParty;              //甲方负责人ID
    private String supervisor;//第三方监理id
    private List<CopyDetailsListDTO> idList;//抄送人list
    private String rectificationPeriod;//整改时限
    private String xCoordinate;//x坐标
    private String yCoordinate;//y坐标
    private String state;//状态
    //以上部分为新增是APP传入
    private String id;
    private String inspectionId;//日常巡检id
    private String supervisorName;//第三方监理名字
    private String firstPartyName;          //甲方负责人名字
    private String assignName;//整改人名字
    private String supplier;//责任单位名字
    private String createName;//创建人名字（甲方验收人）
    private String createOn;//创建时间
    private String modifyOn;//修改时间
    private List<PostDailyPatrolInspectionDetailsDTO> inspectionList;//整改记录
    private String appId;//代表手机端id

    public PostDailyPatrolInspectionDTO(){
        this.appId="";
        this.dealPeople="";
        this.firstParty="";
        this.firstPartyName="";
        this.modifyOn="";
        this.id="";
        this.inspectionId="";//日常巡检id
        this.supervisorName="";//第三方监理名字
        this.assignName="";//整改人名字
        this.supplier="";//责任单位名字
        this.createName="";//创建人名字（甲方验收人）
        this.createOn="";//创建时间
        this.inspectionList=new ArrayList<PostDailyPatrolInspectionDetailsDTO>();//整改记录
        this.state="";//状态
        this.point="";//详细位置
        this.title="";//巡检标题
        this.classifyOne="";//一级分类
        this.classifyTwo="";//二级分类
        this.classifyThree="";//三级分类
        this.projectId="";//工程项目_ID
        this.projectName="";//工程项目编码
        this.buildingId="";//楼栋id
        this.buildingName="";//楼栋编码
        this.floorId="";//楼层id
        this.floorName="";//楼层编码
        this.severityLevel="";//严重等级
        this.description="";//描述
        this.imageList=new ArrayList<InspectionImageDTO>();//整改图片
        this.supplierId="";//责任单位ID
        this.assignId="";//整改人ID
        this.supervisor="";//第三方监理id
        this.idList=new ArrayList<CopyDetailsListDTO>();//抄送人list
        this.rectificationPeriod="";//整改时限
        this.xCoordinate="";//x坐标
        this.yCoordinate="";//y坐标
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

    public String getClassifyTwo() {
        return classifyTwo;
    }

    public void setClassifyTwo(String classifyTwo) {
        this.classifyTwo = classifyTwo;
    }

    public String getClassifyThree() {
        return classifyThree;
    }

    public void setClassifyThree(String classifyThree) {
        this.classifyThree = classifyThree;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }



    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }



    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }



    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<InspectionImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<InspectionImageDTO> imageList) {
        this.imageList = imageList;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId;
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


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public List<PostDailyPatrolInspectionDetailsDTO> getInspectionList() {
        return inspectionList;
    }

    public void setInspectionList(List<PostDailyPatrolInspectionDetailsDTO> inspectionList) {
        this.inspectionList = inspectionList;
    }

    public String getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(String modifyOn) {
        this.modifyOn = modifyOn;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getDealPeople() {
        return dealPeople;
    }

    public void setDealPeople(String dealPeople) {
        this.dealPeople = dealPeople;
    }

    public String getFirstParty() {
        return firstParty;
    }

    public void setFirstParty(String firstParty) {
        this.firstParty = firstParty;
    }

    public String getFirstPartyName() {
        return firstPartyName;
    }

    public void setFirstPartyName(String firstPartyName) {
        this.firstPartyName = firstPartyName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getClassifyOneName() {
        return classifyOneName;
    }

    public void setClassifyOneName(String classifyOneName) {
        this.classifyOneName = classifyOneName;
    }

    public String getClassifyTwoName() {
        return classifyTwoName;
    }

    public void setClassifyTwoName(String classifyTwoName) {
        this.classifyTwoName = classifyTwoName;
    }

    public String getClassifyThreeName() {
        return classifyThreeName;
    }

    public void setClassifyThreeName(String classifyThreeName) {
        this.classifyThreeName = classifyThreeName;
    }
}
