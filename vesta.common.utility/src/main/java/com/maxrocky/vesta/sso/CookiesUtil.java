package com.maxrocky.vesta.sso;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Tom on 2016/2/25 14:14.
 * Describe:
 */
public class CookiesUtil {
    public static final String COOKIES_LOGONUSERID = "logonUserID";
    public static final String COOKIES_LOGONTIME = "logonTime";
    public static final String COOKIES_LASTACCESSTIME = "lastAccessTime";
    public static final String COOKIES_SYSTEMCODES = "systemCodes";
    public static final String COOKIES_EXPIRETIME = "expireTime";
    public static final String COOKIES_PWEXPIREDAYS = "passwordExpireDays";
    public static final String COOKIES_CLIENT_TIME_INTERVAL = "clientTimeInterval";
    private String originalToken = "";
    private String originalTokenSysStr = "";
    private byte[] originalTokenByte;
    public static final String ALL_COOKIES = "AuthUser_AuthMAC";
    public static final String KEY_ONE = "AuthUser_AuthNum";
    public static final String KEY_TWO = "AuthUser_AuthToken";
    public static final String USERID_NAME_REM = "wd_sso_user";
    private String all_cookies_value = null;
    private String key_one_value = null;
    private String key_two_value = null;
    private HashMap hashMap = new HashMap();
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String domainName = "";
    private static String[] currentDomain = { "", "" };
    private int isNologin = 0;
    public static final byte[] _key = { -12, 50, 106, 6, -104, 13, 80, -36,
            -92, -39, 64, -70, 72, -17, -91, 123,
            91, -60, 1, -124, 87, -2, 110, 55 };
    public static final byte[] _iv = { 71, 50, 106, -81, -60, 77, -99, -13 };
    public static final String _defualtUserInfo = ":False:";

    public CookiesUtil(HttpServletRequest request, HttpServletResponse response)
    {
        this.request = request;
        this.response = response;

        String requestURL = request.getRequestURL().toString();
        int startIndex = requestURL.indexOf("//");
        if (startIndex >= 0) {
            requestURL = requestURL.substring(startIndex + 2);
        }
        int endIndex = requestURL.indexOf('/');
        if (endIndex > 0) {
            requestURL = requestURL.substring(0, endIndex);
        }
        String fullDomainName = requestURL.split(":|[?]", 2)[0];
        if (!fullDomainName.equals(currentDomain[0])) {
            for (int i = 0; i < SsoClientUtils.DOMAIN_LIST.size(); i++)
            {
                Map map = (Map)SsoClientUtils.DOMAIN_LIST.get(i);
                Pattern pattern = (Pattern)map.get("regex");

                Matcher matcher = pattern.matcher(fullDomainName);
                if (matcher.matches())
                {
                    currentDomain[0] = fullDomainName;
                    currentDomain[1] = ((String)map.get("name"));
                    break;
                }
            }
        }
        this.domainName = currentDomain[1];
    }

