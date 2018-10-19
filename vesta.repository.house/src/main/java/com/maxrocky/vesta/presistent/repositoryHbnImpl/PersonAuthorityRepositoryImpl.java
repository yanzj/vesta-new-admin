package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.PersonnelAuthorityTimeEntity;
import com.maxrocky.vesta.domain.repository.PersonAuthorityRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by chen on 2016/9/1.
 */

@Repository
public class PersonAuthorityRepositoryImpl implements PersonAuthorityRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void savePersonAuthority(PersonnelAuthorityTimeEntity personnelAuthorityTimeEntity) {
        Session session = getCurrentSession();
        session.save(personnelAuthorityTimeEntity);
        session.flush();
    }

    @Override
    public void dumpAdd(PersonnelAuthorityTimeEntity personnelAuthorityTimeEntity) {
        String sql = "INSERT INTO personnel_authority_time(CURRENT_ID,GRADED,PARENT_ID,ROLE_GROUP,NAME,TIME_STAMP,START) VALUES(?,?,?,?,?,?,1) ON DUPLICATE KEY UPDATE START='1'";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setString(0,personnelAuthorityTimeEntity.getCurrentId());
        query.setString(1,personnelAuthorityTimeEntity.getGraded());
        query.setString(2,personnelAuthorityTimeEntity.getParentId());
        query.setString(3,personnelAuthorityTimeEntity.getRoleGroup());
        query.setString(4,personnelAuthorityTimeEntity.getName());
        query.setDate(5, personnelAuthorityTimeEntity.getTimeStamp());
        query.executeUpdate();
    }

    @Override
    public void delPersonAuthority(PersonnelAuthorityTimeEntity personnelAuthorityTimeEntity) {
        String hql = "update PersonnelAuthorityTimeEntity set start='0',timeStamp =:timeStamp where parentId=:parentId and roleGroup=:roleGroup and graded='2' and start='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("timeStamp",new Date());
        query.setParameter("parentId",personnelAuthorityTimeEntity.getParentId());
        query.setParameter("roleGroup",personnelAuthorityTimeEntity.getRoleGroup());
        query.executeUpdate();
    }

    @Override
    public List<PersonnelAuthorityTimeEntity> getPersonAuthoritys(PersonnelAuthorityTimeEntity personnelAuthorityTimeEntity) {
        return null;
    }
}
