package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.AppRolesetEntity;
import com.maxrocky.vesta.domain.model.HouseProjectEntity;
import com.maxrocky.vesta.domain.model.OrganizeUsermapEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;

import java.util.List;

/**
 * Created by chen on 2016/6/2.
 */
public interface OrganizeUserRepository {
    //新增
    void addOrganizeUser(OrganizeUsermapEntity organizeUsermapEntity);
    //如果没有则新增 有则更新
    void addDumpOrganizeUser(OrganizeUsermapEntity organizeUsermapEntity);
    //删除某组下的所有关系
    void delOrganizeUser(String organizeId);
    //删除某人的所有群组关系
    void delUserOrganize(String staffId);
    //根据组ID获取用户列表
    List<UserPropertyStaffEntity> getStaffsByOrganizeId(String organizeId);
    //根据条件获取该组之外的用户列表
    List<UserPropertyStaffEntity> getStaffsOutOrganize(OrganizeUsermapEntity organizeUsermapEntity);
    //根据组ID和用户ID获取关系
    OrganizeUsermapEntity getOrganizeUsermap(String organizeId,String staffId);
    //根据群组ID获取关系
    List<OrganizeUsermapEntity> getOrganizeUsermapList(String organizeId);
    //删除关系
    void delOrganizeStaff(OrganizeUsermapEntity organizeUsermapEntity);
    //删除某个关系
    void delOStaffs(String organizeId,String staffId);
    //根据用户ID从常用组中获取项目列表
    List<HouseProjectEntity> getOProjectByStaff(String staffId);
    //根据用户ID从常用组中获取角色列表
    List<AppRolesetEntity> getRoleSetByStaff(String staffId);
}
