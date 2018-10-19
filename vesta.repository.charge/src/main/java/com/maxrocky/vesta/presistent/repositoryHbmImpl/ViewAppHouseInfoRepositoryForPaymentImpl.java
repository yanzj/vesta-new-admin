package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.ViewAppHouseInfoEntityForPayment;
import com.maxrocky.vesta.domain.repository.ViewAppHouseInfoRepositoryForPayment;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Tom on 2016/1/18 15:38.
 * Describe:基础房产信息Repository接口实现类
 */
@Repository
public class ViewAppHouseInfoRepositoryForPaymentImpl implements ViewAppHouseInfoRepositoryForPayment {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据条件查询有效房产信息
     * CreateBy:Tom
     * CreateOn:2016-01-18 03:39:10
     */
    @Override
    public ViewAppHouseInfoEntityForPayment getByQuery(ViewAppHouseInfoEntityForPayment viewAppHouseInfoEntityForPayment) {
        StringBuffer hql = new StringBuffer();
        hql.append("FROM ViewAppHouseInfoEntityForPayment WHERE 1 = 1 ");
        hql.append(" AND projectName = :projectName ");
        hql.append(" AND formatName = :formatName ");
        hql.append(" AND blockName = :blockName ");
        hql.append(" AND cellNo = :cellNo ");
        hql.append(" AND houseNo = :houseNo ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("projectName", viewAppHouseInfoEntityForPayment.getProjectName());
        query.setParameter("formatName", viewAppHouseInfoEntityForPayment.getFormatName());
        query.setParameter("blockName", viewAppHouseInfoEntityForPayment.getBlockName());
        query.setParameter("cellNo", viewAppHouseInfoEntityForPayment.getCellNo());
        query.setParameter("houseNo", viewAppHouseInfoEntityForPayment.getHouseNo());
        List<ViewAppHouseInfoEntityForPayment> viewAppHouseInfoEntityForPaymentList = query.list();
        if(viewAppHouseInfoEntityForPaymentList.size() == 0){
            return null;
        }

        return viewAppHouseInfoEntityForPaymentList.get(0);
    }

    /**
     * Describe:根据业主Id查询有效房产列表
     * CreateBy:Tom
     * CreateOn:2016-01-22 03:44:39
     */
    @Override
    public List<ViewAppHouseInfoEntityForPayment> getListByOwnerId(int viewAppOwnerId) {
        StringBuffer hql = new StringBuffer();
        hql.append("SELECT vah FROM ViewAppHouseInfoEntityForPayment AS vah,ViewAppChargeInfoEntity AS vac,ViewAppOwnerInfoEntity AS vao WHERE 1 = 1 ");
        hql.append(" AND vah.houseId = vac.unitId ");
        hql.append(" AND vac.ownerId = vao.ownerId ");
        hql.append(" AND vac.startDate <= NOW() ");
        hql.append(" AND (vac.endDate IS NULL OR vac.endDate >= NOW()) ");
        hql.append(" AND vao.ownerId = :ownerId ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("ownerId", viewAppOwnerId);

        return query.list();
    }

    @Override
    public ViewAppHouseInfoEntityForPayment getHomeInfoByHouseId(String hourseId) {

        String queryHql = "from ViewAppHouseInfoEntityForPayment where houseId='%s'";
        queryHql = String.format(queryHql,hourseId);
        return (ViewAppHouseInfoEntityForPayment) this.getCurrentSession().createQuery(queryHql).uniqueResult();
    }
}
