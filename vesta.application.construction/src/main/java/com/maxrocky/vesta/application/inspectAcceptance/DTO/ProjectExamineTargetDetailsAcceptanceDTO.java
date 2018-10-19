package com.maxrocky.vesta.application.inspectAcceptance.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JAIZEFNEG on 2016/10/20.
 */
public class ProjectExamineTargetDetailsAcceptanceDTO {
    private String examinetTargetId;//检查验收指标ID
    private String examinetTargetDetailId;//验收指标详情id
    private String description;//指标描述
    private String imageUrl;//指标图片地址
    private String isQualifiedForTarget;//指标是否合格 1：合格；0：不合格

    public ProjectExamineTargetDetailsAcceptanceDTO() {
        this.examinetTargetDetailId = "";
        this.examinetTargetId = "";//检查验收指标ID
        this.description = "";//指标描述
        this.imageUrl = "";//指标图片地址
        this.isQualifiedForTarget = "";//指标是否合格 1：合格；0：不合格
    }

    public String getExaminetTargetId() {
        return examinetTargetId;
    }

    public void setExaminetTargetId(String examinetTargetId) {
        this.examinetTargetId = examinetTargetId;
    }

    public String getExaminetTargetDetailId() {
        return examinetTargetDetailId;
    }

    public void setExaminetTargetDetailId(String examinetTargetDetailId) {
        this.examinetTargetDetailId = examinetTargetDetailId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIsQualifiedForTarget() {
        return isQualifiedForTarget;
    }

    public void setIsQualifiedForTarget(String isQualifiedForTarget) {
        this.isQualifiedForTarget = isQualifiedForTarget;
    }
}
