package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.HouseCompanyAdminDTO;

/**
 * Created by Tom on 2016/3/8 14:47.
 * Describe:公司Service接口
 */
public interface HouseCompanyService {

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:返回指定ID的公司
     * CreateBy:Tom
     * CreateOn:2016-03-08 02:52:49
     */
    HouseCompanyAdminDTO getHouseCompanyAdminDTOById(String companyId);

}
