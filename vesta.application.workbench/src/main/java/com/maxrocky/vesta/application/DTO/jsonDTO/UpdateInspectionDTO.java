package com.maxrocky.vesta.application.DTO.jsonDTO;

/**
 * Created by maxrocky on 2017/6/30.
 */
public class UpdateInspectionDTO {
    private String projectNum;

    public UpdateInspectionDTO(){
        this.projectNum="";
    }
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }
}
