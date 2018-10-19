package com.maxrocky.vesta.application.jsonDTO;

import java.util.List;

/**
 * Created by chen on 2016/5/24.
 * 材料验收 材料退场接收数据 封装
 */
public class ExitReceiveDTO {
    private String materialId;    //材料验收ID
    private String projectId;     //项目ID
    private String materialType;  //材料类型
    private String problem;       //问题描述
    private String checkTime;     //验收时间
    private String supplier;      //供应商
    private String dutyUnit;      //责任单位
    private List<MaterialImageDTO> imageList;  //图片列表

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getDutyUnit() {
        return dutyUnit;
    }

    public void setDutyUnit(String dutyUnit) {
        this.dutyUnit = dutyUnit;
    }

    public List<MaterialImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<MaterialImageDTO> imageList) {
        this.imageList = imageList;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
