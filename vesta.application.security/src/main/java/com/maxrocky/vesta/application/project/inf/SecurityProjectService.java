package com.maxrocky.vesta.application.project.inf;

import com.maxrocky.vesta.application.inspectionPlan.DTO.InspectionPlanDTO;
import com.maxrocky.vesta.application.project.DTO.SecurityAllRoleDTO;
import com.maxrocky.vesta.application.project.DTO.SecurityAreaDTO;
import com.maxrocky.vesta.application.project.DTO.SecurityGroupDTO;
import com.maxrocky.vesta.application.project.DTO.SecurityProjectDTO;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2017/6/5.
 */
public interface SecurityProjectService {
    /**
     * 查询集团信息
     *
     * @param securityGroupDTO
     * @param webPage
     * @param userPropertystaff
     * @return
     */
    List<SecurityGroupDTO> getSecurityGroupList(SecurityGroupDTO securityGroupDTO, WebPage webPage, UserPropertyStaffEntity userPropertystaff);

    /**
     * 新增集团信息
     *
     * @param securityGroupDTO
     * @param userPropertystaff
     */
    void addSecurityGroup(SecurityGroupDTO securityGroupDTO, UserPropertyStaffEntity userPropertystaff);

    /**
     * 按ID获取集团权限信息
     *
     * @param groupId
     * @return
     */
    SecurityAllRoleDTO getSecurityGroupRoleListById(String groupId);

    /**
     * 编辑集团信息
     *
     * @param securityGroupDTO
     */
    void updateGroupRole(SecurityGroupDTO securityGroupDTO);

    /**
     * 获取区域信息
     *
     * @param securityAreaDTO
     * @param webPage
     * @param userPropertystaff
     * @return
     */
    List<SecurityAreaDTO> getSecurityAreaList(SecurityAreaDTO securityAreaDTO, WebPage webPage, UserPropertyStaffEntity userPropertystaff);

    /**
     * 新增区域信息
     *
     * @param securityAreaDTO
     * @param userPropertystaff
     */
    void addSecurityArea(SecurityAreaDTO securityAreaDTO, UserPropertyStaffEntity userPropertystaff);

    /**
     * 获取区域权限
     *
     * @param areaId
     * @return
     */
    SecurityAllRoleDTO getSecurityAreaRoleListById(String areaId);

    /**
     * 编辑区域信息
     *
     * @param securityAreaDTO
     */
    void updateAreaRole(SecurityAreaDTO securityAreaDTO);

    /**
     * 安全项目列表信息
     *
     * @param securityProjectDTO
     * @param webPage
     * @param userPropertystaff
     * @return
     */
    List<SecurityProjectDTO> getSecurityProjectList(SecurityProjectDTO securityProjectDTO, WebPage webPage, UserPropertyStaffEntity userPropertystaff);

    /**
     * 新增安全项目信息
     *
     * @param securityProjectDTO
     * @param userPropertystaff
     */
    void addSecurityProject(SecurityProjectDTO securityProjectDTO, UserPropertyStaffEntity userPropertystaff);

    /**
     * 安全项目权限信息
     *
     * @param proejectId
     * @return
     */
    SecurityAllRoleDTO getSecurityProjectRoleListById(String proejectId);

    /**
     * 编辑项目信息
     *
     * @param securityProjectDTO
     */
    void updateProjectRole(SecurityProjectDTO securityProjectDTO);

    /**
     * 删除安全项目
     *
     * @param securityProjectDTO
     */
    void deleteSecurityProject(SecurityProjectDTO securityProjectDTO);

    /**
     * 删除安全区域
     *
     * @param securityAreaDTO
     */
    void deleteSecurityArea(SecurityAreaDTO securityAreaDTO);

    /**
     * 删除安全集团
     *
     * @param securityGroupDTO
     */
    void deleteSecurityGroup(SecurityGroupDTO securityGroupDTO);

    /**
     * 获取项目公司下拉列表
     *
     * @return Map
     */
    public Map<String, String> getProject();


    /**
     * 根据人员信息获取下拉的项目公司信息  安全
     *
     * @return Map
     */
    public Map<String, String> getProjectByUser(UserInformationEntity userPropertystaffEntity);


    /**
     * 根据人员信息获取下拉的项目公司信息 工程
     *
     * @return Map
     */
    public Map<String, String> getESProjectByUser(UserInformationEntity userPropertystaffEntity);

    /**
     * 根据人员信息获取下拉的项目公司信息 工程
     *
     * @return Map
     */
    public Map<String, String> getESAllProjectByUser(UserInformationEntity userPropertystaffEntity);

    /**
     * 获取集团公司下拉列表
     *
     * @return Map
     */
    public Map<String, String> getGroup();

    /**
     * 根据集团公司id获取区域公司下拉列表
     *
     * @return Map
     */
    public Map<String, String> getAreaById(String id);

    /**
     * 根据区域公司id获取项目公司下拉列表
     *
     * @return Map
     */
    public Map<String, String> getProjectById(String id);

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
     * 获取检查计划详情
     */

    InspectionPlanDTO inspectionPlanDetails(String planId);


    /**
     * 获取项目层级信息数据  安全
     *
     * @return Map
     */
    public Map<String, String> getAgencyByTypeAndUser(UserInformationEntity userEntity,String type,String pairentId);

    /**
     * 获取项目层级信息数据 工程
     *
     * @return Map
     */
    public Map<String, String> getESAgencyByTypeAndUser(UserInformationEntity userEntity,String type,String pairentId);

    /**
     * 获取集团 工程
     *
     * @return Map
     */
    public String getESAgencyByUser(UserInformationEntity userEntity);

    /**
     * 获取项目层级信息数据 客关
     *
     * @return Map
     */
    public Map<String, String> getQCAgencyByTypeAndUser(UserInformationEntity userEntity,String type,String pairentId);
}
