package com.maxrocky.vesta.application.jsonDTO;

import java.util.List;

/**
 * Created by chen on 2016/5/23.
 */
public class MaterialAllDTO {
    private String projectId;
    private String projectName;
    private List<MaterialPlanDTO> materialPlanList;

    public List<MaterialPlanDTO> getMaterialPlanList() {
        return materialPlanList;
    }

    public void setMaterialPlanList(List<MaterialPlanDTO> materialPlanList) {
        this.materialPlanList = materialPlanList;
    }

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
}
