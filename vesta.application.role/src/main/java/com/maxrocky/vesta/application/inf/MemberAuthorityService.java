package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.dto.adminDTO.RoleRolesetDTO;
import com.maxrocky.vesta.application.dto.adminDTO.RoleStaffUserDTO;
import com.maxrocky.vesta.domain.model.RoleRolesetEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 会员权限管理_Service
 * Created by WeiYangDong on 2016/8/4.
 */
public interface MemberAuthorityService {

    /**
     * 获取会员角色列表信息
     */
    List<Map<String,Object>> getMemberRolesetList(RoleRolesetDTO roleRolesetDTO,WebPage webPage);

    /**
     * 保存或更新角色信息
     */
    RoleRolesetEntity saveOrUpdateRole(RoleRolesetDTO roleRolesetDTO);

    /**
     * 保存或更新角色操作数据范围信息
     */
    void saveOrUpdateRoleScope(RoleRolesetDTO roleRolesetDTO);

    /**
     * 通过角色Id删除角色信息
     */
    void deleteRoleById(RoleRolesetDTO roleRolesetDTO);

    /**
     * 保存会员角色菜单操作权限
     */
    void saveOrUpdateRoleMenu(RoleRolesetDTO roleRolesetDTO);

    /**
     * 获取会员菜单列表
     * @return  null
     */
    List<Object> getMemberMenuList(String setId);

    /**
     * 通过角色Id获取角色详情
     * @param setId 角色Id
     * @return  RoleRolesetEntity
     */
    RoleRolesetDTO getRoleById(String setId);

    /**
     * 通过角色名称模糊查询角色信息列表
     */
    List<Map<String,Object>> getRolesByRoledesc(RoleRolesetDTO roleRolesetDTO);

    /**
     * 通过角色Id检索成员列表
     */
    List<Map<String,Object>> getStaffUserListByRoleId(RoleStaffUserDTO userPropertystaffDTO,WebPage webPage);

    /**
     * 通过角色Id检索成员总数
     */
    Long getStaffUserCountByRoleId(RoleStaffUserDTO userPropertystaffDTO);

    /**
     * 通过用户Id和角色Id删除用户角色关系信息
     */
    int delMemberRole(RoleStaffUserDTO userPropertystaffDTO);

    /**
     * 角色成员管理-批量保存角色成员
     */
    void saveRoleMember(RoleStaffUserDTO userPropertystaffDTO);

    /**
     * 通过角色Id检索数据范围信息
     * @param rolesetId 角色Id
     * @return  List<Map<String,Object>>
     */
    List<Map<String,Object>> getRoleScopeById(String rolesetId);

    /**
     * 校验用户菜单操作级别(取最高级别)
     * @param staffId   用户Id
     * @param menuId    菜单Id
     * @return  int
     */
    int checkStaffMenuOperationLevel(String staffId,String menuId);

    /**
     * 校验角色名称是否重复
     * @param roledesc  角色描述
     * @return int
     */
    int checkRoledesc(String roledesc);

    /**
     * 导出excel
     * @param
     * @return
     */
    void exportExcel(List<Map<String, Object>> rolesetList, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException;
}
