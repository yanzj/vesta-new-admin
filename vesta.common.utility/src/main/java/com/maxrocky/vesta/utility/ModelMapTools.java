package com.maxrocky.vesta.utility;

import org.springframework.ui.ModelMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tom on 2016/3/18 15:02.
 * Describe:ModelMap Tools
 */
public class ModelMapTools {

    /**
     * Describe:对ModelMap key and value
     * CreateBy:Tom
     * CreateOn:2016-03-18 09:39:01
     */
    public static Map<Object, Object> toMapOfObject(ModelMap modelMap){

        Map<Object, Object> map = new HashMap<Object, Object>();
        if(modelMap == null){
            return map;
        }
        for (String key : modelMap.keySet()){
            if(!StringUtil.isEmpty(key)){
                map.put(key, modelMap.get(key));
            }
        }

        return map;
    }

}
