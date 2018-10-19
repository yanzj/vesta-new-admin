package com.maxrocky.vesta.application.DTO.admin;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2016/5/31.
 */
public class DeliveryPlanJsonDTO {
    private String id;//计划id
    private String projectNum;//项目编号_关联的项目信息new_deliveryplan的项目编码——BJ-YZJMY-X88-10-01-1001
    private String planNum;//计划编号_FXBJ001JFJH201603310001
    private String planName;//计划名称_
    private String planStart;//计划开始时间 2016/4/5_集中交付开始时间

    private List<RectifyJsonListDTO> roomList; //房间lsit
    private List<HouseBuildingJSONDTO> buildingList;//楼栋list
    private List<HouseUnitJSONDTO> unitList;//单元list
    private List<HouseFloorJSONDTO> floorList;//楼层
    public DeliveryPlanJsonDTO(){
        this.floorList=new ArrayList<>();
        this.roomList=new ArrayList<>();
        this.buildingList=new ArrayList<>();
        this.unitList=new ArrayList<>();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanStart() {
        return planStart;
    }

    public void setPlanStart(String planStart) {
        this.planStart = planStart;
    }

    public List<RectifyJsonListDTO> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<RectifyJsonListDTO> roomList) {
        this.roomList = roomList;
    }

    public List<HouseBuildingJSONDTO> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<HouseBuildingJSONDTO> buildingList) {
        this.buildingList = buildingList;
    }

    public List<HouseUnitJSONDTO> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<HouseUnitJSONDTO> unitList) {
        this.unitList = unitList;
    }

    public List<HouseFloorJSONDTO> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<HouseFloorJSONDTO> floorList) {
        this.floorList = floorList;
    }
}
