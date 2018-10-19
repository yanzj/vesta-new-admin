package com.maxrocky.vesta.application.inf;



import com.maxrocky.vesta.application.dto.adminDTO.RoleRoleDTO;
import com.maxrocky.vesta.application.dto.adminDTO.RoleRolesetDTO;
import com.maxrocky.vesta.application.dto.adminDTO.StaffNameDTO;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by zhanghj on 2016/1/17.
 */

public interface RoleRolesetService {

    /**
     * 新增角色
     * @param roleRolesetDTO
     * @return
     */
    public boolean saveRoleSet(RoleRolesetDTO roleRolesetDTO);

    /**
     * 根据角色Id查找角色
     * @param rolesetId
     * @return
     */
    public RoleRolesetDTO getRolesetById(String rolesetId);


    /**
     * 查询角色列表
     * @return
     */
    public List<RoleRolesetDTO> listRoleset(WebPage webPage,String setId,String roleName);

    /**
     * 根据条件查询角色列表
     * */
    public List<RoleRolesetDTO> getRoleSets(String roleSetName,WebPage webPage);

    /**
     * 查询可分配角色列表
     * @return
     */
    public List<RoleRolesetDTO> listRoleset(String setId);


    /**
     * 更新角色信息
     * @param roleRolesetDTO
     * @return
     */
    public boolean updateRoleset(RoleRolesetDTO roleRolesetDTO);


    /**
     * 删除角色信息
     * @param roleRolesetDTO
     * @return
     */
    public boolean deleteRoleset(RoleRolesetDTO roleRolesetDTO);


    /**
     * 权限管理页面列表
     * @param setId
     *
     * @return
     */
    public List<RoleRoleDTO> listRoleSetMap(String setId);

    /**
     * 更新角色权限
     * @param roleSetId
     * @param RoleId
     * @return
     */
    public int updateRoleMap(String roleSetId,String RoleId);

    void saveRoleMap(String roleSetId,String staffId);
    void delRoleMap(String roleSetId,String staffId);

    /**
     * 获取角色外的用户列表
     * */
    List<StaffNameDTO> getOutStaffs(StaffNameDTO staffNameDTO);
    /**
     * 获取角色内的用户列表
     * */
    List<StaffNameDTO> getInStaffs(String roleSetId);
}
