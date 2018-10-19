package com.maxrocky.vesta.filter.Setting;

import com.maxrocky.vesta.utility.AppConfig;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Tom on 2016/3/21 16:36.
 * Describe:
 */
public class AppConfigSettingBean {
    private static long allowAccessTime = 0L;

    public AppConfigSettingBean(){}

    protected void loadConfig(){
        Calendar cal = Calendar.getInstance();
        long currentTime = cal.getTimeInMillis();
        if(currentTime >= allowAccessTime) {
            synchronized(this) {
                if(currentTime < allowAccessTime) {
                    return;
                }

                cal.add(12, 5);
                allowAccessTime = cal.getTimeInMillis();
            }

            File file = new File(AppConfig.CONTENT_PATH + AppConfig.CONFIG_LOCATION);
            if(!file.exists()) {
                System.out.println("File do not exist - File Name:" + AppConfig.CONFIG_LOCATION);
                throw new RuntimeException("File do not exist - File Name:" + AppConfig.CONFIG_LOCATION);
            } else {
                long lastTime = file.lastModified();
                if(lastTime > AppConfig.SETTING_LAST_MODIFY_TIME) {
                    this.parseFile(file);
                    AppConfig.SETTING_LAST_MODIFY_TIME = lastTime;
                }

            }
        }
    }

    protected void parseFile(File file) {
        try {
            DocumentBuilder e = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            this.parseDocument(e.parse(file));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        /*   读取AppConfigSetting中所有模块*/
    protected void parseDocument(Document doc) {
        Element root = doc.getDocumentElement();
        NodeList basicString = root.getElementsByTagName("BasicString");
        if (basicString != null && basicString.getLength() > 0) {
            this.parseBasicString((Element) basicString.item(0));
        }

        NodeList domainString = root.getElementsByTagName("DomainString");
        if (domainString != null && domainString.getLength() > 0) {
            this.parseDomainString((Element) domainString.item(0));
        }
    }

    protected String getElementValueByNodeName(Element element, String nodeName) {
        Attr attr = element.getAttributeNode(nodeName);
        if(null == attr) {
            System.out.println("The node is null----->>" + nodeName);
            return "";
        } else {
            return attr.getValue();
        }
    }

    /*以下是读取每个模块中所有参数*/
    private void parseBasicString(Element element) {
        AppConfig.TOKEN_CHECK_FLAG = element.getAttributeNode("tokenCheckFlag").getValue();
        System.out.println("---------------------------------------AppConfig.TOKEN_CHECK_FLAG:"+AppConfig.TOKEN_CHECK_FLAG);
        AppConfig.USER_ID_TEST = element.getAttributeNode("userIDTest").getValue();
        System.out.println("---------------------------------------AppConfig.USER_ID_TEST:"+AppConfig.USER_ID_TEST);
        AppConfig.STAFF_ID_TEST = element.getAttributeNode("staffIDTest").getValue();
        System.out.println("---------------------------------------AppConfig.STAFF_ID_TEST:"+AppConfig.STAFF_ID_TEST);

//        微信參數
//        AppConfig.AppID = element.getAttributeNode("appid").getValue();
//
//        System.out.println("---------------------------------------AppConfig.AppID:"+AppConfig.AppID);
//        AppConfig.AppSecret = element.getAttributeNode("appsecrt").getValue();
//
//        System.out.println("---------------------------------------AppConfig.AppSecret:"+AppConfig.AppSecret);
    }

    private void parseDomainString(Element element) {
        AppConfig.Cookie_Domain = element.getAttributeNode("cookieDomain").getValue();
        System.out.println("---------------------------------------AppConfig.Cookie_Domain:"+AppConfig.Cookie_Domain);
        AppConfig.Staff_Cookie_Domain = element.getAttributeNode("staffCookieDomain").getValue();
        System.out.println("---------------------------------------AppConfig.Staff_Cookie_Domain:"+AppConfig.Staff_Cookie_Domain);

    }


}
