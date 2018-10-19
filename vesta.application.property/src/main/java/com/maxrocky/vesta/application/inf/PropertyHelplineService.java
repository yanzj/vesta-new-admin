package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.PropertyAnnouncementPageDTO;
import com.maxrocky.vesta.application.DTO.PropertyHotLineDisplayDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.PropertyHelplineEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by liudongxin on 2016/3/30.
 * 物业热线电话方法
 */
public interface PropertyHelplineService {

    /**
     * 通过便民信息Id检索发布项目范围
     */
    List<Map<String,Object>> getProjectScopeByHelplineId(String propertyHelplineId);

    /**
     * CreateBy:liudongxin
     * Description:通过城市获取所有的小区
     * param:城市
     * 业主默认选择：北京
     * ModifyBy:
     */
    ApiResult getAllProject(String city) throws GeneralException;

    /**
     * CreatedBy:liudongxin
     * 获取当前热线服务电话
     * Describe:通过小区id获取服务内容为空的热线电话
     * param:小区id
     * ModifyBy:
     */
    ApiResult getServicePhone(String projectId,String userId) throws GeneralException;

    /**
     * CreatedBy:liudongxin
     * 获取当前热线服务电话
     * Describe:通过小区id获取服务内容不为空的热线电话
     * param:小区id
     * ModifyBy:
     */
    ApiResult getServiceInfo(String projectId,UserInfoEntity userInfo) throws GeneralException;

    /**
     * CreatedBy:liudongxin
     * Describe:获取便民信息列表
     * ModifyBy:
     * param:user
     * param:hotLineDTO
     * param:webPage
     */
    List<PropertyAnnouncementPageDTO> getHotLineList(UserPropertyStaffEntity user,PropertyAnnouncementPageDTO hotLineDTO,WebPage webPage);

    /**
     * 保存
     * param:user
     * param:hotLineDTO
     * return
     */
    String saveHotLine(UserPropertyStaffEntity user,PropertyAnnouncementPageDTO hotLineDTO);

    /**
     * 详情
     * param:id
     * return
     */
    PropertyAnnouncementPageDTO getHotLine(String id);

    /**
     * 便民信息修改
     * param:user
     * param:hotLineDTO
     * return
     */
    String updateHotLine(UserPropertyStaffEntity user,PropertyAnnouncementPageDTO hotLineDTO);

    /**
     * 便民信息删除
     * param:user
     * param:id
     * return
     */
    String deleteHotLIne(UserPropertyStaffEntity user,String id);
    /**
     * 根据通告id查询通告所有范围信息
     * @param id
     * @return
     */
    public List<Object[]> queryByHotLineId(String id);


    public Map<String, Object> queryProjectByHotLineId(String id);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 编辑展示图
    */
    public void editDisplay(PropertyHotLineDisplayDTO propertyHotLineDisplayDTO);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 排序序号最大值，+1
    */
    public Integer getMaxSortNumber(String projectId);


    /**
     * 导出excel
     * param:user
     * param:httpServletResponse
     * return
     */
    String exportExcel(HttpServletResponse httpServletResponse,PropertyAnnouncementPageDTO hotLineDTO, HttpServletRequest httpServletRequest) throws IOException;

}
