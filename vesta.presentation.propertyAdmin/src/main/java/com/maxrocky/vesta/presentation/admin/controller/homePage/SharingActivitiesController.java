package com.maxrocky.vesta.presentation.admin.controller.homePage;

import com.maxrocky.vesta.application.DTO.PropertyCompanyMapDTO;
import com.maxrocky.vesta.application.DTO.SharingActivitiesDTO;
import com.maxrocky.vesta.application.DTO.SharingActivitiesImgDTO;
import com.maxrocky.vesta.application.DTO.SharingActivitiesSearchDTO;
import com.maxrocky.vesta.application.inf.PropertyCompanyService;
import com.maxrocky.vesta.application.inf.SharingActivitieService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * Created by sunmei on 2016/2/21.
 */
@Controller
@RequestMapping(value = "/activities")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class SharingActivitiesController {
    @Autowired
    private SharingActivitieService sharingActivitiesService;
    @Autowired
    private PropertyCompanyService propertyCompanyService;
    @RequestMapping(value = "/activitiesManage.html")
    public String gotoPage(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff,@Valid SharingActivitiesSearchDTO sharingActivitiesSearchDTO,Model model,WebPage webPage){
        // 初始化 登陆人所负责范围
        sharingActivitiesSearchDTO.setQueryScope(userPropertystaff.getQueryScope());
        sharingActivitiesSearchDTO.setPropertyProject(userPropertystaff.getProjectId());
        List<SharingActivitiesDTO> ActivitiesManageList =sharingActivitiesService.ActivitiesList(sharingActivitiesSearchDTO, webPage);
        // 查询 下拉框 区域 公司 项目 Map
        PropertyCompanyMapDTO propertyCompanyMap = propertyCompanyService.queryPropertyCompanyMap(userPropertystaff.getProjectId());
        model.addAttribute("activitiesMessage",ActivitiesManageList);
        model.addAttribute("projects",propertyCompanyMap);
        //搜索条件
        model.addAttribute("activitiesSearch", sharingActivitiesSearchDTO);

        return "/activities/activitiesManage";
    }

    /**
     * 初始化分享活动新增页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/CreateActivities.html")
    public String gotocreatActivities(@ModelAttribute("propertystaff") UserPropertyStaffEntity userProperty,@RequestParam String activitiesId,Model model){
        SharingActivitiesDTO activitiesDTO=sharingActivitiesService.getActivitiesById(activitiesId);
        // 查询 下拉框 区域 公司 项目 Map
        PropertyCompanyMapDTO propertyCompanyMap = propertyCompanyService.queryPropertyCompanyMap(userProperty.getProjectId());
        model.addAttribute("activitiesDTO",activitiesDTO);

        model.addAttribute("projects", propertyCompanyMap);
        return "/activities/addActivitiesManage";
    }

    /**
     * 活动新增
     * @param
     * @param model
     * @return
     */
    @RequestMapping(value = "/addActivities.html",method = RequestMethod.POST)
    public String creatActivities(@Valid SharingActivitiesDTO SharingActivitiesDTO,@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntit,Model model,WebPage webPage){
        sharingActivitiesService.AddActivities(userPropertystaffEntit, SharingActivitiesDTO);
        return "redirect:/activities/activitiesManage.html";
    }

    /**
     * 删除活动
     * @return
     */
    @RequestMapping(value = "/removeActivities")
    public ApiResult removeActivities(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEnt ,@RequestParam String activitiesId)
    {
        String msg=  sharingActivitiesService.delActivitiesById(userPropertystaffEnt,activitiesId);
        return new SuccessApiResult(msg);
    }

    /**
     * 查询图片结果集
     * @return
     */
    @RequestMapping(value = "/getActivitiesImg",method = RequestMethod.GET)
    @ResponseBody
    public List<SharingActivitiesImgDTO> getActivitiesImg(@RequestParam String activitiesId)
    {
        List<SharingActivitiesImgDTO> imgList=  sharingActivitiesService.getImageList(activitiesId);
        return imgList;
    }

    /**
     * 活动排序
     * @param
     * @return
     */
    @RequestMapping(value = "/Activitiessort.html")
    public String getAdvertManageById(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEnt ,@RequestParam String activitiesId,String state,Model model,WebPage webPage)
    {
        List<SharingActivitiesDTO> ActivitiesManageList=sharingActivitiesService.Activitiessort(activitiesId, state, webPage);
        model.addAttribute("activitiesMessage",ActivitiesManageList);
        // 查询 下拉框 区域 公司 项目 Map
        PropertyCompanyMapDTO propertyCompanyMap = propertyCompanyService.queryPropertyCompanyMap(userPropertystaffEnt.getProjectId());
        model.addAttribute("projects",propertyCompanyMap);

        return "/activities/activitiesManage";
    }
}
