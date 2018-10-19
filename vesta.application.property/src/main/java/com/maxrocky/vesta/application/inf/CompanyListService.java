package com.maxrocky.vesta.application.inf;

import java.util.Map;

/**
 * Created by 27978 on 2016/8/11.
 */
public interface CompanyListService {

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取责任单位列表
    */
    public Map<String,String> getCompanyOnes(String projectId, String thirdId);
}
