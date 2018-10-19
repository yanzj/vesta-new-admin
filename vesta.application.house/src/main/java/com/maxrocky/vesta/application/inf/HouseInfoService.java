package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.HouseInfoAdminDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.HouseInfoEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.exception.GeneralException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Tom on 2016/1/19 9:35.
 * Describe:房屋信息Service接口
 */
public interface HouseInfoService {

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:添加房产信息
     * CreateBy:Tom
     * CreateOn:2016-01-19 09:59:48
     */
    ApiResult createHouseInfoEntity(HouseInfoEntity houseInfoEntity);

    /**
     * Code:HI0007
     * Type:UI Method
     * Describe:获取用户房产
     * CreateBy:Tom
     * CreateOn:2016-01-20 11:36:45
     */
    ApiResult getHouseListByUserId(String userId,String projectId);

    /**
     * Code:HI0008
     * Type:UI Method
     * Describe:获取用户房产
     * CreateBy:Tom
     * CreateOn:2016-01-20 01:56:57
     */
    ApiResult getHouseListByUserId(String userId);

    /**
     * Code:For UI0006、UI0009
     * Type:Service Method
     * Describe:根据房产Id获取房产
     * CreateBy:Tom
     * CreateOn:2016-01-20 11:17:33
     */
    HouseInfoAdminDTO getHouseInfoEntityByHouseId(String houseId);

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:根据物业房产Id获取房产信息
     * CreateBy:Tom
     * CreateOn:2016-01-21 02:17:29
     */
    HouseInfoAdminDTO getByViewAppHouseId(int houseId);

    /*********************************************金茂项目******************************************************/

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 获取用户其他的房产
     * ModifyBy:
     */
    ApiResult getOtherHouse(UserInfoEntity user) throws GeneralException;

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 通过房屋id获取房屋详情信息
     * ModifyBy:
     */
    ApiResult getHouseInfo(UserInfoEntity user,String houseId) throws GeneralException;

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 设置默认房产
     * ModifyBy:
     */
    ApiResult getDefaultHouse(UserInfoEntity user,String houseId) throws GeneralException;

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 获取用户默认的房产
     * ModifyBy:
     */
    ApiResult getDefaultHoused(UserInfoEntity user) throws GeneralException;

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 获取用户所有的房产
     * ModifyBy:
     */
    ApiResult getAllHouse(UserInfoEntity user) throws GeneralException;

    //获取项目下拉框
    public LinkedHashMap listProject();

    //获取单元下拉框
    public LinkedHashMap listUnit();

    //获取楼号下拉框
    public LinkedHashMap listBuild();

    //获取房间下拉框
    public LinkedHashMap listRoom();


    /**
     * 获取楼栋信息
     * @return
     */
    Map<String,String> getBuildNum();

    /**
     * 获取楼层信息
     * @return
     */
    Map<String,String> getBuildFloor(String BuildingNum);

    /**
     * 通过房屋id检索房屋信息_Wyd
     * @param roomId    房屋Id
     * @return  HouseInfoEntity
     */
    HouseInfoEntity getHouseInfoByRoomId(String roomId);

    /**
     * 通过房产信息分配门禁_Wyd
     * @param projectCode   项目编码
     * @param area           地块
     * @param buildingNum   楼栋编码
     * @param unitCode      单元编码
     */
    void assignDoorByHouse(String projectCode,String area,String buildingNum,String unitCode,UserInfoEntity userInfoEntity);

    /**
     * 通过房产信息取消门禁权限_Wyd
     * @param projectCode   项目编码
     * @param area           地块
     * @param buildingNum   楼栋编码
     * @param unitCode      单元编码
     */
    void cancelDoorByHouse(String projectCode,String area,String buildingNum,String unitCode,UserInfoEntity userInfoEntity);
}
