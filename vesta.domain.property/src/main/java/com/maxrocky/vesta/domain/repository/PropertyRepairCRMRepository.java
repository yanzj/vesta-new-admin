package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by liudongxin on 2016/4/23.
 */
public interface PropertyRepairCRMRepository {
    /**
     * Describe:创建报修信息
     * CreateBy:liudongxin
     * CreateOn:2016-04-22 10:01:12
     */
    UserPropertyStaffEntity getUser(String  username);
    UserInformationEntity getUserByUserName(String  username);
    /**
     * Describe:创建报修信息
     * CreateBy:liudongxin
     * CreateOn:2016-04-22 10:01:12
     */
    void create(PropertyRepairCRMEntity propertyRepairCRMEntity);

    /**
     * CreatedBy:liudongxin
     * Describe:修改报修信息
     * ModifyBy:
     */
    void update(PropertyRepairCRMEntity propertyRepairCRMEntity);

    /**
     * Describe:根据报修id获取信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-14 09:40:37
     */
    PropertyRepairCRMEntity getById(String id);

    /**
     * Describe:获取全部信息
     * CreateBy:dl
     * CreateOn:2016-04-28 09:40:37
     */
    List<PropertyRepairCRMEntity> getPropertyReprir();

    /**
     * 根据组查询员工id
     * */
    List<Object> getStafidForzu(String id);

    /**
     * 根据组zhijian查询员工id
     * */
    List<Object> getStafidForzhijian(String id);

    /**
     * 根据房间num查询员工id
     * */
    List<Object> getStafidForNum(String id);

    /**
     * 根据员工id查询匹配设备
     * */
    List<MessageTokenEntity> getMessageToken(String id);
    /**
     * 保存消息推送
     * */
    void savemessageTarget(MessageTargetEntity messageTargetEntity);
    /**
     * 保存消息推送内容
     * */
    void saveMessageDetail(MessageDetailEntity messageDetailEntity);
    /**
     * 查询消息
     * */
    MessageTargetEntity getmessageTarge(String id);

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 获取报修单列表
     */
    public List<Object[]> getQuestionList(Map map,WebPage webPage);

    /**
     *
     *  public List<Object[]> getQuestionList(Map map,WebPage webPage);
     * */
    public List<Object[]> getQuestionListNew(Map map,WebPage webPage);
    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 获取报修单详情
     */
    public Object[] getRepairDetail(String repairId);

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 单独撇出来的，根据crm查找标准工时
     */
    public String getRepairDate(String repairDate);

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 根据报修单id获取对应报修图片
     */
    public List<Object[]> getImageList(String repairId, String state);

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 获得常用分类组列表
     */
    public List getGroupList();

    /**
     * 项目下问题清单基础数据统计
     * */
    List<Object[]> getTotalRepair(String projectCode);

    /**
     * 项目下问题清单统计楼栋所有数据
     * */
    Integer getBuildingTotal(String projectCode,String buildingCode);

    /**
     * 项目下问题清单状态数据统计
     * */
    Long getStateTotal(String projectCode,String repairState);

    /**
     * 根据项目编码和楼栋编码和状态查询
     * */
    Long getRepairsTotal(String projectCode,String buildingCode,String repairState);
}
