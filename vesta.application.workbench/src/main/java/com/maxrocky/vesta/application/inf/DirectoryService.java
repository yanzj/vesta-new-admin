package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.exception.GeneralException;

/**
 * Created by zhanghj on 2016/1/22.
 */
public interface
        DirectoryService {

    /**
     * 查询该员工所在公司的通讯录
     * @param projectId
     * @return
     */
        public ApiResult listDirectory(String projectId);

    /**
     * 获取所有部门的通讯录(员工端)
     * createBy：liudongxin
     * @param projectId
     * @return
     * @throws GeneralException
     */
    ApiResult getTelephoneBook(String projectId) throws GeneralException;
}
