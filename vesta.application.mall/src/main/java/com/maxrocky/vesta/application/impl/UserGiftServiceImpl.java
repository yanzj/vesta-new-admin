package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.AdminDTO.UserGiftDTO;
import com.maxrocky.vesta.application.inf.UserGiftService;
import com.maxrocky.vesta.domain.model.UserGiftEntity;
import com.maxrocky.vesta.domain.repository.UserGiftRepository;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/16.
 */
@Service
public class UserGiftServiceImpl implements UserGiftService {

    @Autowired
    UserGiftRepository userGiftRepository;

    @Override
    public UserGiftDTO getUserGift() {
        UserGiftDTO dto = new UserGiftDTO();
        UserGiftEntity u = userGiftRepository.getUserGift();
        if (u != null)
            BeanUtils.copyProperties(u,dto);
        return dto;
    }

    @Override
    public void UpdateUserGift(UserGiftDTO gift) {
        UserGiftEntity userGiftEntity = new UserGiftEntity();
        BeanUtils.copyProperties(gift,userGiftEntity);
        if(userGiftEntity.getUserGiftId() == null){
            userGiftEntity.setUserGiftId(IdGen.uuid());
        }
        if(gift.getGiftType().equals("1")){
            //积分
            userGiftEntity.setProductId("");
        }else{
            //商品
            userGiftEntity.setNumber("");
        }
        userGiftRepository.addUserGify(userGiftEntity);
    }
}
