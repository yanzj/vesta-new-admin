package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 投诉信息表
 * Created by Jason on 2017/7/17.
 */
@Entity
@Table(name = "qc_complain")
public class ComplainEntity {
    private String complainId;//投诉ID
    private String memberId;//会员编号
    private String complainName;//投诉单号
    private Date completeTime;//单据完成时间
    private String documentsState;//单据状态 100000000：已创建；100000001：处理中；100000002：已完成；100000003：已评价；100000004：强制关闭(物业)；100000005：已废弃
    private String revisit;//回访人姓名
    private String visitOpinion;//回访意见
    private Date visitDate;//回访时间
    private String visitSatisfaction;//回访满意度 100000000：非常满意；100000001：满意；100000002：一般；100000003：不满意；100000004：非常不满意；100000005：无人接听；100000006：暂不回访；100000007：拒访；（地产只有：满意、不满意、暂不回访、拒访）
    private String city;//城市编码
    private String disposal;//处理人账号
    private String disposalPortrait;//处理人头像
    private String treatmentPlan;//处理方案
    private String processingResults;//处理结果
    private String houseCode;//房间编码
    private String houseDes;//房间描述
    private String portalComplaintPersonId;//投诉人ID
    private String portalComplaintPersonName;//投诉人姓名
    private String emotion;//投诉人情绪100000000：平和；100000001：激进；100000002：愤怒
    private String relatedNumber;//投诉关联单号
    private String classificationComplaints;//投诉分类100000005：客户服务类；100000003：物业服务类；100000000：销售服务类；100000001：工程质量类；100000002：规划设计类；100000004：其他类
    private String upgrade;//投诉升级人名称
    private String complaintPhone;//投诉人电话
    private String complaintsDescribes;//投诉描述
    private Date submitTime;//投诉时间
    private Date limitedReplyTime;// 限时答复时间
    private String timeOut;//是否超期答复0：否；1：是
    private String complaintSource;//投诉渠道 100000000：呼叫中心；100000001：项目前台；100000002：物业前台
    private String complainCanal;//投诉来源 1:crm ;2:app
    private String complaintLevel;//投诉级别 100000000：一般投诉；100000001：热点投诉；100000002：重要投诉；100000003：重大投诉
    private String majorComplaintReason;//重大投诉原因
    private String importantComplaintReason;//重要投诉原因
    private String whetherSwarmsUes;//是否群诉 0：否；1：是
    private Date dispatchTime;//派单时间
    private String complaintType;//物业投诉分类 100000000：房屋管理类；100000001：设备管理类；100000002：安全管理类；100000003：环境管理类；100000004：综合服务类100000005：业户纠纷类；100000006：地产相关类；100000007：市政相关类
    private Date replyTime;//答复时间
    private String projectNum;//项目编码
    private String returnTime;//驳回次数
    private Date lastReturnTime;//最后驳回时间
    private String ownerVersion;//业主原话
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String createByName;//创建人姓名
    @Id
    @Column(name = "complain_id", nullable = false, length = 50)
    public String getComplainId() {
        return complainId;
    }

    public void setComplainId(String complainId) {
        this.complainId = complainId;
    }

    @Basic
    @Column(name = "member_id", length = 50)
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Basic
    @Column(name = "complain_name", length = 50,unique = true)
    public String getComplainName() {
        return complainName;
    }

    public void setComplainName(String complainName) {
        this.complainName = complainName;
    }

    @Basic
    @Column(name = "complete_time")
    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    @Basic
    @Column(name = "document_state", length = 16)
    public String getDocumentsState() {
        return documentsState;
    }

    public void setDocumentsState(String documentsState) {
        this.documentsState = documentsState;
    }

    @Basic
    @Column(name = "revisit", length = 50)
    public String getRevisit() {
        return revisit;
    }

    public void setRevisit(String revisit) {
        this.revisit = revisit;
    }

    @Basic
    @Column(name = "visit_opinion", length = 500)
    public String getVisitOpinion() {
        return visitOpinion;
    }

    public void setVisitOpinion(String visitOpinion) {
        this.visitOpinion = visitOpinion;
    }

    @Basic
    @Column(name = "visit_date")
    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    @Basic
    @Column(name = "revisit_satisfaction", length = 16)
    public String getVisitSatisfaction() {
        return visitSatisfaction;
    }

    public void setVisitSatisfaction(String visitSatisfaction) {
        this.visitSatisfaction = visitSatisfaction;
    }

    @Basic
    @Column(name = "city_num", length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "disposal", length = 50)
    public String getDisposal() {
        return disposal;
    }

    public void setDisposal(String disposal) {
        this.disposal = disposal;
    }

    @Basic
    @Column(name = "disposal_portrait", length = 100)
    public String getDisposalPortrait() {
        return disposalPortrait;
    }

    public void setDisposalPortrait(String disposalPortrait) {
        this.disposalPortrait = disposalPortrait;
    }

    @Basic
    @Column(name = "treatment_plan", length = 500)
    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    @Basic
    @Column(name = "processing_results", length = 500)
    public String getProcessingResults() {
        return processingResults;
    }

