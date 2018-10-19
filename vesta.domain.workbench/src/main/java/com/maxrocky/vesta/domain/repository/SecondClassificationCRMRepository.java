package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.SecondClassificationEntity;

import java.util.List;

/**
 * Created by dl on 2016/5/9.
 */
public interface SecondClassificationCRMRepository {
    /**
     * Describe:根据id获取信息
     * CreateBy:dl
     * CreateOn:2016-01-14 09:40:37
     */
    SecondClassificationEntity get(String id);
    /**
     * Describe:创建二级分类
     * CreateBy:dl
     * CreateOn:2016-01-17 05:19:23
     */
    void create(SecondClassificationEntity secondClassificationEntity);
    /**
     * CreatedBy:dl
     * Describe:
     * 修改二级分类
     * ModifyBy:
     */
    void update(SecondClassificationEntity secondClassificationEntity);

    /**
     * CreatedBy:dl
     * Describe:
     * 删除二级分类
     * ModifyBy:
     */
    void delete();

    List<SecondClassificationEntity> getSecondClassification(String firstid);
}
