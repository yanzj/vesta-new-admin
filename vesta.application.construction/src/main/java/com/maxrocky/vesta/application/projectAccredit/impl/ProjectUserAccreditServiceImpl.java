package com.maxrocky.vesta.application.projectAccredit.impl;

import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.AccreditManageDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.UserAndRoleRelationDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.UserOrRoleDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.UserProjectRoleAccreditDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inf.UserAccreditService;
import com.maxrocky.vesta.application.projectAccredit.inf.ProjectUserAccreditService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.projectAccredit.repository.ProjectUserAccreditRepository;
import com.maxrocky.vesta.domain.repository.UserAccreditRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by hp on 2017/12/21.
 */
@Service
public class ProjectUserAccreditServiceImpl implements ProjectUserAccreditService {

    @Autowired
    ProjectUserAccreditRepository projectUserAccreditRepository;
    @Autowired
    AuthAgencyService authAgencyService;

    @Override
    public List<AgencyTreeDTO> getProjectAgencyListAll() {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<AuthAgencyESEntity> authAgencyESEntities = projectUserAccreditRepository.getProjectAgencyListAll();
        if(authAgencyESEntities!=null){
            for(AuthAgencyESEntity authAgencyESEntity:authAgencyESEntities){
                AgencyTreeDTO agencyTreeDTO = new AgencyTreeDTO();
                agencyTreeDTO.setId(authAgencyESEntity.getAgencyId());
                agencyTreeDTO.setpId(authAgencyESEntity.getParentId());
                agencyTreeDTO.setName(authAgencyESEntity.getAgencyName());
                agencyTreeDTO.setAgencyType(authAgencyESEntity.getAgencyType());
                agencyTreeDTO.setIconOpen("../static/img/diy/1_open.png");
                agencyTreeDTO.setIconClose("../static/img/diy/1_close.png");
                agencyTreeDTO.setIcon("../static/img/diy/dpt.png");
                agencyTreeDTO.setOpen("false");
                if(authAgencyESEntity.getLevel()<3){
                    agencyTreeDTO.setOpen("true");
                }
                agencyTreeDTOs.add(agencyTreeDTO);
            }
        }
        return agencyTreeDTOs;
    }

    @Override
    public List<AgencyTreeDTO> getProjectAgencyListAllByIds(List<String> updateProject) {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        if(null != updateProject && updateProject.size()>0){
            List<AuthAgencyESEntity> authAgencyESEntities = projectUserAccreditRepository.getProjectAgencyListAllByIds(updateProject);
            if(authAgencyESEntities!=null){
                for(AuthAgencyESEntity authAgencyESEntity:authAgencyESEntities){
                    AgencyTreeDTO agencyTreeDTO = new AgencyTreeDTO();
                    agencyTreeDTO.setId(authAgencyESEntity.getAgencyId());
                    agencyTreeDTO.setpId(authAgencyESEntity.getParentId());
                    agencyTreeDTO.setName(authAgencyESEntity.getAgencyName());
                    agencyTreeDTO.setAgencyType(authAgencyESEntity.getAgencyType());
                    agencyTreeDTO.setIconOpen("../static/img/diy/1_open.png");
                    agencyTreeDTO.setIconClose("../static/img/diy/1_close.png");
                    agencyTreeDTO.setIcon("../static/img/diy/dpt.png");
                    agencyTreeDTO.setOpen("false");
                    if(authAgencyESEntity.getLevel()<3){
                        agencyTreeDTO.setOpen("true");
                    }
                    agencyTreeDTOs.add(agencyTreeDTO);
                }
            }
        }
        return agencyTreeDTOs;
    }

