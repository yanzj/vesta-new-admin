package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.BuildingMappingTimeEntity;
import com.maxrocky.vesta.domain.model.HouseAreaEntity;
import com.maxrocky.vesta.domain.model.HouseBuildingEntity;
import com.maxrocky.vesta.domain.model.HouseProjectEntity;
import com.maxrocky.vesta.taglib.page.WebPage;


import java.util.List;

/**
 * Created by Tom on 2016/1/18 11:12.
 * Describe:楼Repository接口
 */
public interface HouseBuildingRepository {

    /**
     * Describe:根据项目Id获取楼列表
     * CreateBy:Tom
     * CreateOn:2016-01-18 11:13:52
     */
    List<HouseBuildingEntity> getListByProjectId(String projectId, String formatId);

    /**
     * 根据项目编码获取楼栋列表
     * */
    List<HouseBuildingEntity> getListByProjectCode(String projectCode);

    /**
     * Describe:根据楼Id获取楼
     * CreateBy:Tom
     * CreateOn:2016-01-18 03:13:15
     */
    HouseBuildingEntity get(String buildingId);

    /**
     * Describe:根据项目Id、楼名获取楼
     * CreateBy:Tom
     * CreateOn:2016-01-22 04:14:04
     */
    HouseBuildingEntity getByBuildingNameAmdProjectId(String buildingName, String projectId);

    /**
     * Describe:返回指定楼名、项目ID、业态ID
     * CreateBy:Tom
     * CreateOn:2016-01-22 04:14:04
     */
    HouseBuildingEntity getByBuildingNameAmdProjectId(String buildingName, String projectId, String formatId);

    /**
     * 根据项目获取楼下拉框
     * @param projectId
     * @return
     */
    List<HouseBuildingEntity> mapBuild(String projectId);
    /**
     * Describe:根据楼栋id去查询信息
     * CreateBy:langmafeng
     * CreateOn:2016-04-28
     */
    HouseBuildingEntity getInfoByBuildingId(String BuildingId);
    /**
     * Describe:根据楼栋id去查询信息
     * CreateBy:langmafeng
     * CreateOn:2016-04-28
     */
    HouseBuildingEntity getInfoByBuildingNum(String BuildingNum);
    /**
     * Describe:根据楼栋id去查询信息
     * CreateBy:langmafeng
     * CreateOn:2016-04-28
     */
    BuildingMappingTimeEntity getBuildingMappingInfoByBuildingNum(String BuildingNum);

    /**
     * Describe:更新楼栋信息
     * CreatedBy:langmafeng
     * Describe:2016-04-28 17:32
     *
     *
     */
    void updateBuildingInfo(HouseBuildingEntity houseBuildingEntity);
    /**
     * Describe:更新楼栋信息
     * CreatedBy:langmafeng
     * Describe:2016-04-28 17:32
     *
     *
     */
    void updateBuildingMappingInfo(BuildingMappingTimeEntity buildingMappingTimeEntity);

    /**
     * Describe:创建楼栋信息
     * CreateBy:langmafeng
     * CreateOn:2016-04-28 17:35
     */
    void create(HouseBuildingEntity houseBuildingEntity);

    /**
     * 根据区域获取楼下拉框
     * @param projectNum
     * @return
     */
    List<HouseBuildingEntity> getBuildListByProjectNum(String projectNum);

    /**
     * 根据城市获取项目下拉框
     * @param cityNum
     * @return
     */
    List<HouseProjectEntity> getProjectListByCityNum(String cityNum);


    /**
     * 根据项目获取楼下拉框
     * @param projectId
     * @return
     */
    List<HouseBuildingEntity> getBuildListByProjectId(String projectId);

    /**
     * Code:D
     * Type:
     * Describe:更新楼栋别名
     * CreateBy:zhangzhaowen
     * CreateOn:2016/8/30
     */
    public void updateBuildingAlias(String buildingNum, String buildingAlias);
    /**
     * Code:D
     * Type:
     * Describe:获得->更新楼栋别名
     * CreateBy:zhangzhaowen
     * CreateOn:2016/8/30
     */
    public List<HouseBuildingEntity>  getBuildAliasListByProjectId(String projectId,String buildingId,String alias ,WebPage webPage,String areaId);

    /**
     * 通过项目编码获取地块列表 WeiYangDong_2016-11-04
     * @param projectNum    项目编码
     * @return  List<HouseAreaEntity>
     */
    List<HouseAreaEntity> getAreaListByProjectNum(String projectNum);

    /**
     * 通过地块编码获取楼栋列表 WeiYangDong_2016-11-04
     * @param blockNum    地块编码
     * @return  List<HouseBuildingEntity>
     */
    List<HouseBuildingEntity> getBuildListByBlockNum(String blockNum);

}
