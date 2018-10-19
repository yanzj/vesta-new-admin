package com.maxrocky.vesta.domain.repository;


import com.maxrocky.vesta.domain.model.RoleRoleEntity;

import java.util.List;

/**
 * Created by zhanghj on 2016/1/18.
 */
public interface RoleRoleRepository {

    /**
     * 查询所有权限
     * @return
     */
        public List<RoleRoleEntity> listRoleRole();

    /**
     * 根据所属模块查询权限：1-物业管理，2-商户管理，3-房屋租赁管理，4-邻里圈管理，5-用户管理管理，6-数据统计
     * @param roledesc
     * @return
     */
        public List<RoleRoleEntity> listRoleRoleByroledesc(String roledesc);

    /**
     * 获取分类列表
     * @return
     */
    public List<String> getGroup();

    /**
     * 添加权限
     * @param roleRoleEntity
     * @return
     */
    boolean addRole(RoleRoleEntity roleRoleEntity);
}
