package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.BillInfoEntity;
import com.maxrocky.vesta.domain.model.ViewAppBillDetailInfo;
import com.maxrocky.vesta.domain.repository.BillInfoRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuwei on 2016/3/5.
 */
@Repository
public class BillInfoRespositoryImpl extends HibernateDaoImpl implements BillInfoRepository {

    @Autowired
    SessionFactory sessionFactory;
    @Override
    public List<BillInfoEntity> getBillInfosByProject(String projectId, WebPage webPage) {

        String queryHql = "from BillInfoEntity where projectId=? and payResult=? order by callBackTime desc";
        List<Object> params = new ArrayList<>();
        params.add(String.valueOf(projectId));
        params.add(String.valueOf(ViewAppBillDetailInfo.call_back_status_success));
//        params.add(BillInfoEntity.BillInfoProcessStatus.proccess_not);
//        params.add(BillInfoEntity.BillInfoProcessStatus.proccess_did);
        return this.findByPage(queryHql, webPage, params);
    }

    @Override
    public void updateBillInfo(BillInfoEntity billInfoEntity) {
        Session session = sessionFactory.openSession();
        session.update(billInfoEntity);
        session.flush();
        session.close();

    }

    @Override
    public BillInfoEntity getBillInfoById(String billInfoId) {

        String quryHql = "from BillInfoEntity where id='%s'";
        quryHql = String.format(quryHql,billInfoId);
        return (BillInfoEntity) sessionFactory.openSession().createQuery(quryHql).uniqueResult();
    }
}
