package com.maxrocky.vesta.utility;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Itzxs on 2018/7/13.
 */
public class LoadPorperty {

    private static Properties prop;

    public static String getPoerpertyValue(String fileName,String key){
        String value = null;
        prop = new Properties();
        InputStream in = null;
        try{
            //读取属性文件
            in = LoadPorperty.class.getClassLoader().getResourceAsStream(fileName);
            prop.load(in);     //加载属性列表
            if(prop.get(key) != null){
                value = prop.get(key).toString();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }
}
