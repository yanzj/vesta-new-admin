package com.maxrocky.vesta.application.project.impl;

import com.maxrocky.vesta.application.inspectionPlan.DTO.ImageDTO;
import com.maxrocky.vesta.application.inspectionPlan.DTO.InspectionPlanDTO;
import com.maxrocky.vesta.application.inspectionPlan.DTO.InspectionUnitDTO;
import com.maxrocky.vesta.application.project.DTO.*;
import com.maxrocky.vesta.application.project.inf.SecurityProjectService;
import com.maxrocky.vesta.domain.baisc.model.BasicProjectEntity;
import com.maxrocky.vesta.domain.baisc.model.BasicStaffDataEntity;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.project.model.SecurityAreaEntity;
import com.maxrocky.vesta.domain.project.model.SecurityGroupEntity;
import com.maxrocky.vesta.domain.project.model.SecurityProjectEntity;
import com.maxrocky.vesta.domain.project.model.SecurityRoleEntity;
import com.maxrocky.vesta.domain.project.repository.SecurityProjectRepository;
import com.maxrocky.vesta.domain.repository.AuthAgencyRepository;
import com.maxrocky.vesta.domain.repository.UserAgencyRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Jason on 2017/6/5.
 */
@Service
public class SecurityProjectServiceImpl implements SecurityProjectService {
    @Autowired
    private SecurityProjectRepository securityProjectRepository;
    @Autowired
    private UserAgencyRepository userAgencyRepository;
    @Autowired
    private AuthAgencyRepository authAgencyRepository;

    @Override
    public List<SecurityGroupDTO> getSecurityGroupList(SecurityGroupDTO securityGroupDTO, WebPage webPage, UserPropertyStaffEntity userPropertystaff) {
        Map map = new HashMap();
        map.put("groupName", securityGroupDTO.getGroupName());//集团名称
        List<SecurityGroupEntity> securityGroupEntityList = securityProjectRepository.getSecurityGroupList(map, webPage);
        List<SecurityGroupDTO> securityGroupDTOList = new ArrayList<SecurityGroupDTO>();
        if (securityGroupEntityList != null && securityGroupEntityList.size() > 0) {
            for (SecurityGroupEntity securityGroupEntity : securityGroupEntityList) {
                SecurityGroupDTO securityGroupDTO1 = new SecurityGroupDTO();
                securityGroupDTO1.setGroupId(securityGroupEntity.getGroupId());//集团ID
                securityGroupDTO1.setGroupName(securityGroupEntity.getGroupName());//集团名称
                securityGroupDTO1.setCreatDate(DateUtils.format(securityGroupEntity.getCreateOn(), DateUtils.FORMAT_LONG));//创建时间
                securityGroupDTO1.setState(securityGroupEntity.getState());//状态
                securityGroupDTOList.add(securityGroupDTO1);
            }
        }
        return securityGroupDTOList;
    }

    @Override
    public void addSecurityGroup(SecurityGroupDTO securityGroupDTO, UserPropertyStaffEntity userPropertystaff) {
        if (securityGroupDTO != null) {
            SecurityGroupEntity securityGroupEntity = new SecurityGroupEntity();
            securityGroupEntity.setGroupId(IdGen.uuid());
            securityGroupEntity.setGroupName(securityGroupDTO.getGroupName());
            securityGroupEntity.setCreateOn(new Date());
            securityGroupEntity.setModifyOn(new Date());
            securityGroupEntity.setState("1");
            securityProjectRepository.addSecurityEntity(securityGroupEntity);
            //维护基础数据表
            BasicProjectEntity basicProjectEntity = new BasicProjectEntity();
            basicProjectEntity.setCurrentId(securityGroupEntity.getGroupId());
            basicProjectEntity.setName(securityGroupEntity.getGroupName());
            basicProjectEntity.setLevel("1");
            basicProjectEntity.setState("1");
            basicProjectEntity.setModifyDate(new Date());
            securityProjectRepository.addSecurityEntity(basicProjectEntity);
        }
    }

    @Override
    public SecurityAllRoleDTO getSecurityGroupRoleListById(String groupId) {
        SecurityGroupEntity securityGroupEntity = securityProjectRepository.getSecurityGroupDetailById(groupId);
        SecurityAllRoleDTO securityAllRoleDTO = new SecurityAllRoleDTO();
        securityAllRoleDTO.setId(securityGroupEntity.getGroupId());//集团ID
        securityAllRoleDTO.setName(securityGroupEntity.getGroupName());//集团名称
        securityAllRoleDTO.setModifyDate(DateUtils.format(securityGroupEntity.getModifyOn(), DateUtils.FORMAT_LONG));//修改时间
        List<Object[]> agencyObj = securityProjectRepository.getEmploysForGroupDepByGroupId(groupId, "1");//获取当前集团下HSE部门权限对应责任单位列表
        List<Object[]> userPropertyStaffObj = securityProjectRepository.getStaffsForGroupStaffByGroupId(groupId, "1");  //获取当前集团下HSE部门对应的人员列表
        List<SecurityRoleDTO> securityRoleDTOS = null;
        if (agencyObj != null && agencyObj.size() > 0) {
            securityRoleDTOS = new ArrayList<SecurityRoleDTO>();
            for (Object[] obj : agencyObj) {
                SecurityRoleDTO securityRoleDTO = new SecurityRoleDTO();
                securityRoleDTO.setId((String) obj[0]);
                securityRoleDTO.setName((String) obj[1]);
                securityRoleDTOS.add(securityRoleDTO);
            }
            securityAllRoleDTO.setGroupDep(securityRoleDTOS);
        }
        if (userPropertyStaffObj != null && userPropertyStaffObj.size() > 0) {
            securityRoleDTOS = new ArrayList<SecurityRoleDTO>();
            for (Object[] obj : userPropertyStaffObj) {
                SecurityRoleDTO securityRoleDTO = new SecurityRoleDTO();
                securityRoleDTO.setId((String) obj[0]);
                securityRoleDTO.setName((String) obj[1]);
                securityRoleDTOS.add(securityRoleDTO);
            }
            securityAllRoleDTO.setGroupStaff(securityRoleDTOS);
        }
        return securityAllRoleDTO;
    }

    @Override
    public void updateGroupRole(SecurityGroupDTO securityGroupDTO) {
        SecurityGroupEntity securityGroupEntity = securityProjectRepository.getSecurityGroupDetailById(securityGroupDTO.getGroupId());
        if (securityGroupEntity != null) {
            //如果修改了集团名称，必须要维护前端基础数据表
            if (!securityGroupDTO.getGroupName().equals(securityGroupEntity.getGroupName())) {
                securityProjectRepository.updateBasicProject(securityGroupEntity.getGroupId(), securityGroupDTO.getGroupName());
            }
            securityGroupEntity.setGroupName(securityGroupDTO.getGroupName());
            securityGroupEntity.setModifyOn(new Date());
            boolean f = securityProjectRepository.updateEntity(securityGroupEntity);
            if (f) {
                SecurityRoleEntity securityRoleEntity = new SecurityRoleEntity();
                securityRoleEntity.setStatus("1");
                securityRoleEntity.setTypeId(securityGroupDTO.getGroupId());
                securityRoleEntity.setModifyTime(new Date());
                //维护基础数据（为app设计）
                BasicStaffDataEntity basicStaffDataEntity = new BasicStaffDataEntity();
                basicStaffDataEntity.setDataId(securityGroupDTO.getGroupId());
                basicStaffDataEntity.setModifyTime(new Date());
                basicStaffDataEntity.setState("1");
                String ids[];
                //集团HSE部门关联的部门信息
                securityRoleEntity.setDataType("1");
                securityRoleEntity.setTypeRole("1");
                basicStaffDataEntity.setDataType("1");
                basicStaffDataEntity.setDataRole("1");
                if (!StringUtil.isEmpty(securityGroupDTO.getGroupDept())) {
                    ids = securityGroupDTO.getGroupDept().split(",");
                } else {
                    ids = null;
                }
                dumpSave(securityRoleEntity, ids);
                dumpBasicSave(basicStaffDataEntity, ids, "1");
                //集团HSE部门关联的人员信息
                securityRoleEntity.setDataType("2");
                securityRoleEntity.setTypeRole("1");
                basicStaffDataEntity.setDataType("2");
                basicStaffDataEntity.setDataRole("1");
                if (!StringUtil.isEmpty(securityGroupDTO.getGroupStaff())) {
                    ids = securityGroupDTO.getGroupStaff().split(",");
                } else {
                    ids = null;
                }
                dumpSave(securityRoleEntity, ids);
                dumpBasicSave(basicStaffDataEntity, ids, "2");
            }
        }
    }

    @Override
    public List<SecurityAreaDTO> getSecurityAreaList(SecurityAreaDTO securityAreaDTO, WebPage webPage, UserPropertyStaffEntity userPropertystaff) {
        Map map = new HashMap();
        map.put("groupId", securityAreaDTO.getGroupId());//集团Id
        map.put("groupName", securityAreaDTO.getGroupName());//集团名称
        map.put("areaName", securityAreaDTO.getAreaName());//区域名称
        List<SecurityAreaEntity> securityAreaEntityList = securityProjectRepository.getSecurityAreaList(map, webPage);
        List<SecurityAreaDTO> securityAreaDTOList = new ArrayList<SecurityAreaDTO>();
        if (securityAreaEntityList != null && securityAreaEntityList.size() > 0) {
            for (SecurityAreaEntity securityAreaEntity : securityAreaEntityList) {
                SecurityAreaDTO securityAreaDTO1 = new SecurityAreaDTO();
                securityAreaDTO1.setAreaId(securityAreaEntity.getAreaId());//区域ID
                securityAreaDTO1.setAreaName(securityAreaEntity.getAreaName());//区域名称
                securityAreaDTO1.setGroupId(securityAreaEntity.getGroupId());//集团ID
                securityAreaDTO1.setGroupName(securityAreaEntity.getGroupName());//集团名称
                securityAreaDTOList.add(securityAreaDTO1);
            }
        }
        return securityAreaDTOList;
    }

    @Override
    public void addSecurityArea(SecurityAreaDTO securityAreaDTO, UserPropertyStaffEntity userPropertystaff) {
        if (securityAreaDTO != null) {
            SecurityAreaEntity securityAreaEntity = new SecurityAreaEntity();
            securityAreaEntity.setAreaId(IdGen.uuid());//区域ID
            securityAreaEntity.setAreaName(securityAreaDTO.getAreaName());//区域名称
            securityAreaEntity.setGroupId(securityAreaDTO.getGroupId());//集团ID
            SecurityGroupEntity securityGroupEntity = securityProjectRepository.getSecurityGroupDetailById(securityAreaDTO.getGroupId());
            securityAreaEntity.setGroupName(securityGroupEntity.getGroupName());//集团名称
            securityAreaEntity.setCreateOn(new Date());//创建时间
            securityAreaEntity.setModifyOn(new Date());//修改时间
            securityAreaEntity.setState("1");//状态
            securityProjectRepository.addSecurityEntity(securityAreaEntity);
            //维护基础数据表
            BasicProjectEntity basicProjectEntity = new BasicProjectEntity();
            basicProjectEntity.setCurrentId(securityAreaEntity.getAreaId());
            basicProjectEntity.setName(securityAreaEntity.getAreaName());
            basicProjectEntity.setParentId(securityAreaEntity.getGroupId());
            basicProjectEntity.setLevel("2");
            basicProjectEntity.setState("1");
            basicProjectEntity.setModifyDate(new Date());
            securityProjectRepository.addSecurityEntity(basicProjectEntity);
        }
    }

