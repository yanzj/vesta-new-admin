package com.maxrocky.vesta.application.dailyPatrolInspection.DTO;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiazefeng on 2016/10/24.
 */
public class FromPartyBProblemSolvingDTO {
    private String inspectionId;//巡检_ID  关联 project_inspection ——id
    private String description;//描述
    private String state;//状态
    private String appId;//app生成的id
    private List<InspectionImageDTO> imageList;//整改图片
    private int frequency;//次数

    public FromPartyBProblemSolvingDTO() {
        this.frequency=0;
        this.appId="";
        this.state="";
        this.inspectionId = "";
        this.description = "";
        this.imageList = new ArrayList<InspectionImageDTO>();
    }


    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<InspectionImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<InspectionImageDTO> imageList) {
        this.imageList = imageList;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
