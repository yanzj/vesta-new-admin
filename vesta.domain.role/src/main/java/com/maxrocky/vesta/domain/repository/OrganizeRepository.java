package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HouseProjectEntity;
import com.maxrocky.vesta.domain.model.OrganizeEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by chen on 2016/6/1.
 */
public interface OrganizeRepository {
    //新增组
    void addOrganize(OrganizeEntity organizeEntity);
    //根据项目ID获取该项目关联组列表
    List<OrganizeEntity> getOrganizeInProject(String projectId);
    //根据项目ID获取该项目无关的组列表
    List<OrganizeEntity> getOrganizeOutProject(String projectId);
    //根据角色ID获取该角色管理组列表
    List<OrganizeEntity> getOrganizeInRoleSet(String roleSetId);
    //根据角色ID获取该角色无关组列表
    List<OrganizeEntity> getOrganizeOutRoleSet(String roleSetId);
    //根据用户ID获取组
    List<OrganizeEntity> getOrganizeByStaffId(String staffId);
    //获取组列表
    List<OrganizeEntity> getOrganizeList();
    //根据条件获取组列表
    List<OrganizeEntity> getOrganizes(OrganizeEntity organizeEntity,WebPage webPage);
    //获取组详情
    OrganizeEntity getOrganizeDetail(String organizeId);
    //删除组
    void delOrganize(OrganizeEntity organizeEntity);
    //更新组
    void updateOrganize(OrganizeEntity organizeEntity);
    //通过部门id查询
    OrganizeEntity getOrganizeInfo(String crmId);

    //根据项目编码查询
    List<OrganizeEntity> getOrganizeInProjectNum(String projectNum);
    //查询项目实体
    List<HouseProjectEntity> getHouseProject();
}

