package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.PropertyRepairTaskEntity;

import java.util.List;

/**
 * Created by liudongxin on 2016/1/22.
 * 物业报修任务表数据操作接口
 */
public interface PropertyRepairTaskRepository {
    /**---------------------------------业主端部分-------------------------------------*/
    /**
     * 添加物业报修任务
     * @param propertyRepairTaskEntity
     */
    void createRepairTask(PropertyRepairTaskEntity propertyRepairTaskEntity);

    /**
     * 获取任务进展(业主端)[已废弃]
     * @param id
     * @return
     */
    List<Object[]> getTaskProgress(String id);

    /**
     * 通过报修单id获取任务进展(员工端)
     * @param id：报修单id
     * @return
     */
    List<PropertyRepairTaskEntity> getTasksProgress(String id);

    /**
     * 通过任务id获取任务内容
     * @param id
     * @return
     */
    PropertyRepairTaskEntity getTaskInfo(String id);

    /**
     * 获取维修人员id
     * @param id：任务id
     * @return
     */
    PropertyRepairTaskEntity getTaskUserId(String id);

    /**
     * 获取提交报修的任务时间(员工端)
     * @param id
     * @return
     */
    List<PropertyRepairTaskEntity> getTaskDateOne(String id);

    /**
     * 获取派工信息的任务时间(员工端)
     * @param id
     * @return
     */
    List<PropertyRepairTaskEntity> getTaskDateTwo(String id);

    /**
     * 获取维修进展的任务时间(员工端)
     * @param id
     * @return
     */
    List<PropertyRepairTaskEntity> getTaskDateThree(String id);

    /**
     * 获取维修完成的任务时间(员工端)
     * @param id
     * @return
     */
    List<PropertyRepairTaskEntity> getTaskDateFour(String id);

    /**
     * 获取业主回访的任务时间(员工端)
     * @param id
     * @return
     */
    List<PropertyRepairTaskEntity> getTaskDateFive(String id);

    /**
     * 获取报修受理的任务时间(员工端)
     * @param id
     * @return
     */
    List<PropertyRepairTaskEntity> getTaskDateSix(String id);

    /**
     * 获取报修关闭的任务时间(员工端)
     * @param id
     * @return
     */
    List<PropertyRepairTaskEntity> getTaskDateSeven(String id);

    /**
     * 根据UserId取得用户报修消息
     * @param userId
     * @return
     */
    List<Object[]> getPersonalCollection(String userId);

    /**
     * 根据Id获得任务
     * @param taskId
     * @return
     */
    PropertyRepairTaskEntity getPropertyRepairTaskById(String taskId);

    /**
     * 更新已读状态
     * @return
     */
    void editPropertyRepairTask(PropertyRepairTaskEntity propertyRepairTaskEntity);

    /**
     * 根据UserId取得用户报修消息
     * @param userId
     * @return
     */
    String getPersonalCollectionNum(String userId);

    /**
     * 通过报修单Id及维修状态值修改该状态(多条相同状态)为已读
     * @param repairId  报修单Id
     * @param taskStatus    维修状态值
     */
    void updateRepairTaskReadStatus(String repairId,String taskStatus);
}