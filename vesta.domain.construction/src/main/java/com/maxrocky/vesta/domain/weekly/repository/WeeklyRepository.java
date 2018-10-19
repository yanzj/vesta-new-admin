package com.maxrocky.vesta.domain.weekly.repository;

import com.maxrocky.vesta.domain.model.AuthAgencyESEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Itzxs on 2018/4/8.
 */
public interface WeeklyRepository {

    /**
     *
     * 检查验收的检查数，合格数及两周未整改数
     * @param map
     * @return
     */
    List<Object[]> getInspectAcceptanceCount(Map map);

    /**
     * 检查验收使用详情合格的条数
     * @param map
     * @return
     */
    int getInspectAcceptanceCountState(Map map);

    /**
     * 检查验收使用详情超过两周以上未整改的条数
     * @param map
     * @return
     */
    int getOverTwoWeekNum(Map map);

    /**
     * 日常巡检的检查数，合格数及两周未整改数
     * @param map
     * @return
     */
    List<Object[]> getDailyPatrolInspectionCount(Map map);

    /**
     * 日常巡检详情合格条数
     * @param map
     * @return
     */
    int getDailyPatrolInspectionState(Map map);

    /**
     * 日常巡检详情超过两周以上未整改的条数
     * @param map
     * @return
     */
    int getDailyPatrolInspectionOverTwoWeekNum(Map map);

    /**
     * 材料验收检查数，合格数
     * @param map
     * @return
     */
    List<Object[]> getProjectMaterialCount(Map map);

    /**
     * 材料验收详情合格条数
     * @param map
     * @return
     */
    int getProjectMaterialState(Map map);

    /**
     * 样板点评检查数，合格数及两周未整改数
     * @param map
     * @return
     */
    List<Object[]> getProjectSampleCheckCount(Map map);

    /**
     * 样板点评合格数量
     * @param map
     * @return
     */
    int getProjectSampleCheckState(Map map);

    /**
     * 样板点评超过两周以上未整改的条数
     * @param map
     * @return
     */
    int getProjectSampleCheckOverTwoWeekNum(Map map);

    /**
     * 关键工序检查数，合格数及两周未整改数
     * @param map
     * @return
     */
    List<Object[]> getProjectKeyProcessesCount(Map map);

    /**
     * 关键工序合格数量
     * @param map
     * @return
     */
    int getProjectKeyProcessesState(Map map);

    /**
     * 关键工序超过两周以上未整改的条数
     * @param map
     * @return
     */
    int getProjectKeyProcessesOverTwoWeekNum(Map map);

    /**
     * 旁站数量
     * @param map
     * @return
     */
    int getProjectSideStationCount(Map map);

    /**
     * 领导检查数量
     * @param map
     * @return
     */
    int getProjectLeadersCheckCount(Map map);

    /**
     * 根据条件筛选 parentIdList 上级id  agencyIdList当前级别id  type类型 工程
     * @param parentIdList
     * @param agencyIdList
     * @param type
     * @return
     */
    List<AuthAgencyESEntity> getESAllAgencyListByParentId(List<String> parentIdList,List<String> agencyIdList,String type);

    /**
     * 虚拟区域下的所有项目
     * @return
     */
    List<AuthAgencyESEntity> getVirtualAreaProject();

    /**
     * 虚拟区域下的所有项目Id
     * @return
     */
    List<String> getVirtualAreaProjectId();
}
