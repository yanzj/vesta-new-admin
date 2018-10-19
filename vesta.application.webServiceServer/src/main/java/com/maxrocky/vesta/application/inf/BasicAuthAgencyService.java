package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.AuthAgencyListDTO;

/**
 * Created by Magic on 2017/12/6.
 * Description:
 * webService:接收CRM传递的组织机构信息
 * ModifyBy:
 */
public interface BasicAuthAgencyService {
    /**
     * CreateBy:Magic
     * Description:接收投诉单信息
     * ModifyBy:
     */
    String setAuthAgency(AuthAgencyListDTO authAgencyListDTO);
}
