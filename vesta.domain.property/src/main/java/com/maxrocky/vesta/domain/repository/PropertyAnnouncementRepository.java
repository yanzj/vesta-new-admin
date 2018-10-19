package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.PropertyAnnouncementEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.Page;

import java.util.List;

/**
 * Created by JillChen on 2016/1/23.
 * 物业公告 持久层接口
 */
public interface PropertyAnnouncementRepository {
    List<PropertyAnnouncementEntity> propertyAnnouncementList(String projectId,Page page);

    List<PropertyAnnouncementEntity> propertyAnnouncementList(String projectId);

    PropertyAnnouncementEntity propertyAnnouncementDetail(String announcementId);

    /*************************************************以下为后端******************************************************/
    /**
     * 查询物业公告列表
     * @param propertyAnnouncement
     * @return
     */
    List<PropertyAnnouncementEntity> queryPropertyAnnouncement(PropertyAnnouncementEntity propertyAnnouncement,WebPage webPage);

    /**
     * 删除物业公告信息
     * @param propertyAnnouncement
     * @return
     */
    boolean delPropertyAnnouncement(PropertyAnnouncementEntity propertyAnnouncement);

    /**
     * 更新物业公告信息
     * @param propertyAnnouncementEntity
     * @return
     */
    void changePropertyAnnouncement(PropertyAnnouncementEntity propertyAnnouncementEntity);

    /**
     * 添加物业公告信息
     * @param propertyAnnouncement
     * @return
     */
    boolean savePropertyAnnouncement(PropertyAnnouncementEntity propertyAnnouncement);



}
