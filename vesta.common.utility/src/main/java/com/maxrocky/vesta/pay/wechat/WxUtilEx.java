package com.maxrocky.vesta.pay.wechat;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.*;

/**
 * Created by Tom on 2016/2/15 14:36.
 * Describe:微信支付辅助类
 */
public class WxUtilEx {

    public static final String CHARSET = "UTF-8";

    private static final String POST_URL_UNIFIEDORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    private static final String POST_URL_ORDERQUERY = "https://api.mch.weixin.qq.com/pay/orderquery";


    public static void formatMapObject(Map map) {

        Set es = map.entrySet();
        Iterator it = es.iterator();
        List<String> remove_key_list = new ArrayList<String>();

        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();

            if (null == v || v.length() < 1) {
                remove_key_list.add(k);
            }
        }

        for(String key : remove_key_list){
            map.remove(key);
        }

    }


    public static String map2QueryString(Map map) {

        StringBuffer sb = new StringBuffer();

        SortedMap<String, String> temp_map = new TreeMap<String, String>(map);
        temp_map.remove("key");
        temp_map.remove("sign");

        Set es = temp_map.entrySet();
        Iterator it = es.iterator();

        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();

            if (null == v || v.length() < 1) {
                continue;
            }

            if (sb.length() > 0) {
                sb.append("&");
            }

            sb.append(k);
            sb.append("=");
            sb.append(v);
        }

        return sb.toString();
    }



    public static String makeSignWithMd5(String data, String key) {
        data = data + "&key=" + key;
        String sign = WxUtilEx.MD5Encode(data, CHARSET).toUpperCase();

        System.out.println("\n>>>>>>>>>> raw String \n" + data);

        return sign;
    }

    public static String makeSignWithMd5(Map map, String key) {

        String data = map2QueryString(map);
        String sign = makeSignWithMd5(data, key);

        return sign;
    }

    public static void signMapWithMd5(Map map, String key) {

        formatMapObject(map);
        String sign = makeSignWithMd5(map, key);

        map.remove("key");//防止key被存储
        map.put("sign", sign);
    }

    public static boolean compareSign(String signA, String signB) {
        if (signA != null && signB != null && signA.equals(signB)) {
            return true;
        }
        return false;
        //return true;
    }

    public static boolean checkMapSign(Map map, String key) {

        String old_sign = (String) map.get("sign");
        String new_sign = makeSignWithMd5(map, key);

        return compareSign(new_sign, old_sign);
    }

    public static Map postUnifiedOrder(Map map) throws Exception {
        return postXmlData(POST_URL_UNIFIEDORDER, map);
    }

    public static Map postOrderQuery(Map map) throws Exception {
        return postXmlData(POST_URL_ORDERQUERY, map);
    }

    public static Map postXmlData(String url, Map map) throws Exception {

        String post_data = map2XmlString(map);

        String return_data = sendUrlRequest(url, post_data, "POST");

        return xmlString2Map(return_data);
    }

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

    public static String map2XmlString(Map map) throws Exception {

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
        XMLOutputter xmlout = new XMLOutputter(format);
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        xmlout.output(document, bo);

        return bo.toString(CHARSET);
    }

    public static Map xmlString2Map(String xml) throws Exception {
        Map map = new HashMap();

        if (null == xml || xml.trim().length() < 1) {
            return map;
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(xml.getBytes(CHARSET));
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(bais);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();

        Element e = null;
        String k = null;
        String v = null;

        while (it.hasNext()) {
            e = (Element) it.next();
            k = e.getName();
            v = e.getTextNormalize();

            map.put(k, v);
        }

        return map;
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


    public static void main(String[] args) throws Exception {

        Map map = new HashMap();
        map.put("aaa", "你支付测试大功告成");
        map.put("bbb", "bbbbbbbbbbbbbbbbb");
        map.put("ccc", "");

		/*
		try {
			String xml = map2XmlString(map);
			System.out.println("1:" + xml);

			Map p = xmlString2Map(xml);
			System.out.println("2:" + map2QueryString(p));

			xml = "<xml><a>你支付测试大功告成 </a></xml>";
			System.out.println("3:" + map2QueryString(xmlString2Map(xml)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		*/

        String s = "ddddd=dddd&notify_url=htpp://wekskk.wsllsd&zaaa=dddddd&key=AAAAAAAAAAAAAAAAAAAA";
        System.out.println(WxUtilEx.MD5Encode(s, CHARSET).toUpperCase());

        //System.out.println( DataUtil.randomUUID()  );


    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public static String toWeixinFee(String fee) {
        double t_fee = toDouble(fee);
        return String.valueOf((int) (t_fee * 100));
    }

    public static double toDouble(String value) {
        double d = 0;
        try {
            d = Double.parseDouble(value);
        } catch (Exception ex) {
        }
        return d;
    }

}
