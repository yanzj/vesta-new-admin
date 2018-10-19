package com.maxrocky.vesta.application.baseData.adminDTO;

/**
 * Created by chen on 2016/10/26.
 * 责任单位数据封装类
 */
public class ProjectEmployDTO {
    private String sId;              //责任单位ID
    private String sName;            //责任单位名
    private String sNature="1";      //责任单位性质
    private String projectName;      //项目名
    private String projectId;        //项目ID
    private String sMemo;            //备注
    private String sStatus="1";      //状态
    private String sSupplierId;      //供应商ID
    private String modifyTime;       //修改时间
    private String buildId;          //楼栋ID
    private String abbreviationName;//机构简称

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsNature() {
        return sNature;
    }

    public void setsNature(String sNature) {
        this.sNature = sNature;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getsMemo() {
        return sMemo;
    }

    public void setsMemo(String sMemo) {
        this.sMemo = sMemo;
    }

    public String getsStatus() {
        return sStatus;
    }

    public void setsStatus(String sStatus) {
        this.sStatus = sStatus;
    }

    public String getsSupplierId() {
        return sSupplierId;
    }

    public void setsSupplierId(String sSupplierId) {
        this.sSupplierId = sSupplierId;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getBuildId() {
        return buildId;
    }

    public void setBuildId(String buildId) {
        this.buildId = buildId;
    }

    public String getAbbreviationName() {
        return abbreviationName;
    }

    public void setAbbreviationName(String abbreviationName) {
        this.abbreviationName = abbreviationName;
    }
}
