package com.maxrocky.vesta.weixin;

import com.maxrocky.vesta.utility.AppConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by JillChen on 2015/9/20.
 */
public class InitAccessToken extends HttpServlet {
    private static final long serialVersionUID = 1L;
//    private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

    public void init() throws ServletException {
        // 获取web.xml中配置的参数
        TokenThread.appid = AppConfig.AppID;
        TokenThread.appsecret = AppConfig.AppSecret;

        System.out.println("weixin api appid:---------" + TokenThread.appid);
        System.out.println("weixin api appsecret:-------------"+TokenThread.appsecret);

        // 未配置appid、appsecret时给出提示
        if ("".equals(TokenThread.appid) || "".equals(TokenThread.appsecret)) {
            System.out.println("appid and appsecret configuration error, please check carefully.");
        } else {
            // 启动定时获取access_token的线程
            new Thread(new TokenThread()).start();
        }
    }
}

