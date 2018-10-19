package com.maxrocky.vesta.application.inf;

import java.util.Calendar;


/**
 * Created by langmafeng on 2016/4/18.
 * 接收金茂项目CRM传递房屋基础数据
 */
public interface BasicHouseService{
    String queryBasicInfo(String[] projectCodeList, Calendar lastModifyTime);
}
