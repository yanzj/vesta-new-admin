package com.maxrocky.vesta.application.inspectionPlan.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 检查计划
 * Created by Magic on 2017/6/12.
 */
public class InspectionPlanDTO {
    private String id;//自增id
    private String modifyDate;//修改时间
    private String planId;//检查计划id
    private String planName;//检查计划名称
    private String createId;//创建人id
    private String createName;//创建人姓名
    private String createDate;//创建时间
    private String createUnitId;//创建人所属单位id  检查单位
    private String createUnitName;//创建人所属单位   检查单位
    private String planStart;//检查计划开始时间
    private String planEnd;//检查计划结束时间
    private String participant;//参加人员
    private String state;//状态
    private String score;//得分
    private String type;// 1、集团建的检查计划   2、区域建立的检查计划  3、项目自查
    private String fraction;//总分数
    private String appId;//唯一校验  手机端生成id
    private List<InspectionUnitDTO> inUnitList;//检查单位List
    private List<InspectionUnitDTO> unitList;//被检单位List
    private List<ImageDTO> imageList;//签字图片
    public InspectionPlanDTO(){
        this.fraction="0";
        this.type="";
        this.state="";
        this.imageList=new ArrayList<>();
        this.inUnitList=new ArrayList<>();
        this.unitList=new ArrayList<>();
        this.id="";//自增id
        this.modifyDate="";//修改时间
        this.planId="";//检查计划id
        this.planName="";//检查计划名称
        this.createId="";//创建人id
        this.createName="";//创建人姓名
        this.createDate="";//创建时间
        this.createUnitId="";//创建人所属单位id
        this.createUnitName="";//创建人所属单位
        this.planStart="";//检查计划开始时间
        this.planEnd="";//检查计划结束时间
        this.participant="";//参加人员
        this.score="0";//总分数
        this.appId="";//唯一校验  手机端生成id
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
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


    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }



    public List<InspectionUnitDTO> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<InspectionUnitDTO> unitList) {
        this.unitList = unitList;
    }


    public List<ImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageDTO> imageList) {
        this.imageList = imageList;
    }



    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPlanEnd() {
        return planEnd;
    }

    public void setPlanEnd(String planEnd) {
        this.planEnd = planEnd;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public List<InspectionUnitDTO> getInUnitList() {
        return inUnitList;
    }

    public void setInUnitList(List<InspectionUnitDTO> inUnitList) {
        this.inUnitList = inUnitList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFraction() {
        return fraction;
    }

    public void setFraction(String fraction) {
        this.fraction = fraction;
    }
}
