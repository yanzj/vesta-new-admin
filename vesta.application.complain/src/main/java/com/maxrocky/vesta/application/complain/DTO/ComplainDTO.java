package com.maxrocky.vesta.application.complain.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2017/7/18.
 */
public class ComplainDTO {
    private String groupId;//z总部
    private String regionId;//区域
    private String cityId;//城市
    private String projectId;//项目
    private String ptype;//100000000 集团  100000001 区域  100000003 城市 100000002 项目
    private String complainId;//投诉ID
    private String memberId;//会员编号
    private String complainName;//投诉单号
    private String completeTime;//单据完成时间
    private String documentsState;//单据状态 100000000：已创建；100000001：处理中；100000002：已完成；100000003：已评价；100000004：强制关闭(物业)；100000005：已废弃
    private String revisit;//回访人姓名
    private String visitOpinion;//回访意见
    private String visitDate;//回访时间
    private String visitSatisfaction;//回访满意度 100000000：非常满意；100000001：满意；100000002：一般；100000003：不满意；100000004：非常不满意；100000005：无人接听；100000006：暂不回访；100000007：拒访；（地产只有：满意、不满意、暂不回访、拒访）
    private String city;//城市编码
    private String cityName;//城市名称
    private String disposalId;//处理人id
    private String disposalName;//处理人名
    private String disposalPortrait;//处理人头像
    private String disposalPhone;//处理人电话
    private String treatmentPlan;//处理方案
    private String processingResults;//处理结果
    private String houseCode;//房间编码
    private String houseName;//房间名称
    private String houseDes;//房间描述
    private String complaintPersonName;//投诉人姓名
    private String complaintPhone;//投诉人电话
    private String emotion;//投诉人情绪100000000：平和；100000001：激进；100000002：愤怒
    private String relatedNumber;//投诉关联单号
    private String classificationComplaints;//投诉分类100000005：客户服务类；100000003：物业服务类；100000000：销售服务类；100000001：工程质量类；100000002：规划设计类；100000004：其他类
    private String upgrade;//投诉升级人名称
    private String complaintsDescribes;//投诉描述
    private String submitTime;//投诉时间
    private String limitedReplyTime;// 限时答复时间
    private String replyTime;//答复时间(初次)
    private String timeOut;//是否超期答复0：否；1：是
    private String complaintSource;//投诉渠道 100000000：呼叫中心；100000001：项目前台；100000002：物业前台
    private String complainCanal;//投诉来源 100000000:crm ;100000001:app
    private String complaintLevel;//投诉级别 100000000：一般投诉；100000001：热点投诉；100000002：重要投诉；100000003：重大投诉
    private String majorComplaintReason;//重大投诉原因
    private String importantComplaintReason;//重要投诉原因
    private String whetherSwarmsUes;//是否群诉 0：否；1：是
    private String dispatchTime;//派单时间
    private String complaintType;//物业投诉分类 100000000：房屋管理类；100000001：设备管理类；100000002：安全管理类；100000003：环境管理类；100000004：综合服务类100000005：业户纠纷类；100000006：地产相关类；100000007：市政相关类
    private String projectNum;//项目编码
    private String projectName;//项目名称
    private String returnTime;//驳回次数
    private String lastReturnTime;//最后驳回时间
    private String ownerVersion;//业主原话
    private String createBy;//创建人ID
    private String createByName;//创建人名称
    private String createOn;//创建时间
    private String type;//1:创建；2：配单
    private List<ComplainImageDTO> imageList; //问题图片
    private List<ComplainImageDTO> receiveImgList;//处理方案图片
    private List<ComplainImageDTO> completeImgList;//处理结果图片
    private String startDate;//投诉开始时间
    private String endDate;//投诉结束时间

    private Map<String,String> citys; // 城市名称
    private Map<String,String> projects; // 项目名称
    private Map<String,String>  areas; // 地块
    private Map<String,String>  buildings; // 楼栋
    private Map<String,String>  units; // 单元
    private Map<String,String>  floors; // 楼层
    private Map<String,String> rooms; // 房间名称

