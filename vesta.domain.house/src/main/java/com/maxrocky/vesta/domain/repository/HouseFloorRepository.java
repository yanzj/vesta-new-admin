package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HouseFloorEntity;


import java.util.List;
import java.util.Map;

/**
 * Created by langmafeng on 2016/5/7/11:37.
 * 楼层Repository接口
 */
public interface HouseFloorRepository {
    /**
     * Describe:根据楼层id去查询信息
     * CreateBy:langmafeng
     * CreateOn:2016/5/7/11:37
     */
    HouseFloorEntity getInfoByFloorId(String floorId);

    /**
     * Describe:更新楼层信息
     * CreatedBy:langmafeng
     * Describe:2016/5/7/11:37
     *
     *
     */
    void updateBuildingInfo(HouseFloorEntity houseFloorEntity);

    /**
     * Describe:创建楼层信息
     * CreateBy:langmafeng
     * CreateOn:2016/5/7/11:37
     */
    void create(HouseFloorEntity houseFloorEntity);

    /**
     * 根据单元Id获得楼层列表
     * @param unitId
     * @return
     */
    List<HouseFloorEntity> getFloorListByUtilId(String unitId);

    List<HouseFloorEntity> getFloorListByUnitNum(String unitNum);
}
