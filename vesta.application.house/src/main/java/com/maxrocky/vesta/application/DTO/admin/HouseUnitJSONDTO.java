package com.maxrocky.vesta.application.DTO.admin;

/**
 * Created by Magic on 2016/5/31.
 */
public class HouseUnitJSONDTO {
    private String unit;//单元
    private String unitnum;//单元编码
    private String buildingnum;//楼栋编码
    private String plannum;         //活动编码
    private String projectnum;      //项目编码

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitnum() {
        return unitnum;
    }

    public void setUnitnum(String unitnum) {
        this.unitnum = unitnum;
    }

    public String getBuildingnum() {
        return buildingnum;
    }

    public void setBuildingnum(String buildingnum) {
        this.buildingnum = buildingnum;
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
