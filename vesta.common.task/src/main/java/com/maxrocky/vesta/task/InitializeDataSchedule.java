package com.maxrocky.vesta.task;

import com.maxrocky.vesta.application.DTO.InitializeFirstDTO;
import com.maxrocky.vesta.application.inf.BasicDataService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.BasicDataRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by ZhangBailiang on 2016/3/4.
 * 初始化数据表
 * 区域
 * 公司
 * 项目
 * 楼号
 * 业态
 * 单元
 * 房间
 */
//@Component
public class InitializeDataSchedule {

    /**
     * 基础数据 业务逻辑层接口
     */
    @Autowired
    BasicDataService basicDataService;
    /**
     * 基础数据 持久层接口
     */
    @Autowired
    BasicDataRepository basicDataRepository;


    /**
     * 初始化数据 每日零点开始
     */
    @Scheduled(cron = "0 0 2 * * ?")
    //@Scheduled(fixedDelay = 1000 * 60 * 50)
    public void initialData(){
        /**
         * 初始化 区域 公司 信息
         */
        basicDataService.InitializeAreaAndCompany();

        /**
         * 初始化基础数据 项目 楼 单元 房间
         * @return
         */
        // 获取前一天时间 作为条件查询
        String yesterDay = DateUtils.format(DateUtils.getNextDay(new Date()),DateUtils.FORMAT_SHORT);
        // 数据量较大 分页查询
        WebPage webPage = new WebPage();
        // 查询 万达推送表 view_app_houseinfo 总数据量
        WebPage webPages = basicDataService.getViewAppHouseInfoEntity(yesterDay,webPage);
        // 日志DTO
        InitializeFirstDTO initializeFirst = new InitializeFirstDTO();

        try {
            WebPage webPage2 = new WebPage();
            for(int i = 1;i<= webPages.getRecordCount()/3000+1;i++) {
                Integer project = 0;      // 项目数据量
                Integer format = 0;       // 业态数据量
                Integer building = 0;     // 楼号数据量
                Integer unit = 0;         // 单元数据量
                Integer room = 0;         // 房间数据量

                webPage2.setPageIndex(i);// 当前页数
                webPage2.setPageSize(3000);// 每次执行条数
                // 根据分页 查询 万达推送表 view_app_houseinfo
                List<ViewAppHouseInfoEntity> viewAppHouseInfo = basicDataService.getViewAppCompanyinfo(yesterDay, webPage2);
                // 用于判断是否添加日志
                if(viewAppHouseInfo.size() == 0){
                    initializeFirst.setPerform(InitializeDataEntity.perform_no);// 无相关数据
                    initializeFirst.setMatching("否"); // 是否有匹配数据
                }else {
                    initializeFirst.setPerform(InitializeDataEntity.perform_yes);// 有相关数据
                    initializeFirst.setMatching("是"); // 是否有匹配数据
                }
                // 1 代表 项目 业态 楼号 单元 房间
                initializeFirst.setState(InitializeDataEntity.project_housing_state);
                initializeFirst.setBatch(webPage2.getPageIndex()); // 批次
                initializeFirst.setAmount(webPage2.getPageSize()); // 批次量

                for (ViewAppHouseInfoEntity viewApp : viewAppHouseInfo) {
                    // 根据项目名称查询 项目信息是否存在
                    String projectId= "";
                    String formatId = "";
                    String buildingId = "";
                    String unitId="";
                    List<HouseProjectEntity> houseProject = basicDataService.houseProject(viewApp.getProjectName());

                    if (houseProject.size() == 0) {
                        // 项目信息表
                        HouseProjectEntity houseProjects = new HouseProjectEntity();
                        houseProjects.setId(Integer.toString(viewApp.getProjectId()));// 项目 主键
                        projectId = houseProjects.getId();
                        houseProjects.setPinyinCode("");//拼音简写
                        houseProjects.setCompanyId(Integer.toString(viewApp.getCompanyId()));//公司Id
                        houseProjects.setName(viewApp.getProjectName());// 项目 名称
                        houseProjects.setState("NORMAL");//状态
                        houseProjects.setViewAppProjectId(viewApp.getProjectId());//项目ID
                        houseProjects.setCreateBy("admin");//创建人
                        houseProjects.setCreateOn(new Date());
                        houseProjects.setModifyBy("admin");//修改人
                        houseProjects.setModifyOn(new Date());//修改时间
                        // 执行添加项目信息
                        boolean houseproject = basicDataService.saveHouseProject(houseProjects);
                        if (houseproject) {
                            // 记录添加项目信息
                            initializeFirst.setProject(project += 1);
                            initializeFirst.setMatching("是"); // 是否有匹配数据
                        }
                    }else {
                        projectId = houseProject.get(0).getId();
                    }

                    // 业态名称 不可以为空
                    if (null != viewApp.getFormatName()) {
                        List<HouseFormatEntity> houseFormatList = basicDataService.houseFormat(String.valueOf(viewApp.getProjectId()), viewApp.getFormatName());
                        if(houseFormatList.size() == 0){
                            // 业态信息表
                            HouseFormatEntity houseFormats = new HouseFormatEntity();
                            houseFormats.setId(IdGen.uuid());// 主键
                            formatId = houseFormats.getId();
                            houseFormats.setName(viewApp.getFormatName());// 名称
                            houseFormats.setDescription("");// 描述
                            houseFormats.setProjectId(Integer.toString(viewApp.getProjectId()));// 项目ID
                            // 执行添加业态信息
                            boolean houseFormat = basicDataService.saveHouseFormat(houseFormats);
                            if (houseFormat) {
                                // 记录添加业态信息
                                initializeFirst.setFormat(format += 1);
                                initializeFirst.setMatching("是"); // 是否有匹配数据
                            }
                        } else {
                            formatId = houseFormatList.get(0).getId();
                        }
                    }
                    // 根据楼号名称 查询楼号信息是否存在
                    List<HouseBuildingEntity> houseBuilding = basicDataService.houseBuilding(projectId,formatId,viewApp.getBlockName());
                    if (houseBuilding.size() == 0) {
                        // 楼号信息表
                        HouseBuildingEntity houseBuildings = new HouseBuildingEntity();
                        houseBuildings.setId(IdGen.uuid());//主键
                        buildingId =houseBuildings.getId();
                        houseBuildings.setName(viewApp.getBlockName());
                        houseBuildings.setProjectId(Integer.toString(viewApp.getProjectId()));//项目Id
                        List<HouseFormatEntity> houseFormatList = basicDataService.houseFormat(String.valueOf(viewApp.getProjectId()), viewApp.getFormatName());
                        houseBuildings.setFormatId(houseFormatList.get(0).getId());//业态表Id
                        houseBuildings.setStreet("");//街道
                        houseBuildings.setCreateBy("admin");//创建人
                        houseBuildings.setCreateOn(new Date());//创建时间
                        houseBuildings.setModifyBy("admin");//修改人
                        houseBuildings.setModifyOn(new Date());
                        // 执行添加楼号信息
                        boolean houseBuild = basicDataService.saveHouseBuilding(houseBuildings);
                        if (houseBuild) {
                            // 记录添加楼号信息
                            initializeFirst.setBuilding(building += 1);
                            initializeFirst.setMatching("是"); // 是否有匹配数据
                        }
                    }else {
                        buildingId = houseBuilding.get(0).getId();
                    }
                    // 根据单元名称 查询单元信息是否存在
                    List<HouseUnitEntity> houseUnit = basicDataService.houseUnit(buildingId,viewApp.getCellNo());
                    if (houseUnit.size() == 0) {
                        //单元信息表
                        HouseUnitEntity houseUnits = new HouseUnitEntity();
                        houseUnits.setId(IdGen.uuid());
                        unitId = houseUnits.getId();
                        // 根据楼号名称 查询楼号信息
                        List<HouseBuildingEntity> houseBuildings = basicDataService.houseBuilding(projectId,formatId,viewApp.getBlockName());
                        houseUnits.setName(viewApp.getCellNo());
                        houseUnits.setBuildingId(houseBuildings.get(0).getId());//楼Id
                        houseUnits.setCreateOn(new Date());//创建时间
                        houseUnits.setCreateBy("admin");//创建人
                        houseUnits.setModifyOn(new Date());//修改时间
                        houseUnits.setModifyBy("admin");//修改人
                        // 执行添加单信息
                        boolean houseUnid = basicDataService.saveHouseUnit(houseUnits);
                        if (houseUnid) {
                            // 记录添加单元信息
                            initializeFirst.setUnit(unit += 1);
                            initializeFirst.setMatching("是"); // 是否有匹配数据
                        }
                    }else {
                        unitId = houseUnit.get(0).getId();
                    }
                    if(null != viewApp.getHouseNo()){
                        // 根据房间名称 查询房间是否存在
                        List<HouseRoomEntity> houseRoom = basicDataService.houseRoom(unitId,viewApp.getHouseNo());
                        if (houseRoom.size() == 0) {
                            // 房间信息表
                            HouseRoomEntity houseRooms = new HouseRoomEntity();
                            houseRooms.setId(IdGen.uuid());//主键
                            houseRooms.setName(viewApp.getHouseNo());
                            List<HouseUnitEntity> houseUnits = basicDataService.houseUnit(buildingId,viewApp.getCellNo());
                            houseRooms.setUnitId(houseUnits.get(0).getId());// 单元ID
                            houseRooms.setCreateOn(new Date());//创建时间
                            houseRooms.setCreateBy("admin");//创建人
                            houseRooms.setModifyOn(new Date());//修改时间
                            houseRooms.setModifyBy("admin");//修改人
                            // 执行添加房间信息
                            boolean housesRoom = basicDataService.saveHouseRoom(houseRooms);
                            if (housesRoom) {
                                // 记录添加房间信息
                                initializeFirst.setRoom(room += 1);
                                initializeFirst.setMatching("是"); // 是否有匹配数据
                            }
                        }
                    }
                }
                initializeFirst.setBatchDate(new Date());// 批次时间
                initializeFirst.setType(InitializeDataEntity.type_yes);// 成功
                basicDataService.saveInitializeData(initializeFirst);
            }
        }catch (Exception e){
            initializeFirst.setBatchDate(DateUtils.getNextDay(new Date()));// 前一天时间
            initializeFirst.setType(InitializeDataEntity.type_no);// 失败
            basicDataService.saveInitializeData(initializeFirst);
            e.printStackTrace();
        }
    }
}