    public int cookiesIsHave()
            throws Exception
    {
        int flag = 0;
        String cookieStr = this.request.getHeader("cookie");
        if ((cookieStr != null) && (!"".equals(cookieStr)))
        {
            if (cookieStr.indexOf(" ") > -1) {
                cookieStr = cookieStr.replace(" ", "");
            }
            String[] cookieAyy = cookieStr.split(";");

            String name = null;
            String value = null;
            for (int i = 0; i < cookieAyy.length; i++)
            {
                int equelLocal = cookieAyy[i].indexOf("=");
                name = cookieAyy[i].substring(0, equelLocal);
                value = cookieAyy[i].substring(equelLocal + 1, cookieAyy[i].length());
                if (cookieStr.indexOf("\"") > -1) {
                    value = value.replace("\"", "");
                }
                if (name.equals("AuthUser_AuthMAC")) {
                    this.all_cookies_value = value;
                }
                if (name.equals("AuthUser_AuthNum")) {
                    this.key_one_value = value;
                }
                if (name.equals("AuthUser_AuthToken")) {
                    this.key_two_value = value;
                }
            }
            if ((this.all_cookies_value != null) && (this.key_one_value != null) && (this.key_two_value != null))
            {
                byte[] key2_byte_array = SsoClientUtils.KEY.getBytes("UTF-8");

                byte[] key1_byte_array = Base64Tools.getFromBASE64(this.key_one_value);

                byte[] keytwo_byte_array = Base64Tools.getFromBASE64(this.key_two_value);
                byte[] last_array = new byte[keytwo_byte_array.length + key2_byte_array.length];
                System.arraycopy(keytwo_byte_array, 0, last_array, 0, keytwo_byte_array.length);
                System.arraycopy(key2_byte_array, 0, last_array, keytwo_byte_array.length, key2_byte_array.length);

                String temp_all_cookies = StringEncryptTools.Encrypt(last_array, null);
                if (temp_all_cookies.equals(this.all_cookies_value))
                {
                    DES3Tools des = new DES3Tools();

                    byte[] tear_array = des.tearDownArray(keytwo_byte_array, key1_byte_array, 1);

                    des.unMixedAlgorithm(tear_array);
                    this.originalTokenByte = des.des3DecodeCBC();
                    this.originalToken = new String(this.originalTokenByte, "utf-8");
                    this.originalToken = URLTool.decodeURL(this.originalToken);

                    String[] tempString = this.originalToken.split("&");
                    String tempName = "";
                    String tempValue = "";
                    int dengPosition = 0;
                    for (int i = 0; i < tempString.length; i++)
                    {
                        dengPosition = tempString[i].indexOf("=");
                        tempName = tempString[i].substring(0, dengPosition);
                        tempValue = tempString[i].substring(dengPosition + 1, tempString[i].length());
                        this.hashMap.put(tempName, tempValue);
                    }
                    this.originalTokenSysStr = String.valueOf(this.hashMap.get("systemCodes"));

                    String[] tempTokenArray = this.originalTokenSysStr.split(";");
                    if ((tempTokenArray != null) && (tempTokenArray.length == 1) && ("00:00:00".equals(this.hashMap.get("expireTime")))) {
                        this.isNologin = 1;
                    }
                    int isHaveFlag = 0;
                    String[] everyToken = new String[5];
                    this.originalTokenSysStr = "";
                    for (int j = 0; j < tempTokenArray.length; j++)
                    {
                        everyToken = tempTokenArray[j].split(",");
                        if ((everyToken != null) && (everyToken.length > 0)) {
                            if (SsoClientUtils.SYSTEM_CODE.equals(everyToken[0])) {
                                isHaveFlag = 1;
                            } else {
                                this.originalTokenSysStr = (this.originalTokenSysStr + tempTokenArray[j] + ";");
                            }
                        }
                    }
                    if ((this.originalTokenSysStr.length() > 0) &&
                            (this.originalTokenSysStr.lastIndexOf(";") == this.originalTokenSysStr.length() - 1)) {
                        this.originalTokenSysStr = this.originalTokenSysStr.substring(0, this.originalTokenSysStr.length() - 1);
                    }
                    if (isHaveFlag == 0)
                    {
                        flag = 3;
                        return flag;
                    }
                }
                else
                {
                    flag = 2;
                    return flag;
                }
                flag = 1;
            }
        }
        else
        {
            flag = 0;
        }
        return flag;
    }

    public void setCookies(int isSetCookies)
            throws Exception
    {
        byte[] cr1 = Base64Tools.getFromBASE64(this.key_one_value);
        byte[] sr1 = NumberTools.Rands(24).getBytes();

        Iterator iter = this.hashMap.entrySet().iterator();
        while (iter.hasNext())
        {
            Map.Entry entry = (Map.Entry)iter.next();
            this.originalToken = (this.originalToken + entry.getKey() + "=" + entry.getValue());
            if (iter.hasNext()) {
                this.originalToken += "&";
            }
        }
        this.originalTokenByte = this.originalToken.getBytes("UTF-8");
        DES3Tools des = new DES3Tools();

        des.getKeyAndIv(cr1, sr1);

        des.des3EncodeCBC(this.originalTokenByte);

        byte[] mixed_byte = des.MixedAlgorithm();

        byte[] tear_array = des.tearDownArray(mixed_byte, cr1, 0);
        byte[] key2_array = SsoClientUtils.KEY.getBytes("UTF-8");
        byte[] last_array = new byte[tear_array.length + key2_array.length];
        System.arraycopy(tear_array, 0, last_array, 0, tear_array.length);
        System.arraycopy(key2_array, 0, last_array, tear_array.length, key2_array.length);
        this.all_cookies_value = StringEncryptTools.Encrypt(last_array, null);
        this.key_two_value = Base64Tools.getBASE64(tear_array);
        this.key_two_value = this.key_two_value.replaceAll("\r", "");
        this.key_two_value = this.key_two_value.replaceAll("\n", "");
        if (isSetCookies == 1) {
            setFilterCookies();
        }
    }

