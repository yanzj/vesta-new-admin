package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.dto.adminDTO.RoleMenuDTO;
import com.maxrocky.vesta.application.inf.RoleMenuService;
import com.maxrocky.vesta.domain.model.RoleRoleEntity;
import com.maxrocky.vesta.domain.model.RoleRolebuttonmapEntity;
import com.maxrocky.vesta.domain.model.RoleViewmodelEntity;
import com.maxrocky.vesta.domain.repository.RoleButtonMapRepository;
import com.maxrocky.vesta.domain.repository.RoleRoleRepository;
import com.maxrocky.vesta.domain.repository.RoleViewmodelRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.SqlDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghj on 2016/4/18.
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    RoleRoleRepository roleRoleRepository;

    @Autowired
    RoleViewmodelRepository roleViewmodelRepository;

    @Autowired
    RoleButtonMapRepository roleButtonMapRepository;

    @Override
    public String addFirMenu(RoleMenuDTO roleMenuDTO) {
        RoleViewmodelEntity roleViewmodelEntity =roleViewmodelRepository.getLastFirVeiwModel();
        RoleViewmodelEntity newMenu = new RoleViewmodelEntity();
        String roleMenuId = "";
        String roleMenuOrder = "";
        if (roleViewmodelEntity==null||roleViewmodelEntity.getMenuId()==null){
            roleMenuId = "000100000000";
            newMenu.setMenuId(roleMenuId);
            roleMenuOrder = "1";
            newMenu.setMenuorder(roleMenuOrder);
        }else {
            roleMenuOrder = (Integer.parseInt(roleViewmodelEntity.getMenuorder())+1)+"";
            if (roleMenuOrder.length()==1){
                roleMenuId = "000"+roleMenuOrder+"00000000";
            }
            else if (roleMenuOrder.length()==2){
                roleMenuId = "00"+roleMenuOrder+"00000000";
            }
            newMenu.setMenuId(roleMenuId);
            newMenu.setMenuorder(roleMenuOrder);
        }
        newMenu.setChildFlag("N");
        newMenu.setMenuDescription(roleMenuDTO.getRoleMenuDesc());
        newMenu.setMenuName(roleMenuDTO.getRoleMenuName());
        newMenu.setMenuState("01");
        newMenu.setMenulevel("1");
        newMenu.setOperator("crj");
        newMenu.setParantmenuid("");
        newMenu.setRunscript("#");
        newMenu.setOwner("property");
        roleViewmodelRepository.addViewModel(newMenu);
        return null;
    }

    @Override
    public String addSecMenu(RoleMenuDTO roleMenuDTO) {
        //---------------------------------------------------------添加菜单
        RoleViewmodelEntity parMenu = roleViewmodelRepository.getModelById(roleMenuDTO.getRoleMenuParId());
        RoleViewmodelEntity roleViewmodelEntity =roleViewmodelRepository.getLastSecViewModel(roleMenuDTO.getRoleMenuParId());
        RoleViewmodelEntity newMenu = new RoleViewmodelEntity();
        String roleMenuId = "";
        String roleMenuOrder = "";
        if (roleViewmodelEntity==null||roleViewmodelEntity.getMenuId()==null){
            roleMenuId = parMenu.getMenuId().substring(0,4)+"0001"+"0000";
            roleMenuOrder = "1";
        }else {
            roleMenuOrder = (Integer.parseInt(roleViewmodelEntity.getMenuorder())+1)+"";
            if (roleMenuOrder.length()==1){
                roleMenuId = parMenu.getMenuId().substring(0, 4)+"000"+roleMenuOrder+"0000";
            }
            else if (roleMenuOrder.length()==2){
                roleMenuId = parMenu.getMenuId().substring(0,4)+"00"+roleMenuOrder+"0000";
            }
        }
        newMenu.setMenuId(roleMenuId);
        newMenu.setMenuorder(roleMenuOrder);
        newMenu.setChildFlag("Y");
        newMenu.setMenuDescription(roleMenuDTO.getRoleMenuDesc());
        newMenu.setMenuName(roleMenuDTO.getRoleMenuName());
        newMenu.setMenuState("01");
        newMenu.setMenulevel("2");
        newMenu.setOperator("java");
        newMenu.setParantmenuid(roleMenuDTO.getRoleMenuParId());
        newMenu.setRunscript("#");
        newMenu.setOwner("property");
        roleViewmodelRepository.addViewModel(newMenu);
        //-------------------------------------------------添加权限
        RoleRoleEntity newRole = new RoleRoleEntity();
        newRole.setRoleId(roleMenuId);
        newRole.setMakeDate(SqlDateUtils.getDate());
        newRole.setMakeTime(SqlDateUtils.getTime());
        newRole.setModifyDate(SqlDateUtils.getDate());
        newRole.setModifyTime(SqlDateUtils.getTime());
        newRole.setOperator("java");
        newRole.setRoleDescription(roleMenuDTO.getRoleMenuDesc());
        newRole.setRoledesc("1");
        newRole.setRoleName(roleMenuDTO.getRoleMenuName());
        newRole.setRoleSetId("0002");
        roleRoleRepository.addRole(newRole);
        //------------------------------------------------添加权限菜单关系
        RoleRolebuttonmapEntity newMap = new RoleRolebuttonmapEntity();
        newMap.setRoleId(roleMenuId);
        newMap.setMenuId(roleMenuId);
        newMap.setButtonId("none");
        newMap.setButtonState("01");
        newMap.setMakeDate(SqlDateUtils.getDate());
        newMap.setMakeTime(SqlDateUtils.getTime());
        roleButtonMapRepository.addButtonMap(newMap);
        //-------------------------------------------------添加权限与父级菜单关系
        RoleRolebuttonmapEntity parMap = new RoleRolebuttonmapEntity();
        parMap.setRoleId(roleMenuId);
        parMap.setMenuId(parMenu.getMenuId());
        parMap.setButtonId("none");
        parMap.setButtonState("01");
        parMap.setMakeDate(SqlDateUtils.getDate());
        parMap.setMakeTime(SqlDateUtils.getTime());
        roleButtonMapRepository.addButtonMap(parMap);
        return null;
    }

    public  List<RoleMenuDTO> listFirMenu(WebPage webPage){
        List<RoleViewmodelEntity> menus = roleViewmodelRepository.listMenu("999",webPage);
        List<RoleMenuDTO> roleMenuDTOs = new ArrayList<>();
        if (menus.size()>0){
            for (RoleViewmodelEntity menuDto:menus){
                RoleMenuDTO roleMenuDTO = new RoleMenuDTO();
                roleMenuDTO.setRoleMenuId(menuDto.getMenuId());
                roleMenuDTO.setRoleMenuDesc(menuDto.getMenuDescription());
                roleMenuDTO.setRoleMenuName(menuDto.getMenuName());
                roleMenuDTOs.add(roleMenuDTO);
            }
        }
        return roleMenuDTOs;
    }

    @Override
    public List<RoleMenuDTO> listSecMenu(String parId,WebPage webPage) {
        List<RoleViewmodelEntity> menus = roleViewmodelRepository.listMenu(parId,webPage);
        List<RoleMenuDTO> roleMenuDTOs = new ArrayList<>();
        if (menus.size()>0){
            for (RoleViewmodelEntity menuDto:menus){
                RoleMenuDTO roleMenuDTO = new RoleMenuDTO();
                roleMenuDTO.setRoleMenuId(menuDto.getMenuId());
                roleMenuDTO.setRoleMenuDesc(menuDto.getMenuDescription());
                roleMenuDTO.setRoleMenuName(menuDto.getMenuName());
                roleMenuDTOs.add(roleMenuDTO);
            }
        }
        return roleMenuDTOs;
    }
}
