package com.maxrocky.vesta.application.impl;


import com.maxrocky.vesta.application.dto.adminDTO.AgencyAdminDTO;
import com.maxrocky.vesta.application.dto.adminDTO.RoleRoleDTO;
import com.maxrocky.vesta.application.dto.adminDTO.RoleRolesetDTO;
import com.maxrocky.vesta.application.dto.adminDTO.StaffNameDTO;
import com.maxrocky.vesta.application.inf.RoleRoleService;
import com.maxrocky.vesta.application.inf.RoleRolesetService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.SqlDateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/17.
 */
@Service
public class RoleRolesetServiceImpl implements RoleRolesetService {

    @Resource
    private RoleRolesetRepository roleRolesetRepository;

    @Resource
    private RoleRoleService roleRoleService;

    @Resource
    private RoleRolesetmapRepository roleRolesetmapRepository;

    @Autowired
    RoleAnthorityRepository roleAnthorityRepository;

    @Autowired
    RoleDataRepository roleDataRepository;
    @Autowired
    AgencyRepository agencyRepository;

    /**
     * 新增角色信息
     * @param roleRolesetDTO
     * @return
     */
    @Override
    public boolean saveRoleSet(RoleRolesetDTO roleRolesetDTO) {

        RoleRolesetEntity roleRolesetEntity = new RoleRolesetEntity();
        roleRolesetEntity.setSetId(roleRolesetDTO.getSetId());
        roleRolesetEntity.setRoledesc(roleRolesetDTO.getRoledesc());
        roleRolesetEntity.setMakeDate(SqlDateUtils.getDate());
        roleRolesetEntity.setMakeTime(SqlDateUtils.getTime());
        roleRolesetEntity.setModifyDate(SqlDateUtils.getDate());
        roleRolesetEntity.setModifyTime(SqlDateUtils.getTime());
        roleRolesetEntity.setOperator(roleRolesetDTO.getOperator());
        roleRolesetEntity.setSetState("01");
        roleRolesetEntity.setSetType("3");
        roleRolesetEntity.setCompanyId(null);
        roleRolesetEntity.setIsallot(roleRolesetDTO.getIsallot());

        if(!StringUtil.isEmpty(roleRolesetDTO.getAgencys())){
            String[] ids = roleRolesetDTO.getAgencys().split(",");
            RoleDataEntity roleDataEntity;
            for(String id:ids){
                roleDataEntity = new RoleDataEntity();
                roleDataEntity.setId("A"+IdGen.uuid());
                roleDataEntity.setDataType("2");
                roleDataEntity.setDataId(roleRolesetDTO.getSetId());
                roleDataEntity.setAuthorityType("1");
                roleDataEntity.setAuthorityId(id);
                roleDataEntity.setPermission("admin");
                roleDataEntity.setModifyTime(new Date());
                roleDataEntity.setStatus("1");
                roleDataRepository.addDumpRoleData(roleDataEntity);
            }
        }

        if(!StringUtil.isEmpty(roleRolesetDTO.getStaffs())){
            String[] ids = roleRolesetDTO.getStaffs().split(",");
            RoleRoleanthorityEntity roleRoleanthorityEntity;
            for(String id:ids){
                roleRoleanthorityEntity = new RoleRoleanthorityEntity();
                roleRoleanthorityEntity.setUserId(IdGen.uuid());
                roleRoleanthorityEntity.setSetId(roleRolesetDTO.getSetId());
                roleRoleanthorityEntity.setStaffId(id);
                roleAnthorityRepository.roleAdduser(roleRoleanthorityEntity);
            }
        }

        return  roleRolesetRepository.saveRoleSet(roleRolesetEntity);
    }


