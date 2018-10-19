package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.ClickTimesSeachDTO;
import com.maxrocky.vesta.application.DTO.UserLoginStatisticDTO;
import com.maxrocky.vesta.application.inf.UserLoginStatisticService;
import com.maxrocky.vesta.domain.model.UserLoginBookEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.model.UserSettingEntity;
import com.maxrocky.vesta.domain.repository.UserLoginBookRepository;
import com.maxrocky.vesta.domain.repository.UserLoginStatisticRepository;
import com.maxrocky.vesta.domain.repository.UserSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/17.
 * 用户登录统计 业务逻辑层实现类
 */
@Service
public class UserLoginStatisticServiceImpl implements UserLoginStatisticService {
    /**
     * 用户登录统计 持久层接口
     */
    @Autowired
    UserLoginStatisticRepository userLoginStatisticRepository;
    @Autowired
    UserSettingRepository userSettingRepository;

    /* 登录信息 */
    @Autowired
    UserLoginBookRepository userLoginBookRepository;
    /**
     * 初始化用户登录统计
     * @param user
     * @param clickTimesSeach
     * @return
     */
    @Override
    public List<UserLoginStatisticDTO> userLoginStatistic(UserPropertyStaffEntity user, ClickTimesSeachDTO clickTimesSeach) {
        String companyName = "";            // 公司名称
        String propertyProject = user.getProjectId();  // 项目
        String propertyArea = "";           // 区域
        List<UserLoginStatisticDTO> userLoginList = new ArrayList<>();
        // 根据项目ID 查询用户自定义表 项目名称
        List<UserSettingEntity> userSetting = userSettingRepository.userSettingList(propertyProject);
        UserLoginStatisticDTO userLogin = new  UserLoginStatisticDTO();
        if(userSetting.size() > 0){
            Integer landingNumber = 0;//登录次数
            // 项目名称
            userLogin.setProjectName(userSetting.get(0).getProjectName());
            // 注册人数
            userLogin.setRegisteredNumber(userSetting.size());
            // 根据用户ID查询 登录信息表
            for(UserSettingEntity userListt : userSetting){
                // 查询
                List<UserLoginBookEntity> userLoginBookEntity = userLoginStatisticRepository.getLoginByUserId(userListt.getUserId());
                if(userLoginBookEntity.size() > 0){
                    landingNumber += userLoginBookEntity.size();
                    userLogin.setLandingNumber(landingNumber);
                }
            }
            userLoginList.add(userLogin);
        }
        return userLoginList;
    }
}
