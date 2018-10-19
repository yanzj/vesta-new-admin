package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.MemberRoleMenuEntity;
import com.maxrocky.vesta.domain.model.MemberRoleScopeEntity;
import com.maxrocky.vesta.domain.model.RoleRolesetEntity;
import com.maxrocky.vesta.domain.model.RoleViewmodelEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 会员权限管理_Dao
 * Created by WeiYangDong on 2016/8/4.
 */
public interface MemberAuthorityRepository {

    /**
     * 保存或更新Entity
     * @param entity
     * @param <T>
     */
    <T> void saveOrUpdate(T entity);

    /**
     * 通过角色Id物理删除角色信息
     * @param setId 角色Id
     */
    void deleteRoleById(String setId);

    /**
     * 通过角色Id删除角色操作数据范围信息
     * @param setId 角色Id
     */
    void deleteRoleScopeBySetId(String setId);

    /**
     * 通过角色Id删除角色菜单操作级别数据
     * @param setId 角色Id
     */
    void deleteRoleMenuBySetId(String setId);

    /**
     * 通过角色Id更新角色操作范围数据状态值
     * @param setId 角色Id
     */
    void updateRoleScopeStateBySetId(String setId);

    /**
     * 获取会员角色列表信息
     * @param params   检索条件
     * @param webPage  分页
     * @return List<RoleRolesetEntity>
     */
    List<Map<String,Object>> getMemberRolesetList(Map<String,Object> params,WebPage webPage);

    /**
     * 通过角色Id检索角色信息
     * @param setId 角色Id
     * @return
     */
    RoleRolesetEntity getRoleById(String setId);

    /**
     * 通过角色Id检索角色范围数据列表
     * @param setId 角色Id
     * @return  List<MemberRoleScopeEntity>
     */
    List<MemberRoleScopeEntity> getRoleScopeById(String setId);

    /**
     * 通过菜单Id检索菜单信息
     * @param menuId 菜单Id
     * @return RoleViewmodelEntity
     */
    RoleViewmodelEntity getViewmodelByMenuId(String menuId);

    /**
     * 获取会员一级菜单列表
     * @return  List<RoleViewmodelEntity>
     */
    List<RoleViewmodelEntity> getMenuLevelOneList();

    /**
     * 通过会员一级菜单Id获取二级菜单列表
     * @param menuId 菜单Id
     * @return  List<RoleViewmodelEntity>
     */
    List<RoleViewmodelEntity> getMenuLevelTwoListByMenuId(String menuId);

    /**
     * 通过项目Code_查询城市ID/名称
     */
    List<Object[]> queryCityByProjectCode(String projectCode);

    /**
     * 通过角色Id检索角色操作菜单列表
     * @param setId 角色Id
     * @return  List<MemberRoleMenuEntity>
     */
    List<MemberRoleMenuEntity> getRoleMenuBySetId(String setId);

    /**
     * 通过角色名称模糊查询角色信息列表
     * @param roledesc  角色名称
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getRolesByRoledesc(String roledesc);

    /**
     * 通过角色名称模糊查询角色范围信息列表
     * @param roledesc  角色名称
     * @return  List<Map<String,Object>>
     */
    List<Map<String,Object>> getRoleScopesByRoledesc(String roledesc);

    /**
     * 通过角色Id检索成员列表
     * @param params    参数
     * @param webPage   分页
     * @return
     */
    List<Map<String,Object>> getStaffUserListByRoleId(Map<String,Object> params,WebPage webPage);

    /**
     * 通过角色Id检索成员总数
     * @param params 参数
     * @return  Long
     */
    Long getStaffUserCountByRoleId(Map<String,Object> params);

    /**
     * 通过用户Id和角色Id删除用户角色关系信息
     * @param params 参数
     */
    int delMemberRole(Map<String,Object> params);

    /**
     * 通过用户Id与菜单Id检索角色菜单操作级别信息
     * @param params    条件参数
     * @return  List<Map<String,Object>>
     */
    List<Map<String,Object>> getRoleMenuByStaffAndMenu(Map<String,Object> params);

    /**
     * 通过角色Id与菜单Id检索角色菜单操作级别信息
     * @param params    条件参数
     * @return  List<Map<String,Object>>
     */
    MemberRoleMenuEntity getRoleMenuByRoleAndMenu(Map<String,Object> params);

    /**
     * 通过角色描述检索角色数量(用于校验角色名称重复)
     * @param roledesc 角色描述
     * @return int
     */
    int getRoleCountByRoledesc(String roledesc);
}
