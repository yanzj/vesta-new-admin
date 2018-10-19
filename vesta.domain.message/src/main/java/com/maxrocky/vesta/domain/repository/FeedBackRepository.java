package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.FeedBackEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Itzxs on 2018/5/22.
 */
public interface FeedBackRepository {

    boolean saveFeedBack(FeedBackEntity feedBackEntity);

    List<Object[]> getFeedBackList(String create, String userId, String phone, WebPage webPage);

}
