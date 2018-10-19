package com.maxrocky.vesta.application.inspectionPlan.DTO;

/**
 * Created by Magic on 2017/6/20.
 */
public class PlanConditionDTO {
    private String planName;//检查计划名称
    private String planStart;//检查计划开始时间
    private String planEnd;//检查计划结束时间
    private String projectId;//项目公司id
    private String groupId;//集团公司id
    private String areaId;//区域公司id
    private String createDate;//创建时间
    private String state;//状态

    public PlanConditionDTO(){
        this.groupId="";
        this.areaId="";
        this.planName="";//检查计划名称
        this.planStart="";//检查计划开始时间
        this.planEnd="";//检查计划结束时间
        this.projectId="";//创建人所属单位id
        this.createDate="";//创建时间
        this.state="";//状态
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
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


    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
}
