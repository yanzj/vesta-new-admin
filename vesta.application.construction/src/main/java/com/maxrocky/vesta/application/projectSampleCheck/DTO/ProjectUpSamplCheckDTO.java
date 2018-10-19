package com.maxrocky.vesta.application.projectSampleCheck.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2017/1/10.
 */
public class ProjectUpSamplCheckDTO {
    private String sampleCheckId;//样板点评ID
    private String state;//状态
    private String type;//1 甲方  2 监理  3  乙方
    private List<ProjectSampleCheckDetailsDTO> sampleCheckDetails;//样板点评指标信息
    public ProjectUpSamplCheckDTO(){
        this.sampleCheckDetails=new ArrayList<>();
        this.state="";
        this.type="";
        this.sampleCheckId="";
    }

    public String getSampleCheckId() {
        return sampleCheckId;
    }

    public void setSampleCheckId(String sampleCheckId) {
        this.sampleCheckId = sampleCheckId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ProjectSampleCheckDetailsDTO> getSampleCheckDetails() {
        return sampleCheckDetails;
    }

    public void setSampleCheckDetails(List<ProjectSampleCheckDetailsDTO> sampleCheckDetails) {
        this.sampleCheckDetails = sampleCheckDetails;
    }
}
