package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HouseInfoEntity;
import com.maxrocky.vesta.domain.model.PropertyRectifyCRMEntity;
import com.maxrocky.vesta.domain.model.PropertyRepairCRMEntity;
import com.maxrocky.vesta.domain.model.PropertyRepairEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by liudongxin on 2016/4/23.
 */
public interface PropertyRectifyCRMRepository {
    /**
     * Describe:创建整改单信息
     * CreateBy:liudongxin
     * CreateOn:2016-04-22 10:01:12
     */
    void create(PropertyRectifyCRMEntity propertyRectifyCRMEntity);

    /**
     * CreatedBy:liudongxin
     * Describe:修改整改单信息
     * ModifyBy:
     */
    void update(PropertyRectifyCRMEntity propertyRectifyCRMEntity);

    /**
     * Describe:根据整改id获取信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-14 09:40:37
     */
    PropertyRectifyCRMEntity getById(String id);

    /**
     * Describe:根据整改id获取信息
     * CreateBy:Magic
     * CreateOn:2016-01-14 09:40:37
     */
    PropertyRectifyCRMEntity getByAppId(String id);

    /**
     * Describe:获取全部信息
     * CreateBy:dl
     * CreateOn:2016-04-28 09:40:37
     */
    List<PropertyRectifyCRMEntity> getPropertyRectify();


    /**
     * 检查是否有更新
     *
     * @param userProArr
     * @param beginTime
     * @param id
     * @return
     */
    boolean haveNewData(List<String> userProArr, Date beginTime, String id);


    /**
     * 根据模块检查是否有更新
     *
     * @param listAll   拥有查询当前项目的数据lisr
     * @param list5     只查看当前项目自己创建的数据
     * @param beginTime
     * @param id
     * @param planType
     * @return
     */
    boolean haveNewDataByType(List<String> listAll,List<String> list5, String beginTime, String id, String planType,String projectNum,String userId);

    /**
     * 根据模块检查交房单是否有更新
     *
     * @param id
     * @param timeStampNew
     * @return
     */
    boolean haveNewDatajiaofangByType(String id, String timeStampNew,List<String> projectList);

    /**
     * 获取问题（整改单）列表
     *
     * @param userPro
     * @param beginTime
     * @param id
     * @return
     */
    List<Object[]> getQuestionList(List<String> userPro, Date beginTime, String id);

    /**
     * 根据问题类型获取问题（整改单）列表
     *
     * @param userPro
     * @param beginTime
     * @param id
     * @param planType
     * @return
     */
    List<Object[]> getQuestionListByType(List<String> listAll,List<String> list5, String beginTime, String id, String planType,String projectNum,String userId);

    /**
     * 根据当前部门查询待办列表中的整改单
     *
     * @param
     * @param userProject
     * @param beginTime
     * @return
     */
    List<Object[]> getOrderedList(List userProject, String beginTime, String id, String userid);

    /**
     * 根据当前部门查询待办列表中的整改单
     *
     * @param
     * @param userProject
     * @param beginTime
     * @return
     */
    String getOrderedCount(List userProject, String beginTime, String id, String userid);

    /**
     * 根据当前部门查询待办列表中的整改单
     *
     * @param
     * @param userProject
     * @param beginTime
     * @return
     */
    List<String> getOrderedCountList(List userProject, Date beginTime, String id, String userid);

    /**
     * 根据当前部门查询待办列表中的整改单
     *
     * @param
     * @param beginTime
     * @return
     */
    List<String> getOrderedCountAllList(String beginTime, String id);

    /**
     * 保存问题
     *
     * @param question
     */
    void saveQuestion(PropertyRectifyCRMEntity question);

    /**
     * 修改问题
     *
     * @param question
     */
    void updateQuestion(PropertyRectifyCRMEntity question);

    /**
     * 根据整改单号获取
     *
     * @param id
     * @return
     */
    List<Object[]> getQuestionListByRectifyId(String id);

    /**
     * 后台管理系统问题列表查询
     *
     * @param map
     * @param webPage
     * @return
     */
    List<Object[]> getQuestionList(Map map, WebPage webPage);

    /**
     * 后台管理系统批量修改责任人
     *
     * @param map
     * @param webPage
     * @return
     */
    List<Object[]> getQuestionLists(Map map, WebPage webPage);

