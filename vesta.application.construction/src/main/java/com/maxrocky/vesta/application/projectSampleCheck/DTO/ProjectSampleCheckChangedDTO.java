package com.maxrocky.vesta.application.projectSampleCheck.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2017/1/3.
 * 样板点评指标整改信息
 */
public class ProjectSampleCheckChangedDTO {
    private String id;//ID
    private String checkDetailsId;//指标ID
    private String description;//描述
    private String changeTime;//整改时间
    private String createBy;//创建人
    private String createOn;//创建时间
    private String type;//  1 甲方整改 2 第三方监理整改 3 乙方整改
    private String state;//指标状态
    private String serialNumber;//排序号
    private List<SampleCheckImageDTO> imageList;//整改图片
    public ProjectSampleCheckChangedDTO(){
        this.id="";
        this.checkDetailsId="";
        this.description="";
        this.changeTime="";
        this.createBy="";
        this.createOn="";
        this.type="";
        this.state="";
        this.serialNumber="";
        this.imageList=new ArrayList<SampleCheckImageDTO>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCheckDetailsId() {
        return checkDetailsId;
    }

    public void setCheckDetailsId(String checkDetailsId) {
        this.checkDetailsId = checkDetailsId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public List<SampleCheckImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<SampleCheckImageDTO> imageList) {
        this.imageList = imageList;
    }
}
