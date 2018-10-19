package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.jsonDTO.SupplierAppDTO;
import com.maxrocky.vesta.application.DTO.jsonDTO.SupplierRelationshipSnapDTO;

import java.util.Date;

/**
 * Created by mql on 2016/6/16.
 */
public interface SupplierRelationshipSnapService {
    /**
     * 根据修改时间查询
     * @param modifyDate
     * @param id
     * @return
     */
    SupplierRelationshipSnapDTO getByModifyDateAndId(Date modifyDate,String id);
}
