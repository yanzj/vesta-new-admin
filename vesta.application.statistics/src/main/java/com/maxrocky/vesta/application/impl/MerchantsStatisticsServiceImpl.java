package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.MerchantsStatisticsService;
import com.maxrocky.vesta.domain.repository.MerchantsStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ZhangBailiang on 2016/2/17.
 * 商户统计 业务逻辑层实现类
 */
@Service
public class MerchantsStatisticsServiceImpl implements MerchantsStatisticsService {

    /**
     * 商户统计 持久层接口
     */
    @Autowired
    MerchantsStatisticsRepository merchantsStatisticsRepository;
}
