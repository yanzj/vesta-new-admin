package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.OperationLogEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by sunmei on 2016/2/18.
 */
public interface OperationLogRepository {
    /**
     * 初始化列表，属性
     * @return
     */
    List<OperationLogEntity> OperationLogManage(OperationLogEntity operationLogEntity, WebPage webPage);

    /**
     * 后台核心操作日志添加数据
     */
    void addOperationLog(OperationLogEntity operationLogEntity);
}
