package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.ExportExcelDoorLogDTO;
import com.maxrocky.vesta.application.DTO.PropertyDoorDTO;
import com.maxrocky.vesta.application.DTO.PropertyDoorLogDTO;
import com.maxrocky.vesta.application.DTO.PropertyVisitorDTO;
import com.maxrocky.vesta.application.dto.adminDTO.RoleRolesetDTO;
import com.maxrocky.vesta.application.inf.HouseInfoService;
import com.maxrocky.vesta.application.inf.PropertyDoorService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.HouseInfoRepository;
import com.maxrocky.vesta.domain.repository.PropertyDoorRepository;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportExcel;
import com.maxrocky.vesta.utility.ExportUtil;
import com.maxrocky.vesta.utility.IdGen;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * 物业门禁管理-Service接口实现类
 * Created by WeiYangDong on 2016/11/1.
 */
@Service
public class PropertyDoorServiceImpl implements PropertyDoorService {
    @Autowired
    private PropertyDoorRepository propertyDoorRepository;
    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;

    /**
     * 获取物业访客列表 WeiYangDong_2016-11-01
     */
    public List<PropertyVisitorEntity> getPropertyVisitorList(PropertyVisitorDTO propertyVisitorDTO,WebPage webPage){
        Map<String,Object> params = new HashMap<>();
        params.put("projectCode", propertyVisitorDTO.getProjectCodeDTO());
        params.put("address",propertyVisitorDTO.getAddressDTO());
        params.put("ownerName",propertyVisitorDTO.getOwnerNameDTO());
        params.put("visitorName",propertyVisitorDTO.getVisitorNameDTO());
        params.put("searchDateSta",propertyVisitorDTO.getSearchDateSta());
        params.put("searchDateEnd",propertyVisitorDTO.getSearchDateEnd());
        //执行检索
        List<PropertyVisitorEntity> propertyVisitorList = propertyDoorRepository.getPropertyVisitorList(params, webPage);

        return propertyVisitorList;
    }

    /**
     * 获取物业门禁列表 WeiYangDong_2016-11-08
     */
    public List<PropertyDoorEntity> getPropertyDoorList(PropertyDoorDTO propertyDoorDTO,WebPage webPage){
        //封装检索条件
        Map<String,Object> params = new HashMap<>();
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = propertyDoorDTO.getRoleScopeList();
        for (Map<String, Object> roleScope : roleScopeList) {
            String scopeType = (String) roleScope.get("scopeType");
            if (scopeType.equals("1")) {
                //城市级别(分公司)
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(roleScope.get("scopeId").toString());
                for (Object[] project : projectList) {
                    if (!roleScopes.contains(project[0].toString())) {
                        roleScopes += "'" + project[0].toString() + "',";
                    }
                }
            } else if (scopeType.equals("2")) {
                //项目级别
                if (!roleScopes.contains(roleScope.get("scopeId").toString())) {
                    roleScopes += "'" + roleScope.get("scopeId") + "',";
                }
            } else if (scopeType.equals("0")) {
                //全部城市
                roleScopes += "all,";
            }
        }
        params.put("roleScopes",roleScopes);        //权限范围
        if (null != propertyDoorDTO.getScopeId() && !"".equals(propertyDoorDTO.getScopeId())){
            List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(propertyDoorDTO.getScopeId());
            String cityProjects = "";
            for (Object[] project : projectList) {
                if (!cityProjects.contains(project[0].toString())) {
                    cityProjects += "'" + project[0].toString() + "',";
                }
            }
            params.put("cityProjects",cityProjects);                    //城市下所有项目,以城市为单位检索
        }
        params.put("projectCode",propertyDoorDTO.getProjectCode());     //项目编码
        params.put("blockCode",propertyDoorDTO.getBlockCode());         //地块编码
        params.put("buildingNum",propertyDoorDTO.getBuildingNum());     //楼栋编码
        params.put("unitCode",propertyDoorDTO.getUnitCode());           //单元编码
        params.put("floorCode",propertyDoorDTO.getFloorCode());         //楼层编码
        params.put("macAddress",propertyDoorDTO.getMacAddress());       //设备Mac地址
        params.put("deviceType",propertyDoorDTO.getDeviceType());       //设备Mac地址
        //执行检索
        List<PropertyDoorEntity> propertyDoorList = propertyDoorRepository.getPropertyDoorList(params, webPage);
        return propertyDoorList;
    }

    /**
     * 获取用户门禁关系的用户列表 WeiYangDong_2016-11-11
     */
    public List<Map<String,Object>> getPropertyUserDoorList(PropertyDoorDTO propertyDoorDTO,WebPage webPage){
        //封装检索条件
        Map<String,Object> params = new HashMap<>();
        params.put("name",propertyDoorDTO.getNameDTO());
        params.put("mobile",propertyDoorDTO.getMobileDTO());
        params.put("address",propertyDoorDTO.getAddressDTO());
        params.put("doorId",propertyDoorDTO.getDoorId());
        params.put("scopeId",propertyDoorDTO.getScopeId());
        params.put("projectCode",propertyDoorDTO.getProjectCode());
        //执行检索
        List<Map<String, Object>> propertyUserDoorList = propertyDoorRepository.getPropertyUserDoorList(params, webPage);
        return propertyUserDoorList;
    }

