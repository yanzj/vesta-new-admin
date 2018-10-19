package com.maxrocky.vesta.presentation.admin.controller.system;

import com.maxrocky.vesta.application.DTO.AppVersionDTO;
import com.maxrocky.vesta.application.DTO.NavigationMenuDTO;
import com.maxrocky.vesta.application.inf.NavigationMenuService;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by luxinxin on 2016/8/31.
 * app导航菜单管理
 * navigationMenu/navigationMenuManage.html
 */
@Controller
@RequestMapping(value = "/navigationMenu")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class NavigationMenuController {
    @Autowired
    private NavigationMenuService navigationMenuService;

    /**
     * 初始化导航菜单列表
     * @param userPropertystaffEntity
     * @param navigationMenuDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/navigationMenuManage.html",method = RequestMethod.GET)
    public String navigationMenuManage(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                       @Valid NavigationMenuDTO navigationMenuDTO,Model model,WebPage webPage){
        List<NavigationMenuDTO> navigationMenuDTOs = navigationMenuService.getAllnavigationMenus(navigationMenuDTO,webPage);
        model.addAttribute("navigationMenuDTOs",navigationMenuDTOs);
        //model.addAttribute("navigationMenuDTOs",navigationMenuDTOs);
        return "/system/navigationMenu/navigationMenuManage";
    }

    /**
     * 跳转到添加导航菜单信息页面
     * @param userPropertystaffEntity
     * @param navigationMenuDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoAddNavigationMenu")
    public String gotoAddNavigationMenu(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid NavigationMenuDTO navigationMenuDTO,Model model){
        model.addAttribute("navigationMenuDTO", navigationMenuDTO);
        return "/system/navigationMenu/navigationMenuAdd";
    }

    /**
     * 添加导航菜单信息
     * @param
     * @param navigationMenuDTO
     * @return
     */
    @RequestMapping(value = "/navigationMenuAdd",method = RequestMethod.POST)
    public ModelAndView navigationMenuAdd(@Valid NavigationMenuDTO navigationMenuDTO){
        navigationMenuService.saveNavigationMenu(navigationMenuDTO);
        return new ModelAndView("redirect:../navigationMenu/navigationMenuManage.html");
    }

    /**
     * 跳转到导航菜单修改页面
     * @param userPropertystaffEntity
     * @param navigationMenuDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoUpdateNavigationMenu")
    public String gotoUpdateNavigationMenu(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid NavigationMenuDTO navigationMenuDTO,Model model){
        navigationMenuDTO = navigationMenuService.getNavigationMenu(navigationMenuDTO.getNavigationId());
        model.addAttribute("navigationMenuDTO", navigationMenuDTO);
        return "/system/navigationMenu/navigationMenuUpdate";
    }

    /**
     * 导航菜单信息修改
     * @param
     * @param navigationMenuDTO
     * @return
     */
    @RequestMapping(value = "/navigationMenuUpdate")
    public ModelAndView navigationMenuUpdate(@Valid NavigationMenuDTO navigationMenuDTO){
        navigationMenuService.updateNavigationMenu(navigationMenuDTO);
        return new ModelAndView("redirect:../navigationMenu/navigationMenuManage.html");
    }

    /**
     * 删除版本信息
     * @param userPropertystaffEntity
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/navigationMenuDel")
    public ModelAndView navigationMenuDel(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid NavigationMenuDTO navigationMenuDTO,Model model,WebPage webPage){
        navigationMenuService.delNavigationMenu(navigationMenuDTO.getNavigationId());
        return new ModelAndView("redirect:../navigationMenu/navigationMenuManage.html");
    }
}
