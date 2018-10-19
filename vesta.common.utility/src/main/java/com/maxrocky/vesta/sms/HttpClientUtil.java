package com.maxrocky.vesta.sms;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by Magic on 2016/4/26.
 */
public class HttpClientUtil {

    private static class ClientUtilInstance
    {

        private static final HttpClientUtil ClientUtil = new HttpClientUtil(null);



        private ClientUtilInstance()
        {
        }
    }

    private static Logger logger = Logger.getLogger(HttpClientUtil.class);
    private static HttpClient client = null;
    private HttpClientUtil()
    {
        MultiThreadedHttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        params.setConnectionTimeout(5000);
        params.setSoTimeout(10000);
        params.setDefaultMaxConnectionsPerHost(100);
        params.setMaxTotalConnections(256);
        httpConnectionManager.setParams(params);
        client = new HttpClient(httpConnectionManager);
        client.getParams().setConnectionManagerTimeout(3000L);
    }

    public static HttpClientUtil getInstance()
    {
        return ClientUtilInstance.ClientUtil;
    }

    public String doGetRequest(String urlstr)
    {
        String response;
        HttpMethod httpmethod;
        response = "";
        httpmethod = new GetMethod(urlstr);
        int statusCode = 0;
        try {
            statusCode = client.executeMethod(httpmethod);
        } catch (IOException e) {

            httpmethod.releaseConnection();
            e.printStackTrace();
        }
        InputStream _InputStream = null;
        if(statusCode == 200)
            try {
                _InputStream = httpmethod.getResponseBodyAsStream();
            } catch (IOException e) {

                httpmethod.releaseConnection();
                e.printStackTrace();
            }
        if(_InputStream != null)
            response = GetResponseString(_InputStream, "UTF-8");

        return response;
    }

    public String GetResponseString(InputStream _InputStream, String Charset)
    {
        String response = "";
        try
        {
            if(_InputStream != null)
            {
                StringBuffer buffer = new StringBuffer();
                InputStreamReader isr = new InputStreamReader(_InputStream, Charset);
                Reader in = new BufferedReader(isr);
                int ch;
                while((ch = in.read()) > -1)
                    buffer.append((char)ch);
                response = buffer.toString();
                buffer = null;
            }
        }
        catch(Exception e)
        {
            logger.error((new StringBuilder("\u83B7\u53D6\u54CD\u5E94\u9519\u8BEF\uFF0C\u539F\u56E0\uFF1A")).append(e.getMessage()).toString());
            response = (new StringBuilder(String.valueOf(response))).append(e.getMessage()).toString();
            e.printStackTrace();
        }
        return response;
    }

    public static void main(String args[])
    {
        String url = "http://esms.etonenet.com/sms/mt?spid=3060&sppassword=hbkj3060&das=8618611178949&command=MULTI_MT_REQUEST&sm=a1beccd4b1a6a1bf20cda8b5c0bdd3c8ebcdeab3c9a3a1&dc=15";
    }

    HttpClientUtil(HttpClientUtil httpclientutil)
    {
        this();
    }


}
