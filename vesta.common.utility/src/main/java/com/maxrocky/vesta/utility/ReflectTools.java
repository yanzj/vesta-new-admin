package com.maxrocky.vesta.utility;

import org.springframework.ui.ModelMap;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * Created by Tom on 2016/3/18 13:50.
 * Describe:Reflect Tools
 */
public class ReflectTools {

    /**
     * Describe:Class reflect to Map<Object, Object>
     * CreateBy:Tom
     * CreateOn:2016-03-18 01:52:43
     * @param obj Object
     * @return Map<Object, Object>
     */
    public static Map<Object, Object> classToMap(Object obj) throws Exception {
        Map<Object, Object> map = new HashMap<Object, Object>();
        /* get class */
        Class<?> className = obj.getClass();
        /* get class's methods */
        Method methods[] = className.getDeclaredMethods();
        /* get class's fields */
        Field fields[] = className.getDeclaredFields();

        for (Field field : fields){
            /* get field the get method name */
            String getMethodName = getMethodName(field.getName());
            if(!isExistMethod(methods, getMethodName)){
                continue;
            }
            /* get field the type */
            String fieldType = field.getType().getSimpleName();

            /* get field the value */
            String value = "";
            Method method = className.getMethod(getMethodName, null);
            Object object = method.invoke(obj, new Object[]{});
            if(null != object){
                if(fieldType.equals("Date")){
                    value = DateUtils.format((Date)object);
                }
                value = String.valueOf(object);
            }
            map.put(field.getName(), value);
        }

        return map;
    }

    /**
     * Describe:Map<Object, Object> reflect to Class
     * CreateBy:Tom
     * CreateOn:2016-03-18 02:29:05
     * @param map Map<Object, Object>
     * @param obj Object
     */
    public static void MapToClass(Map<Object, Object> map, Object obj) throws Exception {
        /* get class */
        Class<?> className = obj.getClass();
        /* get class's methods */
        Method methods[] = className.getDeclaredMethods();
        /* get class's fields */
        Field fields[] = className.getDeclaredFields();

        for (Field field : fields){
            /* get field the get method name */
            String setMethodName = setMethodName(field.getName());
            if(!isExistMethod(methods, setMethodName)){
                continue;
            }
            /* get field the type */
            String fieldType = field.getType().getSimpleName();
            /* get map value by key(field's name) */
            Object value = map.get(field.getName());

            /* set value to class */
            Method method = className.getMethod(setMethodName, field.getType());
            if(null != value){
                if("String".equals(fieldType)){
                    method.invoke(obj, (String)value);
                }else if("Double".equals(fieldType)){
                    method.invoke(obj, (Double)value);
                }else if ("int".equals(fieldType) || "Integer".equals(fieldType)){
                    method.invoke(obj, Integer.valueOf((String)value));
                }else if("Date".equals(fieldType)){
                    method.invoke(obj, DateUtils.parse((String)value));
                }
            }
        }
    }

    /**
     * Describe:Return get method name
     * CreateBy:Tom
     * CreateOn:2016-03-18 02:10:49
     * @param fieldName field's name
     * @return String
     */
    public static String getMethodName(String fieldName){
        return getMethodName(fieldName, "get");
    }

    /**
     * Describe:Return set method name
     * CreateBy:Tom
     * CreateOn:2016-03-18 02:13:27
     * @param fieldName field's name
     * @return String
     */
    public static String setMethodName(String fieldName){
        return getMethodName(fieldName, "set");
    }

    /**
     * Describe:Return method's name by field's name and method's type
     * CreateBy:Tom
     * CreateOn:2016-03-18 02:03:19
     * @param fieldName field's name
     * @param methodType field's type(get or set)
     * @return String
     */
    public static String getMethodName(String fieldName, String methodType){
        StringBuffer methodName = new StringBuffer(methodType);
        if(!StringUtil.isEmpty(fieldName)){
            methodName.append(fieldName.substring(0,1).toUpperCase());
            methodName.append(fieldName.substring(1));
        }
        return methodName.toString();
    }

    /**
     * Describe:Check method is exist
     * CreateBy:Tom
     * CreateOn:2016-03-18 02:16:00
     * @param methods method list
     * @param methodName method name
     * @return Boolean
     */
    public static Boolean isExistMethod(Method methods[], String methodName){
        if(null != methods){
            for (Method method : methods){
                if(StringUtil.isEqual(methodName, method.getName())){
                    return true;
                }
            }
        }
        return false;
    }

}
