package com.maxrocky.vesta.application.baseData.JsonDTO;

/**
 * Created by chen on 2016/10/25.
 */
public class ProjectDataDTO {
    private String projectId="";          //项目ID
    private String projectName="";        //项目名称
    private String status="";             //状态
    private String timeStamp="";          //修改时间戳
    private String id="";                 //自增长ID


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
