package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.CompanyListService;
import com.maxrocky.vesta.domain.repository.CompanyListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 27978 on 2016/8/12.
 */
@Service
public class CompanyListServiceImpl implements CompanyListService {

    @Autowired
    CompanyListRepository companyListRepository;
    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取责任单位列表
    */
    @Override
    public Map<String, String> getCompanyOnes(String projectId, String thirdId) {
        List<Object[]> companyOnes = companyListRepository.getCompanyOnes(projectId, thirdId);
        Map<String,String> map = new LinkedHashMap<>();
        if(companyOnes != null && !companyOnes.isEmpty()){
            for(Object[] obj : companyOnes){
                map.put(obj[0] == null ? "" : obj[0].toString(), obj[1] == null ? "" : obj[1].toString());
            }
        }
        return map;
    }
}
