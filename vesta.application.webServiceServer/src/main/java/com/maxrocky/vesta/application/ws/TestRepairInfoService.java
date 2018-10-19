package com.maxrocky.vesta.application.ws;

import com.maxrocky.vesta.application.DTO.RepairInfoDTO;
import com.maxrocky.vesta.application.inf.BasicRepairInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by liudongxin on 2016/4/12.
 */
@WebService(name="repairInfoName",serviceName = "repairInfoServiceName")
public class TestRepairInfoService {
    @Autowired
    private BasicRepairInfoService basicRepairInfoService;

    @WebMethod
    public String repairInfo(@WebParam(name="repairInfo") RepairInfoDTO repairInfo){
        return  basicRepairInfoService.repairInfo(repairInfo);
    }
}
