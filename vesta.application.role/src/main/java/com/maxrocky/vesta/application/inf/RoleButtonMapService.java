package com.maxrocky.vesta.application.inf;


import com.maxrocky.vesta.application.dto.adminDTO.AppRoleDTO;

/**
 * Created by chen on 2016/5/11.
 */
public interface RoleButtonMapService {
    //新增权限菜单关系
    public void addRoleButtonMap(AppRoleDTO appRoleDTO);
    //删除菜单关系
    public void deleteRoleButtonMap(String buttonId);
}