    @Override
    public SecurityAllRoleDTO getSecurityAreaRoleListById(String areaId) {
        SecurityAreaEntity securityAreaEntity = securityProjectRepository.getSecurityAreaDetailById(areaId);
        SecurityAllRoleDTO securityAllRoleDTO = new SecurityAllRoleDTO();
        securityAllRoleDTO.setId(securityAreaEntity.getAreaId());//区域ID
        securityAllRoleDTO.setName(securityAreaEntity.getAreaName());//区域名称
        securityAllRoleDTO.setModifyDate(DateUtils.format(securityAreaEntity.getModifyOn(), DateUtils.FORMAT_LONG));//修改时间
        //获取当前区域下HSE部门（项目安全员）权限对应责任单位列表
        List<Object[]> agencyObj = securityProjectRepository.getEmploysForGroupDepByGroupId(areaId, "2");
        //获取当前区域下HSE部门（项目安全员）对应的人员列表
        List<Object[]> userPropertyStaffObj = securityProjectRepository.getStaffsForGroupStaffByGroupId(areaId, "2");
        List<SecurityRoleDTO> securityRoleDTOS = null;
        if (agencyObj != null && agencyObj.size() > 0) {
            securityRoleDTOS = new ArrayList<SecurityRoleDTO>();
            for (Object[] obj : agencyObj) {
                SecurityRoleDTO securityRoleDTO = new SecurityRoleDTO();
                securityRoleDTO.setId((String) obj[0]);
                securityRoleDTO.setName((String) obj[1]);
                securityRoleDTOS.add(securityRoleDTO);
            }
            securityAllRoleDTO.setSecurityOfficerDep(securityRoleDTOS);
        }
        if (userPropertyStaffObj != null && userPropertyStaffObj.size() > 0) {
            securityRoleDTOS = new ArrayList<SecurityRoleDTO>();
            for (Object[] obj : userPropertyStaffObj) {
                SecurityRoleDTO securityRoleDTO = new SecurityRoleDTO();
                securityRoleDTO.setId((String) obj[0]);
                securityRoleDTO.setName((String) obj[1]);
                securityRoleDTOS.add(securityRoleDTO);
            }
            securityAllRoleDTO.setSecurityOfficerStaff(securityRoleDTOS);
        }
        return securityAllRoleDTO;
    }

    @Override
    public void updateAreaRole(SecurityAreaDTO securityAreaDTO) {
        SecurityAreaEntity securityAreaEntity = securityProjectRepository.getSecurityAreaDetailById(securityAreaDTO.getAreaId());
        if (securityAreaEntity != null) {
            if (!securityAreaDTO.getAreaName().equals(securityAreaEntity.getAreaName())) {
                securityProjectRepository.updateBasicProject(securityAreaEntity.getAreaId(), securityAreaDTO.getAreaName());
            }
            securityAreaEntity.setAreaName(securityAreaDTO.getAreaName());
            securityAreaEntity.setModifyOn(new Date());
            boolean f = securityProjectRepository.updateEntity(securityAreaEntity);
            if (f) {
                SecurityRoleEntity securityRoleEntity = new SecurityRoleEntity();
                securityRoleEntity.setStatus("1");
                securityRoleEntity.setTypeId(securityAreaDTO.getAreaId());
                securityRoleEntity.setModifyTime(new Date());
                //基础数据关系（为app设计）
                BasicStaffDataEntity basicStaffDataEntity = new BasicStaffDataEntity();
                basicStaffDataEntity.setDataId(securityAreaDTO.getAreaId());
                basicStaffDataEntity.setState("1");
                basicStaffDataEntity.setModifyTime(new Date());
                String ids[];
                //区域项目安全员（HSE部门）关联的部门信息
                securityRoleEntity.setDataType("1");
                securityRoleEntity.setTypeRole("2");
                basicStaffDataEntity.setDataType("1");
                basicStaffDataEntity.setDataRole("2");
                if (!StringUtil.isEmpty(securityAreaDTO.getSecurityOfficerDep())) {
                    ids = securityAreaDTO.getSecurityOfficerDep().split(",");
                } else {
                    ids = null;
                }
                dumpSave(securityRoleEntity, ids);
                dumpBasicSave(basicStaffDataEntity, ids, "1");
                //区域项目安全员（HSE部门）关联的人员信息
                securityRoleEntity.setDataType("2");
                securityRoleEntity.setTypeRole("2");
                basicStaffDataEntity.setDataType("2");
                basicStaffDataEntity.setDataRole("2");
                if (!StringUtil.isEmpty(securityAreaDTO.getSecurityOfficerStaff())) {
                    ids = securityAreaDTO.getSecurityOfficerStaff().split(",");
                } else {
                    ids = null;
                }
                dumpSave(securityRoleEntity, ids);
                dumpBasicSave(basicStaffDataEntity, ids, "2");
            }
        }
    }

    @Override
    public List<SecurityProjectDTO> getSecurityProjectList(SecurityProjectDTO securityProjectDTO, WebPage webPage, UserPropertyStaffEntity userPropertystaff) {
        Map map = new HashMap();
        map.put("groupId", securityProjectDTO.getGroupId());//集团Id
        map.put("areaId", securityProjectDTO.getAreaId());//区域ID
        map.put("projectName", securityProjectDTO.getProjectName());//区域名称
        List<SecurityProjectEntity> securityProjectEntityList = securityProjectRepository.getSecurityProjectList(map, webPage);
        List<SecurityProjectDTO> securityProjectDTOList = new ArrayList<SecurityProjectDTO>();
        if (securityProjectEntityList != null && securityProjectEntityList.size() > 0) {
            for (SecurityProjectEntity securityProjectEntity : securityProjectEntityList) {
                SecurityProjectDTO securityProjectDTO1 = new SecurityProjectDTO();
                securityProjectDTO1.setProjectId(securityProjectEntity.getProjectId());//项目ID
                securityProjectDTO1.setProjectName(securityProjectEntity.getProjectName());//项目名称
                securityProjectDTO1.setGroupId(securityProjectEntity.getGroupId());//集团ID
                securityProjectDTO1.setGroupName(securityProjectEntity.getGroupName());//集团名称
                securityProjectDTO1.setAreaId(securityProjectEntity.getAreaId());//区域ID
                securityProjectDTO1.setAreaName(securityProjectEntity.getAreaName());//区域名称

                securityProjectDTOList.add(securityProjectDTO1);
            }
        }
        return securityProjectDTOList;

    }

    @Override
    public void addSecurityProject(SecurityProjectDTO securityProjectDTO, UserPropertyStaffEntity userPropertystaff) {
        if (securityProjectDTO != null) {
            SecurityProjectEntity securityProjectEntity = new SecurityProjectEntity();
            securityProjectEntity.setProjectId(IdGen.uuid());//项目ID
            securityProjectEntity.setProjectName(securityProjectDTO.getProjectName());//项目名称
            securityProjectEntity.setState("1");//状态
            securityProjectEntity.setCreateOn(new Date());//创建时间
            securityProjectEntity.setModifyOn(new Date());//修改时间
            SecurityAreaEntity securityAreaEntity = securityProjectRepository.getSecurityAreaDetailById(securityProjectDTO.getAreaId());
            securityProjectEntity.setAreaId(securityAreaEntity.getAreaId());//区域ID
            securityProjectEntity.setAreaName(securityAreaEntity.getAreaName());//区域名称
            securityProjectEntity.setGroupId(securityAreaEntity.getGroupId());//集团ID
            securityProjectEntity.setGroupName(securityAreaEntity.getGroupName());//集团名称

            securityProjectRepository.addSecurityEntity(securityProjectEntity);
            //维护基础数据表
            BasicProjectEntity basicProjectEntity = new BasicProjectEntity();
            basicProjectEntity.setCurrentId(securityProjectEntity.getProjectId());
            basicProjectEntity.setName(securityProjectEntity.getProjectName());
            basicProjectEntity.setParentId(securityProjectEntity.getAreaId());
            basicProjectEntity.setLevel("3");
            basicProjectEntity.setState("1");
            basicProjectEntity.setModifyDate(new Date());
            securityProjectRepository.addSecurityEntity(basicProjectEntity);
        }
    }