    private String area;//地块
    private String buildingId;// 楼栋ID
    private String unitId;// 单元Id
    private String floorId;// 楼层ID
    public ComplainDTO() {
        this.groupId="";
        this.regionId="";
        this.cityId="";
        this.projectId="";
        this.ptype="";
        this.complainId = "";
        this.memberId = "";
        this.complainName = "";
        this.completeTime = "";
        this.documentsState = "";
        this.revisit = "";
        this.visitOpinion = "";
        this.visitDate = "";
        this.visitSatisfaction = "";
        this.city = "";
        this.cityName = "";
        this.disposalId = "";
        this.disposalName = "";//处理人名
        this.disposalPortrait = "";//处理人头像
        this.disposalPhone = "";//处理人手机
        this.treatmentPlan = "";//处理方案
        this.processingResults = "";//处理结果
        this.houseCode = "";//房间编码
        this.houseName = "";//房间名称
        this.houseDes = "";//房间描述
        this.complaintPersonName = "";//投诉人姓名
        this.complaintPhone = "";//投诉人电话
        this.emotion = "";//投诉人情绪100000000：平和；100000001：激进；100000002：愤怒
        this.relatedNumber = "";//投诉关联单号
        this.classificationComplaints = "";//投诉分类100000005：客户服务类；100000003：物业服务类；100000000：销售服务类；100000001：工程质量类；100000002：规划设计类；100000004：其他类
        this.upgrade = "";//投诉升级人名称
        this.complaintsDescribes = "";//投诉描述
        this.submitTime = "";//投诉时间
        this.limitedReplyTime = "";// 限时答复时间
        this.replyTime = "";//答复时间(初次)
        this.timeOut = "";//是否超期答复0：否；1：是
        this.complaintSource = "";//投诉来源 100000000：呼叫中心；100000001：项目前台；100000002：物业前台
        this.complainCanal = "";//投诉渠道 1:crm ="";2:客观App
        this.complaintLevel = "";//投诉级别 100000000：一般投诉；100000001：热点投诉；100000002：重要投诉；100000003：重大投诉
        this.majorComplaintReason = "";//重大投诉原因
        this.importantComplaintReason = "";//重要投诉原因
        this.whetherSwarmsUes = "";//是否群诉 0：否；1：是
        this.dispatchTime = "";//派单时间
        this.complaintType = "";//物业投诉分类 100000000：房屋管理类；100000001：设备管理类；100000002：安全管理类；100000003：环境管理类；100000004：综合服务类100000005：业户纠纷类；100000006：地产相关类；100000007：市政相关类
        this.projectNum = "";//项目编码
        this.projectName = "";//项目名称
        this.returnTime = "";//驳回次数
        this.lastReturnTime = "";//最后驳回时间
        this.ownerVersion = "";//业主原话
        this.createBy = "";//创建人ID
        this.createByName = "";//创建人名称
        this.createOn = "";//创建时间
        this.type = "";//1:创建；2：配单
        this.imageList = new ArrayList<ComplainImageDTO>(); //问题图片
        this.receiveImgList = new ArrayList<ComplainImageDTO>();//处理方案图片
        this.completeImgList = new ArrayList<ComplainImageDTO>();//处理结果图片
    }

    public String getComplainId() {
        return complainId;
    }

