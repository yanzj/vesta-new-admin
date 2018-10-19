package com.maxrocky.vesta.presentation.admin.controller.statistics;

import com.maxrocky.vesta.application.DTO.ClickTimesSeachDTO;
import com.maxrocky.vesta.application.DTO.PropertyCompanyMapDTO;
import com.maxrocky.vesta.application.DTO.RepairStatisticsDTO;
import com.maxrocky.vesta.application.inf.PropertyCompanyService;
import com.maxrocky.vesta.application.inf.RepairStatisticsService;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/17.
 * 数据统计管理 报修统计 控制层
 */
@Controller
@RequestMapping(value = "/statistics")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class RepairStatisticsController {
    /**
     * 报修统计 业务逻辑层接口
     */
    @Autowired
    RepairStatisticsService repairStatisticsService;

    /**
     * 物业项目公司 业务逻辑层接口
     */
    @Autowired
    private PropertyCompanyService propertyCompanyService;

    /**
     * 初始化 报修统计
     * @param clickTimesSeach
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/RepairStatistics.html")
    public String gotoPage(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,ClickTimesSeachDTO clickTimesSeach,Model model,WebPage webPage){
        clickTimesSeach.setPropertyProject(userPropertystaffEntity.getProjectId());
        List<RepairStatisticsDTO> repairStatistics = repairStatisticsService.repairStatistics(clickTimesSeach,webPage,userPropertystaffEntity);
        model.addAttribute("repair",repairStatistics);
        // 查询 下拉框 区域 公司 项目 Map
        PropertyCompanyMapDTO propertyCompanyMap = propertyCompanyService.queryPropertyCompanyMap(userPropertystaffEntity.getProjectId());
        model.addAttribute("propertyCompanyMap",propertyCompanyMap);
        model.addAttribute("click",clickTimesSeach);
        return  "/statistics/RepairStatistics";
    }
}
