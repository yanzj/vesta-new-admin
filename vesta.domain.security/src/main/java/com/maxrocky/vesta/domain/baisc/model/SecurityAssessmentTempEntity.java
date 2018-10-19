package com.maxrocky.vesta.domain.baisc.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 安全考核模板
 * Created by Jason on 2017/6/15.
 */
@Entity
@Table(name = "st_assessment_temp")
public class SecurityAssessmentTempEntity {
    private int id;//自增长ID
    private String assessmentId;//考核ID
    private String assessmentName;//考核项目名称
    private String assessmentScore;//考核分数
    private String assessmentContent;//考核内容
    private String contentNumber;//内容编号
    private String grade;//等级
    private String deductionPrinciple;//扣分原则
    private String createBy;//创建人
    private Date createDate;//创建时间
    private String modifyBy;//修改人
    private Date modifyDate;//修改时间
    private String state;//状态
    private String domain;//所属模块 1：区域；2：项目公司；3：监理；4：施工单位
    private String parentId;//父级ID
    private String level;//级别
    private String isRedLine;//是否红线 0：否；1：是
    private String isBonusPoints;//是否加分 0：否；1：是

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ASSESSMENT_ID", length = 50)
    public String getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(String assessmentId) {
        this.assessmentId = assessmentId;
    }

    @Basic
    @Column(name = "ASSESSMENT_NAME", length = 100)
    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    @Basic
    @Column(name = "ASSESSMENT_SCORE", length = 50)
    public String getAssessmentScore() {
        return assessmentScore;
    }

    public void setAssessmentScore(String assessmentScore) {
        this.assessmentScore = assessmentScore;
    }

    @Basic
    @Column(name = "ASSESSMENT_CONTENT", length = 2000)
    public String getAssessmentContent() {
        return assessmentContent;
    }

    public void setAssessmentContent(String assessmentContent) {
        this.assessmentContent = assessmentContent;
    }

    @Basic
    @Column(name = "CONTENT_NUM", length = 50)
    public String getContentNumber() {
        return contentNumber;
    }

    public void setContentNumber(String contentNumber) {
        this.contentNumber = contentNumber;
    }

    @Basic
    @Column(name = "ASSESSMENT_GRADE", length = 50)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "DEDUCTION_PRINCIPLE", length = 2000)
    public String getDeductionPrinciple() {
        return deductionPrinciple;
    }

    public void setDeductionPrinciple(String deductionPrinciple) {
        this.deductionPrinciple = deductionPrinciple;
    }

    @Basic
    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "MODIFY_DATE")
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Basic
    @Column(name = "CREATE_BY", length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "MODIFY_BY", length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "STATE", length = 50)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "ASSESSMENT_DOMAIN", length = 50)
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Basic
    @Column(name = "ASSESSMENT_PARENT_ID", length = 50)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "ASSESSMENT_LEVEL", length = 50)
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    @Basic
    @Column(name = "IS_REDLINE", length = 6)
    public String getIsRedLine() {
        return isRedLine;
    }

    public void setIsRedLine(String isRedLine) {
        this.isRedLine = isRedLine;
    }
    @Basic
    @Column(name = "IS_BONUSPOINTS", length = 6)
    public String getIsBonusPoints() {
        return isBonusPoints;
    }

    public void setIsBonusPoints(String isBonusPoints) {
        this.isBonusPoints = isBonusPoints;
    }
}
