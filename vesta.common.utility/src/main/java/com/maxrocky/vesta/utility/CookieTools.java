package com.maxrocky.vesta.utility;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by JillChen on 2016/1/4.
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

    /**
     * 返回手机uuid
     */
    public static String getPhoneUUID(HttpServletRequest request){
        return getCookie(request, "uuid");
    }

    /**
     * 返回指定HttpServletRequest中的cookie值
     */
    public static String getCookie(HttpServletRequest request, String cookieName){
        String cookieValue = "";
        final Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie item : cookies){
                String name = item.getName();
                System.out.println("cookieName--->>" + item.getName() + "----cookieValue--->>" + item.getValue());
                if(StringUtil.isEqual(name, cookieName)){
                    cookieValue = item.getValue();
                }
            }
        }
        return cookieValue;
    }

}
