package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.ViewAppChargeInfoEntity;
import com.maxrocky.vesta.domain.repository.ViewAppChargeInfoRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Tom on 2016/1/18 16:05.
 * Describe:基础合同信息Repository接口实现类
 */
@Repository
public class ViewAppChargeInfoRepositoryImpl implements ViewAppChargeInfoRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据房产Id获取有效的合同信息
     * CreateBy:Tom
     * CreateOn:2016-01-18 04:07:11
     */
    @Override
    public ViewAppChargeInfoEntity getByHouseId(int houseId) {
        StringBuffer hql = new StringBuffer();
        hql.append("FROM ViewAppChargeInfoEntity WHERE unitId = :houseId ");
        hql.append(" AND startDate <= NOW() ");
        hql.append(" AND (endDate IS NULL OR endDate >= NOW()) ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("houseId", houseId);
        List<ViewAppChargeInfoEntity> viewAppChargeInfoEntityList = query.list();
        if(viewAppChargeInfoEntityList.size() == 0){
            return null;
        }
        return viewAppChargeInfoEntityList.get(0);
    }
}
