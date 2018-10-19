package com.maxrocky.vesta.presentation.admin.controller.system;

import com.maxrocky.vesta.application.DTO.AppVersionDTO;
import com.maxrocky.vesta.application.inf.AppVersionService;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghj on 2016/2/24.
 */

@Controller
@RequestMapping(value = "/system")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class AppVersionController {
    @Autowired
    private AppVersionService appVersionService;


    /**
     * 初始化版本列表
     * @param userPropertystaffEntity
     * @param appVersionDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/versionManage.html",method = RequestMethod.GET)
    public  String versionManage(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid AppVersionDTO appVersionDTO,Model model,WebPage webPage){
        List<AppVersionDTO> appVersionDTOs = appVersionService.getVersionByType(appVersionDTO,webPage);
        model.addAttribute("appVersionDTOs",appVersionDTOs);
        model.addAttribute("appVersionDTO",appVersionDTO);
        //记录集合长度，如果没有查询出数据则不可导出
        model.addAttribute("isExcel",appVersionDTOs.size());
        return "/system/appversion/AppVersionManage";
    }

    /**
     * 查询数据
     * @param userPropertystaffEntity
     * @param appVersionDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/queryversionManage",method = RequestMethod.POST)
    public  String queryversionManage(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid AppVersionDTO appVersionDTO,Model model,WebPage webPage){
        List<AppVersionDTO> appVersionDTOs = appVersionService.getVersionByType(appVersionDTO,webPage);
        model.addAttribute("appVersionDTOs",appVersionDTOs);
        model.addAttribute("appVersionDTO",appVersionDTO);
        return "/system/appversion/AppVersionManage";
    }

    /**
     * 删除版本信息
     * @param userPropertystaffEntity
     * @param appVersionId
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/versionDel")
    public ModelAndView versionDel(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid String  appVersionId,Model model,WebPage webPage){

        appVersionService.delAppVersion(appVersionId, userPropertystaffEntity);
        return new ModelAndView("redirect:../system/versionManage.html");
    }

    /**
     * 跳转到添加版本信息页面
     * @param userPropertystaffEntity
     * @param appVersionDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoAddVersion")
    public String gotoAddVersion(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid AppVersionDTO appVersionDTO,Model model){
        appVersionDTO.setCreateOn(DateUtils.format(DateUtils.getDate()));
        model.addAttribute("appVersionDTO", appVersionDTO);
        return "/system/appversion/AppVersionAdd";
    }

    /**
     * 添加版本信息
     * @param
     * @param appVersionDTO
     * @return
     */
    @RequestMapping(value = "/versionAdd")
    public ModelAndView versionAdd(@Valid AppVersionDTO appVersionDTO){
        appVersionService.saveAppVersion(appVersionDTO);
        return new ModelAndView("redirect:../system/versionManage.html");
    }

    /**
     * 跳转到版本修改页面
     * @param userPropertystaffEntity
     * @param appVersionDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoUpdateVersion")
    public String gotoUpdateVersion(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid AppVersionDTO  appVersionDTO,Model model){
        appVersionDTO = appVersionService.getVersionBy(appVersionDTO.getAppVersionId(),"1");
        appVersionDTO.setModifyOn(DateUtils.format(DateUtils.getDate()));
        model.addAttribute("appVersionDTO", appVersionDTO);
        return "/system/appversion/AppVersionUpdate";
    }

    /**
     * 版本修改
     * @param
     * @param appVersionDTO
     * @return
     */
    @RequestMapping(value = "/versionUpdate")
    public ModelAndView versionUpdate(@Valid AppVersionDTO appVersionDTO){
        appVersionService.updateAppVersion(appVersionDTO);
        return new ModelAndView("redirect:../system/versionManage.html");
    }

    /**
     * 跳转到详情页面
     * @param userPropertystaffEntity
     * @param appVersionId
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoAppVersion")
    public String gotoUpdateVersion(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid String appVersionId,Model model){
        AppVersionDTO appVersionDTO = appVersionService.getVersionBy(appVersionId,"1");
        appVersionDTO.setModifyOn(DateUtils.format(DateUtils.getDate()));
        model.addAttribute("appVersionDTO", appVersionDTO);
        return "/system/appversion/AppVersionAdd";
    }

    /***
     * 导出excel操作
     * param:user
     * param:httpServletResponse
     * return
     */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public String exportExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                              @Valid AppVersionDTO appVersionDTO,
                              HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest){
        try {
            return appVersionService.exportExcel(user,appVersionDTO,httpServletResponse, httpServletRequest);
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }
}