    /**
     * 通过角色Id查找角色
     * @param rolesetId
     * @return
     */
    @Override
    public RoleRolesetDTO getRolesetById(String rolesetId) {
        RoleRolesetEntity roleRolesetEntity = roleRolesetRepository.getRolesetById(rolesetId);
        RoleRolesetDTO roleRolesetDTO = new RoleRolesetDTO();
            if (roleRolesetEntity!=null) {
                List<AgencyAdminDTO> agencyListDTOs = new ArrayList<AgencyAdminDTO>();
                List<StaffNameDTO> staffNameDTOList = new ArrayList<StaffNameDTO>();
                roleRolesetDTO.setSetId(roleRolesetEntity.getSetId());
                roleRolesetDTO.setRoledesc(roleRolesetEntity.getRoledesc());
                roleRolesetDTO.setMakeDate(roleRolesetEntity.getMakeDate());
                roleRolesetDTO.setMakeTime(roleRolesetEntity.getMakeTime());
                roleRolesetDTO.setModifyDate(roleRolesetEntity.getModifyDate());
                roleRolesetDTO.setModifyTime(roleRolesetEntity.getModifyTime());
                roleRolesetDTO.setOperator(roleRolesetEntity.getOperator());
                roleRolesetDTO.setSetState(roleRolesetEntity.getSetState());
                roleRolesetDTO.setSetType(roleRolesetEntity.getSetType());
                roleRolesetDTO.setCompanyId(roleRolesetEntity.getCompanyId());
                roleRolesetDTO.setIsallot(roleRolesetEntity.getIsallot());
                List<AgencyEntity> agencyEntities = roleDataRepository.getAgencyByRoleSet(rolesetId);//根据角色ID获取关联的组织机构
                List<UserPropertyStaffEntity> userPropertyStaffEntities = roleRolesetRepository.getInStaffs(rolesetId);//根据角色ID获取关联的人
                if(agencyEntities!=null){
                    AgencyAdminDTO agencyAdminDTO;
                    for(AgencyEntity agencyEntity:agencyEntities){
                        agencyAdminDTO = new AgencyAdminDTO();
                        agencyAdminDTO.setAgencyId(agencyEntity.getAgencyId());
                        agencyAdminDTO.setAgencyName(agencyEntity.getAgencyName());
                        if(!StringUtil.isEmpty(agencyEntity.getParentId())){
                            AgencyEntity agencyEntity1 = agencyRepository.getAgencyDetail(agencyEntity.getParentId());
                            if(agencyEntity1!=null){
                                agencyAdminDTO.setAgencyName(agencyEntity.getAgencyName()+"-"+agencyEntity1.getAgencyName());
                            }
                        }
                        agencyListDTOs.add(agencyAdminDTO);
                    }
                }
                if(userPropertyStaffEntities!=null){
                    StaffNameDTO staffNameDTO;
                    for(UserPropertyStaffEntity userPropertyStaffEntity:userPropertyStaffEntities){
                        staffNameDTO = new StaffNameDTO();
                        staffNameDTO.setStaffId(userPropertyStaffEntity.getStaffId());
                        staffNameDTO.setStaffName(userPropertyStaffEntity.getStaffName());
                        staffNameDTOList.add(staffNameDTO);
                    }
                }
                roleRolesetDTO.setAgencyList(agencyListDTOs);  //角色关联的组织机构
                roleRolesetDTO.setStaffList(staffNameDTOList);   //角色关联的人
                return roleRolesetDTO;
            }else {
                return null;
            }
    }

    /**
     * 获得角色集合
     * @return
     */
    @Override
    public List<RoleRolesetDTO> listRoleset(WebPage webPage,String setId,String roleName) {
        List<RoleRolesetDTO> roleRolesetDTOs = new ArrayList<>();
        List<RoleRolesetEntity> roleRolesetEntities = roleRolesetRepository.listRoleset(webPage,setId,roleName);
        if (roleRolesetEntities.size()>0){
            for (RoleRolesetEntity roleRolesetEntity:roleRolesetEntities){
                RoleRolesetDTO roleRolesetDTO = new RoleRolesetDTO();

                roleRolesetDTO.setSetId(roleRolesetEntity.getSetId());
                roleRolesetDTO.setRoledesc(roleRolesetEntity.getRoledesc());
                roleRolesetDTO.setMakeDate(roleRolesetEntity.getMakeDate());
                roleRolesetDTO.setMakeTime(roleRolesetEntity.getMakeTime());
                roleRolesetDTO.setModifyDate(roleRolesetEntity.getModifyDate());
                roleRolesetDTO.setModifyTime(roleRolesetEntity.getModifyTime());
                roleRolesetDTO.setOperator(roleRolesetEntity.getOperator());
                roleRolesetDTO.setSetState(roleRolesetEntity.getSetState());
                roleRolesetDTO.setSetType(roleRolesetEntity.getSetType());
                roleRolesetDTO.setCompanyId(roleRolesetEntity.getCompanyId());
                roleRolesetDTO.setIsallot(roleRolesetEntity.getIsallot());
                roleRolesetDTOs.add(roleRolesetDTO);
            }
        }
        return roleRolesetDTOs;
    }

    @Override
    public List<RoleRolesetDTO> getRoleSets(String roleSetName, WebPage webPage) {
        List<RoleRolesetDTO> roleRolesetDTOs = new ArrayList<>();
        return null;
    }

