package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.AppRolesetEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by zhanghj on 2016/2/20.
 */
public interface AppRoleSetRepository {
    //获取员工App角色信息
    public AppRolesetEntity getAppRolesetById(String id);
    public AppRolesetEntity getAppRolesetByAppSetName(String appSetName);
    /**
     * 获取员工App角色列表
     * @return
     */
    public List<AppRolesetEntity> listAppRoleset();
    /**
     * 通过员工ID 获取角色列表
     * */
    public List<AppRolesetEntity> getRoleNames(String staffId);

    /**
     * 创建角色
     * */
    public void addAppRoleSet(AppRolesetEntity appRolesetEntity);

    /***
     * 修改角色名
     */
    public void updateAppRoleSet(AppRolesetEntity appRolesetEntity);

    /**
     * 删除角色
     * */
    public void delAppRoleSet(AppRolesetEntity appRolesetEntity);

    /**
     * 根据条件获取APP角色列表
     * */
    public List<AppRolesetEntity> getAppRoleSets(String roleSetName,WebPage webPage);

    /**获取角色详情*/
    public AppRolesetEntity getAppRoleSetDetail(String appSetId);

    /**获取角色内的员工*/
    List<UserPropertyStaffEntity> getSetsStaff(String appSetId);
    /**获取角色外的员工*/
    List<UserPropertyStaffEntity> getSetOutStaff(UserPropertyStaffEntity userPropertyStaffEntity);
   }
