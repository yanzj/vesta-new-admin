package com.maxrocky.vesta.application.ws;

import com.maxrocky.vesta.application.DTO.HousingOrdersDTO;
import com.maxrocky.vesta.application.inf.BasicHousingOrdersService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by Magic on 2017/2/8.
 * 验房阶段 交房单数据同步
 */
@WebService(name="housingOrdersName",serviceName = "housingOrdersServiceName")
public class TestHousingOrders {
    @Autowired
    private BasicHousingOrdersService housingOrdersService;

    @WebMethod
    public String sethousingOrders(@WebParam(name="housingOrder") HousingOrdersDTO housingOrders){
        return  housingOrdersService.sethousing(housingOrders);
    }
}