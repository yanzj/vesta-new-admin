package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.CommunityAppointManageDto;
import com.maxrocky.vesta.application.admin.dto.CommunityAppointPageMapDto;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.HouseInfoEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/3/31
 * Time: 10:10
 * Describe:
 */
public interface CommunityHouseBatchService {

    //------------------内部使用接口，给生产者调用
    //根据CRM传输信息增加预约单
    public boolean saveAppointOrder(String userId, HouseInfoEntity houseInfoEntity) throws Exception;
    //修改
    public boolean updateAppointOrder(String userId, HouseInfoEntity houseInfoEntity) throws Exception;
    //删除预约单
    public ApiResult deleteAppointOrder(String id) throws Exception;

    //------------------后台使用接口
    //获取批次详情
    public CommunityAppointManageDto getBatchManage(String id) throws InvocationTargetException, IllegalAccessException;
    //添加批次管理
    public boolean saveBatchManage(CommunityAppointManageDto communityAppointManageDto, UserPropertyStaffEntity userPropertyStaffEntity) throws Exception;
    //修改交付批次
    public boolean updateBatchManage(CommunityAppointManageDto communityAppointManageDto, UserPropertyStaffEntity userPropertyStaffEntity) throws Exception;
    //删除交付批次
    public boolean deleteBatchManage(CommunityAppointManageDto communityAppointManageDto, UserPropertyStaffEntity userPropertyStaffEntity) throws Exception;
    //查询交付批次
    public List<CommunityAppointManageDto> queryAllDeliveryBatch(CommunityAppointManageDto communityAppointManageDto, WebPage webPage) throws InvocationTargetException, IllegalAccessException, Exception;
    //查询所有项目
    public Map quaryAllCommunity() throws Exception;
    //查询所有支付批次
    public Map quaryAllPayBatch() throws Exception;
    //查询所有支付状态
    public Map quaryAllPayStatus() throws Exception;
    //封装导航栏MAP
    public  CommunityAppointPageMapDto getMap() throws Exception;
    //批量设置微支付房屋批次名称
    public boolean updateBatchManageIdAndName(String batchManageName, String batchManageId, String communityId);



}
