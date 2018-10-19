package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.domain.model.OwnerAppealEntity;

/**
 * 会员业主申诉WebService
 * Created by WeiYangDong on 2017/8/10.
 */
public interface MemberAppealService {

    String requestCrmMemberAppeal(OwnerAppealEntity ownerAppealEntity);
}
