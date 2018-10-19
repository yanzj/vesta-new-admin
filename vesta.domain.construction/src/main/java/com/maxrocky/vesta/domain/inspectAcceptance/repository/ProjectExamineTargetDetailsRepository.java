package com.maxrocky.vesta.domain.inspectAcceptance.repository;

import com.maxrocky.vesta.domain.inspectAcceptance.model.ProjectExamineTargetDetailsEntity;
import com.maxrocky.vesta.domain.inspectAcceptance.model.ProjectExamineTargetEntity;

/**
 * Created by jiazefeng on 2016/10/20.
 */
public interface ProjectExamineTargetDetailsRepository {
    /**
     * 新增工程检查验收指标详情信息
     *
     * @param projectExamineTargetDetailsEntity
     * @return
     */
    boolean addProjectExamineTargetInfo(ProjectExamineTargetDetailsEntity projectExamineTargetDetailsEntity);
}
