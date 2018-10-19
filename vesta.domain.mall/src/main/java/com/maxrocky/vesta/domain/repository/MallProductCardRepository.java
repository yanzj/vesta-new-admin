package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.MallProductCardEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/5.
 */
public interface MallProductCardRepository {

    void addOrupdate(MallProductCardEntity c);

    List<MallProductCardEntity> getAll(String mallId, WebPage webPage);

    MallProductCardEntity getCode(String mallId,String code);

}
