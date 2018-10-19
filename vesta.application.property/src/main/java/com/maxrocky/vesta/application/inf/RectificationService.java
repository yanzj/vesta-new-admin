package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.application.DTO.admin.*;
import com.maxrocky.vesta.application.DTO.json.HI0009.DeliveryInformationDTO;
import com.maxrocky.vesta.application.DTO.json.HI0009.HouseReceptionDTO;
import com.maxrocky.vesta.application.DTO.json.HI0009.InternalDTO;
import com.maxrocky.vesta.application.DTO.json.HI0010.RectipierJsomDTO;
import com.maxrocky.vesta.application.DTO.json.HI0012.*;
import com.maxrocky.vesta.application.model.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;

import java.text.ParseException;
import java.util.List;


/**
 * Created by Magic on 2016/4/29.
 */
public interface RectificationService {
    /**
     * 根据id 项目编码 time查询增量查询保修单信息
     * @param projectNum     项目编码
     * @param time    增量查询判断条件 时间
     * @param id      当前登陆人员工id
     * */
    RepairUpDateTimeDTO getAllRepairByApp(String id,String time,String projectNum);
    /**
     * 根据id 项目编码 time查询是否有更新
     * */

    QuestionUpdateCheckDTO getCountPropertyRepairForApp(String id,String time,String projectNum);
    /**
     * 批量判断项目是否有数据更新
     * */
    List<QuestionUpdateCheckAllDTO> getCheckoutAllByProjectNum(PropertyCheckYNDTO propertyCheckYNDTO);
    /**
     * 根据时质检app新增加保修单
     * */

    ApiResult savePropertyRepairForApp(PropertyRepairAppDTO repair,UserPropertyStaffEntity userPropertyStaffEntity);
    /**
     * 根据时质检app新增加保修单
     * */

    ApiResult savePropertyRepairForAppNew(PropertyRepairAppDTO repair,UserPropertyStaffEntity userPropertyStaffEntity);
    /**
     * 根据app传过来的id删除统计信息
     *
     * */
    int countMessage(String channelId,String type);
    /**
     * 根据token获取的员工信息 与app传过来的id进行匹配
     *
     * */
    int pushMessage(String userid,String channelId,String type);
    /**
     * 根据 id 时间戳 增量更新三级分类数据
     * @param time    增量查询判断条件 时间
     * @param id      增量id
     * */
    ThirdTypeClassUpTimeDTO getThirdClassNew(String id,String time);
    /**
     * 根据时间增量查询保修单信息
     * @param user      员工所有权限
     * @param time    增量查询判断条件 时间
     * @param id      当前登陆人员工id
     * */
    int getRepairForTimeCountNew(List user,String time,String id);
    /**
     * 根据时间增量查询保修单信息
     * @param user      员工所有权限
     * @param time    增量查询判断条件 时间
     * @param id      当前登陆人员工id
     * */
    String getRepairForTimeCount(List user,String time,String id,String userid);
    /**
     * 根据时间增量查询保修单信息
     * @param user      员工所有权限
     * @param time    增量查询判断条件 时间
     * @param id      当前登陆人员工id
     * */
    List<String> getRepairForTimedelete(List user,String time,String id,String userid);
    /**
     * 根据时间增量查询保修单信息
     * @param user     员工所有权限
     * @param time    增量查询判断条件 时间
     * @param id      当前登陆人员工id
     * */
    RepairCationUpTimeDTO getRepairForTimeNew(List user,String time,String id,String userid);
    /**
     * 根据时间统计活动增加
     * @param time    增量查询判断条件 时间
     * @param id      当前登陆人员工id
     * */
    PlanActivityCountDTO getPlanActivityCountTimeId(String id,String time);
    /**
     * 根据 id 时间戳 增量更新活动数据信息
     * @param time    增量查询判断条件 时间
     * @param id      增量id
     * */
    PalnActivityUpTimeDTO getPlanActivityForTimeId(String id,String time,String projectNum);
    /**
     * 根据 id 时间戳 增量更新楼栋数据
     * @param time    增量查询判断条件 时间
     * @param id      增量id
     * */
    BuildingMappingUpDTO getBuildingForTimeId(String id,String time,String projectNum);
    /**
     * 根据 id 时间戳 增量更新三级分类数据
     * @param time    增量查询判断条件 时间
     * @param id      增量id
     * */
    ThirdTypeClassUpTimeDTO getThirdClassForTimrId(String id,String time);
    /**
     * 根据时间增量查询保修单信息
     * @param groupid  员工所在组id
     * @param time    增量查询判断条件 时间
     * @param id      当前登陆人员工id
     * */
    RepairCationUpTimeDTO getRepairForTime(Object[] projectid,Object[] groupid,String time,String id);

