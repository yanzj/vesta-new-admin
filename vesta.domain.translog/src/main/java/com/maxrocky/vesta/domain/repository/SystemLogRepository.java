package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.InfoReleaseEntity;
import com.maxrocky.vesta.domain.model.SystemLogEntity;
import com.maxrocky.vesta.domain.model.UserDocumentsEntity;
import com.maxrocky.vesta.domain.model.UserVisitLogEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by chen on 2016/7/19.
 */
public interface SystemLogRepository {

    /**
     * 删除Entity
     * @param entity
     */
    <E> void delete(E entity);

    //新增日志
    void addSysLog(SystemLogEntity systemLogEntity);

    //根据条件获取日志列表
    List<SystemLogEntity> getSystemLogList(SystemLogEntity systemLogEntity,String beginTime,String endTime,WebPage webPage);

    //新增用户访问日志
    void addUserVisitLog(UserVisitLogEntity userVisitLogObj);

    //根据条件获取用户访问日志列表
    List<UserVisitLogEntity> getUserVisitLogList(UserVisitLogEntity userVisitLogObj,String beginTime,String endTime,WebPage webPage);

    //新增用户单据日志
    void addUserDocumentsLog(UserDocumentsEntity systemLogEntity);

    //根据条件获取用户访问单据列表
    List<UserDocumentsEntity> getUserDocumentsLogList(UserDocumentsEntity userVisitLogObj,String beginTime,String endTime,WebPage webPage,String roleScopes);

    //新增信息发布日志
    void addInfoReleaseLog(InfoReleaseEntity systemLogEntity);

    //根据条件获取用户访问单据列表
    List<InfoReleaseEntity> getInfoReleaseLogList(InfoReleaseEntity userVisitLogObj,String beginTime,String endTime,WebPage webPage);

    /**
     * 通过用户类型检索新增用户日志重复数据
     * @param userType 用户类型
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getSystemLogListByUserType(String userType);

    /**
     * 通过用户类型及手机号码检索新增用户日志重复数据
     * @param userType 用户类型
     * @param userMobile 用户手机
     * @return List<SystemLogEntity>
     */
    List<SystemLogEntity> getSystemLogListByUser(String userType,String userMobile);
}
