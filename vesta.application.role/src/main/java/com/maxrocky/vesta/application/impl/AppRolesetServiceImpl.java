package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.dto.adminDTO.AppRoleDTO;
import com.maxrocky.vesta.application.dto.adminDTO.AppRolesetDTO;
import com.maxrocky.vesta.application.dto.adminDTO.RoleSetDTO;
import com.maxrocky.vesta.application.dto.adminDTO.StaffNameDTO;
import com.maxrocky.vesta.application.inf.AppRolesetService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.AppRolesetEntity;
import com.maxrocky.vesta.domain.model.RoleDataEntity;
import com.maxrocky.vesta.domain.model.RoleRoleanthorityEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.AppRoleSetRepository;
import com.maxrocky.vesta.domain.repository.RoleAnthorityRepository;
import com.maxrocky.vesta.domain.repository.RoleDataRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhanghj on 2016/2/21.
 */
@Service
public class AppRolesetServiceImpl implements AppRolesetService{

    @Autowired
    private AppRoleSetRepository appRoleSetRepository;
    @Autowired
    private RoleAnthorityRepository roleAnthorityRepository;
    @Autowired
    private RoleDataRepository roleDataRepository;

    /**
     * 获取app员工角色列表
     * @return
     */
    @Override
    public List<AppRolesetDTO> listAppRoleSet() {
        List<AppRolesetEntity> appRolesetEntities = appRoleSetRepository.listAppRoleset();
        List<AppRolesetDTO> appRolesetDTOs = new ArrayList<>();
        AppRolesetDTO appRolesetDTO_0 = new AppRolesetDTO();
        appRolesetDTO_0.setAppSetId("0");
        appRolesetDTO_0.setAppSetName("-----请选择App角色-----");
        appRolesetDTOs.add(appRolesetDTO_0);
        if (appRolesetEntities.size()>0){
            for (AppRolesetEntity appRolesetEntity:appRolesetEntities){
                AppRolesetDTO appRolesetDTO = new AppRolesetDTO();
                appRolesetDTO.setAppSetId(appRolesetEntity.getAppSetId());
                appRolesetDTO.setAppSetName(appRolesetEntity.getAppSetName());
                appRolesetDTOs.add(appRolesetDTO);
            }
        }
        return appRolesetDTOs;
    }

    @Override
    public List<RoleSetDTO> getAppRoleSets(String roleSetName, WebPage webPage) {
        List<AppRolesetEntity> appRolesetEntityList = appRoleSetRepository.getAppRoleSets(roleSetName,webPage);
        List<RoleSetDTO> roleSetDTOs = new ArrayList<RoleSetDTO>();
        if(appRolesetEntityList!=null){
            RoleSetDTO roleSetDTO;
            for(AppRolesetEntity appRolesetEntity:appRolesetEntityList){
                roleSetDTO = new RoleSetDTO();
                roleSetDTO.setAppRoleSetId(appRolesetEntity.getAppSetId());
                roleSetDTO.setAppRoleSetName(appRolesetEntity.getAppSetName());
                int num = roleAnthorityRepository.getStaffCount(appRolesetEntity.getAppSetId());
                roleSetDTO.setNumber(String.valueOf(num));
                roleSetDTO.setModifyTime(DateUtils.format(appRolesetEntity.getModifyOn()));
                roleSetDTOs.add(roleSetDTO);
            }
        }
        return roleSetDTOs;
    }

    @Override
    public RoleSetDTO getAppRoleSetDetail(String appRoleSetId) {
        RoleSetDTO roleSetDTO = new RoleSetDTO();
        if(appRoleSetId!=null){
            AppRolesetEntity appRolesetEntity = appRoleSetRepository.getAppRoleSetDetail(appRoleSetId);
            roleSetDTO.setAppRoleSetId(appRolesetEntity.getAppSetId());
            roleSetDTO.setAppRoleSetName(appRolesetEntity.getAppSetName());
            roleSetDTO.setModifyTime(DateUtils.format(appRolesetEntity.getModifyOn()));
        }
        return roleSetDTO;
    }

