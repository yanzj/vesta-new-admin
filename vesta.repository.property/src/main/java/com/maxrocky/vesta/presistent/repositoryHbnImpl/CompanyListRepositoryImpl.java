package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.repository.CompanyListRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 27978 on 2016/8/12.
 */
@Repository
public class CompanyListRepositoryImpl implements CompanyListRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取责任单位1列表
    */
    @Override
    public List<Object[]> getCompanyOnes(String projectId, String thirdId) {
        StringBuffer hql = new StringBuffer(" select ");
        hql.append(" supplier.id, supplier.name from SupplierEntity supplier, SupplierRelationshipEntity sre ");
        hql.append(" where supplier.id=sre.supplierId and sre.projectNum=:projectId and sre.thirdId=:thirdId ");
        Query query = this.getCurrentSession().createQuery(hql.toString());
        query.setParameter("projectId", projectId);
        query.setParameter("thirdId", thirdId);
        List<Object[]> list = query.list();
        return list;
    }
}
