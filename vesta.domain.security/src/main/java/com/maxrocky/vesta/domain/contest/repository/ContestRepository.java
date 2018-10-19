package com.maxrocky.vesta.domain.contest.repository;

import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 安全比武
 * Created by yuanyn on 2017/9/1.
 */
public interface ContestRepository {

    /**
     * 安全比武 条件检索查询
     */
    List<Object[]> getContestList(Map map, WebPage webPage);

    /**
     * 获取比武图片
     */
    List<Object[]> getContestImageList(List<String> idList);
}
