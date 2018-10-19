package com.maxrocky.vesta.presentation.admin.controller.system;

import com.maxrocky.vesta.application.DTO.admin.HouseSectionAdminDTO;
import com.maxrocky.vesta.application.inf.HouseSectionService;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by zhanghj on 2016/2/15.
 * 部门管理
 */
@Controller
@RequestMapping(value = "/system")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})

public class SectionController {
    @Autowired
    private HouseSectionService houseSectionService;


    /**
     * 获取部门列表
     * @param userPropertystaffEntity
     * @param houseSectionAdminDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/sectionManage.html",method = RequestMethod.GET)
    public  String sectionManage(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid HouseSectionAdminDTO houseSectionAdminDTO,Model model,WebPage webPage){
        List<HouseSectionAdminDTO> houseSectionAdminDTOs = houseSectionService.listSectionByCompany(userPropertystaffEntity.getQueryScope(),webPage);
        model.addAttribute("houseSectionAdminDTOs",houseSectionAdminDTOs);
        return "/system/section/SectionManage";
    }


    /**
     * 跳到添加部门页面
     * @return
     */
    @RequestMapping(value = "/gotoSectionAdd")
    public String gotoSectionAdd(){

        return "/system/section/SectionAdd";
    }

    /**
     * 添加部门
     * @param userPropertystaffEntity
     * @param houseSectionAdminDTO
     * @return
     */
    @RequestMapping(value = "/sectionAdd")
    public ModelAndView sectionAdd(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid HouseSectionAdminDTO houseSectionAdminDTO){
        houseSectionAdminDTO.setCreateBy(userPropertystaffEntity.getStaffName());
        houseSectionAdminDTO.setModifyBy(userPropertystaffEntity.getStaffName());
        houseSectionAdminDTO.setCompanyId(userPropertystaffEntity.getCompanyId());
        houseSectionAdminDTO.setProjectId(userPropertystaffEntity.getQueryScope());
        boolean result = houseSectionService.addSection(houseSectionAdminDTO);
        return new ModelAndView("redirect:/system/sectionManage.html");
    }

    /**
     * 跳转到修改部门页面
     * @param userPropertystaffEntity
     * @param houseSectionAdminDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoSectionUpdate")
    public String gotoSectionUpdate(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid HouseSectionAdminDTO houseSectionAdminDTO,Model model){
        houseSectionAdminDTO.setProjectId(userPropertystaffEntity.getQueryScope());
        houseSectionAdminDTO = houseSectionService.getSectionDTOById(houseSectionAdminDTO.getSectionId());
        model.addAttribute("houseSectionAdminDTO", houseSectionAdminDTO);
        return "/system/section/SectionUpdate";
    }

    /**
     * 修改部门
     * @param userPropertystaffEntity
     * @param houseSectionAdminDTO
     * @return
     */
    @RequestMapping(value = "/sectionUpdate")
    public ModelAndView sectionUpdate(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid HouseSectionAdminDTO houseSectionAdminDTO){
        houseSectionAdminDTO.setModifyBy(userPropertystaffEntity.getStaffName());
        boolean result = houseSectionService.updateSection(houseSectionAdminDTO);
        return new ModelAndView("redirect:/system/sectionManage.html");
    }


    /**
     * 删除部门
     * @param userPropertystaffEntity
     * @param houseSectionAdminDTO
     * @return
     */
    @RequestMapping(value = "/sectionDelete")
    public ModelAndView sectionDelete(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid HouseSectionAdminDTO houseSectionAdminDTO){
        houseSectionAdminDTO.setModifyBy(userPropertystaffEntity.getStaffName());
        boolean result = houseSectionService.delSection(houseSectionAdminDTO);
        return new ModelAndView("redirect:/system/sectionManage.html");
    }

    /**
     * 部门级别改变
     * @param sectionId
     * @param moveStatus
     * @param pageIndex
     * @return
     */
    @RequestMapping(value="/moveLevel")
    public ModelAndView sectionLevel(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@RequestParam String sectionId,@RequestParam String moveStatus,@RequestParam Integer pageIndex){
    boolean result = houseSectionService.updateLevel(sectionId,moveStatus,userPropertystaffEntity.getQueryScope());
        return new ModelAndView("redirect:/system/sectionManage.html?pageIndex="+pageIndex);
    }
}
