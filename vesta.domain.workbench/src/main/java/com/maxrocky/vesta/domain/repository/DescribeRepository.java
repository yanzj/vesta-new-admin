package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.DescribeEntity;

import java.util.List;

/**
 * Created by liudongxin on 2016/6/15.
 * 三级分类：简要描述数据操作
 */
public interface DescribeRepository {
    /**
     * Describe:根据id获取信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-14 09:40:37
     */
    DescribeEntity get(String id);
    /**
     * Describe:创建简要描述
     * CreateBy:liudongxin
     * CreateOn:2016-01-17 05:19:23
     */
    void create(DescribeEntity describeEntity);
    /**
     * Describe:修改简要描述
     * CreatedBy:liudongxin
     * ModifyBy:
     */
    void update(DescribeEntity describeEntity);

    /**
     * CreatedBy:dl
     * Describe:
     * 删除简要描述
     * ModifyBy:
     */
    void delete();

    /**
     * 根据ID查询
     * @return
     */
    List<DescribeEntity> getListsByIds(String ids);

    /**
     * 根据三级分类查询
     * @param thirdId
     * @return
     */
    List<DescribeEntity> getListByThirdId(String thirdId);
}
