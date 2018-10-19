package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.RoleRolebuttonmapEntity;

import java.util.List;

/**
 * Created by zhanghj on 2016/4/18.
 */
public interface RoleButtonMapRepository {
    //根据角色ID查询关系 质检专用
    public List<RoleRolebuttonmapEntity> getButtonMapList(String appRoleSetId);

    //添加按钮菜单关系
    public boolean addButtonMap(RoleRolebuttonmapEntity roleRolebuttonmapEntity);
    //删除权限菜单关系
    public void delButtonMap(String buttonId);
}
