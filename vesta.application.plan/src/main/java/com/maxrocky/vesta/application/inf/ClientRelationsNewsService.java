package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.adminDTO.QCNewsDTO;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * 客关新闻service接口
 * Created by yuanyn on 2018/6/20 0011.
 */
public interface ClientRelationsNewsService {

    //获取新闻列表
    List<QCNewsDTO> getNewsList(QCNewsDTO newsDTO, WebPage webPage, List<String> projectIds);

    //根据id查询新闻详情
    QCNewsDTO getNewsById(String newsId);

    //发布、更新新闻
    void saveOrUpdateNews(UserInformationEntity userInformationEntity, QCNewsDTO newsDTO);

    //删除新闻
    void delNews(UserInformationEntity userInformationEntity, QCNewsDTO newsDTO);

}
