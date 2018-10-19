package com.maxrocky.vesta.application.DTO.admin;

/**
 * Created by Magic on 2016/5/31.
 */
public class HouseBuildingJSONDTO {

    private String buildingnum;//编码
    private String buildingid;//楼栋id
    private String buildingname;//楼栋名
    private String buildingrecode;//楼栋编号
    private String plannum;         //活动编码
    private String projectnum;      //项目编码

    public String getBuildingnum() {
        return buildingnum;
    }

    public void setBuildingnum(String buildingnum) {
        this.buildingnum = buildingnum;
    }

    public String getBuildingid() {
        return buildingid;
    }

    public void setBuildingid(String buildingid) {
        this.buildingid = buildingid;
    }

    public String getBuildingname() {
        return buildingname;
    }

    public void setBuildingname(String buildingname) {
        this.buildingname = buildingname;
    }

    public String getBuildingrecode() {
        return buildingrecode;
    }

    public void setBuildingrecode(String buildingrecode) {
        this.buildingrecode = buildingrecode;
    }

    public String getPlannum() {
        return plannum;
    }

    public void setPlannum(String plannum) {
        this.plannum = plannum;
    }

    public String getProjectnum() {
        return projectnum;
    }

    public void setProjectnum(String projectnum) {
        this.projectnum = projectnum;
    }
}
