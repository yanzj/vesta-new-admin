package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.PropertyDoorDTO;
import com.maxrocky.vesta.application.DTO.PropertyDoorLogDTO;
import com.maxrocky.vesta.application.DTO.PropertyVisitorDTO;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 物业门禁管理-Service接口
 * Created by WeiYangDong on 2016/11/1.
 */
public interface PropertyDoorService {

    /**
     * 获取物业访客列表 WeiYangDong_2016-11-01
     */
    List<PropertyVisitorEntity> getPropertyVisitorList(PropertyVisitorDTO propertyVisitorDTO,WebPage webPage);

    /**
     * 导入excel  WeiYangDong_2016-11-07
     * POI:解析Excel文件中的数据并把每行数据封装成一个实体
     * @param fis 文件输入流
     */
    boolean importEmployeeByPoi(UserPropertyStaffEntity user, InputStream fis);

    /**
     * 获取物业门禁列表 WeiYangDong_2016-11-08
     */
    List<PropertyDoorEntity> getPropertyDoorList(PropertyDoorDTO propertyDoorDTO,WebPage webPage);

    /**
     * 获取用户门禁关系的用户列表 WeiYangDong_2016-11-11
     */
    List<Map<String,Object>> getPropertyUserDoorList(PropertyDoorDTO propertyDoorDTO,WebPage webPage);

    /**
     * 获取用户列表 WeiYangDong_2016-11-14
     */
    List<Map<String,Object>> getUserList(PropertyDoorDTO propertyDoorDTO,WebPage webPage);

    /**
     * 获取用户详情 WeiYangDong_2016-11-15
     */
    Map<String,Object> getUserInfo(PropertyDoorDTO propertyDoorDTO);

    /**
     * 通过门禁Id获取门禁详情 WeiYangDong_2016-11-10
     */
    PropertyDoorEntity getPropertyDoorById(String id);

    /**
     * 核对门禁设备地理位置信息 WeiYangDong_2016-11-09
     */
    int checkPosition(PropertyDoorDTO propertyDoorDTO);

    /**
     * 核对物业门禁设备信息 WeiYangDong_2016-11-10
     */
    int checkDoor(PropertyDoorDTO propertyDoorDTO);

    /**
     * 保存或更新物业门禁设备 WeiYangDong_2016-11-09
     */
    PropertyDoorEntity saveOrUpdatePropertyDoor(UserPropertyStaffEntity userPropertystaffEntity,
                                         PropertyDoorDTO propertyDoorDTO);

    /**
     * 获取用户门禁关系列表 WeiYangDong_2016-11-15
     */
    List<PropertyUserDoorMapEntity> getUserDoorMapList(PropertyDoorDTO propertyDoorDTO);

    /**
     * 分配/取消分配用户门禁权限 WeiYangDong_2016-11-11
     */
    void assignUserDoor(PropertyDoorDTO propertyDoorDTO);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Param: propertyDoorLogDTO 检索信息
    *  @Description: 获取用户门禁开门日志列表
    */
    List<PropertyDoorLogDTO> getDoorLogList(PropertyDoorLogDTO propertyDoorLogDTO, WebPage webPage);

    /**
     * 门禁批量导入模板下载 WeiYangDong_2016-12-13
     */
    String downLoadTemplate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException;

    /**
     * 批量导出门禁列表数据 WeiYangDong_2016-12-13
     */
    void exportDoorListExcel(List<PropertyDoorEntity> propertyDoorList, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException;

    /**
     * 初始化用户门禁关系数据 WeiYangDong_2016-12-16
     */
    void initializeUserDoor(PropertyDoorDTO propertyDoorDTO);

    /**
     * 初始化用户门禁关系数据 WeiYangDong_2016-12-16
     */
    void initialize(PropertyDoorDTO propertyDoorDTO);

    /* -------------------------------------------------------------------- */
    /* ----------------------门禁功能模块前端接口-Service--------------------- */
    /* -------------------------------------------------------------------- */
    /**
     * 通过用户获取门禁权限列表
     * @param userId  用户
     * @return  List<Map<String,Object>>
     */
    List<Map<String,Object>> getPropertyDoorListByUser(String userId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Param: userPropertyStaffEntity 当前用户， id 选择的doorId，macName 设备mac的名称
    *  @Description: 用户门禁开门记录
    */
    void createDoorLog(UserInfoEntity userInfoEntity, String id, String macName);

    /**
     * 开门日志列表批量导出Excel  Wyd_20170228
     * @param title
     * @param headers
     * @param out
     * @param propertyDoorLogDTO
     */
    void exportDoorLogListExcel(String title, String[] headers, ServletOutputStream out, PropertyDoorLogDTO propertyDoorLogDTO);

    /**
     * 通过楼层获取门禁日志统计信息
     */
    List<Map<String,Object>> getDoorLogStatisticsByFloor(PropertyDoorLogDTO propertyDoorLogDTO);

    /**
     * 通过时间段(小时)获取门禁日志统计信息
     */
    List<Map<String,String>> getDoorLogStatisticsByTime(PropertyDoorLogDTO propertyDoorLogDTO);

    /**
     * 通过楼栋获取门禁日志统计信息
     */
    List<Map<String,Object>> getDoorLogStatisticsByBuilding(PropertyDoorLogDTO propertyDoorLogDTO);
}