    /**
     * 获取用户列表 WeiYangDong_2016-11-14
     */
    public List<Map<String,Object>> getUserList(PropertyDoorDTO propertyDoorDTO,WebPage webPage){
        //封装检索条件
        Map<String,Object> params = new HashMap<>();
        params.put("name",propertyDoorDTO.getNameDTO());
        params.put("mobile",propertyDoorDTO.getMobileDTO());
        params.put("address",propertyDoorDTO.getAddressDTO());
        params.put("userType",propertyDoorDTO.getUserTypeDTO());
        params.put("projectCode",propertyDoorDTO.getProjectCode());
        //执行检索
        List<Map<String, Object>> propertyUserDoorList = propertyDoorRepository.getUserList(params, webPage);
        return propertyUserDoorList;
    }

    /**
     * 获取用户详情 WeiYangDong_2016-11-15
     */
    public Map<String,Object> getUserInfo(PropertyDoorDTO propertyDoorDTO){
        //封装检索条件
        Map<String,Object> params = new HashMap<>();
        params.put("userId",propertyDoorDTO.getUserId());
        //执行查询
        List<Map<String, Object>> userInfoList = propertyDoorRepository.getUserInfo(params);
        Map<String,Object> userInfo = null;
        if (!userInfoList.isEmpty() && userInfoList.size() > 0){
            userInfo = userInfoList.get(0);
        }
        return userInfo;
    }

    /**
     * 获取用户门禁关系列表 WeiYangDong_2016-11-15
     */
    public List<PropertyUserDoorMapEntity> getUserDoorMapList(PropertyDoorDTO propertyDoorDTO){
        Map<String,Object> params = new HashMap<>();
        params.put("userId",propertyDoorDTO.getUserId());
        params.put("doorId",propertyDoorDTO.getDoorId());//通过UserId和DoorId检索用户门禁关系信息
        List<PropertyUserDoorMapEntity> userDoorMapList = propertyDoorRepository.getUserDoorMapList(params);
        return userDoorMapList;
    }

