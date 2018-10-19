package com.maxrocky.vesta.sso;

/**
 * Created by Tom on 2016/2/25 14:17.
 * Describe:
 */
public abstract interface LogBean {

    public abstract void info(String paramString);

    public abstract void info(Throwable paramThrowable);

    public abstract void error(String paramString);

    public abstract void error(Throwable paramThrowable);
}
