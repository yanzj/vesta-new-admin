package com.maxrocky.vesta.domain.baseData.repository;

import com.maxrocky.vesta.domain.baseData.model.BuildingSupplierEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectBuildingEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by chen on 2016/10/17.
 */
public interface ProjectBuildingRepository {

    //新增楼栋
    void addProjectBuild(ProjectBuildingEntity projectBuildingEntity);

    //根据项目ID获取工程楼栋列表
    List<ProjectBuildingEntity> getBuildingsByProjectId(String projectId, WebPage webPage);

    List<ProjectBuildingEntity> getBuildingsByProjectId(String projectId);

    /**
     * 获取该项目下的楼栋数
     * @param projectId
     * @return
     */
    int getBuildingsCountByProjectId(String projectId);

    //修改
    void altProjectBuild(ProjectBuildingEntity projectBuildingEntity);

    //删除
    void delProjectBuild(String buildId);

    //详情
    ProjectBuildingEntity getBuildDetail(String buildId);

    /**
     * @param buildId
     * @param buildName
     * @param projectId
     * @return 查询库中是否有该名称
     */
    boolean checkThisName(String buildId, String buildName, String projectId);

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 新增楼栋
     */
    void insertBuilding(ProjectBuildingEntity projectBuildingEntity);

    /**
     * @param autoId
     * @param projectId
     * @param modifyTime
     * @return 判断工程楼栋是否有更新
     */
    boolean checkUpdateBuild(String autoId, String projectId, String modifyTime);

    /**
     * @param projectId
     * @param timeStamp
     * @param autoId
     * @return 同步工程楼栋数据
     */
    List<ProjectBuildingEntity> getBuildingForTime(String projectId, String timeStamp, long autoId);

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 根据楼栋名称，以及项目id，查询该楼栋是否存在
     */
    ProjectBuildingEntity getProjectBuildByName(String buildName, String projectId);

    /**
     * @param buildingSupplierEntity 新增责任单位与楼栋关系
     */
    void addSupplierBuild(BuildingSupplierEntity buildingSupplierEntity);

    /**
     * @param dutyId
     * @return 获取已存在的关系楼栋ID
     */
    List<String> getBuildSupplier(String dutyId);


    /**
     * @param dutyId
     * @return 获取当前项目下已存在的关系楼栋ID
     */
    List<String> getProjectBuildSupplier(String dutyId,String projectId);

    /**
     * @param buildingSupplierEntity 有则修改 无则新增
     */
    void dumpAddSupplierBuild(BuildingSupplierEntity buildingSupplierEntity);

    /**
     * @param dutyId 删除当前责任单位下关联的楼栋数据
     */
    void delSupplierBuildByDutyId(String dutyId);


    /**
     * @param dutyId 删除当前项目下责任单位下关联的楼栋数据
     */
    void delProjectSupplierBuildByDutyId(String dutyId,String projectId);

    /**
     * @param buildingSupplierEntity 删除某条关系
     */
    void deleteSupplierBuild(BuildingSupplierEntity buildingSupplierEntity);

    /**
     * 根据标段获取楼栋
     *
     * @param tenderId
     * @return
     */
    List<Object[]> getBuildingsByTenderId(String tenderId);

    /**
     * 根据总包Id查询楼栋信息
     *
     * @param supplierId
     * @return
     */
    List<Object[]> getBuildBySupplierId(String supplierId,String projectId);
}
