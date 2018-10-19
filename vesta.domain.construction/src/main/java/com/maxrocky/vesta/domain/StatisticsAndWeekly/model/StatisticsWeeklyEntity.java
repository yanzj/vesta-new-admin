package com.maxrocky.vesta.domain.StatisticsAndWeekly.model;

import javax.persistence.*;

/**
 * Created by yuanyn on 2018/4/10.
 * 周报和统计实体
 */
@Entity
@Table(name = "statistics_weekly")
public class StatisticsWeeklyEntity {

    private String id;//自增长ID
    private String startDate;//开始时间
    private String endDate;//结束时间
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String type;// 类型   1：周报   2：统计
    private Integer countWeek;// 周报4周 1 第一周  2 第二周  3 第三周  4 第四周
    private Integer countStatistics;// 统计3周 1 第一个月  2 第二个月  3 第三个月
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


    @Id
    @Column(name = "ID", nullable = false, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "START_DATE")
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "END_DATE")
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "PROJECT_ID")
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Basic
    @Column(name = "PROJECT_NAME")
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "COUNT_WEEK")
    public Integer getCountWeek() {
        return countWeek;
    }

    public void setCountWeek(Integer countWeek) {
        this.countWeek = countWeek;
    }

    @Basic
    @Column(name = "COUNT_STATISTICS")
    public Integer getCountStatistics() {
        return countStatistics;
    }

    public void setCountStatistics(Integer countStatistics) {
        this.countStatistics = countStatistics;
    }

    @Basic
    @Column(name = "DAILY_PATROL_PARTY")
    public String getDailyPatrolParty() {
        return dailyPatrolParty;
    }

    public void setDailyPatrolParty(String dailyPatrolParty) {
        this.dailyPatrolParty = dailyPatrolParty;
    }

    @Basic
    @Column(name = "DAILY_PATROL_SUPERVISOR")
    public String getDailyPatrolSupervisor() {
        return dailyPatrolSupervisor;
    }

    public void setDailyPatrolSupervisor(String dailyPatrolSupervisor) {
        this.dailyPatrolSupervisor = dailyPatrolSupervisor;
    }

    @Basic
    @Column(name = "DAILY_PATROL_PASS")
    public String getDailyPatrolPass() {
        return dailyPatrolPass;
    }

    public void setDailyPatrolPass(String dailyPatrolPass) {
        this.dailyPatrolPass = dailyPatrolPass;
    }

    @Basic
    @Column(name = "DAILY_PATROL_EXTENDED")
    public String getDailyPatrolExtended() {
        return dailyPatrolExtended;
    }

    public void setDailyPatrolExtended(String dailyPatrolExtended) {
        this.dailyPatrolExtended = dailyPatrolExtended;
    }

    @Basic
    @Column(name = "ACCEPTANCE_SUPERVISOR")
    public String getAcceptanceSupervisor() {
        return acceptanceSupervisor;
    }

    public void setAcceptanceSupervisor(String acceptanceSupervisor) {
        this.acceptanceSupervisor = acceptanceSupervisor;
    }

    @Basic
    @Column(name = "ACCEPTANCE_PASS")
    public String getAcceptancePass() {
        return acceptancePass;
    }

    public void setAcceptancePass(String acceptancePass) {
        this.acceptancePass = acceptancePass;
    }

    @Basic
    @Column(name = "ACCEPTANCE_EXTENDED")
    public String getAcceptanceExtended() {
        return acceptanceExtended;
    }

    public void setAcceptanceExtended(String acceptanceExtended) {
        this.acceptanceExtended = acceptanceExtended;
    }

    @Basic
    @Column(name = "MATERIAL_PARTY")
    public String getMaterialParty() {
        return materialParty;
    }

    public void setMaterialParty(String materialParty) {
        this.materialParty = materialParty;
    }

    @Basic
    @Column(name = "MATERIAL_SUPERVISOR")
    public String getMaterialSupervisor() {
        return materialSupervisor;
    }

    public void setMaterialSupervisor(String materialSupervisor) {
        this.materialSupervisor = materialSupervisor;
    }

    @Basic
    @Column(name = "MATERIAL_PASS")
    public String getMaterialPass() {
        return materialPass;
    }

    public void setMaterialPass(String materialPass) {
        this.materialPass = materialPass;
    }

    @Basic
    @Column(name = "SAMPLE_CHECK_PARTY")
    public String getSampleCheckParty() {
        return sampleCheckParty;
    }

    public void setSampleCheckParty(String sampleCheckParty) {
        this.sampleCheckParty = sampleCheckParty;
    }

    @Basic
    @Column(name = "SAMPLE_CHECK_PASS")
    public String getSampleCheckPass() {
        return sampleCheckPass;
    }

    public void setSampleCheckPass(String sampleCheckPass) {
        this.sampleCheckPass = sampleCheckPass;
    }

    @Basic
    @Column(name = "SAMPLE_CHECK_EXTENDED")
    public String getSampleCheckExtended() {
        return sampleCheckExtended;
    }

    public void setSampleCheckExtended(String sampleCheckExtended) {
        this.sampleCheckExtended = sampleCheckExtended;
    }

    @Basic
    @Column(name = "KEY_PROCESSES_PARTY")
    public String getKeyProcessesParty() {
        return keyProcessesParty;
    }

    public void setKeyProcessesParty(String keyProcessesParty) {
        this.keyProcessesParty = keyProcessesParty;
    }

    @Basic
    @Column(name = "KEY_PROCESSES_PASS")
    public String getKeyProcessesPass() {
        return keyProcessesPass;
    }

    public void setKeyProcessesPass(String keyProcessesPass) {
        this.keyProcessesPass = keyProcessesPass;
    }

    @Basic
    @Column(name = "KEY_PROCESSES_EXTENDED")
    public String getKeyProcessesExtended() {
        return keyProcessesExtended;
    }

    public void setKeyProcessesExtended(String keyProcessesExtended) {
        this.keyProcessesExtended = keyProcessesExtended;
    }

    @Basic
    @Column(name = "SIDE_STATION_STATISTICS")
    public String getSideStationStatistics() {
        return sideStationStatistics;
    }

    public void setSideStationStatistics(String sideStationStatistics) {
        this.sideStationStatistics = sideStationStatistics;
    }

    @Basic
    @Column(name = "LEAD_INSPECT_STATISTICS")
    public String getLeadInspectStatistics() {
        return leadInspectStatistics;
    }

    public void setLeadInspectStatistics(String leadInspectStatistics) {
        this.leadInspectStatistics = leadInspectStatistics;
    }
}
