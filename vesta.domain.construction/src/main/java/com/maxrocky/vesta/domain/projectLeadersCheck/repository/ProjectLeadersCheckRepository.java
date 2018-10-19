package com.maxrocky.vesta.domain.projectLeadersCheck.repository;

import com.maxrocky.vesta.domain.baseData.model.ProjectImagesEntity;
import com.maxrocky.vesta.domain.projectLeadersCheck.model.ProjectLeadersCheckDetailEntity;
import com.maxrocky.vesta.domain.projectLeadersCheck.model.ProjectLeadersCheckEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Talent on 2017/1/16.
 */
public interface ProjectLeadersCheckRepository {
    /**
     * 分页获取领导检查数据
     *
     * @param map
     * @param webPage
     * @return
     */
    List<ProjectLeadersCheckEntity> getLeaderCheckList(Map map, WebPage webPage);

    /**
     * 根据权限获取领导检查数据
     *
     * @param map
     * @param webPage
     * @param staffId
     * @return
     */
    List<ProjectLeadersCheckEntity> getLeaderCheckList(Map map, WebPage webPage, String staffId);

    /**
     * 根据Id查询领导检查信息
     *
     * @param checkId
     * @return
     */
    ProjectLeadersCheckEntity getLeaderCheckById(String checkId);

    /**
     * 根据领导检查ID查询对应的详情
     *
     * @param checkId
     * @return
     */
    List<ProjectLeadersCheckDetailEntity> getListByCheckId(String checkId);

    /**
     * 查询对应的图片信息
     *
     * @param id
     * @return
     */
    List<ProjectImagesEntity> getProjectImages(String id);

    /**
     * 编辑领导检查信息
     *
     * @param leadersCheckEntity
     * @return
     */
    boolean modifyLeaderCheck(ProjectLeadersCheckEntity leadersCheckEntity);
}
