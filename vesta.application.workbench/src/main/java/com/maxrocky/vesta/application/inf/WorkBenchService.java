package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.jsonDTO.StaffInfoDTO;
import com.maxrocky.vesta.application.DTO.jsonDTO.StaffPasswordDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.exception.GeneralException;

/**
 * Created by zhanghj on 2016/1/28.
 */
public interface WorkBenchService {

    /**
     * 获取工作台信息
     * @param userId
     * @return
     */
        public ApiResult getWorkBenchInfo(String userId,String type,String token);

    /**
     * 获取员工信息
     * @param staffId
     * @return
     * @throws GeneralException
     */
    public  ApiResult getStaffInfo(String staffId) throws GeneralException;

    /**
     * 更新员工密码
     * @param staffPasswordDTO
     * @return
     */
    public ApiResult updatePwd(StaffPasswordDTO staffPasswordDTO)throws GeneralException;

    /**
     * 更新员工头像
     * @param staffInfoDTO
     * @return
     */
    public ApiResult updateLOGO(StaffInfoDTO staffInfoDTO)throws GeneralException;

    /**
     * 获取版本信息
     * @param mobileType
     * @return
     * @throws GeneralException
     */
    public ApiResult showVersion(String mobileType,String userType)throws GeneralException;

}
