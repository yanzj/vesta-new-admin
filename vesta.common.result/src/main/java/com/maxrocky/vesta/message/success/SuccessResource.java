package com.maxrocky.vesta.message.success;


import com.maxrocky.vesta.message.error.CustomizedPropertyPlaceholderConfigurer;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * Created by JillChen on 2016/1/6.
 */
public class SuccessResource {
    public static Map<Integer,String> getResource(String successKey){

        String host =  (String) CustomizedPropertyPlaceholderConfigurer.getContextProperty(successKey);
        int code = 0;
        Map<Integer,String> codemap=  new HashedMap();
        codemap.put(code,host);
        return codemap;
    }
}
