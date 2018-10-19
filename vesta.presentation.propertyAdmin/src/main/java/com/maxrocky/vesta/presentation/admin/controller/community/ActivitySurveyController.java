package com.maxrocky.vesta.presentation.admin.controller.community;

import com.maxrocky.vesta.application.admin.dto.ActivitySurveyDTO;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.service.ActivitySurveyService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author WeiYangDong
 * @date 2018/5/10 13:54
 * @deprecated 线下活动调查
 */
@Controller
@RequestMapping(value = "/activitySurvey")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class ActivitySurveyController {

    @Autowired
    StaffUserService staffUserService;

    @Autowired
    ActivitySurveyService activitySurveyService;

    /**
     * 通过城市ID获取项目列表
     */
    @ResponseBody
    @RequestMapping(value = "/queryProjectNameByCityId/{cityId}", method = RequestMethod.GET)
    public ApiResult getProjectByCityId(@PathVariable(value = "cityId") String cityId){
        List<Map<String, Object>> projectList = activitySurveyService.getProjectList(cityId);
        return new SuccessApiResult(projectList);
    }

    /**
     * 获取活动调查列表
     */
    @RequestMapping(value = "/getActivitySurveyList.html")
    public ModelAndView getActivitySurveyList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                              ActivitySurveyDTO activitySurveyDTO,
                                              WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/community/ActivitySurveyList");
        try{
            //分页设置并回显
            webPage.setPageSize(20);
            List<ActivitySurveyDTO> list = activitySurveyService.getList(activitySurveyDTO, null);
            webPage.setRecordCount(list.size());
            modelAndView.addObject("webPage", webPage);
            //导出Excel总数回显
            modelAndView.addObject("isExcel", list.size());
            //检索条件回显
//            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),activitySurveyDTO.getMenuId());
            List<Map<String, Object>> cityList = activitySurveyService.getCityList();
            modelAndView.addObject("city", cityList);
            if (null != activitySurveyDTO.getCityId() && !"".equals(activitySurveyDTO.getCityId())){
                //城市不为空,回显项目列表
//                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), activitySurveyDTO.getCityId(), activitySurveyDTO.getMenuId());
                List<Map<String, Object>> projectList = activitySurveyService.getProjectList(activitySurveyDTO.getCityId());
                modelAndView.addObject("projectList",projectList);
            }
            modelAndView.addObject("activitySurveyDTO",activitySurveyDTO);
            //执行查询
            List<ActivitySurveyDTO> activitySurveyList = activitySurveyService.getList(activitySurveyDTO, webPage);
            modelAndView.addObject("activitySurveyList",activitySurveyList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 获取活动调查详情
     */
    @RequestMapping(value = "/getActivitySurveyDetail.html")
    public ModelAndView getActivitySurveyDetail(ActivitySurveyDTO activitySurveyDTO){
        ModelAndView modelAndView = new ModelAndView("/community/ActivitySurveyDetail");
        try{
            //执行查询
            ActivitySurveyDTO activitySurveyDetail = activitySurveyService.getListById(activitySurveyDTO);
            modelAndView.addObject("activitySurveyDetail",activitySurveyDetail);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 去编辑活动回顾
     */
    @RequestMapping(value = "/toEditActivitySurveyInfo.html")
    public ModelAndView toEditActivitySurveyInfo(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                                 ActivitySurveyDTO activitySurveyDTO){
        ModelAndView modelAndView = new ModelAndView("/community/ActivitySurveyInfoEdit");
        try{
            //执行查询
            ActivitySurveyDTO activitySurveyInfo = activitySurveyService.getInfoById(activitySurveyDTO);
            modelAndView.addObject("activitySurveyInfo",activitySurveyInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 保存或更新活动回顾
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdateActivitySurveyInfo", method = RequestMethod.POST, produces="application/json")
    public Map<String,Object> saveOrUpdateActivitySurveyInfo(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                                             ActivitySurveyDTO activitySurveyDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            //设置操作人
            activitySurveyDTO.setModifyBy(userPropertystaffEntity.getStaffName());
            activitySurveyService.saveOrUpdateActivitySurveyInfo(activitySurveyDTO);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }


    /***
     * 导出excel
     */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public String exportExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                              ActivitySurveyDTO activitySurveyDTO,
                              HttpServletResponse response,
                              HttpServletRequest request){
        try {
            String fileName = "今日我当班列表";
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
            String title = "今日我当班列表";
            String[] headers = {"序号","当班时间","当班项目","当班内容","个人感受","提升建议","电子签名","现场图片"};
            ServletOutputStream out = response.getOutputStream();
            activitySurveyService.exportExcel(title,headers,out,activitySurveyDTO);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }
}
