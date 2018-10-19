package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.domain.model.AccountCRMEntity;
import com.maxrocky.vesta.domain.model.CardCRMEntity;
import com.maxrocky.vesta.domain.model.ContactCRMEntity;
import com.maxrocky.vesta.domain.model.UserCRMEntity;

/**
 * Created by langmafeng on 2016/5/6 11:03.
 * 会员注册向CRM传数据
 */
public interface MembershipRegisterService {
    String userInfoRegister(AccountCRMEntity accountCRMEntity, UserCRMEntity userCRMEntity, ContactCRMEntity contactCRMEntity, CardCRMEntity cardCRMEntity);
}