    @Override
    public List<AccreditManageDTO> getAccreditManageListByCondition(AccreditManageDTO accreditManageDTO, WebPage webPage,UserInformationEntity userInformationEntity) {
        Map map = new HashMap();
        List<AccreditManageDTO> accreditManageDTOS = new ArrayList<>();
        List<AccreditManageDTO> flagDTO = new ArrayList<>();
        Boolean flag = true;
        map.put("agencyId","");
        map.put("staffName","");
        map.put("projectName","");
        map.put("userName","");
        map.put("sysName","");
        map.put("role","");
        if(!StringUtil.isEmpty(accreditManageDTO.getAgencyIdA())){
            map.put("agencyId",accreditManageDTO.getAgencyIdA());
            flag = false;
        }
        if(!StringUtil.isEmpty(accreditManageDTO.getStaffNameA())){
            map.put("staffName","%"+accreditManageDTO.getStaffNameA()+"%");
            flag = false;
        }
        if(!StringUtil.isEmpty(accreditManageDTO.getProjectNameA())){
            map.put("projectName","%"+accreditManageDTO.getProjectNameA()+"%");
            flag = false;
        }
        if(!StringUtil.isEmpty(accreditManageDTO.getUserNameA())){
            map.put("userName","%"+accreditManageDTO.getUserNameA()+"%");
            flag = false;
        }
        if(!StringUtil.isEmpty(accreditManageDTO.getSysNameA())){
            map.put("sysName","%"+accreditManageDTO.getSysNameA()+"%");
            flag = false;
        }
        if(!StringUtil.isEmpty(accreditManageDTO.getRoleNameA())){
            map.put("role","%"+accreditManageDTO.getRoleNameA()+"%");
            flag = false;
        }
        List<Object[]> list = projectUserAccreditRepository.getAccreditManageListByCondition(map,webPage);
        List<String> updateProject = this.getProjectAuthFunctionAndProjectIdByStaffId("ESH40020093",userInformationEntity.getStaffId(),"4");
        List<String> delProject = this.getProjectAuthFunctionAndProjectIdByStaffId("ESH40020094",userInformationEntity.getStaffId(),"4");
        for(Object[] obj : list){
            AccreditManageDTO accreditManageDTO1 =  new AccreditManageDTO();
            accreditManageDTO1.setStaffIdA(obj[0] == null ? "" : obj[0].toString());
            accreditManageDTO1.setUserNameA(obj[1] == null ? "" : obj[1].toString());
            accreditManageDTO1.setSysNameA(obj[2] == null ? "" : obj[2].toString());
            accreditManageDTO1.setSourceFromA(obj[3] == null ? "" : obj[3].toString());
            if("external".equals(accreditManageDTO1.getSourceFromA())){
                accreditManageDTO1.setSourceFromA("外部添加");
            }
            accreditManageDTO1.setAgencyNameA(obj[4] == null ? "" : obj[4].toString());
            accreditManageDTO1.setRoleNameA(obj[5] == null ? "" : obj[5].toString());
            accreditManageDTO1.setModifyOnA(obj[6] == null ? "" : DateUtils.format((Date) obj[6]));
            accreditManageDTO1.setStaffNameA(obj[7] == null ? "" : obj[7].toString());
            accreditManageDTO1.setPeojectRoleUserIdA(obj[8] == null ? "" : obj[8].toString());
            accreditManageDTO1.setAgencyIdA(obj[9] == null ? "" : obj[9].toString());
            if(updateProject.contains(accreditManageDTO1.getAgencyIdA())){
                accreditManageDTO1.setUpdateFunction("Y");
            }
            if(delProject.contains(accreditManageDTO1.getAgencyIdA())){
                accreditManageDTO1.setDelFunction("Y");
            }
            accreditManageDTOS.add(accreditManageDTO1);
        }
        return accreditManageDTOS;
    }

    @Override
    public List<AgencyTreeDTO> getProjectOAAgencyMessage() {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<AuthOuterAgencyEntity> authOuterAgencyEntities = projectUserAccreditRepository.getProjectAuthOuterAgency();
        if(null != authOuterAgencyEntities){
            AgencyTreeDTO agencyTreeDTO;
            for(AuthOuterAgencyEntity authOuterAgencyEntity : authOuterAgencyEntities){
                agencyTreeDTO = new AgencyTreeDTO();
                agencyTreeDTO.setId(authOuterAgencyEntity.getAgencyId());
                agencyTreeDTO.setpId(authOuterAgencyEntity.getParentId());
                agencyTreeDTO.setName(authOuterAgencyEntity.getAgencyName());
                agencyTreeDTO.setIconOpen("../static/img/diy/1_open.png");
                agencyTreeDTO.setIconClose("../static/img/diy/1_close.png");
                agencyTreeDTO.setIcon("../static/img/diy/dpt.png");
                agencyTreeDTO.setOpen("false");
                if(authOuterAgencyEntity.getLevel()<3){
                    agencyTreeDTO.setOpen("true");
                }
                if("0".equals(authOuterAgencyEntity.getStatus())){
                    agencyTreeDTO.setIsHidden("true");
                }
                agencyTreeDTOs.add(agencyTreeDTO);
            }
        }
        return agencyTreeDTOs;
    }

