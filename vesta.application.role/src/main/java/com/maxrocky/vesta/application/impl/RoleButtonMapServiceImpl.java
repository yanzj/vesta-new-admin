package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.dto.adminDTO.AppRoleDTO;
import com.maxrocky.vesta.application.inf.RoleButtonMapService;
import com.maxrocky.vesta.domain.model.AppRolesetMapEntity;
import com.maxrocky.vesta.domain.model.RoleRolebuttonmapEntity;
import com.maxrocky.vesta.domain.repository.RoleButtonMapRepository;
import com.maxrocky.vesta.domain.repository.RoleRolesetmapRepository;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.SqlDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;


/**
 * Created by chen on 2016/5/11.
 */
@Service
public class RoleButtonMapServiceImpl implements RoleButtonMapService {
    @Autowired
    RoleButtonMapRepository roleButtonMapRepository;
    @Autowired
    RoleRolesetmapRepository roleRolesetmapRepository;

    @Override
    public void addRoleButtonMap(AppRoleDTO appRoleDTO) {
        roleButtonMapRepository.delButtonMap(appRoleDTO.getAppRoleSetId());
        if(appRoleDTO.getAppRoleId()!=null){
            String[] ids = appRoleDTO.getAppRoleId().split(",");
            RoleRolebuttonmapEntity roleRolebuttonmapEntity = new RoleRolebuttonmapEntity();
            roleRolebuttonmapEntity.setButtonId(appRoleDTO.getAppRoleSetId());  //此处作buttonId为角色ID
            roleRolebuttonmapEntity.setMakeDate(SqlDateUtils.getDate());
            roleRolebuttonmapEntity.setMakeTime(SqlDateUtils.getTime());
            roleRolebuttonmapEntity.setButtonState("01");
            HashSet<String> roleIds= new HashSet<String>();
            for(int i=0;i<ids.length;i=i+2){          //此处根据页面层级关系数据解析
                roleRolebuttonmapEntity.setRoleId(ids[i]);
                roleRolebuttonmapEntity.setMenuId(ids[i + 1]);
                roleIds.add(ids[i]);
                roleButtonMapRepository.addButtonMap(roleRolebuttonmapEntity);   //保存阶段菜单关联关系
            }
            if(roleIds!=null&&roleIds.size()>0){
                AppRolesetMapEntity appRolesetMapEntity;
                roleRolesetmapRepository.delAppRolesetmap(appRoleDTO.getAppRoleSetId());
                Iterator<String> iterator = roleIds.iterator();
                while(iterator.hasNext()){
                    appRolesetMapEntity = new AppRolesetMapEntity();
                    appRolesetMapEntity.setAppRolesetMapId(IdGen.uuid());
                    appRolesetMapEntity.setAppRoleId(iterator.next());
                    appRolesetMapEntity.setAppSetId(appRoleDTO.getAppRoleSetId());
                    roleRolesetmapRepository.saveAppRolestmap(appRolesetMapEntity);  //保存角色阶段关联关系
                }
            }
        }
    }

    @Override
    public void deleteRoleButtonMap(String buttonId) {
        roleButtonMapRepository.delButtonMap(buttonId);
    }
}
