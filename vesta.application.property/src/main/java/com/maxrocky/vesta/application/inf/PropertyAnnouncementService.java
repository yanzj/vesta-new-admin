package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.PropertyAnnouncementPageDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.CollectionEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.Page;

import java.util.List;

/**
 * Created by JillChen on 2016/1/23.
 * 物业公告管理 业务逻辑层接口
 */
public interface PropertyAnnouncementService {
    /**
     * 物业公告列表 (前端app接口)
     * @param page
     * @return
     */
    ApiResult propertyAnnouncementList(UserInfoEntity user,Page page);

    /**
     * 获取最新的标题和id
     * @return
     */
    ApiResult homeProperty(UserInfoEntity user);

    /***********************************************以下为后端***************************************************/

    /**
     * 初始化物业公告列表(后端)
     * @param propertyAnnouncement
     * @return
     */
    List<PropertyAnnouncementPageDTO> queryPropertyAnnouncement(PropertyAnnouncementPageDTO propertyAnnouncement,WebPage webPage);

    /**
     * 根据物业公告信息ID 删除信息
     * @param announcementId
     * @param userPropertystaffEntity
     * @return
     */
    String removePropertyServicesById(String announcementId, UserPropertyStaffEntity userPropertystaffEntity);

    /**
     * 物业公告管理 添加或修改信息
     * @return
     */
    boolean saveORupdatePropertyAnnouncement(UserPropertyStaffEntity userPropertystaffEntit,PropertyAnnouncementPageDTO propertyAnnouncementPage);

    /**
     * 根据物业公告ID 查询信息详情
     * @param announcementId
     * @return
     */
    PropertyAnnouncementPageDTO queryPropertyAnnouncementById(String announcementId);


}