    @Override
    public List<AgencyTreeDTO> getOAAgencyMessageProject() {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<AuthOuterAgencyEntity> authOuterAgencyEntities = projectUserAccreditRepository.getOwnerAuthOuterAgency();
        if(null != authOuterAgencyEntities){
            AgencyTreeDTO agencyTreeDTO;
            for(AuthOuterAgencyEntity authOuterAgencyEntity : authOuterAgencyEntities){
                agencyTreeDTO = new AgencyTreeDTO();
                agencyTreeDTO.setId(authOuterAgencyEntity.getAgencyId());
                agencyTreeDTO.setpId(authOuterAgencyEntity.getParentId());
                agencyTreeDTO.setName(authOuterAgencyEntity.getAgencyName());
                agencyTreeDTO.setIconOpen("../static/img/diy/1_open.png");
                agencyTreeDTO.setIconClose("../static/img/diy/1_close.png");
                agencyTreeDTO.setIcon("../static/img/diy/dpt.png");
                agencyTreeDTO.setOpen("false");
                if(authOuterAgencyEntity.getLevel()<3){
                    agencyTreeDTO.setOpen("true");
                }
                if("0".equals(authOuterAgencyEntity.getStatus())){
                    agencyTreeDTO.setIsHidden("true");
                }
                agencyTreeDTOs.add(agencyTreeDTO);
            }
        }
        return agencyTreeDTOs;
    }

    @Override
    public void deleteProjectAccredit(String id) {
        RoleStaffProjectMapESEntity roleStaffProjectMapESEntity = projectUserAccreditRepository.getRoleStaffProjectMapById(id);
        if(null != roleStaffProjectMapESEntity){
            roleStaffProjectMapESEntity.setState("0");
            roleStaffProjectMapESEntity.setModifyOn(new Date());
            projectUserAccreditRepository.savePrjectRoleStaffProjectMap(roleStaffProjectMapESEntity);
        }
    }

    @Override
    public List<AgencyTreeDTO> getUserByAgencyId(String agencyId,String category) {
        String getcategory="";
        if(!StringUtil.isEmpty(category)){
            if("3".equals(category)){
                getcategory="st";
            }else if("2".equals(category)){
                getcategory="es";
            }
            else if("1".equals(category)){
                getcategory="qc";
            }
        }else {
            getcategory="es";
        }
        List<AgencyTreeDTO> agencyTreeDTOS = new ArrayList<>();
        List<Object[]> list = projectUserAccreditRepository.getOuterUserByAgencyId(agencyId,getcategory);
        AgencyTreeDTO agencyTreeDTO;
        if(null != list && list.size()>0){
            for(Object[] obj : list){
                agencyTreeDTO = new AgencyTreeDTO();
                agencyTreeDTO.setId(obj[0] == null ? "" : obj[0].toString());
                agencyTreeDTO.setName(obj[1] == null ? "" : obj[1].toString());
                if(null != obj[2]){
                    agencyTreeDTO.setName(obj[1] == null ? "" : obj[1].toString()+"  （" + obj[2].toString() + "）");
                } else if (null != obj[3]){
                    agencyTreeDTO.setName(obj[1] == null ? "" : obj[1].toString()+"  （" + obj[3].toString() + "）");
                }
                agencyTreeDTO.setpId(agencyId);
                agencyTreeDTO.setType("3");
                agencyTreeDTO.setIsParent("false");
                agencyTreeDTOS.add(agencyTreeDTO);
            }
        }
        return agencyTreeDTOS;
    }

