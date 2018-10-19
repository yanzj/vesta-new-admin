package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.QualityLogEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Talent on 2016/11/16.
 */
public interface QualityLogRepository {
    /**
     * 新增日志信息
     *
     * @param qualityLogEntity
     * @return
     */
    boolean addQualityLogInfo(QualityLogEntity qualityLogEntity);

    /**
     * 查询日志列表
     *
     * @param map
     * @return
     */
    List<QualityLogEntity> getQualityLogList(Map map, WebPage webPage);

    /**
     * 查询日志列表(不带分页)
     *
     * @param map
     * @return
     */
    List<QualityLogEntity> getQualityLogList(Map map);
}
