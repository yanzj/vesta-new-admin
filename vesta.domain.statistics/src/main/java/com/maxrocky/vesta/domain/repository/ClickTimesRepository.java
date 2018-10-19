package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ClickTimesEntity;
import com.maxrocky.vesta.domain.model.ClickTimesPageEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by sunmei on 2016/2/15.
 */
public interface ClickTimesRepository {
    /**
     * 初始化列表，属性
     * @return
     */
    List<ClickTimesEntity> ClickManage(ClickTimesEntity clickTimesEntity,  WebPage webPage);

    /**
     * 根据模块类型查询点击量
     * @return
     */
    ClickTimesPageEntity getClickNums(ClickTimesEntity clickTimesEntity);

    /**
     * 根据模块类型查询点击量
     * @return
     */
    ClickTimesPageEntity getClickPageeNums(ClickTimesPageEntity clickTimesPageEntity);

    /**
     * 初始化首页模块列表，属性
     * @return
     */
    List<ClickTimesPageEntity> ClickPageManage(ClickTimesEntity clickTimesEntity,  WebPage webPage);

    /**
     * 首页模块点击量录入
     * @param clickTimesPageEntity
     */
    void AddClickTimesPage(ClickTimesPageEntity clickTimesPageEntity);

    /**
     * 各模块点击量录入
     * @param clickTimesEntity
     */
    void AddClickTimes(ClickTimesEntity clickTimesEntity);

    /**
     * 修改首页模块点击量
     */
    void updateClickTimesPage(ClickTimesPageEntity clickTimesPageEntity);

    /**
     * 修改各模块点击量
     */
    void updateClickTimes(ClickTimesEntity clickTimesEntity);

    /**
     * 查询该模块点击量是否已录入
     * @param projectId
     * @param companyId
     * @param regionId
     * @return
     */
    ClickTimesEntity getClickTimes(String projectId,String companyId,String regionId);
    /**
     * 查询首页该模块点击量是否已录入
     * @param projectId
     * @param companyId
     * @param regionId
     * @return
     */

    ClickTimesPageEntity getClickTimesPage(String projectId,String companyId,String regionId);



}
