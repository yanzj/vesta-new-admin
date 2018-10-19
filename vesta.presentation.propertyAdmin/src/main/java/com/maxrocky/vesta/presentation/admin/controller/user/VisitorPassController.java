package com.maxrocky.vesta.presentation.admin.controller.user;

import com.maxrocky.vesta.application.DTO.admin.VisitorPassDTO;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.inf.VisitorService;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 访客通行Controller
 * Created by WeiYangDong on 2017/12/21.
 */
@Controller
@RequestMapping(value = "/visitor")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class VisitorPassController {

    @Autowired
    VisitorService visitorService;
    @Autowired
    StaffUserService staffUserService;

    /**
     * 获取访客通行列表
     */
    @RequestMapping(value = "/getVisitorPassList.html")
    public ModelAndView getVisitorPassList(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                                           VisitorPassDTO visitorPassDTO,
                                           WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/user/VisitorPassList");
        try{
            //分页设置并回显
            webPage.setPageSize(20);
            List<VisitorPassDTO> list = visitorService.getVisitorPassList(visitorPassDTO, null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(user.getStaffId(),visitorPassDTO.getMenuId());
            modelAndView.addObject("city", cityList);
            if (null != visitorPassDTO.getCityId() && !"".equals(visitorPassDTO.getCityId())){
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(user.getStaffId(), visitorPassDTO.getCityId(), visitorPassDTO.getMenuId());
                modelAndView.addObject("projectList",projectList);
            }
            modelAndView.addObject("visitorPassDTO",visitorPassDTO);
            //执行查询
            List<VisitorPassDTO> visitorPassList = visitorService.getVisitorPassList(visitorPassDTO, webPage);
            modelAndView.addObject("visitorPassList",visitorPassList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 获取访客通行日志列表
     */
    @RequestMapping(value = "/getVisitorPassLogList.html")
    public ModelAndView getVisitorPassLogList(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                                              VisitorPassDTO visitorPassDTO,
                                              WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/user/VisitorPassLogList");
        try{
            //分页设置并回显
            webPage.setPageSize(20);
            List<VisitorPassDTO> list = visitorService.getVisitorPassLogList(visitorPassDTO, null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(user.getStaffId(),visitorPassDTO.getMenuId());
            modelAndView.addObject("city", cityList);
            if (null != visitorPassDTO.getCityId() && !"".equals(visitorPassDTO.getCityId())){
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(user.getStaffId(), visitorPassDTO.getCityId(), visitorPassDTO.getMenuId());
                modelAndView.addObject("projectList",projectList);
            }
            modelAndView.addObject("visitorPassDTO",visitorPassDTO);
            //执行查询
            List<VisitorPassDTO> visitorPassLogList = visitorService.getVisitorPassLogList(visitorPassDTO, webPage);
            modelAndView.addObject("visitorPassLogList",visitorPassLogList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

}
