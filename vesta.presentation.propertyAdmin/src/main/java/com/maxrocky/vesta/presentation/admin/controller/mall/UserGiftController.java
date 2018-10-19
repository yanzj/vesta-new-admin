package com.maxrocky.vesta.presentation.admin.controller.mall;

import com.maxrocky.vesta.application.AdminDTO.IntegralRuleListDTO;
import com.maxrocky.vesta.application.AdminDTO.IntegralRuleQuerry;
import com.maxrocky.vesta.application.AdminDTO.MallDTO;
import com.maxrocky.vesta.application.AdminDTO.UserGiftDTO;
import com.maxrocky.vesta.application.inf.IntegralMallService;
import com.maxrocky.vesta.application.inf.UserGiftService;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/userGift")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class UserGiftController {
    @Autowired
    UserGiftService userGiftService;

    @Autowired
    IntegralMallService integralMallService;


    /**
     * Describe:编辑/新增
     */
    @ResponseBody
    @RequestMapping(value = "/toSaveUserGift")
    public ModelAndView toSaveUserGift(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                       UserGiftDTO d){
        ModelAndView modelAndView = new ModelAndView("/mall/UserGift");
        Map<String,Object> resultMap = new HashedMap();
        try{
            userGiftService.UpdateUserGift(d);
            modelAndView.addObject("gift",userGiftService.getUserGift());
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return modelAndView;
    }


    /**
     * Describe:获取
     */
    @RequestMapping(value = "/getUserGift.html")
    public ModelAndView getUserGift(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntitye ,
                                            WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/mall/UserGift");
        try{
            MallDTO mallDTO = new MallDTO();
            mallDTO.setProductState(1);
            webPage.setPageSize(20);
            List<MallDTO> getProductList = integralMallService.getProductList(mallDTO,null);
            modelAndView.addObject("gift",userGiftService.getUserGift());
            modelAndView.addObject("mall",getProductList);
            webPage.setRecordCount(getProductList.size());
            modelAndView.addObject("webPage", webPage);

        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }
}
