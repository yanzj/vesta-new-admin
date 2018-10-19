package com.maxrocky.vesta.presentation.admin.controller.payment;

import com.maxrocky.vesta.application.admin.dto.BillInfoAdminListResDto;
import com.maxrocky.vesta.application.BillInfoService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by liuwei on 2016/3/5.
 */
@Controller
@RequestMapping("/billInfo")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})

public class BillInfoController {

    @Autowired
    BillInfoService billInfoService;
    @RequestMapping("/getBillInfoList")
    public ModelAndView getBillInfoList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity, WebPage webPage){

        ModelAndView modelAndView = new ModelAndView("/payment/billInfo");
        try {

            if(userPropertystaffEntity != null){
                String projectId = userPropertystaffEntity.getQueryScope();
                List<BillInfoAdminListResDto> billInfoList = billInfoService.getBillInfoList(projectId,webPage);
                modelAndView.addObject("billInfoList",billInfoList);
            }

        } catch (Exception e){
            e.printStackTrace();

        }
        modelAndView.addObject("webPage",webPage);
        return modelAndView;
    }

    @RequestMapping("/updateBillInfoStatus")
    @ResponseBody
    public ApiResult updateBillInfoStatus(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity, String billInfoId){
        try {
            if(userPropertystaffEntity == null || StringUtil.isEmpty(userPropertystaffEntity.getQueryScope())){
                throw new Exception("用户操作不合法");
            }
            return this.billInfoService.updateBillInfoStatus(userPropertystaffEntity,billInfoId);
        }catch (Exception e){
            return  ErrorResource.getError("tip_00000031");
        }
    }


    /***
     * 导出excel操作
     * @param userPropertystaffEntity
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public String exportExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest){

        try {
            if(userPropertystaffEntity == null){
                return "请登录";
            }
            return this.billInfoService.exportExcel(userPropertystaffEntity,httpServletResponse, httpServletRequest);
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }

    }
}
