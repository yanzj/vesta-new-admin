package com.maxrocky.vesta.domain.projectSyncForApp.repository;

import com.maxrocky.vesta.domain.StatisticsAndWeekly.model.StatisticsWeeklyEntity;
import com.maxrocky.vesta.domain.model.AuthAgencyESEntity;
import com.maxrocky.vesta.domain.model.RoleStaffProjectMapESAppEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by yuanyn on 2018/4/24.
 * app项目同步Repository接口
 */
public interface ProjectSyncForRepository {

    //根据项目id查询该项目下拥有角色的人员
    List<Object[]> getUserIdByProjectId(String agencyId);

    //根据人员id 查询有app端角色的项目
    List<String> getProjectNameByUserId(String userId);

    //根据人员id 查询有app端角色的项目Id
    List<String> getProjectIdByUserId(String userId);

    //根据角色id 查询有app端角色的项目Id
    List<String> getProjectIdByRoleId(String roleId, String userId);

    //根据人员id 查询在app端设置了角色的项目
    List<String> getProjectAppNameByUserId(String userId);

    //根据人员id查询角色
    List<Object[]> getRoleByUserId(String userId);

    //根据人员id查询App端角色
    List<Object[]> getRoleIdByUserId(String userId);

    //根据角色Id查询APP端三者关系
    List<RoleStaffProjectMapESAppEntity> getRoleStaffProjectMapEntity(String roleId, String userId);

    //删除三者关系
    void deleteRoleStaffProjectMapESAppEntity( String staffId);

    //保存三者关系
    void saveRoleStaffProjectMapESAppEntity(RoleStaffProjectMapESAppEntity roleStaffProjectMapESAppEntity);
}

