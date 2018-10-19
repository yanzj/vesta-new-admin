package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.MaterialPlanEntity;

import java.util.List;

/**
 * Created by chen on 2016/5/23.
 */
public interface MaterialPlanRepository {
    //获取材料验收记录列表
    List<MaterialPlanEntity> getMaterialPlanList(String projectId);
    //获取材料验收记录详情
    MaterialPlanEntity getMaterialPlanDetail(String materialId);
    //新增材料验收记录
    void addMaterialPlan(MaterialPlanEntity materialPlanEntity);
    //获取验收列表
    List<MaterialPlanEntity> getMaterialPlans();
}
