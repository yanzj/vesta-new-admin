package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.RoleViewmodelEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by JillChen on 2016/1/13.
 */
public interface RoleViewmodelRepository {
    List<RoleViewmodelEntity> getViewListByUserId(String property, String userid);
    // 新权限只查询会员
    List<RoleViewmodelEntity> getAuthViewListByUserId(String property, String userid);

    List<RoleViewmodelEntity> getViewLisOthertByUserId(String property, String userid);
    // 新权限只查询会员
    List<RoleViewmodelEntity> getAuthViewLisOthertByUserId(String property, String userid);
    //获取一级菜单最后一个
    RoleViewmodelEntity getLastFirVeiwModel();

    /**
     * 获取二级菜单最后一个
     * @param parId
     * @return
     */
    RoleViewmodelEntity getLastSecViewModel(String parId);

    //添加菜单
    boolean addViewModel(RoleViewmodelEntity roleViewmodelEntity);

    //根据Id获取
    RoleViewmodelEntity getModelById(String menuId);

    /**
     * 获取菜单列表
     * @param parId
     * @return
     */
    List<RoleViewmodelEntity> listMenu(String parId,WebPage webPage);

    /**
     * 质检APP 根据权限id和角色ID获取菜单列表
     * */
    List<RoleViewmodelEntity> getMenuList(String roleId,String roleSetId);
    /**
     * 根据权限appID获取菜单列表
     * */
    List<RoleViewmodelEntity> appRoleMenus(String appRoleId);
    /**根据角色ID获取菜单列表*/
    List<RoleViewmodelEntity> appMenuList(String roleSetId);



    /****************************新权限***********************/
    //根据id查询菜单实体类数据
    List<RoleViewmodelEntity> getAuthV0iewListByIdList(List<String> viewIdList,String belong);
 }
