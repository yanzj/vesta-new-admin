package com.maxrocky.vesta.application.ProjectMaterial.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Magic on 2016/12/1.
 */
public class ProjectMaterialOutDTO {
    private String id;
    private String materialId;//材料验收ID
    private String outTime;//退场时间
    private String description;//退场描述
    private String createBy;//创建人
    private String createOn;//创建时间
    private String modifyBy;//修改人
    private String modifyOn;//修改时间
    private String appId;//APP传入id唯一校验防止重复
    private List<MaterialImageDTO> imageList;//退场纪录图片List
    public ProjectMaterialOutDTO(){
        this.imageList=new ArrayList<MaterialImageDTO>();
        this.id="";
        this.materialId="";//材料验收ID
        this.outTime="";//退场时间
        this.description="";//退场描述
        this.createBy="";//创建人
        this.createOn="";//创建时间
        this.modifyBy="";//修改人
        this.modifyOn="";//修改时间
        this.appId="";//APP传入id唯一校验防止重复
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

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(String modifyOn) {
        this.modifyOn = modifyOn;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public List<MaterialImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<MaterialImageDTO> imageList) {
        this.imageList = imageList;
    }
}
