package com.maxrocky.vesta.sso;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SsoLoginServlet extends HttpServlet {
    public SsoLoginServlet() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CookiesUtil e = new CookiesUtil(request, response);
            String userName = request.getParameter("txtUserName");
            String passWord = request.getParameter("txtPassword");
            String check = request.getParameter("txtVerificationCode");
            String requesturl = request.getParameter("requesturl");
            String challengeNumber = request.getParameter("challengeNumber");
            String rememberPassword = request.getParameter("rememberPassword");
            String clientTime = request.getParameter("clientTime");
            String rands = (String)request.getSession().getAttribute("sso_rands");
            rands = rands.toUpperCase();
            check = check.toUpperCase();
            if(!rands.equals(check)) {
                request.setAttribute("returnUrl", requesturl);
                request.setAttribute("challengeNumber", challengeNumber);
                request.setAttribute("flag", "1");
                request.getRequestDispatcher(this.getLoginUrl(request)).forward(request, response);
                return;
            }

            if(!this.userIdExist(userName)) {
                request.setAttribute("returnUrl", requesturl);
                request.setAttribute("challengeNumber", challengeNumber);
                request.setAttribute("flag", "8");
                request.getRequestDispatcher(this.getLoginUrl(request)).forward(request, response);
                return;
            }

            boolean flag = false;
            boolean pwdExDays = true;
            int flag1;
            int pwdExDays1;
            ConnnectDbTools conn1;
            if("AD".equals(SsoClientUtils.AD_OR_DB_MODE)) {
                ADTools conn = new ADTools();
                flag1 = conn.getUserAdStatus(userName, passWord);
                pwdExDays1 = conn.getPsExDays();
            } else {
                conn1 = new ConnnectDbTools();
                flag1 = conn1.getUserDbStatus(userName, passWord);
                pwdExDays1 = conn1.getPsExDays();
            }

            if(flag1 <= 2 || flag1 >= 5) {
                String conn2 = SsoClientUtils.CHANGE_PASSWORD_URL + userName;
                request.setAttribute("changepw", conn2);
                request.setAttribute("returnUrl", requesturl);
                request.setAttribute("challengeNumber", challengeNumber);
                request.setAttribute("flag", String.valueOf(flag1));
                request.getRequestDispatcher(this.getLoginUrl(request)).forward(request, response);
                return;
            }

            if(flag1 == 3 || flag1 == 4) {
                conn1 = new ConnnectDbTools();
                String sys_String = conn1.getUserSys(userName);
                if("".equals(sys_String)) {
                    request.setAttribute("returnUrl", requesturl);
                    request.setAttribute("challengeNumber", challengeNumber);
                    request.setAttribute("flag", "7");
                    request.getRequestDispatcher(this.getLoginUrl(request)).forward(request, response);
                    return;
                }

                String date_String = DateTools.formatString(new Date());
                e.getHashMap().put("logonUserID", userName);
                e.getHashMap().put("logonTime", date_String);
                e.getHashMap().put("lastAccessTime", date_String);
                e.getHashMap().put("systemCodes", sys_String);
                ConnnectDbTools bean = new ConnnectDbTools();
                e.getHashMap().put("expireTime", bean.getCookiesExpireTime());
                e.getHashMap().put("passwordExpireDays", Integer.valueOf(pwdExDays1));
                long tempTimelong = 0L;
                if(clientTime != null && !"".equals(clientTime)) {
                    long clientTimelong = Long.parseLong(clientTime);
                    Calendar tempcal = Calendar.getInstance();
                    tempcal.setTimeInMillis(clientTimelong);
                    Calendar cal = Calendar.getInstance();
                    long serverTimelong = cal.getTimeInMillis();
                    tempTimelong = (serverTimelong - clientTimelong) * 10000000L;
                }

                e.getHashMap().put("clientTimeInterval", String.valueOf(tempTimelong));
                if(challengeNumber == null || "".equals(challengeNumber)) {
                    challengeNumber = NumberTools.getClientRandem(256);
                }

                e.setKey_one_value(challengeNumber);
                e.setCookies(1);
                e.userIdPwdRemCookies(userName, passWord, rememberPassword);
                if(requesturl == null || "".equals(requesturl)) {
                    requesturl = SsoClientUtils.DEFAULT_URL;
                }

                this.insertLoginLog(1, userName, request.getRemoteAddr(), e.getKey_one_value(), e.getKey_two_value(), e.getAll_cookies_value());
                response.sendRedirect(requesturl);
            }
        } catch (Exception var26) {
            SsoLogger.error(var26);
            response.sendRedirect(SsoClientUtils.ERROR_URL);
        }

    }

    private boolean userIdExist(String userName) {
        ConnnectDbTools bean = new ConnnectDbTools();
        return bean.isUserExist(userName);
    }

    private String getLoginUrl(HttpServletRequest request) {
        String loginURL = "";

        for(int i = 0; i < SsoClientUtils.DOMAIN_LIST.size(); ++i) {
            Map map = (Map)SsoClientUtils.DOMAIN_LIST.get(i);
            Pattern pattern = (Pattern)map.get("regex");
            Matcher matcher = pattern.matcher(this.getLocalDomainName(request));
            if(matcher.matches()) {
                loginURL = (String)map.get("loginUrl");
                break;
            }
        }

        return loginURL;
    }

    private String getLocalDomainName(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        int startIndex = requestURL.indexOf("//");
        if(startIndex >= 0) {
            requestURL = requestURL.substring(startIndex + 2);
        }

        int endIndex = requestURL.indexOf(47);
        if(endIndex > 0) {
            requestURL = requestURL.substring(0, endIndex);
        }

        String domain = requestURL.split(":")[0];
        return domain;
    }

    private void insertLoginLog(int logType, String userid, String loginIp, String authNum, String authToke, String authMac) {
        ConnnectDbTools bean = new ConnnectDbTools();
        StringBuffer sb = new StringBuffer("用户 ");
        sb.append(userid);
        sb.append(" 从");
        sb.append(SsoClientUtils.SYSTEM_CODE);
        sb.append("本地登录成功");
        bean.insertLog(logType, userid, loginIp, authNum, authToke, authMac, sb.toString());
    }
}