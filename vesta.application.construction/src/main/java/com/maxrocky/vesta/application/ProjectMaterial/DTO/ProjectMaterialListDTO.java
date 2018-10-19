package com.maxrocky.vesta.application.ProjectMaterial.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/11/24.
 */
public class ProjectMaterialListDTO {
    private List<ProjectMaterialDTO> list;
    private String id;
    private String timeStamp;
    private String projectId;
    public ProjectMaterialListDTO(){
        this.list=new ArrayList<ProjectMaterialDTO>();
        this.id="";
        this.timeStamp="";
        this.projectId="";
    }
    public List<ProjectMaterialDTO> getList() {
        return list;
    }

    public void setList(List<ProjectMaterialDTO> list) {
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