    /**
     * 分配/取消分配用户门禁权限 WeiYangDong_2016-11-11
     */
    public void assignUserDoor(PropertyDoorDTO propertyDoorDTO){
        Map<String,Object> params = new HashMap<>();
        params.put("userId",propertyDoorDTO.getUserId());
        params.put("doorId", propertyDoorDTO.getDoorId());
        //通过UserId和DoorId检索用户门禁关系信息
        List<PropertyUserDoorMapEntity> userDoorMapList = propertyDoorRepository.getUserDoorMapList(params);
        if (propertyDoorDTO.getState().equals("0") && userDoorMapList.size() > 0){
            //取消分配
            propertyDoorRepository.delete(userDoorMapList.get(0));
        }else if (propertyDoorDTO.getState().equals("1") && userDoorMapList.size() == 0){
            //分配
            PropertyUserDoorMapEntity propertyUserDoorMapEntity = new PropertyUserDoorMapEntity();
            propertyUserDoorMapEntity.setId(IdGen.uuid());
            propertyUserDoorMapEntity.setCreateBy(propertyDoorDTO.getCreateBy());
            propertyUserDoorMapEntity.setCreateOn(new Date());
            propertyUserDoorMapEntity.setUserId(propertyDoorDTO.getUserId());
            propertyUserDoorMapEntity.setDoorId(propertyDoorDTO.getDoorId());
            propertyDoorRepository.saveOrUpdate(propertyUserDoorMapEntity);
        }
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Param: propertyDoorLogDTO 检索信息
     *  @Description: 获取用户门禁开门日志列表
     */
    @Override
    public List<PropertyDoorLogDTO> getDoorLogList(PropertyDoorLogDTO propertyDoorLogDTO, WebPage webPage) {
        //封装检索条件
        Map<String,Object> params = new HashMap<>();
        //设置用户权限范围(单位项目)
        String roleScopes = "";
        List<Map<String, Object>> roleScopeList = propertyDoorLogDTO.getRoleScopeList();
        for (Map<String, Object> roleScope : roleScopeList) {
            String scopeType = (String) roleScope.get("scopeType");
            if (scopeType.equals("1")) {
                //城市级别(分公司)
                List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(roleScope.get("scopeId").toString());
                for (Object[] project : projectList) {
                    if (!roleScopes.contains(project[0].toString())) {
                        roleScopes += "'" + project[0].toString() + "',";
                    }
                }
            } else if (scopeType.equals("2")) {
                //项目级别
                if (!roleScopes.contains(roleScope.get("scopeId").toString())) {
                    roleScopes += "'" + roleScope.get("scopeId") + "',";
                }
            } else if (scopeType.equals("0")) {
                //全部城市
                roleScopes += "all,";
            }
        }
        params.put("roleScopes",roleScopes);        //权限范围
        if (null != propertyDoorLogDTO.getScopeId() && !"".equals(propertyDoorLogDTO.getScopeId()) && !"0".equals(propertyDoorLogDTO.getScopeId())){
            List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(propertyDoorLogDTO.getScopeId());
            String cityProjects = "";
            for (Object[] project : projectList) {
                cityProjects += "'" + project[0].toString() + "',";
                /**
                 * 下面这段代码存在漏洞,北京下亦庄金茂悦x85会把亦庄金茂悦过滤掉,导致亦庄金茂悦查询不到结果。
                 */
//                if (!cityProjects.contains(project[0].toString())) {
//                    cityProjects += "'" + project[0].toString() + "',";
//                }
            }
            params.put("cityProjects",cityProjects);                    //城市下所有项目,以城市为单位检索
        }
        params.put("projectCode",propertyDoorLogDTO.getProjectCode());     //项目编码
        params.put("deviceDesc", propertyDoorLogDTO.getDeviceDesc());      //设备描述
        params.put("userName", propertyDoorLogDTO.getUserName());   //用户名称
        params.put("userPhone", propertyDoorLogDTO.getUserPhone());     //用户电话
        params.put("startDate", propertyDoorLogDTO.getStartDate());     //日期设定1
        params.put("endDate", propertyDoorLogDTO.getEndDate());         //日期设定2
        List<PropertyDoorLogEntity> propertyDoorLogEntityList = propertyDoorRepository.getDoorLogList(params, webPage);
        List<PropertyDoorLogDTO> propertyDoorLogDTOList = new ArrayList<>();
        if (!propertyDoorLogEntityList.isEmpty()) {
            for (PropertyDoorLogEntity propertyDoorLogEntity : propertyDoorLogEntityList) {
                PropertyDoorLogDTO doorLogDTO = new PropertyDoorLogDTO();
                doorLogDTO.setProjectName(propertyDoorLogEntity.getProjectName());
                doorLogDTO.setUserName(propertyDoorLogEntity.getUserName());
                doorLogDTO.setUserPhone(propertyDoorLogEntity.getUserPhone());
                doorLogDTO.setMacName(propertyDoorLogEntity.getMacName());
                doorLogDTO.setMacAddress(propertyDoorLogEntity.getMacAddress());
                doorLogDTO.setDeviceDesc(propertyDoorLogEntity.getDeviceDesc());
                doorLogDTO.setOpenDateTime(propertyDoorLogEntity.getOpenDateTime());
                propertyDoorLogDTOList.add(doorLogDTO);
            }
        }
        return propertyDoorLogDTOList;
    }

    /**
     * 通过门禁Id获取门禁详情 WeiYangDong_2016-11-10
     */
    public PropertyDoorEntity getPropertyDoorById(String id){
        return propertyDoorRepository.getPropertyDoorById(id);
    }

    /**
     * 核对门禁设备地理位置信息 WeiYangDong_2016-11-09
     */
    public int checkPosition(PropertyDoorDTO propertyDoorDTO){
        int flag = 0;
        if(propertyDoorDTO.getDeviceType().equals("1")){
            //单元门进行校验地理位置信息,小区大门不进行校验
            //封装检索条件
            Map<String,Object> params = new HashMap<>();
            params.put("projectCode",propertyDoorDTO.getProjectCode());     //项目编码
            params.put("blockCode",propertyDoorDTO.getBlockCode());         //地块编码
            params.put("buildingNum",propertyDoorDTO.getBuildingNum());     //楼栋编码
            params.put("unitCode",propertyDoorDTO.getUnitCode());           //单元编码
            if (null != propertyDoorDTO.getFloor() && !"".equals(propertyDoorDTO.getFloor())){
                params.put("floor",propertyDoorDTO.getFloor());             //楼层
                //通过单元编码与楼层名称检索楼层信息
                List<HouseFloorEntity> houseFloorEntities = propertyDoorRepository.getFloorListByUnitAndFloorName(propertyDoorDTO.getUnitCode(), propertyDoorDTO.getFloor());
                if (houseFloorEntities.size() > 0){
                    params.put("floorCode",propertyDoorDTO.getFloorCode());         //楼层编码
                }
            }
            //执行检索
            List<PropertyDoorEntity> propertyDoorList = propertyDoorRepository.getPropertyDoorList(params, new WebPage());
            if (propertyDoorList.size() > 0){
                flag = propertyDoorList.size();
            }
        }
        return flag;
    }

    /**
     * 核对物业门禁设备信息 WeiYangDong_2016-11-10
     */
    public int checkDoor(PropertyDoorDTO propertyDoorDTO){
        //封装检索条件
        Map<String,Object> params = new HashMap<>();
        params.put("macAddress",propertyDoorDTO.getMacAddress());
        //执行检索
        List<PropertyDoorEntity> propertyDoorList = propertyDoorRepository.getPropertyDoorList(params, new WebPage());
        //去除当前参与校验的门禁
        List<PropertyDoorEntity> propertyDoorEntities = new ArrayList<>();
        for (PropertyDoorEntity propertyDoorEntity : propertyDoorList){
            if (!propertyDoorEntity.getId().equals(propertyDoorDTO.getDoorId())){
                propertyDoorEntities.add(propertyDoorEntity);
            }
        }
        int flag = 0;
        if (propertyDoorEntities.size() > 0){
            flag = propertyDoorEntities.size();
        }
        return flag;
    }

    /**
     * 保存或更新物业门禁设备 WeiYangDong_2016-11-09
     */
    public PropertyDoorEntity saveOrUpdatePropertyDoor(UserPropertyStaffEntity userPropertystaffEntity,
                                         PropertyDoorDTO propertyDoorDTO){
        PropertyDoorEntity propertyDoorEntity = null;
        if (null != propertyDoorDTO.getDoorId() && !"".equals(propertyDoorDTO.getDoorId())){
            //执行更新
            propertyDoorEntity = propertyDoorRepository.getPropertyDoorById(propertyDoorDTO.getDoorId());
            propertyDoorEntity.setMacAddress(propertyDoorDTO.getMacAddress());      //设备Mac地址
            propertyDoorEntity.setDeviceDesc(propertyDoorDTO.getDeviceDesc());      //设备描述
            propertyDoorEntity.setDevicePwd(propertyDoorDTO.getDevicePwd());        //设备密码
            propertyDoorRepository.saveOrUpdate(propertyDoorEntity);
        }else{
            //执行新增
            propertyDoorEntity = new PropertyDoorEntity();
            propertyDoorEntity.setId(IdGen.uuid());     //主键Id
            propertyDoorEntity.setCreateOn(new Date()); //创建时间
            propertyDoorEntity.setCreateBy(userPropertystaffEntity.getStaffName()); //创建人
            propertyDoorEntity.setMacAddress(propertyDoorDTO.getMacAddress());      //设备Mac地址
            propertyDoorEntity.setDevicePwd(propertyDoorDTO.getDevicePwd());        //设备密码
            propertyDoorEntity.setDeviceDesc(propertyDoorDTO.getDeviceDesc());      //设备描述
            propertyDoorEntity.setDeviceType(propertyDoorDTO.getDeviceType());      //设备类型(仅提供蓝牙的门禁设备:1,提供蓝牙及无线网络的门禁设备:2)
            propertyDoorEntity.setProjectCode(propertyDoorDTO.getProjectCode());    //项目编码
            propertyDoorEntity.setProjectName(propertyDoorDTO.getProjectName());    //项目名称
            propertyDoorEntity.setBlockCode(propertyDoorDTO.getBlockCode());        //地块编码
            propertyDoorEntity.setArea(propertyDoorDTO.getArea());                  //地块
            propertyDoorEntity.setBuildingNum(propertyDoorDTO.getBuildingNum());    //楼号编码
            propertyDoorEntity.setBuildingRecord(propertyDoorDTO.getBuildingRecord());      //备案楼号
            propertyDoorEntity.setUnitCode(propertyDoorDTO.getUnitCode());          //单元编码
            propertyDoorEntity.setUnit(propertyDoorDTO.getUnit());                  //单元
            if (null != propertyDoorDTO.getFloor() && !"".equals(propertyDoorDTO.getFloor())){
                propertyDoorEntity.setFloor(propertyDoorDTO.getFloor());                //楼层
                //通过单元编码与楼层名称检索楼层信息
                List<HouseFloorEntity> houseFloorEntities = propertyDoorRepository.getFloorListByUnitAndFloorName(propertyDoorDTO.getUnitCode(), propertyDoorDTO.getFloor());
                if (houseFloorEntities.size() > 0){
                    propertyDoorEntity.setFloorCode(houseFloorEntities.get(0).getFloorCode());      //楼层编码
                }
            }
            propertyDoorRepository.saveOrUpdate(propertyDoorEntity);
        }
        return propertyDoorEntity;
    }

    /**
     * 导入excel  WeiYangDong_2016-11-07
     * POI:解析Excel文件中的数据并把每行数据封装成一个实体
     * @param fis 文件输入流
     */
    public boolean importEmployeeByPoi(UserPropertyStaffEntity user, InputStream fis){
        PropertyDoorEntity propertyDoorEntity = null;
        try{
            //创建Excel工作薄
            XSSFWorkbook hwb = new XSSFWorkbook(fis);
            //得到第一个工作表
            XSSFSheet sheet = hwb.getSheetAt(0);
            XSSFRow row = null;
            //遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
            for(int i = 0; i < hwb.getNumberOfSheets(); i++) {
                sheet = hwb.getSheetAt(i);
                //遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数
                for(int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                    row = sheet.getRow(j);
                    propertyDoorEntity = new PropertyDoorEntity();
                    //主键Id
                    propertyDoorEntity.setId(IdGen.uuid());
                    propertyDoorEntity.setCreateBy(user.getStaffName());
                    propertyDoorEntity.setCreateOn(new Date());
                    //设备Mac地址
                    if (null != row.getCell(0) && !"".equals(row.getCell(0).toString())){
                        propertyDoorEntity.setMacAddress(getCellValue(row.getCell(0)).trim());
                    }
                    //设备密码
                    if (null != row.getCell(1) && !"".equals(row.getCell(1).toString())){
                        propertyDoorEntity.setDevicePwd(getCellValue(row.getCell(1)).trim());
                    }
                    //设备描述
                    if (null != row.getCell(2) && !"".equals(row.getCell(2).toString())){
                        propertyDoorEntity.setDeviceDesc(getCellValue(row.getCell(2)).trim());
                    }
                    //设备类型(仅提供蓝牙的门禁设备:1,提供蓝牙及无线网络的门禁设备:2)
                    if (null != row.getCell(3) && !"".equals(row.getCell(3).toString())){
                        propertyDoorEntity.setDeviceType(getCellValue(row.getCell(3)).trim());
                    }
                    //项目编码
                    String projectCode = "";
                    if (null != row.getCell(4) && !"".equals(row.getCell(4).toString())){
                        propertyDoorEntity.setProjectCode(getCellValue(row.getCell(4)).trim());
                        projectCode = getCellValue(row.getCell(4)).trim();
                    }
                    //项目名称
                    if (null != row.getCell(5) && !"".equals(row.getCell(5).toString())){
                        propertyDoorEntity.setProjectName(getCellValue(row.getCell(5)).trim());
                    }
                    //地块
                    String areaName = "";
                    if (null != row.getCell(6) && !"".equals(row.getCell(6).toString())){
                        propertyDoorEntity.setArea(getCellValue(row.getCell(6)).trim());
                        areaName = getCellValue(row.getCell(6)).trim();
                    }
                    //地块编码
                    String blockCode = "";
                    if (!"".equals(projectCode) && !"".equals(areaName)){
                        //通过项目编码与地块名称检索地块编码
                        List<HouseAreaEntity> houseAreaEntities = propertyDoorRepository.getAreaListByProjectAndAreaName(projectCode, areaName);
                        if (houseAreaEntities.size() > 0){
                            propertyDoorEntity.setBlockCode(houseAreaEntities.get(0).getBlockCode());
                            blockCode = houseAreaEntities.get(0).getBlockCode();
                        }
                    }
                    //预售楼号
                    //备案楼号
                    String buildingRecord = "";
                    if (null != row.getCell(7) && !"".equals(row.getCell(7).toString())){
                        propertyDoorEntity.setBuildingRecord(getCellValue(row.getCell(7)).trim());
                        buildingRecord = getCellValue(row.getCell(7)).trim();
                    }
                    //楼号编码
                    String buildingNum = "";
                    if (!"".equals(blockCode) && !"".equals(buildingRecord)){
                        List<HouseBuildingEntity> houseBuildingEntities = null;
                        //通过地块编码与备案楼号检索楼栋信息
                        houseBuildingEntities = propertyDoorRepository.getBuildingListByAreaAndBuildingRecord(blockCode, buildingRecord);
                        if (houseBuildingEntities.size() > 0){
                            propertyDoorEntity.setBuildingNum(houseBuildingEntities.get(0).getBuildingNum());
                            buildingNum = houseBuildingEntities.get(0).getBuildingNum();
                        }else{
                            //如果备案楼号查无结果,则通过地块编码与预售楼号检索楼栋信息
                            houseBuildingEntities = propertyDoorRepository.getBuildingListByAreaAndBuildingSale(blockCode,buildingRecord);
                            if (houseBuildingEntities.size() > 0){
                                propertyDoorEntity.setBuildingNum(houseBuildingEntities.get(0).getBuildingNum());
                                propertyDoorEntity.setBuildingSale(houseBuildingEntities.get(0).getBuildingSale());
                                propertyDoorEntity.setBuildingRecord(null); //若预售楼号查得结果,则清空备案楼号字段
                                buildingNum = houseBuildingEntities.get(0).getBuildingNum();
                            }
                        }
                    }
                    //单元
                    String unitName = "";
                    if (null != row.getCell(8) && !"".equals(row.getCell(8).toString())){
                        propertyDoorEntity.setUnit(getCellValue(row.getCell(8)).trim());
                        unitName = getCellValue(row.getCell(8)).trim();
                    }
                    //单元编码
                    String unitCode = "";
                    if (!"".equals(buildingNum) && !"".equals(unitName)){
                        //通过楼栋编码与单元名称检索单元信息
                        List<HouseUnitEntity> houseUnitEntities = propertyDoorRepository.getUnitListByBuildingAndUnitName(buildingNum, unitName);
                        if (houseUnitEntities.size() > 0){
                            propertyDoorEntity.setUnitCode(houseUnitEntities.get(0).getUnitCode());
                            unitCode = houseUnitEntities.get(0).getUnitCode();
                        }
                    }
                    //楼层
                    String floorName = "";
                    if (null != row.getCell(9) && !"".equals(row.getCell(9).toString())){
                        propertyDoorEntity.setFloor(getCellValue(row.getCell(9)).trim());
                        floorName = getCellValue(row.getCell(9)).trim();
                    }
                    //楼层编码
                    if (!"".equals(unitCode) && !"".equals(floorName)){
                        //通过单元编码与楼层名称检索楼层信息
                        List<HouseFloorEntity> houseFloorEntities = propertyDoorRepository.getFloorListByUnitAndFloorName(unitCode, floorName);
                        if (houseFloorEntities.size() > 0){
                            propertyDoorEntity.setFloorCode(houseFloorEntities.get(0).getFloorCode());
                        }
                    }
                    propertyDoorRepository.saveOrUpdate(propertyDoorEntity);
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //判断从Excel文件中解析出来数据的格式
    private static String getCellValue(XSSFCell cell){
        String value = null;
        //简单的查检列类型
        switch(cell.getCellType())
        {
            case HSSFCell.CELL_TYPE_STRING://字符串
                value = cell.getRichStringCellValue().getString();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC://数字
                long dd = (long)cell.getNumericCellValue();
                value = dd+"";
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                value = "";
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getCellFormula());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN://boolean型值
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                break;
        }
        return value;
    }

    /**
     * 门禁批量导入模板下载 WeiYangDong_2016-12-13
     */
    public String downLoadTemplate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        //创建Sheet页
        XSSFSheet sheet = workBook.createSheet("批量导入门禁表");

        ExportUtil exportUtil = new ExportUtil(workBook, sheet);
        XSSFCellStyle headStyle = exportUtil.getHeadStyle();
        XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();

        // 百分比
        XSSFDataFormat fmt = workBook.createDataFormat();
        XSSFDataFormat fmt1 = workBook.createDataFormat();

        // 四个边框加粗
        XSSFCellStyle style1 = workBook.createCellStyle();
        style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style1.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style1.setBorderTop(XSSFCellStyle.BORDER_THIN);
        XSSFFont font = workBook.createFont();

        // 设置字体加粗
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        style1.setFont(font);

        String[] titles = {"设备Mac地址","设备密码","设备描述","设备类型","项目编码","项目","地块","楼栋","单元","楼层"};

        XSSFRow headRow = sheet.createRow(0);

        XSSFCell cell = null;
        for (int i = 0; i < titles.length; i++) {
            cell = headRow.createCell(i);
            headRow.createCell(i).setCellValue(titles.length);

            cell.setCellStyle(headStyle);
            cell.setCellValue(titles[i]);
        }
        sheet.setColumnWidth(0, 7000);  //设备Mac地址
        sheet.setColumnWidth(1, 7000);  //设备密码
        sheet.setColumnWidth(2, 10000); //设备描述
        sheet.setColumnWidth(3, 3000);  //设备类型
        sheet.setColumnWidth(4, 4000);  //项目编码
        sheet.setColumnWidth(5, 4000);  //项目
        sheet.setColumnWidth(6, 2000);  //地块
        sheet.setColumnWidth(7, 2000);  //楼栋
        sheet.setColumnWidth(8, 2000);  //单元
        sheet.setColumnWidth(9, 2000);  //楼层

        XSSFRow bodyRow = sheet.createRow(1);
        cell = bodyRow.createCell(0);
        cell.setCellStyle(bodyStyle);//表格黑色边框
        cell.setCellValue("10000000001");//设备Mac地址
        cell = bodyRow.createCell(1);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("123456");//设备密码
        cell = bodyRow.createCell(2);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("亦庄金茂悦02栋01单元-1层");//设备描述
        cell = bodyRow.createCell(3);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("1");//设备类型
        cell = bodyRow.createCell(4);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("BJ-YZJMY");//项目编码
        cell = bodyRow.createCell(5);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("亦庄金茂悦");//项目
        cell = bodyRow.createCell(6);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("X88");//地块
        cell = bodyRow.createCell(7);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("02");//楼栋
        cell = bodyRow.createCell(8);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("01");//单元
        cell = bodyRow.createCell(9);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("-1");//楼层

        XSSFRow bodyRow2 = sheet.createRow(2);
        cell = bodyRow2.createCell(0);
        cell.setCellStyle(bodyStyle);//表格黑色边框
        cell.setCellValue("10000000002");//设备Mac地址
        cell = bodyRow2.createCell(1);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("123456");//设备密码
        cell = bodyRow2.createCell(2);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("亦庄金茂悦小区北门");//设备描述
        cell = bodyRow2.createCell(3);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("2");//设备类型
        cell = bodyRow2.createCell(4);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("BJ-YZJMY");//项目编码
        cell = bodyRow2.createCell(5);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("亦庄金茂悦");//项目
        cell = bodyRow2.createCell(6);
        cell.setCellStyle(bodyStyle);
        cell.setCellValue("X88");//地块
//        cell = bodyRow2.createCell(7);
//        cell.setCellStyle(bodyStyle);
//        cell.setCellValue("");//楼栋
//        cell = bodyRow2.createCell(8);
//        cell.setCellStyle(bodyStyle);
//        cell.setCellValue("");//单元
//        cell = bodyRow2.createCell(9);
//        cell.setCellStyle(bodyStyle);
//        cell.setCellValue("");//楼层

        try {
            String fileName = new String(("批量导入门禁表").getBytes(), "ISO8859-1");
            String agent = httpServletRequest.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                fileName = java.net.URLEncoder.encode("批量导入门禁表", "UTF8");
            }
            httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
            workBook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 批量导出门禁列表数据 WeiYangDong_2016-12-13
     */
    public void exportDoorListExcel(List<PropertyDoorEntity> propertyDoorList, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException{
        OutputStream outputStream = httpServletResponse.getOutputStream();

        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);

        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();

        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet("角色管理列表");
        ExportUtil exportUtil = new ExportUtil(workBook, sheet);
        XSSFCellStyle headStyle = exportUtil.getHeadStyle();
        XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();

        // 百分比
        XSSFDataFormat fmt = workBook.createDataFormat();
        XSSFDataFormat fmt1 = workBook.createDataFormat();

        // 四个边框加粗
        XSSFCellStyle style1 = workBook.createCellStyle();
        style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style1.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style1.setBorderTop(XSSFCellStyle.BORDER_THIN);
        XSSFFont font = workBook.createFont();
        // 设置字体加粗
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        style1.setFont(font);

        String[] titles = {"序号","项目名称","地块","楼号","单元号","楼层","设备Mac地址","门禁设备描述","设备密码"};
        XSSFRow headRow = sheet.createRow(0);

        if (propertyDoorList.size() > 0) {

            propertyDoorList.forEach(userDTO -> {

                XSSFCell cell=null;
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }
                sheet.setColumnWidth(0, 1000);  //序号
                sheet.setColumnWidth(1, 4000);  //项目名称
                sheet.setColumnWidth(2, 2000);  //地块
                sheet.setColumnWidth(3, 2000);  //楼栋
                sheet.setColumnWidth(4, 2000);  //单元
                sheet.setColumnWidth(5, 2000);  //楼层
                sheet.setColumnWidth(6, 7000);  //设备Mac地址
                sheet.setColumnWidth(7, 10000); //设备描述
                sheet.setColumnWidth(8, 7000);  //设备密码

                if (propertyDoorList.size() > 0) {
                    for (int i = 0; i < propertyDoorList.size(); i++) {
                        XSSFRow bodyRow = sheet.createRow(i + 1);
                        PropertyDoorEntity propertyDoorEntity = propertyDoorList.get(i);
                        cell = bodyRow.createCell(0);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(i + 1);//序号

                        cell = bodyRow.createCell(1);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(propertyDoorEntity.getProjectName());//项目名称

                        cell = bodyRow.createCell(2);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(propertyDoorEntity.getArea());//地块

                        cell = bodyRow.createCell(3);
                        cell.setCellStyle(bodyStyle);
                        //楼号
                        if (null != propertyDoorEntity.getBuildingRecord() && !"".equals(propertyDoorEntity.getBuildingRecord())){
                            cell.setCellValue(propertyDoorEntity.getBuildingRecord());
                        }else{
                            cell.setCellValue(propertyDoorEntity.getBuildingSale());
                        }

                        cell = bodyRow.createCell(4);
                        cell.setCellStyle(bodyStyle);
                        cell.setCellValue(propertyDoorEntity.getUnit());//单元号

                        cell = bodyRow.createCell(5);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(propertyDoorEntity.getFloor());//楼层

                        cell = bodyRow.createCell(6);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(propertyDoorEntity.getMacAddress());//设备Mac地址

                        cell = bodyRow.createCell(7);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(propertyDoorEntity.getDeviceDesc());//门禁设备描述

                        cell = bodyRow.createCell(8);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(propertyDoorEntity.getDevicePwd());//设备密码

                    }
                }
            });
        }
        try {
            String fileName = new String(("门禁设备管理列表").getBytes(), "ISO8859-1");
            String agent = httpServletRequest.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                fileName = java.net.URLEncoder.encode("门禁设备管理列表", "UTF8");
            }
            httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
            workBook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Autowired
    HouseInfoRepository houseInfoRepository;
    @Autowired
    HouseInfoService houseInfoService;
    /**
     * 初始化门禁用户关系数据 WeiYangDong_2016-12-16
     */
    public void initializeUserDoor(PropertyDoorDTO propertyDoorDTO){
        //通过门禁ID检索门禁信息
        PropertyDoorEntity doorEntity = propertyDoorRepository.getPropertyDoorById(propertyDoorDTO.getDoorId());
        if (null != doorEntity){
            //通过房产位置信息检索所有的业主。单元门 or 小区大门
            String deviceType = doorEntity.getDeviceType();
            Map<String,Object> params = new HashMap<>();
            if (null != deviceType && "1".equals(deviceType)){
                //单元门
                params.put("projectCode",doorEntity.getProjectCode());  //项目编码
                params.put("blockCode",doorEntity.getBlockCode());      //地块编码
                params.put("buildingNum",doorEntity.getBuildingNum());  //楼栋编码
                params.put("unitCode",doorEntity.getUnitCode());        //单元编码
            }else{
                //小区大门
                params.put("projectCode",doorEntity.getProjectCode());
            }
            params.put("userType","3");
//            List<Map<String, Object>> userList = propertyDoorRepository.getUserList(params, null);
            List<Map<String, Object>> userList = propertyDoorRepository.getUserListByHousePosition(params);
            //门禁分配
            Map<String,Object> user = null;
            PropertyUserDoorMapEntity propertyUserDoorMapEntity = null;
            Map<String,Object> userDoorParams = null;
            for (int i = 0, length = userList.size(); i<length; i++){
                user = userList.get(i);
                //校验该用户是否已经拥有该门禁权限
                userDoorParams = new HashMap<>();
                userDoorParams.put("userId",user.get("userId"));
                userDoorParams.put("doorId", doorEntity.getId());
                List<PropertyUserDoorMapEntity> userDoorMapList = propertyDoorRepository.getUserDoorMapList(userDoorParams);
                if (null == userDoorMapList || userDoorMapList.size() == 0){
                    //该用户尚未拥有该门禁权限,追加权限
                    propertyUserDoorMapEntity = new PropertyUserDoorMapEntity();
                    propertyUserDoorMapEntity.setId(IdGen.uuid());
                    propertyUserDoorMapEntity.setUserId(user.get("userId").toString());
                    propertyUserDoorMapEntity.setDoorId(doorEntity.getId());
                    propertyUserDoorMapEntity.setCreateOn(new Date());
                    propertyUserDoorMapEntity.setCreateBy(propertyDoorDTO.getCreateBy());
                    propertyDoorRepository.saveOrUpdate(propertyUserDoorMapEntity);
                }
            }
        }
    }

    /**
     * 初始化用户门禁关系数据 WeiYangDong_2016-12-16
     */
    public void initialize(PropertyDoorDTO propertyDoorDTO){
        //通过项目检索所有的业主
        Map<String,Object> params = new HashMap<>();
        params.put("projectCode",propertyDoorDTO.getProjectCode());
        params.put("userType","3");
        List<Map<String, Object>> userList = propertyDoorRepository.getUserList(params, null);
        Map<String,Object> user = null;
        UserInfoEntity userInfoEntity = null;
        for (int i = 0, length = userList.size(); i<length; i++){
            user = userList.get(i);
            userInfoEntity = new UserInfoEntity();
            userInfoEntity.setUserId(user.get("userId").toString());
            userInfoEntity.setNickName(user.get("nickName").toString());
            //门禁分配
            //检索用户房产列表
            List<HouseInfoEntity> houseInfoEntityList = houseInfoRepository.getHouseByUserMemberId(user.get("id").toString());
            HouseInfoEntity houseInfoEntity = null;
            if (!houseInfoEntityList.isEmpty() && houseInfoEntityList.size() > 0) {
                for (int j = 0,length1 = houseInfoEntityList.size(); j<length1; j++){
                    houseInfoEntity = houseInfoEntityList.get(j);
                    houseInfoService.assignDoorByHouse(houseInfoEntity.getProjectNum(),houseInfoEntity.getArea(),houseInfoEntity.getBuildingNum(),houseInfoEntity.getUnitNum(),userInfoEntity);
                }
            }
        }

    }


    /* -------------------------------------------------------------------- */
    /* ----------------------门禁功能模块前端接口-Service---------------------- */
    /* -------------------------------------------------------------------- */

    /**
     * 通过用户获取门禁权限列表
     * @param userId  用户
     * @return  List<Map<String,Object>>
     */
    public List<Map<String,Object>> getPropertyDoorListByUser(String userId){
        //封装返回结果
        List<Map<String,Object>> doorList = new ArrayList<>();
        List<PropertyDoorEntity> propertyDoorEntityList = propertyDoorRepository.getPropertyDoorListByUser(userId);
        Map<String,Object> door = null;
        PropertyDoorEntity propertyDoorEntity = null;
        for (int i = 0,length = propertyDoorEntityList.size(); i < length; i++){
            propertyDoorEntity = propertyDoorEntityList.get(i);
            door = new HashMap<>();
            door.put("doorId",propertyDoorEntity.getId());
            door.put("macAddress",propertyDoorEntity.getMacAddress());
            door.put("devicePwd",propertyDoorEntity.getDevicePwd());
            door.put("deviceDesc",propertyDoorEntity.getDeviceDesc());
            doorList.add(door);
        }
        return doorList;
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Param: userPropertyStaffEntity 当前用户， id 选择的doorId，macName 设备mac的名称
     *  @Description: 用户门禁开门记录
     */
    @Override
    public void createDoorLog(UserInfoEntity userInfoEntity, String id, String macName) {
        //获取该用户选择的对应propertyDoorEntity
        PropertyDoorEntity propertyDoorEntity = propertyDoorRepository.getPropertyDoorById(id);
        PropertyDoorLogEntity propertyDoorLogEntity = new PropertyDoorLogEntity();
        if (propertyDoorEntity != null) {
            //记录该开门信息
            propertyDoorLogEntity.setId(IdGen.uuid());
            propertyDoorLogEntity.setProjectCode(propertyDoorEntity.getProjectCode());
            propertyDoorLogEntity.setProjectName(propertyDoorEntity.getProjectName());
            propertyDoorLogEntity.setMacName(macName);
            propertyDoorLogEntity.setMacAddress(propertyDoorEntity.getMacAddress());
            propertyDoorLogEntity.setDeviceDesc(propertyDoorEntity.getDeviceDesc());
            propertyDoorLogEntity.setDeviceType(propertyDoorEntity.getDeviceType());

            propertyDoorLogEntity.setOpenDateTime(new Date());

            propertyDoorLogEntity.setUserId(userInfoEntity.getUserId());
            propertyDoorLogEntity.setUserName(userInfoEntity.getRealName());
            propertyDoorLogEntity.setUserPhone(userInfoEntity.getMobile());

            propertyDoorRepository.saveDoorLog(propertyDoorLogEntity);
        }
    }

    /**
     * 开门日志列表批量导出Excel  Wyd_20170228
     * @param title
     * @param headers
     * @param out
     * @param propertyDoorLogDTO
     */
    public void exportDoorLogListExcel(String title, String[] headers, ServletOutputStream out, PropertyDoorLogDTO propertyDoorLogDTO){
        //设置分页
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);
        //执行查询
        List<PropertyDoorLogDTO> doorLogList = getDoorLogList(propertyDoorLogDTO, webPage);
        if (doorLogList != null){
            int num = 1;
            PropertyDoorLogDTO doorLog = null;
            List<ExportExcelDoorLogDTO> exportExcelDoorLogDTOs = new ArrayList<>();
            ExportExcelDoorLogDTO exportExcelDoorLogDTO = null;
            for (int i = 0, length = doorLogList.size(); i < length; i++){
                doorLog = doorLogList.get(i);
                exportExcelDoorLogDTO = new ExportExcelDoorLogDTO();
                exportExcelDoorLogDTO.setNum(num++);                //序号
                exportExcelDoorLogDTO.setProjectName(doorLog.getProjectName() == null ? "" : doorLog.getProjectName());     //项目名称
                exportExcelDoorLogDTO.setUserName(doorLog.getUserName() == null ? "" : doorLog.getUserName());              //用户名
                exportExcelDoorLogDTO.setUserPhone(doorLog.getUserPhone() == null ? "" : doorLog.getUserPhone());           //用户电话
                exportExcelDoorLogDTO.setMacAddress(doorLog.getMacAddress() == null ? "" : doorLog.getMacAddress());        //Mac地址
                exportExcelDoorLogDTO.setDeviceDesc(doorLog.getDeviceDesc() == null ? "" : doorLog.getDeviceDesc());        //设备描述
                exportExcelDoorLogDTO.setOpenDateTime(doorLog.getOpenDateTime() == null ? "" : DateUtils.format(doorLog.getOpenDateTime(), DateUtils.FORMAT_LONG));
                exportExcelDoorLogDTOs.add(exportExcelDoorLogDTO);
            }
            ExportExcel<ExportExcelDoorLogDTO> ex = new ExportExcel<ExportExcelDoorLogDTO>();
            ex.exportExcel(title, headers, exportExcelDoorLogDTOs, out, "yyyy-MM-dd");
            System.out.println("excel导出成功！");
        }
    }

    /**
     * 通过楼层获取门禁日志统计信息
     */
    public List<Map<String,Object>> getDoorLogStatisticsByFloor(PropertyDoorLogDTO propertyDoorLogDTO){
        Map<String,Object> params = new HashedMap();
        params.put("projectCode",propertyDoorLogDTO.getProjectCode());
        return propertyDoorRepository.getDoorLogStatisticsByFloor(params);
    }

    /**
     * 通过时间段(小时)获取门禁日志统计信息
     */
    public List<Map<String,String>> getDoorLogStatisticsByTime(PropertyDoorLogDTO propertyDoorLogDTO){
        Map<String,Object> params = new HashedMap();
        params.put("projectCode",propertyDoorLogDTO.getProjectCode());
        return propertyDoorRepository.getDoorLogStatisticsByTime(params);
    }

    /**
     * 通过楼栋获取门禁日志统计信息
     */
    public List<Map<String,Object>> getDoorLogStatisticsByBuilding(PropertyDoorLogDTO propertyDoorLogDTO){
        Map<String,Object> params = new HashedMap();
        params.put("projectCode",propertyDoorLogDTO.getProjectCode());
        return propertyDoorRepository.getDoorLogStatisticsByBuilding(params);
    }
}
