package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.dto.adminDTO.AppRoleDTO;

import java.util.List;

/**
 * Created by chen on 2016/5/11.
 */
public interface AppRoleService {
    /**权限列表----质检APP阶段列表*/
    public List<AppRoleDTO> getAppRoleList();
    /**阶段菜单*/
    public List<AppRoleDTO> appRoleMenus();
    /**根据角色ID获取阶段菜单*/
    public List<AppRoleDTO> appRoleMenuList(String appRoleSetId);
}
