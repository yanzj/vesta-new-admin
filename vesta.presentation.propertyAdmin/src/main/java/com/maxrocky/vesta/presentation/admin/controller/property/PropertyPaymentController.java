package com.maxrocky.vesta.presentation.admin.controller.property;

import com.maxrocky.vesta.application.DTO.PropertyIpItemDTO;
import com.maxrocky.vesta.application.DTO.PropertyPayOrderDTO;
import com.maxrocky.vesta.application.inf.PaymentService;
import com.maxrocky.vesta.application.inf.PropertyPayService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;
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
 * 物业缴费Controller
 * Created by Administrator on 2016/10/8.
 */
@Controller
@RequestMapping(value = "/propertyPayment")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class PropertyPaymentController {

    @Autowired
    private StaffUserService staffUserService;

    @Autowired
    private PropertyPayService propertyPayService;

//    @Autowired
//    private PaymentService paymentService;

    /**
     * 获取缴费单列表数据 WeiYangDong_2016-11-18
     */
    @RequestMapping(value = "/propertyPayOrderList.html")
    public ModelAndView getPropertyPayOrderList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                                PropertyPayOrderDTO propertyPayOrderDTO,WebPage webPage, Model model){
        try{
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
            propertyPayOrderDTO.setRoleScopeList(roleScopeList);
            //分页设置并回显
            webPage.setPageSize(20);
            List<Map<String, Object>> list = propertyPayService.getPropertyPayOrderList(propertyPayOrderDTO, null);
            webPage.setRecordCount(list.size());
            model.addAttribute("webPage",webPage);
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(), propertyPayOrderDTO.getMenuId());
            model.addAttribute("city", cityList);
            if (null != propertyPayOrderDTO.getScopeId() && !"".equals(propertyPayOrderDTO.getScopeId())) {
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), propertyPayOrderDTO.getScopeId(), propertyPayOrderDTO.getMenuId());
                model.addAttribute("projectList", projectList);
            }
            model.addAttribute("propertyPayOrderDTO", propertyPayOrderDTO);
            //收费项目列表回显
            PropertyIpItemDTO propertyIpItemDTO = new PropertyIpItemDTO();
            //有效收费项目标识
            propertyIpItemDTO.setState(1);
            List<PropertyIpItemEntity> propertyIpItemList = propertyPayService.getPropertyIpItemList(propertyIpItemDTO, null);
            model.addAttribute("propertyIpItemList",propertyIpItemList);
            //执行查询
            List<Map<String, Object>> payOrderList = propertyPayService.getPropertyPayOrderList(propertyPayOrderDTO, webPage);
            model.addAttribute("payOrderList",payOrderList);
        }catch (Exception e){
            e.printStackTrace();
            //发生异常返回报错提示信息
            model.addAttribute("error", "查询出现异常请联系管理员");
            return new ModelAndView("/property/PropertyPayOrderList");
        }
        return new ModelAndView("/property/PropertyPayOrderList");
    }

    /**
     * 获取缴费单详情 WeiYangDong_2016-11-18
     */
    @RequestMapping(value = "/propertyPayOrderInfo.html")
    public ModelAndView getPropertyPayOrderInfo(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                                PropertyPayOrderDTO propertyPayOrderDTO,Model model){
        try{
            //操作状态(0录入发票/1详情)
            model.addAttribute("operationType",propertyPayOrderDTO.getOperationType());
            //缴费订单信息
            Map<String, Object> propertyPayOrder = propertyPayService.getPropertyPayOrderById(propertyPayOrderDTO.getPayOrderIdDto());
            model.addAttribute("propertyPayOrder", propertyPayOrder);
            //缴费支付订单信息
//            PropertyPaymentEntity propertyPaymentEntity = propertyPayService.getPropertyPaymentByPayOrderId(propertyPayOrderDTO.getPayOrderIdDto());
//            model.addAttribute("propertyPayment",null);
//            if (null != propertyPaymentEntity){
//                //支付信息
//                PaymentEntity paymentEntity = paymentService.get(propertyPaymentEntity.getPaymentId());
//                model.addAttribute("payment",paymentEntity);
//                //票据信息
//                PropertyPayInvoiceEntity propertyPayInvoice = propertyPayService.getPropertyPayInvoiceByPaymentId(propertyPaymentEntity.getPaymentId());
//                model.addAttribute("propertyPayInvoice",propertyPayInvoice);
//            }
        }catch (Exception e){
            e.printStackTrace();
            //发生异常重定向到列表页
            return new ModelAndView("redirect:/propertyPayment/propertyPayOrderList.html");
        }
        return new ModelAndView("/property/PropertyPayOrderInfo");
    }

    /**
     * 获取缴费票据列表数据 WeiYangDong_2016-12-01
     */
    @RequestMapping(value = "/propertyPayInvoiceList.html")
    public ModelAndView getPropertyPayInvoiceList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertyStaffEntity,
                                                  PropertyPayOrderDTO propertyPayOrderDTO,Model model,WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/property/PropertyPayInvoiceList");
        try{
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertyStaffEntity.getStaffId());
            propertyPayOrderDTO.setRoleScopeList(roleScopeList);
            //分页设置并回显
            webPage.setPageSize(20);
            List<Map<String, Object>> list = propertyPayService.getPropertyPayInvoiceList(propertyPayOrderDTO, null);
            webPage.setRecordCount(list.size());
            model.addAttribute("webPage",webPage);
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertyStaffEntity.getStaffId(), propertyPayOrderDTO.getMenuId());
            model.addAttribute("city", cityList);
            if (null != propertyPayOrderDTO.getScopeId() && !"".equals(propertyPayOrderDTO.getScopeId())) {
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertyStaffEntity.getStaffId(), propertyPayOrderDTO.getScopeId(), propertyPayOrderDTO.getMenuId());
                model.addAttribute("projectList", projectList);
            }
            model.addAttribute("propertyPayOrderDTO", propertyPayOrderDTO);
            //执行检索
            List<Map<String, Object>> payInvoiceList = propertyPayService.getPropertyPayInvoiceList(propertyPayOrderDTO, webPage);
            model.addAttribute("payInvoiceList",payInvoiceList);
        }catch (Exception e){
            e.printStackTrace();
            //发生异常返回报错提示信息
            model.addAttribute("error", "查询出现异常请联系管理员");
        }
        return modelAndView;
    }

    /**
     * 获取缴费票据详情 WeiYangDong_2016-12-01
     */
    @RequestMapping(value = "/propertyPayInvoiceInfo.html")
    public ModelAndView getPropertyPayInvoiceInfo(PropertyPayOrderDTO propertyPayOrderDTO,Model model){
        ModelAndView modelAndView = new ModelAndView("/property/PropertyPayInvoiceInfo");
        try{
            Map<String, Object> infoMap = propertyPayService.getPropertyPayInvoiceInfo(propertyPayOrderDTO);
            model.addAttribute("info",infoMap);
        }catch (Exception e){
            e.printStackTrace();
            //发生异常返回报错提示信息
            model.addAttribute("error", "查询出现异常请联系管理员");
        }
        return modelAndView;
    }

    /**
     * 保存或更新缴费票据信息
     */
    @ResponseBody
    @RequestMapping(value = "/updateInvoice",method= RequestMethod.POST)
    public Map<String,Object> updateInvoice(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                            @Valid PropertyPayOrderDTO propertyPayOrderDTO){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            propertyPayOrderDTO.setCreateBy(userPropertystaffEntity.getStaffName());
            propertyPayService.saveOrUpdateInvoice(propertyPayOrderDTO);
            resultMap.put("error","0");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error","1");
        }
        return resultMap;
    }
}
