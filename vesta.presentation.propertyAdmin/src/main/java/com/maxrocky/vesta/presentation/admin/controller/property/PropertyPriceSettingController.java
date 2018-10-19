package com.maxrocky.vesta.presentation.admin.controller.property;

import com.maxrocky.vesta.application.DTO.PropertyPriceSettingDTO;
import com.maxrocky.vesta.application.inf.PropertyPriceSettingService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/15.
 * 物业管理 价格设置 控制层
 */

@Controller
@RequestMapping(value = "/property")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class PropertyPriceSettingController {

    /**
     * 维修价格设置 业务逻辑层接口
     */
    @Autowired
    PropertyPriceSettingService propertyPriceSettingService;

    /**
     * 初始化 价格列表
     * @param propertyPriceSetting
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/PropertyPriceSetting.html")
    public String gotoPage(@Valid PropertyPriceSettingDTO propertyPriceSetting,@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntit, Model model,WebPage webPage){
        //根据项目ID查询项目维修价格
        List<PropertyPriceSettingDTO> propertyPrice = propertyPriceSettingService.queryPriceSettingByProjectId(userPropertystaffEntit, propertyPriceSetting,webPage);
        model.addAttribute("propertyPrice",propertyPrice);
        model.addAttribute("priceSetting",propertyPriceSetting);
        return "/property/PropertyPriceSetting";
    }


    /**
     * 初始化 新增价格 或修改价格页面
     * @param propertyPriceSetting
     * @param model
     * @return
     */
    @RequestMapping(value = "/NewPropertyPriceSetting")
    public String newPage(@Valid PropertyPriceSettingDTO propertyPriceSetting,@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaff,Model model){
        PropertyPriceSettingDTO propertyPriceSettings = propertyPriceSettingService.initializePriceSetting(propertyPriceSetting, userPropertystaff);
        model.addAttribute("priceSetting",propertyPriceSettings);
        return  "property/NewPropertyPriceSetting";
    }

    /**
     * 添加或修改 维修价格
     * @param propertyPriceSetting
     * @param userProperty
     * @return
     */
    @RequestMapping(value = "/saveOrUpdatePriceSetting")
    public ApiResult saveOrUpdatePropertyPriceSetting(@Valid PropertyPriceSettingDTO propertyPriceSetting,@ModelAttribute("propertystaff")UserPropertyStaffEntity userProperty){
        String priceSetting = propertyPriceSettingService.modifyPriceSetting(propertyPriceSetting, userProperty);
        return new SuccessApiResult(priceSetting);
    }

    /**
     * 根据维修价格ID删除信息
     * @param userPropertystaffs
     * @param priceSettingId
     * @return
     */
    @RequestMapping(value = "/removePriceSettingById")
    public ApiResult removePriceSetting(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffs ,@RequestParam String priceSettingId)
    {
        String priceSetting = propertyPriceSettingService.removePropertyConsultById(priceSettingId, userPropertystaffs);
        return new SuccessApiResult(priceSetting);
    }

}
