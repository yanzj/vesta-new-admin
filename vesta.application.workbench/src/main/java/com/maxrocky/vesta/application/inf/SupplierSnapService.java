package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.jsonDTO.SupplierAppDTO;
import com.maxrocky.vesta.application.DTO.jsonDTO.SupplierSnapDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by mql on 2016/6/15.
 */
public interface SupplierSnapService {

    /**
     * 根据修改时间查询
     * @param modifyDate
     * @param id
     * @return
     */
    SupplierSnapDTO getByModifyDateAndId(String modifyDate,String id);
}
