package com.maxrocky.vesta.application.JsonDTO;

import java.util.List;

/**
 * Created by chen on 2016/5/18.
 */
public class SideStandDTO {
    private String standId;         //旁站ID
    private String projectId;       //项目ID
    private String caseName;        //工序名
    private String standTime;       //旁站时间
    private String staffId;         //旁站人ID
    private String standPeople;     //旁站人名字
    private String peopleRole;      //旁站人角色
    private String standDesc;       //旁站说明
    private String standPlace;      //旁站位置
    List<RecodeDTO> recodeList;     //旁站记录

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

    public List<RecodeDTO> getRecodeList() {
        return recodeList;
    }

    public void setRecodeList(List<RecodeDTO> recodeList) {
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

    public String getStandId() {
        return standId;
    }

    public void setStandId(String standId) {
        this.standId = standId;
    }

    public String getStandTime() {
        return standTime;
    }

    public void setStandTime(String standTime) {
        this.standTime = standTime;
    }

    public String getStandPlace() {
        return standPlace;
    }

    public void setStandPlace(String standPlace) {
        this.standPlace = standPlace;
    }

    public String getPeopleRole() {
        return peopleRole;
    }

    public void setPeopleRole(String peopleRole) {
        this.peopleRole = peopleRole;
    }

    public String getStandPeople() {
        return standPeople;
    }

    public void setStandPeople(String standPeople) {
        this.standPeople = standPeople;
    }
}
