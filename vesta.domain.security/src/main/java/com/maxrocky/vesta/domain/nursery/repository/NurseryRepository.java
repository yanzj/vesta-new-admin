package com.maxrocky.vesta.domain.nursery.repository;

import com.maxrocky.vesta.domain.nursery.model.NurseryEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * 苗木Repository层
 * Created by yuanyn on 2017/9/29.
 */
public interface NurseryRepository {
    /**
     * 新增苗木信息
     */
    void addNurseryEntity(NurseryEntity nurseryEntity);

    /**
     * 获取苗木List
     */
    List<NurseryEntity> getNurseryList(WebPage webPage);

    /**
     * 修改苗木信息
     */
    void updateNursery(NurseryEntity nurseryEntity);
}
