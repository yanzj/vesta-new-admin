package com.maxrocky.vesta.presentation.admin.controller.notification;

import com.maxrocky.vesta.application.DTO.SMSAlertsDTO;
import com.maxrocky.vesta.application.DTO.SMSAlertsSearchDTO;
import com.maxrocky.vesta.application.DTO.SMSEditDTO;
import com.maxrocky.vesta.application.DTO.SMSPeopleAlertsDTO;
import com.maxrocky.vesta.application.inf.AnnouncementService;
import com.maxrocky.vesta.application.inf.HouseProjectService;
import com.maxrocky.vesta.application.inf.SMSAlertsService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.SMSPeopleAlertsEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.SMSAlertsRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 27978 on 2016/8/31.
 */
@Controller
@RequestMapping(value = "/smsMessage")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class SMSMessageController {

    @Autowired
    HouseProjectService houseProjectService;

    @Autowired
    AnnouncementService announcementService;

    @Autowired
    SMSAlertsService smsAlertsService;

    @Autowired
    StaffUserService staffUserService;

    @Autowired
    SMSAlertsRepository smsAlertsRepository;

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 跳转到短信列表页面
    */
    @RequestMapping(value = "/smsList")
    public String getSMSList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                             @Valid SMSAlertsSearchDTO smsAlertsSearchDTO,WebPage webPage, Model model){
        String menuId = "000100030000";
        //查询所有短信提醒人员
        //获取数据范围权限
        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
        smsAlertsSearchDTO.setRoleScopeList(roleScopeList);
        List<SMSAlertsDTO> list = smsAlertsService.getAllSMSPeople(smsAlertsSearchDTO, webPage);
        model.addAttribute("smsAlertsDTO", list);

        //搜索条件
        //根据项目编号查询该项目名称
        String projectName = new String();
        if (!StringUtils.isNullOrEmpty(smsAlertsSearchDTO.getProjectNum())) {
            projectName = smsAlertsRepository.getProjectName(smsAlertsSearchDTO.getProjectNum());
            smsAlertsSearchDTO.setProjectName(projectName);
        }
        model.addAttribute("smsAlerts", smsAlertsSearchDTO);

        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(), menuId);
        model.addAttribute("city",cityList);
        //回显权限参与的项目列表及项目
        List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), smsAlertsSearchDTO.getCityNum(), menuId);
        if(null != projectList && projectList.size() > 0){
            if (projectList.get(0)[0].equals("0")){
                //projectList.remove(0);
            }
        }
        model.addAttribute("projectList",projectList);

        //获取提醒模块，这里一共三个模块，房屋报修管理，身份申诉管理，活动报名管理
        Map<String,String> map = new LinkedHashMap<>();
        map.put("0", "请选择");
        map.put("005700030000", "房屋报修管理");
        map.put("005200010000", "身份申诉管理");
        map.put("005400030000", "活动报名管理");
        map.put("005700010000", "物业缴费管理");
        map.put("006700030000", "商品订单管理");
        model.addAttribute("alertsModel", map);
        model.addAttribute("isExcel", list.size());
        return "/notification/SMSAlerts";
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 跳转到编辑短信页面
    */
    @RequestMapping(value = "/gotoSMSAlertsAdd")
    public String gotoSMSAlertsAdd(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid String menuId, Model model) {

        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(), menuId);
        model.addAttribute("city",cityList);

        return "/notification/SMSAlertsAdd";
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取某个城市下的最新短信消息
    */
    @RequestMapping(value = "/queryContent/{cityNum}/{projectNum}", method = RequestMethod.GET)
    public ApiResult queryContent(@PathVariable(value = "cityNum") String cityNum, @PathVariable(value = "projectNum") String projectNum) {
        SMSAlertsDTO smsAlertsDTO = smsAlertsService.getSMSAlerts(cityNum, projectNum);
        return new SuccessApiResult(smsAlertsDTO);
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 编辑短信
    */
    @RequestMapping(value = "/smsMessageAdd")
    public ModelAndView activityAdd(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid SMSEditDTO smsEditDTO){

        smsEditDTO.setOperator(userPropertystaffEntity.getStaffName());
        boolean result = smsAlertsService.addSMSAlerts(userPropertystaffEntity, smsEditDTO);
        return new ModelAndView("redirect:../smsMessage/smsList");
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 预新建单个人员
    */
    @RequestMapping(value = "/gotoSMSPeopleAdd")
    public String gotoSMSPeopleAdd(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, Model model) {
        //获取城市
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId());
        model.addAttribute("city", cityList);

        //获取提醒模块，这里一共三个模块，房屋报修管理，身份申诉管理，活动报名管理
        Map<String,String> map = new LinkedHashMap<>();
        map.put("0", "请选择");
        map.put("005700030000", "房屋报修管理");
        map.put("005200010000", "身份申诉管理");
        map.put("005400030000", "活动报名管理");
        map.put("005700010000", "物业缴费管理");
        map.put("006700030000", "商品订单管理");
        model.addAttribute("alertsModel", map);
        return "/notification/SMSPeopleAdd";
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 新建单个人员
    */
    @RequestMapping(value = "/smsPeopleAdd")
    public ModelAndView smsPeopleAdd(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid SMSPeopleAlertsDTO smsPeopleAlertsDTO) {
        smsPeopleAlertsDTO.setOperator(userPropertystaffEntity.getStaffName());
        smsAlertsService.addSMSPeople(userPropertystaffEntity, smsPeopleAlertsDTO);
        return new ModelAndView("redirect:../smsMessage/smsList");
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 预修改
    */
    @RequestMapping(value = "/gotoSMSPeopleUpdate")
    public String gotoSMSPeopleUpdate(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid String id, Model model) {
        SMSPeopleAlertsEntity smsPeopleAlertsEntity = smsAlertsService.getPeopleById(id);
        model.addAttribute("people", smsPeopleAlertsEntity);

        //获取城市
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId());
        model.addAttribute("city", cityList);

        //获取提醒模块，这里一共三个模块，房屋报修管理，身份申诉管理，活动报名管理
        Map<String,String> map = new LinkedHashMap<>();
        map.put("0", "请选择");
        map.put("005700030000", "房屋报修管理");
        map.put("005200010000", "身份申诉管理");
        map.put("005400030000", "活动报名管理");
        map.put("005700010000", "物业缴费管理");
        map.put("006700030000", "商品订单管理");
        model.addAttribute("alertsModel", map);
        return "/notification/SMSPeopleEdit";
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 编辑单个人员
    */
    @RequestMapping(value = "/smsPeopleUpdate")
    public ModelAndView smsPeopleUpdate(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid SMSAlertsDTO smsAlertsDTO) {
        smsAlertsDTO.setOperator(userPropertystaffEntity.getStaffName());
        smsAlertsService.updateSMSPeople(smsAlertsDTO);
        return new ModelAndView("redirect:../smsMessage/smsList");
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 删除人员
    */
    @RequestMapping(value = "/smsPeopleDelete")
    public ModelAndView smsPeopleDelete(@Valid String id) {
        smsAlertsService.deleteSMSPeople(id);
        return new ModelAndView("redirect:../smsMessage/smsList");
    }

    /**
    *  @Author: shanshan
    *  @Date:
    *  @Description: 下载模板
    */
    @RequestMapping(value="/downLoadExcel")
    @ResponseBody
    public String downLoadExcel(HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest,Model model){
        try {
            return smsAlertsService.downLoadTemplate(httpServletRequest, httpServletResponse);
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 导出excel
    */
    @RequestMapping(value = "/exportExcel")
    public String exportExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                              HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest,SMSAlertsDTO smsAlertsDTO, SMSAlertsSearchDTO smsAlertsSearchDTO, Model model, WebPage webPage){
        try {
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
            smsAlertsSearchDTO.setRoleScopeList(roleScopeList);
            return smsAlertsService.exportExcel(httpServletRequest, httpServletResponse, smsAlertsDTO, webPage, smsAlertsSearchDTO);
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 导入excel
    */
    @RequestMapping(value = "/uploadExcel")
    public String uploadExcel(@ModelAttribute("propertystaff") UserPropertyStaffEntity user, HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse, Model model) {
        try {
            MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
            MultipartFile file = request.getFile("myfile");
            InputStream fis = file.getInputStream();
            //POI:得到解析Excel的实体集合
            boolean flag = smsAlertsService.importEmployeeByPoi(user,fis);
            String result = "";
            if (!flag) {
                result = "导入失败！";
            }else{
                result = "导入成功！";
            }
            httpServletRequest.getSession().setAttribute("result", result);

            //关闭流
            fis.close();
            return "redirect:../smsMessage/smsList";
        }catch (Exception e){
            e.printStackTrace();
            httpServletRequest.getSession().setAttribute("result", "导入失败！");
            return "系统错误";
        }
    }
}
