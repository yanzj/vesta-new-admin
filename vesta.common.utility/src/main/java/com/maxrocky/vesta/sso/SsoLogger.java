package com.maxrocky.vesta.sso;

/**
 * Created by Tom on 2016/2/25 14:20.
 * Describe:
 */
public class SsoLogger {
    private static LogBean logBean = new DefaultLogBean();

    public LogBean getLogBean()
    {
        return logBean;
    }

    public void setLogBean(LogBean logBean)
    {
        logBean = logBean;
    }

    static void info(String message)
    {
        if (logBean != null) {
            logBean.info(message);
        }
    }

    static void info(Exception e)
    {
        if (logBean != null) {
            logBean.info(e);
        }
    }

    static void error(String message)
    {
        if (logBean != null) {
            logBean.error(message);
        }
    }

    static void error(Exception e)
    {
        if (logBean != null) {
            logBean.error(e);
        }
    }
}
