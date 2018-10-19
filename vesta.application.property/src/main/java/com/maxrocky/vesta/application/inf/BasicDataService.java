package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.InitializeFirstDTO;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by ZhangBailiang on 2016/3/2.
 * 基础数据 业务逻辑层接口
 */
public interface BasicDataService {

    /**
     * 初始化基础数据
     * 区域
     * 公司
     * @return
     */
    void InitializeAreaAndCompany();

    /**
     * 添加 日志
     * @param areaAndCompany
     */
    void saveInitializeData(InitializeFirstDTO areaAndCompany);

    /**
     * 查询
     * 万达推送表 view_app_houseinfo
     * 总数据量
     * @param yesterDay
     * @param webPage
     * @return
     */
    WebPage getViewAppHouseInfoEntity(String yesterDay,WebPage webPage);

    /**
     * 查询
     * 万达推送表 view_app_houseinfo
     * 分页条件
     * @param yesterDay
     * @param webPage2
     * @return
     */
    List<ViewAppHouseInfoEntity> getViewAppCompanyinfo(String yesterDay,WebPage webPage2);

    /**
     * 根据项目名称查询
     * 项目信息是否存在
     * @param projectName
     * @return
     */
    List<HouseProjectEntity> houseProject(String projectName);

    /**
     * 添加项目信息
     * @param houseProjects
     * @return
     */
    boolean saveHouseProject(HouseProjectEntity houseProjects);

    /**
     * 根据项目ID 业态名称 查询信息
     * @param projectId
     * @param formatName
     * @return
     */
    List<HouseFormatEntity> houseFormat(String projectId, String formatName);

    /**
     * 添加业态信息
     * @param houseFormats
     * @return
     */
    boolean saveHouseFormat(HouseFormatEntity houseFormats);

    /**
     * 根据楼号名称
     * 查询楼号信息是否存在
     * @param blockName
     * @return
     */
    List<HouseBuildingEntity> houseBuilding(String projectId,String formatId, String blockName);

    /**
     * 添加楼号信息
     * @param houseBuildings
     * @return
     */
    boolean saveHouseBuilding(HouseBuildingEntity houseBuildings);

    /**
     * 根据单元名称
     * 查询单元信息是否存在
     * @param cellNo
     * @return
     */
    List<HouseUnitEntity> houseUnit(String buildingId,String cellNo);

    /**
     * 添加单信息
     * @param houseUnits
     * @return
     */
    boolean saveHouseUnit(HouseUnitEntity houseUnits);

    /**
     * 根据房间名称
     * 查询房间是否存在
     * @param houseNo
     * @return
     */
    List<HouseRoomEntity> houseRoom(String unitId,String houseNo);

    /**
     * 添加房间信息
     * @param houseRooms
     * @return
     */
    boolean saveHouseRoom(HouseRoomEntity houseRooms);


}
