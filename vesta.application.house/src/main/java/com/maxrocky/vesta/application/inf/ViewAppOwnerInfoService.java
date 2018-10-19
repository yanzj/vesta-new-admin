package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.ViewAppHouseInfoAdminDTO;
import com.maxrocky.vesta.application.DTO.json.HI0006.HouseParamJsonDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;

/**
 * Created by Tom on 2016/1/18 14:46.
 * Describe:基础业主信息Service接口
 */
public interface ViewAppOwnerInfoService {

    /**
     * Code:HI0006
     * Type:UI Method
     * Describe:根据房屋信息查询身份证件号
     * CreateBy:Tom
     * CreateOn:2016-01-18 02:53:22
     */
    ApiResult getIdCardByHouse(HouseParamJsonDTO houseParamJsonDTO);

    /**
     * Code:For HI0006、UI0002
     * Type:Service Method
     * Describe:根据房屋信息获取房屋、合同、业主信息
     * CreateBy:Tom
     * CreateOn:2016-01-18 05:11:18
     */
    ViewAppHouseInfoAdminDTO getBasicInfoByHouse(HouseParamJsonDTO houseParamJsonDTO);

    /**
     * Code:For HI0006、UI0002
     * Type:Service Method
     * Describe:根据房屋信息获取房屋、业主信息
     * CreateBy:sunmei
     */
    ViewAppHouseInfoAdminDTO getBasicInfoByTenant(HouseParamJsonDTO houseParamJsonDTO);

}
