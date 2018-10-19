package com.maxrocky.vesta.application.ProjectMaterial.DTO;

/**
 * 材料验收指标DTO
 * Created by Magic on 2016/11/24.
 */
public class ProjectMaterialDetailsDTO {
    private String id;//id
    private String materialId;//材料验收ID
    private String targetId;//指标ID
    private String targetName;//指标名
    private String description;//描述
    private String isQualified;//合格
    private String imageUrl;//指标验收图片链接
    private String modifyDate;//修改时间
    private String guide;//指引
    public ProjectMaterialDetailsDTO(){
        this.guide="";
        this.modifyDate="";
        this.id="";
        this.materialId="";//材料验收ID
        this.targetId="";//指标ID
        this.targetName="";//指标名
        this.description="";//描述
        this.isQualified="";//合格
        this.imageUrl="";//指标验收图片链接
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
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

    public String getIsQualified() {
        return isQualified;
    }

    public void setIsQualified(String isQualified) {
        this.isQualified = isQualified;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }


    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
