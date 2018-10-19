package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseAreaEntity;
import com.maxrocky.vesta.domain.repository.HouseAreaRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by langmafeng on 2016/5/7/12:25.
 * 地块接口实现
 */
@Repository
public class HouseAreaRepositoryImpl implements HouseAreaRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据地块id去查询信息
     * CreateBy:langmafeng
     * CreateOn:2016/5/7/12:22.
     *
     * @param blockId
     */
    @Override
    public HouseAreaEntity getInfoByBlockId(String blockId) {
        String  hql="FROM HouseAreaEntity WHERE id='"+blockId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseAreaEntity> blockList=query.list();
        if(blockList.size()>0){
            return blockList.get(0);
        }
        return null;

    }

    /**
     * Describe:更新地块信息
     * CreatedBy:langmafeng
     * Describe:2016/5/7/12:22.
     *
     * @param houseAreaEntity
     */
    @Override
    public void updateHouseAreaInfo(HouseAreaEntity houseAreaEntity) {
        Session session = getCurrentSession();
        session.update(houseAreaEntity);
        session.flush();
        session.close();
    }

    /**
     * Describe:创建地块信息
     * CreateBy:langmafeng
     * CreateOn:2016/5/7/12:22.
     *
     * @param houseAreaEntity
     */
    @Override
    public void create(HouseAreaEntity houseAreaEntity) {
        Session session = getCurrentSession();
        session.save(houseAreaEntity);
        session.flush();
    }

    /**
     * Describe:根据项目id去查询信息
     *
     * @param projectId
     */
    @Override
    public List<HouseAreaEntity> getInfoByProjectId(String projectId) {
        String  hql="FROM HouseAreaEntity WHERE projectId='"+projectId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseAreaEntity> blockList=query.list();
        return blockList;

    }
}