    @Override
    public List<AgencyTreeDTO> getProjectRoleByAgencyId(String agencyId) {
        List<AgencyTreeDTO> agencyTreeDTOS = new ArrayList<>();
        List<AuthRoleseEntity> authRoleseEntities = projectUserAccreditRepository.getProjectRoleByAgencyId(agencyId);
        AuthAgencyESEntity authAgencyESEntity = projectUserAccreditRepository.getAuthAgencyByAgencyId(agencyId);
        AgencyTreeDTO agencyTreeDTO;
        for(AuthRoleseEntity authRoleseEntity : authRoleseEntities){
            agencyTreeDTO = new AgencyTreeDTO();
            agencyTreeDTO.setId(authRoleseEntity.getRoleId() + "|" + agencyId);
            agencyTreeDTO.setpId(agencyId);
            agencyTreeDTO.setName(authRoleseEntity.getRoleName()+" （" +authAgencyESEntity.getAgencyName()+ ")");
            agencyTreeDTO.setType("2");
            agencyTreeDTO.setIsParent("false");
            agencyTreeDTOS.add(agencyTreeDTO);
        }
        return agencyTreeDTOS;
    }

    @Override
    public List<AgencyTreeDTO> getOwnerRoleByAgencyId(String agencyId) {
        List<AgencyTreeDTO> agencyTreeDTOS = new ArrayList<>();
        List<AuthRoleseEntity> authRoleseEntities = projectUserAccreditRepository.getOwnerRoleByAgencyId(agencyId);
        AuthAgencyESEntity authAgencyESEntity = projectUserAccreditRepository.getAuthAgencyByAgencyId(agencyId);
        AgencyTreeDTO agencyTreeDTO;
        for(AuthRoleseEntity authRoleseEntity : authRoleseEntities){
            agencyTreeDTO = new AgencyTreeDTO();
            agencyTreeDTO.setId(authRoleseEntity.getRoleId() + "|" + agencyId);
            agencyTreeDTO.setpId(agencyId);
            agencyTreeDTO.setName(authRoleseEntity.getRoleName()+" （" +authAgencyESEntity.getAgencyName()+ ")");
            agencyTreeDTO.setType("2");
            agencyTreeDTO.setIsParent("false");
            agencyTreeDTOS.add(agencyTreeDTO);
        }
        return agencyTreeDTOS;
    }

    @Override
    public List<UserOrRoleDTO> getUserByName(String staffName,String category) {
        List<UserOrRoleDTO> userOrRoleDTOS = new ArrayList<UserOrRoleDTO>();
        UserOrRoleDTO userOrRoleDTO;
        String getcategory="";
        if(!StringUtil.isEmpty(category)){
            if("3".equals(category)){
                getcategory="st";
            }else if("2".equals(category)){
                getcategory="es";
            }
            else if("1".equals(category)){
                getcategory="qc";
            }
        }else {
            getcategory="es";
        }
        List<Object[]> list = projectUserAccreditRepository.getUserByName(staffName,getcategory);
        if(null != list && list.size()>0){
            for(Object[] obj : list) {
                userOrRoleDTO = new UserOrRoleDTO();
                userOrRoleDTO.setOuterStaffId(obj[0] == null ? "" : obj[0].toString());
                userOrRoleDTO.setOuterStaffName(obj[1] == null ? "" : obj[1].toString());
                if(null != obj[2]){
                    userOrRoleDTO.setOuterStaffName(obj[1] == null ? "" : obj[1].toString()+"  （" + obj[2].toString() + "）");
                }else if (null != obj[3]){
                    userOrRoleDTO.setOuterStaffName(obj[1] == null ? "" : obj[1].toString()+"  （" + obj[3].toString() + "）");
                }
                userOrRoleDTOS.add(userOrRoleDTO);
            }
        }
        return userOrRoleDTOS;
    }

    @Override
    public List<UserOrRoleDTO> getOwnerUserByName(String staffName) {
        List<UserOrRoleDTO> userOrRoleDTOS = new ArrayList<UserOrRoleDTO>();
        UserOrRoleDTO userOrRoleDTO;
        List<Object[]> list = projectUserAccreditRepository.getOwnerUserByName(staffName);
        if(null != list && list.size()>0){
            for(Object[] obj : list) {
                userOrRoleDTO = new UserOrRoleDTO();
                userOrRoleDTO.setOuterStaffId(obj[0] == null ? "" : obj[0].toString());
                userOrRoleDTO.setOuterStaffName(obj[1] == null ? "" : obj[1].toString());
                if(null != obj[2]){
                    userOrRoleDTO.setOuterStaffName(obj[1] == null ? "" : obj[1].toString()+"  （" + obj[2].toString() + "）");
                }else if (null != obj[3]){
                    userOrRoleDTO.setOuterStaffName(obj[1] == null ? "" : obj[1].toString()+"  （" + obj[3].toString() + "）");
                }
                userOrRoleDTOS.add(userOrRoleDTO);
            }
        }
        return userOrRoleDTOS;
    }

