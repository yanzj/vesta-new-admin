package com.maxrocky.vesta.sso;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

class SsoClientUtils {
    public static final String CONNECTION_STRING = "jdbc:sqlserver://";
    public static String CONNECTION_DATASOURCE = "";
    public static String CONNECTION_PROVIDER_NAME = "";
    public static String CONNECTION_USER = "";
    public static String CONNECTION_PASSWORD = "";
    public static String CONNECTION_DBNAME = "";
    public static String AD_SERVER_IP = "";
    public static String AD_SERVER_PORT = "389";
    public static String AD_SERVER_DOMAIN = "";
    public static String AD_DIRECTORYPATH = "";
    public static String AD_ADMIN_USERID = "";
    public static String AD_ADMIN_USERPW = "";
    public static String AD_PD_WARN_EXPIREDAYS = "";
    public static String LOGON_FLAG = "0";
    public static String ERROR_URL = "";
    public static String CHANGE_PASSWORD_URL = "";
    public static String DEFAULT_URL = "";
    public static String SYSTEM_CODE = "";
    public static String KEY = "";
    public static String DATETIMEFORMAT = "";
    public static String SESSIONID_NAME = "";
    public static String COOKIES_PATH = "/";
    public static List DOMAIN_LIST = new ArrayList();
    public static Pattern SSO_FILTER_EXT = null;
    public static Pattern SSO_FILTER_CONTENTEXT = null;
    public static final String SSO_VERIFYIMAGE_SESSION = "sso_rands";
    public static String CONTENT_PAHT = "";
    public static String CONFIG_LOCATION = "/WEB-INF/Setting.xml";
    public static long SETTING_LAST_UPDATETIME = 0L;
    public static String AD_OR_DB_MODE = "AD";
    public static final String CONST_USERPASSWORD_KEY = "Wanda2012";
    public static int ACCOUNT_POLICY = 180;

    SsoClientUtils() {
    }
}
