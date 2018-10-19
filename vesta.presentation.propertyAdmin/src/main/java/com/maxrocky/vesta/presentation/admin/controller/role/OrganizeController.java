package com.maxrocky.vesta.presentation.admin.controller.role;

import com.maxrocky.vesta.application.dto.adminDTO.OrganizeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.OrganizeUserDTO;
import com.maxrocky.vesta.application.dto.adminDTO.OrganizeUserRecDTO;
import com.maxrocky.vesta.application.inf.OrganizeService;
import com.maxrocky.vesta.application.inf.OrganizeUserService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by chen on 2016/6/2.
 */
@Controller
@RequestMapping(value = "/organize")
public class OrganizeController {
    @Autowired
    OrganizeUserService organizeUserService;
    @Autowired
    OrganizeService organizeService;

    /**组 管理*/
    @RequestMapping(value = "/organizeList.html")
    public String organizeManage(@Valid OrganizeDTO organizeDTO,Model model,WebPage webPage){
        List<OrganizeDTO> organizeDTOs = organizeService.getOrganizes(organizeDTO,webPage);
        model.addAttribute("organizeList",organizeDTOs);
        model.addAttribute("organize",organizeDTO.getOrganizeName());
        return "/role/organizeManage";
    }

    /**常用组列表*/
    @RequestMapping(value = "/allOrganizeList",method = RequestMethod.GET)
    public ApiResult allOrganizeList(){
        List<OrganizeDTO> organizeDTOs = organizeService.getOrganizeList();
        return new SuccessApiResult(organizeDTOs);
    }

//    /**新增组*/
//    @RequestMapping(value = "/addOrganize.html")
//    public String addOrganize(@Valid OrganizeDTO organizeDTO){
//        organizeService.addOrganize(organizeDTO);
//        return "redirect:../organize/organizeList.html";
//    }

    /**删除组*/
    @RequestMapping(value = "/delOrganize.html")
    public String delOrganize(@RequestParam(value = "organizeId")String organizeId){
        organizeService.delOrganize(organizeId);
        return "redirect:../organize/organizeList.html";
    }

    /**跳转常用组详情页面*/
    @RequestMapping(value = "/OrganizeDetail.html")
    public String organizeDetail(@RequestParam(value = "organizeId")String organizeId,Model model){
        OrganizeDTO organizeDTO = organizeService.getOrganizeDetail(organizeId);
        model.addAttribute("organize",organizeDTO);
        return "role/OrganizeDetail";
    }

    /**跳转群组新增、修改页面*/
    @RequestMapping(value = "/goAltOrganize.html")
    public String altOrganize(@RequestParam(value = "organizeId",required = false)String organizeId,Model model){
        OrganizeDTO organizeDTO = new OrganizeDTO();
        if(!StringUtil.isEmpty(organizeId)){
            organizeDTO = organizeService.getOrganizeDetail(organizeId);
        }
        model.addAttribute("organize",organizeDTO);
        return "/role/EditOrganize";
    }

    /**新增、修改群组*/
    @RequestMapping(value = "updateOrganize.html")
    public String updateOrganize(@Valid OrganizeDTO organizeDTO){
        if(StringUtil.isEmpty(organizeDTO.getOrganizeId())){    //如果ID为空则为新增
            organizeService.addOrganize(organizeDTO);
        }else {      //否则为修改
            organizeService.updateOrganize(organizeDTO);
        }
        return "redirect:../organize/organizeList.html";
    }

    /**跳转添加人员到组  页面*/
    @RequestMapping(value = "/goAddStaffToOrganize.html")
    public String addStaffToOrganize(Model model,@Valid OrganizeUserDTO organizeUserDTO){
        List<OrganizeUserDTO> organizeUserDTOList = organizeUserService.getStaffByOrganizeId(organizeUserDTO.getOrganizeId());  //组内人员
        List<OrganizeUserDTO> organizeUserDTOs = organizeUserService.getStaffoutOrganize(organizeUserDTO);  //组外所有人员
        OrganizeDTO organizeDTO = organizeService.getOrganizeDetail(organizeUserDTO.getOrganizeId());  //组详情
        model.addAttribute("staffInList",organizeUserDTOList);
        model.addAttribute("staffOutList",organizeUserDTOs);
        model.addAttribute("organize",organizeDTO);
        return "/role/staffOrganize";
    }

    /**新增组 人员关系*/
    @RequestMapping(value = "/addStaffOrganize", method = RequestMethod.GET)
    public ApiResult addStaffOrganize(@Valid OrganizeUserRecDTO organizeUserRecDTO){
        organizeUserService.addOrganizeUser(organizeUserRecDTO);
//        List<OrganizeUserDTO> organizeUserDTOList = organizeUserService.getStaffByOrganizeId(organizeUserRecDTO.getOrganizeId());  //组内人员
        return new SuccessApiResult();
    }
    /**删除组 人员关系*/
    @RequestMapping(value = "/delStaffOrganize",method = RequestMethod.GET)
    public ApiResult delStaffOrganize(@Valid OrganizeUserRecDTO organizeUserRecDTO){
        organizeUserService.delOrganizeUser(organizeUserRecDTO);
        return new SuccessApiResult();
    }

//    /**新增组 项目关系*/
//    @RequestMapping(value = "/addProjectOrganize",method = RequestMethod.GET)
//    public ApiResult addProjectOrganize(@Valid OrganizeProjectDTO organizeProjectDTO){
//        organizeProjectService.addOrganizeProject(organizeProjectDTO);
//        return new SuccessApiResult();
//    }
//    /**删除组 项目关系*/
//    @RequestMapping(value = "/delProjectOrganize",method = RequestMethod.GET)
//    public ApiResult delProjectOrganize(@Valid OrganizeProjectDTO organizeProjectDTO){
//        organizeProjectService.delOrganizeProject(organizeProjectDTO);
//        return new SuccessApiResult();
//    }
}
