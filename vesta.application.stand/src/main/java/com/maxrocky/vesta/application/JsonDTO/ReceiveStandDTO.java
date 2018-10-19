package com.maxrocky.vesta.application.JsonDTO;

import java.util.List;

/**
 * Created by chen on 2016/5/18.
 */
public class ReceiveStandDTO {
    private String caseName;            //工序名称
    private String standPlace;          //旁站位置
    private String standTime;           //旁站时间
    private String staffId;             //员工ID
    private String standDesc;           //旁站要点
    private String projectId;           //项目ID
    private List<ReceiveRecodeDTO> recodeList;    //记录列表

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

    public List<ReceiveRecodeDTO> getRecodeList() {
        return recodeList;
    }

    public void setRecodeList(List<ReceiveRecodeDTO> recodeList) {
        this.recodeList = recodeList;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
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
}
