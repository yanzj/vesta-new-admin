package com.maxrocky.vesta.filter.Setting;

import com.maxrocky.vesta.utility.AppConfig;

import javax.servlet.*;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by Tom on 2016/3/21 16:35.
 * Describe:Load setting config.
 */
public class AppConfigFilter implements Filter {

    private AppConfigSettingBean appConfigSettingBean = new AppConfigSettingBean();

    public AppConfigFilter(){}

    @Override
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
                AppConfig.CONTENT_PATH = configLocation;
            } catch (MalformedURLException var6) {
                var6.printStackTrace();
            }
        } else {
            AppConfig.CONTENT_PATH = filterConfig.getServletContext().getRealPath("/");
        }

        configLocation = filterConfig.getInitParameter("configLocation");
        if(configLocation != null && configLocation.trim().length() > 0) {
            AppConfig.CONFIG_LOCATION = configLocation;
        }

        this.getAppConfigSettingBean().loadConfig();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    protected AppConfigSettingBean getAppConfigSettingBean() {
        return this.appConfigSettingBean;
    }

}
