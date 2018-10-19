package com.maxrocky.vesta.domain.model;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by mql on 2016/5/19.
 */
@Entity
@Table(name="plan_case_repair")
public class PlanCaseRepairEntity {
    private String id;//主键
    private int times;//复查次数
    private String caseId;//问题ID
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

    @Id
    @Column(name = "ID",length = 50,unique = true,nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TIMES",length = 100)
    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    @Basic
    @Column(name = "CASE_ID",length = 50)
    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    @Basic
    @Column(name = "REPAIR_BY",length = 50)
    public String getRepairBy() {
        return repairBy;
    }

    public void setRepairBy(String repairBy) {
        this.repairBy = repairBy;
    }

    @Basic
    @Column(name = "REPAIR_PHONE",length = 50)
    public String getRepairPhone() {
        return repairPhone;
    }

    public void setRepairPhone(String repairPhone) {
        this.repairPhone = repairPhone;
    }

    @Basic
    @Column(name = "REPAIR_DATE")
    public Date getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    @Basic
    @Column(name = "REPAIR_DESC",length = 500)
    public String getRepairDesc() {
        return repairDesc;
    }

    public void setRepairDesc(String repairDesc) {
        this.repairDesc = repairDesc;
    }

    @Basic
    @Column(name = "REVIEW_BY",length = 50)
    public String getReviewBy() {
        return reviewBy;
    }

    public void setReviewBy(String reviewBy) {
        this.reviewBy = reviewBy;
    }

    @Basic
    @Column(name = "REVIEW_PHONE",length = 50)
    public String getReviewPhone() {
        return reviewPhone;
    }

    public void setReviewPhone(String reviewPhone) {
        this.reviewPhone = reviewPhone;
    }

    @Basic
    @Column(name = "REVIEW_DATE",length = 50)
    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    @Basic
    @Column(name = "REVIEW_DESC",length = 50)
    public String getReviewDesc() {
        return reviewDesc;
    }

    public void setReviewDesc(String reviewDesc) {
        this.reviewDesc = reviewDesc;
    }

    @Basic
    @Column(name = "SUPERVISOR",length = 50)
    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    @Basic
    @Column(name = "SUPERVISOR_PHONE",length = 50)
    public String getSupervisorPhone() {
        return supervisorPhone;
    }

    public void setSupervisorPhone(String supervisorPhone) {
        this.supervisorPhone = supervisorPhone;
    }

    @Basic
    @Column(name = "SUPERVISOR_DATE",length = 50)
    public Date getSupervisorDate() {
        return supervisorDate;
    }

    public void setSupervisorDate(Date supervisorDate) {
        this.supervisorDate = supervisorDate;
    }

    @Basic
    @Column(name = "SUPERVISOR_DESC",length = 500)
    public String getSupervisorDesc() {
        return supervisorDesc;
    }

    public void setSupervisorDesc(String supervisorDesc) {
        this.supervisorDesc = supervisorDesc;
    }

    @Basic
    @Column(name = "REVIEW_OPINION",length = 50)
    public String getReviewOpinion() {
        return reviewOpinion;
    }

    public void setReviewOpinion(String reviewOpinion) {
        this.reviewOpinion = reviewOpinion;
    }

    @Basic
    @Column(name = "SUPERVISOR_OPINION",length = 50)
    public String getSupervisorOpinion() {
        return supervisorOpinion;
    }

    public void setSupervisorOpinion(String supervisorOpinion) {
        this.supervisorOpinion = supervisorOpinion;
    }

    @Basic
    @Column(name="REPAIR_NAME",length = 50)
    public String getRepairName() {
        return repairName;
    }

    public void setRepairName(String repairName) {
        this.repairName = repairName;
    }
}