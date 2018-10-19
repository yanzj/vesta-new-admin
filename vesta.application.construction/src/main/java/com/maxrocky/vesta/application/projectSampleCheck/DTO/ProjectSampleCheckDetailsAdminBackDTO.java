package com.maxrocky.vesta.application.projectSampleCheck.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2017/1/3.
 * 样板点评指标信息
 */
public class ProjectSampleCheckDetailsAdminBackDTO {
    private String id;//id
    private String sampleCheckId;//样板点评ID
    private String targetId;//指标ID
    private String targetName;//指标名
    private String description;//描述
    private String guide;//指引
    private String state;//状态
    private String createOn;//创建时间
    private String modifyDate;//修改时间
    private String flag;//审核标示
    private List<SampleCheckImageDTO> imageList;
    private List<SampleCheckImageDTO> image2List;
   private List<ProjectSampleCheckChangedAdminBackDTO> targetDTOByPartyBAnnalList; //乙方整改信息
   private List<ProjectSampleCheckChangedAdminBackDTO> targetDTOBySupervisionAnnalList;//监理整改信息
   private List<ProjectSampleCheckChangedAdminBackDTO> targetDTOByPartyAAnnalList;//甲方整改信息
    public ProjectSampleCheckDetailsAdminBackDTO(){
        this.id="";
        this.sampleCheckId="";
        this.targetId="";
        this.targetName="";
        this.description="";
        this.guide="";
        this.state="";
        this.createOn="";
        this.modifyDate="";
        this.flag="";
        this.imageList=new ArrayList<SampleCheckImageDTO>();
        this.image2List=new ArrayList<SampleCheckImageDTO>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSampleCheckId() {
        return sampleCheckId;
    }

    public void setSampleCheckId(String sampleCheckId) {
        this.sampleCheckId = sampleCheckId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public List<SampleCheckImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<SampleCheckImageDTO> imageList) {
        this.imageList = imageList;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<SampleCheckImageDTO> getImage2List() {
        return image2List;
    }

    public void setImage2List(List<SampleCheckImageDTO> image2List) {
        this.image2List = image2List;
    }

    public List<ProjectSampleCheckChangedAdminBackDTO> getTargetDTOByPartyBAnnalList() {
        return targetDTOByPartyBAnnalList;
    }

    public void setTargetDTOByPartyBAnnalList(List<ProjectSampleCheckChangedAdminBackDTO> targetDTOByPartyBAnnalList) {
        this.targetDTOByPartyBAnnalList = targetDTOByPartyBAnnalList;
    }

    public List<ProjectSampleCheckChangedAdminBackDTO> getTargetDTOBySupervisionAnnalList() {
        return targetDTOBySupervisionAnnalList;
    }

    public void setTargetDTOBySupervisionAnnalList(List<ProjectSampleCheckChangedAdminBackDTO> targetDTOBySupervisionAnnalList) {
        this.targetDTOBySupervisionAnnalList = targetDTOBySupervisionAnnalList;
    }

    public List<ProjectSampleCheckChangedAdminBackDTO> getTargetDTOByPartyAAnnalList() {
        return targetDTOByPartyAAnnalList;
    }

    public void setTargetDTOByPartyAAnnalList(List<ProjectSampleCheckChangedAdminBackDTO> targetDTOByPartyAAnnalList) {
        this.targetDTOByPartyAAnnalList = targetDTOByPartyAAnnalList;
    }
}
