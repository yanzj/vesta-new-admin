package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.HouseBuildingDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Tom on 2016/1/18 11:17.
 * Describe:楼Service接口
 */
public interface HouseBuildingService {

    /**
     * Code:HI0003
     * Type:UI Method
     * Describe:根据项目Id获取楼列表
     * CreateBy:Tom
     * CreateOn:2016-01-18 11:26:34
     */
    ApiResult getBuildingListByProjectId(String projectId, String formatId);

    /**
     * 根据区域ID获取楼栋列表
     *
     * @param projectNum
     * @return
     */
    public Map getBuildListByProjectNum(String projectNum);

    /**
     * 根据城市获取项目列表
     *
     * @param cityNum
     * @return
     */
    public Map getProjectListByCityNum(String cityNum);

    /**
     * 根据项目ID获取楼栋列表
     *
     * @param projectId
     * @return
     */
    public Map getBuildListByProjectId(String projectId);

    /**
     * Code:D
     * Type:
     * Describe:根据项目ID获取楼栋详细信息列表
     * CreateBy:zhangzhaowen
     * CreateOn:2016/8/30
     */
    public List<Map<String, String>> getBuildInfoListByProjectId(String projectId, String buildingId, String alias, WebPage webPage);


    public List<Map<String, String>> getBuildInfoListByProjectIdNew(String projectId, String buildingId, String alias, WebPage webPage,String areaId);

    List<HouseBuildingDTO> getHouseBuildingList(String projectId, String buildingId, String alias, WebPage webPage);

    /**
     * Code:D
     * Type:
     * Describe:更新楼栋别名
     * CreateBy:zhangzhaowen
     * CreateOn:2016/8/30
     */

    public void updateBuildingAlias(String buildingNum, String buildingAlias);

    /**
     * 通过项目编码获取地块列表 WeiYangDong_2016-11-04
     *
     * @param projectNum 项目编码
     * @return Map
     */
    Map getAreaListByProjectNum(String projectNum);

    /**
     * 通过地块编码获取楼栋列表 WeiYangDong_2016-11-04
     *
     * @param blockNum 地块编码
     * @return Map
     */
    Map getBuildListByBlockNum(String blockNum);

}
