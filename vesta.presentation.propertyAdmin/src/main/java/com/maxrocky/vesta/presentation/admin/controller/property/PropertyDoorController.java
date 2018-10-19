package com.maxrocky.vesta.presentation.admin.controller.property;

import com.maxrocky.vesta.application.DTO.PropertyDoorDTO;
import com.maxrocky.vesta.application.DTO.PropertyDoorLogDTO;
import com.maxrocky.vesta.application.DTO.PropertyVisitorDTO;
import com.maxrocky.vesta.application.DTO.admin.HouseRoomTypeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.RoleRolesetDTO;
import com.maxrocky.vesta.application.inf.*;
import com.maxrocky.vesta.domain.model.PropertyDoorEntity;
import com.maxrocky.vesta.domain.model.PropertyUserDoorMapEntity;
import com.maxrocky.vesta.domain.model.PropertyVisitorEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.MapKeyComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.InputStream;
import java.util.*;

/**
 * 物业门禁管理-Controller
 * Created by WeiYangDong on 2016/11/1.
 */
@Controller
@RequestMapping(value = "/property")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class PropertyDoorController {
    @Autowired
    private PropertyDoorService propertyDoorService;
    @Autowired
    private StaffUserService staffUserService;
    @Autowired
    private HouseBuildingService houseBuildingService;
    @Autowired
    private HouseUnitService houseUnitService;
    @Autowired
    private HouseFloorService houseFloorService;

    /**
     * 校验用户是否可以操作该交房计划(权限范围)
     */
    @ResponseBody
    @RequestMapping(value = "/checkEditPropertyDoor/{doorId}/{menuId}")
    public Map<String,Object> checkEdit(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                        @PathVariable(value = "doorId")String doorId,
                                        @PathVariable(value = "menuId")String menuId){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            //用户权限项目范围
            List<Map<String, Object>> roleProjectList = staffUserService.getProjectScopeByStaffId(userPropertystaffEntity.getStaffId(), menuId);
            //交房计划项目范围
            PropertyDoorEntity propertyDoorEntity = propertyDoorService.getPropertyDoorById(doorId);
            String projectNum = propertyDoorEntity.getProjectCode();
            //用户权限范围包含发布范围
            int flag = 0;
            for (Map<String,Object> roleProject : roleProjectList){
                if (projectNum.equals(roleProject.get("projectId").toString())){
                    flag = 1;
                }
            }
            if (flag != 1){
                resultMap.put("error",flag);
                return resultMap;
            }
            resultMap.put("error",1);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * 获取物业访客列表
     * author WeiYangDong_2016-11-01
     */
    @RequestMapping(value = "/propertyVisitorList.html")
    public ModelAndView getPropertyVisitorList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                               @Valid PropertyVisitorDTO propertyVisitorDTO,WebPage webPage,Model model){
        try{
            List<PropertyVisitorEntity> propertyVisitorList = propertyDoorService.getPropertyVisitorList(propertyVisitorDTO, webPage);
            model.addAttribute("propertyVisitorList", propertyVisitorList);
            return new ModelAndView("");
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error", "-1");
            return new ModelAndView("");
        }
    }

    /**
     * 获取物业门禁列表
     * author WeiYangDong_2016-11-04
     */
    @RequestMapping(value = "/propertyDoorList.html")
    public ModelAndView getPropertyDoorList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                            @Valid PropertyDoorDTO propertyDoorDTO,WebPage webPage,Model model){
        try{
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),propertyDoorDTO.getMenuId());
            model.addAttribute("city", cityList);
            if (null != propertyDoorDTO.getScopeId() && !"".equals(propertyDoorDTO.getScopeId())){
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(),propertyDoorDTO.getScopeId(),propertyDoorDTO.getMenuId());
                model.addAttribute("projectList",projectList);
                if (null != propertyDoorDTO.getProjectCode() && !"".equals(propertyDoorDTO.getProjectCode())){
                    //项目不为空,回显地块列表
                    Map areaMap =houseBuildingService.getAreaListByProjectNum(propertyDoorDTO.getProjectCode());
                    model.addAttribute("areaMap",areaMap);
                    if (null != propertyDoorDTO.getBlockCode() && !"".equals(propertyDoorDTO.getBlockCode())){
                        //地块不为空,回显楼栋列表
                        Map buildMap =houseBuildingService.getBuildListByBlockNum(propertyDoorDTO.getBlockCode());
                        model.addAttribute("buildMap",buildMap);
                        if (null != propertyDoorDTO.getBuildingNum() && !"".equals(propertyDoorDTO.getBuildingNum())){
                            //楼栋不为空,回显单元列表
                            Map unitMap = houseUnitService.getUnitMapByBuildingId(propertyDoorDTO.getBuildingNum());
                            model.addAttribute("unitMap",unitMap);
                            if (null != propertyDoorDTO.getUnitCode() && !"".equals(propertyDoorDTO.getUnitCode())){
                                //单元不为空,回显楼层列表
                                Map<String, String> floorMap = houseFloorService.getRoomsByUnitNum(propertyDoorDTO.getUnitCode());
                                model.addAttribute("floorMap",floorMap);
                            }
                        }
                    }
                }
            }
            model.addAttribute("propertyDoorDTO",propertyDoorDTO);
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
            propertyDoorDTO.setRoleScopeList(roleScopeList);
            //执行查询
            List<PropertyDoorEntity> propertyDoorList = propertyDoorService.getPropertyDoorList(propertyDoorDTO, webPage);
            model.addAttribute("propertyDoorList",propertyDoorList);
            model.addAttribute("isExcel",webPage.getRecordCount());

            return new ModelAndView("/property/PropertyDoorList");
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error","-1");
            return new ModelAndView("/property/PropertyDoorList");
        }
    }

    /**
     * 去新增物业门禁页
     * author WeiYangDong_2016-11-08
     */
    @RequestMapping(value = "/toAddPropertyDoor.html")
    public ModelAndView toAddPropertyDoor(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                          @Valid PropertyDoorDTO propertyDoorDTO,Model model){
        //城市列表
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),propertyDoorDTO.getMenuId());
        model.addAttribute("city", cityList);
        return new ModelAndView("/property/PropertyDoorAdd");
    }

    /**
     * 去编辑物业门禁页
     * author WeiYangDong_2016-11-10
     */
    @RequestMapping(value = "/toEditPropertyDoor.html")
    public ModelAndView toEditPropertyDoor(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                           @Valid PropertyDoorDTO propertyDoorDTO,Model model){
        PropertyDoorEntity propertyDoorEntity = propertyDoorService.getPropertyDoorById(propertyDoorDTO.getDoorId());
        model.addAttribute("propertyDoor",propertyDoorEntity);
        return new ModelAndView("/property/PropertyDoorEdit");
    }

    /**
     * 物业门禁去分配用户开门权限
     * author WeiYangDong_2016-11-10
     */
    @RequestMapping(value = "/toAssignUser.html")
    public ModelAndView toAssignUser(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                     @Valid PropertyDoorDTO propertyDoorDTO,WebPage webPage,Model model){
        //分页设置并回显
        webPage.setPageSize(20);
        List<Map<String, Object>> list = propertyDoorService.getPropertyUserDoorList(propertyDoorDTO, null);
        webPage.setRecordCount(list.size());
        model.addAttribute("webPage", webPage);
        //检索条件回显
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(), propertyDoorDTO.getMenuId());
        model.addAttribute("city", cityList);
        if (null != propertyDoorDTO.getScopeId() && !"".equals(propertyDoorDTO.getScopeId())) {
            //城市不为空,回显项目列表
            List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), propertyDoorDTO.getScopeId(), propertyDoorDTO.getMenuId());
            model.addAttribute("projectList", projectList);
        }
        model.addAttribute("propertyDoorDTO", propertyDoorDTO);
        //物业门禁数据
        PropertyDoorEntity propertyDoorEntity = propertyDoorService.getPropertyDoorById(propertyDoorDTO.getDoorId());
        StringBuilder position = new StringBuilder();
        position.append(propertyDoorEntity.getProjectName()+" ");
        position.append(propertyDoorEntity.getArea()+"地块 ");
        if (null != propertyDoorEntity.getBuildingRecord() && !"".equals(propertyDoorEntity.getBuildingRecord())){
            position.append(propertyDoorEntity.getBuildingRecord()+"栋 ");
        }else if (null != propertyDoorEntity.getBuildingSale() && !"".equals(propertyDoorEntity.getBuildingSale())){
            position.append(propertyDoorEntity.getBuildingSale()+"栋 ");
        }
        if (null != propertyDoorEntity.getUnit() && !"".equals(propertyDoorEntity.getUnit())){
            position.append(propertyDoorEntity.getUnit()+"单元 ");
        }
        if (null != propertyDoorEntity.getFloor() && !"".equals(propertyDoorEntity.getFloor())){
            position.append(propertyDoorEntity.getFloor()+"层 ");
        }
        model.addAttribute("position",position.toString());
        model.addAttribute("propertyDoor",propertyDoorEntity);
        //用户门禁关系用户列表数据
        List<Map<String, Object>> propertyUserDoorList = propertyDoorService.getPropertyUserDoorList(propertyDoorDTO, webPage);
        model.addAttribute("propertyUserDoorList",propertyUserDoorList);
        return new ModelAndView("/property/PropertyDoorAssignUser");
    }

    /**
     * 物业用户去分配门禁开门权限
     * author WeiYangDong_2016-11-14
     */
    @RequestMapping(value = "/toAssignDoor.html")
    public ModelAndView toAssignDoor(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                     @Valid PropertyDoorDTO propertyDoorDTO,WebPage webPage,Model model){
        //分页设置并回显
        webPage.setPageSize(20);
        List<Map<String, Object>> list = propertyDoorService.getUserList(propertyDoorDTO, null);
        webPage.setRecordCount(list.size());
        model.addAttribute("webPage", webPage);
        //检索条件回显
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(), propertyDoorDTO.getMenuId());
        model.addAttribute("city", cityList);
        if (null != propertyDoorDTO.getScopeId() && !"".equals(propertyDoorDTO.getScopeId())) {
            //城市不为空,回显项目列表
            List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), propertyDoorDTO.getScopeId(), propertyDoorDTO.getMenuId());
            model.addAttribute("projectList", projectList);
        }
        //检索条件回显
        model.addAttribute("propertyDoorDTO", propertyDoorDTO);
        //用户门禁关系用户列表数据
        List<Map<String, Object>> propertyUserDoorList = propertyDoorService.getUserList(propertyDoorDTO, webPage);
        model.addAttribute("propertyUserDoorList",propertyUserDoorList);
        return new ModelAndView("/property/PropertyUserAssignDoor");
    }

    /**
     * 物业用户分配门禁权限-门禁列表
     * author WeiYangDong_2016-11-15
     */
    @RequestMapping(value = "/toAssignDoorList.html")
    public ModelAndView toAssignDoorList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                         @Valid PropertyDoorDTO propertyDoorDTO,WebPage webPage,Model model){
        //检索条件回显
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),propertyDoorDTO.getMenuId());
        model.addAttribute("city", cityList);
        if (null != propertyDoorDTO.getScopeId() && !"".equals(propertyDoorDTO.getScopeId())){
            //城市不为空,回显项目列表
            List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(),propertyDoorDTO.getScopeId(),propertyDoorDTO.getMenuId());
            model.addAttribute("projectList",projectList);
            if (null != propertyDoorDTO.getProjectCode() && !"".equals(propertyDoorDTO.getProjectCode())){
                //项目不为空,回显地块列表
                Map areaMap =houseBuildingService.getAreaListByProjectNum(propertyDoorDTO.getProjectCode());
                model.addAttribute("areaMap",areaMap);
                if (null != propertyDoorDTO.getBlockCode() && !"".equals(propertyDoorDTO.getBlockCode())){
                    //地块不为空,回显楼栋列表
                    Map buildMap =houseBuildingService.getBuildListByBlockNum(propertyDoorDTO.getBlockCode());
                    model.addAttribute("buildMap",buildMap);
                    if (null != propertyDoorDTO.getBuildingNum() && !"".equals(propertyDoorDTO.getBuildingNum())){
                        //楼栋不为空,回显单元列表
                        Map unitMap = houseUnitService.getUnitMapByBuildingId(propertyDoorDTO.getBuildingNum());
                        model.addAttribute("unitMap",unitMap);
                        if (null != propertyDoorDTO.getUnitCode() && !"".equals(propertyDoorDTO.getUnitCode())){
                            //单元不为空,回显楼层列表
                            Map<String, String> floorMap = houseFloorService.getRoomsByUnitNum(propertyDoorDTO.getUnitCode());
                            model.addAttribute("floorMap",floorMap);
                        }
                    }
                }
            }
        }
        model.addAttribute("propertyDoorDTO", propertyDoorDTO);
        //获取数据范围权限
        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
        propertyDoorDTO.setRoleScopeList(roleScopeList);
        //执行查询
        //用户信息
        Map<String, Object> userInfo = propertyDoorService.getUserInfo(propertyDoorDTO);
        model.addAttribute("userInfo", userInfo);
        //门禁列表
        List<PropertyDoorEntity> propertyDoorList = propertyDoorService.getPropertyDoorList(propertyDoorDTO, null);
        List<PropertyUserDoorMapEntity> userDoorMapList = propertyDoorService.getUserDoorMapList(propertyDoorDTO);
        List<PropertyDoorDTO> propertyDoorDTOList_0 = new ArrayList<>();    //已分配门禁
        List<PropertyDoorDTO> propertyDoorDTOList_1 = new ArrayList<>();    //未分配门禁
        PropertyDoorDTO doorDTO = null;
        PropertyDoorEntity propertyDoorEntity = null;
        PropertyUserDoorMapEntity propertyUserDoorMapEntity = null;
        for (int i = 0,length = propertyDoorList.size(); i < length; i++){
            propertyDoorEntity = propertyDoorList.get(i);
            doorDTO = new PropertyDoorDTO();
            doorDTO.setDoorId(propertyDoorEntity.getId());
            doorDTO.setProjectName(propertyDoorEntity.getProjectName());
            doorDTO.setArea(propertyDoorEntity.getArea());
            doorDTO.setBuildingRecord(propertyDoorEntity.getBuildingRecord());
            doorDTO.setBuildingSale(propertyDoorEntity.getBuildingSale());
            doorDTO.setUnit(propertyDoorEntity.getUnit());
            doorDTO.setFloor(propertyDoorEntity.getFloor());
            doorDTO.setMacAddress(propertyDoorEntity.getMacAddress());
            doorDTO.setDeviceDesc(propertyDoorEntity.getDeviceDesc());
            //处理用户门禁关系
            for (int j = 0,len = userDoorMapList.size(); j < len; j++){
                propertyUserDoorMapEntity = userDoorMapList.get(j);
                if (propertyDoorEntity.getId().equals(propertyUserDoorMapEntity.getDoorId()) && propertyDoorDTO.getUserId().equals(propertyUserDoorMapEntity.getUserId())){
                    doorDTO.setAssignState("0");    //已分配
                }
            }
            if (null != doorDTO.getAssignState()){
                propertyDoorDTOList_0.add(doorDTO);
            }else{
                propertyDoorDTOList_1.add(0,doorDTO);
            }
        }
        List<PropertyDoorDTO> propertyDoorDTOList = null;
        if (null != propertyDoorDTO.getAssignState() && !"".equals(propertyDoorDTO.getAssignState())){
            if ("0".equals(propertyDoorDTO.getAssignState())){
                propertyDoorDTOList = propertyDoorDTOList_0;
            }else if ("1".equals(propertyDoorDTO.getAssignState())){
                propertyDoorDTOList = propertyDoorDTOList_1;
            }
        }else{
            propertyDoorDTOList_0.addAll(propertyDoorDTOList_1);
            propertyDoorDTOList = propertyDoorDTOList_0;
        }
        //手动数据分页(两部分检索结果组合列表数据,需手动分页)
        webPage.setRecordCount(propertyDoorDTOList.size());
        webPage.setPageSize(20);
        int staT = (webPage.getPageIndex() - 1) * webPage.getPageSize();
        int endT = (webPage.getPageIndex() - 1) * webPage.getPageSize() + webPage.getPageSize();
        if (endT > webPage.getRecordCount()){
            model.addAttribute("propertyDoorList",propertyDoorDTOList.subList(staT,webPage.getRecordCount()));
        }else{
            model.addAttribute("propertyDoorList",propertyDoorDTOList.subList(staT,endT));
        }
        return new ModelAndView("/property/PropertyUserAssignDoorList");
    }

    /**
     * 分配/取消分配用户门禁权限
     * author WeiYangDong_2016-11-11
     */
    @ResponseBody
    @RequestMapping(value = "/assignUserDoor",method = RequestMethod.POST)
    public Map<String,Object> assignUserDoor(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                             @Valid PropertyDoorDTO propertyDoorDTO){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            propertyDoorDTO.setCreateBy(userPropertystaffEntity.getStaffName());
            propertyDoorService.assignUserDoor(propertyDoorDTO);
            resultMap.put("error","0");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error","-1");
        }
        return resultMap;
    }

    /**
     * 保存或更新物业门禁设备
     * author WeiYangDong_2016-11-09
     */
    @RequestMapping(value = "/saveOrUpdatePropertyDoor")
    public Map<String,Object> saveOrUpdatePropertyDoor(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                                       @Valid PropertyDoorDTO propertyDoorDTO){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            int flag = 0;
            if (null != propertyDoorDTO.getDoorId() && !"".equals(propertyDoorDTO.getDoorId())){
                //即将执行更新,需核对门禁设备信息
                flag = propertyDoorService.checkDoor(propertyDoorDTO);
            }else{
                //即将执行新增,需核对地理位置信息
                flag = propertyDoorService.checkPosition(propertyDoorDTO);
            }
            if (flag == 0){
                propertyDoorService.saveOrUpdatePropertyDoor(userPropertystaffEntity, propertyDoorDTO);
                resultMap.put("error","0");
            }else{
                resultMap.put("error","1");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error","-1");
        }
        return resultMap;
    }

    /**
     * 批量导入
     * author WeiYangDong_2016-11-07
     */
    @ResponseBody
    @RequestMapping(value = "/uploadExcel")
    public Map<String,Object> uploadExcel(@ModelAttribute("propertystaff") UserPropertyStaffEntity user, HttpServletRequest httpServletRequest) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
            MultipartFile file = request.getFile("excelFile");
            InputStream fis = file.getInputStream();
            //POI:得到解析Excel的实体集合
            boolean flag = propertyDoorService.importEmployeeByPoi(user,fis);
            if (flag){
                resultMap.put("error", "0");
            }else{
                resultMap.put("error", "-1");
            }
            fis.close();
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error", "-1");
        }
        return resultMap;
    }

    /**
     * 门禁批量导入模板下载 WeiYangDong_2016-12-13
     */
    @RequestMapping(value="/downLoadExcel")
    @ResponseBody
    public String downLoadExcel(HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest,Model model){
        try {
            return propertyDoorService.downLoadTemplate(httpServletRequest, httpServletResponse);
        }catch (Exception e){
            e.printStackTrace();
            return "模板下载错误！";
        }
    }

    /**
     * 批量导出门禁列表数据 WeiYangDong_2016-12-13
     */
    @ResponseBody
    @RequestMapping("/exportDoorListExcel")
    public String exportDoorListExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                              @Valid PropertyDoorDTO propertyDoorDTO,
                              HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest){
        try {
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
            propertyDoorDTO.setRoleScopeList(roleScopeList);
            List<PropertyDoorEntity> propertyDoorList = propertyDoorService.getPropertyDoorList(propertyDoorDTO, null);
            propertyDoorService.exportDoorListExcel(propertyDoorList, httpServletResponse, httpServletRequest);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }

    }

    /**
     * 初始化门禁用户关系数据 WeiYangDong_2016-12-22
     */
    @ResponseBody
    @RequestMapping(value = "/initializeUserDoor/{doorId}",method = RequestMethod.GET)
    public  Map<String,Object> initializeUserDoor(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity
                                                  ,@PathVariable(value = "doorId") String doorId){
        Map<String,Object> resultMap = new HashMap<>();
        PropertyDoorDTO propertyDoorDTO = new PropertyDoorDTO();
        propertyDoorDTO.setDoorId(doorId);
        propertyDoorDTO.setCreateBy(userPropertystaffEntity.getStaffName());
        try{
            propertyDoorService.initializeUserDoor(propertyDoorDTO);
            resultMap.put("error", "0");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error", "-1");
        }
        return resultMap;
    }

    /**
     * 初始化门禁用户关系数据 WeiYangDong_2016-12-22
     */
    @ResponseBody
    @RequestMapping(value = "/initialize/{projectCode}",method = RequestMethod.GET)
    public  Map<String,Object> initialize(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity
            ,@PathVariable(value = "projectCode") String projectCode){
        Map<String,Object> resultMap = new HashMap<>();
        PropertyDoorDTO propertyDoorDTO = new PropertyDoorDTO();
        propertyDoorDTO.setProjectCode(projectCode);
        propertyDoorDTO.setCreateBy(userPropertystaffEntity.getStaffName());
        try{
            propertyDoorService.initialize(propertyDoorDTO);
            resultMap.put("error", "0");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error","-1");
        }
        return resultMap;
    }

    /**
    *  @Author: shanshan
    *  @Description: 用户门禁开门日志列表
    */
    @RequestMapping(value = "/getDoorLogList")
    public String getDoorLogList(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, @Valid PropertyDoorLogDTO propertyDoorLogDTO, Model model, WebPage webPage) {
        //检索条件回显
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),propertyDoorLogDTO.getMenuId());
        model.addAttribute("city", cityList);
        if (null != propertyDoorLogDTO.getScopeId() && !"".equals(propertyDoorLogDTO.getScopeId())) {
            //城市不为空,回显项目列表
            List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), propertyDoorLogDTO.getScopeId(), propertyDoorLogDTO.getMenuId());
            model.addAttribute("projectList", projectList);
        }
        model.addAttribute("propertyDoorLogDTO", propertyDoorLogDTO);

        //获取数据范围权限
        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
        propertyDoorLogDTO.setRoleScopeList(roleScopeList);

        //查询权限范围内的门禁开门日志列表
        List<PropertyDoorLogDTO> list = propertyDoorService.getDoorLogList(propertyDoorLogDTO, webPage);
        model.addAttribute("list", list);
        model.addAttribute("isExcel", list.size());
        return "/log/propertyDoorLog";
    }

    /**
     * 开门日志列表批量导出Excel  Wyd_20170228
     */
    @ResponseBody
    @RequestMapping("/exportDoorLogListExcel")
    public String exportDoorLogListExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                                      @Valid PropertyDoorLogDTO propertyDoorLogDTO,
                                      HttpServletResponse response, HttpServletRequest request){
        try {
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
            propertyDoorLogDTO.setRoleScopeList(roleScopeList);

            String fileName = "开门日志管理列表";
            response.reset();
            response.setContentType("application/x-xls");
            String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            String agent = request.getHeader("USER-AGENT").toLowerCase();
            if (agent.contains("firefox")) {
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xls");
            } else {
                response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".xls");
            }
            String title = "开门日志列表";
            String[] headers = {"序号", "项目名称", "用户", "用户电话", "设备Mac地址", "设备描述", "开门时间"};
            ServletOutputStream out = response.getOutputStream();
            propertyDoorService.exportDoorLogListExcel(title,headers,out,propertyDoorLogDTO);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }

    /**
     * 获取门禁统计数据
     */
    @RequestMapping(value = "/getPropertyDoorStatistics.html")
    public String getPropertyDoorStatistics(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                            @Valid PropertyDoorLogDTO propertyDoorLogDTO,Model model){
        try{
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),propertyDoorLogDTO.getMenuId());
            model.addAttribute("city", cityList);
            if (null != propertyDoorLogDTO.getScopeId() && !"".equals(propertyDoorLogDTO.getScopeId())) {
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), propertyDoorLogDTO.getScopeId(), propertyDoorLogDTO.getMenuId());
                model.addAttribute("projectList", projectList);
            }
            model.addAttribute("propertyDoorLogDTO", propertyDoorLogDTO);
            //执行查询
            if (null != propertyDoorLogDTO.getProjectCode() && !"0".equals(propertyDoorLogDTO.getProjectCode())){
                //楼层
                List<Map<String,Object>> floorList = propertyDoorService.getDoorLogStatisticsByFloor(propertyDoorLogDTO);
                if (null != floorList){
                    String floorStr = "[";
                    String floorName = "[";
                    for (Map<String,Object> floorMap : floorList){
                        floorStr += floorMap.get("num") + ",";
                        if (null != floorMap.get("floor")){
                            floorName += "'"+floorMap.get("floor") + "层',";
                        }else{
                            floorName += "'大门',";
                        }
                    }
                    model.addAttribute("floorStr",floorStr.substring(0,floorStr.length()-1) + "]");
                    model.addAttribute("floorName",floorName.substring(0,floorName.length()-1) + "]");
                }
                //时间段
                List<Map<String,String>> timeList = propertyDoorService.getDoorLogStatisticsByTime(propertyDoorLogDTO);
                if (null != timeList){
                    String timeStr = "[";
                    String timeName = "[";
                    Map<String,String> timeMap = timeList.get(0);
                    Map<String, String> resultMap = sortMapByKey(timeMap);    //按Key进行排序
                    //遍历map中的键
                    for (String key : resultMap.keySet()) {
                        timeName += "'"+key+"',";
                    }
                    //遍历map中的值
                    for (Object value : resultMap.values()) {
                        timeStr += value.toString() + ",";
                    }
                    model.addAttribute("timeStr",timeStr.substring(0,timeStr.length()-1) + "]");
                    model.addAttribute("timeName",timeName.substring(0,timeName.length()-1) + "]");
                }
                //楼栋
                List<Map<String,Object>> buildingList = propertyDoorService.getDoorLogStatisticsByBuilding(propertyDoorLogDTO);
                if (null != buildingList){
                    String buildingStr = "[";
                    String buildingName = "[";
                    for (Map<String,Object> buildingMap : buildingList){
                        if (null != buildingMap.get("buildingRecord")){
                            buildingName += "'"+buildingMap.get("buildingRecord") + "栋',";
                            buildingStr += buildingMap.get("num") + ",";
                        }
                    }
                    model.addAttribute("buildingStr",buildingStr.substring(0,buildingStr.length()-1) + "]");
                    model.addAttribute("buildingName",buildingName.substring(0,buildingName.length()-1) + "]");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/statistics/propertyDoorStatistics";
    }

    /**
     * 使用 Map按key进行排序
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<String, String>(
                new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }

}
