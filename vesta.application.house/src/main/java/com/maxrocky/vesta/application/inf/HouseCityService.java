package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.HouseCityDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.HouseProjectEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by liudongxin on 2016/5/18.
 * 城市service接口
 */
public interface HouseCityService {
    /**
     * CreatedBy:liudongxin
     * Describe:获取城市
     * ModifyBy:
     * return
     */
    Map<String,String> getCity();

    /**
     *  取得城市列表
     * @return
     */
    ApiResult getCitys();

    /**
     * 获取上次选中的项目
     * @param userId
     * @return
     */
    public HouseProjectEntity getDefaultProject(String userId);

    /**
     * @param houseCityDTO
     */
    void addCityArea(HouseCityDTO houseCityDTO);

    /**
     * 获取工程阶段区域下拉框数据
     * @return
     */
    List<HouseCityDTO> getSelectCity();
}
