package com.maxrocky.vesta.presentation.admin.controller.community;

import com.maxrocky.vesta.application.admin.dto.CommunityAppointManageDto;
import com.maxrocky.vesta.application.admin.dto.CommunityAppointPageMapDto;
import com.maxrocky.vesta.application.admin.dto.UserAppointDto;
import com.maxrocky.vesta.application.inf.HouseInfoService;
import com.maxrocky.vesta.application.inf.HouseProjectService;
import com.maxrocky.vesta.application.service.CommunityCenterService;
import com.maxrocky.vesta.application.service.CommunityHouseAppointService;
import com.maxrocky.vesta.application.service.CommunityHouseBatchService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/3/31
 * Time: 10:21
 * Describe:
 */
//@RestController
@Controller
@RequestMapping(value = "/communityArea")
public class CommunityCenterController {

    /* 交付批次服务 */
    @Autowired
    CommunityHouseAppointService communityHouseAppointService;
    /* 项目（社区）服务 */
    @Autowired
    HouseProjectService houseProjectService;

    @Autowired
    CommunityCenterService communityCenterService;

    @Autowired
    CommunityHouseBatchService communityHouseBatchService;

    @Autowired
    HouseInfoService houseInfoService;



    /**
     * 交付批次管理列表
     * @param communityAppointManageDto
     * @param model
     * @param webPage
     * @return
     */
        @RequestMapping(value = "/HousePayBatch.html")
        public String gotoPage(@Valid CommunityAppointManageDto communityAppointManageDto,Model model,WebPage webPage) throws Exception {
            // 初始化

            //封装前台导航栏MAP
            CommunityAppointPageMapDto communityAppointPageMapDto = this.communityHouseBatchService.getMap();
            model.addAttribute("communityAppointPageMapDto",communityAppointPageMapDto);


        //#1.分页查询信息
        List<CommunityAppointManageDto> communityAppointManageDtos = this.communityHouseBatchService.queryAllDeliveryBatch(communityAppointManageDto, webPage);
        model.addAttribute("communityAppointManageDtos", communityAppointManageDtos);
        model.addAttribute("communityAppointManageDto", communityAppointManageDto);
        return "/community/HousePayBatch";
    }
    /**
     * 预约管理页面查询
     * @param housePayBatchQueryDto
     * @param model
     * @param webPage
     * @return
     * @throws Exception
     */
    /*@RequestMapping(value = "/AppointManage.html")
    public String gotoPage2(@Valid HousePayBatchQueryDto housePayBatchQueryDto,Model model,WebPage webPage) throws Exception {

        *//* 获取项目（社区）列表 *//*
        List<ProjectReturnJsonDTO> projectList = new ArrayList<ProjectReturnJsonDTO>();
        *//* 添加默认选项 *//*
        projectList.add(new ProjectReturnJsonDTO("0", "全部"));
        projectList.addAll(houseProjectService.getDTOList());

        *//* 获取交付批次 照着样子自己写 O(∩_∩)O哈哈~ 我上午要去见客户拿项目，得睡觉哈 *//*


        *//* 获取状态 考虑以后还可能增加状态，所以不在页面写，可以统一写在一个地方 *//*
        List<HousePayBatchStatusDto> housePayBatchStatusDtoList = new ArrayList<HousePayBatchStatusDto>();
        housePayBatchStatusDtoList.add(new HousePayBatchStatusDto(999, "全部"));
        housePayBatchStatusDtoList.add(new HousePayBatchStatusDto(0 ,"进行中"));
        housePayBatchStatusDtoList.add(new HousePayBatchStatusDto(1, "已完成"));

        *//* 查询交付信息 *//*
        List<CommunityAppointManageDto> communityAppointList = this.communityHouseAppointService.getCommunityAppointList(housePayBatchQueryDto, webPage);

        model.addAttribute("projectList", projectList);

        model.addAttribute("housePayBatchStatusDtoList", housePayBatchStatusDtoList);

        model.addAttribute("communityAppointList", communityAppointList);

        model.addAttribute("housePayBatchQueryDto", housePayBatchQueryDto);
        return "/community/AppointManage";
    }*/

