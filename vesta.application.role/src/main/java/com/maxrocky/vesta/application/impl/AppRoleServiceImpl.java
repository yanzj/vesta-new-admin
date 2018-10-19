package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.dto.adminDTO.AppMenuDTO;
import com.maxrocky.vesta.application.dto.adminDTO.AppRoleDTO;
import com.maxrocky.vesta.application.inf.AppRoleService;
import com.maxrocky.vesta.domain.model.AppRoleEntity;
import com.maxrocky.vesta.domain.model.RoleRolebuttonmapEntity;
import com.maxrocky.vesta.domain.model.RoleViewmodelEntity;
import com.maxrocky.vesta.domain.repository.AppRoleRepository;
import com.maxrocky.vesta.domain.repository.RoleButtonMapRepository;
import com.maxrocky.vesta.domain.repository.RoleViewmodelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/5/11.
 */
@Service
public class AppRoleServiceImpl implements AppRoleService {
    @Autowired
    private AppRoleRepository appRoleRepository;
    @Autowired
    private RoleViewmodelRepository roleViewmodelRepository;
    @Autowired
    private RoleButtonMapRepository roleButtonMapRepository;

    @Override
    public List<AppRoleDTO> getAppRoleList() {
        List<AppRoleEntity> appRoleEntities = appRoleRepository.roleList();
        List<AppRoleDTO> appRoleDTOs = new ArrayList<AppRoleDTO>();
        if(appRoleEntities!=null){
            AppRoleDTO appRoleDTO;
            for(AppRoleEntity appRoleEntity:appRoleEntities){
                appRoleDTO = new AppRoleDTO();
                appRoleDTO.setAppRoleId(appRoleEntity.getAppRoleId());
                appRoleDTO.setAppRoleName(appRoleEntity.getAppRoleName());
                appRoleDTOs.add(appRoleDTO);
            }
        }
        return appRoleDTOs;
    }

    @Override
    public List<AppRoleDTO> appRoleMenus() {
        List<AppRoleEntity> appRoleEntities = appRoleRepository.roleList();
        List<AppRoleDTO> appRoleDTOs = new ArrayList<AppRoleDTO>();
        if(appRoleEntities!=null){
            AppRoleDTO appRoleDTO;
            for(AppRoleEntity appRoleEntity:appRoleEntities){
                appRoleDTO = new AppRoleDTO();
                appRoleDTO.setAppRoleId(appRoleEntity.getAppRoleId());
                appRoleDTO.setAppRoleName(appRoleEntity.getAppRoleName());
                List<RoleViewmodelEntity> roleViewmodelEntities = roleViewmodelRepository.appRoleMenus(appRoleEntity.getAppRoleId());
                List<AppMenuDTO> appMenuDTOs = new ArrayList<AppMenuDTO>();
                if(roleViewmodelEntities!=null){
                    AppMenuDTO appMenuDTO;
                    for(RoleViewmodelEntity roleViewmodelEntity:roleViewmodelEntities){
                        appMenuDTO = new AppMenuDTO();
                        appMenuDTO.setMenuId(roleViewmodelEntity.getMenuId());
                        appMenuDTO.setMenuName(roleViewmodelEntity.getMenuDescription());
                        appMenuDTOs.add(appMenuDTO);
                    }
                    appRoleDTO.setAppMenuDTOList(appMenuDTOs);
                }
                appRoleDTOs.add(appRoleDTO);
            }
        }
        return appRoleDTOs;
    }

    @Override
    public List<AppRoleDTO> appRoleMenuList(String appRoleSetId) {
        List<AppRoleDTO> appRoleDTOs = new ArrayList<AppRoleDTO>();
        List<RoleRolebuttonmapEntity> roleRolebuttonmapEntities = roleButtonMapRepository.getButtonMapList(appRoleSetId);
        if(roleRolebuttonmapEntities!=null){
            AppRoleDTO appRoleDTO;
            for(RoleRolebuttonmapEntity roleRolebuttonmapEntity:roleRolebuttonmapEntities){
                appRoleDTO = new AppRoleDTO();
                appRoleDTO.setAppRoleId(roleRolebuttonmapEntity.getRoleId()+roleRolebuttonmapEntity.getMenuId());
                appRoleDTOs.add(appRoleDTO);
            }
        }
        return appRoleDTOs;
    }
}
