package com.maxrocky.vesta.presentation.admin.controller.mall;

import com.maxrocky.vesta.application.AdminDTO.IntegralRuleDTO;
import com.maxrocky.vesta.application.AdminDTO.IntegralRuleListDTO;
import com.maxrocky.vesta.application.AdminDTO.IntegralRuleQuerry;
import com.maxrocky.vesta.application.AdminDTO.MallDTO;
import com.maxrocky.vesta.application.inf.DefaultConfigService;
import com.maxrocky.vesta.application.inf.IntegralMallService;
import com.maxrocky.vesta.application.inf.IntegralRuleModelService;
import com.maxrocky.vesta.application.inf.IntegralRuleService;
import com.maxrocky.vesta.domain.model.ClientConfigEntity;
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

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 积分商城
 */
@Controller
@RequestMapping(value = "/integralRule")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class IntegralRuleController {

    @Autowired
    IntegralRuleService integralRuleService;

    @Autowired
    IntegralRuleModelService integralRuleModelService;

    @Autowired
    DefaultConfigService defaultConfigService;

    /**
     * Describe:获取规则列表
     */
    @RequestMapping(value = "/getIntegralRuleList.html")
    public ModelAndView getIntegralRuleList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                           IntegralRuleQuerry q,
                                           WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/mall/IntegralRuleList");
        try{
            //分页设置并回显
            webPage.setPageSize(20);
            List<IntegralRuleListDTO> getIntegralRuleSize = integralRuleService.getIntegralRuleList(q,null);
            List<IntegralRuleListDTO> getIntegralRuleList = integralRuleService.getIntegralRuleList(q,webPage);
            modelAndView.addObject("list", getIntegralRuleList);
            webPage.setRecordCount(getIntegralRuleSize.size());
            modelAndView.addObject("webPage", webPage);
            modelAndView.addObject("modelIntegral", integralRuleModelService.getIntegralRuleModelList());
            modelAndView.addObject("defaultConfig", defaultConfigService.getClientConfigList(null, null));
            modelAndView.addObject("integralRuleQuerry", q);

        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }



    /**
     * Describe:编辑/新增规则
     */
    @RequestMapping(value = "/toEditIntegralRule.html")
    public ModelAndView toEditIntegralRule(IntegralRuleQuerry q){
        ModelAndView modelAndView = new ModelAndView("/mall/IntegralRuleEdit");
        try{
            modelAndView.addObject("modelIntegral", integralRuleModelService.getIntegralRuleModelList());
            modelAndView.addObject("defaultConfig", defaultConfigService.getClientConfigList(null, null));
            //执行查询
            modelAndView.addObject("integralRuleDto",integralRuleService.getIntegralRuleById(q.getIntegralRuleId()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:校验项目是否重复
     */
    @ResponseBody
    @RequestMapping(value = "/toSaveIntegralRule")
    public Map<String,Object> saveProductType(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                              IntegralRuleDTO integral){
        Map<String,Object> resultMap = new HashedMap();
        try{
            //设置操作人
            integral.setCreateBy(userPropertystaffEntity.getStaffName());
            integral.setCreateOn(new Date());
            //保存
            resultMap.put("error",integralRuleService.AddIntegralRuleEntity(integral));
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }


    /**
     * Describe:校验重复
     * CreateBy:WeiYangDong_2017-07-17
     */
    @ResponseBody
    @RequestMapping(value = "/checkClientConfigModel")
    public Map<String,Object> checkClientConfigModel(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                              IntegralRuleDTO integral){
        Map<String,Object> resultMap = new HashedMap();
        try{

            //校验
            resultMap.put("error",integralRuleService.getIntegralRuleList(integral));
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }
}
