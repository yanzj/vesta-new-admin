package com.maxrocky.vesta.application.projectSampleCheck.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2017/1/3.
 */
public class ProjectSampleCheckListDTO {
    private List<ProjectSampleCheckDTO> list;
    private String id;
    private String timeStamp;
    private String projectId;
    public ProjectSampleCheckListDTO(){
        this.id="";
        this.timeStamp="";
        this.projectId="";
        this.list=new ArrayList<ProjectSampleCheckDTO>();
    }

    public List<ProjectSampleCheckDTO> getList() {
        return list;
    }

    public void setList(List<ProjectSampleCheckDTO> list) {
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
