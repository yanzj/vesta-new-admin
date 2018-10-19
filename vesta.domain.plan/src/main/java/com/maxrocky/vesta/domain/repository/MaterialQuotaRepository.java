package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.MaterialQuotaEntity;

import java.util.List;

/**
 * Created by chen on 2016/5/23.
 */
public interface MaterialQuotaRepository {
    //新增指标记录
    void addMaterialQuota(MaterialQuotaEntity materialQuotaEntity);
    //根据材料ID获取指标记录列表
    List<MaterialQuotaEntity> MaterialQuotaList(String materialId);
}
