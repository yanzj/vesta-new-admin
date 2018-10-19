package com.maxrocky.vesta.presentation.admin.controller.statistics;

import com.maxrocky.vesta.application.DTO.ClickTimesSeachDTO;
import com.maxrocky.vesta.application.DTO.OwnersLeaseStatisticsDTO;
import com.maxrocky.vesta.application.DTO.PropertyCompanyMapDTO;
import com.maxrocky.vesta.application.inf.OwnersTenantsService;
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
import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/17.
 * 业主、租户统计 控制层
 */
@Controller
@RequestMapping(value = "/statistics")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class OwnersTenantsStatisticsController {

    /**
     * 物业项目公司 业务逻辑层接口
     */
    @Autowired
    private PropertyCompanyService propertyCompanyService;

    /**
     * 业主、租户统计 业务逻辑层接口
     */
    @Autowired
    OwnersTenantsService ownersTenantsService;

    /**
     * 初始化 业主、租户统计
     * @param clickTimes
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/OwnersTenantsStatistics.html")
    public String gotoPage(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,@Valid ClickTimesSeachDTO clickTimes,Model model,WebPage webPage){
        clickTimes.setPropertyProject(user.getProjectId());
        List<OwnersLeaseStatisticsDTO> ownersLeaseStatistics = ownersTenantsService.ownersLease(user,clickTimes);
        model.addAttribute("ownersLease",ownersLeaseStatistics);
        // 查询 下拉框 区域 公司 项目 Map
        PropertyCompanyMapDTO propertyCompanyMap = propertyCompanyService.queryPropertyCompanyMap(user.getProjectId());
        model.addAttribute("propertyCompanyMap",propertyCompanyMap);
        model.addAttribute("click",clickTimes);
        return  "/statistics/OwnersTenantsStatistics";
    }
}
