package com.maxrocky.vesta.domain.repository;


import com.maxrocky.vesta.domain.model.*;

import java.util.List;

/**
 * Created by Magic on 2016/5/3.
 */
public interface RectificationRepository {
    /**
     * 修改app推送匹配
     * */
    void upMessageTar(MessageTargetEntity messageTargetEntity);
    /**
     * 获取app推送匹配
     * */
    List<MessageTargetEntity> getMessagelist(String channelId,String type);
    /**
     * 增加app推送匹配
     * */
    void saveMessageToken(MessageTokenEntity MessageTokenEntity);
    /**
     * 修改app推送匹配
     * */
    void updateMessageToken(MessageTokenEntity MessageTokenEntity);
    /**
     * 获取app推送匹配
     * */
    List<MessageTokenEntity> getMessageTokenList(String channelId,String type);
    /**
     * 根据组id和time增量查询保修单数据
     * */
    int getRepairForTimeCountNew(List user,String time,String id);
    /**
     * 根据组id和time增量查询保修单数据
     * */
    String getRepairForTimeCount(List user,String time,String id,String userid);
    /**
     * 根据组id和time增量查询保修单数据
     * */
    List<String> getRepairForTimedelete(List user,String time,String id,String userid);
    /**
     * 根据组id和time增量查询保修单数据
     * */
    List<Object[]> getRepairForTimeNew(List user,String time,String id,String userid);

    List<ActiveTemporaryTimeEntity> getActiveTemporaryCountList(String id,String time);
    /**
     * 获取活动临时表数据信息
     * */
    List<ActiveTemporaryTimeEntity> getActiveTemporaryTimeList(String id,String time,String projectNum);
    /**
     *
     * 获取三级分类临时表数据信息
     * */
    List<ClassificationTemporaryTimeEntity> getClassTemporaryTimeList(String id,String time);
    /**
     *
     * 获取三级分类临时表数据信息
     * */
    List<ThirdClassificationCommEntity> getClassNewTimeList(String id,String time);
    /**
     * 获取楼栋信息清单   临时表数据表
     * */
    List<BuildingMappingTimeEntity> getBuildingMappingList(String id,String time,String projectNum);
    /**
     * 获取所有crm保修单信息
     * */
    PropertyRepairCRMEntity getRepairTimeList(String id);
    /**
     * 根据组id和time增量查询
     * */
    List<PropertyRepairEntity> getRepairForTime(String projectid,String groupid,String time,String id);
    /**
     * 根据组id和time增量查询
     * */
    List<HouseProjectEntity> getRepairForprojectTime(String projectnum,String time);
    /**
     * 根项目id和time增量查询
     * */
    List<DeliveryPlanCrmEntity> getPlanCrmFroProject(String time,String projectNum);
    /**
     * 根项目id和time增量查询统计
     *
     * */
    int getPlanCountFro(String time,String projectNum);
//    /**
//     * 根据time增量查询
//     * */
//    List<HouseRoomEntity> getHouseRoomList(String time);
    /**
     * 根据time增量查询room
     * */
    int getRoomCountList(String projectNum,String time);
    /**
     * 根据time增量查询room
     * */
    List<Object> getHouseinfoRoomList(String projectnum,String time);
    /**
     * 根据time 分组查询增量楼层
     * */
    List<HouseInfoEntity> getHouseinfoFloorList(String projectnum,String time);
    /**
     * 根据time 分组查询增量单元
     * */
    List<HouseInfoEntity> getHouseinfoUnitList(String projectnum,String time);
    /**
     * 根据time 分组查询增量楼栋
     * */
    List<HouseInfoEntity> getHouseinfoBuildList(String projectnum,String time);
    /**
     * 根据time 分组查询增量项目
     * */
    List<HouseInfoEntity> getHouseinfoProjectList(String projectnum,String time);
    /**
     * 根据项目id和time增量查询
     * */
    List<HouseProjectEntity> getProjecList(Object[] projectid,String time);

    /**
     * 查询活动计划下的房间信息
     * */
    List<Object> getPlanRoomList(String id);

    /**
     * 查询活动计划下的楼层信息
     * */
    List<Object> getPlanFloorList(String id);
    /**
     * 查询活动计划下的单元信息
     * */
    List<Object> getPlanUnitList(String id);
    /**
     * 查询活动计划下的楼栋信息
     * */
    List<Object> getPlanBuildList(String id);
    /**********************************以下部分为原始接口 报废遗留************************************************/
    /**
     *
     * 获取整改单信息列表
     * */
    List<PropertyRepairEntity> getAllList(String name);
    /**
     *根据id获取保修主表信息property_rectify_crm
     *
     * */

