package com.maxrocky.vesta.application.adminDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by mql on 2016/5/19.
 */
public class CaseRepairDTO {

    private String id;//主键
    private int times;//复查次数
    private String caseId;//检查项ID
    private String repairBy;//整改人
    private String repairName;//整改人名称
    private String repairPhone;//整改人联系电话
    private Date repairDate;//整改时间
    private String repairDesc;//整改描述
    private String reviewBy;//复查人
    private String reviewPhone;//复查人联系电话
    private Date reviewDate;//复查时间
    private String reviewOpinion;//复查意见，是否通过
    private String reviewDesc;//复查描述
    private String supervisor;//第三方监理
    private String supervisorPhone;//第三方监理电话
    private Date supervisorDate;//第三方监理复查时间
    private String supervisorOpinion;//第三方复查意见，是否通过
    private String supervisorDesc;//第三方监理复查描述
    private List<QuestionImageDTO> reviewImgList;//复查图片

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getRepairBy() {
        return repairBy;
    }

    public void setRepairBy(String repairBy) {
        this.repairBy = repairBy;
    }

    public String getRepairPhone() {
        return repairPhone;
    }

    public void setRepairPhone(String repairPhone) {
        this.repairPhone = repairPhone;
    }

    public Date getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    public String getRepairDesc() {
        return repairDesc;
    }

    public void setRepairDesc(String repairDesc) {
        this.repairDesc = repairDesc;
    }

    public String getReviewBy() {
        return reviewBy;
    }

    public void setReviewBy(String reviewBy) {
        this.reviewBy = reviewBy;
    }

    public String getReviewPhone() {
        return reviewPhone;
    }

    public void setReviewPhone(String reviewPhone) {
        this.reviewPhone = reviewPhone;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewDesc() {
        return reviewDesc;
    }

    public void setReviewDesc(String reviewDesc) {
        this.reviewDesc = reviewDesc;
    }

    public List<QuestionImageDTO> getReviewImgList() {
        return reviewImgList;
    }

    public void setReviewImgList(List<QuestionImageDTO> reviewImgList) {
        this.reviewImgList = reviewImgList;
    }

    public String getReviewOpinion() {
        return reviewOpinion;
    }

    public void setReviewOpinion(String reviewOpinion) {
        this.reviewOpinion = reviewOpinion;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getSupervisorPhone() {
        return supervisorPhone;
    }

    public void setSupervisorPhone(String supervisorPhone) {
        this.supervisorPhone = supervisorPhone;
    }

    public Date getSupervisorDate() {
        return supervisorDate;
    }

    public void setSupervisorDate(Date supervisorDate) {
        this.supervisorDate = supervisorDate;
    }

    public String getSupervisorOpinion() {
        return supervisorOpinion;
    }

    public void setSupervisorOpinion(String supervisorOpinion) {
        this.supervisorOpinion = supervisorOpinion;
    }

    public String getSupervisorDesc() {
        return supervisorDesc;
    }

    public void setSupervisorDesc(String supervisorDesc) {
        this.supervisorDesc = supervisorDesc;
    }

    public String getRepairName() {
        return repairName;
    }

    public void setRepairName(String repairName) {
        this.repairName = repairName;
    }
}
