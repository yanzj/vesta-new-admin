package com.maxrocky.vesta.application.dto.adminDTO;

/**
 * Created by chen on 2016/6/2.
 */
public class OrganizeProjectDTO {
    private String organizeId;    //组ID
    private String projectId;     //项目ID
    private String projectCode;   //项目编码
    private String projectName;   //项目名

    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
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

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
}
