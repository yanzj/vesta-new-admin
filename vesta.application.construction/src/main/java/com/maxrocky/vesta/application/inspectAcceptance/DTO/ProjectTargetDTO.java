package com.maxrocky.vesta.application.inspectAcceptance.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JAIZEFNEG on 2016/10/20.
 */
public class ProjectTargetDTO {
    private String targetId;//检查指标ID
    private String targetDescripion;//指标描述
    private String examinetTargetId;//检查验收指标ID
    private String examinetTargetDetailId;//验收指标详情id
    private String targetName;//检查指标名称
    private String description;//指标描述
    private String targetImgUrl;//指标图片地址
    private String isQualifiedForTarget;//指标是否合格 1：合格；0：不合格
    private String changeTime;//整改时间
    private String acceptanceTime;//验收时间
    private List<ProjectTargetDTO> projectTargetChangeDTOList;//整改记录
    private List<ProjectTargetDTO> projectTargetAcceptanceDTOList;//验收记录

    public ProjectTargetDTO() {
        this.targetDescripion="";
        this.examinetTargetDetailId="";
        this.examinetTargetId = "";//检查验收指标ID
        this.targetId = "";//检查指标ID
        this.targetName = "";//检查指标名称
        this.description = "";//指标描述
        this.targetImgUrl = "";//指标图片地址
        this.isQualifiedForTarget = "";//指标是否合格 1：合格；0：不合格
        this.changeTime = "";//整改时间
        this.acceptanceTime = "";//验收时间
        this.projectTargetChangeDTOList = new ArrayList<ProjectTargetDTO>();//整改记录
        this.projectTargetAcceptanceDTOList = new ArrayList<ProjectTargetDTO>();//验收记录
    }

    public String getTargetDescripion() {
        return targetDescripion;
    }

    public void setTargetDescripion(String targetDescripion) {
        this.targetDescripion = targetDescripion;
    }

    public String getExaminetTargetDetailId() {
        return examinetTargetDetailId;
    }

    public void setExaminetTargetDetailId(String examinetTargetDetailId) {
        this.examinetTargetDetailId = examinetTargetDetailId;
    }

    public String getExaminetTargetId() {
        return examinetTargetId;
    }

    public void setExaminetTargetId(String examinetTargetId) {
        this.examinetTargetId = examinetTargetId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public String getAcceptanceTime() {
        return acceptanceTime;
    }

    public void setAcceptanceTime(String acceptanceTime) {
        this.acceptanceTime = acceptanceTime;
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

    public String getTargetImgUrl() {
        return targetImgUrl;
    }

    public void setTargetImgUrl(String targetImgUrl) {
        this.targetImgUrl = targetImgUrl;
    }

    public String getIsQualifiedForTarget() {
        return isQualifiedForTarget;
    }

    public void setIsQualifiedForTarget(String isQualifiedForTarget) {
        this.isQualifiedForTarget = isQualifiedForTarget;
    }

    public List<ProjectTargetDTO> getProjectTargetChangeDTOList() {
        return projectTargetChangeDTOList;
    }

    public void setProjectTargetChangeDTOList(List<ProjectTargetDTO> projectTargetChangeDTOList) {
        this.projectTargetChangeDTOList = projectTargetChangeDTOList;
    }

    public List<ProjectTargetDTO> getProjectTargetAcceptanceDTOList() {
        return projectTargetAcceptanceDTOList;
    }

    public void setProjectTargetAcceptanceDTOList(List<ProjectTargetDTO> projectTargetAcceptanceDTOList) {
        this.projectTargetAcceptanceDTOList = projectTargetAcceptanceDTOList;
    }
}
