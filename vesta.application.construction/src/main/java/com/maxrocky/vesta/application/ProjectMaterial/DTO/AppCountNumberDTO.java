package com.maxrocky.vesta.application.ProjectMaterial.DTO;

/**
 * APP待办事项统计DTO
 * Y:需要统计
 * N:不需要统计
 * Created by Magic on 2016/11/30.
 */

public class  AppCountNumberDTO {
    private String dailyPatrol;             //日常巡检
    private String dailyPatrolNB;           //日常巡检待办统计
    private String inspectionAcceptance;    //检查验收
    private String inspectionAcceptanceNB;  //检查验收待办统计
    private String modelReviews;            //样板点评
    private String modelReviewsNB;          //样板点评待办统计
    private String materialAcceptance;      //材料验收
    private String materialAcceptanceNB;    //材料验收待办统计
    private String keyWorkingProcedure;     //关键工序
    private String keyWorkingProcedureNB;   //关键工序待办统计
    private String statisticalReport;       //统计报表
    private String statisticalReportNB;     //统计报表待办统计
    private String houseInspection;         //交房验房+物业运营
    private String houseInspectionNB;       //待办统计

    public AppCountNumberDTO(){
        this.dailyPatrol="";
        this.dailyPatrolNB="0";
        this.inspectionAcceptance="";
        this.inspectionAcceptanceNB="0";
        this.modelReviews="";
        this.modelReviewsNB="0";
        this.materialAcceptance="";
        this.materialAcceptanceNB="0";
        this.keyWorkingProcedure="";
        this.keyWorkingProcedureNB="0";
        this.statisticalReport="";
        this.statisticalReportNB="0";
        this.houseInspection="";
        this.houseInspectionNB="0";
    }
    public String getDailyPatrol() {
        return dailyPatrol;
    }

    public void setDailyPatrol(String dailyPatrol) {
        this.dailyPatrol = dailyPatrol;
    }

    public String getDailyPatrolNB() {
        return dailyPatrolNB;
    }

    public void setDailyPatrolNB(String dailyPatrolNB) {
        this.dailyPatrolNB = dailyPatrolNB;
    }

    public String getInspectionAcceptance() {
        return inspectionAcceptance;
    }

    public void setInspectionAcceptance(String inspectionAcceptance) {
        this.inspectionAcceptance = inspectionAcceptance;
    }

    public String getInspectionAcceptanceNB() {
        return inspectionAcceptanceNB;
    }

    public void setInspectionAcceptanceNB(String inspectionAcceptanceNB) {
        this.inspectionAcceptanceNB = inspectionAcceptanceNB;
    }

    public String getModelReviews() {
        return modelReviews;
    }

    public void setModelReviews(String modelReviews) {
        this.modelReviews = modelReviews;
    }

    public String getModelReviewsNB() {
        return modelReviewsNB;
    }

    public void setModelReviewsNB(String modelReviewsNB) {
        this.modelReviewsNB = modelReviewsNB;
    }

    public String getMaterialAcceptance() {
        return materialAcceptance;
    }

    public void setMaterialAcceptance(String materialAcceptance) {
        this.materialAcceptance = materialAcceptance;
    }

    public String getMaterialAcceptanceNB() {
        return materialAcceptanceNB;
    }

    public void setMaterialAcceptanceNB(String materialAcceptanceNB) {
        this.materialAcceptanceNB = materialAcceptanceNB;
    }

    public String getKeyWorkingProcedure() {
        return keyWorkingProcedure;
    }

    public void setKeyWorkingProcedure(String keyWorkingProcedure) {
        this.keyWorkingProcedure = keyWorkingProcedure;
    }

    public String getKeyWorkingProcedureNB() {
        return keyWorkingProcedureNB;
    }

    public void setKeyWorkingProcedureNB(String keyWorkingProcedureNB) {
        this.keyWorkingProcedureNB = keyWorkingProcedureNB;
    }

    public String getStatisticalReport() {
        return statisticalReport;
    }

    public void setStatisticalReport(String statisticalReport) {
        this.statisticalReport = statisticalReport;
    }

    public String getStatisticalReportNB() {
        return statisticalReportNB;
    }

    public void setStatisticalReportNB(String statisticalReportNB) {
        this.statisticalReportNB = statisticalReportNB;
    }

    public String getHouseInspection() {
        return houseInspection;
    }

    public void setHouseInspection(String houseInspection) {
        this.houseInspection = houseInspection;
    }

    public String getHouseInspectionNB() {
        return houseInspectionNB;
    }

    public void setHouseInspectionNB(String houseInspectionNB) {
        this.houseInspectionNB = houseInspectionNB;
    }
}
