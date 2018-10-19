package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.ClickTimesSeachDTO;
import com.maxrocky.vesta.application.DTO.UserLoginStatisticDTO;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;

import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/17.
 * 用户登录统计 业务逻辑层接口
 */
public interface UserLoginStatisticService {

    /**
     * 初始化登陆统计
     * @param user
     * @param clickTimesSeach
     * @return
     */
    List<UserLoginStatisticDTO> userLoginStatistic(UserPropertyStaffEntity user,ClickTimesSeachDTO clickTimesSeach);

}
