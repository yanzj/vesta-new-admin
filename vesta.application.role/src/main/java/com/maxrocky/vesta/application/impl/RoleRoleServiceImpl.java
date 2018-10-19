package com.maxrocky.vesta.application.impl;


import com.maxrocky.vesta.application.dto.adminDTO.RoleRoleDTO;
import com.maxrocky.vesta.application.dto.adminDTO.RoleRoleMouldDTO;
import com.maxrocky.vesta.application.inf.RoleRoleService;
import com.maxrocky.vesta.domain.model.RoleRoleEntity;
import com.maxrocky.vesta.domain.repository.RoleRoleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by zhanghj on 2016/1/18.
 */
@Service
public class RoleRoleServiceImpl implements RoleRoleService {

    @Resource
    private RoleRoleRepository roleRoleRepository;


    /**
     * 查询所有权限
     * @return
     */
    @Override
    public List<RoleRoleDTO> listRoleRole() {
        List<RoleRoleDTO> roleRoleDTOs = new ArrayList<>();
        List<RoleRoleEntity> roleRoleEntities = roleRoleRepository.listRoleRole();
        if (roleRoleEntities.size()>0){
            for(RoleRoleEntity roleRoleEntity:roleRoleEntities){
                RoleRoleDTO roleRoleDTO = new RoleRoleDTO();

                roleRoleDTO.setRoleId(roleRoleEntity.getRoleId());
                roleRoleDTO.setMakeDate(roleRoleEntity.getMakeDate());
                roleRoleDTO.setMakeTime(roleRoleEntity.getMakeTime());
                roleRoleDTO.setModifyDate(roleRoleEntity.getModifyDate());
                roleRoleDTO.setModifyTime(roleRoleEntity.getModifyTime());
                roleRoleDTO.setOperator(roleRoleEntity.getOperator());
                roleRoleDTO.setRoledesc(roleRoleEntity.getRoledesc());
                roleRoleDTO.setRoleDescription(roleRoleEntity.getRoleDescription());
                roleRoleDTO.setRoleName(roleRoleEntity.getRoleName());

                if(!roleRoleEntity.getRoledesc().equals("1")){
                    roleRoleDTOs.add(roleRoleDTO);
                }
            }

        }
        return roleRoleDTOs;
    }



    /**
     * 根据所属模块查询权限：1-物业管理，2-商户管理，3-房屋租赁管理，4-邻里圈管理，5-用户管理管理，6-数据统计
     * @param roledesc
     * @return
     */
    @Override
    public List<RoleRoleDTO> listRoleRoleByroledesc(String roledesc) {
        List<RoleRoleDTO> roleRoleDTOs = new ArrayList<>();
        List<RoleRoleEntity> roleRoleEntities = roleRoleRepository.listRoleRoleByroledesc(roledesc);
        if (roleRoleEntities.size()>0){
            for(RoleRoleEntity roleRoleEntity:roleRoleEntities){
                RoleRoleDTO roleRoleDTO = new RoleRoleDTO();

                roleRoleDTO.setRoleId(roleRoleEntity.getRoleId());
                roleRoleDTO.setMakeDate(roleRoleEntity.getMakeDate());
                roleRoleDTO.setMakeTime(roleRoleEntity.getMakeTime());
                roleRoleDTO.setModifyDate(roleRoleEntity.getModifyDate());
                roleRoleDTO.setModifyTime(roleRoleEntity.getModifyTime());
                roleRoleDTO.setOperator(roleRoleEntity.getOperator());
                roleRoleDTO.setRoledesc(roleRoleEntity.getRoledesc());
                roleRoleDTO.setRoleDescription(roleRoleEntity.getRoleDescription());
                roleRoleDTO.setRoleName(roleRoleEntity.getRoleName());

                roleRoleDTOs.add(roleRoleDTO);
            }

        }
        return roleRoleDTOs;
    }


    @Override
    public List<RoleRoleMouldDTO> getGroup() {

        Properties properties = new Properties();
        try   {
            properties.load(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("/roleRoleMould.properties"), "UTF-8"));//读取properties文件
        }   catch  (IOException e1)  {
            e1.printStackTrace();
        }
        List<String> list = roleRoleRepository.getGroup();
        List<RoleRoleMouldDTO> roleRoleMouldDTOs = new ArrayList<>();
        if (list.size()>0){
            for (String s :list){
                RoleRoleMouldDTO roleRoleMouldDTO = new RoleRoleMouldDTO();
                roleRoleMouldDTO.setRoleDesc(s);
                roleRoleMouldDTO.setRoleDescName(properties.getProperty(s));
                if(!s.equals("1") && !s.equals("0")){
                    roleRoleMouldDTOs.add(roleRoleMouldDTO);
                }
            }
        }
        properties.clear();

        return roleRoleMouldDTOs;
    }

}
