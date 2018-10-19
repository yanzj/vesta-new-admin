package com.maxrocky.vesta.presentation.admin.controller.property;


import com.maxrocky.vesta.application.DTO.PropertyTypesDTO;
import com.maxrocky.vesta.application.inf.PropertyTypesService;
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
 * Created by sunmei on 2016/1/31.
 */
@Controller
@RequestMapping(value = "/property")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class PropertyTypeController {
    /**
     * 物业服务模块 业务逻辑层接口
     */
    @Autowired
    private PropertyTypesService propertyTypesService;

/*    *//**
     * 初始化公告类型列表
     * @param model
     * @param page
     * @return
     */
    @RequestMapping(value = "/PropertyType.html")

    public String gotoPage(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff,@Valid PropertyTypesDTO propertyTypesDTO,Model model,WebPage page){
        // 查询公告类型信息
        List<PropertyTypesDTO> typesDTOList = propertyTypesService.queryPropertyTypeMessage(propertyTypesDTO,page);
        model.addAttribute("propertyTypes",typesDTOList);
        return "/property/PropertyType";
    }

    /**
     * 初始化公告类型新增页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/CreatPropertyType.html")
    public String creatPropertyType(@RequestParam String typeId,Model model){
        PropertyTypesDTO propertyTypesDTO =new PropertyTypesDTO();
        if(!"".equals(typeId)&&null!=typeId){
            propertyTypesDTO=propertyTypesService.queryPropertyTypesById(typeId);
            model.addAttribute("PropertyType",propertyTypesDTO);
        }
        return "/property/NewPropertyType";
    }
    /**
     * 公告类型新增
     * @param model
     * @return
     */
    @RequestMapping(value = "/AddPropertyType.html")
    public String addPropertyType(@Valid PropertyTypesDTO propertyTypesDTO,@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntit,Model model){
        propertyTypesService.addPropertyTypes(userPropertystaffEntit,propertyTypesDTO);
        return "redirect:/property/PropertyType.html";
    }


    /**
     * 根据ID删除服务信息
     * @return
     */
    @RequestMapping(value = "/removePropertyTypes.html")
    public String removePropertyServices(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,@RequestParam String typeId)
    {
        String propertyTypes = propertyTypesService.removePropertyTypeById(userPropertystaffEntity,typeId);
        return "redirect:/property/PropertyType.html";
    }

}
