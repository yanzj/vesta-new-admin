package com.maxrocky.vesta.application.inspectAcceptance.DTO;

/**
 * Created by JAIZEFENG on 2016/10/19.
 */
public class ProjectAcceptanceDTO {
    private String projectId;//项目ID
    private String buildingId;//楼栋ID
    private String buildingName;//楼栋名称
    private int hasBeenGetOn;//已进行
    private String qualified;//合格率
    private int unqualified;//不合格
    private String onePass;//一次通过率

    public ProjectAcceptanceDTO() {
        this.projectId = "";
        this.buildingId = "";
        this.buildingName = "";
        this.hasBeenGetOn = 0;
        this.qualified = "";
        this.unqualified = 0;
        this.onePass = "";
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getHasBeenGetOn() {
        return hasBeenGetOn;
    }

    public void setHasBeenGetOn(int hasBeenGetOn) {
        this.hasBeenGetOn = hasBeenGetOn;
    }



    public int getUnqualified() {
        return unqualified;
    }

    public void setUnqualified(int unqualified) {
        this.unqualified = unqualified;
    }


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getOnePass() {
        return onePass;
    }

    public void setOnePass(String onePass) {
        this.onePass = onePass;
    }

    public String getQualified() {
        return qualified;
    }

    public void setQualified(String qualified) {
        this.qualified = qualified;
    }
}
