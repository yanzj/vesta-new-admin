package com.maxrocky.vesta.domain.inspectAcceptance.repository;

import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyDetailsEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyEntity;
import com.maxrocky.vesta.domain.inspectAcceptance.model.ProjectExamineEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by JIAZEFENG on 2016/10/17.
 */
public interface InspectAcceptanceRepository {
    /**
     * 按条件检索工程检查列表
     *
     * @param map
     * @param webPage
     * @return
     */
    List<ProjectExamineEntity> searchInspectAcceptanceList(Map map, WebPage webPage,String staffId);
    /**
     * 按条件检索工程检查列表
     *
     * @param map
     * @param webPage
     * @return
     */
    List<ProjectExamineEntity> searchInspectAcceptanceList(Map map, WebPage webPage);
    /**
     * 工程检查列表
     *
     * @return
     */
    List<ProjectExamineEntity> searchInspectAcceptanceAllList();

    /**
     * 按条件检索工程检查列表(不带分页)
     *
     * @return
     */
    List<ProjectExamineEntity> searchInspectAcceptanceAllList(Map map);

    /**
     * 新增工程检查信息
     *
     * @param inspectAcceptanceEntity
     * @return
     */
    boolean addProjectExamineInfo(ProjectExamineEntity inspectAcceptanceEntity);

    /**
     * 检索已进行的工程检查信息
     *
     * @param projectNum
     * @return
     */
    int searchHasBeenGetOnByProjectNum(String projectNum);

    /**
     * 检索合格的工程检查信息
     *
     * @param projectNum
     * @return
     */
    int searchQualifiedByProjectNum(String projectNum);

    /**
     * 检索不合格的工程检查信息
     *
     * @param projectNum
     * @return
     */
    int searchUnqualifiedByProjectNum(String projectNum);

    /**
     * 检索不合格的工程检查信息
     *
     * @param projectNum
     * @return
     */
    int searchOnePassByProjectNum(String projectNum);

    /**
     * 统计工程检查信息（前台）
     *
     * @param projectNum
     * @return
     */
    List<Object[]> searchAcceptanceListByProjectNum(String projectNum);

    /**
     * 根据楼栋检索工程检查批次列表
     *
     * @param buildingId
     * @param projectCategoryName
     * @return
     */
    List<Object[]> searchAcceptanceBatchList(String buildingId, String projectCategoryName);

    /**
     * 检索工程检查批次详情
     *
     * @param batchId
     * @return
     */
    ProjectExamineEntity searchAcceptanceBatchInfo(String batchId);

    /**
     * 修改工程检查批次
     *
     * @param projectExamineEntity
     * @return
     */
    boolean modifyAcceptanceBatchInfo(ProjectExamineEntity projectExamineEntity);

    /**
     * 检查工程验收是否有更新
     *
     * @param id
     * @param beginDateNew
     * @param projectNum
     * @return
     */
    boolean searchToUpdateForAcceptance(String id, String beginDateNew, String projectNum,String creatBy,String type,String day7Ago);

    /**
     * 下载更新数据
     *
     * @param id
     * @param timeStamp
     * @param projectNum
     * @return
     */
    List<ProjectExamineEntity> getAllProjectAcceptanceQuestion(String id, String timeStamp, String projectNum,String day7Ago);

    /**
     * 查询工程抄送人
     *
     * @param batchId
     * @return
     */
    List<ProjectCopyEntity> getProjectCopyList(String batchId);

    /**
     * 查询工程抄送详情
     *
     * @param copyId
     * @return
     */
    List<ProjectCopyDetailsEntity> getProjectCopyDetailsList(String copyId);

    /**
     * 保存抄送
     */
    boolean saveProjectCopy(ProjectCopyEntity projectCopy);

    /**
     * 删除抄送
     * id: 外键id
     */
    boolean deleteProjectCopy(String id);

    /**
     * 保存抄送人员
     */
    boolean saveProjectCopyDetails(ProjectCopyDetailsEntity projectCopyDetails);

    /**
     * 删除抄送人员
     * id: 外键id
     * type：模块
     */
    boolean deleteProjectCopyDetails(String id);

    /**
     * 统计
     *
     * @param map
     * @param webPage
     * @return
     */
    List<ProjectExamineEntity> searchInspectAcceptanceCountList(Map map, WebPage webPage);

    /**
     * 统计
     *
     * @param map
     * @return
     */
    List<Object[]> searchAcceptanceCountList(Map map, WebPage webPage,String staffId);

    /**
     * 统计
     *
     * @param map
     * @return
     */
    List<Object[]> searchAcceptanceCountList(Map map, WebPage webPage);
    /**
     * 统计
     *
     * @param map
     * @return
     */
    List<Object[]> searchAcceptanceCountList(Map map);
    /**
     * 统计
     *
     * @param map
     * @return
     */
    List<Object[]> searchAcceptanceCountList(Map map,String staffId);
    /**
     * 统计
     *
     * @param map
     * @return
     */
    int searchInspectAcceptanceCount(Map map);

    /**
     * 统计
     *
     * @param map
     * @return
     */
    int getCount(Map map,String staffId);
    /**
     * 统计
     *
     * @param map
     * @return
     */
    int getCount(Map map);
    /**
     * 查询自己的批次
     *
     * @param batchId
     * @param staffId
     * @return
     */
    ProjectExamineEntity searchAcceptanceBatchInfoByStaffId(String batchId, String staffId);

    /**
     * 根据APPID检索批次信息
     *
     * @param id
     * @return
     */
    ProjectExamineEntity getProjectExamineByAppId(String id);

    /**
     * 整改中的总数（当前登录人）
     *
     * @param staffId
     * @return
     */
    int getRectificationCount(String staffId);

    List<Object[]> getAllProjectAcceptanceQuestion(String id, String timeStamp, String projectNum, String day7Ago, String staffId, String type);

    List<Object[]> getAllProjectAcceptanceQuestion(String id, String timeStamp, String projectNum, String staffId, String type);
}
