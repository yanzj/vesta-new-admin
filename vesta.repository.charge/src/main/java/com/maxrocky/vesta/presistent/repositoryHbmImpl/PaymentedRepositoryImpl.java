package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.PaymentedRepository;
import com.maxrocky.vesta.utility.Page;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liuwei on 2016/1/31.
 */
@Repository
public class PaymentedRepositoryImpl implements PaymentedRepository {
    @Autowired
    SessionFactory sessionFactory;
    private Session getSession(){
        return this.sessionFactory.getCurrentSession();
    }
    @Override
    public List<ViewAppBillDetailInfo> getBillDetailInfoBuyOwnerId(String ownerId) {

        String queryHql = "from ViewAppBillDetailInfo where ownerId='%s'";
        queryHql = String.format(queryHql,ownerId);

        List<ViewAppBillDetailInfo> viewAppBillDetailInfoList = (List<ViewAppBillDetailInfo>) getSession().createQuery(queryHql).setMaxResults(50).list();
        return viewAppBillDetailInfoList;
    }

    @Override
    public ARBillItem getPaymentTypeByBillItemID(String billItemId) {

        String queryHql = "from  ARBillItem where BillItemID='%s'";
        queryHql = String.format(queryHql,billItemId);

        return (ARBillItem) this.getSession().createQuery(queryHql).uniqueResult();
    }

    @Override
    public ViewAppHouseInfoEntityForPayment getViewAppHourseByHourceId(String hourseId) {

        String queryHql = "from ViewAppHouseInfoEntityForPayment where houseId='%s'";
        queryHql = String.format(queryHql,hourseId);

        return (ViewAppHouseInfoEntityForPayment) this.getSession().createQuery(queryHql).uniqueResult();
    }

    @Override
    public ViewAppBillDetailInfo getBillDetailByPaymentId(String paymentId) {

        String queryHql = "from ViewAppBillDetailInfo where paymentId='%s'";
        queryHql = String.format(queryHql,paymentId);
        return (ViewAppBillDetailInfo) this.getSession().createQuery(queryHql).uniqueResult();
    }

    @Override
    public ViewAppHouseInfoEntityForPayment getHouseInfoByHouseId(String hourseId) {

        String query = "from ViewAppHouseInfoEntityForPayment where houseId='%s'";
        query = String.format(query,hourseId);
        return (ViewAppHouseInfoEntityForPayment) this.getSession().createQuery(query).uniqueResult();
    }

    @Override
    public void saveBillInfo(BillInfoEntity billInfoEntity) {
        this.getSession().save(billInfoEntity);
    }

    @Override
    public BillInfoEntity getBillInfoById(String paymentId) {

        String quryHql = "from BillInfoEntity where id='%s'";
        quryHql = String.format(quryHql,paymentId);
        return (BillInfoEntity) this.getSession().createQuery(quryHql).uniqueResult();
    }

    @Override
    public void save(Object o) {
        this.getSession().save(o);
    }

    @Override
    public void update(Object o) {
        this.getSession().update(o);
    }

    @Override
    public List<ViewAppBillDetailInfo> get() {
        String queryHql = "from ViewAppBillDetailInfo";
        return this.getSession().createQuery(queryHql).list();
    }

    @Override
    public List<ViewAppBillDetailInfo> getViewAppBillDetailInfoByHouseIds(String s,String payEndDate) {

        String queryHql = "from ViewAppBillDetailInfo where hourseId in (%s) and payMoney>0 and payEndDate <= '%s' order by payStartDate";
        queryHql = String.format(queryHql,s,payEndDate);
        return this.getSession().createQuery(queryHql).list();
    }

    @Override
    public List<BillInfoEntity> getBillInfoByPaymentIds(String paymentIds) {

        String queryHql = "from BillInfoEntity where paymentId in(%s) and payResult = '%s'";
        queryHql = String.format(queryHql,paymentIds,ViewAppBillDetailInfo.call_back_status_success);
        return this.getSession().createQuery(queryHql).list();
    }

    @Override
    public List<BillInfoEntity> getBillInfoHistoryList(String userId, String projectId, Page page) {

        String queryHql = "from BillInfoEntity where payerUserId='%s' and projectId='%s'  and payResult='%S' order by callBackTime desc" ;
        queryHql = String.format(queryHql,userId,projectId,ViewAppBillDetailInfo.call_back_status_success);
        return this.getSession().createQuery(queryHql).setFirstResult(page.getFirstResult()).setMaxResults(page.getMaxResult()).list();
    }

    @Override
    public ViewAppHadBillDetialInfoEntity getViewAppHadBillDetialInfoEntityByPaymentId(String paymentId) {
        String queryHql = "from ViewAppHadBillDetialInfoEntity where payementId='%s'";
        queryHql = String.format(queryHql,paymentId);
        return (ViewAppHadBillDetialInfoEntity) this.getSession().createQuery(queryHql).uniqueResult();
    }

    @Override
    public BillInfoEntity getbillInfoSuccessByPaymentId(String paymentId) {

        String queryHql = "from BillInfoEntity where paymentId='%s' and payResult='%s'";
        queryHql = String.format(queryHql,paymentId, ViewAppBillDetailInfo.call_back_status_success);
        return (BillInfoEntity) this.getSession().createQuery(queryHql).uniqueResult();
    }

    @Override
    public ViewAppOwnerInfoEntity getOwnerInfoByOwnerId(String ownerId) {

        String queryHql = "from ViewAppOwnerInfoEntity where ownerId=%s";
        queryHql = String.format(queryHql,ownerId);

        return (ViewAppOwnerInfoEntity) this.getSession().createQuery(queryHql).uniqueResult();
    }

}
