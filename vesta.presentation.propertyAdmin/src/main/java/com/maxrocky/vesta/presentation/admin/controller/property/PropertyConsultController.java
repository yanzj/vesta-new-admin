package com.maxrocky.vesta.presentation.admin.controller.property;

import com.maxrocky.vesta.application.DTO.admin.ProjectSelDTO;
import com.maxrocky.vesta.application.AdminDTO.PropertyConsultDTO;
import com.maxrocky.vesta.application.inf.ConsultService;
import com.maxrocky.vesta.application.inf.UserOwnerService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/1.
 * 物业咨询管理控制层
 */
@Controller
@RequestMapping(value = "/property")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class PropertyConsultController {

    /**
     * 物业咨询管理 业务逻辑层接口
     */
    @Autowired
    ConsultService consultService;
    /**
     * 用户管理 业主 业务逻辑层接口
     */
    @Autowired
    private UserOwnerService userOwnerService;

    @RequestMapping(value = "/PropertyConsult.html")
    public String gotoPage(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid PropertyConsultDTO propertyConsult,Model model,WebPage webPage){
        // 初始化 登陆人查询范围
        propertyConsult.setQueryScope(userPropertystaffEntity.getQueryScope());
        // 默认登录人下拉框
        propertyConsult.setProject(userPropertystaffEntity.getProjectId());

        //咨询管理列表
        List<PropertyConsultDTO> consultList = consultService.queryConsult(propertyConsult, webPage);
        model.addAttribute("consultList",consultList);
        //初始化项目下拉框
        List<ProjectSelDTO> projectSelDTOs = userOwnerService.getProjectSel(userPropertystaffEntity.getProjectId());
        model.addAttribute("projectSelDTOs", projectSelDTOs);
        //搜索条件
        model.addAttribute("propertyConsult", propertyConsult);
        return "/property/PropertyConsult";
    }

    /**
     * 根据咨询信息id删除(更新状态)
     * @param userPropertystaff
     * @param id
     * @return
     */
    @RequestMapping(value = "/removePropertyConsultById")
    public ApiResult removePropertyAnnouncement(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff ,@RequestParam String id)
    {
        String PropertyConsult = consultService.removePropertyConsultById(id, userPropertystaff);
        return new SuccessApiResult(PropertyConsult);
    }

    /**
     * 根据咨询信息id 查询咨询信息及回复信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/NewPropertyConsult")
    public String newPage(@RequestParam String id,@RequestParam String type,Model model){
        PropertyConsultDTO propertyConsult = consultService.queryAnswerMessage(id);
        model.addAttribute("type",type);
        model.addAttribute("propertyConsult",propertyConsult);
        return  "property/NewPropertyConsult";
    }

    /**
     * 回复咨询信息(添加或修改回复信息)
     * @param propertyConsult
     * @param userPropertystaffEntit
     * @return
     */
    @RequestMapping(value = "/replyPropertyConsult.html")
    public String replyPropertyConsult(@Valid PropertyConsultDTO propertyConsult,@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntit){
        consultService.saveORupdatePropertyConsult(userPropertystaffEntit,propertyConsult);
        return "redirect:/property/PropertyConsult.html";
    }
}
