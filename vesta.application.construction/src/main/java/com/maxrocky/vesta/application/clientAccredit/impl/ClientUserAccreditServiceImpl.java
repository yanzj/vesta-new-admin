package com.maxrocky.vesta.application.clientAccredit.impl;

import com.maxrocky.vesta.application.clientAccredit.inf.ClientUserAccreditService;
import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.AccreditManageDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.UserAndRoleRelationDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.UserOrRoleDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.UserProjectRoleAccreditDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.clientAccredit.repository.ClientUserAccreditRepository;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.projectAccredit.repository.ProjectUserAccreditRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by yuanyn on 2018/05/10.
 */
@Service
public class ClientUserAccreditServiceImpl implements ClientUserAccreditService {

    @Autowired
    ClientUserAccreditRepository clientUserAccreditRepository;
    @Autowired
    AuthAgencyService authAgencyService;

    @Override
    public List<AgencyTreeDTO> getClientAgencyListAll() {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<AuthAgencyQCEntity> authAgencyQCEntities = clientUserAccreditRepository.getClientAgencyListAll();
        if(authAgencyQCEntities!=null){
            for(AuthAgencyQCEntity authAgencyQCEntity:authAgencyQCEntities){
                AgencyTreeDTO agencyTreeDTO = new AgencyTreeDTO();
                agencyTreeDTO.setId(authAgencyQCEntity.getAgencyId());
                agencyTreeDTO.setpId(authAgencyQCEntity.getParentId());
                agencyTreeDTO.setName(authAgencyQCEntity.getAgencyName());
                agencyTreeDTO.setAgencyType(authAgencyQCEntity.getAgencyType());
                agencyTreeDTO.setIconOpen("../static/img/diy/1_open.png");
                agencyTreeDTO.setIconClose("../static/img/diy/1_close.png");
                agencyTreeDTO.setIcon("../static/img/diy/dpt.png");
                agencyTreeDTO.setOpen("false");
                if(authAgencyQCEntity.getLevel()<3){
                    agencyTreeDTO.setOpen("true");
                }
                agencyTreeDTOs.add(agencyTreeDTO);
            }
        }
        return agencyTreeDTOs;
    }

