package com.maxrocky.vesta.presentation.admin.controller.house;

import com.maxrocky.vesta.application.DTO.admin.HouseRoomTypeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.*;
import com.maxrocky.vesta.application.project.inf.SecurityProjectService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mql on 2016/6/4.
 */
@Controller
@RequestMapping(value = "/houseroomtype")
@SessionAttributes(types={UserInformationEntity.class,String.class},value = {"authPropertystaff","menulist","secanViewlist"})
public class HouseRoomTypeController {

    @Autowired
    HouseRoomTypeService houseRoomTypeService;

    @Autowired
    HouseInfoService houseInfoService;

    @Autowired
    HouseProjectService houseProjectService;

    @Autowired
    HouseBuildingService houseBuildingService;

    @Autowired
    HouseUnitService houseUnitService;

    @Autowired
    HouseFloorService houseFloorService;

    @Autowired
    HouseTypeService houseTypeService;

    @Autowired
    HouseRoomService houseRoomService;
   @Autowired
    AuthAgencyService authAgencyService;
    @Autowired
    SecurityProjectService securityProjectService;
    @Autowired
    OrganizeService organizeService;

    @RequestMapping(value = "/houseRoomTypeManage.html")
    public String houseRoomTypeManage(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity, @Valid HouseRoomTypeDTO houseRoomTypeDTO, WebPage webPage, Model model){


        List<HouseRoomTypeDTO> roomTypeList = null;
        if(houseRoomTypeDTO.getCityId() == null ||"".equals(houseRoomTypeDTO.getCityId())
                ||houseRoomTypeDTO.getAreaId() == null || "".equals(houseRoomTypeDTO.getAreaId())
                ||houseRoomTypeDTO.getProjectId() == null || "".equals(houseRoomTypeDTO.getProjectId())
                || houseRoomTypeDTO.getBuildingId() == null || "".equals(houseRoomTypeDTO.getBuildingId())
                ||houseRoomTypeDTO.getUnitId() == null || "".equals(houseRoomTypeDTO.getUnitId())){
            roomTypeList = new ArrayList<HouseRoomTypeDTO>();
        }else{
            houseRoomTypeDTO.setProjectId(houseRoomTypeDTO.getProjectId().replace("@$@", "#"));
            houseRoomTypeDTO.setBuildingId(houseRoomTypeDTO.getBuildingId().replace("@$@", "#"));
            houseRoomTypeDTO.setUnitId(houseRoomTypeDTO.getUnitId().replace("@$@", "#"));
            roomTypeList = houseRoomTypeService.getHouseTypeList(houseRoomTypeDTO);
//            model.addAttribute("projects",houseBuildingService.getProjectListByCityNum(houseRoomTypeDTO.getCityId()));
            //项目
            Map<String, String> projectIdList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000002", houseRoomTypeDTO.getCityId());
            Map<String, Object> projectList = organizeService.getProjectNumListProjectId(projectIdList);
            model.addAttribute("projects", projectList);
            model.addAttribute("areaList", houseBuildingService.getAreaListByProjectNum(houseRoomTypeDTO.getProjectId()));
            Map map = houseBuildingService.getBuildListByBlockNum(houseRoomTypeDTO.getAreaId());
            model.addAttribute("buildingList",map);
            model.addAttribute("buildSelectName",map.get(houseRoomTypeDTO.getBuildingId()));
            model.addAttribute("unitList",houseUnitService.getUnitMapByBuildingId(houseRoomTypeDTO.getBuildingId()));
        }

        List<HouseRoomTypeDTO> floorRoomList = houseRoomTypeService.getFloorRooms(houseRoomTypeDTO);
        List<HouseRoomTypeDTO> floorList = houseRoomTypeService.getFloorListByUnitId(houseRoomTypeDTO);
//        Map projects =houseProjectService.getProjectsNum();
//        Map citys=houseProjectService.getcity();
        Map<String, String> groupList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000000", "0");
        model.addAttribute("groups", groupList);
        if (!StringUtil.isEmpty(houseRoomTypeDTO.getGroupId())) {
            //区域
            Map<String, String> regionList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000001", houseRoomTypeDTO.getGroupId());
            model.addAttribute("regions", regionList);
        }
        if (!StringUtil.isEmpty(houseRoomTypeDTO.getRegionId())) {
            //城市
            Map<String, String> cityList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000003", houseRoomTypeDTO.getRegionId());
            model.addAttribute("citys", cityList);
        }
        model.addAttribute("roomTypeList",roomTypeList);
        model.addAttribute("floorRoomList",floorRoomList);
        model.addAttribute("floorList",floorList);
//        model.addAttribute("projects", projects);
//        model.addAttribute("citys",citys);

        model.addAttribute("houseRoomTypeDTO", houseRoomTypeDTO);

        return "/house/houseRoomTypeManage";
    }


