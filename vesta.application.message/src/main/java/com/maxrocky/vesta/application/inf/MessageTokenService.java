package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.appDTO.MessageTokenDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;

/**
 * Created by zhanghj on 2016/1/17.
 */
public interface MessageTokenService {

    /**
     * 登录保存token
     * @return
     */
    public ApiResult saveToken(MessageTokenDTO messageTokenDTO);

    /**
     * 退出删除token
     * @return
     */
    public void delToken(String userId);
}
