package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.FirstClassificationEntity;

import java.util.List;

/**
 * Created by dl on 2016/5/9.
 */
public interface FirstClassificationCRMRepository {

    /**
     * Describe:根据id获取信息
     * CreateBy:dl
     * CreateOn:2016-01-14 09:40:37
     */
    FirstClassificationEntity get(String id);
    /**
     * Describe:创建一级分类
     * CreateBy:dl
     * CreateOn:2016-01-17 05:19:23
     */
    void create(FirstClassificationEntity firstClassificationEntity);
    /**
     * CreatedBy:dl
     * Describe:
     * 修改一级分类
     * ModifyBy:
     */
    void update(FirstClassificationEntity firstClassificationEntity);

    /**
     * CreatedBy:dl
     * Describe:
     * 删除一级分类
     * ModifyBy:
     */
    void delete();

    List<FirstClassificationEntity> getFirstClassification();
}

