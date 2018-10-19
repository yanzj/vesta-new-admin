package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.InitializeFirstDTO;
import com.maxrocky.vesta.application.inf.BasicDataService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.BasicDataRepository;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by ZhangBailiang on 2016/3/2.
 * 基础数据 业务逻辑层实现类
 */
@Service
public class BasicDataServiceImpl implements BasicDataService {

    /**
     * 基础数据 持久层接口
     */
    @Autowired
    BasicDataRepository basicDataRepository;

    /**
     * 初始化基础数据
     * 区域
     * 公司
     * @return
     */
    @Override
    public void InitializeAreaAndCompany() {

        InitializeFirstDTO firstDTO = new InitializeFirstDTO();
        //  判断区域 公司是否添加成功
        Integer area = 0;     // 区域数据量
        Integer company = 0;  // 公司数据量
        try{
            /**
             * 万达推送数据表 view_app_companyinfo
             * 初始化 区域、公司
             */
            // 获取前一天时间 作为条件查询
            String yesterDay = DateUtils.format(DateUtils.getNextDay(new Date()),DateUtils.FORMAT_SHORT);

            // 查询 万达推送表 view_app_companyinfo
            List<ViewAppCompanyinfoEntity> viewAppCompany = basicDataRepository.ViewAppCompany(yesterDay);
            // 用于判断是否添加日志
            if(viewAppCompany.size() == 0){
                firstDTO.setPerform(InitializeDataEntity.perform_no); // 无相关数据
                firstDTO.setMatching("否"); // 是否有匹配数据

            }else {
                firstDTO.setPerform(InitializeDataEntity.perform_yes);// 有相关数据
                firstDTO.setMatching("是"); // 是否有匹配数据
            }
            // 0 代表 区域 公司
            firstDTO.setState(InitializeDataEntity.area_company_state);
            for (int i = 0; i < viewAppCompany.size(); i++) {
                firstDTO.setBatch(i+1); // 批次
                firstDTO.setAmount(viewAppCompany.size()); // 批次量
                // 查询区域信息 是否存在
                List<HouseAreaEntity> houseArea = basicDataRepository.houseArea(viewAppCompany.get(i).getAreaName());
                // 添加区域信息
                if (houseArea.size() == 0) {
                    HouseAreaEntity houseAreas = new HouseAreaEntity();
                    houseAreas.setId(IdGen.uuid());//区域ID
                    houseAreas.setName(viewAppCompany.get(i).getAreaName());// 区域名称
                    houseAreas.setDescription("INSERT"); // 区域描述
                    boolean houseAreaMsg =  basicDataRepository.saveHouseArea(houseAreas);
                    if(houseAreaMsg){
                        // 记录添加区域数量
                        firstDTO.setArea(area += 1);
                        firstDTO.setMatching("是"); // 是否有匹配数据
                    }
                }
                // 查询公司信息 是否存在
                List<HouseCompanyEntity> house = basicDataRepository.houseCompany(viewAppCompany.get(i).getCompanyname());
                if(house.size() == 0){
                    HouseCompanyEntity houseCompany = new HouseCompanyEntity();
                    houseCompany.setId(Integer.toString(viewAppCompany.get(i).getCompanyid()));//主键
                    houseCompany.setName(viewAppCompany.get(i).getCompanyname());//名称
                    houseCompany.setChildFlag("N");//是否有子集
                    houseCompany.setLevel("1");
                    houseCompany.setParentId(Integer.toString(viewAppCompany.get(i).getCompanyid()));//父公司Id
                    List<HouseAreaEntity> houseAreas = basicDataRepository.houseArea(viewAppCompany.get(i).getAreaName());
                    houseCompany.setAreaId(houseAreas.get(0).getId());//区域Id
                    houseCompany.setState("NORMAL");//状态
                    houseCompany.setCreateBy("admin");//创建人
                    houseCompany.setCreateOn(new Date());
                    houseCompany.setModifyBy("admin");//修改人
                    houseCompany.setModifyOn(new Date());//修改时间
                    boolean houseCompanyMsg = basicDataRepository.saveHouseCompany(houseCompany);
                    if(houseCompanyMsg){
                        // 记录 添加公司数量
                        firstDTO.setCompany(company += 1);
                        firstDTO.setMatching("是"); // 是否有匹配数据
                    }
                }
            }
            firstDTO.setBatchDate(new Date());// 批次时间
            firstDTO.setType(InitializeDataEntity.type_yes);// 成功
            saveInitializeData(firstDTO);
        }catch (Exception e){
            firstDTO.setBatchDate(DateUtils.getNextDay(new Date()));// 前一天时间
            firstDTO.setType(InitializeDataEntity.type_no);// 失败
            saveInitializeData(firstDTO);
            e.printStackTrace();
        }
    }

    /**
     * 返回条数
     * @param yesterDay
     * @param webPage
     * @return
     */
    @Override
    public WebPage getViewAppHouseInfoEntity(String yesterDay, WebPage webPage) {
        List<ViewAppHouseInfoEntity> viewAppHouseInfo = basicDataRepository.getViewAppCompanyinfo(yesterDay,webPage);
        WebPage webPage1 = new WebPage();
        webPage1.setPageIndex(webPage.getPageIndex());
        webPage1.setPageSize(webPage.getPageSize());
        webPage1.setRecordCount(webPage.getRecordCount());
        return webPage1;
    }

