package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 同步crm组织机构
 * Created by Magic on 2017/12/6.
 */
public interface AuthAgencyRepository {
    //根据id查询crm同步组织机构信息 安全
    AuthAgencyEntity getAuthAgencyByID(String id);

    //根据id查询crm同步组织机构信息 工程
    AuthAgencyESEntity getESAuthAgencyByID(String id);

    //根据id查询crm同步组织机构信息 客观
    AuthAgencyQCEntity getQCAuthAgencyByID(String id);


    //获取所有crm同步组织机构信息
    List<AuthAgencyEntity> getAllAuthAgencyList();
    //新增或修改crm同步組織机构
    void addAuthAgency(AuthAgencyEntity authAgencyEntity);
    //获取所有机构列表 安全
    List<AuthAgencyEntity> getAllAgencyList();

    //获取所有机构列表 工程
    List<AuthAgencyESEntity> getESAllAgencyList();
    //根据等级获取机构列表
    List<AuthAgencyESEntity> getESAllAgencyListByLevel(String level);

    //获取所有机构列表 客观
    List<AuthAgencyQCEntity> getQCAllAgencyList();

    //根据查询的idList获取所有机构列表
    List<AuthAgencyEntity> getAllAgencyListByAgencyId(String agencyId);

    //根据查询的idList获取所有机构列表
    List<AuthAgencyEntity> getAllAgencyListByAgencyIdList(List<String> idList);

    //根据查询的idList查询路径
    List<String> getAgencyPathListByAgencyId(List<String> idList);

    //获取所有当前机构下机构列表 安全
    List<AuthAgencyEntity> getAllAgencyListById(String id,String agencyName);

    //获取所有当前机构下机构列表 工程
    List<AuthAgencyESEntity> getAllESAgencyListById(String id,String agencyName);

    //获取所有当前机构下机构列表 客观
    List<AuthAgencyQCEntity> getAllQCAgencyListById(String id,String agencyName);

    //获取所有当前机构信息 安全
    AuthAgencyEntity getAgencyListById(String id);

    //获取所有当前机构信息 工程
    AuthAgencyESEntity getEStAgencyListById(String id);

    //获取所有当前机构信息 客观
    AuthAgencyQCEntity getQCtAgencyListById(String id);

    //获取第一级别机构列表
    List<AuthAgencyEntity> getAllAgency();

    //获取第一级别机构列表Age 安全
    List<AuthAgencyEntity> getAgencyByAgencyId(String id);

    //获取第一级别机构列表Age 工程
    List<AuthAgencyESEntity> getESAgencyByAgencyId(String id);

    //获取第一级别机构列表Age 客关
    List<AuthAgencyQCEntity> getQCAgencyByAgencyId(String id);

    //根据条件获取角色信息
    List<Object[]> getAuthRoleseList(Map map, WebPage webPage);

    //根据条件获取角色关联的项目 安全
    List<String> getAuthRoleseProject(String id);

    //根据条件获取角色关联的项目  工程
    List<String> getESAuthRoleseProject(String id);

    //根据条件获取角色关联的项目  客关
    List<String> getQCAuthRoleseProject(String id);

    //根据条件获取角色关联的项目id
    List<String> getAuthRoleseProjectID(String id);

    //删除角色关联的项目
    Boolean delAuthRoleseProject(String id);

    //删除角色关联的项目
    Boolean delAuthRoleseProjectMap(String id,String time);

    //删除角色关联的项目人员  不存在的均为删除状态  客关
    Boolean delAuthRoleseProjectQCMapList(List<String> roleAgnecy,String time,String roleId);

    //删除角色关联的项目人员  不存在的均为删除状态  工程
    Boolean delAuthRoleseProjectESMapList(List<String> roleAgnecy,String time,String roleId);

    //删除角色关联的项目人员  不存在的均为删除状态  安全
    Boolean delAuthRoleseProjectMapList(List<String> roleAgnecy,String time,String roleId);

