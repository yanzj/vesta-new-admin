package com.maxrocky.vesta.application.dto.adminDTO.batch;

/**
 * Created by hp on 2018/1/8.
 */
public class FunctionProjectDTO {

    private String function;
    private String projectId;

    public FunctionProjectDTO() {
        this.function = "";
        this.projectId = "";
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
