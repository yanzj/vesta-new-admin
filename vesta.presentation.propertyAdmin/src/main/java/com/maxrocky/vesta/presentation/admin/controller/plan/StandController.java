package com.maxrocky.vesta.presentation.admin.controller.plan;

import com.maxrocky.vesta.application.AdminDTO.RecodeAdminDTO;
import com.maxrocky.vesta.application.AdminDTO.StandAdminDTO;
import com.maxrocky.vesta.application.inf.SideStandService;
import com.maxrocky.vesta.application.inf.StandRecodeService;
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
 * Created by chen on 2016/5/24.
 * 旁站
 */
@Controller
@RequestMapping(value = "/stand")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class StandController {
    @Autowired
    SideStandService sideStandService;
    @Autowired
    StandRecodeService standRecodeService;

    /**旁站管理页*/
    @RequestMapping(value = "standList.html")
    public String StandList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,Model model,@Valid StandAdminDTO standAdminDTO,WebPage webPage){
        List<StandAdminDTO> standAdminDTOList = sideStandService.getSideStandList(standAdminDTO,webPage);
        model.addAttribute("standList",standAdminDTOList);
        return "plan/standManage";
    }

    /**新增、修改旁站批次*/
    @RequestMapping(value = "addStand.html")
    public String addStand(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid StandAdminDTO standAdminDTO){
        if(standAdminDTO.getStandId()==null||"".equals(standAdminDTO)){
            sideStandService.addStand(standAdminDTO);         //新增
        }else{
            sideStandService.updateStand(standAdminDTO);     //修改
        }
        return "redirect:/stand/standList.html";
    }

    /**打开、关闭旁站批次*/
    @RequestMapping(value = "altStand.html")
    public String updateStand(@RequestParam(value = "standId")String standId){
        sideStandService.altStand(standId);
        return "redirect:/stand/standList.html";
    }

    /**跳转旁站详情页*/
    @RequestMapping(value = "gotoDetail.html")
    public String gotoDetail(@RequestParam(value = "standId")String standId,@RequestParam(value = "flag")String flag,Model model){
        StandAdminDTO standAdminDTO = sideStandService.getSideStand(standId);
        model.addAttribute("stand",standAdminDTO);
        model.addAttribute("flag",flag);
        return "/plan/standDetail";
    }

    /**新增旁站记录*/
    @RequestMapping(value = "addRecode.html")
    public String addRecode(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid RecodeAdminDTO recodeAdminDTO){
        standRecodeService.addStandRecode(recodeAdminDTO);
        return "redirect/stand/standList.html";
    }

    /**删除旁站批次*/
    @RequestMapping(value = "deleteStand.html")
    public String deleteStand(@RequestParam(value = "standId")String standId){
        sideStandService.deleteStand(standId);
        return "redirect/stand/standList.html";
    }
}
