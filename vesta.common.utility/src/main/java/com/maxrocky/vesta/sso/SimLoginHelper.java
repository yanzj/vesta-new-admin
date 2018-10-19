package com.maxrocky.vesta.sso;

import java.io.IOException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SimLoginHelper {
    public SimLoginHelper() {
    }

    public void setSSOLogined(HttpServletRequest request, HttpServletResponse response, String userName) throws Exception {
        CookiesUtil cookiesUtil = new CookiesUtil(request, response);
        String date_String = DateTools.formatString(new Date());
        cookiesUtil.getHashMap().put("logonUserID", userName);
        cookiesUtil.getHashMap().put("logonTime", date_String);
        cookiesUtil.getHashMap().put("lastAccessTime", date_String);
        cookiesUtil.getHashMap().put("systemCodes", SsoClientUtils.SYSTEM_CODE);
        cookiesUtil.getHashMap().put("expireTime", "01:00:00");
        cookiesUtil.getHashMap().put("passwordExpireDays", Integer.valueOf(-1));
        cookiesUtil.getHashMap().put("clientTimeInterval", "0");
        cookiesUtil.setKey_one_value(NumberTools.getClientRandem(256));
        request.getSession().setAttribute("LoginMode", "1");
        cookiesUtil.setCookies(1);
    }

    public void setSSOLogout(HttpServletRequest request, HttpServletResponse response) {
        CookiesUtil cookiesUtil = new CookiesUtil(request, response);
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }

        cookiesUtil.deleteAllCookies();
    }

    public void ssoExitRedirect(HttpServletResponse response, String url) throws IOException {
        response.sendRedirect("wd_sso_logout?action=exit&url=" + url);
    }
}
