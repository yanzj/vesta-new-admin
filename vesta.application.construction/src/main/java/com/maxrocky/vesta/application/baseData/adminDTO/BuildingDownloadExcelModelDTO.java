package com.maxrocky.vesta.application.baseData.adminDTO;

/**
 * Created by Talent on 2016/12/5.
 */
public class BuildingDownloadExcelModelDTO {
    private String buildingName;//楼栋名称
    private String floorStar;//开始楼层
    private String floorEnd;//结束楼层

    public BuildingDownloadExcelModelDTO(String buildingName, String floorStar, String floorEnd) {
        this.buildingName = buildingName;
        this.floorStar = floorStar;
        this.floorEnd = floorEnd;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getFloorStar() {
        return floorStar;
    }

    public void setFloorStar(String floorStar) {
        this.floorStar = floorStar;
    }

    public String getFloorEnd() {
        return floorEnd;
    }

    public void setFloorEnd(String floorEnd) {
        this.floorEnd = floorEnd;
    }
}
