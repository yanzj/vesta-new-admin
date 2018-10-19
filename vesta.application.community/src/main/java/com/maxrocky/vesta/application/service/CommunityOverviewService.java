package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.CommunityNewsDto;
import com.maxrocky.vesta.application.admin.dto.CommunityOverviewDto;
import com.maxrocky.vesta.domain.model.CommunityDetailEntity;
import com.maxrocky.vesta.domain.model.CommunityOverviewEntity;
import com.maxrocky.vesta.domain.model.CommunityOverviewReservationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/5/09
 * Time: 10:10
 * Describe:
 */
public interface CommunityOverviewService extends BaseService<CommunityOverviewEntity>{

    /**
     * Describe:根据计划id获取信息:分页查询批次信息
     * CreateBy:yifan
     * @param communityOverviewDto
     * @param webPage
     * @return CommunityOverviewDto
     * @throws Exception
     */
    public List<CommunityOverviewDto> queryAllByPage(CommunityOverviewDto communityOverviewDto, WebPage webPage) throws Exception;

    /**
     * 带条件查询
     * @param communityOverviewDto
     * @return
     * @throws Exception
     */
    public List<CommunityOverviewDto> queryAllByParam(CommunityOverviewDto communityOverviewDto) throws Exception;

    /**
     * 更新状态
     * @param communityOverviewDto
     */
    public void saveOrUpdateStatus(CommunityOverviewDto communityOverviewDto) throws Exception;

    /**
     * 保存或者更新
     * @param communityOverviewDto
     * @throws Exception
     */
    public void saveOrUpdateOverview(CommunityOverviewDto communityOverviewDto) throws Exception;

    /**
     * 保存或者更新
     * @param communityOverviewDto
     * @throws Exception
     */
    public void saveOrUpdateOverview(UserPropertyStaffEntity userPropertystaffEntity,CommunityOverviewDto communityOverviewDto);
    /*throws Exception*/
    /**
     * 查詢金茂樓盤項目
     * @return
     */
    List<String> listProject();

    /**
     * 通过楼盘Id查询区域范围列表信息
     * @param communityId 楼盘Id
     * @return List<Map<String,Object>>
     */
    Map<String,Object> queryProjectByCommunityId(String communityId);

    /**
     * 通过项目Code集合检索城市列表信息
     * @param projectCodes  项目Code集合
     * @return Map<String,Object>
     */
    Map<String,Object> queryCityByProjectIds(String projectCodes);

    /**
     * 物理删除楼盘项目关系数据
     * @param communityId   楼盘Id
     */
    void deleteCommunityOverviewScope(String communityId);


    /**
     * 物理删除楼盘项目关系数据
     * @param communityId   楼盘Id
     */
    void deleteCommunityOverviewScope(UserPropertyStaffEntity userPropertystaffEntity,String communityId);
    /**
     * Excel导出
     * @param user
     * @param httpServletResponse
     * @param webPage
     * @return
     * @throws IOException
     */
    String exportExcel(UserPropertyStaffEntity user, HttpServletResponse httpServletResponse,CommunityOverviewDto communityOverviewDto,WebPage webPage,HttpServletRequest httpServletRequest)throws IOException;

    /**
     * 通过金茂楼盘Id检索发布项目范围
     */
    List<Map<String,Object>> getProjectScopeByCommunityOverviewId(String communityOverviewId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 修改或保存金茂楼盘去编辑信息
    */
    void saveOrUpdateDetailEdit(UserPropertyStaffEntity userPropertystaffEntity, CommunityOverviewDto communityOverviewDto);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取所有该楼盘的CommunityDetailEntity
    */
    List<CommunityDetailEntity> getAllDetails(String communityId);

    /**
     * 获取楼盘预约列表
     */
    List<CommunityOverviewReservationEntity> getOverviewReservationList(CommunityOverviewDto communityOverviewDto,WebPage webPage);
}
