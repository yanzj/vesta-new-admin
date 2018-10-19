package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HouseFormatEntity;

import java.util.List;

/**
 * Created by Tom on 2016/1/18 10:48.
 * Describe:业态Repository接口
 */
public interface HouseFormatRepository {

    /**
     * Describe:根据项目Id获取所有业态
     * CreateBy:Tom
     * CreateOn:2016-01-18 10:49:26
     */
    List<HouseFormatEntity> getList(String projectId);

    /**
     * Describe:根据业态Id获取业态
     * CreateBy:Tom
     * CreateOn:2016-01-18 03:10:07
     */
    HouseFormatEntity get(String formatId);

    /**
     * Describe:根据业态名称获取业态
     * CreateBy:Tom
     * CreateOn:2016-01-22 04:28:07
     */
    HouseFormatEntity getByName(String formatName);

    /**
     * Describe:返回指定项目ID、业态名称的业态
     * CreateBy:Tom
     * CreateOn:2016-03-16 04:01:29
     */
    HouseFormatEntity getByProjectIdAndName(String projectId,String formatName);
}
