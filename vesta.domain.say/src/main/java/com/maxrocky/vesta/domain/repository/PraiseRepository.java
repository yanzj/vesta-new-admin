package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.PraiseEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.Page;

import java.util.List;

/**
 * Created by chen on 2016/1/20.
 * 我要表扬接口
 */
public interface PraiseRepository {
    /**新增表扬*/
    void AddPraise(PraiseEntity praiseEntity);
    /**获取前台用户表扬列表*/
    List<PraiseEntity> getPraises(String userId,Page page);
    /**获取员工表扬列表*/
    List<PraiseEntity> getStaffPraises(String userId,Page page);
    /**获取表扬详情*/
    PraiseEntity getPraiseDetail(String id);
    /**后台获取表扬列表*/
    List<PraiseEntity> getPraiseList(PraiseEntity praiseEntity,String beginTime,String endTime,WebPage webPage);

    /**
     * 获取员工表扬的未读数量
     * @param targetId
     * @return
     */
    public int countUnread(String targetId);
    /**更新员工表扬未读状态*/
    public void altUnread(String targetId);

    /**更新表扬*/
    public void altPraise(PraiseEntity praiseEntity);
}
