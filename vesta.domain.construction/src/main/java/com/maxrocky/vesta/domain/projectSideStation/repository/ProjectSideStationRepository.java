package com.maxrocky.vesta.domain.projectSideStation.repository;

import com.maxrocky.vesta.domain.projectSideStation.model.ProjectSideStationDetailsEntity;
import com.maxrocky.vesta.domain.projectSideStation.model.ProjectSideStationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Talent on 2016/11/8.
 */
public interface ProjectSideStationRepository {
    /**
     * 检查旁站是否有更新
     *
     * @param id
     * @param time
     * @param projectId
     * @return
     */
    boolean checkForUpdatesFromSideStation(String id, String time, String projectId,String creatBy,String type);
    List<Object[]> getSideStationInfoByParameter(String id, String beginDateNew, String projectId, String staffId, String type);
    /**
     * 查询旁站信息
     *
     * @param sideStationId
     * @return
     */
    ProjectSideStationEntity getSideStationInfoById(String sideStationId);


    /**
     * 查询旁站描述信息
     *
     * @param id
     * @return
     */
    ProjectSideStationDetailsEntity getSideStationDetailsInfoBySideStationId(String id);

    /**
     * 查询旁站描述详细信息(前端)
     *
     * @param sideStationId
     * @return
     */
    List<Object[]> getSideStationDetailsBySideStationId(String sideStationId);

    /**
     * 查询旁站描述详细信息(后端)
     *
     * @param sideStationId
     * @return
     */
    List<Object[]> getSideStationDetailsInfoListBySideStationId(String sideStationId);

    /**
     * 下载更新旁站信息
     *
     * @param id
     * @param beginDateNew
     * @param projectId
     * @return
     */
    List<ProjectSideStationEntity> getSideStationInfoByParameter(String id, String beginDateNew, String projectId);

    /**
     * 统计列表
     *
     * @param projectId
     * @return
     */
    List<Object[]> getSideStationCountByProjectId(String projectId);

    /**
     * 新增旁站详情信息
     *
     * @param projectSideStationDetailsEntity
     */
    boolean addSideStationDetailsInnfo(ProjectSideStationDetailsEntity projectSideStationDetailsEntity);

    /**
     * 新增旁站信息
     *
     * @param projectSideStationEntity
     * @return
     */
    boolean addSideStationInfo(ProjectSideStationEntity projectSideStationEntity);
    /**
     * 修改旁站信息
     *
     * @param projectSideStationEntity
     * @return
     */
    boolean updateSideStationInfo(ProjectSideStationEntity projectSideStationEntity);
    /**
     * 后端检索旁站信息列表
     *
     * @param map
     * @param webPage
     * @return
     */
    List<ProjectSideStationEntity> searchBesideStationList(Map map, WebPage webPage,String staffId);
    /**
     * 后端检索旁站信息列表
     *
     * @param map
     * @param webPage
     * @return
     */
    List<ProjectSideStationEntity> searchBesideStationList(Map map, WebPage webPage);
    List<ProjectSideStationEntity> searchBesideStationList();

    /**
     * 根据APPID查询旁站信息
     *
     * @param id
     * @return
     */
    ProjectSideStationEntity getSideStationInfoByAppId(String id);


}
