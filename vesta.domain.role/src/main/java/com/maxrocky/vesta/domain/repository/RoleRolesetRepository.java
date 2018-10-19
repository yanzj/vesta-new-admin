package com.maxrocky.vesta.domain.repository;





//import com.maxrocky.vesta.domain.RoleRolesetEntity;

import com.maxrocky.vesta.domain.model.RoleRolesetEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by zhanghj on 2016/1/17.
 */
public interface RoleRolesetRepository {

    /**
     * 添加新角色
     * @param roleRolesetEntity
     * @return
     */
        public boolean saveRoleSet(RoleRolesetEntity roleRolesetEntity);

    /**
     * 通过角色Id获得角色
     * @param rolesetId
     * @return
     */
        public RoleRolesetEntity getRolesetById(String rolesetId);
    /**
     * 通过角色Id获得角色
     * @param rolesetName
     * @return
     */
    public RoleRolesetEntity getRolesetByName(String rolesetName);
    /**
     * 获得所有角色列表
     * @return
     */
    public List<RoleRolesetEntity> listRoleset(WebPage webPage,String setId,String roleName);


    /**
     * 更新角色
     * @param roleRolesetEntity
     * @return
     */
    public boolean updateRoleset(RoleRolesetEntity roleRolesetEntity);


    /**
     * 删除角色
     * @param roleRolesetEntity
     * @return
     */
    public boolean deleteRoleset(RoleRolesetEntity roleRolesetEntity);

    /**
     * 根据条件获取角色外的用户列表
     * */
    List<UserPropertyStaffEntity> getOutStaffs(UserPropertyStaffEntity userPropertyStaffEntity);
    /**
     * 根据角色ID获取角色内用户列表
     * */
    List<UserPropertyStaffEntity> getInStaffs(String roleSetId);
}
