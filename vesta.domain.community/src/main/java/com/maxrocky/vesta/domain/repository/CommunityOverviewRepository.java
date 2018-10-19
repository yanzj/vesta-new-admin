package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.CommunityDetailEntity;
import com.maxrocky.vesta.domain.model.CommunityOverviewEntity;
import com.maxrocky.vesta.domain.model.CommunityOverviewReservationEntity;
import com.maxrocky.vesta.domain.model.CommunityOverviewScopeEntity;
import com.maxrocky.vesta.hibernate.IHibernateDao;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyifan on 2016/5/09.
 */
public interface CommunityOverviewRepository extends IHibernateDao {

    /**
     * 保存或更新Entity
     * @param entity
     */
    <T> void saveOrUpdate(T entity);

    /**
     * Describe:根据计划id获取信息:分页查询批次信息
     * CreateBy:yifan
     * @param communityOverviewEntity
     * @param webPage
     * @return DeliveryPlanCrmEntity
     * @throws Exception
     */
    List<CommunityOverviewEntity> queryAllByPage(CommunityOverviewEntity communityOverviewEntity, WebPage webPage,Date staDate, Date endDate,String roleScopes) throws Exception;
    List<CommunityOverviewEntity> queryAllByPage(Map<String,Object> params, WebPage webPage) throws Exception;

    /**
     * 带条件查询
     * @param communityOverviewEntity
     * @return
     * @throws Exception
     */
    public List<CommunityOverviewEntity> queryAllByParam(CommunityOverviewEntity communityOverviewEntity,Date staDate, Date endDate) throws Exception;

    /**
     * 查询最大排序
     */
    public Integer getMaxOrderNum();

    /**
     * 查詢金茂樓盤項目
     */
    List<String> listProject();

    /**
     * 通过楼盘Id清空区域项目信息
     * @param communityOverviewId 楼盘Id
     */
    void deleteCommunityOverviewScope(String communityOverviewId);

    /**
     * 通过楼盘Id查询区域范围列表信息
     * @param communityId 楼盘Id
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> queryProjectByCommunityId(String communityId);

    /**
     * 通过项目Code集合检索城市列表信息
     * @param projectCodes  项目Code集合
     * @return Map<String,Object>
     */
    List<Map<String,Object>> queryCityByProjectIds(String projectCodes);

    /**
     * 通过楼盘Id检索发布范围列表
     * @param announcementId
     * @return
     */
    List<CommunityOverviewScopeEntity> getScopeByCommunityOverview(String announcementId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 通过楼盘id获取所有去编辑信息
    */
    List<CommunityDetailEntity> getCommunityDetailList(String communityId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 删除CommunityDetailEntity
    */
    void deleteCommunityDetailEntity(CommunityDetailEntity communityDetailEntity);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 保存去编辑信息
    */
    void saveCommunityDetailEntity(CommunityDetailEntity communityDetailEntity);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取该楼盘对应的所有CommunityDetailEntity
    */
    List<CommunityDetailEntity> getAllDetails(String communityId);

    /**
     * 通过楼盘ID及图片类型删除图片
     * @param overviewId 楼盘ID
     * @param imgType 图片类型
     */
    void deleteImgByOverview(String overviewId,String imgType);

    /**
     * 获取楼盘预约列表
     * @param paramsMap 参数
     * @return List<CommunityOverviewReservationEntity>
     */
    List<CommunityOverviewReservationEntity> getOverviewReservationList(Map<String,Object> paramsMap,WebPage webPage);
}
