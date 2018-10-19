package com.maxrocky.vesta.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tom on 2016/1/15 11:09.
 * Describe:验证类
 */
public class VerifyUtils {

    /**
     * Describe:手机号码校验
     */
    public static boolean isMobile(String moile){
        return Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$").matcher(moile).matches();
    }

}
