package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseLocationEntity;
import com.maxrocky.vesta.domain.model.HouseTypeLabelEntity;
import com.maxrocky.vesta.domain.repository.HouseTypeLabelRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by mql on 2016/6/3.
 */
@Repository
public class HouseTypeLabelRepositoryImpl extends HibernateDaoImpl implements HouseTypeLabelRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 添加
     * @param houseTypeLabelEntity
     */
    @Override
    public void saveHouseTypeLabel(HouseTypeLabelEntity houseTypeLabelEntity) {
        Session session = getCurrentSession();
        session.save(houseTypeLabelEntity);
        session.flush();
    }

    @Override
    public List<HouseTypeLabelEntity> getHouseTypeLabelListByTypeId(String typeId) {
        String hql = " from HouseTypeLabelEntity where typeId=:typeId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("typeId", typeId);
        List<HouseTypeLabelEntity> list = query.list();
        return list;
    }

    @Override
    public List<HouseLocationEntity> getHouseLocalocation(String type) {
        String hql = " from HouseLocationEntity where type=:typeId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("typeId", type);
        List<HouseLocationEntity> list = query.list();
        return list;
    }

    @Override
    public void deleteByTypeId(String typeId) {
        String hql = "delete from HouseTypeLabelEntity where typeId = :typeId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("typeId",typeId);
        query.executeUpdate();
    }

    @Override
    public List<Object[]> getByTypeId(String typeId) {
        StringBuffer sql = new StringBuffer("select ");
        sql.append(" label.id,label.x_num1,label.x_num2,label.y_num1,label.y_num2,loc.id as locationid,loc.name as locationname ");
        sql.append(" from house_type_label label ");
        sql.append(" left join room_location loc on label.name=loc.id ");
        sql.append(" where label.type_id = :typeId ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("typeId", typeId);
        return (List<Object[]>)query.list();
    }

}
