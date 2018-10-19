package com.maxrocky.vesta.application.inspectionPlan.DTO;

/**
 * Created by magic on 2017/6/20.
 */
public class PlanListDTO {
    private String planId;//检查计划id
    private String planName;//检查计划名称
    private String participant;//参加人员
    private String state;//状态
    private String score;//得分
    private String createUnitId;//
    private String createUnitName;//创建人所属单位
    private String planStart;//检查计划开始时间
    private String planEnd;//检查计划结束时间
    private String createId;//创建人id
    private String createName;//创建人姓名
    private String createDate;//创建时间

    public PlanListDTO(){
        this.planId="";//检查计划id
        this.planName="";//检查计划名称
        this.participant="";//参加人员
        this.state="";//状态
        this.score="";//得分
        this.createUnitName="";//创建人所属单位
        this.planStart="";//检查计划开始时间
        this.planEnd="";//检查计划结束时间
        this.createId="";//创建人id
        this.createName="";//创建人姓名
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCreateUnitName() {
        return createUnitName;
    }

    public void setCreateUnitName(String createUnitName) {
        this.createUnitName = createUnitName;
    }

    public String getPlanStart() {
        return planStart;
    }

    public void setPlanStart(String planStart) {
        this.planStart = planStart;
    }

    public String getPlanEnd() {
        return planEnd;
    }

    public void setPlanEnd(String planEnd) {
        this.planEnd = planEnd;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }


    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateUnitId() {
        return createUnitId;
    }

    public void setCreateUnitId(String createUnitId) {
        this.createUnitId = createUnitId;
    }
}
