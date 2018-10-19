package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2017/12/21.
 */
public interface UserAccreditRepository {

    //获取所有组织架构
    List<AuthAgencyEntity> getAgencyListAll();

    //获取所有组织架构
    List<AuthAgencyEntity> getAgencyListAll(List<String> updateProject);

    //条件检索人员授权信息
    List<Object[]> getAccreditManageListByCondition(Map map,WebPage webPage);

    //获取全部组织架构
    List<AuthAgencyEntity> getAllAgencyMessage();

    //获取外部创建的组织架构
    List<AuthOuterAgencyEntity> getAuthOuterAgency(String category);

    //OA组织机构
    List<AgencyEntity> getOAAgencyMessage();

    //通过Id获取该组织机构下的人员
    List<Object[]> getUserByAgencyId(String agencyId);

    //通过Id获取外部合作单位下的人员
    List<Object[]> getOuterUserByAgencyId(String agencyId,String category);


    //通过Id获取项目层级下的角色
    List<AuthRoleseEntity> getRoleByAgencyId(String agencyId);

    //通过Id获取该项目层级信息
    AuthAgencyEntity getAuthAgencyByAgencyId(String agencyId);

    //模糊搜索人员
    List<Object[]> getUserByName(String staffName,String category);

    //模糊搜索角色
    List<Object[]> getRoleByName(String roleName);
    List<Object[]> getRoleByName(String roleName,List<String> updateProject);

    //保存角色 项目 人员关系   安全
    void saveOrUpdateRoleStaff(RoleStaffProjectMapEntity roleStaffProjectMapEntity);


    //保存角色 项目 人员关系   工程
    void saveESOrUpdateRoleStaff(RoleStaffProjectMapESEntity roleStaffProjectMapEntity);

    //保存角色 项目 人员关系   客关
    void saveQCOrUpdateRoleStaff(RoleStaffProjectMapQCEntity roleStaffProjectMapEntity);

    //根据ID获取角色  项目  人员关系
    RoleStaffProjectMapEntity getRoleStaffProjectMapById(String id);

    //修改人员项目关系
    void saveRoleStaffProjectMap(RoleStaffProjectMapEntity roleStaffProjectMapEntity);


    //删除项目人员角色关系
    boolean delQCOrUpdateRoleStaff(List<String> roleList,String userId,String agencyId);
}
