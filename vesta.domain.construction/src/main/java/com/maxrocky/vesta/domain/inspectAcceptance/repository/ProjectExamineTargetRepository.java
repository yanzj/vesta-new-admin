package com.maxrocky.vesta.domain.inspectAcceptance.repository;

import com.maxrocky.vesta.domain.inspectAcceptance.model.ProjectExamineTargetEntity;

import java.util.List;

/**
 * Created by jiazefeng on 2016/10/19.
 */
public interface ProjectExamineTargetRepository {
    /**
     * 新增工程检查验收指标信息
     *
     * @param projectExamineTargetEntity
     * @return
     */
    boolean addProjectExamineTargetInfo(ProjectExamineTargetEntity projectExamineTargetEntity);

    /**
     * 检索检查验收指标详细信息
     *
     * @param batchId
     * @return
     */
    List<Object[]> searchProjectExamineTargetListByBatchId(String batchId);

    /**
     * 检索检查验收指标整改记录详细信息
     *
     * @param examineTargetId 验收指标ID
     * @return
     */
    List<Object[]> searchProjectExamineTargetCheckListByBatchId(String examineTargetId);

    /**
     * 检索检查验收指标验收记录详细信息
     *
     * @param examineTargetId 验收指标ID
     * @return
     */
    List<Object[]> searchProjectExamineTargetAcceptanceListByBatchId(String examineTargetId);

    /**
     * 检索检查验收指标信息
     *
     * @param batchId 批次ID
     * @return
     */
    List<ProjectExamineTargetEntity> getProjectExamineTargetListByBatchId(String batchId);

    /**
     * 查询验收指标信息
     *
     * @param id
     * @return
     */
    ProjectExamineTargetEntity getProjectExamineTargetById(String id);

    /**
     * 更新检查验收指标信息
     *
     * @param projectExamineTargetEntity
     */
    void updateProjectExamineTarget(ProjectExamineTargetEntity projectExamineTargetEntity);
}