    /**
     * 根据问题id查询详情
     *
     * @param rectifyId
     * @return
     */
    Object[] getAdminQuestionDetail(String rectifyId);

    /**
     * 根据整改单ID获取整改单详情
     *
     * @param rectifyId
     * @return
     */
    Object[] getAdminQuestionByRectifyId(String rectifyId);

    /**
     * 获取序列id
     */
    String getrectifyid();

    /**
     * 根据当前房间id查询当天
     */
    String getrectifyidNew(String num);


    /**
     * 根据当前房间id查询当天
     */
    String getRoomSequence(String num);

    /**
     * Code:D
     * Type:
     * Describe:获得内部负责人
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/8
     */
    List<Object[]> getInnerPersonList(String projectNum);

    /**
     * Code:D
     * Type:
     * Describe:获得供应商负责人
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/8
     */
    List<Object[]> getSupplierResponsibleList(String supplierId);

    /**
     * 根据房间编码查询项目编码w
     */
    String getprojectNumForRoom(String roomNum);

    /**
     * 根据计划批次查询 11 12 13
     */
    String getplanTypeForPlan(String planNum);

    /**
     * 查看批次是否更新
     *
     * @param idNew
     * @param beginDateNew
     * @param projectNum
     * @return
     */
    boolean searchToUpdateForPlan(String idNew, String beginDateNew, String projectNum, String palnNum);
    /**
     * 查看楼栋是否更新
     *
     * @param idNew
     * @param beginDateNew
     * @param projectNum
     * @return
     */
    boolean searchToUpdateForBuilding(String idNew, String beginDateNew, String projectNum);
    /**
     * 查看户型图是否更新
     *
     * @param idNew
     * @param beginDateNew
     * @param projectNum
     * @return
     */
    boolean searchToUpdateForHouseType(String idNew, String beginDateNew, String projectNum);
    /**
     * 根据项目编码检查是否有更新
     * @param id
     * @param time
     * @param projectNum
     * @return
     */
    boolean getCountRepairByProjectNum(String id,String time,String projectNum);

    /**
     * 根据房间爱你编码查询house_houseinfo数据
     *
     * */
    HouseInfoEntity getHouseInfoByRoomNum(String roomNum);

    /**
     * 后台管理系统问题导出列表查询
     *
     * @param map
     * @param webPage
     * @return
     */
    List<Object[]> getQuestionListExcel(Map map, WebPage webPage);


    /**
     *查询所有调用crm失败的数据重新推送给crm
     * @return
     */
    List<PropertyRectifyCRMEntity> getPushCrmRecfity();


    /**
     *查询所有调用crm失败的数据重新推送给crm
     * @return
     */
    List<PropertyRepairCRMEntity> getPushCrmRepair();



    /**
     * CreatedBy:magic
     * Describe:修改保修单信息
     * ModifyBy:
     */
    void updateRepairCrm(PropertyRepairCRMEntity propertyRepairCRMEntity);


    /**
     * 后台管理系统问题打印列表查询正式交房
     *
     * @param map
     * @param webPage
     * @return
     */
    List<Object[]> getPrintList(Map map, WebPage webPage);

    /**
     * 后台管理系统问题打印列表查询工地开放
     *
     * @param map
     * @param webPage
     * @return
     */
    List<Object[]> getPrintHouseOpenList(Map map, WebPage webPage);

    /**
     * 后台管理系统问题打印列表查询内部预验
     *
     * @param map
     * @param webPage
     * @return
     */
    List<Object[]> getPrintAcceptanceList(Map map, WebPage webPage);


    /**
     * 根据计划编码房间编码查询问题信息
     *
     * @return
     */
    String getCountPintRectify(String planType,String planNum,String roomNum,String successOrFailure,String caseState);



    /**
     * 根据部位Id获取部位信息
     ** @return
     */
    String getRoomLocation(String id);

    /**
     * 后台管理系统问题验房打印列表查询
     *
     * @param map
     * @param webPage
     * @return
     */
    List<Object[]> getSignaPrintList(Map map, WebPage webPage);

        /**
         * 根据整改但id获取图片信息
         * */
    List<Object []> getRectifyImageList(List<String> idList);
}
