package com.maxrocky.vesta.application.baseData.impl;

import com.maxrocky.vesta.application.baseData.JsonDTO.BaseDataDTO;
import com.maxrocky.vesta.application.baseData.JsonDTO.BaseProjectDTO;
import com.maxrocky.vesta.application.baseData.JsonDTO.ProjectDataDTO;
import com.maxrocky.vesta.application.baseData.JsonDTO.ProjectPeopleDTO;
import com.maxrocky.vesta.application.baseData.adminDTO.*;
import com.maxrocky.vesta.application.baseData.inf.ProjectProjectService;
import com.maxrocky.vesta.application.dto.adminDTO.batch.AuthCheckNameDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.baseData.model.*;
import com.maxrocky.vesta.domain.baseData.repository.*;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by chen on 2016/10/20.
 */

@Service
public class ProjectProjectServiceImpl implements ProjectProjectService {

    @Autowired
    ProjectProjectRepository projectProjectRepository;
    @Autowired
    StaffEmployRepository staffEmployRepository;
    @Autowired
    ProjectPeopleRepository projectPeopleRepository;
    @Autowired
    AgencyRepository agencyRepository;
    @Autowired
    ProjectCityRepository projectCityRepository;
    @Autowired
    ProjectOperationRepository projectOperationRepository;
    @Autowired
    AppRoleSetRepository appRoleSetRepository;
    @Autowired
    private RoleDataRepository roleDataRepository;
    @Autowired
    private RoleAnthorityRepository roleAnthorityRepository;
    @Resource
    private RoleRolesetRepository roleRolesetRepository;
    @Resource
    private RoleRolesetmapRepository roleRolesetmapRepository;

    /**
     * 新增工程项目
     *
     * @param projectProjectDTO
     */
    @Override
    public void addProjectProject(ProjectProjectDTO projectProjectDTO, UserInformationEntity userPropertyStaffEntity) {
        ProjectProjectEntity projectProjectEntity = new ProjectProjectEntity();
        projectProjectEntity.setId(IdGen.uuid());
        projectProjectEntity.setCityId(projectProjectDTO.getCityId());
        projectProjectEntity.setName(projectProjectDTO.getProjectName());
        projectProjectEntity.setState("1");
        projectProjectEntity.setCreateOn(new Date());
        projectProjectEntity.setModifyOn(new Date());
//        ProjectStaffEmployEntity projectStaffEmployEntity = new ProjectStaffEmployEntity();
//        projectStaffEmployEntity.setProjectId(projectProjectEntity.getId());
//        projectStaffEmployEntity.setDataId(userPropertyStaffEntity.getStaffId());
//        projectStaffEmployEntity.setDataType("2");
//        projectStaffEmployEntity.setProjectRole("1");
//        projectStaffEmployEntity.setStatus("1");
//        projectStaffEmployEntity.setModifyTime(new Date());
//        staffEmployRepository.addProjectStaffEmploy(projectStaffEmployEntity); //创建人默认拥有该项目甲方权限
        projectProjectRepository.addProjectProject(projectProjectEntity);
    }

    @Override
    public void delProjectProject(String projectId) {
        projectProjectRepository.delProjectProject(projectId);
    }

