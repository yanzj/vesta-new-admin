package com.maxrocky.vesta.application.baseData.inf;

import java.util.Map;

/**
 * Created by Magic on 2016/10/18.
 */
public interface GetAllClassifyService {
    /**
     * 获取一级分类
     *
     * @param domain 所属模块
     * @return Map
     */
    public Map<String, String> getClassifyOne(String domain);

    /**
     * 获取二级分类
     *
     * @param domain 所属模块
     * @return Map
     */
    public Map<String, String> getClassifyTwo(String firstid, String domain);

    /**
     * 获取三级分类
     *
     * @param domain 所属模块
     * @return Map
     */
    public Map<String, String> getClassifyThree(String secondId, String domain);

    /**
     * 获取四级分类
     *
     * @param domain 所属模块
     * @return Map
     */
    public Map<String, String> getClassifyFour(String secondId, String domain);

    /**
     * 获取所有项目
     *
     * @return
     */
    public Map<String, String> getAllproject();

    /**
     * 根据项目获取标段
     *
     * @return
     */
    public Map<String, String> getTendersByProjectId(String projectId);

    /**
     * 根据项目编码获取楼栋信息
     *
     * @return
     */
    public Map<String, String> getBuildingByTenderId(String tenderId);

    /**
     * 根据项目编码获取楼栋信息
     *
     * @return
     */
    public Map<String, String> getBuildingForProjectNum(String projectNum);


    /**
     * 根据项目id 获取责任单位
     *
     * @param projectId 项目id
     * @return Map
     */
    public Map<String, String> getDutyList(String projectId);


    /**
     * 根据责任单位 获取整改人
     *
     * @param dutyId 责任单位id
     * @return Map
     */
    public Map<String, String> getDutyPeople(String dutyId);

    /**
     * 根据项目id获取第三方监理
     *
     * @param projectId 责任单位id
     * @return Map
     */
    public Map<String, String> getSurveyorUserList(String projectId);

    /**
     * 查询当前人所拥有的项目
     *
     * @param staffId
     * @return
     */
    Map<String, String> getProjectProjectsByStaffId(String staffId);

    /**
     * 获取当前登录人的菜单权限
     *
     * @param staffId
     * @return
     */
    boolean getRoleViewmodelByStaffId(String staffId);
}
