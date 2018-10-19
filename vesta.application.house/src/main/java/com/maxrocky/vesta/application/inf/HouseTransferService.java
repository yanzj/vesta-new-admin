package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.HouseTransferAdminDTO;
import com.maxrocky.vesta.application.DTO.json.HI0012.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;

import java.util.List;

/**
 * Created by zhangzhaowen on 2016/9/2.10:07
 * Describe:房屋交接状态  业主签字
 */
public interface HouseTransferService {
    /**
     * Code:D
     * Describe:查询是否更新（内部预验）
     * CreateBy:Magic
     * CreateOn:2016/10/21
     * */
    List<HouseCountFlagDTO> getCheckoutHousenternalacceptanceByProjectNum(HouseCheckYNDTO houseCheckYNDTO);
    /**
     * Code:D
     * Describe:查询是否更新（工地开放）
     * CreateBy:Magic
     * CreateOn:2016/10/21
     * */
    List<HouseCountFlagDTO> getCheckoutHouseOpenByProjectNum(HouseCheckYNDTO houseCheckYNDTO);
    /**
     * Code:D
     * Describe:新增实体（工地开放）
     * CreateBy:Magic
     * CreateOn:2016/10/20
     */
    public ApiResult  getHouseOpen(String id,String timeStamp,String projectNum,List<String> projectList);
    /**
     * Code:D
     * Describe:新增实体（工地开放）
     * CreateBy:Magic
     * CreateOn:2016/10/20
     */
    public ApiResult saveHouseOpen(HouseOpenDTO houseOpenDTO,String username);
    /**
     * Code:D
     * Describe:新增实体（内部预验）
     * CreateBy:Magic
     * CreateOn:2016/10/20
     */
    public ApiResult  getInternalacceptanceHouse(String id,String timeStamp,String projectNum,List<String> projectList);
    /**
     * Code:D
     * Describe:新增实体(内部预验)
     * CreateBy:Magic
     * CreateOn:2016/10/20
     */
    public ApiResult saveInternalacceptanceHouse(InternalacceptanceHouseDTO internalacceptanceHouseDTO,String username);

    /**
     * Code:D
     * Describe:新增实体
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/1
     */
    public ApiResult saveHouseTransfer(HouseTransferJsonDTO houseTransferJsonDTO,String username);
    /**
     * Code:D
     * Describe:新增实体
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/1
     */
    public ApiResult saveHouseTransferList(List<HouseTransferJsonDTO> houseTransferJsonDTOList,String username);



    /**
     * Code:D
     * Describe:根据id获得实体
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/1
     */
    public ApiResult  getHouseTransferById(long id);
    /**
     * Code:D
     * Describe:根据id获得实体:离线功能
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/1
     */
    public ApiResult  getHouseTransferByIdAndTime(String id,String timeStamp,List<String> projectList);

    /**
     * Code:D
     * Describe:更新实体
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/1
     */
    public ApiResult updateHouseTransfer(HouseTransferJsonDTO houseTransferJsonDTO);

    /**根据房间号获取水电表信息*/
    HouseTransferAdminDTO getTransferInfoByRoomId(String roomNum);
}
