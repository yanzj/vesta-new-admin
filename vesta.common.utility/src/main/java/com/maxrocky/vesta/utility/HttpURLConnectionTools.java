package com.maxrocky.vesta.utility;

import com.maxrocky.vesta.Json.JsonUtil;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Tom on 2016/2/20 11:36.
 * Describe:
 */
public class HttpURLConnectionTools {

    public static final String CHARSET = "UTF-8";

    /**
     * Post请求Url，map参数
     */
    public static String postXMLMap(String url, Map map) throws Exception {
        return sendUrlRequest(url, mapToXmlString(map), "POST");
    }

    /**
     * Post请求Url，map参数
     */
    public static String postXML(String url, String params) throws Exception {
        return sendUrlRequest(url, params, "POST");
    }

    /**
     * 发送HttpPost请求
     * @param strURL 服务地址
     * @param mapParam map
     * @return 成功:返回json字符串<br/>
     */
    public static String postJsonMap(String strURL, Map mapParam) {
        return postJson(strURL, JsonUtil.toJson(mapParam));
    }

    /**
     * 请求
     */
    public static String sendUrlRequest(String urlStr,String params,String method){
        String tempStr = "";
        HttpURLConnection connection=null;
        System.out.println(method);
        try{

            URL url=new URL(urlStr);
            //创建URL连接，提交到数据，获取返回结果
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.getOutputStream().write(params.getBytes("utf8"));
            System.out.print(params);
            tempStr = new String(readInputStream(connection.getInputStream()));
            System.out.println(" url 返回："+tempStr);
        }
        catch(Exception e)
        {
            System.out.println(" 错误："+tempStr);
            e.printStackTrace();
        }finally{
            if(connection!=null)
                connection.disconnect();
        }
        return tempStr;
    }

    /**
     * 发送HttpPost请求
     * @param strURL 服务地址
     * @param params json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/>
     * @return 成功:返回json字符串<br/>
     */
    public static String postJson(String strURL, String params) {
        System.out.println(strURL);
        System.out.println(params);
        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream(), CHARSET); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            int length = (int) connection.getContentLength();// 获取长度
            InputStream is = connection.getInputStream();
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                String result = new String(data, CHARSET); // utf-8编码
                System.out.println(result);
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error"; // 自定义错误信息
    }

    /**
     * Map请求参数转XML字符串
     */
    public static String mapToXmlString(Map map) throws Exception {

        Element root = new Element("xml");
        Document document = new Document(root);
        Set es = map.entrySet();
        Iterator it = es.iterator();
        Map.Entry entry = null;
        String k = null;
        String v = null;
        Element elements = null;

        while (it.hasNext()) {
            entry = (Map.Entry) it.next();

            k = (String) entry.getKey();
            v = (String) entry.getValue();

            elements = new Element(k);
            elements.setText(v);

            root.addContent(elements);
        }

        Format format = Format.getPrettyFormat();
        format.setEncoding(CHARSET);//设置xml文件的字符为UTF-8，解决中文问题
        XMLOutputter xmlOutputter = new XMLOutputter(format);
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        xmlOutputter.output(document, bo);

        return bo.toString(CHARSET);
    }

    /**
     * 从输入流中读取数据
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len = inStream.read(buffer)) !=-1 ){
            outStream.write(buffer, 0, len);
        }

        byte[] data = outStream.toString(CHARSET).getBytes();//网页的二进制数据
        outStream.close();
        inStream.close();
        return data;
    }

}
