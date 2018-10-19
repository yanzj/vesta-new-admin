package com.maxrocky.vesta.application.ws;

import com.maxrocky.vesta.application.DTO.AuthAgencyListDTO;
import com.maxrocky.vesta.application.inf.BasicAuthAgencyService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 同步crm组织机构数据
 * Created by Magic on 2017/12/6.
 */
@WebService(name="AuthAgencyName",serviceName = "AuthAgencyServiceName")
public class TestAuthAgencyService {
    @Autowired
    BasicAuthAgencyService basicAuthAgencyService;

    @WebMethod
    public String setAuthAgency(@WebParam(name="setAuthAgency") AuthAgencyListDTO authAgencyListDTO){
        return basicAuthAgencyService.setAuthAgency(authAgencyListDTO);
    }
}
