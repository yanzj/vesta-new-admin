package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.UserFeedbackEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by Tom on 2016/1/21 17:55.
 * Describe:用户意见反馈Repository接口
 */
public interface UserFeedbackRepository {

    /**
     * Describe:创建意见反馈
     * CreateBy:Tom
     * CreateOn:2016-01-21 05:59:21
     */
    void create(UserFeedbackEntity userFeedbackEntity);

    /**
     * 意见反馈列表
     * @param userFeedbackEntity
     * @param webPage
     * @return
     */
    List<UserFeedbackEntity> FeedbackList(UserFeedbackEntity userFeedbackEntity, WebPage webPage);

    /**
     * 获取意见反馈列表
     * param userFeedbackEntity
     * param webPage
     * return
     */
    List<UserFeedbackEntity> getFeedbackList(UserFeedbackEntity userFeedbackEntity,WebPage webPage,String roleScopes);

    /**
     * 获取意见反馈信息
     * param id
     * return
     */
    UserFeedbackEntity getFeedbackInfo(String id);

    /**
     * 修改
     * param feedbackEntity
     */
    boolean update(UserFeedbackEntity feedbackEntity);

    /**
     * 通过真实姓名和身份证去检索申诉信息
     * @param content  内容(姓名:身份证)
     * @return List<UserFeedbackEntity>
     */
    List<UserFeedbackEntity> getFeedbackListByContent(String content);
}
