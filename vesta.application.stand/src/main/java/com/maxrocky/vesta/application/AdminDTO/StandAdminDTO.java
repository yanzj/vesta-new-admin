package com.maxrocky.vesta.application.AdminDTO;

import java.util.List;

/**
 * Created by chen on 2016/5/24.
 */
public class StandAdminDTO {
    private String standId;        //主键
    private String caseName;       //工序名称
    private String projectId;      //项目ID
    private String projectName;    //项目名称
    private String standPlace;     //旁站位置
    private String standTime;      //旁站时间
    private String standDesc;      //旁站说明
    private String standUserId;    //旁站人ID
    private String standUser;      //旁站人
    private String status;         //状态
    private List<RecodeAdminDTO> recodeList;  //记录列表

    public String getStandId() {
        return standId;
    }

    public void setStandId(String standId) {
        this.standId = standId;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
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

    public String getStandDesc() {
        return standDesc;
    }

    public void setStandDesc(String standDesc) {
        this.standDesc = standDesc;
    }

    public String getStandPlace() {
        return standPlace;
    }

    public void setStandPlace(String standPlace) {
        this.standPlace = standPlace;
    }

    public String getStandTime() {
        return standTime;
    }

    public void setStandTime(String standTime) {
        this.standTime = standTime;
    }

    public String getStandUser() {
        return standUser;
    }

    public void setStandUser(String standUser) {
        this.standUser = standUser;
    }

    public String getStandUserId() {
        return standUserId;
    }

    public void setStandUserId(String standUserId) {
        this.standUserId = standUserId;
    }

    public List<RecodeAdminDTO> getRecodeList() {
        return recodeList;
    }

    public void setRecodeList(List<RecodeAdminDTO> recodeList) {
        this.recodeList = recodeList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
