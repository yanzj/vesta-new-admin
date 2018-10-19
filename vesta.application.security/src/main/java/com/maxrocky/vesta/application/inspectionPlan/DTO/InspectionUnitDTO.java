package com.maxrocky.vesta.application.inspectionPlan.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 检查计划下单位
 * Created by Magic on 2017/6/12.
 */
public class InspectionUnitDTO {
    private String id;
    private String modifyDate;//修改时间
    private String unitId;//被查单位id uuid
    private String planId;//检查计划id
    private String planName;//检查计划名字
    private String level;//类型 2.区域 3.项目公司
    private String projectId;//项目公司id
    private String projectName;//项目公司名称
    private String state;//状态
    private String rectifyState;//整改状态
    private String score;//得分
    private String fraction;//总分数
//    private List<ProjectScoringDTO> scoringList;//检查项List
    private String planStart;//检查计划开始时间
    private String planEnd;//检查计划结束时间
    private String participant;//参加人员
    private List<ImageDTO> imageList;//签字图片
    public InspectionUnitDTO(){
        this.score="0";
        this.fraction="0";
        this.planStart="";
        this.planEnd="";
        this.participant="";
        this.imageList=new ArrayList<>();
        this.unitId="";//检查单位id uuid
        this.modifyDate="";
        this.id="";
        this.planId="";//检查计划id
        this.planName="";//检查计划名字
        this.level="";//类型
        this.projectId="";//项目公司id
        this.projectName="";//项目公司名称
        this.state="";//状态
        this.rectifyState="";
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRectifyState() {
        return rectifyState;
    }

    public void setRectifyState(String rectifyState) {
        this.rectifyState = rectifyState;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
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

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public List<ImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageDTO> imageList) {
        this.imageList = imageList;
    }

    public String getFraction() {
        return fraction;
    }

    public void setFraction(String fraction) {
        this.fraction = fraction;
    }
}
