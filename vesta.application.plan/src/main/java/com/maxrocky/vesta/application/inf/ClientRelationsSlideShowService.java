package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.adminDTO.SlideShowDTO;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 客关轮播图service接口
 * Created by yuanyn on 2018/6/11 0011.
 */
public interface ClientRelationsSlideShowService {

    //获取轮播图列表
    List<SlideShowDTO> getSlideShowList(SlideShowDTO slideShowDTO, WebPage webPage) throws InvocationTargetException, IllegalAccessException;

    //根据id查询详情
    SlideShowDTO getSlideShowById(String slideShowid) throws InvocationTargetException, IllegalAccessException;

    //发布、更新轮播图
    void saveOrUpdateSlideShow(UserInformationEntity userInformationEntity, SlideShowDTO slideShowDTO);

    //置顶轮播图
    boolean toTopSlideShow(UserInformationEntity userInformationEntity,SlideShowDTO slideShowDTO);

    //删除新闻
    void delSlideShow(UserInformationEntity userInformationEntity,SlideShowDTO slideShowDTO);

    //判断轮播图上架个数是否大于五条
    boolean getIsSlideShow();

}
