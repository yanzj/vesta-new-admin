package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.jsonDTO.MenuDTO;
import com.maxrocky.vesta.domain.model.RoleViewmodelEntity;

import java.util.List;

/**
 * Created by JillChen on 2016/1/13.
 */
public interface RoleViewmodelService {
    List<RoleViewmodelEntity> getViewListByUserId(String property, String userid);
    //新权限只查询会员
    List<RoleViewmodelEntity> getAuthViewListByUserId(String property, String userid);

    List<RoleViewmodelEntity> getViewListOtherByUserId(String property, String userid);
    //新权限只查询会员
    List<RoleViewmodelEntity> getAuthViewListOtherByUserId(String property, String userid);
    //质检根据角色ID获取菜单列表
    List<MenuDTO> appMenuList(String roleSetId);


    //新权限
    List<RoleViewmodelEntity> getAuthRoleViewByIdList(List<String> viewIdList,String category);
}
