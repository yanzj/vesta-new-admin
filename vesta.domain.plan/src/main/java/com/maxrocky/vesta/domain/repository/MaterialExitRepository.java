package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.MaterialExitEntity;

import java.util.List;

/**
 * Created by chen on 2016/5/23.
 */
public interface MaterialExitRepository {
    //新增退场记录
    void addMaterialExit(MaterialExitEntity materialExitEntity);
    //获取退场记录列表
    List<MaterialExitEntity> getMaterialExitList();
}