    @Override
    public SecurityAllRoleDTO getSecurityProjectRoleListById(String projectId) {
        SecurityProjectEntity securityProjectEntity = securityProjectRepository.getSecurityProjectDetailById(projectId);
        SecurityAllRoleDTO securityAllRoleDTO = new SecurityAllRoleDTO();
        securityAllRoleDTO.setId(securityProjectEntity.getProjectId());//项目ID
        securityAllRoleDTO.setName(securityProjectEntity.getProjectName());//项目名称
        securityAllRoleDTO.setModifyDate(DateUtils.format(securityProjectEntity.getModifyOn(), DateUtils.FORMAT_LONG));//修改时间
        //获取当前项目下 项目全体人员权限对应责任单位列表
        List<Object[]> projectOfficerDep = securityProjectRepository.getEmploysForGroupDepByGroupId(projectId, "3");
        //获取当前项目下 项目全体人员权限对应的人员列表
        List<Object[]> projectOfficerStaff = securityProjectRepository.getStaffsForGroupStaffByGroupId(projectId, "3");
        //获取当前项目下 甲方安全员权限对应责任单位列表
        List<Object[]> partyASecurityOfficerDep = securityProjectRepository.getEmploysForGroupDepByGroupId(projectId, "4");
        //获取当前项目下 甲方安全员权限对应的人员列表
        List<Object[]> partyASecurityOfficerStaff = securityProjectRepository.getStaffsForGroupStaffByGroupId(projectId, "4");
        //获取当前项目下 甲方工程师权限对应责任单位列表
        List<Object[]> partyAEngineerDep = securityProjectRepository.getEmploysForGroupDepByGroupId(projectId, "5");
        //获取当前项目下 甲方工程师权限对应的人员列表
        List<Object[]> partyAEngineerStaff = securityProjectRepository.getStaffsForGroupStaffByGroupId(projectId, "5");
        //获取当前项目下 总包权限对应责任单位列表
        List<Object[]> contractorDep = securityProjectRepository.getEmploysForGroupDepByGroupId(projectId, "6");
        //获取当前项目下 总包权限对应的人员列表
        List<Object[]> contractorStaff = securityProjectRepository.getStaffsForGroupStaffByGroupId(projectId, "6");
        //获取当前项目下 监理权限对应责任单位列表
        List<Object[]> supervisorDep = securityProjectRepository.getEmploysForGroupDepByGroupId(projectId, "7");
        //获取当前项目下 监理权限对应的人员列表
        List<Object[]> supervisorStaff = securityProjectRepository.getStaffsForGroupStaffByGroupId(projectId, "7");
        List<SecurityRoleDTO> securityRoleDTOS = null;
        if (projectOfficerDep != null && projectOfficerDep.size() > 0) {
            securityRoleDTOS = new ArrayList<SecurityRoleDTO>();
            for (Object[] obj : projectOfficerDep) {
                SecurityRoleDTO securityRoleDTO = new SecurityRoleDTO();
                securityRoleDTO.setId((String) obj[0]);
                securityRoleDTO.setName((String) obj[1]);
                securityRoleDTOS.add(securityRoleDTO);
            }
            securityAllRoleDTO.setProjectOfficerDep(securityRoleDTOS);
        }
        if (projectOfficerStaff != null && projectOfficerStaff.size() > 0) {
            securityRoleDTOS = new ArrayList<SecurityRoleDTO>();
            for (Object[] obj : projectOfficerStaff) {
                SecurityRoleDTO securityRoleDTO = new SecurityRoleDTO();
                securityRoleDTO.setId((String) obj[0]);
                securityRoleDTO.setName((String) obj[1]);
                securityRoleDTOS.add(securityRoleDTO);
            }
            securityAllRoleDTO.setProjectOfficerStaff(securityRoleDTOS);
        }
        if (partyASecurityOfficerDep != null && partyASecurityOfficerDep.size() > 0) {
            securityRoleDTOS = new ArrayList<SecurityRoleDTO>();
            for (Object[] obj : partyASecurityOfficerDep) {
                SecurityRoleDTO securityRoleDTO = new SecurityRoleDTO();
                securityRoleDTO.setId((String) obj[0]);
                securityRoleDTO.setName((String) obj[1]);
                securityRoleDTOS.add(securityRoleDTO);
            }
            securityAllRoleDTO.setPartyASecurityOfficerDep(securityRoleDTOS);
        }
        if (partyASecurityOfficerStaff != null && partyASecurityOfficerStaff.size() > 0) {
            securityRoleDTOS = new ArrayList<SecurityRoleDTO>();
            for (Object[] obj : partyASecurityOfficerStaff) {
                SecurityRoleDTO securityRoleDTO = new SecurityRoleDTO();
                securityRoleDTO.setId((String) obj[0]);
                securityRoleDTO.setName((String) obj[1]);
                securityRoleDTOS.add(securityRoleDTO);
            }
            securityAllRoleDTO.setPartyASecurityOfficerStaff(securityRoleDTOS);
        }
        if (partyAEngineerDep != null && partyAEngineerDep.size() > 0) {
            securityRoleDTOS = new ArrayList<SecurityRoleDTO>();
            for (Object[] obj : partyAEngineerDep) {
                SecurityRoleDTO securityRoleDTO = new SecurityRoleDTO();
                securityRoleDTO.setId((String) obj[0]);
                securityRoleDTO.setName((String) obj[1]);
                securityRoleDTOS.add(securityRoleDTO);
            }
            securityAllRoleDTO.setPartyAEngineerDep(securityRoleDTOS);
        }
        if (partyAEngineerStaff != null && partyAEngineerStaff.size() > 0) {
            securityRoleDTOS = new ArrayList<SecurityRoleDTO>();
            for (Object[] obj : partyAEngineerStaff) {
                SecurityRoleDTO securityRoleDTO = new SecurityRoleDTO();
                securityRoleDTO.setId((String) obj[0]);
                securityRoleDTO.setName((String) obj[1]);
                securityRoleDTOS.add(securityRoleDTO);
            }
            securityAllRoleDTO.setPartyAEngineerStaff(securityRoleDTOS);
        }
        if (contractorDep != null && contractorDep.size() > 0) {
            securityRoleDTOS = new ArrayList<SecurityRoleDTO>();
            for (Object[] obj : contractorDep) {
                SecurityRoleDTO securityRoleDTO = new SecurityRoleDTO();
                securityRoleDTO.setId((String) obj[0]);
                securityRoleDTO.setName((String) obj[1]);
                securityRoleDTOS.add(securityRoleDTO);
            }
            securityAllRoleDTO.setContractorDep(securityRoleDTOS);
        }
        if (contractorStaff != null && contractorStaff.size() > 0) {
            securityRoleDTOS = new ArrayList<SecurityRoleDTO>();
            for (Object[] obj : contractorStaff) {
                SecurityRoleDTO securityRoleDTO = new SecurityRoleDTO();
                securityRoleDTO.setId((String) obj[0]);
                securityRoleDTO.setName((String) obj[1]);
                securityRoleDTOS.add(securityRoleDTO);
            }
            securityAllRoleDTO.setContractorStaff(securityRoleDTOS);
        }
        if (supervisorDep != null && supervisorDep.size() > 0) {
            securityRoleDTOS = new ArrayList<SecurityRoleDTO>();
            for (Object[] obj : supervisorDep) {
                SecurityRoleDTO securityRoleDTO = new SecurityRoleDTO();
                securityRoleDTO.setId((String) obj[0]);
                securityRoleDTO.setName((String) obj[1]);
                securityRoleDTOS.add(securityRoleDTO);
            }
            securityAllRoleDTO.setSupervisorDep(securityRoleDTOS);
        }
        if (supervisorStaff != null && supervisorStaff.size() > 0) {
            securityRoleDTOS = new ArrayList<SecurityRoleDTO>();
            for (Object[] obj : supervisorStaff) {
                SecurityRoleDTO securityRoleDTO = new SecurityRoleDTO();
                securityRoleDTO.setId((String) obj[0]);
                securityRoleDTO.setName((String) obj[1]);
                securityRoleDTOS.add(securityRoleDTO);
            }
            securityAllRoleDTO.setSupervisortaff(securityRoleDTOS);
        }
        return securityAllRoleDTO;
    }

    @Override
    public void updateProjectRole(SecurityProjectDTO securityProjectDTO) {
        SecurityProjectEntity securityProjectEntity = securityProjectRepository.getSecurityProjectDetailById(securityProjectDTO.getProjectId());
        if (securityProjectEntity != null) {
            if (!securityProjectDTO.getProjectName().equals(securityProjectEntity.getProjectName())) {
                securityProjectRepository.updateBasicProject(securityProjectEntity.getProjectId(), securityProjectDTO.getProjectName());
            }
            securityProjectEntity.setProjectName(securityProjectDTO.getProjectName());
            securityProjectEntity.setModifyOn(new Date());
            boolean f = securityProjectRepository.updateEntity(securityProjectEntity);
            if (f) {
                SecurityRoleEntity securityRoleEntity = new SecurityRoleEntity();
                securityRoleEntity.setStatus("1");
                securityRoleEntity.setTypeId(securityProjectDTO.getProjectId());
                securityRoleEntity.setModifyTime(new Date());
                //基础数据权限关系（为app设计）
                BasicStaffDataEntity basicStaffDataEntity = new BasicStaffDataEntity();
                basicStaffDataEntity.setDataId(securityProjectDTO.getProjectId());
                basicStaffDataEntity.setState("1");
                basicStaffDataEntity.setModifyTime(new Date());
                String ids[];
                //项目下的项目全部人员关联的部门信息
                securityRoleEntity.setDataType("1");
                securityRoleEntity.setTypeRole("3");
                basicStaffDataEntity.setDataType("1");
                basicStaffDataEntity.setDataRole("3");
                if (!StringUtil.isEmpty(securityProjectDTO.getProjectOfficerDep())) {
                    ids = securityProjectDTO.getProjectOfficerDep().split(",");
                } else {
                    ids = null;
                }
                dumpSave(securityRoleEntity, ids);
                dumpBasicSave(basicStaffDataEntity, ids, "1");
                //项目下的项目全部人员关联的人员信息
                securityRoleEntity.setDataType("2");
                securityRoleEntity.setTypeRole("3");
                basicStaffDataEntity.setDataType("2");
                basicStaffDataEntity.setDataRole("3");
                if (!StringUtil.isEmpty(securityProjectDTO.getProjectOfficerStaff())) {
                    ids = securityProjectDTO.getProjectOfficerStaff().split(",");
                } else {
                    ids = null;
                }
                dumpSave(securityRoleEntity, ids);
                dumpBasicSave(basicStaffDataEntity, ids, "2");
                //项目下的甲方安全员关联的部门信息
                securityRoleEntity.setDataType("1");
                securityRoleEntity.setTypeRole("4");
                basicStaffDataEntity.setDataType("1");
                basicStaffDataEntity.setDataRole("4");
                if (!StringUtil.isEmpty(securityProjectDTO.getPartyASecurityOfficerDep())) {
                    ids = securityProjectDTO.getPartyASecurityOfficerDep().split(",");
                } else {
                    ids = null;
                }
                dumpSave(securityRoleEntity, ids);
                dumpBasicSave(basicStaffDataEntity, ids, "1");
                //项目下的甲方安全员关联的人信息
                securityRoleEntity.setDataType("2");
                securityRoleEntity.setTypeRole("4");
                basicStaffDataEntity.setDataType("2");
                basicStaffDataEntity.setDataRole("4");
                if (!StringUtil.isEmpty(securityProjectDTO.getPartyASecurityOfficerStaff())) {
                    ids = securityProjectDTO.getPartyASecurityOfficerStaff().split(",");
                } else {
                    ids = null;
                }
                dumpSave(securityRoleEntity, ids);
                dumpBasicSave(basicStaffDataEntity, ids, "2");
                //项目下的甲方工程师关联的部门信息
                securityRoleEntity.setDataType("1");
                securityRoleEntity.setTypeRole("5");
                basicStaffDataEntity.setDataType("1");
                basicStaffDataEntity.setDataRole("5");
                if (!StringUtil.isEmpty(securityProjectDTO.getPartyAEngineerDep())) {
                    ids = securityProjectDTO.getPartyAEngineerDep().split(",");
                } else {
                    ids = null;
                }
                dumpSave(securityRoleEntity, ids);
                dumpBasicSave(basicStaffDataEntity, ids, "1");
                //项目下的甲方工程师关联的人信息
                securityRoleEntity.setDataType("2");
                securityRoleEntity.setTypeRole("5");
                basicStaffDataEntity.setDataType("2");
                basicStaffDataEntity.setDataRole("5");
                if (!StringUtil.isEmpty(securityProjectDTO.getPartyAEngineerStaff())) {
                    ids = securityProjectDTO.getPartyAEngineerStaff().split(",");
                } else {
                    ids = null;
                }
                dumpSave(securityRoleEntity, ids);
                dumpBasicSave(basicStaffDataEntity, ids, "2");
                //项目下的总包关联的部门信息
                securityRoleEntity.setDataType("1");
                securityRoleEntity.setTypeRole("6");
                basicStaffDataEntity.setDataType("1");
                basicStaffDataEntity.setDataRole("6");
                if (!StringUtil.isEmpty(securityProjectDTO.getContractorDep())) {
                    ids = securityProjectDTO.getContractorDep().split(",");
                } else {
                    ids = null;
                }
                dumpSave(securityRoleEntity, ids);
                dumpBasicSave(basicStaffDataEntity, ids, "1");
                //项目下总包关联的人信息
                securityRoleEntity.setDataType("2");
                securityRoleEntity.setTypeRole("6");
                basicStaffDataEntity.setDataType("2");
                basicStaffDataEntity.setDataRole("6");
                if (!StringUtil.isEmpty(securityProjectDTO.getContractorStaff())) {
                    ids = securityProjectDTO.getContractorStaff().split(",");
                } else {
                    ids = null;
                }
                dumpSave(securityRoleEntity, ids);
                dumpBasicSave(basicStaffDataEntity, ids, "2");
                //项目下的监理关联的部门信息
                securityRoleEntity.setDataType("1");
                securityRoleEntity.setTypeRole("7");
                basicStaffDataEntity.setDataType("1");
                basicStaffDataEntity.setDataRole("7");
                if (!StringUtil.isEmpty(securityProjectDTO.getSupervisorDep())) {
                    ids = securityProjectDTO.getSupervisorDep().split(",");
                } else {
                    ids = null;
                }
                dumpSave(securityRoleEntity, ids);
                dumpBasicSave(basicStaffDataEntity, ids, "1");
                //项目下的监理关联的人信息
                securityRoleEntity.setDataType("2");
                securityRoleEntity.setTypeRole("7");
                basicStaffDataEntity.setDataType("2");
                basicStaffDataEntity.setDataRole("7");
                if (!StringUtil.isEmpty(securityProjectDTO.getSupervisortaff())) {
                    ids = securityProjectDTO.getSupervisortaff().split(",");
                } else {
                    ids = null;
                }
                dumpSave(securityRoleEntity, ids);
                dumpBasicSave(basicStaffDataEntity, ids, "2");
            }
        }
    }

