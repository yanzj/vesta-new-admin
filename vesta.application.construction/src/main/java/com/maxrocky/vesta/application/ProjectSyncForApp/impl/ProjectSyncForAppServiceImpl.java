package com.maxrocky.vesta.application.ProjectSyncForApp.impl;

import com.maxrocky.vesta.application.ProjectSyncForApp.DTO.UserSyncProjectDTO;
import com.maxrocky.vesta.application.ProjectSyncForApp.inf.ProjectSyncForAppService;
import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.UserAndRoleRelationDTO;
import com.maxrocky.vesta.domain.model.AuthAgencyESEntity;
import com.maxrocky.vesta.domain.model.RoleStaffProjectMapESAppEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.projectSyncForApp.repository.ProjectSyncForRepository;
import com.maxrocky.vesta.domain.repository.AuthAgencyRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * app项目同步Service实现
 * Created by yuanyn on 2017/12/21.
 */
@Service
public class ProjectSyncForAppServiceImpl implements ProjectSyncForAppService {

    @Autowired
    private ProjectSyncForRepository projectSyncForRepository;
    @Autowired
    private AuthAgencyRepository authAgencyRepository;

    @Override
    public List<UserSyncProjectDTO> getAccreditManageListByAgencyId(String agencyIdPro, WebPage webPage, UserInformationEntity userInformationEntity, List<String> updateProject) {
        List<UserSyncProjectDTO> userSyncProjectDTOS = new ArrayList<>();
        if(updateProject.contains(agencyIdPro)){
            List<Object[]> objects = projectSyncForRepository.getUserIdByProjectId(agencyIdPro);
            if(null != objects && objects.size()>0){
                for(Object[] obj : objects){
                    UserSyncProjectDTO userSyncProjectDTO = new UserSyncProjectDTO();
                    userSyncProjectDTO.setPeoId(obj[0] == null ? "" : obj[0].toString());
                    userSyncProjectDTO.setPeoName(obj[1] == null ? "" : obj[1].toString());
                    userSyncProjectDTO.setLoginName(obj[2] == null ? "" : obj[2].toString());
                    if(!StringUtil.isEmpty(userSyncProjectDTO.getPeoId())){
                        List<String> projectNames = projectSyncForRepository.getProjectNameByUserId(userSyncProjectDTO.getPeoId());
                        String projectName="";  //适用项目范围
                        if(null != projectNames && projectNames.size()>0){
                            for(int i = 0 ; i < projectNames.size(); i ++){
                                if( i==0 ){
                                    projectName = projectNames.get(i).toString();
                                }else {
                                    projectName = projectName + " ," + projectNames.get(i).toString();
                                }
                            }
                        }
                        userSyncProjectDTO.setProName(projectName);
                        List<String> projectAppNames = projectSyncForRepository.getProjectAppNameByUserId(userSyncProjectDTO.getPeoId());
                        String projectAppName="";  //适用App项目范围
                        if(null != projectAppNames && projectAppNames.size()>0){
                            for(int i = 0 ; i < projectAppNames.size(); i ++){
                                if( i==0 ){
                                    projectAppName = projectAppNames.get(i).toString();
                                }else {
                                    projectAppName = projectAppName + " ," + projectAppNames.get(i).toString();
                                }
                            }
                        }
                        userSyncProjectDTO.setProNameApp(projectAppName);
                    }
                    userSyncProjectDTOS.add(userSyncProjectDTO);
                }
            }
        }else {
            List<String> projectIds = projectSyncForRepository.getProjectIdByUserId(userInformationEntity.getStaffId());
            if(null != projectIds && projectIds.size()>0){
                if(projectIds.contains(agencyIdPro)){
                    userSyncProjectDTOS = this.getAccreditManageByStaffId(userInformationEntity);
                }
            }
        }
        return userSyncProjectDTOS;
    }

    @Override
    public List<UserSyncProjectDTO> getAccreditManageByStaffId(UserInformationEntity userInformationEntity) {
        List<UserSyncProjectDTO> userSyncProjectDTOS = new ArrayList<>();
        UserSyncProjectDTO userSyncProjectDTO = new UserSyncProjectDTO();
        userSyncProjectDTO.setPeoId(userInformationEntity.getStaffId());
        userSyncProjectDTO.setPeoName(userInformationEntity.getStaffName());
        userSyncProjectDTO.setLoginName(userInformationEntity.getSysName());
        List<String> projectNames = projectSyncForRepository.getProjectNameByUserId(userInformationEntity.getStaffId());
        String projectName="";  //适用项目范围
        if(null != projectNames && projectNames.size()>0){
            for(int i = 0 ; i < projectNames.size(); i ++){
                if( i==0 ){
                    projectName = projectNames.get(i).toString();
                }else {
                    projectName = projectName + " ," + projectNames.get(i).toString();
                }
            }
        }else {
            return userSyncProjectDTOS;
        }
        List<String> projectAppNames = projectSyncForRepository.getProjectAppNameByUserId(userInformationEntity.getStaffId());
        String projectAppName="";  //适用App项目范围
        if(null != projectAppNames && projectAppNames.size()>0){
            for(int i = 0 ; i < projectAppNames.size(); i ++){
                if( i==0 ){
                    projectAppName = projectAppNames.get(i).toString();
                }else {
                    projectAppName = projectAppName + " ," + projectAppNames.get(i).toString();
                }
            }
        }
        userSyncProjectDTO.setProNameApp(projectAppName);
        userSyncProjectDTO.setProName(projectName);
        userSyncProjectDTOS.add(userSyncProjectDTO);
        return userSyncProjectDTOS;
    }

