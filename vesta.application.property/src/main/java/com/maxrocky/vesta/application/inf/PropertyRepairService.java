package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.PropertyRepairDTO;
import com.maxrocky.vesta.application.DTO.WorkOrderDetailDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.HouseInfoEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.Page;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by liudongxin on 2016/1/14.
 * 物业报修方法
 */
public interface PropertyRepairService {

    /**---------------------------------业主端：物业报修接口-------------------------------------*/
    /**
     * 历史报修列表：未完成/完成
     * @param type
     * @return
     */
    ApiResult getRepairHistory(String type,UserInfoEntity user, Page page) throws GeneralException;

    /**
     * 获取未报修数量
     * @return
     * @throws GeneralException
     */
    ApiResult getRepairHistoryNum(UserInfoEntity user) throws GeneralException;

    /**
     * 添加物业报修(维修和投诉)
     * @param user
     * @param workOrderDetailDTO
     * @return
     */
    ApiResult createRepairOrder(UserInfoEntity user,WorkOrderDetailDTO workOrderDetailDTO) throws GeneralException;

    /**
     * 微信添加物业报修(维修和投诉)
     * @param user
     * @param workOrderDetailDTO
     * @return
     */
    ApiResult createWeChatRepairOrder(UserInfoEntity user,WorkOrderDetailDTO workOrderDetailDTO) throws GeneralException;

    /**
     * 获取业主信息
     * @param user
     * @return
     */
    ApiResult getOwnerInfo(UserInfoEntity user) throws GeneralException;

    /**
     * 微信获取业主信息
     * @param user
     * @return
     */
    ApiResult getWeChatOwnerInfo(UserInfoEntity user) throws GeneralException;

    /**
     * 获取报修价格
     * @param projectId
     * @return
     * @throws GeneralException
     */
    ApiResult repairPrice(String projectId) throws GeneralException;

    /**
     * 获取历史报修详情信息
     * @param id
     * @return
     */
    ApiResult getRepairDetail(String id) throws GeneralException;

    /**-------------------------------------业主端：投诉接口--------------------------------------------------*/
    /**
     * 投诉列表
     * @param user
     * @return
     */
    ApiResult getComplaint(UserInfoEntity user, Page page);

    /**-------------------------------------员工端：工单管理接口----------------------------------------------*/
    /**
     * 获取工单列表(维修和投诉)
     * @param type
     * @param
     * @return
     * @throws GeneralException
     */
    ApiResult getWorkOrders(String type, UserPropertyStaffEntity user,Page page) throws GeneralException;

    /**
     * 获取工单已完成列表(维修和投诉)
     * @param page
     * @return
     * @throws GeneralException
     */
    ApiResult getRepairsComplete(String type,UserPropertyStaffEntity user,Page page) throws GeneralException;

    /**
     * 获取工单详情信息
     * @param id
     * @return
     * @throws GeneralException
     */
    ApiResult getRepairsInfo(String id) throws GeneralException;

    /**-------------------------------------员工端：随手报接口----------------------------------------------*/
    /**
     * 添加工单
     * @param user
     * @param workOrderDetailDTO
     * @return
     */
    ApiResult repairOrders(UserPropertyStaffEntity user,WorkOrderDetailDTO workOrderDetailDTO) throws GeneralException;

    /**
     * 获取员工信息
     * @param user
     * @return
     */
    ApiResult getEmployeeInfo(UserPropertyStaffEntity user) throws GeneralException;

    /**
     * 随手报历史列表
     * @param user
     * @param page
     * @return
     * @throws GeneralException
     */
    ApiResult getReports(UserPropertyStaffEntity user,Page page) throws GeneralException;

    /**
     * 获取随手报详情信息
     * @param id
     * @return
     */
    ApiResult getReportsDetail(String id) throws GeneralException;

    /**-------------------------------------管理系统端：工单管理接口----------------------------------------------*/
    /**
     * 获取报修信息列表
     * @param workOrderDetail
     * @param webPage
     * @return
     */
    List<WorkOrderDetailDTO> repairInfoList(UserPropertyStaffEntity user,WorkOrderDetailDTO workOrderDetail,WebPage webPage);

    /**
     * 获取报修列表
     * @param workOrderDetail
     * @param webPage
     * @return
     */
    List<WorkOrderDetailDTO>repairList(UserPropertyStaffEntity user,WorkOrderDetailDTO workOrderDetail,WebPage webPage);

    /**
     * 删除
     * @param user
     * @param repairId
     * @return
     */
    String removeRepairById(UserPropertyStaffEntity user,String repairId);

    /**
     * 获取报修信息
     * @param propertyRepairDTO
     * @return
     */
    PropertyRepairDTO repairInfo(PropertyRepairDTO propertyRepairDTO);

    /**
     * 优化导出Excel
     * @param title
     * @param headers
     * @param out
     * @param workOrderDetailDTO
     * @param user
     */
    void exportExcel2(String title, String[] headers, ServletOutputStream out,WorkOrderDetailDTO workOrderDetailDTO,UserPropertyStaffEntity user);

    /**
     * 导出excel
     * param:user
     * param:httpServletResponse
     * return
     */
    String exportExcel(UserPropertyStaffEntity user,WorkOrderDetailDTO workOrderDetailDTO,
                       HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException;

    /**
     * 检索物业报修服务问题答应列表
     * @param repairId 报修单ID
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getRepairServiceQuestionList(String repairId);

    /**
     * 获取物业报修完整数据列表
     */
    List<Map<String,Object>> getPropertyRepairCrmList(UserPropertyStaffEntity user,WorkOrderDetailDTO workOrder, WebPage webPage);

    /**
     * 导出物业报修完整数据列表Excel
     */
    void exportExcelRepairAllDate(String title, String[] headers, ServletOutputStream out,WorkOrderDetailDTO workOrderDetailDTO,UserPropertyStaffEntity user);
}