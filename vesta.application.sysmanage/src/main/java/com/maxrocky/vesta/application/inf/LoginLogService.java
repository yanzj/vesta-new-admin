package com.maxrocky.vesta.application.inf;


import com.maxrocky.vesta.application.DTO.LoginLogSearchDTO;
import com.maxrocky.vesta.application.DTO.LoginLogDTO;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by sunmei on 2016/2/18.
 */
public interface LoginLogService {
    /**
     * 初始化列表，属性
     * @return
     */
    List<LoginLogDTO> LoginLogManage(LoginLogSearchDTO loginLogSearchDTO, WebPage webPage);

    /**
     *
     * @param regionId 区域id
     * @param projectId 项目id
     * @param userId 用户id
     * @param ip 用户ip
     * @param message 登录信息
     * @param equipment 设备型号
     */
    void AddLoginLogManage(String regionId,String projectId,String userId,String ip,String message,String equipment);
}
