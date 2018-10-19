package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.OperationLogDTO;
import com.maxrocky.vesta.application.DTO.admin.UserAnthorityDTO;
import com.maxrocky.vesta.application.inf.OperationLogService;
import com.maxrocky.vesta.application.inf.UserAnthorityService;
import com.maxrocky.vesta.domain.model.AppRolesetEntity;
import com.maxrocky.vesta.domain.model.RoleRoleanthorityEntity;
import com.maxrocky.vesta.domain.model.RoleRolesetEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.AppRoleSetRepository;
import com.maxrocky.vesta.domain.repository.RoleAnthorityRepository;
import com.maxrocky.vesta.domain.repository.RoleRolesetRepository;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhanghj on 2016/1/25.
 */
@Service
public class UserAnthorityServiceImpl implements UserAnthorityService {

    @Autowired
    private RoleAnthorityRepository roleAnthorityRepository;
    @Autowired
    private RoleRolesetRepository roleRolesetRepository;

    @Autowired
    private AppRoleSetRepository appRoleSetRepository;

    @Autowired
    private UserPropertyStaffRepository userPropertyStaffRepository;

    @Autowired
    private OperationLogService operationLogService;

    /**
     * 通过员工id获取角色信息
     * @param staffId
     * @return
     */
    @Override
    public UserAnthorityDTO getUserAnthority(String staffId) {
        UserAnthorityDTO userAnthorityDTO = new UserAnthorityDTO();
        RoleRoleanthorityEntity roleRoleanthorityEntity = roleAnthorityRepository.getAnthorityByStaffId(staffId);
        if (roleRoleanthorityEntity!=null){
            userAnthorityDTO.setUserId(roleRoleanthorityEntity.getUserId());
            userAnthorityDTO.setStaffId(roleRoleanthorityEntity.getStaffId());
            if (roleRoleanthorityEntity.getSetId()!=null) {
                RoleRolesetEntity roleRolesetEntity = roleRolesetRepository.getRolesetById(roleRoleanthorityEntity.getSetId());
                if (roleRolesetEntity != null) {
                    userAnthorityDTO.setSetId(roleRolesetEntity.getSetId());
                    userAnthorityDTO.setRoledesc(roleRolesetEntity.getRoledesc());
                }
            }
            if (roleRoleanthorityEntity.getAppSetId()!=null) {
                AppRolesetEntity appRolesetEntity = appRoleSetRepository.getAppRolesetById(roleRoleanthorityEntity.getAppSetId());
                if (appRolesetEntity != null) {
                    userAnthorityDTO.setAppRoleSetId(appRolesetEntity.getAppSetId());
                    userAnthorityDTO.setAppRoleSetName(appRolesetEntity.getAppSetName());
                }
            }
        }
        return userAnthorityDTO;
    }


    /**
     * 更新员工角色关系
     * @param userAnthorityDTO
     * @return
     */
    @Override
    public boolean updateUserAnthority(UserAnthorityDTO userAnthorityDTO,UserPropertyStaffEntity propertystaffEntity) {

        UserPropertyStaffEntity userPropertyStaffEntity = userPropertyStaffRepository.get(userAnthorityDTO.getStaffId());
        String logContent = "对("+userPropertyStaffEntity.getUserName()+")"+userPropertyStaffEntity.getStaffName();
        RoleRoleanthorityEntity roleRoleanthorityEntity = new RoleRoleanthorityEntity();
        roleRoleanthorityEntity.setUserId(userAnthorityDTO.getStaffId());
        roleRoleanthorityEntity.setStaffId(userAnthorityDTO.getStaffId());
        if (userAnthorityDTO.getSetId().equals("0")){
            roleRoleanthorityEntity.setSetId(null);
            logContent = logContent+"清空了后台管理权限角色";
        }
        else {
            roleRoleanthorityEntity.setSetId(userAnthorityDTO.getSetId());
            RoleRolesetEntity roleRolesetEntity = roleRolesetRepository.getRolesetById(userAnthorityDTO.getSetId());
            if (roleRolesetEntity!=null) {
                logContent = logContent + "分配了后台管理权限角色-" + roleRolesetEntity.getRoledesc();
            }
        }
        if (userAnthorityDTO.getAppRoleSetId().equals("0")){
            roleRoleanthorityEntity.setAppSetId(null);
            logContent = logContent+"，清空了员工App权限角色。";
        }else {
            roleRoleanthorityEntity.setAppSetId(userAnthorityDTO.getAppRoleSetId());
            AppRolesetEntity appRolesetEntity = appRoleSetRepository.getAppRolesetById(userAnthorityDTO.getAppRoleSetId());
            if (appRolesetEntity!=null) {
                logContent = logContent + "，分配了员工App权限角色-" + appRolesetEntity.getAppSetName();
            }
        }
       boolean result = roleAnthorityRepository.updateAnthority(roleRoleanthorityEntity);

        //分配角色日志
        OperationLogDTO operationLogDTO = new OperationLogDTO();
        operationLogDTO.setProjectId(propertystaffEntity.getQueryScope());
        operationLogDTO.setUserName(propertystaffEntity.getUserName());
        operationLogDTO.setContent(logContent);
        operationLogService.addOperationLog(operationLogDTO);
        return result;
    }

    /**
     * 删除员工角色关系
     * @param staffId
     * @return
     */
    @Override
    public boolean deleteUserAnthority(String staffId) {
        boolean result = roleAnthorityRepository.deleteAnthority(staffId);
        return result;
    }
}
