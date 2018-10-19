package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ComplainEntity;
import com.maxrocky.vesta.domain.model.PropertyImageEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2017/7/19.
 */
public interface ComPlainRepository {
    /**
     * 根据投诉单号查询投诉单
     */
    ComplainEntity getComplainEntity(String id);


    /**
     * 保存投诉单信息
     */
    void saveComplain(ComplainEntity complainEntity);

    /**
     * 修改投诉单信息
     */
    void updateComplain(ComplainEntity complainEntity);

    /**
     * 分页获取投诉单信息
     *
     * @param map
     * @param webPage
     * @return
     */
    List<Object[]> getComplainList(Map map, WebPage webPage);

    /**
     * 查询总条数
     *
     * @param map
     * @return
     */
    int getCount(Map map);

    /**
     * 投诉单详情
     *
     * @param complainId
     * @return
     */
    Object[] getComplainInfoById(String complainId);

    /**
     * 投诉单图片信息
     *
     * @param complainId
     * @return
     */
    List<PropertyImageEntity> getComplainImageList(String complainId);

    /**
     * 获取投诉信息
     *
     * @param map
     * @return
     */
    List<Object[]> getComplainList(Map map);
}
