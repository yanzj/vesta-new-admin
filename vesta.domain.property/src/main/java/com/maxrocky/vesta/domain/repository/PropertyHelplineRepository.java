package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HouseProjectEntity;
import com.maxrocky.vesta.domain.model.PropertyHelplineEntity;
import com.maxrocky.vesta.domain.model.PropertyHelplineScopeEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by liudongxin on 2016/3/30.
 * 物业热线服务电话数据操作接口类
 */
public interface PropertyHelplineRepository {

    /**
     * 通过便民信息Id检索发布范围列表
     * @param propertyHelplineId 便民信息Id
     * @return  List<PropertyHelplineScopeEntity>
     */
    List<PropertyHelplineScopeEntity> getScopeByHelpline(String propertyHelplineId);

    /**
     * CreatedBy:liudongxin
     * Description:获取所有城市
     * ModifyBy:
     */
    List<String> getAllCity();

    List<String> getAllCitys();

    /**
     * CreatedBy:liudongxin
     * param:城市
     * Description:获取所有小区
     * ModifyBy:
     */
    List<Object[]> getAllCommunityName(String city);
    /**
     * CreatedBy:liudongxin
     * param:城市
     * Description:获取所有小区
     * ModifyBy:
     */
    List<Object[]> getAllCommunityNames(String city);

    /**
     * CreatedBy:liudongxin
     * param:项目id
     * Description:通过小区id获取服务内容为空的热线电话
     * ModifyBy:
     */
    List<PropertyHelplineEntity> getServicePhone(String projectId);

    /**
     * CreatedBy:liudongxin
     * param:项目id
     * Description:通过小区id获取服务内容为空的热线电话
     * ModifyBy:
     */
    List<PropertyHelplineEntity> getServicePhoneWeiXin(String projectId);

    /**
     * CreatedBy:liudongxin
     * param:项目id
     * Description:通过小区id获取服务内容不为空的热线电话
     * ModifyBy:
     */
    List<PropertyHelplineEntity> getServiceInfo(String projectId);

    /**
     * CreatedBy:liudongxin
     * param:项目id
     * Description:通过小区id获取服务内容不为空的热线电话
     * ModifyBy:
     */
    List<PropertyHelplineEntity> getServiceInfoss(String projectId);

    /**
     * CreatedBy:liudongxin
     * param:项目id
     * Description:通过小区id获取服务内容不为空的热线电话
     * ModifyBy:
     */
    List<PropertyHelplineScopeEntity> getServiceInfos(String projectId);

    /**
     * CreatedBy:liudongxin
     * param:热线id
     * Description:通过热线id获取服务信息
     * ModifyBy:
     */
    PropertyHelplineEntity getServiceInfoById(String id);

    List<PropertyHelplineScopeEntity> getScope(String id);

    /**
     * CreatedBy:liudongxin
     * param:propertyHelpline
     * param:webPage
     * Description:获取热线服务信息
     * return
     */
    List<PropertyHelplineEntity> getHotLineList(PropertyHelplineEntity propertyHelpline,WebPage webPage,String roleScopes);

    /**
     * 保存
     * param:propertyHelpline
     * @return
     */
    boolean saveHotLine(PropertyHelplineEntity propertyHelpline);

    /**
     * 修改/删除
     * param:propertyHelpline
     * return
     */
    boolean updateHotLine(PropertyHelplineEntity propertyHelpline);
    /**
     * 修改/删除
     * param:propertyHelpline
     * return
     */
    boolean updateHotLines(PropertyHelplineScopeEntity propertyHelplineScope);



    /**
     * 根据通告id查询通告所有范围信息
     * @param id
     * @return
     */
    public  List<Object[]> queryByAnnouncementId(String id);


    public List<PropertyHelplineEntity> queryCommunityByProject(String projectCode);


    public HouseProjectEntity queryProByProjectName(String projectName);


    public List<Map<String,Object>> queryProjectByHotLineId(String id);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取所有项目
    */
    public List<HouseProjectEntity> getAllProject();

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取某城市下全部项目
    */
    public List<HouseProjectEntity> getAllProjectByCityNum(String cityNum);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 通过项目编号获取项目名称
    */
    public String getProjectNameById(String projectNum);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 通过城市id获取城市名称
    */
    public String getCityNameById(String cityId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 通过城市名称拿到城市id
    */
    public String getCityIdByName(String cityName);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取便民信息排序序号最大值
    */
    Integer getSortNumber(String projectId);
}