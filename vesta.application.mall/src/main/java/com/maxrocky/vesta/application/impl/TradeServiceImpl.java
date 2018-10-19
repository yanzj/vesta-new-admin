package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.AdminDTO.TradeDTO;
import com.maxrocky.vesta.application.AdminDTO.TradeExDTO;
import com.maxrocky.vesta.application.AdminDTO.TradeQuerry;
import com.maxrocky.vesta.application.inf.TradeService;
import com.maxrocky.vesta.domain.model.TradeEntity;
import com.maxrocky.vesta.domain.repository.TradeRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.ExportExcel;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/16.
 */
@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    TradeRepository tradeRepository;

    @Override
    public List<TradeDTO> getTradeAll(TradeQuerry t, WebPage webPage) {
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("tradeno",t.getTradeNo());   //订单号
        paramsMap.put("productName",t.getProductName());    //商品名称
        paramsMap.put("tradeStatus",t.getTradeStatus());   //订单状态
        paramsMap.put("name",t.getName());//姓名
        paramsMap.put("phone",t.getPhone()); //手机号
        paramsMap.put("weChatAppId",t.getWeChatAppId()); //微信号
        List<TradeDTO> dto = new ArrayList<>();
        List<Object>  list = tradeRepository.getTradeAll(paramsMap,webPage);
        for (Object i : list){
            TradeDTO td = new TradeDTO();
            Object[] obj = (Object[]) i;
            td.setTradeId((String)obj[0] == null ? "" : (String) obj[0]);
            td.setTradeNo((String)obj[1] == null ? "" : (String) obj[1]);
            td.setProductName((String)obj[2] == null ? "" : (String) obj[2]);
            td.setNumber((String)obj[3] == null ? "" : (String) obj[3]);
            td.setCreateOn((Date)obj[4] == null ? null : (Date) obj[4]);
            td.setName((String)obj[5] == null ? "" : (String) obj[5]);
            td.setPhone((String)obj[6] == null ? "" : (String) obj[6]);
            td.setAmount((String)obj[7] == null ? "" : (String) obj[7]);
            td.setTradeStatus((String)obj[8] == null ? "" : (String) obj[8]);
            td.setPayment((String)obj[10] == null ? "" : (String) obj[10]);
            dto.add(td);
        }
        return dto;
    }

    @Override
    public TradeDTO getTradeById(String id) {
        TradeDTO td = new TradeDTO();
        List<Object>  list = tradeRepository.getTradeById(id);
        for (Object i : list){
            Object[] obj = (Object[]) i;
            td.setTradeId((String)obj[0] == null ? "" : (String) obj[0]);
            td.setTradeNo((String)obj[1] == null ? "" : (String) obj[1]);
            td.setProductName((String)obj[2] == null ? "" : (String) obj[2]);
            td.setProductBusiness((String)obj[3] == null ? "" : (String) obj[3]);
            td.setNumber((String)obj[4] == null ? "" : (String) obj[4]);
            td.setCreateOn((Date)obj[5] == null ? null : (Date) obj[5]);
            td.setProductIntegral((Integer)obj[6] == null ? 0 : (Integer) obj[6]);
            td.setName((String)obj[7] == null ? "" : (String) obj[7]);
            td.setPhone((String)obj[8] == null ? "" : (String) obj[8]);
            td.setAmount((String)obj[9] == null ? "" : (String) obj[9]);
            td.setTradeStatus((String)obj[10] == null ? "" : (String) obj[10]);
            td.setAddress((String)obj[11] == null ? "" : (String) obj[11]);
            td.setMessage((String)obj[12] == null ? "" : (String) obj[12]);
            td.setUserPhone((String)obj[13] == null ? "" : (String) obj[13]);
            td.setUserName((String)obj[14] == null ? "" : (String) obj[14]);
        }
        return td;
    }

    @Override
    public void updateTrade(String id) {
        TradeEntity trade = tradeRepository.get(id);
        trade.setTradeStatus("2"); //发货
        tradeRepository.updateTrade(trade);
    }

    @Override
    public void exportTradeAllExcel(String title, String[] headers, ServletOutputStream out,TradeQuerry t) {
        WebPage webPage = new WebPage();
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("tradeno",t.getTradeNo());   //订单号
        paramsMap.put("productName",t.getProductName());    //商品名称
        paramsMap.put("tradeStatus",t.getTradeStatus());   //订单状态
        paramsMap.put("name",t.getName());//姓名
        paramsMap.put("phone",t.getPhone()); //手机号
        List<TradeExDTO> dto = new ArrayList<>();
        List<Object>  list = tradeRepository.getTradeAll(paramsMap,webPage);
        for (Object i : list){
            TradeExDTO td = new TradeExDTO();
            Object[] obj = (Object[]) i;
            td.setTradeNo((String)obj[1] == null ? "" : (String) obj[1]);
            td.setProductName((String)obj[2] == null ? "" : (String) obj[2]);
            td.setNumber((String)obj[3] == null ? "" : (String) obj[3]);
            td.setCreateOn((Date)obj[4] == null ? null : (Date) obj[4]);
            td.setName((String)obj[5] == null ? "" : (String) obj[5]);
            td.setPhone((String)obj[6] == null ? "" : (String) obj[6]);
            td.setAmount((String)obj[7] == null ? "" : (String) obj[7]);
            td.setPayment((String)obj[10] == null ? "" : (String) obj[10]);
            td.setTradeStatus((String)obj[8] == null ? "" : (String) obj[8]);
            td.setAddress((String)obj[9] == null ? "" : (String) obj[9]);
            dto.add(td);
        }

        ExportExcel<TradeExDTO> ex = new ExportExcel<TradeExDTO>();
        ex.exportExcel(title, headers, dto, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }
}
