package com.maxrocky.vesta.domain.baseData.repository;

import com.maxrocky.vesta.domain.baseData.model.ProjectOperationEntity;

import java.util.List;

/**
 * Created by chen on 2016/12/7.
 */
public interface ProjectOperationRepository {

    /**
     * @param projectOperationEntity
     * 新增经营单位
     */
    void addProjectOperation(ProjectOperationEntity projectOperationEntity);

    /**
     * @param projectOperationEntity
     * 更新经营单位
     */
    void updateProjectOperation(ProjectOperationEntity projectOperationEntity);

    /**
     * @param optId
     * @return
     * 获取经营单位详情
     */
    ProjectOperationEntity getProjectOperationDetail(String optId);

    /**
     * @return
     * 获取经营单位列表
     */
    List<ProjectOperationEntity> getProjectOperationList();

}
