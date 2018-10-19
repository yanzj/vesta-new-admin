package com.maxrocky.vesta.domain.baseData.repository;

import com.maxrocky.vesta.domain.baseData.model.*;
import com.maxrocky.vesta.domain.model.AgencyEntity;

import java.util.List;

/**
 * Created by chen on 2016/10/24.
 */
public interface ProjectCategoryRepository {
    /**
     * @param projectCategoryEntity
     * 新增检查项
     */
    void addProjectCategory(ProjectCategoryEntity projectCategoryEntity);

    /**
     * @param projectCategoryEntity
     * 更新检查项
     */
    void updateProjectCategory(ProjectCategoryEntity projectCategoryEntity);

    /**
     * @param domain
     * @return List<ProjectCategoryEntity>
     * 根据所属模块获取检查项列表
     */
    List<ProjectCategoryEntity> getCategoryListByDomain(String domain);

    /**
     * @param categoryId
     * @return
     * 获取检查项详情
     */
    ProjectCategoryEntity getCategoryDetail(String categoryId);

    /**
     * @param categoryId
     * 删除检查项
     */
    void delCategory(String categoryId);

    /**
     * @param supplierCategoryEntity
     * 保存检查项与责任单位关系
     */
    void addCategorySupplier(SupplierCategoryEntity supplierCategoryEntity);

    /**
     * @param autoId
     * @param timeStamp
     * @return
     * 同步三级分类数据
     */
    List<ProjectCategoryEntity> getCategoryListForTime(long autoId,String timeStamp);

    /**
     * @param projectId
     * @param timeStamp
     * @param autoId
     * @return
     * 同步责任单位与检查项关系数据
     */
    List<SupplierCategoryEntity> getSupplierCategoryForTime(String projectId,String timeStamp,long autoId);

    /**
     * @param projectId
     * @param timeStamp
     * @param autoId
     * @return
     * 同步责任单位与楼栋关系数据
     */
    List<BuildingSupplierEntity> getSupplierBuildingForTime(String projectId,String timeStamp,long autoId);

    /**
     * @param autoId
     * @param timeStamp
     * @return
     * 同步检查指标数据
     */
    List<ProjectTargetEntity> getProjectTargetForTime(long autoId,String timeStamp);

    /**
     * @param dutyId
     * @return
     * 根据责任单位Id获取检查项信息
     */
    List<SupplierCategoryEntity> getSupplierCategorys(String dutyId,String domain);

    /**
     * @param supplierCategoryEntity
     * 更新责任单位检查项信息
     */
    void updateSupplierCategory(SupplierCategoryEntity supplierCategoryEntity);

    /**
     * @return
     * 获取责任单位列表
     */
    List<AgencyEntity> getEmploys();

    /**
     * @return
     * 获取监理列表
     */
    List<AgencyEntity> getSurveyors();

    /**
     * 根据 级别，所属模块 以及父级id查询检查项
     * @param level 级别
     * @param domain 所属模块
     * @param parentId 父级id
     * */
    List<ProjectCategoryEntity> getProjectCategoryListAll(int level,String domain,String parentId);

    /**
     * @param categoryId
     * @return
     * 根据当前ID获取下级检查项
     */
    List<ProjectCategoryEntity> getTreeCategoryList(String categoryId);

    /**
     * @param categoryId
     * @return
     * 判断是否有下级
     */
    boolean checkIsParent(String categoryId);

    /**
     * @param supplierCategoryEntity
     * 保存责任单位与检查项关系
     */
    void addSupplierCategory(SupplierCategoryEntity supplierCategoryEntity);

    /**
     * @param supplierCategoryEntity
     * @return
     * 获取当前责任单位当前模块下的检查项ID
     */
    List<String> getCategoryIds(SupplierCategoryEntity supplierCategoryEntity);

    /**
     * @param dutyId
     * 删除当前责任单位下检查项关系
     */
    void delSupplierCategory(String dutyId);

    /**
     * @param supplierCategoryEntity
     * 删除责任单位与检查项关系
     */
    void deleteSupplierCategory(SupplierCategoryEntity supplierCategoryEntity);

    /**
     * @param dutyId
     * @param domain
     * @param parentId
     * @return
     * 根据责任单位ID和模块获取检查项信息
     */
    List<ProjectCategoryEntity> getCategoryByDutyId(String dutyId,String domain,String parentId);

    /**
     * @param dutyId
     * @param domain
     * @return
     * 根据责任单位获取所有检查项信息
     */
    List<ProjectCategoryEntity> getAllCategoryByDutyId(String dutyId,String domain);

    /**
     * @param domain
     * @param categoryId
     * @return
     * 根据模块和父级ID获取检查项
     */
    List<ProjectCategoryEntity> getCategoryTree(String domain,String categoryId);

    /**
     * @param categoryId
     * @return
     * 根据检查项ID获取指标列表
     */
    List<ProjectTargetEntity> getTargetList(String categoryId);

    /**
     * @param categoryName
     * @return
     * 根据名称获取检查项信息
     */
    ProjectCategoryEntity getProjectCategoryByName(int level,String categoryName,String parentId,String domain);

    /**
     * @param targetName
     * @param categoryId
     * @return
     * 根据指标名称 和检查项ID获取指标
     */
    ProjectTargetEntity getTargetByName(String targetName,String categoryId);
    /**
     * @param projectTargetEntity
     * 新增或更新指标
     */
    void addOrUpdateTarget(ProjectTargetEntity projectTargetEntity);
    void addTarget(ProjectTargetEntity projectTargetEntity);
    void updateTarget(ProjectTargetEntity projectTargetEntity);

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Param:
     *  @Description: 保存检查项和标段关系
     */
    void addTenderCategory(TendersCategoryEntity tenderCategoryEntity);

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Param:
     *  @Description: 获取当前标段当前模块下的检查项id
     */
    List<String> getCategoryByIds(TendersCategoryEntity tendersCategoryEntity);

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Param:
     *  @Description: 删除标段与检查项关系
     */
    void deleteTenderCategory(TendersCategoryEntity tendersCategoryEntity);

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Param:
     *  @Description: 根据标段id和模块获取检查项信息
     */
    List<ProjectCategoryEntity> getCategoryByTenderId(String tenderId, String domain, String parentId);

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Param:
     *  @Description: 根据标段获取所有检查项信息
     */
    List<ProjectCategoryEntity> getAllCategoryByTenderId(String tenderId, String domain);

    /**
     * @return
     * 导出查询检查验收检查项数据
     */
    List<Object[]> exportCategory();

    /**
     * @return
     * 导出旁站检查项数据
     */
    List<Object[]> exportSide();

    /**
     * @return
     * 导出材料验收数据
     */
    List<Object[]> exportInspected();

    /**
     * @return
     * 导出日常巡检检查项数据
     */
    List<Object[]> exportDaily();
    /**
     * @return
     * 导出样板点评数据
     */
    List<Object[]> exportModelReviewsy();
}