    //删除角色关联的项目人员   安全
    Boolean delAuthRoleseProjectMapByid(String roleId,String agencyId,String time);

    //删除角色关联的项目人员 工程
    Boolean delESAuthRoleseProjectMapByid(String roleId,String agencyId,String time);

    //删除角色关联的项目人员 客关
    Boolean delQCAuthRoleseProjectMapByid(String roleId,String agencyId,String time);


    //启用角色关联的项目人员
    Boolean upAuthRoleseProjectMap(String id,String time,String agencyId);

    //新增角色信息
    void saveAuthRolese(AuthRoleseEntity authRoleseEntity);

    //修改角色信息
    void upAuthRolese(AuthRoleseEntity authRoleseEntity);

    //按照类型查询角色信息
    List<AuthRoleseEntity> getAuthRoleseEntity(String category);

    //按照类型查询角色信息
    List<AuthRoleseEntity> getAuthRoleseEntityAll();

    //新增角色 项目关系信息
    void saveAuthRoleseProject(AuthRoleProjectEntity authRoleProjectEntity);

    //按id获取角色信息
    AuthRoleseEntity getAuthRoleseEntityByid(String id);

    //获取当前属性的功能点
    List<AuthFunctionPointEntity> getAuthFunctionPointEntityList(String category,String classification,String level);

    //获取当前父节点的功能点
    List<AuthFunctionPointEntity> getAuthFunctionPointEntityListById(String parentId);

    //获取当前角色关联的功能点
    List<AuthFunctionPointRoleEntity> getAuthFunctionPointRoleEntityList(String category,String classification,String authRoleId);

    //修改角色功能点关联关系
    void upFunctionPointRole(String id,String category,String classification,String userid,String updaTime);

    //删除不属于当前 角色id+功能点id+类别+分类 的组合
    void upConcatFunctionPointRoleBy(String id,String category,String classification,String userid,String updaTime,List<String> deleteList);

    //新增角色功能点关联关系
    void saveAuthFunctionPointRole(AuthFunctionPointRoleEntity authFunctionPointRoleEntity);

    //根据组织机构id查询该机构下角色关联关系
    List<Object[]> getAuthAgencyRoleById(String authAgencyid,String category,WebPage webPage,String roleNature);

    //根据组织机构id查询该机构下角色关联关系 工程
    List<Object[]> getESAuthAgencyRoleById(String authAgencyid,String category,WebPage webPage,String roleNature);

    //根据组织机构id查询该机构下角色关联关系 客观
    List<Object[]> getQCAuthAgencyRoleById(String authAgencyid,String category,WebPage webPage,String roleNature);

    //根据组织机构id + 角色id 查询该机构下角色人员关联关系
    List<Object[]> getAuthAgencyRoleUserById(String authAgencyid,String roleId);

    //根据条件获取角色关联的人员 安全
    List<Object[]> getusetNameByRoleIdAuthAgencyId(String roleId,String authAgnecyId,String type);

    //根据条件获取角色关联的人员 工程
    List<Object[]> getusetNameByESRoleIdAuthAgencyId(String roleId,String authAgnecyId,String type);

    //根据条件获取角色关联的人员 客观
    List<Object[]> getusetNameByQCRoleIdAuthAgencyId(String roleId,String authAgnecyId,String type);

    //按员工id查询所有角色信息id
    List<String> getRoleIdlistById(String staffId);

    //按员工id查询所有角色信息id
    List<String> getESRoleIdlistById(String staffId);

    //按员工id查询所有角色信息id 客观
    List<String> getQCRoleIdlistById(String staffId);

    //按角色IDlsit和级别查询view
    List<String> getRoleViewIdlistById(List<String> roleIdList,String leve,String category);


    //根据用户id+项目层级id查询是够有权限查询数据
    boolean checkAuthByUseridAndAgencyId(String authAgencyid,String staffId);


    //按员工id+功能点级别查询所有功能点id 安全
    List<String> getAuthFunctionByStaffId(String staffId,String level,String category);


