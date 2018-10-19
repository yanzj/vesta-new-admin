package com.maxrocky.vesta.presentation.admin.controller.announcement;

import com.maxrocky.vesta.application.DTO.BusinessBulletinDTO;
import com.maxrocky.vesta.application.inf.BusinessBulletinService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.domain.model.BusinessBulletinEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 商业公告Controller
 * Created by WeiYangDong on 2017/9/18.
 */
@Controller
@RequestMapping(value = "/businessBulletin")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class BusinessBulletinController {

    @Autowired
    StaffUserService staffUserService;
    @Autowired
    BusinessBulletinService businessBulletinService;

    /**
     * Describe:新增或编辑商业公告
     * CreateBy:WeiYangDong_2017-09-18
     */
    @RequestMapping(value = "/toEditBusinessBulletinInfo.html")
    public ModelAndView toEditBusinessBulletinInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                   BusinessBulletinDTO businessBulletinDTO){
        ModelAndView modelAndView = new ModelAndView("/announcement/BusinessBulletinEdit");
        try {
            //获取商业公告详情
            BusinessBulletinDTO businessBulletinInfo = null;
            if (null != businessBulletinDTO.getId() && !"".equals(businessBulletinDTO.getId())){
                businessBulletinInfo = businessBulletinService.getBusinessBulletinById(businessBulletinDTO.getId());
            }
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),businessBulletinDTO.getMenuId());
            modelAndView.addObject("city", cityList);
            if (null != businessBulletinInfo && null != businessBulletinInfo.getCityId() && !"".equals(businessBulletinInfo.getCityId())){
                businessBulletinDTO.setCityId(businessBulletinInfo.getCityId());
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), businessBulletinDTO.getCityId(), businessBulletinDTO.getMenuId());
                modelAndView.addObject("projectList",projectList);
            }
            modelAndView.addObject("businessBulletinInfo",businessBulletinInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:获取商业公告列表
     * CreateBy:WeiYangDong_2017-09-19
     */
    @RequestMapping(value = "/getBusinessBulletinList.html")
    public ModelAndView getBusinessBulletinList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                              BusinessBulletinDTO businessBulletinDTO, WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/announcement/BusinessBulletinList");
        try{
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
            businessBulletinDTO.setRoleScopeList(roleScopeList);
            //分页设置并回显
            webPage.setPageSize(20);
            List<BusinessBulletinDTO> list = businessBulletinService.getBusinessBulletinList(businessBulletinDTO, null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),businessBulletinDTO.getMenuId());
            modelAndView.addObject("city", cityList);
            if (null != businessBulletinDTO.getCityId() && !"".equals(businessBulletinDTO.getCityId())){
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), businessBulletinDTO.getCityId(), businessBulletinDTO.getMenuId());
                modelAndView.addObject("projectList",projectList);
            }
            modelAndView.addObject("businessBulletinDTO",businessBulletinDTO);
            //获取商业公告列表
            List<BusinessBulletinDTO> businessBulletinList = businessBulletinService.getBusinessBulletinList(businessBulletinDTO, webPage);
            modelAndView.addObject("businessBulletinList",businessBulletinList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:保存或更新公告信息
     * CreateBy:WeiYangDong_2017-09-19
     */
    @RequestMapping(value = "/saveOrUpdateBusinessBulletinInfo.html",method = RequestMethod.POST)
    public ModelAndView saveOrUpdateBusinessBulletinInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                        BusinessBulletinDTO businessBulletinDTO){
        ModelAndView modelAndView = new ModelAndView("redirect:../businessBulletin/getBusinessBulletinList.html?menuId=006900010000");
        try{
            //设置操作人
            businessBulletinDTO.setModifyBy(userPropertystaffEntity.getStaffName());
            businessBulletinService.saveOrUpdateBusinessBulletinInfo(businessBulletinDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:删除公告信息(物理删除)
     * CreateBy:WeiYangDong_2017-09-20
     */
    @ResponseBody
    @RequestMapping(value = "/deleteBusinessBulletinInfo")
    public Map<String,Object> deleteBusinessBulletinInfo(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                         BusinessBulletinDTO businessBulletinDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            businessBulletinService.deleteBusinessBulletinInfo(businessBulletinDTO.getId());
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }
}
