package com.maxrocky.vesta.presentation.admin.controller.role;

import com.maxrocky.vesta.application.dto.adminDTO.RoleMenuDTO;
import com.maxrocky.vesta.application.inf.RoleMenuService;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by zhanghj on 2016/4/18.
 */

@Controller
@RequestMapping(value = "/role")
public class MenuController {

    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 初始化一级菜单列表
     * @return
     */
    @RequestMapping(value = "/menuManage.html",method = RequestMethod.GET)
    public String menuManage(Model model,WebPage webPage){

        List<RoleMenuDTO> roleMenuDTOs = roleMenuService.listFirMenu(webPage);
        model.addAttribute("roleMenuDTOs", roleMenuDTOs);

        return "/role/MenuManage";
    }
    /**
     * 初始化二级菜单列表
     * @return
     */
    @RequestMapping(value = "/menuSecManage",method = RequestMethod.GET)
    public String menuSecManage(Model model,@Param String parId,WebPage webPage){
        List<RoleMenuDTO> roleMenuDTOs = roleMenuService.listSecMenu(parId,webPage);
        model.addAttribute("roleMenuDTOs", roleMenuDTOs);
        model.addAttribute("parId",parId);
        return "/role/MenuSecManage";
    }



    /**
     * 添加一级菜单
     * @param roleMenuDTO
     * @param
     * @return
     */
    @RequestMapping(value = "/addFirMenu",method = RequestMethod.GET)
    public ModelAndView addFirMenu(@Valid RoleMenuDTO roleMenuDTO){

        roleMenuService.addFirMenu(roleMenuDTO);

        return new ModelAndView("redirect:../role/menuManage.html");
    }
    /**
     * 添加二级菜单
     * @param roleMenuDTO
     * @param
     * @return
     */
    @RequestMapping(value = "/addSecMenu",method = RequestMethod.GET)
    public ModelAndView addSecMenu(@Valid RoleMenuDTO roleMenuDTO){

        roleMenuService.addSecMenu(roleMenuDTO);

        return new ModelAndView("redirect:../role/menuSecManage?parId="+roleMenuDTO.getRoleMenuParId());
    }
}
