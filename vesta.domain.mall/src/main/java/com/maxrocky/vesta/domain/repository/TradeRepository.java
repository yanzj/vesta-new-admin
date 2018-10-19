package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.TradeEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/16.
 */
public interface TradeRepository {

    List<Object> getTradeAll(Map<String,Object> paramsMap, WebPage webPage);

    List<Object> getTradeById(String id);

    void updateTrade(TradeEntity t);

    TradeEntity get(String id);
}