    @Override
    public List<UserOrRoleDTO> getRolerByName(String roleName) {
        List<UserOrRoleDTO> userOrRoleDTOS = new ArrayList<UserOrRoleDTO>();
        UserOrRoleDTO userOrRoleDTO;
        List<Object[]> list = projectUserAccreditRepository.getRoleByName(roleName);
        if(null != list && list.size()>0){
            for(Object[] obj : list) {
                userOrRoleDTO = new UserOrRoleDTO();
                userOrRoleDTO.setOuterStaffId(obj[0] == null ? "" : obj[0].toString());
                if(null != obj[2]){
                    userOrRoleDTO.setOuterStaffId(obj[0] == null ? "" : obj[0].toString() + "|" + obj[2].toString());
                }
                userOrRoleDTO.setOuterStaffName(obj[1] == null ? "" : obj[1].toString());
                if(null != obj[3]){
                    userOrRoleDTO.setOuterStaffName(obj[1] == null ? "" : obj[1].toString() + " (" + obj[3].toString() +")");
                }
                userOrRoleDTOS.add(userOrRoleDTO);
            }
        }
        return userOrRoleDTOS;
    }

    @Override
    public List<UserOrRoleDTO> getRolerByName(String roleName, List<String> updateProject) {
        List<UserOrRoleDTO> userOrRoleDTOS = new ArrayList<UserOrRoleDTO>();
        UserOrRoleDTO userOrRoleDTO;
        List<Object[]> list = projectUserAccreditRepository.getRoleByName(roleName,updateProject);
        if(null != list && list.size()>0){
            for(Object[] obj : list) {
                userOrRoleDTO = new UserOrRoleDTO();
                userOrRoleDTO.setOuterStaffId(obj[0] == null ? "" : obj[0].toString());
                if(null != obj[2]){
                    userOrRoleDTO.setOuterStaffId(obj[0] == null ? "" : obj[0].toString() + "|" + obj[2].toString());
                }
                userOrRoleDTO.setOuterStaffName(obj[1] == null ? "" : obj[1].toString());
                if(null != obj[3]){
                    userOrRoleDTO.setOuterStaffName(obj[1] == null ? "" : obj[1].toString() + " （" + obj[3].toString() +")");
                }
                userOrRoleDTOS.add(userOrRoleDTO);
            }
        }
        return userOrRoleDTOS;
    }

    @Override
    public List<UserOrRoleDTO> getOwnerRolerByName(String roleName, List<String> updateProject) {
        List<UserOrRoleDTO> userOrRoleDTOS = new ArrayList<UserOrRoleDTO>();
        UserOrRoleDTO userOrRoleDTO;
        List<Object[]> list = projectUserAccreditRepository.getOwnerRolerByName(roleName,updateProject);
        if(null != list && list.size()>0){
            for(Object[] obj : list) {
                userOrRoleDTO = new UserOrRoleDTO();
                userOrRoleDTO.setOuterStaffId(obj[0] == null ? "" : obj[0].toString());
                if(null != obj[2]){
                    userOrRoleDTO.setOuterStaffId(obj[0] == null ? "" : obj[0].toString() + "|" + obj[2].toString());
                }
                userOrRoleDTO.setOuterStaffName(obj[1] == null ? "" : obj[1].toString());
                if(null != obj[3]){
                    userOrRoleDTO.setOuterStaffName(obj[1] == null ? "" : obj[1].toString() + " （" + obj[3].toString() +")");
                }
                userOrRoleDTOS.add(userOrRoleDTO);
            }
        }
        return userOrRoleDTOS;
    }


