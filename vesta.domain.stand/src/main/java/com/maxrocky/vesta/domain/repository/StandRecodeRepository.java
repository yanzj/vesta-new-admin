package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.StandRecodeEntity;

import java.util.List;

/**
 * Created by chen on 2016/5/17.
 */
public interface StandRecodeRepository {
    //新增记录
    void addStandRecode(StandRecodeEntity standRecodeEntity);
    //更新记录
    void updateStandRecode(StandRecodeEntity standRecodeEntity);
    //查询记录列表
    List<StandRecodeEntity> getStandRecodeList(String standId);
    //查询记录
    StandRecodeEntity getStandRecodeDetail(String id);
}
