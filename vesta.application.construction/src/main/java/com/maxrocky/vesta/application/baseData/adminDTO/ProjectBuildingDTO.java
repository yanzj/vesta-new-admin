package com.maxrocky.vesta.application.baseData.adminDTO;

/**
 * Created by chen on 2016/10/18.
 * 工程楼栋数据封装类
 */
public class ProjectBuildingDTO {
    private String buildName; //楼栋名称
    private String buildId;   //楼栋ID
    private String createBy;  //创建人
    private String floorNum;  //楼层数
    private String createOn;  //创建时间
    private String startFloor;//开始楼层
    private String endFloor;//结束楼层

    public String getStartFloor() {
        return startFloor;
    }

    public void setStartFloor(String startFloor) {
        this.startFloor = startFloor;
    }

    public String getEndFloor() {
        return endFloor;
    }

    public void setEndFloor(String endFloor) {
        this.endFloor = endFloor;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(String floorNum) {
        this.floorNum = floorNum;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }
}