    /**
     * 获取可分配角色列表
     * @return
     */
    @Override
    public List<RoleRolesetDTO> listRoleset(String setId) {
        WebPage webPage = null;
        List<RoleRolesetDTO> roleRolesetDTOs = new ArrayList<>();
        RoleRolesetDTO roleRolesetDTO_0 = new RoleRolesetDTO();
        roleRolesetDTO_0.setSetId("0");
        roleRolesetDTO_0.setRoledesc("-----请选择管理角色-----");
        roleRolesetDTOs.add(roleRolesetDTO_0);
        List<RoleRolesetEntity> roleRolesetEntities = roleRolesetRepository.listRoleset(webPage,setId,"");
        if (roleRolesetEntities.size()>0){
            for(RoleRolesetEntity roleRolesetEntity:roleRolesetEntities){
                if (roleRolesetEntity.getIsallot().equals("1")){
                    RoleRolesetDTO roleRolesetDTO = new RoleRolesetDTO();
                    roleRolesetDTO.setSetId(roleRolesetEntity.getSetId());
                    roleRolesetDTO.setRoledesc(roleRolesetEntity.getRoledesc());
                    roleRolesetDTOs.add(roleRolesetDTO);
                }
            }
        }
        return roleRolesetDTOs;
    }

    /**
     * 角色更新
     * @param roleRolesetDTO
     * @return
     */
    @Override
    public boolean updateRoleset(RoleRolesetDTO roleRolesetDTO) {
        RoleRolesetEntity roleRolesetEntity = new RoleRolesetEntity();
        //编辑样式还没有，需要设计图！！！！！！！！！！！！！！！！
        roleRolesetEntity = roleRolesetRepository.getRolesetById(roleRolesetDTO.getSetId());
        if (roleRolesetEntity!=null) {
            roleRolesetEntity.setRoledesc(roleRolesetDTO.getRoledesc());

            roleRolesetEntity.setIsallot(roleRolesetDTO.getIsallot());
            roleRolesetRepository.updateRoleset(roleRolesetEntity);

            roleDataRepository.delAdminAgencyRole(roleRolesetDTO.getSetId());
            if(!StringUtil.isEmpty(roleRolesetDTO.getAgencys())){
                String[] ids = roleRolesetDTO.getAgencys().split(",");
                RoleDataEntity roleDataEntity;
                for(String id:ids){
                    roleDataEntity = new RoleDataEntity();
                    roleDataEntity.setId("A"+IdGen.uuid());
                    roleDataEntity.setDataType("2");
                    roleDataEntity.setDataId(roleRolesetDTO.getSetId());
                    roleDataEntity.setAuthorityType("1");
                    roleDataEntity.setAuthorityId(id);
                    roleDataEntity.setPermission("admin");
                    roleDataEntity.setModifyTime(new Date());
                    roleDataEntity.setStatus("1");
                    roleDataRepository.addDumpRoleData(roleDataEntity);
                }
            }

            roleAnthorityRepository.delstaffRole(roleRolesetDTO.getSetId());
            if(!StringUtil.isEmpty(roleRolesetDTO.getStaffs())){
                String[] ids = roleRolesetDTO.getStaffs().split(",");
                RoleRoleanthorityEntity roleRoleanthorityEntity;
                for(String id:ids){
                    roleRoleanthorityEntity = new RoleRoleanthorityEntity();
                    roleRoleanthorityEntity.setUserId(IdGen.uuid());
                    roleRoleanthorityEntity.setSetId(roleRolesetDTO.getSetId());
                    roleRoleanthorityEntity.setStaffId(id);
                    roleAnthorityRepository.roleAdduser(roleRoleanthorityEntity);
                }
            }
            return true;
        }
        else
        {
            return false;
        }

    }

    /**
     * 角色删除
     * @param roleRolesetDTO
     * @return
     */
    @Override
    public boolean deleteRoleset(RoleRolesetDTO roleRolesetDTO) {
        RoleRolesetEntity roleRolesetEntity = new RoleRolesetEntity();
        roleRolesetEntity = roleRolesetRepository.getRolesetById(roleRolesetDTO.getSetId());

        roleRolesetEntity.setSetState("0");
        return roleRolesetRepository.updateRoleset(roleRolesetEntity);
    }

    /**
     * 权限管理页面列表
     * @param setId
     *
     * @return
     */
    @Override
    public List<RoleRoleDTO> listRoleSetMap(String setId) {
        //获取所有权限列表
        List<RoleRoleDTO> roleRoleDTOs = roleRoleService.listRoleRole();

        if (roleRoleDTOs.size()>0){
            for(RoleRoleDTO roleRoleDTO :roleRoleDTOs){
//                for (RoleRolesetmapEntity roleRolesetmapEntity:roleRolesetmapEntities){
//                    if (roleRoleDTO.getRoleId()==roleRolesetmapEntity.getRolRoleId()){//判断在所有的权限中，如果在该角色对应的权限关系里存在，在赋值checkout为 1
//                        roleRoleDTO.setCheckOut("1");
//                        roleRoleDTO.setRoleSetMapId(roleRolesetmapEntity.getRolesetid());
//                        roleRoleDTO.setRoleSetId(roleRolesetmapEntity.getSetId());
//                    }
//                    else {
//                        roleRoleDTO.setCheckOut("0");
//                    }
//                }
                RoleRolesetmapEntity roleRolesetmapEntity = roleRolesetmapRepository.getRolesetMap(setId,roleRoleDTO.getRoleId());
                if(roleRolesetmapEntity!=null){
                    roleRoleDTO.setCheckOut("1");
                    roleRoleDTO.setRoleSetMapId(roleRolesetmapEntity.getRolesetid());
                    roleRoleDTO.setRoleSetId(roleRolesetmapEntity.getSetId());
                }
                else {
                    roleRoleDTO.setCheckOut("0");
                }

            }
        }

        return roleRoleDTOs;
    }

