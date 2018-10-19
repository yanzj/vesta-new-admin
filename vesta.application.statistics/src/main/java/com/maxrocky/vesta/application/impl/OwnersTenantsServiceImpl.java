package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.ClickTimesSeachDTO;
import com.maxrocky.vesta.application.DTO.OwnersLeaseStatisticsDTO;
import com.maxrocky.vesta.application.inf.OwnersTenantsService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.OwnersTenantsRepository;
import com.maxrocky.vesta.domain.repository.UserSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/17.
 *  业主、租户统计 业务逻辑层实现类
 */
@Service
public class OwnersTenantsServiceImpl implements OwnersTenantsService {
    /**
     * 业主、租户 持久层接口
     */
    @Autowired
    OwnersTenantsRepository ownersTenantsRepository;

    @Autowired
    UserSettingRepository userSettingRepository;

    /**
     * 初始化业主、租户统计
     * @param user
     * @param clickTimes
     * @return
     */
    @Override
    public List<OwnersLeaseStatisticsDTO> ownersLease(UserPropertyStaffEntity user, ClickTimesSeachDTO clickTimes) {
        String companyName = "";            // 公司名称
        String propertyProject = user.getProjectId();  // 项目
        String propertyArea = "";           // 区域
        // 根据项目ID 查询用户自定义表 项目名称
        List<UserSettingEntity> userSetting = userSettingRepository.userSettingList(propertyProject);
        List<OwnersLeaseStatisticsDTO> ownersLease = new ArrayList<>();
        OwnersLeaseStatisticsDTO owners = new OwnersLeaseStatisticsDTO();
/*        if(userSetting.size() > 0){
            Integer relativesNumbert = 0;//亲属数量
            Integer tenantsNumbert = 0;//租客数量
            // 项目名称
            owners.setProjectName(userSetting.get(0).getProjectName());
            // 注册人数
            owners.setRegistrationNumber(userSetting.size());
            // 根据用户ID查询 用户信息表
            for(UserSettingEntity userListt : userSetting){

                List<UserInfoEntity> userInfo = ownersTenantsRepository.userInfoList(userListt.getUserId());
                if(userInfo.size() > 0){
                    // 租客数量
                    if(userInfo.get(0).getUserType().equals(UserInfoEntity.USER_TYPE_TENANT)){
                        tenantsNumbert += 1;
                        owners.setTenantsNumbert(tenantsNumbert);
                    }
                    // 亲属数量
                    if(userInfo.get(0).getUserType().equals(UserInfoEntity.USER_TYPE_FAMILY)){
                        relativesNumbert += 1;
                        owners.setRelativesNumbert(relativesNumbert);
                    }
                }
            }
        }*/
        Integer relativesNumbert = 0;//亲属数量
        Integer tenantsNumbert = 0;//租客数量
        List<Object[]> ownersTenants= ownersTenantsRepository.getOwnersTenants(propertyProject);
        if(userSetting.size()>0){
            // 注册人数
            owners.setRegistrationNumber(userSetting.size());
        }else{
            owners.setRegistrationNumber(0);
        }
        if(ownersTenants!=null) {
            for (Object[] tenants : ownersTenants) {
                // 项目名称
                owners.setProjectName((String) tenants[1]);

                // 租客数量
                if(tenants[0].equals(UserInfoEntity.USER_TYPE_TENANT)){
                    tenantsNumbert += 1;
                    owners.setTenantsNumbert(tenantsNumbert);
                }
                // 亲属数量
                if(tenants[0].equals(UserInfoEntity.USER_TYPE_FAMILY)){
                    relativesNumbert += 1;
                    owners.setRelativesNumbert(relativesNumbert);
                }
            }
        }
        ownersLease.add(owners);
        return ownersLease;
    }
}
