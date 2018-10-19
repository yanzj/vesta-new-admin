package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.AnswerEntity;
import com.maxrocky.vesta.domain.model.IsayImageEntity;

import java.util.List;

/**
 * Created by chen on 2016/1/21.
 */
public interface AnswerRepository {
    /**新增反馈信息*/
    void AddAnswer(AnswerEntity answerEntity);
    /**获取回复信息*/
    AnswerEntity getAnswer(String consultId,String answerType);
    /**获取回复信息列表*/
    List<AnswerEntity> getAnswers(String consultId,String answerType);

    /**
     * 更新咨询回复信息
     * @param answerEntity
     */
    void chengeAnswerEntity(AnswerEntity answerEntity);

    /**
     * 查询图片集合
     * @param id
     * @return
     */
    List<IsayImageEntity> getIsayImageList(String id);


}
