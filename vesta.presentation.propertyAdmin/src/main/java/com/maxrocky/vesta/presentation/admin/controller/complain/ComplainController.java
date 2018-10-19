package com.maxrocky.vesta.presentation.admin.controller.complain;

import com.maxrocky.vesta.application.DTO.PropertyRectifyMagicDTO;
import com.maxrocky.vesta.application.complain.DTO.ComplainDTO;
import com.maxrocky.vesta.application.complain.inf.ComplainService;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.*;
import com.maxrocky.vesta.application.project.inf.SecurityProjectService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

/**
 * Created by Jason on 2017/7/26.
 */
@Controller
@RequestMapping(value = "/complain")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class ComplainController {
    @Autowired
    ComplainService complainService;
    @Autowired
    OrganizeService organizeService;
    @Autowired
    HouseProjectService houseProjectService;
    @Autowired
    HouseRoomService houseRoomService;

    @Autowired
    HouseBuildingService houseBuildingService;

    @Autowired
    HouseUnitService houseUnitService;

    @Autowired
    HouseFloorService houseFloorService;

    @Autowired
    SecurityProjectService securityProjectService;

    @Autowired
    AuthAgencyService authAgencyService;

    @RequestMapping(value = "/complain.html")
    public String getComplainList(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid ComplainDTO complainDTO, Model model, WebPage webPage) {

        Map<String, String> groupList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity,"100000000","0");
        model.addAttribute("groups", groupList);
        if(!StringUtil.isEmpty(complainDTO.getGroupId())){
            Map<String, String> regionList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity,"100000001",complainDTO.getGroupId());
            model.addAttribute("regions", regionList);
        }
        if(!StringUtil.isEmpty(complainDTO.getRegionId())){
            Map<String, String> cityList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity,"100000003",complainDTO.getRegionId());
            model.addAttribute("citys", cityList);
        }
        if(!StringUtil.isEmpty(complainDTO.getCityId())){
            Map<String, String> projectList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity,"100000002",complainDTO.getCityId());
            /*List<String> projectIds = new ArrayList<>();
            Iterator<Map.Entry<String,String>> iterator = projectList.entrySet().iterator();
            while(iterator.hasNext()){
                projectIds.add(iterator.next().getKey());
            }
            List<String> projectNums = houseProjectService.getProjectNumsByProjectIds(projectIds);*/
            model.addAttribute("projects", projectList);
        }
        //根据当前人获取所拥有的项目权限
        /*List<String> projectList = organizeService.getOProjectList(userInformationEntity.getStaffId());
        if (projectList.size() > 0) {
            //for循环截取城市编码集合
            List<String> cityList = new ArrayList<>();
            for (String projectNum : projectList) {
                String a[] = projectNum.split("-");
                cityList.add(a[0]);
            }
            //获取当前登录人权限下城市信息
            Map<String, String> citys = houseProjectService.getCitys(cityList);
            complainDTO.setCitys(citys);
        }
        //根据当前登录人权限和城市编码  获取项目list
        if (!StringUtil.isEmpty(complainDTO.getCity())) {
            Map<String, String> projectMap = houseProjectService.getProjectsMagic(complainDTO.getCity());
            Map<String, String> projects = new HashMap<String, String>();
            List<String> projectCityList = new ArrayList<>();
            for (String projectNum : projectList) {
                String a[] = projectNum.split("-");
                if (complainDTO.getCity().equals(a[0])) {
                    projectCityList.add(projectNum);
                }
            }
            projects.put("", "请选择项目");
            for (String projectNum : projectCityList) {
                projects.put(projectNum, projectMap.get(projectNum));
            }
            complainDTO.setProjects(projects);
        }*/
        //取得地块信息
        if(!StringUtil.isEmpty(complainDTO.getProjectId())){
            String projectNum = houseRoomService.getProjectNumById(complainDTO.getProjectId());
            complainDTO.setProjectNum(projectNum);
            Map<String, String> areas=houseBuildingService.getAreaListByProjectNum(projectNum);
            complainDTO.setAreas(areas);
        }
        // 取得楼栋信息------项目id添加
        if(!StringUtil.isEmpty(complainDTO.getArea())){
            Map<String, String> builds=houseBuildingService.getBuildListByBlockNum(complainDTO.getArea());
            complainDTO.setBuildings(builds);
        }
        // 取得单元列表
        if(null != complainDTO.getBuildingId() && !"".equals(complainDTO.getBuildingId())){
            Map<String, String> units = houseUnitService.getUnitMapByBuildingId(complainDTO.getBuildingId());
            complainDTO.setUnits(units);
        }
        // 取得楼层信息
        if(null != complainDTO.getUnitId() && !"".equals(complainDTO.getUnitId())){
            Map<String, String> floors = houseFloorService.getRoomsByUnitNum(complainDTO.getUnitId());
            complainDTO.setFloors(floors);
        }
        // 取得房间信息
        if(null != complainDTO.getFloorId() && !"".equals(complainDTO.getFloorId())){
            Map<String, String> rooms = houseRoomService.getRoomsByFloorNum(complainDTO.getFloorId());
            complainDTO.setRooms(rooms);
        }
        //根据项目编码获取房间信息
