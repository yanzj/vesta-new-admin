package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.dto.adminDTO.AppRoleDTO;
import com.maxrocky.vesta.application.dto.adminDTO.AppRolesetDTO;
import com.maxrocky.vesta.application.dto.adminDTO.RoleSetDTO;
import com.maxrocky.vesta.application.dto.adminDTO.StaffNameDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by zhanghj on 2016/2/21.
 */
public interface AppRolesetService {

    public List<AppRolesetDTO> listAppRoleSet();

    /**
     * 根据条件获取app角色列表
     * */
    public List<RoleSetDTO> getAppRoleSets(String roleSetName,WebPage webPage);

    /**获取角色详情*/
    public RoleSetDTO getAppRoleSetDetail(String appRoleSetId);

    /**新增APP角色*/
    public void addAppRoleSet(AppRoleDTO appRoleDTO);

    /**更新APP角色*/
    public void updateAppRoleSet(AppRoleDTO appRoleDTO);

    /**删除APP角色*/
    public void delAppRoleSet(String appRoleSetId);

    /**获取角色列表*/
    List<RoleSetDTO> allAppRoleSets();
    /**获取角色内的用户*/
    List<StaffNameDTO> getSetsStaff(String appRoleSetId);
    /**根据条件获取角色外的用户*/
    List<StaffNameDTO> getSetOutStaff(StaffNameDTO staffNameDTO);
    /**保存用户角色关系*/
    ApiResult saveStaffRoleSet(StaffNameDTO staffNameDTO);
    /**删除用户角色关系*/
    ApiResult delStaffRoleSet(StaffNameDTO staffNameDTO);
    //根据员工ID获取角色
    List<AppRolesetDTO> getAppRoleSetsByStaffId(String staffId);
 }
