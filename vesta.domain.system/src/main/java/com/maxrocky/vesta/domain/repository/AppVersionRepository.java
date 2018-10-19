package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.AppVersionEntity;
import com.maxrocky.vesta.taglib.page.WebPage;


import java.util.List;

/**
 * Created by zhanghj on 2016/1/28.
 */
public interface AppVersionRepository {

    /**
     * 根据手机类型获取版本列表
     * @param appVersionEntity
     * @return
     */
    public List<AppVersionEntity> getVersionByType(AppVersionEntity appVersionEntity,WebPage webPage);

    /**
     * 根据手机类型获取最新版本号
     * @param type
     * @return
     */
    public AppVersionEntity getNewVersionByType(String type,String userType);

    /**
     * 添加版本信息
     * @param appVersionEntity
     * @return
     */
    public boolean saveAppVersion(AppVersionEntity appVersionEntity);

    /**
     * 修改版本消息
     * @param appVersionEntity
     * @return
     */
    public boolean updateAppVersion(AppVersionEntity appVersionEntity);

    /**
     * 根据id获取版本信息
     * @param id
     * @return
     */
    public AppVersionEntity getVersionBy(String id);

    /**
     * 通过appVersionType获得版本详情
     * 2016-07-19_Wyd
     * @param appVersionType IOS:1,安卓:2
     * @return AppVersionEntity
     */
    AppVersionEntity getAppVersionByAppType(String appVersionType);
}
