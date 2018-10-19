package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.UserGiftEntity;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/16.
 */
public interface UserGiftRepository {
    /**
     * 获取礼物规则
     * @return
     */
    UserGiftEntity getUserGift();

    /**
     * 新增或修改
     * @param u
     */
    void addUserGify(UserGiftEntity u);
}