    //按员工id+功能点级别查询所有功能点id 工程
    List<String> getProjectAuthFunctionByStaffId(String staffId,String level,String category);

    //按员工id+功能点级别查询所有功能点id 客观
    List<String> getQCProjectAuthFunctionByStaffId(String staffId,String level,String category);

    //按员工id+功能点级别查询所有功能点id 和对应的项目id
    List<String> getAuthFunctionAndProjectIdByStaffId(String function,String staffId, String level);

    //根据当前登录人获取其在客关下有新闻权限的区域或集团
    List<String> getNewsFunctionByStaffId(String function, String staffId);

    //根据当前登录人获取其在客关下有新闻权限的区域或集团名称和id
    List<Object[]> getNewsProjectNameAndIdByStaffId(String function, String staffId);

    //根据用户id + 项目层级级别 查询是否有关联的角色信息 安全
    boolean checkAuthRoleByUserIdandtype(String type,String staffId);

    //根据用户id + 项目层级级别 查询是否有关联的角色信息 工程
    boolean checkESAuthRoleByUserIdandtype(String type,String staffId);

    //根据用户id + 项目层级级别 查询是否有关联的角色信息 客观
    boolean checkQCAuthRoleByUserIdandtype(String type,String staffId);

    //根据用户id + 项目层级级别 查询是否有关联的角色信息 安全
    List<String> checkAuthRoleListByUserIdandtype(String type,String staffId);

    //根据用户id + 项目层级级别 查询是否有关联的角色信息 工程
    List<String> checkESAuthRoleListByUserIdandtype(String type,String staffId);

    //根据用户id + 项目层级级别 查询是否有关联的角色信息 客关
    List<String> checkQCAuthRoleListByUserIdandtype(String type,String staffId);

    //根据用户id + 项目层级级别 查询是否有关联的角色信息 安全
    boolean checkAuthRoleByUserIdAndAgencyId(String agencyId,String staffId);

    //根据用户id + 项目层级级别 查询是否有关联的角色信息 工程
    boolean checkESAuthRoleByUserIdAndAgencyId(String agencyId,String staffId);

    //根据用户id + 项目层级级别 查询是否有关联的角色信息 客观
    boolean checkQCAuthRoleByUserIdAndAgencyId(String agencyId,String staffId);

    //根据上一级别id 查询绑定的角色信息 和级别
    List<String> getRoleIdlistByAgencyId(String agencyId, String level);



    //根据查询该角色下有没有人员绑定
    boolean checkDeleteRole(String roleId);

    //根据区域Id获取城市信息
    List<AuthAgencyEntity> getCityListByAreaId(String areaId);

    //根据区域Id获取城市信息
    List<AuthAgencyESEntity> getESCityListByAreaId(String areaId);

    //根据区域Id获取城市信息 客观
    List<AuthAgencyQCEntity> getQCCityListByAreaId(String areaId);

    //根据条件删选 parentIdList 上级id  agencyIdList当前级别id  type类型 安全
    List<AuthAgencyEntity> getAllAgencyListByParentId(List<String> parentIdList,List<String> agencyIdList,String type);

    //根据条件删选 parentIdList 上级id  agencyIdList当前级别id  type类型 工程
    List<AuthAgencyESEntity> getESAllAgencyListByParentId(List<String> parentIdList,List<String> agencyIdList,String type);

    //根据条件删选 parentIdList 上级id  agencyIdList当前级别id  type类型 客关
    List<AuthAgencyQCEntity> getQCAllAgencyListByParentId(List<String> parentIdList,List<String> agencyIdList,String type);


    //保存项目信息 安全
    void saveAuthAgency(AuthAgencyEntity authAgencyEntity);

    //保存项目信息 工程
    void saveESAuthAgency(AuthAgencyESEntity authAgencyESEntity);


    //根据项目编码查询分类人员信息
    List<Object[]> getAuthClassifyStaff(String projectNum,String classifyId,WebPage webPage);

}
