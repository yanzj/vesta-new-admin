package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ViewAppOwnerInfoEntity;

/**
 * Created by Tom on 2016/1/18 16:15.
 * Describe:基础业主信息Repository接口
 */
public interface ViewAppOwnerInfoRepository {

    /**
     * Describe:根据业主Id查询业主信息
     * CreateBy:Tom
     * CreateOn:2016-01-18 04:17:22
     */
    ViewAppOwnerInfoEntity getByOwnerId(int ownerId);

}
