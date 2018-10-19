package com.maxrocky.vesta.sms;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Magic on 2016/4/26.
 */
public class SDKHttpClient {

    public SDKHttpClient()
    {
    }
    static Logger logger = Logger.getLogger(SDKHttpClient.class);

    public static String registAndLogout(String url, String param)
    {
        String ret = "\u5931\u8D25";
        url = (new StringBuilder(String.valueOf(url))).append("?").append(param).toString();
        System.out.println((new StringBuilder("\u3010SDKHttpClient\u3011\u53D1\u9001\u8BF7\u6C42\u5230SDK->")).append(url).toString());
        String responseString = HttpClientUtil.getInstance().doGetRequest(url);
        responseString = responseString.trim();
        if(responseString != null && !"".equals(responseString))
            ret = xmlResponseForRegist(responseString);
        return ret;
    }

    public static String xmlResponseForRegist(String response)
    {
        String result = "\u5931\u8D25";
        Document document = null;
        try
        {
            document = DocumentHelper.parseText(response);
        }
        catch(DocumentException e)
        {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        result = root.elementText("error");
        return result;
    }

    public static List getMos(String url, String sn, String key)
    {
        List _Mos = new ArrayList();
        if("".equals(url))
            return _Mos;
        String param = (new StringBuilder("cdkey=")).append(sn).append("&password=").append(key).toString();
        url = (new StringBuilder(String.valueOf(url))).append("?").append(param).toString();
        System.out.println((new StringBuilder("\u3010SDKHttpClient\u3011Request-Url:")).append(url).toString());
        HttpClientUtil.getInstance();
        String responseString = HttpClientUtil.getInstance().doGetRequest(url);
        responseString = responseString.trim();
        if(responseString != null && !"".equals(responseString))
        {
            List elements = xmlDoc(responseString);
            for(Iterator iterator = elements.iterator(); iterator.hasNext();)
            {
                Element element = (Element)iterator.next();
                if(element != null)
                {
                    logger.debug((new StringBuilder("\u3010SDKHttpClient\u3011\u4E0A\u884C\u8BF7\u6C42->")).append(responseString).toString());
                    Mo mo = new Mo();
                    mo.setMobileNumber(element.elementText("srctermid"));
                    mo.setSmsContent(element.elementText("msgcontent"));
                    mo.setAddSerial(element.elementText("addSerial"));
                    mo.setAddSerialRev(element.elementText("addSerialRev"));
                    mo.setSentTime(element.elementText("sendTime"));
                    _Mos.add(mo);
                }
            }

        }
        return _Mos;
    }

    public static List getReports(String url, String sn, String key)
    {
        List _Reports = new ArrayList();
        if("".equals(url))
            return _Reports;
        String param = (new StringBuilder("cdkey=")).append(sn).append("&password=").append(key).toString();
        url = (new StringBuilder(String.valueOf(url))).append("?").append(param).toString();
        logger.debug((new StringBuilder("\u3010SDKHttpClient\u3011Request-Url:")).append(url).toString());
        String responseString = HttpClientUtil.getInstance().doGetRequest(url);
        responseString = responseString.trim();
        if(responseString != null && !"".equals(responseString))
        {
            List elements = xmlDoc(responseString);
            for(Iterator iterator = elements.iterator(); iterator.hasNext();)
            {
                Element element = (Element)iterator.next();
                if(element != null)
                {
                    logger.debug((new StringBuilder("\u3010SDKHttpClient\u3011REPORT->")).append(element.elementText("seqid")).toString());
                    StatusReport report = new StatusReport();
                    report.setMobile(element.elementText("srctermid"));
                    report.setErrorCode(element.elementText("state"));
                    report.setSeqID(Long.parseLong(element.elementText("seqid")));
                    report.setReceiveDate(element.elementText("receiveDate"));
                    report.setSubmitDate(element.elementText("submitDate"));
                    report.setServiceCodeAdd(element.elementText("addSerialRev"));
                    _Reports.add(report);
                }
            }

        }
        return _Reports;
    }

    public static String sendSMS(String url, String param)
    {
        String ret = "";
        url = (new StringBuilder(String.valueOf(url))).append("?").append(param).toString();
        System.out.println((new StringBuilder("\u3010SDKHttpClient\u3011\u53D1\u9001MT\u5230SDK->")).append(url).toString());
        String responseString = HttpClientUtil.getInstance().doGetRequest(url);
        responseString = responseString.trim();
        if(responseString != null && !"".equals(responseString))
            ret = xmlMt(responseString);
        return ret;
    }

    public static String getBalance(String url, String param)
    {
        String ret = "\u5931\u8D25";
        url = (new StringBuilder(String.valueOf(url))).append("?").append(param).toString();
        String responseString = HttpClientUtil.getInstance().doGetRequest(url);
        responseString = responseString.trim();
        if(responseString != null && !"".equals(responseString))
            ret = xmlResponse(responseString);
        return ret;
    }

    public static String xmlResponse(String response)
    {
        String result = "\u5931\u8D25";
        Document document = null;
        try
        {
            document = DocumentHelper.parseText(response);
        }
        catch(DocumentException e)
        {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        result = root.elementText("message");
        return result;
    }

    private static List xmlDoc(String response)
    {
        boolean result = false;
        Document document = null;
        try
        {
            document = DocumentHelper.parseText(response);
        }
        catch(DocumentException e)
        {
            e.printStackTrace();
            return null;
        }
        Element root = document.getRootElement();
        List list = root.elements();
        List elemets = new ArrayList();
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            Element element = (Element)iterator.next();
            String message = element.getName();
            if("message".equalsIgnoreCase(message) && element.elements().size() > 0)
                elemets.add(element);
        }

        return elemets;
    }

    public static String xmlMt(String response)
    {
        String result = "0";
        Document document = null;
        try
        {
            document = DocumentHelper.parseText(response);
        }
        catch(DocumentException e)
        {
            e.printStackTrace();
            result = "-250";
        }
        Element root = document.getRootElement();
        result = root.elementText("error");
        if(result == null || "".equals(result))
            result = "-250";
        return result;
    }

    public static void main(String args[])
    {
        String url = "http://sdk4report.eucp.b2m.cn:8080/sdkproxy/querybalance.action";
        String param = "cdkey=6SDK-EKF-6687-KHQPL&password=795836";
        String result = getBalance(url, param);
        System.out.println(result);
    }


}
