package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.*;

import java.util.List;

/**
 * Created by chen on 2016/6/17.
 */
public interface RoleDataRepository {
    //新增数据权限关系
    void addRoleData(RoleDataEntity roleDataEntity);
    //有则更新，无则新增
    void addDumpRoleData(RoleDataEntity roleDataEntity);
    //删除数据权限关系
    void updateRoleData(RoleDataEntity roleDataEntity);
    //清除与常用组的所有关系
    void delOrganizeRoleData(RoleDataEntity roleDataEntity);
    //清除角色与常用组的关系
    void delOrganizeRoleSet(RoleDataEntity roleDataEntity);
    //删除角色下与机构的关系
    void delAgencyRole(RoleDataEntity roleDataEntity);
    //删除后台角色与机构的关系
    void delAdminAgencyRole(String setId);
    //删除后台角色与机构的关系
    void delAdminAgencyRole(String setId,String authId,String projectId);
    //删除后台角色与机构的关系
    void delAdminAgencyRole(String setId,String projectId);
    //删除项目下的某个权限关系
    void delProjectRole(RoleDataEntity roleDataEntity);
    //根据组织ID获取角色
    List<AppRolesetEntity> getDataByAuthority(String authorityId);
    //根据常用组ID获取角色
    List<RoleDataEntity> getDataByOrganzie(String organizeId);
    //根据角色ID获取常用组ID
    List<RoleDataEntity> getDataByRoleSet(String appRoleSetId);
    //根据角色ID获取组织列表
    List<AgencyEntity> getAgencyByRoleSet(String appRoleSetId);
    //根据角色ID获取员工列表
    List<UserPropertyStaffEntity> getStaffByRoleSet(String appRoleSetId);
    //根据用户ID获取项目列表
    List<HouseProjectEntity> getDataByStaffId(String staffId);
    //根据项目ID获取组织列表
    List<AgencyEntity> getRoleDataByProject(String projectId);
    //根据项目ID和项目下的权限获取组织列表
    List<AgencyEntity> getProjectRoles(String projectId,String permission);
    //根据项目ID获取员工列表
    List<UserPropertyStaffEntity> getStaffByProject(String projectId);
    //根据项目ID及项目下的权限ID获取相关人员
    List<UserPropertyStaffEntity> getProjectStaffRole(String projectId,String permission);
    //根据项目ID及项目下的权限ID获取相关群组
    List<OrganizeEntity> getProjectOrganizeRole(String projectId,String permission);
    //根据条件获取
    List<RoleDataEntity> getRoleDateOne(RoleDataEntity roleDataEntity);
    //获取项目权限下所有的关系数据
    List<RoleDataEntity> getProjectRoleData(String projectId,String authorityType,String permission);
    //根据用户ID获取角色列表
    List<AppRolesetEntity> getRoleSetFromData(String staffId);
    //根据组ID获取项目列表
    List<HouseProjectEntity> getProjectByOrganizeId(String organizeId);
    //根据组织机构ID获取关联项目列表
    List<HouseProjectEntity> getProjectByAgencyId(String agencyId);
    //根据员工ID获取项目及项目下的权限组
    List<String> getProjectRole(String staffId);
    //根据员工ID项目编码判断此人是否有对应权限
    boolean havaDispatch(String staffId,String projectCode,String permission);
    //获取人员权限视图数据
    List<Object[]> getViewsList(String timeStamp,Integer num);
    //根据员工ID获取项目及项目下的权限组
    List<String> getProjectRoleByNum(String staffId,String projectNum);

    /**
     * 获取打印流水号
     * */
    String getPrintSeq(String num);
    List<RoleDataEntity> searchProjectRoleData(String projectId,String authorityType,String dataId);
    //删除角色下与机构的关系
    boolean deleteAgencyRole(RoleDataEntity roleDataEntity);
    boolean deleteAgencyRole(String projectId,String authorityType,String dataId);

    List<Object[]> getComplintByProjectId(String projectId);
}

