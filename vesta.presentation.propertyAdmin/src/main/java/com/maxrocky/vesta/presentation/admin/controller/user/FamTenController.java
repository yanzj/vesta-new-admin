package com.maxrocky.vesta.presentation.admin.controller.user;

import com.maxrocky.vesta.application.DTO.admin.*;
import com.maxrocky.vesta.application.inf.HouseInfoService;
import com.maxrocky.vesta.application.inf.HouseUserBookService;
import com.maxrocky.vesta.application.inf.UserInfoService;
import com.maxrocky.vesta.application.inf.UserOwnerService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.HouseUserBookEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by zhanghj on 2016/2/2.
 */
@Controller
@RequestMapping(value = "/user")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})

public class FamTenController {

    @Autowired
    private UserOwnerService userOwnerService;
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private HouseUserBookService houseUserBookService;

    @Autowired
    private HouseInfoService houseInfoService;

    /**
     * 初始化业主列表
     * @param userPropertystaffEntity
     * @param userOwnerDTO
     * @param webPage
     * @param model
     * @return
     */
    @RequestMapping(value = "/famtenManage.html",method = RequestMethod.GET)
    public String listFamTen(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid UserOwnerDTO userOwnerDTO,WebPage webPage,Model model){

        //初始化项目下拉框
        List<ProjectSelDTO> projectSelDTOs = userOwnerService.getProjectSel(userPropertystaffEntity.getProjectId());
        model.addAttribute("projectSelDTOs", projectSelDTOs);
        if (projectSelDTOs.size()>0) {
            //初始化楼栋列表
            List<BuildingSelDTO> buildingSelDTOs = userOwnerService.getBuildSel(projectSelDTOs.get(0).getProjectId());
            model.addAttribute("buildingSelDTOs",buildingSelDTOs);
            if (buildingSelDTOs.size()>0){
                //初始化单元列表
                List<UnitSelDTO> unitSelDTOs = userOwnerService.getUnitSel(buildingSelDTOs.get(0).getBuildingId());
                model.addAttribute("unitSelDTOs",unitSelDTOs);
                if (unitSelDTOs.size()>0){
                    List<RoomDTO> roomDTOs = userOwnerService.getRoomSel(unitSelDTOs.get(0).getUnitId());
                    model.addAttribute("roomDTOs",roomDTOs);
                }
            }
        }
        //获取业主列表
        userOwnerDTO.setProjectIdDto(userPropertystaffEntity.getQueryScope());//按照管理员所属项目权限
        List<UserOwnerDTO> userDTOs = userOwnerService.listOwnerDto(userOwnerDTO, webPage, HouseUserBookEntity.USER_TYPE_FAMILY+HouseUserBookEntity.USER_TYPE_TENANT);


        model.addAttribute("userDTOs",userDTOs);      //列表
        model.addAttribute("userOwnerDTO", userOwnerDTO);        //搜索栏条件
        return "/user/FamTenManage";
    }


    /**
     * 删除家属或租户关系
     * @param userPropertystaffEntity
     * @param houseUserid
     * @return
     */
    @RequestMapping(value = "/delFamTen",method = RequestMethod.GET)
    public ModelAndView delFamTen(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Param String houseUserid){

        String result = userOwnerService.delFamTen(houseUserid);
        UserTenantDTO userTenantDTO = new UserTenantDTO();
        userTenantDTO.setTnUserId(result);
        if (!result.equals("")){
            userOwnerService.updateType(userTenantDTO);
        }

        return new ModelAndView("redirect:/user/famtenManage.html");
    }



    /**
     * 新增租户初始页
     * */
    @RequestMapping(value = "/tenant.html")
    public String tentantManage(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,Model model) {
        List<FormatSelDTO> formatSelDTOs = userOwnerService.getFormatSel(userPropertystaffEntity.getProjectId());    //业态下拉框
        model.addAttribute("formatList", formatSelDTOs);
        model.addAttribute("projectId", userPropertystaffEntity.getProjectId());
        if(formatSelDTOs!=null){
            List<BuildingSelDTO> buildingSelDTOs = userOwnerService.getformatBuildSel(userPropertystaffEntity.getProjectId(),formatSelDTOs.get(0).getFormatId());  //楼栋下拉框
            model.addAttribute("buildingList", buildingSelDTOs);
            if (buildingSelDTOs != null) {
                List<UnitSelDTO> unitSelDTOs = userOwnerService.getUnitSel(buildingSelDTOs.get(0).getBuildingId());  //单元下拉框
                model.addAttribute("unitList",unitSelDTOs);
                if (unitSelDTOs != null) {
                    List<RoomDTO> roomDTOs = userOwnerService.getRoomSel(unitSelDTOs.get(0).getUnitId());  //房间号下拉框
                    model.addAttribute("roomList",roomDTOs);
                }
            }
        }
        return "/user/AddTenant";
    }

    /**
     * 新增租户
     * */
    @RequestMapping(value = "addTenant.html")
    public String addTenant(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid UserTenantDTO userTenantDTO,Model model){
        List<FormatSelDTO> formatSelDTOs = userOwnerService.getFormatSel(userPropertystaffEntity.getProjectId());    //业态下拉框
        model.addAttribute("formatList",formatSelDTOs);
        model.addAttribute("projectId",userPropertystaffEntity.getProjectId());
        if(formatSelDTOs!=null){
            List<BuildingSelDTO> buildingSelDTOs = userOwnerService.getformatBuildSel(userPropertystaffEntity.getProjectId(),formatSelDTOs.get(0).getFormatId());  //楼栋下拉框
            model.addAttribute("buildingList",buildingSelDTOs);
            if (buildingSelDTOs != null) {
                List<UnitSelDTO> unitSelDTOs = userOwnerService.getUnitSel(buildingSelDTOs.get(0).getBuildingId());  //单元下拉框
                model.addAttribute("unitList",unitSelDTOs);
                if (unitSelDTOs != null) {
                    List<RoomDTO> roomDTOs = userOwnerService.getRoomSel(unitSelDTOs.get(0).getUnitId());  //房间号下拉框
                    model.addAttribute("roomList",roomDTOs);
                }
            }
        }
        UserTenantDTO usrTen = userOwnerService.addFamTen(userTenantDTO,userPropertystaffEntity);
        usrTen.setDtoRealName(userTenantDTO.getDtoRealName());
        usrTen.setDtoMobile(userTenantDTO.getDtoMobile());
//        model.addAttribute("result",usrTen.getTnResult());
        userOwnerService.updateType(usrTen);
        model.addAttribute("userTenantDTO",usrTen);
        return "/user/AddTenant";
    }

    /**
     * 更改缴费状态
     * @param userPropertystaffEntity
     * @param houseuserid
     * @param
     * @return
     */
    @RequestMapping(value ="/turnPayType")
    public ApiResult updateIsPay(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@RequestParam String houseuserid){

        String updResult = userOwnerService.updatePayType(houseuserid);

        return new SuccessApiResult(updResult);
    }

}

