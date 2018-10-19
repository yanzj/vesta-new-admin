package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.CommunityActivityApplyTimeRangeEntity;
import com.maxrocky.vesta.domain.model.CommunityActivityInfoScopeEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.Page;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by liuwei on 2016/1/13.
 */
public interface CommunityService {

    ApiResult GetCommuntiyActivityList(Page page, UserInfoEntity vestaToken);

    ApiResult GetHomeActivityList();

    /*获取报名信息*/
    ApiResult activityApplyList(UserInfoEntity vestaToke,String projectCode);

    void add();

    /**
     * CreatedBy:liuwei
     * Describe:活动报名(万达、金茂)
     * ModifyBy:
     * param id:活动id
     * param vestaToken:用户id
     */
    ApiResult updateApplyStatus(String activityId, UserInfoEntity vestaToken);

    ApiResult saveCommnuityInfo(CommnuitySaveReqDto commnuitySaveReqDto);

    public CommunityHomeLastestResDto getLastestCommunity(String projectId,Integer count);


    //-----后台接口-----------------------------------------------

    /**
     * 通过活动Id检索发布项目范围
     */
    List<Map<String,Object>> getProjectScopeByActivityId(String activityId);

    /**
     * 获取活动列表
     *
     * @param activityAdminDto
     * @param webPage
     * @return
     */
    public List<ActivityAdminDto> listActivity(ActivityAdminDto activityAdminDto, WebPage webPage);

    /**
     * 修改活动
     *
     * @param activityAdminDto
     * @return
     */
    public boolean updateActicity(ActivityAdminDto activityAdminDto);

    /**
     * 通过id查找活动
     *
     * @param activityId
     * @return
     */
    public ActivityAdminDto getActivityById(String activityId);

    /**
     * 删除活动-暂时将活动变为已结束
     *
     * @param activityAdminDto
     * @return
     */
    public boolean deleteActivity(ActivityAdminDto activityAdminDto);

    /**
     * 删除活动-暂时将活动变为已结束
     *
     * @param activityAdminDto
     * @return
     */
    public boolean deleteActivity(UserPropertyStaffEntity userPropertystaffEntity,ActivityAdminDto activityAdminDto);

    /**
     * 获取活动报名人列表
     *
     * @param activityApplyAdminDto
     * @param webPage
     * @return
     */
    public List<ActivityApplyAdminDto> listApplyDto(ActivityApplyAdminDto activityApplyAdminDto, WebPage webPage);

    /**
     * 获取活动报名人列表s
     *
     * @param activityApplyAdminDto
     * @param webPage
     * @return
     */
    public List<ActivityApplyAdminDto> listApplyDtos(ActivityApplyAdminDto activityApplyAdminDto, WebPage webPage);

    /**
     * 优化活动项目报名信息导出Excel
     * @param title
     * @param headers
     * @param out
     * @param activityApplyAdminDto
     */
    void activityProjectApplyExportExcel(String title, String[] headers, ServletOutputStream out, ActivityApplyAdminDto activityApplyAdminDto);

    /**
     * 获取活动项目报名列表
     *
     * @param activityApplyAdminDto
     * @param webPage
     * @return
     */
    public List<ActivityApplyAdminDto> activityProjectApply(ActivityApplyAdminDto activityApplyAdminDto, WebPage webPage);


    /**
     * 新增活动  1
     * @param activityAdminDto
     * @return
     */
    public boolean addActivity(ActivityAdminDto activityAdminDto);
    /**
     * 新增活动  1
     * @param activityAdminDto
     * @return
     */
    public boolean addActivity(UserPropertyStaffEntity userPropertystaffEntity,ActivityAdminDto activityAdminDto);

    /**
     * 修改状态
     * @param activityAdminDto
     * @return
     */
    public boolean turnActivity(ActivityAdminDto activityAdminDto);

    /**
     * 通过活动Id检索首页推荐图
     * @param activityId    活动Id
     * @return String
     */
    String getCommunityImageById(String activityId);

