package com.maxrocky.vesta.presentation.admin.controller.mall;

import com.maxrocky.vesta.application.AdminDTO.MallDTO;
import com.maxrocky.vesta.application.AdminDTO.TradeDTO;
import com.maxrocky.vesta.application.AdminDTO.TradeQuerry;
import com.maxrocky.vesta.application.AdminDTO.UserGiftDTO;
import com.maxrocky.vesta.application.inf.DefaultConfigService;
import com.maxrocky.vesta.application.inf.TradeService;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/16.
 */
@Controller
@RequestMapping(value = "/trade")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class TradeController {

    @Autowired
    TradeService tradeService;

    @Autowired
    DefaultConfigService defaultConfigService;

    /**
     * Describe:获取
     */
    @RequestMapping(value = "/tradeList.html")
    public ModelAndView tradeList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntitye , TradeQuerry q,
                                    WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/mall/TradeList");
        try{
            webPage.setPageSize(20);
            List<TradeDTO> getTradeSize = tradeService.getTradeAll(q,null);
            modelAndView.addObject("list",tradeService.getTradeAll(q,webPage));
            webPage.setRecordCount(getTradeSize.size());
            modelAndView.addObject("defaultConfig", defaultConfigService.getClientConfigList(null, null));

            modelAndView.addObject("webPage", webPage);
            modelAndView.addObject("q", q);

        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * Describe:获取
     */
    @RequestMapping(value = "/tradeDetail.html")
    public ModelAndView tradeDetail(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntitye , TradeQuerry q){
        ModelAndView modelAndView = new ModelAndView("/mall/TradeDetail");
        try{
            modelAndView.addObject("list",tradeService.getTradeById(q.getTradeId()));

        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }


    /**
     * Describe:编辑/新增
     */
    @ResponseBody
    @RequestMapping(value = "/updateTrade")
    public ModelAndView updateTrade(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                       TradeQuerry d){
        ModelAndView modelAndView = new ModelAndView("/mall/TradeList");
        Map<String,Object> resultMap = new HashedMap();
        try{
            tradeService.updateTrade(d.getTradeId());
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return modelAndView;
    }


    /**
     *
     */
    @ResponseBody
    @RequestMapping("/exportTradeAllExcel")
    public String exportTradeAllExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity user
            , TradeQuerry q,
                                         HttpServletResponse response, HttpServletRequest request){
        try {
            //获取数据范围权限

            String fileName = "订单管理";
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
            String title = "订单管理列表";
            String[] headers = {"订单号", "商品名称", "订单时间", "购买数量", "支付积分", "支付金额", "业主","联系方式","状态","收货地址"};
            ServletOutputStream out = response.getOutputStream();
            tradeService.exportTradeAllExcel(title,headers,out,q);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }
}
