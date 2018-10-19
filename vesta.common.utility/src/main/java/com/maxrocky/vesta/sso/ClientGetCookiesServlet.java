package com.maxrocky.vesta.sso;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Tom on 2016/2/25 14:10.
 * Describe:
 */
public class ClientGetCookiesServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String userid = request.getParameter("userid");
        String pwdExDays = request.getParameter("pwdexdays");

        CookiesUtil cookiesUtil = new CookiesUtil(request, response);
        String date_String = DateTools.formatString(new Date());

        ConnnectDbTools conn = new ConnnectDbTools();
        String sys_String = conn.getUserSys(userid);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        StringBuffer sb = new StringBuffer("");
        if (("-1".equals(sys_String)) || ("0".equals(sys_String)))
        {
            sb.append(sys_String);
        }
        else
        {
            cookiesUtil.getHashMap().put("logonUserID", userid);
            cookiesUtil.getHashMap().put("logonTime", date_String);
            cookiesUtil.getHashMap().put("lastAccessTime", date_String);
            cookiesUtil.getHashMap().put("systemCodes", sys_String);
            cookiesUtil.getHashMap().put("expireTime", conn.getCookiesExpireTime());
            cookiesUtil.getHashMap().put("passwordExpireDays", pwdExDays);

            cookiesUtil.setKey_one_value(NumberTools.getClientRandem(256));
            try
            {
                cookiesUtil.setCookies(0);
            }
            catch (Exception e)
            {
                SsoLogger.error(e);
            }
            sb.append("AuthUser_AuthNum");
            sb.append(":");
            sb.append(cookiesUtil.getKey_one_value());
            sb.append(";");
            sb.append("AuthUser_AuthToken");
            sb.append(":");
            sb.append(cookiesUtil.getKey_two_value());
            sb.append(";");
            sb.append("AuthUser_AuthMAC");
            sb.append(":");
            sb.append(cookiesUtil.getAll_cookies_value());
            sb.append(";");
            sb.append("domain");
            sb.append(":");
            sb.append(getLocalDomainName(request));
            sb.append(";");
            sb.append("expiretime");
            sb.append(":");
            int expireTime = DateTools.getCookiesExpireTime((String)cookiesUtil.getHashMap().get("expireTime"));
            sb.append(String.valueOf(expireTime));
        }
        out.println(sb.toString());
        out.flush();
        out.close();
    }

    private String getLocalDomainName(HttpServletRequest request)
    {
        String requestURL = request.getRequestURL().toString();
        int startIndex = requestURL.indexOf("//");
        if (startIndex >= 0) {
            requestURL = requestURL.substring(startIndex + 2);
        }
        int endIndex = requestURL.indexOf('/');
        if (endIndex > 0) {
            requestURL = requestURL.substring(0, endIndex);
        }
        String domain = requestURL.split(":")[0];

        return domain;
    }

    public void set(String key, String value, int seconds_age, HttpServletResponse response)
    {
        if (response == null) {
            throw new IllegalStateException("Servlet response not available");
        }
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(seconds_age);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <BODY>");
        out.print("    This is ");
        out.print(getClass());
        out.println(", using the POST method");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }
}
