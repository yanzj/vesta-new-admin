package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HouseCityEntity;
import com.maxrocky.vesta.domain.model.HouseProjectEntity;

import java.util.List;

/**
 * Created by langmafeng on 2016/5/7/11:03.
 * 城市Repository接口
 */
public interface HouseCityRepository {

    /**
     * Describe:根据城市id去查询信息
     * CreateBy:langmafeng
     * CreateOn:2016/5/7/11:03.
     */
    HouseCityEntity getInfoByCityId(String cityId);

    /**
     * Describe:更新城市信息
     * CreatedBy:langmafeng
     * Describe:2016/5/7/11:03.
     */
    void updateBuildingInfo(HouseCityEntity houseCityEntity);

    /**
     * Describe:创建城市信息
     * CreateBy:langmafeng
     * CreateOn:2016/5/7/11:03.
     */
    void create(HouseCityEntity houseCityEntity);

    /**
     * 获取城市列表
     *
     * @return
     */
    List<HouseCityEntity> getCityList();
    /**
     * 获取城市列表
     *
     * @return
     */
    List<HouseCityEntity> getCityListMagic(List<String> citys);
}

