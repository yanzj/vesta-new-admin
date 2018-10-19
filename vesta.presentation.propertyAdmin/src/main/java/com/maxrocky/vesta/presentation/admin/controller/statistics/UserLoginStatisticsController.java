package com.maxrocky.vesta.presentation.admin.controller.statistics;

import com.maxrocky.vesta.application.DTO.ClickTimesSeachDTO;
import com.maxrocky.vesta.application.DTO.PropertyCompanyMapDTO;
import com.maxrocky.vesta.application.DTO.UserLoginStatisticDTO;
import com.maxrocky.vesta.application.inf.PropertyCompanyService;
import com.maxrocky.vesta.application.inf.UserLoginStatisticService;
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
 * 用户登录统计 控制层
 */
@Controller
@RequestMapping(value = "/statistics")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class UserLoginStatisticsController {
    /**
     * 物业项目公司 业务逻辑层接口
     */
    @Autowired
    private PropertyCompanyService propertyCompanyService;

    /**
     * 用户登录
     */
    @Autowired
    UserLoginStatisticService userLoginStatisticService;
    /**
     * 初始化用户登录统计
     * @param clickTimesSeach
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/UserLoginStatistic.html")
    public String gotoPage(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,@Valid ClickTimesSeachDTO clickTimesSeach,Model model,WebPage webPage){
        clickTimesSeach.setPropertyProject(user.getProjectId());
        List<UserLoginStatisticDTO> userLoginStatistic = userLoginStatisticService.userLoginStatistic(user,clickTimesSeach);
        model.addAttribute("userLogin",userLoginStatistic);
        // 查询 下拉框 区域 公司 项目 Map
        PropertyCompanyMapDTO propertyCompanyMap = propertyCompanyService.queryPropertyCompanyMap(user.getProjectId());
        model.addAttribute("propertyCompanyMap",propertyCompanyMap);
        model.addAttribute("click",clickTimesSeach);
        return  "/statistics/UserLoginStatistic";
    }
}