    /**
     * 跳转到添加页面
     */
    @RequestMapping(value = "/addPage")
    public String addPage(@Valid CommunityAppointManageDto communityAppointManageDto,Model model){
        //获取项目集合
        //封装前台导航栏MAP
        try {
            CommunityAppointPageMapDto communityAppointPageMapDto = this.communityHouseBatchService.getMap();
            model.addAttribute("communityAppointPageMapDto",communityAppointPageMapDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/community/HousePayBatchAdd";
    }
    /**
     * 交付预约批次管理 点击添加
     * @param model
     * @return
     */
    @RequestMapping(value = "/saveBatchManage", method = RequestMethod.POST)
    public ModelAndView addPropertyServices(@Valid CommunityAppointManageDto communityAppointManageDto,@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntit,Model model){
        boolean batchManage = false;
        try {
            batchManage = this.communityHouseBatchService.saveBatchManage(communityAppointManageDto,userPropertystaffEntit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:../communityArea/HousePayBatch.html");
    }
    /**
     * 跳转到修改页面
     */
    @RequestMapping(value = "/updatePage")
    public String updatePage(@Valid String id,Model model){
        try {
            CommunityAppointPageMapDto communityAppointPageMapDto = this.communityHouseBatchService.getMap();
            model.addAttribute("communityAppointPageMapDto",communityAppointPageMapDto);

            //根据id获取属性值
            CommunityAppointManageDto batchManage = this.communityHouseBatchService.getBatchManage(id);

            model.addAttribute("batchManage",batchManage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/community/HousePayBatchUpdate";
    }
    /**
     * 交付预约批次管理 点击修改
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateBatchManage", method = RequestMethod.POST)
    public ModelAndView updatePropertyServices(@Valid CommunityAppointManageDto communityAppointManageDto,@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntit,Model model){
        boolean flag = false;
        try {
            if(communityAppointManageDto != null){
                flag = this.communityHouseBatchService.updateBatchManage(communityAppointManageDto, userPropertystaffEntit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:../communityArea/HousePayBatch.html");
    }
    /**
     * 交付预约批次管理 点击删除
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteBatchManage", method = RequestMethod.GET)
    public ModelAndView deletePropertyServices(@Valid CommunityAppointManageDto communityAppointManageDto,@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntit,Model model){
        boolean batchManage = false;
        try {
            batchManage = this.communityHouseBatchService.deleteBatchManage(communityAppointManageDto, userPropertystaffEntit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:../communityArea/HousePayBatch.html");
    }


    /**
     * 用于预约列表
     * @param userAppointDto
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/UserAppoint.html")
    public String userAppoint(UserAppointDto userAppointDto,Model model,WebPage webPage){
        LinkedHashMap<String,String> projects = houseInfoService.listProject();
        model.addAttribute("projects",projects);
        LinkedHashMap<String,String> builds = houseInfoService.listBuild();
        model.addAttribute("builds",builds);
        LinkedHashMap<String,String> units = houseInfoService.listUnit();
        model.addAttribute("units",units);
        List<UserAppointDto> userAppointDtos = communityHouseAppointService.listAppoints(userAppointDto,webPage);
        model.addAttribute("userAppointDtos",userAppointDtos);
        model.addAttribute("userAppointDto",userAppointDto);


        return "/community/UserAppoint";
    }

    /**
     *  根据交付计划ID 删除信息
     * @param userPropertystaffEntity
     * @param housepayID
     * @return
     */
    @RequestMapping(value = "/deleteHousePay")
    public ApiResult removePropertyAnnouncement(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity ,@RequestParam String housepayID)
    {
        String propertyAnnouncement = communityHouseAppointService.removeHousePayById(housepayID, userPropertystaffEntity);
        return new SuccessApiResult(propertyAnnouncement);
    }

}