    @Override
    public void addAppRoleSet(AppRoleDTO appRoleDTO) {
        AppRolesetEntity appRolesetEntity = new AppRolesetEntity();
        appRolesetEntity.setAppSetId(appRoleDTO.getAppRoleSetId());
        appRolesetEntity.setAppSetName(appRoleDTO.getAppRoleSetName());
        appRolesetEntity.setAppSetState(AppRolesetEntity.STATE_ON);
        appRolesetEntity.setAppSetAllot(AppRolesetEntity.ALLOT_YES);
        appRolesetEntity.setCreateOn(new Date());
        appRolesetEntity.setModifyOn(new Date());
        if(!StringUtil.isEmpty(appRoleDTO.getAgencys())){
            String[] ids = appRoleDTO.getAgencys().split(",");
            for(int i=0;i<ids.length;i++){
                RoleDataEntity roleDataEntity = new RoleDataEntity();
                roleDataEntity.setId(IdGen.uuid());
                roleDataEntity.setStatus("1");
                roleDataEntity.setDataType("2");
                roleDataEntity.setDataId(appRolesetEntity.getAppSetId());
                roleDataEntity.setAuthorityType("1");
                roleDataEntity.setAuthorityId(ids[i]);
                roleDataRepository.addRoleData(roleDataEntity);  //保存角色与机构的关系
            }
        }
        if(!StringUtil.isEmpty(appRoleDTO.getStaffs())){
            String[] sIds = appRoleDTO.getStaffs().split(",");
            for(int j=0;j<sIds.length;j++){
                RoleRoleanthorityEntity roleRoleanthorityEntity = new RoleRoleanthorityEntity();
                roleRoleanthorityEntity.setUserId(IdGen.uuid());
                roleRoleanthorityEntity.setStaffId(sIds[j]);
                roleRoleanthorityEntity.setAppSetId(appRolesetEntity.getAppSetId());
                roleAnthorityRepository.roleAdduser(roleRoleanthorityEntity);  //保存角色与人的关系
            }
        }
        appRoleSetRepository.addAppRoleSet(appRolesetEntity);
    }

    @Override
    public void updateAppRoleSet(AppRoleDTO appRoleDTO) {
        AppRolesetEntity appRolesetEntity = appRoleSetRepository.getAppRoleSetDetail(appRoleDTO.getAppRoleSetId());
        appRolesetEntity.setAppSetName(appRoleDTO.getAppRoleSetName());
        appRolesetEntity.setModifyOn(new Date());

        RoleDataEntity roleDataEntity1 = new RoleDataEntity();
        roleDataEntity1.setAuthorityType("1");
        roleDataEntity1.setDataType("2");
        roleDataEntity1.setDataId(appRolesetEntity.getAppSetId());
        roleDataRepository.delAgencyRole(roleDataEntity1);//先清除原有的数据关系
        if(!StringUtil.isEmpty(appRoleDTO.getAgencys())) {
            String[] ids = appRoleDTO.getAgencys().split(",");
            for (int i = 0; i < ids.length; i++) {
                RoleDataEntity roleDataEntity = new RoleDataEntity();
                roleDataEntity.setId(IdGen.uuid());
                roleDataEntity.setStatus("1");
                roleDataEntity.setDataType("2");
                roleDataEntity.setDataId(appRolesetEntity.getAppSetId());
                roleDataEntity.setAuthorityType("1");
                roleDataEntity.setAuthorityId(ids[i]);
                roleDataEntity.setModifyTime(new Date());
                roleDataRepository.addRoleData(roleDataEntity);  //保存角色与机构的关系
            }
        }

        roleAnthorityRepository.delRoleanthority(appRolesetEntity.getAppSetId());//先清除原有的数据关系
        if(!StringUtil.isEmpty(appRoleDTO.getStaffs())){
            String[] sIds = appRoleDTO.getStaffs().split(",");
            for(int j=0;j<sIds.length;j++){
                RoleRoleanthorityEntity roleRoleanthorityEntity = new RoleRoleanthorityEntity();
                roleRoleanthorityEntity.setUserId(IdGen.uuid());
                roleRoleanthorityEntity.setStaffId(sIds[j]);
                roleRoleanthorityEntity.setAppSetId(appRolesetEntity.getAppSetId());
                roleAnthorityRepository.roleAdduser(roleRoleanthorityEntity);  //保存角色与人的关系
            }
        }
        appRoleSetRepository.updateAppRoleSet(appRolesetEntity);
    }

    @Override
    public void delAppRoleSet(String appRoleSetId) {
        roleAnthorityRepository.delRoleanthority(appRoleSetId);        //删除角色之前  先清除该角色下与用户的关联关系
        AppRolesetEntity appRolesetEntity = appRoleSetRepository.getAppRoleSetDetail(appRoleSetId);
        appRolesetEntity.setAppSetState(AppRolesetEntity.STATE_OFF);
        appRoleSetRepository.updateAppRoleSet(appRolesetEntity);
    }

    @Override
    public List<RoleSetDTO> allAppRoleSets() {
        List<AppRolesetEntity> appRolesetEntityList = appRoleSetRepository.listAppRoleset();
        List<RoleSetDTO> roleSetDTOs = new ArrayList<RoleSetDTO>();
        if(appRolesetEntityList!=null){
            RoleSetDTO roleSetDTO;
            for(AppRolesetEntity appRolesetEntity:appRolesetEntityList){
                roleSetDTO = new RoleSetDTO();
                roleSetDTO.setAppRoleSetId(appRolesetEntity.getAppSetId());
                roleSetDTO.setAppRoleSetName(appRolesetEntity.getAppSetName());
                roleSetDTOs.add(roleSetDTO);
            }
        }
        return roleSetDTOs;
    }

