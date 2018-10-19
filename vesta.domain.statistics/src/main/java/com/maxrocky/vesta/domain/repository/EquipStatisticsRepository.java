package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ClickTimesEntity;
import com.maxrocky.vesta.domain.model.EquipStatisticsEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by sunmei on 2016/2/17.
 */
public interface EquipStatisticsRepository {
    /**
     * 初始化列表，属性
     * @return
     */
    List<EquipStatisticsEntity> EquipManage(EquipStatisticsEntity equipStatisticsEntity, WebPage webPage);

    int getEquipCount(String projectId,String type);

    void addEquipManage(EquipStatisticsEntity equipStatisticsEntity);

    void updateEquipManage(EquipStatisticsEntity equipStatisticsEntity);

    EquipStatisticsEntity getEquipStatistics(String projectId,String userId);
}
