package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseCompanyEntity;
import com.maxrocky.vesta.domain.repository.HouseCompanyRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Tom on 2016/1/22 16:01.
 * Describe:公司Repository接口实现类
 */
@Repository
public class HouseCompanyRepositoryImpl implements HouseCompanyRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据公司名称获取公司
     * CreateBy:Tom
     * CreateOn:2016-01-22 04:03:14
     */
    @Override
    public HouseCompanyEntity getByCompanyName(String companyName) {
        String hql = "FROM HouseCompanyEntity WHERE name = :name";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("name", companyName);
        List<HouseCompanyEntity> houseCompanyEntityList = query.list();
        if(houseCompanyEntityList.size() == 0){
            return null;
        }

        return houseCompanyEntityList.get(0);
    }

    @Override
    public HouseCompanyEntity getCompanyById(String companyId) {
        String hql = "from HouseCompanyEntity as h where h.id = :id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id",companyId);
        if (query.list().size()>0){
            HouseCompanyEntity houseCompanyEntity = (HouseCompanyEntity)query.list().get(0);
            return  houseCompanyEntity;
        }
        return null;
    }
}
