package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.SystemNotificationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.Page;

import java.util.List;

/**
 * Created by Admin on 2016/5/21.
 * 系统通知持久层
 */
public interface SystemNotificationRepository {

    /**
     * 查询系统通知信息列表
     * @param systemNotificationEntity
     * @param webPage
     * @return
     */
    List<SystemNotificationEntity> querySystemNotificationEntityList(SystemNotificationEntity systemNotificationEntity,WebPage webPage);

    /**
     * 取得展示的系统通知
     * @param systemNotificationEntity
     * @param page
     * @return
     */
    List<SystemNotificationEntity>  systemNotificationEntityList(SystemNotificationEntity systemNotificationEntity,Page page);

    /**
     * 新建系统通知信息
     * @param systemNotificationEntity
     */
    boolean saveSystemNotification(SystemNotificationEntity systemNotificationEntity);

    /**
     * 删除系统通知信息
     * @param systemNotificationEntity
     */
    boolean deleteSystemNotification(SystemNotificationEntity systemNotificationEntity);

    /**
     * 更新系统通知信息
     * @param systemNotificationEntity
     */
    boolean updateSystemNotification(SystemNotificationEntity systemNotificationEntity);

    /**
     * 根据ID 取得系统通知信息
     * @param notificationId
     * @return
     */
    SystemNotificationEntity getSystemNotificationEntity(String notificationId);


}