    @Override
    public Map getRoleByUserId(String userId) {
        if (!StringUtil.isEmpty(userId)) {
            List<Object[]> list1 = projectSyncForRepository.getRoleIdByUserId(userId);
            List<Object[]> list = projectSyncForRepository.getRoleByUserId(userId);
            Map<String, String> map = new LinkedHashMap<>();
            if(null != list1 && list1.size()>0){
                for (Object[] obj : list1) {
                    map.put(obj[0] == null ? "" : obj[0].toString(), obj[1] == null ? "" : obj[1].toString());
                }
            }else {
                map.put("", "请选择角色");
            }
            if (null != list && list.size()>0) {
                for (Object[] obj : list) {
                    if(!map.entrySet().iterator().next().getKey().equals(obj[0] == null ? "" : obj[0].toString())){
                        map.put(obj[0] == null ? "" : obj[0].toString(), obj[1] == null ? "" : obj[1].toString());
                    }
                }
            }
            return map;
        } else {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("", "请选择角色");
            return map;
        }
    }

    @Override
    public String getAppRoleByUserId(String userId) {
        String roleId = "";
        List<Object[]> list = projectSyncForRepository.getRoleIdByUserId(userId);
        if(null != list && list.size()>0){
            for(Object[] obj : list){
                roleId = obj[0] == null ? "" : obj[0].toString();
            }
        }
        return roleId;
    }

    @Override
    public List<AgencyTreeDTO> getPorjectByCheck(String roleId, String userId) {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<String> projectIds = projectSyncForRepository.getProjectIdByRoleId(roleId, userId);
        List<AuthAgencyESEntity> agencyEntities = authAgencyRepository.getESAllAgencyList();
        List<String> projectIdList = new ArrayList<>();
        if(null !=projectIds && projectIds.size()>0){
            for(String projectId : projectIds){
                for(AuthAgencyESEntity authAgencyESEntity : agencyEntities){
                    String agencyPath = authAgencyESEntity.getAgencyPath().replace("/", ",").substring(1);
                    String str[] = agencyPath.split(",");
                    List<String> list = Arrays.asList(str);
                    if(list.contains(projectId)){
                        projectIdList.add(authAgencyESEntity.getAgencyId());
                    }
                }
            }
        }
        List<RoleStaffProjectMapESAppEntity> roleStaffProjectMapESAppEntityList =  projectSyncForRepository.getRoleStaffProjectMapEntity(roleId, userId);
        if(null != projectIdList && projectIdList.size()>0){
            if(agencyEntities!=null && agencyEntities.size()>0){
                for(AuthAgencyESEntity agencyEntity : agencyEntities){
                    for(String proId : projectIdList){
                       if(agencyEntity.getAgencyId().equals(proId)){
                           AgencyTreeDTO agencyTreeDTO = new AgencyTreeDTO();
                           agencyTreeDTO.setId(agencyEntity.getAgencyId());
                           agencyTreeDTO.setpId(agencyEntity.getParentId());
                           agencyTreeDTO.setName(agencyEntity.getAgencyName());
                           agencyTreeDTO.setAgencyType(agencyEntity.getAgencyType());
                           agencyTreeDTO.setIconOpen("../static/img/diy/1_open.png");
                           agencyTreeDTO.setIconClose("../static/img/diy/1_close.png");
                           agencyTreeDTO.setIcon("../static/img/diy/dpt.png");
                           if(agencyEntity.getAgencyType().equals("2")) {
                               agencyTreeDTO.setIcon("../static/img/diy/cpy.png");
                           }
                           agencyTreeDTO.setOpen("false");
                           if(agencyEntity.getLevel()<3){
                               agencyTreeDTO.setOpen("true");
                           }
                           if(null != roleStaffProjectMapESAppEntityList && roleStaffProjectMapESAppEntityList.size()>0){
                               for(RoleStaffProjectMapESAppEntity roleStaffProjectMapESAppEntity : roleStaffProjectMapESAppEntityList){
                                   if(roleStaffProjectMapESAppEntity.getAgencyId().equals(agencyEntity.getAgencyId())){
                                       agencyTreeDTO.setChecked(true);
                                       agencyTreeDTO.setOpen("true");
                                       break;
                                   }
                               }
                           }
                           agencyTreeDTOs.add(agencyTreeDTO);
                       }
                    }
                }
            }
        }
        return agencyTreeDTOs;
    }

    @Override
    public void saveUserRoleProject(UserAndRoleRelationDTO userAndRoleRelationDTO) {
        String [] str;//项目Id
        if(null != userAndRoleRelationDTO.getCategory() && !StringUtil.isEmpty(userAndRoleRelationDTO.getCategory())){
            str = userAndRoleRelationDTO.getCategory().split(",");
            projectSyncForRepository.deleteRoleStaffProjectMapESAppEntity(userAndRoleRelationDTO.getAuthStaffId());
        }else{
            str = null;
        }
        if(null != str){
            for(String pId : str){
                RoleStaffProjectMapESAppEntity roleStaffProjectMapESAppEntity = new RoleStaffProjectMapESAppEntity();
                roleStaffProjectMapESAppEntity.setAgencyId(pId);
                roleStaffProjectMapESAppEntity.setModifyOn(new Date());
                roleStaffProjectMapESAppEntity.setRoleId(userAndRoleRelationDTO.getAuthRoleIds());
                roleStaffProjectMapESAppEntity.setStaffId(userAndRoleRelationDTO.getAuthStaffId());
                roleStaffProjectMapESAppEntity.setState("1");
                projectSyncForRepository.saveRoleStaffProjectMapESAppEntity(roleStaffProjectMapESAppEntity);
            }
        }
    }
}