    @Override
    public void deleteSecurityProject(SecurityProjectDTO securityProjectDTO) {
        boolean f = securityProjectRepository.deleteSecurityProject(securityProjectDTO.getProjectId(), "");
        if (f) {
            securityProjectRepository.deleteSecurityRole(securityProjectDTO.getProjectId(), "");
            //维护基础数据
            securityProjectRepository.deleteBasicProject(securityProjectDTO.getProjectId(),"");
            securityProjectRepository.deleteBasicRoles(securityProjectDTO.getProjectId(),"");
        }
    }

    @Override
    public void deleteSecurityArea(SecurityAreaDTO securityAreaDTO) {
        //删除区域
        boolean f = securityProjectRepository.deleteSecurityArea(securityAreaDTO.getAreaId(), "");
        if (f) {
            //维护基础数据
            securityProjectRepository.deleteBasicProject(securityAreaDTO.getAreaId(),"");
            securityProjectRepository.deleteBasicRoles(securityAreaDTO.getAreaId(),"");
            //删除该区域下权限关系
            securityProjectRepository.deleteSecurityRole(securityAreaDTO.getAreaId(), "");
            //删除该区域下所有项目
            boolean k = securityProjectRepository.deleteSecurityProjectByAreaId(securityAreaDTO.getAreaId());
            if (k) {
                List<SecurityProjectEntity> securityProjectEntityList = securityProjectRepository.getSecurityProjectList(securityAreaDTO.getAreaId(), "");
                if (securityProjectEntityList != null && securityProjectEntityList.size() > 0) {
                    String ids = "";
                    String projectIds = "";
                    for (SecurityProjectEntity securityProjectEntity : securityProjectEntityList) {
                        ids += "'" + securityProjectEntity.getProjectId() + "',";
                    }
                    projectIds = ids.substring(0, ids.length() - 1);
                    //删除该区域下所有项目下的权限关系
                    securityProjectRepository.deleteSecurityRole("", projectIds);
                    //维护基础数据
                    securityProjectRepository.deleteBasicRoles("",projectIds);
                }
            }
        }
    }

