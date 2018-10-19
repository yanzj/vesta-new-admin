package com.maxrocky.vesta.presentation.admin.controller.property;

import com.maxrocky.vesta.application.AdminDTO.AnswerAdminDTO;
import com.maxrocky.vesta.application.AdminDTO.PraiseAdminDTO;
import com.maxrocky.vesta.application.DTO.admin.ProjectSelDTO;
import com.maxrocky.vesta.application.inf.AnswerService;
import com.maxrocky.vesta.application.inf.PraiseService;
import com.maxrocky.vesta.application.inf.UserOwnerService;
import com.maxrocky.vesta.domain.model.AnswerEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by chen on 2016/2/19.
 * 表扬管理控制层
 */

@Controller
@RequestMapping(value = "/property")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class PraiseManageController {
    @Autowired
    PraiseService praiseService;
    @Autowired
    AnswerService answerService;
    @Autowired
    UserOwnerService userOwnerService;

    /**
     * 表扬管理
     * */
    @RequestMapping(value = "/Praise.html")
    public String ManageIndex(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid PraiseAdminDTO praiseAdminDTO,WebPage webPage,Model model){
        List<ProjectSelDTO> projectSelDTOs = userOwnerService.getProjectSel(userPropertystaffEntity.getProjectId());
        if(praiseAdminDTO.getProjectIds()==null||praiseAdminDTO.getProjectIds().equals("0")){
            praiseAdminDTO.setProjectIds(userPropertystaffEntity.getProjectId());
        }
        List<PraiseAdminDTO> praiseAdminDTOList = praiseService.getPraiseList(praiseAdminDTO,webPage);
        model.addAttribute("projectSelDTOs", projectSelDTOs);
        model.addAttribute("list",praiseAdminDTOList);
        model.addAttribute("praise",praiseAdminDTO);
        return "/property/PropertyPraise";
    }

    /**
     * 表扬详情
     * */
    @RequestMapping(value = "/PraiseAnswer.html")
    public String PraiseDetail(@RequestParam("praiseId")String praiseId,Model model){
        PraiseAdminDTO praiseAdminDTO = praiseService.getPraise(praiseId);
        model.addAttribute("Panswer",praiseAdminDTO);
        return "property/PraiseAnswer";
    }

    /**
     * 新增回复
     * */
    @RequestMapping(value = "/addAnswer.html")
    public String addPraiseAnswer(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaff,@Valid AnswerAdminDTO answerAdminDTO){
        answerAdminDTO.setAnswerType(AnswerEntity.answer_praise);
        answerService.AddAnswer(userPropertystaff,answerAdminDTO);
        return "redirect:/property/Praise.html";
    }

    /**
     * 删除
     * */
    @RequestMapping(value = "/delPraise.html")
    public String delPraise(@RequestParam("praiseId")String praiseId){
        praiseService.deletePraise(praiseId);
        return "redirect:/property/Praise.html";
    }

 }
