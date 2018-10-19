package com.maxrocky.vesta.sso;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ConfigUtil {
    private static long allowAccessTime = 0L;

    public ConfigUtil() {
    }

    protected void loadConfig() {
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

            File file = new File(SsoClientUtils.CONTENT_PAHT + SsoClientUtils.CONFIG_LOCATION);
            if(!file.exists()) {
                throw new RuntimeException("File do not exist - File Name:" + SsoClientUtils.CONFIG_LOCATION);
            } else {
                long lasttime = file.lastModified();
                if(lasttime > SsoClientUtils.SETTING_LAST_UPDATETIME) {
                    this.parseFile(file);
                    SsoClientUtils.SETTING_LAST_UPDATETIME = lasttime;
                }

            }
        }
    }

    protected void parseFile(File file) {
        try {
            DocumentBuilder e = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            this.parseDocument(e.parse(file));
        } catch (SAXException var3) {
            SsoLogger.error(var3);
        } catch (IOException var4) {
            SsoLogger.error(var4);
        } catch (ParserConfigurationException var5) {
            SsoLogger.error(var5);
        }

    }

    protected void parseDocument(Document doc) {
        Element root = doc.getDocumentElement();
        NodeList connectionString = root.getElementsByTagName("ConnectionString");
        if(connectionString != null && connectionString.getLength() > 0) {
            this.parseConnectionString((Element)connectionString.item(0));
        }

        NodeList adConfiguration = root.getElementsByTagName("ActiveDirectoryConfiguration");
        if(adConfiguration != null && adConfiguration.getLength() > 0) {
            this.parseADConfiguration((Element)adConfiguration.item(0));
        }

        NodeList currentSite = root.getElementsByTagName("CurrentSite");
        if(currentSite != null && currentSite.getLength() > 0) {
            this.parseCurrentSite((Element)currentSite.item(0));
        }

        NodeList cookieDomains = root.getElementsByTagName("CookieDomains");
        if(cookieDomains != null && cookieDomains.getLength() > 0) {
            this.parseCookieDomains((Element)cookieDomains.item(0));
        }

        NodeList urls = root.getElementsByTagName("Urls");
        if(urls != null && urls.getLength() > 0) {
            this.parseUrls((Element)urls.item(0));
        }

        NodeList userConfiguration = root.getElementsByTagName("UserConfiguration");
        if(userConfiguration != null && userConfiguration.getLength() > 0) {
            this.parseUserConfiguration((Element)userConfiguration.item(0));
        }

        NodeList includeFileExtension = root.getElementsByTagName("IncludeFileExtension");
        if(includeFileExtension != null && includeFileExtension.getLength() > 0) {
            this.parseIncludeFileExtension((Element)includeFileExtension.item(0));
        }

        try {
            SsoClientUtils.CONNECTION_USER = DES3Tools.getECBDecodeStr(SsoClientUtils.CONNECTION_USER);
            SsoClientUtils.CONNECTION_PASSWORD = DES3Tools.getECBDecodeStr(SsoClientUtils.CONNECTION_PASSWORD);
            SsoClientUtils.AD_ADMIN_USERID = DES3Tools.getECBDecodeStr(SsoClientUtils.AD_ADMIN_USERID);
            SsoClientUtils.AD_ADMIN_USERPW = DES3Tools.getECBDecodeStr(SsoClientUtils.AD_ADMIN_USERPW);
        } catch (Exception var11) {
            SsoLogger.error(var11);
        }

    }

    private void parseConnectionString(Element element) {
        SsoClientUtils.CONNECTION_DATASOURCE = element.getAttributeNode("dataSource").getValue();
        SsoClientUtils.CONNECTION_PROVIDER_NAME = element.getAttributeNode("providerName").getValue();
        SsoClientUtils.CONNECTION_USER = element.getAttributeNode("userID").getValue();
        SsoClientUtils.CONNECTION_PASSWORD = element.getAttributeNode("password").getValue();
        SsoClientUtils.CONNECTION_DBNAME = element.getAttributeNode("dbName").getValue();
    }

    private void parseADConfiguration(Element element) {
        SsoClientUtils.AD_SERVER_IP = element.getAttributeNode("server").getValue();
        Attr port = element.getAttributeNode("port");
        if(port != null) {
            SsoClientUtils.AD_SERVER_PORT = port.getValue();
        }

        SsoClientUtils.AD_SERVER_DOMAIN = "@" + element.getAttributeNode("domain").getValue().replace("@", "");
        SsoClientUtils.AD_DIRECTORYPATH = element.getAttributeNode("directoryPath").getValue();
        SsoClientUtils.AD_ADMIN_USERID = element.getAttributeNode("userName").getValue();
        SsoClientUtils.AD_ADMIN_USERPW = element.getAttributeNode("password").getValue();
        SsoClientUtils.AD_PD_WARN_EXPIREDAYS = element.getAttributeNode("passwordWarnExpireDays").getValue();
    }

    private void parseCurrentSite(Element element) {
        SsoClientUtils.SYSTEM_CODE = element.getAttributeNode("systemCode").getValue();
        SsoClientUtils.KEY = element.getAttributeNode("key").getValue();
        SsoClientUtils.DATETIMEFORMAT = element.getAttributeNode("dateTimeFormat").getValue();
        SsoClientUtils.SESSIONID_NAME = element.getAttributeNode("sessionIdName").getValue();
    }

    private void parseCookieDomains(Element element) {
        Attr cookiePath = element.getAttributeNode("path");
        if(cookiePath != null) {
            SsoClientUtils.COOKIES_PATH = cookiePath.getValue();
        }

        NodeList domainList = element.getElementsByTagName("domain");

        for(int i = 0; i < domainList.getLength(); ++i) {
            HashMap map = new HashMap();
            Element domainElement = (Element)domainList.item(i);
            map.put("name", domainElement.getAttributeNode("name").getValue());
            map.put("regex", Pattern.compile(domainElement.getAttributeNode("regex").getValue()));
            map.put("myHost", domainElement.getAttributeNode("myHost").getValue());
            map.put("loginUrl", domainElement.getAttributeNode("loginUrl").getValue());
            map.put("crossdomainUrl", domainElement.getAttributeNode("crossdomainUrl").getValue());
            map.put("defaultSite", domainElement.getAttributeNode("defaultSite").getValue());
            SsoClientUtils.DOMAIN_LIST.add(map);
        }

    }

    private void parseUrls(Element element) {
        SsoClientUtils.LOGON_FLAG = element.getAttributeNode("logonFlag").getValue();
        SsoClientUtils.ERROR_URL = element.getAttributeNode("errorUrl").getValue();
        SsoClientUtils.CHANGE_PASSWORD_URL = element.getAttributeNode("changePasswordUrl").getValue();
        SsoClientUtils.DEFAULT_URL = element.getAttributeNode("defaulteurl").getValue();
    }

    private void parseUserConfiguration(Element element) {
        Attr mode = element.getAttributeNode("value");
        if(mode != null) {
            SsoClientUtils.AD_OR_DB_MODE = mode.getValue();
        }

    }

    private void parseIncludeFileExtension(Element element) {
        SsoClientUtils.SSO_FILTER_EXT = Pattern.compile(element.getAttributeNode("ext_value").getValue());
        SsoClientUtils.SSO_FILTER_CONTENTEXT = Pattern.compile(element.getAttributeNode("content_value").getValue());
    }
}