package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.Page;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwei on 2016/1/13.
 */
public interface CommunityRespository {

    /**
     * 保存或更新Entity
     * @param entity
     */
    <T> void saveOrUpdate(T entity);

    /**
     * 通过公告Id检索发布范围列表
     * @param activityId    活动Id
     * @return  List<CommunityActivityInfoScopeEntity>
     */
    List<CommunityActivityInfoScopeEntity> getScopeByActivity(String activityId);

    void add();


    List<Object> getCommuntiyActivityList(Page page, Class clazz, String projectId);

    /**
     * CreatedBy:liuwei
     * Describe:获取活动详情(万达、金茂)
     * ModifyBy:
     * param id:活动id
     */
    CommunityActivityInfoEntity getCommuntiyActivityInfo(String id, String projectId);

    List<CommunityImageInfoEntity> getCommunityImageListByActivityId(String id);

    List<CommunityActivityInfoEntity> getHomeActivityEntityList();

    CommunityImageInfoEntity getFirstImageEntity(String activityId);

    CommunityActivityApplyInfoEntity getApplyInfoEntity(String id, String userId);
    CommunityActivityApplyInfoEntity getUserApplyInfo(String activityId, String userId);

    void saveApplyInfoEntity(CommunityActivityApplyInfoEntity applyInfoEntity);

    void saveCommunityInfo(CommunityActivityInfoEntity communityActivityInfoEntity);

    void saveCommnuityImageInfo(CommunityImageInfoEntity imageInfoEntity);

    /**
     * 后台活动分页列表
     * @param communityActivityInfoEntity
     * @param webPage
     * @return
     */
    public List<CommunityActivityInfoEntity> listActivityInfo(CommunityActivityInfoEntity communityActivityInfoEntity, WebPage webPage,String roleScopes);

    /**
     * 获取某个活动的报名人数
     * @param activityId
     * @return
     */
    public int countApply(String activityId);

    /**
     * 修改活动详情
     * @param communityActivityInfoEntity
     * @return
     */
    public boolean updateActivity(CommunityActivityInfoEntity communityActivityInfoEntity);

    /**
     * 获取报名信息列表
     * @param communityActivityInfoEntity
     * @param communityActivityApplyInfoEntity
     * @param userInfoEntity
     * @param webPage
     * @return
     */
    public List<Object> listActivityApply(CommunityActivityInfoEntity communityActivityInfoEntity,CommunityActivityApplyInfoEntity communityActivityApplyInfoEntity,UserInfoEntity userInfoEntity, WebPage webPage);

    /**
     * 获取报名信息列表
     * @param communityActivityInfoEntity
     * @param communityActivityApplyInfoEntity
     * @param communityActivityInfoScopeEntity
     * @param userInfoEntity
     * @param webPage
     * @return
     */
    public List<Object> listActivityApplys
    (CommunityActivityInfoEntity communityActivityInfoEntity,
     CommunityActivityApplyInfoEntity communityActivityApplyInfoEntity,
     CommunityActivityInfoScopeEntity communityActivityInfoScopeEntity,
     UserInfoEntity userInfoEntity,
     String roleScopes);



    /**
     * 获取报名信息列表
     * @param communityActivityInfoEntity
     * @param communityActivityApplyInfoEntity
     * @param communityActivityInfoScopeEntity
     * @param userInfoEntity
     * @param webPage
     * @return
     */
    public List<Object> activityProjectApply
    (CommunityActivityInfoEntity communityActivityInfoEntity,
     CommunityActivityApplyInfoEntity communityActivityApplyInfoEntity,
     CommunityActivityInfoScopeEntity communityActivityInfoScopeEntity,
     UserInfoEntity userInfoEntity);

    List<CommunityActivityInfoEntity> getCommunityListByProjectId(String projectId, String communitys);

    List<CommunityActivityInfoEntity> getLastestActivityInfoByProjectId(String projectId);

    CommunityActivityInfoEntity getCommunityByProjectIdAndType(String projectId, Integer activity_share_type);

    void saveAdminCommunityActivityInfo(CommunityActivityInfoEntity communityActivityInfoEntity);

    /**
     * 后台保存图片
     * @param communityImageInfoEntity
     */
    void saveAdminCommunityImage(CommunityImageInfoEntity communityImageInfoEntity);

    /**
     * 根据活动ID查询图片
     * @param activityId
     * @return
     */
    List<CommunityImageInfoEntity>  getCommunityImageById(String activityId);

    /**
     * 更新图片
     * @param communityImageInfoEntity
     * @return
     */
    public boolean updateCommunityImage(CommunityImageInfoEntity communityImageInfoEntity);

