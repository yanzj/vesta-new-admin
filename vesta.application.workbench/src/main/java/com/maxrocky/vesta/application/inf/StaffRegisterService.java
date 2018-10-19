package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.WorkApportionDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.utility.Page;

import java.util.List;

/**
 * Created by zhanghj on 2016/1/21.
 */
public interface StaffRegisterService {

    /**
     * 员工签到
     * @param staffId
     *
     * @return
     */
    public ApiResult saveStaffRegister(String staffId);

    /**
     * 获得签到表
     * @param staffId
     * @param page
     * @param
     * @return
     */
    public ApiResult listStaffRegister(String staffId,Page page);

    /**
     * 获取签到人员的所有部门(员工端)
     * createBy：liudongxin
     * @param projectId
     * @return
     * @throws GeneralException
     */
    ApiResult department(String projectId) throws GeneralException;

    /**
     * 获取部门下所有签到人员(管理端)
     * createBy：liudongxin
     * @param sectionId
     * @return
     * @throws GeneralException
     */
    List<WorkApportionDTO> registers(String sectionId);
}
