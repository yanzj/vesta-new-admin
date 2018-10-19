package com.maxrocky.vesta.application.inf;



import com.maxrocky.vesta.application.DTO.adminDTO.MessageInsertDTO;

import java.util.List;

/**
 * Created by zhanghj on 2016/1/15.
 */
public interface MessageInsertService {

    /**
     * 消息添加接口
     * @param messageInsertDTO
     * @param users
     * @return
     */
    public boolean InsertMessage(MessageInsertDTO messageInsertDTO, List<String> users);
}
