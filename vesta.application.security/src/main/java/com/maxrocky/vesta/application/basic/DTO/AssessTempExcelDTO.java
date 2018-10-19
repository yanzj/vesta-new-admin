package com.maxrocky.vesta.application.basic.DTO;

/**
 * Created by Jason on 2017/6/15.
 */
public class AssessTempExcelDTO {
    private String assessmentName;//考核项目名称
    private String assessmentScore;//考核分数
    private String contentNumber;//内容编号
    private String assessmentContent;//考核内容
    private String grade;//等级
    private String deductionPrinciple;//扣分原则

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

    public String getContentNumber() {
        return contentNumber;
    }

    public void setContentNumber(String contentNumber) {
        this.contentNumber = contentNumber;
    }

    public String getAssessmentContent() {
        return assessmentContent;
    }

    public void setAssessmentContent(String assessmentContent) {
        this.assessmentContent = assessmentContent;
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
}
