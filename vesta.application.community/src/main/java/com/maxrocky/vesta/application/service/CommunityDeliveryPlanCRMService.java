package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.CommunityReservationListDto;
import com.maxrocky.vesta.application.admin.dto.DeliveryPlanCrmDto;
import com.maxrocky.vesta.application.admin.dto.UserAppointDto;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/4/26
 * Time: 10:10
 * Describe:
 */
public interface CommunityDeliveryPlanCRMService {
    /**
     * Describe:创建交付计划信息
     * CreateBy:liudongxin
     * CreateOn:2016-04-22 10:01:12
     */
    void create(DeliveryPlanCrmDto deliveryPlanCrmDto);

    /**
     * CreatedBy:liudongxin
     * Describe:修改交付计划信息
     * ModifyBy:
     */
    void update(DeliveryPlanCrmDto deliveryPlanCrmDto) throws InvocationTargetException, IllegalAccessException;

    /**
     * Describe:修改交付计划信息
     * ModifyBy:
     */
    void updateShortName(DeliveryPlanCrmDto deliveryPlanCrmDto) throws InvocationTargetException, IllegalAccessException;

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
     * @param deliveryPlanCrmDto
     * @param webPage
     * @return  DeliveryPlanCrmEntity
     * @throws Exception
     */
    public List<DeliveryPlanCrmDto> queryDeliveryPlanCrm(DeliveryPlanCrmDto deliveryPlanCrmDto, WebPage webPage) throws Exception;
    public List<DeliveryPlanCrmDto> queryDeliveryPlanCrm_1(DeliveryPlanCrmDto deliveryPlanCrmDto, WebPage webPage) throws Exception;

    /**
     * 查询所有项目
     * @return
     */
    public LinkedHashMap<String,String> listProject();

    /**
     * 查询所有交付批次
     * @return
     */
    public List<String> listBatch();

    /**
     * 删除
     * @param deliveryPlanCrmEntity
     */
    public void updateStatus(DeliveryPlanCrmEntity deliveryPlanCrmEntity);
    /**
     * 删除
     * @param deliveryPlanCrmEntity
     */
    public void updateStatuss(UserPropertyStaffEntity user,DeliveryPlanCrmEntity deliveryPlanCrmEntity);

    /**
     * 根据计划id查询房屋列表
     * @return 以逗号分隔的字符串
     */
    public String getHouseListByPlanId(String id);

    /**
     * 根据计划ID检索房屋信息列表
     * @param id
     * @return
     */
    public List<UserAppointDto> searchHouseListByPlanId(String id,WebPage webPage,DeliveryPlanCrmEntity deliveryPlanCrmEntity);

    /**
     * 查询所有CRM批次信息进行展示
     * @return
     */
    public ApiResult queryAllDeliveryPlanCrmToIntrface(String userId,String projectCode,String cityId,WebPage webPage) throws GeneralException;

    /**
     * 查询计划详情根据计划id
     * @param planId
     * @return
     * @throws GeneralException
     */
    public ApiResult queryBatchDetailById(String planId) throws GeneralException;

    /**
     * 查询计划详情根据计划id
     * @param planId
     * @return
     * @throws GeneralException
     */
    public ApiResult queryBatchDetailById(String planId,String userId) throws GeneralException;

    /**
     * 获取个人在当前项目下的房产信息
     * @param userId
     * @param ProjectName
     * @return
     */
    public ApiResult queryPersonalHouseByPlan(String userId,String ProjectName);

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
     * 保存预约单
     * @param userInfo
     * @param communityReservationListDto
     */
    void saveReservation(UserInfoEntity userInfo, CommunityReservationListDto communityReservationListDto);
    /**
     * 更新预约单
     * @param userInfo
     * @param communityReservationListDto
     */
    void updateReservation(UserInfoEntity userInfo, CommunityReservationListDto communityReservationListDto);
    /**
     * 取消预约单
     * @param userInfo
     * @param communityReservationListDto
     */
    void cancelReservation(UserInfoEntity userInfo, CommunityReservationListDto communityReservationListDto);

    /**
     * 查询预约状态
     * @param userInfo
     * @param communityReservationListDto
     * @return
     */
    public Integer queryReservationStatus(UserInfoEntity userInfo, CommunityReservationListDto communityReservationListDto);

    /**
     * 查询项目下所有批次信息
     * @param projectNum
     * @return
     */
    public List<String> queryBatchByProjectNum(String projectNum);

    /**
     * 通过交房计划ID获取交房详情信息列表 WeiYangDong_2017-01-05
     */
    List<Map<String,Object>> getPlanHouseListByPlanId(String id,WebPage webPage);

    /**-------------------------------------管理系统端：业主预约管理接口----------------------------------------------*/
    /**
     * CreatedBy:liudongxin
     * Describe:获取业主预约信息列表
     * ModifyBy:
     */
    List<UserAppointDto> reservationList(UserAppointDto userAppointDto,WebPage webPage);

    /**
     * 根据userinfo。id = usercrm。member。id 查询usercrm。信息
     * @param id
     */
    public List<Object[]> queryUser(String id);

    /**
     * CreatedBy:liudongxin
     * Describe:通过单元获取交付批次
     * ModifyBy:
     */
    //LinkedHashMap BatchList();

    /**
     * 通过userId查询交互预约消息列表
     * @param userId 用户Id
     * @return List<Map<String,Object>>
     */
     List<Map<String,Object>> getDeliveryPlanMsgByUser(String userId);

    /**
     * 导出excel
     * param:user
     * param:httpServletResponse
     * return
     */
    String exportExcel(UserPropertyStaffEntity user,DeliveryPlanCrmDto deliveryPlanCrmDto,
                       UserAppointDto userAppointDto, HttpServletResponse httpServletResponse,
                       String type, HttpServletRequest httpServletRequest) throws Exception;

    /**
     * 获取交付计划时间段配置列表
     */
    List<DeliveryPlanReservationTimeRangeEntity> getReservationTimeRangeList(DeliveryPlanCrmDto deliveryPlanCrmDto);

    /**
     * 保存交付预约时间段配置
     */
    void saveReservationTimeRange(DeliveryPlanCrmDto deliveryPlanCrmDto);
}
