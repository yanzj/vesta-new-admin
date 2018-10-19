package com.maxrocky.vesta.domain.baseData.repository;

import com.maxrocky.vesta.domain.baseData.model.ProjectFloorEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectHouseImageEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by chen on 2016/10/20.
 */
public interface ProjectFloorRepository {
    /**
     * 根据楼栋ID获取楼层数
     *
     * @param buildId
     * @return
     */
    String getFloorNumByBuildId(String buildId);

    /**
     * 根据楼栋ID获取楼层列表
     *
     * @param buildId
     * @return
     */
    List<ProjectFloorEntity> getFloorsByBuildId(String buildId, WebPage webPage);

    /**
     * 新增楼层
     *
     * @param projectFloorEntity
     */
    void addProjectFloor(ProjectFloorEntity projectFloorEntity);

    /**
     * 更新楼层
     *
     * @param projectFloorEntity
     */
    void updateProjectFloor(ProjectFloorEntity projectFloorEntity);

    /**
     * 获取工程楼层详情
     *
     * @param floorId
     * @return
     */
    ProjectFloorEntity getProjectFloorDetail(String floorId);

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 批量修改楼层的时候，检验该楼层是否存在
     */
    ProjectFloorEntity getProjectFloor(String floorName, String buildId);

    /**
     * @param floorName
     * @param buildId
     * @param floorId
     * @return 校验当前楼层名是否存在
     */
    boolean checkThisFloorName(String floorName, String buildId, String floorId);

    /**
     * @param projectId
     * @param timeStamp
     * @param autoId
     * @return 同步工程楼层信息
     */
    List<ProjectFloorEntity> getFloorListForTime(String projectId, String timeStamp, long autoId);

    /**
     * @param projectId
     * @param timeStamp
     * @param autoId
     * @return 同步工程楼层户型图信息
     */
    List<ProjectHouseImageEntity> getHouseImageForTime(String projectId, String timeStamp, long autoId);

    /**
     * 根据楼栋ID 查询所有楼层
     *
     * @param id
     * @return
     */
    List<Object[]> getBuildFloorsByProject(String id);

    /**
     * 根据楼栋ID查询楼层
     *
     * @param buildId
     * @return
     */
    List<ProjectFloorEntity> getProjectFloorsByBuildId(String buildId);
}
