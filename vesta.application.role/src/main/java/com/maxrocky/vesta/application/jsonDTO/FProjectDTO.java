package com.maxrocky.vesta.application.jsonDTO;

import java.util.List;

/**
 * Created by chen on 2016/10/25.
 */
public class FProjectDTO {
    private String projectId;         //项目ID
    private String projectName;       //项目名
    List<PurviewNameDTO> purviewList; //角色列表

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<PurviewNameDTO> getPurviewList() {
        return purviewList;
    }

    public void setPurviewList(List<PurviewNameDTO> purviewList) {
        this.purviewList = purviewList;
    }
}
