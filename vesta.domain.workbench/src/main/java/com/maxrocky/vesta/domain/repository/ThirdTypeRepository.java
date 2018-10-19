package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ThirdTypeEntity;

/**
 * Created by chen on 2016/4/22.
 */
public interface ThirdTypeRepository {
    //新增三级分类
    void AddThirdType(ThirdTypeEntity thirdTypeEntity);
    //修改三级分类
    void UpdateThirdType(ThirdTypeEntity thirdTypeEntity);
}
