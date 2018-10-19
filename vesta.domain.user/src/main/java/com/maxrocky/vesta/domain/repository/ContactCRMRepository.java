package com.maxrocky.vesta.domain.repository;


import com.maxrocky.vesta.domain.model.ContactCRMEntity;

import java.util.List;

/**
 * Created by liudongxin on 2016/4/28.
 */
public interface ContactCRMRepository {
    /**
     * Describe:根据会员编号获取信息
     * CreateBy:lingdongxin
     * CreateOn:2016-01-14 09:40:37
     */
    ContactCRMEntity get(String memberId);

    /**
     * Describe:创建通讯信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-19 10:01:12
     */
    void create(ContactCRMEntity contactCRMEntity);

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改通讯信息
     * ModifyBy:
     */
    void update(ContactCRMEntity contactCRMEntity);
    /**
     * CreatedBy:dl
     * Describe:
     * 获取会员的全部通讯信息
     *
     * ModifyBy:
     */
    List<ContactCRMEntity> getContactInfo();
}
