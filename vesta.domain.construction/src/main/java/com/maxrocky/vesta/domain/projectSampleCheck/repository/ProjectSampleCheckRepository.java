package com.maxrocky.vesta.domain.projectSampleCheck.repository;

import com.maxrocky.vesta.domain.baseData.model.ProjectImagesEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyDetailsEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyEntity;
import com.maxrocky.vesta.domain.projectSampleCheck.model.ProjectSampleCheckChangedEntity;
import com.maxrocky.vesta.domain.projectSampleCheck.model.ProjectSampleCheckDetailsEntity;
import com.maxrocky.vesta.domain.projectSampleCheck.model.ProjectSampleCheckEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2017/1/3.
 */
public interface ProjectSampleCheckRepository {
    /**
     * 保存样板点评主表信息
     */
    boolean saveOrUpdateSampleCheck(ProjectSampleCheckEntity projectSampleCheckEntity);

    /**
     * 保存样板点评指标信息
     */
    boolean saveOrUpdateSampleCheckDetails(ProjectSampleCheckDetailsEntity projectSampleCheckDetailsEntity);

    /**
     * 保存 样板点评指标整改信息
     */
    void saveSampleCheckChanged(ProjectSampleCheckChangedEntity projectSampleCheckChangedEntity);

    /**
     * 按样板点评ID+appId查询样板点评主表信息
     */
    ProjectSampleCheckEntity querySampleCheckByID(String id, String appId);

    /**
     * 根据样板点评ID查询指标表数据
     */
    List<ProjectSampleCheckDetailsEntity> querySampleCheckDetailsById(String id);

    /**
     * 根据指标信息ID查询指标信息
     * */
    ProjectSampleCheckDetailsEntity querySampleCheckDetailsEntity(String id);
    /**
     * 根据样板点评指标ID查询整改信息
     */
    List<ProjectSampleCheckChangedEntity> querySampleCheckChangedEntity(String id);

    /**
     * 查询图片
     */
    List<ProjectImagesEntity> getProjectImages(String id);

    /**
     * 查询样板点评指标信息合格不合格
     * */
    List<ProjectImagesEntity> getQualifiedImage(String id,String state);

    /**
     * 保存抄送
     */
    boolean saveProjectCopy(ProjectCopyEntity projectCopy);

    /**
     * 保存抄送人详情
     *
     * @param projectCopyDetailsEntity
     */
    void saveProjectCopyDetails(ProjectCopyDetailsEntity projectCopyDetailsEntity);

    /**
     * 检查样板点评是否有更新
     *
     * @param id
     * @param time
     * @param projectId
     * @param staffId
     * @param type
     * @return
     */
    boolean sampleCheckForUpdate(String id, String time, String projectId, String staffId, String type);

    /**
     * 下载更新样板点评数据
     *
     * @param id
     * @param time
     * @param projectId
     * @param staffId
     * @param type
     * @return
     */
    List<ProjectSampleCheckEntity> getAllKeyProcessesQuestion(String id, String time, String projectId, String staffId, String type);

    /**
     * 按项目查询统计信息
     */
    List<Object[]> searchSampleCheck(String projectId);


    /**
     * 后台查询列表
     * */
    List<ProjectSampleCheckEntity> getSampleCheckAdmin(Map map, WebPage webPage);

    /**
     * 后台查询统计
     * */
    List<Object[]> getSampleCheckCount(Map map, WebPage webPage);

    /**
     * 后台查询统计分页
     * */
    int getSampleCheckWebPage(Map map);
    /***
     *查询当前登录人有多少条不合格数据
     * */
    int getCountSampleCheck(String staffId);
}
