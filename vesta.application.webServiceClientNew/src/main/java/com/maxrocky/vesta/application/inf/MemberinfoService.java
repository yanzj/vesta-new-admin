package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.domain.model.*;

/**
 * Created by liudongxin on 2016/5/24.
 * 会员更新
 */
public interface MemberinfoService {
    String userInfoUpdate(UserCRMEntity userCRMEntity,
                          ContactCRMEntity contactCRMEntity,
                          CardCRMEntity cardCRMEntity,
                          CarCRMEntity carCRMEntity,
                          FamilyCRMEntity familyCRMEntity,
                          AccountCRMEntity accountCRMEntity,
                          GradeCRMEntity gradeCRMEntity);
}