    /**
     * 通过活动Id检索活动范围数据
     * @param activityId 楼盘Id
     * @return List<Map<String,Object>>
     */
    Map<String,Object> queryProjectByCommunityId(String activityId);

    /**
     * 执行更新
     * @param activityAdminDto
     * @return
     */
    boolean updateEditActicity(ActivityAdminDto activityAdminDto);
    /**
     * 执行更新
     * @param activityAdminDto
     * @return
     */
    boolean updateEditActicity(UserPropertyStaffEntity userPropertystaffEntity,ActivityAdminDto activityAdminDto);


    /*********************************************金茂项目******************************************************/

    /**
     * CreatedBy:liudongxin
     * 获取活动列表
     * Describe:
     * 游客、普通用户查看所有公开的活动
     * 业主、同住人查看公开和本小区公开的活动
     * param user：用户信息
     * param page：分页
     * ModifyBy:
     */
    ApiResult getActivities(UserInfoEntity user, WebPage page,String projectCode) throws GeneralException;

    ApiResult getActivitiesWeixin(UserInfoEntity user, WebPage page,String projectCode) throws GeneralException;

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 查看我已报名的活动列表
     * ModifyBy:
     */
    ApiResult getMyActivities(UserInfoEntity user, WebPage page) throws GeneralException;

    /**
     * CreatedBy:liudongxin
     * 获取活动详情
     * Describe:
     * 通过活动id获取详情信息
     * param id:活动id
     * ModifyBy:
     */
    ApiResult getActivityInfo(UserInfoEntity user,String id) throws GeneralException;

    /**
     * 获取活动详情(游客)
     */
    ApiResult getActivityInfoByVisitor(String id) throws GeneralException;

    ApiResult delActivityNewInfo(UserInfoEntity user,String id) throws GeneralException;

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 活动报名
     * param id:活动id
     * ModifyBy:
     */
    ApiResult getApply(UserInfoEntity user, HomeShareCommunityDto obj) throws GeneralException;

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 活动取消
     * param id:活动id
     * ModifyBy:
     */
    ApiResult getCancel(UserInfoEntity user,String id) throws GeneralException;

    Map<String,Object> queryByActivityId(String activityId);
    //查询活动所属的项目
    Map queryProjectByActivityId(String activityId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取个人活动消息列表
    */
    List getActivityPersonalMessag(UserInfoEntity user, String visit);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 个人活动消息已读标记
    */
    ApiResult changeReadStatus(UserInfoEntity user, String activityId);

    /**
     * 导出excel   活动信息
     * param:user
     * param:httpServletResponse
     * return
     */
    String exportExcel(ActivityAdminDto activityAdminDto,HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException;

    /**
     * 导出excel   活动报名
     * param:user
     * param:httpServletResponse
     * return
     */
    void applyExportExcel(String title, String[] headers, ServletOutputStream out, ActivityApplyAdminDto activityApplyAdminDto) throws IOException;

    /**
     * 导出excel   活动项目报名信息
     * param:user
     * param:httpServletResponse
     * return
     */
    String activityProjectApply(ActivityApplyAdminDto activityApplyAdminDto,HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException;

    /**
     * 通过条件检索活动报名信息列表 WeiYangDong_2016-12-19
     */
    List<Map<String,Object>> getApplyList(ActivityApplyAdminDto activityApplyAdminDto,WebPage webPage);

    /**
     * 通过活动ID获取活动发布项目范围
     */
    List<CommunityActivityInfoScopeEntity> getActivityScopeList(String id);

    /**
     * 通过活动房产发布范围获取房产范围树形数据结构
     */
    String getScopeTreeByHouseScope(ActivityAdminDto activityAdminDto);

    /**
     * 保存活动发布范围
     */
    void saveHouseScope(ActivityAdminDto activityAdminDto);

    /**
     * 保存活动报名时间段配置
     */
    void saveApplyTimeRange(ActivityAdminDto activityAdminDto);

    /**
     * 获取活动报名时间段配置列表
     */
    List<CommunityActivityApplyTimeRangeEntity> getApplyTimeRangeList(ActivityAdminDto activityAdminDto);
}