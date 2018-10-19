package com.maxrocky.vesta.presistent.repositoryHbnlmpl;

import com.maxrocky.vesta.domain.model.VoucherEntity;
import com.maxrocky.vesta.domain.repository.VoucherRepository;
import com.maxrocky.vesta.utility.DateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chen on 2016/4/1.
 */
@Repository
public class VoucherRepositoryImpl implements VoucherRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<VoucherEntity> getVoucherList() {
        String hql = "from VoucherEntity where status = 2";
        Query query = getCurrentSession().createQuery(hql);
        List<VoucherEntity> voucherEntities = query.list();
        return voucherEntities;
    }

    @Override
    public VoucherEntity getVoucherDetail(String id) {
        String hql = "from VoucherEntity where id =:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        VoucherEntity voucherEntity = (VoucherEntity) query.uniqueResult();
        return voucherEntity;
    }

    @Override
    public void updateVoucher(VoucherEntity voucherEntity) {
        Session session = getCurrentSession();
        session.update(voucherEntity);
        session.flush();
    }

    @Override
    public List<VoucherEntity> getOverDueList() {
        String hql = "from VoucherEntity where endTime<=:endTime";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("endTime", DateUtils.getDate());
        List<VoucherEntity> voucherEntities = query.list();
        return voucherEntities;
    }
}