    /**
     * 根据id获取图片信息
     * @param imageId
     * @return
     */
    public CommunityImageInfoEntity getImageById(String imageId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 报名状态更改
    */
    public void changeStatus(CommunityActivityInfoEntity communityActivityInfoEntity);


    /*********************************************金茂项目******************************************************/

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 获取所有活动
     * ModifyBy:
     */
    List<CommunityActivityInfoEntity> getAllActivities();

    /**
     * CreatedBy:liudongxin
     * 获取活动列表
     * Describe:
     * 游客、普通用户查看所有公开的活动
     * ModifyBy:
     */
    List<CommunityActivityInfoEntity> getOpenActivities(WebPage page);

    /**
     * CreatedBy:liudongxin
     * 获取活动列表
     * Describe:
     * 游客、普通用户查看所有公开的活动
     * ModifyBy:
     */
    List<CommunityActivityInfoEntity> getOpenActivitiesByProjectId(WebPage page,String projectCode);

    List<CommunityActivityInfoEntity> getOpenActivitiesByCity(WebPage page,String cityId);

    /**
     * CreatedBy:liudongxin
     * 获取活动列表
     * Describe:
     * 业主、同住人查看公开和本小区公开的活动
     * ModifyBy:
     * param projectId:项目id
     * param page:分页
     */
    List<Object[]> getActivities(String projectId,WebPage page);

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 查看我已报名的活动列表
     * param userId:用户id
     * param page:分页
     * ModifyBy:
     */
    List<Object[]> getMyActivities(String userId,WebPage page);

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 查看已报名信息
     * ModifyBy:
     * param activityId:活动id
     */
    List<CommunityActivityApplyInfoEntity> getApplyInfo(String activityId);

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 查看所有报名信息
     * ModifyBy:
     * param activityId:活动id
     */
    List<CommunityActivityApplyInfoEntity> getAllApplyInfo(String activityId);

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 活动取消创建
     * ModifyBy:
     */
    void saveCancel(CommunityCancelEntity communityCancel);

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 查询取消信息
     * param userId:用户id
     * ModifyBy:
     */
    CommunityCancelEntity getCancelInfo(String userId);

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 查询取消信息
     * ModifyBy:
     */
    List<CommunityCancelEntity> getCancelInfo();

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 通过id查询取消信息
     * ModifyBy:
     */
    CommunityCancelEntity getCancelInfoById(String id);

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改取消信息
     * ModifyBy:
     */
    void updateCancle(CommunityCancelEntity communityCancelEntity);

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改活动信息
     * ModifyBy:
     */
    void updateActivityInfo(CommunityActivityInfoEntity communityActivityInfoEntity);

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改报名信息
     * ModifyBy:
     */
    void updateApplyInfo(CommunityActivityApplyInfoEntity communityActivityApplyInfo);

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 保存报名信息
     * ModifyBy:
     */
    void saveApplyInfo(CommunityActivityApplyInfoEntity communityActivityApplyInfo);

    /**
     * 通过活动Id查询区域范围列表信息
     * @param activityId 活动Id
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> queryProjectByActivityId(String activityId);

    /**
     * 通过主键Id检索活动详情
     * @param activityId
     * @return
     */
    CommunityActivityInfoEntity queryActivityInfoById(String activityId);

    /**
     * 通过活动Id删除活动区域中间表信息
     * @param activityId    活动Id
     */
    void deleteCommunityActivityInfoScope(String activityId);

    List<Object[]> getActivitiesByActId(String activeId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 通过活动id拿到所有活动项目
    */
    public List<CommunityActivityInfoScopeEntity> getAllActivityProject(String activityId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取该用户已报名的活动开始时间早于报名开始时间的全部活动
    */
    List<CommunityActivityInfoEntity> getActivityByEndDate(String userId);

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 获取该用户已报名的活动开始时间晚于报名开始时间的全部活动
     */
    List<CommunityActivityInfoEntity> getActivityByStartDate(String userId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取已报名的活动详情
    */
    CommunityActivityApplyInfoEntity getCommunityApply(String userId, String activityId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 个人活动消息已读标记
    */
    void changeReadStatus(String userId, String activityId);

    /**
     * 通过条件检索活动报名信息列表 WeiYangDong_2016-12-19
     * @param params 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getApplyList(Map<String,Object> params,WebPage webPage);

    /**
     * 通过条件检索活动报名信息列表 WeiYangDong_2016-12-19
     * @param params 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getApplyListOld(Map<String,Object> params,WebPage webPage);

    /**
     * 通过活动ID及报名日期删除时间段配置
     * @param activityId 活动ID
     * @param applyDate 报名日期
     */
    void deleteApplyTimeRangeByActivityAndDate(String activityId,Date applyDate);

    /**
     * 获取活动报名时间段配置列表
     * @param activityId 活动ID
     * @return List<CommunityActivityApplyTimeRangeEntity>
     */
    List<CommunityActivityApplyTimeRangeEntity> getApplyTimeRangeListByActivity(String activityId,Date applyDate);
}