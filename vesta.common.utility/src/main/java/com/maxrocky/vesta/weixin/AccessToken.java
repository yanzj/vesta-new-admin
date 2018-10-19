package com.maxrocky.vesta.weixin;

/**
 * Created by JillChen on 2015/9/20.
 */
public class AccessToken {

    //过期毫秒---绝对值
    private long  expiresIn;

    private String token;

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
