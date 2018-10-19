package com.maxrocky.vesta.domain.projectKeyProcesses.repository;

import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.projectKeyProcesses.model.ProjectKeyProcessesEntity;
import com.maxrocky.vesta.domain.projectKeyProcesses.model.ProjectKeyProcessesTargetDetailsEntity;
import com.maxrocky.vesta.domain.projectKeyProcesses.model.ProjectKeyProcessesTargetEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Talent on 2016/11/22.
 */
public interface ProjectKeyProcessesRepository {
    /**
     * 新增关键工序
     *
     * @param projectKeyProcessesEntity
     * @return
     */
    boolean addKeyProcesses(ProjectKeyProcessesEntity projectKeyProcessesEntity);

    /**
     * 修改关键工序
     *
     * @param projectKeyProcessesEntity
     * @return
     */
    boolean modifyKeyProcesses(ProjectKeyProcessesEntity projectKeyProcessesEntity);

    /**
     * 通过APPID查询工序信息
     *
     * @param appId
     * @return
     */
    ProjectKeyProcessesEntity getKeyProcessesInfoByAppId(String appId);

    /**
     * 新增关键工序指标信息
     *
     * @param projectKeyProcessesTargetEntity
     * @return
     */
    boolean addKeyProcessesTarget(ProjectKeyProcessesTargetEntity projectKeyProcessesTargetEntity);

    /**
     * 编辑关键工序指标信息
     *
     * @param projectKeyProcessesTargetEntity
     * @return
     */
    boolean modifyKeyProcessesTarget(ProjectKeyProcessesTargetEntity projectKeyProcessesTargetEntity);

    /**
     * 新增关键工序指标详情信息
     *
     * @param projectKeyProcessesTargetDetailsEntity
     * @return
     */
    boolean addKeyProcessesTargetDetails(ProjectKeyProcessesTargetDetailsEntity projectKeyProcessesTargetDetailsEntity);

    /**
     * 通过工序ID查询工序信息
     *
     * @param processId
     * @return
     */
    ProjectKeyProcessesEntity getKeyProcessesInfoByProcessId(String processId);

    /**
     * 工序指标信息
     *
     * @param processId
     * @return
     */
    List<Object[]> getKeyProcessesTargetListByProcessId(String processId);

    /**
     * 工序指标信息
     *
     * @param processId
     * @return
     */
    List<Object[]> searchKeyProcessesTargetListByProcessId(String processId);

    /**
     * 工序指标详情信息
     *
     * @param processTargetId
     * @return
     */
    List<Object[]> getKeyProcessesTargetDetailListByProcessId(String processTargetId);

    /**
     * 工序乙方指标详情信息
     *
     * @param processTargetId
     * @return
     */
    List<Object[]> getPartyBTargetDetailListByProcessId(String processTargetId);

    /**
     * 工序监理指标详情信息
     *
     * @param processTargetId
     * @return
     */
    List<Object[]> getSupervisionTargetDetailListByProcessId(String processTargetId);

    /**
     * 工序甲方指标详情信息
     *
     * @param processTargetId
     * @return
     */
    List<Object[]> getPartyATargetDetailListByProcessId(String processTargetId);

    /**
     * 检查关键工序是否有更新
     *
     * @param id
     * @param time
     * @param projectId
     * @return
     */
    boolean keyProcessesCheckForUpdate(String id, String time, String projectId,String creatBy,String type);

    /**
     * 得到关键工序信息
     *
     * @param id
     * @param time
     * @param projectId
     * @return
     */
    List<Object[]> getAllKeyProcessesQuestion(String id, String time, String projectId,String creatBy,String type);

    /**
     * 统计关键工序
     *
     * @param projectId
     * @return
     */
    List<Object[]> addUpKeyProcessesByProjectId(String projectId);

    /**
     * 根据ID查询工序指标
     *
     * @param processTargetId
     * @return
     */
    ProjectKeyProcessesTargetEntity getKeyProcessesTargetListById(String processTargetId);

    /**
     * 关键工序列表
     *
     * @param map
     * @param webPage
     * @return
     */
    List<ProjectKeyProcessesEntity> searchKeyProcessesList(Map map, WebPage webPage);
    List<ProjectKeyProcessesEntity> searchKeyProcessesList(Map map, WebPage webPage, String staffId);
    /**
     * 关键工序(不带分页)
     *
     * @param map
     * @return
     */
    List<ProjectKeyProcessesEntity> searchKeyProcessesList(Map map);

    /**
     * 关键工序统计
     *
     * @param map
     * @return
     */
    List<Object[]> searchKeyProcessesCountList(Map map, WebPage webPage,String staffId);
    /**
     * 关键工序统计(不带分页)
     *
     * @param map
     * @return
     */
    List<Object[]> searchKeyProcessesCountList(Map map,String staffId);
    /**
     * 关键工序统计
     *
     * @param map
     * @return
     */
    List<Object[]> searchKeyProcessesCountList(Map map,WebPage webPage);

    /**
     * 关键工序统计(不带分页)
     * @param map
     * @return
     */
    List<Object[]> searchKeyProcessesCountList(Map map);
    /**
     * 总数
     *
     * @param map
     * @return
     */
    int searchKeyProcessesCount(Map map);

    /**
     * 总数
     *
     * @param map
     * @return
     */
    int getCount(Map map,String staffId);
    /**
     * 总数
     *
     * @param map
     * @return
     */
    int getCount(Map map);

    /**
     * 查询自己的批次
     *
     * @param processId
     * @param staffId
     * @return
     */
    ProjectKeyProcessesEntity searchKeyProcessByStaffId(String processId, String staffId);

    /**
     * 整改中的总数（当前登录人）
     *
     * @param staffId
     * @return
     */
    int getRectificationCount(String staffId);
}
