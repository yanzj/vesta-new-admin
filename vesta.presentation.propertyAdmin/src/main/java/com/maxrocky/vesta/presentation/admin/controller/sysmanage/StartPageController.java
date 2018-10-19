package com.maxrocky.vesta.presentation.admin.controller.sysmanage;

import com.maxrocky.vesta.application.DTO.StartPageDTO;
import com.maxrocky.vesta.application.inf.StartPageService;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;

/**
 * Created by sunmei on 2016/2/29.
 */
@Controller
@RequestMapping(value = "/startPage")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class StartPageController {

    @Autowired
    private StartPageService startPageService;

    /**
     * 初始化启动页新增页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoStartPageMange.html")
    public String creatStartPageMange(Model model){
        return "/sysmanage/StartPageMange";
    }
    /**
     * 启动页新增
     * @param model
     * @return
     */
    @RequestMapping(value = "/startPageMange.html")
    public String addStartPage(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntit,@Valid StartPageDTO startPageDTO,Model model){
        String msg=startPageService.createStartPage(userPropertystaffEntit, startPageDTO);
        model.addAttribute("msg",msg);
        return "redirect:/startPage/gotoStartPageMange.html";
    }
}
