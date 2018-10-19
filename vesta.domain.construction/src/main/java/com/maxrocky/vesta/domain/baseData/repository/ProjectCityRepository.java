package com.maxrocky.vesta.domain.baseData.repository;

import com.maxrocky.vesta.domain.baseData.model.ProjectCityEntity;

import java.util.List;

/**
 * Created by chen on 2016/12/6.
 */
public interface ProjectCityRepository {

    /**
     * @param projectCityEntity 新增工程 城市
     */
    void addProjectCity(ProjectCityEntity projectCityEntity);

    /**
     * @param projectCityEntity 修改城市
     */
    void updateProjectCity(ProjectCityEntity projectCityEntity);

    /**
     * @return 获取所有城市列表
     */
    List<ProjectCityEntity> getAllCityList();

    /**
     * @param optId
     * @return 根据经营单位ID获取对应城市列表
     */
    List<ProjectCityEntity> getCityByOptId(String optId);

    /**
     * @param cityId
     * @return 获取城市详情
     */
    ProjectCityEntity getCityDetail(String cityId);

    /**
     * @param cityName
     * @return 获取城市详情
     */
    boolean checkCityDetail(String cityName);

    /**
     * 根据区域获取项目信息
     *
     * @param optId
     * @return
     */
    List<Object[]> getProejctListByOptId(String optId);
}