    public void setComplainId(String complainId) {
        this.complainId = complainId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getComplainName() {
        return complainName;
    }

    public void setComplainName(String complainName) {
        this.complainName = complainName;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public String getDocumentsState() {
        return documentsState;
    }

    public void setDocumentsState(String documentsState) {
        this.documentsState = documentsState;
    }

    public String getRevisit() {
        return revisit;
    }

    public void setRevisit(String revisit) {
        this.revisit = revisit;
    }

    public String getVisitOpinion() {
        return visitOpinion;
    }

    public void setVisitOpinion(String visitOpinion) {
        this.visitOpinion = visitOpinion;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitSatisfaction() {
        return visitSatisfaction;
    }

    public void setVisitSatisfaction(String visitSatisfaction) {
        this.visitSatisfaction = visitSatisfaction;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDisposalId() {
        return disposalId;
    }

    public void setDisposalId(String disposalId) {
        this.disposalId = disposalId;
    }

    public String getDisposalName() {
        return disposalName;
    }

    public void setDisposalName(String disposalName) {
        this.disposalName = disposalName;
    }

    public String getDisposalPortrait() {
        return disposalPortrait;
    }

    public void setDisposalPortrait(String disposalPortrait) {
        this.disposalPortrait = disposalPortrait;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public String getProcessingResults() {
        return processingResults;
    }

    public void setProcessingResults(String processingResults) {
        this.processingResults = processingResults;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseDes() {
        return houseDes;
    }

    public void setHouseDes(String houseDes) {
        this.houseDes = houseDes;
    }

    public String getComplaintPersonName() {
        return complaintPersonName;
    }

    public void setComplaintPersonName(String complaintPersonName) {
        this.complaintPersonName = complaintPersonName;
    }

    public String getComplaintPhone() {
        return complaintPhone;
    }

    public void setComplaintPhone(String complaintPhone) {
        this.complaintPhone = complaintPhone;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getRelatedNumber() {
        return relatedNumber;
    }

    public void setRelatedNumber(String relatedNumber) {
        this.relatedNumber = relatedNumber;
    }

    public String getClassificationComplaints() {
        return classificationComplaints;
    }

    public void setClassificationComplaints(String classificationComplaints) {
        this.classificationComplaints = classificationComplaints;
    }

    public String getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(String upgrade) {
        this.upgrade = upgrade;
    }

    public String getComplaintsDescribes() {
        return complaintsDescribes;
    }

    public void setComplaintsDescribes(String complaintsDescribes) {
        this.complaintsDescribes = complaintsDescribes;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getLimitedReplyTime() {
        return limitedReplyTime;
    }

    public void setLimitedReplyTime(String limitedReplyTime) {
        this.limitedReplyTime = limitedReplyTime;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getComplaintSource() {
        return complaintSource;
    }

    public void setComplaintSource(String complaintSource) {
        this.complaintSource = complaintSource;
    }

    public String getComplainCanal() {
        return complainCanal;
    }

    public void setComplainCanal(String complainCanal) {
        this.complainCanal = complainCanal;
    }

    public String getComplaintLevel() {
        return complaintLevel;
    }

    public void setComplaintLevel(String complaintLevel) {
        this.complaintLevel = complaintLevel;
    }

    public String getMajorComplaintReason() {
        return majorComplaintReason;
    }

    public void setMajorComplaintReason(String majorComplaintReason) {
        this.majorComplaintReason = majorComplaintReason;
    }

    public String getImportantComplaintReason() {
        return importantComplaintReason;
    }

    public void setImportantComplaintReason(String importantComplaintReason) {
        this.importantComplaintReason = importantComplaintReason;
    }

    public String getWhetherSwarmsUes() {
        return whetherSwarmsUes;
    }

    public void setWhetherSwarmsUes(String whetherSwarmsUes) {
        this.whetherSwarmsUes = whetherSwarmsUes;
    }

    public String getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(String dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
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

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getLastReturnTime() {
        return lastReturnTime;
    }

    public void setLastReturnTime(String lastReturnTime) {
        this.lastReturnTime = lastReturnTime;
    }

    public String getOwnerVersion() {
        return ownerVersion;
    }

    public void setOwnerVersion(String ownerVersion) {
        this.ownerVersion = ownerVersion;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ComplainImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<ComplainImageDTO> imageList) {
        this.imageList = imageList;
    }

    public List<ComplainImageDTO> getReceiveImgList() {
        return receiveImgList;
    }

    public void setReceiveImgList(List<ComplainImageDTO> receiveImgList) {
        this.receiveImgList = receiveImgList;
    }

    public List<ComplainImageDTO> getCompleteImgList() {
        return completeImgList;
    }

    public void setCompleteImgList(List<ComplainImageDTO> completeImgList) {
        this.completeImgList = completeImgList;
    }

    public String getDisposalPhone() {
        return disposalPhone;
    }

    public void setDisposalPhone(String disposalPhone) {
        this.disposalPhone = disposalPhone;
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

    public Map<String, String> getCitys() {
        return citys;
    }

    public void setCitys(Map<String, String> citys) {
        this.citys = citys;
    }

    public Map<String, String> getProjects() {
        return projects;
    }

    public void setProjects(Map<String, String> projects) {
        this.projects = projects;
    }

    public Map<String, String> getRooms() {
        return rooms;
    }

    public void setRooms(Map<String, String> rooms) {
        this.rooms = rooms;
    }

    public Map<String, String> getAreas() {
        return areas;
    }

    public void setAreas(Map<String, String> areas) {
        this.areas = areas;
    }

    public Map<String, String> getBuildings() {
        return buildings;
    }

    public void setBuildings(Map<String, String> buildings) {
        this.buildings = buildings;
    }

    public Map<String, String> getUnits() {
        return units;
    }

    public void setUnits(Map<String, String> units) {
        this.units = units;
    }

    public Map<String, String> getFloors() {
        return floors;
    }

    public void setFloors(Map<String, String> floors) {
        this.floors = floors;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

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

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }
}
