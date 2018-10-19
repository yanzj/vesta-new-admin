package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.HomeSwitchingDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInfoEntity;

/**
 * Created by JillChen on 2016/2/18.
 */
public interface HomePageService {
    /**
     * 首页切换 "项目" 列表
     * @param user
     * @return
     */
    ApiResult getHomeInfoSwitching(UserInfoEntity user);

    /**
     * 首页 切换 更新默认项目
     * @return
     */
    ApiResult replaceUserSetting(UserInfoEntity user,HomeSwitchingDTO homeSwitching);
}
