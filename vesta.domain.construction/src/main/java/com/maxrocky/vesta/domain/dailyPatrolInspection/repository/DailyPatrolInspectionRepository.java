package com.maxrocky.vesta.domain.dailyPatrolInspection.repository;

import com.maxrocky.vesta.domain.baseData.model.ProjectImagesEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.DailyPatrolInspectionDetailsEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.DailyPatrolInspectionEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyDetailsEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2016/10/17.
 */
public interface DailyPatrolInspectionRepository {
    /**
     * 按项目判断是否有数据更新
     */
    boolean checkInspectionByApp(String id, String timeStamp, String projectId, String creaBy, String type);

    /**
     * 查询所有日常巡检的整改单
     */
    List<Object[]> getInspectionListByApp(String id, String timeStamp, String projectId, String creaid, String type);

    /**
     * 查询所有日常巡检的整改单
     */
    List<Object[]> getInspectionList(Map map, WebPage webPage);

    /**
     * 导出所有日常巡检的整改单
     */
    List<Object[]> getexportExcelList(Map map);

    DailyPatrolInspectionEntity getInspectionEntityById(String id);

    /**
     * 根据appid存入
     */
    DailyPatrolInspectionEntity getInspectionEntityByAppId(String id);

    /**
     * 保存日常巡检的整改单
     */
    void saveInspection(DailyPatrolInspectionEntity dailyPatrolInspection);

    /**
     * 保存日常巡检的整改单
     */
    void updateInspection(DailyPatrolInspectionEntity dailyPatrolInspection);

    /**
     * 保存图片
     */
    void saveProjectImages(ProjectImagesEntity projectImages);


    /**
     * 删除不包含的图片
     */
    void deleteByNotIds(List<String> id);

    /**
     * 保存图片
     */
    List<ProjectImagesEntity> getProjectImages(String id);

    /**
     * 删除图片
     * *id: 外键id
     * type：模块
     */
    void deleteProjectImages(String id, String type);

    /**
     * 保存抄送
     */
    void saveProjectCopy(ProjectCopyEntity projectCopy);

    /**
     * 删除抄送
     * id: 外键id
     * type：模块
     */
    void deleteProjectCopy(String id, String type);

    /**
     * 保存抄送人员
     */
    void saveProjectCopyDetails(ProjectCopyDetailsEntity ProjectCopyDetails);

    /**
     * 删除抄送人员
     * id: 外键id
     * type：模块
     */
    void deleteProjectCopyDetails(String id);

    /**
     * 查询抄送人员
     * id: 外键id
     * type：模块
     */
    List<Object[]> getProjectCopy(String id);

    /**
     * 查询整改记录
     */

    List<DailyPatrolInspectionDetailsEntity> getDailyPatrolInspectionDetails(String id);

    /**
     * 按项目查询统计信息
     */
    List<Object[]> searchInspection(String projectId);


    /**
     * 查询日常巡检代办理事项
     */
    List<Object[]> getInspectionListByAppTodo(String id, String timeStamp, String creaid);


    /**
     * 查询日常巡检待办事项是否有新数据更心
     */
    boolean checkInspectionTodo(String id, String timeStamp, String creaBy);

    /**
     * 保存巡检详情
     *
     * @param dailyPatrolInspectionDetailsEntity
     */
    void saveInspectionDetais(DailyPatrolInspectionDetailsEntity dailyPatrolInspectionDetailsEntity);


    /**
     * 批量删除图片
     * *id: 外键id
     * type：模块
     */
    void deleteProjectImagesList(List id, String type);

    /**
     * 日常巡检统计列表（后端）
     *
     * @param map
     * @return
     */
    List<Object[]> searchInspectionCount(Map map, WebPage webPage);

    /**
     * 日常巡检统计列表（后端）
     *
     * @param map
     * @return
     */
    List<Object[]> searchInspectionCount(Map map);

    int searchDailyPatrolInspectionCount(Map map);

    int getCount(Map map);

    /**
     * 统计项目
     */
    int getProjectCount(Map map);

    /**
     * 统计区域
     */
    int getOperatCount(Map map);

    /**
     * 查询日常巡检详情
     */
    Object[] getInspectionListByAdmin(String id);


    DailyPatrolInspectionEntity getDailyPatrolInspection(String id);

    /**
     * 日常巡检 按照当前登录人的员工id 查询整改中本人所负责人数据
     */
    int inspectionCount(String userId);


    List<DailyPatrolInspectionEntity> getDailyPatrolInspectionByInspectionId(String inspectionId);


    /**
     * 日常巡检统计（项目）
     *
     * @param map
     * @return
     */
    List<Object[]> searchInspectionProjecrCount(Map map, WebPage webPage);

    /**
     * 日常巡检统计（区域）
     *
     * @param map
     * @return
     */
    List<Object[]> searchInspectionOperaCount(Map map, WebPage webPage);

    /**
     * 日常巡检统计（项目）去掉分页
     *
     * @param map
     * @return
     */
    List<Object[]> searchInspectionProjecrCount(Map map);

    /**
     * 日常巡检统计（区域）去掉分页
     *
     * @param map
     * @return
     */
    List<Object[]> searchInspectionOperaCount(Map map);
}
