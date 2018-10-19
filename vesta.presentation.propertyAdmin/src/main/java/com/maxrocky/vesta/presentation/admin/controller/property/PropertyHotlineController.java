package com.maxrocky.vesta.presentation.admin.controller.property;

import com.maxrocky.vesta.application.DTO.PropertyHotlineDTO;
import com.maxrocky.vesta.application.inf.PropertyHotlineService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.domain.model.PropertyHotlineEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.sun.javafx.sg.prism.NGShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 物业服务热线Controller
 * Created by WeiYangDong on 2016/12/14.
 */
@Controller
@RequestMapping(value = "/property")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class PropertyHotlineController {
    @Autowired
    private PropertyHotlineService propertyHotlineService;
    @Autowired
    private StaffUserService staffUserService;

    /**
     * 校验用户是否可以操作该交房计划(权限范围)
     */
    @ResponseBody
    @RequestMapping(value = "/checkEditPropertyHotline/{hotlineId}/{menuId}")
    public Map<String,Object> checkEdit(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                        @PathVariable(value = "hotlineId")String hotlineId,
                                        @PathVariable(value = "menuId")String menuId){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            //用户权限项目范围
            List<Map<String, Object>> roleProjectList = staffUserService.getProjectScopeByStaffId(userPropertystaffEntity.getStaffId(), menuId);
            //交房计划项目范围
            PropertyHotlineEntity propertyHotlineEntity = propertyHotlineService.getPropertyHotlineById(hotlineId);
            String projectNum = propertyHotlineEntity.getProjectCode();
            //用户权限范围包含发布范围
            int flag = 0;
            for (Map<String,Object> roleProject : roleProjectList){
                if (projectNum.equals(roleProject.get("projectId").toString())){
                    flag = 1;
                }
            }
            if (flag != 1){
                resultMap.put("error",flag);
                return resultMap;
            }
            resultMap.put("error",1);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * 获取物业服务热线列表
     * WeiYnagDong 于 2016-12-14
     */
    @RequestMapping(value = "/propertyHotlineList.html")
    public ModelAndView getPropertyHotlineList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                               @Valid PropertyHotlineDTO propertyHotlineDTO,WebPage webPage,Model model){
        ModelAndView modelAndView = new ModelAndView("/property/PropertyHotlineList");
        try{
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),propertyHotlineDTO.getMenuId());
            model.addAttribute("city", cityList);
            if (null != propertyHotlineDTO.getScopeId() && !"".equals(propertyHotlineDTO.getScopeId())) {
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), propertyHotlineDTO.getScopeId(), propertyHotlineDTO.getMenuId());
                model.addAttribute("projectList", projectList);
            }
            model.addAttribute("propertyHotlineDTO",propertyHotlineDTO);
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
            propertyHotlineDTO.setRoleScopeList(roleScopeList);
            //执行查询
            List<PropertyHotlineEntity> propertyHotlineList = propertyHotlineService.getPropertyHotlineList(propertyHotlineDTO, webPage);
            model.addAttribute("hotlineList",propertyHotlineList);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error","-1");
        }
        return modelAndView;
    }

    /**
     * 去新增物业服务热线
     * WeiYnagDong 于 2016-12-14
     */
    @RequestMapping(value = "/toEditPropertyHotline.html")
    public ModelAndView toAddPropertyHotline(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                             @Valid PropertyHotlineDTO propertyHotlineDTO,Model model){
        //城市列表
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),propertyHotlineDTO.getMenuId());
        model.addAttribute("city", cityList);
        if (null != propertyHotlineDTO.getHotlineId() && !"".equals(propertyHotlineDTO.getHotlineId())){
            //去修改,回显服务热线信息
            PropertyHotlineEntity propertyHotlineEntity = propertyHotlineService.getPropertyHotlineById(propertyHotlineDTO.getHotlineId());
            model.addAttribute("propertyHotline",propertyHotlineEntity);
            //回显项目列表
            List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), propertyHotlineEntity.getCityId(), propertyHotlineDTO.getMenuId());
            model.addAttribute("projectList", projectList);
        }
        return new ModelAndView("/property/PropertyHotlineEdit");
    }

    /**
     * 保存或更新物业服务热线
     * WeiYnagDong 于 2016-12-14
     */
    @RequestMapping(value = "/saveOrUpdatePropertyHotline")
    public Map<String,Object> saveOrUpdatePropertyHotline(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                                       @Valid PropertyHotlineDTO propertyHotlineDTO){
        Map<String,Object> resultMap = new HashMap<>();
        propertyHotlineDTO.setModifyBy(userPropertystaffEntity.getStaffName());
        try{
            propertyHotlineService.saveOrUpdatePropertyHotline(propertyHotlineDTO);
            resultMap.put("error","0");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error","-1");
        }
        return resultMap;
    }

    /**
     * 删除物业服务热线
     * WeiYnagDong 于 2016-12-14
     */
    @RequestMapping(value = "/deletePropertyHotline")
    public Map<String,Object> deletePropertyHotline(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                                     @Valid PropertyHotlineDTO propertyHotlineDTO){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            propertyHotlineService.deletePropertyHotline(propertyHotlineDTO);
            resultMap.put("error","0");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error","-1");
        }
        return resultMap;
    }
}
