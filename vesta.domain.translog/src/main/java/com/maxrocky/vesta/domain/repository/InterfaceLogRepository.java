package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.*;

/**
 * Created by liudongxin on 2016/4/22.
 */
public interface InterfaceLogRepository {
    /**
     * Describe:创建接口日志信息
     * CreateBy:liudongxin
     * CreateOn:2016-04-22 10:01:12
     */
    void create(InterfaceLogEntity interfaceLogEntity);

    /**
     * Describe:创建接口日志信息(批次计划)
     * CreateBy:Magic
     * CreateOn:2017-07-05
     */
    void createPlanLog(InterfacePlanLogEntity interfacePlanLogEntity);

    /**
     * CreatedBy:liudongxin
     * Describe:修改接口日志信息
     * ModifyBy:
     */
    void update(InterfaceLogEntity interfaceLogEntity);

    /**
     * Describe:根据日志id获取信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    InterfaceLogEntity getById(String id);
    /**
     * Describe:创建接口日志信息
     * CreateBy:liudongxin
     * CreateOn:2016-04-22 10:01:12
     */
    void createRepair(InterFaceRepairLogsEntity interFaceRepairLogsEntity);

    /**
     * Describe:创建接口日志信息
     * CreateBy:liudongxin
     * CreateOn:2016-04-22 10:01:12
     */
    void createRectify(InterFaceRectifyLogsEntity interFaceRectifyLogsEntity);


    /**
     * Describe:创建接口日志信息(项目分类人员信息)
     * CreateBy:Magic
     * CreateOn:2017-07-18
     */
    void createClassUserLog(InterfaceClassUserLogEntity interfaceClassUserLogEntity);
    /**
     * Describe:创建接口日志信息(投诉单)
     * CreateBy:Magic
     * CreateOn:2017-07-18
     */
     void createComplainLog(InterfaceComPlainLogEntity interfaceComPlainLogEntity);
}
