package com.maxrocky.vesta.application.ws;

import com.maxrocky.vesta.application.DTO.ComplaintListDTO;
import com.maxrocky.vesta.application.inf.BasicComplainService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 同步投诉单信息
 * Created by Magic on 2017/7/18.
 */
@WebService(name="ComplainName",serviceName = "ComplainServiceName")
public class TestComplainService {
    @Autowired
    BasicComplainService complainService;

    @WebMethod
    public String setComplaint(@WebParam(name="setComplaint") ComplaintListDTO complaintListDTO){
        return complainService.setComplaint(complaintListDTO);
    }
}
