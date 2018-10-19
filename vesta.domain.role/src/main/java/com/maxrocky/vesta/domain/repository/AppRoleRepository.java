package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.AppRoleEntity;

import java.util.List;

/**
 * Created by chen on 2016/5/4.
 */
public interface AppRoleRepository {
    //根据角色ID列表获取权限列表
    public List<AppRoleEntity> getRoles(String setIds);

    //根据角色ID获取权限列表
    public List<AppRoleEntity> getRoleList(String setId);

    //获取权限列表
    public List<AppRoleEntity> roleList();

    /**
     * 根据角色ID获取 交付APP 权限列表
     *
     * @param appSetId
     * @return
     */
    List<AppRoleEntity> getRepairAppRoleList(String appSetId);

    /**
     * 根据角色ID获取 工程APP 权限列表
     *
     * @param appSetId
     * @return
     */
    List<AppRoleEntity> getEngineeringAppRoleList(String appSetId);
}
