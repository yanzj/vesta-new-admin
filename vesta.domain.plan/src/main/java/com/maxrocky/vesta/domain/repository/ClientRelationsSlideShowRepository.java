package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ClientRelationsSlideShowEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 客关轮播图DAO层接口
 * Created by yuanyn on 2018/6/11 0011.
 */
public interface ClientRelationsSlideShowRepository {

    /**
     * 获取轮播图列表
     * @param paramsMap
     * @param webPage
     * @return List<Map<String,Object>>
     */
    List<ClientRelationsSlideShowEntity> getSlideShowList(Map<String,Object> paramsMap, WebPage webPage);

    //根据id获取轮播图详情
    ClientRelationsSlideShowEntity getSlideShowById(String slideShowId);

    //保存或者修改Entity
    void saveSlideShowEntity(ClientRelationsSlideShowEntity clientRelationsSlideShowEntity);

    //查询已上架的轮播图数
    boolean getIsSlideShow();

}
