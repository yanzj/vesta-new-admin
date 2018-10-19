package com.maxrocky.vesta.presentation.admin.controller.system;

import com.maxrocky.vesta.application.AdminDTO.SystemLogDTO;
import com.maxrocky.vesta.application.DTO.admin.HouseProjectDto;
import com.maxrocky.vesta.application.admin.dto.TransfersDto;
import com.maxrocky.vesta.application.impl.DefaultConfigServiceImpl;
import com.maxrocky.vesta.application.inf.AnnouncementService;
import com.maxrocky.vesta.application.inf.HouseProjectService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.inf.SystemLogService;
import com.maxrocky.vesta.domain.model.ClientConfigEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chen on 2016/7/19.
 */

@Controller
@RequestMapping(value = "/log")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class LogController {
    @Autowired
    SystemLogService systemLogService;
    @Autowired
    HouseProjectService houseProjectService;
    @Autowired
    AnnouncementService announcementService;
    @Autowired
    StaffUserService staffUserService;
    @Autowired
    DefaultConfigServiceImpl defaultConfigService;

    /**
     * 新增用户日志管理页
     * */
    @RequestMapping(value = "/addUserLog.html")
    public String addUserLog(@Valid SystemLogDTO systemLogDTO,Model model,WebPage webPage){
        //分页
        webPage.setPageSize(30);
        //查询
        List<SystemLogDTO> systemLogDTOList = systemLogService.getSystemLogList(systemLogDTO, webPage);
        //回显
        Map projects =houseProjectService.getProjects();
        model.addAttribute("logList",systemLogDTOList);
        model.addAttribute("projectList",projects);
        model.addAttribute("sysLog",systemLogDTO);
        model.addAttribute("isExcel",systemLogDTOList.size());
        List<ClientConfigEntity> clientConfigList = defaultConfigService.getClientConfigList(null, null);
        model.addAttribute("clientConfigList",clientConfigList);
        return "log/addUserLog";
    }

    /**
     * 用户访问日志管理页
     * */
    @RequestMapping(value = "/userVisitLog.html")
    public String userVisitLog(@Valid SystemLogDTO systemLogDTO,Model model,WebPage webPage,HttpServletRequest request){
        //systemLogDTO.setLogType("1");
        webPage.setPageSize(30);
        String cityId=request.getParameter("city");
        List<SystemLogDTO> cityAllInfo=new ArrayList<SystemLogDTO>();//
        List<SystemLogDTO> allCityproect=new ArrayList<SystemLogDTO>();//
    /*    if("0".equals(cityId)||cityId==null){
            //城市为全部城市,查询所有

            //allCityproect=systemLogService.getuserVisitlLogList(systemLogDTO,webPage);

        }
        else{
            //查询的是具体的一个城市下面的项目
            List<HouseProjectDto> houseProjects=houseProjectService.getProject(cityId);
            if(houseProjects!=null){
                for(HouseProjectDto HouseProject:houseProjects){
                    String projectName=HouseProject.getName();
                    systemLogDTO.setProjectId(projectName);
                    //webPage=null;
                    List<SystemLogDTO> systemLogDTOList= systemLogService.getuserVisitlLogList(systemLogDTO,webPage);
                    cityAllInfo.addAll(systemLogDTOList);
                }
            }
        }*/
        List<SystemLogDTO> systemLogDTOList=systemLogService.getuserVisitlLogList(systemLogDTO,webPage);
       // List<SystemLogDTO> systemLogDTOList =
        Map projects =houseProjectService.getProjects();
        List<Object[]> objects = this.announcementService.listCity();
        List<TransfersDto> city1 = new ArrayList<TransfersDto>();
        city1.add(new TransfersDto("0", "全部城市"));
        for (Object[] object : objects) {
            String cid = object[0].toString();
            String name = object[1].toString();
            city1.add(new TransfersDto(cid, name));
        }
        model.addAttribute("city", city1);
        model.addAttribute("logList",systemLogDTOList);
        model.addAttribute("projectList",projects);
        model.addAttribute("sysLog",systemLogDTO);
        model.addAttribute("isExcel",systemLogDTOList.size());
        List<ClientConfigEntity> clientConfigList = defaultConfigService.getClientConfigList(null, null);
        model.addAttribute("clientConfigList",clientConfigList);
        return "log/userVisitLog";
    }



    /**
     * 用户单据日志管理页面
     * */
    @RequestMapping(value = "/userDocumentsLog.html")
    public String userDocumentsLog(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                   @Valid SystemLogDTO systemLogDTO,Model model,WebPage webPage,HttpServletRequest request){
        //检索条件回显
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(user.getStaffId(),systemLogDTO.getMenuId());
        model.addAttribute("city", cityList);
        if (null != systemLogDTO.getScopeId() && !"".equals(systemLogDTO.getScopeId())) {
            //城市不为空,回显项目列表
            List<Object[]> projectList = staffUserService.listProjectByCity(user.getStaffId(), systemLogDTO.getScopeId(), systemLogDTO.getMenuId());
            model.addAttribute("projectList", projectList);
        }
        //获取数据范围权限
        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
        systemLogDTO.setRoleScopeList(roleScopeList);
        webPage.setPageSize(30);
        List<SystemLogDTO> systemLogDTOList = systemLogService.getuserDocumentsLogList(systemLogDTO, webPage);

//        Map projects =houseProjectService.getProjects();
//        model.addAttribute("projectList",projects);
        model.addAttribute("logList",systemLogDTOList);
        model.addAttribute("sysLog",systemLogDTO);
        model.addAttribute("isExcel",systemLogDTOList.size());
        List<ClientConfigEntity> clientConfigList = defaultConfigService.getClientConfigList(null, null);
        model.addAttribute("clientConfigList",clientConfigList);
        return "log/userDocumentsLog";
    }

    /**
     * 信息发布日志管理页面
     * */
    @RequestMapping(value = "/infoReleaseLog.html")
    public String infoReleaseLog(@Valid SystemLogDTO systemLogDTO,Model model,WebPage webPage){
        webPage.setPageSize(30);
        // systemLogDTO.setLogType("1");
        List<SystemLogDTO> systemLogDTOList = systemLogService.getInfoReleaseLogList(systemLogDTO, webPage);
        // Map map = houseProjectService.getProjectInfo();
        Map projects =houseProjectService.getProjects();
        model.addAttribute("logList",systemLogDTOList);
        model.addAttribute("projectList",projects);
        model.addAttribute("sysLog",systemLogDTO);
        model.addAttribute("isExcel",systemLogDTOList.size());
        return "log/infoReleaseLog";
    }

    /***
     * 导出excel操作   新增用户日志导出
     * param:systemLogDTO
     * param:httpServletResponse
     * return
     */
    @RequestMapping("/addUserLogExportExcel")
    @ResponseBody
    public String addUserLogExportExcel(WebPage webPage,HttpServletResponse response, HttpServletRequest request,@Valid SystemLogDTO systemLogDTO){
        try {
//            return systemLogService.addUserLogExportExcel(systemLogDTO,httpServletResponse, httpServletRequest);
            String fileName = "新增用户日志列表";
            response.reset();
            response.setContentType("application/x-xls");
            String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            String agent = request.getHeader("USER-AGENT").toLowerCase();
            if (agent.contains("firefox")) {
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xls");
            } else {
                response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".xls");
            }
            String title = "新增用户日志";
            String[] headers = {"序号", "注册时间", "用户昵称", "用户类型", "手机号", "身份证","来源","系统版本","项目"};
            ServletOutputStream out = response.getOutputStream();
            systemLogService.addUserLogExportExcel2(title,headers,out,systemLogDTO);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }

    /***
     * 导出excel操作   用户访问日志导出
     * param:systemLogDTO
     * param:httpServletResponse
     * return
     */
    @RequestMapping("/userVisitLogExportExcel")
    @ResponseBody
    public String userVisitLogExportExcel(WebPage webPage,HttpServletResponse response,HttpServletRequest request,@Valid SystemLogDTO systemLogDTO){
        try {
//            return systemLogService.userVisitLogExportExcel(systemLogDTO, httpServletResponse, httpServletRequest);
            String fileName = "用户访问日志列表";
            response.reset();
            response.setContentType("application/x-xls");
            String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            String agent = request.getHeader("USER-AGENT").toLowerCase();
            if (agent.contains("firefox")) {
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xls");
            } else {
                response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".xls");
            }
            String title = "用户访问日志";
            String[] headers = {"序号", "访问时间", "用户昵称", "用户类型", "手机号", "访问版块","访问功能","访问内容","访问来源"};
            ServletOutputStream out = response.getOutputStream();
            systemLogService.userVisitLogExportExcel2(title,headers,out,systemLogDTO);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }

    /***
     * 导出excel操作   用户单据日志导出
     * param:systemLogDTO
     * param:httpServletResponse
     * return
     */
    @RequestMapping("/userDocumentsLogExportExcel")
    @ResponseBody
    public String userDocumentsLogExportExcel(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                              WebPage webPage,HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest,@Valid SystemLogDTO systemLogDTO){
        try {
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
            systemLogDTO.setRoleScopeList(roleScopeList);
            return systemLogService.userDocumentsLogExportExcel(systemLogDTO, httpServletResponse, httpServletRequest);
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }

    /***
     * 导出excel操作   信息发布日志导出
     * param:systemLogDTO
     * param:httpServletResponse
     * return
     */
    @RequestMapping("/infoReleaseLogExportExcel")
    @ResponseBody
    public String infoReleaseLogExportExcel(WebPage webPage,HttpServletResponse response,HttpServletRequest request,@Valid SystemLogDTO systemLogDTO){
        try {
//            return systemLogService.infoReleaseLogExportExcel(systemLogDTO, httpServletResponse, httpServletRequest);
            String fileName = "信息发布日志列表";
            response.reset();
            response.setContentType("application/x-xls");
            String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            String agent = request.getHeader("USER-AGENT").toLowerCase();
            if (agent.contains("firefox")) {
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xls");
            } else {
                response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".xls");
            }
            String title = "信息发布日志";
            String[] headers = {"序号", "发布时间", "后台用户名","版块","功能","操作类型","关联社区","发布内容"};
            ServletOutputStream out = response.getOutputStream();
            systemLogService.infoReleaseLogExportExcel2(title,headers,out,systemLogDTO);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }

    /**
     * Describe:清除新增用户日志重复数据
     * CreateBy:WeiYangDong_2017-10-16
     */
    @ResponseBody
    @RequestMapping("/cleanSystemLogRepeatData")
    public String cleanRepeatData(){
        systemLogService.cleanSystemLogRepeatData();
        return "清除成功！";
    }
}
