package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/3/31
 * Time: 10:10
 * Describe:
 */
public interface CommunityCenterService {


    //---------------------------------------前台代码-----------------------------------------------------
    /**
     * 查询所有新闻信息
     * @return
     * @throws Exception
     */
    public ApiResult getAllCommunityNews(String userId,String pinyinCode,String cityId) throws Exception;
    /**
     * 根据新闻id查询新闻详情
     * @return
     * @throws Exception
     */
    public ApiResult getNewsDetailById(String newsId,String visit, UserInfoEntity userInfo) throws Exception;
    /**
     * 查询所有楼盘信息
     * @return
     * @throws Exception
     */
    public ApiResult getAllCommunityOverview(String userId,String projectCode,String cityId) throws Exception;
    /**
     * 根据楼盘id查询楼盘详情
     * @return
     * @throws Exception
     */
    public ApiResult getCommunityDetailById(String communityId,UserInfoEntity userInfo) throws Exception;
    /**
     * 查询所有用户名下房产信息
     * @param userId
     * @return
     * @throws Exception
     */
    public ApiResult getPayDetail(String userId) throws Exception;

    /**
     * 根据交付预约详情单id，获取业主信息与房屋集中交付时间
     * @param communityHouseAppointId
     * @return
     * @throws Exception
     */
    public ApiResult getAppointDetail(String communityHouseAppointId) throws Exception;
    /**
     * 根据批次id，用户id，定位用户名下所有房产信息，展示
     * @param batchId，userId
     * @return
     * @throws Exception
     */
    public ApiResult getAppointDetail2(String batchId,String userId ) throws Exception;

    /**
     * 修改用户预约单预约时间
     * @param communityHouseAppointId
     * @return
     * @throws Exception
     */
    public ApiResult updateAppointDate(String communityHouseAppointId, String date, String amorpm) throws Exception;

    /**
     * 根据预约单号修改预约状态
     * @param communityHouseAppointId
     * @param status
     * @throws Exception
     */
    public ApiResult updateAppointStatus(String communityHouseAppointId, Integer status) throws Exception;



}
