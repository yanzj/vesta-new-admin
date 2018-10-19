package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.dto.adminDTO.OrganizeDTO;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by chen on 2016/6/2.
 */
public interface OrganizeService {
    //根据组ID获取组详情
    OrganizeDTO getOrganizeDetail(String organizeId);
    //获取组列表
    List<OrganizeDTO> getOrganizeList();
    //根据项目id获取该项目关联组列表
    List<OrganizeDTO> getOrganizeInList(String projectId);
    //根据项目ID获取该项目外的组列表
    List<OrganizeDTO> getOrganizeOutList(String projectId);
    //根据角色ID获取该角色关联组列表
    List<OrganizeDTO> getOrganizeInRole(String roleSetId);
    //根据角色ID获取该角色外的组列表
    List<OrganizeDTO> getOrganizeOutRole(String roleSetId);
    //根据条件获取组列表
    List<OrganizeDTO> getOrganizes(OrganizeDTO organizeDTO,WebPage webPage);
    //新增组
    void addOrganize(OrganizeDTO organizeDTO);
    //删除组
    void delOrganize(String organizeId);
    //修改组
    void updateOrganize(OrganizeDTO organizeDTO);
    //根据员工ID获取组列表
    List<String> getOrgeList(String staffId);
    //根据员工ID获取单向组列表
    List<OrganizeDTO> getOrgans(String staffId);
    //根据员工ID获取项目列表
    List<String> getOProjectList(String staffId);
    //根据员工id获取crmid
    List<String> getOrgeCrmList(String staffId);
    //根据员工ID获取项目及项目下权限组
    List<String> getProjectRole(String staffId);
    //根据员工ID获取项目及项目下权限组
    List<String> getProjectRoleByNum(String staffId,String projectNum);
    //根据项目Id获取项目Num
    Map<String, Object> getProjectNumListProjectId(Map<String, String> map);
}
