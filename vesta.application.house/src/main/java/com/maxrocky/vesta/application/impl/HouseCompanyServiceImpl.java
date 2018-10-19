package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.HouseCompanyAdminDTO;
import com.maxrocky.vesta.application.inf.HouseCompanyService;
import com.maxrocky.vesta.domain.model.HouseCompanyEntity;
import com.maxrocky.vesta.domain.repository.HouseCompanyRepository;
import com.maxrocky.vesta.utility.StringUtil;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tom on 2016/3/8 14:48.
 * Describe:公司Service接口实现类
 */
@Service
public class HouseCompanyServiceImpl implements HouseCompanyService {

    /* 公司 */
    @Autowired
    HouseCompanyRepository houseCompanyRepository;

    /* mapper */
    @Autowired
    MapperFacade mapper;

    /**
     * Code:For Service
     * Type:Service Method
     * Describe:返回指定ID的公司
     * CreateBy:Tom
     * CreateOn:2016-03-08 02:52:49
     */
    @Override
    public HouseCompanyAdminDTO getHouseCompanyAdminDTOById(String companyId) {

        if(StringUtil.isEmpty(companyId)){
            return null;
        }
        HouseCompanyAdminDTO houseCompanyAdminDTO = null;
        HouseCompanyEntity houseCompanyEntity = houseCompanyRepository.getCompanyById(companyId);
        if(houseCompanyEntity != null){
            houseCompanyAdminDTO = mapper.map(houseCompanyEntity, HouseCompanyAdminDTO.class);
        }

        return houseCompanyAdminDTO;
    }

}