    @Override
    public List<StaffNameDTO> getSetsStaff(String appRoleSetId) {
        List<StaffNameDTO> staffNameDTOList = new ArrayList<StaffNameDTO>();
        List<UserPropertyStaffEntity> userPropertyStaffEntities = appRoleSetRepository.getSetsStaff(appRoleSetId);
        if(userPropertyStaffEntities!=null){
            StaffNameDTO staffNameDTO;
            for(UserPropertyStaffEntity userPropertyStaffEntity:userPropertyStaffEntities){
                staffNameDTO = new StaffNameDTO();
                staffNameDTO.setStaffId(userPropertyStaffEntity.getStaffId());
                staffNameDTO.setStaffName(userPropertyStaffEntity.getStaffName()+" ("+userPropertyStaffEntity.getUserName()+")");
                staffNameDTOList.add(staffNameDTO);
            }
        }
        return staffNameDTOList;
    }

    @Override
    public List<StaffNameDTO> getSetOutStaff(StaffNameDTO staffNameDTO) {
        UserPropertyStaffEntity userPropertyStaffEntity = new UserPropertyStaffEntity();
        userPropertyStaffEntity.setStaffName(staffNameDTO.getStaffName());
        userPropertyStaffEntity.setRoleSetId(staffNameDTO.getRoleSetId());
        List<UserPropertyStaffEntity> userPropertyStaffEntities = appRoleSetRepository.getSetOutStaff(userPropertyStaffEntity);
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
    public ApiResult saveStaffRoleSet(StaffNameDTO staffNameDTO) {
        RoleRoleanthorityEntity roleRoleanthorityEntity = new RoleRoleanthorityEntity();
        RoleDataEntity roleDataEntity = new RoleDataEntity();
        roleRoleanthorityEntity.setUserId(IdGen.uuid());
        roleRoleanthorityEntity.setAppSetId(staffNameDTO.getRoleSetId());
        roleRoleanthorityEntity.setStaffId(staffNameDTO.getStaffId());
        roleDataEntity.setAuthorityId(staffNameDTO.getStaffId());
        roleDataEntity.setAuthorityType("3");
        roleDataEntity.setId(staffNameDTO.getRoleSetId());
        roleDataEntity.setDataType("2");
        List<RoleDataEntity> roleDataEntities = roleDataRepository.getRoleDateOne(roleDataEntity);
        if(roleDataEntities!=null&&roleDataEntities.size()>0){
            return new ErrorApiResult(22,"您已添加过该数据了");
        }
        List<RoleRoleanthorityEntity> roleRoleanthorityEntities = roleAnthorityRepository.getRoleanthoritys(staffNameDTO.getStaffId(),staffNameDTO.getRoleSetId());
        if(roleRoleanthorityEntities!=null&&roleRoleanthorityEntities.size()>0){
            return new ErrorApiResult(22,"您已添加过该数据了");
        }
        roleAnthorityRepository.roleAdduser(roleRoleanthorityEntity);
        return new SuccessApiResult();
    }

    @Override
    public ApiResult delStaffRoleSet(StaffNameDTO staffNameDTO) {
        roleAnthorityRepository.delStaffRoleSet(staffNameDTO.getStaffId(),staffNameDTO.getRoleSetId());
        return new SuccessApiResult();
    }

    @Override
    public List<AppRolesetDTO> getAppRoleSetsByStaffId(String staffId) {
        List<AppRolesetEntity> appRolesetEntityList = appRoleSetRepository.getRoleNames(staffId);
        List<AppRolesetEntity> appRolesetEntitys = roleDataRepository.getRoleSetFromData(staffId);
        List<AppRolesetDTO> appRolesetDTOs = new ArrayList<AppRolesetDTO>();
        if(appRolesetEntityList!=null){
            AppRolesetDTO appRolesetDTO;
            for(AppRolesetEntity appRolesetEntity:appRolesetEntityList){
                appRolesetDTO = new AppRolesetDTO();
                appRolesetDTO.setAppSetName(appRolesetEntity.getAppSetName());
                appRolesetDTO.setAppSetId(appRolesetEntity.getAppSetId());
                appRolesetDTOs.add(appRolesetDTO);

            }
        }
        if(appRolesetEntitys!=null){
            AppRolesetDTO appRolesetDTO;
            for(AppRolesetEntity appRolesetEntity:appRolesetEntitys){
                appRolesetDTO = new AppRolesetDTO();
                appRolesetDTO.setAppSetName(appRolesetEntity.getAppSetName());
                appRolesetDTO.setAppSetId(appRolesetEntity.getAppSetId());
                appRolesetDTOs.add(appRolesetDTO);
            }
        }
        return appRolesetDTOs;
    }
}
