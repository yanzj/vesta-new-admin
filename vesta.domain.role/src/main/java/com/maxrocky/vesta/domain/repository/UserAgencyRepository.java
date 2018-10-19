package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by chen on 2016/6/18.
 */
public interface UserAgencyRepository {
    //新增用户机构关系
    void addUserAgency(UserAgencymapEntity userAgencymapEntity);
    //有则修改用户机构关系 无则新增用户机构关系
    void addDumpUserAgency(UserAgencymapEntity userAgencymapEntity);
    //OA新增或修改关系表
    void addOAUserAgency(UserAgencymapEntity userAgencymapEntity);
    //改OA新增或修改关系表
    void addUserAgency(UserAgencyEntity userAgencyEntity);
    //更新用户机构关系
    void updateUserAgency(String agencyId);
    //删除用户机构关系
    void delUserAgency(UserAgencymapEntity userAgencymapEntity);
    //根据人员ID删除人员机构关系
    void deleteUserAgency(String staffId);
    //根据人员ID和机构ID查询关系
    List<UserAgencymapEntity> getUserAgencymap(String staffId);
    //根据机构ID获取用户列表
    List<UserPropertyStaffEntity> getStaffByAgencyId(String agencyId);
    //根据机构ID集合获取用户列表
    List<UserInformationEntity> getStaffByAgencyId(List<String> agencyIds);
    //根据机构ID集合获取关联的人员Id
    List<UserAgencyEntity> getStaffIdByAgencyId(List<String> agencyIds);
    //根据机构ID获取用户列表 带分页
    List<UserPropertyStaffEntity> getStaffListByAgencyId(String agencyId,WebPage webPage);
    //根据员工ID获取角色列表
    List<AppRolesetEntity> getRoleSetFromAgency(String staffId);
    //根据条件获取机构外用户列表
    List<UserPropertyStaffEntity> getAgencyOutStaff(UserPropertyStaffEntity userPropertyStaffEntity);
    //根据条件查询用户列表
    List<UserPropertyStaffEntity> getStaffForNames(UserPropertyStaffEntity userPropertyStaffEntity,WebPage webPage);
    //根据员工ID获取所属机构列表
    List<AgencyEntity> getAgencysByStaffId(String staffId);
    //根据员工ID获取所属机构列表
    AgencyEntity getAgencysByStaffId(String staffId,String agencyId);
    //删除OA的关系数据
    void delOAUserAgency(String staffId);
    //根据人员ID和机构ID删除人员机构关系
    void deleteUserAgency(String staffId, String agencyId);
    //根据部门ID获取员工列表
    List<Object[]> getStaffInfoByAgencyId(String AgencyId);
    //获取OA同步已启用的全部用户
    List<UserMapEntity> getUserMap();
    //条件检索OA用户
    List<Object[]> getOAUserManageListByCondition(Map map,WebPage webPage,List<String> idList);
    //保存组织架构关系
    void addOuterUserAgency(UserAgencyEntity userAgencyEntity);
    //保存启用状态
    void saveOrUpdateUserAgencyEntity(UserMapEntity userMapEntity);
    //保存三者关系
    void saveOrUpdateUserMap(RoleStaffProjectMapEntity roleStaffProjectMapEntity);
    //获取OA同步的组织机构
    List<AgencyEntity> getAgencysByOA();
    //获取OA同步的组织机构
    List<AgencyEntity> getAgencysByOuter();
    //保存组织机构
    void saveOrUpdateAgency(AuthOuterAgencyEntity authOuterAgencyEntity);
    //获取OA同步的人员
    List<UserPropertyStaffEntity> getStaff();
    //获取OA同步的人员
    List<UserPropertyStaffEntity> getStaffById(List<String> staffIds);
    //保存人员信息
    void saveOrUpdateUser(UserInformationEntity userInformationEntity);
    //修改人员信息
    void saveOrUpdateUser_2(UserInformationEntity userInformationEntity);
    //查询人员与项目关联关系
    List<UserAgencymapEntity> getUserAgency();
    //查询人员与项目关联关系
    List<UserAgencymapEntity> getUserAgencyById(List<String> idList);
    //保存项目与人员关系
    void saveOrUpdateUserAgency(UserAgencyEntity userAgencyEntity);
    //修改项目与人员关系
    void updateUserAgencyEntity(UserAgencyEntity userAgencyEntity);
    //获取OA同步人员信息
    List<StaffCRMEntity> getStaffByOA();
    //获取OA同步分部信息
    List<SubcompanyCRMEntity> getSubByOA();
    //获取OA同步部门信息
    List<DepartmentCRMEntity> getDepByOA();
    //根据级别查询组织机构
    List<AuthOuterAgencyEntity> getAgencyByLevel(Integer level);
    //获取没有路径和级别的组织架构
    List<AuthOuterAgencyEntity> getAgency();

    //删除用户机构关系 新权限
    void delAuthUserAgency(UserAgencyEntity userAgencyEntity);


   void delUserMapEntity(String staffId);

}
