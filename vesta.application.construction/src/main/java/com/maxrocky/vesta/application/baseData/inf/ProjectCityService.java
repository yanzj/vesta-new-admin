package com.maxrocky.vesta.application.baseData.inf;

import com.maxrocky.vesta.application.baseData.adminDTO.OperationDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by chen on 2016/12/7.
 */
public interface ProjectCityService {
    /**
     * @param optId
     * @return 根据经营单位ID获取对应区域城市列表
     */
    Map getCityListByOptId(String optId);

    /**
     * @return 获取经营单位列表
     */
    Map getProjectOperationList();

    /**
     * @return 经营单位列表
     */
    List<OperationDTO> getOperationList();

    /**
     * @return 城市列表
     */
    List<OperationDTO> getAllCityList();

    /**
     * @param optName 新增经营单位
     */
    void addProjectOperation(String optName);

    /**
     * @param operationDTO 更新经营单位
     */
    void updateProjectOperation(OperationDTO operationDTO);

    /**
     * @param optId 删除经营单位
     */
    void delProjectOperation(String optId);

    /**
     * @param operationDTO 新增工程城市区域
     */
    void addProjectCity(OperationDTO operationDTO);

    /**
     * @param operationDTO 更新城市区域
     */
    void updateProjectCity(OperationDTO operationDTO);

    /**
     * @param cityId 删除城市区域
     */
    void delProjectCity(String cityId);

    /**
     * @param cityName 校验工程城市区域
     */
    boolean checkProjectCity(String cityName);

    /**
     * 根据区域获取项目信息
     *
     * @param optId
     * @return
     */
    Map getProejctListByOptId(String optId);
}
