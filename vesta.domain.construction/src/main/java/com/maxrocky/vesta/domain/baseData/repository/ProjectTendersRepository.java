package com.maxrocky.vesta.domain.baseData.repository;

import com.maxrocky.vesta.domain.baseData.model.ProjectCategoryEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectTendersEntity;
import com.maxrocky.vesta.domain.baseData.model.TendersBuildingEntity;
import com.maxrocky.vesta.domain.baseData.model.TendersCategoryEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by chen on 2016/10/24.
 */
public interface ProjectTendersRepository {
    /**
     * @author chenning
     * @param projectTendersEntity
     * 新增标段
     */
    void addProjectTenders(ProjectTendersEntity projectTendersEntity);

    /**
     * @param projectTendersEntity
     * 更新标段
     */
    void updateProjectTenders(ProjectTendersEntity projectTendersEntity);

    /**
     * @param tenderId
     * @return
     * 获取标段详情
     */
    ProjectTendersEntity getTendersDetail(String tenderId);

    /**
     * @param projectId
     * @param webPage
     * @return
     * 根据项目ID获取标段列表
     */
    List<ProjectTendersEntity> getTendersByProjectId(String projectId,WebPage webPage);
    /**
     * @param projectId
     * @return
     * 根据项目ID获取标段列表
     */
    List<ProjectTendersEntity> getTendersByProjectId(String projectId);
    /**
     * @param tendersBuildingEntity
     * 保存标段楼栋关系
     */
    void addTendersBuild(TendersBuildingEntity tendersBuildingEntity);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Param:
    *  @Description: 更新标段楼栋关系
    */
    void updateTendersBuild(TendersBuildingEntity tendersBuildingEntity);

    /**
     * @param tendersCategoryEntity
     * 保存标段检查项关系
     */
    void addTendersCategory(TendersCategoryEntity tendersCategoryEntity);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Param:
    *  @Description: 更新标段检查项关系
    */
    void updateTendersCategory(TendersCategoryEntity tendersCategoryEntity);

    /**
     * @param autoId
     * @param timeStamp
     * @param projectId
     * @return
     * 同步楼栋与标段关系数据
     */
    List<TendersBuildingEntity> getTendersBuildForTime(String projectId,long autoId, String timeStamp);

    /**
     * @param autoId
     * @param timeStamp
     * @param projectId
     * @return
     * 同步标段与三级分类关系数据
     */
    List<TendersCategoryEntity> getTendersCategoryForTime(String projectId,long autoId, String timeStamp);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description:
    */
    List<ProjectCategoryEntity> getCategoryByParentId(String parentId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description:
    */
    List<ProjectCategoryEntity> getCategoryByModelId(String modelId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Param:
    *  @Description: 根据标段Id获取该标段对应的所有楼栋
    */
    List<TendersBuildingEntity> getTendersBuildingByTenderId(String tenderId);
    List<String> getTendersBuilds(String tenderId);

    /**
     * @param projectId
     * @return
     * 根据项目ID获取当前项目下所有标段对应的楼栋ID
     */
    List<String> getTendBuildsByProjectId(String projectId,String tendId);
    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Param:
    *  @Description: 根据标段Id获取该标段对应的所有检查项
    */
    List<TendersCategoryEntity> getTendersCategoryByTenderId(String tenderId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Param:
    *  @Description: 根据标段id和楼栋id，查看标段楼栋关系是否存在
    */
    TendersBuildingEntity getTenderBuildById(String tenderId, String buildId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Param:
    *  @Description: 根据标段id和检查项id，查看标段检查项关系是否存在
    */
    TendersCategoryEntity getTenderCategoryById(String tenderId, String categoryId);

    /**
     * @param tendersBuildingEntity
     * 根据标段ID和楼栋ID删除关系
     */
    void deleteTendBuild(TendersBuildingEntity tendersBuildingEntity);

    /**
     * @param tendersBuildingEntity
     * 有则修改 无则新增
     */
    void dumpAddTendBuild(TendersBuildingEntity tendersBuildingEntity);
}
