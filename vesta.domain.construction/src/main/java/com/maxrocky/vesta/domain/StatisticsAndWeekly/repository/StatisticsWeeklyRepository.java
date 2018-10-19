package com.maxrocky.vesta.domain.StatisticsAndWeekly.repository;

import com.maxrocky.vesta.domain.StatisticsAndWeekly.model.StatisticsWeeklyEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.DailyPatrolInspectionEntity;
import com.maxrocky.vesta.domain.inspectAcceptance.model.ProjectExamineEntity;
import com.maxrocky.vesta.domain.model.AuthAgencyESEntity;
import com.maxrocky.vesta.domain.projectKeyProcesses.model.ProjectKeyProcessesEntity;
import com.maxrocky.vesta.domain.projectLeadersCheck.model.ProjectLeadersCheckEntity;
import com.maxrocky.vesta.domain.projectMaterial.model.ProjectMaterialEntity;
import com.maxrocky.vesta.domain.projectSampleCheck.model.ProjectSampleCheckEntity;
import com.maxrocky.vesta.domain.projectSideStation.model.ProjectSideStationEntity;

import java.util.List;

/**
 * Created by yuanyn on 2018/4/10.
 * 定时统计与周报Repository接口
 */
public interface StatisticsWeeklyRepository {


    //下载项目信息
    List<AuthAgencyESEntity> getProject(String projectOrAll);

    //按时间下载日常巡检周报或统计
    List<Object[]> getDailyByTime(String startTime, String endTime, String extendedTime);

    //按时间下载检查验收周报或统计
    List<Object[]> getAcceptanceByTime(String startTime, String endTime, String extendedTime);

    //按时间下载材料验收周报或统计
    List<Object[]> getMaterialByTime(String startTime, String endTime);

    //按时间下载样板点评周报或统计
    List<Object[]> getSampleCheckByTime(String startTime, String endTime, String extendedTime);

    //按时间下载关键工序周报或统计
    List<Object[]> getKeyProcessesByTime(String startTime, String endTime, String extendedTime);

    //按时间下载旁站周报或统计
    List<Object[]> getSideStationByTime(String startTime, String endTime);

    //按时间下载领导检查周报或统计
    List<Object[]> getLeadInspectByTime(String startTime, String endTime);

    //保存周报
    void saveStatisticsWeeklyEntity(StatisticsWeeklyEntity statisticsWeeklyEntity);

    //删除以前的数据
    void delStatisticsWeeklyEntity(String type);

}

