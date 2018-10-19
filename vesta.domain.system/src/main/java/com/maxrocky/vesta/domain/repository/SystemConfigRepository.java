package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.SystemConfigEntity;

import java.util.List;

/**
 * Created by Tom on 2016/1/13 20:11.
 * Describe:系统配置Repository接口
 */
public interface SystemConfigRepository {

    /**
     * Describe:根据系统代码获取系统配置信息。
     * CreateBy:Tom
     * CreateOn:2016-01-13 08:12:05
     */
    SystemConfigEntity get(String configCode);

    /**
     * Describe:获取系统配置列表
     * CreateBy:Tom
     * CreateOn:2016-03-08 11:07:04
     */
    List<SystemConfigEntity> getList();

}
