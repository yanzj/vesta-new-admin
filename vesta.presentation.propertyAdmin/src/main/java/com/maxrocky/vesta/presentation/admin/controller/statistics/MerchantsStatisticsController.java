package com.maxrocky.vesta.presentation.admin.controller.statistics;

import com.maxrocky.vesta.application.DTO.ClickTimesSeachDTO;
import com.maxrocky.vesta.application.DTO.PropertyCompanyMapDTO;
import com.maxrocky.vesta.application.inf.MerchantsStatisticsService;
import com.maxrocky.vesta.application.inf.PropertyCompanyService;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;

/**
 * Created by ZhangBailiang on 2016/2/17.
 * 商户统计 控制层
 */
@Controller
@RequestMapping(value = "/statistics")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class MerchantsStatisticsController {
    /**
     * 物业项目公司 业务逻辑层接口
     */
    @Autowired
    private PropertyCompanyService propertyCompanyService;

    /**
     * 商户统计 业务逻辑层接口
     */
    @Autowired
    MerchantsStatisticsService merchantsStatisticsService;

    /**
     * 初始化 商户统计
     * @param clickTimesSeach
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/MerchantsStatistics.html")
    public String gotoPage(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntit,@Valid ClickTimesSeachDTO clickTimesSeach,Model model,WebPage webPage){

        // 查询 下拉框 区域 公司 项目 Map
        PropertyCompanyMapDTO propertyCompanyMap = propertyCompanyService.queryPropertyCompanyMap(userPropertystaffEntit.getProjectId());
        model.addAttribute("propertyCompanyMap",propertyCompanyMap);

        return  "/statistics/MerchantsStatistics";
    }
}
