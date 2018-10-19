package com.maxrocky.vesta.utility;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Tom on 2016/1/19 15:24.
 * Describe:加密工具
 */
public class EncryptUtils {

    /**
     * 密码MD5加密
     */
    public static String encryptPassword(String password){
        return DigestUtils.md5Hex(password);
    }

}
