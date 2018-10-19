package com.maxrocky.vesta.weixin;

import java.util.UUID;

/**
 * Created by JillChen on 2015/9/21.
 */
public class CookieTools {


    /**
     * 获取新token
     * @return
     */
    public static String getNewCookie(){
        UUID uuid =  UUID.randomUUID();
            System.out.println("新建一个  cookie-----------"+uuid.toString());
        return uuid.toString();
    }

}
