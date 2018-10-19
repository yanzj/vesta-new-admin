package com.maxrocky.vesta.utility;

import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.Map;

/**
 * Created by Tom on 2016/2/17 20:16.
 * Describe:BASE64编码，解码
 */
public class BASE64Utils {

    /**
     * Describe:对指定字符串进行BASE64编码
     * CreateBy:Tom
     * CreateOn:2016-02-17 08:19:28
     */
    public static String encode(String str){
        if(StringUtil.isEmpty(str)){
            return null;
        }
        return (new BASE64Encoder()).encode(str.getBytes());
    }

    /**
     * Describe:对指定字符串进行BASE64解码
     * CreateBy:Tom
     * CreateOn:2016-02-17 08:20:51
     */
    public static String decode(String str){

        String s = "";

        if(StringUtil.isEmpty(str)){
            return s;
        }
        try {
            byte[] b = (new BASE64Decoder()).decodeBuffer(str);
            return new String(b);
        }catch (Exception ex){
            ex.printStackTrace();
            return s;
        }
    }

    /**
     * Describe:对ModelMap key and value
     * CreateBy:Tom
     * CreateOn:2016-03-18 09:39:01
     */
    public static ModelMap decodeModelMap(ModelMap modelMap){

        ModelMap deModelMap = new ModelMap();
        if(modelMap == null){
            return deModelMap;
        }

        for (String enKey : modelMap.keySet()){
            String enValue = (String)modelMap.get(enKey);
            String deKey = decode(enKey);
            String deValue = "";
            if(!StringUtil.isEmpty(enValue)){
                deValue = decode(enValue);
            }
            deModelMap.addAttribute(deKey, deValue);
        }

        return deModelMap;
    }
}
