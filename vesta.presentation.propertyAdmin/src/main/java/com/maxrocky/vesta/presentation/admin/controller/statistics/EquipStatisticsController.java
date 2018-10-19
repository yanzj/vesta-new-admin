package com.maxrocky.vesta.presentation.admin.controller.statistics;

import com.maxrocky.vesta.application.DTO.ClickTimesSeachDTO;
import com.maxrocky.vesta.application.DTO.EquipStatisticsDTO;
import com.maxrocky.vesta.application.DTO.PropertyCompanyMapDTO;
import com.maxrocky.vesta.application.inf.EquipStatisticsService;
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
 * Created by sunmei on 2016/2/17.
 */
@Controller
@RequestMapping(value = "/equip")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class EquipStatisticsController {
    @Autowired
    private PropertyCompanyService propertyCompanyService;

    @Autowired
    private EquipStatisticsService equipStatisticsService;
/*    *//**
     * 初始化各设备统计列表
     * @param model
     * @param page
     * @return
     */
    @RequestMapping(value = "/EquipStatistics.html")

    public String gotoClickNums(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntit,@Valid ClickTimesSeachDTO clickTimesSeachDTO ,Model model,WebPage page){
        // 初始化 登陆人所负责范围
        clickTimesSeachDTO.setQueryScope(userPropertystaffEntit.getQueryScope());
        // 查询各模块点击量信息
        List<EquipStatisticsDTO> equipStatisticsList= equipStatisticsService.EquipManage(clickTimesSeachDTO, page);
        // 查询 下拉框 区域 公司 项目 Map
        PropertyCompanyMapDTO propertyCompanyMap = propertyCompanyService.queryPropertyCompanyMap(userPropertystaffEntit.getProjectId());
        model.addAttribute("equipStatisticsList",equipStatisticsList);
        model.addAttribute("equipStatisticsMap",propertyCompanyMap);
        model.addAttribute("clickTimesSeachDTO",clickTimesSeachDTO);
        return "/statistics/equipStatistics";
    }
}
