package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.UserTokenEntity;

/**
 * Created by chen on 2016/4/6.
 */
public interface UserTokenRepository {
        /**
         * 保存token
         * @param object
         */
        void Add(UserTokenEntity object);

        void ClearUnvaliableUserToken();

        UserTokenEntity GET(String vestaToken);

        void ClearToken(String formUser);

        void UpdateToken(UserTokenEntity formUser, String toUser);

        void Update(String vestatoken);

        UserTokenEntity getByUserId(String userId);

}
