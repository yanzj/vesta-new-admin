package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by liudongxin on 2016/1/14.
 * 物业报修数据操作接口
 */
public interface PropertyRepairRepository {
    /**---------------------------------业主端部分-------------------------------------*/
    /**
     * 历史报修列表：未完成/完成
     * @param roomId
     * @param type
     * @return
     */
    List<Object[]> getHistory(String type,String roomId,Page page);

    /**
     * 获取完成/未完成的总条数(已废弃)
     * @param type
     * @return
     */
    int getPageNum(String type);

    /**
     * 获取未报修数量
     * @return
     */
    Integer getRepairHistoryNum(UserInfoEntity user);

    /**
     * 添加
     * @param propertyRepairEntity
     */
    void saveRepair(PropertyRepairEntity propertyRepairEntity);

    /**
     * 获取报修详情
     * @param id
     * @return
     */
    PropertyRepairEntity getRepairDetail(String id);

    /**
     * 修改报修单
     * @param propertyRepairEntity
     */
    void updateRepair(PropertyRepairEntity propertyRepairEntity);

    /**
     * 获取投诉列表
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getComplaint(UserInfoEntity user,Page page);

    /**---------------------------------员工端部分-------------------------------------*/
    /**
     * 维修：获取待分配的任务(维修人员/维修主管)
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getRepairPending(String projectId,Page page);

    /**
     * 投诉：获取待分配的任务(客服主管)
     * @param page
     * @return
     */
    List<Object[]> getComplaintPending(String projectId,Page page);

    /**
     * 秩序：获取待分配的任务(秩序主管)
     * @param projectId
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getSequencePending(String projectId,Page page);

    /**
     * 环境：获取待分配的任务(环境主管)
     * @param projectId
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getEnvironmentPending(String projectId,Page page);

    /**
     * 获取所有待分配的任务(管理员)
     * @param projectId
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getAllPending(String projectId,Page page);

    /**
     * 获取待分配总条数(已废弃)
     * @param
     * @return
     */
    int getTaskPendingNum();

    /**
     * 维修：获取进行中的任务(维修主管)
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getRepairUnderway(String projectId,Page page);

    /**
     * 维修：获取自己进行中的任务(维修人员)
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getMyRepairUnderway(UserPropertyStaffEntity user,Page page);

    /**
     * 投诉：获取进行中的任务(客服主管)
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getComplaintUnderway(String projectId,Page page);

    /**
     * 投诉：获取自己进行中的任务(客服人员)
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getMyComplaintUnderway(UserPropertyStaffEntity user,Page page);

    /**
     * 秩序：获取进行中的任务(秩序主管)
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getSequenceUnderway(String projectId,Page page);

    /**
     * 秩序：获取自己进行中的任务(秩序人员)
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getMySequenceUnderway(UserPropertyStaffEntity user,Page page);

    /**
     * 环境：获取进行中的任务(环境主管)
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getEnvironmentUnderway(String projectId,Page page);

    /**
     * 环境：获取自己进行中的任务(环境人员)
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getMyEnvironmentUnderway(UserPropertyStaffEntity user,Page page);

    /**
     * 获取所有进行中的任务(管理员)
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getAllUnderway(String projectId,Page page);

    /**
     * 获取进行中总条数(已废弃)
     * @param
     * @return
     */
    int getTaskUnderwayNum();

    /**
     * 维修：获取已完成的任务(维修主管)
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getRepairComplete(String projectId,Page page);

    /**
     * 维修：获取自己已完成的任务(维修人员)
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getMyRepairComplete(UserPropertyStaffEntity user,Page page);

    /**
     * 投诉：获取已完成的任务(客服主管)
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getComplaintComplete(String projectId,Page page);

    /**
     * 投诉：获取自己已完成的任务(客服人员)
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getMyComplaintComplete(UserPropertyStaffEntity user,Page page);

    /**
     * 秩序：获取已完成的任务(秩序主管)
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getSequenceComplete(String projectId,Page page);

    /**
     * 秩序：获取自己已完成的任务(秩序人员)
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getMySequenceComplete(UserPropertyStaffEntity user,Page page);

    /**
     * 环境：获取已完成的任务(环境主管)
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getEnvironmentComplete(String projectId,Page page);

    /**
     * 环境：获取自己已完成的任务(环境人员)
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getMyEnvironmentComplete(UserPropertyStaffEntity user,Page page);

    /**
     * 获取所有已完成的任务(管理员)
     * @param page
     * @return
     */
    List<PropertyRepairEntity> getAllComplete(String projectId,Page page);

    /**
     * 随手报历史报修列表
     * @param user
     * @return
     */
    List<PropertyRepairEntity> getReportsHistory(UserPropertyStaffEntity user,Page page);

    /**---------------------------------管理系统部分-------------------------------------*/
    /**
     * 获取工单列表
     * @param propertyRepair
     * @param webPage
     * @return
     */
    List<PropertyRepairEntity> getWorkOrderList(PropertyRepairEntity propertyRepair,WebPage webPage,String roleScopes);

    /**
     * 获取报修单列表
     * @param obj
     * @param webPage
     * @return
     */
    List<Object[]> getRepairList(Object[] obj,WebPage webPage);

    /**
     * 删除/修改报修单
     * @param propertyRepairEntity
     */
    boolean updateRepaired(PropertyRepairEntity propertyRepairEntity);


    /**
     * 添加
     * @param propertyRepairCRMEntity
     */
    void saveRepairCrm(PropertyRepairCRMEntity propertyRepairCRMEntity);

    /**
     * 根据appID和保修单Id查询是否存在改数据
     * **/

    PropertyRepairCRMEntity queryRepairByIdOrappId(String id,String appId);

    /**
     * 添加
     * @param propertyRepairCRMEntity
     */
    void updateRepairCrm(PropertyRepairCRMEntity propertyRepairCRMEntity);

    /**
     * 检索物业报修服务问题答应列表
     * @param repairId 报修单ID
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getRepairServiceQuestionList(String repairId);

    /**
     * 获取物业报修完整数据列表
     */
    List<Map<String,Object>> getPropertyRepairCrmList(Map<String,Object> params, WebPage webPage);
}