package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.PropertyRepairTaskEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.exception.GeneralException;

import java.util.List;

/**
 * Created by liudongxin on 2016/1/22.
 * 物业报修任务方法接口
 */
public interface PropertyRepairTaskService {
    /**---------------------------------业主端部分-------------------------------------*/
    /**
     * 获取报修进展
     * @param id：保修单id
     * @return
     * @throws GeneralException
     */
    ApiResult getTaskProgress(String id) throws GeneralException;

    /**
     * 继续报修
     * @param user：用户
     * @param id：保修单id
     * @return
     * @throws GeneralException
     */
    ApiResult repairContinue(UserInfoEntity user,String id) throws GeneralException;

    /**
     * 停止报修
     * @param user：用户
     * @param id：保修单id
     * @return
     * @throws GeneralException
     */
    ApiResult repairStop(UserInfoEntity user,String id) throws GeneralException;

    /**---------------------------------员工端部分-------------------------------------*/
    /**
     * 工单抢单
     * @param user：用户
     * @param id：保修单id
     * @return
     * @throws GeneralException
     */
    ApiResult repairScramble(UserPropertyStaffEntity user,WorkApportionDTO workApportionDTO) throws GeneralException;

    /**
     * 工单继续
     * @param user：用户
     * @param id：保修单id
     * @return
     * @throws GeneralException
     */
    ApiResult orderContinue(UserPropertyStaffEntity user,String id) throws GeneralException;

    /**
     * 工单派单
     * @param user
     * @param workOrderTelBookDTO
     * @return
     * @throws GeneralException
     */
    ApiResult repairsApportion(UserPropertyStaffEntity user,WorkOrderTelBookDTO workOrderTelBookDTO) throws GeneralException;

    /**
     * 工单退单
     * @param user
     * @param id
     * @return
     * @throws GeneralException
     */
    ApiResult chargeBack(UserPropertyStaffEntity user,String id) throws GeneralException;

    /**
     * 通过报修单id获取工单进展
     * @param id：保修单id
     * @return
     * @throws GeneralException
     */
    ApiResult getRepairsProgress(String id) throws GeneralException;

    /**
     * 通过报修单id获取工单进展内容
     * @param id
     * @return
     * @throws GeneralException
     */
    ApiResult getTaskContent(String id) throws GeneralException;

    /**
     * 添加进展任务内容
     * @param user
     * @param workApportionDTO
     * @return
     * @throws GeneralException
     */
    ApiResult repairsContent(UserPropertyStaffEntity user,WorkApportionDTO workApportionDTO) throws GeneralException;

    /**
     * 提交抵达现场
     * @param user
     * @param id
     * @return
     * @throws GeneralException
     */
    ApiResult arriveLocale(UserPropertyStaffEntity user,String id) throws GeneralException;

    /**
     * 工单暂停
     * @param user
     * @param id
     * @return
     * @throws GeneralException
     */
    ApiResult repairPause(UserPropertyStaffEntity user,String id) throws GeneralException;

    /**
     * 工单全部维修完成
     * @param user
     * @param id
     * @return
     * @throws GeneralException
     */
    ApiResult repairComplete(UserPropertyStaffEntity user,String id) throws GeneralException;

    /**
     * 工单回访完成
     * @param user
     * @param id
     * @return
     * @throws GeneralException
     */
    ApiResult repairVisiting(UserPropertyStaffEntity user,String id) throws GeneralException;

    /**
     * 随手报：获取报修进展
     * @param id：保修单id
     * @return
     * @throws GeneralException
     */
    ApiResult reportsProgress(String id) throws GeneralException;

    /**
     * 随手报：继续报修
     * @param user：用户
     * @param id：保修单id
     * @return
     * @throws GeneralException
     */
    ApiResult reportsContinue(UserPropertyStaffEntity user,String id) throws GeneralException;

    /**
     * 随手报：停止报修
     * @param user：用户
     * @param id：保修单id
     * @return
     * @throws GeneralException
     */
    ApiResult reportsStop(UserPropertyStaffEntity user,String id) throws GeneralException;

    /**---------------------------------管理端部分-------------------------------------*/
    /**
     * 反馈显示
     * @param user
     * @param workOrderContentDTO
     * @return
     */
    WorkOrderContentDTO repairFreeBack(UserPropertyStaffEntity user,WorkOrderContentDTO workOrderContentDTO);

    /**
     * 反馈提交
     * @param user
     * @param workOrderContentDTO
     * @return
     */
    String saveFreeBack(UserPropertyStaffEntity user,WorkOrderContentDTO workOrderContentDTO);

    /**
     * 分配显示
     * @param user
     * @param workApportionDTO
     * @return
     */
    WorkApportionDTO repairsAssign(UserPropertyStaffEntity user,WorkApportionDTO workApportionDTO);

    /**
     * 分配提交
     * @param user
     * @param workApportionDTO
     * @return
     */
    String saveAssign(UserPropertyStaffEntity user,WorkApportionDTO workApportionDTO);

    /**
     * 获取报修进展
     * @param repairId
     * @return
     */
    PropertyProgressInfoDTO repairProgress(String repairId);

    /**
     * 根据UserId取得用户报修消息
     * @param userId
     * @return
     */
    List<PropertyRepairTaskDTO> getPersonalCollection(String userId,UserInfoEntity user,String visit);

    /**
     * 更新已读状态
     * @return
     */
    ApiResult editPropertyRepairTask(String taskId);


    /**
     * 根据UserId取得未读消息条数
     * @param userId
     * @return
     */
    ApiResult getPersonalCollectionNum(String userId);
}