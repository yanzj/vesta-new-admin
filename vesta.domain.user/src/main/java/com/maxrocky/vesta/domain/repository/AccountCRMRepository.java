package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.AccountCRMEntity;
import com.maxrocky.vesta.domain.model.UserCRMEntity;

import java.util.List;

/**
 * Created by liudongxin on 2016/4/13.
 */
public interface AccountCRMRepository {

    /**
     * Describe:根据会员编号获取信息
     * CreateBy:lingdongxin
     * CreateOn:2016-01-14 09:40:37
     */
    AccountCRMEntity get(String id, String memberId);

    /**
     * Describe:创建会员账号信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-19 10:01:12
     */
    void create(AccountCRMEntity accountCRMEntity);

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改会员账号信息
     * ModifyBy:
     */
    void update(AccountCRMEntity accountCRMEntity);

    /**
     * Describe:获取所有会员账号信息
     * CreateBy:dinglei
     * CreateOn:2016-01-14 09:40:37
     */
    List<AccountCRMEntity> getAccountInfo();

    /**
     * 根据accountId取得数据
     * @param accountId
     * @return
     */
    AccountCRMEntity get(String accountId);


    /**
     * 根据mobile取得数据
     * @param mobile
     * @return
     */
    AccountCRMEntity getByMobile(String mobile);
}
