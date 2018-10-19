package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.SystemNotificationDTO;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 2016/5/21.
 * 系统公告管理 业务逻辑层接口
 */
public interface SystemNotificationService {

    /**
     *取得系统通知列表
     * @param systemNotificationDTO
     * @param webPage
     * @return
     */
    List<SystemNotificationDTO> querySystemNotification(SystemNotificationDTO systemNotificationDTO, WebPage webPage);

    /**
     * 添加系统通告
     * @param systemNotificationDTO
     */
    String addSystemNotification(SystemNotificationDTO systemNotificationDTO,UserPropertyStaffEntity userProperty);

    /**
     * 更新系统通告
     * @param systemNotificationDTO
     */
    String editSystemNotification(SystemNotificationDTO systemNotificationDTO,UserPropertyStaffEntity userProperty);

    /**
     * 删除系统通告
     * @param systemNotificationId
     */
    String removeSystemNotification(String systemNotificationId,UserPropertyStaffEntity userProperty);

    /**
     * 根据ID取得系统公告
     * @param systemNotificationId
     * @return
     */
    SystemNotificationDTO getSystemNotificationDTOById(String systemNotificationId);

    /**
     * 导出excel
     * param:user
     * param:httpServletResponse
     * return
     */
    String exportExcel(UserPropertyStaffEntity user,SystemNotificationDTO systemNotification,HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException;

}
