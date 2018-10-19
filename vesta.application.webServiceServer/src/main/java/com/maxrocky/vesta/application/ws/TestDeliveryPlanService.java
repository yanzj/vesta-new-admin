package com.maxrocky.vesta.application.ws;

import com.maxrocky.vesta.application.DTO.DeliverPlanInfoDTO;
import com.maxrocky.vesta.application.inf.BasicDeliveryPlanService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by liudongxin on 2016/4/12.
 */
@WebService(name="deliverPlanName",serviceName = "deliverPlanServiceName")
public class TestDeliveryPlanService {
    @Autowired
    private BasicDeliveryPlanService basicDeliveryPlanService;

    @WebMethod
    public String deliverPlanInfo(@WebParam(name="deliverPlanInfo") DeliverPlanInfoDTO deliverPlanInfo){
        return basicDeliveryPlanService.deliverPlanInfo(deliverPlanInfo);
    }
}
