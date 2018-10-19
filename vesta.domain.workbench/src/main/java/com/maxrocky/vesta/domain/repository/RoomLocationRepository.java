package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.RoomLocationEntity;

import java.util.List;

/**
 * Created by chen on 2016/4/22.
 */
public interface RoomLocationRepository {
    //保存房间位置
    void SaveLocation(RoomLocationEntity roomLocationEntity);
    //修改房间位置
    void UpdateLocation(RoomLocationEntity roomLocationEntity);
    //获取房间位置
    RoomLocationEntity getRoomLocation();
    /**
     * 获取房屋位置列表
     * @return List<RoomLocationEntity>
     */
    List<RoomLocationEntity> getRoomLocations();
}