    @RequestMapping(value = "/houseRoomLabel")
    public String huseRoomLabel(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,@Valid HouseRoomTypeDTO houseRoomTypeDTO,Model model,WebPage webPage) {
//        model.addAttribute("houseTypeList",houseTypeService.getHouseTypeMap());
        model.addAttribute("allHouseType",houseTypeService.getHouseTypeAll(houseRoomTypeDTO.getHouseType(),webPage));
        CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
        //获取功能点
        List<String> function = authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(), "4", "1");
        if (function != null) {
            //校验是否有  qch40010064; //保存-点击蓝色房间设置
            for (int i = 0; i < function.size(); i++) {
                switch (function.get(i).toString()) {
                    case "QCH40010064":
                        checkAuthFunctionDTO.setQch40010064("Y");
                        break;
                }
            }
        }
        model.addAttribute("function", checkAuthFunctionDTO);
        model.addAttribute("houseRoomTypeDTO", houseRoomTypeDTO);
        return "/house/houseRoomLabel";
    }

    @RequestMapping(value = "/houseRoomLabelSave",method = RequestMethod.POST)
    public String houseRoomLabelSave(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,@Valid HouseRoomTypeDTO houseRoomTypeDTO,Model model) {
//        if(houseRoomTypeDTO.getUnitId() != null){
//            houseRoomTypeDTO.setUnitId(houseRoomTypeDTO.getUnitId().replace("@$@", "#"));
//        }
//        if(houseRoomTypeDTO.getRoomId() != null){
//            houseRoomTypeDTO.setRoomId(houseRoomTypeDTO.getRoomId().replace("@$@", "#"));
//        }
//        if(houseRoomTypeDTO.getBuildingId() != null){
//            houseRoomTypeDTO.setBuildingId(houseRoomTypeDTO.getBuildingId().replace("@$@", "#"));
//        }
//        if(houseRoomTypeDTO.getRoomNum() != null){
//            houseRoomTypeDTO.setRoomNum(houseRoomTypeDTO.getRoomNum().replace("@$@", "#"));
//        }
//        houseRoomTypeService.saveRoomTypeLabel(houseRoomTypeDTO);
//
//        List<HouseRoomTypeDTO> roomTypeList = null;
//        if(houseRoomTypeDTO.getProjectId() == null || "".equals(houseRoomTypeDTO.getProjectId())
//                || houseRoomTypeDTO.getBuildingId() == null || "".equals(houseRoomTypeDTO.getBuildingId())
//                ||houseRoomTypeDTO.getUnitId() == null || "".equals(houseRoomTypeDTO.getUnitId())){
//            roomTypeList = new ArrayList<HouseRoomTypeDTO>();
//        }else{
//            roomTypeList = houseRoomTypeService.getHouseTypeList(houseRoomTypeDTO);
//            model.addAttribute("areaList", houseBuildingService.getAreaListByProjectNum(houseRoomTypeDTO.getProjectId()));
//            model.addAttribute("buildingList", houseBuildingService.getBuildListByProjectNum(houseRoomTypeDTO.getProjectId()));
//            model.addAttribute("unitList",houseUnitService.getUnitMapByBuildingId(houseRoomTypeDTO.getBuildingId()));
//        }
//
//        List<HouseRoomTypeDTO> floorRoomList = houseRoomTypeService.getFloorRooms(houseRoomTypeDTO);
//        List<HouseRoomTypeDTO> floorList = houseRoomTypeService.getFloorListByUnitId(houseRoomTypeDTO);
//        Map projects =houseProjectService.getProjectsNum();
//        Map citys=houseProjectService.getcity();
//        model.addAttribute("citys",citys);
//        model.addAttribute("roomTypeList",roomTypeList);
//        model.addAttribute("floorRoomList",floorRoomList);
//        model.addAttribute("floorList",floorList);
//        model.addAttribute("projects",projects);
//        model.addAttribute("houseRoomTypeDTO", houseRoomTypeDTO);
//



        if(houseRoomTypeDTO.getUnitId() != null){
            houseRoomTypeDTO.setUnitId(houseRoomTypeDTO.getUnitId().replace("@$@", "#"));
        }
        if(houseRoomTypeDTO.getRoomId() != null){
            houseRoomTypeDTO.setRoomId(houseRoomTypeDTO.getRoomId().replace("@$@", "#"));
        }
        if(houseRoomTypeDTO.getBuildingId() != null){
            houseRoomTypeDTO.setBuildingId(houseRoomTypeDTO.getBuildingId().replace("@$@", "#"));
        }
        if(houseRoomTypeDTO.getRoomNum() != null){
            houseRoomTypeDTO.setRoomNum(houseRoomTypeDTO.getRoomNum().replace("@$@", "#"));
        }
        houseRoomTypeService.saveRoomTypeLabel(houseRoomTypeDTO);

        List<HouseRoomTypeDTO> roomTypeList = null;
        if(houseRoomTypeDTO.getProjectId() == null || "".equals(houseRoomTypeDTO.getProjectId())
                || houseRoomTypeDTO.getBuildingId() == null || "".equals(houseRoomTypeDTO.getBuildingId())
                ||houseRoomTypeDTO.getUnitId() == null || "".equals(houseRoomTypeDTO.getUnitId())){
            roomTypeList = new ArrayList<HouseRoomTypeDTO>();
        }else{
            roomTypeList = houseRoomTypeService.getHouseTypeList(houseRoomTypeDTO);
//            model.addAttribute("projects",houseBuildingService.getProjectListByCityNum(houseRoomTypeDTO.getCityId()));
            //项目
            Map<String, String> projectIdList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000002", houseRoomTypeDTO.getCityId());
            Map<String, Object> projectList = organizeService.getProjectNumListProjectId(projectIdList);
            model.addAttribute("projects", projectList);
            model.addAttribute("areaList", houseBuildingService.getAreaListByProjectNum(houseRoomTypeDTO.getProjectId()));
            Map map = houseBuildingService.getBuildListByBlockNum(houseRoomTypeDTO.getAreaId());
            model.addAttribute("buildingList",map);
            model.addAttribute("buildSelectName",map.get(houseRoomTypeDTO.getBuildingId()));
            model.addAttribute("unitList",houseUnitService.getUnitMapByBuildingId(houseRoomTypeDTO.getBuildingId()));
        }

        List<HouseRoomTypeDTO> floorRoomList = houseRoomTypeService.getFloorRooms(houseRoomTypeDTO);
        List<HouseRoomTypeDTO> floorList = houseRoomTypeService.getFloorListByUnitId(houseRoomTypeDTO);
        Map<String, String> groupList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000000", "0");
        model.addAttribute("groups", groupList);
        if (!StringUtil.isEmpty(houseRoomTypeDTO.getGroupId())) {
            //区域
            Map<String, String> regionList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000001", houseRoomTypeDTO.getGroupId());
            model.addAttribute("regions", regionList);
        }
        if (!StringUtil.isEmpty(houseRoomTypeDTO.getRegionId())) {
            //城市
            Map<String, String> cityList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000003", houseRoomTypeDTO.getRegionId());
            model.addAttribute("citys", cityList);
        }
        model.addAttribute("roomTypeList",roomTypeList);
        model.addAttribute("floorRoomList",floorRoomList);
        model.addAttribute("floorList",floorList);
        model.addAttribute("houseRoomTypeDTO", houseRoomTypeDTO);
        return "/house/houseRoomTypeManage";
    }


    /**
     * 根据城市编码获取项目列表
     * @param houseRoomTypeDTO
     * @return
     */
    @RequestMapping(value="/getProjectList")
    public ApiResult getProjectList(@Valid HouseRoomTypeDTO houseRoomTypeDTO){
        Map map =houseBuildingService.getProjectListByCityNum(houseRoomTypeDTO.getCityId());
        return new SuccessApiResult(map);
    }


    /**
     * 根据项目编码获取地块列表
     * @param houseRoomTypeDTO
     * @return
     */
    @RequestMapping(value="/getAreaList")
    public ApiResult getAreaList(@Valid HouseRoomTypeDTO houseRoomTypeDTO){
        Map map =houseBuildingService.getProjectListByCityNum(houseRoomTypeDTO.getCityId());
        return new SuccessApiResult(map);
    }
    /**
     * 根据项目编码获取楼栋列表
     * @param houseRoomTypeDTO
     * @return
     */
    @RequestMapping(value="/getBuildingList")
    public ApiResult getBuildingList(@Valid HouseRoomTypeDTO houseRoomTypeDTO){
        Map map =houseBuildingService.getBuildListByProjectNum(houseRoomTypeDTO.getProjectId());
        return new SuccessApiResult(map);
    }

    /**
     * 根据项目编码获取楼栋列表
     * @param houseRoomTypeDTO
     * @return
     */
    @RequestMapping(value="/getBuildingListByProject")
    public ApiResult getBuildingListByProject(@Valid HouseRoomTypeDTO houseRoomTypeDTO){
        Map map =houseBuildingService.getBuildListByProjectId(houseRoomTypeDTO.getProjectId());
        return new SuccessApiResult(map);
    }

    /**
     * 根据项目编码获取地块列表 WeiYangDong_2016-11-04
     * @param houseRoomTypeDTO
     * @return
     */
    @RequestMapping(value="/getAreaListByProject")
    public ApiResult getAreaListByProject(@Valid HouseRoomTypeDTO houseRoomTypeDTO){
        Map map =houseBuildingService.getAreaListByProjectNum(houseRoomTypeDTO.getProjectId());
        return new SuccessApiResult(map);
    }

    /**
     * 根据项目编码获取地块列表
     * @param houseRoomTypeDTO
     * @return
     */
    @RequestMapping(value="/getAreaListByProjectId")
    public ApiResult getAreaListByProjectId(@Valid HouseRoomTypeDTO houseRoomTypeDTO){
        String projectNum = houseRoomService.getProjectNumById(houseRoomTypeDTO.getProjectId());
        Map map =houseBuildingService.getAreaListByProjectNum(projectNum);
        return new SuccessApiResult(map);
    }

    /**
     * 根据地块编码获取楼栋列表 WeiYangDong_2016-11-04
     * @param houseRoomTypeDTO
     * @return
     */
    @RequestMapping(value="/getBuildingListByArea")
    public ApiResult getBuildingListByArea(@Valid HouseRoomTypeDTO houseRoomTypeDTO){
        Map map =houseBuildingService.getBuildListByBlockNum(houseRoomTypeDTO.getAreaId());
        return new SuccessApiResult(map);
    }

    /**
     * 根据楼栋编码获取单元列表
     * @param houseRoomTypeDTO
     * @return
     */
    @RequestMapping(value="/getUnitList")
    public ApiResult getUnitList(@Valid HouseRoomTypeDTO houseRoomTypeDTO) {
        return new SuccessApiResult(houseUnitService.getUnitMapByBuildingId(houseRoomTypeDTO.getBuildingId()));
    }


    /**
     * 根据单元ID获取楼层列表
     * @param houseRoomTypeDTO
     * @return
     */
    @RequestMapping(value="/getFloorList")
    public ApiResult getFloorList(@Valid HouseRoomTypeDTO houseRoomTypeDTO) {
        return new SuccessApiResult(houseFloorService.getRoomsByUnitId(houseRoomTypeDTO.getUnitId()));
    }

    /**
     * 根据单元ID获取楼层列表
     * @param houseRoomTypeDTO
     * @return
     */
    @RequestMapping(value="/getFloorListByNum")
    public ApiResult getFloorListByNum(@Valid HouseRoomTypeDTO houseRoomTypeDTO) {
        return new SuccessApiResult(houseFloorService.getRoomsByUnitNum(houseRoomTypeDTO.getUnitId()));
    }


    /**
     * 根据楼层ID获取房间列表
     * @param houseRoomTypeDTO
     * @return
     */
    @RequestMapping(value="/getRoomList")
    public ApiResult getRoomList(@Valid HouseRoomTypeDTO houseRoomTypeDTO) {
        return new SuccessApiResult(houseRoomService.getRoomsByFloorId(houseRoomTypeDTO.getFloor()));
    }

    /**
     * 根据楼层Num获取房间列表
     * @param houseRoomTypeDTO
     * @return
     */
    @RequestMapping(value="/getRoomListByNum")
    public ApiResult getRoomListByNum(@Valid HouseRoomTypeDTO houseRoomTypeDTO) {
        return new SuccessApiResult(houseRoomService.getRoomsByFloorNum(houseRoomTypeDTO.getFloor()));
    }


}
