package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.BillInfoEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by liuwei on 2016/3/5.
 */

public interface BillInfoRepository {
    List<BillInfoEntity> getBillInfosByProject(String projectId, WebPage webPage);

    void updateBillInfo(BillInfoEntity billInfoEntity);

    BillInfoEntity getBillInfoById(String billInfoId);
}
