package com.maxrocky.vesta.sso;

import java.io.PrintStream;

/**
 * Created by Tom on 2016/2/25 14:15.
 * Describe:
 */
public class DefaultLogBean implements LogBean{
    public void info(String message)
    {
        System.out.println(message);
    }

    public void info(Throwable e)
    {
        e.printStackTrace();
    }

    public void error(String message)
    {
        System.out.println(message);
    }

    public void error(Throwable e)
    {
        e.printStackTrace();
    }
}
