package com.maxrocky.vesta.domain.project.repository;

import com.maxrocky.vesta.domain.baisc.model.BasicStaffDataEntity;
import com.maxrocky.vesta.domain.project.model.SecurityAreaEntity;
import com.maxrocky.vesta.domain.project.model.SecurityGroupEntity;
import com.maxrocky.vesta.domain.project.model.SecurityProjectEntity;
import com.maxrocky.vesta.domain.project.model.SecurityRoleEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2017/6/5.
 */
public interface SecurityProjectRepository {
    /**
     * 获取集团名称
     *
     * @param map
     * @param webPage
     * @return
     */
    List<SecurityGroupEntity> getSecurityGroupList(Map map, WebPage webPage);

    /**
     * 新增集团信息
     *
     * @param o
     */
    void addSecurityEntity(Object o);

    /**
     * 按ID获取集团信息
     *
     * @param groupId
     * @return
     */
    SecurityGroupEntity getSecurityGroupDetailById(String groupId);

    /**
     * 获取部门信息
     *
     * @param groupId
     * @param s
     * @return
     */
    List<Object[]> getEmploysForGroupDepByGroupId(String groupId, String s);

    /**
     * 获取人员信息
     *
     * @param groupId
     * @param s
     * @return
     */
    List<Object[]> getStaffsForGroupStaffByGroupId(String groupId, String s);

    /**
     * 编辑集团信息
     *
     * @param o
     * @return
     */
    boolean updateEntity(Object o);

    /**
     * 获取与部门、人的关系
     *
     * @param typeId   集团ID、区域ID、项目ID
     * @param dataType 1：责任单位
     *                 2：人员
     * @param typeRole 1：HSE部门
     *                 2：项目安全员
     *                 3：项目全体成员
     *                 4：甲方全全员
     *                 5：甲方工程师
     *                 6：总包、监理
     * @return
     */
    List<SecurityRoleEntity> getStaffEmploys(String typeId, String dataType, String typeRole);

    /**
     * 删除对应的关系
     *
     * @param securityRoleEntity
     */
    void deleteGroupRole(SecurityRoleEntity securityRoleEntity);

    /**
     * 新增关系
     *
     * @param securityRoleEntity
     */
    void dumpAddGroupRole(SecurityRoleEntity securityRoleEntity);

    /**
     * 分页获取区域列表信息
     *
     * @param map
     * @param webPage
     * @return
     */
    List<SecurityAreaEntity> getSecurityAreaList(Map map, WebPage webPage);

    /**
     * 按ID获取区域详情
     *
     * @param areaId
     * @return
     */
    SecurityAreaEntity getSecurityAreaDetailById(String areaId);

    /**
     * 分页安全项目 信息
     *
     * @param map
     * @param webPage
     * @return
     */
    List<SecurityProjectEntity> getSecurityProjectList(Map map, WebPage webPage);

    /**
     * 按ID获取安全项目信息
     *
     * @param projectId
     * @return
     */
    SecurityProjectEntity getSecurityProjectDetailById(String projectId);

    /**
     * 删除安全项目
     *
     * @param projectId
     */
    boolean deleteSecurityProject(String projectId, String groupId);
    /**
     * 删除基础数据安全项目
     *
     * @param id
     */
    boolean deleteBasicProject(String id, String ids);

    /**
     * 删除对应角色的关系
     *
     * @param typeId
     */
    void deleteSecurityRole(String typeId, String typeIds);

    /**
     * 删除安全区域
     *
     * @param areaId
     * @return
     */
    boolean deleteSecurityArea(String areaId, String groupId);

    /**
     * 按区域ID获取项目信息
     *
     * @param areaId
     * @return
     */
    List<SecurityProjectEntity> getSecurityProjectList(String areaId, String groupId);

    /**
     * 按区域ID删除对应项目
     *
     * @param areaId
     * @return
     */
    boolean deleteSecurityProjectByAreaId(String areaId);

    /**
     * 按类型ID删除关系
     *
     * @param typeIds 集团id、区域id、项目id
     */
    void deleteSecurityRoleByTypeIds(String typeIds);

    /**
     * 删除集团信息
     *
     * @param groupId
     * @return
     */
    boolean deleteSecurityGroup(String groupId);

    /**
     * 获取集团下所有的区域信息
     *
     * @param groupId
     * @return
     */
    List<SecurityAreaEntity> getSecurityAreaList(String groupId);

    /**
     * 获取项目下拉框
     */
    List<SecurityProjectEntity> getProject();

    /**
     * 获取集团公司下拉框
     */
    List<SecurityGroupEntity> getGroup();

    /**
     * 获取集团公司下拉框
     */
    List<SecurityAreaEntity> getAreaById(String id);


    /**
     * 获取集团公司下拉框
     */
    List<SecurityProjectEntity> getProjectById(String id);

    /**
     * 修改项目基础数据
     *
     * @param id
     * @param name
     */
    void updateBasicProject(String id, String name);

    /**
     * 校验集团名称
     *
     * @param groupName
     * @return
     */
    boolean checkGroupName(String groupName);

    /**
     * 校验区域名称
     *
     * @param areaName
     * @return
     */
    boolean checkAreaName(String areaName);

    /**
     * 校验项目名称
     *
     * @param projectName
     * @return
     */
    boolean checkProjectName(String projectName);

    /**
     * 查出基础数据
     *
     * @param dataId
     * @param dataRole
     * @return
     */
    List<BasicStaffDataEntity> getBasicStaffEmploys(String dataId, String dataRole, String dataType);

    /**
     * 删除基础数据关系权限
     *
     * @param basicStaffDataEntity
     */
    void deleteBasicRole(BasicStaffDataEntity basicStaffDataEntity);
    /**
     * 删除基础数据人员与项目关系
     *
     * @param id
     */
    void deleteBasicRoles(String id, String ids);

    /**
     * 新增基础数据关系权限
     *
     * @param basicStaffDataEntity
     */
    void dumpAddBasicRole(BasicStaffDataEntity basicStaffDataEntity);

    /**
     * 根据集团Id 查询项目详情
     */
    List<SecurityProjectEntity> getPorjectByGroupId(String id);

    /**
     *  根据Id 获取检查详情
     */
    Object[] getPlanById(String planId);


    /**
     *  根据id查询计划图片信息
     */
    List<Object[]> getPlanImageById(String planId,String type);

    /**
     * 根据id查询计划检查单位信息
     */
    List<Object[]> getPlanProjectById(String planId);

    /**
     * 根据查询出idList查询计划图片信息
     */
    List<Object[]> getPlanImageByIdList(List<String> idList,String type);

    /**
     * 查询关联关系
     */
    List<SecurityRoleEntity> securityRoleEntity();

}
