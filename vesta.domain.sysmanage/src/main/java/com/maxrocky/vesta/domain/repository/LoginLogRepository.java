package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.LoginLogEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by sunmei on 2016/2/18.
 */
public interface LoginLogRepository {
    /**
     * 初始化列表，属性
     * @return
     */
    List<LoginLogEntity> LoginLogManage(LoginLogEntity loginLogEntity, WebPage webPage);

    /**
     * 登录日志录入
     * @param loginLogEntity
     */
    void addLoginLogManage(LoginLogEntity loginLogEntity);
}
