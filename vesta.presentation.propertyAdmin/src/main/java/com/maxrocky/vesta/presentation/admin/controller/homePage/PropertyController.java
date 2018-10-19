package com.maxrocky.vesta.presentation.admin.controller.homePage;

import com.maxrocky.vesta.application.DTO.PropertyDTO;
import com.maxrocky.vesta.application.inf.PropertyService;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * Created by sunmei on 2016/2/26.
 */
@Controller
@RequestMapping(value = "/pageProperty")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class PropertyController {
    @Autowired
    private PropertyService propertyService;
    @RequestMapping(value = "/PropertyManage.html")
    public String gotoPage(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff,Model model,WebPage webPage){

        List<PropertyDTO> propertyManageList =propertyService.PropertyManage(webPage);

        model.addAttribute("propertyManage",propertyManageList);
        return "/property/propertyManage";
    }
    /**
     * 首页模块排序
     * @param
     * @return
     */
    @RequestMapping(value = "/propertysort.html")
    public String getAdvertManageById(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEnt ,@RequestParam String id,String state,Model model,WebPage webPage)
    {
        List<PropertyDTO> propertyManageList=propertyService.Propertysort(id, state, webPage);
        model.addAttribute("propertyManage",propertyManageList);
        return "/property/propertyManage";
    }

}
