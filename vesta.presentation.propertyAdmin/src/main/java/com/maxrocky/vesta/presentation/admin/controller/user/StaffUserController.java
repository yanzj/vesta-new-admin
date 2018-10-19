package com.maxrocky.vesta.presentation.admin.controller.user;

import com.maxrocky.vesta.application.DTO.admin.UserPropertystaffDTO;
import com.maxrocky.vesta.application.admin.dto.TransfersDto;
import com.maxrocky.vesta.application.inf.AnnouncementService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.ms.Integraltype;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员账户管理_Controller
 * Created by WeiYangDong on 2016/8/11.
 */
@Controller
@RequestMapping(value = "/staffUser")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class StaffUserController {

    @Autowired
    private StaffUserService staffUserService;

//    @Autowired
//    private AnnouncementService announcementService;

    /**
     * 获取账号信息列表
     */
    @RequestMapping(value = "/staffUserList.html")
    public ModelAndView staffUserList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                      @Valid UserPropertystaffDTO userPropertystaffDTO,WebPage webPage, Model model){
        try{
            //查询所有CRM项目城市列表
            List<Map<String, Object>> cityList = staffUserService.listCity();
            model.addAttribute("city", cityList);

            List<Map<String, Object>> staffUserList = staffUserService.getStaffUserList(userPropertystaffDTO, webPage);
            model.addAttribute("staffUserList",staffUserList);
            model.addAttribute("isExcel", staffUserList.size());
            webPage.setPageSize(20);
            webPage.setPageIndex(webPage.getPageIndex());
            webPage.setRecordCount(staffUserService.getStaffUserCount(userPropertystaffDTO).intValue());
            //TODO 项目回显
            //回显权限参与的项目列表及项目
            List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), userPropertystaffDTO.getScopeId(), "002300020000");
            if(null != projectList && projectList.size() > 0){
                if (projectList.get(0)[0].equals("0")){
                    //projectList.remove(0);
                }
            }
            model.addAttribute("projectList", projectList);
            model.addAttribute("userPropertystaffDTO",userPropertystaffDTO);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error", "查询出现异常请联系管理员");
            return new ModelAndView("/user/StaffUserList");
        }
        return new ModelAndView("/user/StaffUserList");
    }

    /**
     * 重置员工用户密码(默认密码为123456)
     */
    @ResponseBody
    @RequestMapping(value = "/resetPassWord",method= RequestMethod.POST)
    public Map<String,Object> resetPassWord(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                            @Valid UserPropertystaffDTO userPropertystaffDTO){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            userPropertystaffDTO.setModifyByDto(userPropertystaffEntity.getStaffName());
            boolean flag = staffUserService.resetPassWord(userPropertystaffDTO);
            if (flag){
                resultMap.put("error","0");//重置成功
            }else{
                resultMap.put("error","1");//重置失败
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error", "1"); //重置失败
        }
        return resultMap;
    }

    /**
     * 删除员工用户
     */
    @ResponseBody
    @RequestMapping(value = "/delStaffUser",method= RequestMethod.POST)
    public Map<String,Object> delStaffUser(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                           @Valid UserPropertystaffDTO userPropertystaffDTO){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            userPropertystaffDTO.setModifyByDto(userPropertystaffEntity.getStaffName());
            boolean flag = staffUserService.delStaffUser(userPropertystaffDTO);
            if (flag){
                resultMap.put("error","0");//删除成功
            }else{
                resultMap.put("error","1");//删除失败
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error", "1"); //删除失败
        }
        return resultMap;
    }

    /**
     * 去用户编辑页面
     */
    @RequestMapping(value = "/toEditStaffUser.html")
    public ModelAndView toEditStaffUser(@Valid UserPropertystaffDTO userPropertystaffDTO,Model model){
        try{
            if (null != userPropertystaffDTO.getStaffIdDto() && !"".equals(userPropertystaffDTO.getStaffIdDto())){
                //编辑
                UserPropertyStaffEntity staffUserEntity = staffUserService.getStaffUserById(userPropertystaffDTO.getStaffIdDto());
                userPropertystaffDTO.setStaffIdDto(staffUserEntity.getStaffId());
                userPropertystaffDTO.setUserNameDto(staffUserEntity.getUserName());
                userPropertystaffDTO.setStaffNameDto(staffUserEntity.getStaffName());
                userPropertystaffDTO.setMobileDto(staffUserEntity.getMobile());
                userPropertystaffDTO.setEmail(staffUserEntity.getEmail());
                userPropertystaffDTO.setScope(staffUserEntity.getScope());
                userPropertystaffDTO.setProjectName(staffUserEntity.getProject());
                userPropertystaffDTO.setCompany(staffUserEntity.getCompany());
                userPropertystaffDTO.setPasswordDto(staffUserEntity.getPassword());
                model.addAttribute("staffUser",userPropertystaffDTO);
                //角色回显
                List<Map<String, Object>> roleDescList = staffUserService.getRoleByStaffId(userPropertystaffDTO.getStaffIdDto());
                model.addAttribute("roleList",roleDescList);
            }
            model.addAttribute("error","0");
        }catch (Exception e){
            e.printStackTrace();
            return new ModelAndView("/staffUser/staffUserList.html");
        }
        return new ModelAndView("/user/StaffUserUpdate");
    }

    /**
     * 保存或更新员工用户
     */
    @RequestMapping(value = "/saveOrUpdateStaffUser")
    public ModelAndView saveOrUpdateStaffUser(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                              @Valid UserPropertystaffDTO userPropertystaffDTO,Model model){
        try{
            //设置操作人
            userPropertystaffDTO.setModifyByDto(userPropertystaffEntity.getStaffName());
            //保存员工信息
            UserPropertyStaffEntity staffUser = staffUserService.saveOrUpdateStaffUser(userPropertystaffDTO);
            //保存员工角色信息
            userPropertystaffDTO.setStaffIdDto(staffUser.getStaffId());
            staffUserService.saveOrUpdateRoleanthority(userPropertystaffDTO);
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error","1");
            model.addAttribute("staffUser", userPropertystaffDTO);
            return new ModelAndView("/user/StaffUserUpdate");
        }
        return new ModelAndView("redirect:/staffUser/staffUserList.html");
    }

    /**
     * 去编辑密码页面
     */
    @RequestMapping(value = "/toEditStaffUserPassword")
    public ModelAndView toEditStaffUserPassword(Model model){
        return new ModelAndView("/user/StaffUserPasswordEdit");
    }

    /**
     * 更新员工密码
     */
    @ResponseBody
    @RequestMapping(value = "/updateStaffUserPassword",method = RequestMethod.POST)
    public Map<String,Object> updateStaffUserPassword(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                                      @Valid UserPropertystaffDTO userPropertystaffDTO,Model model){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            //设置操作人
            userPropertystaffDTO.setModifyByDto(userPropertystaffEntity.getStaffName());
            //设置操作员工
            userPropertystaffDTO.setStaffIdDto(userPropertystaffEntity.getStaffId());
            if (userPropertystaffDTO.getNewPassword_1().equals(userPropertystaffDTO.getNewPassword_2())){
                staffUserService.updatePassWord(userPropertystaffDTO);
            }else{
                resultMap.put("error","1");
            }
            resultMap.put("error","0");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error","-1");
        }
        return resultMap;
    }

    /**
     * 去批量设置角色页面_2016-09-29_Wyd
     */
    @RequestMapping(value = "/toBatchSetRole")
    public ModelAndView toBatchSetRole(@Valid UserPropertystaffDTO userPropertystaffDTO,Model model){
        try{
            if (null != userPropertystaffDTO.getBatchRoleState() && userPropertystaffDTO.getBatchRoleState().equals("all")){
                List<Map<String, Object>> staffUserList = staffUserService.getStaffUserList(userPropertystaffDTO, null);
                model.addAttribute("staffUserList", staffUserList);
            }else{
                List<Map<String, Object>> staffUserList = staffUserService.getBatchStaffUser(userPropertystaffDTO);
                model.addAttribute("staffUserList", staffUserList);
            }
            return new ModelAndView("/user/StaffUserBatchRoleSet");
        }catch (Exception e){
            e.printStackTrace();
            return new ModelAndView("redirect:/staffUser/staffUserList.html");
        }
    }

    /**
     * 批量设置角色_2016-10-21_Wyd
     */
    @RequestMapping(value = "/batchSetRole",method = RequestMethod.POST)
    public ModelAndView batchSetRole(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                     @Valid UserPropertystaffDTO userPropertystaffDTO,Model model){
        try{
            //设置操作人
            userPropertystaffDTO.setModifyByDto(userPropertystaffEntity.getStaffName());
            //保存员工角色信息
            staffUserService.batchSetRole(userPropertystaffDTO);
            return new ModelAndView("redirect:/staffUser/staffUserList.html");
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error","1");
            model.addAttribute("staffUser", userPropertystaffDTO);
            return new ModelAndView("/user/StaffUserBatchRoleSet");
        }
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Param:
    *  @Description: 导出excel
    */
    @RequestMapping(value = "/exportExcel")
    public String exportExcel(HttpServletRequest request, HttpServletResponse response, @Valid UserPropertystaffDTO userPropertystaffDTO,WebPage webPage, Model model) {
        //获取所有账户
        try {
            List<Map<String, Object>> staffUserList = staffUserService.getStaffUserList(userPropertystaffDTO, webPage);
            return staffUserService.exportExcel(request, response, staffUserList, webPage);
        }catch (Exception e) {
            e.printStackTrace();
            return "系统错误";
        }
    }
}
