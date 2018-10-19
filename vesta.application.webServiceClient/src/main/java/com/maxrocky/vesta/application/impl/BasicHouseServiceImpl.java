package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.house.*;
import com.maxrocky.vesta.application.house.model.*;
import com.maxrocky.vesta.application.inf.BasicHouseService;
import com.maxrocky.vesta.application.inf.HouseHouseProjectAllService;
import com.maxrocky.vesta.application.inf.IBasicService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by langmafeng on 2016/4/18.
 * 接收金茂项目CRM传递房屋基础数据
 */
@Service
public class BasicHouseServiceImpl implements BasicHouseService {
    @Autowired
    private HouseHouseProjectAllService HouseHouseProject;
    @Autowired
    private HouseInfoRepository houseInfoRepository;
    @Autowired
    private InterfaceLogRepository interfaceLogRepository;
    @Autowired
    private HouseRoomRepository houseRoomRepository;
    @Autowired
    private HouseBuildingRepository houseBuildingRepository;
    @Autowired
    private HouseProjectRepository houseProjectRepository;
    @Autowired
    private HouseFloorRepository houseFloorRepository;
    @Autowired
    private HouseCityRepository houseCityRepository;
    @Autowired
    private HouseUnitRepository houseUnitRepository;
    @Autowired
    private HouseAreaRepository houseAreaRepository;
    @Autowired
    private TemporaryTableUpdateRepository temporaryTableUpdateRepository;

