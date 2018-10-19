package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.utility.Page;

import java.util.List;

/**
 * Created by liuwei on 2016/1/31.
 */
public interface PaymentedRepository {

    List<ViewAppBillDetailInfo> getBillDetailInfoBuyOwnerId(String ownerId);

    ARBillItem getPaymentTypeByBillItemID(String billItemId);


    ViewAppOwnerInfoEntity getOwnerInfoByOwnerId(String ownerId);
    ViewAppHouseInfoEntityForPayment getViewAppHourseByHourceId(String hourseId);

    ViewAppBillDetailInfo getBillDetailByPaymentId(String paymentId);

    ViewAppHouseInfoEntityForPayment getHouseInfoByHouseId(String hourseId);

    void saveBillInfo(BillInfoEntity billInfoEntity);

    BillInfoEntity getBillInfoById(String paymentId);

    void save(Object o);

    void update(Object o);

    List<ViewAppBillDetailInfo> get();


    List<ViewAppBillDetailInfo> getViewAppBillDetailInfoByHouseIds(String s,String payEndDate);

    List<BillInfoEntity> getBillInfoByPaymentIds(String s);

    List<BillInfoEntity> getBillInfoHistoryList(String userId, String projectId, Page page);

    ViewAppHadBillDetialInfoEntity getViewAppHadBillDetialInfoEntityByPaymentId(String paymentId);

    BillInfoEntity getbillInfoSuccessByPaymentId(String paymentId);
}
