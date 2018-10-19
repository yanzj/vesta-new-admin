package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ImgActivitiesEntity;
import com.maxrocky.vesta.domain.model.SharingActivitiesEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by sunmei on 2016/2/19.
 */
public interface SharingActivitiesRepository {
    /**
     * 分享活动列表
     * @return
     */
    List<SharingActivitiesEntity> SharingActivities(SharingActivitiesEntity sharingActivitiesEntity, WebPage webPage);



    /**
     * 创建分享活动
     * @param sharingActivitiesEntity
     */
    void createActivities(SharingActivitiesEntity sharingActivitiesEntity);
    //

    /**
     * 创建分享活动图片
     * @param imgActivitiesEntity
     */
    void createActivitiesImg(ImgActivitiesEntity imgActivitiesEntity);

    /****
     * 根据项目查询分享活动详情
     */
    List<SharingActivitiesEntity> getActivitiesManageByItemId(String itemId);


    /**
     * 根据id查询分享活动详情
     * @return
     */
    SharingActivitiesEntity getActivitiesById(String id);

    /**
     * 根据Imgid查询分享活动图片
     * @return
     */
    List<ImgActivitiesEntity> getActivitiesImgByImgId(String id);

    /**
     * 修改分享活动
     */
    void updateActivities(SharingActivitiesEntity sharingActivitiesEntity);

    /**
     * 修改分享活动
     */
    void updateActivitiesImg(ImgActivitiesEntity imgActivitiesEntity);


    /**
     * 根据Id删除活动
     * @param activitiesId
     */
    boolean deleteActivitiesById(String activitiesId,String imgId);

    /**
     * 根据Id删除图片
     */
    void deleteImgById(String imgId);

    List<SharingActivitiesEntity> getSharingActivity(String projectId);

    List<ImgActivitiesEntity> getImageListByAcitvityId(String id);


    /****
     * 排序
     */
    SharingActivitiesEntity getActivitiesManageBysortDown(int sort,String itemId);
    /****
     * 排序
     */
    SharingActivitiesEntity getActivitiesManageBysortUp(int sort,String itemId);

}
