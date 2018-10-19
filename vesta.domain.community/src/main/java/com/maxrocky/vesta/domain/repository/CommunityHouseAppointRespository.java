package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.CommunityHouseAppointEntity;
import com.maxrocky.vesta.domain.model.HouseInfoEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by Tom on 2016/4/14 0:06.
 * Describe:
 */
public interface CommunityHouseAppointRespository {

    List<CommunityHouseAppointEntity> getList(CommunityHouseAppointEntity communityHouseAppointEntity ,WebPage webPage);

    /**
     * 后台查询用户预约信息
     * @param communityHouseAppointEntity
     * @param userInfoEntity
     * @return
     */
    List<Object> getUserAppoints(CommunityHouseAppointEntity communityHouseAppointEntity,UserInfoEntity userInfoEntity,HouseInfoEntity houseInfoEntity,WebPage webPage);

    CommunityHouseAppointEntity propertyHousePay(String housepayID);

    boolean delPropertyHousePay(CommunityHouseAppointEntity communityHouseAppointEntity);


}