    public void setProcessingResults(String processingResults) {
        this.processingResults = processingResults;
    }

    @Basic
    @Column(name = "house_code", length = 50)
    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    @Basic
    @Column(name = "house_des", length = 50)
    public String getHouseDes() {
        return houseDes;
    }

    public void setHouseDes(String houseDes) {
        this.houseDes = houseDes;
    }

    @Basic
    @Column(name = "portal_complain_person_id", length = 50)
    public String getPortalComplaintPersonId() {
        return portalComplaintPersonId;
    }

    public void setPortalComplaintPersonId(String portalComplaintPersonId) {
        this.portalComplaintPersonId = portalComplaintPersonId;
    }

    @Basic
    @Column(name = "portal_complain_person_name", length = 50)
    public String getPortalComplaintPersonName() {
        return portalComplaintPersonName;
    }

    public void setPortalComplaintPersonName(String portalComplaintPersonName) {
        this.portalComplaintPersonName = portalComplaintPersonName;
    }

    @Basic
    @Column(name = "emotion", length = 16)
    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    @Basic
    @Column(name = "relate_number", length = 50)
    public String getRelatedNumber() {
        return relatedNumber;
    }

    public void setRelatedNumber(String relatedNumber) {
        this.relatedNumber = relatedNumber;
    }

    @Basic
    @Column(name = "classify_complaints", length = 50)
    public String getClassificationComplaints() {
        return classificationComplaints;
    }

    public void setClassificationComplaints(String classificationComplaints) {
        this.classificationComplaints = classificationComplaints;
    }

    @Basic
    @Column(name = "up_grade", length = 50)
    public String getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(String upgrade) {
        this.upgrade = upgrade;
    }

    @Basic
    @Column(name = "complain_phone", length = 50)
    public String getComplaintPhone() {
        return complaintPhone;
    }

    public void setComplaintPhone(String complaintPhone) {
        this.complaintPhone = complaintPhone;
    }

    @Basic
    @Column(name = "comlaints_des", length = 500)
    public String getComplaintsDescribes() {
        return complaintsDescribes;
    }

    public void setComplaintsDescribes(String complaintsDescribes) {
        this.complaintsDescribes = complaintsDescribes;
    }

    @Basic
    @Column(name = "submit_time")
    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    @Basic
    @Column(name = "limited_reply_time")
    public Date getLimitedReplyTime() {
        return limitedReplyTime;
    }

    public void setLimitedReplyTime(Date limitedReplyTime) {
        this.limitedReplyTime = limitedReplyTime;
    }

    @Basic
    @Column(name = "time_out", length = 6)
    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    @Basic
    @Column(name = "complain_source", length = 50)
    public String getComplaintSource() {
        return complaintSource;
    }

    public void setComplaintSource(String complaintSource) {
        this.complaintSource = complaintSource;
    }

    @Basic
    @Column(name = "complain_canal", length = 50)
    public String getComplainCanal() {
        return complainCanal;
    }

    public void setComplainCanal(String complainCanal) {
        this.complainCanal = complainCanal;
    }

    @Basic
    @Column(name = "complain_level", length = 50)
    public String getComplaintLevel() {
        return complaintLevel;
    }

    public void setComplaintLevel(String complaintLevel) {
        this.complaintLevel = complaintLevel;
    }

    @Basic
    @Column(name = "major_complain_reason", length = 500)
    public String getMajorComplaintReason() {
        return majorComplaintReason;
    }

    public void setMajorComplaintReason(String majorComplaintReason) {
        this.majorComplaintReason = majorComplaintReason;
    }

    @Basic
    @Column(name = "important_complain_reason", length = 500)
    public String getImportantComplaintReason() {
        return importantComplaintReason;
    }

    public void setImportantComplaintReason(String importantComplaintReason) {
        this.importantComplaintReason = importantComplaintReason;
    }

    @Basic
    @Column(name = "whether_swarmsUes", length = 6)
    public String getWhetherSwarmsUes() {
        return whetherSwarmsUes;
    }

    public void setWhetherSwarmsUes(String whetherSwarmsUes) {
        this.whetherSwarmsUes = whetherSwarmsUes;
    }

    @Basic
    @Column(name = "dispatch_time")
    public Date getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(Date dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    @Basic
    @Column(name = "complain_type", length = 50)
    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    @Basic
    @Column(name = "reply_time")
    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    @Basic
    @Column(name = "project_num", length = 50)
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    @Basic
    @Column(name = "return_time")
    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    @Basic
    @Column(name = "last_return_time")
    public Date getLastReturnTime() {
        return lastReturnTime;
    }

    public void setLastReturnTime(Date lastReturnTime) {
        this.lastReturnTime = lastReturnTime;
    }

    @Basic
    @Column(name = "owner_version",length = 2000)
    public String getOwnerVersion() {
        return ownerVersion;
    }

    public void setOwnerVersion(String ownerVersion) {
        this.ownerVersion = ownerVersion;
    }
    @Basic
    @Column(name = "CREATE_BY",length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    @Basic
    @Column(name = "CREATE_ON")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
    @Basic
    @Column(name = "CREATE_BY_NAME", length = 50)
    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }
}