    @Override
    public void saveOrUpdateUserRoleRelation(UserAndRoleRelationDTO userAndRoleRelationDTO) {
        RoleStaffProjectMapESEntity roleStaffProjectMapESEntity = new RoleStaffProjectMapESEntity();
        String [] staffId;//人员Id
        String [] str;//角色id与项目Id
        if(null != userAndRoleRelationDTO.getAuthStaffId() && !StringUtil.isEmpty(userAndRoleRelationDTO.getAuthStaffId())){
            staffId = userAndRoleRelationDTO.getAuthStaffId().split(",");
        }else{
            staffId = null;
        }
        if(null != userAndRoleRelationDTO.getAuthRoleIds() && !StringUtil.isEmpty(userAndRoleRelationDTO.getAuthRoleIds())){
            str = userAndRoleRelationDTO.getAuthRoleIds().split(",");
            if(null != staffId){
                for(String sId : staffId){
                    for(String rId : str){
                        String [] roleId = rId.split("\\|");
                        roleStaffProjectMapESEntity.setState("1");
                        roleStaffProjectMapESEntity.setStaffId(sId);
                        roleStaffProjectMapESEntity.setRoleId(roleId[0].toString());
                        roleStaffProjectMapESEntity.setAgencyId(roleId[1].toString());
                        projectUserAccreditRepository.saveOrUpdateProjectRoleStaff(roleStaffProjectMapESEntity);
                    }
                }
            }
        }
    }

    @Override
    public ApiResult saveOrUpdateUserRoleRelation_2(List<UserProjectRoleAccreditDTO> userList) {
        if( null != userList && userList.size()>0){
            for(UserProjectRoleAccreditDTO userProjectRoleAccreditDTO : userList){
                String [] staffId;//人员Id
                String [] roleId;//角色id
                String [] proId;//项目Id
                if(null != userProjectRoleAccreditDTO.getuId() && !StringUtil.isEmpty(userProjectRoleAccreditDTO.getuId())){
                    staffId = userProjectRoleAccreditDTO.getuId().split(",");
                }else{
                    staffId = null;
                }
                if(null != userProjectRoleAccreditDTO.getrId() && !StringUtil.isEmpty(userProjectRoleAccreditDTO.getrId())){
                    roleId = userProjectRoleAccreditDTO.getrId().split(",");
                }else{
                    roleId = null;
                }
                if(null != userProjectRoleAccreditDTO.getProId() && !StringUtil.isEmpty(userProjectRoleAccreditDTO.getProId())){
                    proId = userProjectRoleAccreditDTO.getProId().split(",");
                }else{
                    proId = null;
                }
                if(null !=staffId && null != roleId && null != proId ){
                    for(String sId : staffId){
                        RoleStaffProjectMapESEntity roleStaffProjectMapESEntity = new RoleStaffProjectMapESEntity();
                        roleStaffProjectMapESEntity.setState("1");
                        roleStaffProjectMapESEntity.setStaffId(sId);
                        roleStaffProjectMapESEntity.setRoleId(roleId[0].toString());
                        roleStaffProjectMapESEntity.setAgencyId(proId[0].toString());
                        roleStaffProjectMapESEntity.setModifyOn(new Date());
                        projectUserAccreditRepository.saveOrUpdateProjectRoleStaff(roleStaffProjectMapESEntity);
                    }
                }
            }
            return new SuccessApiResult("ok");
        } else {
            return new SuccessApiResult("没有可操作数据");
        }
    }

    @Override
    public List<String> getProjectAuthFunctionAndProjectIdByStaffId(String function, String staffId, String level) {
        List<String> roleIdList = projectUserAccreditRepository.getProjectAuthFunctionAndProjectIdByStaffId(function,staffId,level);
        List<AuthAgencyESEntity> authAgencyESEntities = projectUserAccreditRepository.getProjectAgencyListAll();
        List<String> roleIdList1 = new ArrayList<String>();
        if(roleIdList!=null){
            for(String agencyId : roleIdList){
                for(AuthAgencyESEntity authAgencyESEntity : authAgencyESEntities){
                    String agencyPath = authAgencyESEntity.getAgencyPath().replace("/", ",").substring(1);
                    String str[] = agencyPath.split(",");
                    List<String> list = Arrays.asList(str);
                    if(list.contains(agencyId)){
                        roleIdList1.add(authAgencyESEntity.getAgencyId());
                    }
                }
            }
            if(null != roleIdList1 && roleIdList.size()>0){
                roleIdList.addAll(roleIdList1);
            }
            return roleIdList;
        }
        return null;
    }
}
