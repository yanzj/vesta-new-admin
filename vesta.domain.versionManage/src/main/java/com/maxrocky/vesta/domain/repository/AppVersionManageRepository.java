package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.AppVersionInfoEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Talent on 2016/11/3.
 */
public interface AppVersionManageRepository {
    /**
     * 分页查询app版本列表
     *
     * @param map
     * @param webPage
     * @return
     */
    List<AppVersionInfoEntity> getVersionListByType(Map map, WebPage webPage);

    /**
     * 添加版本信息
     *
     * @param appVersionEntity
     * @return
     */
    boolean saveAppVersion(AppVersionInfoEntity appVersionEntity);

    /**
     * 根据ID查询版本信息
     *
     * @param appVersionId
     * @return
     */
    AppVersionInfoEntity getVersionById(String appVersionId);

    /**
     * 编辑版本信息
     *
     * @param appVersionInfoEntity
     * @return
     */
    boolean updateAppVersion(AppVersionInfoEntity appVersionInfoEntity);

    /**
     * 质检APP检查最新版本信息
     *
     * @param appVersionType
     * @return
     */
    AppVersionInfoEntity getNewVersionByAppVersionType(String appVersionType,String appEdition);
}
