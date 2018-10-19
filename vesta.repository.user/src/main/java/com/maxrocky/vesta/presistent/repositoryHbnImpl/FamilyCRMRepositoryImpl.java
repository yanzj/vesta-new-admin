package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.FamilyCRMEntity;
import com.maxrocky.vesta.domain.repository.FamilyCRMRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liudongxin on 2016/4/28.
 */
@Repository
public class FamilyCRMRepositoryImpl implements FamilyCRMRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据id、会员编号获取信息
     * CreateBy:lingdongxin
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public FamilyCRMEntity get(String id, String memberId) {
        String hql="FROM FamilyCRMEntity WHERE id='"+id+"' and memberId='"+memberId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<FamilyCRMEntity> familyCRMList=query.list();
        if(familyCRMList.size()>0){
            return familyCRMList.get(0);
        }
        return null;
    }

    /**
     * Describe:创建家庭信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-19 10:01:12
     */
    @Override
    public void create(FamilyCRMEntity familyCRMEntity) {
        Session session = getCurrentSession();
        session.save(familyCRMEntity);
        session.flush();
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改家庭信息
     * ModifyBy:
     */
    @Override
    public void update(FamilyCRMEntity familyCRMEntity) {
        Session session = getCurrentSession();
        session.update(familyCRMEntity);
        session.flush();
        session.close();
    }
    /**
     * Describe:获取会员的个人信息
     * CreateBy:dl
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public List<FamilyCRMEntity> getFamilyInfo() {
        String hql="FROM FamilyCRMEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<FamilyCRMEntity> familyList=query.list();
        return familyList;
    }
}
