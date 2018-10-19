package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.RoleViewmodelService;
import com.maxrocky.vesta.application.jsonDTO.MenuDTO;
import com.maxrocky.vesta.domain.model.RoleViewmodelEntity;
import com.maxrocky.vesta.domain.repository.RoleViewmodelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JillChen on 2016/1/13.
 */
@Service
public class RoleViewmodelServiceImpl implements RoleViewmodelService {

    @Autowired
    RoleViewmodelRepository roleViewmodelRepository;

    @Override
    public List<RoleViewmodelEntity> getViewListByUserId(String property, String userid) {
        List<RoleViewmodelEntity> viewmodels = new ArrayList<>();
        if(userid==null){
            return null;
        }else{
            viewmodels = roleViewmodelRepository.getViewListByUserId(property,userid);
            if(viewmodels!=null && viewmodels.size()>0){
                return viewmodels;
            }
        }
        return null;
    }

    @Override
    public List<RoleViewmodelEntity> getAuthViewListByUserId(String property, String userid) {
        List<RoleViewmodelEntity> viewmodels = new ArrayList<>();
        if(userid==null){
            return null;
        }else{
            viewmodels = roleViewmodelRepository.getAuthViewListByUserId(property,userid);
            if(viewmodels!=null && viewmodels.size()>0){
                return viewmodels;
            }
        }
        return null;
    }

    @Override
    public List<RoleViewmodelEntity> getViewListOtherByUserId(String property, String userid) {
        List<RoleViewmodelEntity> viewmodels = new ArrayList<>();
        if(userid==null){
            return null;
        }else{
            viewmodels = roleViewmodelRepository.getViewLisOthertByUserId(property,userid);
            if(viewmodels!=null && viewmodels.size()>0){
                return viewmodels;
            }
        }
        return null;
    }

    @Override
    public List<RoleViewmodelEntity> getAuthViewListOtherByUserId(String property, String userid) {
        List<RoleViewmodelEntity> viewmodels = new ArrayList<>();
        if(userid==null){
            return null;
        }else{
            viewmodels = roleViewmodelRepository.getAuthViewLisOthertByUserId(property,userid);
            if(viewmodels!=null && viewmodels.size()>0){
                return viewmodels;
            }
        }
        return null;
    }

    @Override
    public List<MenuDTO> appMenuList(String roleSetId) {
        List<MenuDTO> menuDTOList = new ArrayList<MenuDTO>();
        List<RoleViewmodelEntity> viewmodelEntities = roleViewmodelRepository.appMenuList(roleSetId);
        if(viewmodelEntities!=null){
            MenuDTO menuDTO;
            for(RoleViewmodelEntity roleViewmodelEntity:viewmodelEntities){
                menuDTO = new MenuDTO();
                menuDTO.setMenuName(roleViewmodelEntity.getMenuName());
                menuDTOList.add(menuDTO);
            }
        }
        return menuDTOList;
    }

    @Override
    public List<RoleViewmodelEntity> getAuthRoleViewByIdList(List<String> viewIdList,String category) {
        String belong="";
        if("3".equals(category)){
            belong="6";//安全
        }else if("2".equals(category)){
            belong="2";//安全
        }else if("1".equals(category)){
            belong="3";//客关
        }
        List<RoleViewmodelEntity>  viewmodels = roleViewmodelRepository.getAuthV0iewListByIdList(viewIdList,belong);
        if(viewmodels!=null && viewmodels.size()>0){
            return viewmodels;
        }
        return null;
    }
}