    /**
     * 项目楼栋清单增量更新
     * @param groupid  员工所在组id
     * @param time    增量查询判断条件 时间
     * */
    BuildingListTimeDTO getProjectBuildForTime(Object[] groupid,String time);

    /**
     * 项目活动清单增量更新
     * @param projectid  员工所在组负责项目id
     * @param time    增量查询判断条件 时间
     * */
    PlanUpTimeListDTO getProjectPlanListForTime(Object[] projectid,String time);
    /**
     * 项目楼栋清单增量更新
     * @param groupid  员工所在组id
     * @param time    增量查询判断条件 时间
     * */
    int getBuildCountForTime(Object[] groupid,String time);
    /**
     * 项目活动清单增量统计
     * @param projectid  员工所在组负责项目id
     * @param time    增量查询判断条件 时间
     * */
    int getProjectPlanCountForTime(Object[] projectid,String time);
    /**
     * 获取整改单信息集合
     * */
    List<RectificationListDTO> getRectificationList(String id,String companyId);

    /**
     *
     * 接单后报修数据存入
     * */
    ReturnJsonDTO setRectificAction(List<RectificationListDTO> PropertyAllList,String id,String username) throws ParseException;


    /**
     * 判断当前订单是否已处理
     * */
    RectipierJsomDTO getRectificaType(String id,String type,String name);

    /**
     * 内部预检信息结合
     * */
    List<RectifyPlanDTO> getInternalPreInspection(String id,String type);

    /**
     * 三级分类查询ThirdTypeEntity
     * */
    List<ThirdTypeJsonDTO> getThirdTypeJson();
    /**
     * 工地开放阶段评价
     *
     * */
    ApiResult setHouseReception(List<HouseReceptionDTO> HouseReceptionList) throws ParseException;
    /**
     * 正式交房信息
     * */
    ApiResult setDeliveryList(List<DeliveryInformationDTO> DeliveryList) throws ParseException;
    /**
     * 内部语言
     * */
    ApiResult setInternalList(List<InternalDTO> DeliveryList) throws ParseException;



    /**
     * 项目楼栋清单
     * */
    List<PreInspectionList> getProjectListAllRoom();

    /**
     * 根据员工部门获取项目和楼栋清单
     * */
    List<PreInspectionList> getProjectForUser(String id);

    /**
     * 根据员工部门获取项目和楼栋清单
     * */
    List<PreInspectionList> getProjectForPROJECT(String id);

    /**
     * 重新派单
     * */
    int setRePieOrder(String id,String username,RePieOrderDTO RePieOrderDTO,String stafName);
    /**
     * Code:D
     * Type:
     * Describe:后台重新派单
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/8
     */
    int ToRePieOrder(UserInformationEntity userInformationEntity, PropertyRectifyAdminDTO propertyRectifyAdminDTO);

    /**
     *
     * 质检app新增报修单 重做接口Magic
     * */
    ApiResult saveRepairMagicAppNew(PropertyRepairAppDTO repair,UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 根据id 项目编码 time查询增量查询保修单信息 重做magic
     * @param projectNum     项目编码
     * @param time    增量查询判断条件 时间
     * @param id      当前登陆人员工id
     * */
    RepairTodoTimeMagicDTO getAllRepairMagicAppNew(String id,String time,String projectNum,List<String> user,String userid);


    /**
     * 根据时间增量查询当前登录人待办事项数据  Magic重做
     * @param user     员工所有权限
     * @param time    增量查询判断条件 时间
     * @param id      当前登陆人员工id
     * */
    RepairTodoTimeMagicDTO getRepairMagicTimeNew(List user,String time,String id,String userid);

    /**
     *
     * 接单后报修数据存入 重做接口Magic
     * */
    ReturnJsonDTO updateRepairNew(List<PropertyRepairToDoMagicDTO> repairList,String id,String username) throws ParseException;
}
