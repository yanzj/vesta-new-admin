package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.hibernate.IHibernateDao;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liudongxin on 2016/4/22.
 */
public interface DeliveryPlanCRMRepository extends IHibernateDao {
    /**
     * 保存或更新Entity
     * @param entity
     */
    <T> void saveOrUpdate(T entity);

    /**
     * Describe:创建交付计划信息
     * CreateBy:liudongxin
     * CreateOn:2016-04-22 10:01:12
     */
    void create(DeliveryPlanCrmEntity deliveryPlanCrmEntity);

    /**
     * CreatedBy:liudongxin
     * Describe:修改交付计划信息
     * ModifyBy:
     */
    void update(DeliveryPlanCrmEntity deliveryPlanCrmEntity);
    /**
     * CreatedBy:liudongxin
     * Describe:修改交付计划信息
     * ModifyBy:
     */
    void updatetime(ActiveTemporaryTimeEntity ActiveTemporary);

    /**
     * Describe:根据计划id获取信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    DeliveryPlanCrmEntity getById(String id);
    /**
     * Describe:根据计划id获取信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    ActiveTemporaryTimeEntity getBytimeId(String id);

    /**
     * Describe:根据计划id获取信息:分页查询批次信息
     * CreateBy:yifan
     * CreateOn:2016-04-27 09:40:37
     *
     * @param deliveryPlanCrmEntity
     * @param webPage
     * @return DeliveryPlanCrmEntity
     * @throws Exception
     */
    public List<DeliveryPlanCrmEntity> queryDeliveryPlanCrmEntity(DeliveryPlanCrmEntity deliveryPlanCrmEntity, WebPage webPage,String roleScopes) throws Exception;

    /**
     * 条件查询
     * @param deliveryPlanCrmEntity
     * @return
     * @throws Exception
     */
    public List<DeliveryPlanCrmEntity> findAll(DeliveryPlanCrmEntity deliveryPlanCrmEntity,WebPage webPage) throws Exception;
    /**
     * 查询所有项目
     * CreateBy:yifan
     * CreateOn:2016-04-27 09:40:37
     *
     * @return
     */
    public LinkedHashMap<String, String> listProject();

    /**
     * 查询所有批次
     * CreateBy:yifan
     * CreateOn:2016-04-27 09:40:37
     *
     * @return
     */
    public List<String> listBatch();

    /**
     * 根据项目编号获取项目名称
     * CreateBy:yifan
     * CreateOn:2016-04-27 09:40:37
     *
     * @return
     */
    public String getProNameByProNum(String proNum);

    /**
     * 根据计划id查询房屋列表
     * CreateBy:yifan
     * CreateOn:2016-04-27 09:40:37
     *
     * @return 以逗号分隔的字符串
     */
    public List<String> getHouseListByPlanId(String id);
    /**
     * 根据计划id查询房屋列表
     *
     */
    public List<HouseInfoEntity> getHouseInfoListByPlanId(String id,WebPage webPage);

    /**
     * 通过交房计划ID获取交房详情信息列表 WeiYangDong_2017-01-04
     * @param id 交房计划ID
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getPlanHouseListByPlanId(String id,WebPage webPage);

    /**
     * 查询计划详情
     * CreateBy:yifan
     * CreateOn:2016-04-27 09:40:37
     *
     * @param userId      用户id
     * @param projectName 项目名称
     */
    public List<String> queryPersonalHouseByPlan(String userId, String projectName);

    /**
     * 查询预约时间
     * CreateBy:yifan
     * CreateOn:2016-04-29 09:40:37
     *
     * @param userId      用户id
     * @param planName 项目名称
     */
    public String queryReservationDate(String userId, String planName);

    /**
     * 查询项目名称，楼栋名称，单元名称，房间名称
     * CreateBy:yifan
     * CreateOn:2016-04-27 09:40:37
     *
     * @param userId      用户id
     * @param projectName 项目名称
     */
    public List<Object[]> queryHouseAddress(String userId, String projectName);

    public <E> E get(Class<E> entityClass, Serializable id);

    /**
     * 更新
     * @param communityReservation
     * @return
     */
    public void updateReservation(CommunityReservationListEntity communityReservation);

    /**
     * 添加
     * @param communityReservation
     * @return
     */
    public void saveReservation(CommunityReservationListEntity communityReservation);

    /**
     * 删除
     * @param communityReservation
     * @return
     */
    public void cancelReservation(CommunityReservationListEntity communityReservation);

    /**
     * 预约状态
     * @param communityReservation
     * @return
     */
    public Integer queryReservationStatus(CommunityReservationListEntity communityReservation);

    /**
     * Describe:获取业主预约信息列表
     * CreateBy:liudongxin
     * CreateOn:2016-04-29 10:01:12
     */
    public List<CommunityReservationListEntity> getReservationList(CommunityReservationListEntity userAppoint, WebPage webPage,String roleScopes);

    /**
     * 查询项目下所有批次信息
     * @param projectNum
     * @return
     */
    public List<String> queryBatchByProjectNum(String projectNum);
    /**
     * 根据项目获取该项目下所有项目
     * */
    public List<Object[]> queryPlanByProjectNum(String projectNum,List<String> listPlan);
    /**
     * 查询用户信息
     * @param id
     */
    public List<Object[]> queryUser(String id);

    /**
     * 根据房间编码查询活动编码
     * @return
     */
    Object[] getByRoomNum(String roomNum);

    /**
     * 通过userId查询交互预约消息列表
     * @param userId 用户Id
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getDeliveryPlanMsgByUser(String userId);

    /**
     * 通过userId和planName检索用户预约详情
     * @param userId 用户Id
     * @param planName  交付批次（计划）名称
     * @return CommunityReservationListEntity
     */
    CommunityReservationListEntity getReservationByUserAndPlan(String userId,String planName);

    /**
     * 通过userId检索用户预约详情
     * @param userId      用户id
     */
    CommunityReservationListEntity getReservationByUserId(String userId);

    /**
     * 获取交付计划时间段配置列表
     * @param deliveryPlanId 交付计划ID
     * @return List<DeliveryPlanReservationTimeRangeEntity>
     */
    List<DeliveryPlanReservationTimeRangeEntity> getReservationTimeRangeListByPlan(String deliveryPlanId,Date reservationDate);

    /**
     * 通过计划ID及预约日期删除时间段配置
     * @param deliveryPlanId 交付计划ID
     * @param reservationDate 预约日期
     */
    void deleteReservationTimeRangeByPlanAndDate(String deliveryPlanId,Date reservationDate);
}