    PropertyRepairEntity getRepairById(String id);
    /**
     * 根据id获取保修任务表
     *
     * */
    PropertyRepairTaskEntity getRepairTaskById(String id);
    /**
     *
     * 获取整改单信息列表
     * */
    List<PropertyRepairCRMEntity> getPREList();

    /**
     * 获取保修单信息列表
     * */
    PropertyRepairCRMEntity getRecitifyList(String name,String id);
    /**
     * 获取所有crm保修单信息
     * */
    PropertyRepairCRMEntity getQueryForidList(String id);

    /**
     *
     * 获取当前报修单的图片附件
     * */
    List<PropertyImageEntity> getImageForId(String id);
    /***/
    List<PropertyImageEntity> getImageForOver(String id);
    /**
     * 修改保修单数据
     * */
    void updateProperty(PropertyRepairCRMEntity propertyRepairCRMEntity);
    /**
     * 修改整改单数据
     * */
    void updatePropertyRectify(PropertyRectifyCRMEntity propertyRectifyCRMEntity);
    /**
     * 获取当前id的项目名称
     * */
    HouseInfoEntity getProject(String id);
    /**
     * 根据报修单id获取保修信息
     * */
    PropertyRepairCRMEntity getdelepople(String id);
    /***/
    RoomLocationEntititly getRoomLocation(String id);

    //------------------------------------内部预验模块
    /***
     *
     * 获取所有项目信息
     * */
    List<HouseProjectEntity> gethousListAll();
    /***
     *
     * 获取当前公司项目信息
     * */
    List<HouseProjectEntity> gethousListforcompid(String id);
    /***
     * 根据项目id获取区域信息
     * */
    List<HouseAreaEntity> gethousListForProject(String id);

    /***
     * 获取房屋位置
     * */
    List<HouseRoomEntity> gethouseroom(String id);

    /**
     *获取单元位子
     * */
    List<HouseUnitEntity> gethouseUnit(String id);
    /**
     * 获取楼层信息
     *
     * **/
    List<HouseFloorEntity> getfloorList(String id);
    /**
     * 获取楼栋信息
     * */
    List<HouseBuildingEntity> gethouseBuilding(String id);
    /**
     * 根据当前房屋id获取整改单
     * */
    List<PropertyRectifyCRMEntity> getPropertyRectify(String id);

    /**
     *
     * 按项目id查询houseinfo具体数据
     * */
    List<HouseInfoEntity> getHouseInfo(String id);

    //*******************************************交房验房阶段修该***************************

    /**
     * 按照type和项目id查询计划批次信息
     * */
    List<DeliveryPlanCrmEntity> getDeliveryPlanCrm(String type,String id);

    /**
     * 查询房产信息
     * */
    List<Object> gethouseList(String id);
    /**
     * 查询房产信息building
     * */
    List<Object> gethouseBuildingList(String id);
    /**
     * 查询房产信息unit
     * */
    List<Object> gethouseunitList(String id);
    /**
     * 查询房产信息unit
     * */
    List<Object> gethouseFloorList(String id);
    /**
     *
     * 获取项目信息
     * **/
    HouseProjectEntity getHouseProjectEntity(String id);

    /**
     * ThirdTypeEntity三级分类查询
     * */
    List<ThirdTypeEntity> getThirdTypeEntity();

    /**
     * 增加
     * */
    void saveHouseReception(HouseReceptionEntity HouseReception);

    void saveDeliveryInformation(DeliveryInformationEntity DeliveryInformation);


    /**
     * 一级分类
     * */
    List<FirstClassificationEntity> getfirstClass();
    /**
     * 二级分类
     * */
    List<SecondClassificationEntity> getSecondClass();
    /**
     *三级分类
     * */
    List<ThirdClassificationEntity> getThirdClass();
    /**
     * 三级分类关联处理方式
     * */
    List<RepairModeEntity> getRepairMode();
    /**
     * 三级分类关联处理时间
     * */
    List<WorkTimeEntity> getWorkTime();


    UserPropertyStaffEntity getusername(String id);

    /**
     * 根据组id和time增量查询保修单数据
     * */
    List<Object[]> getAllRepairByApp(String id,String time,String projectNum);

    /**
     * 根据id查询保修单数据
     * */
    Object[] getAllRepairById(String id,String appId);
    /**
     * 按当前登录人的员工id 查询他所负责的所有整改单和保修单（整改中状态）
     * */
    List<Object[]> getHouseInspectionNB(String userId);
    /**
     * 按当前登录人的员工id 查询他所负责的所有整改单（整改中状态）
     * */
    int getRectifyCountById(String userId);
    /**
     * 按当前登录人的员工id 查询他所负责的所有报修单（整改中状态）
     * */
    int getRepairCountById(String userId);
}