//        if (!StringUtil.isEmpty(complainDTO.getProjectNum())) {
//            Map<String, String> rooms = houseRoomService.getRoomsByProjectNum(complainDTO.getProjectNum());
//            complainDTO.setRooms(rooms);
//        }

        List<String> function=authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","1");
        CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
        if(function!=null){
            //搜索  QCH40010090   导出Excel   QCH40010091   详情   QCH40010092
            for (int i = 0; i < function.size(); i++) {
                switch (function.get(i).toString()) {
                    case "QCH40010090":
                        checkAuthFunctionDTO.setQch40010090("Y");
                        break;
                    case "QCH40010091":
                        checkAuthFunctionDTO.setQch40010091("Y");
                        break;
                    case "QCH40010092":
                        checkAuthFunctionDTO.setQch40010092("Y");
                        break;
                }
            }
        }

        List<ComplainDTO> complainDTOList = complainService.getComplainList(complainDTO, webPage, userInformationEntity);
        model.addAttribute("complainList", complainDTOList);
        model.addAttribute("complain", complainDTO);
        model.addAttribute("typeMaps", complainDTO);
        model.addAttribute("function", checkAuthFunctionDTO);

        return "/complain/ComplainList";
    }

    /**
     * 根据用户和项目层级
     *
     * @param complainDTO
     * @return
     */
    @RequestMapping(value = "/getQCAuthAgency")
    public ApiResult getQCAuthAgency(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                     @Valid ComplainDTO complainDTO) {
        if("100000002".equals(complainDTO.getPtype())){
            Map map = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity,complainDTO.getPtype(),complainDTO.getCityId());
            List<String> projectIds = new ArrayList<>();
            Iterator<Map.Entry<String,String>> iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                projectIds.add(iterator.next().getKey());
            }
            Map<String,String> projectNums = houseProjectService.getProNumsByProIds(projectIds);
            return new SuccessApiResult(projectNums);
        }
        if("100000003".equals(complainDTO.getPtype())){
            Map map = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity,complainDTO.getPtype(),complainDTO.getRegionId());
            return new SuccessApiResult(map);
        }else {
            Map map = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity,complainDTO.getPtype(),complainDTO.getGroupId());
            return new SuccessApiResult(map);
        }
    }
    /**
     * 投诉单详情接口
     *
     * @param complainId
     * @return
     */
    @RequestMapping(value = "/getComplainInfoById")
    public String getComplainInfoById(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, @RequestParam String complainId) {
        ComplainDTO complainDTO = complainService.getComplainInfoById(userInformationEntity, complainId);
        model.addAttribute("complainInfo", complainDTO);
        return "/complain/ComplainDetail";
    }

    /**
     * 按当前登录人权限和城市编码获取项目下拉框
     */
    @RequestMapping(value = "/getProjectListByCityNum")
    public ApiResult getProjectListByCityNum(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                             @Valid ComplainDTO complainDTO) {
        //根据当前人获取所拥有的项目权限
        List<String> projectList = organizeService.getOProjectList(userPropertystaffEntity.getStaffId());
        //根据当前登录人权限和城市编码  获取项目list
        Map<String, String> projects = new HashMap<String, String>();
        projects.put("", "请选择项目");
        if (!StringUtil.isEmpty(complainDTO.getCity())) {
            Map<String, String> projectMap = houseProjectService.getProjectsMagic(complainDTO.getCity());
            List<String> projectCityList = new ArrayList<>();
            for (String projectNum : projectList) {
                String a[] = projectNum.split("-");
                if (complainDTO.getCity().equals(a[0])) {
                    projectCityList.add(projectNum);
                }
            }
            for (String projectNum : projectCityList) {
                projects.put(projectNum, projectMap.get(projectNum));
            }
        }
        return new SuccessApiResult(projects);
    }

    /**
     * 按当前项目获取房间
     */
    @RequestMapping(value = "/getRoomListByProjectNum")
    public ApiResult getRoomListByProjectNum(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                             @Valid ComplainDTO complainDTO) {
        Map<String, String> rooms = houseRoomService.getRoomsByProjectNum(complainDTO.getProjectNum());
        return new SuccessApiResult(rooms);
    }
    /**
     * 导出EXCEL
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportExcels", method = RequestMethod.GET)
    public void exportExcels(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, HttpServletRequest request, HttpServletResponse response, @Valid ComplainDTO complainDTO) throws Exception {
        String fileName = "投诉单信息";
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
        String title = "投诉单EXCEL文档";
        String[] headers = {"序号", "项目名称", "房间信息", "投诉描述", "单据状态", "创建人", "投诉人", "限时答复时间", "投诉时间"};

        ServletOutputStream out = response.getOutputStream();
        complainService.exportExcel(title, headers, out, complainDTO,userInformationEntity);
    }
}
