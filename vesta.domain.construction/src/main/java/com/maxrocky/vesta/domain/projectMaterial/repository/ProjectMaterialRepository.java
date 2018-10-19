package com.maxrocky.vesta.domain.projectMaterial.repository;

import com.maxrocky.vesta.domain.projectMaterial.model.ProjectMaterialDetailsEntity;
import com.maxrocky.vesta.domain.projectMaterial.model.ProjectMaterialEntity;
import com.maxrocky.vesta.domain.projectMaterial.model.ProjectMaterialOutEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;


/**
 * Created by Magic on 2016/11/24.
 */
public interface ProjectMaterialRepository {
    /**
     * 保存材料验收主表
     * */
    void saveProjectMaterial(ProjectMaterialEntity projectMaterialEntity);
    /**
     * 修改材料验收主表
     * */
    void updateProjectMaterial(ProjectMaterialEntity projectMaterialEntity);

    /**
     * 保存或修改材料验收指标信息
     * */
    void saveUpdateProjectMaterialDetails(ProjectMaterialDetailsEntity projectMaterialDetailsEntity);

    /**
     * 保存退场纪录实体表
     * */
    void saveUpdateProjectMaterialOut(ProjectMaterialOutEntity projectMaterialOutEntity);

    /**
     * 修改退场纪录实体表
     * */
    void updateProjectMaterialOut(ProjectMaterialOutEntity projectMaterialOutEntity);
    /**
     *根据当前登录人id查询处理中的材料验收数据
     * */
    int coutMaterial(String userId);
    /**
     * 根据id查询材料验收
     * */
    ProjectMaterialEntity getMaterialById(String id);

    /**
     * 根据id查询材料验收
     * */
    ProjectMaterialEntity getMaterialByIdandAppId(String id,String appId);

    /**
     * 根据Appid查询材料验收
     * */
    ProjectMaterialEntity getMaterialByAppId(String appId);

    /**
     * 根据id查询材料验收指标
     * */
    ProjectMaterialDetailsEntity getProjectMaterialDetails(String id);

    /**
     * 根据材料验收id查询所有指标信息
     * */
    List<ProjectMaterialDetailsEntity> getMaterialDetailsList(String id);

    /**
     * 根据id查询退场纪录
     * **/
    ProjectMaterialOutEntity getMaterialOut(String id);


    /**
     * 根据时间、ID、projectId、type(权限)、员工ID 增量查询材料验收数据
     * */
    List<ProjectMaterialEntity> getMaterialEntityList(String id,String time,String projectId,String type,String userId);
    /**
     * 根据时间、ID、projectId、type(权限)、员工ID 判断是否有数据更新
     * */
    boolean checkoutMaterial(String id,String time,String projectId,String type,String userId);

    /**
     * 按项目查询统计信息
     */
    List<Object[]> searchMaterial(String projectId);

    /**
     * 后台查询列表
     * */
    List<ProjectMaterialEntity> getMaterialAdmin(Map map, WebPage webPage);
}
