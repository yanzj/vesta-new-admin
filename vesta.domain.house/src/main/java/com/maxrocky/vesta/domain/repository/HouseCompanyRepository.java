package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HouseCompanyEntity;

/**
 * Created by Tom on 2016/1/22 16:01.
 * Describe:公司Repository接口
 */
public interface HouseCompanyRepository {

    /**
     * Describe:根据公司名称获取公司
     * CreateBy:Tom
     * CreateOn:2016-01-22 04:02:42
     */
    HouseCompanyEntity getByCompanyName(String companyName);

    /**
     * 通过公司Id查找公司信息
     * @param companyId
     * @return
     */
    public HouseCompanyEntity getCompanyById(String companyId);

}
