package com.maxrocky.vesta.presentation.admin.controller.system;

import com.maxrocky.vesta.application.DTO.ElectricWarnDTO;
import com.maxrocky.vesta.application.inf.AutoPushMessageService;
import com.maxrocky.vesta.application.inf.ElectricWarnService;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by zhanghj on 2016/2/23.
 * 电量预警管理
 */

@Controller
@RequestMapping(value = "/system")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})

public class ElectricWarnController {
    @Autowired
    private ElectricWarnService electricWarnService;
    @Autowired
    private AutoPushMessageService autoPushMessageService;

    /**
     * 获取项目下预警信息
     * @param userPropertystaffEntity
     * @param electricWarnDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/eleWarnManage.html",method = RequestMethod.GET)
    public  String eleWarnManage(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid ElectricWarnDTO electricWarnDTO,Model model){
       electricWarnDTO = electricWarnService.getEleWarnByProId(userPropertystaffEntity.getQueryScope());
        model.addAttribute("electricWarnDTO",electricWarnDTO);
        return "/system/elewarn/EleWarnManage";
    }
    @RequestMapping(value = "/eleWarnUpdate",method = RequestMethod.GET)
    public ModelAndView eleWarnUpdate(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid ElectricWarnDTO electricWarnDTO,Model model){
        boolean result = electricWarnService.updateEleWarn(electricWarnDTO,userPropertystaffEntity);
        model.addAttribute("result",result);
        return new ModelAndView("redirect:../system/eleWarnManage.html");
    }

    /**
     * 手动消息推送
     * @return
     */
    @RequestMapping(value = "/tesZhj.html")
    public String testZhj(){

        autoPushMessageService.autoPushUserIosMessage();

        return "/system/elewarn/TestZhj";
    }

    /**
     * 手动添加消息
     * @return
     */
    @RequestMapping(value = "/addMessage.html")
    public String addMessage(){

        autoPushMessageService.autoAddMessage();

        return "/system/elewarn/TestZhj";
    }
}
