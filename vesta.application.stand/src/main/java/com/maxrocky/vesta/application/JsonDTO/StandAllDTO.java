package com.maxrocky.vesta.application.JsonDTO;

import java.util.List;

/**
 * Created by chen on 2016/5/18.
 */
public class StandAllDTO {
    private String projectId;
    private String projectName;
    private List<SideStandDTO> standList;

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

    public List<SideStandDTO> getStandList() {
        return standList;
    }

    public void setStandList(List<SideStandDTO> standList) {
        this.standList = standList;
    }
}
