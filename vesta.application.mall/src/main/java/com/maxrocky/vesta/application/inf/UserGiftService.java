package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.AdminDTO.UserGiftDTO;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/16.
 */
public interface UserGiftService {

    UserGiftDTO getUserGift();

    void UpdateUserGift(UserGiftDTO gift);
}
