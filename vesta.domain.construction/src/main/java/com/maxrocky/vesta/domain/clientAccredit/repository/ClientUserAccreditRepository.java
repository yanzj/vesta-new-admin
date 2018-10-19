package com.maxrocky.vesta.domain.clientAccredit.repository;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2017/12/21.
 */
public interface ClientUserAccreditRepository {

    //获取所有组织架构
    List<AuthAgencyQCEntity> getClientAgencyListAll();

    //获取所有组织架构
    List<AuthAgencyQCEntity> getClientAgencyListAllByIds(List<String> updateProject);

    //条件检索人员授权信息
    List<Object[]> getAccreditManageListByCondition(Map map, WebPage webPage);

    //获取全部组织架构
    List<AuthAgencyEntity> getAllAgencyMessage();

    //获取外部创建的组织架构
    List<AuthOuterAgencyEntity> getClientAuthOuterAgency();

    //获取外部创建的组织架构甲方
    List<AuthOuterAgencyEntity> getOwnerAuthOuterAgency();

    //通过Id获取该组织机构下的人员
    List<Object[]> getUserByAgencyId(String agencyId);

    //通过Id获取外部合作单位下的人员
    List<Object[]> getOuterUserByAgencyId(String agencyId, String category);


    //通过Id获取项目层级下的角色
    List<AuthRoleseEntity> getClientRoleByAgencyId(String agencyId);

    //通过Id获取项目层级下的甲方角色
    List<AuthRoleseEntity> getOwnerRoleByAgencyId(String agencyId);

    //通过Id获取该项目层级信息
    AuthAgencyQCEntity getAuthAgencyByAgencyId(String agencyId);

    //模糊搜索人员
    List<Object[]> getUserByName(String staffName, String category);

    //模糊搜索甲方人员
    List<Object[]> getOwnerUserByName(String staffName);

    //模糊搜索角色
    List<Object[]> getRoleByName(String roleName);
    List<Object[]> getRoleByName(String roleName, List<String> updateProject);

    //模糊搜索甲方角色
    List<Object[]> getOwnerRolerByName(String roleName, List<String> updateProject);

    //保存角色 项目 人员关系
    void saveOrUpdateProjectRoleStaff(RoleStaffProjectMapQCEntity roleStaffProjectMapQCEntity);

    //根据ID获取角色  项目  人员关系
    RoleStaffProjectMapQCEntity getRoleStaffProjectMapById(String id);

    //修改人员项目关系
    void saveClientRoleStaffProjectMap(RoleStaffProjectMapQCEntity roleStaffProjectMapQCEntity);

    //按员工id+功能点级别查询所有功能点id 和对应的项目id
    List<String> getClientAuthFunctionAndProjectIdByStaffId(String function, String staffId, String level);

}
