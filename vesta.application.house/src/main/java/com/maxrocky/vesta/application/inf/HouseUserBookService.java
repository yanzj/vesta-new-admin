package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.HouseUserAdminDTO;
import com.maxrocky.vesta.application.DTO.admin.HouseUserBookAdminDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.HouseUserBookEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;

import java.util.List;

/**
 * Created by Tom on 2016/1/19 10:08.
 * Describe:房屋业主关系Service接口
 */
public interface HouseUserBookService {

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:创建房屋业主关系
     * CreateBy:Tom
     * CreateOn:2016-01-19 10:10:04
     */
    ApiResult createHouseUserBook(HouseUserBookEntity houseUserBookEntity);

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:根据房产Id查询用户信息
     * CreateBy:Tom
     * CreateOn:2016-01-20 05:39:27
     */
    ApiResult getHouseUserBookListByHouseId(String houseId);

    /**
     * Code:For UI0004、UI0007
     * Type:Service Method
     * Describe:根据用户Id、房产Id获取用户关系列表
     * CreateBy:Tom
     * CreateOn:2016-01-20 06:35:31
     */
    List<HouseUserAdminDTO> getHouseUserAdminListByUserIdAndProjectId(String userId, String projectId);

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:删除房产、业主关系
     * CreateBy:Tom
     * CreateOn:2016-01-20 08:40:57
     */
    ApiResult deleteHouseUserBook(String userId, String houseUserId);

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:获取用户当前最高类型
     * CreateBy:Tom
     * CreateOn:2016-01-20 09:24:05
     */
    String getUserType(String userId, String houseId);

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:判断是否业主
     * CreateBy:Tom
     * CreateOn:2016-01-20 10:30:39
     */
    Boolean checkOwner(String userId, String houseId);

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:根据用户Id、房产Id获取房产成员关系
     * CreateBy:Tom
     * CreateOn:2016-01-20 10:51:07
     */
    ApiResult getHouseUserBookByUserIdAndHouseId(String userId, String houseId);

    /**
     * Code:For UI0002
     * Type:Service Method
     * Describe:根据用户Id、物业业主Id创建房产业主关系
     * CreateBy:Tom
     * CreateOn:2016-01-22 03:39:38
     */
    void createOwnerHouseUserBook(String userId, int viewAppOwnerId);

    /**
     * Describe:根据houseId创建房产业主关系
     * @param houseId
     * CreateBy:sunmei
     */
    void createOwnerHouseUserBookByHouseId(String userId,int houseId);

    /**
     * Code:For UI0012
     * Type:Service Method
     * Describe:返回指定授权信息
     * CreateBy:Tom
     * CreateOn:2016-02-21 11:52:24
     */
    HouseUserBookAdminDTO getHouseUserBookAdminDTOByHouseUserId(String houseUserId);

    /**
     * Code:For UI0013
     * Type:UI Method
     * Describe:修改授权信息
     * CreateBy:Tom
     * CreateOn:2016-02-21 02:09:29
     */
    ApiResult updateHouseUserBook(HouseUserBookAdminDTO houseUserBookAdminDTO);


    /**
     * CreatedBy:liudongxin
     * Describe:
     * 获取同住人
     * param id:房屋id
     * ModifyBy:
     */
    ApiResult getHousemate(UserInfoEntity userInfo,String id);

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 添加同住人
     * param id:房屋id
     * param name:真实姓名
     * param phone:电话
     * ModifyBy:
     */
    ApiResult createHousemates(UserInfoEntity userInfo,String id,String name,String phone,String idCardNumber);

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 删除同住人
     * param id:房屋id
     * ModifyBy:
     */
    ApiResult getDelHousemate(UserInfoEntity userInfo,String id,String userId);

}
