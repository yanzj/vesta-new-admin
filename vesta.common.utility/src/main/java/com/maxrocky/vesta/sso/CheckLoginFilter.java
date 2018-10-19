package com.maxrocky.vesta.sso;

import java.io.IOException;
import java.net.MalformedURLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckLoginFilter implements Filter {
    private CheckLoginBean checkLoginBean = new CheckLoginBean();

    public CheckLoginFilter() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        int returnValue = this.getCheckLoginBean().processLogin(request, response);
        if(returnValue == 6 || returnValue == 1) {
            filterChain.doFilter(request, response);
        }

    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        String containerType = filterConfig.getInitParameter("containerType");
        String configLocation;
        if(containerType != null && containerType.toUpperCase().contains("WEBLOGIC")) {
            configLocation = "";

            try {
                configLocation = filterConfig.getServletContext().getResource("/").getPath();
                configLocation = this.getClass().getClassLoader().getResource("/").getPath();
                configLocation = configLocation.substring(0, configLocation.indexOf("classes"));
                configLocation = configLocation.substring(0, configLocation.indexOf("WEB-INF"));
                SsoClientUtils.CONTENT_PAHT = configLocation;
            } catch (MalformedURLException var6) {
                SsoLogger.error(var6);
            }
        } else {
            SsoClientUtils.CONTENT_PAHT = filterConfig.getServletContext().getRealPath("/");
        }

        configLocation = filterConfig.getInitParameter("configLocation");
        if(configLocation != null && configLocation.trim().length() > 0) {
            SsoClientUtils.CONFIG_LOCATION = configLocation;
        }

        this.getCheckLoginBean().init();

//        try {
//            ConnnectDbTools e = new ConnnectDbTools();
//            SsoClientUtils.ACCOUNT_POLICY = e.getDbPasswordAccountPolicy();
//        } catch (Exception var5) {
//            SsoLogger.error(var5);
//        }

    }

    protected CheckLoginBean getCheckLoginBean() {
        return this.checkLoginBean;
    }
}

