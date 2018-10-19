package com.maxrocky.vesta.application.ProjectMaterial.DTO;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/11/24.
 */
public class ProjectMaterialDTO {
    private String id;//自增长ID
    private String materialId;//材料验收Id
    private String batchName;//批次名称
    private String classifyOne;//一级分类
    private String classifyTwo;//二级分类
    private String classifyOneName;//一级分类名称
    private String classifyTwoName;//二级分类名称
    private String approachTime;//进场时间
    private String approachNumber;//进场批量
    private String usedPart;//准备使用部位
    private String createOn;//创建时间
    private String createBy;//创建人Id
    private String createName;//创建人名字
    private String inspectedTime;//验收时间
    private List<ProjectMaterialDetailsDTO> detailsList;//验收指标信息lsit
    private String supplierId;//供应商id
    private String supplierName;//供应商名字
    private String assignId;//材料负责人Id
    private String assignName;//材料负责人
    private String supervisor;//第三方监理id
    private String supervisorName;//第三方监理名字
    private String state;//状态：草稿、不合格、合格、已退场
    private String outTime;//退场时间
    private String description;//退场描述
    private List<MaterialImageDTO> imageList;//退场纪录图片List
    private String appId;//第三方监理名字
    private String dealPeople;//处理人
    private String modifyDate;//修改时间
    private String projectId;//项目ID
    private String projectName;//项目名称
    public ProjectMaterialDTO(){
        this.id="";
        this.projectId="";
        this.projectName="";
        this.state="";
        this.appId="";
        this.dealPeople="";
        this.materialId="";
        this.batchName="";
        this.classifyOne="";
        this.classifyTwo="";
        this.classifyOneName="";
        this.classifyTwoName="";
        this.approachTime="";
        this.approachNumber="";
        this.usedPart="";
        this.createOn="";
        this.createBy="";
        this.createName="";
        this.inspectedTime="";
        this.detailsList=new ArrayList<ProjectMaterialDetailsDTO>();
        this.supplierId="";
        this.supplierName="";
        this.assignId="";
        this.assignName="";
        this.supervisor="";
        this.supervisorName="";
        this.outTime="";
        this.description="";
        this.imageList=new ArrayList<MaterialImageDTO>();
    }
    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
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

    public String getApproachTime() {
        return approachTime;
    }

    public void setApproachTime(String approachTime) {
        this.approachTime = approachTime;
    }

    public String getApproachNumber() {
        return approachNumber;
    }

    public void setApproachNumber(String approachNumber) {
        this.approachNumber = approachNumber;
    }

    public String getUsedPart() {
        return usedPart;
    }

    public void setUsedPart(String usedPart) {
        this.usedPart = usedPart;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
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

    public String getInspectedTime() {
        return inspectedTime;
    }

    public void setInspectedTime(String inspectedTime) {
        this.inspectedTime = inspectedTime;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MaterialImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<MaterialImageDTO> imageList) {
        this.imageList = imageList;
    }

    public List<ProjectMaterialDetailsDTO> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<ProjectMaterialDetailsDTO> detailsList) {
        this.detailsList = detailsList;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDealPeople() {
        return dealPeople;
    }

    public void setDealPeople(String dealPeople) {
        this.dealPeople = dealPeople;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
