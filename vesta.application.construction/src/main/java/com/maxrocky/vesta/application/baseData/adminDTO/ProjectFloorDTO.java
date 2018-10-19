package com.maxrocky.vesta.application.baseData.adminDTO;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by chen on 2016/10/21.
 * 工程楼层封装类
 */
public class ProjectFloorDTO {
    private String projectId;
    private String floorId;       //楼层ID
    private String floorName;     //楼层名
    private String createOn;      //创建时间
    private String modifyOn;      //修改时间
    private String buildId;       //楼栋Id  wss
    private MultipartFile homePageimgpath; //原位标注图  wss
    private MultipartFile homePageimgpathExecute;//批量添加，原位标注图
    private MultipartFile homePageimgpathExecuteUpdate;//批量修改，原位标注图
    private String firstFloorArea;//第一楼层区间
    private String firstFloorAreaUpdate;//第一楼层区间修改
    private String secondFloorArea;//第二楼层区间
    private String secondFloorAreaUpdate;//第二楼层区间修改

    public MultipartFile getHomePageimgpathExecuteUpdate() {
        return homePageimgpathExecuteUpdate;
    }

    public void setHomePageimgpathExecuteUpdate(MultipartFile homePageimgpathExecuteUpdate) {
        this.homePageimgpathExecuteUpdate = homePageimgpathExecuteUpdate;
    }

    public MultipartFile getHomePageimgpathExecute() {
        return homePageimgpathExecute;
    }

    public void setHomePageimgpathExecute(MultipartFile homePageimgpathExecute) {
        this.homePageimgpathExecute = homePageimgpathExecute;
    }

    public String getFirstFloorAreaUpdate() {
        return firstFloorAreaUpdate;
    }

    public void setFirstFloorAreaUpdate(String firstFloorAreaUpdate) {
        this.firstFloorAreaUpdate = firstFloorAreaUpdate;
    }

    public String getSecondFloorAreaUpdate() {
        return secondFloorAreaUpdate;
    }

    public void setSecondFloorAreaUpdate(String secondFloorAreaUpdate) {
        this.secondFloorAreaUpdate = secondFloorAreaUpdate;
    }

    public String getFirstFloorArea() {
        return firstFloorArea;
    }

    public void setFirstFloorArea(String firstFloorArea) {
        this.firstFloorArea = firstFloorArea;
    }

    public String getSecondFloorArea() {
        return secondFloorArea;
    }

    public void setSecondFloorArea(String secondFloorArea) {
        this.secondFloorArea = secondFloorArea;
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public MultipartFile getHomePageimgpath() {
        return homePageimgpath;
    }

    public void setHomePageimgpath(MultipartFile homePageimgpath) {
        this.homePageimgpath = homePageimgpath;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(String modifyOn) {
        this.modifyOn = modifyOn;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
