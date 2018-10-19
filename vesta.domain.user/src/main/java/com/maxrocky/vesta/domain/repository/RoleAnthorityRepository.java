package com.maxrocky.vesta.domain.repository;

/**
 * Created by zhanghj on 2016/1/25.
 */

import com.maxrocky.vesta.domain.model.RoleRoleanthorityEntity;

import java.util.List;

/**
 * 员工角色表
 */
public interface RoleAnthorityRepository {

    /**
     * 根据员工Id查找关系
     * @param staffId
     * @return
     */
    public RoleRoleanthorityEntity getAnthorityByStaffId(String staffId);

    /**
     * 更新员工角色关系
     * @param anthority
     * @return
     */
    public boolean updateAnthority(RoleRoleanthorityEntity anthority);

    /**
     * 根据角色ID获取相应的关系
     * */
    public List<RoleRoleanthorityEntity> getRoleanthorityList(String appSetId);

    /**
     * 删除员工角色关系
     */
    public boolean deleteAnthority(String staffId);

    /**根据员工ID查找对应关系*/
    public List<RoleRoleanthorityEntity> getRoleanthoritys(String staffId);

    /**
     * 质检app角色添加用户
     * */
    public void roleAdduser(RoleRoleanthorityEntity roleRoleanthorityEntity);

    /**
     * 根据角色ID 查询该角色下的用户数
     * */
    public int getStaffCount(String appRoleSetId);

    /**删除关联关系*/
    public void delRoleanthority(String appRoleSetId);

    /**根据条件删除关系*/
    public void delStaffRoleSet(String staffId,String appRoleSetId);
    /**删除质检角色关系*/
    void delAdminStaffRoleSet(String staffId,String roleSetId);
    //删除质检后台角色关系
    void delstaffRole(String setId);
    //删除质检后台角色关系(工程下删除)
    void delstaffRole(String setId,String projectId);
    /**根据条件查找关系*/
    List<RoleRoleanthorityEntity> getRoleanthoritys(String staffId,String appRoleSetId);
  }
