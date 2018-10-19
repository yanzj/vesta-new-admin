package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseFloorEntity;
import com.maxrocky.vesta.domain.repository.HouseFloorRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by langmafeng on 2016/5/7/11:45.
 * 实现楼层接口
 */
@Repository
public class HouseFloorRepositoryImpl implements HouseFloorRepository {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据楼层id去查询信息
     * CreateBy:langmafeng
     * CreateOn:2016/5/7/11:37
     *
     * @param floorId
     */
    @Override
    public HouseFloorEntity getInfoByFloorId(String floorId) {
        String hql = "FROM HouseFloorEntity WHERE id='" + floorId + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseFloorEntity> floorList = query.list();
        if (floorList.size() > 0) {
            return floorList.get(0);
        }
        return null;
    }

    /**
     * Describe:更新楼层信息
     * CreatedBy:langmafeng
     * Describe:2016/5/7/11:37
     *
     * @param houseFloorEntity
     */
    @Override
    public void updateBuildingInfo(HouseFloorEntity houseFloorEntity) {
        Session session = getCurrentSession();
        session.update(houseFloorEntity);
        session.flush();
        session.close();
    }

    /**
     * Describe:创建楼层信息
     * CreateBy:langmafeng
     * CreateOn:2016/5/7/11:37
     *
     * @param houseFloorEntity
     */
    @Override
    public void create(HouseFloorEntity houseFloorEntity) {
        Session session = getCurrentSession();
        session.save(houseFloorEntity);
        session.flush();
    }

    @Override
    public List<HouseFloorEntity> getFloorListByUtilId(String unitId) {

        String hql = "FROM HouseFloorEntity WHERE unitCode = :unitId ORDER BY floorName ASC";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("unitId", unitId);
        return query.list();
    }

    @Override
    public List<HouseFloorEntity> getFloorListByUnitNum(String unitNum) {

        String hql = "FROM HouseFloorEntity WHERE unitCode = :unitNum ORDER BY floorName ASC";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("unitNum", unitNum);
        return query.list();
    }
}
