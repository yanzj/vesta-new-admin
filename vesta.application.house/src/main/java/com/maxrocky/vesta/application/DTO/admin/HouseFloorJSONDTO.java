package com.maxrocky.vesta.application.DTO.admin;

/**
 * Created by Magic on 2016/6/3.
 */
public class HouseFloorJSONDTO {
    private String floor;//楼层
    private String floorid;//楼层id
    private String floornum;//楼层编码
    private String plannum;         //活动编码
    private String projectnum;      //项目编码
    private String unitnum;


    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getFloorid() {
        return floorid;
    }

    public void setFloorid(String floorid) {
        this.floorid = floorid;
    }

    public String getFloornum() {
        return floornum;
    }

    public void setFloornum(String floornum) {
        this.floornum = floornum;
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

    public String getUnitnum() {
        return unitnum;
    }

    public void setUnitnum(String unitnum) {
        this.unitnum = unitnum;
    }
}
