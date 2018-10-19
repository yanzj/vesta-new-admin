package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.RepairInfoDTO;

/**
 * Created by liudongxin on 2016/4/10.
 * Description:
 * webService:接收金茂项目CRM传递的报修信息
 * ModifyBy:
 */
public interface BasicRepairInfoService {

    /**
     * CreateBy:liudongxin
     * Description:接收报修/整改单信息
     * param repairInfo：报修集合
     * ModifyBy:
     */
    String repairInfo(RepairInfoDTO repairInfo);
}
