package com.maxrocky.vesta.application.inspectAcceptance.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JAIZEFNEG on 2016/10/20.
 */
public class ProjectExamineTargetDetailsDTO {
    private String targetId;//检查指标ID
    private String examinetTargetId;//检查验收指标ID
    private String targetName;//检查指标名称
    private String targetDescription;//检查指标描述
    private String description;//指标描述
    private String imageUrl;//指标图片地址
    private String isQualifiedForTarget;//指标是否合格 1：合格；0：不合格
    private List<ProjectExamineTargetDetailsChangeDTO> projectExamineTargetDetailsChangeDTOs;//整改记录
    private List<ProjectExamineTargetDetailsAcceptanceDTO> projectExamineTargetDetailsAcceptanceDTOs;//验收记录

    public ProjectExamineTargetDetailsDTO() {
        this.targetId = "";//检查指标ID
        this.examinetTargetId = "";//检查验收指标ID
        this.targetName = "";//检查指标名称
        this.targetDescription = "";//检查指标描述
        this.description = "";//指标描述
        this.imageUrl = "";//指标图片地址
        this.isQualifiedForTarget = "";//指标是否合格 1：合格；0：不合格
        this.projectExamineTargetDetailsChangeDTOs = new ArrayList<ProjectExamineTargetDetailsChangeDTO>();//整改记录
        this.projectExamineTargetDetailsAcceptanceDTOs = new ArrayList<ProjectExamineTargetDetailsAcceptanceDTO>();//验收记录
    }

    public String getTargetDescription() {
        return targetDescription;
    }

    public void setTargetDescription(String targetDescription) {
        this.targetDescription = targetDescription;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getExaminetTargetId() {
        return examinetTargetId;
    }

    public void setExaminetTargetId(String examinetTargetId) {
        this.examinetTargetId = examinetTargetId;
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


    public String getIsQualifiedForTarget() {
        return isQualifiedForTarget;
    }

    public void setIsQualifiedForTarget(String isQualifiedForTarget) {
        this.isQualifiedForTarget = isQualifiedForTarget;
    }

    public List<ProjectExamineTargetDetailsChangeDTO> getProjectExamineTargetDetailsChangeDTOs() {
        return projectExamineTargetDetailsChangeDTOs;
    }

    public void setProjectExamineTargetDetailsChangeDTOs(List<ProjectExamineTargetDetailsChangeDTO> projectExamineTargetDetailsChangeDTOs) {
        this.projectExamineTargetDetailsChangeDTOs = projectExamineTargetDetailsChangeDTOs;
    }

    public List<ProjectExamineTargetDetailsAcceptanceDTO> getProjectExamineTargetDetailsAcceptanceDTOs() {
        return projectExamineTargetDetailsAcceptanceDTOs;
    }

    public void setProjectExamineTargetDetailsAcceptanceDTOs(List<ProjectExamineTargetDetailsAcceptanceDTO> projectExamineTargetDetailsAcceptanceDTOs) {
        this.projectExamineTargetDetailsAcceptanceDTOs = projectExamineTargetDetailsAcceptanceDTOs;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