    @Override
    public List<ProjectProjectDTO> getProjectProjectsByCityId(String cityId, String staffId) {
        List<ProjectProjectEntity> projectProjectEntities = projectProjectRepository.getProjectProjectsByCityId(cityId, staffId);
        List<ProjectProjectDTO> projectProjectDTOs = new ArrayList<ProjectProjectDTO>();
        if (projectProjectEntities != null) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (ProjectProjectEntity projectProjectEntity : projectProjectEntities) {
                map.put(projectProjectEntity.getId(), projectProjectEntity);
            }
            Iterator it = map.keySet().iterator();
            ProjectProjectDTO projectProjectDTO;
            while (it.hasNext()) {
                ProjectProjectEntity projectProjectEntity = (ProjectProjectEntity) map.get(it.next());
                projectProjectDTO = new ProjectProjectDTO();
                projectProjectDTO.setCityId(projectProjectEntity.getCityId());
                projectProjectDTO.setProjectId(projectProjectEntity.getId());
                projectProjectDTO.setProjectName(projectProjectEntity.getName());
                projectProjectDTOs.add(projectProjectDTO);
            }
        }
//        List<ProjectProjectDTO> projectProjectDTOs = new ArrayList<ProjectProjectDTO>();
//            if (projectProjectEntities != null) {
//            ProjectProjectDTO projectProjectDTO;
//            for (ProjectProjectEntity projectProjectEntity : projectProjectEntities) {
//                projectProjectDTO = new ProjectProjectDTO();
//                projectProjectDTO.setCityId(projectProjectEntity.getCityId());
//                projectProjectDTO.setProjectId(projectProjectEntity.getId());
//                projectProjectDTO.setProjectName(projectProjectEntity.getName());
//                projectProjectDTOs.add(projectProjectDTO);
//            }
//        }
        return projectProjectDTOs;
    }

    @Override
    public Map getProjectProjects() {
        List<ProjectProjectEntity> list = projectProjectRepository.getProjectProjects();
        Map<String, String> projects = new LinkedHashMap<>();
        projects.put("", "请选择");
        if (list.size() > 0) {
            for (ProjectProjectEntity projectProjectEntity : list) {
                projects.put(projectProjectEntity.getId(), projectProjectEntity.getName());
            }
        }
        return projects;
    }

    @Override
    public ApiResult getProjectProjectList(String timeStamp, String autoNum) {
        List<ProjectProjectEntity> projectProjectEntities = projectProjectRepository.getProjectProjectList(timeStamp, Integer.parseInt(autoNum));
        List<ProjectDataDTO> projectDataDTOs = new ArrayList<ProjectDataDTO>();
        BaseProjectDTO baseProjectDTO = new BaseProjectDTO();
        String tempTime = "";
        String lastNum = "";
        if (projectProjectEntities != null && projectProjectEntities.size() != 0) {
            ProjectDataDTO projectDataDTO;
            for (ProjectProjectEntity projectProjectEntity : projectProjectEntities) {
                projectDataDTO = new ProjectDataDTO();
                projectDataDTO.setId(String.valueOf(projectProjectEntity.getAutoNum()));
                projectDataDTO.setProjectId(projectProjectEntity.getId());
                projectDataDTO.setProjectName(projectProjectEntity.getName());
                projectDataDTO.setStatus(projectProjectEntity.getState());
                projectDataDTO.setTimeStamp(DateUtils.format(projectProjectEntity.getModifyOn()));
                tempTime = projectDataDTO.getTimeStamp();
                lastNum = projectDataDTO.getId();
                projectDataDTOs.add(projectDataDTO);
            }
        }
        baseProjectDTO.setId(lastNum);
        baseProjectDTO.setTimeStamp(tempTime);
        baseProjectDTO.setList(projectDataDTOs);
        return new SuccessApiResult(baseProjectDTO);
    }

    @Override
    public List<ProjectProjectDTO> getProjectAll(ProjectProjectDTO projectProjectDTO, WebPage webPage) {
        ProjectProjectEntity projectProjectEntity = new ProjectProjectEntity();
        projectProjectEntity.setName(projectProjectDTO.getProjectName());
        projectProjectEntity.setCityId(projectProjectDTO.getCityId());
        if (!StringUtil.isEmpty(projectProjectDTO.getOptId()) && StringUtil.isEmpty(projectProjectDTO.getCityId())) {
            projectProjectEntity.setCityId("0");
        }
        List<ProjectProjectDTO> projectProjectDTOs = new ArrayList<ProjectProjectDTO>();
        List<ProjectProjectEntity> projectProjectEntities = projectProjectRepository.getProjectAll(projectProjectEntity, webPage);
        if (projectProjectEntities != null) {
            ProjectProjectDTO projectProjectDTO1;
            for (ProjectProjectEntity projectProjectEntity1 : projectProjectEntities) {
                projectProjectDTO1 = new ProjectProjectDTO();
                projectProjectDTO1.setProjectId(projectProjectEntity1.getId());
                projectProjectDTO1.setProjectName(projectProjectEntity1.getName());
                ProjectCityEntity projectCityEntity = projectCityRepository.getCityDetail(projectProjectEntity1.getCityId());
                if (projectCityEntity != null) {
                    projectProjectDTO1.setCityName(projectCityEntity.getCityName());
                    ProjectOperationEntity projectOperationEntity = projectOperationRepository.getProjectOperationDetail(projectCityEntity.getOptId());
                    if (projectOperationEntity != null) {
                        projectProjectDTO1.setOptName(projectOperationEntity.getOptName());
                    }
                }
                projectProjectDTO1.setModifyOn(DateUtils.format(projectProjectEntity1.getModifyOn()));
                projectProjectDTOs.add(projectProjectDTO1);
            }
        }
        return projectProjectDTOs;
    }

    @Override
    public void updateProjectProject(ProjectProjectReceiveDTO projectProjectReceiveDTO) {
        ProjectProjectEntity projectProjectEntity = projectProjectRepository.getProjectProjectDetail(projectProjectReceiveDTO.getProjectId());
        if (!projectProjectEntity.getName().equals(projectProjectReceiveDTO.getProjectName())) {  //如果修改了项目名则维护基础表项目名
            projectPeopleRepository.updateForProjectName(projectProjectReceiveDTO.getProjectName(), projectProjectEntity.getId());
        }
        projectProjectEntity.setName(projectProjectReceiveDTO.getProjectName());
        projectProjectEntity.setModifyOn(new Date());
        //判断当前项目下 甲方权限、数据查看（系统管理员）、非正常关单、工程经理、领导权限 是否有添加责任单位或人  并进行相关删除保存操作

        ProjectStaffEmployEntity projectStaffEmployEntity = new ProjectStaffEmployEntity();
        projectStaffEmployEntity.setProjectId(projectProjectReceiveDTO.getProjectId());
        projectStaffEmployEntity.setStatus("1");
        String[] ids;
        if ("2".equals(projectProjectReceiveDTO.getFlag())) {
            /**
             * 甲方权限关联部门部分
             * */
            projectStaffEmployEntity.setDataType("1");
            projectStaffEmployEntity.setProjectRole("1");
            if (!StringUtil.isEmpty(projectProjectReceiveDTO.getOwnerDept())) {
                ids = projectProjectReceiveDTO.getOwnerDept().split(",");
                savePartyA(projectProjectReceiveDTO.getProjectId(), ids, "1");
                savePartyAByAdmin(ids, 1, projectProjectReceiveDTO.getProjectId());
            } else {
                deleteData("后端默认甲方权限", projectProjectReceiveDTO.getProjectId(), 1);
                ids = null;
            }
            dumpSave(projectStaffEmployEntity, ids);
            /**
             * 甲方权限关联人员部分
             * */
            projectStaffEmployEntity.setDataType("2");
            projectStaffEmployEntity.setProjectRole("1");
            if (!StringUtil.isEmpty(projectProjectReceiveDTO.getOwnerStaff())) {
                ids = projectProjectReceiveDTO.getOwnerStaff().split(",");
                savePartyA(projectProjectReceiveDTO.getProjectId(), ids, "3");
                savePartyAByAdmin(ids, 3, projectProjectReceiveDTO.getProjectId());
            } else {
                deleteData("后端默认甲方权限", projectProjectReceiveDTO.getProjectId(), 2);
                ids = null;
            }
            dumpSave(projectStaffEmployEntity, ids);

            /**
             * 领导权限关联人员部分
             */
            projectStaffEmployEntity.setDataType("2");
            projectStaffEmployEntity.setProjectRole("5");
            if (!StringUtil.isEmpty(projectProjectReceiveDTO.getLeaderStaff())) {
                ids = projectProjectReceiveDTO.getLeaderStaff().split(",");
                saveLeader(projectProjectReceiveDTO.getProjectId(), ids, "3");//APP默认领导权限
                saveLeaderByAdmin(ids, 3, projectProjectReceiveDTO.getProjectId());//管理后台默认领导权限
            } else {
                deleteData("后台默认领导权限", projectProjectReceiveDTO.getProjectId(), 2);
                ids = null;
            }
            dumpSave(projectStaffEmployEntity, ids);
            /**领导权限关联部门部分*/
            projectStaffEmployEntity.setDataType("1");
            projectStaffEmployEntity.setProjectRole("5");
            if (!StringUtil.isEmpty(projectProjectReceiveDTO.getLeaderDept())) {
                ids = projectProjectReceiveDTO.getLeaderDept().split(",");
                saveLeader(projectProjectReceiveDTO.getProjectId(), ids, "1");//APP默认领导权限
                saveLeaderByAdmin(ids, 1, projectProjectReceiveDTO.getProjectId());//管理后台默认领导权限
            } else {
                deleteData("后台默认领导权限", projectProjectReceiveDTO.getProjectId(), 1);
                ids = null;
            }
            dumpSave(projectStaffEmployEntity, ids);

        } else {
            /**
             * 数据查看(系统管理员)关联部门部分
             * */
            projectStaffEmployEntity.setDataType("1");
            projectStaffEmployEntity.setProjectRole("2");
            if (!StringUtil.isEmpty(projectProjectReceiveDTO.getViewDept())) {
                ids = projectProjectReceiveDTO.getViewDept().split(",");
                saveQualityManager(ids, 1, projectProjectReceiveDTO.getProjectId());
            } else {
                deleteData("默认系统管理员", projectProjectReceiveDTO.getProjectId(), 1);
                ids = null;
            }
            dumpSave(projectStaffEmployEntity, ids);
            /**
             * 数据查看（系统管理员）关联人员部分
             * */
            projectStaffEmployEntity.setDataType("2");
            projectStaffEmployEntity.setProjectRole("2");
            if (!StringUtil.isEmpty(projectProjectReceiveDTO.getViewStaff())) {
                ids = projectProjectReceiveDTO.getViewStaff().split(",");
                saveQualityManager(ids, 2, projectProjectReceiveDTO.getProjectId());
            } else {
                deleteData("默认系统管理员", projectProjectReceiveDTO.getProjectId(), 2);
                ids = null;
            }
            dumpSave(projectStaffEmployEntity, ids);
            /**关单权限关联部门部分*/
            projectStaffEmployEntity.setDataType("1");
            projectStaffEmployEntity.setProjectRole("3");
            if (!StringUtil.isEmpty(projectProjectReceiveDTO.getCloseDept())) {
                ids = projectProjectReceiveDTO.getCloseDept().split(",");
            } else {
                ids = null;
            }
            dumpSave(projectStaffEmployEntity, ids);
            /**关单权限关联人员部分*/
            projectStaffEmployEntity.setDataType("2");
            projectStaffEmployEntity.setProjectRole("3");
            if (!StringUtil.isEmpty(projectProjectReceiveDTO.getCloseStaff())) {
                ids = projectProjectReceiveDTO.getCloseStaff().split(",");
            } else {
                ids = null;
            }
            dumpSave(projectStaffEmployEntity, ids);
            /**
             * （工程）经理权限关联部门部分
             * */
            projectStaffEmployEntity.setDataType("1");
            projectStaffEmployEntity.setProjectRole("4");
            if (!StringUtil.isEmpty(projectProjectReceiveDTO.getSurveyorDept())) {
                ids = projectProjectReceiveDTO.getSurveyorDept().split(",");
                saveProjectManager(ids, 1, projectProjectReceiveDTO.getProjectId());//管理后台默认工程经理
                saveEngineeringManager(projectProjectReceiveDTO.getProjectId(), ids, "1");//APP默认工程经理
            } else {
                deleteData("默认工程经理权限", projectProjectReceiveDTO.getProjectId(), 1);
                ids = null;
            }
            dumpSave(projectStaffEmployEntity, ids);
            /**
             * （工程）经理权限关联人员部分
             * */
            projectStaffEmployEntity.setDataType("2");
            projectStaffEmployEntity.setProjectRole("4");
            if (!StringUtil.isEmpty(projectProjectReceiveDTO.getSurveyorStaff())) {
                ids = projectProjectReceiveDTO.getSurveyorStaff().split(",");
                saveProjectManager(ids, 2, projectProjectReceiveDTO.getProjectId());//管理后台默认工程经理
                saveEngineeringManager(projectProjectReceiveDTO.getProjectId(), ids, "3");//APP默认工程经理
            } else {
                deleteData("默认工程经理权限", projectProjectReceiveDTO.getProjectId(), 2);
                ids = null;
            }
            dumpSave(projectStaffEmployEntity, ids);
        }
        projectProjectRepository.updateProjectProject(projectProjectEntity);
    }

    public void dumpSave(ProjectStaffEmployEntity projectStaffEmployEntity, String[] ids) {
        List<String> compairDTO1 = new ArrayList<String>();
        List<String> compairDTO2 = new ArrayList<String>();
        List<String> compairDTO3 = new ArrayList<String>();
        Iterator<String> it1;
        Iterator<String> it2;
        List<ProjectStaffEmployEntity> projectStaffEmployEntities;
        projectStaffEmployEntities = staffEmployRepository.getStaffEmploys(projectStaffEmployEntity.getProjectId(), projectStaffEmployEntity.getDataType(), projectStaffEmployEntity.getProjectRole());//查出数据库中已存在的数据
        if (projectStaffEmployEntities != null && projectStaffEmployEntities.size() > 0) {
            for (ProjectStaffEmployEntity projectStaffEmployEntity2 : projectStaffEmployEntities) {
                compairDTO1.add(projectStaffEmployEntity2.getDataId());   //将数据库中的数据存放于compairDTO1
            }
        }
        if (ids != null) {
            for (int i = 0; i < ids.length; i++) {
                compairDTO2.add(ids[i]);      //将页面传来的数据存放于compairDTO2
            }
        }
        compairDTO3.addAll(compairDTO1);
        compairDTO1.removeAll(compairDTO2);//比较后 为待删除的数据
        it1 = compairDTO1.iterator();
        while (it1.hasNext()) {
            projectStaffEmployEntity.setDataId(it1.next());
            staffEmployRepository.deleteProjectRole(projectStaffEmployEntity);         //删除权限关联数据
        }
        compairDTO2.removeAll(compairDTO3);//比较后 待新增的数据
        it2 = compairDTO2.iterator();
        while (it2.hasNext()) {
            projectStaffEmployEntity.setDataId(it2.next());
            staffEmployRepository.dumpAddProjectRole(projectStaffEmployEntity);  //保存最新关系
        }
        compairDTO1.clear();
        compairDTO2.clear();
        compairDTO3.clear();
    }

    /**
     * app甲方默认权限
     *
     * @param projectId
     * @param ids
     * @param type
     */
    public void savePartyA(String projectId, String[] ids, String type) {
        AppRolesetEntity appRolesetEntity = appRoleSetRepository.getAppRolesetByAppSetName("APP默认甲方权限");//获取app角色（甲方工程师）
        if (type.equals("1")) {
            if (appRolesetEntity != null) {
                saveCom(ids, appRolesetEntity.getAppSetId(), projectId, type);
            }
        } else {
            if (appRolesetEntity != null) {
                saveCom(ids, appRolesetEntity.getAppSetId(), projectId, type);
            }
        }
    }

    /**
     * APP默认领导权限
     *
     * @param projectId
     * @param ids
     * @param type
     */
    public void saveLeader(String projectId, String[] ids, String type) {
        AppRolesetEntity appRolesetEntity = appRoleSetRepository.getAppRolesetByAppSetName("APP默认领导权限");//获取app角色（领导权限）
        if (type.equals("1")) {
            if (appRolesetEntity != null) {
                saveCom(ids, appRolesetEntity.getAppSetId(), projectId, type);
            }
        } else {
            if (appRolesetEntity != null) {
                saveCom(ids, appRolesetEntity.getAppSetId(), projectId, type);
            }
        }
    }

    /**
     * APP默认工程经理权限
     *
     * @param projectId
     * @param ids
     * @param type
     */
    public void saveEngineeringManager(String projectId, String[] ids, String type) {
        AppRolesetEntity appRolesetEntity = appRoleSetRepository.getAppRolesetByAppSetName("APP默认工程经理");//获取app角色（工程经理权限）
        if (type.equals("1")) {
            if (appRolesetEntity != null) {
                saveCom(ids, appRolesetEntity.getAppSetId(), projectId, type);
            }
        } else {
            if (appRolesetEntity != null) {
                saveCom(ids, appRolesetEntity.getAppSetId(), projectId, type);
            }
        }
    }

    public void saveCom(String[] ids, String dataId, String projectId, String type) {
        roleDataRepository.deleteAgencyRole(projectId, type, dataId);
//        List<RoleDataEntity> roleDataEntitys = roleDataRepository.searchProjectRoleData(projectId, type, dataId);
//        if (roleDataEntitys != null && roleDataEntitys.size() > 0) {
//            for (RoleDataEntity roleDataEntity : roleDataEntitys) {
//                roleDataRepository.deleteAgencyRole(roleDataEntity);
//            }
//        }
        for (int i = 0; i < ids.length; i++) {
            RoleDataEntity roleDataEntity1 = new RoleDataEntity();
            roleDataEntity1.setId(IdGen.uuid());
            roleDataEntity1.setStatus("1");//状态 0删除 1正常
            roleDataEntity1.setDataType("2");//角色
            roleDataEntity1.setDataId(dataId);//角色数据ID
            roleDataEntity1.setAuthorityType(type);//1机构 2群组 3用户
            roleDataEntity1.setAuthorityId(ids[i]); //用户ID
            roleDataEntity1.setPermission(projectId);//为了解决问题，在此地方用此字段放项目ID,
            roleDataEntity1.setModifyTime(new Date());
            roleDataRepository.addRoleData(roleDataEntity1);  //保存角色与人的关系(后台)
        }
    }

    /**
     * 管理后台默认甲方权限
     *
     * @param ids
     * @param type
     * @param projectId
     */
    public void savePartyAByAdmin(String[] ids, int type, String projectId) {
        RoleRolesetEntity roleRolesetEntity = roleRolesetRepository.getRolesetByName("后端默认甲方权限");//获取后台角色（甲方权限）
        if (type == 1) {
            List<RoleDataEntity> roleDataEntitys = roleDataRepository.searchProjectRoleData("admin", "1", roleRolesetEntity.getSetId());
            if (roleDataEntitys != null && roleDataEntitys.size() > 0) {
                for (RoleDataEntity roleDataEntity : roleDataEntitys) {
                    for (String id : ids) {
                        if (!id.equals(roleDataEntity.getAuthorityId())) {
                            roleDataRepository.delAdminAgencyRole(roleRolesetEntity.getSetId(), roleDataEntity.getAuthorityId(), projectId);
                        }
                    }
                }
            }
            for (String id : ids) {
                RoleDataEntity roleDataEntity = new RoleDataEntity();
                roleDataEntity.setId("A" + IdGen.uuid());
                roleDataEntity.setDataType("2");
                roleDataEntity.setDataId(roleRolesetEntity.getSetId());
                roleDataEntity.setAuthorityType("1");
                roleDataEntity.setAuthorityId(id);
                roleDataEntity.setPermission(projectId);
                roleDataEntity.setModifyTime(new Date());
                roleDataEntity.setStatus("1");
                roleDataRepository.addDumpRoleData(roleDataEntity);
            }
        } else {
            roleAnthorityRepository.delstaffRole(roleRolesetEntity.getSetId(), projectId);
            for (String id : ids) {
                RoleRoleanthorityEntity roleRoleanthorityEntity = new RoleRoleanthorityEntity();
                roleRoleanthorityEntity.setUserId(IdGen.uuid());
                roleRoleanthorityEntity.setSetId(roleRolesetEntity.getSetId());
                roleRoleanthorityEntity.setStaffId(id);
                roleRoleanthorityEntity.setProjectId(projectId);
                roleAnthorityRepository.roleAdduser(roleRoleanthorityEntity);
            }
        }
    }

    /**
     * 管理后台系统管理员权限
     */
    public void saveQualityManager(String[] ids, int type, String projectId) {
        RoleRolesetEntity roleRolesetEntity1 = roleRolesetRepository.getRolesetByName("默认系统管理员");//获取后台（系统管理员）角色
        if (type == 1) {
            List<RoleDataEntity> roleDataEntitys = roleDataRepository.searchProjectRoleData("admin", "1", roleRolesetEntity1.getSetId());
            if (roleDataEntitys != null && roleDataEntitys.size() > 0) {
                for (RoleDataEntity roleDataEntity : roleDataEntitys) {
                    for (String id : ids) {
                        if (!id.equals(roleDataEntity.getAuthorityId())) {
                            roleDataRepository.delAdminAgencyRole(roleRolesetEntity1.getSetId(), roleDataEntity.getAuthorityId(), projectId);
                        }
                    }
                }
            }
            for (String id : ids) {
                RoleDataEntity roleDataEntity = new RoleDataEntity();
                roleDataEntity.setId("A" + IdGen.uuid());
                roleDataEntity.setDataType("2");
                roleDataEntity.setDataId(roleRolesetEntity1.getSetId());
                roleDataEntity.setAuthorityType("1");
                roleDataEntity.setAuthorityId(id);
                roleDataEntity.setPermission(projectId);
                roleDataEntity.setModifyTime(new Date());
                roleDataEntity.setStatus("1");
                roleDataRepository.addDumpRoleData(roleDataEntity);
            }
        } else {
            roleAnthorityRepository.delstaffRole(roleRolesetEntity1.getSetId(), projectId);
            for (String id : ids) {
                RoleRoleanthorityEntity roleRoleanthorityEntity = new RoleRoleanthorityEntity();
                roleRoleanthorityEntity.setUserId(IdGen.uuid());
                roleRoleanthorityEntity.setSetId(roleRolesetEntity1.getSetId());
                roleRoleanthorityEntity.setStaffId(id);
                roleRoleanthorityEntity.setProjectId(projectId);
                roleAnthorityRepository.roleAdduser(roleRoleanthorityEntity);
            }
        }
    }

    /**
     * 管理后台默认工程经理
     */
    public void saveProjectManager(String[] ids, int type, String projectId) {
        RoleRolesetEntity roleRolesetEntity1 = roleRolesetRepository.getRolesetByName("默认工程经理权限");//获取后台（工程经理）角色
        if (type == 1) {
            List<RoleDataEntity> roleDataEntitys = roleDataRepository.searchProjectRoleData("admin", "1", roleRolesetEntity1.getSetId());
            if (roleDataEntitys != null && roleDataEntitys.size() > 0) {
                for (RoleDataEntity roleDataEntity : roleDataEntitys) {
                    for (String id : ids) {
                        if (!id.equals(roleDataEntity.getAuthorityId())) {
                            roleDataRepository.delAdminAgencyRole(roleRolesetEntity1.getSetId(), roleDataEntity.getAuthorityId(), projectId);
                        }
                    }
                }
            }
            for (String id : ids) {
                RoleDataEntity roleDataEntity = new RoleDataEntity();
                roleDataEntity.setId("A" + IdGen.uuid());
                roleDataEntity.setDataType("2");
                roleDataEntity.setDataId(roleRolesetEntity1.getSetId());
                roleDataEntity.setAuthorityType("1");
                roleDataEntity.setAuthorityId(id);
                roleDataEntity.setPermission(projectId);
                roleDataEntity.setModifyTime(new Date());
                roleDataEntity.setStatus("1");
                roleDataRepository.addDumpRoleData(roleDataEntity);
            }
        } else {
            roleAnthorityRepository.delstaffRole(roleRolesetEntity1.getSetId(),projectId);
            for (String id : ids) {
                RoleRoleanthorityEntity roleRoleanthorityEntity = new RoleRoleanthorityEntity();
                roleRoleanthorityEntity.setUserId(IdGen.uuid());
                roleRoleanthorityEntity.setSetId(roleRolesetEntity1.getSetId());
                roleRoleanthorityEntity.setStaffId(id);
                roleRoleanthorityEntity.setProjectId(projectId);
                roleAnthorityRepository.roleAdduser(roleRoleanthorityEntity);
            }
        }
    }

    /**
     * 管理后台默认领导权限
     *
     * @param ids
     * @param type
     * @param projectId
     */
    public void saveLeaderByAdmin(String[] ids, int type, String projectId) {
        RoleRolesetEntity roleRolesetEntity1 = roleRolesetRepository.getRolesetByName("后台默认领导权限");//获取后台（领导权限）角色
        if (type == 1) {
            List<RoleDataEntity> roleDataEntitys = roleDataRepository.searchProjectRoleData("admin", "1", roleRolesetEntity1.getSetId());
            if (roleDataEntitys != null && roleDataEntitys.size() > 0) {
                for (RoleDataEntity roleDataEntity : roleDataEntitys) {
                    for (String id : ids) {
                        if (!id.equals(roleDataEntity.getAuthorityId())) {
                            roleDataRepository.delAdminAgencyRole(roleRolesetEntity1.getSetId(), roleDataEntity.getAuthorityId(), projectId);
                        }
                    }
                }
            }
            for (String id : ids) {
                RoleDataEntity roleDataEntity = new RoleDataEntity();
                roleDataEntity.setId("A" + IdGen.uuid());
                roleDataEntity.setDataType("2");
                roleDataEntity.setDataId(roleRolesetEntity1.getSetId());
                roleDataEntity.setAuthorityType("1");
                roleDataEntity.setAuthorityId(id);
                roleDataEntity.setPermission(projectId);
                roleDataEntity.setModifyTime(new Date());
                roleDataEntity.setStatus("1");
                roleDataRepository.addDumpRoleData(roleDataEntity);
            }
        } else {
            roleAnthorityRepository.delstaffRole(roleRolesetEntity1.getSetId(),projectId);
            for (String id : ids) {
                RoleRoleanthorityEntity roleRoleanthorityEntity = new RoleRoleanthorityEntity();
                roleRoleanthorityEntity.setUserId(IdGen.uuid());
                roleRoleanthorityEntity.setSetId(roleRolesetEntity1.getSetId());
                roleRoleanthorityEntity.setStaffId(id);
                roleRoleanthorityEntity.setProjectId(projectId);
                roleAnthorityRepository.roleAdduser(roleRoleanthorityEntity);
            }
        }
    }

    /**
     * 删除角色与组织单位\人的关系数据
     */
    public void deleteData(String name, String projectId, int type) {
        RoleRolesetEntity roleRolesetEntity1 = roleRolesetRepository.getRolesetByName(name);//获取后台（工程经理、系统管理员）角色
        if (type == 1) {
            roleDataRepository.delAdminAgencyRole(roleRolesetEntity1.getSetId(), projectId);
        } else {
            roleAnthorityRepository.delstaffRole(roleRolesetEntity1.getSetId(),projectId);
        }
    }

    @Override
    public ProjectRoleAllDTO getProjectDetail(String projectId) {
        ProjectProjectEntity projectProjectEntity = projectProjectRepository.getProjectProjectDetail(projectId);
        ProjectRoleAllDTO projectRoleAllDTO = new ProjectRoleAllDTO();
        projectRoleAllDTO.setProjectId(projectProjectEntity.getId());
        projectRoleAllDTO.setProjectName(projectProjectEntity.getName());
        projectRoleAllDTO.setModifyOn(DateUtils.format(projectProjectEntity.getModifyOn()));
        List<AgencyEntity> agencyEntities1 = staffEmployRepository.getEmploysForProjectPermission(projectId, "1");             //获取当前工程项目下甲方权限对应责任单位列表
        List<UserPropertyStaffEntity> userPropertyStaffEntities1 = staffEmployRepository.getStaffsForProjectPermission(projectId, "1");  //获取当前工程项目下甲方权限对应的人员列表
        List<AgencyEntity> agencyEntities2 = staffEmployRepository.getEmploysForProjectPermission(projectId, "2");             //获取当前工程项目下数据查看对应责任单位列表
        List<UserPropertyStaffEntity> userPropertyStaffEntities2 = staffEmployRepository.getStaffsForProjectPermission(projectId, "2");  //获取当前工程项目下数据查看对应的人员列表
        List<AgencyEntity> agencyEntities3 = staffEmployRepository.getEmploysForProjectPermission(projectId, "3");             //获取当前工程项目下非正常关单对应责任单位列表
        List<UserPropertyStaffEntity> userPropertyStaffEntities3 = staffEmployRepository.getStaffsForProjectPermission(projectId, "3");  //获取当前工程项目下非正常关单对应的人员列表
        List<AgencyEntity> agencyEntities4 = staffEmployRepository.getEmploysForProjectPermission(projectId, "4");             //获取当前工程项目下非正常关单对应责任单位列表
        List<UserPropertyStaffEntity> userPropertyStaffEntities4 = staffEmployRepository.getStaffsForProjectPermission(projectId, "4");  //获取当前工程项目下非正常关单对应的人员列表
        List<AgencyEntity> agencyEntities5 = staffEmployRepository.getEmploysForProjectPermission(projectId, "5");             //获取当前工程项目下领导权限对应的责任单位
        List<UserPropertyStaffEntity> userPropertyStaffEntities5 = staffEmployRepository.getStaffsForProjectPermission(projectId, "5");  //获取当前工程项目下领导权限对应的人员列表
        ProjectRoleDTO projectRoleDTO;
        List<ProjectRoleDTO> projectRoleDTOs;
        if (agencyEntities1 != null) {
            projectRoleDTOs = new ArrayList<ProjectRoleDTO>();
            for (AgencyEntity agencyEntity : agencyEntities1) {
                projectRoleDTO = new ProjectRoleDTO();
                projectRoleDTO.setpRoleId(agencyEntity.getAgencyId());
                projectRoleDTO.setpRoleName(agencyEntity.getAgencyName());
                projectRoleDTOs.add(projectRoleDTO);
            }
            projectRoleAllDTO.setOwnerDepts(projectRoleDTOs);
        }
        if (userPropertyStaffEntities1 != null) {
            projectRoleDTOs = new ArrayList<ProjectRoleDTO>();
            for (UserPropertyStaffEntity userPropertyStaffEntity : userPropertyStaffEntities1) {
                projectRoleDTO = new ProjectRoleDTO();
                projectRoleDTO.setpRoleId(userPropertyStaffEntity.getStaffId());
                projectRoleDTO.setpRoleName(userPropertyStaffEntity.getStaffName());
                projectRoleDTOs.add(projectRoleDTO);
            }
            projectRoleAllDTO.setOwnerStaffs(projectRoleDTOs);
        }
        if (agencyEntities2 != null) {
            projectRoleDTOs = new ArrayList<ProjectRoleDTO>();
            for (AgencyEntity agencyEntity : agencyEntities2) {
                projectRoleDTO = new ProjectRoleDTO();
                projectRoleDTO.setpRoleId(agencyEntity.getAgencyId());
                projectRoleDTO.setpRoleName(agencyEntity.getAgencyName());
                projectRoleDTOs.add(projectRoleDTO);
            }
            projectRoleAllDTO.setViewDepts(projectRoleDTOs);
        }
        if (userPropertyStaffEntities2 != null) {
            projectRoleDTOs = new ArrayList<ProjectRoleDTO>();
            for (UserPropertyStaffEntity userPropertyStaffEntity : userPropertyStaffEntities2) {
                projectRoleDTO = new ProjectRoleDTO();
                projectRoleDTO.setpRoleId(userPropertyStaffEntity.getStaffId());
                projectRoleDTO.setpRoleName(userPropertyStaffEntity.getStaffName());
                projectRoleDTOs.add(projectRoleDTO);
            }
            projectRoleAllDTO.setViewStaffs(projectRoleDTOs);
        }
        if (agencyEntities3 != null) {
            projectRoleDTOs = new ArrayList<ProjectRoleDTO>();
            for (AgencyEntity agencyEntity : agencyEntities3) {
                projectRoleDTO = new ProjectRoleDTO();
                projectRoleDTO.setpRoleId(agencyEntity.getAgencyId());
                projectRoleDTO.setpRoleName(agencyEntity.getAgencyName());
                projectRoleDTOs.add(projectRoleDTO);
            }
            projectRoleAllDTO.setCloseDepts(projectRoleDTOs);
        }
        if (userPropertyStaffEntities3 != null) {
            projectRoleDTOs = new ArrayList<ProjectRoleDTO>();
            for (UserPropertyStaffEntity userPropertyStaffEntity : userPropertyStaffEntities3) {
                projectRoleDTO = new ProjectRoleDTO();
                projectRoleDTO.setpRoleId(userPropertyStaffEntity.getStaffId());
                projectRoleDTO.setpRoleName(userPropertyStaffEntity.getStaffName());
                projectRoleDTOs.add(projectRoleDTO);
            }
            projectRoleAllDTO.setCloseStaffs(projectRoleDTOs);
        }
        if (agencyEntities4 != null) {
            projectRoleDTOs = new ArrayList<ProjectRoleDTO>();
            for (AgencyEntity agencyEntity : agencyEntities4) {
                projectRoleDTO = new ProjectRoleDTO();
                projectRoleDTO.setpRoleId(agencyEntity.getAgencyId());
                projectRoleDTO.setpRoleName(agencyEntity.getAgencyName());
                projectRoleDTOs.add(projectRoleDTO);
            }
            projectRoleAllDTO.setSurveyorDepts(projectRoleDTOs);
        }
        if (userPropertyStaffEntities4 != null) {
            projectRoleDTOs = new ArrayList<ProjectRoleDTO>();
            for (UserPropertyStaffEntity userPropertyStaffEntity : userPropertyStaffEntities4) {
                projectRoleDTO = new ProjectRoleDTO();
                projectRoleDTO.setpRoleId(userPropertyStaffEntity.getStaffId());
                projectRoleDTO.setpRoleName(userPropertyStaffEntity.getStaffName());
                projectRoleDTOs.add(projectRoleDTO);
            }
            projectRoleAllDTO.setSurveyorStaffs(projectRoleDTOs);
        }
        if (agencyEntities5 != null && agencyEntities5.size() > 0) {
            projectRoleDTOs = new ArrayList<ProjectRoleDTO>();
            for (AgencyEntity agencyEntity : agencyEntities5) {
                projectRoleDTO = new ProjectRoleDTO();
                projectRoleDTO.setpRoleId(agencyEntity.getAgencyId());
                projectRoleDTO.setpRoleName(agencyEntity.getAgencyName());
                projectRoleDTOs.add(projectRoleDTO);
            }
            projectRoleAllDTO.setLeaderDepts(projectRoleDTOs);
        }
        if (userPropertyStaffEntities5 != null && userPropertyStaffEntities5.size() > 0) {
            projectRoleDTOs = new ArrayList<ProjectRoleDTO>();
            for (UserPropertyStaffEntity userPropertyStaffEntity : userPropertyStaffEntities5) {
                projectRoleDTO = new ProjectRoleDTO();
                projectRoleDTO.setpRoleId(userPropertyStaffEntity.getStaffId());
                projectRoleDTO.setpRoleName(userPropertyStaffEntity.getStaffName());
                projectRoleDTOs.add(projectRoleDTO);
            }
            projectRoleAllDTO.setLeaderStaffs(projectRoleDTOs);
        }
        return projectRoleAllDTO;
    }

    @Override
    public ApiResult getProjectPeopleForTime(String projectId, String timeStamp, String autoNum) {
        List<BaseProjectPeopleEntity> baseProjectPeopleEntities = projectPeopleRepository.getProjectPeopleForTime(projectId, timeStamp, Long.parseLong(autoNum));
        BaseDataDTO baseDataDTO = new BaseDataDTO();
        List<ProjectPeopleDTO> projectPeopleDTOs = new ArrayList<ProjectPeopleDTO>();
        if (baseProjectPeopleEntities != null && baseProjectPeopleEntities.size() > 0) {
            ProjectPeopleDTO projectPeopleDTO;
            for (BaseProjectPeopleEntity baseProjectPeopleEntity : baseProjectPeopleEntities) {
                projectPeopleDTO = new ProjectPeopleDTO();
                projectPeopleDTO.setSupplierId(baseProjectPeopleEntity.getSupplierId());
                projectPeopleDTO.setSupplierName(baseProjectPeopleEntity.getSupplierName());
                projectPeopleDTO.setSupplierType(baseProjectPeopleEntity.getSupplierType());
                if ("1".equals(baseProjectPeopleEntity.getSupplierType())) {
                    AgencyEntity agencyEntity = agencyRepository.getAgencyDetail(baseProjectPeopleEntity.getSupplierId());
                    if (agencyEntity != null) {
                        projectPeopleDTO.setNature(agencyEntity.getNature());
                    }
                }
                projectPeopleDTO.setProjectId(baseProjectPeopleEntity.getProjectId());
                projectPeopleDTO.setProjectName(baseProjectPeopleEntity.getProjectName());
                projectPeopleDTO.setPeopleId(baseProjectPeopleEntity.getPeopleId());
                projectPeopleDTO.setPeopleName(baseProjectPeopleEntity.getPeopleName());
                projectPeopleDTO.setStatus(baseProjectPeopleEntity.getStatus());
                projectPeopleDTO.setId(baseProjectPeopleEntity.getAutoId());
                projectPeopleDTOs.add(projectPeopleDTO);
            }
            baseDataDTO.setId(String.valueOf(baseProjectPeopleEntities.get(baseProjectPeopleEntities.size() - 1).getAutoId()));
            baseDataDTO.setTimeStamp(DateUtils.format(baseProjectPeopleEntities.get(baseProjectPeopleEntities.size() - 1).getModifyTime()));
        }
        baseDataDTO.setList(projectPeopleDTOs);
        return new SuccessApiResult(baseDataDTO);
    }

    @Override
    public List<ProjectDTO> getProjectByName(String projectName) {
        List<ProjectProjectEntity> list = projectProjectRepository.getProjectByName(projectName);
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        if(list.size()>0 && list != null){
            for(ProjectProjectEntity projectProjectEntity : list){
                ProjectDTO projectDTO = new ProjectDTO();
                projectDTO.setId(projectProjectEntity.getId());
                projectDTO.setProjectName(projectProjectEntity.getName());
                projectDTOS.add(projectDTO);
            }
        }
        return projectDTOS;
    }

    @Override
    public List<ProjectTreeDTO> getAreaList() {
        List<ProjectTreeDTO> list = new ArrayList<>();
        ProjectTreeDTO projectTreeDTO = new ProjectTreeDTO();
        projectTreeDTO.setId("1");
        projectTreeDTO.setName("金茂集团");
        projectTreeDTO.setOpen("true");
        projectTreeDTO.setIconOpen("../static/img/diy/1_open.png");
        projectTreeDTO.setIconClose("../static/img/diy/1_close.png");
        projectTreeDTO.setIsParent("true");
        projectTreeDTO.setType("0");
        list.add(projectTreeDTO);
        return list;
    }

    @Override
    public List<ProjectTreeDTO> getProjectList(String parentId) {
        List<ProjectTreeDTO> list = new ArrayList<>();
        List<ProjectOperationEntity> projectOperationEntityList =projectProjectRepository.getProjectOperation();
        if(projectOperationEntityList.size()>0 && projectOperationEntityList != null){
            for(ProjectOperationEntity projectOperationEntity : projectOperationEntityList){
                ProjectTreeDTO projectTreeDTO = new ProjectTreeDTO();
                projectTreeDTO.setId(projectOperationEntity.getOptId());
                projectTreeDTO.setName(projectOperationEntity.getOptName());
                projectTreeDTO.setpId("1");
                projectTreeDTO.setIcon("../static/img/diy/9.png");
                projectTreeDTO.setIsParent("false");
                projectTreeDTO.setType("1");
                list.add(projectTreeDTO);
            }
        }
        return list;
    }

    @Override
    public List<ProjectTreeDTO> getNextProjectList(String parentId) {
        List<ProjectTreeDTO> list = new ArrayList<>();
        List<ProjectProjectEntity> projectProjectEntityList = projectProjectRepository.getProjectList(parentId);
        if(projectProjectEntityList.size()>0 && projectProjectEntityList != null){
            for(ProjectProjectEntity projectProjectEntity : projectProjectEntityList){
                ProjectTreeDTO projectTreeDTO = new ProjectTreeDTO();
                projectTreeDTO.setId(projectProjectEntity.getId());
                projectTreeDTO.setType("2");
                projectTreeDTO.setName(projectProjectEntity.getName());
                projectTreeDTO.setIsParent("false");
                projectTreeDTO.setpId(parentId);
                list.add(projectTreeDTO);
            }
        }
        return list;
    }

    @Override
    public ProjectStaffRelationDTO getProjectRole() {
        List<String> userId = new ArrayList<>();
        List<String> departmentId = new ArrayList<>();
        List<String> projectId = new ArrayList<>();
        List<String> areaId = new ArrayList<>();

        List<ProjectRoleDTO> roleList = new ArrayList<>();
        List<ProjectRoleDTO> projectList = new ArrayList<>();
        List<ProjectRoleDTO> areaList = new ArrayList<>();
        List<ProjectRoleDTO> departmentList = new ArrayList<>();

        List<ProjectOperationEntity> projectOperationEntityList = new ArrayList<>();
        List<ProjectProjectEntity> projectProjectEntityList = new ArrayList<>();
        List<UserPropertyStaffEntity> userPropertyStaffEntityList = new ArrayList<>();
        List<AgencyEntity> agencyEntityList = new ArrayList<>();
        ProjectStaffRelationDTO projectStaffRelationDTO = new ProjectStaffRelationDTO();
        List<ProjectStaffRelationEntity> projectStaffRelationEntities = projectProjectRepository.getProjectRole();
        if(projectStaffRelationEntities.size()>0 && projectStaffRelationEntities != null){
            for(ProjectStaffRelationEntity list : projectStaffRelationEntities){
                if("1".equals(list.getpType())){ //区域
                    areaId.add(list.getProjectId());
                }else
                if("2".equals(list.getpType())){ //项目
                    projectId.add(list.getProjectId());
                }
                if("1".equals(list.getuType())){ //人员
                    userId.add(list.getStaffId());
                }else
                if("2".equals(list.getuType())){ //部门
                    departmentId.add(list.getStaffId());
                }
            }
            if(areaId != null && areaId.size()>0){
                projectOperationEntityList = projectProjectRepository.getAreaById(areaId);
                if(projectOperationEntityList.size()>0 && projectOperationEntityList != null){
                    for(ProjectOperationEntity projectOperationEntity : projectOperationEntityList){
                        ProjectRoleDTO area = new ProjectRoleDTO();
                        area.setpRoleId(projectOperationEntity.getOptId());
                        area.setpRoleName(projectOperationEntity.getOptName());
                        areaList.add(area);
                    }
                }
            }
            if(projectId != null && projectId.size()>0){
                projectProjectEntityList = projectProjectRepository.getProjectById(projectId);
                if(projectProjectEntityList.size()>0 && projectProjectEntityList != null){
                    for(ProjectProjectEntity projectProjectEntity : projectProjectEntityList){
                        ProjectRoleDTO project = new ProjectRoleDTO();
                        project.setpRoleId(projectProjectEntity.getId());
                        project.setpRoleName(projectProjectEntity.getName());
                        projectList.add(project);
                    }
                }
            }
            if(userId != null && userId.size()>0 ){
                userPropertyStaffEntityList = projectProjectRepository.getUserByStaffId(userId);
                if(userPropertyStaffEntityList.size()>0 && userPropertyStaffEntityList != null){
                    for(UserPropertyStaffEntity userPropertyStaffEntity : userPropertyStaffEntityList){
                        ProjectRoleDTO role = new ProjectRoleDTO();
                        role.setpRoleId(userPropertyStaffEntity.getStaffId());
                        role.setpRoleName(userPropertyStaffEntity.getStaffName());
                        roleList.add(role);
                    }
                }
            }
            if(departmentId != null && departmentId.size()>0){
                agencyEntityList = projectProjectRepository.getAgencyBydepartmentId(departmentId);
                if(agencyEntityList.size()>0 && agencyEntityList != null){
                    for(AgencyEntity agencyEntity : agencyEntityList){
                        ProjectRoleDTO department = new ProjectRoleDTO();
                        department.setpRoleId(agencyEntity.getAgencyId());
                        department.setpRoleName(agencyEntity.getAgencyName());
                        departmentList.add(department);
                    }
                }
            }
            projectStaffRelationDTO.setGroupProject(projectList);
            projectStaffRelationDTO.setGroupStaff(roleList);
            projectStaffRelationDTO.setGroupArea(areaList);
            projectStaffRelationDTO.setGroupDepartment(departmentList);
        }
        return projectStaffRelationDTO;
    }

    @Override
    public void updateProjectRole(ConstructionProjectDTO constructionProjectDTO) {
        ProjectStaffRelationEntity projectStaffRelationEntity = new ProjectStaffRelationEntity();
        String[] projectId;//项目ID
        String[] areaId; //区域Id
        String[] staffId;//人员Id
        String[] depId;//部门ID
        if(constructionProjectDTO.getProjectBelongArea() != null && !StringUtil.isEmpty(constructionProjectDTO.getProjectBelongArea())){
            areaId = constructionProjectDTO.getProjectBelongArea().split(",");
        } else {
            areaId = null;
        }
        if(constructionProjectDTO.getProjectBelongProject() != null && !StringUtil.isEmpty(constructionProjectDTO.getProjectBelongProject())){
            projectId = constructionProjectDTO.getProjectBelongProject().split(",");
        } else {
            projectId = null;
        }
        if(constructionProjectDTO.getSecurityOfficerStaff() != null && !StringUtil.isEmpty(constructionProjectDTO.getSecurityOfficerStaff())){
            staffId = constructionProjectDTO.getSecurityOfficerStaff().split(",");
//            staffEmployRepository.deleProjectRole(constructionProjectDTO.getSecurityOfficerStaff(),"2");//删除以前的关系
        } else {
            staffId = null;
        }
        if(constructionProjectDTO.getSecurityOfficerDep() != null && !StringUtil.isEmpty(constructionProjectDTO.getSecurityOfficerDep())){
            depId = constructionProjectDTO.getSecurityOfficerDep().split(",");
//            staffEmployRepository.deleProjectRole(constructionProjectDTO.getSecurityOfficerDep(),"1");//删除以前的关系
        } else {
            depId = null;
        }
        projectProjectRepository.updateProjectStaff();//先删除表旧数据
        //再新增数据
        if(staffId != null){
            for(String sId : staffId){
                if(projectId != null){
                    for(String pId : projectId){
                        projectStaffRelationEntity.setProjectId(pId);
                        projectStaffRelationEntity.setpType("2");//项目
                        projectStaffRelationEntity.setStaffId(sId);
                        projectStaffRelationEntity.setuType("1");//人员
                        projectStaffRelationEntity.setState("1");
                        projectStaffRelationEntity.setModifyDate(new Date());
                        projectProjectRepository.addProjectStaff(projectStaffRelationEntity);
                    }
                }
                if(areaId != null){
                    for(String aId : areaId){
                        projectStaffRelationEntity.setProjectId(aId);
                        projectStaffRelationEntity.setpType("1");//区域
                        projectStaffRelationEntity.setStaffId(sId);
                        projectStaffRelationEntity.setuType("1");//人员
                        projectStaffRelationEntity.setState("1");
                        projectStaffRelationEntity.setModifyDate(new Date());
                        projectProjectRepository.addProjectStaff(projectStaffRelationEntity);
                    }
                }
            }
        }
        if(depId != null){
            for(String dId : depId){
                if(projectId != null){
                    for(String pId : projectId){
                        projectStaffRelationEntity.setProjectId(pId);
                        projectStaffRelationEntity.setpType("2");//项目
                        projectStaffRelationEntity.setStaffId(dId);
                        projectStaffRelationEntity.setuType("2");//部门
                        projectStaffRelationEntity.setState("1");
                        projectStaffRelationEntity.setModifyDate(new Date());
                        projectProjectRepository.addProjectStaff(projectStaffRelationEntity);
                    }
                }
                if(areaId != null){
                    for(String aId : areaId){
                        projectStaffRelationEntity.setProjectId(aId);
                        projectStaffRelationEntity.setpType("1");//区域
                        projectStaffRelationEntity.setStaffId(dId);
                        projectStaffRelationEntity.setuType("2");//部门
                        projectStaffRelationEntity.setState("1");
                        projectStaffRelationEntity.setModifyDate(new Date());
                        projectProjectRepository.addProjectStaff(projectStaffRelationEntity);
                    }
                }
            }
        }
        List<String> areaIds = new ArrayList<>();
        List<ProjectProjectEntity> list =new ArrayList<>();
        if(areaId != null){
            for(int i=0 ;i<areaId.length;i++){
                areaIds.add(areaId[i]);
            }
            list = projectProjectRepository.getProjectListByArea(areaIds);
        }
        List<String> projectIds = new ArrayList<>();
        if(projectId != null){
            for(int i=0 ;i<projectId.length;i++){
                projectIds.add(projectId[i]);
            }
        }
        if(list.size()>0 && list != null) {
            for (ProjectProjectEntity project : list) {
                projectIds.add(project.getId());
            }
        }
        /**
         * 领导权限关联人员部分
         */
        staffEmployRepository.deleteProjectRole();//删除所有的关系
        if(staffId != null) {
            for (String id : projectIds) {
                ProjectStaffEmployEntity projectStaffEmployEntity = new ProjectStaffEmployEntity();
                projectStaffEmployEntity.setDataType("2");
                projectStaffEmployEntity.setProjectRole("5");
                projectStaffEmployEntity.setProjectId(id);
                projectStaffEmployEntity.setSource("1");
                saveLeader(id, staffId, "3");//APP默认领导权限
                projectRoleSave(projectStaffEmployEntity, constructionProjectDTO.getSecurityOfficerStaff());
            }
        }
        /**领导权限关联部门部分*/
        if(depId != null) {
            for (String id : projectIds) {
                ProjectStaffEmployEntity projectStaffEmployEntity = new ProjectStaffEmployEntity();
                projectStaffEmployEntity.setDataType("1");
                projectStaffEmployEntity.setProjectRole("5");
                projectStaffEmployEntity.setProjectId(id);
                projectStaffEmployEntity.setSource("1");
                saveLeader(id, depId, "1");//APP默认领导权限
                projectRoleSave(projectStaffEmployEntity, constructionProjectDTO.getSecurityOfficerDep());
            }
        }
    }

    @Override
    public int checkName(AuthCheckNameDTO authCheckNameDTO) {
        try{
            if(!StringUtil.isEmpty(authCheckNameDTO.getsId())){
                //id不为空 则证明走的修改方法
                //校验当前id和sName是否匹配
                AgencyEntity agencyEntity = staffEmployRepository.getEmployDetail(authCheckNameDTO.getsId());
                if(agencyEntity!=null){
                    if(authCheckNameDTO.getsName().equals(agencyEntity.getAgencyName())){
                        //判断当前id查询数据和name是否匹配
                        return  1;
                    }else{
                        //不匹配则证明修改了组织机构名称
                        //校验当前项目下绑定的组织治机构是否匹配单签输入名字
                        if(staffEmployRepository.checkAgencyName(authCheckNameDTO.getProjectId(),authCheckNameDTO.getsName())){
                            //没有   通过
                            return  1;
                        }
                    }
                }
            }else{
                //校验当前项目下是都有输入的组织机构名称
                if(staffEmployRepository.checkAgencyName(authCheckNameDTO.getProjectId(),authCheckNameDTO.getsName())){
                    //没有  通过
                    return  1;
                }
            }
            return 0;
        }catch (Exception e){
            return -1;
        }
    }

    //保存关系
    public void projectRoleSave(ProjectStaffEmployEntity projectStaffEmployEntity, String ids ) {
        Iterator<String> it;
        List<String> compairDTO = new ArrayList<String>();
        if (!StringUtil.isEmpty(ids) && ids != null) {
            String [] staffId = ids.split(",");
            for (int i = 0; i < staffId.length; i++) {
                compairDTO.add(staffId[i]);
            }
        }
        it = compairDTO.iterator();
        while (it.hasNext()) {
            projectStaffEmployEntity.setDataId(it.next());
            staffEmployRepository.dumpAddProjectRole(projectStaffEmployEntity);  //保存最新关系
        }
        compairDTO.clear();
    }
}