    @Override
    public void deleteSecurityGroup(SecurityGroupDTO securityGroupDTO) {
        //删除集团信息
        boolean f = securityProjectRepository.deleteSecurityGroup(securityGroupDTO.getGroupId());
        if (f) {
            //维护基础数据
            securityProjectRepository.deleteBasicProject(securityGroupDTO.getGroupId(),"");
            List<SecurityProjectEntity> securityProjectEntityList1 = securityProjectRepository.getPorjectByGroupId(securityGroupDTO.getGroupId());
            if (securityProjectEntityList1 != null && securityProjectEntityList1.size() > 0) {
                String ids = "";
                String projectIds = "";
                for (SecurityProjectEntity securityProjectEntity : securityProjectEntityList1) {
                    ids += "'" + securityProjectEntity.getProjectId() + "',";
                }
                projectIds = ids.substring(0, ids.length() - 1);
                securityProjectRepository.deleteBasicProject("",projectIds);
                securityProjectRepository.deleteBasicRoles("",projectIds);
            }
            securityProjectRepository.deleteBasicRoles(securityGroupDTO.getGroupId(),"");
            //删除集团下权限关系信息
            securityProjectRepository.deleteSecurityRole(securityGroupDTO.getGroupId(), "");
            //删除集团下所有区域信息
            boolean k = securityProjectRepository.deleteSecurityArea("", securityGroupDTO.getGroupId());
            if (k) {
                //删除该集团下所有区域权限对应的关系信息
                List<SecurityAreaEntity> securityAreaEntityList = securityProjectRepository.getSecurityAreaList(securityGroupDTO.getGroupId());
                if (securityAreaEntityList != null && securityAreaEntityList.size() > 0) {
                    String ids = "";
                    String areaIds = "";
                    for (SecurityAreaEntity securityAreaEntity : securityAreaEntityList) {
                        ids += "'" + securityAreaEntity.getAreaId() + "',";
                    }
                    areaIds = ids.substring(0, ids.length() - 1);
                    securityProjectRepository.deleteSecurityRole("", areaIds);
                    securityProjectRepository.deleteBasicRoles("",areaIds);
                    //删除该集团下所有区域下对应的项目
                    boolean l = securityProjectRepository.deleteSecurityProject("", securityGroupDTO.getGroupId());
                    if (l) {
                        //删除该集团下所有区域下对应项目的权限关系信息
                        List<SecurityProjectEntity> securityProjectEntityList = securityProjectRepository.getSecurityProjectList("", securityGroupDTO.getGroupId());
                        if (securityProjectEntityList != null && securityProjectEntityList.size() > 0) {
                            String iids = "";
                            String projectIds = "";
                            for (SecurityProjectEntity securityProjectEntity : securityProjectEntityList) {
                                iids += "'" + securityProjectEntity.getProjectId() + "',";
                            }
                            projectIds = iids.substring(0, iids.length() - 1);
                            securityProjectRepository.deleteSecurityRole("", projectIds);
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取项目公司下拉列表
     *
     * @return Map
     */
    @Override
    public Map<String, String> getProject() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("", "请选择项目公司");
        List<AuthAgencyEntity> agencyEntities = authAgencyRepository.getAllAgencyList();
        if (agencyEntities != null && !agencyEntities.isEmpty()) {
            for (AuthAgencyEntity pro : agencyEntities) {
                if(pro.getAgencyType().equals("100000002")) {
                    map.put(pro.getAgencyId(), pro.getAgencyName());
                }
            }
        }
        return map;

    }

    @Override
    public Map<String, String> getProjectByUser(UserInformationEntity userPropertystaffEntity) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("", "请选择项目公司");
        //1.查看是否给当前登录人授权过总部权限
        if(authAgencyRepository.checkAuthRoleByUserIdandtype("100000000",userPropertystaffEntity.getStaffId())){
            List<AuthAgencyEntity> agencyEntities = authAgencyRepository.getAllAgencyList();
            if (agencyEntities != null && !agencyEntities.isEmpty()) {
                for (AuthAgencyEntity pro : agencyEntities) {
                    if(pro.getAgencyType().equals("100000002")) {
                        map.put(pro.getAgencyId(), pro.getAgencyName());
                    }
                }
            }
        }else if(authAgencyRepository.checkAuthRoleByUserIdandtype("100000001",userPropertystaffEntity.getStaffId())){
            List<String> parentIdList=authAgencyRepository.checkAuthRoleListByUserIdandtype("100000001",userPropertystaffEntity.getStaffId());
            //根据条件删选
            if(parentIdList!=null && !parentIdList.isEmpty()){
                //区域直属项目层级
                List<AuthAgencyEntity> agencyEntities =authAgencyRepository.getAllAgencyListByParentId(parentIdList,null,"100000002");
                if (agencyEntities != null && !agencyEntities.isEmpty()) {
                    for (AuthAgencyEntity pro : agencyEntities) {
                            map.put(pro.getAgencyId(), pro.getAgencyName());
                    }
                }
                //区域 城市下项目
                List<AuthAgencyEntity> parEntities =authAgencyRepository.getAllAgencyListByParentId(parentIdList,null,"100000003");
                if(parEntities!=null && !parEntities.isEmpty()){
                    List<String> cityIarentId=new ArrayList<>();
                    for (AuthAgencyEntity pro : parEntities) {
                        cityIarentId.add(pro.getAgencyId());
                    }
                    if(cityIarentId!=null && !cityIarentId.isEmpty()){
                        List<AuthAgencyEntity> cityAgencyEntities =authAgencyRepository.getAllAgencyListByParentId(cityIarentId,null,"100000002");
                        if (cityAgencyEntities != null && !cityAgencyEntities.isEmpty()) {
                            for (AuthAgencyEntity pro : cityAgencyEntities) {
                                    map.put(pro.getAgencyId(), pro.getAgencyName());
                            }
                        }
                    }
                }
            }
        }else if(authAgencyRepository.checkAuthRoleByUserIdandtype("100000003",userPropertystaffEntity.getStaffId())){
            List<String> parentIdList=authAgencyRepository.checkAuthRoleListByUserIdandtype("100000003",userPropertystaffEntity.getStaffId());
            //根据条件删选
            if(parentIdList!=null && !parentIdList.isEmpty()){
                List<AuthAgencyEntity> agencyEntities =authAgencyRepository.getAllAgencyListByParentId(parentIdList,null,"100000002");
                if (agencyEntities != null && !agencyEntities.isEmpty()) {
                    for (AuthAgencyEntity pro : agencyEntities) {
                            map.put(pro.getAgencyId(), pro.getAgencyName());
                    }
                }
            }
        }else if(authAgencyRepository.checkAuthRoleByUserIdandtype("100000002",userPropertystaffEntity.getStaffId())){
            List<String> agencyIdList=authAgencyRepository.checkAuthRoleListByUserIdandtype("100000002",userPropertystaffEntity.getStaffId());
            //根据条件删选
            if(agencyIdList!=null && !agencyIdList.isEmpty()){
                List<AuthAgencyEntity> agencyEntities =authAgencyRepository.getAllAgencyListByParentId(null,agencyIdList,"100000002");
                if (agencyEntities != null && !agencyEntities.isEmpty()) {
                    for (AuthAgencyEntity pro : agencyEntities) {
                            map.put(pro.getAgencyId(), pro.getAgencyName());
                    }
                }
            }

        }
        return map;
    }

    @Override
    public Map<String, String> getESProjectByUser(UserInformationEntity userPropertystaffEntity) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("", "请选择项目公司");
        //1.查看是否给当前登录人授权过总部权限
        if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000000",userPropertystaffEntity.getStaffId())){
            List<AuthAgencyEntity> agencyEntities = authAgencyRepository.getAllAgencyList();
            if (agencyEntities != null && !agencyEntities.isEmpty()) {
                for (AuthAgencyEntity pro : agencyEntities) {
                    if(pro.getAgencyType().equals("100000002")) {
                        map.put(pro.getAgencyId(), pro.getAgencyName());
                    }
                }
            }
        }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000001",userPropertystaffEntity.getStaffId())){
            List<String> parentIdList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000001",userPropertystaffEntity.getStaffId());
            //根据条件删选
            if(parentIdList!=null && !parentIdList.isEmpty()){
                //区域直属项目层级
                List<AuthAgencyESEntity> agencyEntities =authAgencyRepository.getESAllAgencyListByParentId(parentIdList,null,"100000002");
                if (agencyEntities != null && !agencyEntities.isEmpty()) {
                    for (AuthAgencyESEntity pro : agencyEntities) {
                        map.put(pro.getAgencyId(), pro.getAgencyName());
                    }
                }
                //区域 城市下项目
                List<AuthAgencyESEntity> parEntities =authAgencyRepository.getESAllAgencyListByParentId(parentIdList,null,"100000003");
                if(parEntities!=null && !parEntities.isEmpty()){
                    List<String> cityIarentId=new ArrayList<>();
                    for (AuthAgencyESEntity pro : parEntities) {
                        cityIarentId.add(pro.getAgencyId());
                    }
                    if(cityIarentId!=null && !cityIarentId.isEmpty()){
                        List<AuthAgencyESEntity> cityAgencyEntities =authAgencyRepository.getESAllAgencyListByParentId(cityIarentId,null,"100000002");
                        if (cityAgencyEntities != null && !cityAgencyEntities.isEmpty()) {
                            for (AuthAgencyESEntity pro : cityAgencyEntities) {
                                map.put(pro.getAgencyId(), pro.getAgencyName());
                            }
                        }
                    }
                }
            }
        }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000003",userPropertystaffEntity.getStaffId())){
            List<String> parentIdList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000003",userPropertystaffEntity.getStaffId());
            //根据条件删选
            if(parentIdList!=null && !parentIdList.isEmpty()){
                List<AuthAgencyESEntity> agencyEntities =authAgencyRepository.getESAllAgencyListByParentId(parentIdList,null,"100000002");
                if (agencyEntities != null && !agencyEntities.isEmpty()) {
                    for (AuthAgencyESEntity pro : agencyEntities) {
                        map.put(pro.getAgencyId(), pro.getAgencyName());
                    }
                }
            }
        }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000002",userPropertystaffEntity.getStaffId())){
            List<String> agencyIdList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000002",userPropertystaffEntity.getStaffId());
            //根据条件删选
            if(agencyIdList!=null && !agencyIdList.isEmpty()){
                List<AuthAgencyESEntity> agencyEntities =authAgencyRepository.getESAllAgencyListByParentId(null,agencyIdList,"100000002");
                if (agencyEntities != null && !agencyEntities.isEmpty()) {
                    for (AuthAgencyESEntity pro : agencyEntities) {
                        map.put(pro.getAgencyId(), pro.getAgencyName());
                    }
                }
            }

        }
        return map;
    }

    @Override
    public Map<String, String> getESAllProjectByUser(UserInformationEntity userPropertystaffEntity) {
        Map<String, String> map = new LinkedHashMap<>();
        //1.查看是否给当前登录人授权过总部权限
        if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000000",userPropertystaffEntity.getStaffId())){
            List<AuthAgencyESEntity> agencyEntities = authAgencyRepository.getESAllAgencyList();
            if (agencyEntities != null && !agencyEntities.isEmpty()) {
                for (AuthAgencyESEntity pro : agencyEntities) {
                    if(pro.getAgencyType().equals("100000002")) {
                        map.put(pro.getAgencyId(), pro.getAgencyName());
                    }
                }
            }
        }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000001",userPropertystaffEntity.getStaffId())){
            List<String> parentIdList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000001",userPropertystaffEntity.getStaffId());
            //根据条件删选
            if(parentIdList!=null && !parentIdList.isEmpty()){
                //区域直属项目层级
                List<AuthAgencyESEntity> agencyEntities =authAgencyRepository.getESAllAgencyListByParentId(parentIdList,null,"100000002");
                if (agencyEntities != null && !agencyEntities.isEmpty()) {
                    for (AuthAgencyESEntity pro : agencyEntities) {
                        map.put(pro.getAgencyId(), pro.getAgencyName());
                    }
                }
                //区域 城市下项目
                List<AuthAgencyESEntity> parEntities =authAgencyRepository.getESAllAgencyListByParentId(parentIdList,null,"100000003");
                if(parEntities!=null && !parEntities.isEmpty()){
                    List<String> cityIarentId=new ArrayList<>();
                    for (AuthAgencyESEntity pro : parEntities) {
                        cityIarentId.add(pro.getAgencyId());
                    }
                    if(cityIarentId!=null && !cityIarentId.isEmpty()){
                        List<AuthAgencyESEntity> cityAgencyEntities =authAgencyRepository.getESAllAgencyListByParentId(cityIarentId,null,"100000002");
                        if (cityAgencyEntities != null && !cityAgencyEntities.isEmpty()) {
                            for (AuthAgencyESEntity pro : cityAgencyEntities) {
                                map.put(pro.getAgencyId(), pro.getAgencyName());
                            }
                        }
                    }
                }
            }
        }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000003",userPropertystaffEntity.getStaffId())){
            List<String> parentIdList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000003",userPropertystaffEntity.getStaffId());
            //根据条件删选
            if(parentIdList!=null && !parentIdList.isEmpty()){
                List<AuthAgencyESEntity> agencyEntities =authAgencyRepository.getESAllAgencyListByParentId(parentIdList,null,"100000002");
                if (agencyEntities != null && !agencyEntities.isEmpty()) {
                    for (AuthAgencyESEntity pro : agencyEntities) {
                        map.put(pro.getAgencyId(), pro.getAgencyName());
                    }
                }
            }
        }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000002",userPropertystaffEntity.getStaffId())){
            List<String> agencyIdList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000002",userPropertystaffEntity.getStaffId());
            //根据条件删选
            if(agencyIdList!=null && !agencyIdList.isEmpty()){
                List<AuthAgencyESEntity> agencyEntities =authAgencyRepository.getESAllAgencyListByParentId(null,agencyIdList,"100000002");
                if (agencyEntities != null && !agencyEntities.isEmpty()) {
                    for (AuthAgencyESEntity pro : agencyEntities) {
                        map.put(pro.getAgencyId(), pro.getAgencyName());
                    }
                }
            }

        }
        return map;
    }

    /**
     * 获取集团公司下拉列表
     *
     * @return Map
     */
    @Override
    public Map<String, String> getGroup() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("", "请选择集团公司");
        List<AuthAgencyEntity> list = authAgencyRepository.getAllAgencyList();
        if (list != null && !list.isEmpty()) {
            for (AuthAgencyEntity pro : list) {
                if(pro.getAgencyType().equals("100000000")) {
                    map.put(pro.getAgencyId(), pro.getAgencyName());
                }
            }
        }
        return map;
    }
    /**
     * 根据集团公司id获取区域公司下拉列表
     *
     * @return Map
     */
    @Override
    public Map<String, String> getAreaById(String id) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("", "请选择区域公司");
        List<AuthAgencyEntity> list = authAgencyRepository.getAgencyByAgencyId(id);
        if (list != null && !list.isEmpty()) {
            for (AuthAgencyEntity pro : list) {
                if(pro.getAgencyType().equals("100000001")) {
                    map.put(pro.getAgencyId(), pro.getAgencyName());
                }
            }
        }
        return map;
    }
    /**
     * 根据区域公司id获取项目公司下拉列表
     *
     * @return Map
     */
    @Override
    public Map<String, String> getProjectById(String id) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("", "请选择项目公司");
        List<AuthAgencyEntity> list = authAgencyRepository.getAgencyByAgencyId(id);
        if (list != null && !list.isEmpty()) {
            for (AuthAgencyEntity pro : list) {
                if(pro.getAgencyType().equals("100000002")) {
                    map.put(pro.getAgencyId(), pro.getAgencyName());
                }else if(pro.getAgencyType().equals("100000003")){
                    List<AuthAgencyEntity> authAgencyEntities = authAgencyRepository.getAgencyByAgencyId(pro.getAgencyId());
                    if(null != authAgencyEntities && authAgencyEntities.size()>0){
                        for (AuthAgencyEntity pro1 : authAgencyEntities) {
                            map.put(pro1.getAgencyId(), pro1.getAgencyName());
                        }
                    }
                }
            }
        }
        return map;
    }

    @Override
    public boolean checkGroupName(String groupName) {
        return securityProjectRepository.checkGroupName(groupName);
    }

    @Override
    public boolean checkAreaName(String areaName) {
        return securityProjectRepository.checkAreaName(areaName);
    }

    @Override
    public boolean checkProjectName(String projectName) {
        return securityProjectRepository.checkProjectName(projectName);
    }

    @Override
    public InspectionPlanDTO inspectionPlanDetails(String planId) {
        InspectionPlanDTO inspectionPlan = new InspectionPlanDTO();
        //根据idList查询主表信息
        Object[] obj=securityProjectRepository.getPlanById(planId);
        //根据idList查询图片信息
        List<Object[]> imagelist=securityProjectRepository.getPlanImageById(planId,"3");
        //根据idList查询检查单位信息
        List<Object[]> unitList=securityProjectRepository.getPlanProjectById(planId);
        List<String> unitIdList=new ArrayList<>();
        List<Object[]> unitImageList=new ArrayList<>();
        if(unitList!=null && unitList.size()>0){
            for(Object[] unitid : unitList){
                if(unitid[4] != null){
                    unitIdList.add(unitid[4].toString());
                }
            }
            unitImageList=securityProjectRepository.getPlanImageByIdList(unitIdList,"4");
        }
        if(obj != null){
            inspectionPlan.setId(obj[0] == null ? "" : obj[0].toString());//自增id
            inspectionPlan.setModifyDate(obj[1] == null ? "" : DateUtils.format((Date) obj[1]));//修改时间
            inspectionPlan.setAppId(obj[2] == null ? "" : obj[2].toString());//唯一校验  手机端生成id
            inspectionPlan.setPlanId(obj[3] == null ? "" : obj[3].toString());//检查计划id
            inspectionPlan.setPlanName(obj[4] == null ? "" : obj[4].toString());//检查计划名称
            inspectionPlan.setCreateId(obj[5] == null ? "" : obj[5].toString());//创建人id
            inspectionPlan.setCreateName(obj[6] == null ? "" : obj[6].toString());//创建人姓名
            inspectionPlan.setCreateDate(obj[7] == null ? "" : DateUtils.format((Date) obj[7]));//创建时间
            inspectionPlan.setCreateUnitId(obj[8] == null ? "" : obj[8].toString());//创建人所属单位id
            inspectionPlan.setCreateUnitName(obj[9] == null ? "" : obj[9].toString());//创建人所属单位
            inspectionPlan.setParticipant(obj[12] == null ? "" : obj[12].toString());//参加人员
            inspectionPlan.setState(obj[13] == null ? "" : obj[13].toString());//状态
            inspectionPlan.setScore(obj[14] == null ? "0" : obj[14].toString());//得分
            inspectionPlan.setType(obj[15] == null ? "" : obj[15].toString());
            inspectionPlan.setFraction(obj[16] == null ? "0" : obj[16].toString());

            if (imagelist != null && !imagelist.isEmpty()) {
                List<ImageDTO> imgJsonList = new ArrayList<ImageDTO>();
                for (Object[] img : imagelist) {
                    //判断外键是否相等计划
                    if (img[0].toString().equals(inspectionPlan.getPlanId())) {
                        ImageDTO imageDTO = new ImageDTO();
                        imageDTO.setId(img[1] == null ? "" : img[1].toString());
                        imageDTO.setImageUrl(img[2] == null ? "" : img[2].toString());
                        imageDTO.setType(img[3] == null ? "" : img[3].toString());
                        imageDTO.setBusinessId(inspectionPlan.getPlanId());
                        imgJsonList.add(imageDTO);
                    }
                }
                inspectionPlan.setImageList(imgJsonList);//检查计划签字图片
            }

            if (unitList != null && !unitList.isEmpty()) {
                //被检查单位
                List<InspectionUnitDTO> proUnitList = new ArrayList<InspectionUnitDTO>();
                //参加检查单位
                List<InspectionUnitDTO> proInUnitList = new ArrayList<InspectionUnitDTO>();
                for (Object[] unit : unitList) {
                    InspectionUnitDTO unitDTO = new InspectionUnitDTO();
                    unitDTO.setId(unit[1] == null ? "" : unit[1].toString());
                    unitDTO.setModifyDate(unit[2] == null ? "" : DateUtils.format((Date) unit[2]));//修改时间
                    unitDTO.setLevel(unit[3] == null ? "" : unit[3].toString());//级别 2.区域 3.项目公司
                    unitDTO.setUnitId(unit[4] == null ? "" : unit[4].toString());//检查单位id uuid
                    unitDTO.setPlanId(unit[5] == null ? "" : unit[5].toString());//检查计划id
                    unitDTO.setPlanName(unit[6] == null ? "" : unit[6].toString());//检查计划名字
                    unitDTO.setProjectId(unit[7] == null ? "" : unit[7].toString());//项目公司id
                    unitDTO.setProjectName(unit[8] == null ? "" : unit[8].toString());//项目公司名称
                    unitDTO.setState(unit[9] == null ? "" : unit[9].toString());//状态
                    unitDTO.setScore(unit[10] == null ? "0" : unit[10].toString());
                    unitDTO.setPlanStart(unit[11] == null ? "" : DateUtils.format((Date) unit[11]));
                    unitDTO.setPlanEnd(unit[12] == null ? "" : DateUtils.format((Date) unit[12]));
                    inspectionPlan.setPlanStart(StringUtils.substringBefore(unit[11] == null ? "" : DateUtils.format((Date) unit[11])," "));//检查计划开始时间
                    inspectionPlan.setPlanEnd(StringUtils.substringBefore(unit[12] == null ? "" : DateUtils.format((Date) unit[12])," "));//检查计划结束时间
                    unitDTO.setParticipant(unit[13] == null ? "" : unit[13].toString());
                    unitDTO.setFraction(unit[14] == null ? "0" : unit[14].toString());
                    unitDTO.setRectifyState(unit[15] == null ? "" : unit[15].toString());
                    if (unitImageList != null && !unitImageList.isEmpty()) {
                        List<ImageDTO> unitImgList = new ArrayList<ImageDTO>();
                        for (Object[] unitImg : unitImageList) {
                            //判断外键是否相等检查单位
                            if (unitImg[0].toString().equals(unitDTO.getUnitId())) {
                                ImageDTO imageDTO = new ImageDTO();
                                imageDTO.setId(unitImg[1] == null ? "" : unitImg[1].toString());
                                imageDTO.setImageUrl(unitImg[2] == null ? "" : unitImg[2].toString());
                                imageDTO.setType(unitImg[3] == null ? "" : unitImg[3].toString());
                                imageDTO.setBusinessId(inspectionPlan.getPlanId());
                                unitImgList.add(imageDTO);
                            }
                        }
                        unitDTO.setImageList(unitImgList);//检查计划签字图片
                    }
                    if("2".equals(unit[0].toString())) {
                        //TYPE==2 参加检查单位  没有检查项
                        proInUnitList.add(unitDTO);
                    }else if("3".equals(unit[0].toString())){
                        //TYPE==3 被检查单位  有检查项
                        proUnitList.add(unitDTO);
                    }
                }
                inspectionPlan.setUnitList(proUnitList);//参加检查单位信息
                inspectionPlan.setInUnitList(proInUnitList);//被检查单位信息
            }
        }
        return inspectionPlan;
    }

    @Override
    public Map<String, String> getAgencyByTypeAndUser(UserInformationEntity userEntity, String type,String pairentId) {
        Map<String, String> map = new LinkedHashMap<>();
//        map.put("", "请选择项目公司");
        if("100000000".equals(type)){
            map.put("", "请选择集团公司");
            if(authAgencyRepository.checkAuthRoleByUserIdandtype("100000000",userEntity.getStaffId())
                    ||authAgencyRepository.checkAuthRoleByUserIdandtype("100000001",userEntity.getStaffId())
                    ||authAgencyRepository.checkAuthRoleByUserIdandtype("100000002",userEntity.getStaffId())
                    ||authAgencyRepository.checkAuthRoleByUserIdandtype("100000003",userEntity.getStaffId())){
                //集团下拉框  只要存在绑定关系就存在集团信息
                List<AuthAgencyEntity> list = authAgencyRepository.getAllAgencyList();
                if (list != null && !list.isEmpty()) {
                    for (AuthAgencyEntity pro : list) {
                        if(pro.getAgencyType().equals("100000000")) {
                            map.put(pro.getAgencyId(), pro.getAgencyName());
                        }
                    }
                }
            }
        }else if("100000001".equals(type)){
            map.put("", "请选择区域公司");
            //该角色是否绑定集团信息
            if(authAgencyRepository.checkAuthRoleByUserIdandtype("100000000",userEntity.getStaffId())){
                List<AuthAgencyEntity> list = authAgencyRepository.getAgencyByAgencyId(pairentId);
                if (list != null && !list.isEmpty()) {
                    for (AuthAgencyEntity pro : list) {
                        if(pro.getAgencyType().equals("100000001")) {
                            map.put(pro.getAgencyId(), pro.getAgencyName());
                        }
                    }
                }
            }else if(authAgencyRepository.checkAuthRoleByUserIdandtype("100000001",userEntity.getStaffId())){
                //绑定的区域信息
                List<String> agencyIdList=authAgencyRepository.checkAuthRoleListByUserIdandtype("100000001",userEntity.getStaffId());
                if(agencyIdList!=null && !agencyIdList.isEmpty()){
                    List<AuthAgencyEntity> list =authAgencyRepository.getAllAgencyListByParentId(null,agencyIdList,"100000001");
                    if (list != null && !list.isEmpty()) {
                        for (AuthAgencyEntity pro : list) {
                            map.put(pro.getAgencyId(), pro.getAgencyName());
                        }
                    }
                }
            }else if(authAgencyRepository.checkAuthRoleByUserIdandtype("100000003",userEntity.getStaffId())){
                //该用户绑定的城市
                List<String> agencyIdList=authAgencyRepository.checkAuthRoleListByUserIdandtype("100000003",userEntity.getStaffId());
                if(agencyIdList!=null && !agencyIdList.isEmpty()){
                    List<String> cityParInd=new ArrayList<>();//城市的上极id
                    List<AuthAgencyEntity> list =authAgencyRepository.getAllAgencyListByParentId(null,agencyIdList,"100000003");
                    if (list != null && !list.isEmpty()) {
                        for (AuthAgencyEntity pro : list) {
                            cityParInd.add(pro.getParentId());
                        }
                        List<AuthAgencyEntity> cithList =authAgencyRepository.getAllAgencyListByParentId(null,cityParInd,"100000001");
                        if (cithList != null && !cithList.isEmpty()) {
                            for (AuthAgencyEntity city : cithList) {
                                map.put(city.getAgencyId(), city.getAgencyName());
                            }
                        }
                    }
                }
            }else if(authAgencyRepository.checkAuthRoleByUserIdandtype("100000002",userEntity.getStaffId())){
                List<String> agencyIdList=authAgencyRepository.checkAuthRoleListByUserIdandtype("100000002",userEntity.getStaffId());
                if(agencyIdList!=null && !agencyIdList.isEmpty()){
                    List<String> projectParInd=new ArrayList<>();//项目的上一级别d
                    List<AuthAgencyEntity> list =authAgencyRepository.getAllAgencyListByParentId(null,agencyIdList,"100000002");
                    if(list!=null && !list.isEmpty()){
                        for (AuthAgencyEntity pro : list) {
                            projectParInd.add(pro.getParentId());
                        }
                        //1.项目上一级别直接为区域+项目上一级别为城市的上一级别id
                        List<AuthAgencyEntity> cityList =authAgencyRepository.getAllAgencyListByParentId(null,projectParInd,"100000003");
                        if(cityList!=null && !cityList.isEmpty()){
                            for (AuthAgencyEntity pro : cityList) {
                                projectParInd.add(pro.getParentId());
                            }
                        }

                        List<AuthAgencyEntity> cithList =authAgencyRepository.getAllAgencyListByParentId(null,projectParInd,"100000001");
                        if (cithList != null && !cithList.isEmpty()) {
                            for (AuthAgencyEntity city : cithList) {
                                map.put(city.getAgencyId(), city.getAgencyName());
                            }
                        }
                    }
                }
            }
        }else if("100000002".equals(type)){
            map.put("", "请选择项目公司");
            if(authAgencyRepository.checkAuthRoleByUserIdandtype("100000000",userEntity.getStaffId())
                    || authAgencyRepository.checkAuthRoleByUserIdandtype("100000001",userEntity.getStaffId())){
                List<AuthAgencyEntity> list = authAgencyRepository.getAgencyByAgencyId(pairentId);
                if (list != null && !list.isEmpty()) {
                    for (AuthAgencyEntity pro : list) {
                        if(pro.getAgencyType().equals("100000002")) {
                            map.put(pro.getAgencyId(), pro.getAgencyName());
                        }else if(pro.getAgencyType().equals("100000003")){
                            List<AuthAgencyEntity> authAgencyEntities = authAgencyRepository.getAgencyByAgencyId(pro.getAgencyId());
                            if(null != authAgencyEntities && authAgencyEntities.size()>0){
                                for (AuthAgencyEntity pro1 : authAgencyEntities) {
                                    map.put(pro1.getAgencyId(), pro1.getAgencyName());
                                }
                            }
                        }
                    }
                }
            }else if(authAgencyRepository.checkAuthRoleByUserIdandtype("100000003",userEntity.getStaffId())){
                List<String> agencyIdList=authAgencyRepository.checkAuthRoleListByUserIdandtype("100000003",userEntity.getStaffId());
                List<String> parentIdList=new ArrayList<>();
                parentIdList.add(pairentId);
                if(agencyIdList!=null && !agencyIdList.isEmpty()){
                    List<AuthAgencyEntity> list =authAgencyRepository.getAllAgencyListByParentId(parentIdList,agencyIdList,"100000002");
                    if (list != null && !list.isEmpty()) {
                        for (AuthAgencyEntity project : list) {
                            map.put(project.getAgencyId(), project.getAgencyName());
                        }
                    }
                }
            }else if(authAgencyRepository.checkAuthRoleByUserIdandtype("100000002",userEntity.getStaffId())){
                List<String> parentIdList=new ArrayList<>();
                parentIdList.add(pairentId);
                List<String> agencyIdList=authAgencyRepository.checkAuthRoleListByUserIdandtype("100000002",userEntity.getStaffId());
                //根据区域查询城市
                List<AuthAgencyEntity> list =authAgencyRepository.getAllAgencyListByParentId(parentIdList,null,"100000003");
                if(list!=null && !list.isEmpty()){
                    for (AuthAgencyEntity city : list) {
                        parentIdList.add(city.getAgencyId());
                    }
                }
                List<AuthAgencyEntity> projectList =authAgencyRepository.getAllAgencyListByParentId(parentIdList,agencyIdList,"100000002");
                if (projectList != null && !projectList.isEmpty()) {
                    for (AuthAgencyEntity project : projectList) {
                        map.put(project.getAgencyId(), project.getAgencyName());
                    }
                }
            }
        }
        return map;
    }

    @Override
    public Map<String, String>  getESAgencyByTypeAndUser(UserInformationEntity userEntity, String type, String pairentId) {
        Map<String, String> map = new LinkedHashMap<>();
        if("100000000".equals(type)){
            map.put("", "请选择集团");
            if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000000",userEntity.getStaffId())
                    ||authAgencyRepository.checkESAuthRoleByUserIdandtype("100000001",userEntity.getStaffId())
                    ||authAgencyRepository.checkESAuthRoleByUserIdandtype("100000002",userEntity.getStaffId())
                    ||authAgencyRepository.checkESAuthRoleByUserIdandtype("100000003",userEntity.getStaffId())){
                //集团下拉框  只要存在绑定关系就存在集团信息
                List<AuthAgencyESEntity> list = authAgencyRepository.getESAllAgencyList();
                if (list != null && !list.isEmpty()) {
                    for (AuthAgencyESEntity pro : list) {
                        if(pro.getAgencyType().equals("100000000")) {
                            map.put(pro.getAgencyId(), pro.getAgencyName());
                        }
                    }
                }
            }
        }else if("100000001".equals(type)){
            map.put("", "请选择区域");
            //该角色是否绑定集团信息
            if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000000",userEntity.getStaffId())){
                List<AuthAgencyESEntity> list = authAgencyRepository.getESAgencyByAgencyId(pairentId);
                if (list != null && !list.isEmpty()) {
                    for (AuthAgencyESEntity pro : list) {
                        if(pro.getAgencyType().equals("100000001")) {
                            map.put(pro.getAgencyId(), pro.getAgencyName());
                        }
                    }
                }
            }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000001",userEntity.getStaffId())){
                //绑定的区域信息
                List<String> agencyIdList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000001",userEntity.getStaffId());
                if(agencyIdList!=null && !agencyIdList.isEmpty()){
                    List<AuthAgencyESEntity> list =authAgencyRepository.getESAllAgencyListByParentId(null,agencyIdList,"100000001");
                    if (list != null && !list.isEmpty()) {
                        for (AuthAgencyESEntity pro : list) {
                            map.put(pro.getAgencyId(), pro.getAgencyName());
                        }
                    }
                }
            }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000003",userEntity.getStaffId())){
                //该用户绑定的城市
                List<String> agencyIdList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000003",userEntity.getStaffId());
                if(agencyIdList!=null && !agencyIdList.isEmpty()){
                    List<String> cityParInd=new ArrayList<>();//城市的上极id
                    List<AuthAgencyESEntity> list =authAgencyRepository.getESAllAgencyListByParentId(null,agencyIdList,"100000003");
                    if (list != null && !list.isEmpty()) {
                        for (AuthAgencyESEntity pro : list) {
                            cityParInd.add(pro.getParentId());
                        }
                        List<AuthAgencyESEntity> cithList =authAgencyRepository.getESAllAgencyListByParentId(null,cityParInd,"100000001");
                        if (cithList != null && !cithList.isEmpty()) {
                            for (AuthAgencyESEntity city : cithList) {
                                map.put(city.getAgencyId(), city.getAgencyName());
                            }
                        }
                    }
                }
            }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000002",userEntity.getStaffId())){
                List<String> agencyIdList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000002",userEntity.getStaffId());
                if(agencyIdList!=null && !agencyIdList.isEmpty()){
                    List<String> projectParInd=new ArrayList<>();//项目的上一级别d
                    List<AuthAgencyESEntity> list =authAgencyRepository.getESAllAgencyListByParentId(null,agencyIdList,"100000002");
                    if(list!=null && !list.isEmpty()){
                        for (AuthAgencyESEntity pro : list) {
                            projectParInd.add(pro.getParentId());
                        }
                        //1.项目上一级别直接为区域+项目上一级别为城市的上一级别id
                        List<AuthAgencyESEntity> cityList =authAgencyRepository.getESAllAgencyListByParentId(null,projectParInd,"100000003");
                        if(cityList!=null && !cityList.isEmpty()){
                            for (AuthAgencyESEntity pro : cityList) {
                                projectParInd.add(pro.getParentId());
                            }
                        }
                        List<AuthAgencyESEntity> cithList =authAgencyRepository.getESAllAgencyListByParentId(null,projectParInd,"100000001");
                        if (cithList != null && !cithList.isEmpty()) {
                            for (AuthAgencyESEntity city : cithList) {
                                map.put(city.getAgencyId(), city.getAgencyName());
                            }
                        }
                    }
                }
            }
        }else if("100000003".equals(type)){
            map.put("", "请选择城市");
            //如果绑定集团和区域
            if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000000",userEntity.getStaffId())
                    || authAgencyRepository.checkESAuthRoleByUserIdandtype("100000001",userEntity.getStaffId())){
                List<AuthAgencyESEntity> list = authAgencyRepository.getESAgencyByAgencyId(pairentId);
                if (list != null && !list.isEmpty()) {
                    for (AuthAgencyESEntity pro : list) {
                        if(pro.getAgencyType().equals("100000003")) {
                            map.put(pro.getAgencyId(), pro.getAgencyName());
                        }
                    }
                }
            }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000003",userEntity.getStaffId())){
                //绑定的城市
                List<String> parentIdList=new ArrayList<>();
                parentIdList.add(pairentId);//上一级别id
                List<String> agencyIdList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000003",userEntity.getStaffId());
                if(agencyIdList!=null && !agencyIdList.isEmpty()){
                    List<AuthAgencyESEntity> cityList =authAgencyRepository.getESAllAgencyListByParentId(parentIdList,agencyIdList,"100000003");
                    if(cityList!=null && !cityList.isEmpty()){
                        for (AuthAgencyESEntity pro : cityList) {
                            map.put(pro.getAgencyId(), pro.getAgencyName());
                        }
                    }
                }
            }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000002",userEntity.getStaffId())){
                //绑定的项目
                List<String> parentIdList=new ArrayList<>();
                parentIdList.add(pairentId);//上一级别id
                //查询醒目的上一级id 城市
                List<String> agencyIdList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000002",userEntity.getStaffId());
                if(agencyIdList!=null && !agencyIdList.isEmpty()){
                    List<AuthAgencyESEntity> agencyList =authAgencyRepository.getESAllAgencyListByParentId(null,agencyIdList,"100000002");
                    if(agencyList!=null && !agencyList.isEmpty()){
                        List<String> cityList=new ArrayList<>();
                        for (AuthAgencyESEntity pro : agencyList) {
                            cityList.add(pro.getParentId());
//                            map.put(pro.getAgencyId(), pro.getAgencyName());
                        }
                        List<AuthAgencyESEntity> city1List =authAgencyRepository.getESAllAgencyListByParentId(parentIdList,cityList,"100000003");
                        if(city1List!=null && !city1List.isEmpty()){
                            for (AuthAgencyESEntity pro : city1List) {
                                map.put(pro.getAgencyId(), pro.getAgencyName());
                            }
                        }
                    }
                }
            }

        }else if("100000002".equals(type)){
            map.put("", "请选择项目");
            if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000000",userEntity.getStaffId())
                    || authAgencyRepository.checkESAuthRoleByUserIdandtype("100000001",userEntity.getStaffId())
                    || authAgencyRepository.checkESAuthRoleByUserIdandtype("100000003",userEntity.getStaffId())){
                List<AuthAgencyESEntity> list = authAgencyRepository.getESAgencyByAgencyId(pairentId);
                if (list != null && !list.isEmpty()) {
                    for (AuthAgencyESEntity pro : list) {
                        if(pro.getAgencyType().equals("100000002")) {
                            map.put(pro.getAgencyId(), pro.getAgencyName());
                        }
                    }
                }
            }else if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000002",userEntity.getStaffId())){
                List<String> parentIdList=new ArrayList<>();
                parentIdList.add(pairentId);
                List<String> agencyIdList=authAgencyRepository.checkESAuthRoleListByUserIdandtype("100000002",userEntity.getStaffId());
                if(agencyIdList!=null && !agencyIdList.isEmpty()){
                    List<AuthAgencyESEntity> projectList =authAgencyRepository.getESAllAgencyListByParentId(parentIdList,agencyIdList,"100000002");
                    if (projectList != null && !projectList.isEmpty()) {
                        for (AuthAgencyESEntity project : projectList) {
                            map.put(project.getAgencyId(), project.getAgencyName());
                        }
                    }
                }
            }
        }
        return map;
    }

    @Override
    public String getESAgencyByUser(UserInformationEntity userEntity){
        if(authAgencyRepository.checkESAuthRoleByUserIdandtype("100000000",userEntity.getStaffId())
                ||authAgencyRepository.checkESAuthRoleByUserIdandtype("100000001",userEntity.getStaffId())
                ||authAgencyRepository.checkESAuthRoleByUserIdandtype("100000002",userEntity.getStaffId())
                ||authAgencyRepository.checkESAuthRoleByUserIdandtype("100000003",userEntity.getStaffId())){
            //集团下拉框  只要存在绑定关系就存在集团信息
            List<AuthAgencyESEntity> list = authAgencyRepository.getESAllAgencyList();
            if (list != null && !list.isEmpty()) {
                for (AuthAgencyESEntity pro : list) {
                    if(pro.getAgencyType().equals("100000000")) {
                        return pro.getAgencyId();
                    }
                }
            }
        }
        return null;
    }

    public Map<String, String> getQCAgencyByTypeAndUser(UserInformationEntity userEntity, String type, String pairentId) {
        Map<String, String> map = new LinkedHashMap<>();
        if("100000000".equals(type)){
            map.put("", "请选择集团");
            if(authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000000",userEntity.getStaffId())
                    ||authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000001",userEntity.getStaffId())
                    ||authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000002",userEntity.getStaffId())
                    ||authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000003",userEntity.getStaffId())){
                //集团下拉框  只要存在绑定关系就存在集团信息
                List<AuthAgencyQCEntity> list = authAgencyRepository.getQCAllAgencyList();
                if (list != null && !list.isEmpty()) {
                    for (AuthAgencyQCEntity pro : list) {
                        if(pro.getAgencyType().equals("100000000")) {
                            map.put(pro.getAgencyId(), pro.getAgencyName());
                        }
                    }
                }
            }
        }else if("100000001".equals(type)){
            map.put("", "请选择区域");
            //该角色是否绑定集团信息
            if(authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000000",userEntity.getStaffId())){
                List<AuthAgencyQCEntity> list = authAgencyRepository.getQCAgencyByAgencyId(pairentId);
                if (list != null && !list.isEmpty()) {
                    for (AuthAgencyQCEntity pro : list) {
                        if(pro.getAgencyType().equals("100000001")) {
                            map.put(pro.getAgencyId(), pro.getAgencyName());
                        }
                    }
                }
            }else if(authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000001",userEntity.getStaffId())){
                //绑定的区域信息
                List<String> agencyIdList=authAgencyRepository.checkQCAuthRoleListByUserIdandtype("100000001",userEntity.getStaffId());
                if(agencyIdList!=null && !agencyIdList.isEmpty()){
                    List<AuthAgencyQCEntity> list =authAgencyRepository.getQCAllAgencyListByParentId(null,agencyIdList,"100000001");
                    if (list != null && !list.isEmpty()) {
                        for (AuthAgencyQCEntity pro : list) {
                            map.put(pro.getAgencyId(), pro.getAgencyName());
                        }
                    }
                }
            }else if(authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000003",userEntity.getStaffId())){
                //该用户绑定的城市
                List<String> agencyIdList=authAgencyRepository.checkQCAuthRoleListByUserIdandtype("100000003",userEntity.getStaffId());
                if(agencyIdList!=null && !agencyIdList.isEmpty()){
                    List<String> cityParInd=new ArrayList<>();//城市的上极id
                    List<AuthAgencyQCEntity> list =authAgencyRepository.getQCAllAgencyListByParentId(null,agencyIdList,"100000003");
                    if (list != null && !list.isEmpty()) {
                        for (AuthAgencyQCEntity pro : list) {
                            cityParInd.add(pro.getParentId());
                        }
                        List<AuthAgencyQCEntity> cithList =authAgencyRepository.getQCAllAgencyListByParentId(null,cityParInd,"100000001");
                        if (cithList != null && !cithList.isEmpty()) {
                            for (AuthAgencyQCEntity city : cithList) {
                                map.put(city.getAgencyId(), city.getAgencyName());
                            }
                        }
                    }
                }
            }else if(authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000002",userEntity.getStaffId())){
                List<String> agencyIdList=authAgencyRepository.checkQCAuthRoleListByUserIdandtype("100000002",userEntity.getStaffId());
                if(agencyIdList!=null && !agencyIdList.isEmpty()){
                    List<String> projectParInd=new ArrayList<>();//项目的上一级别d
                    List<AuthAgencyQCEntity> list =authAgencyRepository.getQCAllAgencyListByParentId(null,agencyIdList,"100000002");
                    if(list!=null && !list.isEmpty()){
                        for (AuthAgencyQCEntity pro : list) {
                            projectParInd.add(pro.getParentId());
                        }
                        //1.项目上一级别直接为区域+项目上一级别为城市的上一级别id
                        List<AuthAgencyQCEntity> cityList =authAgencyRepository.getQCAllAgencyListByParentId(null,projectParInd,"100000003");
                        if(cityList!=null && !cityList.isEmpty()){
                            for (AuthAgencyQCEntity pro : cityList) {
                                projectParInd.add(pro.getParentId());
                            }
                        }
                        List<AuthAgencyQCEntity> cithList =authAgencyRepository.getQCAllAgencyListByParentId(null,projectParInd,"100000001");
                        if (cithList != null && !cithList.isEmpty()) {
                            for (AuthAgencyQCEntity city : cithList) {
                                map.put(city.getAgencyId(), city.getAgencyName());
                            }
                        }
                    }
                }
            }
        }else if("100000003".equals(type)){
            map.put("", "请选择城市");
            //如果绑定集团和区域
            if(authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000000",userEntity.getStaffId())
                    || authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000001",userEntity.getStaffId())){
                List<AuthAgencyQCEntity> list = authAgencyRepository.getQCAgencyByAgencyId(pairentId);
                if (list != null && !list.isEmpty()) {
                    for (AuthAgencyQCEntity pro : list) {
                        if(pro.getAgencyType().equals("100000003")) {
                            map.put(pro.getAgencyId(), pro.getAgencyName());
                        }
                    }
                }
            }else if(authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000003",userEntity.getStaffId())){
                //绑定的城市
                List<String> parentIdList=new ArrayList<>();
                parentIdList.add(pairentId);//上一级别id
                List<String> agencyIdList=authAgencyRepository.checkQCAuthRoleListByUserIdandtype("100000003",userEntity.getStaffId());
                if(agencyIdList!=null && !agencyIdList.isEmpty()){
                    List<AuthAgencyQCEntity> cityList =authAgencyRepository.getQCAllAgencyListByParentId(parentIdList,agencyIdList,"100000003");
                    if(cityList!=null && !cityList.isEmpty()){
                        for (AuthAgencyQCEntity pro : cityList) {
                            map.put(pro.getAgencyId(), pro.getAgencyName());
                        }
                    }
                }
            }else if(authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000002",userEntity.getStaffId())){
                //绑定的项目
                List<String> parentIdList=new ArrayList<>();
                parentIdList.add(pairentId);//上一级别id
                //查询醒目的上一级id 城市
                List<String> agencyIdList=authAgencyRepository.checkQCAuthRoleListByUserIdandtype("100000002",userEntity.getStaffId());
                if(agencyIdList!=null && !agencyIdList.isEmpty()){
                    List<AuthAgencyQCEntity> agencyList =authAgencyRepository.getQCAllAgencyListByParentId(null,agencyIdList,"100000002");
                    if(agencyList!=null && !agencyList.isEmpty()){
                        List<String> cityList=new ArrayList<>();
                        for (AuthAgencyQCEntity pro : agencyList) {
                            cityList.add(pro.getParentId());
//                            map.put(pro.getAgencyId(), pro.getAgencyName());
                        }
                        List<AuthAgencyQCEntity> city1List =authAgencyRepository.getQCAllAgencyListByParentId(parentIdList,cityList,"100000003");
                        if(city1List!=null && !city1List.isEmpty()){
                            for (AuthAgencyQCEntity pro : city1List) {
                                map.put(pro.getAgencyId(), pro.getAgencyName());
                            }
                        }
                    }
                }
            }

        }else if("100000002".equals(type)){
            map.put("", "请选择项目");
            if(authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000000",userEntity.getStaffId())
                    || authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000001",userEntity.getStaffId())
                    || authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000003",userEntity.getStaffId())){
                List<AuthAgencyQCEntity> list = authAgencyRepository.getQCAgencyByAgencyId(pairentId);
                if (list != null && !list.isEmpty()) {
                    for (AuthAgencyQCEntity pro : list) {
                        if(pro.getAgencyType().equals("100000002")) {
                            map.put(pro.getAgencyId(), pro.getAgencyName());
                        }
                    }
                }
            }else if(authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000002",userEntity.getStaffId())){
                List<String> parentIdList=new ArrayList<>();
                parentIdList.add(pairentId);
                List<String> agencyIdList=authAgencyRepository.checkQCAuthRoleListByUserIdandtype("100000002",userEntity.getStaffId());
                if(agencyIdList!=null && !agencyIdList.isEmpty()){
                    List<AuthAgencyQCEntity> projectList =authAgencyRepository.getQCAllAgencyListByParentId(parentIdList,agencyIdList,"100000002");
                    if (projectList != null && !projectList.isEmpty()) {
                        for (AuthAgencyQCEntity project : projectList) {
                            map.put(project.getAgencyId(), project.getAgencyName());
                        }
                    }
                }
            }
        }
        return map;
    }

    /**
     * 编辑权限
     *
     * @param securityRoleEntity
     * @param ids
     */
    public void dumpSave(SecurityRoleEntity securityRoleEntity, String[] ids) {
        List<String> compairDTO1 = new ArrayList<String>();
        List<String> compairDTO2 = new ArrayList<String>();
        List<String> compairDTO3 = new ArrayList<String>();
        Iterator<String> it1;
        Iterator<String> it2;
        List<SecurityRoleEntity> securityRoleEntities;
        //查出数据库中已存在的数据
        securityRoleEntities = securityProjectRepository.getStaffEmploys(securityRoleEntity.getTypeId(), securityRoleEntity.getDataType(), securityRoleEntity.getTypeRole());
        if (securityRoleEntities != null && securityRoleEntities.size() > 0) {
            for (SecurityRoleEntity securityRoleEntity1 : securityRoleEntities) {
                //将数据库中的数据存放于compairDTO1
                compairDTO1.add(securityRoleEntity1.getDataId());
            }
        }
        if (ids != null) {
            for (int i = 0; i < ids.length; i++) {
                compairDTO2.add(ids[i]);      //将页面传来的数据存放于compairDTO2
            }
        }
        compairDTO3.addAll(compairDTO1);
        //比较后 为待删除的数据
        compairDTO1.removeAll(compairDTO2);
        it1 = compairDTO1.iterator();
        while (it1.hasNext()) {
            securityRoleEntity.setDataId(it1.next());
            //删除权限关联数据
            securityProjectRepository.deleteGroupRole(securityRoleEntity);
        }
        compairDTO2.removeAll(compairDTO3);//比较后 待新增的数据
        it2 = compairDTO2.iterator();
        while (it2.hasNext()) {
            securityRoleEntity.setDataId(it2.next());
            //保存最新关系
            securityProjectRepository.dumpAddGroupRole(securityRoleEntity);
        }
        compairDTO1.clear();
        compairDTO2.clear();
        compairDTO3.clear();
    }

    /**
     * 编辑基础数据权限
     *
     * @param basicStaffDataEntity
     * @param ids
     * @param type                 1:部门；2：人员
     */
    public void dumpBasicSave(BasicStaffDataEntity basicStaffDataEntity, String[] ids, String type) {
        List<String> compairDTO1 = new ArrayList<String>();
        List<String> compairDTO2 = new ArrayList<String>();
        List<String> compairDTO3 = new ArrayList<String>();
        Iterator<String> it1;
        Iterator<String> it2;
        List<BasicStaffDataEntity> basicStaffDataEntities;
        //查出数据库中已存在的数据
        basicStaffDataEntities = securityProjectRepository.getBasicStaffEmploys(basicStaffDataEntity.getDataId(), basicStaffDataEntity.getDataRole(), basicStaffDataEntity.getDataType());
        if (basicStaffDataEntities != null && basicStaffDataEntities.size() > 0) {
            for (BasicStaffDataEntity basicStaffDataEntity1 : basicStaffDataEntities) {
                //将数据库中的数据存放于compairDTO1
                compairDTO1.add(basicStaffDataEntity1.getStaffId());
            }
        }
        if (type.equals("1")) {
            if (ids != null) {
                String agency = "";
                String agencyId = "";
                for (int i = 0; i < ids.length; i++) {
                    agency += "'" + ids[i] + "',";
                }
                agencyId = agency.substring(0, agency.length() - 1);
                List<Object[]> list = userAgencyRepository.getStaffInfoByAgencyId(agencyId);
                if (list != null && list.size() > 0) {
                    for (Object[] obj : list) {
                        compairDTO2.add(obj[0].toString());
                    }
                }
            }
        } else {
            if (ids != null) {
                for (int i = 0; i < ids.length; i++) {
                    compairDTO2.add(ids[i]);      //将页面传来的数据存放于compairDTO2
                }
            }
        }
        compairDTO3.addAll(compairDTO1);
        //比较后 为待删除的数据
        compairDTO1.removeAll(compairDTO2);
        it1 = compairDTO1.iterator();
        while (it1.hasNext()) {
            basicStaffDataEntity.setStaffId(it1.next());
            //删除权限关联数据
            securityProjectRepository.deleteBasicRole(basicStaffDataEntity);
        }
        compairDTO2.removeAll(compairDTO3);//比较后 待新增的数据
        it2 = compairDTO2.iterator();
        while (it2.hasNext()) {
            basicStaffDataEntity.setStaffId(it2.next());
            //保存最新关系
            securityProjectRepository.dumpAddBasicRole(basicStaffDataEntity);
        }
        compairDTO1.clear();
        compairDTO2.clear();
        compairDTO3.clear();
    }
}
