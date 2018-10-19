package com.maxrocky.vesta.presentation.admin.controller.user;

import com.maxrocky.vesta.application.AdminDTO.UserIntegralRuleRecordDTO;
import com.maxrocky.vesta.application.AdminDTO.UserIntegralRuleRecordEntityDTO;
import com.maxrocky.vesta.application.AdminDTO.UserIntegralRuleRecordQuerry;
import com.maxrocky.vesta.application.DTO.admin.QualityAccessLogDTO;
import com.maxrocky.vesta.application.DTO.admin.QuestionQuerry;
import com.maxrocky.vesta.application.DTO.admin.QuestionnaireDTO;
import com.maxrocky.vesta.application.DTO.admin.QuestionnaireQuerry;
import com.maxrocky.vesta.application.inf.*;
import com.maxrocky.vesta.domain.model.QuestionnaireEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/11.
 */
@Controller
@RequestMapping(value = "/quest")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class QuestionController {
    @Autowired
    QuestionnaireService questionnaireService;

    @Autowired
    QuestionTitleService questionTitleService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    StaffUserService staffUserService;

    @Autowired
    DefaultConfigService defaultConfigService;
    /**
     * 主页
     */
    @RequestMapping(value = "/quest.html")
    public ModelAndView quest(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                            QuestionnaireQuerry q,
                                            WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/quest/quest");
        try{
            //分页设置并回显
            webPage.setPageSize(20);
            List<QuestionnaireDTO> getListSize = questionnaireService.getAll(q,null);
            List<QuestionnaireDTO> getList = questionnaireService.getAll(q,webPage);
            modelAndView.addObject("list", getList);
            webPage.setRecordCount(getListSize.size());
            modelAndView.addObject("webPage", webPage);
            modelAndView.addObject("q", q);



        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/questDetail.html")
    public ModelAndView getUserIntegralDetail(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                              String id){
        ModelAndView modelAndView = new ModelAndView("/quest/questDetail");
        try{
            QuestionnaireEntity q = questionnaireService.getDetail(id);
            modelAndView.addObject("quest", q);
            modelAndView.addObject("questtitle", questionTitleService.getAll(q));
            modelAndView.addObject("subject", subjectService.getAll(q));
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }


    @RequestMapping(value = "/questEdit.html")
    public ModelAndView getRule(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                QuestionQuerry q){
        ModelAndView modelAndView = new ModelAndView("/quest/questEdit");
        try{
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),q.getMenuId());
            modelAndView.addObject("city", cityList);
            QuestionnaireEntity qs = questionnaireService.getDetail(q.getQuestId());
            modelAndView.addObject("quest", qs);
            modelAndView.addObject("questtitle", questionTitleService.getAll(qs));
            modelAndView.addObject("subject", subjectService.getAll(qs));
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 批量导入
     */
    @ResponseBody
    @RequestMapping(value = "/addQuest")
    public Map<String,Object> uploadExcel(@ModelAttribute("propertystaff") UserPropertyStaffEntity user, QuestionQuerry q) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            questionnaireService.test(q);
            resultMap.put("error", "0");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error", "-1");
        }
        return resultMap;
    }

    /**
     * 批量导入
     */
    @ResponseBody
    @RequestMapping(value = "/addImage")
    public Map<String,Object> addImage(@ModelAttribute("propertystaff") UserPropertyStaffEntity user, MultipartFile url) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            String c = questionnaireService.addImage(url);

            resultMap.put("uname",c);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error", "-1");
        }
        return resultMap;
    }

    /**
     * 逻辑删除调查问卷
     */
    @ResponseBody
    @RequestMapping(value = "/deleteQuest")
    public Map<String,Object> deleteQuest(String id){
        Map<String,Object> resultMap = new HashedMap();
        try{
            questionnaireService.deleteQuest(id);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

}
