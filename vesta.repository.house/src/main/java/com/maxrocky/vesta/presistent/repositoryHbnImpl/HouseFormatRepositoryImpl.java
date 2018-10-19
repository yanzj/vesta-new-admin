package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseFormatEntity;
import com.maxrocky.vesta.domain.repository.HouseFormatRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Tom on 2016/1/18 10:50.
 * Describe:业态Repository接口实体类
 */
@Repository
public class HouseFormatRepositoryImpl implements HouseFormatRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据项目Id获取所有业态列表
     * CreateBy:Tom
     * CreateOn:2016-01-18 10:50:40
     */
    @Override
    public List<HouseFormatEntity> getList(String projectId) {
        String hql = "FROM HouseFormatEntity WHERE projectId = :projectId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId", projectId);
        return query.list();
    }

    /**
     * Describe:根据业态Id获取业态
     * CreateBy:Tom
     * CreateOn:2016-01-18 03:10:33
     */
    @Override
    public HouseFormatEntity get(String formatId) {
        return (HouseFormatEntity)getCurrentSession().get(HouseFormatEntity.class, formatId);
    }

    /**
     * Describe:根据业态名称获取业态
     * CreateBy:Tom
     * CreateOn:2016-01-22 04:28:30
     */
    @Override
    public HouseFormatEntity getByName(String formatName) {
        String hql = "FROM HouseFormatEntity WHERE name = :name";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("name", formatName);
        List<HouseFormatEntity> houseFormatEntityList = query.list();
        if(houseFormatEntityList.size() == 0){
            return null;
        }

        return houseFormatEntityList.get(0);
    }

    /**
     * Describe:返回指定项目ID、业态名称的业态
     * CreateBy:Tom
     * CreateOn:2016-03-16 04:02:29
     */
    @Override
    public HouseFormatEntity getByProjectIdAndName(String projectId, String formatName) {
        String hql = "FROM HouseFormatEntity WHERE name = :name AND projectId = :projectId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("name", formatName);
        query.setParameter("projectId", projectId);
        List<HouseFormatEntity> houseFormatEntityList = query.list();
        if(houseFormatEntityList.size() == 0){
            return null;
        }

        return houseFormatEntityList.get(0);
    }
}
