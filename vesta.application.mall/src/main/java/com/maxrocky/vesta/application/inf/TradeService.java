package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.AdminDTO.TradeDTO;
import com.maxrocky.vesta.application.AdminDTO.TradeQuerry;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/16.
 */
public interface TradeService {

    List<TradeDTO> getTradeAll(TradeQuerry t,WebPage webPage);

    TradeDTO getTradeById(String id);

    void updateTrade(String id);

    void exportTradeAllExcel(String title, String[] headers, ServletOutputStream out,TradeQuerry q);

    }