    /**
     * 更新角色权限
     * @param roleSetId
     * @param RoleId
     * @return
     */
    @Override
    public int updateRoleMap(String roleSetId, String RoleId) {
        int count = 0;
        List<RoleRolesetmapEntity> roleRolesetmapEntityList = roleRolesetmapRepository.listRolesetMapBySetId(roleSetId);
        if (roleRolesetmapEntityList.size()>0){
            for (RoleRolesetmapEntity roleRolesetmapEntity:roleRolesetmapEntityList){
                roleRolesetmapRepository.deleteRoleRolesetMap(roleRolesetmapEntity);
            }
        }
        if (RoleId!=null) {
            String[] strings = RoleId.split(",");
            for (int i = 0; i < strings.length; i++) {
                RoleRolesetmapEntity roleRolesetmapEntity = new RoleRolesetmapEntity();
                roleRolesetmapEntity.setRolesetid(IdGen.uuid());
                roleRolesetmapEntity.setRolRoleId(strings[i]);
                roleRolesetmapEntity.setSetId(roleSetId);
                if (roleRolesetmapRepository.saveRolesetMap(roleRolesetmapEntity)) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public void saveRoleMap(String roleSetId, String staffId) {
        RoleRoleanthorityEntity roleRoleanthorityEntity = new RoleRoleanthorityEntity();
        roleRoleanthorityEntity.setUserId(IdGen.uuid());
        roleRoleanthorityEntity.setStaffId(staffId);
        roleRoleanthorityEntity.setSetId(roleSetId);
        roleAnthorityRepository.roleAdduser(roleRoleanthorityEntity);
    }

    @Override
    public void delRoleMap(String roleSetId, String staffId) {
        roleAnthorityRepository.delAdminStaffRoleSet(staffId,roleSetId);
    }

    @Override
    public List<StaffNameDTO> getOutStaffs(StaffNameDTO staffNameDTO) {
        UserPropertyStaffEntity userPropertyStaffEntity = new UserPropertyStaffEntity();
        userPropertyStaffEntity.setStaffName(staffNameDTO.getStaffName());
        userPropertyStaffEntity.setRoleSetId(staffNameDTO.getRoleSetId());
        List<UserPropertyStaffEntity> userPropertyStaffEntities = roleRolesetRepository.getOutStaffs(userPropertyStaffEntity);
        List<StaffNameDTO> staffNameDTOList = new ArrayList<StaffNameDTO>();
        if(userPropertyStaffEntities!=null){
            StaffNameDTO staffNameDTO1;
            for(UserPropertyStaffEntity userPropertyStaffEntity1:userPropertyStaffEntities){
                staffNameDTO1 = new StaffNameDTO();
                staffNameDTO1.setStaffId(userPropertyStaffEntity1.getStaffId());
                staffNameDTO1.setStaffName(userPropertyStaffEntity1.getStaffName() + "  （" + userPropertyStaffEntity1.getUserName() + "）");
                staffNameDTO1.setUserName(userPropertyStaffEntity1.getUserName());
                staffNameDTOList.add(staffNameDTO1);
            }
        }
        return staffNameDTOList;
    }

    @Override
    public List<StaffNameDTO> getInStaffs(String roleSetId) {
        List<UserPropertyStaffEntity> userPropertyStaffEntities = roleRolesetRepository.getInStaffs(roleSetId);
        List<StaffNameDTO> staffNameDTOList = new ArrayList<StaffNameDTO>();
        if(userPropertyStaffEntities!=null){
            StaffNameDTO staffNameDTO1;
            for(UserPropertyStaffEntity userPropertyStaffEntity1:userPropertyStaffEntities){
                staffNameDTO1 = new StaffNameDTO();
                staffNameDTO1.setStaffId(userPropertyStaffEntity1.getStaffId());
                staffNameDTO1.setStaffName(userPropertyStaffEntity1.getStaffName() + "  （" + userPropertyStaffEntity1.getUserName() + "）");
                staffNameDTO1.setUserName(userPropertyStaffEntity1.getUserName());
                staffNameDTOList.add(staffNameDTO1);
            }
        }
        return staffNameDTOList;
    }
}