    /**
     * 查询
     * 万达推送表 view_app_houseinfo
     * 分页条件
     * @param yesterDay
     * @param webPage2
     * @return
     */
    @Override
    public List<ViewAppHouseInfoEntity> getViewAppCompanyinfo(String yesterDay, WebPage webPage2) {
        List<ViewAppHouseInfoEntity> viewAppHouseInfo = basicDataRepository.getViewAppCompanyinfo(yesterDay,webPage2);
        return viewAppHouseInfo;
    }

    /**
     * 根据项目名称查询
     * 项目信息是否存在
     * @param projectName
     * @return
     */
    @Override
    public List<HouseProjectEntity> houseProject(String projectName) {
        List<HouseProjectEntity> houseProject = basicDataRepository.houseProject(projectName);
        return houseProject;
    }

    /**
     * 添加项目信息
     * @param houseProjects
     * @return
     */
    @Override
    public boolean saveHouseProject(HouseProjectEntity houseProjects) {
        boolean houseproject = basicDataRepository.saveHouseProject(houseProjects);
        return houseproject;
    }

    /**
     * 根据项目ID 业态名称 查询信息
     * @param projectId
     * @param formatName
     * @return
     */
    @Override
    public List<HouseFormatEntity> houseFormat(String projectId, String formatName) {
        List<HouseFormatEntity> houseFormat = basicDataRepository.houseFormat(projectId, formatName);
        return houseFormat;
    }

    /**
     * 添加业态信息
     * @param houseFormats
     * @return
     */
    @Override
    public boolean saveHouseFormat(HouseFormatEntity houseFormats) {
        boolean houseFormat = basicDataRepository.saveHouseFormat(houseFormats);
        return houseFormat;
    }

    /**
     * 根据楼号名称
     * 查询楼号信息是否存在
     * @param blockName
     * @return
     */
    @Override
    public List<HouseBuildingEntity> houseBuilding(String projectId,String formatId,String blockName) {
        List<HouseBuildingEntity> houseBuilding = basicDataRepository.houseBuilding(projectId,formatId,blockName);
        return houseBuilding;
    }

    /**
     * 添加楼号信息
     * @param houseBuildings
     * @return
     */
    @Override
    public boolean saveHouseBuilding(HouseBuildingEntity houseBuildings) {
        boolean houseBuild = basicDataRepository.saveHouseBuilding(houseBuildings);
        return houseBuild;
    }

    /**
     * 根据单元名称
     * 查询单元信息是否存在
     * @param cellNo
     * @return
     */
    @Override
    public List<HouseUnitEntity> houseUnit(String buildingId,String cellNo) {
        List<HouseUnitEntity> houseUnit = basicDataRepository.houseUnit(buildingId,cellNo);
        return houseUnit;
    }

    /**
     * 添加单信息
     * @param houseUnits
     * @return
     */
    @Override
    public boolean saveHouseUnit(HouseUnitEntity houseUnits) {
        boolean houseUnid = basicDataRepository.saveHouseUnit(houseUnits);
        return houseUnid;
    }

    /**
     * 根据房间名称
     * 查询房间是否存在
     * @param houseNo
     * @return
     */
    @Override
    public List<HouseRoomEntity> houseRoom(String unitId,String houseNo) {
        List<HouseRoomEntity> houseRoom = basicDataRepository.houseRoom(unitId,houseNo);
        return houseRoom;
    }

    /**
     * 添加房间信息
     * @param houseRooms
     * @return
     */
    @Override
    public boolean saveHouseRoom(HouseRoomEntity houseRooms) {
        boolean housesRoom = basicDataRepository.saveHouseRoom(houseRooms);
        return housesRoom;
    }
    /**
     * 添加区域公司 日志
     * @param areaAndCompany
     */
    @Override
    public void saveInitializeData(InitializeFirstDTO areaAndCompany) {
        InitializeDataEntity initialize = new InitializeDataEntity();
        initialize.setDataId(IdGen.uuid()); // ID
        initialize.setBatch(areaAndCompany.getBatch());       // 批次
        initialize.setAmount(areaAndCompany.getAmount());     // 批次量
        initialize.setBatchDate(areaAndCompany.getBatchDate());// 批次时间
        initialize.setState(areaAndCompany.getState());      // 0 代表 区域 公司   1 代表 项目 业态 楼号 单元 房间
        initialize.setType(areaAndCompany.getType());        // 是否成功
        initialize.setArea(areaAndCompany.getArea());        // 区域数据量
        initialize.setCompany(areaAndCompany.getCompany());  // 公司数据量
        initialize.setPerform(areaAndCompany.getPerform());  // 是否有数据
        initialize.setMatching(areaAndCompany.getMatching());// 是否有匹配数据
        initialize.setProject(areaAndCompany.getProject());  // 项目数据量
        initialize.setFormat(areaAndCompany.getFormat());    // 业态数据量
        initialize.setBuilding(areaAndCompany.getBuilding());// 楼号数据量
        initialize.setUnit(areaAndCompany.getUnit());        // 单元数据量
        initialize.setRoom(areaAndCompany.getRoom());        // 房间数据量
        // 添加区域公司 日志
        basicDataRepository.saveInitializeData(initialize);
    }
}
