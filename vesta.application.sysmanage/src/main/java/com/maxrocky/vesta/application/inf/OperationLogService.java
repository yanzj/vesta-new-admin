package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.OperationLogDTO;
import com.maxrocky.vesta.application.DTO.OperationLogSearchDTO;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by sunmei on 2016/2/18.
 */
public interface OperationLogService {
    //OperationLogSearchDTO
    /**
     * 初始化列表，属性
     * @return
     */
    List<OperationLogDTO> OperationLogManage(OperationLogSearchDTO operationLogSearchDTO, WebPage webPage);

    /**
     * 核心操作日志录入
     * @param operationLogDTO
     */
    void addOperationLog(OperationLogDTO operationLogDTO);
}
