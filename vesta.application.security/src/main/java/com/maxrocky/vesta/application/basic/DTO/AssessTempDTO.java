package com.maxrocky.vesta.application.basic.DTO;

import java.util.Date;

/**
 * Created by Jason on 2017/6/15.
 */
public class AssessTempDTO {
    private String id;//自增长ID
    private String assessmentId;//考核ID
    private String assessmentName;//考核项目名称
    private String assessmentScore;//考核分数
    private String assessmentContent;//考核内容
    private String contentNumber;//内容编号
    private String grade;//等级
    private String deductionPrinciple;//扣分原则
    private String createBy;//创建人
    private String createDate;//创建时间
    private String modifyBy;//修改人
    private String modifyDate;//修改时间
    private String state;//状态
    private String domain;//所属模块 1：区域；2：项目公司；3：监理；4：施工单位

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(String assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public String getAssessmentScore() {
        return assessmentScore;
    }

    public void setAssessmentScore(String assessmentScore) {
        this.assessmentScore = assessmentScore;
    }

    public String getAssessmentContent() {
        return assessmentContent;
    }

    public void setAssessmentContent(String assessmentContent) {
        this.assessmentContent = assessmentContent;
    }

    public String getContentNumber() {
        return contentNumber;
    }

    public void setContentNumber(String contentNumber) {
        this.contentNumber = contentNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDeductionPrinciple() {
        return deductionPrinciple;
    }

    public void setDeductionPrinciple(String deductionPrinciple) {
        this.deductionPrinciple = deductionPrinciple;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
