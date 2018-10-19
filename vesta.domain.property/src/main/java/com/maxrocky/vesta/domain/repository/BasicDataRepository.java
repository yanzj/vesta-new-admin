package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by ZhangBailiang on 2016/3/2.
 * 基础数据 持久层接口
 */
public interface BasicDataRepository {

    /**
     * view_app_companyinfo
     * @return
     */
    List<ViewAppCompanyinfoEntity> ViewAppCompany(String yesterDay);

    /**
     * 查询 区域 信息
     * @param name
     * @return
     */
    List<HouseAreaEntity> houseArea(String  name);

    /**
     * 添加区域信息
     * @param houseAreas
     */
    boolean saveHouseArea(HouseAreaEntity houseAreas);

    /**
     * 查询 公司  信息
     * @param companyName
     * @return
     */
    List<HouseCompanyEntity> houseCompany(String  companyName);

    /**
     * 添加 公司 信息
     * @param houseCompany
     * @return
     */
    boolean saveHouseCompany(HouseCompanyEntity houseCompany);

    /**
     * 分页查询 view_app_houseinfo
     * @param webPage
     * @return
     */
    List<ViewAppHouseInfoEntity> getViewAppCompanyinfo(String yesterDay,WebPage webPage);

    /**
     * 查询 项目 信息
     * @param projectName
     * @return
     */
    List<HouseProjectEntity> houseProject(String projectName);

    /**
     * 添加 项目 信息
     * @param houseProjects
     * @return
     */
    boolean saveHouseProject(HouseProjectEntity houseProjects);

    /**
     * 查询 业态 信息
     * @param formatName
     * @return
     */
    List<HouseFormatEntity> houseFormat(String projectId, String formatName);

    /**
     * 添加 业态 信息
     * @param houseFormats
     * @return
     */
    boolean saveHouseFormat(HouseFormatEntity houseFormats);

    /**
     * 查询 楼号 信息
     * @param blockName
     * @return
     */
    List<HouseBuildingEntity> houseBuilding(String projectId,String formatId,String blockName);

    /**
     * 添加 楼号 信息
     * @param houseBuildings
     * @return
     */
    boolean saveHouseBuilding(HouseBuildingEntity houseBuildings);

    /**
     * 查询 单元 信息
     * @param cellNo
     * @return
     */
    List<HouseUnitEntity> houseUnit(String buildingId,String cellNo);

    /**
     * 添加 单元 信息
     * @param houseUnits
     * @return
     */
    boolean saveHouseUnit(HouseUnitEntity houseUnits);

    /**
     * 查询 房间 信息
     * @param houseNo
     * @return
     */
    List<HouseRoomEntity> houseRoom(String unitId,String houseNo);

    /**
     * 添加 房间 信息
     * @param houseRooms
     * @return
     */
    boolean saveHouseRoom(HouseRoomEntity houseRooms);

    /**
     * 添加 区域公司 日志
     * @param initialize
     */
    void saveInitializeData(InitializeDataEntity initialize);
}
