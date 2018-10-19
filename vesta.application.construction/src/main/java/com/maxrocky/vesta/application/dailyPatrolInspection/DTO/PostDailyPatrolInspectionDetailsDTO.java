package com.maxrocky.vesta.application.dailyPatrolInspection.DTO;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/10/24.
 */
public class PostDailyPatrolInspectionDetailsDTO {
    private String id;//ID
    private String inspectionId;//巡检_ID  关联 project_inspection ——id
    private String description;//描述
    private String createBy;//创建人
    private String createName;//创建人姓名
    private String createOn;//创建时间
    private String type;//0 乙方整改  1 第三方监理整改   2 甲方整改
    private List<InspectionImageDTO> imageList;//整改图片
    private int frequency;//次数
    private String detailsState;//状态
    public PostDailyPatrolInspectionDetailsDTO(){
        this.detailsState="";
        this.frequency=0;
        this.id="";
        this.inspectionId="";
        this.description="";
        this.createName="";
        this.createBy="";
        this.createOn="";
        this.type="";
        this.imageList=new ArrayList<InspectionImageDTO>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public List<InspectionImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<InspectionImageDTO> imageList) {
        this.imageList = imageList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getDetailsState() {
        return detailsState;
    }

    public void setDetailsState(String detailsState) {
        this.detailsState = detailsState;
    }
}