    /**
     * 通过项目编码、最后修改时间查询信息
     * param:projectCodeList
     * param:lastModifyTime
     */
    @Override
    public String queryBasicInfo(String[] projectCodeList, Calendar lastModifyTime) {
        String result = "";
        try {
            HousePropertyRequest request = new HousePropertyRequest();
            request.setProjectCodeList(projectCodeList);
            //request.setProjectCodeList(null);
            request.setLastModifyTime(lastModifyTime);
            BasicServiceLocator basicService = new BasicServiceLocator();
            IBasicService iBasicService = basicService.getBasicHttpBinding_IBasicService();
            HousePropertyResponse response = iBasicService.housePropertyQuery(request);
            HousePropertyResponseBody responseBody = response.getBody();

            if (responseBody != null) {
                //1. 更新城市数据
                City[] cityList = responseBody.getCityList();
                if (cityList.length > 0) {
                    for (int i = 0; i < cityList.length; i++) {
                        City city = cityList[i];
                        HouseCityEntity cityInfo = houseCityRepository.getInfoByCityId(city.getCityId());
                        //有数据则更新,无数据则创建 存入分表中
                        if (cityInfo != null) {
                            String up="0";
                            if(!StringUtil.isEmpty(city.getName())){
                                if(!StringUtil.isEmpty(cityInfo.getCityName())){
                                    if(!cityInfo.getCityName().equals(city.getName())){
                                        cityInfo.setCityName(city.getName());
                                        up="1";
                                    }
                                }else{
                                    cityInfo.setCityName(city.getName());
                                    up="1";
                                }
                            }
                            if (!StringUtil.isEmpty(city.getCityCode())) {
                                if(!StringUtil.isEmpty(cityInfo.getCityCode())){
                                    if(!cityInfo.getCityCode().equals(city.getCityCode())){
                                        cityInfo.setCityCode(city.getCityCode());
                                        up="1";
                                    }
                                }else{
                                    cityInfo.setCityCode(city.getCityCode());
                                    up="1";
                                }
                            }
                            if (city.getModifiedOn() != null) {
                                cityInfo.setCreateOn(city.getModifiedOn().getTime());
                            }
                            cityInfo.setModifyOn(new Date());
                            if("1".equals(up)){
                                houseCityRepository.updateBuildingInfo(cityInfo);
                            }
                        } else {
                            HouseCityEntity houseCityEntity = new HouseCityEntity();
                            houseCityEntity.setCityName(city.getName());
                            houseCityEntity.setCityCode(city.getCityCode());
                            houseCityEntity.setId(city.getCityId());
                            if (city.getModifiedOn() != null) {
                                houseCityEntity.setCreateOn(city.getModifiedOn().getTime());
                            }
                            houseCityEntity.setModifyOn(new Date());
                            houseCityRepository.create(houseCityEntity);
                        }

                        //更新 楼栋层级表  城市一级别 0
                        BuildingMappingTimeEntity cityEntity = temporaryTableUpdateRepository.queryBuild(city.getCityId());
                        if (cityEntity != null) {
                            String up="0";
                            if (!StringUtil.isEmpty(city.getCityCode())) {
                                if(!city.getCityCode().equals(cityEntity.getCurrentNum())){
                                    cityEntity.setCurrentNum(city.getCityCode());
                                    up="1";
                                }
                            }
                            if(!StringUtil.isEmpty(city.getName())){
                                if(!city.getName().equals(cityEntity.getName())){
                                    cityEntity.setParentId(city.getName());
                                    up="1";
                                }
                            }
                            cityEntity.setStart("0");
                            cityEntity.setGraded("0");
                            cityEntity.setTimeStamp(new Date());
                            if("1".equals(up)){
                                temporaryTableUpdateRepository.updateBuild(cityEntity);
                            }
                        } else {
                            BuildingMappingTimeEntity cityEntityTime = new BuildingMappingTimeEntity();
                            cityEntityTime.setCurrentId(city.getCityId());
                            cityEntityTime.setCurrentNum(city.getCityCode());
                            cityEntityTime.setName(city.getName());
                            cityEntityTime.setStart("0");
                            cityEntityTime.setGraded("0");//0级别  城市
                            cityEntityTime.setTimeStamp(new Date());
                            temporaryTableUpdateRepository.createBuild(cityEntityTime);
                        }
                    }
                }


                //2. 更新项目级别数据
                Project[] projectList = responseBody.getProjectList();
                if (projectList.length > 0) {
                    for (int i = 0; i < projectList.length; i++) {
                        Project project = projectList[i];
                        HouseProjectEntity projectInfo = houseProjectRepository.getInfoByProjectId(project.getProjectId());
                        //序列表有不做修改  没有新增
                        if (!StringUtil.isEmpty(project.getProjectCode())) {
                            String[] ss = project.getProjectCode().split("-");
                            String repair = ss[ss.length - 1] + "-B";
                            String getrepair = houseProjectRepository.getsequence(repair);
                            if ("".equals(getrepair)) {
                                houseProjectRepository.saverepair(repair);
                            }
                            String rectify = ss[ss.length - 1] + "-Z";
                            String getrectify = houseProjectRepository.getsequence(rectify);
                            if ("".equals(getrectify)) {
                                houseProjectRepository.saverepair(rectify);
                            }
                            //打印的
                            String printJF = ss[ss.length - 1] + "-JF";
                            String getprintJF = houseProjectRepository.getprintsequence(printJF);
                            if ("".equals(getprintJF)) {
                                houseProjectRepository.saveprint(printJF);
                            }
                            String printNY = ss[ss.length - 1] + "-NY";
                            String getprintNY = houseProjectRepository.getprintsequence(printNY);
                            if ("".equals(getprintNY)) {
                                houseProjectRepository.saveprint(printNY);
                            }
                            String printGK = ss[ss.length - 1] + "-GK";
                            String getprintGK = houseProjectRepository.getprintsequence(printGK);
                            if ("".equals(getprintGK)) {
                                houseProjectRepository.saveprint(printGK);
                            }
                            String complain=  ss[ss.length - 1] + "-T";
                            String getComplain=houseProjectRepository.getprintsequence(complain);
                            if ("".equals(getComplain)) {
                                houseProjectRepository.saveprint(complain);
                            }
                        }
                        //有数据则更新没有则创建 存入分表中
                        if (projectInfo != null) {
                            String up="0";
                            //所属项目公司
                            if (!StringUtil.isEmpty(project.getBusinessUnitName())) {
                                if(!StringUtil.isEmpty(projectInfo.getBusinessUnitName())){
                                    if(!projectInfo.getBusinessUnitName().equals(project.getBusinessUnitName())){
                                        projectInfo.setBusinessUnitName(project.getBusinessUnitName());
                                        up="1";
                                    }
                                }else{
                                    projectInfo.setBusinessUnitName(project.getBusinessUnitName());
                                    up="1";
                                }
                            }
                            //所属项目公司id
                            if (!StringUtil.isEmpty(project.getBusinessUnitid())) {
                                if(!StringUtil.isEmpty(projectInfo.getBusinessUnitId())){
                                    if(!projectInfo.getBusinessUnitId().equals(project.getBusinessUnitid())){
                                        projectInfo.setBusinessUnitId(project.getBusinessUnitid());
                                        up="1";
                                    }
                                }else{
                                    projectInfo.setBusinessUnitId(project.getBusinessUnitid());
                                    up="1";
                                }
                            }
                            //竣工日期
                            if (project.getEndDate() != null) {
                                projectInfo.setCompleteOn(project.getEndDate().getTime());
                            }
                            projectInfo.setModifyOn(new Date());
                            //所属经营单位
                            if (!StringUtil.isEmpty(project.getOwningBusinessUnit())) {
                                if(!StringUtil.isEmpty(projectInfo.getOwningBusinessUnit())){
                                    if(!projectInfo.getOwningBusinessUnit().equals(project.getOwningBusinessUnit())){
                                        projectInfo.setOwningBusinessUnit(project.getOwningBusinessUnit());
                                        up="1";
                                    }
                                }else{
                                    projectInfo.setOwningBusinessUnit(project.getOwningBusinessUnit());
                                    up="1";
                                }
                            }
                            //经营单位id
                            if (!StringUtil.isEmpty(project.getOwningBusinessUnitid())) {
                                if(!StringUtil.isEmpty(projectInfo.getOwningBusinessUnitId())){
                                    if(!projectInfo.getOwningBusinessUnitId().equals(project.getOwningBusinessUnitid())){
                                        projectInfo.setOwningBusinessUnitId(project.getOwningBusinessUnitid());
                                        up="1";
                                    }
                                }else{
                                    projectInfo.setOwningBusinessUnitId(project.getOwningBusinessUnitid());
                                    up="1";
                                }
                            }
                            //关联城市id
                            if (!StringUtil.isEmpty(project.getCityId())) {
                                if(!StringUtil.isEmpty(projectInfo.getCityId())){
                                    if(!projectInfo.getCityId().equals(project.getCityId())){
                                        projectInfo.setCityId(project.getCityId());
                                        up="1";
                                    }
                                }else{
                                    projectInfo.setCityId(project.getCityId());
                                    up="1";
                                }
                            }
                            //项目编码
                            if (!StringUtil.isEmpty(project.getProjectCode())) {
                                if(!StringUtil.isEmpty(projectInfo.getPinyinCode())){
                                    if(!projectInfo.getPinyinCode().equals(project.getProjectCode())){
                                        projectInfo.setPinyinCode(project.getProjectCode());
                                        up="1";
                                    }
                                }else{
                                    projectInfo.setPinyinCode(project.getProjectCode());
                                    up="1";
                                }
                            }
                            //项目名称
                            if (!StringUtil.isEmpty(project.getProjectName())) {
                                if(!StringUtil.isEmpty(projectInfo.getName())){
                                    if(!projectInfo.getName().equals(project.getProjectName())){
                                        projectInfo.setName(project.getProjectName());
                                        projectInfo.setOriginName(project.getProjectName());
                                        up="1";
                                    }
                                }else{
                                    projectInfo.setName(project.getProjectName());
                                    projectInfo.setOriginName(project.getProjectName());
                                    up="1";
                                }
                            }
                            //分期
                            if (!StringUtil.isEmpty(project.getProjectStage())) {
                                if(!StringUtil.isEmpty(projectInfo.getInstalment())){
                                    if(!projectInfo.getInstalment().equals(project.getProjectStage())){
                                        projectInfo.setInstalment(project.getProjectStage());
                                        up="1";
                                    }
                                }else{
                                    projectInfo.setInstalment(project.getProjectStage());
                                    up="1";
                                }
                            }
                            //关联城市编码
                            if (!StringUtil.isEmpty(project.getCityCode())) {
                                if(!StringUtil.isEmpty(projectInfo.getCityNum())){
                                    if(!projectInfo.getCityNum().equals(project.getCityCode())){
                                        projectInfo.setCityNum(project.getCityCode());
                                        up="1";
                                    }
                                }else{
                                    projectInfo.setCityNum(project.getCityCode());
                                    up="1";
                                }
                            }
                            if (project.getModifiedOn() != null) {
                                projectInfo.setCreateOn(project.getModifiedOn().getTime());
                            }
                            if("1".equals(up)){
                                houseProjectRepository.updateProjectInfo(projectInfo);
                            }
                        } else {
                            HouseProjectEntity houseProjectEntity = new HouseProjectEntity();
                            //开工日期
                            if (project.getBeginDate() != null) {
                                houseProjectEntity.setCreateOn(project.getBeginDate().getTime());
                            } else {
                                houseProjectEntity.setCreateOn(new Date());
                            }
                            //所属项目公司
                            if(!StringUtil.isEmpty(project.getBusinessUnitName())){
                                houseProjectEntity.setBusinessUnitName(project.getBusinessUnitName());
                            }
                            //所属项目公司ID
                            if(!StringUtil.isEmpty(project.getBusinessUnitid())){
                                houseProjectEntity.setBusinessUnitId(project.getBusinessUnitid());
                            }
                            //竣工日期
                            if (project.getEndDate() != null) {
                                houseProjectEntity.setCompleteOn(project.getEndDate().getTime());
                            }
                            if (project.getModifiedOn() != null) {
                                houseProjectEntity.setCreateOn(project.getModifiedOn().getTime());
                            }
                            //经营单位id
                            if(!StringUtil.isEmpty(project.getOwningBusinessUnitid())){
                                houseProjectEntity.setOwningBusinessUnitId(project.getOwningBusinessUnitid());
                            }
                            //所属经营单位
                            if(!StringUtil.isEmpty(project.getOwningBusinessUnit())){
                                houseProjectEntity.setOwningBusinessUnit(project.getOwningBusinessUnit());
                            }
                            houseProjectEntity.setModifyOn(new Date());
                            //关联城市id
                            if(!StringUtil.isEmpty(project.getCityId())){
                                houseProjectEntity.setCityId(project.getCityId());
                            }
                            //关联城市编码
                            if(!StringUtil.isEmpty(project.getCityCode())){
                                houseProjectEntity.setCityNum(project.getCityCode());
                            }
                            //项目编码
                            if(!StringUtil.isEmpty(project.getProjectCode())){
                                houseProjectEntity.setPinyinCode(project.getProjectCode());
                            }
                            //主键id
                            if(!StringUtil.isEmpty(project.getProjectId())){
                                houseProjectEntity.setId(project.getProjectId());
                            }
                            //项目名称
                            if(!StringUtil.isEmpty(project.getProjectName())){
                                houseProjectEntity.setName(project.getProjectName());
                            }
                            //分期
                            if(!StringUtil.isEmpty(project.getProjectStage())){
                                houseProjectEntity.setInstalment(project.getProjectStage());
                            }
                            houseProjectEntity.setState("NORMAL");
                            houseProjectRepository.create(houseProjectEntity);

                        }
                        //更新房屋信息临时表  项目更新
                        BuildingMappingTimeEntity buildEntity = temporaryTableUpdateRepository.queryBuild(project.getProjectId());
                        if (buildEntity != null) {
                            String up="0";
                            if (!StringUtil.isEmpty(project.getProjectCode())) {
                                if(!project.getProjectCode().equals(buildEntity.getCurrentNum())){
                                    buildEntity.setCurrentNum(project.getProjectCode());
                                    up="1";
                                }
                            }
                            if (!StringUtil.isEmpty(project.getProjectName())) {
                                if(!project.getProjectName().equals(buildEntity.getName())){
                                    buildEntity.setName(project.getProjectName());
                                    up="1";
                                }
                            }
                            if(!StringUtil.isEmpty(project.getCityId())){
                                if(!project.getCityId().equals(buildEntity.getParentId())){
                                    buildEntity.setParentId(project.getCityId());
                                    up="1";
                                }
                            }
                            if(!StringUtil.isEmpty(project.getCityCode())){
                                if(!project.getCityCode().equals(buildEntity.getParentNum())){
                                    buildEntity.setParentNum(project.getCityCode());
                                    up="1";
                                }
                            }
                            buildEntity.setStart("0");
                            buildEntity.setGraded("1");
                            buildEntity.setTimeStamp(new Date());
                            if("1".equals(up)){
                                temporaryTableUpdateRepository.updateBuild(buildEntity);
                            }
                        } else {
                            BuildingMappingTimeEntity buildInfo = new BuildingMappingTimeEntity();
                            buildInfo.setCurrentId(project.getProjectId());
                            buildInfo.setCurrentNum(project.getProjectCode());
                            buildInfo.setParentId(project.getCityId());
                            buildInfo.setParentNum(project.getCityCode());
                            buildInfo.setName(project.getProjectName());
                            buildInfo.setStart("0");
                            buildInfo.setGraded("1");//项目级别
                            buildInfo.setTimeStamp(new Date());
                            temporaryTableUpdateRepository.createBuild(buildInfo);
                        }
                    }
                }
                //当未选择项目时候 不更新项目级别以下数据
                if(projectCodeList!=null && projectCodeList.length>0){
                    //更新地块数据
                    Block[] blockList = responseBody.getBlockList();
                    if (blockList.length > 0) {
                        for (int i = 0; i < blockList.length; i++) {
                            Block block = blockList[i];
                            HouseAreaEntity areaInfo = houseAreaRepository.getInfoByBlockId(block.getBlockId());
                            //有数据则更新没有数据则创建
                            if (areaInfo != null) {
                                String up="0";
                                //地块编码
                                if (!StringUtil.isEmpty(block.getBlockCode())) {
                                    if(!StringUtil.isEmpty(areaInfo.getBlockCode())){
                                        if(!areaInfo.getBlockCode().equals(block.getBlockCode())){
                                            areaInfo.setBlockCode(block.getBlockCode());
                                            up="1";
                                        }
                                    }else{
                                        areaInfo.setBlockCode(block.getBlockCode());
                                        up="1";
                                    }
                                }
                                //地块名称
                                if (!StringUtil.isEmpty(block.getName())) {
                                    if(!StringUtil.isEmpty(areaInfo.getName())){
                                        if(!areaInfo.getName().equals(block.getName())){
                                            areaInfo.setName(block.getName());
                                            up="1";
                                        }
                                    }else{
                                        areaInfo.setName(block.getName());
                                        up="1";
                                    }
                                }
                                //地块号
                                if(!StringUtil.isEmpty(block.getBlockNum())){
                                    if(!StringUtil.isEmpty(areaInfo.getBlockNum())){
                                        if(!areaInfo.getBlockNum().equals(block.getBlockNum())){
                                            areaInfo.setBlockNum(block.getBlockNum());
                                            up="1";
                                        }
                                    }else{
                                        areaInfo.setBlockNum(block.getBlockNum());
                                        up="1";
                                    }
                                }
                                //房产类型
                                if(block.getPropertyType()!=null){
                                    if(!StringUtil.isEmpty(block.getPropertyType().getValue())){
                                        if(!StringUtil.isEmpty(areaInfo.getPropertyType())){
                                            if(!areaInfo.getPropertyType().equals(block.getPropertyType().getValue())){
                                                areaInfo.setPropertyType(block.getPropertyType().getValue());
                                                up="1";
                                            }
                                        }else{
                                            areaInfo.setPropertyType(block.getPropertyType().getValue());
                                            up="1";
                                        }
                                    }
                                }
                                //关联项目id
                                if (!StringUtil.isEmpty(block.getProjectId())) {
                                    if(!StringUtil.isEmpty(areaInfo.getProjectId())){
                                        if(!areaInfo.getProjectId().equals(block.getProjectId())){
                                            areaInfo.setProjectId(block.getProjectId());
                                            up="1";
                                        }
                                    }else{
                                        areaInfo.setProjectId(block.getProjectId());
                                        up="1";
                                    }
                                }
                                //关联项目编码
                                if (!StringUtil.isEmpty(block.getProjectCode())) {
                                    if(!StringUtil.isEmpty(areaInfo.getProjectCode())){
                                        if(!areaInfo.getProjectCode().equals(block.getProjectCode())){
                                            areaInfo.setProjectCode(block.getProjectCode());
                                            up="1";
                                        }
                                    }else{
                                        areaInfo.setProjectCode(block.getProjectCode());
                                        up="1";
                                    }
                                }
                                if (block.getModifiedOn() != null) {
                                    areaInfo.setCreateOn(block.getModifiedOn().getTime());
                                }
                                //状态 0:有效 1:失效
                                if(!StringUtil.isEmpty(block.getStateCode()+"")){
                                    if(!StringUtil.isEmpty(areaInfo.getStateCode())){
                                        if(!areaInfo.getStateCode().equals(block.getStateCode()+"")){
                                            areaInfo.setStateCode(block.getStateCode()+"");
                                            up="1";
                                        }
                                    }else{
                                        areaInfo.setStateCode(block.getStateCode()+"");
                                        up="1";
                                    }
                                }
                                areaInfo.setModifyOn(new Date());
                                if("1".equals(up)){
                                    houseAreaRepository.updateHouseAreaInfo(areaInfo);
                                }
                            } else {
                                HouseAreaEntity houseAreaEntity = new HouseAreaEntity();
                                houseAreaEntity.setId(block.getBlockId());
                                if(!StringUtil.isEmpty(block.getBlockCode())){
                                    houseAreaEntity.setBlockCode(block.getBlockCode());
                                }
                                if(!StringUtil.isEmpty(block.getName())){
                                    houseAreaEntity.setName(block.getName());
                                }
                                if(!StringUtil.isEmpty(block.getBlockNum())){
                                    houseAreaEntity.setBlockNum(block.getBlockNum());
                                }
                                if(block.getPropertyType()!=null){
                                    if(!StringUtil.isEmpty(block.getPropertyType().getValue())){
                                        houseAreaEntity.setPropertyType(block.getPropertyType().getValue());
                                    }
                                }
                                if(!StringUtil.isEmpty(block.getProjectId())){
                                    houseAreaEntity.setProjectId(block.getProjectId());
                                }
                                if(!StringUtil.isEmpty(block.getProjectCode())){
                                    houseAreaEntity.setProjectCode(block.getProjectCode());
                                }
                                if(!StringUtil.isEmpty(block.getStateCode()+"")){
                                    houseAreaEntity.setStateCode(block.getStateCode()+"");//0:有效 1:失效
                                }else{
                                    houseAreaEntity.setStateCode("0");//0:有效 1:失效
                                }
                                houseAreaEntity.setModifyOn(new Date());
                                if (block.getModifiedOn() != null) {
                                    houseAreaEntity.setCreateOn(block.getModifiedOn().getTime());
                                }
                                houseAreaRepository.create(houseAreaEntity);
                            }
                            //更新房屋信息临时表 地块 级别1.5
                            BuildingMappingTimeEntity areaEntity = temporaryTableUpdateRepository.queryBuild(block.getBlockId());
                            if (areaEntity != null) {
                                String up="0";
                                //当前级别编码
                                if (!StringUtil.isEmpty(block.getBlockCode())) {
                                    if(!block.getBlockCode().equals(areaEntity.getCurrentNum())){
                                        areaEntity.setCurrentNum(block.getBlockCode());
                                        up="1";
                                    }
                                }
                                //名称
                                if(!StringUtil.isEmpty(block.getName())){
                                    if(!block.getName().equals(areaEntity.getName())){
                                        areaEntity.setName(block.getName());
                                        up="1";
                                    }
                                }
                                //关联的上一级id 项目id
                                if (!StringUtil.isEmpty(block.getProjectId())) {
                                    if(!block.getProjectId().equals(areaEntity.getParentId())){
                                        areaEntity.setParentId(block.getProjectId());
                                        up="1";
                                    }
                                }
                                //关联的上一级编码 项目编码
                                if (!StringUtil.isEmpty(block.getProjectCode())) {
                                    if(!block.getProjectCode().equals(areaEntity.getParentNum())){
                                        areaEntity.setParentNum(block.getProjectCode());
                                        up="1";
                                    }
                                }
                                //状态0:有效 1:失效
                                if(!StringUtil.isEmpty(block.getStateCode()+"")){
                                    if(!StringUtil.isEmpty(areaEntity.getStart())){
                                        if(!areaEntity.getStart().equals(block.getStateCode()+"")){
                                            areaEntity.setStart(block.getStateCode()+"");
                                            up="1";
                                        }
                                    }else{
                                        areaEntity.setStart(block.getStateCode()+"");
                                        up="1";
                                    }
                                }
                                //类型
                                if(block.getPropertyType()!=null){
                                    if(!StringUtil.isEmpty(block.getPropertyType().getValue())){
                                        if(!StringUtil.isEmpty(areaEntity.getPropertyType())){
                                            if(!areaEntity.getPropertyType().equals(block.getPropertyType().getValue())){
                                                areaEntity.setPropertyType(block.getPropertyType().getValue());
                                                up="1";
                                            }
                                        }else{
                                            areaEntity.setPropertyType(block.getPropertyType().getValue());
                                            up="1";
                                        }
                                    }
                                }
                                areaEntity.setGraded("1.5");
                                areaEntity.setTimeStamp(new Date());
                                if("1".equals(up)){
                                    temporaryTableUpdateRepository.updateBuild(areaEntity);
                                }
                            } else {
                                BuildingMappingTimeEntity areaEntityInfo = new BuildingMappingTimeEntity();
                                areaEntityInfo.setCurrentId(block.getBlockId());
                                areaEntityInfo.setCurrentNum(block.getBlockCode());
                                areaEntityInfo.setName(block.getName());
                                areaEntityInfo.setParentId(block.getProjectId());
                                areaEntityInfo.setParentNum(block.getProjectCode());
                                if(block.getPropertyType()!=null){
                                    if(!StringUtil.isEmpty(block.getPropertyType().getValue())){
                                        areaEntityInfo.setPropertyType(block.getPropertyType().getValue());
                                    }
                                }
                                if(block.getPropertyType()!=null){
                                    if(!StringUtil.isEmpty(block.getPropertyType().getValue())){
                                        areaEntityInfo.setPropertyType(block.getPropertyType().getValue());
                                    }
                                }
                                if(!StringUtil.isEmpty(block.getStateCode()+"")){
                                    areaEntityInfo.setStart(block.getStateCode()+"");//0:有效 1:失效
                                }else{
                                    areaEntityInfo.setStart("0");//0:有效 1:失效
                                }
                                areaEntityInfo.setGraded("1.5");
                                areaEntityInfo.setTimeStamp(new Date());
                                temporaryTableUpdateRepository.createBuild(areaEntityInfo);
                            }
                        }
                    }

                    //更新楼栋信息
                    Building[] buildingList = responseBody.getBuildingList();
                    if (buildingList.length > 0) {
                        for (int i = 0; i < buildingList.length; i++) {
                            Building building = buildingList[i];
                            HouseBuildingEntity buildingInfo = houseBuildingRepository.getInfoByBuildingId(building.getHousingResourcesId());
                            //有数据则更新没有数据则创建 插入表中
                            if (buildingInfo != null) {
                                String up="0";
                                //预售编码
                                if (!StringUtil.isEmpty(building.getBlockBuildingCode())) {
                                    if(!StringUtil.isEmpty(buildingInfo.getBuildingNum())){
                                        if(!buildingInfo.getBuildingNum().equals(building.getBlockBuildingCode())){
                                            buildingInfo.setBuildingNum(building.getBlockBuildingCode());
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setBuildingNum(building.getBlockBuildingCode());
                                        up="1";
                                    }

                                }
                                //备案编码
                                if (!StringUtil.isEmpty(building.getRecordBuildingCode())) {
                                    if(!StringUtil.isEmpty(buildingInfo.getRecordBuildingCode())){
                                        if(!buildingInfo.getRecordBuildingCode().equals(building.getRecordBuildingCode())){
                                            buildingInfo.setRecordBuildingCode(building.getRecordBuildingCode());
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setRecordBuildingCode(building.getRecordBuildingCode());
                                        up="1";
                                    }
                                }
                                //房产类型
                                if(building.getPropertyType()!=null){
                                    if (!StringUtil.isEmpty(building.getPropertyType().getValue())) {
                                        if(!StringUtil.isEmpty(buildingInfo.getPropertyType())){
                                            if(!buildingInfo.getPropertyType().equals(building.getPropertyType().getValue())){
                                                buildingInfo.setPropertyType(building.getPropertyType().getValue());
                                                up="1";
                                            }
                                        }else{
                                            buildingInfo.setPropertyType(building.getPropertyType().getValue());
                                            up="1";
                                        }
                                    }
                                }
                                //预售楼栋名称
                                if (!StringUtil.isEmpty(building.getSaleBuildingName())) {
                                    if(!StringUtil.isEmpty(buildingInfo.getSaleBuildingName())){
                                        if(!buildingInfo.getSaleBuildingName().equals(building.getSaleBuildingName())){
                                            buildingInfo.setSaleBuildingName(building.getSaleBuildingName());
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setSaleBuildingName(building.getSaleBuildingName());
                                        up="1";
                                    }

                                }
                                // 备案楼栋名称
                                if (!StringUtil.isEmpty(building.getRecordBuildingName())) {
                                    if(!StringUtil.isEmpty(buildingInfo.getRecordBuildingName())){
                                        if(!buildingInfo.getRecordBuildingName().equals(building.getRecordBuildingName())){
                                            buildingInfo.setRecordBuildingName(building.getRecordBuildingName());
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setRecordBuildingName(building.getRecordBuildingName());
                                        up="1";
                                    }
                                }
                                //建筑类型
                                if(building.getConstructionType()!=null){
                                    if (!StringUtil.isEmpty(building.getConstructionType().getValue())) {
                                        if(!StringUtil.isEmpty(buildingInfo.getConstructionType())){
                                            if(!buildingInfo.getConstructionType().equals(building.getConstructionType().getValue())){
                                                buildingInfo.setConstructionType(building.getConstructionType().getValue());
                                                up="1";
                                            }
                                        }else{
                                            buildingInfo.setConstructionType(building.getConstructionType().getValue());
                                            up="1";
                                        }
                                    }
                                }
                                //销售装修类型
                                if(building.getDecorationStandard()!=null){
                                    if (!StringUtil.isEmpty(building.getDecorationStandard().getValue())) {
                                        if(!StringUtil.isEmpty(buildingInfo.getDecorationStandard())){
                                            if(!buildingInfo.getDecorationStandard().equals(building.getDecorationStandard().getValue())){
                                                buildingInfo.setDecorationStandard(building.getDecorationStandard().getValue());
                                                up="1";
                                            }
                                        }else{
                                            buildingInfo.setDecorationStandard(building.getDecorationStandard().getValue());
                                            up="1";
                                        }
                                    }
                                }
                                //层高（米）
                                if (!StringUtil.isEmpty(building.getFloorHeight()+"")) {
                                    if(!StringUtil.isEmpty(buildingInfo.getFloorHeight()+"")){
                                        if(!(buildingInfo.getFloorHeight()+"").equals(building.getFloorHeight()+"")){
                                            buildingInfo.setFloorHeight(building.getFloorHeight());
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setFloorHeight(building.getFloorHeight());
                                        up="1";
                                    }
                                }
                                //总层数
                                if (!StringUtil.isEmpty(building.getTotalFloor()+"")) {
                                    if(!StringUtil.isEmpty(buildingInfo.getTotalFloor()+"")){
                                        if(!(buildingInfo.getTotalFloor()+"").equals(building.getTotalFloor()+"")){
                                            buildingInfo.setTotalFloor(building.getTotalFloor()+"");
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setTotalFloor(building.getTotalFloor()+"");
                                        up="1";
                                    }
                                }
                                //总户数
                                if (!StringUtil.isEmpty(building.getTotalHouse()+"")) {
                                    if(!StringUtil.isEmpty(buildingInfo.getTotalHouse()+"")){
                                        if(!(buildingInfo.getTotalHouse()+"").equals(building.getTotalHouse()+"")){
                                            buildingInfo.setTotalHouse(building.getTotalHouse()+"");
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setTotalHouse(building.getTotalHouse()+"");
                                        up="1";
                                    }
                                }
                                //单元总数
                                if (!StringUtil.isEmpty(building.getTotalUnit()+"")) {
                                    if(!StringUtil.isEmpty(buildingInfo.getTotalUnit()+"")){
                                        if(!(buildingInfo.getTotalUnit()+"").equals(building.getTotalUnit()+"")){
                                            buildingInfo.setTotalUnit(building.getTotalUnit()+"");
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setTotalUnit(building.getTotalUnit()+"");
                                        up="1";
                                    }
                                }
                                //建筑面积总和（平方米）
                                if (!StringUtil.isEmpty(building.getTotalBuildingArea()+"")) {
                                    if(!StringUtil.isEmpty(buildingInfo.getTotalBuildingArea()+"")){
                                        if(!(buildingInfo.getTotalBuildingArea()+"").equals(building.getTotalBuildingArea()+"")){
                                            buildingInfo.setTotalBuildingArea(building.getTotalBuildingArea());
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setTotalBuildingArea(building.getTotalBuildingArea());
                                        up="1";
                                    }
                                }
                                //绿化面积总和（平方米）
                                if (!StringUtil.isEmpty(building.getTotalGreenArea()+"")) {
                                    if(!StringUtil.isEmpty(buildingInfo.getTotalGreenArea()+"")){
                                        if(!(buildingInfo.getTotalGreenArea()+"").equals(building.getTotalGreenArea()+"")){
                                            buildingInfo.setTotalGreenArea(building.getTotalGreenArea());
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setTotalGreenArea(building.getTotalGreenArea());
                                        up="1";
                                    }
                                }
                                //花园面积总和（平方米）
                                if (!StringUtil.isEmpty(building.getTotalGardenArea()+"")) {
                                    if(!StringUtil.isEmpty(buildingInfo.getTotalGardenArea()+"")){
                                        if(!(buildingInfo.getTotalGardenArea()+"").equals(building.getTotalGardenArea()+"")){
                                            buildingInfo.setTotalGardenArea(building.getTotalGardenArea());
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setTotalGardenArea(building.getTotalGardenArea());
                                        up="1";
                                    }
                                }
                                //套内面积总和（平方米）
                                if (!StringUtil.isEmpty(building.getTotalInternalArea()+"")) {
                                    if(!StringUtil.isEmpty(buildingInfo.getTotalInternalArea()+"")){
                                        if(!(buildingInfo.getTotalInternalArea()+"").equals(building.getTotalInternalArea()+"")){
                                            buildingInfo.setTotalInternalArea(building.getTotalInternalArea());
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setTotalInternalArea(building.getTotalInternalArea());
                                        up="1";
                                    }
                                }
                                //地块
                                if (!StringUtil.isEmpty(building.getBlock())) {
                                    if(!StringUtil.isEmpty(buildingInfo.getArea())){
                                        if(!buildingInfo.getArea().equals(building.getBlock())){
                                            buildingInfo.setArea(building.getBlock());
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setArea(building.getBlock());
                                        up="1";
                                    }
                                }
                                //楼栋预售楼号
                                if (!StringUtil.isEmpty(building.getSaleBuilding())) {
                                    if(!StringUtil.isEmpty(buildingInfo.getBuildingSale())){
                                        if(!buildingInfo.getBuildingSale().equals(building.getSaleBuilding())){
                                            buildingInfo.setBuildingSale(building.getSaleBuilding());
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setBuildingSale(building.getSaleBuilding());
                                        up="1";
                                    }
                                }
                                //楼栋备案楼号
                                if (!StringUtil.isEmpty(building.getBuildingNum())) {
                                    if(!StringUtil.isEmpty(buildingInfo.getBuildingRecord())){
                                        if(!building.getBuildingNum().equals(buildingInfo.getBuildingRecord())){
                                            buildingInfo.setBuildingRecord(building.getBuildingNum());
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setBuildingRecord(building.getBuildingNum());
                                        up="1";
                                    }
                                }
                                //组团编码
                                if (!StringUtil.isEmpty(building.getGroupCode())) {
                                    if(!StringUtil.isEmpty(buildingInfo.getGroupCode())){
                                        if(!buildingInfo.getGroupCode().equals(building.getGroupCode())){
                                            buildingInfo.setGroupCode(building.getGroupCode());
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setGroupCode(building.getGroupCode());
                                        up="1";
                                    }
                                }
                                //组团名称
                                if (!StringUtil.isEmpty(building.getGroupName())) {
                                    if(!StringUtil.isEmpty(buildingInfo.getGroupName())){
                                        if(!buildingInfo.getGroupName().equals(building.getGroupName())){
                                            buildingInfo.setGroupName(building.getGroupName());
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setGroupName(building.getGroupName());
                                        up="1";
                                    }
                                }
                                //关联的地块id
                                if (!StringUtil.isEmpty(building.getBlockId())) {
                                    if(!StringUtil.isEmpty(buildingInfo.getBlockId())){
                                        if(!buildingInfo.getBlockId().equals(building.getBlockId())){
                                            buildingInfo.setBlockId(building.getBlockId());
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setBlockId(building.getBlockId());
                                        up="1";
                                    }
                                }
                                //关联的地块编码
                                if (!StringUtil.isEmpty(building.getBlockCode())) {
                                    if(!StringUtil.isEmpty(buildingInfo.getBlockNum())){
                                        if(!buildingInfo.getBlockNum().equals(building.getBlockCode())){
                                            buildingInfo.setBlockNum(building.getBlockCode());
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setBlockNum(building.getBlockCode());
                                        up="1";
                                    }
                                }

                                if (building.getModifiedOn() != null) {
                                    buildingInfo.setCreateOn(building.getModifiedOn().getTime());
                                }
                                buildingInfo.setModifyOn(new Date());
                                //项目编码
                                if (!StringUtil.isEmpty(building.getProjectCode()) ) {
                                    if(!StringUtil.isEmpty(buildingInfo.getProjectNum())){
                                        if(!buildingInfo.getProjectNum().equals(building.getProjectCode())){
                                            buildingInfo.setProjectNum(building.getProjectCode());
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setProjectNum(building.getProjectCode());
                                        up="1";
                                    }
                                }
                                //项目id
                                if (!StringUtil.isEmpty(building.getProjectId())) {
                                    if(!StringUtil.isEmpty(buildingInfo.getProjectId())){
                                        if(!buildingInfo.getProjectId().equals(building.getProjectId())){
                                            buildingInfo.setProjectId(building.getProjectId());
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setProjectId(building.getProjectId());
                                        up="1";
                                    }
                                }
                                //状态0:有效 1:失效
                                if (!StringUtil.isEmpty(building.getStateCode()+"")) {
                                    if(!StringUtil.isEmpty(buildingInfo.getStateCode()+"")){
                                        if(!(buildingInfo.getStateCode()+"").equals(building.getStateCode()+"")){
                                            buildingInfo.setStateCode(building.getStateCode()+"");
                                            up="1";
                                        }
                                    }else{
                                        buildingInfo.setStateCode(building.getStateCode()+"");
                                        up="1";
                                    }
                                }
                                if("1".equals(up)){
                                    houseBuildingRepository.updateBuildingInfo(buildingInfo);
                                }
                            } else {
                                HouseBuildingEntity houseBuildingEntity = new HouseBuildingEntity();
                                houseBuildingEntity.setId(building.getHousingResourcesId());
                                if(!StringUtil.isEmpty(building.getBlock())){
                                    houseBuildingEntity.setArea(building.getBlock());
                                }

                                if(!StringUtil.isEmpty(building.getBlockBuildingCode())){
                                    houseBuildingEntity.setBuildingNum(building.getBlockBuildingCode());
                                }

                                if(!StringUtil.isEmpty(building.getBuildingNum())){
                                    houseBuildingEntity.setBuildingRecord(building.getBuildingNum());
                                }
                                if (building.getModifiedOn() != null) {
                                    houseBuildingEntity.setCreateOn(building.getModifiedOn().getTime());
                                }
                                if(!StringUtil.isEmpty(building.getProjectCode())){
                                    houseBuildingEntity.setProjectNum(building.getProjectCode());
                                }
                                if(!StringUtil.isEmpty(building.getProjectId())){
                                    houseBuildingEntity.setProjectId(building.getProjectId());
                                }
                                if(!StringUtil.isEmpty(building.getSaleBuilding())){
                                    houseBuildingEntity.setBuildingSale(building.getSaleBuilding());
                                }
                                if(!StringUtil.isEmpty(building.getBlockId())){
                                    houseBuildingEntity.setBlockId(building.getBlockId());
                                }
                                if(!StringUtil.isEmpty(building.getBlockCode())){
                                    houseBuildingEntity.setBlockNum(building.getBlockCode());
                                }
                                houseBuildingEntity.setModifyOn(new Date());
                                if(!StringUtil.isEmpty(building.getRecordBuildingCode())){
                                    houseBuildingEntity.setRecordBuildingCode(building.getRecordBuildingCode());
                                }
                                if(building.getPropertyType()!=null){
                                    if(!StringUtil.isEmpty(building.getPropertyType().getValue())){
                                        houseBuildingEntity.setPropertyType(building.getPropertyType().getValue());
                                    }
                                }
                                if(!StringUtil.isEmpty(building.getSaleBuildingName())){
                                    houseBuildingEntity.setSaleBuildingName(building.getSaleBuildingName());
                                }
                                if(!StringUtil.isEmpty(building.getRecordBuildingName())){
                                    houseBuildingEntity.setRecordBuildingName(building.getRecordBuildingName());
                                }
                                if(building.getConstructionType()!=null){
                                    if(!StringUtil.isEmpty(building.getConstructionType().getValue())){
                                        houseBuildingEntity.setConstructionType(building.getConstructionType().getValue());
                                    }
                                }
                                if(building.getDecorationStandard()!=null){
                                    if(!StringUtil.isEmpty(building.getDecorationStandard().getValue())){
                                        houseBuildingEntity.setDecorationStandard(building.getDecorationStandard().getValue());
                                    }
                                }
                                if(!StringUtil.isEmpty(building.getFloorHeight()+"")){
                                    houseBuildingEntity.setFloorHeight(building.getFloorHeight());
                                }
                                if(!StringUtil.isEmpty(building.getFloorHeight()+"")){
                                    houseBuildingEntity.setFloorHeight(building.getFloorHeight());
                                }
                                if(!StringUtil.isEmpty(building.getTotalFloor()+"")){
                                    houseBuildingEntity.setTotalFloor(building.getTotalFloor()+"");
                                }
                                if(!StringUtil.isEmpty(building.getTotalHouse()+"")){
                                    houseBuildingEntity.setTotalHouse(building.getTotalHouse()+"");
                                }
                                if(!StringUtil.isEmpty(building.getTotalUnit()+"")){
                                    houseBuildingEntity.setTotalUnit(building.getTotalUnit()+"");
                                }
                                if(!StringUtil.isEmpty(building.getTotalBuildingArea()+"")){
                                    houseBuildingEntity.setTotalBuildingArea(building.getTotalBuildingArea());
                                }
                                if(!StringUtil.isEmpty(building.getTotalGreenArea()+"")){
                                    houseBuildingEntity.setTotalGreenArea(building.getTotalGreenArea());
                                }
                                if(!StringUtil.isEmpty(building.getTotalGardenArea()+"")){
                                    houseBuildingEntity.setTotalGardenArea(building.getTotalGardenArea());
                                }
                                if(!StringUtil.isEmpty(building.getTotalInternalArea()+"")){
                                    houseBuildingEntity.setTotalInternalArea(building.getTotalInternalArea());
                                }
                                if(!StringUtil.isEmpty(building.getGroupCode())){
                                    houseBuildingEntity.setGroupCode(building.getGroupCode());
                                }
                                if(!StringUtil.isEmpty(building.getGroupName())){
                                    houseBuildingEntity.setGroupName(building.getGroupName());
                                }
                                if(!StringUtil.isEmpty(building.getStateCode()+"")){
                                    houseBuildingEntity.setStateCode(building.getStateCode()+"");
                                }else{
                                    houseBuildingEntity.setStateCode("0");
                                }
                                houseBuildingRepository.create(houseBuildingEntity);

                            }
                            //更新房屋信息临时表
                            BuildingMappingTimeEntity buildEntity = temporaryTableUpdateRepository.queryBuild(building.getHousingResourcesId());
                            if (buildEntity != null) {
                                String up="0";
                                //预售编码
                                if (!StringUtil.isEmpty(building.getBlockBuildingCode())) {
                                    if(!building.getBlockBuildingCode().equals(buildEntity.getCurrentNum())){
                                        buildEntity.setCurrentNum(building.getBlockBuildingCode());
                                        up="1";
                                    }
                                }
                                //备案编码
                                if (!StringUtil.isEmpty(building.getRecordBuildingCode())) {
                                    if(!StringUtil.isEmpty(buildEntity.getRecordCurrentNum())){
                                        if(!building.getRecordBuildingCode().equals(buildEntity.getRecordCurrentNum())){
                                            buildEntity.setRecordCurrentNum(building.getRecordBuildingCode());
                                            up="1";
                                        }
                                    }else{
                                        buildEntity.setRecordCurrentNum(building.getRecordBuildingCode());
                                        up="1";
                                    }
                                }
                                //楼栋级别上一级id编码改为地块
                                if (!StringUtil.isEmpty(building.getBlockId())) {
                                    if(!building.getBlockId().equals(buildEntity.getParentId())){
                                        buildEntity.setParentId(building.getBlockId());
                                        up="1";
                                    }
                                }
                                if (!StringUtil.isEmpty(building.getBlockCode())) {
                                    if(!building.getBlockCode().equals(buildEntity.getParentNum())){
                                        buildEntity.setParentNum(building.getBlockCode());
                                        up="1";
                                    }
                                }
                                //预售名称
                                if (!StringUtil.isEmpty(building.getSaleBuildingName())) {
                                    if(!StringUtil.isEmpty(buildEntity.getName())){
                                        if(!building.getSaleBuildingName().equals(buildEntity.getName())){
                                            buildEntity.setName(building.getSaleBuildingName());
                                            up="1";
                                        }
                                    }else{
                                        buildEntity.setName(building.getSaleBuildingName());
                                        up="1";
                                    }
                                }
                                //备案名称
                                if (!StringUtil.isEmpty(building.getRecordBuildingName())) {
                                    if(!StringUtil.isEmpty(buildEntity.getRecordName())){
                                        if(!building.getRecordBuildingName().equals(buildEntity.getRecordName())){
                                            buildEntity.setRecordName(building.getRecordBuildingName());
                                            up="1";
                                        }
                                    }else{
                                        buildEntity.setRecordName(building.getRecordBuildingName());
                                        up="1";
                                    }
                                }
                                //状态0:有效 1:失效
                                if(!StringUtil.isEmpty(building.getStateCode()+"")){
                                    if(!StringUtil.isEmpty(buildEntity.getStart())){
                                        if(!buildEntity.getStart().equals(building.getStateCode()+"")){
                                            buildEntity.setStart(building.getStateCode()+"");
                                            up="1";
                                        }
                                    }else{
                                        buildEntity.setStart(building.getStateCode()+"");
                                        up="1";
                                    }
                                }
                                //房产类型
                                if(building.getPropertyType()!=null){
                                    if (!StringUtil.isEmpty(building.getPropertyType().getValue())) {
                                        if(!StringUtil.isEmpty(buildEntity.getPropertyType())){
                                            if(!buildEntity.getPropertyType().equals(building.getPropertyType().getValue())){
                                                buildEntity.setPropertyType(building.getPropertyType().getValue());
                                                up="1";
                                            }
                                        }else{
                                            buildEntity.setPropertyType(building.getPropertyType().getValue());
                                            up="1";
                                        }
                                    }
                                }
                                buildEntity.setGraded("2");
                                buildEntity.setTimeStamp(new Date());
                                if("1".equals(up)){
                                    temporaryTableUpdateRepository.updateBuild(buildEntity);
                                }
                            } else {
                                BuildingMappingTimeEntity buildInfo = new BuildingMappingTimeEntity();
                                buildInfo.setCurrentId(building.getHousingResourcesId());
                                buildInfo.setCurrentNum(building.getBlockBuildingCode());
                                if(!StringUtil.isEmpty(building.getRecordBuildingCode())){
                                    buildInfo.setRecordCurrentNum(building.getRecordBuildingCode());
                                }
                                if(!StringUtil.isEmpty(building.getSaleBuildingName())){
                                    buildInfo.setName(building.getSaleBuildingName());
                                }
                                if(!StringUtil.isEmpty(building.getRecordBuildingName())){
                                    buildInfo.setRecordName(building.getRecordBuildingName());
                                }
                                //楼栋级别上一级id编码改为地块
                                buildInfo.setParentId(building.getBlockId());
                                buildInfo.setParentNum(building.getBlockCode());
                                if(!StringUtil.isEmpty(building.getStateCode()+"")){
                                    buildInfo.setStart(building.getStateCode()+"");
                                }else{
                                    buildInfo.setStart("0");
                                }
                                if(building.getPropertyType()!=null){
                                    if(!StringUtil.isEmpty(building.getPropertyType().getValue())){
                                        buildInfo.setPropertyType(building.getPropertyType().getValue());
                                    }
                                }
                                buildInfo.setGraded("2");
                                buildInfo.setTimeStamp(new Date());
                                temporaryTableUpdateRepository.createBuild(buildInfo);
                            }
                        }
                    }
                    //更新单元数据信息
                    Unit[] unitList = responseBody.getUnitList();
                    if (unitList.length > 0) {
                        for (int i = 0; i < unitList.length; i++) {
                            Unit unit = unitList[i];
                            HouseUnitEntity unitInfo = houseUnitRepository.getInfoByUnitId(unit.getUnitId());
                            //有数据更新 否则创建 存入分表中
                            if (unitInfo != null) {
                                String up="0";
                                //预售单元编码
                                if (!StringUtil.isEmpty(unit.getUnitcode())) {
                                    if(!StringUtil.isEmpty(unitInfo.getUnitCode())){
                                        if(!unitInfo.getUnitCode().equals(unit.getUnitcode())){
                                            unitInfo.setUnitCode(unit.getUnitcode());
                                            up="1";
                                        }
                                    }else{
                                        unitInfo.setUnitCode(unit.getUnitcode());
                                        up="1";
                                    }
                                }
                                //备案单元编码
                                if (!StringUtil.isEmpty(unit.getRecordUnitCode())) {
                                    if(!StringUtil.isEmpty(unitInfo.getRecordUnitCode())){
                                        if(!unitInfo.getRecordUnitCode().equals(unit.getRecordUnitCode())){
                                            unitInfo.setRecordUnitCode(unit.getRecordUnitCode());
                                            up="1";
                                        }
                                    }else{
                                        unitInfo.setRecordUnitCode(unit.getRecordUnitCode());
                                        up="1";
                                    }
                                }
                                //预售单元名称
                                if (!StringUtil.isEmpty(unit.getName())) {
                                    if(!StringUtil.isEmpty(unitInfo.getName())){
                                        if(!unitInfo.getName().equals(unit.getName())){
                                            unitInfo.setName(unit.getName());
                                            up="1";
                                        }
                                    }else{
                                        unitInfo.setName(unit.getName());
                                        up="1";
                                    }
                                }
                                //备案单元名称
                                if (!StringUtil.isEmpty(unit.getRecordUnitName())) {
                                    if(!StringUtil.isEmpty(unitInfo.getRecordUnitName())){
                                        if(!unitInfo.getRecordUnitName().equals(unit.getRecordUnitName())){
                                            unitInfo.setRecordUnitName(unit.getRecordUnitName());
                                            up="1";
                                        }
                                    }else{
                                        unitInfo.setRecordUnitName(unit.getRecordUnitName());
                                        up="1";
                                    }
                                }
                                //预售单元号
                                if (!StringUtil.isEmpty(unit.getUnitNum())) {
                                    if(!StringUtil.isEmpty(unitInfo.getUnitNum())){
                                        if(!unitInfo.getUnitNum().equals(unit.getUnitNum())){
                                            unitInfo.setUnitNum(unit.getUnitNum());
                                            up="1";
                                        }
                                    }else{
                                        unitInfo.setUnitNum(unit.getUnitNum());
                                        up="1";
                                    }
                                }
                                //备案单元号
                                if (!StringUtil.isEmpty(unit.getRecordUnitNum())) {
                                    if(!StringUtil.isEmpty(unitInfo.getRecordUnitNum())){
                                        if(!unitInfo.getRecordUnitNum().equals(unit.getRecordUnitNum())){
                                            unitInfo.setRecordUnitNum(unit.getRecordUnitNum());
                                            up="1";
                                        }
                                    }else{
                                        unitInfo.setRecordUnitNum(unit.getRecordUnitNum());
                                        up="1";
                                    }
                                }
                                //房产类型
                                if(unit.getPropertyType()!=null){
                                    if (!StringUtil.isEmpty(unit.getPropertyType().getValue())) {
                                        if(!StringUtil.isEmpty(unitInfo.getPropertyType())){
                                            if(!unitInfo.getPropertyType().equals(unit.getPropertyType().getValue())){
                                                unitInfo.setPropertyType(unit.getPropertyType().getValue());
                                                up="1";
                                            }
                                        }else{
                                            unitInfo.setPropertyType(unit.getPropertyType().getValue());
                                            up="1";
                                        }
                                    }
                                }
                                //关联楼栋ID
                                if (!StringUtil.isEmpty(unit.getBuildingId())) {
                                    if(!StringUtil.isEmpty(unitInfo.getBuildingId())){
                                        if(!unitInfo.getBuildingId().equals(unit.getBuildingId())){
                                            unitInfo.setBuildingId(unit.getBuildingId());
                                            up="1";
                                        }
                                    }else{
                                        unitInfo.setBuildingId(unit.getBuildingId());
                                        up="1";
                                    }
                                }
                                //关联预售楼栋编码
                                if (!StringUtil.isEmpty(unit.getBuildingCode())) {
                                    if(!StringUtil.isEmpty(unitInfo.getBuildingCode())){
                                        if(!unitInfo.getBuildingCode().equals(unit.getBuildingCode())){
                                            unitInfo.setBuildingCode(unit.getBuildingCode());
                                            up="1";
                                        }
                                    }else{
                                        unitInfo.setBuildingCode(unit.getBuildingCode());
                                        up="1";
                                    }
                                }
                                //关联备案楼栋编码
                                if (!StringUtil.isEmpty(unit.getRecordBuildingCode())) {
                                    if(!StringUtil.isEmpty(unitInfo.getRecordBuildingCode())){
                                        if(!unitInfo.getRecordBuildingCode().equals(unit.getRecordBuildingCode())){
                                            unitInfo.setRecordBuildingCode(unit.getRecordBuildingCode());
                                            up="1";
                                        }
                                    }else{
                                        unitInfo.setRecordBuildingCode(unit.getRecordBuildingCode());
                                        up="1";
                                    }

                                }
                                //状态0:有效 1:失效
                                if(!StringUtil.isEmpty(unit.getStateCode()+"")){
                                    if(!StringUtil.isEmpty(unitInfo.getStateCode())){
                                        if(!unitInfo.getStateCode().equals(unit.getStateCode()+"")){
                                            unitInfo.setStateCode(unit.getStateCode()+"");
                                            up="1";
                                        }
                                    }else{
                                        unitInfo.setStateCode(unit.getStateCode()+"");
                                        up="1";
                                    }
                                }
                                if (unit.getModifiedOn() != null) {
                                    unitInfo.setCreateOn(unit.getModifiedOn().getTime());
                                }
                                if("1".equals(up)){
                                    unitInfo.setModifyOn(new Date());
                                    houseUnitRepository.updateUnitInfo(unitInfo);
                                }
                            } else {
                                HouseUnitEntity houseUnitEntity = new HouseUnitEntity();
                                //主键
                                houseUnitEntity.setId(unit.getUnitId());
                                //预售单元名称
                                if(!StringUtil.isEmpty(unit.getName())){
                                    houseUnitEntity.setName(unit.getName());
                                }
                                //备案单元名称
                                if(!StringUtil.isEmpty(unit.getRecordUnitName())){
                                    houseUnitEntity.setRecordUnitName(unit.getRecordUnitName());
                                }
                                //预售单元编码
                                if(!StringUtil.isEmpty(unit.getUnitcode())){
                                    houseUnitEntity.setUnitCode(unit.getUnitcode());
                                }
                                //备案单元编码
                                if(!StringUtil.isEmpty(unit.getRecordUnitCode())){
                                    houseUnitEntity.setRecordUnitCode(unit.getRecordUnitCode());
                                }
                                //关联楼栋ID
                                if(!StringUtil.isEmpty(unit.getBuildingId())){
                                    houseUnitEntity.setBuildingId(unit.getBuildingId());
                                }
                                //预售单元号
                                if(!StringUtil.isEmpty(unit.getUnitNum())){
                                    houseUnitEntity.setUnitNum(unit.getUnitNum());
                                }
                                //备案单元单元号
                                if(!StringUtil.isEmpty(unit.getRecordUnitNum())){
                                    houseUnitEntity.setRecordUnitNum(unit.getRecordUnitNum());
                                }
                                //关联预售楼栋编码
                                if(!StringUtil.isEmpty(unit.getBuildingCode())){
                                    houseUnitEntity.setBuildingCode(unit.getBuildingCode());
                                }
                                //关联备案楼栋编码
                                if(!StringUtil.isEmpty(unit.getRecordBuildingCode())){
                                    houseUnitEntity.setRecordBuildingCode(unit.getRecordBuildingCode());
                                }
                                //房产类型
                                if(unit.getPropertyType()!=null){
                                    if(!StringUtil.isEmpty(unit.getPropertyType().getValue())){
                                        houseUnitEntity.setPropertyType(unit.getPropertyType().getValue());
                                    }
                                }
                                //状态
                                if(!StringUtil.isEmpty(unit.getStateCode()+"")){
                                    houseUnitEntity.setStateCode(unit.getStateCode()+"");
                                }else{
                                    houseUnitEntity.setStateCode("0");
                                }

                                if (unit.getModifiedOn() != null) {
                                    houseUnitEntity.setCreateOn(unit.getModifiedOn().getTime());
                                }
                                houseUnitEntity.setModifyOn(new Date());
                                houseUnitRepository.create(houseUnitEntity);

                            }

                            //更新房屋信息临时表
                            BuildingMappingTimeEntity buildEntity = temporaryTableUpdateRepository.queryBuild(unit.getUnitId());
                            if (buildEntity != null) {
                                String up="0";
                                //预售单元编码
                                if (!StringUtil.isEmpty(unit.getUnitcode())) {
                                    if(!unit.getUnitcode().equals(buildEntity.getParentNum())){
                                        buildEntity.setCurrentNum(unit.getUnitcode());
                                        up="1";
                                    }
                                }
                                //备案单元编码
                                if (!StringUtil.isEmpty(unit.getRecordUnitCode())) {
                                    if(!StringUtil.isEmpty(buildEntity.getRecordParentNum())){
                                        if(!unit.getRecordUnitCode().equals(buildEntity.getRecordCurrentNum())){
                                            buildEntity.setRecordCurrentNum(unit.getRecordUnitCode());
                                            up="1";
                                        }
                                    }else{
                                        buildEntity.setRecordCurrentNum(unit.getRecordUnitCode());
                                        up="1";
                                    }
                                }
                                //关联楼栋id
                                if (!StringUtil.isEmpty(unit.getBuildingId())) {
                                    if(!unit.getBuildingId().equals(buildEntity.getParentId())){
                                        buildEntity.setParentId(unit.getBuildingId());
                                        up="1";
                                    }

                                }
                                //关联预售楼栋编码
                                if (!StringUtil.isEmpty(unit.getBuildingCode())) {
                                    if(!unit.getBuildingCode().equals(buildEntity.getParentNum())){
                                        buildEntity.setParentNum(unit.getBuildingCode());
                                        up="1";
                                    }
                                }
                                //关联备案楼栋编码
                                if (!StringUtil.isEmpty(unit.getRecordBuildingCode())) {
                                    if(!StringUtil.isEmpty(buildEntity.getRecordParentNum())){
                                        if(!unit.getRecordBuildingCode().equals(buildEntity.getRecordParentNum())){
                                            buildEntity.setRecordParentNum(unit.getRecordBuildingCode());
                                            up="1";
                                        }
                                    }else{
                                        buildEntity.setRecordParentNum(unit.getRecordBuildingCode());
                                        up="1";
                                    }
                                }
                                //预售单元名称
                                if (!StringUtil.isEmpty(unit.getName())) {
                                    if(!unit.getName().equals(buildEntity.getName())){
                                        buildEntity.setName(unit.getName());
                                        up="1";
                                    }
                                }
                                //备案单元名称
                                if (!StringUtil.isEmpty(unit.getRecordUnitName())) {
                                    if(!StringUtil.isEmpty(buildEntity.getRecordName())){
                                        if(!unit.getRecordUnitName().equals(buildEntity.getRecordName())){
                                            buildEntity.setRecordName(unit.getRecordUnitName());
                                            up="1";
                                        }
                                    }else{
                                        buildEntity.setRecordName(unit.getRecordUnitName());
                                        up="1";
                                    }
                                }
                                //状态0:有效 1:失效
                                if(!StringUtil.isEmpty(unit.getStateCode()+"")){
                                    if(!StringUtil.isEmpty(buildEntity.getStart())){
                                        if(!buildEntity.getStart().equals(unit.getStateCode()+"")){
                                            buildEntity.setStart(unit.getStateCode()+"");
                                            up="1";
                                        }
                                    }else{
                                        buildEntity.setStart(unit.getStateCode()+"");
                                        up="1";
                                    }
                                }
                                //房产类型
                                if(unit.getPropertyType()!=null){
                                    if (!StringUtil.isEmpty(unit.getPropertyType().getValue())) {
                                        if(!StringUtil.isEmpty(buildEntity.getPropertyType())){
                                            if(!buildEntity.getPropertyType().equals(unit.getPropertyType().getValue())){
                                                buildEntity.setPropertyType(unit.getPropertyType().getValue());
                                                up="1";
                                            }
                                        }else{
                                            buildEntity.setPropertyType(unit.getPropertyType().getValue());
                                            up="1";
                                        }
                                    }
                                }
                                buildEntity.setGraded("3");
                                buildEntity.setTimeStamp(new Date());
                                if("1".equals(up)){
                                    temporaryTableUpdateRepository.updateBuild(buildEntity);
                                }

                            } else {
                                BuildingMappingTimeEntity buildInfo = new BuildingMappingTimeEntity();
                                buildInfo.setCurrentId(unit.getUnitId());
                                if(!StringUtil.isEmpty(unit.getUnitcode())){
                                    buildInfo.setCurrentNum(unit.getUnitcode());
                                }
                                if(!StringUtil.isEmpty(unit.getRecordUnitCode())){
                                    buildInfo.setRecordCurrentNum(unit.getRecordUnitCode());
                                }
                                if(!StringUtil.isEmpty(unit.getName())){
                                    buildInfo.setName(unit.getName());
                                }
                                if(!StringUtil.isEmpty(unit.getRecordUnitName())){
                                    buildInfo.setRecordName(unit.getRecordUnitName());
                                }
                                if(!StringUtil.isEmpty(unit.getBuildingId())){
                                    buildInfo.setParentId(unit.getBuildingId());
                                }
                                if(!StringUtil.isEmpty(unit.getBuildingCode())){
                                    buildInfo.setParentNum(unit.getBuildingCode());
                                }
                                if(!StringUtil.isEmpty(unit.getRecordBuildingCode())){
                                    buildInfo.setRecordParentNum(unit.getRecordBuildingCode());
                                }
                                if(!StringUtil.isEmpty(unit.getStateCode()+"")){
                                    buildInfo.setStart(unit.getStateCode()+"");
                                }else{
                                    buildInfo.setStart("0");
                                }
                                if(unit.getPropertyType()!=null){
                                    if(!StringUtil.isEmpty(unit.getPropertyType().getValue())){
                                        buildInfo.setPropertyType(unit.getPropertyType().getValue());
                                    }
                                }
                                buildInfo.setGraded("3");
                                buildInfo.setTimeStamp(new Date());
                                temporaryTableUpdateRepository.createBuild(buildInfo);
                            }
                        }
                    }
                    //更新楼层数据
                    Floor[] floorList = responseBody.getFloorList();
                    if (floorList.length > 0) {
                        for (int i = 0; i < floorList.length; i++) {
                            Floor floor = floorList[i];
                            HouseFloorEntity floorInfo = houseFloorRepository.getInfoByFloorId(floor.getFloorId());
                            //有数据则更新,无数据则创建 存入分表中
                            if (floorInfo != null) {
                                String up="0";
                                //预售楼层编码
                                if (!StringUtil.isEmpty(floor.getFloorcode())) {
                                    if(!StringUtil.isEmpty(floorInfo.getFloorCode())){
                                        if(!floorInfo.getFloorCode().equals(floor.getFloorcode())){
                                            floorInfo.setFloorCode(floor.getFloorcode());
                                            up="1";
                                        }
                                    }else{
                                        floorInfo.setFloorCode(floor.getFloorcode());
                                        up="1";
                                    }
                                }
                                //备案楼层编码
                                if (!StringUtil.isEmpty(floor.getRecordFloorcode())) {
                                    if(!StringUtil.isEmpty(floorInfo.getRecordFloorcode())){
                                        if(!floorInfo.getRecordFloorcode().equals(floor.getRecordFloorcode())){
                                            floorInfo.setRecordFloorcode(floor.getRecordFloorcode());
                                            up="1";
                                        }
                                    }else{
                                        floorInfo.setRecordFloorcode(floor.getRecordFloorcode());
                                        up="1";
                                    }
                                }
                                //预售楼层名称
                                if (!StringUtil.isEmpty(floor.getName())) {
                                    if(!StringUtil.isEmpty(floorInfo.getFloorName())){
                                        if(!floorInfo.getFloorName().equals(floor.getName())){
                                            floorInfo.setFloorName(floor.getName());
                                            up="1";
                                        }
                                    }else{
                                        floorInfo.setFloorName(floor.getName());
                                        up="1";
                                    }
                                }
                                //备案楼层名称
                                if (!StringUtil.isEmpty(floor.getRecordFloorName())) {
                                    if(!StringUtil.isEmpty(floorInfo.getRecordFloorName())){
                                        if(!floorInfo.getRecordFloorName().equals(floor.getRecordFloorName())){
                                            floorInfo.setRecordFloorName(floor.getRecordFloorName());
                                            up="1";
                                        }
                                    }else{
                                        floorInfo.setRecordFloorName(floor.getRecordFloorName());
                                        up="1";
                                    }
                                }
                                //预售楼层号
                                if (!StringUtil.isEmpty(floor.getFloorNum())) {
                                    if(!StringUtil.isEmpty(floorInfo.getFloorNum())){
                                        if(!floorInfo.getFloorNum().equals(floor.getFloorNum())){
                                            floorInfo.setFloorNum(floor.getFloorNum());
                                            up="1";
                                        }
                                    }else{
                                        floorInfo.setFloorNum(floor.getFloorNum());
                                        up="1";
                                    }
                                }
                                //备案楼层号
                                if (!StringUtil.isEmpty(floor.getRecordFloorNum())) {
                                    if(!StringUtil.isEmpty(floorInfo.getRecordFloorNum())){
                                        if(!floorInfo.getRecordFloorNum().equals(floor.getRecordFloorNum())){
                                            floorInfo.setRecordFloorNum(floor.getRecordFloorNum());
                                            up="1";
                                        }
                                    }else{
                                        floorInfo.setRecordFloorNum(floor.getRecordFloorNum());
                                        up="1";
                                    }
                                }
                                //房产类型
                                if(floor.getPropertyType()!=null){
                                    if (!StringUtil.isEmpty(floor.getPropertyType().getValue())) {
                                        if(!StringUtil.isEmpty(floorInfo.getPropertyType())){
                                            if(!floorInfo.getPropertyType().equals(floor.getPropertyType().getValue())){
                                                floorInfo.setPropertyType(floor.getPropertyType().getValue());
                                                up="1";
                                            }
                                        }else{
                                            floorInfo.setPropertyType(floor.getPropertyType().getValue());
                                            up="1";
                                        }
                                    }
                                }
                                //关联单元ID
                                if (!StringUtil.isEmpty(floor.getUnitId())) {
                                    if(!StringUtil.isEmpty(floorInfo.getUnitId())){
                                        if(!floorInfo.getUnitId().equals(floor.getUnitId())){
                                            floorInfo.setUnitId(floor.getUnitId());
                                            up="1";
                                        }
                                    }else{
                                        floorInfo.setUnitId(floor.getUnitId());
                                        up="1";
                                    }
                                }
                                //关联预售单元编码
                                if (!StringUtil.isEmpty(floor.getUnitCode())) {
                                    if(!StringUtil.isEmpty(floorInfo.getUnitCode())){
                                        if(!floorInfo.getUnitCode().equals(floor.getUnitCode())){
                                            floorInfo.setUnitCode(floor.getUnitCode());
                                            up="1";
                                        }
                                    }else{
                                        floorInfo.setUnitCode(floor.getUnitCode());
                                        up="1";
                                    }
                                }
                                //关联备案单元编码
                                if (!StringUtil.isEmpty(floor.getRecordUnitCode())) {
                                    if(!StringUtil.isEmpty(floorInfo.getRecordUnitCode())){
                                        if(!floorInfo.getRecordUnitCode().equals(floor.getRecordUnitCode())){
                                            floorInfo.setRecordUnitCode(floor.getRecordUnitCode());
                                            up="1";
                                        }
                                    }else{
                                        floorInfo.setRecordUnitCode(floor.getRecordUnitCode());
                                        up="1";
                                    }
                                }
                                //状态0:有效 1:失效
                                if(!StringUtil.isEmpty(floor.getStateCode()+"")){
                                    if(!StringUtil.isEmpty(floorInfo.getStateCode())){
                                        if(!floorInfo.getStateCode().equals(floor.getStateCode()+"")){
                                            floorInfo.setStateCode(floor.getStateCode()+"");
                                            up="1";
                                        }
                                    }else{
                                        floorInfo.setStateCode(floor.getStateCode()+"");
                                        up="1";
                                    }
                                }
                                if (floor.getModifiedOn() != null) {
                                    floorInfo.setCreateOn(floor.getModifiedOn().getTime());
                                }
                                if("1".equals(up)){
                                    floorInfo.setModifyOn(new Date());
                                    houseFloorRepository.updateBuildingInfo(floorInfo);

                                }
                            } else {
                                HouseFloorEntity houseFloorEntity = new HouseFloorEntity();
                                houseFloorEntity.setId(floor.getFloorId());
                                if(!StringUtil.isEmpty(floor.getFloorcode())){
                                    houseFloorEntity.setFloorCode(floor.getFloorcode());
                                }

                                if(!StringUtil.isEmpty(floor.getRecordFloorcode())){
                                    houseFloorEntity.setRecordFloorcode(floor.getRecordFloorcode());
                                }

                                if(!StringUtil.isEmpty(floor.getFloorNum())){
                                    houseFloorEntity.setFloorNum(floor.getFloorNum());
                                }

                                if(!StringUtil.isEmpty(floor.getRecordFloorNum())){
                                    houseFloorEntity.setRecordFloorNum(floor.getRecordFloorNum());
                                }

                                if(!StringUtil.isEmpty(floor.getName())){
                                    houseFloorEntity.setFloorName(floor.getName());
                                }

                                if(!StringUtil.isEmpty(floor.getRecordFloorName())){
                                    houseFloorEntity.setRecordFloorName(floor.getRecordFloorName());
                                }

                                if(!StringUtil.isEmpty(floor.getUnitCode())){
                                    houseFloorEntity.setUnitCode(floor.getUnitCode());
                                }

                                if(!StringUtil.isEmpty(floor.getRecordUnitCode())){
                                    houseFloorEntity.setRecordUnitCode(floor.getRecordUnitCode());
                                }

                                if(!StringUtil.isEmpty(floor.getUnitId())){
                                    houseFloorEntity.setUnitId(floor.getUnitId());
                                }

                                if (floor.getModifiedOn() != null) {
                                    houseFloorEntity.setCreateOn(floor.getModifiedOn().getTime());
                                }
                                houseFloorEntity.setModifyOn(new Date());
                                if(floor.getPropertyType()!=null){
                                    if(!StringUtil.isEmpty(floor.getPropertyType().getValue())){
                                        houseFloorEntity.setPropertyType(floor.getPropertyType().getValue());
                                    }
                                }
                                if(!StringUtil.isEmpty(floor.getStateCode()+"")){
                                    houseFloorEntity.setStateCode(floor.getStateCode()+"");
                                }else{
                                    houseFloorEntity.setStateCode("0");
                                }

                                houseFloorRepository.create(houseFloorEntity);

                            }
                            //更新房屋信息临时表 级别 4
                            BuildingMappingTimeEntity buildEntity = temporaryTableUpdateRepository.queryBuild(floor.getFloorId());
                            if (buildEntity != null) {
                                String up="0";
                                //预售楼层编码
                                if (!StringUtil.isEmpty(floor.getFloorcode())) {
                                    if(!floor.getFloorcode().equals(buildEntity.getCurrentNum())){
                                        buildEntity.setCurrentNum(floor.getFloorcode());
                                        up="1";
                                    }
                                }
                                //备案楼层编码
                                if (!StringUtil.isEmpty(floor.getRecordFloorcode())) {
                                    if(!StringUtil.isEmpty(buildEntity.getRecordCurrentNum())){
                                        if(!floor.getRecordFloorcode().equals(buildEntity.getRecordCurrentNum())){
                                            buildEntity.setRecordCurrentNum(floor.getRecordFloorcode());
                                            up="1";
                                        }
                                    }else{
                                        buildEntity.setRecordCurrentNum(floor.getRecordFloorcode());
                                        up="1";
                                    }
                                }
                                //关联单元ID
                                if (!StringUtil.isEmpty(floor.getUnitId())) {
                                    if(!floor.getUnitId().equals(buildEntity.getParentId())){
                                        buildEntity.setParentId(floor.getUnitId());
                                        up="1";                                }

                                }
                                //关联预售单元编码
                                if (!StringUtil.isEmpty(floor.getUnitCode())) {
                                    if(!floor.getUnitCode().equals(buildEntity.getParentNum())){
                                        buildEntity.setParentNum(floor.getUnitCode());
                                        up="1";
                                    }
                                }
                                //关联备案单元编码
                                if (!StringUtil.isEmpty(floor.getRecordUnitCode())) {
                                    if(!StringUtil.isEmpty(buildEntity.getRecordParentNum())){
                                        if(!floor.getRecordUnitCode().equals(buildEntity.getRecordParentNum())){
                                            buildEntity.setRecordParentNum(floor.getRecordUnitCode());
                                            up="1";
                                        }
                                    }else{
                                        buildEntity.setRecordParentNum(floor.getRecordUnitCode());
                                        up="1";
                                    }
                                }
                                //名字
                                if (!StringUtil.isEmpty(floor.getName())) {
                                    if(!StringUtil.isEmpty(buildEntity.getName())){
                                        if(!floor.getName().equals(buildEntity.getName())){
                                            buildEntity.setName(floor.getName());
                                            up="1";
                                        }
                                    }else{
                                        buildEntity.setName(floor.getName());
                                        up="1";
                                    }
                                }
                                //备案名字
                                if (!StringUtil.isEmpty(floor.getRecordFloorName())) {
                                    if(!StringUtil.isEmpty(buildEntity.getRecordName())){
                                        if(!floor.getRecordFloorName().equals(buildEntity.getRecordName())){
                                            buildEntity.setRecordName(floor.getRecordFloorName());
                                            up="1";
                                        }
                                    }else{
                                        buildEntity.setRecordName(floor.getRecordFloorName());
                                        up="1";
                                    }
                                }
                                //状态0:有效 1:失效
                                if(!StringUtil.isEmpty(floor.getStateCode()+"")){
                                    if(!StringUtil.isEmpty(buildEntity.getStart())){
                                        if(!buildEntity.getStart().equals(floor.getStateCode()+"")){
                                            buildEntity.setStart(floor.getStateCode()+"");
                                            up="1";
                                        }
                                    }else{
                                        buildEntity.setStart(floor.getStateCode()+"");
                                        up="1";
                                    }
                                }

                                //房产类型
                                if(floor.getPropertyType()!=null){
                                    if (!StringUtil.isEmpty(floor.getPropertyType().getValue())) {
                                        if(!StringUtil.isEmpty(buildEntity.getPropertyType())){
                                            if(!buildEntity.getPropertyType().equals(floor.getPropertyType().getValue())){
                                                buildEntity.setPropertyType(floor.getPropertyType().getValue());
                                                up="1";
                                            }
                                        }else{
                                            buildEntity.setPropertyType(floor.getPropertyType().getValue());
                                            up="1";
                                        }
                                    }
                                }
                                buildEntity.setGraded("4");
                                buildEntity.setTimeStamp(new Date());
                                if("1".equals(up)){
                                    temporaryTableUpdateRepository.updateBuild(buildEntity);
                                }
                            } else {
                                BuildingMappingTimeEntity buildInfo = new BuildingMappingTimeEntity();
                                buildInfo.setCurrentId(floor.getFloorId());
                                if(!StringUtil.isEmpty(floor.getFloorcode())){
                                    buildInfo.setCurrentNum(floor.getFloorcode());
                                }
                                if(!StringUtil.isEmpty(floor.getRecordFloorcode())){
                                    buildInfo.setRecordCurrentNum(floor.getRecordFloorcode());
                                }
                                if(!StringUtil.isEmpty(floor.getName())){
                                    buildInfo.setName(floor.getName());
                                }
                                if(!StringUtil.isEmpty(floor.getRecordFloorName())){
                                    buildInfo.setRecordName(floor.getRecordFloorName());
                                }
                                if(!StringUtil.isEmpty(floor.getUnitId())){
                                    buildInfo.setParentId(floor.getUnitId());
                                }
                                if(!StringUtil.isEmpty(floor.getUnitCode())){
                                    buildInfo.setParentNum(floor.getUnitCode());
                                }
                                if(!StringUtil.isEmpty(floor.getRecordUnitCode())){
                                    buildInfo.setRecordParentNum(floor.getRecordUnitCode());
                                }
                                if(floor.getPropertyType()!=null){
                                    if(!StringUtil.isEmpty(floor.getPropertyType().getValue())){
                                        buildInfo.setPropertyType(floor.getPropertyType().getValue());
                                    }
                                }
                                if(!StringUtil.isEmpty(floor.getStateCode()+"")){
                                    buildInfo.setStart(floor.getStateCode()+"");
                                }else{
                                    buildInfo.setStart("0");
                                }
                                buildInfo.setGraded("4");
                                buildInfo.setTimeStamp(new Date());
                                temporaryTableUpdateRepository.createBuild(buildInfo);
                            }
                        }
                    }

                    //更新房间数据
                    House[] houseList = responseBody.getHouseList();
                    if (houseList.length > 0) {
                        for (int i = 0; i < houseList.length; i++) {
                            House house = houseList[i];
                            HouseRoomEntity houseRoom = houseRoomRepository.getHouseByRoomId(house.getHouseId());
                            if (houseRoom != null) {
                                String up="0";
                                //使用面积（平方米）
                                if(!StringUtil.isEmpty(house.getUseArea()+"")){
                                    if(!StringUtil.isEmpty(houseRoom.getUseArea()+"")){
                                        if(!(houseRoom.getUseArea()+"").equals(house.getUseArea()+"")){
                                            houseRoom.setUseArea(house.getUseArea());
                                            up="1";
                                        }
                                    }else{
                                        houseRoom.setUseArea(house.getUseArea());
                                        up="1";
                                    }
                                }
                                //业态类型
                                if(!StringUtil.isEmpty(house.getBusinessType())){
                                    if(!StringUtil.isEmpty(houseRoom.getBusinessType())){
                                        if(!houseRoom.getBusinessType().equals(house.getBusinessType())){
                                            houseRoom.setBusinessType(house.getBusinessType());
                                            up="1";
                                        }
                                    }else{
                                        houseRoom.setBusinessType(house.getBusinessType());
                                        up="1";
                                    }
                                }
                                //实际入住日期
                                if (house.getActualCheckinDate() != null) {
                                    houseRoom.setActualCheckinDate(house.getActualCheckinDate().getTime());
                                }
                                //产权性质
                                if(house.getPropertyNature()!=null){
                                    if(!StringUtil.isEmpty(house.getPropertyNature().getValue())){
                                        if(!StringUtil.isEmpty(houseRoom.getPropertyNature())){
                                            if(!houseRoom.getPropertyNature().equals(house.getPropertyNature().getValue())){
                                                houseRoom.setPropertyNature(house.getPropertyNature().getValue());
                                                up="1";
                                            }
                                        }else{
                                            houseRoom.setPropertyNature(house.getPropertyNature().getValue());
                                            up="1";
                                        }
                                    }
                                }

                                //房产类型
                                if(house.getPropertyType()!=null){
                                    if(!StringUtil.isEmpty(house.getPropertyType().getValue())){
                                        if(!StringUtil.isEmpty(houseRoom.getPropertyType())){
                                            if(!houseRoom.getPropertyType().equals(house.getPropertyType().getValue())){
                                                houseRoom.setPropertyType(house.getPropertyType().getValue());
                                                up="1";
                                            }
                                        }else{
                                            houseRoom.setPropertyType(house.getPropertyType().getValue());
                                            up="1";
                                        }
                                    }
                                }
                                //使用性质
                                if(house.getUseNature()!=null){
                                    if(!StringUtil.isEmpty(house.getUseNature().getValue())){
                                        if(!StringUtil.isEmpty(houseRoom.getUseNature())){
                                            if(!houseRoom.getUseNature().equals(house.getUseNature().getValue())){
                                                houseRoom.setUseNature(house.getUseNature().getValue());
                                                up="1";
                                            }
                                        }else{
                                            houseRoom.setUseNature(house.getUseNature().getValue());
                                            up="1";
                                        }
                                    }
                                }
                                //房屋状态
                                if(house.getHouseStatus()!=null){
                                    if(!StringUtil.isEmpty(house.getHouseStatus().getValue())){
                                        if(!StringUtil.isEmpty(houseRoom.getHouseStatus())){
                                            if(!houseRoom.getHouseStatus().equals(house.getHouseStatus().getValue())){
                                                houseRoom.setHouseStatus(house.getHouseStatus().getValue());
                                                up="1";
                                            }
                                        }else{
                                            houseRoom.setHouseStatus(house.getHouseStatus().getValue());
                                            up="1";
                                        }
                                    }
                                }
                                //朝向
                                if(!StringUtil.isEmpty(house.getOrientation())){
                                    if(!StringUtil.isEmpty(houseRoom.getOrientation())){
                                        if(!houseRoom.getOrientation().equals(house.getOrientation())){
                                            houseRoom.setOrientation(house.getOrientation());
                                            up="1";
                                        }
                                    }else{
                                        houseRoom.setOrientation(house.getOrientation());
                                        up="1";
                                    }
                                }
                                //户型-室
                                if(!StringUtil.isEmpty(house.getBedroom()+"")){
                                    if(!StringUtil.isEmpty(houseRoom.getBedroom()+"")){
                                        if(!(houseRoom.getBedroom()+"").equals(house.getBedroom()+"")){
                                            houseRoom.setBedroom(house.getBedroom()+"");
                                            up="1";
                                        }
                                    }else{
                                        houseRoom.setBedroom(house.getBedroom()+"");
                                        up="1";
                                    }
                                }
                                //户型-厅
                                if(!StringUtil.isEmpty(house.getParlour()+"")){
                                    if(!StringUtil.isEmpty(houseRoom.getParlour()+"")){
                                        if(!(houseRoom.getParlour()+"").equals(house.getParlour()+"")){
                                            houseRoom.setParlour(house.getParlour()+"");
                                            up="1";
                                        }
                                    }else{
                                        houseRoom.setParlour(house.getParlour()+"");
                                        up="1";
                                    }
                                }
                                //户型-卫
                                if(!StringUtil.isEmpty(house.getToilet()+"")){
                                    if(!StringUtil.isEmpty(houseRoom.getToilet()+"")){
                                        if(!(houseRoom.getToilet()+"").equals(house.getToilet()+"")){
                                            houseRoom.setToilet(house.getToilet()+"");
                                            up="1";
                                        }
                                    }else{
                                        houseRoom.setToilet(house.getToilet()+"");
                                        up="1";
                                    }
                                }
                                //户型-厨
                                if(!StringUtil.isEmpty(house.getKitchen()+"")){
                                    if(!StringUtil.isEmpty(houseRoom.getKitchen()+"")){
                                        if(!(houseRoom.getKitchen()+"").equals(house.getKitchen()+"")){
                                            houseRoom.setKitchen(house.getKitchen()+"");
                                            up="1";
                                        }
                                    }else{
                                        houseRoom.setKitchen(house.getKitchen()+"");
                                        up="1";
                                    }
                                }
                                //户型-阳台
                                if(!StringUtil.isEmpty(house.getBalcony()+"")){
                                    if(!StringUtil.isEmpty(houseRoom.getBalcony()+"")){
                                        if(!(houseRoom.getBalcony()+"").equals(house.getBalcony()+"")){
                                            houseRoom.setBalcony(house.getBalcony()+"");
                                            up="1";
                                        }
                                    }else{
                                        houseRoom.setBalcony(house.getBalcony()+"");
                                        up="1";
                                    }
                                }
                                //建筑面积
                                if(!StringUtil.isEmpty(house.getStructureArea()+"")){
                                    if(!StringUtil.isEmpty(houseRoom.getStructureArea()+"")){
                                        if(!(houseRoom.getStructureArea()+"").equals(house.getStructureArea()+"")){
                                            houseRoom.setStructureArea(house.getStructureArea());
                                            up="1";
                                        }
                                    }else{
                                        houseRoom.setStructureArea(house.getStructureArea());
                                        up="1";
                                    }
                                }
                                //套内面积
                                if(!StringUtil.isEmpty(house.getInsideSpace()+"")){
                                    if(!StringUtil.isEmpty(houseRoom.getInsideSpace()+"")){
                                        if(!(houseRoom.getInsideSpace()+"").equals(house.getInsideSpace()+"")){
                                            houseRoom.setInsideSpace(house.getInsideSpace());
                                            up="1";
                                        }
                                    }else{
                                        houseRoom.setInsideSpace(house.getInsideSpace());
                                        up="1";
                                    }
                                }
                                //装修标准
                                if(house.getDecorationStandard()!=null){
                                    if(!StringUtil.isEmpty(house.getDecorationStandard().getValue())){
                                        if(!StringUtil.isEmpty(houseRoom.getDecorationStandard())){
                                            if(!houseRoom.getDecorationStandard().equals(house.getDecorationStandard().getValue())){
                                                houseRoom.setDecorationStandard(house.getDecorationStandard().getValue());
                                                up="1";
                                            }
                                        }else{
                                            houseRoom.setDecorationStandard(house.getDecorationStandard().getValue());
                                            up="1";
                                        }
                                    }
                                }
                                //预售房间编码
                                if(!StringUtil.isEmpty(house.getUnitHouseCode())){
                                    if(!StringUtil.isEmpty(houseRoom.getRoomNum())){
                                        if(!houseRoom.getRoomNum().equals(house.getUnitHouseCode())){
                                            houseRoom.setRoomNum(house.getUnitHouseCode());
                                            up="1";
                                        }
                                    }else{
                                        houseRoom.setRoomNum(house.getUnitHouseCode());
                                        up="1";
                                    }
                                }
                                //备案房间编码
                                if(!StringUtil.isEmpty(house.getRecordHouseCode())){
                                    if(!StringUtil.isEmpty(houseRoom.getRecordHouseCode())){
                                        if(!houseRoom.getRecordHouseCode().equals(house.getRecordHouseCode())){
                                            houseRoom.setRecordHouseCode(house.getRecordHouseCode());
                                            up="1";
                                        }
                                    }else{
                                        houseRoom.setRecordHouseCode(house.getRecordHouseCode());
                                        up="1";
                                    }
                                }
                                //关联楼层ID
                                if(!StringUtil.isEmpty(house.getFloorId())){
                                    if(!StringUtil.isEmpty(houseRoom.getFloorId())){
                                        if(!houseRoom.getFloorId().equals(house.getFloorId())){
                                            houseRoom.setFloorId(house.getFloorId());
                                            up="1";
                                        }
                                    }else{
                                        houseRoom.setFloorId(house.getFloorId());
                                        up="1";
                                    }
                                }
                                //关联预售楼层编码
                                if(!StringUtil.isEmpty(house.getFloorCode())){
                                    if(!StringUtil.isEmpty(houseRoom.getFloorNum())){
                                        if(!houseRoom.getFloorNum().equals(house.getFloorCode())){
                                            houseRoom.setFloorNum(house.getFloorCode());
                                            up="1";
                                        }
                                    }else{
                                        houseRoom.setFloorNum(house.getFloorCode());
                                        up="1";
                                    }
                                }
                                //关联备案楼层编码
                                if(!StringUtil.isEmpty(house.getRecordFloorCode())){
                                    if(!StringUtil.isEmpty(houseRoom.getRecordFloorCode())){
                                        if(!houseRoom.getRecordFloorCode().equals(house.getRecordFloorCode())){
                                            houseRoom.setRecordFloorCode(house.getRecordFloorCode());
                                            up="1";
                                        }
                                    }else{
                                        houseRoom.setRecordFloorCode(house.getRecordFloorCode());
                                        up="1";
                                    }
                                }
                                //预售房间号
                                if(!StringUtil.isEmpty(house.getHouseNum())){
                                    if(!StringUtil.isEmpty(houseRoom.getName())){
                                        if(!houseRoom.getName().equals(house.getHouseNum())){
                                            houseRoom.setName(house.getHouseNum());
                                            up="1";
                                        }
                                    }else{
                                        houseRoom.setName(house.getHouseNum());
                                        up="1";
                                    }
                                }
                                //备案房间号
                                if(!StringUtil.isEmpty(house.getRecordHouseNum())){
                                    if(!StringUtil.isEmpty(houseRoom.getRecordHouseNum())){
                                        if(!houseRoom.getRecordHouseNum().equals(house.getRecordHouseNum())){
                                            houseRoom.setRecordHouseNum(house.getRecordHouseNum());
                                            up="1";
                                        }
                                    }else{
                                        houseRoom.setRecordHouseNum(house.getRecordHouseNum());
                                        up="1";
                                    }
                                }
                                //预售房间名称
                                if(!StringUtil.isEmpty(house.getHouseName())){
                                    if(!StringUtil.isEmpty(houseRoom.getHouseName())){
                                        if(!houseRoom.getHouseName().equals(house.getHouseName())){
                                            houseRoom.setHouseName(house.getHouseName());
                                            up="1";
                                        }
                                    }else{
                                        houseRoom.setHouseName(house.getHouseName());
                                        up="1";
                                    }
                                }
                                //备案房间名称
                                if(!StringUtil.isEmpty(house.getRecordHouseName())){
                                    if(!StringUtil.isEmpty(houseRoom.getRecordHouseName())){
                                        if(!houseRoom.getRecordHouseName().equals(house.getRecordHouseName())){
                                            houseRoom.setRecordHouseName(house.getRecordHouseName());
                                            up="1";
                                        }
                                    }else{
                                        houseRoom.setRecordHouseName(house.getRecordHouseName());
                                        up="1";
                                    }
                                }
                                if (house.getModifiedOn() != null) {
                                    houseRoom.setCreateOn(house.getModifiedOn().getTime());
                                }
                                //状态0:有效 1:失效
                                if(!StringUtil.isEmpty(house.getStateCode()+"")){
                                    if(!StringUtil.isEmpty(houseRoom.getStateCode())){
                                        if(!houseRoom.getStateCode().equals(house.getStateCode()+"")){
                                            houseRoom.setStateCode(house.getStateCode()+"");
                                            up="1";
                                        }
                                    }else{
                                        houseRoom.setStateCode(house.getStateCode()+"");
                                        up="1";
                                    }
                                }
                                if("1".equals(up)){
                                    houseRoomRepository.updateHouseInfo(houseRoom);
                                }
                            } else {
                                HouseRoomEntity houseRoomEntity = new HouseRoomEntity();
                                houseRoomEntity.setId(house.getHouseId());

                                if(!StringUtil.isEmpty(house.getUseArea()+"")){
                                    houseRoomEntity.setUseArea(house.getUseArea());   //使用面积（平方米）
                                }
                                if(!StringUtil.isEmpty(house.getBusinessType())){
                                    houseRoomEntity.setBusinessType(house.getBusinessType()); //业态类型
                                }
                                if(house.getActualCheckinDate()!=null){
                                    if(house.getActualCheckinDate().getTime()!=null){
                                        houseRoomEntity.setActualCheckinDate(house.getActualCheckinDate().getTime());//实际入住日期
                                    }
                                }
                                if(house.getPropertyNature()!=null){
                                    if(!StringUtil.isEmpty(house.getPropertyNature().getValue())){
                                        houseRoomEntity.setPropertyNature(house.getPropertyNature().getValue());//产权性质
                                    }
                                }
                                if(house.getPropertyType()!=null){
                                    if(!StringUtil.isEmpty(house.getPropertyType().getValue())){
                                        houseRoomEntity.setPropertyType(house.getPropertyType().getValue());//房产类型
                                    }
                                }
                                if(house.getUseNature()!=null){
                                    if(!StringUtil.isEmpty(house.getUseNature().getValue())){
                                        houseRoomEntity.setUseNature(house.getUseNature().getValue());//使用性质
                                    }
                                }
                                if(house.getHouseStatus()!=null){
                                    if(!StringUtil.isEmpty(house.getHouseStatus().getValue())){
                                        houseRoomEntity.setHouseStatus(house.getHouseStatus().getValue());//房屋状态
                                    }
                                }
                                if(!StringUtil.isEmpty(house.getOrientation())){
                                    houseRoomEntity.setOrientation(house.getOrientation());//朝向
                                }
                                if(!StringUtil.isEmpty(house.getBedroom()+"")){
                                    houseRoomEntity.setBedroom(house.getBedroom()+""); //户型-室
                                }
                                if(!StringUtil.isEmpty(house.getParlour()+"")){
                                    houseRoomEntity.setParlour(house.getParlour()+"");//户型-厅
                                }
                                if(!StringUtil.isEmpty(house.getToilet()+"")){
                                    houseRoomEntity.setToilet(house.getToilet()+"");//户型-卫
                                }
                                if(!StringUtil.isEmpty(house.getKitchen()+"")){
                                    houseRoomEntity.setKitchen(house.getKitchen()+""); //户型-厨
                                }
                                if(!StringUtil.isEmpty(house.getBalcony()+"")){
                                    houseRoomEntity.setBalcony(house.getBalcony()+"");//户型-阳台
                                }
                                if(!StringUtil.isEmpty(house.getStructureArea()+"")){
                                    houseRoomEntity.setStructureArea(house.getStructureArea());//建筑面积
                                }
                                if(!StringUtil.isEmpty(house.getInsideSpace()+"")){
                                    houseRoomEntity.setInsideSpace(house.getInsideSpace());//套内面积
                                }
                                if(house.getDecorationStandard()!=null){
                                    if(!StringUtil.isEmpty(house.getDecorationStandard().getValue())){
                                        houseRoomEntity.setDecorationStandard(house.getDecorationStandard().getValue());//装修标准
                                    }
                                }
                                if(!StringUtil.isEmpty(house.getUnitHouseCode())){
                                    houseRoomEntity.setRoomNum(house.getUnitHouseCode());//预售房间编码
                                }
                                if(!StringUtil.isEmpty(house.getRecordHouseCode())){
                                    houseRoomEntity.setRecordHouseCode(house.getRecordHouseCode());//备案房间编码
                                }
                                if(!StringUtil.isEmpty(house.getFloorId())){
                                    houseRoomEntity.setFloorId(house.getFloorId());//关联楼层ID
                                }
                                if(!StringUtil.isEmpty(house.getFloorCode())){
                                    houseRoomEntity.setFloorNum(house.getFloorCode());//关联预售楼层编码
                                }
                                if(!StringUtil.isEmpty(house.getRecordFloorCode())){
                                    houseRoomEntity.setRecordFloorCode(house.getRecordFloorCode());//关联备案楼层编码
                                }
                                if(!StringUtil.isEmpty(house.getHouseNum())){
                                    houseRoomEntity.setName(house.getHouseNum());//预售房间号
                                }
                                if(!StringUtil.isEmpty(house.getRecordHouseNum())){
                                    houseRoomEntity.setRecordHouseNum(house.getRecordHouseNum());//备案房间号
                                }
                                if(!StringUtil.isEmpty(house.getHouseName())){
                                    houseRoomEntity.setHouseName(house.getHouseName()); //预售房间名称
                                }
                                if(!StringUtil.isEmpty(house.getRecordHouseName())){
                                    houseRoomEntity.setRecordHouseName(house.getRecordHouseName()); //备案房间名称
                                }
                                if(house.getModifiedOn()!=null){
                                    if(house.getModifiedOn().getTime()!=null){
                                        houseRoomEntity.setCreateOn(house.getModifiedOn().getTime());
                                    }
                                }
                                if(!StringUtil.isEmpty(house.getStateCode()+"")){
                                    houseRoomEntity.setStateCode(house.getStateCode()+"");
                                }else{
                                    houseRoomEntity.setStateCode("0");
                                }
                                houseRoomRepository.create(houseRoomEntity);
                            }
                            HouseInfoEntity houseInfo = houseInfoRepository.get(house.getHouseId());
                            if (houseInfo != null) {
                                String up="0";
                                //预售楼栋编码
                                if (!StringUtil.isEmpty(house.getBlockBuildingCode())) {
                                    if(!StringUtil.isEmpty(houseInfo.getBuildingNum())){
                                        if(!houseInfo.getBuildingNum().equals(house.getBlockBuildingCode())){
                                            houseInfo.setBuildingNum(house.getBlockBuildingCode());
                                            up="1";
                                        }
                                    }else{
                                        houseInfo.setBuildingNum(house.getBlockBuildingCode());
                                        up="1";
                                    }
                                }
                                //备案楼栋编码
                                if (!StringUtil.isEmpty(house.getRecordBuildingCode())) {
                                    if(!StringUtil.isEmpty(houseInfo.getRecordbuildingNum())){
                                        if(!houseInfo.getRecordbuildingNum().equals(house.getRecordBuildingCode())){
                                            houseInfo.setRecordbuildingNum(house.getRecordBuildingCode());
                                            up="1";
                                        }
                                    }else{
                                        houseInfo.setRecordbuildingNum(house.getRecordBuildingCode());
                                        up="1";
                                    }
                                }
                                if (!StringUtil.isEmpty(house.getDecorateStandardIdName())) {
                                    if(!StringUtil.isEmpty(houseInfo.getDecorationStandard())){
                                        if(!houseInfo.getDecorationStandard().equals(house.getDecorateStandardIdName())){
                                            houseInfo.setDecorationStandard(house.getDecorateStandardIdName());
                                            up="1";
                                        }
                                    }else{
                                        houseInfo.setDecorationStandard(house.getDecorateStandardIdName());
                                        up="1";
                                    }
                                }
                                if (!StringUtil.isEmpty(house.getFloorId())) {
                                    if(!StringUtil.isEmpty(houseInfo.getFloorId())){
                                        if(!houseInfo.getFloorId().equals(house.getFloorId())){
                                            houseInfo.setFloorId(house.getFloorId());
                                            up="1";
                                        }
                                    }else{
                                        houseInfo.setFloorId(house.getFloorId());
                                        up="1";
                                    }
                                }
                                //预售楼层编码
                                if (!StringUtil.isEmpty(house.getFloorCode())) {
                                    if(!StringUtil.isEmpty(houseInfo.getFloorNum())){
                                        if(!houseInfo.getFloorNum().equals(house.getFloorCode())){
                                            houseInfo.setFloorNum(house.getFloorCode());
                                            up="1";
                                        }
                                    }else{
                                        houseInfo.setFloorNum(house.getFloorCode());
                                        up="1";
                                    }
                                }
                                //备案楼层编码
                                if (!StringUtil.isEmpty(house.getRecordFloorCode())) {
                                    if(!StringUtil.isEmpty(houseInfo.getRecordFloorNum())){
                                        if(!houseInfo.getRecordFloorNum().equals(house.getRecordFloorCode())){
                                            houseInfo.setRecordFloorNum(house.getRecordFloorCode());
                                            up="1";
                                        }
                                    }else{
                                        houseInfo.setRecordFloorNum(house.getRecordFloorCode());
                                        up="1";
                                    }
                                }
                                //预售房间号码
                                if (!StringUtil.isEmpty(house.getHouseNum())) {
                                    if(!StringUtil.isEmpty(houseInfo.getRoomName())){
                                        if(!houseInfo.getRoomName().equals(house.getHouseNum())){
                                            houseInfo.setRoomName(house.getHouseNum());
                                            up="1";
                                        }
                                    }else{
                                        houseInfo.setRoomName(house.getHouseNum());
                                        up="1";
                                    }
                                }
                                //预售房间号码
                                if (!StringUtil.isEmpty(house.getHouseNum())) {
                                    if(!StringUtil.isEmpty(houseInfo.getRoomName())){
                                        if(!houseInfo.getRoomName().equals(house.getHouseNum())){
                                            houseInfo.setRoomName(house.getHouseNum());
                                            up="1";
                                        }
                                    }else{
                                        houseInfo.setRoomName(house.getHouseNum());
                                        up="1";
                                    }
                                }
                                //备案房间号码
                                if (!StringUtil.isEmpty(house.getRecordHouseNum())) {
                                    if(!StringUtil.isEmpty(houseInfo.getRecordRoomName())){
                                        if(!houseInfo.getRecordRoomName().equals(house.getRecordHouseNum())){
                                            houseInfo.setRecordRoomName(house.getRecordHouseNum());
                                            up="1";
                                        }
                                    }else{
                                        houseInfo.setRecordRoomName(house.getRecordHouseNum());
                                        up="1";
                                    }
                                }
                                //楼栋id
                                if (!StringUtil.isEmpty(house.getHousingResourcesId())){
                                    if(!StringUtil.isEmpty(houseInfo.getBuildingId())){
                                        if(!houseInfo.getBuildingId().equals(house.getHousingResourcesId())){
                                            houseInfo.setBuildingId(house.getHousingResourcesId());
                                            up="1";
                                        }
                                    }else{
                                        houseInfo.setBuildingId(house.getHousingResourcesId());
                                        up="1";
                                    }
                                }
                                //项目编码
                                if (!StringUtil.isEmpty(house.getProjectCode())) {
                                    if(!StringUtil.isEmpty(houseInfo.getProjectNum())){
                                        if(!houseInfo.getProjectNum().equals(house.getProjectCode())){
                                            houseInfo.setProjectNum(house.getProjectCode());
                                            up="1";
                                        }
                                    }else{
                                        houseInfo.setProjectNum(house.getProjectCode());
                                        up="1";
                                    }
                                }
                                //项目id
                                if (!StringUtil.isEmpty((house.getProjectId()))) {
                                    if(!StringUtil.isEmpty(houseInfo.getProjectId())){
                                        if(!houseInfo.getProjectId().equals(house.getProjectId())){
                                            houseInfo.setProjectId(house.getProjectId());
                                            up="1";
                                        }
                                    }else{
                                        houseInfo.setProjectId(house.getProjectId());
                                        up="1";
                                    }
                                }
                                //业态名称
                                if (!StringUtil.isEmpty(house.getRoomType())) {
                                    if(!StringUtil.isEmpty(houseInfo.getFormatName())){
                                        if(!houseInfo.getFormatName().equals(house.getRoomType())){
                                            houseInfo.setFormatName(house.getRoomType());
                                            up="1";
                                        }
                                    }else{
                                        houseInfo.setFormatName(house.getRoomType());
                                        up="1";
                                    }
                                }
                                //预售房间编码
                                if (!StringUtil.isEmpty(house.getUnitHouseCode())) {
                                    if(!StringUtil.isEmpty(houseInfo.getRoomNum())){
                                        if(!houseInfo.getRoomNum().equals(house.getUnitHouseCode())){
                                            houseInfo.setRoomNum(house.getUnitHouseCode());
                                            up="1";
                                        }
                                    }else{
                                        houseInfo.setRoomNum(house.getUnitHouseCode());
                                        up="1";
                                    }
                                }
                                //备案房间编码
                                if (!StringUtil.isEmpty(house.getRecordHouseCode())) {
                                    if(!StringUtil.isEmpty(houseInfo.getRecordRoomNum())){
                                        if(!houseInfo.getRecordRoomNum().equals(house.getRecordHouseCode())){
                                            houseInfo.setRecordRoomNum(house.getRecordHouseCode());
                                            up="1";
                                        }
                                    }else{
                                        houseInfo.setRecordRoomNum(house.getRecordHouseCode());
                                        up="1";
                                    }
                                }
                                //户型
                                if (!StringUtil.isEmpty(house.getUnitSetId())) {
                                    if(!StringUtil.isEmpty(houseInfo.getHouseType())){
                                        if(!houseInfo.getHouseType().equals(house.getUnitSetId())){
                                            houseInfo.setHouseType(house.getUnitSetId());
                                            up="1";
                                        }
                                    }else{
                                        houseInfo.setHouseType(house.getUnitSetId());
                                        up="1";
                                    }
                                }
                                //预售房间地址
                                if (!StringUtil.isEmpty(house.getHouseName())) {
                                    if(!StringUtil.isEmpty(houseInfo.getAddress())){
                                        if(!houseInfo.getAddress().equals(house.getHouseName())){
                                            houseInfo.setAddress(house.getHouseName());
                                            up="1";
                                        }
                                    }else{
                                        houseInfo.setAddress(house.getHouseName());
                                        up="1";
                                    }
                                }
                                //备案房间地址
                                if (!StringUtil.isEmpty(house.getRecordHouseName())) {
                                    if(!StringUtil.isEmpty(houseInfo.getRecordAddress())){
                                        if(!houseInfo.getRecordAddress().equals(house.getRecordHouseName())){
                                            houseInfo.setRecordAddress(house.getRecordHouseName());
                                            up="1";
                                        }
                                    }else{
                                        houseInfo.setRecordAddress(house.getRecordHouseName());
                                        up="1";
                                    }
                                }
                                if("1".equals(up)){
                                    houseInfo.setModifyOn(new Date());
                                    houseInfoRepository.updateHouseInfo(houseInfo);
                                }

                            } else {
                                HouseInfoEntity houseInfoEntity = new HouseInfoEntity();
                                houseInfoEntity.setId(house.getHouseId());//房间id
                                houseInfoEntity.setRoomId(house.getHouseId());//房间id
                                //预售楼栋编码
                                if (!StringUtil.isEmpty(house.getBlockBuildingCode())) {
                                    houseInfoEntity.setBuildingNum(house.getBlockBuildingCode());
                                }
                                //备案楼栋编码
                                if(!StringUtil.isEmpty(house.getRecordBuildingCode())){
                                    houseInfoEntity.setRecordbuildingNum(house.getRecordBuildingCode());
                                }
                                if (!StringUtil.isEmpty(house.getDecorateStandardIdName())) {
                                    houseInfoEntity.setDecorationStandard(house.getDecorateStandardIdName());
                                }
                                //楼层id
                                if (!StringUtil.isEmpty(house.getFloorId())) {
                                    houseInfoEntity.setFloorId(house.getFloorId());
                                }
                                //预售楼层编码
                                if (!StringUtil.isEmpty(house.getFloorCode())) {
                                    houseInfoEntity.setFloorNum(house.getFloorCode());
                                }
                                //备案楼层编码
                                if (!StringUtil.isEmpty(house.getRecordFloorCode())) {
                                    houseInfoEntity.setRecordFloorNum(house.getRecordFloorCode());
                                }
                                //预售房间号码
                                if (!StringUtil.isEmpty(house.getHouseNum())) {
                                    houseInfoEntity.setRoomName(house.getHouseNum());
                                }
                                //备案房间号码
                                if (!StringUtil.isEmpty(house.getRecordHouseNum())) {
                                    houseInfoEntity.setRecordRoomNum(house.getRecordHouseNum());
                                }
                                //楼栋id
                                if (!StringUtil.isEmpty(house.getHousingResourcesId())) {
                                    houseInfoEntity.setBuildingId(house.getHousingResourcesId());
                                }
                                // 项目编码
                                if (!StringUtil.isEmpty(house.getProjectCode())) {
                                    houseInfoEntity.setProjectNum(house.getProjectCode());
                                }
                                //项目id
                                if (!StringUtil.isEmpty((house.getProjectId()))) {
                                    houseInfoEntity.setProjectId(house.getProjectId());
                                }
                                if (!StringUtil.isEmpty(house.getRoomType())) {
                                    houseInfoEntity.setFormatName(house.getRoomType());
                                }
                                //预售房间编码
                                if (!StringUtil.isEmpty(house.getUnitHouseCode())) {
                                    houseInfoEntity.setRoomNum(house.getUnitHouseCode());
                                }
                                //备案房间编码
                                if (!StringUtil.isEmpty(house.getRecordHouseCode())) {
                                    houseInfoEntity.setRecordRoomNum(house.getRecordHouseCode());
                                }
                                //预售房间地址
                                if (!StringUtil.isEmpty(house.getHouseName())) {
                                    houseInfoEntity.setAddress(house.getHouseName());
                                }
                                //备案房间地址
                                if (!StringUtil.isEmpty(house.getRecordHouseName())) {
                                    houseInfoEntity.setRecordAddress(house.getRecordHouseName());
                                }
                                if (!StringUtil.isEmpty(house.getUnitSetId())) {
                                    houseInfoEntity.setHouseType(house.getUnitSetId());
                                }
                                if (house.getModifiedOn() != null) {
                                    houseInfoEntity.setCreateOn(house.getModifiedOn().getTime());
                                }
                                houseInfoEntity.setModifyOn(new Date());
                                houseInfoEntity.setState("1");
                                houseInfoRepository.create(houseInfoEntity);
                            }

                            //更新房屋信息临时表 作对比 无更新则跳过  更新5级别 房间
                            BuildingMappingTimeEntity buildRoomEntity = temporaryTableUpdateRepository.queryBuild(house.getHouseId());
                            if (buildRoomEntity != null) {
                                String up="0";
                                //预售房间编码
                                if (!StringUtil.isEmpty(house.getUnitHouseCode())) {
                                    if(!house.getUnitHouseCode().equals(buildRoomEntity.getCurrentNum())){
                                        buildRoomEntity.setCurrentNum(house.getUnitHouseCode());
                                        up="1";
                                    }
                                }
                                //备案房间编码
                                if (!StringUtil.isEmpty(house.getRecordHouseCode())) {
                                    if(!StringUtil.isEmpty(buildRoomEntity.getRecordCurrentNum())){
                                        if(!buildRoomEntity.getRecordCurrentNum().equals(house.getRecordHouseCode())){
                                            buildRoomEntity.setRecordCurrentNum(house.getRecordHouseCode());
                                            up="1";
                                        }
                                    }else{
                                        buildRoomEntity.setRecordCurrentNum(house.getRecordHouseCode());
                                        up="1";
                                    }
                                }
                                //楼层id
                                if (!StringUtil.isEmpty(house.getFloorId())) {
                                    if(!house.getFloorId().equals(buildRoomEntity.getParentId())){
                                        buildRoomEntity.setParentId(house.getFloorId());
                                        up="1";
                                    }
                                }
                                //预售楼层编码
                                if (!StringUtil.isEmpty(house.getFloorCode())) {
                                    if(!house.getFloorCode().equals(buildRoomEntity.getParentNum())){
                                        buildRoomEntity.setParentNum(house.getFloorCode());
                                        up="1";
                                    }
                                }
                                //备案楼层编码
                                if (!StringUtil.isEmpty(house.getRecordFloorCode())) {
                                    if(!StringUtil.isEmpty(buildRoomEntity.getRecordParentNum())){
                                        if(!buildRoomEntity.getRecordParentNum().equals(house.getRecordFloorCode())){
                                            buildRoomEntity.setRecordParentNum(house.getRecordFloorCode());
                                            up="1";
                                        }
                                    }else{
                                        buildRoomEntity.setRecordParentNum(house.getRecordFloorCode());
                                        up="1";
                                    }
                                }
                                if (!StringUtil.isEmpty(house.getHouseNum())) {
                                    if(!house.getHouseNum().equals(buildRoomEntity.getName())){
                                        buildRoomEntity.setName(house.getHouseNum());
                                        up="1";
                                    }
                                }
                                //备案房间号
                                if (!StringUtil.isEmpty(house.getRecordHouseNum())) {
                                    if(!StringUtil.isEmpty(buildRoomEntity.getRecordName())){
                                        if(!buildRoomEntity.getRecordName().equals(house.getRecordHouseNum())){
                                            buildRoomEntity.setRecordName(house.getRecordHouseNum());
                                            up="1";
                                        }
                                    }else{
                                        buildRoomEntity.setRecordName(house.getRecordHouseNum());
                                        up="1";
                                    }
                                }
                                //预售房间地址
                                if (!StringUtil.isEmpty(house.getHouseName())) {
                                    if(!StringUtil.isEmpty(buildRoomEntity.getAddress())){
                                        if(!buildRoomEntity.getAddress().equals(house.getHouseName())){
                                            buildRoomEntity.setAddress(house.getHouseName());
                                            up="1";
                                        }
                                    }else{
                                        buildRoomEntity.setAddress(house.getHouseName());
                                        up="1";
                                    }
                                }
                                //备案房间地址
                                if (!StringUtil.isEmpty(house.getRecordHouseName())) {
                                    if(!StringUtil.isEmpty(buildRoomEntity.getRecordAddress())){
                                        if(!buildRoomEntity.getRecordAddress().equals(house.getRecordHouseName())){
                                            buildRoomEntity.setRecordAddress(house.getRecordHouseName());
                                            up="1";
                                        }
                                    }else{
                                        buildRoomEntity.setRecordAddress(house.getRecordHouseName());
                                        up="1";
                                    }
                                }
                                //状态0:有效 1:失效
                                if(!StringUtil.isEmpty(house.getStateCode()+"")){
                                    if(!StringUtil.isEmpty(buildRoomEntity.getStart())){
                                        if(!buildRoomEntity.getStart().equals(house.getStateCode()+"")){
                                            buildRoomEntity.setStart(house.getStateCode()+"");
                                            up="1";
                                        }
                                    }else{
                                        buildRoomEntity.setStart(house.getStateCode()+"");
                                        up="1";
                                    }
                                }

                                //房产类型
                                if(house.getPropertyType()!=null){
                                    if (!StringUtil.isEmpty(house.getPropertyType().getValue())) {
                                        if(!StringUtil.isEmpty(buildRoomEntity.getPropertyType())){
                                            if(!buildRoomEntity.getPropertyType().equals(house.getPropertyType().getValue())){
                                                buildRoomEntity.setPropertyType(house.getPropertyType().getValue());
                                                up="1";
                                            }
                                        }else{
                                            buildRoomEntity.setPropertyType(house.getPropertyType().getValue());
                                            up="1";
                                        }
                                    }
                                }

                                buildRoomEntity.setGraded("5");
                                buildRoomEntity.setTimeStamp(new Date());
                                if("1".equals(up)){
                                    temporaryTableUpdateRepository.updateBuild(buildRoomEntity);
                                }
                            } else {
                                BuildingMappingTimeEntity buildInfo = new BuildingMappingTimeEntity();
                                buildInfo.setCurrentId(house.getHouseId());
                                if(!StringUtil.isEmpty(house.getUnitHouseCode())){
                                    buildInfo.setCurrentNum(house.getUnitHouseCode());   //预售房间编码
                                }

                                if(!StringUtil.isEmpty(house.getRecordHouseCode())){
                                    buildInfo.setRecordCurrentNum(house.getRecordHouseCode());    //备案房间编码
                                }

                                if(!StringUtil.isEmpty(house.getFloorId())){
                                    buildInfo.setParentId(house.getFloorId());
                                }
                                if(!StringUtil.isEmpty(house.getFloorCode())){
                                    buildInfo.setParentNum(house.getFloorCode());  //预售楼层编码
                                }

                                if(!StringUtil.isEmpty(house.getRecordFloorCode())){
                                    buildInfo.setRecordParentNum(house.getRecordFloorCode());   //备案楼层编码
                                }

                                if(!StringUtil.isEmpty(house.getHouseNum())){
                                    buildInfo.setName(house.getHouseNum());
                                }

                                if(!StringUtil.isEmpty(house.getRecordHouseNum())){
                                    buildInfo.setRecordName(house.getRecordHouseNum());//备案房间号
                                }

                                if(!StringUtil.isEmpty(house.getHouseName())){
                                    buildInfo.setAddress(house.getHouseName());    //预售房间地址
                                }

                                if(!StringUtil.isEmpty(house.getRecordHouseName())){
                                    buildInfo.setRecordAddress(house.getRecordHouseName()); //备案房间地址
                                }
                                if(!StringUtil.isEmpty(house.getStateCode()+"")){
                                    buildInfo.setStart(house.getStateCode()+"");//状态0:有效 1:失效
                                }else{
                                    buildInfo.setStart("0");//状态0:有效 1:失效
                                }
                                buildInfo.setGraded("5");
                                buildInfo.setTimeStamp(new Date());
                                temporaryTableUpdateRepository.createBuild(buildInfo);
                            }
                        }
                    }
                }
            }
            //查询成功或失败
            if ("1".equals(response.getHeader().getStatus())) {
                //调用日志
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("查询房屋信息接口！");
                interfaceLogEntity.setCode("200");
                interfaceLogEntity.setEntityName("house_houseInfo");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogEntity.setMessage(response.getHeader().getErrorMessage());
                interfaceLogRepository.create(interfaceLogEntity);
            } else {
                //调用日志
                InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
                interfaceLogEntity.setId(IdGen.uuid());
                interfaceLogEntity.setInterfaceName("查询房屋信息接口！");
                interfaceLogEntity.setCode("500");
                interfaceLogEntity.setEntityName("house_houseInfo");
                interfaceLogEntity.setErrorDate(new Date());
                interfaceLogEntity.setMessage(response.getHeader().getErrorMessage());
                interfaceLogRepository.create(interfaceLogEntity);
            }
            result = "200";
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result = "500";
            //调用日志
            InterfaceLogEntity interfaceLogEntity = new InterfaceLogEntity();
            interfaceLogEntity.setId(IdGen.uuid());
            interfaceLogEntity.setInterfaceName("查询房屋信息接口！");
            interfaceLogEntity.setCode("500");
            interfaceLogEntity.setEntityName("house_houseInfo");
            interfaceLogEntity.setErrorDate(new Date());
            //interfaceLogEntity.setMessage(response.getHeader().getErrorMessage());
            interfaceLogRepository.create(interfaceLogEntity);
            return result;
        }
    }
}