    public void setFilterCookies()
    {
        int expireTime = DateTools.getCookiesExpireTime((String)this.hashMap.get("expireTime"));
        if ((this.hashMap.get("clientTimeInterval") != null) && (!"".equals((String)this.hashMap.get("clientTimeInterval"))))
        {
            long csTime = Long.parseLong((String)this.hashMap.get("clientTimeInterval")) / 10000000L;
            expireTime -= (int)csTime;
            if (expireTime == 0) {
                expireTime = -1;
            }
        }
        set("AuthUser_LoginId", (String)getHashMap().get("logonUserID"), expireTime);
        set("AuthUser_AuthMAC", this.all_cookies_value, expireTime);
        set("AuthUser_AuthNum", this.key_one_value, expireTime);
        set("AuthUser_AuthToken", this.key_two_value, expireTime);
    }

    public void userIdPwdRemCookies(String userName, String pwd, String rememberPassword)
            throws Exception
    {
        StringBuffer str = new StringBuffer("");
        if ((rememberPassword != null) && ("1".equals(rememberPassword)))
        {
            str.append(userName);
            str.append(":");
            str.append("True");
            str.append(":");
            str.append(pwd);
        }
        else
        {
            str.append(userName);
            str.append(":False:");
        }
        DES3Tools des3 = new DES3Tools();
        des3.setKey(_key);
        des3.setKeyiv(_iv);
        des3.des3EncodeCBC(str.toString().getBytes("utf-8"));
        set("wd_sso_user", Base64Tools.getBASE64(des3.getDes3r()), 1209600);
    }

    public void set(String name, String value, int seconds_age)
    {
        if (this.response == null) {
            throw new IllegalStateException("Servlet response not available");
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(seconds_age);
        cookie.setDomain(this.domainName);
        cookie.setPath(SsoClientUtils.COOKIES_PATH);
        this.response.addCookie(cookie);
    }

    public void deleteCookies(String key)
    {
        Cookie cookie = new Cookie(key, null);
        cookie.setMaxAge(0);
        cookie.setDomain(this.domainName);
        cookie.setPath(SsoClientUtils.COOKIES_PATH);
        this.response.addCookie(cookie);
    }

    public void deleteAllCookies()
    {
        Cookie[] cookies = this.request.getCookies();
        for (int i = 0; i < cookies.length; i++)
        {
            Cookie cookie = new Cookie(cookies[i].getName(), null);
            cookie.setMaxAge(0);
            cookie.setDomain(this.domainName);
            cookie.setPath(SsoClientUtils.COOKIES_PATH);
            this.response.addCookie(cookie);
        }
    }

    public int getPwExpiredDays()
    {
        int days = Integer.valueOf((String)getHashMap().get("passwordExpireDays")).intValue();
        if (Integer.parseInt(SsoClientUtils.AD_PD_WARN_EXPIREDAYS) < days) {
            days = -1;
        }
        return days;
    }

    public HashMap getHashMap()
    {
        return this.hashMap;
    }

    public void setHashMap(HashMap hashMap)
    {
        this.hashMap = hashMap;
    }

    public String getOriginalTokenSysStr()
    {
        return this.originalTokenSysStr;
    }

    public void setOriginalTokenSysStr(String originalTokenSysStr)
    {
        this.originalTokenSysStr = originalTokenSysStr;
    }

    public int getIsNologin()
    {
        return this.isNologin;
    }

    public void setIsNologin(int isNologin)
    {
        this.isNologin = isNologin;
    }

    public String getAll_cookies_value()
    {
        return this.all_cookies_value;
    }

    public void setAll_cookies_value(String all_cookies_value)
    {
        this.all_cookies_value = all_cookies_value;
    }

    public String getKey_one_value()
    {
        return this.key_one_value;
    }

    public void setKey_one_value(String key_one_value)
    {
        this.key_one_value = key_one_value;
    }

    public String getKey_two_value()
    {
        return this.key_two_value;
    }

    public void setKey_two_value(String key_two_value)
    {
        this.key_two_value = key_two_value;
    }
}
