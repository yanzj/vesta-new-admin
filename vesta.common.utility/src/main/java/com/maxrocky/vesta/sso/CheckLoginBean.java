package com.maxrocky.vesta.sso;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckLoginBean {
    public static final int SCCESSED = 1;
    public static final int CROSS_DOMAIN_REDIRECT = 2;
    public static final int IVALID_COOKIE = 3;
    public static final int ACCESS_DENY = 4;
    public static final int LOGOUT = 5;
    public static final int PASS = 6;
    private Object[] currentDomain = new Object[2];
    private ConfigUtil configUtil = new ConfigUtil();

    public CheckLoginBean() {
    }

    public ConfigUtil getConfigUtil() {
        return this.configUtil;
    }

    public void setConfigUtil(ConfigUtil configUtil) {
        if(configUtil != null) {
            this.configUtil = configUtil;
        }

    }

    protected void init() {
        this.getConfigUtil().loadConfig();
    }

    protected int processLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.init();
        byte returnValue = 3;
        String requestURL = request.getRequestURL().toString();
        CookiesUtil cookiesUtil = new CookiesUtil(request, response);
        int fiterFlag = this.judgeFile(requestURL);
        String queryStr;
        String gotoUrl;
        String action;
        String tempGotoURL;
        if(fiterFlag == 0) {
            queryStr = request.getHeader("Referer");
            gotoUrl = this.getLocalDomainName(requestURL);
            action = gotoUrl.substring(gotoUrl.indexOf(46) + 1);
            int queryArr;
            String tempUrl;
            if(queryStr != null && queryStr.length() > 0 && !queryStr.contains(action)) {
                Map var16;
                Matcher var19;
                Pattern var20;
                for(queryArr = 0; queryArr < SsoClientUtils.DOMAIN_LIST.size(); ++queryArr) {
                    var16 = (Map)SsoClientUtils.DOMAIN_LIST.get(queryArr);
                    var20 = (Pattern)var16.get("regex");
                    var19 = var20.matcher(queryStr);
                    if(var19.find()) {
                        StringBuffer var21 = new StringBuffer("");
                        var21.append(var16.get("crossdomainUrl"));
                        var21.append("?");
                        var21.append("ReturnUrl=");
                        var21.append(URLTool.encodeURL(requestURL));
                        var21.append("&");
                        var21.append("ReturnHost=");
                        var21.append(URLTool.encodeURL(this.getLocalDomainName(requestURL)));
                        response.sendRedirect(var21.toString());
                        response.getWriter().close();
                        return 2;
                    }
                }

                for(queryArr = 0; queryArr < SsoClientUtils.DOMAIN_LIST.size(); ++queryArr) {
                    var16 = (Map)SsoClientUtils.DOMAIN_LIST.get(queryArr);
                    var20 = (Pattern)var16.get("regex");
                    var19 = var20.matcher(requestURL);
                    if(var19.find()) {
                        tempUrl = var16.get("loginUrl").toString();
                        tempUrl = tempUrl + "?reason=enhancedSecurity&RetutnUrl=" + URLTool.encodeURL(requestURL);
                        response.sendRedirect(tempUrl);
                        response.getWriter().close();
                        return 4;
                    }
                }

                response.sendError(403);
                return 4;
            }

            queryArr = 0;

            try {
                queryArr = cookiesUtil.cookiesIsHave();
            } catch (Exception var15) {
                SsoLogger.error(var15);
            }

            String queryItem;
            String url;
            if(queryArr != 0 && queryArr != 2 && queryArr != 4) {
                if(queryArr == 1) {
                    cookiesUtil.setFilterCookies();
                    if(cookiesUtil.getIsNologin() == 0) {
                        request.setAttribute("loginid", cookiesUtil.getHashMap().get("logonUserID"));
                        tempGotoURL = "<script type=\"text/javascript\">var wd_sso_menuInfo=\'" + cookiesUtil.getOriginalTokenSysStr() + "\';" + " var wd_sso_sessionidname=\'" + SsoClientUtils.SESSIONID_NAME + "\'; var wd_sso_passwordExpireDays=" + cookiesUtil.getPwExpiredDays() + ";</script>";
                        request.setAttribute("wd_sso_menuInfo", tempGotoURL);
                    }

                    SsoLogger.info(Calendar.getInstance().getTime() + " User logged in - loginid:" + cookiesUtil.getHashMap().get("logonUserID"));
                    return 1;
                }

                if(queryArr == 3) {
                    tempGotoURL = NumberTools.getClientRandem(256);
                    queryItem = request.getQueryString();
                    if(queryItem != null && !"".equals(queryItem)) {
                        queryItem = "?" + queryItem;
                    } else {
                        queryItem = "";
                    }

                    url = URLTool.encodeURL(requestURL + queryItem);
                    tempUrl = this.getDomain(requestURL).get("loginUrl") + "challengeNumber=" + tempGotoURL + "&flag=3&systemCode=" + SsoClientUtils.SYSTEM_CODE + "&RetutnUrl=" + url;
                    response.sendRedirect(tempUrl);
                    returnValue = 4;
                }
            } else {
                tempGotoURL = NumberTools.getClientRandem(256);
                queryItem = request.getQueryString();
                if(queryItem != null && !"".equals(queryItem)) {
                    queryItem = "?" + queryItem;
                } else {
                    queryItem = "";
                }

                url = URLTool.encodeURL(requestURL + queryItem);
                tempUrl = this.getDomain(requestURL).get("loginUrl") + "?challengeNumber=" + tempGotoURL + "&flag=3&systemCode=" + SsoClientUtils.SYSTEM_CODE + "&RetutnUrl=" + url;
                response.sendRedirect(tempUrl);
                returnValue = 3;
            }
        } else if(fiterFlag == 1) {
            queryStr = request.getQueryString();
            if(request.getSession(false) != null) {
                request.getSession().invalidate();
            }

            if(queryStr != null && queryStr.length() > 0) {
                gotoUrl = null;
                action = null;
                String[] var17 = queryStr.split("&");

                for(int var18 = 0; var18 < var17.length; ++var18) {
                    String[] var22 = var17[var18].split("=");
                    if(var22.length == 2 && "action".equals(var22[0])) {
                        action = var22[1];
                    } else if(var22.length == 2 && "url".equals(var22[0])) {
                        gotoUrl = var22[1];
                    }
                }

                if(action != null && !"exit".equals(action)) {
                    tempGotoURL = gotoUrl != null && gotoUrl.length() != 0?gotoUrl:this.getDomain(requestURL).get("loginUrl").toString();
                    response.sendRedirect(tempGotoURL);
                } else {
                    cookiesUtil.deleteCookies("AuthUser_AuthMAC");
                    cookiesUtil.deleteCookies("AuthUser_AuthNum");
                    cookiesUtil.deleteCookies("AuthUser_AuthToken");
                    tempGotoURL = gotoUrl != null && gotoUrl.length() != 0?gotoUrl:request.getHeader("referer");
                    response.sendRedirect("http://" + this.getDomain(requestURL).get("myHost") + "/wd_sso_logout?action=exitall&url=" + URLTool.encodeURL(tempGotoURL));
                }
            } else {
                cookiesUtil.deleteCookies("AuthUser_AuthMAC");
                cookiesUtil.deleteCookies("AuthUser_AuthNum");
                cookiesUtil.deleteCookies("AuthUser_AuthToken");
                response.sendRedirect(this.getDomain(requestURL).get("loginUrl").toString());
            }

            returnValue = 5;
        } else {
            returnValue = 6;
        }

        return returnValue;
    }

    private int judgeFile(String url) {
        return SsoClientUtils.SSO_FILTER_EXT.matcher(url).matches()?(SsoClientUtils.SSO_FILTER_CONTENTEXT.matcher(url).matches()?1:2):0;
    }

    private Map getDomain(String requestURL) {
        String domain = this.getLocalDomainName(requestURL);
        if(domain.equals(this.currentDomain[0])) {
            return (Map)this.currentDomain[1];
        } else {
            for(int i = 0; i < SsoClientUtils.DOMAIN_LIST.size(); ++i) {
                Map map = (Map)SsoClientUtils.DOMAIN_LIST.get(i);
                Pattern pattern = (Pattern)map.get("regex");
                Matcher matcher = pattern.matcher(domain);
                if(matcher.matches()) {
                    this.currentDomain[0] = domain;
                    this.currentDomain[1] = map;
                    return map;
                }
            }

            return new HashMap(2);
        }
    }

    private String getLocalDomainName(String requestURL) {
        String tempDomain = requestURL;
        int startIndex = requestURL.indexOf("//");
        if(startIndex >= 0) {
            tempDomain = requestURL.substring(startIndex + 2);
        }

        int endIndex = tempDomain.indexOf(47);
        if(endIndex > 0) {
            tempDomain = tempDomain.substring(0, endIndex);
        }

        return tempDomain.split(":|[?]", 2)[0];
    }
}
