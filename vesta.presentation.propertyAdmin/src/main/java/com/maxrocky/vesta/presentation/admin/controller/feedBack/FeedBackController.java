package com.maxrocky.vesta.presentation.admin.controller.feedBack;

import com.maxrocky.vesta.application.DTO.PropertyRectifyMagicDTO;
import com.maxrocky.vesta.application.DTO.adminDTO.FeedBackDTO;
import com.maxrocky.vesta.application.DTO.adminDTO.FeedBackExcelDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inf.FeedBackService;
import com.maxrocky.vesta.application.weekly.DTO.ExportExcel;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Itzxs on 2018/5/23.
 */
@Controller
@RequestMapping(value = "/feedBack")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class FeedBackController {

    @Autowired
    FeedBackService feedBackService;

    @Autowired
    AuthAgencyService authAgencyService;

    @RequestMapping("/getFeedBackList.html")
    public String getFeedBackList(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, @Valid FeedBackDTO feedBackDTO, Model model, WebPage webPage){
        /*if(feedBackService.checkRoot(userInformationEntity.getStaffId())){
        }*/
        List<FeedBackDTO> feedBackDTOS = feedBackService.getFeedBackList(feedBackDTO,webPage);

        CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
        List<String> function = authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(), "4", "1");
        //意见反馈  搜索QCH40010132    导出QCH40010133   详情QCH40010134
        for (int i = 0; i < function.size(); i++) {
            switch (function.get(i).toString()) {
                case "QCH40010132":
                    checkAuthFunctionDTO.setQch40010132("Y");
                    break;
                case "QCH40010133":
                    checkAuthFunctionDTO.setQch40010133("Y");
                    break;
                case "QCH40010134":
                    checkAuthFunctionDTO.setQch40010134("Y");
                    break;
            }
        }
        model.addAttribute("feedBacks",feedBackDTOS);
        model.addAttribute("feedBackDTO",feedBackDTO);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "feedBack/feedBack";
    }

    @RequestMapping(value = "/getDetail", method = RequestMethod.POST)
    public Map<String,Object> getDetail(@ModelAttribute("authPropertystaff")UserInformationEntity userPropertystaffEntity,
                                          Model model, @Valid FeedBackDTO feedBackDTO){
        Map<String,Object> resultMap = new HashMap();
        resultMap.put("check",1);
        if(feedBackDTO != null){
            if(feedBackDTO.getId() != null && !"".equals(feedBackDTO.getId())){
                resultMap.put("data",feedBackService.getDetail(feedBackDTO));
                resultMap.put("check",0);
                return resultMap;
            }
            return resultMap;
        }
        return resultMap;
    }

    @RequestMapping(value = "/printExportExcel")
    @ResponseBody
    public void printExportExcel(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest,
                                   FeedBackDTO feedBackDTO){
        List<FeedBackDTO> feedBackDTOS = feedBackService.getFeedBackList(feedBackDTO,null);
        List<FeedBackExcelDTO> feedBackExcelDTOS = new ArrayList<>();
        if(feedBackDTOS != null && !feedBackDTOS.isEmpty()){
            for (int i = 0; i < feedBackDTOS.size(); i++) {
                FeedBackExcelDTO feedBackExcelDTO = new FeedBackExcelDTO();
                feedBackExcelDTO.setId(String.valueOf(i+1));
                feedBackExcelDTO.setUserId(feedBackDTOS.get(i).getUserId());
                feedBackExcelDTO.setPhone(feedBackDTOS.get(i).getPhone());
                feedBackExcelDTO.setContent(feedBackDTOS.get(i).getContent());
                feedBackExcelDTO.setCreateDate(feedBackDTOS.get(i).getCreateDate());
                feedBackExcelDTOS.add(feedBackExcelDTO);
            }
        }
        String [] headArr = {"序号","用户名","联系方式","意见反馈内容","提交时间"};

        ExportExcel ee = new ExportExcel();
        ee.exportExcel(headArr,feedBackExcelDTOS,"意见反馈",httpServletResponse);
    }
}
