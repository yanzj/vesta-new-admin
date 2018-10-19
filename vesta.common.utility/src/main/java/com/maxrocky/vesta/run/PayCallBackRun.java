package com.maxrocky.vesta.run;

import com.maxrocky.vesta.utility.HttpURLConnectionTools;

import java.util.Map;

/**
 * Created by Tom on 2016/2/20 11:47.
 * Describe:支付回调
 */
public class PayCallBackRun implements Runnable {

    private String url;//访问地址
    private Map paramsMap;//Map参数

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map getParamsMap() {
        return paramsMap;
    }

    public void setParamsMap(Map paramsMap) {
        this.paramsMap = paramsMap;
    }

    @Override
    public void run() {
        HttpURLConnectionTools.postJsonMap(url, paramsMap);
    }
}
