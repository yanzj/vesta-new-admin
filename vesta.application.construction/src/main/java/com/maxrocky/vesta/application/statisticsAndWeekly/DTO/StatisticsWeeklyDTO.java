package com.maxrocky.vesta.application.statisticsAndWeekly.DTO;

/**
 * Created by hp on 2018/4/10.
 */
public class StatisticsWeeklyDTO {

    private String id;//自增长ID
    private String startDate;//开始时间
    private String endDate;//结束时间
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String type;// 类型   1：周报   2：统计
    private String countWeek;// 周报4周 1 第一周  2 第二周  3 第三周  4 第四周
    private String countStatistics;// 统计3周 1 第一个月  2 第二个月  3 第三个月
    private String dailyPatrolParty;//日常巡检甲方
    private String dailyPatrolSupervisor;//日常巡检监理
    private String dailyPatrolPass;//日常巡检合格率
    private String dailyPatrolExtended;//日常巡检超期两周未整改
    private String acceptanceSupervisor;//检查验收监理
    private String acceptancePass;//检查验收合格率
    private String acceptanceExtended;//检查验收超期两周未整改
    private String materialParty;//材料验收甲方
    private String materialSupervisor;//材料验收监理
    private String materialPass;//材料验收合格率
    private String sampleCheckParty;//样板点评甲方
    private String sampleCheckPass;//样板点评合格率
    private String sampleCheckExtended;//样板点评超期两周未整改
    private String keyProcessesParty;//关键工序甲方
    private String keyProcessesPass;//关键工序合格率
    private String keyProcessesExtended;//关键工序超期两周未整改
    private String sideStationStatistics;//旁站统计
    private String leadInspectStatistics;//领导检查统计

    public StatisticsWeeklyDTO() {
        this.id = "";
        this.startDate = "";
        this.endDate = "";
        this.projectId = "";
        this.projectName = "";
        this.type = "";
        this.countWeek = "";
        this.countStatistics = "";
        this.dailyPatrolParty = "";
        this.dailyPatrolSupervisor = "";
        this.dailyPatrolPass = "";
        this.dailyPatrolExtended = "";
        this.acceptanceSupervisor = "";
        this.acceptancePass = "";
        this.acceptanceExtended = "";
        this.materialParty = "";
        this.materialSupervisor = "";
        this.materialPass = "";
        this.sampleCheckParty = "";
        this.sampleCheckPass = "";
        this.sampleCheckExtended = "";
        this.keyProcessesParty = "";
        this.keyProcessesPass = "";
        this.keyProcessesExtended = "";
        this.sideStationStatistics = "";
        this.leadInspectStatistics = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountWeek() {
        return countWeek;
    }

    public void setCountWeek(String countWeek) {
        this.countWeek = countWeek;
    }

    public String getCountStatistics() {
        return countStatistics;
    }

    public void setCountStatistics(String countStatistics) {
        this.countStatistics = countStatistics;
    }

    public String getDailyPatrolParty() {
        return dailyPatrolParty;
    }

    public void setDailyPatrolParty(String dailyPatrolParty) {
        this.dailyPatrolParty = dailyPatrolParty;
    }

    public String getDailyPatrolSupervisor() {
        return dailyPatrolSupervisor;
    }

    public void setDailyPatrolSupervisor(String dailyPatrolSupervisor) {
        this.dailyPatrolSupervisor = dailyPatrolSupervisor;
    }

    public String getDailyPatrolPass() {
        return dailyPatrolPass;
    }

    public void setDailyPatrolPass(String dailyPatrolPass) {
        this.dailyPatrolPass = dailyPatrolPass;
    }

    public String getDailyPatrolExtended() {
        return dailyPatrolExtended;
    }

    public void setDailyPatrolExtended(String dailyPatrolExtended) {
        this.dailyPatrolExtended = dailyPatrolExtended;
    }

    public String getAcceptanceSupervisor() {
        return acceptanceSupervisor;
    }

    public void setAcceptanceSupervisor(String acceptanceSupervisor) {
        this.acceptanceSupervisor = acceptanceSupervisor;
    }

    public String getAcceptancePass() {
        return acceptancePass;
    }

    public void setAcceptancePass(String acceptancePass) {
        this.acceptancePass = acceptancePass;
    }

    public String getAcceptanceExtended() {
        return acceptanceExtended;
    }

    public void setAcceptanceExtended(String acceptanceExtended) {
        this.acceptanceExtended = acceptanceExtended;
    }

    public String getMaterialParty() {
        return materialParty;
    }

    public void setMaterialParty(String materialParty) {
        this.materialParty = materialParty;
    }

    public String getMaterialSupervisor() {
        return materialSupervisor;
    }

    public void setMaterialSupervisor(String materialSupervisor) {
        this.materialSupervisor = materialSupervisor;
    }

    public String getMaterialPass() {
        return materialPass;
    }

    public void setMaterialPass(String materialPass) {
        this.materialPass = materialPass;
    }

    public String getSampleCheckParty() {
        return sampleCheckParty;
    }

    public void setSampleCheckParty(String sampleCheckParty) {
        this.sampleCheckParty = sampleCheckParty;
    }

    public String getSampleCheckPass() {
        return sampleCheckPass;
    }

    public void setSampleCheckPass(String sampleCheckPass) {
        this.sampleCheckPass = sampleCheckPass;
    }

    public String getSampleCheckExtended() {
        return sampleCheckExtended;
    }

    public void setSampleCheckExtended(String sampleCheckExtended) {
        this.sampleCheckExtended = sampleCheckExtended;
    }

    public String getKeyProcessesParty() {
        return keyProcessesParty;
    }

    public void setKeyProcessesParty(String keyProcessesParty) {
        this.keyProcessesParty = keyProcessesParty;
    }

    public String getKeyProcessesPass() {
        return keyProcessesPass;
    }

    public void setKeyProcessesPass(String keyProcessesPass) {
        this.keyProcessesPass = keyProcessesPass;
    }

    public String getKeyProcessesExtended() {
        return keyProcessesExtended;
    }

    public void setKeyProcessesExtended(String keyProcessesExtended) {
        this.keyProcessesExtended = keyProcessesExtended;
    }

    public String getSideStationStatistics() {
        return sideStationStatistics;
    }

    public void setSideStationStatistics(String sideStationStatistics) {
        this.sideStationStatistics = sideStationStatistics;
    }

    public String getLeadInspectStatistics() {
        return leadInspectStatistics;
    }

    public void setLeadInspectStatistics(String leadInspectStatistics) {
        this.leadInspectStatistics = leadInspectStatistics;
    }
}
