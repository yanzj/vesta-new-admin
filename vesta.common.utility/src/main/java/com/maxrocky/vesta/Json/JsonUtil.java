package com.maxrocky.vesta.Json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * Created by JillChen on 2016/1/26.
 */
@Component
public class JsonUtil {
    private static final String DEFAULT_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
    public static final ObjectMapper mapper;

    static {

        System.out.println("---------JsonUtil----------");
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        mapper = new ObjectMapper();
        mapper.setDateFormat(dateFormat);
    }

    public static String toJson(Object obj) {

        System.out.print("---------toJson----------");
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("转换json字符失败!");
        }
    }

}
