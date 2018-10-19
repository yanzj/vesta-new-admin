package com.maxrocky.vesta.presentation.admin.controller.mall;

import com.maxrocky.vesta.application.AdminDTO.UserIntegralRuleRecordDTO;
import com.maxrocky.vesta.application.AdminDTO.UserIntegralRuleRecordEntityDTO;
import com.maxrocky.vesta.application.AdminDTO.UserIntegralRuleRecordQuerry;
import com.maxrocky.vesta.application.inf.DefaultConfigService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.inf.UserIntegralRuleRecordService;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/15.
 */
@Controller
@RequestMapping(value = "/userIntegralRule")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class UserIntegralRuleController {

    @Autowired
    UserIntegralRuleRecordService userIntegralRuleRecordService;

    @Autowired
    StaffUserService staffUserService;

    @Autowired
    DefaultConfigService defaultConfigService;

    /**
     */
    @RequestMapping(value = "/getUserIntegralList.html")
    public ModelAndView getIntegralRuleList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                            UserIntegralRuleRecordQuerry q,
                                            WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/mall/UserIntegralRuleList");
        try{
            //是否展示U+会员卡
            modelAndView.addObject("isShowUPlus",0);
            List<String> staffNames = new ArrayList<>();
            staffNames.add("超级");
            staffNames.add("测试");
            staffNames.add("天津");
            staffNames.add("郑州");
            staffNames.add("青岛");
            for (String staffName : staffNames){
                if (userPropertystaffEntity.getStaffName().contains(staffName)){
                    modelAndView.addObject("isShowUPlus",1);
                    break;
                }
            }
            if (userPropertystaffEntity.getStaffName().contains("超级"))
            //分页设置并回显
            webPage.setPageSize(20);
            List<UserIntegralRuleRecordDTO> getListSize = userIntegralRuleRecordService.getUserIntegralRuleRecordList(q,null);
            List<UserIntegralRuleRecordDTO> getList = userIntegralRuleRecordService.getUserIntegralRuleRecordList(q,webPage);
            modelAndView.addObject("list", getList);
            webPage.setRecordCount(getListSize.size());
            modelAndView.addObject("defaultConfig", defaultConfigService.getClientConfigList(null, null));
            modelAndView.addObject("webPage", webPage);
            modelAndView.addObject("integralRuleQuerry", q);

        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/getUserIntegralDetail.html")
    public ModelAndView getUserIntegralDetail(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                            UserIntegralRuleRecordQuerry q,
                                            WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/mall/UserIntegralRuleDetail");
        try{
            List<UserIntegralRuleRecordEntityDTO> list = userIntegralRuleRecordService.getUserIntegralRuleRecordEntityList(q);
            //分页设置并回显
            modelAndView.addObject("integralRuleQuerry", q);
            modelAndView.addObject("list", list);

        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }


    @RequestMapping(value = "/getRule.html")
    public ModelAndView getRule(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                            UserIntegralRuleRecordQuerry q){
        ModelAndView modelAndView = new ModelAndView("/mall/getRule");
        try{
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),q.getMenuId());
            modelAndView.addObject("city", cityList);
            if (null != q.getCityId() && !"".equals(q.getCityId())) {
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), q.getCityId(), q.getMenuId());
                modelAndView.addObject("projectList", projectList);
            }
            modelAndView.addObject("q", q);
            modelAndView.addObject("defaultConfig", defaultConfigService.getClientConfigList(null, null));

            modelAndView.addObject("list", userIntegralRuleRecordService.getCityRule(q));
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }


    /**
     * 批量导入
     */
    @ResponseBody
    @RequestMapping(value = "/uploadExcel")
    public Map<String,Object> uploadExcel(@ModelAttribute("propertystaff") UserPropertyStaffEntity user, HttpServletRequest httpServletRequest) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
            MultipartFile file = request.getFile("excelFile");
            InputStream fis = file.getInputStream();
            //POI:得到解析Excel的实体集合
            resultMap = userIntegralRuleRecordService.uploadExcel(user,fis);
            fis.close();
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error", "-1");
        }
        return resultMap;
    }

}
