package com.maxrocky.vesta.presentation.admin.controller.mall;

import com.maxrocky.vesta.application.AdminDTO.SellerDTO;
import com.maxrocky.vesta.application.AdminDTO.SellerReceiveDTO;
import com.maxrocky.vesta.application.JsonDTO.SellerTypeDTO;
import com.maxrocky.vesta.application.inf.SellerTypeService;
import com.maxrocky.vesta.application.inf.ShopSellerService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by chen on 2016/1/25.
 */

@Controller
@RequestMapping(value = "/seller")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class SellerController {
    @Autowired
    ShopSellerService shopSellerService;
    @Autowired
    SellerTypeService sellerTypeService;

    /**管理首页*/
    @RequestMapping(value = "/SellerManage.html")
    public String manageIndex(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid SellerDTO sellerDTO,Model model,WebPage webPage){
        sellerDTO.setProjectId(userPropertystaffEntity.getProjectId());
        List<SellerDTO> sellerDTOList = shopSellerService.getShopSellers(sellerDTO, webPage);
        model.addAttribute("sellerList",sellerDTOList);
        model.addAttribute("seller",sellerDTO);
        return "mall/sellerManage";
    }

    /**上移、下移*/
    @RequestMapping(value = "AlterSeller.html")
    public String alterSeller(@RequestParam String sellerId,@RequestParam String status,@RequestParam Integer pageIndex){
        shopSellerService.MoveShopSeller(sellerId,status);
        return "redirect:/seller/SellerManage.html?pageIndex="+pageIndex;
    }

    /**新增、修改  商户初始化页面*/
    @RequestMapping(value = "/addSeller.html")
    public String addSeller(@RequestParam(required = false) String sellerId,@RequestParam(required = false)boolean flag,Model model){
        SellerReceiveDTO sellerReceiveDTO = new SellerReceiveDTO();
        if(!StringUtil.isEmpty(sellerId)){                             //如果有商户ID 则赋值
            sellerReceiveDTO = shopSellerService.getSellerDetail(sellerId);
        }
        List<SellerTypeDTO> sellerTypeDTOs = sellerTypeService.getTypeList();     //分类列表
        model.addAttribute("typeList",sellerTypeDTOs);
        model.addAttribute("seller",sellerReceiveDTO);
        model.addAttribute("result",flag);
        return "mall/addSeller";
    }

    /**新增或修改*/
    @RequestMapping(value = "updateSeller.html",method = RequestMethod.POST)
    public String updateSeller(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid SellerReceiveDTO sellerReceiveDTO){
        if(StringUtil.isEmpty(sellerReceiveDTO.getSellerId())){      //如果商户ID为空 则新增商户
            sellerReceiveDTO.setProjectId(userPropertystaffEntity.getProjectId());
            shopSellerService.AddShopSeller(sellerReceiveDTO);
            return "redirect:/seller/SellerManage.html";
        }else{
            boolean result = shopSellerService.UpdateSeller(sellerReceiveDTO);   //如果商户ID不空 则修改
            return "redirect:/seller/addSeller.html?sellerId="+sellerReceiveDTO.getSellerId()+"&flag="+result;
        }
    }

    /**删除*/
    @RequestMapping(value = "/delete.html")
    public String deleteSeller(@RequestParam String sellerId){
        shopSellerService.DelShopSeller(sellerId);
        return "redirect:/seller/SellerManage.html";
    }

    /**商户分类管理初始化页面*/
    @RequestMapping(value = "/typeList.html")
    public String typeList(Model model,WebPage webPage){
        List<SellerTypeDTO> sellerTypeEntities = sellerTypeService.getTypeList(webPage);
        model.addAttribute("list",sellerTypeEntities);
        return "mall/typeManage";
    }

    /**新增商户分类*/
    @RequestMapping(value="addType.html")
    public String addType(@Valid SellerTypeDTO sellerTypeDTO){
        sellerTypeService.addSellerType(sellerTypeDTO);
        return "redirect:/seller/typeList.html";
    }

    /**修改商户分类*/
    @RequestMapping(value = "altType")
    public ApiResult altType(@Valid SellerTypeDTO sellerTypeDTO,Model model){
        sellerTypeService.updateSellerType(sellerTypeDTO);
        SellerTypeDTO sellerTypeDTO1 = sellerTypeService.getSellerTypeDetail(sellerTypeDTO.getTypeId());
        return new SuccessApiResult(sellerTypeDTO1);
    }

    /**删除商户分类*/
    @RequestMapping(value = "delType.html")
    public String delType(@RequestParam String typeId){
        sellerTypeService.delSellerType(typeId);
        return "redirect:/seller/typeList.html";
    }
}