    @Override
    public List<AgencyTreeDTO> getClientAgencyListAllByIds(List<String> updateProject) {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        if(null != updateProject && updateProject.size()>0){
            List<AuthAgencyQCEntity> authAgencyQCEntities = clientUserAccreditRepository.getClientAgencyListAllByIds(updateProject);
            if(authAgencyQCEntities!=null){
                for(AuthAgencyQCEntity authAgencyQCEntity:authAgencyQCEntities){
                    AgencyTreeDTO agencyTreeDTO = new AgencyTreeDTO();
                    agencyTreeDTO.setId(authAgencyQCEntity.getAgencyId());
                    agencyTreeDTO.setpId(authAgencyQCEntity.getParentId());
                    agencyTreeDTO.setName(authAgencyQCEntity.getAgencyName());
                    agencyTreeDTO.setAgencyType(authAgencyQCEntity.getAgencyType());
                    agencyTreeDTO.setIconOpen("../static/img/diy/1_open.png");
                    agencyTreeDTO.setIconClose("../static/img/diy/1_close.png");
                    agencyTreeDTO.setIcon("../static/img/diy/dpt.png");
                    agencyTreeDTO.setOpen("false");
                    if(authAgencyQCEntity.getLevel()<3){
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
        List<Object[]> list = clientUserAccreditRepository.getAccreditManageListByCondition(map,webPage);
        List<String> updateProject = this.getClientAuthFunctionAndProjectIdByStaffId("QCH40010119",userInformationEntity.getStaffId(),"4");
        List<String> delProject = this.getClientAuthFunctionAndProjectIdByStaffId("QCH40010120",userInformationEntity.getStaffId(),"4");
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
    public List<AgencyTreeDTO> getClientOAAgencyMessage() {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<AuthOuterAgencyEntity> authOuterAgencyEntities = clientUserAccreditRepository.getClientAuthOuterAgency();
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
    public List<AgencyTreeDTO> getOAAgencyMessageClient() {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<AuthOuterAgencyEntity> authOuterAgencyEntities = clientUserAccreditRepository.getOwnerAuthOuterAgency();
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
                agencyTreeDTOs.add(agencyTreeDTO);
            }
        }
        return agencyTreeDTOs;
    }

    @Override
    public void deleteClientAccredit(String id) {
        RoleStaffProjectMapQCEntity roleStaffProjectMapQCEntity = clientUserAccreditRepository.getRoleStaffProjectMapById(id);
        if(null != roleStaffProjectMapQCEntity){
            roleStaffProjectMapQCEntity.setState("0");
            roleStaffProjectMapQCEntity.setModifyOn(new Date());
            clientUserAccreditRepository.saveClientRoleStaffProjectMap(roleStaffProjectMapQCEntity);
        }
    }

    @Override
    public List<AgencyTreeDTO> getUserByAgencyId(String agencyId,String category) {
        List<AgencyTreeDTO> agencyTreeDTOS = new ArrayList<>();
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
            getcategory="qc";
        }
        List<Object[]> list = clientUserAccreditRepository.getOuterUserByAgencyId(agencyId,getcategory);
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
    public List<AgencyTreeDTO> getClientRoleByAgencyId(String agencyId) {
        List<AgencyTreeDTO> agencyTreeDTOS = new ArrayList<>();
        List<AuthRoleseEntity> authRoleseEntities = clientUserAccreditRepository.getClientRoleByAgencyId(agencyId);
        AuthAgencyQCEntity authAgencyQCEntity = clientUserAccreditRepository.getAuthAgencyByAgencyId(agencyId);
        AgencyTreeDTO agencyTreeDTO;
        for(AuthRoleseEntity authRoleseEntity : authRoleseEntities){
            agencyTreeDTO = new AgencyTreeDTO();
            agencyTreeDTO.setId(authRoleseEntity.getRoleId() + "|" + agencyId);
            agencyTreeDTO.setpId(agencyId);
            agencyTreeDTO.setName(authRoleseEntity.getRoleName()+" （" +authAgencyQCEntity.getAgencyName()+ ")");
            agencyTreeDTO.setType("2");
            agencyTreeDTO.setIsParent("false");
            agencyTreeDTOS.add(agencyTreeDTO);
        }
        return agencyTreeDTOS;
    }

    @Override
    public List<AgencyTreeDTO> getOwnerRoleByAgencyId(String agencyId) {
        List<AgencyTreeDTO> agencyTreeDTOS = new ArrayList<>();
        List<AuthRoleseEntity> authRoleseEntities = clientUserAccreditRepository.getOwnerRoleByAgencyId(agencyId);
        AuthAgencyQCEntity authAgencyQCEntity = clientUserAccreditRepository.getAuthAgencyByAgencyId(agencyId);
        AgencyTreeDTO agencyTreeDTO;
        for(AuthRoleseEntity authRoleseEntity : authRoleseEntities){
            agencyTreeDTO = new AgencyTreeDTO();
            agencyTreeDTO.setId(authRoleseEntity.getRoleId() + "|" + agencyId);
            agencyTreeDTO.setpId(agencyId);
            agencyTreeDTO.setName(authRoleseEntity.getRoleName()+" （" +authAgencyQCEntity.getAgencyName()+ ")");
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
            getcategory="qc";
        }
        List<Object[]> list = clientUserAccreditRepository.getUserByName(staffName,getcategory);
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
        List<Object[]> list = clientUserAccreditRepository.getOwnerUserByName(staffName);
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
        List<Object[]> list = clientUserAccreditRepository.getRoleByName(roleName);
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
        List<Object[]> list = clientUserAccreditRepository.getRoleByName(roleName,updateProject);
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
        List<Object[]> list = clientUserAccreditRepository.getOwnerRolerByName(roleName,updateProject);
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
        RoleStaffProjectMapQCEntity roleStaffProjectMapQCEntity = new RoleStaffProjectMapQCEntity();
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
                        roleStaffProjectMapQCEntity.setState("1");
                        roleStaffProjectMapQCEntity.setStaffId(sId);
                        roleStaffProjectMapQCEntity.setRoleId(roleId[0].toString());
                        roleStaffProjectMapQCEntity.setAgencyId(roleId[1].toString());
                        clientUserAccreditRepository.saveOrUpdateProjectRoleStaff(roleStaffProjectMapQCEntity);
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
                        RoleStaffProjectMapQCEntity roleStaffProjectMapQCEntity = new RoleStaffProjectMapQCEntity();
                        roleStaffProjectMapQCEntity.setState("1");
                        roleStaffProjectMapQCEntity.setStaffId(sId);
                        roleStaffProjectMapQCEntity.setRoleId(roleId[0].toString());
                        roleStaffProjectMapQCEntity.setAgencyId(proId[0].toString());
                        roleStaffProjectMapQCEntity.setModifyOn(new Date());
                        clientUserAccreditRepository.saveOrUpdateProjectRoleStaff(roleStaffProjectMapQCEntity);
                    }
                }
            }
            return new SuccessApiResult("ok");
        } else {
            return new SuccessApiResult("没有可操作数据");
        }
    }

    @Override
    public List<String> getClientAuthFunctionAndProjectIdByStaffId(String function, String staffId, String level) {
        List<String> roleIdList = clientUserAccreditRepository.getClientAuthFunctionAndProjectIdByStaffId(function,staffId,level);
        List<AuthAgencyQCEntity> authAgencyQCEntities = clientUserAccreditRepository.getClientAgencyListAll();
        List<String> roleIdList1 = new ArrayList<String>();
        if(roleIdList!=null){
            for(String agencyId : roleIdList){
                for(AuthAgencyQCEntity authAgencyQCEntity : authAgencyQCEntities){
                    String agencyPath = authAgencyQCEntity.getAgencyPath().replace("/", ",").substring(1);
                    String str[] = agencyPath.split(",");
                    List<String> list = Arrays.asList(str);
                    if(list.contains(agencyId)){
                        roleIdList1.add(authAgencyQCEntity.getAgencyId());
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
